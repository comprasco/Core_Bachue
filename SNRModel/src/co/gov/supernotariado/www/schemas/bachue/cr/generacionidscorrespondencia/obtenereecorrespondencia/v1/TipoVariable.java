/**
 * TipoVariable.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1;

public class TipoVariable implements java.io.Serializable
{
	private static final long serialVersionUID = 2796324825902504043L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoVariable.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "tipoVariable"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("llave");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "llave"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valor");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        "valor"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc   = null;
	private java.lang.String llave;
	private java.lang.String valor;
	private boolean          __hashCodeCalc = false;

	public TipoVariable()
	{
	}

	public TipoVariable(java.lang.String llave, java.lang.String valor)
	{
		this.llave     = llave;
		this.valor     = valor;
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
	 * Sets the llave value for this TipoVariable.
	 *
	 * @param llave
	 */
	public void setLlave(java.lang.String llave)
	{
		this.llave = llave;
	}

	/**
	 * Gets the llave value for this TipoVariable.
	 *
	 * @return llave
	 */
	public java.lang.String getLlave()
	{
		return llave;
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
	 * Sets the valor value for this TipoVariable.
	 *
	 * @param valor
	 */
	public void setValor(java.lang.String valor)
	{
		this.valor = valor;
	}

	/**
	 * Gets the valor value for this TipoVariable.
	 *
	 * @return valor
	 */
	public java.lang.String getValor()
	{
		return valor;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoVariable))
			return false;

		TipoVariable other = (TipoVariable)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.llave == null) && (other.getLlave() == null))
				|| ((this.llave != null) && this.llave.equals(other.getLlave())))
				&& (((this.valor == null) && (other.getValor() == null))
				|| ((this.valor != null) && this.valor.equals(other.getValor())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getLlave() != null)
			_hashCode += getLlave().hashCode();

		if(getValor() != null)
			_hashCode += getValor().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
