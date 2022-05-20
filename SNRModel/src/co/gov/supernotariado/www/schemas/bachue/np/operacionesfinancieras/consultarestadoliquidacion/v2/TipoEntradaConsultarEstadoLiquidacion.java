/**
 * TipoEntradaConsultarEstadoLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2;

public class TipoEntradaConsultarEstadoLiquidacion implements java.io.Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = -1285835376074526683L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarEstadoLiquidacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/consultarestadoliquidacion/v2",
		        "tipoEntradaConsultarEstadoLiquidacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReferencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/consultarestadoliquidacion/v2",
		        "numeroReferencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("montoTransaccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/consultarestadoliquidacion/v2",
		        "montoTransaccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object     __equalsCalc     = null;
	private java.math.BigDecimal montoTransaccion;
	private java.lang.String     numeroReferencia;
	private boolean              __hashCodeCalc   = false;

	public TipoEntradaConsultarEstadoLiquidacion()
	{
	}

	public TipoEntradaConsultarEstadoLiquidacion(
	    java.lang.String numeroReferencia, java.math.BigDecimal montoTransaccion
	)
	{
		this.numeroReferencia     = numeroReferencia;
		this.montoTransaccion     = montoTransaccion;
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
	 * Sets the montoTransaccion value for this TipoEntradaConsultarEstadoLiquidacion.
	 *
	 * @param montoTransaccion
	 */
	public void setMontoTransaccion(java.math.BigDecimal montoTransaccion)
	{
		this.montoTransaccion = montoTransaccion;
	}

	/**
	 * Gets the montoTransaccion value for this TipoEntradaConsultarEstadoLiquidacion.
	 *
	 * @return montoTransaccion
	 */
	public java.math.BigDecimal getMontoTransaccion()
	{
		return montoTransaccion;
	}

	/**
	 * Sets the numeroReferencia value for this TipoEntradaConsultarEstadoLiquidacion.
	 *
	 * @param numeroReferencia
	 */
	public void setNumeroReferencia(java.lang.String numeroReferencia)
	{
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the numeroReferencia value for this TipoEntradaConsultarEstadoLiquidacion.
	 *
	 * @return numeroReferencia
	 */
	public java.lang.String getNumeroReferencia()
	{
		return numeroReferencia;
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
		if(!(obj instanceof TipoEntradaConsultarEstadoLiquidacion))
			return false;

		TipoEntradaConsultarEstadoLiquidacion other = (TipoEntradaConsultarEstadoLiquidacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numeroReferencia == null) && (other.getNumeroReferencia() == null))
				|| ((this.numeroReferencia != null) && this.numeroReferencia.equals(other.getNumeroReferencia())))
				&& (((this.montoTransaccion == null) && (other.getMontoTransaccion() == null))
				|| ((this.montoTransaccion != null) && this.montoTransaccion.equals(other.getMontoTransaccion())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getNumeroReferencia() != null)
			_hashCode += getNumeroReferencia().hashCode();

		if(getMontoTransaccion() != null)
			_hashCode += getMontoTransaccion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
