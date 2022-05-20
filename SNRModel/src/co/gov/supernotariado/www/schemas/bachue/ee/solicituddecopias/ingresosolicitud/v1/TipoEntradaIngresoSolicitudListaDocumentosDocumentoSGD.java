/**
 * TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6815774752658028626L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        ">>tipoEntradaIngresoSolicitud>listaDocumentos>documentoSGD"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("DId");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "dId"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docName");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1", "docName"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumental");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecopias/ingresosolicitud/v1",
		        "tipoDocumental"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;
	
	/** Propiedad d id. */
	private java.lang.String dId;
	
	/** Propiedad doc name. */
	private java.lang.String docName;
	
	/** Propiedad tipo documental. */
	private java.lang.String tipoDocumental;
	
	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud lista documentos documento SGD.
	 */
	public TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud lista documentos documento SGD.
	 *
	 * @param dId de d id
	 * @param docName de doc name
	 * @param tipoDocumental de tipo documental
	 */
	public TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD(
	    java.lang.String dId, java.lang.String docName, java.lang.String tipoDocumental
	)
	{
		this.dId                = dId;
		this.docName            = docName;
		this.tipoDocumental     = tipoDocumental;
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

	/**
	 * Sets the dId value for this TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.
	 *
	 * @param dId de d id
	 */
	public void setDId(java.lang.String dId)
	{
		this.dId = dId;
	}

	/**
	 * Gets the dId value for this TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.
	 *
	 * @return dId
	 */
	public java.lang.String getDId()
	{
		return dId;
	}

	/**
	 * Sets the docName value for this TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.
	 *
	 * @param docName de doc name
	 */
	public void setDocName(java.lang.String docName)
	{
		this.docName = docName;
	}

	/**
	 * Gets the docName value for this TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.
	 *
	 * @return docName
	 */
	public java.lang.String getDocName()
	{
		return docName;
	}

	/**
	 * Sets the tipoDocumental value for this TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.
	 *
	 * @param tipoDocumental de tipo documental
	 */
	public void setTipoDocumental(java.lang.String tipoDocumental)
	{
		this.tipoDocumental = tipoDocumental;
	}

	/**
	 * Gets the tipoDocumental value for this TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD.
	 *
	 * @return tipoDocumental
	 */
	public java.lang.String getTipoDocumental()
	{
		return tipoDocumental;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD))
			return false;

		TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD other = (TipoEntradaIngresoSolicitudListaDocumentosDocumentoSGD)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.dId == null) && (other.getDId() == null))
				|| ((this.dId != null) && this.dId.equals(other.getDId())))
				&& (((this.docName == null) && (other.getDocName() == null))
				|| ((this.docName != null) && this.docName.equals(other.getDocName())))
				&& (((this.tipoDocumental == null) && (other.getTipoDocumental() == null))
				|| ((this.tipoDocumental != null) && this.tipoDocumental.equals(other.getTipoDocumental())));
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

		if(getDId() != null)
			_hashCode += getDId().hashCode();

		if(getDocName() != null)
			_hashCode += getDocName().hashCode();

		if(getTipoDocumental() != null)
			_hashCode += getTipoDocumental().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
