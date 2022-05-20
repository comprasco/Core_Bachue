package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.CambioPropietarioRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades CambioPropietarioDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
public class CambioPropietarioDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6284787934357019618L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.cambioPropietario";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CambioPropietarioDelegado.class, ProyectosCommon.CATASTRO_10);

	/** Propiedad icpr remote. */
	private CambioPropietarioRemote icpr_remote;

	/**
	 * Instancia un nuevo objeto cambio propietario delegado.
	 */
	public CambioPropietarioDelegado()
	{
		icpr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CambioPropietarioRemote getRemote()
	    throws B2BException
	{
		if(icpr_remote == null)
		{
			try
			{
				icpr_remote = (CambioPropietarioRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                 .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				icpr_remote = null;
			}
		}

		return icpr_remote;
	}
}
