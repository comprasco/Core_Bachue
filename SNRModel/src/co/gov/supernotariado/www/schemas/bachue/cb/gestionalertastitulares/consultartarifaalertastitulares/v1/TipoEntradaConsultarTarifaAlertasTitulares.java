/**
 * TipoEntradaConsultarTarifaAlertasTitulares.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarTarifaAlertasTitulares.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarTarifaAlertasTitulares implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2829159564808768347L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarTarifaAlertasTitulares.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
		        "tipoEntradaConsultarTarifaAlertasTitulares"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cantidadAlertasNuevas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
		        "cantidadAlertasNuevas"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/consultartarifaalertastitulares/v1",
		        "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc          = null;
	
	/** Propiedad cantidad alertas nuevas. */
	private java.lang.String cantidadAlertasNuevas;
	
	/** Propiedad modulo. */
	private java.lang.String modulo;
	
	/** Propiedad numero documento. */
	private java.lang.String numeroDocumento;
	
	/** Propiedad tipo documento. */
	private java.lang.String tipoDocumento;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc        = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar tarifa alertas titulares.
	 */
	public TipoEntradaConsultarTarifaAlertasTitulares()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar tarifa alertas titulares.
	 *
	 * @param modulo de modulo
	 * @param cantidadAlertasNuevas de cantidad alertas nuevas
	 * @param tipoDocumento de tipo documento
	 * @param numeroDocumento de numero documento
	 */
	public TipoEntradaConsultarTarifaAlertasTitulares(
	    java.lang.String modulo, java.lang.String cantidadAlertasNuevas, java.lang.String tipoDocumento,
	    java.lang.String numeroDocumento
	)
	{
		this.modulo                    = modulo;
		this.cantidadAlertasNuevas     = cantidadAlertasNuevas;
		this.tipoDocumento             = tipoDocumento;
		this.numeroDocumento           = numeroDocumento;
	}

	/**
	 * Sets the cantidadAlertasNuevas value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @param cantidadAlertasNuevas de cantidad alertas nuevas
	 */
	public void setCantidadAlertasNuevas(java.lang.String cantidadAlertasNuevas)
	{
		this.cantidadAlertasNuevas = cantidadAlertasNuevas;
	}

	/**
	 * Gets the cantidadAlertasNuevas value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @return cantidadAlertasNuevas
	 */
	public java.lang.String getCantidadAlertasNuevas()
	{
		return cantidadAlertasNuevas;
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
	 * Sets the modulo value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the numeroDocumento value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @param numeroDocumento de numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
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
	 * Sets the tipoDocumento value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @param tipoDocumento de tipo documento
	 */
	public void setTipoDocumento(java.lang.String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the tipoDocumento value for this TipoEntradaConsultarTarifaAlertasTitulares.
	 *
	 * @return tipoDocumento
	 */
	public java.lang.String getTipoDocumento()
	{
		return tipoDocumento;
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
		if(!(obj instanceof TipoEntradaConsultarTarifaAlertasTitulares))
			return false;

		TipoEntradaConsultarTarifaAlertasTitulares other = (TipoEntradaConsultarTarifaAlertasTitulares)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.modulo == null) && (other.getModulo() == null))
				|| ((this.modulo != null) && this.modulo.equals(other.getModulo())))
				&& (((this.cantidadAlertasNuevas == null) && (other.getCantidadAlertasNuevas() == null))
				|| ((this.cantidadAlertasNuevas != null)
				&& this.cantidadAlertasNuevas.equals(other.getCantidadAlertasNuevas())))
				&& (((this.tipoDocumento == null) && (other.getTipoDocumento() == null))
				|| ((this.tipoDocumento != null) && this.tipoDocumento.equals(other.getTipoDocumento())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())));
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

		if(getModulo() != null)
			_hashCode += getModulo().hashCode();

		if(getCantidadAlertasNuevas() != null)
			_hashCode += getCantidadAlertasNuevas().hashCode();

		if(getTipoDocumento() != null)
			_hashCode += getTipoDocumento().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
