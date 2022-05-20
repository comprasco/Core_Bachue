package co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2;

import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SBB_NP_OperacionesFinancierasSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_NP_OperacionesFinancierasSOAP12BindingImpl extends BaseServices
    implements SBB_NP_OperacionesFinancieras_PortType
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_NP_OperacionesFinancierasSOAP12BindingImpl.class, ProyectosCommon.NOTIFICACION_PAGOS_04);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3183141049074892174L;

	/**
	 * Esta operación permite consultar el estado de una referencia de pago con el objetivo de poder entregar el servicio.
	 *
	 * @param ltecel_entrada de ltecel entrada
	 * @return el valor de tipo salida consultar estado liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(
	    TipoEntradaConsultarEstadoLiquidacion ltecel_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultarEstadoLiquidacion ltsel_response;

		ltsel_response = new TipoSalidaConsultarEstadoLiquidacion();

		try
		{
			ltsel_response = getNotificacionDePagosRemote()
					                 .consultarEstadoLiquidacion(
					    ltecel_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarEstadoLiquidacion", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultarEstadoLiquidacion", le_e);
		}

		return ltsel_response;
	}

	/**
	 * La notificación de pago en línea con número de referencia es una operación que recibe la información de un pago recibido por la entidad recaudadora referente a un recibo de liquidación existente.
	 *
	 * @param ltenp_entrada de ltenp entrada
	 * @return el valor de tipo salida notificar pago
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaNotificarPago notificarPago(TipoEntradaNotificarPago ltenp_entrada)
	    throws RemoteException
	{
		TipoSalidaNotificarPago ltsnp_response;

		ltsnp_response = new TipoSalidaNotificarPago();

		try
		{
			ltsnp_response = getNotificacionDePagosRemote()
					                 .notificarPago(
					    ltenp_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("notificarPago", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("notificarPago", le_e);
		}

		return ltsnp_response;
	}

	/**
	 * Esta operación recibe la anulación de las  liquidaciones ya generadas por fecha vencidas y/o  cambio de tarifas.
	 *
	 * @param ltera_entrada de ltera entrada
	 * @return el valor de tipo salida registrar anulacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarAnulacion registrarAnulacion(TipoEntradaRegistrarAnulacion ltera_entrada)
	    throws RemoteException
	{
		TipoSalidaRegistrarAnulacion ltsra_response;

		ltsra_response = new TipoSalidaRegistrarAnulacion();

		try
		{
			ltsra_response = getNotificacionDePagosRemote()
					                 .registrarAnulacion(
					    ltera_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarAnulacion", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registrarAnulacion", le_e);
		}

		return ltsra_response;
	}

	/**
	 * Esta operación permite recibir la información de una liquidación generada en el componente core, es notificada a través del BUS de servicios.
	 *
	 * @param lterl_entrada de lterl entrada
	 * @return el valor de tipo salida registrar liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarLiquidacion registrarLiquidacion(TipoEntradaRegistrarLiquidacion lterl_entrada)
	    throws RemoteException
	{
		TipoSalidaRegistrarLiquidacion ltsrl_response;

		ltsrl_response = new TipoSalidaRegistrarLiquidacion();

		try
		{
			ltsrl_response = getNotificacionDePagosRemote()
					                 .registrarLiquidacion(
					    lterl_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarLiquidacion", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registrarLiquidacion", le_e);
		}

		return ltsrl_response;
	}

	/**
	 * Esta operación registra la información en el componente Notificaciones de Pago.
	 *
	 * @param lterrc_entrada de lterrc entrada
	 * @return el valor de tipo salida registrar recibo caja
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaRegistrarReciboCaja registrarReciboCaja(TipoEntradaRegistrarReciboCaja lterrc_entrada)
	    throws RemoteException
	{
		TipoSalidaRegistrarReciboCaja ltsrrc_response;

		ltsrrc_response = new TipoSalidaRegistrarReciboCaja();

		try
		{
			ltsrrc_response = getNotificacionDePagosRemote()
					                  .registrarReciboCaja(
					    lterrc_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registrarReciboCaja", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registrarReciboCaja", le_e);
		}

		return ltsrrc_response;
	}
}
