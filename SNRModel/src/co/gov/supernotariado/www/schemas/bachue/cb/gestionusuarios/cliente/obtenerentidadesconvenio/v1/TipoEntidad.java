/**
 * TipoEntidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerentidadesconvenio.v1;

public class TipoEntidad implements java.io.Serializable
{
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

	private java.lang.Object     __equalsCalc       = null;
	private java.math.BigInteger codigoDepartamento;
	private java.lang.String     codigoEntidad;
	private java.math.BigInteger codigoMunicipio;
	private java.math.BigInteger codigoPais;
	private java.lang.String     nombreEntidad;
	private boolean              __hashCodeCalc     = false;

	public TipoEntidad()
	{
	}

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
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the codigoDepartamento value for this TipoEntidad.
	 *
	 * @param codigoDepartamento
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
	 * @param codigoEntidad
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
	 * @param codigoMunicipio
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
	 * @param codigoPais
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
	 * Sets the nombreEntidad value for this TipoEntidad.
	 *
	 * @param nombreEntidad
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
