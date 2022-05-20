/**
 * TipoSalidaConsultarTarifaServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarTarifaServicio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarTarifaServicio implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2141489214381493033L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarTarifaServicio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoSalidaConsultarTarifaServicio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("serviciosTarifados");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "serviciosTarifados"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoServicioCTSO"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "servicioTarifado"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                 __equalsCalc       =
		null;
	
	/** Propiedad codigo mensaje. */
	private java.math.BigInteger                                                                                             codigoMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String                                                                                                 descripcionMensaje;
	
	/** Propiedad servicios tarifados. */
	private co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSO[] serviciosTarifados;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                          __hashCodeCalc     =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar tarifa servicio.
	 */
	public TipoSalidaConsultarTarifaServicio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar tarifa servicio.
	 *
	 * @param serviciosTarifados de servicios tarifados
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaConsultarTarifaServicio(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSO[] serviciosTarifados,
	    java.math.BigInteger                                                                                             codigoMensaje,
	    java.lang.String                                                                                                 descripcionMensaje
	)
	{
		this.serviciosTarifados     = serviciosTarifados;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarTarifaServicio.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarTarifaServicio.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarTarifaServicio.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarTarifaServicio.
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
	 * Sets the serviciosTarifados value for this TipoSalidaConsultarTarifaServicio.
	 *
	 * @param serviciosTarifados de servicios tarifados
	 */
	public void setServiciosTarifados(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSO[] serviciosTarifados
	)
	{
		this.serviciosTarifados = serviciosTarifados;
	}

	/**
	 * Gets the serviciosTarifados value for this TipoSalidaConsultarTarifaServicio.
	 *
	 * @return serviciosTarifados
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSO[] getServiciosTarifados()
	{
		return serviciosTarifados;
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
		if(!(obj instanceof TipoSalidaConsultarTarifaServicio))
			return false;

		TipoSalidaConsultarTarifaServicio other = (TipoSalidaConsultarTarifaServicio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.serviciosTarifados == null) && (other.getServiciosTarifados() == null))
				|| ((this.serviciosTarifados != null)
				&& java.util.Arrays.equals(this.serviciosTarifados, other.getServiciosTarifados())))
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

		if(getServiciosTarifados() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getServiciosTarifados()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getServiciosTarifados(), i);

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
