/**
 * TipoEntradaEnviarCorrespondenciaFisica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cr.enviocorrespondenciafisica.enviarcorrespondenciafisica.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaEnviarCorrespondenciaFisica.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
public class TipoEntradaEnviarCorrespondenciaFisica implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7866121938568536684L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaEnviarCorrespondenciaFisica.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/enviocorrespondenciafisica/enviarcorrespondenciafisica/v1",
		        "tipoEntradaEnviarCorrespondenciaFisica"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificador");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/enviocorrespondenciafisica/enviarcorrespondenciafisica/v1",
		        "identificador"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad identificador. */
	private java.lang.String identificador;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada enviar correspondencia fisica.
	 */
	public TipoEntradaEnviarCorrespondenciaFisica()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada enviar correspondencia fisica.
	 *
	 * @param identificador de identificador
	 */
	public TipoEntradaEnviarCorrespondenciaFisica(java.lang.String identificador)
	{
		this.identificador = identificador;
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
	 * Sets the identificador value for this TipoEntradaEnviarCorrespondenciaFisica.
	 *
	 * @param identificador de identificador
	 */
	public void setIdentificador(java.lang.String identificador)
	{
		this.identificador = identificador;
	}

	/**
	 * Gets the identificador value for this TipoEntradaEnviarCorrespondenciaFisica.
	 *
	 * @return identificador
	 */
	public java.lang.String getIdentificador()
	{
		return identificador;
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
		if(!(obj instanceof TipoEntradaEnviarCorrespondenciaFisica))
			return false;

		TipoEntradaEnviarCorrespondenciaFisica other = (TipoEntradaEnviarCorrespondenciaFisica)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.identificador == null) && (other.getIdentificador() == null))
				|| ((this.identificador != null) && this.identificador.equals(other.getIdentificador())));
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

		if(getIdentificador() != null)
			_hashCode += getIdentificador().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
