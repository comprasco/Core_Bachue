package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.SalvedadesRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades SalvedadesDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/03/2020
 */
public class SalvedadesDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 939876180239943477L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.salvedades";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SalvedadesDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad is remote. */
	private SalvedadesRemote is_remote;

	/**
	 * Instancia un nuevo objeto mutaciones tercer orden delegado.
	 */
	public SalvedadesDelegado()
	{
		is_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SalvedadesRemote getRemote()
	    throws B2BException
	{
		if(is_remote == null)
		{
			try
			{
				is_remote = (SalvedadesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                        .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				is_remote = null;
			}
		}

		return is_remote;
	}
}
