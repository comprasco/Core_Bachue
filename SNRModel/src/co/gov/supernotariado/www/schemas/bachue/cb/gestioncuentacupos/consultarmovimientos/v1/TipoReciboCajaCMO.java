/**
 * TipoReciboCajaCMO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1;



/**
 * The Class TipoReciboCajaCMO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoReciboCajaCMO implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -972461149997354148L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoReciboCajaCMO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "tipoReciboCajaCMO"
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
		elemField.setFieldName("valorConsumo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "valorConsumo"
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

	/** The valor consumo. */
	private java.math.BigDecimal valorConsumo;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo recibo caja CMO.
	 */
	public TipoReciboCajaCMO()
	{
	}

	/**
	 * Instantiates a new tipo recibo caja CMO.
	 *
	 * @param numero the numero
	 * @param valorConsumo the valor consumo
	 */
	public TipoReciboCajaCMO(java.lang.String numero, java.math.BigDecimal valorConsumo)
	{
		this.numero           = numero;
		this.valorConsumo     = valorConsumo;
	}

	/**
	 * Gets the numero value for this TipoReciboCajaCMO.
	 *
	 * @return numero
	 */
	public java.lang.String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero value for this TipoReciboCajaCMO.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(java.lang.String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the valorConsumo value for this TipoReciboCajaCMO.
	 *
	 * @return valorConsumo
	 */
	public java.math.BigDecimal getValorConsumo()
	{
		return valorConsumo;
	}

	/**
	 * Sets the valorConsumo value for this TipoReciboCajaCMO.
	 *
	 * @param valorConsumo the new valor consumo
	 */
	public void setValorConsumo(java.math.BigDecimal valorConsumo)
	{
		this.valorConsumo = valorConsumo;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoReciboCajaCMO))
			return false;

		TipoReciboCajaCMO other = (TipoReciboCajaCMO)obj;

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
				&& (((this.valorConsumo == null) && (other.getValorConsumo() == null))
				|| ((this.valorConsumo != null) && this.valorConsumo.equals(other.getValorConsumo())));
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

		if(getValorConsumo() != null)
			_hashCode += getValorConsumo().hashCode();

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
