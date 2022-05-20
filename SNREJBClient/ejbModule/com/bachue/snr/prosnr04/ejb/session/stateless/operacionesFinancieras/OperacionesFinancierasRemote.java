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

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades OperacionesFinancierasRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2019
 */
@Remote
public interface OperacionesFinancierasRemote
{
	
	/**
	 * Actualizar datos solicitante.
	 *
	 * @param ateads_peticion de TipoEntradaActualizarDatosSolicitante
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida actualizar datos solicitante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(
	    TipoEntradaActualizarDatosSolicitante ateads_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar tarifa servicio.
	 *
	 * @param atects_peticion de TipoEntradaConsultarTarifaServicio
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar tarifa servicio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarTarifaServicio consultarTarifaServicio(
	    TipoEntradaConsultarTarifaServicio atects_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar liquidacion.
	 *
	 * @param ategl_peticion de TipoEntradaGenerarLiquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida generar liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaGenerarLiquidacion generarLiquidacion(
	    TipoEntradaGenerarLiquidacion ategl_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener clave PDF liquidacion.
	 *
	 * @param ateocpl_peticion de TipoEntradaObtenerClavePDFLiquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida obtener clave PDF liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaObtenerClavePDFLiquidacion obtenerClavePDFLiquidacion(
	    TipoEntradaObtenerClavePDFLiquidacion ateocpl_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Registrar pago.
	 *
	 * @param aterp_peticion de TipoEntradaRegistrarPago
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida registrar pago
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRegistrarPago registrarPago(
	    TipoEntradaRegistrarPago aterp_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar actualizacion datos solicitante.
	 *
	 * @param atevads_entrada de TipoEntradaValidarActualizacionDatosSolicitante
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida validar actualizacion datos solicitante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaValidarActualizacionDatosSolicitante validarActualizacionDatosSolicitante(
	    TipoEntradaValidarActualizacionDatosSolicitante atevads_entrada, String as_userId, String as_localIp,
	    String                                          as_remoteIp
	)
	    throws B2BException;
}
