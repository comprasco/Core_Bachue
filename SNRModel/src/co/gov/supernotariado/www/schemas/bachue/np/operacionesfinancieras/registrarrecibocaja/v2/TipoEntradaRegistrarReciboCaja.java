/**
 * TipoEntradaRegistrarReciboCaja.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2;

public class TipoEntradaRegistrarReciboCaja implements java.io.Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 6762237312440678563L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarReciboCaja.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarrecibocaja/v2",
		        "tipoEntradaRegistrarReciboCaja"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReferencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarrecibocaja/v2",
		        "numeroReferencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaHoraRecibo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/np/operacionesfinancieras/registrarrecibocaja/v2",
		        "fechaHoraRecibo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object   __equalsCalc     = null;
	private java.util.Calendar fechaHoraRecibo;
	private java.lang.String   numeroReferencia;
	private boolean            __hashCodeCalc   = false;

	public TipoEntradaRegistrarReciboCaja()
	{
	}

	public TipoEntradaRegistrarReciboCaja(java.lang.String numeroReferencia, java.util.Calendar fechaHoraRecibo)
	{
		this.numeroReferencia     = numeroReferencia;
		this.fechaHoraRecibo      = fechaHoraRecibo;
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
	 * Sets the fechaHoraRecibo value for this TipoEntradaRegistrarReciboCaja.
	 *
	 * @param fechaHoraRecibo
	 */
	public void setFechaHoraRecibo(java.util.Calendar fechaHoraRecibo)
	{
		this.fechaHoraRecibo = fechaHoraRecibo;
	}

	/**
	 * Gets the fechaHoraRecibo value for this TipoEntradaRegistrarReciboCaja.
	 *
	 * @return fechaHoraRecibo
	 */
	public java.util.Calendar getFechaHoraRecibo()
	{
		return fechaHoraRecibo;
	}

	/**
	 * Sets the numeroReferencia value for this TipoEntradaRegistrarReciboCaja.
	 *
	 * @param numeroReferencia
	 */
	public void setNumeroReferencia(java.lang.String numeroReferencia)
	{
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the numeroReferencia value for this TipoEntradaRegistrarReciboCaja.
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
		if(!(obj instanceof TipoEntradaRegistrarReciboCaja))
			return false;

		TipoEntradaRegistrarReciboCaja other = (TipoEntradaRegistrarReciboCaja)obj;

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
				&& (((this.fechaHoraRecibo == null) && (other.getFechaHoraRecibo() == null))
				|| ((this.fechaHoraRecibo != null) && this.fechaHoraRecibo.equals(other.getFechaHoraRecibo())));
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

		if(getFechaHoraRecibo() != null)
			_hashCode += getFechaHoraRecibo().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
