/**
 * SUT_CO_EnvioDocumentos_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1;

public class SUT_CO_EnvioDocumentos_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_Service
{
	private static final long serialVersionUID = -1942788546629197200L;

	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CO_EnvioDocumentosPortWSDDServiceName = "SUT_CO_EnvioDocumentosPort";

	// Use to get a proxy class for SUT_CO_EnvioDocumentosPort
	private java.lang.String  SUT_CO_EnvioDocumentosPort_address = "http://www.example.com";
	private java.util.HashSet ports                              = null;

	public SUT_CO_EnvioDocumentos_ServiceLocator()
	{
	}

	public SUT_CO_EnvioDocumentos_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	public SUT_CO_EnvioDocumentos_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SUT_CO_EnvioDocumentosPort".equals(portName))
			setSUT_CO_EnvioDocumentosPortEndpointAddress(address);
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
			if(
			    co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentosSOAP12BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentosSOAP12BindingStub(
					    new java.net.URL(SUT_CO_EnvioDocumentosPort_address), this
					);
				_stub.setPortName(getSUT_CO_EnvioDocumentosPortWSDDServiceName());

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

		if("SUT_CO_EnvioDocumentosPort".equals(inputPortName))
			return getSUT_CO_EnvioDocumentosPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/co/enviodocumentos/v1",
			        "SUT_CO_EnvioDocumentosPort"
			    )
			);
		}

		return ports.iterator();
	}

	public co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType getSUT_CO_EnvioDocumentosPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CO_EnvioDocumentosPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CO_EnvioDocumentosPort(endpoint);
	}

	public co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentos_PortType getSUT_CO_EnvioDocumentosPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentosSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.co.enviodocumentos.v1.SUT_CO_EnvioDocumentosSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CO_EnvioDocumentosPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public java.lang.String getSUT_CO_EnvioDocumentosPortAddress()
	{
		return SUT_CO_EnvioDocumentosPort_address;
	}

	public void setSUT_CO_EnvioDocumentosPortEndpointAddress(java.lang.String address)
	{
		SUT_CO_EnvioDocumentosPort_address = address;
	}

	public void setSUT_CO_EnvioDocumentosPortWSDDServiceName(java.lang.String name)
	{
		SUT_CO_EnvioDocumentosPortWSDDServiceName = name;
	}

	public java.lang.String getSUT_CO_EnvioDocumentosPortWSDDServiceName()
	{
		return SUT_CO_EnvioDocumentosPortWSDDServiceName;
	}

	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/co/enviodocumentos/v1", "SUT_CO_EnvioDocumentos"
		);
	}
}
