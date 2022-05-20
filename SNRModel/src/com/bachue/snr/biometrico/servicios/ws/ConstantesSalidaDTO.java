/**
 * ConstantesSalidaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades ConstantesSalidaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class ConstantesSalidaDTO extends com.bachue.snr.biometrico.servicios.ws.BaseSalidaDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    ConstantesSalidaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "constantesSalidaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("constante");
		elemField.setXmlName(new javax.xml.namespace.QName("", "constante"));
		elemField.setXmlType(
		    new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "constante")
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                   __equalsCalc   = null;
	
	/** Propiedad constante. */
	private com.bachue.snr.biometrico.servicios.ws.Constante[] constante;
	
	/** Propiedad hash code calc. */
	private boolean                                            __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto constantes salida DTO.
	 */
	public ConstantesSalidaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto constantes salida DTO.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param constante de constante
	 */
	public ConstantesSalidaDTO(
	    java.lang.String codigo, java.lang.String mensaje, com.bachue.snr.biometrico.servicios.ws.Constante[] constante
	)
	{
		super(codigo, mensaje);
		this.constante = constante;
	}

	/**
	 * Sets the constante value for this ConstantesSalidaDTO.
	 *
	 * @param constante de constante
	 */
	public void setConstante(com.bachue.snr.biometrico.servicios.ws.Constante[] constante)
	{
		this.constante = constante;
	}

	/**
	 * Cambia el valor de constante.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setConstante(int i, com.bachue.snr.biometrico.servicios.ws.Constante _value)
	{
		this.constante[i] = _value;
	}

	/**
	 * Gets the constante value for this ConstantesSalidaDTO.
	 *
	 * @return constante
	 */
	public com.bachue.snr.biometrico.servicios.ws.Constante[] getConstante()
	{
		return constante;
	}

	/**
	 * Retorna Objeto o variable de valor constante.
	 *
	 * @param i de i
	 * @return el valor de constante
	 */
	public com.bachue.snr.biometrico.servicios.ws.Constante getConstante(int i)
	{
		return this.constante[i];
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
		if(!(obj instanceof ConstantesSalidaDTO))
			return false;

		ConstantesSalidaDTO other = (ConstantesSalidaDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj)
				&& (((this.constante == null) && (other.getConstante() == null))
				|| ((this.constante != null) && java.util.Arrays.equals(this.constante, other.getConstante())));
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

		if(getConstante() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getConstante()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getConstante(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
