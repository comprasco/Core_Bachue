/**
 * TipoEntradaConsultarNuevasMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1;

public class TipoEntradaConsultarNuevasMatriculas implements java.io.Serializable
{
	private static final long serialVersionUID = -5235914472744800436L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarNuevasMatriculas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "tipoEntradaConsultarNuevasMatriculas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("agrupacionEspacial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "agrupacionEspacial"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        ">tipoEntradaConsultarNuevasMatriculas>agrupacionEspacial"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc = null;
	private co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial agrupacionEspacial;
	private boolean          __hashCodeCalc = false;

	public TipoEntradaConsultarNuevasMatriculas()
	{
	}

	public TipoEntradaConsultarNuevasMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial agrupacionEspacial
	)
	{
		this.agrupacionEspacial = agrupacionEspacial;
	}

	/**
	 * Sets the agrupacionEspacial value for this TipoEntradaConsultarNuevasMatriculas.
	 *
	 * @param agrupacionEspacial
	 */
	public void setAgrupacionEspacial(
	    co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial agrupacionEspacial
	)
	{
		this.agrupacionEspacial = agrupacionEspacial;
	}

	/**
	 * Gets the agrupacionEspacial value for this TipoEntradaConsultarNuevasMatriculas.
	 *
	 * @return agrupacionEspacial
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial getAgrupacionEspacial()
	{
		return agrupacionEspacial;
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

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarNuevasMatriculas))
			return false;

		TipoEntradaConsultarNuevasMatriculas other = (TipoEntradaConsultarNuevasMatriculas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.agrupacionEspacial == null) && (other.getAgrupacionEspacial() == null))
				|| ((this.agrupacionEspacial != null) && this.agrupacionEspacial.equals(other.getAgrupacionEspacial())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getAgrupacionEspacial() != null)
			_hashCode += getAgrupacionEspacial().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
