package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.PartesInteresadasRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades PartesInteresadasDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/03/2020
 */
public class PartesInteresadasDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6399005831976230326L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.partesInteresadas";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(PartesInteresadasDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad imto remote. */
	private PartesInteresadasRemote ipir_remote;

	/**
	 * Instancia un nuevo objeto RRR matriculas delegado.
	 */
	public PartesInteresadasDelegado()
	{
		ipir_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PartesInteresadasRemote getRemote()
	    throws B2BException
	{
		if(ipir_remote == null)
		{
			try
			{
				ipir_remote = (PartesInteresadasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                 .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ipir_remote = null;
			}
		}

		return ipir_remote;
	}
}
