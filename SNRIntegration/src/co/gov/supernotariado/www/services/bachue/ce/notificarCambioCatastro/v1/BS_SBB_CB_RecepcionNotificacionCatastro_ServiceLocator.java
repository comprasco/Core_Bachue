/**
 * BS_SBB_CB_RecepcionNotificacionCatastro_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1;

/**
 * Clase que contiene todos las propiedades BS_SBB_CB_RecepcionNotificacionCatastro_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class BS_SBB_CB_RecepcionNotificacionCatastro_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_Service
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -632470648409308628L;

	/** Propiedad B S SB B C B recepcion notificacion catastro port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String BS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName = "BS_SBB_CB_RecepcionNotificacionCatastroPort";

	/** Propiedad B S SB B C B recepcion notificacion catastro port address. */
	// Use to get a proxy class for BS_SBB_CB_RecepcionNotificacionCatastroPort
	private java.lang.String  BS_SBB_CB_RecepcionNotificacionCatastroPort_address = "http://www.example.com";
	
	/** Propiedad ports. */
	private java.util.HashSet ports                                               = null;

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion catastro service locator.
	 */
	public BS_SBB_CB_RecepcionNotificacionCatastro_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion catastro service locator.
	 *
	 * @param config correspondiente al valor de config
	 */
	public BS_SBB_CB_RecepcionNotificacionCatastro_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B recepcion notificacion catastro service locator.
	 *
	 * @param wsdlLoc correspondiente al valor de wsdl loc
	 * @param sName correspondiente al valor de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public BS_SBB_CB_RecepcionNotificacionCatastro_ServiceLocator(
	    java.lang.String wsdlLoc, javax.xml.namespace.QName sName
	)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_PortType getBS_SBB_CB_RecepcionNotificacionCatastroPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(BS_SBB_CB_RecepcionNotificacionCatastroPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getBS_SBB_CB_RecepcionNotificacionCatastroPort(endpoint);
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_PortType getBS_SBB_CB_RecepcionNotificacionCatastroPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getBS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritDoc} */
	public java.lang.String getBS_SBB_CB_RecepcionNotificacionCatastroPortAddress()
	{
		return BS_SBB_CB_RecepcionNotificacionCatastroPort_address;
	}

	/**
	 * Modifica el valor de BS_SBB_CB_RecepcionNotificacionCatastroPortEndpointAddress.
	 *
	 * @param address correspondiente al valor de address
	 */
	public void setBS_SBB_CB_RecepcionNotificacionCatastroPortEndpointAddress(java.lang.String address)
	{
		BS_SBB_CB_RecepcionNotificacionCatastroPort_address = address;
	}

	/**
	 * Modifica el valor de BS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName.
	 *
	 * @param name correspondiente al valor de name
	 */
	public void setBS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName(java.lang.String name)
	{
		BS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor b S SB B C B recepcion notificacion catastro port WSDD service name.
	 *
	 * @return el valor de b S SB B C B recepcion notificacion catastro port WSDD service name
	 */
	public java.lang.String getBS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName()
	{
		return BS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName;
	}

	/**
	 * Set the endpoint address for the specified port name.
	 *
	 * @param portName correspondiente al valor de port name
	 * @param address correspondiente al valor de address
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public void setEndpointAddress(java.lang.String portName, java.lang.String address)
	    throws javax.xml.rpc.ServiceException
	{
		if("BS_SBB_CB_RecepcionNotificacionCatastroPort".equals(portName))
			setBS_SBB_CB_RecepcionNotificacionCatastroPortEndpointAddress(address);
		else
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
	}

	/**
	 * Set the endpoint address for the specified port name.
	 *
	 * @param portName correspondiente al valor de port name
	 * @param address correspondiente al valor de address
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
	 * @param serviceEndpointInterface correspondiente al valor de service endpoint interface
	 * @return el valor de port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			if(
			    co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.ce.notificarCambioCatastro.v1.BS_SBB_CB_RecepcionNotificacionCatastro_BindingStub(
					    new java.net.URL(BS_SBB_CB_RecepcionNotificacionCatastroPort_address), this
					);
				_stub.setPortName(getBS_SBB_CB_RecepcionNotificacionCatastroPortWSDDServiceName());

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
	 * @param portName correspondiente al valor de port name
	 * @param serviceEndpointInterface correspondiente al valor de service endpoint interface
	 * @return el valor de port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
	    throws javax.xml.rpc.ServiceException
	{
		if(portName == null)
			return getPort(serviceEndpointInterface);

		java.lang.String inputPortName = portName.getLocalPart();

		if("BS_SBB_CB_RecepcionNotificacionCatastroPort".equals(inputPortName))
			return getBS_SBB_CB_RecepcionNotificacionCatastroPort();
		else
		{
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub)_stub).setPortName(portName);

			return _stub;
		}
	}

	/** {@inheritDoc} */
	public java.util.Iterator getPorts()
	{
		if(ports == null)
		{
			ports = new java.util.HashSet();
			ports.add(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/ce/notificarCambioCatastro/v1",
			        "BS_SBB_CB_RecepcionNotificacionCatastroPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritDoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ce/notificarCambioCatastro/v1",
		    "BS_SBB_CB_RecepcionNotificacionCatastro"
		);
	}
}
