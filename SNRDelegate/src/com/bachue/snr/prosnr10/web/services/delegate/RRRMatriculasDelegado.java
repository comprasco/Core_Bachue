package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.RRRMatriculasRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades RRRMatriculasDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/03/2020
 */
public class RRRMatriculasDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4472836193747523779L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.RRRMatriculas";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RRRMatriculasDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad imto remote. */
	private RRRMatriculasRemote irrrmr_remote;

	/**
	 * Instancia un nuevo objeto RRR matriculas delegado.
	 */
	public RRRMatriculasDelegado()
	{
		irrrmr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RRRMatriculasRemote getRemote()
	    throws B2BException
	{
		if(irrrmr_remote == null)
		{
			try
			{
				irrrmr_remote = (RRRMatriculasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                               .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				irrrmr_remote = null;
			}
		}

		return irrrmr_remote;
	}
}
