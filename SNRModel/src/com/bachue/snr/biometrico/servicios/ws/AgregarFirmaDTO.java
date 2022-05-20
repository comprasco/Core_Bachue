/**
 * AgregarFirmaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.bachue.snr.biometrico.servicios.ws;


/**
 * Clase que contiene todos las propiedades AgregarFirmaDTO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class AgregarFirmaDTO extends com.bachue.snr.biometrico.servicios.ws.BaseDTO implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    AgregarFirmaDTO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "http://ws.servicios.corebachue.bachue.supernotariado.gov.co/", "agregarFirmaDTO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("firma");
		elemField.setXmlName(new javax.xml.namespace.QName("", "firma"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idTramite");
		elemField.setXmlName(new javax.xml.namespace.QName("", "idTramite"));
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
		elemField.setFieldName("numeroDocCiudadano");
		elemField.setXmlName(new javax.xml.namespace.QName("", "numeroDocCiudadano"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocCiudadano");
		elemField.setXmlName(new javax.xml.namespace.QName("", "tipoDocCiudadano"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc       = null;
	
	/** Propiedad firma. */
	private java.lang.String firma;
	
	/** Propiedad id tramite. */
	private java.lang.String idTramite;
	
	/** Propiedad id usuario. */
	private java.lang.String idUsuario;
	
	/** Propiedad tipo doc ciudadano. */
	private java.lang.String tipoDocCiudadano;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc     = false;
	
	/** Propiedad numero doc ciudadano. */
	private long             numeroDocCiudadano;

	/**
	 * Instancia un nuevo objeto agregar firma DTO.
	 */
	public AgregarFirmaDTO()
	{
	}

	/**
	 * Instancia un nuevo objeto agregar firma DTO.
	 *
	 * @param firma de firma
	 * @param idTramite de id tramite
	 * @param idUsuario de id usuario
	 * @param numeroDocCiudadano de numero doc ciudadano
	 * @param tipoDocCiudadano de tipo doc ciudadano
	 */
	public AgregarFirmaDTO(
	    java.lang.String firma, java.lang.String idTramite, java.lang.String idUsuario, long numeroDocCiudadano,
	    java.lang.String tipoDocCiudadano
	)
	{
		this.firma                              = firma;
		this.idTramite                          = idTramite;
		this.idUsuario                          = idUsuario;
		this.numeroDocCiudadano                 = numeroDocCiudadano;
		this.tipoDocCiudadano                   = tipoDocCiudadano;
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
	 * Sets the firma value for this AgregarFirmaDTO.
	 *
	 * @param firma de firma
	 */
	public void setFirma(java.lang.String firma)
	{
		this.firma = firma;
	}

	/**
	 * Gets the firma value for this AgregarFirmaDTO.
	 *
	 * @return firma
	 */
	public java.lang.String getFirma()
	{
		return firma;
	}

	/**
	 * Sets the idTramite value for this AgregarFirmaDTO.
	 *
	 * @param idTramite de id tramite
	 */
	public void setIdTramite(java.lang.String idTramite)
	{
		this.idTramite = idTramite;
	}

	/**
	 * Gets the idTramite value for this AgregarFirmaDTO.
	 *
	 * @return idTramite
	 */
	public java.lang.String getIdTramite()
	{
		return idTramite;
	}

	/**
	 * Sets the idUsuario value for this AgregarFirmaDTO.
	 *
	 * @param idUsuario de id usuario
	 */
	public void setIdUsuario(java.lang.String idUsuario)
	{
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the idUsuario value for this AgregarFirmaDTO.
	 *
	 * @return idUsuario
	 */
	public java.lang.String getIdUsuario()
	{
		return idUsuario;
	}

	/**
	 * Sets the numeroDocCiudadano value for this AgregarFirmaDTO.
	 *
	 * @param numeroDocCiudadano de numero doc ciudadano
	 */
	public void setNumeroDocCiudadano(long numeroDocCiudadano)
	{
		this.numeroDocCiudadano = numeroDocCiudadano;
	}

	/**
	 * Gets the numeroDocCiudadano value for this AgregarFirmaDTO.
	 *
	 * @return numeroDocCiudadano
	 */
	public long getNumeroDocCiudadano()
	{
		return numeroDocCiudadano;
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
	 * Sets the tipoDocCiudadano value for this AgregarFirmaDTO.
	 *
	 * @param tipoDocCiudadano de tipo doc ciudadano
	 */
	public void setTipoDocCiudadano(java.lang.String tipoDocCiudadano)
	{
		this.tipoDocCiudadano = tipoDocCiudadano;
	}

	/**
	 * Gets the tipoDocCiudadano value for this AgregarFirmaDTO.
	 *
	 * @return tipoDocCiudadano
	 */
	public java.lang.String getTipoDocCiudadano()
	{
		return tipoDocCiudadano;
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
		if(!(obj instanceof AgregarFirmaDTO))
			return false;

		AgregarFirmaDTO other = (AgregarFirmaDTO)obj;

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
				&& (((this.idTramite == null) && (other.getIdTramite() == null))
				|| ((this.idTramite != null) && this.idTramite.equals(other.getIdTramite())))
				&& (((this.idUsuario == null) && (other.getIdUsuario() == null))
				|| ((this.idUsuario != null) && this.idUsuario.equals(other.getIdUsuario())))
				&& (this.numeroDocCiudadano == other.getNumeroDocCiudadano())
				&& (((this.tipoDocCiudadano == null) && (other.getTipoDocCiudadano() == null))
				|| ((this.tipoDocCiudadano != null) && this.tipoDocCiudadano.equals(other.getTipoDocCiudadano())));
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

		if(getIdTramite() != null)
			_hashCode += getIdTramite().hashCode();

		if(getIdUsuario() != null)
			_hashCode += getIdUsuario().hashCode();

		_hashCode += new Long(getNumeroDocCiudadano()).hashCode();

		if(getTipoDocCiudadano() != null)
			_hashCode += getTipoDocCiudadano().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
