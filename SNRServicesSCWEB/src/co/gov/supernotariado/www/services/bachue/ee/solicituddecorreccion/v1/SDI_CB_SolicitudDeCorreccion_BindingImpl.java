/**
 * SDI_CB_SolicitudDeCorreccion_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.solicituddecorreccion.v1;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoObtenerCausalesCorreccion;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;


public class SDI_CB_SolicitudDeCorreccion_BindingImpl extends BaseServices
    implements SDI_CB_SolicitudDeCorreccion_PortType
{
	private static final long          serialVersionUID = 558068794152349576L;
	private static final LoggerHandler clh_LOGGER       = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SDI_CB_SolicitudDeCorreccion_BindingImpl.class, ProyectosCommon.SOLICITUD_DE_CORRECCION_17);

	public TipoSalidaIngresoSolicitud ingresoSolicitud(TipoEntradaIngresoSolicitud ateis_teis)
	    throws java.rmi.RemoteException
	{
		TipoSalidaIngresoSolicitud itsis_tsis;

		itsis_tsis = null;

		try
		{
			itsis_tsis = getSolicitudCorreccionRemote()
					             .ingresoSolicitud(ateis_teis, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("ingresoSolicitud", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("ingresoSolicitud", le_e);
		}

		return itsis_tsis;
	}

	public TipoSalidaObtenerCausalesCorreccion obtenerCausalesCorreccion(TipoObtenerCausalesCorreccion atocc_tocc)
	    throws java.rmi.RemoteException
	{
		TipoSalidaObtenerCausalesCorreccion itsocc_tsocc;

		itsocc_tsocc = null;

		try
		{
			itsocc_tsocc = getSolicitudCorreccionRemote()
					               .obtenerCausalesCorreccion(
					    atocc_tocc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerCausalesCorreccion", lb2be_e);
		}

		return itsocc_tsocc;
	}
}
