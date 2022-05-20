package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.MutacionesCuartoOrdenRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MutacionesCuartoOrdenDelegado.
 *
 * @author Carlos Calderón
 * Fecha de Creacion: 4/03/2020
 */
public class MutacionesCuartoOrdenDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8201954272915368071L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.mutacionesCuartoOrden";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MutacionesCuartoOrdenDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad imco remote. */
	private MutacionesCuartoOrdenRemote imco_remote;

	/**
	 * Instancia un nuevo objeto mutaciones cuarto orden delegado.
	 */
	public MutacionesCuartoOrdenDelegado()
	{
		imco_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MutacionesCuartoOrdenRemote getRemote()
	    throws B2BException
	{
		if(imco_remote == null)
		{
			try
			{
				imco_remote = (MutacionesCuartoOrdenRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                     .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				imco_remote = null;
			}
		}

		return imco_remote;
	}
}
