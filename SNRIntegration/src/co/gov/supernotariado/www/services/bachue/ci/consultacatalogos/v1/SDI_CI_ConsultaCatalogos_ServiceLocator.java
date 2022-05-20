/**
 * SDI_CI_ConsultaCatalogos_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1;


/**
 * Clase que contiene todos las propiedades SDI_CI_ConsultaCatalogos_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SDI_CI_ConsultaCatalogos_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_Service
{
	/** Propiedad SD I C I consulta catalogos port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SDI_CI_ConsultaCatalogosPortWSDDServiceName = "SDI_CI_ConsultaCatalogosPort";

	/** Propiedad SD I C I consulta catalogos port address. */
	// Use to get a proxy class for SDI_CI_ConsultaCatalogosPort
	private java.lang.String SDI_CI_ConsultaCatalogosPort_address = "http://192.168.100.43:7018/services/ci/consultarcatalogos/v1";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

/**
 * OSB Service.
 */
	public SDI_CI_ConsultaCatalogos_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SD I C I consulta catalogos service locator.
	 *
	 * @param config correspondiente al valor de config
	 */
	public SDI_CI_ConsultaCatalogos_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SD I C I consulta catalogos service locator.
	 *
	 * @param wsdlLoc correspondiente al valor de wsdl loc
	 * @param sName correspondiente al valor de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SDI_CI_ConsultaCatalogos_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
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
		if("SDI_CI_ConsultaCatalogosPort".equals(portName))
			setSDI_CI_ConsultaCatalogosPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogosSOAP12BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogosSOAP12BindingStub(
					    new java.net.URL(SDI_CI_ConsultaCatalogosPort_address), this
					);
				_stub.setPortName(getSDI_CI_ConsultaCatalogosPortWSDDServiceName());

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

		if("SDI_CI_ConsultaCatalogosPort".equals(inputPortName))
			return getSDI_CI_ConsultaCatalogosPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/ci/consultacatalogos/v1",
			        "SDI_CI_ConsultaCatalogosPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType getSDI_CI_ConsultaCatalogosPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SDI_CI_ConsultaCatalogosPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSDI_CI_ConsultaCatalogosPort(endpoint);
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogos_PortType getSDI_CI_ConsultaCatalogosPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogosSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.ci.consultacatalogos.v1.SDI_CI_ConsultaCatalogosSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSDI_CI_ConsultaCatalogosPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritDoc} */
	public java.lang.String getSDI_CI_ConsultaCatalogosPortAddress()
	{
		return SDI_CI_ConsultaCatalogosPort_address;
	}

	/**
	 * Modifica el valor de SDI_CI_ConsultaCatalogosPortEndpointAddress.
	 *
	 * @param address correspondiente al valor de address
	 */
	public void setSDI_CI_ConsultaCatalogosPortEndpointAddress(java.lang.String address)
	{
		SDI_CI_ConsultaCatalogosPort_address = address;
	}

	/**
	 * Modifica el valor de SDI_CI_ConsultaCatalogosPortWSDDServiceName.
	 *
	 * @param name correspondiente al valor de name
	 */
	public void setSDI_CI_ConsultaCatalogosPortWSDDServiceName(java.lang.String name)
	{
		SDI_CI_ConsultaCatalogosPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SD I C I consulta catalogos port WSDD service name.
	 *
	 * @return el valor de SD I C I consulta catalogos port WSDD service name
	 */
	public java.lang.String getSDI_CI_ConsultaCatalogosPortWSDDServiceName()
	{
		return SDI_CI_ConsultaCatalogosPortWSDDServiceName;
	}

	/** {@inheritDoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ci/consultacatalogos/v1", "SDI_CI_ConsultaCatalogos"
		);
	}
}
