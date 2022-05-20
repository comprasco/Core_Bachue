package com.bachue.snr.prosnr16.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr16.ejb.session.stateless.cyn.AcuseRecibidoFisicoRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoFisicoDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 26/03/2020
 */
public class AcuseRecibidoFisicoDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2660066435190135933L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.acuseRecibidoFisico";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AcuseRecibidoFisicoDelegado.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** Propiedad iar remote. */
	private AcuseRecibidoFisicoRemote iarfr_remote;

	/**
	 * Instancia un nuevo objeto acuse recibido fisico delegado.
	 */
	public AcuseRecibidoFisicoDelegado()
	{
		iarfr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AcuseRecibidoFisicoRemote getRemote()
	    throws B2BException
	{
		if(iarfr_remote == null)
		{
			try
			{
				iarfr_remote = (AcuseRecibidoFisicoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                    .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iarfr_remote = null;
			}
		}

		return iarfr_remote;
	}
}
