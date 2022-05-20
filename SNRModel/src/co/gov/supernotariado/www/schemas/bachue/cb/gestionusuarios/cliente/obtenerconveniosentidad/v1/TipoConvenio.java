/**
 * TipoConvenio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerconveniosentidad.v1;

public class TipoConvenio implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoConvenio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "tipoConvenio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoConvenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "codigoConvenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionConvenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "descripcionConvenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaInicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "fechaInicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaFinalizacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "fechaFinalizacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc        = null;
	private java.lang.String codigoConvenio;
	private java.lang.String descripcionConvenio;
	private java.lang.String fechaFinalizacion;
	private java.lang.String fechaInicio;
	private boolean          __hashCodeCalc      = false;

	public TipoConvenio()
	{
	}

	public TipoConvenio(
	    java.lang.String codigoConvenio, java.lang.String descripcionConvenio, java.lang.String fechaInicio,
	    java.lang.String fechaFinalizacion
	)
	{
		this.codigoConvenio          = codigoConvenio;
		this.descripcionConvenio     = descripcionConvenio;
		this.fechaInicio             = fechaInicio;
		this.fechaFinalizacion       = fechaFinalizacion;
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the codigoConvenio value for this TipoConvenio.
	 *
	 * @param codigoConvenio
	 */
	public void setCodigoConvenio(java.lang.String codigoConvenio)
	{
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * Gets the codigoConvenio value for this TipoConvenio.
	 *
	 * @return codigoConvenio
	 */
	public java.lang.String getCodigoConvenio()
	{
		return codigoConvenio;
	}

	/**
	 * Sets the descripcionConvenio value for this TipoConvenio.
	 *
	 * @param descripcionConvenio
	 */
	public void setDescripcionConvenio(java.lang.String descripcionConvenio)
	{
		this.descripcionConvenio = descripcionConvenio;
	}

	/**
	 * Gets the descripcionConvenio value for this TipoConvenio.
	 *
	 * @return descripcionConvenio
	 */
	public java.lang.String getDescripcionConvenio()
	{
		return descripcionConvenio;
	}

	/**
	 * Sets the fechaFinalizacion value for this TipoConvenio.
	 *
	 * @param fechaFinalizacion
	 */
	public void setFechaFinalizacion(java.lang.String fechaFinalizacion)
	{
		this.fechaFinalizacion = fechaFinalizacion;
	}

	/**
	 * Gets the fechaFinalizacion value for this TipoConvenio.
	 *
	 * @return fechaFinalizacion
	 */
	public java.lang.String getFechaFinalizacion()
	{
		return fechaFinalizacion;
	}

	/**
	 * Sets the fechaInicio value for this TipoConvenio.
	 *
	 * @param fechaInicio
	 */
	public void setFechaInicio(java.lang.String fechaInicio)
	{
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the fechaInicio value for this TipoConvenio.
	 *
	 * @return fechaInicio
	 */
	public java.lang.String getFechaInicio()
	{
		return fechaInicio;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoConvenio))
			return false;

		TipoConvenio other = (TipoConvenio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoConvenio == null) && (other.getCodigoConvenio() == null))
				|| ((this.codigoConvenio != null) && this.codigoConvenio.equals(other.getCodigoConvenio())))
				&& (((this.descripcionConvenio == null) && (other.getDescripcionConvenio() == null))
				|| ((this.descripcionConvenio != null)
				&& this.descripcionConvenio.equals(other.getDescripcionConvenio())))
				&& (((this.fechaInicio == null) && (other.getFechaInicio() == null))
				|| ((this.fechaInicio != null) && this.fechaInicio.equals(other.getFechaInicio())))
				&& (((this.fechaFinalizacion == null) && (other.getFechaFinalizacion() == null))
				|| ((this.fechaFinalizacion != null) && this.fechaFinalizacion.equals(other.getFechaFinalizacion())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodigoConvenio() != null)
			_hashCode += getCodigoConvenio().hashCode();

		if(getDescripcionConvenio() != null)
			_hashCode += getDescripcionConvenio().hashCode();

		if(getFechaInicio() != null)
			_hashCode += getFechaInicio().hashCode();

		if(getFechaFinalizacion() != null)
			_hashCode += getFechaFinalizacion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
