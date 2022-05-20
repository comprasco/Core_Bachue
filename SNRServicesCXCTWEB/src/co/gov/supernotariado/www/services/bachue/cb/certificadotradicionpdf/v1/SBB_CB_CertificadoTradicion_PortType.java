/**
 * SBB_CB_CertificadoTradicion_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.v1;


/**
 * Interface que contiene todos las propiedades SBB_CB_CertificadoTradicion_PortType.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public interface SBB_CB_CertificadoTradicion_PortType extends java.rmi.Remote
{
	/**
	 * Obtener PDF.
	 *
	 * @param entrada de entrada
	 * @return el valor de co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtener PD f.v 1 . tipo salida certificado tradicion PDF
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoSalidaCertificadoTradicionPDF obtenerPDF(
	    co.gov.supernotariado.www.services.bachue.cb.certificadotradicionpdf.obtenerPDF.v1.TipoEntradaCertificadoTradicionPDF entrada
	)
	    throws java.rmi.RemoteException;
}
