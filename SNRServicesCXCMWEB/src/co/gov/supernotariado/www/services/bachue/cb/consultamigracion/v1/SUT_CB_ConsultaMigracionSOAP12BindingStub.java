/**
 * SUT_CB_ConsultaMigracionSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1;


/**
 * Clase que contiene todos las propiedades SUT_CB_ConsultaMigracionSOAP12BindingStub.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SUT_CB_ConsultaMigracionSOAP12BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.cb.consultamigracion.v1.SUT_CB_ConsultaMigracion_PortType
{
	/** Propiedad operations. */
	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[3];
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
	 * Instancia un nuevo objeto SU T C B consulta migracion SOAP 12 binding stub.
	 *
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CB_ConsultaMigracionSOAP12BindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto SU T C B consulta migracion SOAP 12 binding stub.
	 *
	 * @param endpointURL de endpoint URL
	 * @param service de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CB_ConsultaMigracionSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	/**
	 * Instancia un nuevo objeto SU T C B consulta migracion SOAP 12 binding stub.
	 *
	 * @param service de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SUT_CB_ConsultaMigracionSOAP12BindingStub(javax.xml.rpc.Service service)
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    ">tipoDatosMatriculasMigracion>infoMatriculaMigrada"
			);
		cachedSerQNames.add(qName);
		cls = boolean.class;
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    ">tipoDatosMatriculasMigracion>matricula"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    ">tipoMatriculaConsultada>matricula"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    ">tipoSalidaConsultaMigracionMatriculas>codMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    ">tipoSalidaConsultaMigracionMatriculas>descripcionMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    "tipoDatosMatriculasMigracion"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoDatosMatriculasMigracion.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    "tipoEntradaConsultaMigracionMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    "tipoMatriculaConsultada"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			    "tipoSalidaConsultaMigracionMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			    ">tipoEntradaConsultaMigracionORIP>codCirculoRegistral"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			    ">tipoSalidaConsultaMigracionORIP>codMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			    ">tipoSalidaConsultaMigracionORIP>descripcionMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			    ">tipoSalidaConsultaMigracionORIP>informacionMigrada"
			);
		cachedSerQNames.add(qName);
		cls = boolean.class;
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			    "tipoEntradaConsultaMigracionORIP"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			    "tipoSalidaConsultaMigracionORIP"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			    ">tipoEntradaConsultaMigracionPredio>numIdentificacionPredio"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			    ">tipoEntradaConsultaMigracionPredio>tipoIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredioTipoIdentificacionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			    ">tipoSalidaConsultaMigracionPredio>codMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			    ">tipoSalidaConsultaMigracionPredio>descripcionMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			    ">tipoSalidaConsultaMigracionPredio>informacionMigrada"
			);
		cachedSerQNames.add(qName);
		cls = boolean.class;
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			    "tipoEntradaConsultaMigracionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			    "tipoSalidaConsultaMigracionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas consultarMigracionMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1/consultarMigracionMatriculas"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarMigracionMatriculas"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas.class
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
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP consultarMigracionORIP(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1/consultarMigracionORIP"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarMigracionORIP"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP.class
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
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio consultarMigracionPredio(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/v1/consultarMigracionPredio"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultarMigracionPredio"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio.class
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
		oper.setName("consultarMigracionPredio");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			        "entradaConsultaMigracionPredio"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
			        "tipoEntradaConsultaMigracionPredio"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
		        "tipoSalidaConsultaMigracionPredio"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
		        "salidaConsultaMigracionPredio"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarMigracionORIP");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			        "entradaConsultaMigracionORIP"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
			        "tipoEntradaConsultaMigracionORIP"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
		        "tipoSalidaConsultaMigracionORIP"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
		        "salidaConsultaMigracionORIP"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarMigracionMatriculas");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			        "entradaConsultaMigracionMatriculas"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
			        "tipoEntradaConsultaMigracionMatriculas"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "tipoSalidaConsultaMigracionMatriculas"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "salidaConsultaMigracionMatriculas"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;
	}
}
