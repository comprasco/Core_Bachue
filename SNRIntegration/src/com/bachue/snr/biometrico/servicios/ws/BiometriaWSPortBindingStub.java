/**
 * BiometriaWSPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;

public class BiometriaWSPortBindingStub extends org.apache.axis.client.Stub implements com.bachue.snr.biometrico.servicios.ws.BiometriaWS
{
	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[15];
		_initOperationDesc1();
		_initOperationDesc2();
	}

	private java.util.Vector cachedDeserFactories = new java.util.Vector();
	private java.util.Vector cachedSerClasses     = new java.util.Vector();
	private java.util.Vector cachedSerFactories   = new java.util.Vector();
	private java.util.Vector cachedSerQNames      = new java.util.Vector();

	public BiometriaWSPortBindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	public BiometriaWSPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public BiometriaWSPortBindingStub(javax.xml.rpc.Service service)
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
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "agregarFirmaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.AgregarFirmaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "agregarFirmaSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "baseDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.BaseDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "baseSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.BaseSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "booleanSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "borrarHuellasDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.BorrarHuellasDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "claveDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.ClaveDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "constante"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.Constante.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "constantesSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "dedosEnum"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.DedosEnum.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "estadisticasEntradaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.EstadisticasEntradaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "estadisticasSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "firmaSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "huellaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.HuellaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "logDTO");
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.LogDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirmaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirmaSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "sesionDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.SesionDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "sesionEntradaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.SesionEntradaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "stringSalidaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "usuarioDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.UsuarioDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "usuarioEntradaDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "verificacionDTO"
			);
		cachedSerQNames.add(qName);
		cls = com.bachue.snr.biometrico.servicios.ws.VerificacionDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO actualizarClave(
	    com.bachue.snr.biometrico.servicios.ws.UsuarioDTO entradaClave
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("actualizarClave");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "actualizarClave"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaClave});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO agregarFirma(
	    com.bachue.snr.biometrico.servicios.ws.AgregarFirmaDTO entradaAgregarFirma
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[12]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("agregarFirma");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "agregarFirma"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaAgregarFirma});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO borrarHuellas(
	    com.bachue.snr.biometrico.servicios.ws.BorrarHuellasDTO entradaUsuario
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("borrarHuellas");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "borrarHuellas"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaUsuario});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO consultarEstadisticas(
	    com.bachue.snr.biometrico.servicios.ws.EstadisticasEntradaDTO entradaEstadisticas
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("consultarEstadisticas");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "consultarEstadisticas"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaEstadisticas});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.SesionDTO consultarSesion(
	    com.bachue.snr.biometrico.servicios.ws.SesionEntradaDTO entradaSesion
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("consultarSesion");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "consultarSesion"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaSesion});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.SesionDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.SesionDTO)org.apache.axis.utils.JavaUtils.convert(
					    _resp, com.bachue.snr.biometrico.servicios.ws.SesionDTO.class
					);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO crearUsuario(
	    com.bachue.snr.biometrico.servicios.ws.UsuarioDTO entradaUsuario
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("crearUsuario");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "crearUsuario"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaUsuario});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO eliminarFirma(
	    com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO entradaEliminarFirma
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[14]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("eliminarFirma");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "eliminarFirma"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaEliminarFirma});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO enrolarUsuario(
	    com.bachue.snr.biometrico.servicios.ws.HuellaDTO[] entradaHuella
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("enrolarUsuario");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "enrolarUsuario"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaHuella});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO obtenerConstantes()
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("obtenerConstantes");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerConstantes"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO obtenerFirma(
	    com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO entradaObtenerFirma
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[13]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("obtenerFirma");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirma"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaObtenerFirma});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO obtenerTipoSegundoFactor(
	    com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO entradaUsuario
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("obtenerTipoSegundoFactor");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerTipoSegundoFactor"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaUsuario});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO obtenerUsuario(
	    com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO entradaUsuario
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("obtenerUsuario");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerUsuario"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaUsuario});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO registrarEvento(
	    com.bachue.snr.biometrico.servicios.ws.LogDTO entradaLog
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("registrarEvento");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "registrarEvento"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaLog});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO verificarClave(
	    com.bachue.snr.biometrico.servicios.ws.ClaveDTO entradaClave
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[11]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("verificarClave");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "verificarClave"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaClave});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
				}
			}
		}
		catch(org.apache.axis.AxisFault axisFaultException)
		{
			throw axisFaultException;
		}
	}

	public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO verificarUsuario(
	    com.bachue.snr.biometrico.servicios.ws.VerificacionDTO entradaVerificacion
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("verificarUsuario");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "verificarUsuario"
		    )
		);

		setRequestHeaders(_call);
		setAttachments(_call);

		try
		{
			java.lang.Object _resp = _call.invoke(new java.lang.Object[]{entradaVerificacion});

			if(_resp instanceof java.rmi.RemoteException)
				throw (java.rmi.RemoteException)_resp;
			else
			{
				extractAttachments(_call);

				try
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO)org.apache.axis.utils.JavaUtils
						.convert(_resp, com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
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
		oper.setName("obtenerConstantes");
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "constantesSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("borrarHuellas");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaUsuario"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "borrarHuellasDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.BorrarHuellasDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "stringSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("enrolarUsuario");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaHuella"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "huellaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.HuellaDTO[].class, false, false
			);
		param.setOmittable(true);
		param.setNillable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "booleanSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("registrarEvento");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaLog"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "logDTO"),
			    com.bachue.snr.biometrico.servicios.ws.LogDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "booleanSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarSesion");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaSesion"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "sesionEntradaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.SesionEntradaDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "sesionDTO")
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.SesionDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaSesion"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("obtenerTipoSegundoFactor");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaUsuario"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "usuarioEntradaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "stringSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[5]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("crearUsuario");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaUsuario"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "usuarioDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.UsuarioDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "stringSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("actualizarClave");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaClave"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "usuarioDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.UsuarioDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "stringSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[7]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("obtenerUsuario");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaUsuario"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "usuarioEntradaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "stringSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[8]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("verificarUsuario");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaVerificacion"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "verificacionDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.VerificacionDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "booleanSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[9] = oper;
	}

	private static void _initOperationDesc2()
	{
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarEstadisticas");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaEstadisticas"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "estadisticasEntradaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.EstadisticasEntradaDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "estadisticasSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaEstadisticas"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[10]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("verificarClave");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaClave"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "claveDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.ClaveDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "booleanSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "salidaResultado"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[11]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("agregarFirma");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaAgregarFirma"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "agregarFirmaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.AgregarFirmaDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "agregarFirmaSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "agregarFirmaResponse"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[12]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("obtenerFirma");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaObtenerFirma"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirmaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirmaSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "obtenerFirmaResponse"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[13]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("eliminarFirma");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName("", "entradaEliminarFirma"), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirmaDTO"
			    ), com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO.class, false, false
			);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "firmaSalidaDTO"
		    )
		);
		oper.setReturnClass(com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO.class);
		oper.setReturnQName(new javax.xml.namespace.QName("", "eliminarFirmaResponse"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[14] = oper;
	}
}
