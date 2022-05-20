/**
 * ClaveDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades ClaveDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class ClaveDTO extends com.bachue.snr.biometrico.servicios.ws.BaseDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    ClaveDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "claveDTO")
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("clave");
		elemField.setXmlName(new javax.xml.namespace.QName("", "clave"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idUsuario");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuario"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sesion");
		elemField.setXmlName(new javax.xml.namespace.QName("", "sesion"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad clave. */
	private java.lang.String clave;
	
	/** Propiedad id usuario. */
	private java.lang.String idUsuario;
	
	/** Propiedad sesion. */
	private java.lang.String sesion;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto clave DTO.
	 */
	public ClaveDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto clave DTO.
	 *
	 * @param clave de clave
	 * @param idUsuario de id usuario
	 * @param sesion de sesion
	 */
	public ClaveDTO(java.lang.String clave, java.lang.String idUsuario, java.lang.String sesion)
	{
		this.clave         = clave;
		this.idUsuario     = idUsuario;
		this.sesion        = sesion;
	}

	/**
	 * Sets the clave value for this ClaveDTO.
	 *
	 * @param clave de clave
	 */
	public void setClave(java.lang.String clave)
	{
		this.clave = clave;
	}

	/**
	 * Gets the clave value for this ClaveDTO.
	 *
	 * @return clave
	 */
	public java.lang.String getClave()
	{
		return clave;
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
	 * Sets the idUsuario value for this ClaveDTO.
	 *
	 * @param idUsuario de id usuario
	 */
	public void setIdUsuario(java.lang.String idUsuario)
	{
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the idUsuario value for this ClaveDTO.
	 *
	 * @return idUsuario
	 */
	public java.lang.String getIdUsuario()
	{
		return idUsuario;
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
	 * Sets the sesion value for this ClaveDTO.
	 *
	 * @param sesion de sesion
	 */
	public void setSesion(java.lang.String sesion)
	{
		this.sesion = sesion;
	}

	/**
	 * Gets the sesion value for this ClaveDTO.
	 *
	 * @return sesion
	 */
	public java.lang.String getSesion()
	{
		return sesion;
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
		if(!(obj instanceof ClaveDTO))
			return false;

		ClaveDTO other = (ClaveDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj)
				&& (((this.clave == null) && (other.getClave() == null))
				|| ((this.clave != null) && this.clave.equals(other.getClave())))
				&& (((this.idUsuario == null) && (other.getIdUsuario() == null))
				|| ((this.idUsuario != null) && this.idUsuario.equals(other.getIdUsuario())))
				&& (((this.sesion == null) && (other.getSesion() == null))
				|| ((this.sesion != null) && this.sesion.equals(other.getSesion())));
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

		if(getClave() != null)
			_hashCode += getClave().hashCode();

		if(getIdUsuario() != null)
			_hashCode += getIdUsuario().hashCode();

		if(getSesion() != null)
			_hashCode += getSesion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
