/**
 * TipoAnotacionCancelada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1;


/**
 * Clase que contiene todos las propiedades TipoAnotacionCancelada.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoAnotacionCancelada implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoAnotacionCancelada.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoAnotacionCancelada"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anotacionCancelada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "anotacionCancelada"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad anotacion cancelada. */
	/* Numero de la anotción cancelada */
	private java.lang.String anotacionCancelada;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo anotacion cancelada.
	 */
	public TipoAnotacionCancelada()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo anotacion cancelada.
	 *
	 * @param anotacionCancelada de anotacion cancelada
	 */
	public TipoAnotacionCancelada(java.lang.String anotacionCancelada)
	{
		this.anotacionCancelada = anotacionCancelada;
	}

	/**
	 * Gets the anotacionCancelada value for this TipoAnotacionCancelada.
	 *
	 * @return anotacionCancelada   * Numero de la anotción cancelada
	 */
	public java.lang.String getAnotacionCancelada()
	{
		return anotacionCancelada;
	}

	/**
	 * Sets the anotacionCancelada value for this TipoAnotacionCancelada.
	 *
	 * @param anotacionCancelada   * Numero de la anotción cancelada
	 */
	public void setAnotacionCancelada(java.lang.String anotacionCancelada)
	{
		this.anotacionCancelada = anotacionCancelada;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoAnotacionCancelada))
			return false;

		TipoAnotacionCancelada other = (TipoAnotacionCancelada)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.anotacionCancelada == null) && (other.getAnotacionCancelada() == null))
				|| ((this.anotacionCancelada != null) && this.anotacionCancelada.equals(other.getAnotacionCancelada())));
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

		if(getAnotacionCancelada() != null)
			_hashCode += getAnotacionCancelada().hashCode();

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
