/**
 * SUT_CE_MensajeroSms_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1;


/**
 * Clase que contiene todos las propiedades SUT_CE_MensajeroSms_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class SUT_CE_MensajeroSms_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7854204506416864448L;

	/** Propiedad SU T C E mensajero sms port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CE_MensajeroSmsPortWSDDServiceName = "SUT_CE_MensajeroSmsPort";

	/** Propiedad SU T C E mensajero sms port address. */
	// Use to get a proxy class for SUT_CE_MensajeroSmsPort
	private java.lang.String SUT_CE_MensajeroSmsPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SU T C E mensajero sms service locator.
	 */
	public SUT_CE_MensajeroSms_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SU T C E mensajero sms service locator.
	 *
	 * @param config de config
	 */
	public SUT_CE_MensajeroSms_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SU T C E mensajero sms service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CE_MensajeroSms_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SUT_CE_MensajeroSmsPort".equals(portName))
			setSUT_CE_MensajeroSmsPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSmsSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSmsSOAP12BindingStub(
					    new java.net.URL(SUT_CE_MensajeroSmsPort_address), this
					);
				_stub.setPortName(getSUT_CE_MensajeroSmsPortWSDDServiceName());

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

		if("SUT_CE_MensajeroSmsPort".equals(inputPortName))
			return getSUT_CE_MensajeroSmsPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/ce/mensajerosms/v1", "SUT_CE_MensajeroSmsPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType getSUT_CE_MensajeroSmsPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CE_MensajeroSmsPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CE_MensajeroSmsPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSms_PortType getSUT_CE_MensajeroSmsPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSmsSOAP12BindingStub _stub = new co.gov.supernotariado.www.services.bachue.ce.mensajerosms.v1.SUT_CE_MensajeroSmsSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CE_MensajeroSmsPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getSUT_CE_MensajeroSmsPortAddress()
	{
		return SUT_CE_MensajeroSmsPort_address;
	}

	/**
	 * Modifica el valor de SUT_CE_MensajeroSmsPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSUT_CE_MensajeroSmsPortEndpointAddress(java.lang.String address)
	{
		SUT_CE_MensajeroSmsPort_address = address;
	}

	/**
	 * Modifica el valor de SUT_CE_MensajeroSmsPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSUT_CE_MensajeroSmsPortWSDDServiceName(java.lang.String name)
	{
		SUT_CE_MensajeroSmsPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C E mensajero sms port WSDD service name.
	 *
	 * @return el valor de SU T C E mensajero sms port WSDD service name
	 */
	public java.lang.String getSUT_CE_MensajeroSmsPortWSDDServiceName()
	{
		return SUT_CE_MensajeroSmsPortWSDDServiceName;
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ce/mensajerosms/v1", "SUT_CE_MensajeroSms"
		);
	}
}
