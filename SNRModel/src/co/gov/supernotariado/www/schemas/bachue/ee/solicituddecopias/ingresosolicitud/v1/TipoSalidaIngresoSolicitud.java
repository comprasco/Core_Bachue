/**
 * TipoSalidaIngresoSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaIngresoSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaIngresoSolicitud implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5096359310993463998L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaIngresoSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        "tipoSalidaIngresoSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idSolicitudCopias");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        "idSolicitudCopias"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "codigo"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        ">tipoSalidaIngresoSolicitud>codigo"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "mensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                   __equalsCalc =
		null;
	
	/** Propiedad codigo. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitudCodigo codigo;
	
	/** Propiedad id solicitud copias. */
	private java.lang.String idSolicitudCopias;
	
	/** Propiedad mensaje. */
	private java.lang.String mensaje;
	
	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida ingreso solicitud.
	 */
	public TipoSalidaIngresoSolicitud()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida ingreso solicitud.
	 *
	 * @param idSolicitudCopias de id solicitud copias
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 */
	public TipoSalidaIngresoSolicitud(
	    java.lang.String idSolicitudCopias,
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitudCodigo codigo,
	    java.lang.String mensaje
	)
	{
		this.idSolicitudCopias     = idSolicitudCopias;
		this.codigo                = codigo;
		this.mensaje               = mensaje;
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
	 * Sets the codigo value for this TipoSalidaIngresoSolicitud.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitudCodigo codigo
	)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo value for this TipoSalidaIngresoSolicitud.
	 *
	 * @return codigo
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitudCodigo getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the idSolicitudCopias value for this TipoSalidaIngresoSolicitud.
	 *
	 * @param idSolicitudCopias de id solicitud copias
	 */
	public void setIdSolicitudCopias(java.lang.String idSolicitudCopias)
	{
		this.idSolicitudCopias = idSolicitudCopias;
	}

	/**
	 * Gets the idSolicitudCopias value for this TipoSalidaIngresoSolicitud.
	 *
	 * @return idSolicitudCopias
	 */
	public java.lang.String getIdSolicitudCopias()
	{
		return idSolicitudCopias;
	}

	/**
	 * Sets the mensaje value for this TipoSalidaIngresoSolicitud.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mensaje value for this TipoSalidaIngresoSolicitud.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaIngresoSolicitud))
			return false;

		TipoSalidaIngresoSolicitud other = (TipoSalidaIngresoSolicitud)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idSolicitudCopias == null) && (other.getIdSolicitudCopias() == null))
				|| ((this.idSolicitudCopias != null) && this.idSolicitudCopias.equals(other.getIdSolicitudCopias())))
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.mensaje == null) && (other.getMensaje() == null))
				|| ((this.mensaje != null) && this.mensaje.equals(other.getMensaje())));
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

		if(getIdSolicitudCopias() != null)
			_hashCode += getIdSolicitudCopias().hashCode();

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getMensaje() != null)
			_hashCode += getMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
