/**
 * TipoEntradaIndicePropietarios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1;


/**
 * El esquema define los
 *                         datos de entrada para la operacion Consultar
 *                         Indice de Propietarios.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public class TipoEntradaIndicePropietarios implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6571384842569733222L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIndicePropietarios.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "tipoEntradaIndicePropietarios"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "tipoDocumentoPersona"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        ">tipoEntradaIndicePropietarios>tipoDocumentoPersona"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "numDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numNIT");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "numNIT"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "primerNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "segundoNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "primerApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "tipoIdentificacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        ">tipoEntradaIndicePropietarios>tipoIdentificacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("convenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "convenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "codDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("entidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "entidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("auditoriaEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "auditoriaEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("auditoriaUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "auditoriaUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad auditoria entidad. */
	private java.lang.String auditoriaEntidad;

	/** Propiedad auditoria usuario. */
	private java.lang.String auditoriaUsuario;

	/** Propiedad cod circulo registral. */
	private java.lang.String codCirculoRegistral;

	/** Propiedad cod departamento. */
	/* Código DANE, string de longitud 2 */
	private java.lang.String codDepartamento;

	/** Propiedad cod municipio. */
	/* código DANE, longitud 5, dpto + municipio */
	private java.lang.String codMunicipio;

	/** Propiedad convenio. */
	/* Nombre de convenio que tendría la SNR con la
	 *                                 Entidad que consume el servicio */
	private java.lang.String convenio;

	/** Propiedad entidad. */
	private java.lang.String entidad;

	/** Propiedad num documento persona. */
	/* Corresponde al numero de documento de una
	 *                                 persona natural según el tipo de documento pesona que ha enviado */
	private java.lang.String numDocumentoPersona;

	/** Propiedad num identificacion predio. */
	/* Al seleccionar un tipo de Identificación
	 *                                 predio,
	 *                                 se espera en este campo el número correspondiente; para el
	 * caso
	 *                                 de
	 *                                 Matricula, el sistema toma los tres primeros caracteres como
	 *                                 Código Circulo Registral y los siguientes como el Número de
	 *                                 Matrícula Inmobiliaria. */
	private java.lang.String numIdentificacionPredio;

	/** Propiedad num NIT. */
	/* Corresponde al Número Único de Identificación
	 *                                 Tributaria. */
	private java.lang.String numNIT;

	/** Propiedad primer apellido. */
	/* Corresponde al primer apellido de propietario */
	private java.lang.String primerApellido;

	/** Propiedad primer nombre. */
	/* Corresponde al primer nombre de propietario */
	private java.lang.String primerNombre;

	/** Propiedad razon social. */
	/* Corresponde a la razón social del propietario */
	private java.lang.String razonSocial;

	/** Propiedad segundo apellido. */
	/* Corresponde al segundo apellido de propietario */
	private java.lang.String segundoApellido;

	/** Propiedad segundo nombre. */
	/* Corresponde al segundo nombre de propietario */
	private java.lang.String segundoNombre;

	/** Propiedad tipo documento persona. */
	/* Corresponde al tipo de documento del
	 *                                 propietario. Solo para las personas naturales */
	private co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoDocumentoPersona tipoDocumentoPersona;

	/** Propiedad tipo identificacion predio. */
	/* Corresponde al tipo de identificacion por el
	 *                                 cual podemos buscar un predio. */
	private co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoIdentificacionPredio tipoIdentificacionPredio;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada indice propietarios.
	 */
	public TipoEntradaIndicePropietarios()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada indice propietarios.
	 *
	 * @param tipoDocumentoPersona correspondiente al valor de tipo documento persona
	 * @param numDocumentoPersona correspondiente al valor de num documento persona
	 * @param numNIT correspondiente al valor de num NIT
	 * @param primerNombre correspondiente al valor de primer nombre
	 * @param segundoNombre correspondiente al valor de segundo nombre
	 * @param primerApellido correspondiente al valor de primer apellido
	 * @param segundoApellido correspondiente al valor de segundo apellido
	 * @param razonSocial correspondiente al valor de razon social
	 * @param tipoIdentificacionPredio correspondiente al valor de tipo identificacion predio
	 * @param numIdentificacionPredio correspondiente al valor de num identificacion predio
	 * @param convenio correspondiente al valor de convenio
	 * @param codDepartamento correspondiente al valor de cod departamento
	 * @param codMunicipio correspondiente al valor de cod municipio
	 * @param codCirculoRegistral correspondiente al valor de cod circulo registral
	 * @param entidad correspondiente al valor de entidad
	 * @param auditoriaEntidad correspondiente al valor de auditoria entidad
	 * @param auditoriaUsuario correspondiente al valor de auditoria usuario
	 */
	public TipoEntradaIndicePropietarios(
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoDocumentoPersona     tipoDocumentoPersona,
	    java.lang.String                                                                                                                   numDocumentoPersona,
	    java.lang.String                                                                                                                   numNIT,
	    java.lang.String                                                                                                                   primerNombre,
	    java.lang.String                                                                                                                   segundoNombre,
	    java.lang.String                                                                                                                   primerApellido,
	    java.lang.String                                                                                                                   segundoApellido,
	    java.lang.String                                                                                                                   razonSocial,
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoIdentificacionPredio tipoIdentificacionPredio,
	    java.lang.String                                                                                                                   numIdentificacionPredio,
	    java.lang.String                                                                                                                   convenio,
	    java.lang.String                                                                                                                   codDepartamento,
	    java.lang.String                                                                                                                   codMunicipio,
	    java.lang.String                                                                                                                   codCirculoRegistral,
	    java.lang.String                                                                                                                   entidad,
	    java.lang.String                                                                                                                   auditoriaEntidad,
	    java.lang.String                                                                                                                   auditoriaUsuario
	)
	{
		this.tipoDocumentoPersona         = tipoDocumentoPersona;
		this.numDocumentoPersona          = numDocumentoPersona;
		this.numNIT                       = numNIT;
		this.primerNombre                 = primerNombre;
		this.segundoNombre                = segundoNombre;
		this.primerApellido               = primerApellido;
		this.segundoApellido              = segundoApellido;
		this.razonSocial                  = razonSocial;
		this.tipoIdentificacionPredio     = tipoIdentificacionPredio;
		this.numIdentificacionPredio      = numIdentificacionPredio;
		this.convenio                     = convenio;
		this.codDepartamento              = codDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.entidad                      = entidad;
		this.auditoriaEntidad             = auditoriaEntidad;
		this.auditoriaUsuario             = auditoriaUsuario;
	}

	/**
	 * Sets the auditoriaEntidad value for this TipoEntradaIndicePropietarios.
	 *
	 * @param auditoriaEntidad correspondiente al valor de auditoria entidad
	 */
	public void setAuditoriaEntidad(java.lang.String auditoriaEntidad)
	{
		this.auditoriaEntidad = auditoriaEntidad;
	}

	/**
	 * Gets the auditoriaEntidad value for this TipoEntradaIndicePropietarios.
	 *
	 * @return auditoriaEntidad
	 */
	public java.lang.String getAuditoriaEntidad()
	{
		return auditoriaEntidad;
	}

	/**
	 * Sets the auditoriaUsuario value for this TipoEntradaIndicePropietarios.
	 *
	 * @param auditoriaUsuario correspondiente al valor de auditoria usuario
	 */
	public void setAuditoriaUsuario(java.lang.String auditoriaUsuario)
	{
		this.auditoriaUsuario = auditoriaUsuario;
	}

	/**
	 * Gets the auditoriaUsuario value for this TipoEntradaIndicePropietarios.
	 *
	 * @return auditoriaUsuario
	 */
	public java.lang.String getAuditoriaUsuario()
	{
		return auditoriaUsuario;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaIndicePropietarios.
	 *
	 * @param codCirculoRegistral correspondiente al valor de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaIndicePropietarios.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codDepartamento value for this TipoEntradaIndicePropietarios.
	 *
	 * @param codDepartamento   * Código DANE, string de longitud 2
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this TipoEntradaIndicePropietarios.
	 *
	 * @return codDepartamento   * Código DANE, string de longitud 2
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMunicipio value for this TipoEntradaIndicePropietarios.
	 *
	 * @param codMunicipio   * código DANE, longitud 5, dpto + municipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this TipoEntradaIndicePropietarios.
	 *
	 * @return codMunicipio   * código DANE, longitud 5, dpto + municipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the convenio value for this TipoEntradaIndicePropietarios.
	 *
	 * @param convenio   * Nombre de convenio que tendría la SNR con la
	     *                                 Entidad que consume el servicio
	 */
	public void setConvenio(java.lang.String convenio)
	{
		this.convenio = convenio;
	}

	/**
	 * Gets the convenio value for this TipoEntradaIndicePropietarios.
	 *
	 * @return convenio   * Nombre de convenio que tendría la SNR con la
	     *                                 Entidad que consume el servicio
	 */
	public java.lang.String getConvenio()
	{
		return convenio;
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
	 * @return el valor de deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the entidad value for this TipoEntradaIndicePropietarios.
	 *
	 * @param entidad correspondiente al valor de entidad
	 */
	public void setEntidad(java.lang.String entidad)
	{
		this.entidad = entidad;
	}

	/**
	 * Gets the entidad value for this TipoEntradaIndicePropietarios.
	 *
	 * @return entidad
	 */
	public java.lang.String getEntidad()
	{
		return entidad;
	}

	/**
	 * Sets the numDocumentoPersona value for this TipoEntradaIndicePropietarios.
	 *
	 * @param numDocumentoPersona   * Corresponde al numero de documento de una
	     *                                 persona natural según el tipo de documento pesona que ha enviado
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this TipoEntradaIndicePropietarios.
	 *
	 * @return numDocumentoPersona   * Corresponde al numero de documento de una
	     *                                 persona natural según el tipo de documento pesona que ha enviado
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the numIdentificacionPredio value for this TipoEntradaIndicePropietarios.
	 *
	 * @param numIdentificacionPredio   * Al seleccionar un tipo de Identificación
	     *                                 predio,
	     *                                 se espera en este campo el número correspondiente; para el
	     * caso
	     *                                 de
	     *                                 Matricula, el sistema toma los tres primeros caracteres como
	     *                                 Código Circulo Registral y los siguientes como el Número de
	     *                                 Matrícula Inmobiliaria.
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
	 * @return el valor de serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object.
	 *
	 * @return el valor de type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoEntradaIndicePropietarios.
	 *
	 * @return numIdentificacionPredio   * Al seleccionar un tipo de Identificación
	     *                                 predio,
	     *                                 se espera en este campo el número correspondiente; para el
	     * caso
	     *                                 de
	     *                                 Matricula, el sistema toma los tres primeros caracteres como
	     *                                 Código Circulo Registral y los siguientes como el Número de
	     *                                 Matrícula Inmobiliaria.
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
	}

	/**
	 * Sets the numNIT value for this TipoEntradaIndicePropietarios.
	 *
	 * @param numNIT   * Corresponde al Número Único de Identificación
	     *                                 Tributaria.
	 */
	public void setNumNIT(java.lang.String numNIT)
	{
		this.numNIT = numNIT;
	}

	/**
	 * Gets the numNIT value for this TipoEntradaIndicePropietarios.
	 *
	 * @return numNIT   * Corresponde al Número Único de Identificación
	     *                                 Tributaria.
	 */
	public java.lang.String getNumNIT()
	{
		return numNIT;
	}

	/**
	 * Sets the primerApellido value for this TipoEntradaIndicePropietarios.
	 *
	 * @param primerApellido   * Corresponde al primer apellido de propietario
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this TipoEntradaIndicePropietarios.
	 *
	 * @return primerApellido   * Corresponde al primer apellido de propietario
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this TipoEntradaIndicePropietarios.
	 *
	 * @param primerNombre   * Corresponde al primer nombre de propietario
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this TipoEntradaIndicePropietarios.
	 *
	 * @return primerNombre   * Corresponde al primer nombre de propietario
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the razonSocial value for this TipoEntradaIndicePropietarios.
	 *
	 * @param razonSocial   * Corresponde a la razón social del propietario
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoEntradaIndicePropietarios.
	 *
	 * @return razonSocial   * Corresponde a la razón social del propietario
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellido value for this TipoEntradaIndicePropietarios.
	 *
	 * @param segundoApellido   * Corresponde al segundo apellido de propietario
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoEntradaIndicePropietarios.
	 *
	 * @return segundoApellido   * Corresponde al segundo apellido de propietario
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this TipoEntradaIndicePropietarios.
	 *
	 * @param segundoNombre   * Corresponde al segundo nombre de propietario
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoEntradaIndicePropietarios.
	 *
	 * @return segundoNombre   * Corresponde al segundo nombre de propietario
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
	}

	/**
	 * Sets the tipoDocumentoPersona value for this TipoEntradaIndicePropietarios.
	 *
	 * @param tipoDocumentoPersona   * Corresponde al tipo de documento del
	     *                                 propietario. Solo para las personas naturales
	 */
	public void setTipoDocumentoPersona(
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoDocumentoPersona tipoDocumentoPersona
	)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this TipoEntradaIndicePropietarios.
	 *
	 * @return tipoDocumentoPersona   * Corresponde al tipo de documento del
	     *                                 propietario. Solo para las personas naturales
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoDocumentoPersona getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
	}

	/**
	 * Sets the tipoIdentificacionPredio value for this TipoEntradaIndicePropietarios.
	 *
	 * @param tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
	     *                                 cual podemos buscar un predio.
	 */
	public void setTipoIdentificacionPredio(
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoIdentificacionPredio tipoIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio = tipoIdentificacionPredio;
	}

	/**
	 * Gets the tipoIdentificacionPredio value for this TipoEntradaIndicePropietarios.
	 *
	 * @return tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
	     *                                 cual podemos buscar un predio.
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.TipoEntradaIndicePropietariosTipoIdentificacionPredio getTipoIdentificacionPredio()
	{
		return tipoIdentificacionPredio;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaIndicePropietarios))
			return false;

		TipoEntradaIndicePropietarios other = (TipoEntradaIndicePropietarios)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocumentoPersona == null) && (other.getTipoDocumentoPersona() == null))
				|| ((this.tipoDocumentoPersona != null)
				&& this.tipoDocumentoPersona.equals(other.getTipoDocumentoPersona())))
				&& (((this.numDocumentoPersona == null) && (other.getNumDocumentoPersona() == null))
				|| ((this.numDocumentoPersona != null)
				&& this.numDocumentoPersona.equals(other.getNumDocumentoPersona())))
				&& (((this.numNIT == null) && (other.getNumNIT() == null))
				|| ((this.numNIT != null) && this.numNIT.equals(other.getNumNIT())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())))
				&& (((this.tipoIdentificacionPredio == null) && (other.getTipoIdentificacionPredio() == null))
				|| ((this.tipoIdentificacionPredio != null)
				&& this.tipoIdentificacionPredio.equals(other.getTipoIdentificacionPredio())))
				&& (((this.numIdentificacionPredio == null) && (other.getNumIdentificacionPredio() == null))
				|| ((this.numIdentificacionPredio != null)
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())))
				&& (((this.convenio == null) && (other.getConvenio() == null))
				|| ((this.convenio != null) && this.convenio.equals(other.getConvenio())))
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.entidad == null) && (other.getEntidad() == null))
				|| ((this.entidad != null) && this.entidad.equals(other.getEntidad())))
				&& (((this.auditoriaEntidad == null) && (other.getAuditoriaEntidad() == null))
				|| ((this.auditoriaEntidad != null) && this.auditoriaEntidad.equals(other.getAuditoriaEntidad())))
				&& (((this.auditoriaUsuario == null) && (other.getAuditoriaUsuario() == null))
				|| ((this.auditoriaUsuario != null) && this.auditoriaUsuario.equals(other.getAuditoriaUsuario())));
		__equalsCalc     = null;

		return _equals;
	}

	/** {@inheritdoc} */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getTipoDocumentoPersona() != null)
			_hashCode += getTipoDocumentoPersona().hashCode();

		if(getNumDocumentoPersona() != null)
			_hashCode += getNumDocumentoPersona().hashCode();

		if(getNumNIT() != null)
			_hashCode += getNumNIT().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		if(getTipoIdentificacionPredio() != null)
			_hashCode += getTipoIdentificacionPredio().hashCode();

		if(getNumIdentificacionPredio() != null)
			_hashCode += getNumIdentificacionPredio().hashCode();

		if(getConvenio() != null)
			_hashCode += getConvenio().hashCode();

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getEntidad() != null)
			_hashCode += getEntidad().hashCode();

		if(getAuditoriaEntidad() != null)
			_hashCode += getAuditoriaEntidad().hashCode();

		if(getAuditoriaUsuario() != null)
			_hashCode += getAuditoriaUsuario().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
