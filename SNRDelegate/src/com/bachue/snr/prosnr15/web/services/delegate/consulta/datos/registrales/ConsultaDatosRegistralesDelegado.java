package com.bachue.snr.prosnr15.web.services.delegate.consulta.datos.registrales;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr15.ejb.session.stateless.consulta.datos.registrales.ConsultaDatosRegistralesRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades ConsultaDatosRegistralesDelegado.
 *
 * @author Gabriel Arias
 */
public class ConsultaDatosRegistralesDelegado implements Serializable
{
	private static final long              serialVersionUID = 6844416750302289856L;
	private static final String            JNDI_NAME        = "jndi.consultaDatosRegistrales";
	private static final LoggerHandler     clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaDatosRegistralesDelegado.class, ProyectosCommon.CONSULTA_DATOS_REGISTRALES_15);
	private ConsultaDatosRegistralesRemote icdr_remote;

	/**
	 * Método constructor.
	 */
	public ConsultaDatosRegistralesDelegado()
	{
		icdr_remote                                         = null;
	}

	/**
	 * Método encargado de consultar el remote.
	 *
	 * @return retorna el remote.
	 * @throws B2BException
	 */
	public ConsultaDatosRegistralesRemote getRemote()
	    throws B2BException
	{
		if(icdr_remote == null)
		{
			try
			{
				icdr_remote = (ConsultaDatosRegistralesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                        .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				icdr_remote = null;
			}
		}

		return icdr_remote;
	}
}
