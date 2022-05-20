/**
 * TipoEntradaObtenerReciboCaja.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerrecibocaja.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaObtenerReciboCaja.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaObtenerReciboCaja implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3822624959361284086L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerReciboCaja.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
		        "tipoEntradaObtenerReciboCaja"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1", "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("referenciaPago");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerrecibocaja/v1",
		        "ReferenciaPago"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nir. */
	private java.lang.String NIR;
	
	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad referencia pago. */
	private java.lang.String referenciaPago;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada obtener recibo caja.
	 */
	public TipoEntradaObtenerReciboCaja()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada obtener recibo caja.
	 *
	 * @param NIR de nir
	 * @param referenciaPago de referencia pago
	 */
	public TipoEntradaObtenerReciboCaja(java.lang.String NIR, java.lang.String referenciaPago)
	{
		this.NIR                = NIR;
		this.referenciaPago     = referenciaPago;
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
	 * Sets the NIR value for this TipoEntradaObtenerReciboCaja.
	 *
	 * @param NIR de nir
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the NIR value for this TipoEntradaObtenerReciboCaja.
	 *
	 * @return NIR
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the referenciaPago value for this TipoEntradaObtenerReciboCaja.
	 *
	 * @param referenciaPago de referencia pago
	 */
	public void setReferenciaPago(java.lang.String referenciaPago)
	{
		this.referenciaPago = referenciaPago;
	}

	/**
	 * Gets the referenciaPago value for this TipoEntradaObtenerReciboCaja.
	 *
	 * @return referenciaPago
	 */
	public java.lang.String getReferenciaPago()
	{
		return referenciaPago;
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
		if(!(obj instanceof TipoEntradaObtenerReciboCaja))
			return false;

		TipoEntradaObtenerReciboCaja other = (TipoEntradaObtenerReciboCaja)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())))
				&& (((this.referenciaPago == null) && (other.getReferenciaPago() == null))
				|| ((this.referenciaPago != null) && this.referenciaPago.equals(other.getReferenciaPago())));
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

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

		if(getReferenciaPago() != null)
			_hashCode += getReferenciaPago().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
