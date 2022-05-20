/**
 * SUT_CN_NotificadorCorrespondencia_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1;


/**
 * Interface que contiene todos las propiedades SUT_CN_NotificadorCorrespondencia_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public interface SUT_CN_NotificadorCorrespondencia_PortType extends java.rmi.Remote
{
	/**
	 * Notificar correspondencia.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v 1 . tipo salida notificar correspondencia
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoSalidaNotificarCorrespondencia notificarCorrespondencia(
	    co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoEntradaNotificarCorrespondencia entrada
	)
	    throws java.rmi.RemoteException;
}
