/**
 * SBB_CB_EntregaProducto_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;


public interface SBB_CB_EntregaProducto_Service extends Service
{
	public SBB_CB_EntregaProducto_PortType getSBB_CB_EntregaProductoPort()
	    throws ServiceException;

	public SBB_CB_EntregaProducto_PortType getSBB_CB_EntregaProductoPort(java.net.URL portAddress)
	    throws ServiceException;

	public String getSBB_CB_EntregaProductoPortAddress();
}
