/**
 * TipoSalidaEnviarDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.co.enviodocumentos.enviardocumento.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaEnviarDocumento.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaEnviarDocumento implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8004246499446003486L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaEnviarDocumento.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        "tipoSalidaEnviarDocumento"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docName");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "docName"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DID");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1", "dID"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/enviodocumentos/enviardocumento/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc       = null;
	
	/** Propiedad codigo mensaje. */
	private java.math.BigInteger codigoMensaje;
	
	/** Propiedad d ID. */
	private java.lang.String     dID;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String     descripcionMensaje;
	
	/** Propiedad doc name. */
	private java.lang.String     docName;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo salida enviar documento.
	 */
	public TipoSalidaEnviarDocumento()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida enviar documento.
	 *
	 * @param docName de doc name
	 * @param dID de d ID
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaEnviarDocumento(
	    java.lang.String docName, java.lang.String dID, java.math.BigInteger codigoMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.docName                = docName;
		this.dID                    = dID;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaEnviarDocumento.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaEnviarDocumento.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the dID value for this TipoSalidaEnviarDocumento.
	 *
	 * @param dID de d ID
	 */
	public void setDID(java.lang.String dID)
	{
		this.dID = dID;
	}

	/**
	 * Gets the dID value for this TipoSalidaEnviarDocumento.
	 *
	 * @return dID
	 */
	public java.lang.String getDID()
	{
		return dID;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaEnviarDocumento.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaEnviarDocumento.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
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
	 * Sets the docName value for this TipoSalidaEnviarDocumento.
	 *
	 * @param docName de doc name
	 */
	public void setDocName(java.lang.String docName)
	{
		this.docName = docName;
	}

	/**
	 * Gets the docName value for this TipoSalidaEnviarDocumento.
	 *
	 * @return docName
	 */
	public java.lang.String getDocName()
	{
		return docName;
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
		if(!(obj instanceof TipoSalidaEnviarDocumento))
			return false;

		TipoSalidaEnviarDocumento other = (TipoSalidaEnviarDocumento)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.docName == null) && (other.getDocName() == null))
				|| ((this.docName != null) && this.docName.equals(other.getDocName())))
				&& (((this.dID == null) && (other.getDID() == null))
				|| ((this.dID != null) && this.dID.equals(other.getDID())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getDocName() != null)
			_hashCode += getDocName().hashCode();

		if(getDID() != null)
			_hashCode += getDID().hashCode();

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
