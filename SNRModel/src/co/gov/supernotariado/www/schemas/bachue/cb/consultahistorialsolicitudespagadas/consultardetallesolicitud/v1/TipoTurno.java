/**
 * TipoTurno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoTurno.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoTurno implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8355259279937370513L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoTurno.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "tipoTurno"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "turno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "descripcionServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descricionSubServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "descricionSubServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "fechaServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc          = null;
	
	/** Propiedad descricion sub servicio. */
	private java.lang.String descricionSubServicio;
	
	/** Propiedad descripcion servicio. */
	private java.lang.String descripcionServicio;
	
	/** Propiedad fecha servicio. */
	private java.lang.String fechaServicio;
	
	/** Propiedad nir. */
	private java.lang.String nir;
	
	/** Propiedad turno. */
	private java.lang.String turno;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc        = false;

	/**
	 * Instancia un nuevo objeto tipo turno.
	 */
	public TipoTurno()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo turno.
	 *
	 * @param nir de nir
	 * @param turno de turno
	 * @param descripcionServicio de descripcion servicio
	 * @param descricionSubServicio de descricion sub servicio
	 * @param fechaServicio de fecha servicio
	 */
	public TipoTurno(
	    java.lang.String nir, java.lang.String turno, java.lang.String descripcionServicio,
	    java.lang.String descricionSubServicio, java.lang.String fechaServicio
	)
	{
		this.nir                       = nir;
		this.turno                     = turno;
		this.descripcionServicio       = descripcionServicio;
		this.descricionSubServicio     = descricionSubServicio;
		this.fechaServicio             = fechaServicio;
	}

	/**
	 * Sets the descricionSubServicio value for this TipoTurno.
	 *
	 * @param descricionSubServicio de descricion sub servicio
	 */
	public void setDescricionSubServicio(java.lang.String descricionSubServicio)
	{
		this.descricionSubServicio = descricionSubServicio;
	}

	/**
	 * Gets the descricionSubServicio value for this TipoTurno.
	 *
	 * @return descricionSubServicio
	 */
	public java.lang.String getDescricionSubServicio()
	{
		return descricionSubServicio;
	}

	/**
	 * Sets the descripcionServicio value for this TipoTurno.
	 *
	 * @param descripcionServicio de descripcion servicio
	 */
	public void setDescripcionServicio(java.lang.String descripcionServicio)
	{
		this.descripcionServicio = descripcionServicio;
	}

	/**
	 * Gets the descripcionServicio value for this TipoTurno.
	 *
	 * @return descripcionServicio
	 */
	public java.lang.String getDescripcionServicio()
	{
		return descripcionServicio;
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
	 * Sets the fechaServicio value for this TipoTurno.
	 *
	 * @param fechaServicio de fecha servicio
	 */
	public void setFechaServicio(java.lang.String fechaServicio)
	{
		this.fechaServicio = fechaServicio;
	}

	/**
	 * Gets the fechaServicio value for this TipoTurno.
	 *
	 * @return fechaServicio
	 */
	public java.lang.String getFechaServicio()
	{
		return fechaServicio;
	}

	/**
	 * Sets the nir value for this TipoTurno.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoTurno.
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
	 * Sets the turno value for this TipoTurno.
	 *
	 * @param turno de turno
	 */
	public void setTurno(java.lang.String turno)
	{
		this.turno = turno;
	}

	/**
	 * Gets the turno value for this TipoTurno.
	 *
	 * @return turno
	 */
	public java.lang.String getTurno()
	{
		return turno;
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
		if(!(obj instanceof TipoTurno))
			return false;

		TipoTurno other = (TipoTurno)obj;

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
				&& (((this.turno == null) && (other.getTurno() == null))
				|| ((this.turno != null) && this.turno.equals(other.getTurno())))
				&& (((this.descripcionServicio == null) && (other.getDescripcionServicio() == null))
				|| ((this.descripcionServicio != null)
				&& this.descripcionServicio.equals(other.getDescripcionServicio())))
				&& (((this.descricionSubServicio == null) && (other.getDescricionSubServicio() == null))
				|| ((this.descricionSubServicio != null)
				&& this.descricionSubServicio.equals(other.getDescricionSubServicio())))
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

		if(getTurno() != null)
			_hashCode += getTurno().hashCode();

		if(getDescripcionServicio() != null)
			_hashCode += getDescripcionServicio().hashCode();

		if(getDescricionSubServicio() != null)
			_hashCode += getDescricionSubServicio().hashCode();

		if(getFechaServicio() != null)
			_hashCode += getFechaServicio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
