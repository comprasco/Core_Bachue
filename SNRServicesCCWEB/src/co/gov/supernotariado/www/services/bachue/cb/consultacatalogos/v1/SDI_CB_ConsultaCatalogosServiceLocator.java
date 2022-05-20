/**
 * SDI_CB_ConsultaCatalogosServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1;

import org.apache.axis.EngineConfiguration;

import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

import java.net.URL;

import java.rmi.Remote;

import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;

import javax.xml.rpc.ServiceException;


public class SDI_CB_ConsultaCatalogosServiceLocator extends Service implements SDI_CB_ConsultaCatalogosService
{
	private static final long serialVersionUID = -4097917263735799949L;
	private HashSet           ports            = null;

	// The WSDD service name defaults to the port name.
	private String SDI_CB_ConsultaCatalogosWSDDServiceName = "SDI_CB_ConsultaCatalogos";

	// Use to get a proxy class for SDI_CB_ConsultaCatalogos
	private String SDI_CB_ConsultaCatalogos_address = "http://localhost:7001/SNRServicesCCWEB/services/SDI_CB_ConsultaCatalogos";

	public SDI_CB_ConsultaCatalogosServiceLocator()
	{
	}

	public SDI_CB_ConsultaCatalogosServiceLocator(EngineConfiguration aec_config)
	{
		super(aec_config);
	}

	public SDI_CB_ConsultaCatalogosServiceLocator(String as_wsdlLoc, QName as_sName)
	    throws ServiceException
	{
		super(as_wsdlLoc, as_sName);
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(String as_portName, String as_address)
	    throws ServiceException
	{
		if("SDI_CB_ConsultaCatalogos".equals(as_portName))
			setSDI_CB_ConsultaCatalogosEndpointAddress(as_address);
		else
			throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + as_portName);
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(QName aqn_portName, String as_address)
	    throws ServiceException
	{
		setEndpointAddress(aqn_portName.getLocalPart(), as_address);
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public Remote getPort(Class ac_serviceEndpointInterface)
	    throws ServiceException
	{
		try
		{
			if(SDI_CB_ConsultaCatalogos.class.isAssignableFrom(ac_serviceEndpointInterface))
			{
				SDI_CB_ConsultaCatalogosSOAP12BindingStub _stub = new SDI_CB_ConsultaCatalogosSOAP12BindingStub(
					    new URL(SDI_CB_ConsultaCatalogos_address), this
					);
				_stub.setPortName(getSDI_CB_ConsultaCatalogosWSDDServiceName());

				return _stub;
			}
		}
		catch(Throwable t)
		{
			throw new ServiceException(t);
		}

		throw new ServiceException(
		    "There is no stub implementation for the interface:  "
		    + ((ac_serviceEndpointInterface == null) ? "null" : ac_serviceEndpointInterface.getName())
		);
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public Remote getPort(QName aqn_portName, Class ac_serviceEndpointInterface)
	    throws ServiceException
	{
		if(aqn_portName == null)
			return getPort(ac_serviceEndpointInterface);

		String ls_inputPortName = aqn_portName.getLocalPart();

		if("SDI_CB_ConsultaCatalogos".equals(ls_inputPortName))
			return getSDI_CB_ConsultaCatalogos();
		else
		{
			Remote _stub = getPort(ac_serviceEndpointInterface);
			((Stub)_stub).setPortName(aqn_portName);

			return _stub;
		}
	}

	public Iterator getPorts()
	{
		if(ports == null)
		{
			ports = new HashSet();
			ports.add(
			    new QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultacatalogos/v1",
			        "SDI_CB_ConsultaCatalogos"
			    )
			);
		}

		return ports.iterator();
	}

	public co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1.SDI_CB_ConsultaCatalogos getSDI_CB_ConsultaCatalogos()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SDI_CB_ConsultaCatalogos_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSDI_CB_ConsultaCatalogos(endpoint);
	}

	public co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1.SDI_CB_ConsultaCatalogos getSDI_CB_ConsultaCatalogos(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1.SDI_CB_ConsultaCatalogosSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1.SDI_CB_ConsultaCatalogosSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSDI_CB_ConsultaCatalogosWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public String getSDI_CB_ConsultaCatalogosAddress()
	{
		return SDI_CB_ConsultaCatalogos_address;
	}

	public void setSDI_CB_ConsultaCatalogosEndpointAddress(String address)
	{
		SDI_CB_ConsultaCatalogos_address = address;
	}

	public void setSDI_CB_ConsultaCatalogosWSDDServiceName(String name)
	{
		SDI_CB_ConsultaCatalogosWSDDServiceName = name;
	}

	public String getSDI_CB_ConsultaCatalogosWSDDServiceName()
	{
		return SDI_CB_ConsultaCatalogosWSDDServiceName;
	}

	public QName getServiceName()
	{
		return new QName(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultacatalogos/v1",
		    "SDI_CB_ConsultaCatalogosService"
		);
	}
}
