/**
 * SBB_CB_IndicePropietariosSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1;


/**
 * Clase que contiene todos las propiedades SBB_CB_IndicePropietariosSOAP12BindingStub.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SBB_CB_IndicePropietariosSOAP12BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.v1.SBB_CB_IndicePropietarios_PortType
{
	/** Propiedad operations. */
	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[1];
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
	 * Instancia un nuevo objeto SB B C B indice propietarios SOAP 12 binding stub.
	 *
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SBB_CB_IndicePropietariosSOAP12BindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto SB B C B indice propietarios SOAP 12 binding stub.
	 *
	 * @param endpointURL de endpoint URL
	 * @param service de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SBB_CB_IndicePropietariosSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	/**
	 * Instancia un nuevo objeto SB B C B indice propietarios SOAP 12 binding stub.
	 *
	 * @param service de service
	 * @throws AxisFault cuando se produce algun error en el proceso
	 */
	public SBB_CB_IndicePropietariosSOAP12BindingStub(javax.xml.rpc.Service service)
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>codCirculoRegistral"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>codDepartamento"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>codMunicipio"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>direccionPredio"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>nomCirculoRegistral"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>nomDepartamento"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>nomMunicipio"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>numMatriculaInmobiliaria"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>numPredial"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">inmuebleDTO>NUPRE"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">propietarioDTO>nomPropietario"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">propietarioDTO>nomTipoDocumento"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">propietarioDTO>numDocumentoPersona"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">propietarioDTO>numNIT"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>codDepartamento"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>codMunicipio"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>convenio"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>numDocumentoPersona"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>numIdentificacionPredio"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>numNIT"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>primerApellido"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>primerNombre"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>razonSocial"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>segundoApellido"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>segundoNombre"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>tipoDocumentoPersona"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoDocumentoPersona.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoEntradaIndicePropietarios>tipoIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoIdentificacionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoSalidaIndicePropietarios>cantidadResultados"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoSalidaIndicePropietarios>codMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    ">tipoSalidaIndicePropietarios>descripcionMensaje"
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
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "inmuebleDTO"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "propietarioDTO"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.PropietarioDTO.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    "tipoEntradaIndicePropietarios"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			    "tipoSalidaIndicePropietarios"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios consultar(
	    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/v1/consultar");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "consultar"));

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
					return (co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios.class
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
		oper.setName("consultar");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			        "entradaIndicePropietarios"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
			        "tipoEntradaIndicePropietarios"
			    ),
			    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        "tipoSalidaIndicePropietarios"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        "salidaIndicePropietarios"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;
	}
}
