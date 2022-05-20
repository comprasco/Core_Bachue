/**
 * SUT_CO_CierreAperturaExpediente_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1;


/**
 * Interface que contiene todos las propiedades SUT_CO_CierreAperturaExpediente_Service.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/05/2020
 */
public interface SUT_CO_CierreAperturaExpediente_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SU T C O cierre apertura expediente port.
	 *
	 * @return el valor de SU T C O cierre apertura expediente port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType getSUT_CO_CierreAperturaExpedientePort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C O cierre apertura expediente port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SU T C O cierre apertura expediente port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType getSUT_CO_CierreAperturaExpedientePort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C O cierre apertura expediente port address.
	 *
	 * @return el valor de SU T C O cierre apertura expediente port address
	 */
	public java.lang.String getSUT_CO_CierreAperturaExpedientePortAddress();
}
