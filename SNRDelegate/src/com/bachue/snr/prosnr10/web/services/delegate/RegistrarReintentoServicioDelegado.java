package com.bachue.snr.prosnr10.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.RegistrarReintentoServicioRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades RegistrarReintentoServicioDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
public class RegistrarReintentoServicioDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3806952805709518543L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.registrarReintentoServicio";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RegistrarReintentoServicioDelegado.class, ProyectosCommon.CATASTRO_10);

	/** Propiedad icpr remote. */
	private RegistrarReintentoServicioRemote icpr_remote;

	/**
	 * Instancia un nuevo objeto registrar reintento servicio delegado.
	 */
	public RegistrarReintentoServicioDelegado()
	{
		icpr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistrarReintentoServicioRemote getRemote()
	    throws B2BException
	{
		if(icpr_remote == null)
		{
			try
			{
				icpr_remote = (RegistrarReintentoServicioRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
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
