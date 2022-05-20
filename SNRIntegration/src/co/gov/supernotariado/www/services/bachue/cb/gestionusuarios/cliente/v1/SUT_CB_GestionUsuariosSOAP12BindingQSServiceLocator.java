/**
 * SUT_CB_GestionUsuariosSOAP12BindingQSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionusuarios.cliente.v1;

public class SUT_CB_GestionUsuariosSOAP12BindingQSServiceLocator extends org.apache.axis.client.Service
    implements SUT_CB_GestionUsuariosSOAP12BindingQSService
{
	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CB_GestionUsuariosSOAP12BindingQSPortWSDDServiceName = "SUT_CB_GestionUsuariosSOAP12BindingQSPort";

	// Use to get a proxy class for SUT_CB_GestionUsuariosSOAP12BindingQSPort
	private java.lang.String  SUT_CB_GestionUsuariosSOAP12BindingQSPort_address = "http://192.168.100.43:7016/services/ci/gestionusuarios/v1";
	private java.util.HashSet ports                                             = null;

/**
 * OSB Service
 */
	public SUT_CB_GestionUsuariosSOAP12BindingQSServiceLocator()
	{
	}

	public SUT_CB_GestionUsuariosSOAP12BindingQSServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	public SUT_CB_GestionUsuariosSOAP12BindingQSServiceLocator(
	    java.lang.String wsdlLoc, javax.xml.namespace.QName sName
	)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
	    throws javax.xml.rpc.ServiceException
	{
		if("SUT_CB_GestionUsuariosSOAP12BindingQSPort".equals(portName))
			setSUT_CB_GestionUsuariosSOAP12BindingQSPortEndpointAddress(address);
		else
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
	    throws javax.xml.rpc.ServiceException
	{
		setEndpointAddress(portName.getLocalPart(), address);
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			if(SUT_CB_GestionUsuarios.class.isAssignableFrom(serviceEndpointInterface))
			{
				SUT_CB_GestionUsuariosSOAP12BindingStub _stub = new SUT_CB_GestionUsuariosSOAP12BindingStub(
					    new java.net.URL(SUT_CB_GestionUsuariosSOAP12BindingQSPort_address), this
					);
				_stub.setPortName(getSUT_CB_GestionUsuariosSOAP12BindingQSPortWSDDServiceName());

				return _stub;
			}
		}
		catch(java.lang.Throwable t)
		{
			throw new javax.xml.rpc.ServiceException(t);
		}

		throw new javax.xml.rpc.ServiceException(
		    "There is no stub implementation for the interface:  "
		    + ((serviceEndpointInterface == null) ? "null" : serviceEndpointInterface.getName())
		);
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
	    throws javax.xml.rpc.ServiceException
	{
		if(portName == null)
			return getPort(serviceEndpointInterface);

		java.lang.String inputPortName = portName.getLocalPart();

		if("SUT_CB_GestionUsuariosSOAP12BindingQSPort".equals(inputPortName))
			return getSUT_CB_GestionUsuariosSOAP12BindingQSPort();
		else
		{
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub)_stub).setPortName(portName);

			return _stub;
		}
	}

	public java.util.Iterator getPorts()
	{
		if(ports == null)
		{
			ports = new java.util.HashSet();
			ports.add(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1",
			        "SUT_CB_GestionUsuariosSOAP12BindingQSPort"
			    )
			);
		}

		return ports.iterator();
	}

	public SUT_CB_GestionUsuarios getSUT_CB_GestionUsuariosSOAP12BindingQSPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CB_GestionUsuariosSOAP12BindingQSPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CB_GestionUsuariosSOAP12BindingQSPort(endpoint);
	}

	public SUT_CB_GestionUsuarios getSUT_CB_GestionUsuariosSOAP12BindingQSPort(java.net.URL portAddress)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			SUT_CB_GestionUsuariosSOAP12BindingStub _stub = new SUT_CB_GestionUsuariosSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CB_GestionUsuariosSOAP12BindingQSPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public java.lang.String getSUT_CB_GestionUsuariosSOAP12BindingQSPortAddress()
	{
		return SUT_CB_GestionUsuariosSOAP12BindingQSPort_address;
	}

	public void setSUT_CB_GestionUsuariosSOAP12BindingQSPortEndpointAddress(java.lang.String address)
	{
		SUT_CB_GestionUsuariosSOAP12BindingQSPort_address = address;
	}

	public void setSUT_CB_GestionUsuariosSOAP12BindingQSPortWSDDServiceName(java.lang.String name)
	{
		SUT_CB_GestionUsuariosSOAP12BindingQSPortWSDDServiceName = name;
	}

	public java.lang.String getSUT_CB_GestionUsuariosSOAP12BindingQSPortWSDDServiceName()
	{
		return SUT_CB_GestionUsuariosSOAP12BindingQSPortWSDDServiceName;
	}

	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionusuarios/v1",
		    "SUT_CB_GestionUsuariosSOAP12BindingQSService"
		);
	}
}
