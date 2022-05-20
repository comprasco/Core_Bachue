/**
 * SBB_EE_IndicePropietarios_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1;

import org.apache.axis.client.Stub;

import org.apache.axis.message.SOAPHeaderElement;

import java.security.SecureRandom;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.namespace.QName;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;


/**
 * Clase que contiene todos las propiedades SBB_EE_IndicePropietarios_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public class SBB_EE_IndicePropietarios_ServiceLocator extends org.apache.axis.client.Service implements co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5075175905161961938L;

	/** Constante cs_DATE_FORMAT. */
	private static final String cs_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/** Constante cs_WSSE_URI. */
	private static final String cs_WSSE_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

	/** Constante cs_WSU_URI. */
	private static final String cs_WSU_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

	/** Propiedad SB B E E indice propietarios port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private java.lang.String SBB_EE_IndicePropietariosPortWSDDServiceName = "SBB_EE_IndicePropietariosPort";

	/** Propiedad SB B E E indice propietarios port address. */
	// Use to get a proxy class for SBB_EE_IndicePropietariosPort
	private java.lang.String SBB_EE_IndicePropietariosPort_address = "http://www.example.com";

	/** Propiedad ports. */
	private java.util.HashSet ports = null;

	/** Propiedad is password. */
	private String is_password;

	/** Propiedad is username. */
	private String is_username;

	/**
	 * Instancia un nuevo objeto SB B E E indice propietarios service locator.
	 */
	public SBB_EE_IndicePropietarios_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SB B E E indice propietarios service locator.
	 *
	 * @param config correspondiente al valor de config
	 */
	public SBB_EE_IndicePropietarios_ServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	/**
	 * Instancia un nuevo objeto SB B E E indice propietarios service locator.
	 *
	 * @param wsdlLoc correspondiente al valor de wsdl loc
	 * @param sName correspondiente al valor de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SBB_EE_IndicePropietarios_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
	    throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_Service#setPassword(java.lang.String)
	 */
	public void setPassword(String as_s)
	{
		is_password = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor password.
	 *
	 * @return el valor de password
	 */
	public String getPassword()
	{
		return is_password;
	}

	/* (non-Javadoc)
	 * @see co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_Service#setUsername(java.lang.String)
	 */
	public void setUsername(String as_s)
	{
		is_username = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor username.
	 *
	 * @return el valor de username
	 */
	public String getUsername()
	{
		return is_username;
	}

	/**
	 * Adds the ws security header.
	 *
	 * @param as_binding de as binding
	 * @param as_user de as user
	 * @param as_password de as password
	 * @throws SOAPException cuando se produce algun error en el proceso
	 */
	private void addWsSecurityHeader(Stub as_binding, String as_user, String as_password)
	    throws SOAPException
	{
		QName             lqn_headerName;
		SOAPHeaderElement lsoaphe_header;

		lqn_headerName     = new QName(cs_WSSE_URI, "Security");
		lsoaphe_header     = new SOAPHeaderElement(lqn_headerName);

		lsoaphe_header.setActor(null);
		lsoaphe_header.setPrefix("wsse");
		lsoaphe_header.setMustUnderstand(false);
		lsoaphe_header.addNamespaceDeclaration("wsu", cs_WSU_URI);

		{
			SOAPElement lsoape_usernameToken;

			lsoape_usernameToken = lsoaphe_header.addChildElement("UsernameToken");

			{
				SOAPElement lsoape_user;

				lsoape_user = lsoape_usernameToken.addChildElement("Username");
				lsoape_user.setValue(as_user);
			}

			{
				SOAPElement lsoape_password;

				lsoape_password = lsoape_usernameToken.addChildElement("Password");
				lsoape_password.setValue(as_password);
			}

			{
				SOAPElement lsoape_nonce;

				lsoape_nonce = lsoape_usernameToken.addChildElement("Nonce");
				lsoape_nonce.setValue(generateNonce());
			}

			{
				SOAPElement lsoape_created;

				lsoape_created = lsoape_usernameToken.addChildElement("Created");

				lsoape_created.setPrefix("wsu");
				lsoape_created.setValue(generateTimestamp());
			}
		}

		as_binding.setHeader(lsoaphe_header);
	}

	/**
	 * Generate nonce.
	 *
	 * @return el valor de string
	 */
	private String generateNonce()
	{
		SecureRandom  lsr_random;
		StringBuilder lsb_nonce;

		lsr_random     = new SecureRandom();
		lsb_nonce      = new StringBuilder();

		for(int i = 0; i < 15; i++)
			lsb_nonce.append(lsr_random.nextInt(10));

		return "msJPTHku44rHAqPIRvbNQA==";    // lsb_nonce.toString();
	}

	/**
	 * Generate timestamp.
	 *
	 * @return el valor de string
	 */
	private String generateTimestamp()
	{
		return new SimpleDateFormat(cs_DATE_FORMAT).format(new Date());
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
		if("SBB_EE_IndicePropietariosPort".equals(portName))
			setSBB_EE_IndicePropietariosPortEndpointAddress(address);
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
			    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType.class
				    .isAssignableFrom(serviceEndpointInterface)
			)
			{
				co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_BindingStub _stub =
					new co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_BindingStub(
					    new java.net.URL(SBB_EE_IndicePropietariosPort_address), this
					);
				_stub.setPortName(getSBB_EE_IndicePropietariosPortWSDDServiceName());

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

		if("SBB_EE_IndicePropietariosPort".equals(inputPortName))
			return getSBB_EE_IndicePropietariosPort();
		else
		{
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub)_stub).setPortName(portName);

			return _stub;
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de ports
	 */
	public java.util.Iterator getPorts()
	{
		if(ports == null)
		{
			ports = new java.util.HashSet();
			ports.add(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/ee/indicePropietarios/v1",
			        "SBB_EE_IndicePropietariosPort"
			    )
			);
		}

		return ports.iterator();
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de SB B E E indice propietarios port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType getSBB_EE_IndicePropietariosPort()
	    throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;

		try
		{
			endpoint = new java.net.URL(SBB_EE_IndicePropietariosPort_address);
		}
		catch(java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}

		{
			SBB_EE_IndicePropietarios_PortType lse_portType;

			lse_portType = getSBB_EE_IndicePropietariosPort(endpoint);

			lse_portType.setPassword(getPassword());
			lse_portType.setUsername(getUsername());

			return lse_portType;
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param portAddress de port address
	 * @return el valor de SB B E E indice propietarios port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.v1.SBB_EE_IndicePropietarios_PortType getSBB_EE_IndicePropietariosPort(
	    java.net.URL portAddress
	)
	    throws javax.xml.rpc.ServiceException
	{
		try
		{
			SBB_EE_IndicePropietarios_BindingStub _stub = new SBB_EE_IndicePropietarios_BindingStub(portAddress, this);

			_stub.setPortName(getSBB_EE_IndicePropietariosPortWSDDServiceName());

			addWsSecurityHeader(_stub, getUsername(), getPassword());

			return _stub;
		}
		catch(SOAPException e)
		{
			e.printStackTrace();

			return null;
		}
		catch(org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de SB B E E indice propietarios port address
	 */
	public java.lang.String getSBB_EE_IndicePropietariosPortAddress()
	{
		return SBB_EE_IndicePropietariosPort_address;
	}

	/**
	 * Modifica el valor de SBB_EE_IndicePropietariosPortEndpointAddress.
	 *
	 * @param address correspondiente al valor de address
	 */
	public void setSBB_EE_IndicePropietariosPortEndpointAddress(java.lang.String address)
	{
		SBB_EE_IndicePropietariosPort_address = address;
	}

	/**
	 * Modifica el valor de SBB_EE_IndicePropietariosPortWSDDServiceName.
	 *
	 * @param name correspondiente al valor de name
	 */
	public void setSBB_EE_IndicePropietariosPortWSDDServiceName(java.lang.String name)
	{
		SBB_EE_IndicePropietariosPortWSDDServiceName = name;
	}

	/**
	 * Retorna Objeto o variable de valor SB B E E indice propietarios port WSDD service name.
	 *
	 * @return el valor de SB B E E indice propietarios port WSDD service name
	 */
	public java.lang.String getSBB_EE_IndicePropietariosPortWSDDServiceName()
	{
		return SBB_EE_IndicePropietariosPortWSDDServiceName;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de service name
	 */
	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName(
		    "https://www.supernotariado.gov.co/services/bachue/ee/indicePropietarios/v1", "SBB_EE_IndicePropietarios"
		);
	}
}
