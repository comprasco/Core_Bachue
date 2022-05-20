/**
 * TipoSalidaNotificarCambioCatastro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaNotificarCambioCatastro.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/03/2020
 */
public class TipoSalidaNotificarCambioCatastro implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4235175659130072572L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaNotificarCambioCatastro.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "tipoSalidaNotificarCambioCatastro"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoRegistro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "estadoRegistro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaRegistro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "fechaRegistro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "descripcionMensaje"
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

	/** Propiedad estado registro. */
	private java.lang.String estadoRegistro;

	/** Propiedad fecha registro. */
	private java.util.Calendar fechaRegistro;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida notificar cambio catastro.
	 */
	public TipoSalidaNotificarCambioCatastro()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida notificar cambio catastro.
	 *
	 * @param estadoRegistro de estado registro
	 * @param fechaRegistro de fecha registro
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaNotificarCambioCatastro(
	    java.lang.String estadoRegistro, java.util.Calendar fechaRegistro, java.math.BigInteger codMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.estadoRegistro         = estadoRegistro;
		this.fechaRegistro          = fechaRegistro;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
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

	/**
	 * Sets the codMensaje value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @param codMensaje de cod mensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the estadoRegistro value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @param estadoRegistro de estado registro
	 */
	public void setEstadoRegistro(java.lang.String estadoRegistro)
	{
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * Gets the estadoRegistro value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @return estadoRegistro
	 */
	public java.lang.String getEstadoRegistro()
	{
		return estadoRegistro;
	}

	/**
	 * Sets the fechaRegistro value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @param fechaRegistro de fecha registro
	 */
	public void setFechaRegistro(java.util.Calendar fechaRegistro)
	{
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Gets the fechaRegistro value for this TipoSalidaNotificarCambioCatastro.
	 *
	 * @return fechaRegistro
	 */
	public java.util.Calendar getFechaRegistro()
	{
		return fechaRegistro;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaNotificarCambioCatastro))
			return false;

		TipoSalidaNotificarCambioCatastro other = (TipoSalidaNotificarCambioCatastro)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.estadoRegistro == null) && (other.getEstadoRegistro() == null))
				|| ((this.estadoRegistro != null) && this.estadoRegistro.equals(other.getEstadoRegistro())))
				&& (((this.fechaRegistro == null) && (other.getFechaRegistro() == null))
				|| ((this.fechaRegistro != null) && this.fechaRegistro.equals(other.getFechaRegistro())))
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

		if(getEstadoRegistro() != null)
			_hashCode += getEstadoRegistro().hashCode();

		if(getFechaRegistro() != null)
			_hashCode += getFechaRegistro().hashCode();

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
