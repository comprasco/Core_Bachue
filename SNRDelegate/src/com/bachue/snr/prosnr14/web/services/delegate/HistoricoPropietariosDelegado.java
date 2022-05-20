package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.HistoricoPropietariosRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades HistoricoPropietariosDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class HistoricoPropietariosDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8248303617887976965L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.historicoPropietarios";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(HistoricoPropietariosDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad ihpr remote. */
	private HistoricoPropietariosRemote ihpr_remote;

	/**
	 * Instancia un nuevo objeto historico propietarios delegado.
	 */
	public HistoricoPropietariosDelegado()
	{
		ihpr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public HistoricoPropietariosRemote getRemote()
	    throws B2BException
	{
		if(ihpr_remote == null)
		{
			try
			{
				ihpr_remote = (HistoricoPropietariosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                     .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ihpr_remote = null;
			}
		}

		return ihpr_remote;
	}
}
