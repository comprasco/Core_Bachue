/**
 * TipoDireccionAnterior.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1;


/**
 * Clase que contiene todos las propiedades TipoDireccionAnterior.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoDireccionAnterior implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDireccionAnterior.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "tipoDireccionAnterior"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "direccionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad direccion predio. */
	/* corresponde a una dirección anterior. */
	private java.lang.String direccionPredio;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo direccion anterior.
	 */
	public TipoDireccionAnterior()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo direccion anterior.
	 *
	 * @param direccionPredio de direccion predio
	 */
	public TipoDireccionAnterior(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
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
	 * Sets the direccionPredio value for this TipoDireccionAnterior.
	 *
	 * @param direccionPredio   * corresponde a una dirección anterior.
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the direccionPredio value for this TipoDireccionAnterior.
	 *
	 * @return direccionPredio   * corresponde a una dirección anterior.
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
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
		if(!(obj instanceof TipoDireccionAnterior))
			return false;

		TipoDireccionAnterior other = (TipoDireccionAnterior)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())));
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

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
