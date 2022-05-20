/**
 * SUT_CO_CierreAperturaExpediente_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1;


/**
 * Clase que contiene todos las propiedades SUT_CO_CierreAperturaExpediente_ServiceLocator.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/05/2020
 */
public class SUT_CO_CierreAperturaExpediente_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_Service
{
	/** Propiedad SU T C O cierre apertura expediente port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CO_CierreAperturaExpedientePortWSDDServiceName = "SUT_CO_CierreAperturaExpedientePort";

	/** Propiedad SU T C O cierre apertura expediente port address. */
	// Use to get a proxy class for SUT_CO_CierreAperturaExpedientePort
	private java.lang.String SUT_CO_CierreAperturaExpedientePort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SU T C O cierre apertura expediente service locator.
	 */
	public SUT_CO_CierreAperturaExpediente_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SU T C O cierre apertura expediente service locator.
	 *
	 * @param config de config
	 */
	public SUT_CO_CierreAperturaExpediente_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SU T C O cierre apertura expediente service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CO_CierreAperturaExpediente_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SUT_CO_CierreAperturaExpedientePort".equals(portName))
			setSUT_CO_CierreAperturaExpedientePortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpedienteSOAP12BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpedienteSOAP12BindingStub(
					    new java.net.URL(SUT_CO_CierreAperturaExpedientePort_address), this
					);
				_stub.setPortName(getSUT_CO_CierreAperturaExpedientePortWSDDServiceName());

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

		if("SUT_CO_CierreAperturaExpedientePort".equals(inputPortName))
			return getSUT_CO_CierreAperturaExpedientePort();
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
			        "https://www.supernotariado.gov.co/services/bachue/co/cierreaperturaexpediente/v1",
			        "SUT_CO_CierreAperturaExpedientePort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType getSUT_CO_CierreAperturaExpedientePort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CO_CierreAperturaExpedientePort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CO_CierreAperturaExpedientePort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpediente_PortType getSUT_CO_CierreAperturaExpedientePort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpedienteSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.co.cierreaperturaexpediente.v1.SUT_CO_CierreAperturaExpedienteSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CO_CierreAperturaExpedientePortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getSUT_CO_CierreAperturaExpedientePortAddress()
	{
		return SUT_CO_CierreAperturaExpedientePort_address;
	}

	/**
	 * Modifica el valor de SUT_CO_CierreAperturaExpedientePortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSUT_CO_CierreAperturaExpedientePortEndpointAddress(java.lang.String address)
	{
		SUT_CO_CierreAperturaExpedientePort_address = address;
	}

	/**
	 * Modifica el valor de SUT_CO_CierreAperturaExpedientePortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSUT_CO_CierreAperturaExpedientePortWSDDServiceName(java.lang.String name)
	{
		SUT_CO_CierreAperturaExpedientePortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C O cierre apertura expediente port WSDD service name.
	 *
	 * @return el valor de SU T C O cierre apertura expediente port WSDD service name
	 */
	public java.lang.String getSUT_CO_CierreAperturaExpedientePortWSDDServiceName()
	{
		return SUT_CO_CierreAperturaExpedientePortWSDDServiceName;
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/co/cierreaperturaexpediente/v1",
		    "SUT_CO_CierreAperturaExpediente"
		);
	}
}
