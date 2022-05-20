package com.bachue.snr.prosnr02.web.listeners;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr02.ejb.session.stateless.workflow.WorkflowRemote;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;

import javax.servlet.http.HttpSessionEvent;


/**
 * Clase que contiene todos las funcionalidades de ListenerManager.
 *
 * @author Julian Vaca
 */
public class ListenerManager implements javax.servlet.ServletContextListener, javax.servlet.http.HttpSessionListener,
	javax.servlet.ServletRequestListener
{
	/** Propiedad cs SCHEDULE R CONTAINER. */
	private static String cs_SCHEDULER_CONTAINER = "SCHEDULER_CONTAINER";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ListenerManager.class);

	/**
	 * Instancia un nuevo objeto listener manager.
	 */
	public ListenerManager()
	{
		super();
	}

	/** {@inheritdoc} */
	public void contextDestroyed(ServletContextEvent asce_event)
	{
		SchedulerContainer lsc_contenedor;

		lsc_contenedor = getSchedulerContainer(asce_event.getServletContext());

		if(lsc_contenedor != null)
			lsc_contenedor.stopSchedulers();
	}

	/** {@inheritdoc} */
	public void contextInitialized(ServletContextEvent asce_event)
	{
		System.setProperty("java.awt.headless", "true");

		try
		{
			WorkflowRemote lrr_workflowRemote;
			Constantes     lc_constante;

			lrr_workflowRemote     = new com.bachue.snr.prosnr02.web.delegate.workflow.WorkflowDelegate().getRemote();
			lc_constante           = lrr_workflowRemote.findConstantesById(ConstanteCommon.CONFIGURACION_IDIOMA_PAIS);

			if(lc_constante != null)
			{
				String ls_caracter;
				ls_caracter = lc_constante.getCaracter();

				if(StringUtils.isValidString(ls_caracter))
				{
					String   ls_idioma;
					String   ls_pais;
					String[] ls_temp;

					ls_temp       = ls_caracter.split("-");
					ls_idioma     = ls_temp[0];
					ls_pais       = ls_temp[1];

					Locale.setDefault(new Locale(ls_idioma, ls_pais));
				}
			}
			else
				Locale.setDefault(new Locale("es", "CO"));
		}
		catch(B2BException b2b2e_e)
		{
			clh_LOGGER.error("contextInitialized", b2b2e_e);
		}
	}

	/** {@inheritDoc} */
	public void requestDestroyed(ServletRequestEvent asre_event)
	{
	}

	/** {@inheritDoc} */
	public void requestInitialized(ServletRequestEvent asre_event)
	{
		javax.servlet.ServletContext lsc_context;

		lsc_context = asre_event.getServletContext();

		if(lsc_context != null)
		{
			SchedulerContainer lsc_contenedor;

			lsc_contenedor = getSchedulerContainer(lsc_context);

			if(lsc_contenedor == null)
			{
				lsc_contenedor = SchedulerContainer.getInstance();

				lsc_context.setAttribute(cs_SCHEDULER_CONTAINER, lsc_contenedor);
			}

			if(!lsc_contenedor.isIniciado())
			{
				ServletRequest lsr_request;

				lsr_request = asre_event.getServletRequest();

				if(lsr_request != null)
					lsc_contenedor.initSchedulers(lsr_request.getLocalPort());
			}
		}
	}

	/** {@inheritdoc} */
	public void sessionCreated(HttpSessionEvent ahse_event)
	{
	}

	/** {@inheritdoc} */
	public void sessionDestroyed(HttpSessionEvent ahse_event)
	{
	}

	/**
	 * Retorna el valor de scheduler container.
	 *
	 * @param asc_context correspondiente al valor del tipo de objeto ServletContext
	 * @return el valor de scheduler container
	 */
	private SchedulerContainer getSchedulerContainer(ServletContext asc_context)
	{
		Object             lo_tmp;
		SchedulerContainer lsc_contenedor;

		lo_tmp             = asc_context.getAttribute(cs_SCHEDULER_CONTAINER);
		lsc_contenedor     = null;

		if((lo_tmp != null) && lo_tmp instanceof SchedulerContainer)
			lsc_contenedor = (SchedulerContainer)lo_tmp;

		return lsc_contenedor;
	}
}
