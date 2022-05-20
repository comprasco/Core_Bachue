/**
 * TipoOrip.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerorips.v1;


/**
 * Clase que contiene todos las propiedades TipoOrip.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoOrip implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7543550441939789026L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoOrip.class, true
		);
	

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1", "tipoOrip"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoORIP");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1", "codigoORIP"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreORIP");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1", "nombreORIP"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1",
		        "codigoDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1",
		        "nombreDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1", "codigoMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerorips/v1", "nombreMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc       = null;
	
	/** Propiedad codigo departamento. */
	private java.math.BigInteger codigoDepartamento;
	
	/** Propiedad codigo municipio. */
	private java.math.BigInteger codigoMunicipio;
	
	/** Propiedad codigo ORIP. */
	private java.lang.String     codigoORIP;
	
	/** Propiedad nombre departamento. */
	private java.lang.String     nombreDepartamento;
	
	/** Propiedad nombre municipio. */
	private java.lang.String     nombreMunicipio;
	
	/** Propiedad nombre ORIP. */
	private java.lang.String     nombreORIP;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo orip.
	 */
	public TipoOrip()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo orip.
	 *
	 * @param codigoORIP de codigo ORIP
	 * @param nombreORIP de nombre ORIP
	 * @param codigoDepartamento de codigo departamento
	 * @param nombreDepartamento de nombre departamento
	 * @param codigoMunicipio de codigo municipio
	 * @param nombreMunicipio de nombre municipio
	 */
	public TipoOrip(
	    java.lang.String codigoORIP, java.lang.String nombreORIP, java.math.BigInteger codigoDepartamento,
	    java.lang.String nombreDepartamento, java.math.BigInteger codigoMunicipio, java.lang.String nombreMunicipio
	)
	{
		this.codigoORIP             = codigoORIP;
		this.nombreORIP             = nombreORIP;
		this.codigoDepartamento     = codigoDepartamento;
		this.nombreDepartamento     = nombreDepartamento;
		this.codigoMunicipio        = codigoMunicipio;
		this.nombreMunicipio        = nombreMunicipio;
	}

	/**
	 * Sets the codigoDepartamento value for this TipoOrip.
	 *
	 * @param codigoDepartamento de codigo departamento
	 */
	public void setCodigoDepartamento(java.math.BigInteger codigoDepartamento)
	{
		this.codigoDepartamento = codigoDepartamento;
	}

	/**
	 * Gets the codigoDepartamento value for this TipoOrip.
	 *
	 * @return codigoDepartamento
	 */
	public java.math.BigInteger getCodigoDepartamento()
	{
		return codigoDepartamento;
	}

	/**
	 * Sets the codigoMunicipio value for this TipoOrip.
	 *
	 * @param codigoMunicipio de codigo municipio
	 */
	public void setCodigoMunicipio(java.math.BigInteger codigoMunicipio)
	{
		this.codigoMunicipio = codigoMunicipio;
	}

	/**
	 * Gets the codigoMunicipio value for this TipoOrip.
	 *
	 * @return codigoMunicipio
	 */
	public java.math.BigInteger getCodigoMunicipio()
	{
		return codigoMunicipio;
	}

	/**
	 * Sets the codigoORIP value for this TipoOrip.
	 *
	 * @param codigoORIP de codigo ORIP
	 */
	public void setCodigoORIP(java.lang.String codigoORIP)
	{
		this.codigoORIP = codigoORIP;
	}

	/**
	 * Gets the codigoORIP value for this TipoOrip.
	 *
	 * @return codigoORIP
	 */
	public java.lang.String getCodigoORIP()
	{
		return codigoORIP;
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
	 * Sets the nombreDepartamento value for this TipoOrip.
	 *
	 * @param nombreDepartamento de nombre departamento
	 */
	public void setNombreDepartamento(java.lang.String nombreDepartamento)
	{
		this.nombreDepartamento = nombreDepartamento;
	}

	/**
	 * Gets the nombreDepartamento value for this TipoOrip.
	 *
	 * @return nombreDepartamento
	 */
	public java.lang.String getNombreDepartamento()
	{
		return nombreDepartamento;
	}

	/**
	 * Sets the nombreMunicipio value for this TipoOrip.
	 *
	 * @param nombreMunicipio de nombre municipio
	 */
	public void setNombreMunicipio(java.lang.String nombreMunicipio)
	{
		this.nombreMunicipio = nombreMunicipio;
	}

	/**
	 * Gets the nombreMunicipio value for this TipoOrip.
	 *
	 * @return nombreMunicipio
	 */
	public java.lang.String getNombreMunicipio()
	{
		return nombreMunicipio;
	}

	/**
	 * Sets the nombreORIP value for this TipoOrip.
	 *
	 * @param nombreORIP de nombre ORIP
	 */
	public void setNombreORIP(java.lang.String nombreORIP)
	{
		this.nombreORIP = nombreORIP;
	}

	/**
	 * Gets the nombreORIP value for this TipoOrip.
	 *
	 * @return nombreORIP
	 */
	public java.lang.String getNombreORIP()
	{
		return nombreORIP;
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
		if(!(obj instanceof TipoOrip))
			return false;

		TipoOrip other = (TipoOrip)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoORIP == null) && (other.getCodigoORIP() == null))
				|| ((this.codigoORIP != null) && this.codigoORIP.equals(other.getCodigoORIP())))
				&& (((this.nombreORIP == null) && (other.getNombreORIP() == null))
				|| ((this.nombreORIP != null) && this.nombreORIP.equals(other.getNombreORIP())))
				&& (((this.codigoDepartamento == null) && (other.getCodigoDepartamento() == null))
				|| ((this.codigoDepartamento != null) && this.codigoDepartamento.equals(other.getCodigoDepartamento())))
				&& (((this.nombreDepartamento == null) && (other.getNombreDepartamento() == null))
				|| ((this.nombreDepartamento != null) && this.nombreDepartamento.equals(other.getNombreDepartamento())))
				&& (((this.codigoMunicipio == null) && (other.getCodigoMunicipio() == null))
				|| ((this.codigoMunicipio != null) && this.codigoMunicipio.equals(other.getCodigoMunicipio())))
				&& (((this.nombreMunicipio == null) && (other.getNombreMunicipio() == null))
				|| ((this.nombreMunicipio != null) && this.nombreMunicipio.equals(other.getNombreMunicipio())));
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

		if(getCodigoORIP() != null)
			_hashCode += getCodigoORIP().hashCode();

		if(getNombreORIP() != null)
			_hashCode += getNombreORIP().hashCode();

		if(getCodigoDepartamento() != null)
			_hashCode += getCodigoDepartamento().hashCode();

		if(getNombreDepartamento() != null)
			_hashCode += getNombreDepartamento().hashCode();

		if(getCodigoMunicipio() != null)
			_hashCode += getCodigoMunicipio().hashCode();

		if(getNombreMunicipio() != null)
			_hashCode += getNombreMunicipio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
