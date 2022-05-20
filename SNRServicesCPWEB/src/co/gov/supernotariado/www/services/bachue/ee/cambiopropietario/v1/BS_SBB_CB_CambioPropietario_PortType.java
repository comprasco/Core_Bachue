/**
 * BS_SBB_CB_CambioPropietario_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.cambiopropietario.v1;


/**
 * Interface que contiene todos las propiedades BS_SBB_CB_CambioPropietario_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
public interface BS_SBB_CB_CambioPropietario_PortType extends java.rmi.Remote
{
	/**
	 * Consultar cambio propietario.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.ee.cambio propietario.consultar cambio propietario.v 1 . tipo salida consultar cambio propietario
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoSalidaConsultarCambioPropietario consultarCambioPropietario(
	    co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario entrada
	)
	    throws java.rmi.RemoteException;
}
