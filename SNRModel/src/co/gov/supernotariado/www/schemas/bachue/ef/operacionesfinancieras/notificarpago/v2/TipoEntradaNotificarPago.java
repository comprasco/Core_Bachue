/**
 * TipoEntradaNotificarPago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ef.operacionesfinancieras.notificarpago.v2;

public class TipoEntradaNotificarPago implements java.io.Serializable
{
	private static final long serialVersionUID = -616667632539418130L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaNotificarPago.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "tipoEntradaNotificarPago"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoEntidadRecaudadora");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "codigoEntidadRecaudadora"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoPuntoRecaudoEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "codigoPuntoRecaudoEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoTipoRecaudo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "codigoTipoRecaudo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaTransaccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "fechaTransaccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaBancaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "fechaBancaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoTransaccionRecaudador");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "codigoTransaccionRecaudador"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("montoTransaccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "montoTransaccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReferenciaBachue");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ef/operacionesfinancieras/notificarpago/v2",
		        "numeroReferenciaBachue"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object     __equalsCalc                = null;
	private java.lang.String     codigoEntidadRecaudadora;
	private java.lang.String     codigoPuntoRecaudoEntidad;
	private java.lang.String     codigoTipoRecaudo;
	private java.lang.String     codigoTransaccionRecaudador;
	private java.util.Calendar   fechaBancaria;
	private java.util.Calendar   fechaTransaccion;
	private java.math.BigDecimal montoTransaccion;
	private java.lang.String     numeroReferenciaBachue;
	private boolean              __hashCodeCalc              = false;

	public TipoEntradaNotificarPago()
	{
	}

	public TipoEntradaNotificarPago(
	    java.lang.String codigoEntidadRecaudadora, java.lang.String codigoPuntoRecaudoEntidad,
	    java.lang.String codigoTipoRecaudo, java.util.Calendar fechaTransaccion, java.util.Calendar fechaBancaria,
	    java.lang.String codigoTransaccionRecaudador, java.math.BigDecimal montoTransaccion,
	    java.lang.String numeroReferenciaBachue
	)
	{
		this.codigoEntidadRecaudadora        = codigoEntidadRecaudadora;
		this.codigoPuntoRecaudoEntidad       = codigoPuntoRecaudoEntidad;
		this.codigoTipoRecaudo               = codigoTipoRecaudo;
		this.fechaTransaccion                = fechaTransaccion;
		this.fechaBancaria                   = fechaBancaria;
		this.codigoTransaccionRecaudador     = codigoTransaccionRecaudador;
		this.montoTransaccion                = montoTransaccion;
		this.numeroReferenciaBachue          = numeroReferenciaBachue;
	}

	/**
	 * Gets the codigoEntidadRecaudadora value for this TipoEntradaNotificarPago.
	 *
	 * @return codigoEntidadRecaudadora
	 */
	public java.lang.String getCodigoEntidadRecaudadora()
	{
		return codigoEntidadRecaudadora;
	}

	/**
	 * Sets the codigoEntidadRecaudadora value for this TipoEntradaNotificarPago.
	 *
	 * @param codigoEntidadRecaudadora
	 */
	public void setCodigoEntidadRecaudadora(java.lang.String codigoEntidadRecaudadora)
	{
		this.codigoEntidadRecaudadora = codigoEntidadRecaudadora;
	}

	/**
	 * Gets the codigoPuntoRecaudoEntidad value for this TipoEntradaNotificarPago.
	 *
	 * @return codigoPuntoRecaudoEntidad
	 */
	public java.lang.String getCodigoPuntoRecaudoEntidad()
	{
		return codigoPuntoRecaudoEntidad;
	}

	/**
	 * Sets the codigoPuntoRecaudoEntidad value for this TipoEntradaNotificarPago.
	 *
	 * @param codigoPuntoRecaudoEntidad
	 */
	public void setCodigoPuntoRecaudoEntidad(java.lang.String codigoPuntoRecaudoEntidad)
	{
		this.codigoPuntoRecaudoEntidad = codigoPuntoRecaudoEntidad;
	}

	/**
	 * Gets the codigoTipoRecaudo value for this TipoEntradaNotificarPago.
	 *
	 * @return codigoTipoRecaudo
	 */
	public java.lang.String getCodigoTipoRecaudo()
	{
		return codigoTipoRecaudo;
	}

	/**
	 * Sets the codigoTipoRecaudo value for this TipoEntradaNotificarPago.
	 *
	 * @param codigoTipoRecaudo
	 */
	public void setCodigoTipoRecaudo(java.lang.String codigoTipoRecaudo)
	{
		this.codigoTipoRecaudo = codigoTipoRecaudo;
	}

	/**
	 * Gets the fechaTransaccion value for this TipoEntradaNotificarPago.
	 *
	 * @return fechaTransaccion
	 */
	public java.util.Calendar getFechaTransaccion()
	{
		return fechaTransaccion;
	}

	/**
	 * Sets the fechaTransaccion value for this TipoEntradaNotificarPago.
	 *
	 * @param fechaTransaccion
	 */
	public void setFechaTransaccion(java.util.Calendar fechaTransaccion)
	{
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * Gets the fechaBancaria value for this TipoEntradaNotificarPago.
	 *
	 * @return fechaBancaria
	 */
	public java.util.Calendar getFechaBancaria()
	{
		return fechaBancaria;
	}

	/**
	 * Sets the fechaBancaria value for this TipoEntradaNotificarPago.
	 *
	 * @param fechaBancaria
	 */
	public void setFechaBancaria(java.util.Calendar fechaBancaria)
	{
		this.fechaBancaria = fechaBancaria;
	}

	/**
	 * Gets the codigoTransaccionRecaudador value for this TipoEntradaNotificarPago.
	 *
	 * @return codigoTransaccionRecaudador
	 */
	public java.lang.String getCodigoTransaccionRecaudador()
	{
		return codigoTransaccionRecaudador;
	}

	/**
	 * Sets the codigoTransaccionRecaudador value for this TipoEntradaNotificarPago.
	 *
	 * @param codigoTransaccionRecaudador
	 */
	public void setCodigoTransaccionRecaudador(java.lang.String codigoTransaccionRecaudador)
	{
		this.codigoTransaccionRecaudador = codigoTransaccionRecaudador;
	}

	/**
	 * Gets the montoTransaccion value for this TipoEntradaNotificarPago.
	 *
	 * @return montoTransaccion
	 */
	public java.math.BigDecimal getMontoTransaccion()
	{
		return montoTransaccion;
	}

	/**
	 * Sets the montoTransaccion value for this TipoEntradaNotificarPago.
	 *
	 * @param montoTransaccion
	 */
	public void setMontoTransaccion(java.math.BigDecimal montoTransaccion)
	{
		this.montoTransaccion = montoTransaccion;
	}

	/**
	 * Gets the numeroReferenciaBachue value for this TipoEntradaNotificarPago.
	 *
	 * @return numeroReferenciaBachue
	 */
	public java.lang.String getNumeroReferenciaBachue()
	{
		return numeroReferenciaBachue;
	}

	/**
	 * Sets the numeroReferenciaBachue value for this TipoEntradaNotificarPago.
	 *
	 * @param numeroReferenciaBachue
	 */
	public void setNumeroReferenciaBachue(java.lang.String numeroReferenciaBachue)
	{
		this.numeroReferenciaBachue = numeroReferenciaBachue;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaNotificarPago))
			return false;

		TipoEntradaNotificarPago other = (TipoEntradaNotificarPago)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoEntidadRecaudadora == null) && (other.getCodigoEntidadRecaudadora() == null))
				|| ((this.codigoEntidadRecaudadora != null)
				&& this.codigoEntidadRecaudadora.equals(other.getCodigoEntidadRecaudadora())))
				&& (((this.codigoPuntoRecaudoEntidad == null) && (other.getCodigoPuntoRecaudoEntidad() == null))
				|| ((this.codigoPuntoRecaudoEntidad != null)
				&& this.codigoPuntoRecaudoEntidad.equals(other.getCodigoPuntoRecaudoEntidad())))
				&& (((this.codigoTipoRecaudo == null) && (other.getCodigoTipoRecaudo() == null))
				|| ((this.codigoTipoRecaudo != null) && this.codigoTipoRecaudo.equals(other.getCodigoTipoRecaudo())))
				&& (((this.fechaTransaccion == null) && (other.getFechaTransaccion() == null))
				|| ((this.fechaTransaccion != null) && this.fechaTransaccion.equals(other.getFechaTransaccion())))
				&& (((this.fechaBancaria == null) && (other.getFechaBancaria() == null))
				|| ((this.fechaBancaria != null) && this.fechaBancaria.equals(other.getFechaBancaria())))
				&& (((this.codigoTransaccionRecaudador == null) && (other.getCodigoTransaccionRecaudador() == null))
				|| ((this.codigoTransaccionRecaudador != null)
				&& this.codigoTransaccionRecaudador.equals(other.getCodigoTransaccionRecaudador())))
				&& (((this.montoTransaccion == null) && (other.getMontoTransaccion() == null))
				|| ((this.montoTransaccion != null) && this.montoTransaccion.equals(other.getMontoTransaccion())))
				&& (((this.numeroReferenciaBachue == null) && (other.getNumeroReferenciaBachue() == null))
				|| ((this.numeroReferenciaBachue != null)
				&& this.numeroReferenciaBachue.equals(other.getNumeroReferenciaBachue())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodigoEntidadRecaudadora() != null)
			_hashCode += getCodigoEntidadRecaudadora().hashCode();

		if(getCodigoPuntoRecaudoEntidad() != null)
			_hashCode += getCodigoPuntoRecaudoEntidad().hashCode();

		if(getCodigoTipoRecaudo() != null)
			_hashCode += getCodigoTipoRecaudo().hashCode();

		if(getFechaTransaccion() != null)
			_hashCode += getFechaTransaccion().hashCode();

		if(getFechaBancaria() != null)
			_hashCode += getFechaBancaria().hashCode();

		if(getCodigoTransaccionRecaudador() != null)
			_hashCode += getCodigoTransaccionRecaudador().hashCode();

		if(getMontoTransaccion() != null)
			_hashCode += getMontoTransaccion().hashCode();

		if(getNumeroReferenciaBachue() != null)
			_hashCode += getNumeroReferenciaBachue().hashCode();

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
