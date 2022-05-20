package com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras;

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

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr04.business.operacionesFinancieras.OperacionesFinancierasBusiness;

import com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras.OperacionesFinancierasRemote;

import org.perf4j.StopWatch;


/**
 * Class que contiene todos las propiedades OperacionesFinancierasBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "OperacionesFinancieras", mappedName = "operacionesFinancierasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class OperacionesFinancierasBean implements OperacionesFinancierasRemote
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
	public TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(
	    TipoEntradaActualizarDatosSolicitante ateads_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaActualizarDatosSolicitante ltsads_response;
		StopWatch                            lsw_watch;

		lsw_watch           = Logger.getNewStopWatch();
		ltsads_response     = getOperacionesFinancierasBusiness()
				                      .actualizarDatosSolicitante(ateads_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "actualizarDatosSolicitante", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsads_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarTarifaServicio consultarTarifaServicio(
	    TipoEntradaConsultarTarifaServicio atects_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaConsultarTarifaServicio ltst_respuesta;
		StopWatch                         lsw_watch;

		lsw_watch          = Logger.getNewStopWatch();
		ltst_respuesta     = getOperacionesFinancierasBusiness()
				                     .consultarTarifaServicio(atects_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "consultarTarifaServicio", as_userId, as_localIp, as_remoteIp, null
		);

		return ltst_respuesta;
	}

	/** {@inheritdoc} */
	public TipoSalidaGenerarLiquidacion generarLiquidacion(
	    TipoEntradaGenerarLiquidacion ategl_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		TipoSalidaGenerarLiquidacion ltsgl_respuesta;

		lsw_watch           = Logger.getNewStopWatch();
		ltsgl_respuesta     = getOperacionesFinancierasBusiness()
				                      .generarLiquidacion(ategl_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "generarLiquidacion", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsgl_respuesta;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerClavePDFLiquidacion obtenerClavePDFLiquidacion(
	    TipoEntradaObtenerClavePDFLiquidacion ateocpl_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaObtenerClavePDFLiquidacion ltsocpl_response;

		lsw_watch            = Logger.getNewStopWatch();
		ltsocpl_response     = getOperacionesFinancierasBusiness()
				                       .obtenerClavePDFLiquidacion(
				    ateocpl_peticion, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "obtenerClavePDFLiquidacion", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsocpl_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaRegistrarPago registrarPago(
	    TipoEntradaRegistrarPago aterp_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaRegistrarPago ltsrp_response;
		StopWatch               lsw_watch;

		lsw_watch          = Logger.getNewStopWatch();
		ltsrp_response     = getOperacionesFinancierasBusiness()
				                     .registrarPago(aterp_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "OperacionesFinancierasBean", "registrarPago", as_userId, as_localIp, as_remoteIp, null);

		return ltsrp_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaValidarActualizacionDatosSolicitante validarActualizacionDatosSolicitante(
	    TipoEntradaValidarActualizacionDatosSolicitante atevads_entrada, String as_userId, String as_localIp,
	    String                                          as_remoteIp
	)
	    throws B2BException
	{
		TipoSalidaValidarActualizacionDatosSolicitante ltsrp_response;
		StopWatch                                      lsw_watch;

		lsw_watch          = Logger.getNewStopWatch();
		ltsrp_response     = getOperacionesFinancierasBusiness()
				                     .validarActualizacionDatosSolicitante(
				    atevads_entrada, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "OperacionesFinancierasBean", "validarActualizacionDatosSolicitante", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltsrp_response;
	}
}
