/**
 * SBB_CB_TramitesEnCursoSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.v1;

import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoEntradaDatosTramites;
import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoSalidaDatosTramites;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_TramitesEnCursoSOAP12BindingImpl.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/03/2020
 */
public class SBB_CB_TramitesEnCursoSOAP12BindingImpl extends BaseServices implements SBB_CB_TramitesEnCurso_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8408593478707140983L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_TramitesEnCursoSOAP12BindingImpl.class, ProyectosCommon.COEXISTENCIA_14);

	/** {@inheritdoc} */
	public TipoSalidaDatosTramites consultarTramites(TipoEntradaDatosTramites atedt_entrada)
	    throws RemoteException
	{
		TipoSalidaDatosTramites ltsdt_salida;

		ltsdt_salida = new TipoSalidaDatosTramites();

		try
		{
			ltsdt_salida = getTramitesEnCursoRemote()
					               .consultarTramites(
					    atedt_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTramites", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarTramites", le_e);
		}

		return ltsdt_salida;
	}
}
