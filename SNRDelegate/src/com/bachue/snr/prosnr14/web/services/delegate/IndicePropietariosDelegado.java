package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.IndicePropietariosRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades IndicePropietariosDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class IndicePropietariosDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2857187038685760231L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.indicePropietarios";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(IndicePropietariosDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad iip remote. */
	private IndicePropietariosRemote iip_remote;

	/**
	 * Instancia un nuevo objeto indice propietarios delegado.
	 */
	public IndicePropietariosDelegado()
	{
		iip_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public IndicePropietariosRemote getRemote()
	    throws B2BException
	{
		if(iip_remote == null)
		{
			try
			{
				iip_remote = (IndicePropietariosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                 .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iip_remote = null;
			}
		}

		return iip_remote;
	}
}
