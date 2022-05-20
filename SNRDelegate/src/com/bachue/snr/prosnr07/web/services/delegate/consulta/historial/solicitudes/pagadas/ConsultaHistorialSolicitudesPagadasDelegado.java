package com.bachue.snr.prosnr07.web.services.delegate.consulta.historial.solicitudes.pagadas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr07.ejb.session.stateless.consulta.historial.solicitudes.pagadas.ConsultaHistorialSolicitudesPagadasRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades ConsultaHistorialSolicitudesPagadasDelegado.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 27/05/2020
 */
public class ConsultaHistorialSolicitudesPagadasDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -141179119668754605L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.consultaHistorialSolicitudesPagadas";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaHistorialSolicitudesPagadasDelegado.class);

	/** Propiedad ichspr remote. */
	private ConsultaHistorialSolicitudesPagadasRemote ichspr_remote;

	/**
	 * Instancia un nuevo objeto consulta historial solicitudes pagadas delegado.
	 */
	public ConsultaHistorialSolicitudesPagadasDelegado()
	{
		ichspr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaHistorialSolicitudesPagadasRemote getRemote()
	    throws B2BException
	{
		if(ichspr_remote == null)
		{
			try
			{
				ichspr_remote = (ConsultaHistorialSolicitudesPagadasRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                                     .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ichspr_remote = null;
			}
		}

		return ichspr_remote;
	}
}
