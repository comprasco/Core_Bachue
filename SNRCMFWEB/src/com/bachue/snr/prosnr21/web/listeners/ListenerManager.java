package com.bachue.snr.prosnr21.web.listeners;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.common.scheduler.SNRClusterScheduler;

import com.bachue.snr.prosnr21.web.listeners.scheduler.ConciliacionesScheduler;
import com.bachue.snr.prosnr21.web.util.ContextHelper;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;


/**
 * Gestor de acciones de inicio y detención de la aplicación
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
public class ListenerManager implements javax.servlet.ServletContextListener
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ListenerManager.class);

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
			String ls_constante;

			ls_constante = new com.bachue.snr.prosnr21.web.services.delegate.ParameterDelegate().getRemote()
					                                                                                .obtenerCaracterConstante(
					    com.bachue.snr.prosnr01.common.constants.ConstanteCommon.CONFIGURACION_IDIOMA_PAIS
					);

			if(com.b2bsg.common.util.StringUtils.isValidString(ls_constante))
			{
				String   ls_idioma;
				String   ls_pais;
				String[] ls_temp;

				ls_temp       = ls_constante.split("-");
				ls_idioma     = ls_temp[0];
				ls_pais       = ls_temp[1];

				Locale.setDefault(new Locale(ls_idioma, ls_pais));
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
	 * Inicio de ejecución de tareas automaticas
	 *
	 * @param asc_contexto Contexto de aplicaciones donde se iniciara la ejecución las tareas automaticas
	 */
	private void initScheduler(ServletContext asc_contexto)
	{
		if(asc_contexto != null)
		{
			ConciliacionesScheduler lcs_scheduler;

			lcs_scheduler = new ConciliacionesScheduler();

			lcs_scheduler.iniciarScheduler(asc_contexto.getServletContextName());

			ContextHelper.setScheduler(asc_contexto, lcs_scheduler);
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

			lsnrcs_scheduler = ContextHelper.getSchedulerContainer(asc_contexto);

			if(lsnrcs_scheduler != null)
			{
				lsnrcs_scheduler.detenerSchedule();

				ContextHelper.removetSchedulerContainer(asc_contexto);
			}
		}
	}
}
