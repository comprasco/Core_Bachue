/**
 * SUT_CR_EnvioCorrespondenciaFisica_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1;


/**
 * Interface que contiene todos las propiedades SUT_CR_EnvioCorrespondenciaFisica_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public interface SUT_CR_EnvioCorrespondenciaFisica_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SU T C R envio correspondencia fisica port.
	 *
	 * @return el valor de SU T C R envio correspondencia fisica port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisica_PortType getSUT_CR_EnvioCorrespondenciaFisicaPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C R envio correspondencia fisica port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SU T C R envio correspondencia fisica port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cr.enviocorrespondenciafisica.v1.SUT_CR_EnvioCorrespondenciaFisica_PortType getSUT_CR_EnvioCorrespondenciaFisicaPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SU T C R envio correspondencia fisica port address.
	 *
	 * @return el valor de SU T C R envio correspondencia fisica port address
	 */
	public java.lang.String getSUT_CR_EnvioCorrespondenciaFisicaPortAddress();
}
