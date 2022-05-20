/**
 * SUT_CN_NotificadorCorrespondencia_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1;


/**
 * Clase que contiene todos las propiedades SUT_CN_NotificadorCorrespondencia_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
public class SUT_CN_NotificadorCorrespondencia_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9081116299949313470L;

	/** Propiedad SU T C N notificador correspondencia port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CN_NotificadorCorrespondenciaPortWSDDServiceName = "SUT_CN_NotificadorCorrespondenciaPort";

	/** Propiedad SU T C N notificador correspondencia port address. */
	// Use to get a proxy class for SUT_CN_NotificadorCorrespondenciaPort
	private java.lang.String SUT_CN_NotificadorCorrespondenciaPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SU T C N notificador correspondencia service locator.
	 */
	public SUT_CN_NotificadorCorrespondencia_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SU T C N notificador correspondencia service locator.
	 *
	 * @param config de config
	 */
	public SUT_CN_NotificadorCorrespondencia_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SU T C N notificador correspondencia service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CN_NotificadorCorrespondencia_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SUT_CN_NotificadorCorrespondenciaPort".equals(portName))
			setSUT_CN_NotificadorCorrespondenciaPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondenciaSOAP12BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondenciaSOAP12BindingStub(
					    new java.net.URL(SUT_CN_NotificadorCorrespondenciaPort_address), this
					);
				_stub.setPortName(getSUT_CN_NotificadorCorrespondenciaPortWSDDServiceName());

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

		if("SUT_CN_NotificadorCorrespondenciaPort".equals(inputPortName))
			return getSUT_CN_NotificadorCorrespondenciaPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/cn/notificadorcorrespondencia/v1",
			        "SUT_CN_NotificadorCorrespondenciaPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType getSUT_CN_NotificadorCorrespondenciaPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CN_NotificadorCorrespondenciaPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CN_NotificadorCorrespondenciaPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondencia_PortType getSUT_CN_NotificadorCorrespondenciaPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondenciaSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.cn.notificadorcorrespondencia.v1.SUT_CN_NotificadorCorrespondenciaSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CN_NotificadorCorrespondenciaPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getSUT_CN_NotificadorCorrespondenciaPortAddress()
	{
		return SUT_CN_NotificadorCorrespondenciaPort_address;
	}

	/**
	 * Modifica el valor de SUT_CN_NotificadorCorrespondenciaPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSUT_CN_NotificadorCorrespondenciaPortEndpointAddress(java.lang.String address)
	{
		SUT_CN_NotificadorCorrespondenciaPort_address = address;
	}

	/**
	 * Modifica el valor de SUT_CN_NotificadorCorrespondenciaPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSUT_CN_NotificadorCorrespondenciaPortWSDDServiceName(java.lang.String name)
	{
		SUT_CN_NotificadorCorrespondenciaPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C N notificador correspondencia port WSDD service name.
	 *
	 * @return el valor de SU T C N notificador correspondencia port WSDD service name
	 */
	public java.lang.String getSUT_CN_NotificadorCorrespondenciaPortWSDDServiceName()
	{
		return SUT_CN_NotificadorCorrespondenciaPortWSDDServiceName;
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cn/notificadorcorrespondencia/v1",
		    "SUT_CN_NotificadorCorrespondencia"
		);
	}
}
