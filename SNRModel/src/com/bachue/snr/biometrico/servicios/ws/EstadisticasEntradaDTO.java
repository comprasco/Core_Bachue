/**
 * EstadisticasEntradaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades EstadisticasEntradaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class EstadisticasEntradaDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    EstadisticasEntradaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "estadisticasEntradaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idEntidad");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idEntidad"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipo");
		elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad id entidad. */
	private java.lang.String idEntidad;
	
	/** Propiedad tipo. */
	private java.lang.String tipo;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto estadisticas entrada DTO.
	 */
	public EstadisticasEntradaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto estadisticas entrada DTO.
	 *
	 * @param idEntidad de id entidad
	 * @param tipo de tipo
	 */
	public EstadisticasEntradaDTO(java.lang.String idEntidad, java.lang.String tipo)
	{
		this.idEntidad     = idEntidad;
		this.tipo          = tipo;
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
	 * Sets the idEntidad value for this EstadisticasEntradaDTO.
	 *
	 * @param idEntidad de id entidad
	 */
	public void setIdEntidad(java.lang.String idEntidad)
	{
		this.idEntidad = idEntidad;
	}

	/**
	 * Gets the idEntidad value for this EstadisticasEntradaDTO.
	 *
	 * @return idEntidad
	 */
	public java.lang.String getIdEntidad()
	{
		return idEntidad;
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
	 * Sets the tipo value for this EstadisticasEntradaDTO.
	 *
	 * @param tipo de tipo
	 */
	public void setTipo(java.lang.String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * Gets the tipo value for this EstadisticasEntradaDTO.
	 *
	 * @return tipo
	 */
	public java.lang.String getTipo()
	{
		return tipo;
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
		if(!(obj instanceof EstadisticasEntradaDTO))
			return false;

		EstadisticasEntradaDTO other = (EstadisticasEntradaDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idEntidad == null) && (other.getIdEntidad() == null))
				|| ((this.idEntidad != null) && this.idEntidad.equals(other.getIdEntidad())))
				&& (((this.tipo == null) && (other.getTipo() == null))
				|| ((this.tipo != null) && this.tipo.equals(other.getTipo())));
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

		if(getIdEntidad() != null)
			_hashCode += getIdEntidad().hashCode();

		if(getTipo() != null)
			_hashCode += getTipo().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
