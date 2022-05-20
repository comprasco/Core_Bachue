/**
 * SUT_CN_NotificadorCorrespondencia_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1;


/**
 * Interface que contiene todos las propiedades SUT_CN_NotificadorCorrespondencia_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public interface SUT_CN_NotificadorCorrespondencia_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SU T C N notificador correspondencia port.
	 *
	 * @return el valor de SU T C N notificador correspondencia port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType getSUT_CN_NotificadorCorrespondenciaPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C N notificador correspondencia port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SU T C N notificador correspondencia port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType getSUT_CN_NotificadorCorrespondenciaPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C N notificador correspondencia port address.
	 *
	 * @return el valor de SU T C N notificador correspondencia port address
	 */
	public java.lang.String getSUT_CN_NotificadorCorrespondenciaPortAddress();
}
