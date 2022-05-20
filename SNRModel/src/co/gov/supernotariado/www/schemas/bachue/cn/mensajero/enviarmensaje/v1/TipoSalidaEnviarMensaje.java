/**
 * TipoSalidaEnviarMensaje.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaEnviarMensaje.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/03/2020
 */
public class TipoSalidaEnviarMensaje implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5586240633628347240L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaEnviarMensaje.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "tipoSalidaEnviarMensaje"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaRecepcion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "fechaRecepcion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificadorGenerado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "identificadorGenerado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod mensaje. */
	private java.math.BigInteger codMensaje;

	/** Propiedad descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** Propiedad fecha recepcion. */
	private java.util.Calendar fechaRecepcion;

	/** Propiedad identificador generado. */
	private java.lang.String identificadorGenerado;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida enviar mensaje.
	 */
	public TipoSalidaEnviarMensaje()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida enviar mensaje.
	 *
	 * @param fechaRecepcion de fecha recepcion
	 * @param identificadorGenerado de identificador generado
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaEnviarMensaje(
	    java.util.Calendar fechaRecepcion, java.lang.String identificadorGenerado, java.math.BigInteger codMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.fechaRecepcion            = fechaRecepcion;
		this.identificadorGenerado     = identificadorGenerado;
		this.codMensaje                = codMensaje;
		this.descripcionMensaje        = descripcionMensaje;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaEnviarMensaje.
	 *
	 * @param codMensaje de cod mensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaEnviarMensaje.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaEnviarMensaje.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaEnviarMensaje.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
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
	 * Sets the fechaRecepcion value for this TipoSalidaEnviarMensaje.
	 *
	 * @param fechaRecepcion de fecha recepcion
	 */
	public void setFechaRecepcion(java.util.Calendar fechaRecepcion)
	{
		this.fechaRecepcion = fechaRecepcion;
	}

	/**
	 * Gets the fechaRecepcion value for this TipoSalidaEnviarMensaje.
	 *
	 * @return fechaRecepcion
	 */
	public java.util.Calendar getFechaRecepcion()
	{
		return fechaRecepcion;
	}

	/**
	 * Sets the identificadorGenerado value for this TipoSalidaEnviarMensaje.
	 *
	 * @param identificadorGenerado de identificador generado
	 */
	public void setIdentificadorGenerado(java.lang.String identificadorGenerado)
	{
		this.identificadorGenerado = identificadorGenerado;
	}

	/**
	 * Gets the identificadorGenerado value for this TipoSalidaEnviarMensaje.
	 *
	 * @return identificadorGenerado
	 */
	public java.lang.String getIdentificadorGenerado()
	{
		return identificadorGenerado;
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
		if(!(obj instanceof TipoSalidaEnviarMensaje))
			return false;

		TipoSalidaEnviarMensaje other = (TipoSalidaEnviarMensaje)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.fechaRecepcion == null) && (other.getFechaRecepcion() == null))
				|| ((this.fechaRecepcion != null) && this.fechaRecepcion.equals(other.getFechaRecepcion())))
				&& (((this.identificadorGenerado == null) && (other.getIdentificadorGenerado() == null))
				|| ((this.identificadorGenerado != null)
				&& this.identificadorGenerado.equals(other.getIdentificadorGenerado())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getFechaRecepcion() != null)
			_hashCode += getFechaRecepcion().hashCode();

		if(getIdentificadorGenerado() != null)
			_hashCode += getIdentificadorGenerado().hashCode();

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
