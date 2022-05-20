/**
 * TipoEntradaRegistrarCambioQuintoOrdenPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarCambioQuintoOrdenPredio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRegistrarCambioQuintoOrdenPredio implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3927790382026131603L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarCambioQuintoOrdenPredio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        ">tipoEntradaRegistrarCambioQuintoOrden>predio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "numPredial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad num predial. */
	private java.lang.String numPredial;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar cambio quinto orden predio.
	 */
	public TipoEntradaRegistrarCambioQuintoOrdenPredio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar cambio quinto orden predio.
	 *
	 * @param numPredial de num predial
	 */
	public TipoEntradaRegistrarCambioQuintoOrdenPredio(java.lang.String numPredial)
	{
		this.numPredial = numPredial;
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
	 * Sets the numPredial value for this TipoEntradaRegistrarCambioQuintoOrdenPredio.
	 *
	 * @param numPredial de num predial
	 */
	public void setNumPredial(java.lang.String numPredial)
	{
		this.numPredial = numPredial;
	}

	/**
	 * Gets the numPredial value for this TipoEntradaRegistrarCambioQuintoOrdenPredio.
	 *
	 * @return numPredial
	 */
	public java.lang.String getNumPredial()
	{
		return numPredial;
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
		if(!(obj instanceof TipoEntradaRegistrarCambioQuintoOrdenPredio))
			return false;

		TipoEntradaRegistrarCambioQuintoOrdenPredio other = (TipoEntradaRegistrarCambioQuintoOrdenPredio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numPredial == null) && (other.getNumPredial() == null))
				|| ((this.numPredial != null) && this.numPredial.equals(other.getNumPredial())));
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

		if(getNumPredial() != null)
			_hashCode += getNumPredial().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
