/**
 * BorrarHuellasDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades BorrarHuellasDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class BorrarHuellasDTO extends com.bachue.snr.biometrico.servicios.ws.BaseDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    BorrarHuellasDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "borrarHuellasDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idUsuario");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuario"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idUsuarioCreacion");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuarioCreacion"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc      = null;
	
	/** Propiedad id usuario. */
	private java.lang.String idUsuario;
	
	/** Propiedad id usuario creacion. */
	private java.lang.String idUsuarioCreacion;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc    = false;

	/**
	 * Instancia un nuevo objeto borrar huellas DTO.
	 */
	public BorrarHuellasDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto borrar huellas DTO.
	 *
	 * @param idUsuario de id usuario
	 * @param idUsuarioCreacion de id usuario creacion
	 */
	public BorrarHuellasDTO(java.lang.String idUsuario, java.lang.String idUsuarioCreacion)
	{
		this.idUsuario             = idUsuario;
		this.idUsuarioCreacion     = idUsuarioCreacion;
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
	 * Sets the idUsuario value for this BorrarHuellasDTO.
	 *
	 * @param idUsuario de id usuario
	 */
	public void setIdUsuario(java.lang.String idUsuario)
	{
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the idUsuario value for this BorrarHuellasDTO.
	 *
	 * @return idUsuario
	 */
	public java.lang.String getIdUsuario()
	{
		return idUsuario;
	}

	/**
	 * Sets the idUsuarioCreacion value for this BorrarHuellasDTO.
	 *
	 * @param idUsuarioCreacion de id usuario creacion
	 */
	public void setIdUsuarioCreacion(java.lang.String idUsuarioCreacion)
	{
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	/**
	 * Gets the idUsuarioCreacion value for this BorrarHuellasDTO.
	 *
	 * @return idUsuarioCreacion
	 */
	public java.lang.String getIdUsuarioCreacion()
	{
		return idUsuarioCreacion;
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
		if(!(obj instanceof BorrarHuellasDTO))
			return false;

		BorrarHuellasDTO other = (BorrarHuellasDTO)obj;

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
				&& (((this.idUsuarioCreacion == null) && (other.getIdUsuarioCreacion() == null))
				|| ((this.idUsuarioCreacion != null) && this.idUsuarioCreacion.equals(other.getIdUsuarioCreacion())));
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

		if(getIdUsuarioCreacion() != null)
			_hashCode += getIdUsuarioCreacion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
