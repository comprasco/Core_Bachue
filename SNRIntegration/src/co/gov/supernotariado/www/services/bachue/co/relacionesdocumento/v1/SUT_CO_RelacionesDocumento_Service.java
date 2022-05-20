/**
 * SUT_CO_RelacionesDocumento_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.relacionesdocumento.v1;

import java.net.URL;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;


public interface SUT_CO_RelacionesDocumento_Service extends Service
{
	public SUT_CO_RelacionesDocumento_PortType getSUT_CO_RelacionesDocumentoPort()
	    throws ServiceException;

	public SUT_CO_RelacionesDocumento_PortType getSUT_CO_RelacionesDocumentoPort(URL au_portAddress)
	    throws ServiceException;

	public String getSUT_CO_RelacionesDocumentoPortAddress();
}
