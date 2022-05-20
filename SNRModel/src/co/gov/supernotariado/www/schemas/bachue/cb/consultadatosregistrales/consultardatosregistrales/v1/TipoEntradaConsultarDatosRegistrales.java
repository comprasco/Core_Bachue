/**
 * TipoEntradaConsultarDatosRegistrales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarDatosRegistrales.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarDatosRegistrales implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6441618216144763319L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarDatosRegistrales.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "tipoEntradaConsultarDatosRegistrales"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("criterioBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "criterioBusqueda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorCriterioBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "valorCriterioBusqueda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc          = null;
	
	/** Propiedad criterio busqueda. */
	private java.lang.String criterioBusqueda;
	
	/** Propiedad modulo. */
	private java.lang.String modulo;
	
	/** Propiedad valor criterio busqueda. */
	private java.lang.String valorCriterioBusqueda;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc        = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar datos registrales.
	 */
	public TipoEntradaConsultarDatosRegistrales()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar datos registrales.
	 *
	 * @param modulo de modulo
	 * @param criterioBusqueda de criterio busqueda
	 * @param valorCriterioBusqueda de valor criterio busqueda
	 */
	public TipoEntradaConsultarDatosRegistrales(
	    java.lang.String modulo, java.lang.String criterioBusqueda, java.lang.String valorCriterioBusqueda
	)
	{
		this.modulo                    = modulo;
		this.criterioBusqueda          = criterioBusqueda;
		this.valorCriterioBusqueda     = valorCriterioBusqueda;
	}

	/**
	 * Sets the criterioBusqueda value for this TipoEntradaConsultarDatosRegistrales.
	 *
	 * @param criterioBusqueda de criterio busqueda
	 */
	public void setCriterioBusqueda(java.lang.String criterioBusqueda)
	{
		this.criterioBusqueda = criterioBusqueda;
	}

	/**
	 * Gets the criterioBusqueda value for this TipoEntradaConsultarDatosRegistrales.
	 *
	 * @return criterioBusqueda
	 */
	public java.lang.String getCriterioBusqueda()
	{
		return criterioBusqueda;
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
	 * Sets the modulo value for this TipoEntradaConsultarDatosRegistrales.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarDatosRegistrales.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
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
	 * Sets the valorCriterioBusqueda value for this TipoEntradaConsultarDatosRegistrales.
	 *
	 * @param valorCriterioBusqueda de valor criterio busqueda
	 */
	public void setValorCriterioBusqueda(java.lang.String valorCriterioBusqueda)
	{
		this.valorCriterioBusqueda = valorCriterioBusqueda;
	}

	/**
	 * Gets the valorCriterioBusqueda value for this TipoEntradaConsultarDatosRegistrales.
	 *
	 * @return valorCriterioBusqueda
	 */
	public java.lang.String getValorCriterioBusqueda()
	{
		return valorCriterioBusqueda;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarDatosRegistrales))
			return false;

		TipoEntradaConsultarDatosRegistrales other = (TipoEntradaConsultarDatosRegistrales)obj;

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
				&& (((this.criterioBusqueda == null) && (other.getCriterioBusqueda() == null))
				|| ((this.criterioBusqueda != null) && this.criterioBusqueda.equals(other.getCriterioBusqueda())))
				&& (((this.valorCriterioBusqueda == null) && (other.getValorCriterioBusqueda() == null))
				|| ((this.valorCriterioBusqueda != null)
				&& this.valorCriterioBusqueda.equals(other.getValorCriterioBusqueda())));
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

		if(getCriterioBusqueda() != null)
			_hashCode += getCriterioBusqueda().hashCode();

		if(getValorCriterioBusqueda() != null)
			_hashCode += getValorCriterioBusqueda().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
