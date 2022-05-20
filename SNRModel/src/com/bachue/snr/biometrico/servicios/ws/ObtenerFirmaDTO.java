/**
 * ObtenerFirmaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades ObtenerFirmaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class ObtenerFirmaDTO extends com.bachue.snr.biometrico.servicios.ws.BaseDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    ObtenerFirmaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirmaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idUsuario");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuario"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idfirma");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idfirma"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad id usuario. */
	private java.lang.String idUsuario;
	
	/** Propiedad idfirma. */
	private java.lang.String idfirma;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto obtener firma DTO.
	 */
	public ObtenerFirmaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto obtener firma DTO.
	 *
	 * @param idUsuario de id usuario
	 * @param idfirma de idfirma
	 */
	public ObtenerFirmaDTO(java.lang.String idUsuario, java.lang.String idfirma)
	{
		this.idUsuario     = idUsuario;
		this.idfirma       = idfirma;
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
	 * Sets the idUsuario value for this ObtenerFirmaDTO.
	 *
	 * @param idUsuario de id usuario
	 */
	public void setIdUsuario(java.lang.String idUsuario)
	{
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the idUsuario value for this ObtenerFirmaDTO.
	 *
	 * @return idUsuario
	 */
	public java.lang.String getIdUsuario()
	{
		return idUsuario;
	}

	/**
	 * Sets the idfirma value for this ObtenerFirmaDTO.
	 *
	 * @param idfirma de idfirma
	 */
	public void setIdfirma(java.lang.String idfirma)
	{
		this.idfirma = idfirma;
	}

	/**
	 * Gets the idfirma value for this ObtenerFirmaDTO.
	 *
	 * @return idfirma
	 */
	public java.lang.String getIdfirma()
	{
		return idfirma;
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
		if(!(obj instanceof ObtenerFirmaDTO))
			return false;

		ObtenerFirmaDTO other = (ObtenerFirmaDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj)
				&& (((this.idUsuario == null) && (other.getIdUsuario() == null))
				|| ((this.idUsuario != null) && this.idUsuario.equals(other.getIdUsuario())))
				&& (((this.idfirma == null) && (other.getIdfirma() == null))
				|| ((this.idfirma != null) && this.idfirma.equals(other.getIdfirma())));
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

		if(getIdUsuario() != null)
			_hashCode += getIdUsuario().hashCode();

		if(getIdfirma() != null)
			_hashCode += getIdfirma().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
