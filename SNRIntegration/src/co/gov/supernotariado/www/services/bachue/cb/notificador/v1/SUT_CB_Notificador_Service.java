/**
 * SUT_CB_Notificador_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.notificador.v1;


/**
 * Interface que contiene todos las propiedades SUT_CB_Notificador_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public interface SUT_CB_Notificador_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SU T C B notificador port.
	 *
	 * @return el valor de SU T C B notificador port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType getSUT_CB_NotificadorPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C B notificador port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SU T C B notificador port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType getSUT_CB_NotificadorPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C B notificador port address.
	 *
	 * @return el valor de SU T C B notificador port address
	 */
	public java.lang.String getSUT_CB_NotificadorPortAddress();
}
