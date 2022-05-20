/**
 * TipoEntradaRegistrarLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registrarliquidacion.v2;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarLiquidacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRegistrarLiquidacion implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1333590892809050137L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarLiquidacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registrarliquidacion/v2",
		        "tipoEntradaRegistrarLiquidacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReferencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registrarliquidacion/v2",
		        "numeroReferencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registrarliquidacion/v2",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorTotalServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registrarliquidacion/v2",
		        "valorTotalServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaLiquidacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registrarliquidacion/v2",
		        "fechaLiquidacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaVencimiento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registrarliquidacion/v2",
		        "fechaVencimiento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("canalOrigenServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registrarliquidacion/v2",
		        "canalOrigenServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad canal origen servicio. */
	private java.lang.String canalOrigenServicio;

	/** Propiedad fecha liquidacion. */
	private java.util.Calendar fechaLiquidacion;

	/** Propiedad fecha vencimiento. */
	private java.util.Calendar fechaVencimiento;

	/** Propiedad nir. */
	private java.lang.String nir;

	/** Propiedad numero referencia. */
	private java.lang.String numeroReferencia;

	/** Propiedad valor total servicio. */
	private java.math.BigDecimal valorTotalServicio;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar liquidacion.
	 */
	public TipoEntradaRegistrarLiquidacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar liquidacion.
	 *
	 * @param numeroReferencia de numero referencia
	 * @param nir de nir
	 * @param valorTotalServicio de valor total servicio
	 * @param fechaLiquidacion de fecha liquidacion
	 * @param fechaVencimiento de fecha vencimiento
	 */
	public TipoEntradaRegistrarLiquidacion(
	    java.lang.String numeroReferencia, java.lang.String nir, java.math.BigDecimal valorTotalServicio,
	    java.util.Calendar fechaLiquidacion, java.util.Calendar fechaVencimiento, java.lang.String canalOrigenServicio
	)
	{
		this.numeroReferencia        = numeroReferencia;
		this.nir                     = nir;
		this.valorTotalServicio      = valorTotalServicio;
		this.fechaLiquidacion        = fechaLiquidacion;
		this.fechaVencimiento        = fechaVencimiento;
		this.canalOrigenServicio     = canalOrigenServicio;
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
	 * Sets the fechaLiquidacion value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @param fechaLiquidacion de fecha liquidacion
	 */
	public void setFechaLiquidacion(java.util.Calendar fechaLiquidacion)
	{
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the fechaLiquidacion value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @return fechaLiquidacion
	 */
	public java.util.Calendar getFechaLiquidacion()
	{
		return fechaLiquidacion;
	}

	/**
	 * Sets the fechaVencimiento value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @param fechaVencimiento de fecha vencimiento
	 */
	public void setFechaVencimiento(java.util.Calendar fechaVencimiento)
	{
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the fechaVencimiento value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @return fechaVencimiento
	 */
	public java.util.Calendar getFechaVencimiento()
	{
		return fechaVencimiento;
	}

	/**
	 * Sets the nir value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
	}

	/**
	 * Sets the numeroReferencia value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @param numeroReferencia de numero referencia
	 */
	public void setNumeroReferencia(java.lang.String numeroReferencia)
	{
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the numeroReferencia value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @return numeroReferencia
	 */
	public java.lang.String getNumeroReferencia()
	{
		return numeroReferencia;
	}

	/**
	 * Modifica el valor de CanalOrigenServicio.
	 *
	 * @param canalOrigenServicio de canal origen servicio
	 */
	public void setCanalOrigenServicio(java.lang.String canalOrigenServicio)
	{
		this.canalOrigenServicio = canalOrigenServicio;
	}

	/**
	 * Retorna Objeto o variable de valor canal origen servicio.
	 *
	 * @return el valor de canal origen servicio
	 */
	public java.lang.String getCanalOrigenServicio()
	{
		return canalOrigenServicio;
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

	/**
	 * Sets the valorTotalServicio value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @param valorTotalServicio de valor total servicio
	 */
	public void setValorTotalServicio(java.math.BigDecimal valorTotalServicio)
	{
		this.valorTotalServicio = valorTotalServicio;
	}

	/**
	 * Gets the valorTotalServicio value for this TipoEntradaRegistrarLiquidacion.
	 *
	 * @return valorTotalServicio
	 */
	public java.math.BigDecimal getValorTotalServicio()
	{
		return valorTotalServicio;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param obj de obj
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaRegistrarLiquidacion))
			return false;

		TipoEntradaRegistrarLiquidacion other = (TipoEntradaRegistrarLiquidacion)obj;

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
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.valorTotalServicio == null) && (other.getValorTotalServicio() == null))
				|| ((this.valorTotalServicio != null) && this.valorTotalServicio.equals(other.getValorTotalServicio())))
				&& (((this.fechaLiquidacion == null) && (other.getFechaLiquidacion() == null))
				|| ((this.fechaLiquidacion != null) && this.fechaLiquidacion.equals(other.getFechaLiquidacion())))
				&& (((this.fechaVencimiento == null) && (other.getFechaVencimiento() == null))
				|| ((this.fechaVencimiento != null) && this.fechaVencimiento.equals(other.getFechaVencimiento())))
				&& (((this.canalOrigenServicio == null) && (other.getCanalOrigenServicio() == null))
				|| ((this.canalOrigenServicio != null)
				&& this.canalOrigenServicio.equals(other.getCanalOrigenServicio())));
		__equalsCalc     = null;

		return _equals;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de int
	 */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getNumeroReferencia() != null)
			_hashCode += getNumeroReferencia().hashCode();

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getValorTotalServicio() != null)
			_hashCode += getValorTotalServicio().hashCode();

		if(getFechaLiquidacion() != null)
			_hashCode += getFechaLiquidacion().hashCode();

		if(getFechaVencimiento() != null)
			_hashCode += getFechaVencimiento().hashCode();

		if(getCanalOrigenServicio() != null)
			_hashCode += getCanalOrigenServicio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
