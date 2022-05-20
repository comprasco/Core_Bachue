package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.MatriculasRelacionadasRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MatriculasRelacionadasDelegado.
 *
 * @author  Carlos Calder�n
 * Fecha de Creacion: 10/03/2020
 */
public class MatriculasRelacionadasDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3563092403389730024L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.matriculasRelacionadas";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(MatriculasRelacionadasDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad imrr remote. */
	private MatriculasRelacionadasRemote imrr_remote;

	/**
	 * Instancia un nuevo objeto matriculas relacionadas delegado.
	 */
	public MatriculasRelacionadasDelegado()
	{
		imrr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MatriculasRelacionadasRemote getRemote()
	    throws B2BException
	{
		if(imrr_remote == null)
		{
			try
			{
				imrr_remote = (MatriculasRelacionadasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                      .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				imrr_remote = null;
			}
		}

		return imrr_remote;
	}
}
