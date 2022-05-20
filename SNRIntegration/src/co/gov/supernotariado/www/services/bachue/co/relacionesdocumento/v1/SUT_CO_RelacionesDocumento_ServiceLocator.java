/**
 * SUT_CO_RelacionesDocumento_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.relacionesdocumento.v1;

import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;

import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

import java.net.MalformedURLException;
import java.net.URL;

import java.rmi.Remote;

import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;

import javax.xml.rpc.ServiceException;


public class SUT_CO_RelacionesDocumento_ServiceLocator extends Service implements SUT_CO_RelacionesDocumento_Service
{
	private static final long serialVersionUID = 5138661868152590206L;
	private HashSet           ports            = null;

	// The WSDD service name defaults to the port name.
	private String SUT_CO_RelacionesDocumentoPortWSDDServiceName = "SUT_CO_RelacionesDocumentoPort";

	// Use to get a proxy class for SUT_CO_RelacionesDocumentoPort
	private String SUT_CO_RelacionesDocumentoPort_address = "http://www.example.com";

	public SUT_CO_RelacionesDocumento_ServiceLocator()
	{
	}

	public SUT_CO_RelacionesDocumento_ServiceLocator(EngineConfiguration aec_config)
	{
		super(aec_config);
	}

	public SUT_CO_RelacionesDocumento_ServiceLocator(String as_wsdlLoc, QName aqn_sName)
	    throws ServiceException
	{
		super(as_wsdlLoc, aqn_sName);
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(String as_portName, String as_address)
	    throws ServiceException
	{
		if("SUT_CO_RelacionesDocumentoPort".equals(as_portName))
			setSUT_CO_RelacionesDocumentoPortEndpointAddress(as_address);
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
			if(SUT_CO_RelacionesDocumento_PortType.class.isAssignableFrom(ac_serviceEndpointInterface))
			{
				SUT_CO_RelacionesDocumentoSOAP12BindingStub _stub = new SUT_CO_RelacionesDocumentoSOAP12BindingStub(
					    new URL(SUT_CO_RelacionesDocumentoPort_address), this
					);
				_stub.setPortName(getSUT_CO_RelacionesDocumentoPortWSDDServiceName());

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

		if("SUT_CO_RelacionesDocumentoPort".equals(ls_inputPortName))
			return getSUT_CO_RelacionesDocumentoPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/co/relacionesdocumento/v1",
			        "SUT_CO_RelacionesDocumentoPort"
			    )
			);
		}

		return ports.iterator();
	}

	public SUT_CO_RelacionesDocumento_PortType getSUT_CO_RelacionesDocumentoPort()
	    throws ServiceException
	{
		URL lu_endpoint;

		try
		{
			lu_endpoint = new URL(SUT_CO_RelacionesDocumentoPort_address);
		}
		catch(MalformedURLException e)
		{
			throw new ServiceException(e);
		}

		return getSUT_CO_RelacionesDocumentoPort(lu_endpoint);
	}

	public SUT_CO_RelacionesDocumento_PortType getSUT_CO_RelacionesDocumentoPort(URL au_portAddress)
	    throws ServiceException
	{
		try
		{
			SUT_CO_RelacionesDocumentoSOAP12BindingStub _stub = new SUT_CO_RelacionesDocumentoSOAP12BindingStub(
				    au_portAddress, this
				);
			_stub.setPortName(getSUT_CO_RelacionesDocumentoPortWSDDServiceName());

			return _stub;
		}
		catch(AxisFault e)
		{
			return null;
		}
	}

	public String getSUT_CO_RelacionesDocumentoPortAddress()
	{
		return SUT_CO_RelacionesDocumentoPort_address;
	}

	public void setSUT_CO_RelacionesDocumentoPortEndpointAddress(String as_address)
	{
		SUT_CO_RelacionesDocumentoPort_address = as_address;
	}

	public void setSUT_CO_RelacionesDocumentoPortWSDDServiceName(String as_name)
	{
		SUT_CO_RelacionesDocumentoPortWSDDServiceName = as_name;
	}

	public String getSUT_CO_RelacionesDocumentoPortWSDDServiceName()
	{
		return SUT_CO_RelacionesDocumentoPortWSDDServiceName;
	}

	public QName getServiceName()
	{
		return new QName(
		    "https://www.supernotariado.gov.co/services/bachue/co/relacionesdocumento/v1", "SUT_CO_RelacionesDocumento"
		);
	}
}
