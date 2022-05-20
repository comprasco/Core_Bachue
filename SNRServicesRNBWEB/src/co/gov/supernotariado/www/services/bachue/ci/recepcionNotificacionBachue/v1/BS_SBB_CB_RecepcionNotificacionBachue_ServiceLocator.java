/**
 * BS_SBB_CB_RecepcionNotificacionBachue_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_RecepcionNotificacionBachue_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
public class BS_SBB_CB_RecepcionNotificacionBachue_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -167622332308831060L;

	/** Propiedad B S SB B C B recepcion notificacion bachue port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String BS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName = "BS_SBB_CB_RecepcionNotificacionBachuePort";

	/** Propiedad B S SB B C B recepcion notificacion bachue port address. */
	// Use to get a proxy class for BS_SBB_CB_RecepcionNotificacionBachuePort
	private java.lang.String BS_SBB_CB_RecepcionNotificacionBachuePort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion bachue service locator.
	 */
	public BS_SBB_CB_RecepcionNotificacionBachue_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion bachue service locator.
	 *
	 * @param config de config
	 */
	public BS_SBB_CB_RecepcionNotificacionBachue_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion bachue service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public BS_SBB_CB_RecepcionNotificacionBachue_ServiceLocator(
	    java.lang.String wsdlLoc, javax.xml.namespace.QName sName
	)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_PortType getBS_SBB_CB_RecepcionNotificacionBachuePort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(BS_SBB_CB_RecepcionNotificacionBachuePort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getBS_SBB_CB_RecepcionNotificacionBachuePort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_PortType getBS_SBB_CB_RecepcionNotificacionBachuePort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getBS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getBS_SBB_CB_RecepcionNotificacionBachuePortAddress()
	{
		return BS_SBB_CB_RecepcionNotificacionBachuePort_address;
	}

	/**
	 * Modifica el valor de BS_SBB_CB_RecepcionNotificacionBachuePortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setBS_SBB_CB_RecepcionNotificacionBachuePortEndpointAddress(java.lang.String address)
	{
		BS_SBB_CB_RecepcionNotificacionBachuePort_address = address;
	}

	/**
	 * Modifica el valor de BS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setBS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName(java.lang.String name)
	{
		BS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor b S SB B C B recepcion notificacion bachue port WSDD service name.
	 *
	 * @return el valor de b S SB B C B recepcion notificacion bachue port WSDD service name
	 */
	public java.lang.String getBS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName()
	{
		return BS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName;
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
		if("BS_SBB_CB_RecepcionNotificacionBachuePort".equals(portName))
			setBS_SBB_CB_RecepcionNotificacionBachuePortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.ci.recepcionNotificacionBachue.v1.BS_SBB_CB_RecepcionNotificacionBachue_BindingStub(
					    new java.net.URL(BS_SBB_CB_RecepcionNotificacionBachuePort_address), this
					);
				_stub.setPortName(getBS_SBB_CB_RecepcionNotificacionBachuePortWSDDServiceName());

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

		if("BS_SBB_CB_RecepcionNotificacionBachuePort".equals(inputPortName))
			return getBS_SBB_CB_RecepcionNotificacionBachuePort();
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
			        "https://www.supernotariado.gov.co/services/bachue/ci/recepcionNotificacionBachue/v1",
			        "BS_SBB_CB_RecepcionNotificacionBachuePort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ci/recepcionNotificacionBachue/v1",
		    "BS_SBB_CB_RecepcionNotificacionBachue"
		);
	}
}
