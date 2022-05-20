/**
 * SUT_CB_Notificador_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.notificador.v1;


/**
 * Interface que contiene todos las propiedades SUT_CB_Notificador_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 2/04/2020
 */
public interface SUT_CB_Notificador_PortType extends java.rmi.Remote
{
	/**
	 * Acusar mensaje.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v 1 . tipo salida acusar mensaje
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje acusarMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje entrada
	)
	    throws java.rmi.RemoteException;
}
