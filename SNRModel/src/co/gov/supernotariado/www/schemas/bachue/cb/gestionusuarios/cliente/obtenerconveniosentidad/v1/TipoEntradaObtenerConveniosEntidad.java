/**
 * TipoEntradaObtenerConveniosEntidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerconveniosentidad.v1;

public class TipoEntradaObtenerConveniosEntidad implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerConveniosEntidad.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "tipoEntradaObtenerConveniosEntidad"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerconveniosentidad/v1",
		        "codigoEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc   = null;
	private java.lang.String codigoEntidad;
	private boolean          __hashCodeCalc = false;

	public TipoEntradaObtenerConveniosEntidad()
	{
	}

	public TipoEntradaObtenerConveniosEntidad(java.lang.String codigoEntidad)
	{
		this.codigoEntidad = codigoEntidad;
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
	 * Sets the codigoEntidad value for this TipoEntradaObtenerConveniosEntidad.
	 *
	 * @param codigoEntidad
	 */
	public void setCodigoEntidad(java.lang.String codigoEntidad)
	{
		this.codigoEntidad = codigoEntidad;
	}

	/**
	 * Gets the codigoEntidad value for this TipoEntradaObtenerConveniosEntidad.
	 *
	 * @return codigoEntidad
	 */
	public java.lang.String getCodigoEntidad()
	{
		return codigoEntidad;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaObtenerConveniosEntidad))
			return false;

		TipoEntradaObtenerConveniosEntidad other = (TipoEntradaObtenerConveniosEntidad)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoEntidad == null) && (other.getCodigoEntidad() == null))
				|| ((this.codigoEntidad != null) && this.codigoEntidad.equals(other.getCodigoEntidad())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodigoEntidad() != null)
			_hashCode += getCodigoEntidad().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
