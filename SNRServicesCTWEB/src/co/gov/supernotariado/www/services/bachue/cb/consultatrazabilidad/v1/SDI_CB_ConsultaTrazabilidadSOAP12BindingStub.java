/**
 * SDI_CB_ConsultaTrazabilidadSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1;


/**
 * Clase que contiene todos las propiedades SDI_CB_ConsultaTrazabilidadSOAP12BindingStub.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/09/2020
 */
public class SDI_CB_ConsultaTrazabilidadSOAP12BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.cb.consultatrazabilidad.v1.SDI_CB_ConsultaTrazabilidad_PortType
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
	 * Instancia un nuevo objeto SD I C B consulta trazabilidad SOAP 12 binding stub.
	 *
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SDI_CB_ConsultaTrazabilidadSOAP12BindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto SD I C B consulta trazabilidad SOAP 12 binding stub.
	 *
	 * @param endpointURL correspondiente al valor de endpoint URL
	 * @param service correspondiente al valor de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SDI_CB_ConsultaTrazabilidadSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	/**
	 * Instancia un nuevo objeto SD I C B consulta trazabilidad SOAP 12 binding stub.
	 *
	 * @param service correspondiente al valor de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SDI_CB_ConsultaTrazabilidadSOAP12BindingStub(javax.xml.rpc.Service service)
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
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			    ">>tipoSalidaConsultaActosTurno>actos>acto"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurnoActosActo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			    ">tipoSalidaConsultaActosTurno>actos"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurnoActosActo[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			    ">>tipoSalidaConsultaActosTurno>actos>acto"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1", "acto"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			    "tipoEntradaConsultaActosTurno"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			    "tipoSalidaConsultaActosTurno"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			    ">>tipoSalidaConsultaDetalleCertificado>certificados>certificado"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificadoCertificadosCertificado.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			    ">tipoSalidaConsultaDetalleCertificado>certificados"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			    ">>tipoSalidaConsultaDetalleCertificado>certificados>certificado"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			    "certificado"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			    "tipoEntradaConsultaDetalleCertificado"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			    "tipoSalidaConsultaDetalleCertificado"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    ">tipoElementoNir>documentos"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoDocumento[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoDocumento"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "documento"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    ">tipoElementoNir>matriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoMatricula[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoMatricula"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "matricula"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    ">tipoElementoNir>trazabilidades"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoTrazabilidad[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoTrazabilidad"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "trazabilidad"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    ">tipoEntradaConsultarTrazabilidad>criterioBusqueda"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidadCriterioBusqueda.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    ">tipoSalidaConsultarTrazabilidad>codigoMensaje"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidadCodigoMensaje.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    ">tipoSalidaConsultarTrazabilidad>elementosnir"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoElementoNir[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoElementoNir"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "elementonir"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoDocumento"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoDocumento.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoElementoNir"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoElementoNir.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoEntradaConsultarTrazabilidad"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoMatricula"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoMatricula.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoSalidaConsultarTrazabilidad"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			    "tipoTrazabilidad"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoTrazabilidad.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno consultaActosTurno(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultatrazabilidad/v1/ConsultaActosTurno"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ConsultaActosTurno"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno.class
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
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado consultaDetalleCertificado(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultatrazabilidad/v1/ConsultaDetalleCertificado"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ConsultaDetalleCertificado"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado.class
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
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad consultarTrazabilidad(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/cb/consultatrazabilidad/v1/ConsultarTrazabilidad"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ConsultarTrazabilidad"));

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
					return (co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad.class
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
		oper.setName("ConsultarTrazabilidad");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			        "entradaConsultarTrazabilidad"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
			        "tipoEntradaConsultarTrazabilidad"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoSalidaConsultarTrazabilidad"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidad.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "salidaConsultarTrazabilidad"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ConsultaActosTurno");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			        "entradaConsultaActosTurno"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
			        "tipoEntradaConsultaActosTurno"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "tipoSalidaConsultaActosTurno"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaActosTurno/v1",
		        "salidaConsultaActosTurno"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1]     = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ConsultaDetalleCertificado");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			        "entradaConsultaDetalleCertificado"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
			        "tipoEntradaConsultaDetalleCertificado"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "tipoSalidaConsultaDetalleCertificado"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "salidaConsultaDetalleCertificado"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;
	}
}