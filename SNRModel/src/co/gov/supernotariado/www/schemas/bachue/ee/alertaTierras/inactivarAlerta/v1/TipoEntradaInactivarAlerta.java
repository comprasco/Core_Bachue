/**
 * TipoEntradaInactivarAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaInactivarAlerta.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaInactivarAlerta implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3463511883574338018L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaInactivarAlerta.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1",
		        "tipoEntradaInactivarAlerta"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "idAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idMotivo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "idMotivo"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1",
		        ">tipoEntradaInactivarAlerta>idMotivo"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("oficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "oficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codTipoDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1",
		        "codTipoDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "fechaDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docNumero");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "docNumero"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docIdSGD");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "docIdSGD"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("docNameSGD");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1", "docNameSGD"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("textoInactivacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inactivarAlerta/v1",
		        "textoInactivacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod tipo documento publico. */
	private java.lang.String codTipoDocumentoPublico;

	/** Propiedad doc id SGD. */
	private java.lang.String docIdSGD;

	/** Propiedad doc name SGD. */
	private java.lang.String docNameSGD;

	/** Propiedad doc numero. */
	private java.lang.String docNumero;

	/** Propiedad fecha documento. */
	private java.util.Calendar fechaDocumento;

	/** Propiedad id motivo. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlertaIdMotivo idMotivo;

	/** Propiedad oficina origen. */
	private java.lang.String oficinaOrigen;

	/** Propiedad texto inactivacion. */
	private java.lang.String textoInactivacion;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad id alerta. */
	private int idAlerta;

	/**
	 * Instancia un nuevo objeto tipo entrada inactivar alerta.
	 */
	public TipoEntradaInactivarAlerta()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada inactivar alerta.
	 *
	 * @param idAlerta de id alerta
	 * @param idMotivo de id motivo
	 * @param oficinaOrigen de oficina origen
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 * @param fechaDocumento de fecha documento
	 * @param docNumero de doc numero
	 * @param docIdSGD de doc id SGD
	 * @param docNameSGD de doc name SGD
	 * @param textoInactivacion de texto inactivacion
	 */
	public TipoEntradaInactivarAlerta(
	    int idAlerta,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlertaIdMotivo idMotivo,
	    java.lang.String oficinaOrigen, java.lang.String codTipoDocumentoPublico, java.util.Calendar fechaDocumento,
	    java.lang.String docNumero, java.lang.String docIdSGD, java.lang.String docNameSGD,
	    java.lang.String textoInactivacion
	)
	{
		this.idAlerta                    = idAlerta;
		this.idMotivo                    = idMotivo;
		this.oficinaOrigen               = oficinaOrigen;
		this.codTipoDocumentoPublico     = codTipoDocumentoPublico;
		this.fechaDocumento              = fechaDocumento;
		this.docNumero                   = docNumero;
		this.docIdSGD                    = docIdSGD;
		this.docNameSGD                  = docNameSGD;
		this.textoInactivacion           = textoInactivacion;
	}

	/**
	 * Gets the idAlerta value for this TipoEntradaInactivarAlerta.
	 *
	 * @return idAlerta
	 */
	public int getIdAlerta()
	{
		return idAlerta;
	}

	/**
	 * Sets the idAlerta value for this TipoEntradaInactivarAlerta.
	 *
	 * @param idAlerta de id alerta
	 */
	public void setIdAlerta(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/**
	 * Gets the idMotivo value for this TipoEntradaInactivarAlerta.
	 *
	 * @return idMotivo
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlertaIdMotivo getIdMotivo()
	{
		return idMotivo;
	}

	/**
	 * Sets the idMotivo value for this TipoEntradaInactivarAlerta.
	 *
	 * @param idMotivo de id motivo
	 */
	public void setIdMotivo(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inactivarAlerta.v1.TipoEntradaInactivarAlertaIdMotivo idMotivo
	)
	{
		this.idMotivo = idMotivo;
	}

	/**
	 * Gets the oficinaOrigen value for this TipoEntradaInactivarAlerta.
	 *
	 * @return oficinaOrigen
	 */
	public java.lang.String getOficinaOrigen()
	{
		return oficinaOrigen;
	}

	/**
	 * Sets the oficinaOrigen value for this TipoEntradaInactivarAlerta.
	 *
	 * @param oficinaOrigen de oficina origen
	 */
	public void setOficinaOrigen(java.lang.String oficinaOrigen)
	{
		this.oficinaOrigen = oficinaOrigen;
	}

	/**
	 * Gets the codTipoDocumentoPublico value for this TipoEntradaInactivarAlerta.
	 *
	 * @return codTipoDocumentoPublico
	 */
	public java.lang.String getCodTipoDocumentoPublico()
	{
		return codTipoDocumentoPublico;
	}

	/**
	 * Sets the codTipoDocumentoPublico value for this TipoEntradaInactivarAlerta.
	 *
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 */
	public void setCodTipoDocumentoPublico(java.lang.String codTipoDocumentoPublico)
	{
		this.codTipoDocumentoPublico = codTipoDocumentoPublico;
	}

	/**
	 * Gets the fechaDocumento value for this TipoEntradaInactivarAlerta.
	 *
	 * @return fechaDocumento
	 */
	public java.util.Calendar getFechaDocumento()
	{
		return fechaDocumento;
	}

	/**
	 * Sets the fechaDocumento value for this TipoEntradaInactivarAlerta.
	 *
	 * @param fechaDocumento de fecha documento
	 */
	public void setFechaDocumento(java.util.Calendar fechaDocumento)
	{
		this.fechaDocumento = fechaDocumento;
	}

	/**
	 * Gets the docNumero value for this TipoEntradaInactivarAlerta.
	 *
	 * @return docNumero
	 */
	public java.lang.String getDocNumero()
	{
		return docNumero;
	}

	/**
	 * Sets the docNumero value for this TipoEntradaInactivarAlerta.
	 *
	 * @param docNumero de doc numero
	 */
	public void setDocNumero(java.lang.String docNumero)
	{
		this.docNumero = docNumero;
	}

	/**
	 * Gets the docIdSGD value for this TipoEntradaInactivarAlerta.
	 *
	 * @return docIdSGD
	 */
	public java.lang.String getDocIdSGD()
	{
		return docIdSGD;
	}

	/**
	 * Sets the docIdSGD value for this TipoEntradaInactivarAlerta.
	 *
	 * @param docIdSGD de doc id SGD
	 */
	public void setDocIdSGD(java.lang.String docIdSGD)
	{
		this.docIdSGD = docIdSGD;
	}

	/**
	 * Gets the docNameSGD value for this TipoEntradaInactivarAlerta.
	 *
	 * @return docNameSGD
	 */
	public java.lang.String getDocNameSGD()
	{
		return docNameSGD;
	}

	/**
	 * Sets the docNameSGD value for this TipoEntradaInactivarAlerta.
	 *
	 * @param docNameSGD de doc name SGD
	 */
	public void setDocNameSGD(java.lang.String docNameSGD)
	{
		this.docNameSGD = docNameSGD;
	}

	/**
	 * Gets the textoInactivacion value for this TipoEntradaInactivarAlerta.
	 *
	 * @return textoInactivacion
	 */
	public java.lang.String getTextoInactivacion()
	{
		return textoInactivacion;
	}

	/**
	 * Sets the textoInactivacion value for this TipoEntradaInactivarAlerta.
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
		if(!(obj instanceof TipoEntradaInactivarAlerta))
			return false;

		TipoEntradaInactivarAlerta other = (TipoEntradaInactivarAlerta)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true && (this.idAlerta == other.getIdAlerta())
				&& (((this.idMotivo == null) && (other.getIdMotivo() == null))
				|| ((this.idMotivo != null) && this.idMotivo.equals(other.getIdMotivo())))
				&& (((this.oficinaOrigen == null) && (other.getOficinaOrigen() == null))
				|| ((this.oficinaOrigen != null) && this.oficinaOrigen.equals(other.getOficinaOrigen())))
				&& (((this.codTipoDocumentoPublico == null) && (other.getCodTipoDocumentoPublico() == null))
				|| ((this.codTipoDocumentoPublico != null)
				&& this.codTipoDocumentoPublico.equals(other.getCodTipoDocumentoPublico())))
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
		_hashCode += getIdAlerta();

		if(getIdMotivo() != null)
			_hashCode += getIdMotivo().hashCode();

		if(getOficinaOrigen() != null)
			_hashCode += getOficinaOrigen().hashCode();

		if(getCodTipoDocumentoPublico() != null)
			_hashCode += getCodTipoDocumentoPublico().hashCode();

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
