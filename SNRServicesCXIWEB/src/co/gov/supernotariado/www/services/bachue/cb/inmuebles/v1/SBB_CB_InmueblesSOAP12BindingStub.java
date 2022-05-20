/**
 * SBB_CB_InmueblesSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_InmueblesSOAP12BindingStub.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_InmueblesSOAP12BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.cb.inmuebles.v1.SBB_CB_Inmuebles_PortType
{
	/** Propiedad operations. */
	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[5];
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
	 * Instancia un nuevo objeto SB B C B inmuebles SOAP 12 binding stub.
	 *
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SBB_CB_InmueblesSOAP12BindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto SB B C B inmuebles SOAP 12 binding stub.
	 *
	 * @param endpointURL de endpoint URL
	 * @param service de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SBB_CB_InmueblesSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	/**
	 * Instancia un nuevo objeto SB B C B inmuebles SOAP 12 binding stub.
	 *
	 * @param service de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SBB_CB_InmueblesSOAP12BindingStub(javax.xml.rpc.Service service)
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
		addBindings0();
		addBindings1();
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas consultarDatosBasicos(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarDatosBasicos"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarDatosBasicos"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas.class
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
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble consultarDatosInmueble(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarDatosInmueble"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarDatosInmueble"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble.class
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
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores consultarDireccionesAnteriores(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarDireccionesAnteriores"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarDireccionesAnteriores"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores.class
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
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas consultarMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarMatriculas");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarMatriculas"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas.class
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
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario consultarPropietarios(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/v1/consultarPropietarios"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarPropietarios"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario.class
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
		oper.setName("consultarDatosInmueble");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			        "entradaDatosInmueble"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			        "tipoEntradaDatosInmueble"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "tipoSalidaDatosInmueble"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
		        "salidaDatosInmueble"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarPropietarios");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			        "entradaDatosPropietario"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			        "tipoEntradaDatosPropietario"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
		        "tipoSalidaDatosPropietario"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
		        "salidaDatosPropietario"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarDireccionesAnteriores");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			        "entradaDireccionesAnteriores"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			        "tipoEntradaDireccionesAnteriores"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "tipoSalidaDireccionesAnteriores"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "salidaDireccionesAnteriores"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarMatriculas");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			        "entradaConsultaMatriculas"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			        "tipoEntradaConsultaMatriculas"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "tipoSalidaConsultaMatriculas"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "salidaConsultaMatriculas"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarDatosBasicos");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			        "entradaDatosBasicos"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			        "tipoEntradaDatosBasicosMatriculas"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "tipoSalidaDatosBasicosMatriculas"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "salidaDatosBasicos"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;
	}

	/**
	 * Adds the bindings 0.
	 */
	private void addBindings0()
	{
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoEntradaDatosBasicosMatriculas>codDepartamento"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoEntradaDatosBasicosMatriculas>codMunicipio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoEntradaDatosBasicosMatriculas>convenio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoEntradaDatosBasicosMatriculas>criterioBusqueda"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculasCriterioBusqueda.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>codCirculoRegistral"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>codDepartamento"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>codMunicipio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>direccionPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>estadoMatricula"
			);
		cachedSerQNames.add(qName);
		cls = int.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>estadoNUPRE"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>nomCirculoRegistral"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>nomDepartamento"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>nomMunicipio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>numMatriculaInmobiliaria"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>numPredial"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>NUPRE"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoMatricula>tipoPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>numDocumentoPersona"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>numNIT"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>porcentajeParticipacion"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>primerApellido"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>primerNombre"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>razonSocial"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>segundoApellido"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>segundoNombre"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoPropietario>tipoDocumentoPersona"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoSalidaDatosBasicosMatriculas>cantidadMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoSalidaDatosBasicosMatriculas>codMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    ">tipoSalidaDatosBasicosMatriculas>descripcionMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    "tipoEntradaDatosBasicosMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoEntradaDatosBasicosMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    "tipoMatricula"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoMatricula.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    "tipoPropietario"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoPropietario.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
			    "tipoSalidaDatosBasicosMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1.TipoSalidaDatosBasicosMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoEntradaDatosInmueble>convenio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoEntradaDatosInmueble>numIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoEntradaDatosInmueble>tipoIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmuebleTipoIdentificacionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>areaConstruida"
			);
		cachedSerQNames.add(qName);
		cls = long.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>areaInmueble"
			);
		cachedSerQNames.add(qName);
		cls = long.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>areaPrivada"
			);
		cachedSerQNames.add(qName);
		cls = long.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>codCirculoRegistral"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>codDepartamento"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>codMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>codMunicipio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>complementaciones"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>descripcionMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>direccionPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>estadoFolio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>estadoNUPRE"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>fechaAperturaFolio"
			);
		cachedSerQNames.add(qName);
		cls = java.util.Calendar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>fechaInstrumento"
			);
		cachedSerQNames.add(qName);
		cls = java.util.Calendar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>linderos"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>NIR"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>nomBarrio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>nomCirculoRegistral"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>nomDepartamento"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>nomMunicipio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>nomTipoDocumentoPublico"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>nomVereda"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>numMatriculaInmobiliaria"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>numPredial"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>numPredialAnterior"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>NUPRE"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>radicacion"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    ">tipoSalidaDatosInmueble>tipoPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    "tipoEntradaDatosInmueble"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoEntradaDatosInmueble.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosImueble/v1",
			    "tipoSalidaDatosInmueble"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosImueble.v1.TipoSalidaDatosInmueble.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    ">tipoDireccionAnterior>direccionPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    ">tipoEntradaDireccionesAnteriores>convenio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    ">tipoEntradaDireccionesAnteriores>numIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    ">tipoEntradaDireccionesAnteriores>tipoIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnterioresTipoIdentificacionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    ">tipoSalidaDireccionesAnteriores>codMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    ">tipoSalidaDireccionesAnteriores>descripcionMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    "tipoDireccionAnterior"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    "tipoEntradaDireccionesAnteriores"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoEntradaDireccionesAnteriores.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
			    "tipoSalidaDireccionesAnteriores"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoSalidaDireccionesAnteriores.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    ">tipoEntradaConsultaMatriculas>codDepartamento"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    ">tipoEntradaConsultaMatriculas>codMunicipio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    ">tipoEntradaConsultaMatriculas>convenio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    ">tipoEntradaConsultaMatriculas>criterioBusqueda"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculasCriterioBusqueda.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    ">tipoMatricula>matricula"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    ">tipoSalidaConsultaMatriculas>codMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    ">tipoSalidaConsultaMatriculas>descripcionMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    "tipoEntradaConsultaMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "tipoMatricula"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
			    "tipoSalidaConsultaMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoSalidaConsultaMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoEntradaDatosPropietario>convenio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoEntradaDatosPropietario>numIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoEntradaDatosPropietario>tipoIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietarioTipoIdentificacionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>codGenero"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>nomPropietario"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>nomTipoDocumento"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>numDocumentoPersona"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>numNIT"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>porcentajeParticipacion"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>primerApellido"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>primerNombre"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>razonSocial"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>segundoApellido"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>segundoNombre"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>tipoDocumentoPersona"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);
	}

	/**
	 * Adds the bindings 1.
	 */
	private void addBindings1()
	{
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoPropietario>tipoPersona"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoSalidaDatosPropietario>codMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    ">tipoSalidaDatosPropietario>descripcionMensaje"
			);
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(
		    org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName
		    )
		);
		cachedDeserFactories.add(
		    org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(
		        org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName
		    )
		);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    "tipoEntradaDatosPropietario"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoEntradaDatosPropietario.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    "tipoPropietario"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
			    "tipoSalidaDatosPropietario"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoSalidaDatosPropietario.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}
}
