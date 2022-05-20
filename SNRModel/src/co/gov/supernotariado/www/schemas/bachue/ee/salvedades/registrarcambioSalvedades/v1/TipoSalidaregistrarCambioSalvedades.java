/**
 * TipoSalidaregistrarCambioSalvedades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaregistrarCambioSalvedades.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaregistrarCambioSalvedades implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5945615111800858296L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaregistrarCambioSalvedades.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "tipoSalidaregistrarCambioSalvedades"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc       = null;
	
	/** Propiedad cod mensaje. */
	private java.math.BigInteger codMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String     descripcionMensaje;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo salidaregistrar cambio salvedades.
	 */
	public TipoSalidaregistrarCambioSalvedades()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salidaregistrar cambio salvedades.
	 *
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaregistrarCambioSalvedades(java.math.BigInteger codMensaje, java.lang.String descripcionMensaje)
	{
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
	 * Sets the codMensaje value for this TipoSalidaregistrarCambioSalvedades.
	 *
	 * @param codMensaje de cod mensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaregistrarCambioSalvedades.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaregistrarCambioSalvedades.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaregistrarCambioSalvedades.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaregistrarCambioSalvedades))
			return false;

		TipoSalidaregistrarCambioSalvedades other = (TipoSalidaregistrarCambioSalvedades)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
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

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
