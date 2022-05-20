/**
 * TipoEntradaRegistrarReintentoServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarReintentoServicio.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
public class TipoEntradaRegistrarReintentoServicio implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2189212979872738485L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarReintentoServicio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
		        "tipoEntradaRegistrarReintentoServicio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codTransaccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
		        "codTransaccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("operacionRegistro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
		        "operacionRegistro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoRegistro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
		        "estadoRegistro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReintento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
		        "numeroReintento"
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/recepcionNotificacionBachue/registrarReintentoServicio/v1",
		        "fechaRegistro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod transaccion. */
	private java.lang.String codTransaccion;

	/** Propiedad estado registro. */
	private java.lang.String estadoRegistro;

	/** Propiedad fecha registro. */
	private java.lang.String fechaRegistro;

	/** Propiedad numero reintento. */
	private java.lang.String numeroReintento;

	/** Propiedad operacion registro. */
	private java.lang.String operacionRegistro;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar reintento servicio.
	 */
	public TipoEntradaRegistrarReintentoServicio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar reintento servicio.
	 *
	 * @param codTransaccion de cod transaccion
	 * @param operacionRegistro de operacion registro
	 * @param estadoRegistro de estado registro
	 * @param numeroReintento de numero reintento
	 * @param fechaRegistro de fecha registro
	 */
	public TipoEntradaRegistrarReintentoServicio(
	    java.lang.String codTransaccion, java.lang.String operacionRegistro, java.lang.String estadoRegistro,
	    java.lang.String numeroReintento, java.lang.String fechaRegistro
	)
	{
		this.codTransaccion        = codTransaccion;
		this.operacionRegistro     = operacionRegistro;
		this.estadoRegistro        = estadoRegistro;
		this.numeroReintento       = numeroReintento;
		this.fechaRegistro         = fechaRegistro;
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
	 * Sets the codTransaccion value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @param codTransaccion de cod transaccion
	 */
	public void setCodTransaccion(java.lang.String codTransaccion)
	{
		this.codTransaccion = codTransaccion;
	}

	/**
	 * Gets the codTransaccion value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @return codTransaccion
	 */
	public java.lang.String getCodTransaccion()
	{
		return codTransaccion;
	}

	/**
	 * Sets the estadoRegistro value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @param estadoRegistro de estado registro
	 */
	public void setEstadoRegistro(java.lang.String estadoRegistro)
	{
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * Gets the estadoRegistro value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @return estadoRegistro
	 */
	public java.lang.String getEstadoRegistro()
	{
		return estadoRegistro;
	}

	/**
	 * Sets the fechaRegistro value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @param fechaRegistro de fecha registro
	 */
	public void setFechaRegistro(java.lang.String fechaRegistro)
	{
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Gets the fechaRegistro value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @return fechaRegistro
	 */
	public java.lang.String getFechaRegistro()
	{
		return fechaRegistro;
	}

	/**
	 * Sets the numeroReintento value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @param numeroReintento de numero reintento
	 */
	public void setNumeroReintento(java.lang.String numeroReintento)
	{
		this.numeroReintento = numeroReintento;
	}

	/**
	 * Gets the numeroReintento value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @return numeroReintento
	 */
	public java.lang.String getNumeroReintento()
	{
		return numeroReintento;
	}

	/**
	 * Sets the operacionRegistro value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @param operacionRegistro de operacion registro
	 */
	public void setOperacionRegistro(java.lang.String operacionRegistro)
	{
		this.operacionRegistro = operacionRegistro;
	}

	/**
	 * Gets the operacionRegistro value for this TipoEntradaRegistrarReintentoServicio.
	 *
	 * @return operacionRegistro
	 */
	public java.lang.String getOperacionRegistro()
	{
		return operacionRegistro;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaRegistrarReintentoServicio))
			return false;

		TipoEntradaRegistrarReintentoServicio other = (TipoEntradaRegistrarReintentoServicio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codTransaccion == null) && (other.getCodTransaccion() == null))
				|| ((this.codTransaccion != null) && this.codTransaccion.equals(other.getCodTransaccion())))
				&& (((this.operacionRegistro == null) && (other.getOperacionRegistro() == null))
				|| ((this.operacionRegistro != null) && this.operacionRegistro.equals(other.getOperacionRegistro())))
				&& (((this.estadoRegistro == null) && (other.getEstadoRegistro() == null))
				|| ((this.estadoRegistro != null) && this.estadoRegistro.equals(other.getEstadoRegistro())))
				&& (((this.numeroReintento == null) && (other.getNumeroReintento() == null))
				|| ((this.numeroReintento != null) && this.numeroReintento.equals(other.getNumeroReintento())))
				&& (((this.fechaRegistro == null) && (other.getFechaRegistro() == null))
				|| ((this.fechaRegistro != null) && this.fechaRegistro.equals(other.getFechaRegistro())));
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

		if(getCodTransaccion() != null)
			_hashCode += getCodTransaccion().hashCode();

		if(getOperacionRegistro() != null)
			_hashCode += getOperacionRegistro().hashCode();

		if(getEstadoRegistro() != null)
			_hashCode += getEstadoRegistro().hashCode();

		if(getNumeroReintento() != null)
			_hashCode += getNumeroReintento().hashCode();

		if(getFechaRegistro() != null)
			_hashCode += getFechaRegistro().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
