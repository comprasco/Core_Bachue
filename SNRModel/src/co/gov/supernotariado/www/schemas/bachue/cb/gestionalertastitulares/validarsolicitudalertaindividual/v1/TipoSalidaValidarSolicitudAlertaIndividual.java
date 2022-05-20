/**
 * TipoSalidaValidarSolicitudAlertaIndividual.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaValidarSolicitudAlertaIndividual.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaValidarSolicitudAlertaIndividual implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2715962835229837434L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaValidarSolicitudAlertaIndividual.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        "tipoSalidaValidarSolicitudAlertaIndividual"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("departamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        "departamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("ciudad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        "ciudad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        "direccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        ">tipoSalidaValidarSolicitudAlertaIndividual>codigoMensaje"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/validarsolicitudalertaindividual/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                                __equalsCalc       =
		null;
	
	/** Propiedad ciudad. */
	private java.lang.String                                                                                                                                                ciudad;
	
	/** Propiedad codigo mensaje. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje codigoMensaje;
	
	/** Propiedad departamento. */
	private java.lang.String                                                                                                                                                departamento;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String                                                                                                                                                descripcionMensaje;
	
	/** Propiedad direccion. */
	private java.lang.String                                                                                                                                                direccion;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                         __hashCodeCalc     =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida validar solicitud alerta individual.
	 */
	public TipoSalidaValidarSolicitudAlertaIndividual()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida validar solicitud alerta individual.
	 *
	 * @param departamento de departamento
	 * @param ciudad de ciudad
	 * @param direccion de direccion
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaValidarSolicitudAlertaIndividual(
	    java.lang.String departamento, java.lang.String ciudad, java.lang.String direccion,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje codigoMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.departamento           = departamento;
		this.ciudad                 = ciudad;
		this.direccion              = direccion;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the ciudad value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @param ciudad de ciudad
	 */
	public void setCiudad(java.lang.String ciudad)
	{
		this.ciudad = ciudad;
	}

	/**
	 * Gets the ciudad value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @return ciudad
	 */
	public java.lang.String getCiudad()
	{
		return ciudad;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje codigoMensaje
	)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @return codigoMensaje
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividualCodigoMensaje getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the departamento value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @param departamento de departamento
	 */
	public void setDepartamento(java.lang.String departamento)
	{
		this.departamento = departamento;
	}

	/**
	 * Gets the departamento value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @return departamento
	 */
	public java.lang.String getDepartamento()
	{
		return departamento;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaValidarSolicitudAlertaIndividual.
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
	 * Sets the direccion value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @param direccion de direccion
	 */
	public void setDireccion(java.lang.String direccion)
	{
		this.direccion = direccion;
	}

	/**
	 * Gets the direccion value for this TipoSalidaValidarSolicitudAlertaIndividual.
	 *
	 * @return direccion
	 */
	public java.lang.String getDireccion()
	{
		return direccion;
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
		if(!(obj instanceof TipoSalidaValidarSolicitudAlertaIndividual))
			return false;

		TipoSalidaValidarSolicitudAlertaIndividual other = (TipoSalidaValidarSolicitudAlertaIndividual)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.departamento == null) && (other.getDepartamento() == null))
				|| ((this.departamento != null) && this.departamento.equals(other.getDepartamento())))
				&& (((this.ciudad == null) && (other.getCiudad() == null))
				|| ((this.ciudad != null) && this.ciudad.equals(other.getCiudad())))
				&& (((this.direccion == null) && (other.getDireccion() == null))
				|| ((this.direccion != null) && this.direccion.equals(other.getDireccion())))
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

		if(getDepartamento() != null)
			_hashCode += getDepartamento().hashCode();

		if(getCiudad() != null)
			_hashCode += getCiudad().hashCode();

		if(getDireccion() != null)
			_hashCode += getDireccion().hashCode();

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
