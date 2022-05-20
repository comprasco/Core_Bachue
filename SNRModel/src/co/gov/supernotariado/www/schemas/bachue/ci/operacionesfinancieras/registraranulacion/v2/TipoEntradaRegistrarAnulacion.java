/**
 * TipoEntradaRegistrarAnulacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ci.operacionesfinancieras.registraranulacion.v2;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarAnulacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRegistrarAnulacion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5251308858140014985L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarAnulacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registraranulacion/v2",
		        "tipoEntradaRegistrarAnulacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReferencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ci/operacionesfinancieras/registraranulacion/v2",
		        "numeroReferencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc     = null;
	
	/** Propiedad numero referencia. */
	private java.lang.String numeroReferencia;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc   = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar anulacion.
	 */
	public TipoEntradaRegistrarAnulacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar anulacion.
	 *
	 * @param numeroReferencia de numero referencia
	 */
	public TipoEntradaRegistrarAnulacion(java.lang.String numeroReferencia)
	{
		this.numeroReferencia = numeroReferencia;
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
	 * Sets the numeroReferencia value for this TipoEntradaRegistrarAnulacion.
	 *
	 * @param numeroReferencia de numero referencia
	 */
	public void setNumeroReferencia(java.lang.String numeroReferencia)
	{
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the numeroReferencia value for this TipoEntradaRegistrarAnulacion.
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
		if(!(obj instanceof TipoEntradaRegistrarAnulacion))
			return false;

		TipoEntradaRegistrarAnulacion other = (TipoEntradaRegistrarAnulacion)obj;

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
				|| ((this.numeroReferencia != null) && this.numeroReferencia.equals(other.getNumeroReferencia())));
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

		__hashCodeCalc = false;

		return _hashCode;
	}
}
