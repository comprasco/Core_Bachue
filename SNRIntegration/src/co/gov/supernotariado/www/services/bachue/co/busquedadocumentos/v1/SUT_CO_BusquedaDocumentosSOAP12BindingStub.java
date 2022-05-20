/**
 * SUT_CO_BusquedaDocumentosSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.busquedadocumentos.v1;

import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoEntradaConsultar;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoEntradaConsultarRepositorio;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoParametro;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.consultar.v1.TipoSalidaConsultar;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoEntradaObtenerArchivo;
import co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1.TipoSalidaObtenerArchivo;

import org.apache.axis.AxisEngine;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;

import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;

import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.SerializerFactory;

import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.EnumDeserializerFactory;
import org.apache.axis.encoding.ser.EnumSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleListSerializerFactory;
import org.apache.axis.encoding.ser.SimpleSerializerFactory;

import org.apache.axis.soap.SOAPConstants;

import org.apache.axis.utils.JavaUtils;

import java.net.URL;

import java.rmi.RemoteException;

import java.util.Vector;

import javax.xml.namespace.QName;

import javax.xml.rpc.Service;


/**
 * Clase que contiene todos las propiedades SUT_CO_BusquedaDocumentosSOAP12BindingStub.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/08/2020
 */
public class SUT_CO_BusquedaDocumentosSOAP12BindingStub extends Stub implements SUT_CO_BusquedaDocumentos_PortType
{
	/** Propiedad operations. */
	static OperationDesc[] _operations;

	static
	{
		_operations = new OperationDesc[2];
		_initOperationDesc1();
	}

	/** Propiedad cached deser factories. */
	private Vector cachedDeserFactories = new Vector();

	/** Propiedad cached ser classes. */
	private Vector cachedSerClasses = new Vector();

	/** Propiedad cached ser factories. */
	private Vector cachedSerFactories = new Vector();

	/** Propiedad cached ser Q names. */
	private Vector cachedSerQNames = new Vector();

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos SOAP 12 binding stub.
	 *
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CO_BusquedaDocumentosSOAP12BindingStub()
	    throws AxisFault
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos SOAP 12 binding stub.
	 *
	 * @param au_endpointURL correspondiente al valor de au endpoint URL
	 * @param as_service correspondiente al valor de as service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CO_BusquedaDocumentosSOAP12BindingStub(URL au_endpointURL, Service as_service)
	    throws AxisFault
	{
		this(as_service);
		super.cachedEndpoint = au_endpointURL;
	}

	/**
	 * Instancia un nuevo objeto SU T C O busqueda documentos SOAP 12 binding stub.
	 *
	 * @param as_service correspondiente al valor de as service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CO_BusquedaDocumentosSOAP12BindingStub(Service as_service)
	    throws AxisFault
	{
		if(as_service == null)
			super.service = new org.apache.axis.client.Service();
		else
			super.service = as_service;

		((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");

		Class cls;
		QName qName;
		QName qName2;
		Class beansf       = BeanSerializerFactory.class;
		Class beandf       = BeanDeserializerFactory.class;
		Class enumsf       = EnumSerializerFactory.class;
		Class enumdf       = EnumDeserializerFactory.class;
		Class arraysf      = ArraySerializerFactory.class;
		Class arraydf      = ArrayDeserializerFactory.class;
		Class simplesf     = SimpleSerializerFactory.class;
		Class simpledf     = SimpleDeserializerFactory.class;
		Class simplelistsf = SimpleListSerializerFactory.class;
		Class simplelistdf = SimpleListDeserializerFactory.class;
		qName              = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
			    ">tipoEntradaConsultar>parametros"
			);
		cachedSerQNames.add(qName);
		cls = TipoParametro[].class;
		cachedSerClasses.add(cls);
		qName      = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "tipoParametro"
			);
		qName2     = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "parametro"
			);
		cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new ArrayDeserializerFactory());

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
			    ">tipoEntradaConsultar>repositorio"
			);
		cachedSerQNames.add(qName);
		cls = TipoEntradaConsultarRepositorio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
			    ">tipoSalidaConsultar>documentos"
			);
		cachedSerQNames.add(qName);
		cls = TipoDocumento[].class;
		cachedSerClasses.add(cls);
		qName      = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "tipoDocumento"
			);
		qName2     = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "documento"
			);
		cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new ArrayDeserializerFactory());

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "tipoDocumento"
			);
		cachedSerQNames.add(qName);
		cls = TipoDocumento.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
			    "tipoEntradaConsultar"
			);
		cachedSerQNames.add(qName);
		cls = TipoEntradaConsultar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "tipoParametro"
			);
		cachedSerQNames.add(qName);
		cls = TipoParametro.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
			    "tipoSalidaConsultar"
			);
		cachedSerQNames.add(qName);
		cls = TipoSalidaConsultar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1",
			    "tipoEntradaObtenerArchivo"
			);
		cachedSerQNames.add(qName);
		cls = TipoEntradaObtenerArchivo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1",
			    "tipoSalidaObtenerArchivo"
			);
		cachedSerQNames.add(qName);
		cls = TipoSalidaObtenerArchivo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	/** {@inheritDoc} */
	public TipoSalidaConsultar consultar(TipoEntradaConsultar atec_entrada)
	    throws RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new NoEndPointException();

		Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/co/busquedadocumentos/v1/Consultar");
		_call.setEncodingStyle(null);
		_call.setProperty(Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new QName("", "Consultar"));

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			Object _resp = _call.invoke(new Object[]{atec_entrada});

			if(_resp instanceof RemoteException)
				throw (RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (TipoSalidaConsultar)_resp;
				}
				catch(Exception _exception)
				{
					return (TipoSalidaConsultar)JavaUtils.convert(_resp, TipoSalidaConsultar.class);
				}
			}
		}
		catch(AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	/** {@inheritDoc} */
	public TipoSalidaObtenerArchivo obtenerArchivo(TipoEntradaObtenerArchivo entrada)
	    throws RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/co/busquedadocumentos/v1/ObtenerArchivo"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new QName("", "ObtenerArchivo"));

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			Object _resp = _call.invoke(new Object[]{entrada});

			if(_resp instanceof RemoteException)
				throw (RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (TipoSalidaObtenerArchivo)_resp;
				}
				catch(Exception _exception)
				{
					return (TipoSalidaObtenerArchivo)org.apache.axis.utils.JavaUtils.convert(
					    _resp, TipoSalidaObtenerArchivo.class
					);
				}
			}
		}
		catch(AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	/**
	 * Creates the call.
	 *
	 * @return el valor de call
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	protected Call createCall()
	    throws RemoteException
	{
		try
		{
			Call _call = super._createCall();

			if(super.maintainSessionSet)
				_call.setMaintainSession(super.maintainSession);

			if(super.cachedUsername != null)
				_call.setUsername(super.cachedUsername);

			if(super.cachedPassword != null)
				_call.setPassword(super.cachedPassword);

			if(super.cachedEndpoint != null)
				_call.setTargetEndpointAddress(super.cachedEndpoint);

			if(super.cachedTimeout != null)
				_call.setTimeout(super.cachedTimeout);

			if(super.cachedPortName != null)
				_call.setPortName(super.cachedPortName);

			java.util.Enumeration keys = super.cachedProperties.keys();

			while(keys.hasMoreElements())
			{
				String key = (String)keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}

			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized(this)
			{
				if(firstCall())
				{
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);

					for(int i = 0; i < cachedSerFactories.size(); ++i)
					{
						Class  cls   = (Class)cachedSerClasses.get(i);
						QName  qName = (QName)cachedSerQNames.get(i);
						Object x     = cachedSerFactories.get(i);

						if(x instanceof Class)
						{
							Class sf = (Class)cachedSerFactories.get(i);
							Class df = (Class)cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
						else if(x instanceof javax.xml.rpc.encoding.SerializerFactory)
						{
							SerializerFactory   sf = (SerializerFactory)cachedSerFactories.get(i);
							DeserializerFactory df = (DeserializerFactory)cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}

			return _call;
		}
		catch(Throwable _t)
		{
			throw new AxisFault("Failure trying to get the Call object", _t);
		}
	}

	/**
	 * Inits the operation desc 1.
	 */
	private static void _initOperationDesc1()
	{
		OperationDesc oper;
		ParameterDesc param;
		oper = new OperationDesc();
		oper.setName("Consultar");
		param = new ParameterDesc(
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
			        "entradaConsultar"
			    ), ParameterDesc.IN,
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
			        "tipoEntradaConsultar"
			    ), TipoEntradaConsultar.class, false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1",
		        "tipoSalidaConsultar"
		    )
		);
		oper.setReturnClass(TipoSalidaConsultar.class);
		oper.setReturnQName(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/consultar/v1", "salidaConsultar"
		    )
		);
		oper.setStyle(Style.DOCUMENT);
		oper.setUse(Use.LITERAL);
		_operations[0]     = oper;

		oper = new OperationDesc();
		oper.setName("ObtenerArchivo");
		param = new ParameterDesc(
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1",
			        "entradaObtenerArchivo"
			    ), ParameterDesc.IN,
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1",
			        "tipoEntradaObtenerArchivo"
			    ), TipoEntradaObtenerArchivo.class, false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1",
		        "tipoSalidaObtenerArchivo"
		    )
		);
		oper.setReturnClass(TipoSalidaObtenerArchivo.class);
		oper.setReturnQName(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1",
		        "salidaObtenerArchivo"
		    )
		);
		oper.setStyle(Style.DOCUMENT);
		oper.setUse(Use.LITERAL);
		_operations[1] = oper;
	}
}
