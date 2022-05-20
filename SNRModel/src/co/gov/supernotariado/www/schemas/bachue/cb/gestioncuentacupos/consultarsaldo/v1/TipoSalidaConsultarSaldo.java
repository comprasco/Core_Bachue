/**
 * TipoSalidaConsultarSaldo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1;



/**
 * The Class TipoSalidaConsultarSaldo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarSaldo implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3551306619937577349L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarSaldo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "tipoSalidaConsultarSaldo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("saldoActual");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "saldoActual"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("ultimaRecargaFecha");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "ultimaRecargaFecha"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("ultimaRecargaValor");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "ultimaRecargaValor"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The codigo mensaje. */
	private java.math.BigInteger codigoMensaje;

	/** The descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** The saldo actual. */
	private java.math.BigDecimal saldoActual;

	/** The ultima recarga fecha. */
	private java.util.Calendar ultimaRecargaFecha;

	/** The ultima recarga valor. */
	private java.math.BigDecimal ultimaRecargaValor;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo salida consultar saldo.
	 */
	public TipoSalidaConsultarSaldo()
	{
	}

	/**
	 * Instantiates a new tipo salida consultar saldo.
	 *
	 * @param saldoActual the saldo actual
	 * @param ultimaRecargaFecha the ultima recarga fecha
	 * @param ultimaRecargaValor the ultima recarga valor
	 * @param codigoMensaje the codigo mensaje
	 * @param descripcionMensaje the descripcion mensaje
	 */
	public TipoSalidaConsultarSaldo(
	    java.math.BigDecimal saldoActual, java.util.Calendar ultimaRecargaFecha, java.math.BigDecimal ultimaRecargaValor,
	    java.math.BigInteger codigoMensaje, java.lang.String descripcionMensaje
	)
	{
		this.saldoActual            = saldoActual;
		this.ultimaRecargaFecha     = ultimaRecargaFecha;
		this.ultimaRecargaValor     = ultimaRecargaValor;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the saldoActual value for this TipoSalidaConsultarSaldo.
	 *
	 * @return saldoActual
	 */
	public java.math.BigDecimal getSaldoActual()
	{
		return saldoActual;
	}

	/**
	 * Sets the saldoActual value for this TipoSalidaConsultarSaldo.
	 *
	 * @param saldoActual the new saldo actual
	 */
	public void setSaldoActual(java.math.BigDecimal saldoActual)
	{
		this.saldoActual = saldoActual;
	}

	/**
	 * Gets the ultimaRecargaFecha value for this TipoSalidaConsultarSaldo.
	 *
	 * @return ultimaRecargaFecha
	 */
	public java.util.Calendar getUltimaRecargaFecha()
	{
		return ultimaRecargaFecha;
	}

	/**
	 * Sets the ultimaRecargaFecha value for this TipoSalidaConsultarSaldo.
	 *
	 * @param ultimaRecargaFecha the new ultima recarga fecha
	 */
	public void setUltimaRecargaFecha(java.util.Calendar ultimaRecargaFecha)
	{
		this.ultimaRecargaFecha = ultimaRecargaFecha;
	}

	/**
	 * Gets the ultimaRecargaValor value for this TipoSalidaConsultarSaldo.
	 *
	 * @return ultimaRecargaValor
	 */
	public java.math.BigDecimal getUltimaRecargaValor()
	{
		return ultimaRecargaValor;
	}

	/**
	 * Sets the ultimaRecargaValor value for this TipoSalidaConsultarSaldo.
	 *
	 * @param ultimaRecargaValor the new ultima recarga valor
	 */
	public void setUltimaRecargaValor(java.math.BigDecimal ultimaRecargaValor)
	{
		this.ultimaRecargaValor = ultimaRecargaValor;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarSaldo.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarSaldo.
	 *
	 * @param codigoMensaje the new codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarSaldo.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarSaldo.
	 *
	 * @param descripcionMensaje the new descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarSaldo))
			return false;

		TipoSalidaConsultarSaldo other = (TipoSalidaConsultarSaldo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.saldoActual == null) && (other.getSaldoActual() == null))
				|| ((this.saldoActual != null) && this.saldoActual.equals(other.getSaldoActual())))
				&& (((this.ultimaRecargaFecha == null) && (other.getUltimaRecargaFecha() == null))
				|| ((this.ultimaRecargaFecha != null) && this.ultimaRecargaFecha.equals(other.getUltimaRecargaFecha())))
				&& (((this.ultimaRecargaValor == null) && (other.getUltimaRecargaValor() == null))
				|| ((this.ultimaRecargaValor != null) && this.ultimaRecargaValor.equals(other.getUltimaRecargaValor())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getSaldoActual() != null)
			_hashCode += getSaldoActual().hashCode();

		if(getUltimaRecargaFecha() != null)
			_hashCode += getUltimaRecargaFecha().hashCode();

		if(getUltimaRecargaValor() != null)
			_hashCode += getUltimaRecargaValor().hashCode();

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
