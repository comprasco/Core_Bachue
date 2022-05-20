/**
 * SUT_CB_Notificador_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.notificador.v1;


/**
 * Clase que contiene todos las propiedades SUT_CB_Notificador_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class SUT_CB_Notificador_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4861361400665955896L;

	/** Propiedad SU T C B notificador port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CB_NotificadorPortWSDDServiceName = "SUT_CB_NotificadorPort";

	/** Propiedad SU T C B notificador port address. */
	// Use to get a proxy class for SUT_CB_NotificadorPort
	private java.lang.String SUT_CB_NotificadorPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SU T C B notificador service locator.
	 */
	public SUT_CB_Notificador_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SU T C B notificador service locator.
	 *
	 * @param config de config
	 */
	public SUT_CB_Notificador_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SU T C B notificador service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CB_Notificador_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SUT_CB_NotificadorPort".equals(portName))
			setSUT_CB_NotificadorPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_NotificadorSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_NotificadorSOAP12BindingStub(
					    new java.net.URL(SUT_CB_NotificadorPort_address), this
					);
				_stub.setPortName(getSUT_CB_NotificadorPortWSDDServiceName());

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

		if("SUT_CB_NotificadorPort".equals(inputPortName))
			return getSUT_CB_NotificadorPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/cb/notificador/v1", "SUT_CB_NotificadorPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType getSUT_CB_NotificadorPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CB_NotificadorPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CB_NotificadorPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_Notificador_PortType getSUT_CB_NotificadorPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_NotificadorSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.cb.notificador.v1.SUT_CB_NotificadorSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CB_NotificadorPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getSUT_CB_NotificadorPortAddress()
	{
		return SUT_CB_NotificadorPort_address;
	}

	/**
	 * Modifica el valor de SUT_CB_NotificadorPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSUT_CB_NotificadorPortEndpointAddress(java.lang.String address)
	{
		SUT_CB_NotificadorPort_address = address;
	}

	/**
	 * Modifica el valor de SUT_CB_NotificadorPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSUT_CB_NotificadorPortWSDDServiceName(java.lang.String name)
	{
		SUT_CB_NotificadorPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C B notificador port WSDD service name.
	 *
	 * @return el valor de SU T C B notificador port WSDD service name
	 */
	public java.lang.String getSUT_CB_NotificadorPortWSDDServiceName()
	{
		return SUT_CB_NotificadorPortWSDDServiceName;
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cb/notificador/v1", "SUT_CB_Notificador"
		);
	}
}
