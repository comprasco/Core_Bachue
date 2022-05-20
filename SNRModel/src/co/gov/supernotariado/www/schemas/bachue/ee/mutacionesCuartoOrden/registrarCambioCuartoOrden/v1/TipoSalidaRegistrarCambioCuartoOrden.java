/**
 * TipoSalidaRegistrarCambioCuartoOrden.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaRegistrarCambioCuartoOrden.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 7/05/2020
 */
public class TipoSalidaRegistrarCambioCuartoOrden implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8822593453338361754L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaRegistrarCambioCuartoOrden.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "tipoSalidaRegistrarCambioCuartoOrden"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
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
	private java.lang.String codMensaje;

	/** Propiedad descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida registrar cambio cuarto orden.
	 */
	public TipoSalidaRegistrarCambioCuartoOrden()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida registrar cambio cuarto orden.
	 *
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaRegistrarCambioCuartoOrden(java.lang.String codMensaje, java.lang.String descripcionMensaje)
	{
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaRegistrarCambioCuartoOrden.
	 *
	 * @return codMensaje
	 */
	public java.lang.String getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaRegistrarCambioCuartoOrden.
	 *
	 * @param codMensaje de cod mensaje
	 */
	public void setCodMensaje(java.lang.String codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaRegistrarCambioCuartoOrden.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaRegistrarCambioCuartoOrden.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaRegistrarCambioCuartoOrden))
			return false;

		TipoSalidaRegistrarCambioCuartoOrden other = (TipoSalidaRegistrarCambioCuartoOrden)obj;

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
}
