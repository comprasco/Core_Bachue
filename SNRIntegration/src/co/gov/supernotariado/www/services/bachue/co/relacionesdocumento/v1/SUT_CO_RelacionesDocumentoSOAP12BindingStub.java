/**
 * SUT_CO_RelacionesDocumentoSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.co.relacionesdocumento.v1;

import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoEntradaRelacionarDocumento;
import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoParametroRD;
import co.gov.supernotariado.www.schemas.bachue.co.relacionesdocumento.relacionardocumento.v1.TipoSalidaRelacionarDocumento;

import org.apache.axis.AxisEngine;
import org.apache.axis.AxisFault;

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


public class SUT_CO_RelacionesDocumentoSOAP12BindingStub extends Stub implements SUT_CO_RelacionesDocumento_PortType
{
	static OperationDesc[] _operations;

	static
	{
		_operations = new OperationDesc[1];
		_initOperationDesc1();
	}

	private Vector cachedDeserFactories = new Vector();
	private Vector cachedSerClasses     = new Vector();
	private Vector cachedSerFactories   = new Vector();
	private Vector cachedSerQNames      = new Vector();

	public SUT_CO_RelacionesDocumentoSOAP12BindingStub()
	    throws AxisFault
	{
		this(null);
	}

	public SUT_CO_RelacionesDocumentoSOAP12BindingStub(URL au_endpointURL, Service as_service)
	    throws AxisFault
	{
		this(as_service);
		super.cachedEndpoint = au_endpointURL;
	}

	public SUT_CO_RelacionesDocumentoSOAP12BindingStub(Service as_service)
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
			    "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
			    ">tipoEntradaRelacionarDocumento>parametros"
			);
		cachedSerQNames.add(qName);
		cls = TipoParametroRD[].class;
		cachedSerClasses.add(cls);
		qName      = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
			    "tipoParametroRD"
			);
		qName2     = new QName("", "parametro");
		cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new ArrayDeserializerFactory());

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
			    "tipoEntradaRelacionarDocumento"
			);
		cachedSerQNames.add(qName);
		cls = TipoEntradaRelacionarDocumento.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
			    "tipoParametroRD"
			);
		cachedSerQNames.add(qName);
		cls = TipoParametroRD.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
			    "tipoSalidaRelacionarDocumento"
			);
		cachedSerQNames.add(qName);
		cls = TipoSalidaRelacionarDocumento.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	public TipoSalidaRelacionarDocumento relacionarDocumento(TipoEntradaRelacionarDocumento aterd_entrada)
	    throws RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/co/relacionesdocumento/v1/RelacionarDocumento"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new QName("", "RelacionarDocumento"));

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			Object _resp = _call.invoke(new Object[]{aterd_entrada});

			if(_resp instanceof RemoteException)
				throw (RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (TipoSalidaRelacionarDocumento)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (TipoSalidaRelacionarDocumento)JavaUtils.convert(_resp, TipoSalidaRelacionarDocumento.class);
				}
			}
		}
		catch(AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

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

	private static void _initOperationDesc1()
	{
		OperationDesc oper;
		ParameterDesc param;
		oper = new OperationDesc();
		oper.setName("RelacionarDocumento");
		param = new ParameterDesc(
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
			        "entradaRelacionarDocumento"
			    ), ParameterDesc.IN,
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
			        "tipoEntradaRelacionarDocumento"
			    ), TipoEntradaRelacionarDocumento.class, false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "tipoSalidaRelacionarDocumento"
		    )
		);
		oper.setReturnClass(TipoSalidaRelacionarDocumento.class);
		oper.setReturnQName(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/relacionesdocumento/relacionardocumento/v1",
		        "salidaRelacionarDocumento"
		    )
		);
		oper.setStyle(Style.DOCUMENT);
		oper.setUse(Use.LITERAL);
		_operations[0] = oper;
	}
}
