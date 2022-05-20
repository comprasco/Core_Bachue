/**
 * BS_SDI_CB_SolicitudDeCopias_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.solicituddecopias.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoEntradaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;


public class BS_SDI_CB_SolicitudDeCopias_BindingImpl extends BaseServices
    implements BS_SDI_CB_SolicitudDeCopias_PortType
{
	private static final long          serialVersionUID = 7375563267394850705L;
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BS_SDI_CB_SolicitudDeCopias_BindingImpl.class, ProyectosCommon.SOLICITUD_DE_COPIAS_18);

	public TipoSalidaConsultarSolicitud consultarSolicitud(TipoEntradaConsultarSolicitud ltecs_entrada)
	    throws java.rmi.RemoteException
	{
		TipoSalidaConsultarSolicitud itsis_tsis;

		itsis_tsis = null;

		try
		{
			itsis_tsis = getSolicitudCopiasRemote()
					             .consultarSolicitud(
					    ltecs_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarSolicitud", lb2be_e);
		}

		return itsis_tsis;
	}

	public TipoSalidaIngresoSolicitud ingresoSolicitud(TipoEntradaIngresoSolicitud lteis_entrada)
	    throws java.rmi.RemoteException
	{
		TipoSalidaIngresoSolicitud itsis_tsis;

		itsis_tsis = null;

		try
		{
			itsis_tsis = getSolicitudCopiasRemote()
					             .ingresoSolicitud(
					    lteis_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("ingresoSolicitud", lb2be_e);
		}

		return itsis_tsis;
	}
}
