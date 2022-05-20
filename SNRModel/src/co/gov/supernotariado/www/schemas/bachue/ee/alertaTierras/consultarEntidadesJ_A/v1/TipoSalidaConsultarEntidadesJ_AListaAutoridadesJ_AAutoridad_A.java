/**
 * TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarEntidadesJ_A.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5769084940210879457L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1",
		        ">>tipoSalidaConsultarEntidadesJ_A>listaAutoridadesJ_A>autoridad_A"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoAutoridad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1",
		        "codigoAutoridad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreAutoridad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1",
		        "nombreAutoridad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("esAgenciaNacionalTierras");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarEntidadesJ_A/v1",
		        "esAgenciaNacionalTierras"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo autoridad. */
	private java.lang.String codigoAutoridad;

	/** Propiedad es agencia nacional tierras. */
	private java.lang.String esAgenciaNacionalTierras;

	/** Propiedad nombre autoridad. */
	private java.lang.String nombreAutoridad;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar entidades J A lista autoridades J A autoridad A.
	 */
	public TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar entidades J A lista autoridades J A autoridad A.
	 *
	 * @param codigoAutoridad de codigo autoridad
	 * @param nombreAutoridad de nombre autoridad
	 * @param esAgenciaNacionalTierras de es agencia nacional tierras
	 */
	public TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A(
	    java.lang.String codigoAutoridad, java.lang.String nombreAutoridad, java.lang.String esAgenciaNacionalTierras
	)
	{
		this.codigoAutoridad              = codigoAutoridad;
		this.nombreAutoridad              = nombreAutoridad;
		this.esAgenciaNacionalTierras     = esAgenciaNacionalTierras;
	}

	/**
	 * Gets the codigoAutoridad value for this TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.
	 *
	 * @return codigoAutoridad
	 */
	public java.lang.String getCodigoAutoridad()
	{
		return codigoAutoridad;
	}

	/**
	 * Sets the codigoAutoridad value for this TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.
	 *
	 * @param codigoAutoridad de codigo autoridad
	 */
	public void setCodigoAutoridad(java.lang.String codigoAutoridad)
	{
		this.codigoAutoridad = codigoAutoridad;
	}

	/**
	 * Gets the nombreAutoridad value for this TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.
	 *
	 * @return nombreAutoridad
	 */
	public java.lang.String getNombreAutoridad()
	{
		return nombreAutoridad;
	}

	/**
	 * Sets the nombreAutoridad value for this TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.
	 *
	 * @param nombreAutoridad de nombre autoridad
	 */
	public void setNombreAutoridad(java.lang.String nombreAutoridad)
	{
		this.nombreAutoridad = nombreAutoridad;
	}

	/**
	 * Gets the esAgenciaNacionalTierras value for this TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.
	 *
	 * @return esAgenciaNacionalTierras
	 */
	public java.lang.String getEsAgenciaNacionalTierras()
	{
		return esAgenciaNacionalTierras;
	}

	/**
	 * Sets the esAgenciaNacionalTierras value for this TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A.
	 *
	 * @param esAgenciaNacionalTierras de es agencia nacional tierras
	 */
	public void setEsAgenciaNacionalTierras(java.lang.String esAgenciaNacionalTierras)
	{
		this.esAgenciaNacionalTierras = esAgenciaNacionalTierras;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A))
			return false;

		TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A other = (TipoSalidaConsultarEntidadesJ_AListaAutoridadesJ_AAutoridad_A)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoAutoridad == null) && (other.getCodigoAutoridad() == null))
				|| ((this.codigoAutoridad != null) && this.codigoAutoridad.equals(other.getCodigoAutoridad())))
				&& (((this.nombreAutoridad == null) && (other.getNombreAutoridad() == null))
				|| ((this.nombreAutoridad != null) && this.nombreAutoridad.equals(other.getNombreAutoridad())))
				&& (((this.esAgenciaNacionalTierras == null) && (other.getEsAgenciaNacionalTierras() == null))
				|| ((this.esAgenciaNacionalTierras != null)
				&& this.esAgenciaNacionalTierras.equals(other.getEsAgenciaNacionalTierras())));
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

		if(getCodigoAutoridad() != null)
			_hashCode += getCodigoAutoridad().hashCode();

		if(getNombreAutoridad() != null)
			_hashCode += getNombreAutoridad().hashCode();

		if(getEsAgenciaNacionalTierras() != null)
			_hashCode += getEsAgenciaNacionalTierras().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
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
}
