package com.bachue.snr.prosnr21.web.util;

import com.bachue.snr.prosnr21.web.listeners.scheduler.ConciliacionesScheduler;

import javax.servlet.ServletContext;


/**
 * Utilidades de gesti�n de objetos a nivel de aplicacion
 *
 * @author Edgar Prieto
 */
public class ContextHelper
{
	/** Identificador de agendador de tareas autom�ticas */
	private static final String cs_SCHEDULER_CONTAINER = "SCHEDULER_CONTAINER";

	/**
	 * Adiciona a la aplicaci�n el contenedor de Jobs de ejecuci�n.
	 *
	 * @param asc_contexto correspondiente al valor del tipo de objeto ServletContext
	 * @param acs_scheduler Agendador de tareas autom�ticas
	 */
	public static void setScheduler(ServletContext asc_contexto, ConciliacionesScheduler acs_scheduler)
	{
		setAttribute(asc_contexto, cs_SCHEDULER_CONTAINER, acs_scheduler);
	}

	/**
	 * Retorna el agendador de tareas autom�ticas
	 *
	 * @param asc_contexto correspondiente al valor del tipo de objeto ServletContext
	 * @return Agendador de tareas automaticas
	 */
	public static ConciliacionesScheduler getSchedulerContainer(ServletContext asc_contexto)
	{
		Object lo_scheduler;

		lo_scheduler = getAttribute(asc_contexto, cs_SCHEDULER_CONTAINER);

		return ((lo_scheduler != null) && lo_scheduler instanceof ConciliacionesScheduler)
		? (ConciliacionesScheduler)lo_scheduler : null;
	}

	/**
	 * Retorna el agendador de tareas autom�ticas
	 *
	 * @param asc_contexto correspondiente al valor del tipo de objeto ServletContext
	 * @return Agendador de tareas automaticas
	 */
	public static ConciliacionesScheduler removetSchedulerContainer(ServletContext asc_contexto)
	{
		Object lo_scheduler;

		lo_scheduler = getAttribute(asc_contexto, cs_SCHEDULER_CONTAINER);

		return ((lo_scheduler != null) && lo_scheduler instanceof ConciliacionesScheduler)
		? (ConciliacionesScheduler)lo_scheduler : null;
	}

	/**
	 * Asocia a nivel de aplicacion un atributo un id
	 *
	 * @param asc_contexto Contenedor de objetos de aplicaci�n
	 * @param as_id Id con el cual se asociar� el atributo
	 * @param ao_atributo Atributo a asociar a nivel de aplicaci�n
	 * @return el valor de scheduler container
	 */
	private static void setAttribute(ServletContext asc_contexto, String as_id, Object ao_atributo)
	{
		if(asc_contexto != null)
			asc_contexto.setAttribute(as_id, ao_atributo);
	}

	/**
	 * Retorna el objeto a nivel de aplicaci�n asociado al id
	 *
	 * @param asc_contexto Contenedor de objetos de aplicaci�n
	 * @param as_id Llave del objeto a identificar
	 * @return el valor de scheduler container
	 */
	private static Object getAttribute(ServletContext asc_contexto, String as_id)
	{
		return (asc_contexto != null) ? asc_contexto.getAttribute(as_id) : null;
	}
}
