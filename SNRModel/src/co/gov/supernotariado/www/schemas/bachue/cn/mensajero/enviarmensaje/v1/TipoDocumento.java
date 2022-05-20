/**
 * TipoDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1;


/**
 * Clase que contiene todos las propiedades TipoDocumento.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/03/2020
 */
public class TipoDocumento implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7130817653694295802L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDocumento.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoDocumento"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DID");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "dID"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DDocName");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "dDocName"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad d doc name. */
	private java.lang.String dDocName;

	/** Propiedad d ID. */
	private java.math.BigInteger dID;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo documento.
	 */
	public TipoDocumento()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo documento.
	 *
	 * @param dID de d ID
	 * @param dDocName de d doc name
	 */
	public TipoDocumento(java.math.BigInteger dID, java.lang.String dDocName)
	{
		this.dID          = dID;
		this.dDocName     = dDocName;
	}

	/**
	 * Sets the dDocName value for this TipoDocumento.
	 *
	 * @param dDocName de d doc name
	 */
	public void setDDocName(java.lang.String dDocName)
	{
		this.dDocName = dDocName;
	}

	/**
	 * Gets the dDocName value for this TipoDocumento.
	 *
	 * @return dDocName
	 */
	public java.lang.String getDDocName()
	{
		return dDocName;
	}

	/**
	 * Sets the dID value for this TipoDocumento.
	 *
	 * @param dID de d ID
	 */
	public void setDID(java.math.BigInteger dID)
	{
		this.dID = dID;
	}

	/**
	 * Gets the dID value for this TipoDocumento.
	 *
	 * @return dID
	 */
	public java.math.BigInteger getDID()
	{
		return dID;
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoDocumento))
			return false;

		TipoDocumento other = (TipoDocumento)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.dID == null) && (other.getDID() == null))
				|| ((this.dID != null) && this.dID.equals(other.getDID())))
				&& (((this.dDocName == null) && (other.getDDocName() == null))
				|| ((this.dDocName != null) && this.dDocName.equals(other.getDDocName())));
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

		if(getDID() != null)
			_hashCode += getDID().hashCode();

		if(getDDocName() != null)
			_hashCode += getDDocName().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
