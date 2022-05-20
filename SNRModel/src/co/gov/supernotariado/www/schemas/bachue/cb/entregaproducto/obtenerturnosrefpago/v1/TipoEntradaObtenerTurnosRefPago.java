/**
 * TipoEntradaObtenerTurnosRefPago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaObtenerTurnosRefPago.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaObtenerTurnosRefPago implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6904528017849867478L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerTurnosRefPago.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "tipoEntradaObtenerTurnosRefPago"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("referenciaPago");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "ReferenciaPago"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad referencia pago. */
	private java.lang.String referenciaPago;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada obtener turnos ref pago.
	 */
	public TipoEntradaObtenerTurnosRefPago()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada obtener turnos ref pago.
	 *
	 * @param referenciaPago de referencia pago
	 */
	public TipoEntradaObtenerTurnosRefPago(java.lang.String referenciaPago)
	{
		this.referenciaPago = referenciaPago;
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
	 * Sets the referenciaPago value for this TipoEntradaObtenerTurnosRefPago.
	 *
	 * @param referenciaPago de referencia pago
	 */
	public void setReferenciaPago(java.lang.String referenciaPago)
	{
		this.referenciaPago = referenciaPago;
	}

	/**
	 * Gets the referenciaPago value for this TipoEntradaObtenerTurnosRefPago.
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
		if(!(obj instanceof TipoEntradaObtenerTurnosRefPago))
			return false;

		TipoEntradaObtenerTurnosRefPago other = (TipoEntradaObtenerTurnosRefPago)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
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

		if(getReferenciaPago() != null)
			_hashCode += getReferenciaPago().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
