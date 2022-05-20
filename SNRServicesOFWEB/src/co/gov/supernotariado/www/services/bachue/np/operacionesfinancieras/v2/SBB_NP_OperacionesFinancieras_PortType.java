/**
 * SBB_NP_OperacionesFinancieras_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.np.operacionesfinancieras.v2;


/**
 * Interface que contiene todos las propiedades SBB_NP_OperacionesFinancieras_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_NP_OperacionesFinancieras_PortType extends java.rmi.Remote
{
	/**
	 * Consultar estado liquidacion.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v 2 . tipo salida consultar estado liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Notificar pago.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v 2 . tipo salida notificar pago
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago notificarPago(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Registrar anulacion.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v 2 . tipo salida registrar anulacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion registrarAnulacion(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Registrar liquidacion.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v 2 . tipo salida registrar liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion registrarLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Registrar recibo caja.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v 2 . tipo salida registrar recibo caja
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja registrarReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja entrada
	)
	    throws java.rmi.RemoteException;
}
