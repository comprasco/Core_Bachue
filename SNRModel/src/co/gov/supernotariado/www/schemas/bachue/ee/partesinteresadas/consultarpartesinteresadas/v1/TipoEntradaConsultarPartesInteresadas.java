/**
 * TipoEntradaConsultarPartesInteresadas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarPartesInteresadas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarPartesInteresadas implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5075018785914607591L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarPartesInteresadas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "tipoEntradaConsultarPartesInteresadas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "tipoIdentificacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        ">tipoEntradaConsultarPartesInteresadas>tipoIdentificacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                          __equalsCalc             =
		null;
	
	/** Propiedad num identificacion predio. */
	private java.lang.String                                                                                                                                          numIdentificacionPredio;
	
	/** Propiedad tipo identificacion predio. */
	private co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadasTipoIdentificacionPredio tipoIdentificacionPredio;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                   __hashCodeCalc           =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar partes interesadas.
	 */
	public TipoEntradaConsultarPartesInteresadas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar partes interesadas.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public TipoEntradaConsultarPartesInteresadas(
	    co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadasTipoIdentificacionPredio tipoIdentificacionPredio,
	    java.lang.String                                                                                                                                          numIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio     = tipoIdentificacionPredio;
		this.numIdentificacionPredio      = numIdentificacionPredio;
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
	 * Sets the numIdentificacionPredio value for this TipoEntradaConsultarPartesInteresadas.
	 *
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoEntradaConsultarPartesInteresadas.
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
	 * Sets the tipoIdentificacionPredio value for this TipoEntradaConsultarPartesInteresadas.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 */
	public void setTipoIdentificacionPredio(
	    co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadasTipoIdentificacionPredio tipoIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio = tipoIdentificacionPredio;
	}

	/**
	 * Gets the tipoIdentificacionPredio value for this TipoEntradaConsultarPartesInteresadas.
	 *
	 * @return tipoIdentificacionPredio
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadasTipoIdentificacionPredio getTipoIdentificacionPredio()
	{
		return tipoIdentificacionPredio;
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
		if(!(obj instanceof TipoEntradaConsultarPartesInteresadas))
			return false;

		TipoEntradaConsultarPartesInteresadas other = (TipoEntradaConsultarPartesInteresadas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoIdentificacionPredio == null) && (other.getTipoIdentificacionPredio() == null))
				|| ((this.tipoIdentificacionPredio != null)
				&& this.tipoIdentificacionPredio.equals(other.getTipoIdentificacionPredio())))
				&& (((this.numIdentificacionPredio == null) && (other.getNumIdentificacionPredio() == null))
				|| ((this.numIdentificacionPredio != null)
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())));
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

		if(getTipoIdentificacionPredio() != null)
			_hashCode += getTipoIdentificacionPredio().hashCode();

		if(getNumIdentificacionPredio() != null)
			_hashCode += getNumIdentificacionPredio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
