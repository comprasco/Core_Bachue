package com.bachue.snr.prosnr01.web.delegate.recepcion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.recepcion.RecepcionDocumentosRemote;


/**
 * Clase para el acceso a la interfaz remota de operaciones financieras.
 *
 * @author Gabriel Arias
 *
 */
public class RecepcionDocumentosDelegado implements java.io.Serializable
{
	/**Constante serialVersionUID */
	private static final long serialVersionUID = 5925235588392008489L;

	/**Cosntante JNDI_NAME*/
	private static final String JNDI_NAME = "jndi.recepcionDocumentos";

	/**Constante clh_LOGGER*/
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(RecepcionDocumentosDelegado.class);

	/**Propiedad remote*/
	private RecepcionDocumentosRemote ird_remote;

	/**
	 * Constructor
	 */
	public RecepcionDocumentosDelegado()
	{
		ird_remote = null;
	}

	/**
	 * Método de obtencion de la propiedad Remote
	 * @return de tipo Recepción de documentos Remote con el valor
	 * @throws B2BException
	 */
	public RecepcionDocumentosRemote getRemote()
	    throws B2BException
	{
		if(ird_remote == null)
		{
			try
			{
				ird_remote = (RecepcionDocumentosRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                                  .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				ird_remote = null;
			}
		}

		return ird_remote;
	}
}
