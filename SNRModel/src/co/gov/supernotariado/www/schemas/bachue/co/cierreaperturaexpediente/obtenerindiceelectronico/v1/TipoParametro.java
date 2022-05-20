/**
 * TipoParametro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.obtenerindiceelectronico.v1;



/**
 * Clase que contiene todos las propiedades TipoParametro.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/05/2020
 */
public class TipoParametro implements java.io.Serializable
{
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoParametro.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/obtenerindiceelectronico/v1",
		        "tipoParametro"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/obtenerindiceelectronico/v1",
		        "nombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valor");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/obtenerindiceelectronico/v1",
		        "valor"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad nombre. */
	private java.lang.String nombre;

	/** Propiedad valor. */
	private java.lang.String valor;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo parametro.
	 */
	public TipoParametro()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo parametro.
	 *
	 * @param nombre de nombre
	 * @param valor de valor
	 */
	public TipoParametro(java.lang.String nombre, java.lang.String valor)
	{
		this.nombre     = nombre;
		this.valor      = valor;
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
	 * Sets the nombre value for this TipoParametro.
	 *
	 * @param nombre de nombre
	 */
	public void setNombre(java.lang.String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Gets the nombre value for this TipoParametro.
	 *
	 * @return nombre
	 */
	public java.lang.String getNombre()
	{
		return nombre;
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
	 * Sets the valor value for this TipoParametro.
	 *
	 * @param valor de valor
	 */
	public void setValor(java.lang.String valor)
	{
		this.valor = valor;
	}

	/**
	 * Gets the valor value for this TipoParametro.
	 *
	 * @return valor
	 */
	public java.lang.String getValor()
	{
		return valor;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoParametro))
			return false;

		TipoParametro other = (TipoParametro)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.nombre == null) && (other.getNombre() == null))
				|| ((this.nombre != null) && this.nombre.equals(other.getNombre())))
				&& (((this.valor == null) && (other.getValor() == null))
				|| ((this.valor != null) && this.valor.equals(other.getValor())));
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

		if(getNombre() != null)
			_hashCode += getNombre().hashCode();

		if(getValor() != null)
			_hashCode += getValor().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
