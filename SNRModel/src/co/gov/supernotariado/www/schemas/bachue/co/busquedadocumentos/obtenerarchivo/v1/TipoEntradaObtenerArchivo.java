/**
 * TipoEntradaObtenerArchivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.co.busquedadocumentos.obtenerarchivo.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaObtenerArchivo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaObtenerArchivo implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6683696721800107907L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerArchivo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1",
		        "tipoEntradaObtenerArchivo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DDocName");
		elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1", "dDocName"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DID");
		elemField.setXmlName(new javax.xml.namespace.QName("https://www.supernotariado.gov.co/schemas/bachue/co/busquedadocumentos/obtenerarchivo/v1", "dID"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad d doc name. */
	private java.lang.String dDocName;
	
	/** Propiedad d ID. */
	private java.lang.String dID;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada obtener archivo.
	 */
	public TipoEntradaObtenerArchivo()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada obtener archivo.
	 *
	 * @param dDocName de d doc name
	 * @param dID de d ID
	 */
	public TipoEntradaObtenerArchivo(java.lang.String dDocName, java.lang.String dID)
	{
		this.dDocName     = dDocName;
		this.dID          = dID;
	}

	/**
	 * Sets the dDocName value for this TipoEntradaObtenerArchivo.
	 *
	 * @param dDocName de d doc name
	 */
	public void setDDocName(java.lang.String dDocName)
	{
		this.dDocName = dDocName;
	}

	/**
	 * Gets the dDocName value for this TipoEntradaObtenerArchivo.
	 *
	 * @return dDocName
	 */
	public java.lang.String getDDocName()
	{
		return dDocName;
	}

	/**
	 * Sets the dID value for this TipoEntradaObtenerArchivo.
	 *
	 * @param dID de d ID
	 */
	public void setDID(java.lang.String dID)
	{
		this.dID = dID;
	}

	/**
	 * Gets the dID value for this TipoEntradaObtenerArchivo.
	 *
	 * @return dID
	 */
	public java.lang.String getDID()
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
		if(!(obj instanceof TipoEntradaObtenerArchivo))
			return false;

		TipoEntradaObtenerArchivo other = (TipoEntradaObtenerArchivo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.dDocName == null) && (other.getDDocName() == null))
				|| ((this.dDocName != null) && this.dDocName.equals(other.getDDocName())))
				&& (((this.dID == null) && (other.getDID() == null))
				|| ((this.dID != null) && this.dID.equals(other.getDID())));
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

		if(getDDocName() != null)
			_hashCode += getDDocName().hashCode();

		if(getDID() != null)
			_hashCode += getDID().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
