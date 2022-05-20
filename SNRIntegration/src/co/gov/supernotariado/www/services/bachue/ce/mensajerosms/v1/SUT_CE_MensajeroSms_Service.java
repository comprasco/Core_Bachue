/**
 * SUT_CE_MensajeroSms_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1;


/**
 * Interface que contiene todos las propiedades SUT_CE_MensajeroSms_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public interface SUT_CE_MensajeroSms_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SU T C E mensajero sms port.
	 *
	 * @return el valor de SU T C E mensajero sms port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType getSUT_CE_MensajeroSmsPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C E mensajero sms port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SU T C E mensajero sms port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType getSUT_CE_MensajeroSmsPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C E mensajero sms port address.
	 *
	 * @return el valor de SU T C E mensajero sms port address
	 */
	public java.lang.String getSUT_CE_MensajeroSmsPortAddress();
}
