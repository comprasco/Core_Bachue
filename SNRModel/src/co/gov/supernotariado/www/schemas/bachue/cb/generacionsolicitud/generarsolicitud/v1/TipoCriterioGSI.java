/**
 * TipoCriterioGSI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1;



/**
 * The Class TipoCriterioGSI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoCriterioGSI implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3553606152695959352L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoCriterioGSI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoCriterioGSI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "codigo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valor");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "valor"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The codigo. */
	private java.lang.String codigo;

	/** The valor. */
	private java.lang.String valor;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo criterio GSI.
	 */
	public TipoCriterioGSI()
	{
	}

	/**
	 * Instantiates a new tipo criterio GSI.
	 *
	 * @param codigo the codigo
	 * @param valor the valor
	 */
	public TipoCriterioGSI(java.lang.String codigo, java.lang.String valor)
	{
		this.codigo     = codigo;
		this.valor      = valor;
	}

	/**
	 * Gets the codigo value for this TipoCriterioGSI.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the codigo value for this TipoCriterioGSI.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the valor value for this TipoCriterioGSI.
	 *
	 * @return valor
	 */
	public java.lang.String getValor()
	{
		return valor;
	}

	/**
	 * Sets the valor value for this TipoCriterioGSI.
	 *
	 * @param valor the new valor
	 */
	public void setValor(java.lang.String valor)
	{
		this.valor = valor;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoCriterioGSI))
			return false;

		TipoCriterioGSI other = (TipoCriterioGSI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.valor == null) && (other.getValor() == null))
				|| ((this.valor != null) && this.valor.equals(other.getValor())));
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

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getValor() != null)
			_hashCode += getValor().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
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
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
