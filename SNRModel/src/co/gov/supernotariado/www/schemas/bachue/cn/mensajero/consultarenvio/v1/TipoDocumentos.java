/**
 * TipoSalidaConsultarEnvioListaMensajesMensajeDocumentosDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarEnvioListaMensajesMensajeDocumentosDocumento.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2020
 */
public class TipoDocumentos implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4094674732433695018L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDocumentos.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        ">>>>tipoSalidaConsultarEnvio>listaMensajes>mensaje>documentos>documento"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DID");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "dID"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DDocName");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "dDocName"
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
	private java.lang.String dID;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar envio lista mensajes mensaje documentos documento.
	 */
	public TipoDocumentos()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar envio lista mensajes mensaje documentos documento.
	 *
	 * @param dID correspondiente al valor de d ID
	 * @param dDocName correspondiente al valor de d doc name
	 */
	public TipoDocumentos(
	    java.lang.String dID, java.lang.String dDocName
	)
	{
		this.dID          = dID;
		this.dDocName     = dDocName;
	}

	/**
	 * Sets the dDocName value for this TipoSalidaConsultarEnvioListaMensajesMensajeDocumentosDocumento.
	 *
	 * @param dDocName correspondiente al valor de d doc name
	 */
	public void setDDocName(java.lang.String dDocName)
	{
		this.dDocName = dDocName;
	}

	/**
	 * Gets the dDocName value for this TipoSalidaConsultarEnvioListaMensajesMensajeDocumentosDocumento.
	 *
	 * @return dDocName
	 */
	public java.lang.String getDDocName()
	{
		return dDocName;
	}

	/**
	 * Sets the dID value for this TipoSalidaConsultarEnvioListaMensajesMensajeDocumentosDocumento.
	 *
	 * @param dID correspondiente al valor de d ID
	 */
	public void setDID(java.lang.String dID)
	{
		this.dID = dID;
	}

	/**
	 * Gets the dID value for this TipoSalidaConsultarEnvioListaMensajesMensajeDocumentosDocumento.
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
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
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
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
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
		if(!(obj instanceof TipoDocumentos))
			return false;

		TipoDocumentos other = (TipoDocumentos)obj;

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
