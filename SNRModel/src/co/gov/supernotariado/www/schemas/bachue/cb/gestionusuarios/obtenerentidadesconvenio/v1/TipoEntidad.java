/**
 * TipoEntidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerentidadesconvenio.v1;


/**
 * Clase que contiene todos las propiedades TipoEntidad.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntidad implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5793221926880595754L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntidad.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
		        "tipoEntidad"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
		        "codigoEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
		        "nombreEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoPais");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
		        "codigoPais"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
		        "codigoDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerentidadesconvenio/v1",
		        "codigoMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc       = null;
	
	/** Propiedad codigo departamento. */
	private java.math.BigInteger codigoDepartamento;
	
	/** Propiedad codigo entidad. */
	private java.lang.String     codigoEntidad;
	
	/** Propiedad codigo municipio. */
	private java.math.BigInteger codigoMunicipio;
	
	/** Propiedad codigo pais. */
	private java.math.BigInteger codigoPais;
	
	/** Propiedad nombre entidad. */
	private java.lang.String     nombreEntidad;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo entidad.
	 */
	public TipoEntidad()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entidad.
	 *
	 * @param codigoEntidad de codigo entidad
	 * @param nombreEntidad de nombre entidad
	 * @param codigoPais de codigo pais
	 * @param codigoDepartamento de codigo departamento
	 * @param codigoMunicipio de codigo municipio
	 */
	public TipoEntidad(
	    java.lang.String codigoEntidad, java.lang.String nombreEntidad, java.math.BigInteger codigoPais,
	    java.math.BigInteger codigoDepartamento, java.math.BigInteger codigoMunicipio
	)
	{
		this.codigoEntidad          = codigoEntidad;
		this.nombreEntidad          = nombreEntidad;
		this.codigoPais             = codigoPais;
		this.codigoDepartamento     = codigoDepartamento;
		this.codigoMunicipio        = codigoMunicipio;
	}

	/**
	 * Sets the codigoDepartamento value for this TipoEntidad.
	 *
	 * @param codigoDepartamento de codigo departamento
	 */
	public void setCodigoDepartamento(java.math.BigInteger codigoDepartamento)
	{
		this.codigoDepartamento = codigoDepartamento;
	}

	/**
	 * Gets the codigoDepartamento value for this TipoEntidad.
	 *
	 * @return codigoDepartamento
	 */
	public java.math.BigInteger getCodigoDepartamento()
	{
		return codigoDepartamento;
	}

	/**
	 * Sets the codigoEntidad value for this TipoEntidad.
	 *
	 * @param codigoEntidad de codigo entidad
	 */
	public void setCodigoEntidad(java.lang.String codigoEntidad)
	{
		this.codigoEntidad = codigoEntidad;
	}

	/**
	 * Gets the codigoEntidad value for this TipoEntidad.
	 *
	 * @return codigoEntidad
	 */
	public java.lang.String getCodigoEntidad()
	{
		return codigoEntidad;
	}

	/**
	 * Sets the codigoMunicipio value for this TipoEntidad.
	 *
	 * @param codigoMunicipio de codigo municipio
	 */
	public void setCodigoMunicipio(java.math.BigInteger codigoMunicipio)
	{
		this.codigoMunicipio = codigoMunicipio;
	}

	/**
	 * Gets the codigoMunicipio value for this TipoEntidad.
	 *
	 * @return codigoMunicipio
	 */
	public java.math.BigInteger getCodigoMunicipio()
	{
		return codigoMunicipio;
	}

	/**
	 * Sets the codigoPais value for this TipoEntidad.
	 *
	 * @param codigoPais de codigo pais
	 */
	public void setCodigoPais(java.math.BigInteger codigoPais)
	{
		this.codigoPais = codigoPais;
	}

	/**
	 * Gets the codigoPais value for this TipoEntidad.
	 *
	 * @return codigoPais
	 */
	public java.math.BigInteger getCodigoPais()
	{
		return codigoPais;
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
	 * Sets the nombreEntidad value for this TipoEntidad.
	 *
	 * @param nombreEntidad de nombre entidad
	 */
	public void setNombreEntidad(java.lang.String nombreEntidad)
	{
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * Gets the nombreEntidad value for this TipoEntidad.
	 *
	 * @return nombreEntidad
	 */
	public java.lang.String getNombreEntidad()
	{
		return nombreEntidad;
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
		if(!(obj instanceof TipoEntidad))
			return false;

		TipoEntidad other = (TipoEntidad)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoEntidad == null) && (other.getCodigoEntidad() == null))
				|| ((this.codigoEntidad != null) && this.codigoEntidad.equals(other.getCodigoEntidad())))
				&& (((this.nombreEntidad == null) && (other.getNombreEntidad() == null))
				|| ((this.nombreEntidad != null) && this.nombreEntidad.equals(other.getNombreEntidad())))
				&& (((this.codigoPais == null) && (other.getCodigoPais() == null))
				|| ((this.codigoPais != null) && this.codigoPais.equals(other.getCodigoPais())))
				&& (((this.codigoDepartamento == null) && (other.getCodigoDepartamento() == null))
				|| ((this.codigoDepartamento != null) && this.codigoDepartamento.equals(other.getCodigoDepartamento())))
				&& (((this.codigoMunicipio == null) && (other.getCodigoMunicipio() == null))
				|| ((this.codigoMunicipio != null) && this.codigoMunicipio.equals(other.getCodigoMunicipio())));
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

		if(getCodigoEntidad() != null)
			_hashCode += getCodigoEntidad().hashCode();

		if(getNombreEntidad() != null)
			_hashCode += getNombreEntidad().hashCode();

		if(getCodigoPais() != null)
			_hashCode += getCodigoPais().hashCode();

		if(getCodigoDepartamento() != null)
			_hashCode += getCodigoDepartamento().hashCode();

		if(getCodigoMunicipio() != null)
			_hashCode += getCodigoMunicipio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
