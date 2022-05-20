package com.bachue.snr.prosnr01.web.delegate.envioCorrespondenciaElectronica;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.envioCorrespondenciaElectronica.EnvioCorrespondenciaElectronicaRemote;


/**
 * Clase que contiene todos las propiedades EnvioCorrespondenciaFisicaDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
public class EnvioCorrespondenciaElectronicaDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8677729823681172296L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.envioCorrespondenciaElectronica";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioCorrespondenciaElectronicaDelegado.class);

	/** Propiedad iecf remote. */
	private EnvioCorrespondenciaElectronicaRemote iece_remote;

	/**
	 * Instancia un nuevo objeto envio correspondencia Electronica delegado.
	 */
	public EnvioCorrespondenciaElectronicaDelegado()
	{
		iece_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EnvioCorrespondenciaElectronicaRemote getRemote()
	    throws B2BException
	{
		if(iece_remote == null)
		{
			try
			{
				iece_remote = (EnvioCorrespondenciaElectronicaRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                               .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iece_remote = null;
			}
		}

		return iece_remote;
	}
}
