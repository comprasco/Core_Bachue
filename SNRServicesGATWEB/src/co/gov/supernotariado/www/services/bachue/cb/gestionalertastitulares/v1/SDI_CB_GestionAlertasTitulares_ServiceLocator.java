/**
 * SDI_CB_GestionAlertasTitulares_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1;


/**
 * Clase que contiene todos las propiedades SDI_CB_GestionAlertasTitulares_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SDI_CB_GestionAlertasTitulares_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitulares_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5059276564403780312L;

	/** Propiedad SD I C B gestion alertas titulares port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SDI_CB_GestionAlertasTitularesPortWSDDServiceName = "SDI_CB_GestionAlertasTitularesPort";

	/** Propiedad SD I C B gestion alertas titulares port address. */
	// Use to get a proxy class for SDI_CB_GestionAlertasTitularesPort
	private java.lang.String SDI_CB_GestionAlertasTitularesPort_address = "http://localhost:7001/SNRServicesGATWEB/services/SDI_CB_GestionAlertasTitulares";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SD I C B gestion alertas titulares service locator.
	 */
	public SDI_CB_GestionAlertasTitulares_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SD I C B gestion alertas titulares service locator.
	 *
	 * @param config de config
	 */
	public SDI_CB_GestionAlertasTitulares_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SD I C B gestion alertas titulares service locator.
	 *
	 * @param wsdlLoc de wsdl loc
	 * @param sName de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SDI_CB_GestionAlertasTitulares_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SDI_CB_GestionAlertasTitularesPort".equals(portName))
			setSDI_CB_GestionAlertasTitularesPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitulares_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitularesSOAP12BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitularesSOAP12BindingStub(
					    new java.net.URL(SDI_CB_GestionAlertasTitularesPort_address), this
					);
				_stub.setPortName(getSDI_CB_GestionAlertasTitularesPortWSDDServiceName());

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

		if("SDI_CB_GestionAlertasTitularesPort".equals(inputPortName))
			return getSDI_CB_GestionAlertasTitularesPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1",
			        "SDI_CB_GestionAlertasTitularesPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitulares_PortType getSDI_CB_GestionAlertasTitularesPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SDI_CB_GestionAlertasTitularesPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSDI_CB_GestionAlertasTitularesPort(endpoint);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitulares_PortType getSDI_CB_GestionAlertasTitularesPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitularesSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1.SDI_CB_GestionAlertasTitularesSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSDI_CB_GestionAlertasTitularesPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritdoc} */
	public java.lang.String getSDI_CB_GestionAlertasTitularesPortAddress()
	{
		return SDI_CB_GestionAlertasTitularesPort_address;
	}

	/**
	 * Modifica el valor de SDI_CB_GestionAlertasTitularesPortEndpointAddress.
	 *
	 * @param address de address
	 */
	public void setSDI_CB_GestionAlertasTitularesPortEndpointAddress(java.lang.String address)
	{
		SDI_CB_GestionAlertasTitularesPort_address = address;
	}

	/**
	 * Modifica el valor de SDI_CB_GestionAlertasTitularesPortWSDDServiceName.
	 *
	 * @param name de name
	 */
	public void setSDI_CB_GestionAlertasTitularesPortWSDDServiceName(java.lang.String name)
	{
		SDI_CB_GestionAlertasTitularesPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SD I C B gestion alertas titulares port WSDD service name.
	 *
	 * @return el valor de SD I C B gestion alertas titulares port WSDD service name
	 */
	public java.lang.String getSDI_CB_GestionAlertasTitularesPortWSDDServiceName()
	{
		return SDI_CB_GestionAlertasTitularesPortWSDDServiceName;
	}

	/** {@inheritdoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/cb/gestionalertastitulares/v1",
		    "SDI_CB_GestionAlertasTitulares"
		);
	}
}
