/**
 * BS_SBB_CB_NuevasMatriculas_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.nuevasMatriculas.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_NuevasMatriculas_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface BS_SBB_CB_NuevasMatriculas_PortType extends java.rmi.Remote
{
	/**
	 * Consultar nuevas matriculas.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ee.nuevas matriculas.consultar nuevas matriculas.v 1 . tipo salida consultar nuevas matriculas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculas consultarNuevasMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculas entrada
	)
	    throws java.rmi.RemoteException;
}
