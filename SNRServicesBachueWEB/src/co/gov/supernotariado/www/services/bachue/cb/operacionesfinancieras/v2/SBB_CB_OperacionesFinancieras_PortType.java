/**
 * SBB_CB_OperacionesFinancieras_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.operacionesfinancieras.v2;


/**
 * Interface que contiene todos las propiedades SBB_CB_OperacionesFinancieras_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_CB_OperacionesFinancieras_PortType extends java.rmi.Remote
{
	/**
	 * Actualizar datos solicitante.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v 2 . tipo salida actualizar datos solicitante
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoSalidaActualizarDatosSolicitante actualizarDatosSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2.TipoEntradaActualizarDatosSolicitante entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Consultar tarifa servicio.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v 2 . tipo salida consultar tarifa servicio
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoSalidaConsultarTarifaServicio consultarTarifaServicio(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicio entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Generar liquidacion.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v 2 . tipo salida generar liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoSalidaGenerarLiquidacion generarLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoEntradaGenerarLiquidacion entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Obtener clave PDF liquidacion.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v 2 . tipo salida obtener clave PDF liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoSalidaObtenerClavePDFLiquidacion obtenerClavePDFLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2.TipoEntradaObtenerClavePDFLiquidacion entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Registrar pago.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v 2 . tipo salida registrar pago
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoSalidaRegistrarPago registrarPago(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2.TipoEntradaRegistrarPago entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Validar actualizacion datos solicitante.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v 2 . tipo salida validar actualizacion datos solicitante
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoSalidaValidarActualizacionDatosSolicitante validarActualizacionDatosSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.validaractualizaciondatossolicitante.v2.TipoEntradaValidarActualizacionDatosSolicitante entrada
	)
	    throws java.rmi.RemoteException;
}
