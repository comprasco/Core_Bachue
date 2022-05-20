/**
 * TipoEntradaAcusarMensaje.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaAcusarMensaje.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class TipoEntradaAcusarMensaje implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5273049819538495549L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaAcusarMensaje.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1",
		        "tipoEntradaAcusarMensaje"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificador");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1", "identificador"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("guia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1", "guia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAcuse");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1", "fechaAcuse"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaEnvio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/notificador/acusarmensaje/v1", "fechaEnvio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad fecha acuse. */
	private java.util.Calendar fechaAcuse;

	/** Propiedad fecha envio. */
	private java.util.Calendar fechaEnvio;

	/** Propiedad guia. */
	private java.lang.String guia;

	/** Propiedad identificador. */
	private java.lang.String identificador;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada acusar mensaje.
	 */
	public TipoEntradaAcusarMensaje()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada acusar mensaje.
	 *
	 * @param identificador de identificador
	 * @param guia de guia
	 * @param fechaAcuse de fecha acuse
	 * @param fechaEnvio de fecha envio
	 */
	public TipoEntradaAcusarMensaje(
	    java.lang.String identificador, java.lang.String guia, java.util.Calendar fechaAcuse, java.util.Calendar fechaEnvio
	)
	{
		this.identificador     = identificador;
		this.guia              = guia;
		this.fechaAcuse        = fechaAcuse;
		this.fechaEnvio        = fechaEnvio;
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
	 * Sets the fechaAcuse value for this TipoEntradaAcusarMensaje.
	 *
	 * @param fechaAcuse de fecha acuse
	 */
	public void setFechaAcuse(java.util.Calendar fechaAcuse)
	{
		this.fechaAcuse = fechaAcuse;
	}

	/**
	 * Gets the fechaAcuse value for this TipoEntradaAcusarMensaje.
	 *
	 * @return fechaAcuse
	 */
	public java.util.Calendar getFechaAcuse()
	{
		return fechaAcuse;
	}

	/**
	 * Sets the fechaEnvio value for this TipoEntradaAcusarMensaje.
	 *
	 * @param fechaEnvio de fecha envio
	 */
	public void setFechaEnvio(java.util.Calendar fechaEnvio)
	{
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * Gets the fechaEnvio value for this TipoEntradaAcusarMensaje.
	 *
	 * @return fechaEnvio
	 */
	public java.util.Calendar getFechaEnvio()
	{
		return fechaEnvio;
	}

	/**
	 * Sets the guia value for this TipoEntradaAcusarMensaje.
	 *
	 * @param guia de guia
	 */
	public void setGuia(java.lang.String guia)
	{
		this.guia = guia;
	}

	/**
	 * Gets the guia value for this TipoEntradaAcusarMensaje.
	 *
	 * @return guia
	 */
	public java.lang.String getGuia()
	{
		return guia;
	}

	/**
	 * Sets the identificador value for this TipoEntradaAcusarMensaje.
	 *
	 * @param identificador de identificador
	 */
	public void setIdentificador(java.lang.String identificador)
	{
		this.identificador = identificador;
	}

	/**
	 * Gets the identificador value for this TipoEntradaAcusarMensaje.
	 *
	 * @return identificador
	 */
	public java.lang.String getIdentificador()
	{
		return identificador;
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
		if(!(obj instanceof TipoEntradaAcusarMensaje))
			return false;

		TipoEntradaAcusarMensaje other = (TipoEntradaAcusarMensaje)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.identificador == null) && (other.getIdentificador() == null))
				|| ((this.identificador != null) && this.identificador.equals(other.getIdentificador())))
				&& (((this.guia == null) && (other.getGuia() == null))
				|| ((this.guia != null) && this.guia.equals(other.getGuia())))
				&& (((this.fechaAcuse == null) && (other.getFechaAcuse() == null))
				|| ((this.fechaAcuse != null) && this.fechaAcuse.equals(other.getFechaAcuse())))
				&& (((this.fechaEnvio == null) && (other.getFechaEnvio() == null))
				|| ((this.fechaEnvio != null) && this.fechaEnvio.equals(other.getFechaEnvio())));
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

		if(getIdentificador() != null)
			_hashCode += getIdentificador().hashCode();

		if(getGuia() != null)
			_hashCode += getGuia().hashCode();

		if(getFechaAcuse() != null)
			_hashCode += getFechaAcuse().hashCode();

		if(getFechaEnvio() != null)
			_hashCode += getFechaEnvio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
