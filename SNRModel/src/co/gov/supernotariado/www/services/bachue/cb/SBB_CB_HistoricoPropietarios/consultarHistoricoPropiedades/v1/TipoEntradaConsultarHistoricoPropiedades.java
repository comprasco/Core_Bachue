/**
 * TipoEntradaConsultarHistoricoPropiedades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1;



/**
 * El esquema define los
 *                         datos de entrada para la operacion consultarHistoricoPropiedades.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 20/03/2020
 */
public class TipoEntradaConsultarHistoricoPropiedades implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6507081161532102332L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarHistoricoPropiedades.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "tipoEntradaConsultarHistoricoPropiedades"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "tipoDocumentoPersona"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        ">tipoEntradaConsultarHistoricoPropiedades>tipoDocumentoPersona"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "numNIT"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "primerApellido"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "primerNombre"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "segundoApellido"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "segundoNombre"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "razonSocial"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/SBB_CB_HistoricoPropietarios/consultarHistoricoPropiedades/v1",
		        "convenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad convenio. */
	/* nombre de la Entidad con la que se tiene
	 *                                 convenio */
	private java.lang.String convenio;

	/** Propiedad num documento persona. */
	/* Número de documento de una persona natural */
	private java.lang.String numDocumentoPersona;

	/** Propiedad num NIT. */
	/* Corresponde al Número Único de Identificación
	 *                                 Tributaria. */
	private java.lang.String numNIT;

	/** Propiedad primer apellido. */
	/* primer apellido del propietario para realizar
	 *                                 la
	 *                                 búsqueda del histórico de sus propiedades */
	private java.lang.String primerApellido;

	/** Propiedad primer nombre. */
	/* primer nombre del propietario para realizar la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades */
	private java.lang.String primerNombre;

	/** Propiedad razon social. */
	/* razon Social del propietario para realizar la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades */
	private java.lang.String razonSocial;

	/** Propiedad segundo apellido. */
	/* segundo apellido del propietario para realizar
	 *                                 la búsqueda del histórico
	 *                                 de sus propiedades */
	private java.lang.String segundoApellido;

	/** Propiedad segundo nombre. */
	/* segundo nombre del propietario para realizar
	 *                                 la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades */
	private java.lang.String segundoNombre;

	/** Propiedad tipo documento persona. */
	/* Tipo de identificación de una persona Natural */
	private co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedadesTipoDocumentoPersona tipoDocumentoPersona;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar historico propiedades.
	 */
	public TipoEntradaConsultarHistoricoPropiedades()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar historico propiedades.
	 *
	 * @param tipoDocumentoPersona de tipo documento persona
	 * @param numDocumentoPersona de num documento persona
	 * @param numNIT de num NIT
	 * @param primerApellido de primer apellido
	 * @param primerNombre de primer nombre
	 * @param segundoApellido de segundo apellido
	 * @param segundoNombre de segundo nombre
	 * @param razonSocial de razon social
	 * @param convenio de convenio
	 */
	public TipoEntradaConsultarHistoricoPropiedades(
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedadesTipoDocumentoPersona tipoDocumentoPersona,
	    java.lang.String                                                                                                                                                        numDocumentoPersona,
	    java.lang.String                                                                                                                                                        numNIT,
	    java.lang.String                                                                                                                                                        primerApellido,
	    java.lang.String                                                                                                                                                        primerNombre,
	    java.lang.String                                                                                                                                                        segundoApellido,
	    java.lang.String                                                                                                                                                        segundoNombre,
	    java.lang.String                                                                                                                                                        razonSocial,
	    java.lang.String                                                                                                                                                        convenio
	)
	{
		this.tipoDocumentoPersona     = tipoDocumentoPersona;
		this.numDocumentoPersona      = numDocumentoPersona;
		this.numNIT                   = numNIT;
		this.primerApellido           = primerApellido;
		this.primerNombre             = primerNombre;
		this.segundoApellido          = segundoApellido;
		this.segundoNombre            = segundoNombre;
		this.razonSocial              = razonSocial;
		this.convenio                 = convenio;
	}

	/**
	 * Sets the convenio value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param convenio   * nombre de la Entidad con la que se tiene
	 *                                 convenio
	 */
	public void setConvenio(java.lang.String convenio)
	{
		this.convenio = convenio;
	}

	/**
	 * Gets the convenio value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return convenio   * nombre de la Entidad con la que se tiene
	 *                                 convenio
	 */
	public java.lang.String getConvenio()
	{
		return convenio;
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType de mech type
	 * @param _javaType de java type
	 * @param _xmlType de xml type
	 * @return el valor de deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the numDocumentoPersona value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param numDocumentoPersona   * Número de documento de una persona natural
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return numDocumentoPersona   * Número de documento de una persona natural
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the numNIT value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param numNIT   * Corresponde al Número Único de Identificación
	 *                                 Tributaria.
	 */
	public void setNumNIT(java.lang.String numNIT)
	{
		this.numNIT = numNIT;
	}

	/**
	 * Gets the numNIT value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return numNIT   * Corresponde al Número Único de Identificación
	 *                                 Tributaria.
	 */
	public java.lang.String getNumNIT()
	{
		return numNIT;
	}

	/**
	 * Sets the primerApellido value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param primerApellido   * primer apellido del propietario para realizar
	 *                                 la
	 *                                 búsqueda del histórico de sus propiedades
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return primerApellido   * primer apellido del propietario para realizar
	 *                                 la
	 *                                 búsqueda del histórico de sus propiedades
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param primerNombre   * primer nombre del propietario para realizar la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return primerNombre   * primer nombre del propietario para realizar la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the razonSocial value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param razonSocial   * razon Social del propietario para realizar la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return razonSocial   * razon Social del propietario para realizar la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellido value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param segundoApellido   * segundo apellido del propietario para realizar
	 *                                 la búsqueda del histórico
	 *                                 de sus propiedades
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return segundoApellido   * segundo apellido del propietario para realizar
	 *                                 la búsqueda del histórico
	 *                                 de sus propiedades
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param segundoNombre   * segundo nombre del propietario para realizar
	 *                                 la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return segundoNombre   * segundo nombre del propietario para realizar
	 *                                 la
	 *                                 búsqueda del histórico de
	 *                                 sus propiedades
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType de mech type
	 * @param _javaType de java type
	 * @param _xmlType de xml type
	 * @return el valor de serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the tipoDocumentoPersona value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @param tipoDocumentoPersona   * Tipo de identificación de una persona Natural
	 */
	public void setTipoDocumentoPersona(
	    co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedadesTipoDocumentoPersona tipoDocumentoPersona
	)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this TipoEntradaConsultarHistoricoPropiedades.
	 *
	 * @return tipoDocumentoPersona   * Tipo de identificación de una persona Natural
	 */
	public co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedadesTipoDocumentoPersona getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarHistoricoPropiedades))
			return false;

		TipoEntradaConsultarHistoricoPropiedades other = (TipoEntradaConsultarHistoricoPropiedades)obj;

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
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())))
				&& (((this.convenio == null) && (other.getConvenio() == null))
				|| ((this.convenio != null) && this.convenio.equals(other.getConvenio())));
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

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		if(getConvenio() != null)
			_hashCode += getConvenio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
