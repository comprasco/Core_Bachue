package com.bachue.snr.prosnr01.web.delegate.gobernaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.ejb.session.stateless.gobernaciones.GobernacionesRemote;


/**
 * Clase para el acceso a la interfaz remota de gobernaciones.
 *
 * @author Luis Chacon
 *
 */
public class GobernacionesDelegado implements java.io.Serializable
{
	/**Constante serialVersionUID */
	private static final long serialVersionUID = 3463691125632623966L;

	/**Constante JNDI_NAME*/
	private static final String JNDI_NAME = "jndi.gobernaciones";

	/**Constante clh_LOGGER*/
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(GobernacionesDelegado.class);

	/** Propiedad remote. */
	private GobernacionesRemote igr_remote;

	/**
	 * Instancia un nuevo objeto gobernaciones delegado.
	 */
	public GobernacionesDelegado()
	{
		igr_remote = null;
	}

	/**
	 * Método de obtencion de la propiedad Gobernaciones remote
	 * @return de tipo Gobernaciones remote con el valor de la propiedad
	 * @throws B2BException
	 */
	public GobernacionesRemote getRemote()
	    throws B2BException
	{
		if(igr_remote == null)
		{
			try
			{
				igr_remote = (GobernacionesRemote)com.b2bsg.common.serviceLocator.ServiceLocator.getServiceLocator()
						                                                                            .getConfigRegisteredObject(
						    JNDI_NAME, false
						);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("getRemote", le_e);
				igr_remote = null;
			}
		}

		return igr_remote;
	}
}
