/**
 * SUT_CN_MensajeroSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cn.mensajero.v1;

import org.apache.axis.Message;

import com.b2bsg.common.logging.LoggerHandler;
import com.b2bsg.common.util.StringUtils;

/**
 * Clase que contiene todos las propiedades SUT_CN_MensajeroSOAP12BindingStub.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 31/03/2020
 */
public class SUT_CN_MensajeroSOAP12BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.cn.mensajero.v1.SUT_CN_Mensajero_PortType
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SUT_CN_MensajeroSOAP12BindingStub.class);

	/** Propiedad operations. */
	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[2];
		_initOperationDesc1();
	}

	/** Propiedad cached deser factories. */
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	/** Propiedad cached ser classes. */
	private java.util.Vector cachedSerClasses = new java.util.Vector();

	/** Propiedad cached ser factories. */
	private java.util.Vector cachedSerFactories = new java.util.Vector();

	/** Propiedad cached ser Q names. */
	private java.util.Vector cachedSerQNames = new java.util.Vector();

	/**
	 * Instancia un nuevo objeto SU T C N mensajero SOAP 12 binding stub.
	 *
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CN_MensajeroSOAP12BindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto SU T C N mensajero SOAP 12 binding stub.
	 *
	 * @param endpointURL correspondiente al valor de endpoint URL
	 * @param service correspondiente al valor de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CN_MensajeroSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	/**
	 * Instancia un nuevo objeto SU T C N mensajero SOAP 12 binding stub.
	 *
	 * @param service correspondiente al valor de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CN_MensajeroSOAP12BindingStub(javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		if(service == null)
			super.service = new org.apache.axis.client.Service();
		else
			super.service = service;

		((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");

		java.lang.Class           cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		java.lang.Class           beansf       = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		java.lang.Class           beandf       = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		java.lang.Class           enumsf       = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		java.lang.Class           enumdf       = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		java.lang.Class           arraysf      = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		java.lang.Class           arraydf      = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		java.lang.Class           simplesf     = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		java.lang.Class           simpledf     = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		java.lang.Class           simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		java.lang.Class           simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName                                  = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    ">>>>tipoSalidaConsultarEnvio>listaMensajes>mensaje>documentos>documento"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoDocumentos.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    ">>>tipoSalidaConsultarEnvio>listaMensajes>mensaje>documentos"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoDocumentos[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    ">>>>tipoSalidaConsultarEnvio>listaMensajes>mensaje>documentos>documento"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "documento"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    ">>tipoSalidaConsultarEnvio>listaMensajes>mensaje"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoMensaje.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    ">tipoSalidaConsultarEnvio>listaMensajes"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoMensaje[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    ">>tipoSalidaConsultarEnvio>listaMensajes>mensaje"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "mensaje"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    "tipoEntradaConsultarEnvio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			    "tipoSalidaConsultarEnvio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
			    ">tipoEntradaEnviarMensaje>documentos"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoDocumento"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "documento"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
			    ">tipoEntradaEnviarMensaje>variables"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoVariableEMI"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "variable"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoDestinatarioEMI"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDestinatarioEMI.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoDocumento"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoDocumento.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
			    "tipoEntradaEnviarMensaje"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
			    "tipoSalidaEnviarMensaje"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoVariableEMI"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoVariableEMI.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio consultarEnvio(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/cn/mensajero/v1/ConsultarEnvio");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ConsultarEnvio"));

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entrada});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio.class
						);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje enviarMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/cn/mensajero/v1/EnviarMensaje");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "EnviarMensaje"));

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entrada});
			
			{
				Message lm_mRequest;
				String ls_request;

				lm_mRequest = _call.getMessageContext().getRequestMessage();
				ls_request = lm_mRequest != null ? lm_mRequest.getSOAPPartAsString() : null;

				if(StringUtils.isValidString(ls_request))
					clh_LOGGER.info("enviarMensaje-cliente-request", ls_request);
			}
			
			
			{
				Message lm_mResponse;
 				String ls_response;

 				lm_mResponse = _call.getMessageContext().getResponseMessage();
 				ls_response = lm_mResponse != null ? lm_mResponse.getSOAPPartAsString() : null;

 				if(StringUtils.isValidString(ls_response))
 					clh_LOGGER.info("enviarMensaje-cliente-response", ls_response);
			}

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje.class
						);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	/**
	 * Creates the call.
	 *
	 * @return el valor de org.apache.axis.client. call
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	protected org.apache.axis.client.Call createCall()
	    throws java.rmi.RemoteException
	{
		try
		{
			org.apache.axis.client.Call _call = super._createCall();

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
				java.lang.String key = (java.lang.String)keys.nextElement();
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
						java.lang.Class           cls   = (java.lang.Class)cachedSerClasses.get(i);
						javax.xml.namespace.QName qName = (javax.xml.namespace.QName)cachedSerQNames.get(i);
						java.lang.Object          x     = cachedSerFactories.get(i);

						if(x instanceof Class)
						{
							java.lang.Class sf = (java.lang.Class)cachedSerFactories.get(i);
							java.lang.Class df = (java.lang.Class)cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
						else if(x instanceof javax.xml.rpc.encoding.SerializerFactory)
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
		catch(java.lang.Throwable _t)
		{
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	/**
	 * Inits the operation desc 1.
	 */
	private static void _initOperationDesc1()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("EnviarMensaje");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
			        "entradaEnviarMensaje"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
			        "tipoEntradaEnviarMensaje"
			    ), co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "tipoSalidaEnviarMensaje"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "salidaEnviarMensaje"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ConsultarEnvio");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			        "entradaConsultarEnvio"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
			        "tipoEntradaConsultarEnvio"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        "tipoSalidaConsultarEnvio"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        "salidaConsultarEnvio"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;
	}
}
