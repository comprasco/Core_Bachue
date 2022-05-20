package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.NuevasMatriculasRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MutacionesTercerOrdenDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/03/2020
 */
public class NuevasMatriculasDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5546246128050165777L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.nuevasMatriculas";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(NuevasMatriculasDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad imto remote. */
	private NuevasMatriculasRemote inm_remote;

	/**
	 * Instancia un nuevo objeto mutaciones tercer orden delegado.
	 */
	public NuevasMatriculasDelegado()
	{
		inm_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NuevasMatriculasRemote getRemote()
	    throws B2BException
	{
		if(inm_remote == null)
		{
			try
			{
				inm_remote = (NuevasMatriculasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                               .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				inm_remote = null;
			}
		}

		return inm_remote;
	}
}
