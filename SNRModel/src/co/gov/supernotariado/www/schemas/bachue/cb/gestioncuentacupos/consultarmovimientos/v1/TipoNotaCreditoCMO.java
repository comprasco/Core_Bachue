/**
 * TipoNotaCreditoCMO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1;



/**
 * The Class TipoNotaCreditoCMO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoNotaCreditoCMO implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1162033262407974461L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoNotaCreditoCMO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "tipoNotaCreditoCMO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numero");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "numero"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valor");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "valor"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The numero. */
	private java.lang.String numero;

	/** The valor. */
	private java.math.BigDecimal valor;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo nota credito CMO.
	 */
	public TipoNotaCreditoCMO()
	{
	}

	/**
	 * Instantiates a new tipo nota credito CMO.
	 *
	 * @param numero the numero
	 * @param valor the valor
	 */
	public TipoNotaCreditoCMO(java.lang.String numero, java.math.BigDecimal valor)
	{
		this.numero     = numero;
		this.valor      = valor;
	}

	/**
	 * Gets the numero value for this TipoNotaCreditoCMO.
	 *
	 * @return numero
	 */
	public java.lang.String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero value for this TipoNotaCreditoCMO.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(java.lang.String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the valor value for this TipoNotaCreditoCMO.
	 *
	 * @return valor
	 */
	public java.math.BigDecimal getValor()
	{
		return valor;
	}

	/**
	 * Sets the valor value for this TipoNotaCreditoCMO.
	 *
	 * @param valor the new valor
	 */
	public void setValor(java.math.BigDecimal valor)
	{
		this.valor = valor;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoNotaCreditoCMO))
			return false;

		TipoNotaCreditoCMO other = (TipoNotaCreditoCMO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numero == null) && (other.getNumero() == null))
				|| ((this.numero != null) && this.numero.equals(other.getNumero())))
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

		if(getNumero() != null)
			_hashCode += getNumero().hashCode();

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
