/**
 * TipoTiposActo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1;


/**
 * Clase que contiene todos las propiedades TipoTiposActo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoTiposActo implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4840492271645773128L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoTiposActo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "tipoTiposActo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "descripcionActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1", "valorActo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad descripcion acto. */
	/* Descripción del Acto del trámite en curso */
	private java.lang.String descripcionActo;

	/** Propiedad valor acto. */
	/* Valor del Acto del trámite en curso */
	private java.math.BigDecimal valorActo;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo tipos acto.
	 */
	public TipoTiposActo()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo tipos acto.
	 *
	 * @param descripcionActo de descripcion acto
	 * @param valorActo de valor acto
	 */
	public TipoTiposActo(java.lang.String descripcionActo, java.math.BigDecimal valorActo)
	{
		this.descripcionActo     = descripcionActo;
		this.valorActo           = valorActo;
	}

	/**
	 * Sets the descripcionActo value for this TipoTiposActo.
	 *
	 * @param descripcionActo   * Descripción del Acto del trámite en curso
	 */
	public void setDescripcionActo(java.lang.String descripcionActo)
	{
		this.descripcionActo = descripcionActo;
	}

	/**
	 * Gets the descripcionActo value for this TipoTiposActo.
	 *
	 * @return descripcionActo   * Descripción del Acto del trámite en curso
	 */
	public java.lang.String getDescripcionActo()
	{
		return descripcionActo;
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
	 * Return type metadata object.
	 *
	 * @return el valor de type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the valorActo value for this TipoTiposActo.
	 *
	 * @param valorActo   * Valor del Acto del trámite en curso
	 */
	public void setValorActo(java.math.BigDecimal valorActo)
	{
		this.valorActo = valorActo;
	}

	/**
	 * Gets the valorActo value for this TipoTiposActo.
	 *
	 * @return valorActo   * Valor del Acto del trámite en curso
	 */
	public java.math.BigDecimal getValorActo()
	{
		return valorActo;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoTiposActo))
			return false;

		TipoTiposActo other = (TipoTiposActo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.descripcionActo == null) && (other.getDescripcionActo() == null))
				|| ((this.descripcionActo != null) && this.descripcionActo.equals(other.getDescripcionActo())))
				&& (((this.valorActo == null) && (other.getValorActo() == null))
				|| ((this.valorActo != null) && this.valorActo.equals(other.getValorActo())));
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

		if(getDescripcionActo() != null)
			_hashCode += getDescripcionActo().hashCode();

		if(getValorActo() != null)
			_hashCode += getValorActo().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
