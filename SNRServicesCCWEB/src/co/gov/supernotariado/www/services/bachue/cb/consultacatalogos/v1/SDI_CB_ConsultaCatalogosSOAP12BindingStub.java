/**
 * SDI_CB_ConsultaCatalogosSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultacatalogos.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogosCodigoMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos;

import org.apache.axis.AxisEngine;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;

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

import java.util.Enumeration;
import java.util.Vector;

import javax.xml.namespace.QName;

import javax.xml.rpc.Service;

import javax.xml.rpc.encoding.SerializerFactory;


public class SDI_CB_ConsultaCatalogosSOAP12BindingStub extends Stub implements SDI_CB_ConsultaCatalogos
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

	public SDI_CB_ConsultaCatalogosSOAP12BindingStub()
	    throws AxisFault
	{
		this(null);
	}

	public SDI_CB_ConsultaCatalogosSOAP12BindingStub(URL au_endpointURL, Service as_service)
	    throws org.apache.axis.AxisFault
	{
		this(as_service);
		super.cachedEndpoint = au_endpointURL;
	}

	public SDI_CB_ConsultaCatalogosSOAP12BindingStub(Service as_service)
	    throws org.apache.axis.AxisFault
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
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			    ">tipoSalidaConsultarCatalogos>catalogos"
			);
		cachedSerQNames.add(qName);
		cls = TiposCatalogos[].class;
		cachedSerClasses.add(cls);
		qName      = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			    "tiposCatalogos"
			);
		qName2     = new QName("", "catalogo");
		cachedSerFactories.add(new ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new ArrayDeserializerFactory());

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			    ">tipoSalidaConsultarCatalogos>codigoMensaje"
			);
		cachedSerQNames.add(qName);
		cls = TipoSalidaConsultarCatalogosCodigoMensaje.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			    "tipoEntradaConsultarCatalogos"
			);
		cachedSerQNames.add(qName);
		cls = TipoEntradaConsultarCatalogos.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			    "tipoSalidaConsultarCatalogos"
			);
		cachedSerQNames.add(qName);
		cls = TipoSalidaConsultarCatalogos.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			    "tiposCatalogos"
			);
		cachedSerQNames.add(qName);
		cls = TiposCatalogos.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	public TipoSalidaConsultarCatalogos consultarCatalogos(TipoEntradaConsultarCatalogos atecc_entrada)
	    throws RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new NoEndPointException();

		Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultacatalogos/v1/ConsultarCatalogos"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new QName("", "ConsultarCatalogos"));

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			Object _resp = _call.invoke(new Object[]{atecc_entrada});

			if(_resp instanceof RemoteException)
				throw (RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (TipoSalidaConsultarCatalogos)_resp;
				}
				catch(Exception _exception)
				{
					return (TipoSalidaConsultarCatalogos)JavaUtils.convert(_resp, TipoSalidaConsultarCatalogos.class);
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

			Enumeration keys = super.cachedProperties.keys();

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
						Class            cls   = (Class)cachedSerClasses.get(i);
						QName            qName = (QName)cachedSerQNames.get(i);
						java.lang.Object x     = cachedSerFactories.get(i);

						if(x instanceof Class)
						{
							Class sf = (Class)cachedSerFactories.get(i);
							Class df = (Class)cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
						else if(x instanceof SerializerFactory)
						{
							org.apache.axis.encoding.SerializerFactory   sf = (org.apache.axis.encoding.SerializerFactory)cachedSerFactories
									.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)cachedDeserFactories
									.get(i);
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
		oper.setName("ConsultarCatalogos");
		param = new ParameterDesc(
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			        "entradaConsultarCatalogos"
			    ), ParameterDesc.IN,
			    new QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
			        "tipoEntradaConsultarCatalogos"
			    ), TipoEntradaConsultarCatalogos.class, false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "tipoSalidaConsultarCatalogos"
		    )
		);
		oper.setReturnClass(TipoSalidaConsultarCatalogos.class);
		oper.setReturnQName(
		    new QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "salidaConsultarCatalogos"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;
	}
}
