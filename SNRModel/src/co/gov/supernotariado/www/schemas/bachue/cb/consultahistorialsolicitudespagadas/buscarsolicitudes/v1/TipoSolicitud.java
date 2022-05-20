/**
 * TipoSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1;


/**
 * Clase que contiene todos las propiedades TipoSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSolicitud implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4194485847187148191L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "tipoSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "descripcionServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionSubServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "descripcionSubServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "fechaServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc           = null;
	
	/** Propiedad descripcion servicio. */
	private java.lang.String descripcionServicio;
	
	/** Propiedad descripcion sub servicio. */
	private java.lang.String descripcionSubServicio;
	
	/** Propiedad fecha servicio. */
	private java.lang.String fechaServicio;
	
	/** Propiedad nir. */
	private java.lang.String nir;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc         = false;

	/**
	 * Instancia un nuevo objeto tipo solicitud.
	 */
	public TipoSolicitud()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo solicitud.
	 *
	 * @param nir de nir
	 * @param descripcionServicio de descripcion servicio
	 * @param descripcionSubServicio de descripcion sub servicio
	 * @param fechaServicio de fecha servicio
	 */
	public TipoSolicitud(
	    java.lang.String nir, java.lang.String descripcionServicio, java.lang.String descripcionSubServicio,
	    java.lang.String fechaServicio
	)
	{
		this.nir                        = nir;
		this.descripcionServicio        = descripcionServicio;
		this.descripcionSubServicio     = descripcionSubServicio;
		this.fechaServicio              = fechaServicio;
	}

	/**
	 * Sets the descripcionServicio value for this TipoSolicitud.
	 *
	 * @param descripcionServicio de descripcion servicio
	 */
	public void setDescripcionServicio(java.lang.String descripcionServicio)
	{
		this.descripcionServicio = descripcionServicio;
	}

	/**
	 * Gets the descripcionServicio value for this TipoSolicitud.
	 *
	 * @return descripcionServicio
	 */
	public java.lang.String getDescripcionServicio()
	{
		return descripcionServicio;
	}

	/**
	 * Sets the descripcionSubServicio value for this TipoSolicitud.
	 *
	 * @param descripcionSubServicio de descripcion sub servicio
	 */
	public void setDescripcionSubServicio(java.lang.String descripcionSubServicio)
	{
		this.descripcionSubServicio = descripcionSubServicio;
	}

	/**
	 * Gets the descripcionSubServicio value for this TipoSolicitud.
	 *
	 * @return descripcionSubServicio
	 */
	public java.lang.String getDescripcionSubServicio()
	{
		return descripcionSubServicio;
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
	 * Sets the fechaServicio value for this TipoSolicitud.
	 *
	 * @param fechaServicio de fecha servicio
	 */
	public void setFechaServicio(java.lang.String fechaServicio)
	{
		this.fechaServicio = fechaServicio;
	}

	/**
	 * Gets the fechaServicio value for this TipoSolicitud.
	 *
	 * @return fechaServicio
	 */
	public java.lang.String getFechaServicio()
	{
		return fechaServicio;
	}

	/**
	 * Sets the nir value for this TipoSolicitud.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoSolicitud.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
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
		if(!(obj instanceof TipoSolicitud))
			return false;

		TipoSolicitud other = (TipoSolicitud)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.descripcionServicio == null) && (other.getDescripcionServicio() == null))
				|| ((this.descripcionServicio != null)
				&& this.descripcionServicio.equals(other.getDescripcionServicio())))
				&& (((this.descripcionSubServicio == null) && (other.getDescripcionSubServicio() == null))
				|| ((this.descripcionSubServicio != null)
				&& this.descripcionSubServicio.equals(other.getDescripcionSubServicio())))
				&& (((this.fechaServicio == null) && (other.getFechaServicio() == null))
				|| ((this.fechaServicio != null) && this.fechaServicio.equals(other.getFechaServicio())));
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

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getDescripcionServicio() != null)
			_hashCode += getDescripcionServicio().hashCode();

		if(getDescripcionSubServicio() != null)
			_hashCode += getDescripcionSubServicio().hashCode();

		if(getFechaServicio() != null)
			_hashCode += getFechaServicio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
