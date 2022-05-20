/**
 * SBB_CB_Anotaciones_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.anotaciones.v1;


/**
 * Interface que contiene todos las propiedades SBB_CB_Anotaciones_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_CB_Anotaciones_PortType extends java.rmi.Remote
{
	/**
	 * Consultar anotaciones.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultar anotaciones.v 1 . tipo anotacion[]
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacion[] consultarAnotaciones(
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion entrada
	)
	    throws java.rmi.RemoteException;
}
