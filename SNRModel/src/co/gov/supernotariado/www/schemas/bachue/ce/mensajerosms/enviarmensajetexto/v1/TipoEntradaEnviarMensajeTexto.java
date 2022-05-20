/**
 * TipoEntradaEnviarMensajeTexto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ce.mensajerosms.enviarmensajetexto.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaEnviarMensajeTexto.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class TipoEntradaEnviarMensajeTexto implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6263154590926573008L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaEnviarMensajeTexto.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/mensajerosms/enviarmensajetexto/v1",
		        "tipoEntradaEnviarMensajeTexto"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroTelefonoMovil");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/mensajerosms/enviarmensajetexto/v1",
		        "numeroTelefonoMovil"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("contenidoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/mensajerosms/enviarmensajetexto/v1",
		        "contenidoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad contenido mensaje. */
	private java.lang.String contenidoMensaje;

	/** Propiedad numero telefono movil. */
	private java.lang.String numeroTelefonoMovil;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada enviar mensaje texto.
	 */
	public TipoEntradaEnviarMensajeTexto()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada enviar mensaje texto.
	 *
	 * @param numeroTelefonoMovil de numero telefono movil
	 * @param contenidoMensaje de contenido mensaje
	 */
	public TipoEntradaEnviarMensajeTexto(java.lang.String numeroTelefonoMovil, java.lang.String contenidoMensaje)
	{
		this.numeroTelefonoMovil     = numeroTelefonoMovil;
		this.contenidoMensaje        = contenidoMensaje;
	}

	/**
	 * Sets the contenidoMensaje value for this TipoEntradaEnviarMensajeTexto.
	 *
	 * @param contenidoMensaje de contenido mensaje
	 */
	public void setContenidoMensaje(java.lang.String contenidoMensaje)
	{
		this.contenidoMensaje = contenidoMensaje;
	}

	/**
	 * Gets the contenidoMensaje value for this TipoEntradaEnviarMensajeTexto.
	 *
	 * @return contenidoMensaje
	 */
	public java.lang.String getContenidoMensaje()
	{
		return contenidoMensaje;
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
	 * Sets the numeroTelefonoMovil value for this TipoEntradaEnviarMensajeTexto.
	 *
	 * @param numeroTelefonoMovil de numero telefono movil
	 */
	public void setNumeroTelefonoMovil(java.lang.String numeroTelefonoMovil)
	{
		this.numeroTelefonoMovil = numeroTelefonoMovil;
	}

	/**
	 * Gets the numeroTelefonoMovil value for this TipoEntradaEnviarMensajeTexto.
	 *
	 * @return numeroTelefonoMovil
	 */
	public java.lang.String getNumeroTelefonoMovil()
	{
		return numeroTelefonoMovil;
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
		if(!(obj instanceof TipoEntradaEnviarMensajeTexto))
			return false;

		TipoEntradaEnviarMensajeTexto other = (TipoEntradaEnviarMensajeTexto)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numeroTelefonoMovil == null) && (other.getNumeroTelefonoMovil() == null))
				|| ((this.numeroTelefonoMovil != null)
				&& this.numeroTelefonoMovil.equals(other.getNumeroTelefonoMovil())))
				&& (((this.contenidoMensaje == null) && (other.getContenidoMensaje() == null))
				|| ((this.contenidoMensaje != null) && this.contenidoMensaje.equals(other.getContenidoMensaje())));
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

		if(getNumeroTelefonoMovil() != null)
			_hashCode += getNumeroTelefonoMovil().hashCode();

		if(getContenidoMensaje() != null)
			_hashCode += getContenidoMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
