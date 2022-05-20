/**
 * TipoSalidaConsultarEstadoSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1;



/**
 * The Class TipoSalidaConsultarEstadoSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarEstadoSolicitud implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 177094505067146006L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarEstadoSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "tipoSalidaConsultarEstadoSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idEtapa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "idEtapa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreEtapa");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "nombreEtapa"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idMotivo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "idMotivo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMotivo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "descripcionMotivo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaFin");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "fechaFin"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
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

	/** The codigo mensaje. */
	private java.math.BigInteger codigoMensaje;

	/** The descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** The descripcion motivo. */
	private java.lang.String descripcionMotivo;

	/** The estado. */
	private java.lang.String estado;

	/** The fecha fin. */
	private java.util.Calendar fechaFin;

	/** The id etapa. */
	private java.lang.String idEtapa;

	/** The id motivo. */
	private java.lang.String idMotivo;

	/** The nombre etapa. */
	private java.lang.String nombreEtapa;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo salida consultar estado solicitud.
	 */
	public TipoSalidaConsultarEstadoSolicitud()
	{
	}

	/**
	 * Instantiates a new tipo salida consultar estado solicitud.
	 *
	 * @param NIR the nir
	 * @param idEtapa the id etapa
	 * @param nombreEtapa the nombre etapa
	 * @param idMotivo the id motivo
	 * @param descripcionMotivo the descripcion motivo
	 * @param estado the estado
	 * @param fechaFin the fecha fin
	 * @param codigoMensaje the codigo mensaje
	 * @param descripcionMensaje the descripcion mensaje
	 */
	public TipoSalidaConsultarEstadoSolicitud(
	    java.lang.String NIR, java.lang.String idEtapa, java.lang.String nombreEtapa, java.lang.String idMotivo,
	    java.lang.String descripcionMotivo, java.lang.String estado, java.util.Calendar fechaFin,
	    java.math.BigInteger codigoMensaje, java.lang.String descripcionMensaje
	)
	{
		this.NIR                    = NIR;
		this.idEtapa                = idEtapa;
		this.nombreEtapa            = nombreEtapa;
		this.idMotivo               = idMotivo;
		this.descripcionMotivo      = descripcionMotivo;
		this.estado                 = estado;
		this.fechaFin               = fechaFin;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the NIR value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return NIR
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the NIR value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param NIR the new nir
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the idEtapa value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return idEtapa
	 */
	public java.lang.String getIdEtapa()
	{
		return idEtapa;
	}

	/**
	 * Sets the idEtapa value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param idEtapa the new id etapa
	 */
	public void setIdEtapa(java.lang.String idEtapa)
	{
		this.idEtapa = idEtapa;
	}

	/**
	 * Gets the nombreEtapa value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return nombreEtapa
	 */
	public java.lang.String getNombreEtapa()
	{
		return nombreEtapa;
	}

	/**
	 * Sets the nombreEtapa value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param nombreEtapa the new nombre etapa
	 */
	public void setNombreEtapa(java.lang.String nombreEtapa)
	{
		this.nombreEtapa = nombreEtapa;
	}

	/**
	 * Gets the idMotivo value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return idMotivo
	 */
	public java.lang.String getIdMotivo()
	{
		return idMotivo;
	}

	/**
	 * Sets the idMotivo value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param idMotivo the new id motivo
	 */
	public void setIdMotivo(java.lang.String idMotivo)
	{
		this.idMotivo = idMotivo;
	}

	/**
	 * Gets the descripcionMotivo value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return descripcionMotivo
	 */
	public java.lang.String getDescripcionMotivo()
	{
		return descripcionMotivo;
	}

	/**
	 * Sets the descripcionMotivo value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param descripcionMotivo the new descripcion motivo
	 */
	public void setDescripcionMotivo(java.lang.String descripcionMotivo)
	{
		this.descripcionMotivo = descripcionMotivo;
	}

	/**
	 * Gets the estado value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return estado
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the fechaFin value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return fechaFin
	 */
	public java.util.Calendar getFechaFin()
	{
		return fechaFin;
	}

	/**
	 * Sets the fechaFin value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(java.util.Calendar fechaFin)
	{
		this.fechaFin = fechaFin;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @param codigoMensaje the new codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarEstadoSolicitud.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarEstadoSolicitud.
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
		if(!(obj instanceof TipoSalidaConsultarEstadoSolicitud))
			return false;

		TipoSalidaConsultarEstadoSolicitud other = (TipoSalidaConsultarEstadoSolicitud)obj;

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
				&& (((this.idEtapa == null) && (other.getIdEtapa() == null))
				|| ((this.idEtapa != null) && this.idEtapa.equals(other.getIdEtapa())))
				&& (((this.nombreEtapa == null) && (other.getNombreEtapa() == null))
				|| ((this.nombreEtapa != null) && this.nombreEtapa.equals(other.getNombreEtapa())))
				&& (((this.idMotivo == null) && (other.getIdMotivo() == null))
				|| ((this.idMotivo != null) && this.idMotivo.equals(other.getIdMotivo())))
				&& (((this.descripcionMotivo == null) && (other.getDescripcionMotivo() == null))
				|| ((this.descripcionMotivo != null) && this.descripcionMotivo.equals(other.getDescripcionMotivo())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())))
				&& (((this.fechaFin == null) && (other.getFechaFin() == null))
				|| ((this.fechaFin != null) && this.fechaFin.equals(other.getFechaFin())))
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

		if(getIdEtapa() != null)
			_hashCode += getIdEtapa().hashCode();

		if(getNombreEtapa() != null)
			_hashCode += getNombreEtapa().hashCode();

		if(getIdMotivo() != null)
			_hashCode += getIdMotivo().hashCode();

		if(getDescripcionMotivo() != null)
			_hashCode += getDescripcionMotivo().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		if(getFechaFin() != null)
			_hashCode += getFechaFin().hashCode();

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
