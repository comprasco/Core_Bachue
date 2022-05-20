package com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras;

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

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr04.business.operacionesFinancieras.OperacionesFinancierasBusiness;

import org.perf4j.StopWatch;


/**
 * Class que contiene todos las propiedades OperacionesFinancierasBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "NotificacionDePagos", mappedName = "notificacionDePagosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class NotificacionDePagosBean implements NotificacionDePagosRemote
{
	/** Propiedad irb business. */
	private OperacionesFinancierasBusiness irb_business;

	/**
	 * Retorna el valor de operaciones financieras business.
	 *
	 * @return el valor de operaciones financieras business
	 */
	public OperacionesFinancierasBusiness getOperacionesFinancierasBusiness()
	{
		if(irb_business == null)
			irb_business = new OperacionesFinancierasBusiness();

		return irb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(
	    TipoEntradaConsultarEstadoLiquidacion atecel_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaConsultarEstadoLiquidacion lrp_respuesta;

		lsw_watch     = Logger.getNewStopWatch();

		lrp_respuesta = getOperacionesFinancierasBusiness()
				                .consultarEstadoLiquidacion(atecel_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "consultarEstadoLiquidacion", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lrp_respuesta;
	}

	/** {@inheritdoc} */
	public TipoSalidaNotificarPago notificarPago(
	    TipoEntradaNotificarPago atenp_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		TipoSalidaNotificarPago ltsnp_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsnp_return     = getOperacionesFinancierasBusiness()
				                   .notificarPago(atenp_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "OperacionesFinancierasBean", "notificarPago", as_userId, as_localIp, as_remoteIp, null);

		return ltsnp_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaRegistrarAnulacion registrarAnulacion(
	    TipoEntradaRegistrarAnulacion atera_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		TipoSalidaRegistrarAnulacion ltsra_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsra_return     = getOperacionesFinancierasBusiness()
				                   .registrarAnulacion(atera_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "registrarAnulacion", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsra_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaRegistrarLiquidacion registrarLiquidacion(
	    TipoEntradaRegistrarLiquidacion aterl_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		TipoSalidaRegistrarLiquidacion ltsrl_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsrl_return     = getOperacionesFinancierasBusiness()
				                   .registrarLiquidacion(aterl_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "registrarLiquidacion", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsrl_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaRegistrarReciboCaja registrarReciboCaja(
	    TipoEntradaRegistrarReciboCaja aterrc_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		TipoSalidaRegistrarReciboCaja ltsrc_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsrc_return     = getOperacionesFinancierasBusiness()
				                   .registrarReciboCaja(aterrc_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "registrarReciboCaja", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsrc_return;
	}
}
