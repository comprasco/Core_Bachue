/**
 * BS_SBB_CB_RRRMatriculas_BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1;

public class BS_SBB_CB_RRRMatriculas_BindingStub extends org.apache.axis.client.Stub implements co.gov.supernotariado.www.services.bachue.ee.RRRMatriculas.v1.BS_SBB_CB_RRRMatriculas_PortType
{
	static org.apache.axis.description.OperationDesc[] _operations;

	static
	{
		_operations = new org.apache.axis.description.OperationDesc[1];
		_initOperationDesc1();
	}

	private java.util.Vector cachedDeserFactories = new java.util.Vector();
	private java.util.Vector cachedSerClasses     = new java.util.Vector();
	private java.util.Vector cachedSerFactories   = new java.util.Vector();
	private java.util.Vector cachedSerQNames      = new java.util.Vector();

	public BS_SBB_CB_RRRMatriculas_BindingStub()
	    throws org.apache.axis.AxisFault
	{
		this(null);
	}

	public BS_SBB_CB_RRRMatriculas_BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
	    throws org.apache.axis.AxisFault
	{
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public BS_SBB_CB_RRRMatriculas_BindingStub(javax.xml.rpc.Service service)
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
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho>intervinientes>interviniente"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaHipotecas>hipoteca>intervinientes>interviniente"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipotecaIntervinientesInterviniente.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaPublicidades>publicidad>intervinientes>interviniente"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidadIntervinientesInterviniente.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaResponsabilidades>responsabilidad>intervinientes>interviniente"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidadIntervinientesInterviniente.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaRestricciones>restriccion>intervinientes>interviniente"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccionIntervinientesInterviniente.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho>intervinientes"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerechoIntervinientesInterviniente[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho>intervinientes>interviniente"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "interviniente"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>tipoSalidaConsultarRRRMatriculas>listaHipotecas>hipoteca>intervinientes"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipotecaIntervinientesInterviniente[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaHipotecas>hipoteca>intervinientes>interviniente"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "interviniente"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>tipoSalidaConsultarRRRMatriculas>listaPublicidades>publicidad>intervinientes"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidadIntervinientesInterviniente[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaPublicidades>publicidad>intervinientes>interviniente"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "interviniente"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>tipoSalidaConsultarRRRMatriculas>listaResponsabilidades>responsabilidad>intervinientes"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidadIntervinientesInterviniente[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaResponsabilidades>responsabilidad>intervinientes>interviniente"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "interviniente"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>tipoSalidaConsultarRRRMatriculas>listaRestricciones>restriccion>intervinientes"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccionIntervinientesInterviniente[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>>>tipoSalidaConsultarRRRMatriculas>listaRestricciones>restriccion>intervinientes>interviniente"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "interviniente"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerecho.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaHipotecas>hipoteca"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaPublicidades>publicidad"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaResponsabilidades>responsabilidad"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaRestricciones>restriccion"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">tipoEntradaConsultarRRRMatriculas>tipoIdentificacionPredio"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">tipoSalidaConsultarRRRMatriculas>listaDerechos"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaDerechosDerecho[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaDerechos>derecho"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1", "derecho"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">tipoSalidaConsultarRRRMatriculas>listaHipotecas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaHipotecasHipoteca[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaHipotecas>hipoteca"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "hipoteca"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">tipoSalidaConsultarRRRMatriculas>listaPublicidades"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaPublicidadesPublicidad[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaPublicidades>publicidad"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "publicidad"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">tipoSalidaConsultarRRRMatriculas>listaResponsabilidades"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaResponsabilidadesResponsabilidad[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaResponsabilidades>responsabilidad"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "responsabilidad"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">tipoSalidaConsultarRRRMatriculas>listaRestricciones"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculasListaRestriccionesRestriccion[].class;
		cachedSerClasses.add(cls);
		qName      = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    ">>tipoSalidaConsultarRRRMatriculas>listaRestricciones>restriccion"
			);
		qName2     = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "restriccion"
			);
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "tipoEntradaConsultarRRRMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
			    "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			    "tipoSalidaConsultarRRRMatriculas"
			);
		cachedSerQNames.add(qName);
		cls = co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);
	}

	public co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas consultarRRRMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas entrada
	)
	    throws java.rmi.RemoteException
	{
		if(super.cachedEndpoint == null)
			throw new org.apache.axis.NoEndPointException();

		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI(
		    "https://www.supernotariado.gov.co/services/bachue/ee/RRRMatriculas/v1/ConsultarRRRMatriculas"
		);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "ConsultarRRRMatriculas"));

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
					return (co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas)_resp;
				}
				catch(java.lang.Exception _exception)
				{
					return (co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas)org.apache.axis.utils.JavaUtils
						.convert(
						    _resp,
						    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas.class
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
		oper.setName("ConsultarRRRMatriculas");
		param = new org.apache.axis.description.ParameterDesc(
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			        "entradaConsultarRRRMatriculas"
			    ), org.apache.axis.description.ParameterDesc.IN,
			    new javax.xml.namespace.QName(
			        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
			        "tipoEntradaConsultarRRRMatriculas"
			    ),
			    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoEntradaConsultarRRRMatriculas.class,
			    false, false
			);
		oper.addParameter(param);
		oper.setReturnType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "tipoSalidaConsultarRRRMatriculas"
		    )
		);
		oper.setReturnClass(
		    co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1.TipoSalidaConsultarRRRMatriculas.class
		);
		oper.setReturnQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        "salidaConsultarRRRMatriculas"
		    )
		);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;
	}
}
