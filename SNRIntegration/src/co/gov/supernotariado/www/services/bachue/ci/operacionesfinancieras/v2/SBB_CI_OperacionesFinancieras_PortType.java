/**
 * SBB_CI_OperacionesFinancieras_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2;


/**
 * Interface que contiene todos las propiedades SBB_CI_OperacionesFinancieras_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public interface SBB_CI_OperacionesFinancieras_PortType extends java.rmi.Remote
{
	/**
	 * Registrar anulacion.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v 2 . tipo salida registrar anulacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion registrarAnulacion(
	    co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Registrar liquidacion.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v 2 . tipo salida registrar liquidacion
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion registrarLiquidacion(
	    co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion entrada
	)
	    throws java.rmi.RemoteException;

	/**
	 * Registrar recibo caja.
	 *
	 * @param entrada correspondiente al valor de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarrecibocaja.v 2 . tipo salida registrar recibo caja
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja registrarReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja entrada
	)
	    throws java.rmi.RemoteException;
}
