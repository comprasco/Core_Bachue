/**
 * TiposAlertas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1;


/**
 * Clase que contiene todos las propiedades TiposAlertas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TiposAlertas implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6713558697826752442L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TiposAlertas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "tiposAlertas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificadorAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "identificadorAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroMatricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "numeroMatricula"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "direccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaVigencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "fechaVigencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "departamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("municipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "municipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1", "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreOrip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "nombreOrip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultaralerta/v1",
		        "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc        = null;
	
	/** Propiedad departamento. */
	private java.lang.String departamento;
	
	/** Propiedad direccion. */
	private java.lang.String direccion;
	
	/** Propiedad estado. */
	private java.lang.String estado;
	
	/** Propiedad fecha vigencia. */
	private java.lang.String fechaVigencia;
	
	/** Propiedad identificador alerta. */
	private java.lang.String identificadorAlerta;
	
	/** Propiedad municipio. */
	private java.lang.String municipio;
	
	/** Propiedad nombre orip. */
	private java.lang.String nombreOrip;
	
	/** Propiedad numero matricula. */
	private java.lang.String numeroMatricula;
	
	/** Propiedad orip. */
	private java.lang.String orip;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc      = false;

	/**
	 * Instancia un nuevo objeto tipos alertas.
	 */
	public TiposAlertas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipos alertas.
	 *
	 * @param identificadorAlerta de identificador alerta
	 * @param numeroMatricula de numero matricula
	 * @param direccion de direccion
	 * @param fechaVigencia de fecha vigencia
	 * @param departamento de departamento
	 * @param municipio de municipio
	 * @param orip de orip
	 * @param nombreOrip de nombre orip
	 * @param estado de estado
	 */
	public TiposAlertas(
	    java.lang.String identificadorAlerta, java.lang.String numeroMatricula, java.lang.String direccion,
	    java.lang.String fechaVigencia, java.lang.String departamento, java.lang.String municipio, java.lang.String orip,
	    java.lang.String nombreOrip, java.lang.String estado
	)
	{
		this.identificadorAlerta     = identificadorAlerta;
		this.numeroMatricula         = numeroMatricula;
		this.direccion               = direccion;
		this.fechaVigencia           = fechaVigencia;
		this.departamento            = departamento;
		this.municipio               = municipio;
		this.orip                    = orip;
		this.nombreOrip              = nombreOrip;
		this.estado                  = estado;
	}

	/**
	 * Sets the departamento value for this TiposAlertas.
	 *
	 * @param departamento de departamento
	 */
	public void setDepartamento(java.lang.String departamento)
	{
		this.departamento = departamento;
	}

	/**
	 * Gets the departamento value for this TiposAlertas.
	 *
	 * @return departamento
	 */
	public java.lang.String getDepartamento()
	{
		return departamento;
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
	 * Sets the direccion value for this TiposAlertas.
	 *
	 * @param direccion de direccion
	 */
	public void setDireccion(java.lang.String direccion)
	{
		this.direccion = direccion;
	}

	/**
	 * Gets the direccion value for this TiposAlertas.
	 *
	 * @return direccion
	 */
	public java.lang.String getDireccion()
	{
		return direccion;
	}

	/**
	 * Sets the estado value for this TiposAlertas.
	 *
	 * @param estado de estado
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the estado value for this TiposAlertas.
	 *
	 * @return estado
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the fechaVigencia value for this TiposAlertas.
	 *
	 * @param fechaVigencia de fecha vigencia
	 */
	public void setFechaVigencia(java.lang.String fechaVigencia)
	{
		this.fechaVigencia = fechaVigencia;
	}

	/**
	 * Gets the fechaVigencia value for this TiposAlertas.
	 *
	 * @return fechaVigencia
	 */
	public java.lang.String getFechaVigencia()
	{
		return fechaVigencia;
	}

	/**
	 * Sets the identificadorAlerta value for this TiposAlertas.
	 *
	 * @param identificadorAlerta de identificador alerta
	 */
	public void setIdentificadorAlerta(java.lang.String identificadorAlerta)
	{
		this.identificadorAlerta = identificadorAlerta;
	}

	/**
	 * Gets the identificadorAlerta value for this TiposAlertas.
	 *
	 * @return identificadorAlerta
	 */
	public java.lang.String getIdentificadorAlerta()
	{
		return identificadorAlerta;
	}

	/**
	 * Sets the municipio value for this TiposAlertas.
	 *
	 * @param municipio de municipio
	 */
	public void setMunicipio(java.lang.String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * Gets the municipio value for this TiposAlertas.
	 *
	 * @return municipio
	 */
	public java.lang.String getMunicipio()
	{
		return municipio;
	}

	/**
	 * Sets the nombreOrip value for this TiposAlertas.
	 *
	 * @param nombreOrip de nombre orip
	 */
	public void setNombreOrip(java.lang.String nombreOrip)
	{
		this.nombreOrip = nombreOrip;
	}

	/**
	 * Gets the nombreOrip value for this TiposAlertas.
	 *
	 * @return nombreOrip
	 */
	public java.lang.String getNombreOrip()
	{
		return nombreOrip;
	}

	/**
	 * Sets the numeroMatricula value for this TiposAlertas.
	 *
	 * @param numeroMatricula de numero matricula
	 */
	public void setNumeroMatricula(java.lang.String numeroMatricula)
	{
		this.numeroMatricula = numeroMatricula;
	}

	/**
	 * Gets the numeroMatricula value for this TiposAlertas.
	 *
	 * @return numeroMatricula
	 */
	public java.lang.String getNumeroMatricula()
	{
		return numeroMatricula;
	}

	/**
	 * Sets the orip value for this TiposAlertas.
	 *
	 * @param orip de orip
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
	}

	/**
	 * Gets the orip value for this TiposAlertas.
	 *
	 * @return orip
	 */
	public java.lang.String getOrip()
	{
		return orip;
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
		if(!(obj instanceof TiposAlertas))
			return false;

		TiposAlertas other = (TiposAlertas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.identificadorAlerta == null) && (other.getIdentificadorAlerta() == null))
				|| ((this.identificadorAlerta != null)
				&& this.identificadorAlerta.equals(other.getIdentificadorAlerta())))
				&& (((this.numeroMatricula == null) && (other.getNumeroMatricula() == null))
				|| ((this.numeroMatricula != null) && this.numeroMatricula.equals(other.getNumeroMatricula())))
				&& (((this.direccion == null) && (other.getDireccion() == null))
				|| ((this.direccion != null) && this.direccion.equals(other.getDireccion())))
				&& (((this.fechaVigencia == null) && (other.getFechaVigencia() == null))
				|| ((this.fechaVigencia != null) && this.fechaVigencia.equals(other.getFechaVigencia())))
				&& (((this.departamento == null) && (other.getDepartamento() == null))
				|| ((this.departamento != null) && this.departamento.equals(other.getDepartamento())))
				&& (((this.municipio == null) && (other.getMunicipio() == null))
				|| ((this.municipio != null) && this.municipio.equals(other.getMunicipio())))
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.nombreOrip == null) && (other.getNombreOrip() == null))
				|| ((this.nombreOrip != null) && this.nombreOrip.equals(other.getNombreOrip())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())));
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

		if(getIdentificadorAlerta() != null)
			_hashCode += getIdentificadorAlerta().hashCode();

		if(getNumeroMatricula() != null)
			_hashCode += getNumeroMatricula().hashCode();

		if(getDireccion() != null)
			_hashCode += getDireccion().hashCode();

		if(getFechaVigencia() != null)
			_hashCode += getFechaVigencia().hashCode();

		if(getDepartamento() != null)
			_hashCode += getDepartamento().hashCode();

		if(getMunicipio() != null)
			_hashCode += getMunicipio().hashCode();

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getNombreOrip() != null)
			_hashCode += getNombreOrip().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
