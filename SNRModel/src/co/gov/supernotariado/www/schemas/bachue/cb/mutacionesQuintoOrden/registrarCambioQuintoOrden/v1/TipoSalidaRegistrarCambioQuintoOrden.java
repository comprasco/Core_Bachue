/**
 * TipoSalidaRegistrarCambioQuintoOrden.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaRegistrarCambioQuintoOrden.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaRegistrarCambioQuintoOrden implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4690277406516418401L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaRegistrarCambioQuintoOrden.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "tipoSalidaRegistrarCambioQuintoOrden"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "tipoPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc            = null;
	
	/** Propiedad cod mensaje. */
	private java.math.BigInteger codMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String     descripcionMensaje;
	
	/** Propiedad num identificacion predio. */
	private java.lang.String     numIdentificacionPredio;
	
	/** Propiedad tipo predio. */
	private java.lang.String     tipoPredio;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc          = false;

	/**
	 * Instancia un nuevo objeto tipo salida registrar cambio quinto orden.
	 */
	public TipoSalidaRegistrarCambioQuintoOrden()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida registrar cambio quinto orden.
	 *
	 * @param numIdentificacionPredio de num identificacion predio
	 * @param tipoPredio de tipo predio
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaRegistrarCambioQuintoOrden(
	    java.lang.String numIdentificacionPredio, java.lang.String tipoPredio, java.math.BigInteger codMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.numIdentificacionPredio     = numIdentificacionPredio;
		this.tipoPredio                  = tipoPredio;
		this.codMensaje                  = codMensaje;
		this.descripcionMensaje          = descripcionMensaje;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaRegistrarCambioQuintoOrden.
	 *
	 * @param codMensaje de cod mensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaRegistrarCambioQuintoOrden.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaRegistrarCambioQuintoOrden.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaRegistrarCambioQuintoOrden.
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
	 * Sets the numIdentificacionPredio value for this TipoSalidaRegistrarCambioQuintoOrden.
	 *
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoSalidaRegistrarCambioQuintoOrden.
	 *
	 * @return numIdentificacionPredio
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
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
	 * Sets the tipoPredio value for this TipoSalidaRegistrarCambioQuintoOrden.
	 *
	 * @param tipoPredio de tipo predio
	 */
	public void setTipoPredio(java.lang.String tipoPredio)
	{
		this.tipoPredio = tipoPredio;
	}

	/**
	 * Gets the tipoPredio value for this TipoSalidaRegistrarCambioQuintoOrden.
	 *
	 * @return tipoPredio
	 */
	public java.lang.String getTipoPredio()
	{
		return tipoPredio;
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
		if(!(obj instanceof TipoSalidaRegistrarCambioQuintoOrden))
			return false;

		TipoSalidaRegistrarCambioQuintoOrden other = (TipoSalidaRegistrarCambioQuintoOrden)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numIdentificacionPredio == null) && (other.getNumIdentificacionPredio() == null))
				|| ((this.numIdentificacionPredio != null)
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())))
				&& (((this.tipoPredio == null) && (other.getTipoPredio() == null))
				|| ((this.tipoPredio != null) && this.tipoPredio.equals(other.getTipoPredio())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
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

		if(getNumIdentificacionPredio() != null)
			_hashCode += getNumIdentificacionPredio().hashCode();

		if(getTipoPredio() != null)
			_hashCode += getTipoPredio().hashCode();

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
