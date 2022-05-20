/**
 * SBB_CI_OperacionesFinancieras_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2;


/**
 * Clase que contiene todos las propiedades SBB_CI_OperacionesFinancieras_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SBB_CI_OperacionesFinancieras_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6307253399162221025L;

	/** Propiedad SB B C I operaciones financieras port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SBB_CI_OperacionesFinancierasPortWSDDServiceName = "SBB_CI_OperacionesFinancierasPort";

	/** Propiedad SB B C I operaciones financieras port address. */
	// Use to get a proxy class for SBB_CI_OperacionesFinancierasPort
	private java.lang.String SBB_CI_OperacionesFinancierasPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/**
	 * Instancia un nuevo objeto SB B C I operaciones financieras service locator.
	 */
	public SBB_CI_OperacionesFinancieras_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SB B C I operaciones financieras service locator.
	 *
	 * @param config correspondiente al valor de config
	 */
	public SBB_CI_OperacionesFinancieras_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SB B C I operaciones financieras service locator.
	 *
	 * @param wsdlLoc correspondiente al valor de wsdl loc
	 * @param sName correspondiente al valor de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SBB_CI_OperacionesFinancieras_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
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
		if("SBB_CI_OperacionesFinancierasPort".equals(portName))
			setSBB_CI_OperacionesFinancierasPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancierasSOAP12BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancierasSOAP12BindingStub(
					    new java.net.URL(SBB_CI_OperacionesFinancierasPort_address), this
					);
				_stub.setPortName(getSBB_CI_OperacionesFinancierasPortWSDDServiceName());

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

		if("SBB_CI_OperacionesFinancierasPort".equals(inputPortName))
			return getSBB_CI_OperacionesFinancierasPort();
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
			        "https://www.supernotariado.gov.co/services/bachue/ci/operacionesfinancieras/v2",
			        "SBB_CI_OperacionesFinancierasPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType getSBB_CI_OperacionesFinancierasPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SBB_CI_OperacionesFinancierasPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		return getSBB_CI_OperacionesFinancierasPort(endpoint);
	}

	/** {@inheritDoc} */
	public co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancieras_PortType getSBB_CI_OperacionesFinancierasPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancierasSOAP12BindingStub _stub =
				new co.gov.supernotariado.www.services.bachue.ci.operacionesfinancieras.v2.SBB_CI_OperacionesFinancierasSOAP12BindingStub(
				    portAddress, this
				);
			_stub.setPortName(getSBB_CI_OperacionesFinancierasPortWSDDServiceName());

			return _stub;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritDoc} */
	public java.lang.String getSBB_CI_OperacionesFinancierasPortAddress()
	{
		return SBB_CI_OperacionesFinancierasPort_address;
	}

	/**
	 * Modifica el valor de SBB_CI_OperacionesFinancierasPortEndpointAddress.
	 *
	 * @param address correspondiente al valor de address
	 */
	public void setSBB_CI_OperacionesFinancierasPortEndpointAddress(java.lang.String address)
	{
		SBB_CI_OperacionesFinancierasPort_address = address;
	}

	/**
	 * Modifica el valor de SBB_CI_OperacionesFinancierasPortWSDDServiceName.
	 *
	 * @param name correspondiente al valor de name
	 */
	public void setSBB_CI_OperacionesFinancierasPortWSDDServiceName(java.lang.String name)
	{
		SBB_CI_OperacionesFinancierasPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SB B C I operaciones financieras port WSDD service name.
	 *
	 * @return el valor de SB B C I operaciones financieras port WSDD service name
	 */
	public java.lang.String getSBB_CI_OperacionesFinancierasPortWSDDServiceName()
	{
		return SBB_CI_OperacionesFinancierasPortWSDDServiceName;
	}

	/** {@inheritDoc} */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ci/operacionesfinancieras/v2",
		    "SBB_CI_OperacionesFinancieras"
		);
	}
}
