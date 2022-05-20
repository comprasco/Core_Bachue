/**
 * TipoEntradaNotificarDigitalizacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaNotificarDigitalizacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaNotificarDigitalizacion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3025570808036258842L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaNotificarDigitalizacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
		        "tipoEntradaNotificarDigitalizacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sistemaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
		        "sistemaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificadorTramite");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
		        "identificadorTramite"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/controldigitalizacion/notificardigitalizacion/v1",
		        "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc         = null;
	
	/** Propiedad estado. */
	private java.lang.String estado;
	
	/** Propiedad identificador tramite. */
	private java.lang.String identificadorTramite;
	
	/** Propiedad sistema origen. */
	private java.lang.String sistemaOrigen;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc       = false;

	/**
	 * Instancia un nuevo objeto tipo entrada notificar digitalizacion.
	 */
	public TipoEntradaNotificarDigitalizacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada notificar digitalizacion.
	 *
	 * @param sistemaOrigen de sistema origen
	 * @param identificadorTramite de identificador tramite
	 * @param estado de estado
	 */
	public TipoEntradaNotificarDigitalizacion(
	    java.lang.String sistemaOrigen, java.lang.String identificadorTramite, java.lang.String estado
	)
	{
		this.sistemaOrigen            = sistemaOrigen;
		this.identificadorTramite     = identificadorTramite;
		this.estado                   = estado;
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
	 * Sets the estado value for this TipoEntradaNotificarDigitalizacion.
	 *
	 * @param estado de estado
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the estado value for this TipoEntradaNotificarDigitalizacion.
	 *
	 * @return estado
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the identificadorTramite value for this TipoEntradaNotificarDigitalizacion.
	 *
	 * @param identificadorTramite de identificador tramite
	 */
	public void setIdentificadorTramite(java.lang.String identificadorTramite)
	{
		this.identificadorTramite = identificadorTramite;
	}

	/**
	 * Gets the identificadorTramite value for this TipoEntradaNotificarDigitalizacion.
	 *
	 * @return identificadorTramite
	 */
	public java.lang.String getIdentificadorTramite()
	{
		return identificadorTramite;
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
	 * Sets the sistemaOrigen value for this TipoEntradaNotificarDigitalizacion.
	 *
	 * @param sistemaOrigen de sistema origen
	 */
	public void setSistemaOrigen(java.lang.String sistemaOrigen)
	{
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * Gets the sistemaOrigen value for this TipoEntradaNotificarDigitalizacion.
	 *
	 * @return sistemaOrigen
	 */
	public java.lang.String getSistemaOrigen()
	{
		return sistemaOrigen;
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
		if(!(obj instanceof TipoEntradaNotificarDigitalizacion))
			return false;

		TipoEntradaNotificarDigitalizacion other = (TipoEntradaNotificarDigitalizacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.sistemaOrigen == null) && (other.getSistemaOrigen() == null))
				|| ((this.sistemaOrigen != null) && this.sistemaOrigen.equals(other.getSistemaOrigen())))
				&& (((this.identificadorTramite == null) && (other.getIdentificadorTramite() == null))
				|| ((this.identificadorTramite != null)
				&& this.identificadorTramite.equals(other.getIdentificadorTramite())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())));
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

		if(getSistemaOrigen() != null)
			_hashCode += getSistemaOrigen().hashCode();

		if(getIdentificadorTramite() != null)
			_hashCode += getIdentificadorTramite().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
