/**
 * TipoEntradaRegistrarPago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.registrarpago.v2;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarPago.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRegistrarPago implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1624616783500622425L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarPago.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
		        "tipoEntradaRegistrarPago"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoEntidadRecaudadora");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/registrarpago/v2",
		        "numeroReferenciaBachue"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc                = null;
	
	/** Propiedad codigo entidad recaudadora. */
	private java.lang.String     codigoEntidadRecaudadora;
	
	/** Propiedad codigo punto recaudo entidad. */
	private java.lang.String     codigoPuntoRecaudoEntidad;
	
	/** Propiedad codigo tipo recaudo. */
	private java.lang.String     codigoTipoRecaudo;
	
	/** Propiedad codigo transaccion recaudador. */
	private java.lang.String     codigoTransaccionRecaudador;
	
	/** Propiedad fecha bancaria. */
	private java.util.Calendar   fechaBancaria;
	
	/** Propiedad fecha transaccion. */
	private java.util.Calendar   fechaTransaccion;
	
	/** Propiedad monto transaccion. */
	private java.math.BigDecimal montoTransaccion;
	
	/** Propiedad numero referencia bachue. */
	private java.lang.String     numeroReferenciaBachue;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc              = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar pago.
	 */
	public TipoEntradaRegistrarPago()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar pago.
	 *
	 * @param codigoEntidadRecaudadora de codigo entidad recaudadora
	 * @param codigoPuntoRecaudoEntidad de codigo punto recaudo entidad
	 * @param codigoTipoRecaudo de codigo tipo recaudo
	 * @param fechaTransaccion de fecha transaccion
	 * @param fechaBancaria de fecha bancaria
	 * @param codigoTransaccionRecaudador de codigo transaccion recaudador
	 * @param montoTransaccion de monto transaccion
	 * @param numeroReferenciaBachue de numero referencia bachue
	 */
	public TipoEntradaRegistrarPago(
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
	 * Sets the codigoEntidadRecaudadora value for this TipoEntradaRegistrarPago.
	 *
	 * @param codigoEntidadRecaudadora de codigo entidad recaudadora
	 */
	public void setCodigoEntidadRecaudadora(java.lang.String codigoEntidadRecaudadora)
	{
		this.codigoEntidadRecaudadora = codigoEntidadRecaudadora;
	}

	/**
	 * Gets the codigoEntidadRecaudadora value for this TipoEntradaRegistrarPago.
	 *
	 * @return codigoEntidadRecaudadora
	 */
	public java.lang.String getCodigoEntidadRecaudadora()
	{
		return codigoEntidadRecaudadora;
	}

	/**
	 * Sets the codigoPuntoRecaudoEntidad value for this TipoEntradaRegistrarPago.
	 *
	 * @param codigoPuntoRecaudoEntidad de codigo punto recaudo entidad
	 */
	public void setCodigoPuntoRecaudoEntidad(java.lang.String codigoPuntoRecaudoEntidad)
	{
		this.codigoPuntoRecaudoEntidad = codigoPuntoRecaudoEntidad;
	}

	/**
	 * Gets the codigoPuntoRecaudoEntidad value for this TipoEntradaRegistrarPago.
	 *
	 * @return codigoPuntoRecaudoEntidad
	 */
	public java.lang.String getCodigoPuntoRecaudoEntidad()
	{
		return codigoPuntoRecaudoEntidad;
	}

	/**
	 * Sets the codigoTipoRecaudo value for this TipoEntradaRegistrarPago.
	 *
	 * @param codigoTipoRecaudo de codigo tipo recaudo
	 */
	public void setCodigoTipoRecaudo(java.lang.String codigoTipoRecaudo)
	{
		this.codigoTipoRecaudo = codigoTipoRecaudo;
	}

	/**
	 * Gets the codigoTipoRecaudo value for this TipoEntradaRegistrarPago.
	 *
	 * @return codigoTipoRecaudo
	 */
	public java.lang.String getCodigoTipoRecaudo()
	{
		return codigoTipoRecaudo;
	}

	/**
	 * Sets the codigoTransaccionRecaudador value for this TipoEntradaRegistrarPago.
	 *
	 * @param codigoTransaccionRecaudador de codigo transaccion recaudador
	 */
	public void setCodigoTransaccionRecaudador(java.lang.String codigoTransaccionRecaudador)
	{
		this.codigoTransaccionRecaudador = codigoTransaccionRecaudador;
	}

	/**
	 * Gets the codigoTransaccionRecaudador value for this TipoEntradaRegistrarPago.
	 *
	 * @return codigoTransaccionRecaudador
	 */
	public java.lang.String getCodigoTransaccionRecaudador()
	{
		return codigoTransaccionRecaudador;
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
	 * Sets the fechaBancaria value for this TipoEntradaRegistrarPago.
	 *
	 * @param fechaBancaria de fecha bancaria
	 */
	public void setFechaBancaria(java.util.Calendar fechaBancaria)
	{
		this.fechaBancaria = fechaBancaria;
	}

	/**
	 * Gets the fechaBancaria value for this TipoEntradaRegistrarPago.
	 *
	 * @return fechaBancaria
	 */
	public java.util.Calendar getFechaBancaria()
	{
		return fechaBancaria;
	}

	/**
	 * Sets the fechaTransaccion value for this TipoEntradaRegistrarPago.
	 *
	 * @param fechaTransaccion de fecha transaccion
	 */
	public void setFechaTransaccion(java.util.Calendar fechaTransaccion)
	{
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * Gets the fechaTransaccion value for this TipoEntradaRegistrarPago.
	 *
	 * @return fechaTransaccion
	 */
	public java.util.Calendar getFechaTransaccion()
	{
		return fechaTransaccion;
	}

	/**
	 * Sets the montoTransaccion value for this TipoEntradaRegistrarPago.
	 *
	 * @param montoTransaccion de monto transaccion
	 */
	public void setMontoTransaccion(java.math.BigDecimal montoTransaccion)
	{
		this.montoTransaccion = montoTransaccion;
	}

	/**
	 * Gets the montoTransaccion value for this TipoEntradaRegistrarPago.
	 *
	 * @return montoTransaccion
	 */
	public java.math.BigDecimal getMontoTransaccion()
	{
		return montoTransaccion;
	}

	/**
	 * Sets the numeroReferenciaBachue value for this TipoEntradaRegistrarPago.
	 *
	 * @param numeroReferenciaBachue de numero referencia bachue
	 */
	public void setNumeroReferenciaBachue(java.lang.String numeroReferenciaBachue)
	{
		this.numeroReferenciaBachue = numeroReferenciaBachue;
	}

	/**
	 * Gets the numeroReferenciaBachue value for this TipoEntradaRegistrarPago.
	 *
	 * @return numeroReferenciaBachue
	 */
	public java.lang.String getNumeroReferenciaBachue()
	{
		return numeroReferenciaBachue;
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
		if(!(obj instanceof TipoEntradaRegistrarPago))
			return false;

		TipoEntradaRegistrarPago other = (TipoEntradaRegistrarPago)obj;

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

	/** {@inheritdoc} */
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
}
