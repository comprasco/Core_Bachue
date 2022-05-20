package com.bachue.snr.prosnr06.web.listeners;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.common.scheduler.SNRClusterScheduler;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;


/**
 * Gestor de acciones de inicio y detención de la aplicación
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creación: 12/09/2019
 */
public class ListenerManager implements javax.servlet.ServletContextListener
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ListenerManager.class);

	/** Llave de atrivuto a la cual se asociará el gestor de tareas automaticas */
	private static String cs_SCHEDULER_CONTAINER = "SCHEDULER_CONTAINER";

	/** {@inheritDoc} */
	public void contextDestroyed(ServletContextEvent asce_evento)
	{
		stopScheduler(asce_evento.getServletContext());
	}

	/** {@inheritDoc} */
	public void contextInitialized(ServletContextEvent asce_evento)
	{
		System.setProperty("java.awt.headless", "true");

		try
		{
			com.bachue.snr.prosnr01.model.sdb.pgn.Constantes lc_constante;

			lc_constante = new com.bachue.snr.prosnr01.web.delegate.reference.ReferenceDelegate().getRemote()
					                                                                                 .findConstantesById(
					    com.bachue.snr.prosnr01.common.constants.ConstanteCommon.CONFIGURACION_IDIOMA_PAIS
					);

			if(lc_constante != null)
			{
				String ls_caracter;
				ls_caracter = lc_constante.getCaracter();

				if(com.b2bsg.common.util.StringUtils.isValidString(ls_caracter))
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
		catch(com.b2bsg.common.exception.B2BException b2b2e_e)
		{
			clh_LOGGER.error("contextInitialized", b2b2e_e);
		}

		initScheduler(asce_evento.getServletContext());
	}

	/**
	 * Obtener el gestor de tareas automáticas
	 *
	 * @param asc_context Contexto de aplicaciones donde se buscará el gestor de tareas automaticas
	 */
	private SNRClusterScheduler getScheduler(ServletContext asc_contexto)
	{
		Object              lo_tmp;
		SNRClusterScheduler lsnrcs_scheduler;

		lo_tmp               = (asc_contexto != null) ? asc_contexto.getAttribute(cs_SCHEDULER_CONTAINER) : null;
		lsnrcs_scheduler     = null;

		if((lo_tmp != null) && lo_tmp instanceof SNRClusterScheduler)
			lsnrcs_scheduler = (SNRClusterScheduler)lo_tmp;

		return lsnrcs_scheduler;
	}

	/**
	 * Inicio de ejecución de tareas automaticas
	 *
	 * @param asc_contexto Contexto de aplicaciones donde se iniciara la ejecución las tareas automaticas
	 */
	private void initScheduler(ServletContext asc_contexto)
	{
		if(asc_contexto != null)
		{
			SNRClusterScheduler lsnrcs_scheduler;

			lsnrcs_scheduler = new com.bachue.snr.prosnr06.web.Scheduler.EnvioDocumentoScheduler();

			lsnrcs_scheduler.iniciarScheduler(asc_contexto.getServletContextName());

			asc_contexto.setAttribute(cs_SCHEDULER_CONTAINER, lsnrcs_scheduler);
		}
	}

	/**
	 * Detener la ejecución de tareas automaticas
	 *
	 * @param asc_contexto Contexto de aplicaciones donde se detendra la ejecucion de tareas automáticas
	 */
	private void stopScheduler(ServletContext asc_contexto)
	{
		if(asc_contexto != null)
		{
			SNRClusterScheduler lsnrcs_scheduler;

			lsnrcs_scheduler = getScheduler(asc_contexto);

			if(lsnrcs_scheduler != null)
			{
				lsnrcs_scheduler.detenerSchedule();

				asc_contexto.removeAttribute(cs_SCHEDULER_CONTAINER);
			}
		}
	}
}
