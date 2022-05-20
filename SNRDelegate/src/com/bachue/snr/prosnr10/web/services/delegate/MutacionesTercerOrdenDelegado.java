package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.MutacionesTercerOrdenRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MutacionesTercerOrdenDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/03/2020
 */
public class MutacionesTercerOrdenDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5546246128050165777L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.mutacionesTercerOrden";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MutacionesTercerOrdenDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad imto remote. */
	private MutacionesTercerOrdenRemote imto_remote;

	/**
	 * Instancia un nuevo objeto mutaciones tercer orden delegado.
	 */
	public MutacionesTercerOrdenDelegado()
	{
		imto_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MutacionesTercerOrdenRemote getRemote()
	    throws B2BException
	{
		if(imto_remote == null)
		{
			try
			{
				imto_remote = (MutacionesTercerOrdenRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                     .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				imto_remote = null;
			}
		}

		return imto_remote;
	}
}
