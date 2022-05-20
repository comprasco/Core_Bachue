/**
 * SBB_CB_Inmuebles_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_Inmuebles_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_Inmuebles_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_Service
{
	/** Propiedad SB B C B inmuebles port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SBB_CB_InmueblesPortWSDDServiceName = "SBB_CB_InmueblesPort";

	/** Propiedad SB B C B inmuebles port address. */
	// Use to get a proxy class for SBB_CB_InmueblesPort
	private java.lang.String SBB_CB_InmueblesPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SB B C B inmuebles service locator.
	 */
	public SBB_CB_Inmuebles_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SB B C B inmuebles service locator.
	 *
	 * @param config de config
	 */
	public SBB_CB_Inmuebles_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SB B C B inmuebles service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SBB_CB_Inmuebles_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	/**
	 * Set the endpoint address for the specified port name.
	 *
	 * @param portName de port name
	 * @param address de address
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
	    throws javax.xml.rpc.ServiceException
	{
		if("SBB_CB_InmueblesPort".equals(portName))
			setSBB_CB_InmueblesPortEndpointAddress(address);
		else
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
	}

	/**
	 * Set the endpoint address for the specified port name.
	 *
	 * @param portName de port name
	 * @param address de address
	 * @throws ServiceException cuando se produce algun error en el proceso
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
	 *
	 * @param serviceEndpointInterface de service endpoint interface
	 * @return el valor de port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			if(
			    co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_InmueblesSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_InmueblesSOAP12BindingStub(
					    new java.net.URL(SBB_CB_InmueblesPort_address), this
					);
				_stub.setPortName(getSBB_CB_InmueblesPortWSDDServiceName());

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
	 *
	 * @param portName de port name
	 * @param serviceEndpointInterface de service endpoint interface
	 * @return el valor de port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
	    throws javax.xml.rpc.ServiceException
	{
		if(portName == null)
			return getPort(serviceEndpointInterface);

		java.lang.String inputPortName = portName.getLocalPart();

		if("SBB_CB_InmueblesPort".equals(inputPortName))
			return getSBB_CB_InmueblesPort();
		else
		{
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub)_stub).setPortName(portName);

			return _stub;
		}
	}

	/** {@inheritdoc} */
	public java.util.Iterator getPorts()
	{
		if(ports == null)
		{
			ports = new java.util.HashSet();
			ports.add(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1", "SBB_CB_InmueblesPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_PortType getSBB_CB_InmueblesPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SBB_CB_InmueblesPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSBB_CB_InmueblesPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_PortType getSBB_CB_InmueblesPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_InmueblesSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_InmueblesSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSBB_CB_InmueblesPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getSBB_CB_InmueblesPortAddress()
	{
		return SBB_CB_InmueblesPort_address;
	}

	/**
	 * Modifica el valor de SBB_CB_InmueblesPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSBB_CB_InmueblesPortEndpointAddress(java.lang.String address)
	{
		SBB_CB_InmueblesPort_address = address;
	}

	/**
	 * Modifica el valor de SBB_CB_InmueblesPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSBB_CB_InmueblesPortWSDDServiceName(java.lang.String name)
	{
		SBB_CB_InmueblesPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SB B C B inmuebles port WSDD service name.
	 *
	 * @return el valor de SB B C B inmuebles port WSDD service name
	 */
	public java.lang.String getSBB_CB_InmueblesPortWSDDServiceName()
	{
		return SBB_CB_InmueblesPortWSDDServiceName;
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1", "SBB_CB_Inmuebles"
		);
	}
}
