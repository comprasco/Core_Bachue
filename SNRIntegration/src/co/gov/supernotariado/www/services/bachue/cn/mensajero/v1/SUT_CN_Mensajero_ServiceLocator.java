/**
 * SUT_CN_Mensajero_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.mensajero.v1;


/**
 * Clase que contiene todos las propiedades SUT_CN_Mensajero_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 *
 */
public class SUT_CN_Mensajero_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_Service
{
	/** Propiedad SU T C N mensajero port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CN_MensajeroPortWSDDServiceName = "SUT_CN_MensajeroPort";

	/** Propiedad SU T C N mensajero port address. */
	// Use to get a proxy class for SUT_CN_MensajeroPort
	private java.lang.String SUT_CN_MensajeroPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SU T C N mensajero service locator.
	 */
	public SUT_CN_Mensajero_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SU T C N mensajero service locator.
	 *
	 * @param config de config
	 */
	public SUT_CN_Mensajero_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SU T C N mensajero service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CN_Mensajero_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	/** {@inheritdoc} */
	public java.lang.String getSUT_CN_MensajeroPortAddress()
	{
		return SUT_CN_MensajeroPort_address;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C N mensajero port WSDD service name.
	 *
	 * @return el valor de SU T C N mensajero port WSDD service name
	 */
	public java.lang.String getSUT_CN_MensajeroPortWSDDServiceName()
	{
		return SUT_CN_MensajeroPortWSDDServiceName;
	}

	/**
	 * Modifica el valor de SUT_CN_MensajeroPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSUT_CN_MensajeroPortWSDDServiceName(java.lang.String name)
	{
		SUT_CN_MensajeroPortWSDDServiceName = name;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType getSUT_CN_MensajeroPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CN_MensajeroPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CN_MensajeroPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType getSUT_CN_MensajeroPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_MensajeroSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_MensajeroSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CN_MensajeroPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/**
	 * Modifica el valor de SUT_CN_MensajeroPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSUT_CN_MensajeroPortEndpointAddress(java.lang.String address)
	{
		SUT_CN_MensajeroPort_address = address;
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
			    co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_MensajeroSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_MensajeroSOAP12BindingStub(
					    new java.net.URL(SUT_CN_MensajeroPort_address), this
					);
				_stub.setPortName(getSUT_CN_MensajeroPortWSDDServiceName());

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

		if("SUT_CN_MensajeroPort".equals(inputPortName))
			return getSUT_CN_MensajeroPort();
		else
		{
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub)_stub).setPortName(portName);

			return _stub;
		}
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cn/mensajero/v1", "SUT_CN_Mensajero"
		);
	}

	/** {@inheritdoc} */
	public java.util.Iterator getPorts()
	{
		if(ports == null)
		{
			ports = new java.util.HashSet();
			ports.add(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cn/mensajero/v1", "SUT_CN_MensajeroPort"
			    )
			);
		}

		return ports.iterator();
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
		if("SUT_CN_MensajeroPort".equals(portName))
			setSUT_CN_MensajeroPortEndpointAddress(address);
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
}
