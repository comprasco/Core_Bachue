/**
 * BS_SBB_CB_MutacionesCuartoOrden_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.mutacionescuartoOrden.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_MutacionesCuartoOrden_PortType.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 7/05/2020
 */
public interface BS_SBB_CB_MutacionesCuartoOrden_PortType extends java.rmi.Remote
{
	/**
	 * Registra cambio cuarto orden.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ee.mutaciones cuarto orden.registrar cambio cuarto orden.v 1 . tipo salida registrar cambio cuarto orden
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoSalidaRegistrarCambioCuartoOrden registraCambioCuartoOrden(
	    co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrden entrada
	)
	    throws java.rmi.RemoteException;
}
