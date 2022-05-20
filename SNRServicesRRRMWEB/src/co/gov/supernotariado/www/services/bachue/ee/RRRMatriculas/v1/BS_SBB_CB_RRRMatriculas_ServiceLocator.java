/**
 * BS_SBB_CB_RRRMatriculas_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1;

public class BS_SBB_CB_RRRMatriculas_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_Service
{
	private static final long serialVersionUID = 6175000909605267835L;

	// The WSDD service name defaults to the port name.
	private java.lang.String BS_SBB_CB_RRRMatriculasPortWSDDServiceName = "BS_SBB_CB_RRRMatriculasPort";

	// Use to get a proxy class for BS_SBB_CB_RRRMatriculasPort
	private java.lang.String  BS_SBB_CB_RRRMatriculasPort_address = "http://www.example.com";
	private java.util.HashSet ports                               = null;

	public BS_SBB_CB_RRRMatriculas_ServiceLocator()
	{
	}

	public BS_SBB_CB_RRRMatriculas_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	public BS_SBB_CB_RRRMatriculas_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	public co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_PortType getBS_SBB_CB_RRRMatriculasPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(BS_SBB_CB_RRRMatriculasPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getBS_SBB_CB_RRRMatriculasPort(endpoint);
	}

	public co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_PortType getBS_SBB_CB_RRRMatriculasPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getBS_SBB_CB_RRRMatriculasPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public java.lang.String getBS_SBB_CB_RRRMatriculasPortAddress()
	{
		return BS_SBB_CB_RRRMatriculasPort_address;
	}

	public void setBS_SBB_CB_RRRMatriculasPortEndpointAddress(java.lang.String address)
	{
		BS_SBB_CB_RRRMatriculasPort_address = address;
	}

	public void setBS_SBB_CB_RRRMatriculasPortWSDDServiceName(java.lang.String name)
	{
		BS_SBB_CB_RRRMatriculasPortWSDDServiceName = name;
	}

	public java.lang.String getBS_SBB_CB_RRRMatriculasPortWSDDServiceName()
	{
		return BS_SBB_CB_RRRMatriculasPortWSDDServiceName;
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
	    throws javax.xml.rpc.ServiceException
	{
		if("BS_SBB_CB_RRRMatriculasPort".equals(portName))
			setBS_SBB_CB_RRRMatriculasPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_BindingStub(
					    new java.net.URL(BS_SBB_CB_RRRMatriculasPort_address), this
					);
				_stub.setPortName(getBS_SBB_CB_RRRMatriculasPortWSDDServiceName());

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

		if("BS_SBB_CB_RRRMatriculasPort".equals(inputPortName))
			return getBS_SBB_CB_RRRMatriculasPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/ee/RRRMatriculas/v1",
			        "BS_SBB_CB_RRRMatriculasPort"
			    )
			);
		}

		return ports.iterator();
	}

	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ee/RRRMatriculas/v1", "BS_SBB_CB_RRRMatriculas"
		);
	}
}
