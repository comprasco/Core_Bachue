/**
 * SBB_CB_Salvedades_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.v1;

public class SBB_CB_Salvedades_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_Salvedades_Service
{
	private static final long serialVersionUID = 7543243054145027199L;

	// The WSDD service name defaults to the port name.
	private java.lang.String SBB_CB_SalvedadesPortWSDDServiceName = "SBB_CB_SalvedadesPort";

	// Use to get a proxy class for SBB_CB_SalvedadesPort
	private java.lang.String  SBB_CB_SalvedadesPort_address = "http://www.example.com";
	private java.util.HashSet ports                         = null;

	public SBB_CB_Salvedades_ServiceLocator()
	{
	}

	public SBB_CB_Salvedades_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	public SBB_CB_Salvedades_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SBB_CB_SalvedadesPort".equals(portName))
			setSBB_CB_SalvedadesPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_Salvedades_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_SalvedadesSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_SalvedadesSOAP12BindingStub(
					    new java.net.URL(SBB_CB_SalvedadesPort_address), this
					);
				_stub.setPortName(getSBB_CB_SalvedadesPortWSDDServiceName());

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

		if("SBB_CB_SalvedadesPort".equals(inputPortName))
			return getSBB_CB_SalvedadesPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/v1", "SBB_CB_SalvedadesPort"
			    )
			);
		}

		return ports.iterator();
	}

	public co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_Salvedades_PortType getSBB_CB_SalvedadesPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SBB_CB_SalvedadesPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSBB_CB_SalvedadesPort(endpoint);
	}

	public co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_Salvedades_PortType getSBB_CB_SalvedadesPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_SalvedadesSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.salvedades.v1.SBB_CB_SalvedadesSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSBB_CB_SalvedadesPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public java.lang.String getSBB_CB_SalvedadesPortAddress()
	{
		return SBB_CB_SalvedadesPort_address;
	}

	public void setSBB_CB_SalvedadesPortEndpointAddress(java.lang.String address)
	{
		SBB_CB_SalvedadesPort_address = address;
	}

	public void setSBB_CB_SalvedadesPortWSDDServiceName(java.lang.String name)
	{
		SBB_CB_SalvedadesPortWSDDServiceName = name;
	}

	public java.lang.String getSBB_CB_SalvedadesPortWSDDServiceName()
	{
		return SBB_CB_SalvedadesPortWSDDServiceName;
	}

	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/v1", "SBB_CB_Salvedades"
		);
	}
}
