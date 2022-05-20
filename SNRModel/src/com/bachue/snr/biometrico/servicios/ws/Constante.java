/**
 * Constante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades Constante.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class Constante implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    Constante.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "constante")
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("caracter");
		elemField.setXmlName(new javax.xml.namespace.QName("", "caracter"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idConstante");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idConstante"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad caracter. */
	private java.lang.String caracter;
	
	/** Propiedad id constante. */
	private java.lang.String idConstante;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto constante.
	 */
	public Constante()
	{
	}

	/**
	 * Instancia un nuevo objeto constante.
	 *
	 * @param caracter de caracter
	 * @param idConstante de id constante
	 */
	public Constante(java.lang.String caracter, java.lang.String idConstante)
	{
		this.caracter        = caracter;
		this.idConstante     = idConstante;
	}

	/**
	 * Sets the caracter value for this Constante.
	 *
	 * @param caracter de caracter
	 */
	public void setCaracter(java.lang.String caracter)
	{
		this.caracter = caracter;
	}

	/**
	 * Gets the caracter value for this Constante.
	 *
	 * @return caracter
	 */
	public java.lang.String getCaracter()
	{
		return caracter;
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
	 * Sets the idConstante value for this Constante.
	 *
	 * @param idConstante de id constante
	 */
	public void setIdConstante(java.lang.String idConstante)
	{
		this.idConstante = idConstante;
	}

	/**
	 * Gets the idConstante value for this Constante.
	 *
	 * @return idConstante
	 */
	public java.lang.String getIdConstante()
	{
		return idConstante;
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
		if(!(obj instanceof Constante))
			return false;

		Constante other = (Constante)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.caracter == null) && (other.getCaracter() == null))
				|| ((this.caracter != null) && this.caracter.equals(other.getCaracter())))
				&& (((this.idConstante == null) && (other.getIdConstante() == null))
				|| ((this.idConstante != null) && this.idConstante.equals(other.getIdConstante())));
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

		if(getCaracter() != null)
			_hashCode += getCaracter().hashCode();

		if(getIdConstante() != null)
			_hashCode += getIdConstante().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
