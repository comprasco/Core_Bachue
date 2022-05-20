/**
 * SBB_CB_CertificadoTradicion_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1;


/**
 * Interface que contiene todos las propiedades SBB_CB_CertificadoTradicion_Service.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_CB_CertificadoTradicion_Service extends javax.xml.rpc.Service
{
	/**
	 * Retorna Objeto o variable de valor SB B C B certificado tradicion port.
	 *
	 * @return el valor de SB B C B certificado tradicion port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1.SBB_CB_CertificadoTradicion_PortType getSBB_CB_CertificadoTradicionPort()
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SB B C B certificado tradicion port.
	 *
	 * @param portAddress de port address
	 * @return el valor de SB B C B certificado tradicion port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1.SBB_CB_CertificadoTradicion_PortType getSBB_CB_CertificadoTradicionPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException;

	/**
	 * Retorna Objeto o variable de valor SB B C B certificado tradicion port address.
	 *
	 * @return el valor de SB B C B certificado tradicion port address
	 */
	public java.lang.String getSBB_CB_CertificadoTradicionPortAddress();
}
