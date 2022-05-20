/**
 * SUT_CB_ConsultaMigracion_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1;


/**
 * Clase que contiene todos las propiedades SUT_CB_ConsultaMigracion_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SUT_CB_ConsultaMigracion_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_Service
{
	/** Propiedad SU T C B consulta migracion port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SUT_CB_ConsultaMigracionPortWSDDServiceName = "SUT_CB_ConsultaMigracionPort";

	/** Propiedad SU T C B consulta migracion port address. */
	// Use to get a proxy class for SUT_CB_ConsultaMigracionPort
	private java.lang.String SUT_CB_ConsultaMigracionPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SU T C B consulta migracion service locator.
	 */
	public SUT_CB_ConsultaMigracion_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SU T C B consulta migracion service locator.
	 *
	 * @param config de config
	 */
	public SUT_CB_ConsultaMigracion_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SU T C B consulta migracion service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CB_ConsultaMigracion_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SUT_CB_ConsultaMigracionPort".equals(portName))
			setSUT_CB_ConsultaMigracionPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracionSOAP12BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracionSOAP12BindingStub(
					    new java.net.URL(SUT_CB_ConsultaMigracionPort_address), this
					);
				_stub.setPortName(getSUT_CB_ConsultaMigracionPortWSDDServiceName());

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

		if("SUT_CB_ConsultaMigracionPort".equals(inputPortName))
			return getSUT_CB_ConsultaMigracionPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1",
			        "SUT_CB_ConsultaMigracionPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_PortType getSUT_CB_ConsultaMigracionPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SUT_CB_ConsultaMigracionPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSUT_CB_ConsultaMigracionPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_PortType getSUT_CB_ConsultaMigracionPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracionSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracionSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSUT_CB_ConsultaMigracionPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getSUT_CB_ConsultaMigracionPortAddress()
	{
		return SUT_CB_ConsultaMigracionPort_address;
	}

	/**
	 * Modifica el valor de SUT_CB_ConsultaMigracionPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSUT_CB_ConsultaMigracionPortEndpointAddress(java.lang.String address)
	{
		SUT_CB_ConsultaMigracionPort_address = address;
	}

	/**
	 * Modifica el valor de SUT_CB_ConsultaMigracionPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSUT_CB_ConsultaMigracionPortWSDDServiceName(java.lang.String name)
	{
		SUT_CB_ConsultaMigracionPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C B consulta migracion port WSDD service name.
	 *
	 * @return el valor de SU T C B consulta migracion port WSDD service name
	 */
	public java.lang.String getSUT_CB_ConsultaMigracionPortWSDDServiceName()
	{
		return SUT_CB_ConsultaMigracionPortWSDDServiceName;
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1", "SUT_CB_ConsultaMigracion"
		);
	}
}
