package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.MutacionesQuintoOrdenRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MutacionesQuintoOrdenDelegado.
 *
 * @author  Carlos Calder�n
 * Fecha de Creacion: 4/03/2020
 */
public class MutacionesQuintoOrdenDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4427580769602655478L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.mutacionesQuintoOrden";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MutacionesQuintoOrdenDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad imqo remote. */
	private MutacionesQuintoOrdenRemote imqo_remote;

	/**
	 * Instancia un nuevo objeto mutaciones tercer orden delegado.
	 */
	public MutacionesQuintoOrdenDelegado()
	{
		imqo_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MutacionesQuintoOrdenRemote getRemote()
	    throws B2BException
	{
		if(imqo_remote == null)
		{
			try
			{
				imqo_remote = (MutacionesQuintoOrdenRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                     .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				imqo_remote = null;
			}
		}

		return imqo_remote;
	}
}
