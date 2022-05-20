/**
 * RolTypeORU.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1;


/**
 * Clase que contiene todos las propiedades RolTypeORU.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class RolTypeORU implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5457239738344655121L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    RolTypeORU.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
		        "rolTypeORU"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
		        "codigoRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
		        "nombreRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad codigo rol. */
	private java.lang.String codigoRol;
	
	/** Propiedad nombre rol. */
	private java.lang.String nombreRol;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto rol type ORU.
	 */
	public RolTypeORU()
	{
	}

	/**
	 * Instancia un nuevo objeto rol type ORU.
	 *
	 * @param codigoRol de codigo rol
	 * @param nombreRol de nombre rol
	 */
	public RolTypeORU(java.lang.String codigoRol, java.lang.String nombreRol)
	{
		this.codigoRol     = codigoRol;
		this.nombreRol     = nombreRol;
	}

	/**
	 * Sets the codigoRol value for this RolTypeORU.
	 *
	 * @param codigoRol de codigo rol
	 */
	public void setCodigoRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Gets the codigoRol value for this RolTypeORU.
	 *
	 * @return codigoRol
	 */
	public java.lang.String getCodigoRol()
	{
		return codigoRol;
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
	 * Sets the nombreRol value for this RolTypeORU.
	 *
	 * @param nombreRol de nombre rol
	 */
	public void setNombreRol(java.lang.String nombreRol)
	{
		this.nombreRol = nombreRol;
	}

	/**
	 * Gets the nombreRol value for this RolTypeORU.
	 *
	 * @return nombreRol
	 */
	public java.lang.String getNombreRol()
	{
		return nombreRol;
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
		if(!(obj instanceof RolTypeORU))
			return false;

		RolTypeORU other = (RolTypeORU)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoRol == null) && (other.getCodigoRol() == null))
				|| ((this.codigoRol != null) && this.codigoRol.equals(other.getCodigoRol())))
				&& (((this.nombreRol == null) && (other.getNombreRol() == null))
				|| ((this.nombreRol != null) && this.nombreRol.equals(other.getNombreRol())));
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

		if(getCodigoRol() != null)
			_hashCode += getCodigoRol().hashCode();

		if(getNombreRol() != null)
			_hashCode += getNombreRol().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
