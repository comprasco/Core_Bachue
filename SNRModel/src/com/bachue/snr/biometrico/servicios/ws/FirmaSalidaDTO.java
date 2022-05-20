/**
 * FirmaSalidaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades FirmaSalidaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class FirmaSalidaDTO extends com.bachue.snr.biometrico.servicios.ws.BaseSalidaDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    FirmaSalidaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "firmaSalidaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("resultado");
		elemField.setXmlName(new javax.xml.namespace.QName("", "resultado"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad resultado. */
	private java.lang.String resultado;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto firma salida DTO.
	 */
	public FirmaSalidaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto firma salida DTO.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param resultado de resultado
	 */
	public FirmaSalidaDTO(java.lang.String codigo, java.lang.String mensaje, java.lang.String resultado)
	{
		super(codigo, mensaje);
		this.resultado = resultado;
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
	 * Sets the resultado value for this FirmaSalidaDTO.
	 *
	 * @param resultado de resultado
	 */
	public void setResultado(java.lang.String resultado)
	{
		this.resultado = resultado;
	}

	/**
	 * Gets the resultado value for this FirmaSalidaDTO.
	 *
	 * @return resultado
	 */
	public java.lang.String getResultado()
	{
		return resultado;
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
		if(!(obj instanceof FirmaSalidaDTO))
			return false;

		FirmaSalidaDTO other = (FirmaSalidaDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj)
				&& (((this.resultado == null) && (other.getResultado() == null))
				|| ((this.resultado != null) && this.resultado.equals(other.getResultado())));
		__equalsCalc     = null;

		return _equals;
	}

	/** {@inheritdoc} */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = super.hashCode();

		if(getResultado() != null)
			_hashCode += getResultado().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
