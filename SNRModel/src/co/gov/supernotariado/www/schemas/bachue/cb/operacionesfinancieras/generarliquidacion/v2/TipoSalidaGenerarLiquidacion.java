/**
 * TipoSalidaGenerarLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2;


/**
 * Clase que contiene todos las propiedades TipoSalidaGenerarLiquidacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaGenerarLiquidacion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1272364016386654284L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaGenerarLiquidacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoSalidaGenerarLiquidacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReferencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaLiquidacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "fechaVencimiento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorTotalServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "valorTotalServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("serviciosLiquidados");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "serviciosLiquidados"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoServicioGLO"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "servicioLiquidado"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                           __equalsCalc        =
		null;
	
	/** Propiedad codigo mensaje. */
	private java.math.BigInteger                                                                                       codigoMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String                                                                                           descripcionMensaje;
	
	/** Propiedad fecha liquidacion. */
	private java.util.Calendar                                                                                         fechaLiquidacion;
	
	/** Propiedad fecha vencimiento. */
	private java.util.Calendar                                                                                         fechaVencimiento;
	
	/** Propiedad nir. */
	private java.lang.String                                                                                           nir;
	
	/** Propiedad numero referencia. */
	private java.lang.String                                                                                           numeroReferencia;
	
	/** Propiedad valor total servicio. */
	private java.math.BigDecimal                                                                                       valorTotalServicio;
	
	/** Propiedad servicios liquidados. */
	private co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLO[] serviciosLiquidados;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                    __hashCodeCalc      =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida generar liquidacion.
	 */
	public TipoSalidaGenerarLiquidacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida generar liquidacion.
	 *
	 * @param numeroReferencia de numero referencia
	 * @param nir de nir
	 * @param fechaLiquidacion de fecha liquidacion
	 * @param fechaVencimiento de fecha vencimiento
	 * @param valorTotalServicio de valor total servicio
	 * @param serviciosLiquidados de servicios liquidados
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaGenerarLiquidacion(
	    java.lang.String numeroReferencia, java.lang.String nir, java.util.Calendar fechaLiquidacion,
	    java.util.Calendar fechaVencimiento, java.math.BigDecimal valorTotalServicio,
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLO[] serviciosLiquidados,
	    java.math.BigInteger codigoMensaje, java.lang.String descripcionMensaje
	)
	{
		this.numeroReferencia        = numeroReferencia;
		this.nir                     = nir;
		this.fechaLiquidacion        = fechaLiquidacion;
		this.fechaVencimiento        = fechaVencimiento;
		this.valorTotalServicio      = valorTotalServicio;
		this.serviciosLiquidados     = serviciosLiquidados;
		this.codigoMensaje           = codigoMensaje;
		this.descripcionMensaje      = descripcionMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
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
	 * Sets the fechaLiquidacion value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param fechaLiquidacion de fecha liquidacion
	 */
	public void setFechaLiquidacion(java.util.Calendar fechaLiquidacion)
	{
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the fechaLiquidacion value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return fechaLiquidacion
	 */
	public java.util.Calendar getFechaLiquidacion()
	{
		return fechaLiquidacion;
	}

	/**
	 * Sets the fechaVencimiento value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param fechaVencimiento de fecha vencimiento
	 */
	public void setFechaVencimiento(java.util.Calendar fechaVencimiento)
	{
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the fechaVencimiento value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return fechaVencimiento
	 */
	public java.util.Calendar getFechaVencimiento()
	{
		return fechaVencimiento;
	}

	/**
	 * Sets the nir value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
	}

	/**
	 * Sets the numeroReferencia value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param numeroReferencia de numero referencia
	 */
	public void setNumeroReferencia(java.lang.String numeroReferencia)
	{
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the numeroReferencia value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return numeroReferencia
	 */
	public java.lang.String getNumeroReferencia()
	{
		return numeroReferencia;
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
	 * Sets the serviciosLiquidados value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param serviciosLiquidados de servicios liquidados
	 */
	public void setServiciosLiquidados(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLO[] serviciosLiquidados
	)
	{
		this.serviciosLiquidados = serviciosLiquidados;
	}

	/**
	 * Gets the serviciosLiquidados value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return serviciosLiquidados
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLO[] getServiciosLiquidados()
	{
		return serviciosLiquidados;
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
	 * Sets the valorTotalServicio value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @param valorTotalServicio de valor total servicio
	 */
	public void setValorTotalServicio(java.math.BigDecimal valorTotalServicio)
	{
		this.valorTotalServicio = valorTotalServicio;
	}

	/**
	 * Gets the valorTotalServicio value for this TipoSalidaGenerarLiquidacion.
	 *
	 * @return valorTotalServicio
	 */
	public java.math.BigDecimal getValorTotalServicio()
	{
		return valorTotalServicio;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaGenerarLiquidacion))
			return false;

		TipoSalidaGenerarLiquidacion other = (TipoSalidaGenerarLiquidacion)obj;

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
				&& (((this.fechaLiquidacion == null) && (other.getFechaLiquidacion() == null))
				|| ((this.fechaLiquidacion != null) && this.fechaLiquidacion.equals(other.getFechaLiquidacion())))
				&& (((this.fechaVencimiento == null) && (other.getFechaVencimiento() == null))
				|| ((this.fechaVencimiento != null) && this.fechaVencimiento.equals(other.getFechaVencimiento())))
				&& (((this.valorTotalServicio == null) && (other.getValorTotalServicio() == null))
				|| ((this.valorTotalServicio != null) && this.valorTotalServicio.equals(other.getValorTotalServicio())))
				&& (((this.serviciosLiquidados == null) && (other.getServiciosLiquidados() == null))
				|| ((this.serviciosLiquidados != null)
				&& java.util.Arrays.equals(this.serviciosLiquidados, other.getServiciosLiquidados())))
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

		if(getNumeroReferencia() != null)
			_hashCode += getNumeroReferencia().hashCode();

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getFechaLiquidacion() != null)
			_hashCode += getFechaLiquidacion().hashCode();

		if(getFechaVencimiento() != null)
			_hashCode += getFechaVencimiento().hashCode();

		if(getValorTotalServicio() != null)
			_hashCode += getValorTotalServicio().hashCode();

		if(getServiciosLiquidados() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getServiciosLiquidados()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getServiciosLiquidados(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
