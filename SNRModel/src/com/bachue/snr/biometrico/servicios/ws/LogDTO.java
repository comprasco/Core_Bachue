/**
 * LogDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades LogDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class LogDTO extends com.bachue.snr.biometrico.servicios.ws.BaseDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    LogDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "logDTO")
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("detalle");
		elemField.setXmlName(new javax.xml.namespace.QName("", "detalle"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("evento");
		elemField.setXmlName(new javax.xml.namespace.QName("", "evento"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idEntidad");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idEntidad"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idUsuario");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idUsuario"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad detalle. */
	private java.lang.String detalle;
	
	/** Propiedad evento. */
	private java.lang.String evento;
	
	/** Propiedad id entidad. */
	private java.lang.String idEntidad;
	
	/** Propiedad id usuario. */
	private java.lang.String idUsuario;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto log DTO.
	 */
	public LogDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto log DTO.
	 *
	 * @param detalle de detalle
	 * @param evento de evento
	 * @param idEntidad de id entidad
	 * @param idUsuario de id usuario
	 */
	public LogDTO(
	    java.lang.String detalle, java.lang.String evento, java.lang.String idEntidad, java.lang.String idUsuario
	)
	{
		this.detalle       = detalle;
		this.evento        = evento;
		this.idEntidad     = idEntidad;
		this.idUsuario     = idUsuario;
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
	 * Sets the detalle value for this LogDTO.
	 *
	 * @param detalle de detalle
	 */
	public void setDetalle(java.lang.String detalle)
	{
		this.detalle = detalle;
	}

	/**
	 * Gets the detalle value for this LogDTO.
	 *
	 * @return detalle
	 */
	public java.lang.String getDetalle()
	{
		return detalle;
	}

	/**
	 * Sets the evento value for this LogDTO.
	 *
	 * @param evento de evento
	 */
	public void setEvento(java.lang.String evento)
	{
		this.evento = evento;
	}

	/**
	 * Gets the evento value for this LogDTO.
	 *
	 * @return evento
	 */
	public java.lang.String getEvento()
	{
		return evento;
	}

	/**
	 * Sets the idEntidad value for this LogDTO.
	 *
	 * @param idEntidad de id entidad
	 */
	public void setIdEntidad(java.lang.String idEntidad)
	{
		this.idEntidad = idEntidad;
	}

	/**
	 * Gets the idEntidad value for this LogDTO.
	 *
	 * @return idEntidad
	 */
	public java.lang.String getIdEntidad()
	{
		return idEntidad;
	}

	/**
	 * Sets the idUsuario value for this LogDTO.
	 *
	 * @param idUsuario de id usuario
	 */
	public void setIdUsuario(java.lang.String idUsuario)
	{
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the idUsuario value for this LogDTO.
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
		if(!(obj instanceof LogDTO))
			return false;

		LogDTO other = (LogDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj)
				&& (((this.detalle == null) && (other.getDetalle() == null))
				|| ((this.detalle != null) && this.detalle.equals(other.getDetalle())))
				&& (((this.evento == null) && (other.getEvento() == null))
				|| ((this.evento != null) && this.evento.equals(other.getEvento())))
				&& (((this.idEntidad == null) && (other.getIdEntidad() == null))
				|| ((this.idEntidad != null) && this.idEntidad.equals(other.getIdEntidad())))
				&& (((this.idUsuario == null) && (other.getIdUsuario() == null))
				|| ((this.idUsuario != null) && this.idUsuario.equals(other.getIdUsuario())));
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

		if(getDetalle() != null)
			_hashCode += getDetalle().hashCode();

		if(getEvento() != null)
			_hashCode += getEvento().hashCode();

		if(getIdEntidad() != null)
			_hashCode += getIdEntidad().hashCode();

		if(getIdUsuario() != null)
			_hashCode += getIdUsuario().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
