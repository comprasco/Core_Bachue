/**
 * EstadisticasSalidaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades EstadisticasSalidaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class EstadisticasSalidaDTO extends com.bachue.snr.biometrico.servicios.ws.BaseSalidaDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    EstadisticasSalidaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "estadisticasSalidaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("contador");
		elemField.setXmlName(new javax.xml.namespace.QName("", "contador"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;
	
	/** Propiedad contador. */
	private int              contador;

	/**
	 * Instancia un nuevo objeto estadisticas salida DTO.
	 */
	public EstadisticasSalidaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto estadisticas salida DTO.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param contador de contador
	 */
	public EstadisticasSalidaDTO(java.lang.String codigo, java.lang.String mensaje, int contador)
	{
		super(codigo, mensaje);
		this.contador                       = contador;
	}

	/**
	 * Sets the contador value for this EstadisticasSalidaDTO.
	 *
	 * @param contador de contador
	 */
	public void setContador(int contador)
	{
		this.contador = contador;
	}

	/**
	 * Gets the contador value for this EstadisticasSalidaDTO.
	 *
	 * @return contador
	 */
	public int getContador()
	{
		return contador;
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
		if(!(obj instanceof EstadisticasSalidaDTO))
			return false;

		EstadisticasSalidaDTO other = (EstadisticasSalidaDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj) && (this.contador == other.getContador());
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
		_hashCode += getContador();
		__hashCodeCalc = false;

		return _hashCode;
	}
}
