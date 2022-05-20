package co.gov.supernotariado.www.services.bachue.cb.operacionesfinancieras.v2;

import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoEntradaActualizarDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoSalidaActualizarDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicio;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoSalidaConsultarTarifaServicio;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoEntradaGenerarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoSalidaGenerarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoEntradaObtenerClavePDFLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoSalidaObtenerClavePDFLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoEntradaRegistrarPago;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoSalidaRegistrarPago;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoEntradaValidarActualizacionDatosSolicitante;
import co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoSalidaValidarActualizacionDatosSolicitante;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_CB_OperacionesFinancierasSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_OperacionesFinancierasSOAP12BindingImpl extends BaseServices
    implements SBB_CB_OperacionesFinancieras_PortType
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_OperacionesFinancierasSOAP12BindingImpl.class, ProyectosCommon.NOTIFICACION_PAGOS_04);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6465280361641300650L;

	/**
	 * Esta operación permite actualizar la información básica del solicitante del servicio que no pudo ser capturada por el canal durante la liquidación.
	 *
	 * @param ateads_entrada de ateads entrada
	 * @return el valor de tipo salida actualizar datos solicitante
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(
	    TipoEntradaActualizarDatosSolicitante ateads_entrada
	)
	    throws java.rmi.RemoteException
	{
		TipoSalidaActualizarDatosSolicitante ltsads_response;

		ltsads_response = new TipoSalidaActualizarDatosSolicitante();

		try
		{
			ltsads_response = getOperacionesFinancierasRemote()
					                  .actualizarDatosSolicitante(
					    ateads_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarDatosSolicitante", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("actualizarDatosSolicitante", le_e);
		}

		return ltsads_response;
	}

	/**
	 * Esta operación permite realizar la consulta de la tarifa de un servicio.
	 * Como servicio entiéndase consultas o certificados.
	 *
	 * @param atects_entrada de atects entrada
	 * @return el valor de tipo salida consultar tarifa servicio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarTarifaServicio consultarTarifaServicio(TipoEntradaConsultarTarifaServicio atects_entrada)
	    throws RemoteException
	{
		TipoSalidaConsultarTarifaServicio ltscts_response;

		ltscts_response = new TipoSalidaConsultarTarifaServicio();

		try
		{
			ltscts_response = getOperacionesFinancierasRemote()
					                  .consultarTarifaServicio(
					    atects_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTarifaServicio", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarTarifaServicio", le_e);
		}

		return ltscts_response;
	}

	/**
	 * Esta operación permite ingresa la solicitud y generar la liquidación de una serie de consultas y certificados realizados por un usuario. Como servicio entiéndase consultas o certificados.
	 *
	 * @param ltegl_entrada de ltegl entrada
	 * @return el valor de tipo salida generar liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaGenerarLiquidacion generarLiquidacion(TipoEntradaGenerarLiquidacion ltegl_entrada)
	    throws RemoteException
	{
		TipoSalidaGenerarLiquidacion ltsgl_response;

		ltsgl_response = new TipoSalidaGenerarLiquidacion();

		try
		{
			ltsgl_response = getOperacionesFinancierasRemote()
					                 .generarLiquidacion(
					    ltegl_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarLiquidacion", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarLiquidacion", le_e);
		}

		return ltsgl_response;
	}

	/**
	 * Se expone desde OWCC para retornar el contenido del documento de liquidación para un pago, a partir de la clave de dicho documento.
	 *
	 * @param lteocpl_entrada de lteocpl entrada
	 * @return el valor de tipo salida obtener clave PDF liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaObtenerClavePDFLiquidacion obtenerClavePDFLiquidacion(
	    TipoEntradaObtenerClavePDFLiquidacion lteocpl_entrada
	)
	    throws RemoteException
	{
		TipoSalidaObtenerClavePDFLiquidacion ltsocpl_response;

		ltsocpl_response = new TipoSalidaObtenerClavePDFLiquidacion();

		try
		{
			ltsocpl_response = getOperacionesFinancierasRemote()
					                   .obtenerClavePDFLiquidacion(
					    lteocpl_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("obtenerClavePDFLiquidacion", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("obtenerClavePDFLiquidacion", le_e);
		}

		return ltsocpl_response;
	}

	/**
	 * Con esta operación se informa al Core Bachué los pagos notificados.
	 *
	 * @param lterr_entrada de lterr entrada
	 * @return el valor de tipo salida registrar pago
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarPago registrarPago(TipoEntradaRegistrarPago lterr_entrada)
	    throws RemoteException
	{
		TipoSalidaRegistrarPago ltsrp_response;

		ltsrp_response = new TipoSalidaRegistrarPago();

		try
		{
			ltsrp_response = getOperacionesFinancierasRemote()
					                 .registrarPago(
					    lterr_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarPago", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registrarPago", le_e);
		}

		return ltsrp_response;
	}

	/**
	 * Valida que los datos del usuario solicitante están actualizados.
	 *
	 * @param atevads_entrada de atevads entrada
	 * @return el valor de tipo salida validar actualizacion datos solicitante
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaValidarActualizacionDatosSolicitante validarActualizacionDatosSolicitante(
	    TipoEntradaValidarActualizacionDatosSolicitante atevads_entrada
	)
	    throws RemoteException
	{
		TipoSalidaValidarActualizacionDatosSolicitante ltsrp_response;

		ltsrp_response = new TipoSalidaValidarActualizacionDatosSolicitante();

		try
		{
			ltsrp_response = getOperacionesFinancierasRemote()
					                 .validarActualizacionDatosSolicitante(
					    atevads_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarActualizacionDatosSolicitante", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("validarActualizacionDatosSolicitante", le_e);
		}

		return ltsrp_response;
	}
}
