/**
 * SBB_CB_GeneracionSolicitudSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.generacionsolicitud.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoEntradaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSalidaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoEntradaGenerarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSalidaGenerarSolicitud;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase creada para la implementación de las operaciones del servicio web Generacion Solicitud
 *
 * @author Manuel Blanco
 *
 */
public class SBB_CB_GeneracionSolicitudSOAP12BindingImpl extends BaseServices
    implements SBB_CB_GeneracionSolicitud_PortType
{
	/** Constante serialVersionUID */
	private static final long serialVersionUID = 8133478384534095586L;

	/** Constante clh_LOGGER */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_GeneracionSolicitudSOAP12BindingImpl.class, ProyectosCommon.GENERACION_SOLICITUD_13);

	/**
	 * Metodo para generar una solicitud
	 *
	 * @param ategs_entrada Objeto contenedor de los parametros a utilizar en la generación de la solicitud
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaGenerarSolicitud generarSolicitud(TipoEntradaGenerarSolicitud ategs_entrada)
	    throws RemoteException
	{
		TipoSalidaGenerarSolicitud ltsiu_return;

		ltsiu_return = null;

		try
		{
			ltsiu_return = getGeneracionSolicitudRemote()
					               .generarSolicitud(
					    ategs_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitud", lb2be_e);
		}

		return ltsiu_return;
	}

	/**
	 * Metodo para consultar el estado de una solicitud
	 *
	 * @param aeces_entrada Objeto contenedor de los parametros a utilizar en la consulta
	 * @return Respuesta de la ejecución de la operación
	 * @throws RemoteException Si ocurre un error en la comunicación con el cliente que consume la operación
	 */
	public TipoSalidaConsultarEstadoSolicitud consultarEstadoSolicitud(
	    TipoEntradaConsultarEstadoSolicitud aeces_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarEstadoSolicitud ltsces_return;

		ltsces_return = null;

		try
		{
			ltsces_return = getGeneracionSolicitudRemote()
					                .consultarEstadoSolicitud(
					    aeces_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarSolicitud", lb2be_e);
		}

		return ltsces_return;
	}
}
