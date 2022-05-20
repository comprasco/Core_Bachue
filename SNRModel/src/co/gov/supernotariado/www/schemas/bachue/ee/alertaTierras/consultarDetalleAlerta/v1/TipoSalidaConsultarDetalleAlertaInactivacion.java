/**
 * TipoSalidaConsultarDetalleAlertaInactivacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarDetalleAlertaInactivacion.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/04/2020
 */
public class TipoSalidaConsultarDetalleAlertaInactivacion implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6997018093865309224L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarDetalleAlertaInactivacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        ">tipoSalidaConsultarDetalleAlerta>inactivacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("motivoInactivacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "motivoInactivacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomOficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "nomOficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomTipoDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "nomTipoDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "fechaDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docNumero");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "docNumero"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docIdSGD");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "docIdSGD"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docNameSGD");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "docNameSGD"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("textoInactivacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "textoInactivacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad doc id SGD. */
	private java.lang.String docIdSGD;

	/** Propiedad doc name SGD. */
	private java.lang.String docNameSGD;

	/** Propiedad doc numero. */
	private java.lang.String docNumero;

	/** Propiedad fecha documento. */
	private java.util.Calendar fechaDocumento;

	/** Propiedad motivo inactivacion. */
	private java.lang.String motivoInactivacion;

	/** Propiedad nom oficina origen. */
	private java.lang.String nomOficinaOrigen;

	/** Propiedad nom tipo documento publico. */
	private java.lang.String nomTipoDocumentoPublico;

	/** Propiedad texto inactivacion. */
	private java.lang.String textoInactivacion;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar detalle alerta inactivacion.
	 */
	public TipoSalidaConsultarDetalleAlertaInactivacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar detalle alerta inactivacion.
	 *
	 * @param motivoInactivacion de motivo inactivacion
	 * @param nomOficinaOrigen de nom oficina origen
	 * @param nomTipoDocumentoPublico de nom tipo documento publico
	 * @param fechaDocumento de fecha documento
	 * @param docNumero de doc numero
	 * @param docIdSGD de doc id SGD
	 * @param docNameSGD de doc name SGD
	 * @param textoInactivacion de texto inactivacion
	 */
	public TipoSalidaConsultarDetalleAlertaInactivacion(
	    java.lang.String motivoInactivacion, java.lang.String nomOficinaOrigen, java.lang.String nomTipoDocumentoPublico,
	    java.util.Calendar fechaDocumento, java.lang.String docNumero, java.lang.String docIdSGD,
	    java.lang.String docNameSGD, java.lang.String textoInactivacion
	)
	{
		this.motivoInactivacion          = motivoInactivacion;
		this.nomOficinaOrigen            = nomOficinaOrigen;
		this.nomTipoDocumentoPublico     = nomTipoDocumentoPublico;
		this.fechaDocumento              = fechaDocumento;
		this.docNumero                   = docNumero;
		this.docIdSGD                    = docIdSGD;
		this.docNameSGD                  = docNameSGD;
		this.textoInactivacion           = textoInactivacion;
	}

	/**
	 * Gets the motivoInactivacion value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return motivoInactivacion
	 */
	public java.lang.String getMotivoInactivacion()
	{
		return motivoInactivacion;
	}

	/**
	 * Sets the motivoInactivacion value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param motivoInactivacion de motivo inactivacion
	 */
	public void setMotivoInactivacion(java.lang.String motivoInactivacion)
	{
		this.motivoInactivacion = motivoInactivacion;
	}

	/**
	 * Gets the nomOficinaOrigen value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return nomOficinaOrigen
	 */
	public java.lang.String getNomOficinaOrigen()
	{
		return nomOficinaOrigen;
	}

	/**
	 * Sets the nomOficinaOrigen value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param nomOficinaOrigen de nom oficina origen
	 */
	public void setNomOficinaOrigen(java.lang.String nomOficinaOrigen)
	{
		this.nomOficinaOrigen = nomOficinaOrigen;
	}

	/**
	 * Gets the nomTipoDocumentoPublico value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return nomTipoDocumentoPublico
	 */
	public java.lang.String getNomTipoDocumentoPublico()
	{
		return nomTipoDocumentoPublico;
	}

	/**
	 * Sets the nomTipoDocumentoPublico value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param nomTipoDocumentoPublico de nom tipo documento publico
	 */
	public void setNomTipoDocumentoPublico(java.lang.String nomTipoDocumentoPublico)
	{
		this.nomTipoDocumentoPublico = nomTipoDocumentoPublico;
	}

	/**
	 * Gets the fechaDocumento value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return fechaDocumento
	 */
	public java.util.Calendar getFechaDocumento()
	{
		return fechaDocumento;
	}

	/**
	 * Sets the fechaDocumento value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param fechaDocumento de fecha documento
	 */
	public void setFechaDocumento(java.util.Calendar fechaDocumento)
	{
		this.fechaDocumento = fechaDocumento;
	}

	/**
	 * Gets the docNumero value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return docNumero
	 */
	public java.lang.String getDocNumero()
	{
		return docNumero;
	}

	/**
	 * Sets the docNumero value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param docNumero de doc numero
	 */
	public void setDocNumero(java.lang.String docNumero)
	{
		this.docNumero = docNumero;
	}

	/**
	 * Gets the docIdSGD value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return docIdSGD
	 */
	public java.lang.String getDocIdSGD()
	{
		return docIdSGD;
	}

	/**
	 * Sets the docIdSGD value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param docIdSGD de doc id SGD
	 */
	public void setDocIdSGD(java.lang.String docIdSGD)
	{
		this.docIdSGD = docIdSGD;
	}

	/**
	 * Gets the docNameSGD value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return docNameSGD
	 */
	public java.lang.String getDocNameSGD()
	{
		return docNameSGD;
	}

	/**
	 * Sets the docNameSGD value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param docNameSGD de doc name SGD
	 */
	public void setDocNameSGD(java.lang.String docNameSGD)
	{
		this.docNameSGD = docNameSGD;
	}

	/**
	 * Gets the textoInactivacion value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @return textoInactivacion
	 */
	public java.lang.String getTextoInactivacion()
	{
		return textoInactivacion;
	}

	/**
	 * Sets the textoInactivacion value for this TipoSalidaConsultarDetalleAlertaInactivacion.
	 *
	 * @param textoInactivacion de texto inactivacion
	 */
	public void setTextoInactivacion(java.lang.String textoInactivacion)
	{
		this.textoInactivacion = textoInactivacion;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarDetalleAlertaInactivacion))
			return false;

		TipoSalidaConsultarDetalleAlertaInactivacion other = (TipoSalidaConsultarDetalleAlertaInactivacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.motivoInactivacion == null) && (other.getMotivoInactivacion() == null))
				|| ((this.motivoInactivacion != null) && this.motivoInactivacion.equals(other.getMotivoInactivacion())))
				&& (((this.nomOficinaOrigen == null) && (other.getNomOficinaOrigen() == null))
				|| ((this.nomOficinaOrigen != null) && this.nomOficinaOrigen.equals(other.getNomOficinaOrigen())))
				&& (((this.nomTipoDocumentoPublico == null) && (other.getNomTipoDocumentoPublico() == null))
				|| ((this.nomTipoDocumentoPublico != null)
				&& this.nomTipoDocumentoPublico.equals(other.getNomTipoDocumentoPublico())))
				&& (((this.fechaDocumento == null) && (other.getFechaDocumento() == null))
				|| ((this.fechaDocumento != null) && this.fechaDocumento.equals(other.getFechaDocumento())))
				&& (((this.docNumero == null) && (other.getDocNumero() == null))
				|| ((this.docNumero != null) && this.docNumero.equals(other.getDocNumero())))
				&& (((this.docIdSGD == null) && (other.getDocIdSGD() == null))
				|| ((this.docIdSGD != null) && this.docIdSGD.equals(other.getDocIdSGD())))
				&& (((this.docNameSGD == null) && (other.getDocNameSGD() == null))
				|| ((this.docNameSGD != null) && this.docNameSGD.equals(other.getDocNameSGD())))
				&& (((this.textoInactivacion == null) && (other.getTextoInactivacion() == null))
				|| ((this.textoInactivacion != null) && this.textoInactivacion.equals(other.getTextoInactivacion())));
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

		if(getMotivoInactivacion() != null)
			_hashCode += getMotivoInactivacion().hashCode();

		if(getNomOficinaOrigen() != null)
			_hashCode += getNomOficinaOrigen().hashCode();

		if(getNomTipoDocumentoPublico() != null)
			_hashCode += getNomTipoDocumentoPublico().hashCode();

		if(getFechaDocumento() != null)
			_hashCode += getFechaDocumento().hashCode();

		if(getDocNumero() != null)
			_hashCode += getDocNumero().hashCode();

		if(getDocIdSGD() != null)
			_hashCode += getDocIdSGD().hashCode();

		if(getDocNameSGD() != null)
			_hashCode += getDocNameSGD().hashCode();

		if(getTextoInactivacion() != null)
			_hashCode += getTextoInactivacion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
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
}
