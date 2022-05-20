/**
 * TipoEntradaObtenerRolesComponente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolescomponente.v1;

public class TipoEntradaObtenerRolesComponente implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerRolesComponente.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
		        "tipoEntradaObtenerRolesComponente"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("componente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
		        "componente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc   = null;
	private java.lang.String componente;
	private boolean          __hashCodeCalc = false;

	public TipoEntradaObtenerRolesComponente()
	{
	}

	public TipoEntradaObtenerRolesComponente(java.lang.String componente)
	{
		this.componente = componente;
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
	 * Sets the componente value for this TipoEntradaObtenerRolesComponente.
	 *
	 * @param componente
	 */
	public void setComponente(java.lang.String componente)
	{
		this.componente = componente;
	}

	/**
	 * Gets the componente value for this TipoEntradaObtenerRolesComponente.
	 *
	 * @return componente
	 */
	public java.lang.String getComponente()
	{
		return componente;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaObtenerRolesComponente))
			return false;

		TipoEntradaObtenerRolesComponente other = (TipoEntradaObtenerRolesComponente)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.componente == null) && (other.getComponente() == null))
				|| ((this.componente != null) && this.componente.equals(other.getComponente())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getComponente() != null)
			_hashCode += getComponente().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
