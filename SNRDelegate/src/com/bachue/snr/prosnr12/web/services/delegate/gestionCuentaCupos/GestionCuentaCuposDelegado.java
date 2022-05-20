package com.bachue.snr.prosnr12.web.services.delegate.gestionCuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr12.ejb.session.stateless.gestionCuentaCupos.GestionCuentaCuposRemote;

import java.io.Serializable;


/**
 * Clase para el acceso a la interfaz remota del servicio web Gestion Cuenta Cupos.
 *
 * @author Manuel Blanco
 *
 */
public class GestionCuentaCuposDelegado implements Serializable
{
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 4120275520263534178L;

	/** Constante JNDI_NAME */
	private static final String JNDI_NAME = "jndi.gestionCuentaCupos";

	/** Constante clh_LOGGER */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GestionCuentaCuposDelegado.class, ProyectosCommon.GESTION_CUENTA_CUPOS_12);

	/** Propiedad igccr_remote */
	private GestionCuentaCuposRemote igccr_remote;

	/**
	 * Constructor por defecto
	 */
	public GestionCuentaCuposDelegado()
	{
		igccr_remote = null;
	}

	/**
	 * Obtiene la interfaz remota del servicio web Gestion Cuenta Cupos.
	 *
	 * @return Interfaz remota relacionada al JNDI_NAME
	 * @throws B2BException Si ocurre un error en la busqueda de la interfaz remota
	 */
	public GestionCuentaCuposRemote getRemote()
	    throws B2BException
	{
		if(igccr_remote == null)
		{
			try
			{
				igccr_remote = (GestionCuentaCuposRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                   .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igccr_remote = null;
			}
		}

		return igccr_remote;
	}
}
