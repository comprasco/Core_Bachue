/**
 * TipoSalidaObtenerClavePDFLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.obtenerclavepdfliquidacion.v2;


/**
 * Clase que contiene todos las propiedades TipoSalidaObtenerClavePDFLiquidacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaObtenerClavePDFLiquidacion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4533856803303056666L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaObtenerClavePDFLiquidacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
		        "tipoSalidaObtenerClavePDFLiquidacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("claveDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
		        "claveDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
		        "nombreDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/obtenerclavepdfliquidacion/v2",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc       = null;
	
	/** Propiedad clave documento. */
	private java.lang.String     claveDocumento;
	
	/** Propiedad codigo mensaje. */
	private java.math.BigInteger codigoMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String     descripcionMensaje;
	
	/** Propiedad nombre documento. */
	private java.lang.String     nombreDocumento;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo salida obtener clave PDF liquidacion.
	 */
	public TipoSalidaObtenerClavePDFLiquidacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida obtener clave PDF liquidacion.
	 *
	 * @param claveDocumento de clave documento
	 * @param nombreDocumento de nombre documento
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaObtenerClavePDFLiquidacion(
	    java.lang.String claveDocumento, java.lang.String nombreDocumento, java.math.BigInteger codigoMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.claveDocumento         = claveDocumento;
		this.nombreDocumento        = nombreDocumento;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the claveDocumento value for this TipoSalidaObtenerClavePDFLiquidacion.
	 *
	 * @param claveDocumento de clave documento
	 */
	public void setClaveDocumento(java.lang.String claveDocumento)
	{
		this.claveDocumento = claveDocumento;
	}

	/**
	 * Gets the claveDocumento value for this TipoSalidaObtenerClavePDFLiquidacion.
	 *
	 * @return claveDocumento
	 */
	public java.lang.String getClaveDocumento()
	{
		return claveDocumento;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaObtenerClavePDFLiquidacion.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaObtenerClavePDFLiquidacion.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaObtenerClavePDFLiquidacion.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaObtenerClavePDFLiquidacion.
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
	 * Sets the nombreDocumento value for this TipoSalidaObtenerClavePDFLiquidacion.
	 *
	 * @param nombreDocumento de nombre documento
	 */
	public void setNombreDocumento(java.lang.String nombreDocumento)
	{
		this.nombreDocumento = nombreDocumento;
	}

	/**
	 * Gets the nombreDocumento value for this TipoSalidaObtenerClavePDFLiquidacion.
	 *
	 * @return nombreDocumento
	 */
	public java.lang.String getNombreDocumento()
	{
		return nombreDocumento;
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
		if(!(obj instanceof TipoSalidaObtenerClavePDFLiquidacion))
			return false;

		TipoSalidaObtenerClavePDFLiquidacion other = (TipoSalidaObtenerClavePDFLiquidacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.claveDocumento == null) && (other.getClaveDocumento() == null))
				|| ((this.claveDocumento != null) && this.claveDocumento.equals(other.getClaveDocumento())))
				&& (((this.nombreDocumento == null) && (other.getNombreDocumento() == null))
				|| ((this.nombreDocumento != null) && this.nombreDocumento.equals(other.getNombreDocumento())))
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

		if(getClaveDocumento() != null)
			_hashCode += getClaveDocumento().hashCode();

		if(getNombreDocumento() != null)
			_hashCode += getNombreDocumento().hashCode();

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
