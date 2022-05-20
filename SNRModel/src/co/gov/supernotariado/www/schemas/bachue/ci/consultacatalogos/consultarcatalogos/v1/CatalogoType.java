/**
 * CatalogoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ci.consultacatalogos.consultarcatalogos.v1;


/**
 * Interface que contiene todos las propiedades de consulta catalogo
 * @author dbeltran
 *
 */
public class CatalogoType implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    CatalogoType.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/consultacatalogos/consultarcatalogos/v1",
		        "catalogoType"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/consultacatalogos/consultarcatalogos/v1", "codigo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/consultacatalogos/consultarcatalogos/v1", "nombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Constante equalsCalc
	 */
	private java.lang.Object __equalsCalc = null;
	private java.lang.String codigo;

	/**Constante nombre*/
	private java.lang.String nombre;

	/**Constante hash code calc*/
	private boolean __hashCodeCalc = false;

	/**Constructor catalogoType*/
	public CatalogoType()
	{
	}

	/**
	 * Constructor catalogoType
	 * @param codigo con el codigo
	 * @param nombre xon el nombre
	 */
	public CatalogoType(java.lang.String codigo, java.lang.String nombre)
	{
		this.codigo     = codigo;
		this.nombre     = nombre;
	}

	/**
	 * Gets the codigo value for this CatalogoType.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the codigo value for this CatalogoType.
	 *
	 * @param codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the nombre value for this CatalogoType.
	 *
	 * @return nombre
	 */
	public java.lang.String getNombre()
	{
		return nombre;
	}

	/**
	 * Sets the nombre value for this CatalogoType.
	 *
	 * @param nombre
	 */
	public void setNombre(java.lang.String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Método equals
	 */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof CatalogoType))
			return false;

		CatalogoType other = (CatalogoType)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.nombre == null) && (other.getNombre() == null))
				|| ((this.nombre != null) && this.nombre.equals(other.getNombre())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getNombre() != null)
			_hashCode += getNombre().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
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
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
