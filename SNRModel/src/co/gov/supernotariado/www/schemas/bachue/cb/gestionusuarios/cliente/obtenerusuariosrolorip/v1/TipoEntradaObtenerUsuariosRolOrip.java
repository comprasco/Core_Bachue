/**
 * TipoEntradaObtenerUsuariosRolOrip.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerusuariosrolorip.v1;

public class TipoEntradaObtenerUsuariosRolOrip implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerUsuariosRolOrip.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
		        "tipoEntradaObtenerUsuariosRolOrip"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoCirculo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
		        "codigoCirculo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
		        "codigoRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc   = null;
	private java.lang.String codigoCirculo;
	private java.lang.String codigoRol;
	private boolean          __hashCodeCalc = false;

	public TipoEntradaObtenerUsuariosRolOrip()
	{
	}

	public TipoEntradaObtenerUsuariosRolOrip(java.lang.String codigoCirculo, java.lang.String codigoRol)
	{
		this.codigoCirculo     = codigoCirculo;
		this.codigoRol         = codigoRol;
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
	 * Sets the codigoCirculo value for this TipoEntradaObtenerUsuariosRolOrip.
	 *
	 * @param codigoCirculo
	 */
	public void setCodigoCirculo(java.lang.String codigoCirculo)
	{
		this.codigoCirculo = codigoCirculo;
	}

	/**
	 * Gets the codigoCirculo value for this TipoEntradaObtenerUsuariosRolOrip.
	 *
	 * @return codigoCirculo
	 */
	public java.lang.String getCodigoCirculo()
	{
		return codigoCirculo;
	}

	/**
	 * Sets the codigoRol value for this TipoEntradaObtenerUsuariosRolOrip.
	 *
	 * @param codigoRol
	 */
	public void setCodigoRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Gets the codigoRol value for this TipoEntradaObtenerUsuariosRolOrip.
	 *
	 * @return codigoRol
	 */
	public java.lang.String getCodigoRol()
	{
		return codigoRol;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaObtenerUsuariosRolOrip))
			return false;

		TipoEntradaObtenerUsuariosRolOrip other = (TipoEntradaObtenerUsuariosRolOrip)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoCirculo == null) && (other.getCodigoCirculo() == null))
				|| ((this.codigoCirculo != null) && this.codigoCirculo.equals(other.getCodigoCirculo())))
				&& (((this.codigoRol == null) && (other.getCodigoRol() == null))
				|| ((this.codigoRol != null) && this.codigoRol.equals(other.getCodigoRol())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodigoCirculo() != null)
			_hashCode += getCodigoCirculo().hashCode();

		if(getCodigoRol() != null)
			_hashCode += getCodigoRol().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
