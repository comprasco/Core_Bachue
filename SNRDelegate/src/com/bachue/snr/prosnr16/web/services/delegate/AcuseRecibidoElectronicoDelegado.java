package com.bachue.snr.prosnr16.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr16.ejb.session.stateless.cyn.AcuseRecibidoElectronicoRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades AcuseRecibidoElectronicoDelegado.
 *
 * @author  Carlos Calder?n
 * Fecha de Creacion: 30/03/2020
 */
public class AcuseRecibidoElectronicoDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1056112732602984259L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.acuseRecibidoElectronico";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(AcuseRecibidoElectronicoDelegado.class, ProyectosCommon.COMUNICACIONES_Y_NOTIFICACIONES_16);

	/** Propiedad iar remote. */
	private AcuseRecibidoElectronicoRemote iarer_remote;

	/**
	 * Instancia un nuevo objeto acuse recibido electronico delegado.
	 */
	public AcuseRecibidoElectronicoDelegado()
	{
		iarer_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AcuseRecibidoElectronicoRemote getRemote()
	    throws B2BException
	{
		if(iarer_remote == null)
		{
			try
			{
				iarer_remote = (AcuseRecibidoElectronicoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                         .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iarer_remote = null;
			}
		}

		return iarer_remote;
	}
}
