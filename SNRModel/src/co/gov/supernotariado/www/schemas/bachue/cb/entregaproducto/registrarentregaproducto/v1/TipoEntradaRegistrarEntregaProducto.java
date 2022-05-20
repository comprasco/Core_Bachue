/**
 * TipoEntradaRegistrarEntregaProducto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.registrarentregaproducto.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarEntregaProducto.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRegistrarEntregaProducto implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1515843931442658335L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarEntregaProducto.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
		        "tipoEntradaRegistrarEntregaProducto"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
		        "Turno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoVerificacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
		        "CodigoVerificacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("referenciaPago");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/registrarentregaproducto/v1",
		        "ReferenciaPago"
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
	
	/** Propiedad referencia pago. */
	private java.lang.String referenciaPago;
	
	/** Propiedad turno. */
	private java.lang.String turno;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar entrega producto.
	 */
	public TipoEntradaRegistrarEntregaProducto()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar entrega producto.
	 *
	 * @param turno de turno
	 * @param codigoVerificacion de codigo verificacion
	 * @param referenciaPago de referencia pago
	 */
	public TipoEntradaRegistrarEntregaProducto(
	    java.lang.String turno, java.lang.String codigoVerificacion, java.lang.String referenciaPago
	)
	{
		this.turno                  = turno;
		this.codigoVerificacion     = codigoVerificacion;
		this.referenciaPago         = referenciaPago;
	}

	/**
	 * Sets the codigoVerificacion value for this TipoEntradaRegistrarEntregaProducto.
	 *
	 * @param codigoVerificacion de codigo verificacion
	 */
	public void setCodigoVerificacion(java.lang.String codigoVerificacion)
	{
		this.codigoVerificacion = codigoVerificacion;
	}

	/**
	 * Gets the codigoVerificacion value for this TipoEntradaRegistrarEntregaProducto.
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
	 * Sets the referenciaPago value for this TipoEntradaRegistrarEntregaProducto.
	 *
	 * @param referenciaPago de referencia pago
	 */
	public void setReferenciaPago(java.lang.String referenciaPago)
	{
		this.referenciaPago = referenciaPago;
	}

	/**
	 * Gets the referenciaPago value for this TipoEntradaRegistrarEntregaProducto.
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
	 * Sets the turno value for this TipoEntradaRegistrarEntregaProducto.
	 *
	 * @param turno de turno
	 */
	public void setTurno(java.lang.String turno)
	{
		this.turno = turno;
	}

	/**
	 * Gets the turno value for this TipoEntradaRegistrarEntregaProducto.
	 *
	 * @return turno
	 */
	public java.lang.String getTurno()
	{
		return turno;
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
		if(!(obj instanceof TipoEntradaRegistrarEntregaProducto))
			return false;

		TipoEntradaRegistrarEntregaProducto other = (TipoEntradaRegistrarEntregaProducto)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.turno == null) && (other.getTurno() == null))
				|| ((this.turno != null) && this.turno.equals(other.getTurno())))
				&& (((this.codigoVerificacion == null) && (other.getCodigoVerificacion() == null))
				|| ((this.codigoVerificacion != null) && this.codigoVerificacion.equals(other.getCodigoVerificacion())))
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

		if(getTurno() != null)
			_hashCode += getTurno().hashCode();

		if(getCodigoVerificacion() != null)
			_hashCode += getCodigoVerificacion().hashCode();

		if(getReferenciaPago() != null)
			_hashCode += getReferenciaPago().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
