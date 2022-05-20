package com.bachue.snr.prosnr05.web.services.delegate;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr05.ejb.session.stateless.consulta.catalogos.ConsultaCatalogosRemote;


/**
 * Clase que contiene todos las propiedades ConsultaCatalogosDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class ConsultaCatalogosDelegado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6040472666403373298L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.consultaCatalogos";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaCatalogosDelegado.class);

	/** Propiedad iccr remote. */
	private ConsultaCatalogosRemote iccr_remote;

	/**
	 * Instancia un nuevo objeto consulta catalogos delegado.
	 */
	public ConsultaCatalogosDelegado()
	{
		iccr_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de ConsultaCatalogosRemote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaCatalogosRemote getRemote()
	    throws B2BException
	{
		if(iccr_remote == null)
		{
			try
			{
				iccr_remote = (ConsultaCatalogosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                 .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iccr_remote = null;
			}
		}

		return iccr_remote;
	}
}
