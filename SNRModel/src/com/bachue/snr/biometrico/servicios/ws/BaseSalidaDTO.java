/**
 * BaseSalidaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades BaseSalidaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class BaseSalidaDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    BaseSalidaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "baseSalidaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad codigo. */
	private java.lang.String codigo;
	
	/** Propiedad mensaje. */
	private java.lang.String mensaje;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto base salida DTO.
	 */
	public BaseSalidaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto base salida DTO.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 */
	public BaseSalidaDTO(java.lang.String codigo, java.lang.String mensaje)
	{
		this.codigo      = codigo;
		this.mensaje     = mensaje;
	}

	/**
	 * Sets the codigo value for this BaseSalidaDTO.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo value for this BaseSalidaDTO.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
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
	 * Sets the mensaje value for this BaseSalidaDTO.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mensaje value for this BaseSalidaDTO.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
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
		if(!(obj instanceof BaseSalidaDTO))
			return false;

		BaseSalidaDTO other = (BaseSalidaDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.mensaje == null) && (other.getMensaje() == null))
				|| ((this.mensaje != null) && this.mensaje.equals(other.getMensaje())));
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

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getMensaje() != null)
			_hashCode += getMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
