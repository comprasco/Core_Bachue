/**
 * TipoEntradaVerificarProducto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.verificarproducto.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaVerificarProducto.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaVerificarProducto implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -322924370130120175L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaVerificarProducto.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
		        "tipoEntradaVerificarProducto"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoVerificacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/verificarproducto/v1",
		        "CodigoVerificacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc       = null;
	
	/** Propiedad codigo verificacion. */
	private java.lang.String codigoVerificacion;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo entrada verificar producto.
	 */
	public TipoEntradaVerificarProducto()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada verificar producto.
	 *
	 * @param codigoVerificacion de codigo verificacion
	 */
	public TipoEntradaVerificarProducto(java.lang.String codigoVerificacion)
	{
		this.codigoVerificacion = codigoVerificacion;
	}

	/**
	 * Sets the codigoVerificacion value for this TipoEntradaVerificarProducto.
	 *
	 * @param codigoVerificacion de codigo verificacion
	 */
	public void setCodigoVerificacion(java.lang.String codigoVerificacion)
	{
		this.codigoVerificacion = codigoVerificacion;
	}

	/**
	 * Gets the codigoVerificacion value for this TipoEntradaVerificarProducto.
	 *
	 * @return codigoVerificacion
	 */
	public java.lang.String getCodigoVerificacion()
	{
		return codigoVerificacion;
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaVerificarProducto))
			return false;

		TipoEntradaVerificarProducto other = (TipoEntradaVerificarProducto)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoVerificacion == null) && (other.getCodigoVerificacion() == null))
				|| ((this.codigoVerificacion != null) && this.codigoVerificacion.equals(other.getCodigoVerificacion())));
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

		if(getCodigoVerificacion() != null)
			_hashCode += getCodigoVerificacion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
