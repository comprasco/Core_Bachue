/**
 * TipoSalidaGenerarSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1;



/**
 * The Class TipoSalidaGenerarSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaGenerarSolicitud implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2562451579940948090L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaGenerarSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoSalidaGenerarSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoEstadoSolicitud");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "codigoEstadoSolicitud"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionEstadoSolicitud");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "descripcionEstadoSolicitud"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensajeAlSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "mensajeAlSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The nir. */
	private java.lang.String NIR;

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The codigo estado solicitud. */
	private java.lang.String codigoEstadoSolicitud;

	/** The codigo mensaje. */
	private java.math.BigInteger codigoMensaje;

	/** The descripcion estado solicitud. */
	private java.lang.String descripcionEstadoSolicitud;

	/** The descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** The mensaje al solicitante. */
	private java.lang.String mensajeAlSolicitante;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo salida generar solicitud.
	 */
	public TipoSalidaGenerarSolicitud()
	{
	}

	/**
	 * Instantiates a new tipo salida generar solicitud.
	 *
	 * @param NIR the nir
	 * @param codigoEstadoSolicitud the codigo estado solicitud
	 * @param descripcionEstadoSolicitud the descripcion estado solicitud
	 * @param mensajeAlSolicitante the mensaje al solicitante
	 * @param codigoMensaje the codigo mensaje
	 * @param descripcionMensaje the descripcion mensaje
	 */
	public TipoSalidaGenerarSolicitud(
	    java.lang.String NIR, java.lang.String codigoEstadoSolicitud, java.lang.String descripcionEstadoSolicitud,
	    java.lang.String mensajeAlSolicitante, java.math.BigInteger codigoMensaje, java.lang.String descripcionMensaje
	)
	{
		this.NIR                            = NIR;
		this.codigoEstadoSolicitud          = codigoEstadoSolicitud;
		this.descripcionEstadoSolicitud     = descripcionEstadoSolicitud;
		this.mensajeAlSolicitante           = mensajeAlSolicitante;
		this.codigoMensaje                  = codigoMensaje;
		this.descripcionMensaje             = descripcionMensaje;
	}

	/**
	 * Gets the NIR value for this TipoSalidaGenerarSolicitud.
	 *
	 * @return NIR
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the NIR value for this TipoSalidaGenerarSolicitud.
	 *
	 * @param NIR the new nir
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the codigoEstadoSolicitud value for this TipoSalidaGenerarSolicitud.
	 *
	 * @return codigoEstadoSolicitud
	 */
	public java.lang.String getCodigoEstadoSolicitud()
	{
		return codigoEstadoSolicitud;
	}

	/**
	 * Sets the codigoEstadoSolicitud value for this TipoSalidaGenerarSolicitud.
	 *
	 * @param codigoEstadoSolicitud the new codigo estado solicitud
	 */
	public void setCodigoEstadoSolicitud(java.lang.String codigoEstadoSolicitud)
	{
		this.codigoEstadoSolicitud = codigoEstadoSolicitud;
	}

	/**
	 * Gets the descripcionEstadoSolicitud value for this TipoSalidaGenerarSolicitud.
	 *
	 * @return descripcionEstadoSolicitud
	 */
	public java.lang.String getDescripcionEstadoSolicitud()
	{
		return descripcionEstadoSolicitud;
	}

	/**
	 * Sets the descripcionEstadoSolicitud value for this TipoSalidaGenerarSolicitud.
	 *
	 * @param descripcionEstadoSolicitud the new descripcion estado solicitud
	 */
	public void setDescripcionEstadoSolicitud(java.lang.String descripcionEstadoSolicitud)
	{
		this.descripcionEstadoSolicitud = descripcionEstadoSolicitud;
	}

	/**
	 * Gets the mensajeAlSolicitante value for this TipoSalidaGenerarSolicitud.
	 *
	 * @return mensajeAlSolicitante
	 */
	public java.lang.String getMensajeAlSolicitante()
	{
		return mensajeAlSolicitante;
	}

	/**
	 * Sets the mensajeAlSolicitante value for this TipoSalidaGenerarSolicitud.
	 *
	 * @param mensajeAlSolicitante the new mensaje al solicitante
	 */
	public void setMensajeAlSolicitante(java.lang.String mensajeAlSolicitante)
	{
		this.mensajeAlSolicitante = mensajeAlSolicitante;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaGenerarSolicitud.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaGenerarSolicitud.
	 *
	 * @param codigoMensaje the new codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaGenerarSolicitud.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaGenerarSolicitud.
	 *
	 * @param descripcionMensaje the new descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaGenerarSolicitud))
			return false;

		TipoSalidaGenerarSolicitud other = (TipoSalidaGenerarSolicitud)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())))
				&& (((this.codigoEstadoSolicitud == null) && (other.getCodigoEstadoSolicitud() == null))
				|| ((this.codigoEstadoSolicitud != null)
				&& this.codigoEstadoSolicitud.equals(other.getCodigoEstadoSolicitud())))
				&& (((this.descripcionEstadoSolicitud == null) && (other.getDescripcionEstadoSolicitud() == null))
				|| ((this.descripcionEstadoSolicitud != null)
				&& this.descripcionEstadoSolicitud.equals(other.getDescripcionEstadoSolicitud())))
				&& (((this.mensajeAlSolicitante == null) && (other.getMensajeAlSolicitante() == null))
				|| ((this.mensajeAlSolicitante != null)
				&& this.mensajeAlSolicitante.equals(other.getMensajeAlSolicitante())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
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

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

		if(getCodigoEstadoSolicitud() != null)
			_hashCode += getCodigoEstadoSolicitud().hashCode();

		if(getDescripcionEstadoSolicitud() != null)
			_hashCode += getDescripcionEstadoSolicitud().hashCode();

		if(getMensajeAlSolicitante() != null)
			_hashCode += getMensajeAlSolicitante().hashCode();

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
