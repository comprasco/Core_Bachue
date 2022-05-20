/**
 * BS_SBB_CB_PartesInteresadas_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_PartesInteresadas_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface BS_SBB_CB_PartesInteresadas_PortType extends java.rmi.Remote
{
	/**
	 * Consultar partes interesadas.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v 1 . tipo salida consultar partes interesadas
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadas consultarPartesInteresadas(
	    co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas entrada
	)
	    throws java.rmi.RemoteException;
}
