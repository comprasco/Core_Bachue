/**
 * SUT_CO_BusquedaDocumentos_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1;

import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;

import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

import java.net.MalformedURLException;
import java.net.URL;

import java.rmi.Remote;

import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;

import javax.xml.rpc.ServiceException;


/**
 * Clase que contiene todos las propiedades SUT_CO_BusquedaDocumentos_ServiceLocator.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SUT_CO_BusquedaDocumentos_ServiceLocator extends Service implements SUT_CO_BusquedaDocumentos_Service
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2757591942836649030L;

	/** Propiedad ports. */
	private HashSet ports = null;

	/** Propiedad SU T C O busqueda documentos port WSDD service name. */
	// The WSDD service name defaults to the port name.
	private String SUT_CO_BusquedaDocumentosPortWSDDServiceName = "SUT_CO_BusquedaDocumentosPort";

	/** Propiedad SU T C O busqueda documentos port address. */
	// Use to get a proxy class for SUT_CO_BusquedaDocumentosPort
	private String SUT_CO_BusquedaDocumentosPort_address = "http://www.example.com";

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos service locator.
	 */
	public SUT_CO_BusquedaDocumentos_ServiceLocator()
	{
	}

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos service locator.
	 *
	 * @param aec_config correspondiente al valor de aec config
	 */
	public SUT_CO_BusquedaDocumentos_ServiceLocator(EngineConfiguration aec_config)
	{
		super(aec_config);
	}

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos service locator.
	 *
	 * @param as_wsdlLoc correspondiente al valor de as wsdl loc
	 * @param sName correspondiente al valor de s name
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public SUT_CO_BusquedaDocumentos_ServiceLocator(String as_wsdlLoc, QName sName)
	    throws ServiceException
	{
		super(as_wsdlLoc, sName);
	}

	/**
	 * Set the endpoint address for the specified port name.
	 *
	 * @param as_portName correspondiente al valor de as port name
	 * @param as_address correspondiente al valor de as address
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public void setEndpointAddress(String as_portName, String as_address)
	    throws ServiceException
	{
		if("SUT_CO_BusquedaDocumentosPort".equals(as_portName))
			setSUT_CO_BusquedaDocumentosPortEndpointAddress(as_address);
		else
			throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + as_portName);
	}

	/**
	 * Set the endpoint address for the specified port name.
	 *
	 * @param aq_portName correspondiente al valor de aq port name
	 * @param as_address correspondiente al valor de as address
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public void setEndpointAddress(QName aq_portName, String as_address)
	    throws ServiceException
	{
		setEndpointAddress(aq_portName.getLocalPart(), as_address);
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 *
	 * @param ac_serviceEndpointInterface correspondiente al valor de ac service endpoint interface
	 * @return el valor de port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public Remote getPort(Class ac_serviceEndpointInterface)
	    throws ServiceException
	{
		try
		{
			if(SUT_CO_BusquedaDocumentos_PortType.class.isAssignableFrom(ac_serviceEndpointInterface))
			{
				SUT_CO_BusquedaDocumentosSOAP12BindingStub _stub = new SUT_CO_BusquedaDocumentosSOAP12BindingStub(
					    new URL(SUT_CO_BusquedaDocumentosPort_address), this
					);
				_stub.setPortName(getSUT_CO_BusquedaDocumentosPortWSDDServiceName());

				return _stub;
			}
		}
		catch(Throwable t)
		{
			throw new ServiceException(t);
		}

		throw new ServiceException(
		    "There is no stub implementation for the interface:  "
		    + ((ac_serviceEndpointInterface == null) ? "null" : ac_serviceEndpointInterface.getName())
		);
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 *
	 * @param aq_portName correspondiente al valor de aq port name
	 * @param ac_serviceEndpointInterface correspondiente al valor de ac service endpoint interface
	 * @return el valor de port
	 * @throws ServiceException cuando se produce algun error en el proceso
	 */
	public Remote getPort(QName aq_portName, Class ac_serviceEndpointInterface)
	    throws ServiceException
	{
		if(aq_portName == null)
			return getPort(ac_serviceEndpointInterface);

		String ls_inputPortName = aq_portName.getLocalPart();

		if("SUT_CO_BusquedaDocumentosPort".equals(ls_inputPortName))
			return getSUT_CO_BusquedaDocumentosPort();
		else
		{
			Remote _stub = getPort(ac_serviceEndpointInterface);
			((Stub)_stub).setPortName(aq_portName);

			return _stub;
		}
	}

	/** {@inheritDoc} */
	public Iterator getPorts()
	{
		if(ports == null)
		{
			ports = new HashSet();
			ports.add(
			    new QName(
			        "https://www.supernotariado.gov.co/services/bachue/co/busquedadocumentos/v1",
			        "SUT_CO_BusquedaDocumentosPort"
			    )
			);
		}

		return ports.iterator();
	}

	/** {@inheritDoc} */
	public SUT_CO_BusquedaDocumentos_PortType getSUT_CO_BusquedaDocumentosPort()
	    throws ServiceException
	{
		URL endpoint;

		try
		{
			endpoint = new URL(SUT_CO_BusquedaDocumentosPort_address);
		}
		catch(MalformedURLException e)
		{
			throw new ServiceException(e);
		}

		return getSUT_CO_BusquedaDocumentosPort(endpoint);
	}

	/** {@inheritDoc} */
	public SUT_CO_BusquedaDocumentos_PortType getSUT_CO_BusquedaDocumentosPort(URL au_portAddress)
	    throws ServiceException
	{
		try
		{
			SUT_CO_BusquedaDocumentosSOAP12BindingStub _stub = new SUT_CO_BusquedaDocumentosSOAP12BindingStub(
				    au_portAddress, this
				);
			_stub.setPortName(getSUT_CO_BusquedaDocumentosPortWSDDServiceName());

			return _stub;
		}
		catch(AxisFault e)
		{
			return null;
		}
	}

	/** {@inheritDoc} */
	public String getSUT_CO_BusquedaDocumentosPortAddress()
	{
		return SUT_CO_BusquedaDocumentosPort_address;
	}

	/**
	 * Modifica el valor de SUT_CO_BusquedaDocumentosPortEndpointAddress.
	 *
	 * @param as_address correspondiente al valor de as address
	 */
	public void setSUT_CO_BusquedaDocumentosPortEndpointAddress(String as_address)
	{
		SUT_CO_BusquedaDocumentosPort_address = as_address;
	}

	/**
	 * Modifica el valor de SUT_CO_BusquedaDocumentosPortWSDDServiceName.
	 *
	 * @param as_name correspondiente al valor de as name
	 */
	public void setSUT_CO_BusquedaDocumentosPortWSDDServiceName(String as_name)
	{
		SUT_CO_BusquedaDocumentosPortWSDDServiceName = as_name;
	}

	/**
	 * Retorna Objeto o variable de valor SU T C O busqueda documentos port WSDD service name.
	 *
	 * @return el valor de SU T C O busqueda documentos port WSDD service name
	 */
	public String getSUT_CO_BusquedaDocumentosPortWSDDServiceName()
	{
		return SUT_CO_BusquedaDocumentosPortWSDDServiceName;
	}

	/** {@inheritDoc} */
	public QName getServiceName()
	{
		return new QName(
		    "https://www.supernotariado.gov.co/services/bachue/co/busquedadocumentos/v1", "SUT_CO_BusquedaDocumentos"
		);
	}
}
