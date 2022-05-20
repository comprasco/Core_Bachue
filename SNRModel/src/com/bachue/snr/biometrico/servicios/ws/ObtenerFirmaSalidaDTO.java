/**
 * ObtenerFirmaSalidaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades ObtenerFirmaSalidaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class ObtenerFirmaSalidaDTO extends com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    ObtenerFirmaSalidaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "obtenerFirmaSalidaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("firma");
		elemField.setXmlName(new javax.xml.namespace.QName("", "firma"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idFirma");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idFirma"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad firma. */
	private java.lang.String firma;
	
	/** Propiedad id firma. */
	private java.lang.String idFirma;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto obtener firma salida DTO.
	 */
	public ObtenerFirmaSalidaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto obtener firma salida DTO.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param resultado de resultado
	 * @param firma de firma
	 * @param idFirma de id firma
	 */
	public ObtenerFirmaSalidaDTO(
	    java.lang.String codigo, java.lang.String mensaje, java.lang.String resultado, java.lang.String firma,
	    java.lang.String idFirma
	)
	{
		super(codigo, mensaje, resultado);
		this.firma       = firma;
		this.idFirma     = idFirma;
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
	 * Sets the firma value for this ObtenerFirmaSalidaDTO.
	 *
	 * @param firma de firma
	 */
	public void setFirma(java.lang.String firma)
	{
		this.firma = firma;
	}

	/**
	 * Gets the firma value for this ObtenerFirmaSalidaDTO.
	 *
	 * @return firma
	 */
	public java.lang.String getFirma()
	{
		return firma;
	}

	/**
	 * Sets the idFirma value for this ObtenerFirmaSalidaDTO.
	 *
	 * @param idFirma de id firma
	 */
	public void setIdFirma(java.lang.String idFirma)
	{
		this.idFirma = idFirma;
	}

	/**
	 * Gets the idFirma value for this ObtenerFirmaSalidaDTO.
	 *
	 * @return idFirma
	 */
	public java.lang.String getIdFirma()
	{
		return idFirma;
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
		if(!(obj instanceof ObtenerFirmaSalidaDTO))
			return false;

		ObtenerFirmaSalidaDTO other = (ObtenerFirmaSalidaDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj)
				&& (((this.firma == null) && (other.getFirma() == null))
				|| ((this.firma != null) && this.firma.equals(other.getFirma())))
				&& (((this.idFirma == null) && (other.getIdFirma() == null))
				|| ((this.idFirma != null) && this.idFirma.equals(other.getIdFirma())));
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

		if(getFirma() != null)
			_hashCode += getFirma().hashCode();

		if(getIdFirma() != null)
			_hashCode += getIdFirma().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
