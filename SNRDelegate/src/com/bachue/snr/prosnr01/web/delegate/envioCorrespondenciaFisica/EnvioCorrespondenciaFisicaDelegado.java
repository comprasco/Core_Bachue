package com.bachue.snr.prosnr01.web.delegate.envioCorrespondenciaFisica;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.envioCorrespondenciaFisica.EnvioCorrespondenciaFisicaRemote;


/**
 * Clase que contiene todos las propiedades EnvioCorrespondenciaFisicaDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
public class EnvioCorrespondenciaFisicaDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8372514215144057478L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.envioCorrespondenciaFisica";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EnvioCorrespondenciaFisicaDelegado.class);

	/** Propiedad iecf remote. */
	private EnvioCorrespondenciaFisicaRemote iecf_remote;

	/**
	 * Instancia un nuevo objeto envio correspondencia fisica delegado.
	 */
	public EnvioCorrespondenciaFisicaDelegado()
	{
		iecf_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EnvioCorrespondenciaFisicaRemote getRemote()
	    throws B2BException
	{
		if(iecf_remote == null)
		{
			try
			{
				iecf_remote = (EnvioCorrespondenciaFisicaRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                          .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iecf_remote = null;
			}
		}

		return iecf_remote;
	}
}
