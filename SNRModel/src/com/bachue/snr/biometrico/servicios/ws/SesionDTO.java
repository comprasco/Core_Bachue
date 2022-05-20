/**
 * SesionDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades SesionDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class SesionDTO extends com.bachue.snr.biometrico.servicios.ws.BaseDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    SesionDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName("http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "sesionDTO")
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(new javax.xml.namespace.QName("", "codigo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("resultado");
		elemField.setXmlName(new javax.xml.namespace.QName("", "resultado"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sesion");
		elemField.setXmlName(new javax.xml.namespace.QName("", "sesion"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object  __equalsCalc   = null;
	
	/** Propiedad codigo. */
	private java.lang.String  codigo;
	
	/** Propiedad mensaje. */
	private java.lang.String  mensaje;
	
	/** Propiedad resultado. */
	private java.lang.Boolean resultado;
	
	/** Propiedad sesion. */
	private java.lang.String  sesion;
	
	/** Propiedad hash code calc. */
	private boolean           __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto sesion DTO.
	 */
	public SesionDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto sesion DTO.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param resultado de resultado
	 * @param sesion de sesion
	 */
	public SesionDTO(
	    java.lang.String codigo, java.lang.String mensaje, java.lang.Boolean resultado, java.lang.String sesion
	)
	{
		this.codigo        = codigo;
		this.mensaje       = mensaje;
		this.resultado     = resultado;
		this.sesion        = sesion;
	}

	/**
	 * Sets the codigo value for this SesionDTO.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo value for this SesionDTO.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
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
	 * Sets the mensaje value for this SesionDTO.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mensaje value for this SesionDTO.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
	}

	/**
	 * Sets the resultado value for this SesionDTO.
	 *
	 * @param resultado de resultado
	 */
	public void setResultado(java.lang.Boolean resultado)
	{
		this.resultado = resultado;
	}

	/**
	 * Gets the resultado value for this SesionDTO.
	 *
	 * @return resultado
	 */
	public java.lang.Boolean getResultado()
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
	 * Sets the sesion value for this SesionDTO.
	 *
	 * @param sesion de sesion
	 */
	public void setSesion(java.lang.String sesion)
	{
		this.sesion = sesion;
	}

	/**
	 * Gets the sesion value for this SesionDTO.
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
		if(!(obj instanceof SesionDTO))
			return false;

		SesionDTO other = (SesionDTO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = super.equals(obj)
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.mensaje == null) && (other.getMensaje() == null))
				|| ((this.mensaje != null) && this.mensaje.equals(other.getMensaje())))
				&& (((this.resultado == null) && (other.getResultado() == null))
				|| ((this.resultado != null) && this.resultado.equals(other.getResultado())))
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

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getMensaje() != null)
			_hashCode += getMensaje().hashCode();

		if(getResultado() != null)
			_hashCode += getResultado().hashCode();

		if(getSesion() != null)
			_hashCode += getSesion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
