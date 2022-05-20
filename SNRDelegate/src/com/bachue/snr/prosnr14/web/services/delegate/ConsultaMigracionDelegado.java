package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.ConsultaMigracionRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades MatriculasRelacionadasDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/03/2020
 */
public class ConsultaMigracionDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7438207203006867399L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.consultaMigracion";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaMigracionDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad icmr remote. */
	private ConsultaMigracionRemote icmr_remote;

	/**
	 * Instancia un nuevo objeto consulta migracion delegado.
	 */
	public ConsultaMigracionDelegado()
	{
		icmr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaMigracionRemote getRemote()
	    throws B2BException
	{
		if(icmr_remote == null)
		{
			try
			{
				icmr_remote = (ConsultaMigracionRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                 .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				icmr_remote = null;
			}
		}

		return icmr_remote;
	}
}
