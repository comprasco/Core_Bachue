package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.TramitesEnCursoRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades TramitesEnCursoDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class TramitesEnCursoDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6117640608487635431L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.tramitesEnCurso";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(TramitesEnCursoDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad itecr remote. */
	private TramitesEnCursoRemote itecr_remote;

	/**
	 * Instancia un nuevo objeto tramites en curso delegado.
	 */
	public TramitesEnCursoDelegado()
	{
		itecr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TramitesEnCursoRemote getRemote()
	    throws B2BException
	{
		if(itecr_remote == null)
		{
			try
			{
				itecr_remote = (TramitesEnCursoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				itecr_remote = null;
			}
		}

		return itecr_remote;
	}
}
