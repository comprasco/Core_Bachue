package com.bachue.snr.prosnr01.web.delegate.crearTurno;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.crearTurno.CrearTurnoRemote;


/**
 * Clase que contiene todos las propiedades CrearTurnoDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
public class CrearTurnoDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2138456841326203927L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.crearTurno";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CrearTurnoDelegado.class);

	/** Propiedad ict remote. */
	private CrearTurnoRemote ict_remote;

	/**
	 * Instancia un nuevo objeto crear turno delegado.
	 */
	public CrearTurnoDelegado()
	{
		ict_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CrearTurnoRemote getRemote()
	    throws B2BException
	{
		if(ict_remote == null)
		{
			try
			{
				ict_remote = (CrearTurnoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                         .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ict_remote = null;
			}
		}

		return ict_remote;
	}
}
