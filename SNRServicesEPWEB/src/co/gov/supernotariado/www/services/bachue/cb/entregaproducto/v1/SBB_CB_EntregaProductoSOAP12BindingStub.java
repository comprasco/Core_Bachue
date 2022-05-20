/**
 * SBB_CB_EntregaProductoSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1;

public class SBB_CB_EntregaProductoSOAP12BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.cb.entregaproducto.v1.SBB_CB_EntregaProducto_PortType
{
	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[5];
		_initOperationDesc1();
	}

	private java.util.Vector cachedDeserFactories = new java.util.Vector();
	private java.util.Vector cachedSerClasses     = new java.util.Vector();
	private java.util.Vector cachedSerFactories   = new java.util.Vector();
	private java.util.Vector cachedSerQNames      = new java.util.Vector();

	public SBB_CB_EntregaProductoSOAP12BindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	public SBB_CB_EntregaProductoSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public SBB_CB_EntregaProductoSOAP12BindingStub(javax.xml.rpc.Service service)
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
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
			    "tipoEntradaObtenerProducto"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
			    "tipoSalidaObtenerProducto"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
			    "tipoEntradaObtenerReciboCaja"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
			    "tipoSalidaObtenerReciboCaja"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			    ">tipoSalidaObtenerTurnosRefPago>turnos"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoTurno[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			    "tipoTurno"
			);
		qName2     = new javax.xml.namespace.QName("", "turno");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			    "tipoEntradaObtenerTurnosRefPago"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			    "tipoSalidaObtenerTurnosRefPago"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			    "tipoTurno"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoTurno.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
			    "tipoEntradaRegistrarEntregaProducto"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
			    "tipoSalidaRegistrarEntregaProducto"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
			    "tipoEntradaVerificarProducto"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
			    "tipoSalidaVerificarProducto"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto obtenerProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/ObtenerProducto"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ObtenerProducto"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto.class
						);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja obtenerReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/ObtenerReciboCaja"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ObtenerReciboCaja"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja.class
						);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago obtenerTurnosRefPago(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/ObtenerTurnosRefPago"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ObtenerTurnosRefPago"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago.class
						);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto registrarEntregaProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/RegistrarEntregaProducto"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "RegistrarEntregaProducto"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto.class
						);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto verificarProducto(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/entregaproducto/v1/VerificarProducto"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "VerificarProducto"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto.class
						);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

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

	private static void _initOperationDesc1()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("VerificarProducto");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
			        "entradaVerificarProducto"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
			        "tipoEntradaVerificarProducto"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoEntradaVerificarProducto.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
		        "tipoSalidaVerificarProducto"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1.TipoSalidaVerificarProducto.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
		        "salidaVerificarProducto"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ObtenerProducto");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
			        "entradaObtenerProducto"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
			        "tipoEntradaObtenerProducto"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoEntradaObtenerProducto.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
		        "tipoSalidaObtenerProducto"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerproducto.v1.TipoSalidaObtenerProducto.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerproducto/v1",
		        "salidaObtenerProducto"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ObtenerTurnosRefPago");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			        "entradaObtenerTurnosRefPago"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
			        "tipoEntradaObtenerTurnosRefPago"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoEntradaObtenerTurnosRefPago.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "tipoSalidaObtenerTurnosRefPago"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoSalidaObtenerTurnosRefPago.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "salidaObtenerTurnosRefPago"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("RegistrarEntregaProducto");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
			        "entradaRegistrarEntregaProducto"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
			        "tipoEntradaRegistrarEntregaProducto"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoEntradaRegistrarEntregaProducto.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
		        "tipoSalidaRegistrarEntregaProducto"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1.TipoSalidaRegistrarEntregaProducto.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
		        "salidaRegistrarEntregaProducto"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ObtenerReciboCaja");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
			        "entradaObtenerReciboCaja"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
			        "tipoEntradaObtenerReciboCaja"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoEntradaObtenerReciboCaja.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
		        "tipoSalidaObtenerReciboCaja"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1.TipoSalidaObtenerReciboCaja.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
		        "salidaObtenerReciboCaja"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;
	}
}
