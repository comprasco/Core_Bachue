/**
 * BS_SBB_CB_PartesInteresadas_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1;


/**
 * Clase que contiene todos las propiedades BS_SBB_CB_PartesInteresadas_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class BS_SBB_CB_PartesInteresadas_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4801023868218949207L;

	/** Propiedad B S SB B C B partes interesadas port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String BS_SBB_CB_PartesInteresadasPortWSDDServiceName = "BS_SBB_CB_PartesInteresadasPort";

	/** Propiedad B S SB B C B partes interesadas port address. */
	// Use to get a proxy class for BS_SBB_CB_PartesInteresadasPort
	private java.lang.String BS_SBB_CB_PartesInteresadasPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto b S SB B C B partes interesadas service locator.
	 */
	public BS_SBB_CB_PartesInteresadas_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B partes interesadas service locator.
	 *
	 * @param config de config
	 */
	public BS_SBB_CB_PartesInteresadas_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto b S SB B C B partes interesadas service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public BS_SBB_CB_PartesInteresadas_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType getBS_SBB_CB_PartesInteresadasPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(BS_SBB_CB_PartesInteresadasPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getBS_SBB_CB_PartesInteresadasPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType getBS_SBB_CB_PartesInteresadasPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getBS_SBB_CB_PartesInteresadasPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getBS_SBB_CB_PartesInteresadasPortAddress()
	{
		return BS_SBB_CB_PartesInteresadasPort_address;
	}

	/**
	 * Modifica el valor de BS_SBB_CB_PartesInteresadasPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setBS_SBB_CB_PartesInteresadasPortEndpointAddress(java.lang.String address)
	{
		BS_SBB_CB_PartesInteresadasPort_address = address;
	}

	/**
	 * Modifica el valor de BS_SBB_CB_PartesInteresadasPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setBS_SBB_CB_PartesInteresadasPortWSDDServiceName(java.lang.String name)
	{
		BS_SBB_CB_PartesInteresadasPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor b S SB B C B partes interesadas port WSDD service name.
	 *
	 * @return el valor de b S SB B C B partes interesadas port WSDD service name
	 */
	public java.lang.String getBS_SBB_CB_PartesInteresadasPortWSDDServiceName()
	{
		return BS_SBB_CB_PartesInteresadasPortWSDDServiceName;
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
		if("BS_SBB_CB_PartesInteresadasPort".equals(portName))
			setBS_SBB_CB_PartesInteresadasPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.ee.partesinteresadas.v1.BS_SBB_CB_PartesInteresadas_BindingStub(
					    new java.net.URL(BS_SBB_CB_PartesInteresadasPort_address), this
					);
				_stub.setPortName(getBS_SBB_CB_PartesInteresadasPortWSDDServiceName());

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

		if("BS_SBB_CB_PartesInteresadasPort".equals(inputPortName))
			return getBS_SBB_CB_PartesInteresadasPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/ee/partesinteresadas/v1",
			        "BS_SBB_CB_PartesInteresadasPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ee/partesinteresadas/v1", "BS_SBB_CB_PartesInteresadas"
		);
	}
}
