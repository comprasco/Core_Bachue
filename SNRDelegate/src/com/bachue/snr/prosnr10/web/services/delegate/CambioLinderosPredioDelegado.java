package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.CambioLinderosPredioRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades CambioLinderosPredioDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/03/2020
 */
public class CambioLinderosPredioDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8422897310619291694L;

	/** Constante JNDI NAME. */
	private static final String JNDI_NAME = "jndi.cambioLinderosPredio";

	/** Constante clh LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(CambioLinderosPredioDelegado.class, ProyectosCommon.CATASTRO_10);

	/**  Propiedad is remote. */
	private CambioLinderosPredioRemote iclpr_remote;

	/**
	 * Instancia un nuevo objeto mutaciones tercer orden delegado.
	 */
	public CambioLinderosPredioDelegado()
	{
		iclpr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CambioLinderosPredioRemote getRemote()
	    throws B2BException
	{
		if(iclpr_remote == null)
		{
			try
			{
				iclpr_remote = (CambioLinderosPredioRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                     .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iclpr_remote = null;
			}
		}

		return iclpr_remote;
	}
}
