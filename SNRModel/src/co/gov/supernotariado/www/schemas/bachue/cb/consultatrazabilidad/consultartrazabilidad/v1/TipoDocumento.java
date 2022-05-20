/**
 * TipoDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1;


/**
 * Clase que contiene todos las propiedades TipoDocumento.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/09/2019
 */
public class TipoDocumento implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6239534570645619586L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDocumento.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoDocumento"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "fechaDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("oficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "oficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoOficina");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoOficina"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "departamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("municipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "municipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad departamento. */
	/* Departamento de donde pertenece el documento */
	private java.lang.String departamento;

	/** Propiedad fecha documento. */
	/* Fecha de creación del documento */
	private java.lang.String fechaDocumento;

	/** Propiedad municipio. */
	/* Municipio de donde pertenece el documento */
	private java.lang.String municipio;

	/** Propiedad numero documento. */
	/* Número de identificación del tipo de documento */
	private java.lang.String numeroDocumento;

	/** Propiedad oficina origen. */
	/* Oficina donde se encuentra asentado el tipo de
	 *                                 documento */
	private java.lang.String oficinaOrigen;

	/** Propiedad tipo. */
	/* Corresponde al tipo de documento. */
	private java.lang.String tipo;

	/** Propiedad tipo entidad. */
	/* Tipo de entidad de donde pertenece el
	 *                                 documento */
	private java.lang.String tipoEntidad;

	/** Propiedad tipo oficina. */
	/* Tipo de la oficina donde se encuentra asentado
	 *                                 el documento */
	private java.lang.String tipoOficina;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo documento.
	 */
	public TipoDocumento()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo documento.
	 *
	 * @param tipo correspondiente al valor de tipo
	 * @param numeroDocumento correspondiente al valor de numero documento
	 * @param fechaDocumento correspondiente al valor de fecha documento
	 * @param oficinaOrigen correspondiente al valor de oficina origen
	 * @param tipoOficina correspondiente al valor de tipo oficina
	 * @param departamento correspondiente al valor de departamento
	 * @param municipio correspondiente al valor de municipio
	 * @param tipoEntidad correspondiente al valor de tipo entidad
	 */
	public TipoDocumento(
	    java.lang.String tipo, java.lang.String numeroDocumento, java.lang.String fechaDocumento,
	    java.lang.String oficinaOrigen, java.lang.String tipoOficina, java.lang.String departamento,
	    java.lang.String municipio, java.lang.String tipoEntidad
	)
	{
		this.tipo                = tipo;
		this.numeroDocumento     = numeroDocumento;
		this.fechaDocumento      = fechaDocumento;
		this.oficinaOrigen       = oficinaOrigen;
		this.tipoOficina         = tipoOficina;
		this.departamento        = departamento;
		this.municipio           = municipio;
		this.tipoEntidad         = tipoEntidad;
	}

	/**
	 * Sets the departamento value for this TipoDocumento.
	 *
	 * @param departamento   * Departamento de donde pertenece el documento
	 */
	public void setDepartamento(java.lang.String departamento)
	{
		this.departamento = departamento;
	}

	/**
	 * Gets the departamento value for this TipoDocumento.
	 *
	 * @return departamento   * Departamento de donde pertenece el documento
	 */
	public java.lang.String getDepartamento()
	{
		return departamento;
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
	 * Sets the fechaDocumento value for this TipoDocumento.
	 *
	 * @param fechaDocumento   * Fecha de creación del documento
	 */
	public void setFechaDocumento(java.lang.String fechaDocumento)
	{
		this.fechaDocumento = fechaDocumento;
	}

	/**
	 * Gets the fechaDocumento value for this TipoDocumento.
	 *
	 * @return fechaDocumento   * Fecha de creación del documento
	 */
	public java.lang.String getFechaDocumento()
	{
		return fechaDocumento;
	}

	/**
	 * Sets the municipio value for this TipoDocumento.
	 *
	 * @param municipio   * Municipio de donde pertenece el documento
	 */
	public void setMunicipio(java.lang.String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * Gets the municipio value for this TipoDocumento.
	 *
	 * @return municipio   * Municipio de donde pertenece el documento
	 */
	public java.lang.String getMunicipio()
	{
		return municipio;
	}

	/**
	 * Sets the numeroDocumento value for this TipoDocumento.
	 *
	 * @param numeroDocumento   * Número de identificación del tipo de documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoDocumento.
	 *
	 * @return numeroDocumento   * Número de identificación del tipo de documento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Sets the oficinaOrigen value for this TipoDocumento.
	 *
	 * @param oficinaOrigen   * Oficina donde se encuentra asentado el tipo de
	     *                                 documento
	 */
	public void setOficinaOrigen(java.lang.String oficinaOrigen)
	{
		this.oficinaOrigen = oficinaOrigen;
	}

	/**
	 * Gets the oficinaOrigen value for this TipoDocumento.
	 *
	 * @return oficinaOrigen   * Oficina donde se encuentra asentado el tipo de
	     *                                 documento
	 */
	public java.lang.String getOficinaOrigen()
	{
		return oficinaOrigen;
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
	 * Sets the tipo value for this TipoDocumento.
	 *
	 * @param tipo   * Corresponde al tipo de documento.
	 */
	public void setTipo(java.lang.String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * Gets the tipo value for this TipoDocumento.
	 *
	 * @return tipo   * Corresponde al tipo de documento.
	 */
	public java.lang.String getTipo()
	{
		return tipo;
	}

	/**
	 * Sets the tipoEntidad value for this TipoDocumento.
	 *
	 * @param tipoEntidad   * Tipo de entidad de donde pertenece el
	     *                                 documento
	 */
	public void setTipoEntidad(java.lang.String tipoEntidad)
	{
		this.tipoEntidad = tipoEntidad;
	}

	/**
	 * Gets the tipoEntidad value for this TipoDocumento.
	 *
	 * @return tipoEntidad   * Tipo de entidad de donde pertenece el
	     *                                 documento
	 */
	public java.lang.String getTipoEntidad()
	{
		return tipoEntidad;
	}

	/**
	 * Sets the tipoOficina value for this TipoDocumento.
	 *
	 * @param tipoOficina   * Tipo de la oficina donde se encuentra asentado
	     *                                 el documento
	 */
	public void setTipoOficina(java.lang.String tipoOficina)
	{
		this.tipoOficina = tipoOficina;
	}

	/**
	 * Gets the tipoOficina value for this TipoDocumento.
	 *
	 * @return tipoOficina   * Tipo de la oficina donde se encuentra asentado
	     *                                 el documento
	 */
	public java.lang.String getTipoOficina()
	{
		return tipoOficina;
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
		if(!(obj instanceof TipoDocumento))
			return false;

		TipoDocumento other = (TipoDocumento)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipo == null) && (other.getTipo() == null))
				|| ((this.tipo != null) && this.tipo.equals(other.getTipo())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())))
				&& (((this.fechaDocumento == null) && (other.getFechaDocumento() == null))
				|| ((this.fechaDocumento != null) && this.fechaDocumento.equals(other.getFechaDocumento())))
				&& (((this.oficinaOrigen == null) && (other.getOficinaOrigen() == null))
				|| ((this.oficinaOrigen != null) && this.oficinaOrigen.equals(other.getOficinaOrigen())))
				&& (((this.tipoOficina == null) && (other.getTipoOficina() == null))
				|| ((this.tipoOficina != null) && this.tipoOficina.equals(other.getTipoOficina())))
				&& (((this.departamento == null) && (other.getDepartamento() == null))
				|| ((this.departamento != null) && this.departamento.equals(other.getDepartamento())))
				&& (((this.municipio == null) && (other.getMunicipio() == null))
				|| ((this.municipio != null) && this.municipio.equals(other.getMunicipio())))
				&& (((this.tipoEntidad == null) && (other.getTipoEntidad() == null))
				|| ((this.tipoEntidad != null) && this.tipoEntidad.equals(other.getTipoEntidad())));
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

		if(getTipo() != null)
			_hashCode += getTipo().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		if(getFechaDocumento() != null)
			_hashCode += getFechaDocumento().hashCode();

		if(getOficinaOrigen() != null)
			_hashCode += getOficinaOrigen().hashCode();

		if(getTipoOficina() != null)
			_hashCode += getTipoOficina().hashCode();

		if(getDepartamento() != null)
			_hashCode += getDepartamento().hashCode();

		if(getMunicipio() != null)
			_hashCode += getMunicipio().hashCode();

		if(getTipoEntidad() != null)
			_hashCode += getTipoEntidad().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
