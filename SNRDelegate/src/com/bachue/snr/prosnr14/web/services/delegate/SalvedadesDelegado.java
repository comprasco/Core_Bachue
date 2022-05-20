package com.bachue.snr.prosnr14.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.SalvedadesRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades SalvedadesDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class SalvedadesDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3775601849827437603L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.salvedadesCX";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SalvedadesDelegado.class, ProyectosCommon.COEXISTENCIA_14);

	/** Propiedad imrr remote. */
	private SalvedadesRemote isr_remote;

	/**
	 * Instancia un nuevo objeto matriculas relacionadas delegado.
	 */
	public SalvedadesDelegado()
	{
		isr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SalvedadesRemote getRemote()
	    throws B2BException
	{
		if(isr_remote == null)
		{
			try
			{
				isr_remote = (SalvedadesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                         .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				isr_remote = null;
			}
		}

		return isr_remote;
	}
}
