package com.bachue.snr.prosnr08.web.services.delegate.entrega.producto;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr08.ejb.session.stateless.entrega.producto.EntregaProductoRemote;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades EntregarProductoDelegado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 10/10/2019
 */
public class EntregarProductoDelegado implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7653971533989344221L;

	/** Constante JNDI_NAME. */
	private static final String JNDI_NAME = "jndi.entregaProducto";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(EntregarProductoDelegado.class);

	/** Propiedad iep remote. */
	private EntregaProductoRemote iep_remote;

	/**
	 * Instancia un nuevo objeto entregar producto delegado.
	 */
	public EntregarProductoDelegado()
	{
		iep_remote = null;
	}

	/**
	 * Retorna Objeto o variable de valor remote.
	 *
	 * @return el valor de remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public EntregaProductoRemote getRemote()
	    throws B2BException
	{
		if(iep_remote == null)
		{
			try
			{
				iep_remote = (EntregaProductoRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                              .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				iep_remote = null;
			}
		}

		return iep_remote;
	}
}
