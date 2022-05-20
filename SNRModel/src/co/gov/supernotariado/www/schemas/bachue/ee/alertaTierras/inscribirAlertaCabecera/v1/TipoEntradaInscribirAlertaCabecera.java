/**
 * TipoEntradaInscribirAlertaCabecera.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.inscribirAlertaCabecera.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaInscribirAlertaCabecera.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaInscribirAlertaCabecera implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8065168243445912994L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaInscribirAlertaCabecera.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "tipoEntradaInscribirAlertaCabecera"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "tipoAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "idEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("procesoNroRadicado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "procesoNroRadicado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("procesoFechaRadicado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "procesoFechaRadicado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("oficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "oficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codTipoDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "codTipoDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "docNameSGD"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nitComunidadEtnica");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "nitComunidadEtnica"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("creadoSNR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "creadoSNR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/inscribirAlertaCabecera/v1",
		        "idUsuario"
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

	/** Propiedad creado SNR. */
	private java.lang.String creadoSNR;

	/** Propiedad doc id SGD. */
	private java.lang.String docIdSGD;

	/** Propiedad doc name SGD. */
	private java.lang.String docNameSGD;

	/** Propiedad doc numero. */
	private java.lang.String docNumero;

	/** Propiedad fecha documento. */
	private java.util.Calendar fechaDocumento;

	/** Propiedad id usuario. */
	private java.lang.String idUsuario;

	/** Propiedad nit comunidad etnica. */
	private java.lang.String nitComunidadEtnica;

	/** Propiedad oficina origen. */
	private java.lang.String oficinaOrigen;

	/** Propiedad proceso fecha radicado. */
	private java.util.Calendar procesoFechaRadicado;

	/** Propiedad proceso nro radicado. */
	private java.lang.String procesoNroRadicado;

	/** Propiedad tipo alerta. */
	private java.lang.String tipoAlerta;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad id entidad. */
	private int idEntidad;

	/**
	 * Instancia un nuevo objeto tipo entrada inscribir alerta cabecera.
	 */
	public TipoEntradaInscribirAlertaCabecera()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada inscribir alerta cabecera.
	 *
	 * @param tipoAlerta de tipo alerta
	 * @param idEntidad de id entidad
	 * @param procesoNroRadicado de proceso nro radicado
	 * @param procesoFechaRadicado de proceso fecha radicado
	 * @param oficinaOrigen de oficina origen
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 * @param fechaDocumento de fecha documento
	 * @param docNumero de doc numero
	 * @param docIdSGD de doc id SGD
	 * @param docNameSGD de doc name SGD
	 * @param nitComunidadEtnica de nit comunidad etnica
	 * @param creadoSNR de creado SNR
	 * @param idUsuario de id usuario
	 */
	public TipoEntradaInscribirAlertaCabecera(
	    java.lang.String tipoAlerta, int idEntidad, java.lang.String procesoNroRadicado,
	    java.util.Calendar procesoFechaRadicado, java.lang.String oficinaOrigen,
	    java.lang.String codTipoDocumentoPublico, java.util.Calendar fechaDocumento, java.lang.String docNumero,
	    java.lang.String docIdSGD, java.lang.String docNameSGD, java.lang.String nitComunidadEtnica,
	    java.lang.String creadoSNR, java.lang.String idUsuario
	)
	{
		this.tipoAlerta                  = tipoAlerta;
		this.idEntidad                   = idEntidad;
		this.procesoNroRadicado          = procesoNroRadicado;
		this.procesoFechaRadicado        = procesoFechaRadicado;
		this.oficinaOrigen               = oficinaOrigen;
		this.codTipoDocumentoPublico     = codTipoDocumentoPublico;
		this.fechaDocumento              = fechaDocumento;
		this.docNumero                   = docNumero;
		this.docIdSGD                    = docIdSGD;
		this.docNameSGD                  = docNameSGD;
		this.nitComunidadEtnica          = nitComunidadEtnica;
		this.creadoSNR                   = creadoSNR;
		this.idUsuario                   = idUsuario;
	}

	/**
	 * Gets the tipoAlerta value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return tipoAlerta
	 */
	public java.lang.String getTipoAlerta()
	{
		return tipoAlerta;
	}

	/**
	 * Sets the tipoAlerta value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param tipoAlerta de tipo alerta
	 */
	public void setTipoAlerta(java.lang.String tipoAlerta)
	{
		this.tipoAlerta = tipoAlerta;
	}

	/**
	 * Gets the idEntidad value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return idEntidad
	 */
	public int getIdEntidad()
	{
		return idEntidad;
	}

	/**
	 * Sets the idEntidad value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param idEntidad de id entidad
	 */
	public void setIdEntidad(int idEntidad)
	{
		this.idEntidad = idEntidad;
	}

	/**
	 * Gets the procesoNroRadicado value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return procesoNroRadicado
	 */
	public java.lang.String getProcesoNroRadicado()
	{
		return procesoNroRadicado;
	}

	/**
	 * Sets the procesoNroRadicado value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param procesoNroRadicado de proceso nro radicado
	 */
	public void setProcesoNroRadicado(java.lang.String procesoNroRadicado)
	{
		this.procesoNroRadicado = procesoNroRadicado;
	}

	/**
	 * Gets the procesoFechaRadicado value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return procesoFechaRadicado
	 */
	public java.util.Calendar getProcesoFechaRadicado()
	{
		return procesoFechaRadicado;
	}

	/**
	 * Sets the procesoFechaRadicado value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param procesoFechaRadicado de proceso fecha radicado
	 */
	public void setProcesoFechaRadicado(java.util.Calendar procesoFechaRadicado)
	{
		this.procesoFechaRadicado = procesoFechaRadicado;
	}

	/**
	 * Gets the oficinaOrigen value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return oficinaOrigen
	 */
	public java.lang.String getOficinaOrigen()
	{
		return oficinaOrigen;
	}

	/**
	 * Sets the oficinaOrigen value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param oficinaOrigen de oficina origen
	 */
	public void setOficinaOrigen(java.lang.String oficinaOrigen)
	{
		this.oficinaOrigen = oficinaOrigen;
	}

	/**
	 * Gets the codTipoDocumentoPublico value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return codTipoDocumentoPublico
	 */
	public java.lang.String getCodTipoDocumentoPublico()
	{
		return codTipoDocumentoPublico;
	}

	/**
	 * Sets the codTipoDocumentoPublico value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 */
	public void setCodTipoDocumentoPublico(java.lang.String codTipoDocumentoPublico)
	{
		this.codTipoDocumentoPublico = codTipoDocumentoPublico;
	}

	/**
	 * Gets the fechaDocumento value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return fechaDocumento
	 */
	public java.util.Calendar getFechaDocumento()
	{
		return fechaDocumento;
	}

	/**
	 * Sets the fechaDocumento value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param fechaDocumento de fecha documento
	 */
	public void setFechaDocumento(java.util.Calendar fechaDocumento)
	{
		this.fechaDocumento = fechaDocumento;
	}

	/**
	 * Gets the docNumero value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return docNumero
	 */
	public java.lang.String getDocNumero()
	{
		return docNumero;
	}

	/**
	 * Sets the docNumero value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param docNumero de doc numero
	 */
	public void setDocNumero(java.lang.String docNumero)
	{
		this.docNumero = docNumero;
	}

	/**
	 * Gets the docIdSGD value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return docIdSGD
	 */
	public java.lang.String getDocIdSGD()
	{
		return docIdSGD;
	}

	/**
	 * Sets the docIdSGD value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param docIdSGD de doc id SGD
	 */
	public void setDocIdSGD(java.lang.String docIdSGD)
	{
		this.docIdSGD = docIdSGD;
	}

	/**
	 * Gets the docNameSGD value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return docNameSGD
	 */
	public java.lang.String getDocNameSGD()
	{
		return docNameSGD;
	}

	/**
	 * Sets the docNameSGD value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param docNameSGD de doc name SGD
	 */
	public void setDocNameSGD(java.lang.String docNameSGD)
	{
		this.docNameSGD = docNameSGD;
	}

	/**
	 * Gets the nitComunidadEtnica value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return nitComunidadEtnica
	 */
	public java.lang.String getNitComunidadEtnica()
	{
		return nitComunidadEtnica;
	}

	/**
	 * Sets the nitComunidadEtnica value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param nitComunidadEtnica de nit comunidad etnica
	 */
	public void setNitComunidadEtnica(java.lang.String nitComunidadEtnica)
	{
		this.nitComunidadEtnica = nitComunidadEtnica;
	}

	/**
	 * Gets the creadoSNR value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return creadoSNR
	 */
	public java.lang.String getCreadoSNR()
	{
		return creadoSNR;
	}

	/**
	 * Sets the creadoSNR value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param creadoSNR de creado SNR
	 */
	public void setCreadoSNR(java.lang.String creadoSNR)
	{
		this.creadoSNR = creadoSNR;
	}

	/**
	 * Gets the idUsuario value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @return idUsuario
	 */
	public java.lang.String getIdUsuario()
	{
		return idUsuario;
	}

	/**
	 * Sets the idUsuario value for this TipoEntradaInscribirAlertaCabecera.
	 *
	 * @param idUsuario de id usuario
	 */
	public void setIdUsuario(java.lang.String idUsuario)
	{
		this.idUsuario = idUsuario;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaInscribirAlertaCabecera))
			return false;

		TipoEntradaInscribirAlertaCabecera other = (TipoEntradaInscribirAlertaCabecera)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoAlerta == null) && (other.getTipoAlerta() == null))
				|| ((this.tipoAlerta != null) && this.tipoAlerta.equals(other.getTipoAlerta())))
				&& (this.idEntidad == other.getIdEntidad())
				&& (((this.procesoNroRadicado == null) && (other.getProcesoNroRadicado() == null))
				|| ((this.procesoNroRadicado != null) && this.procesoNroRadicado.equals(other.getProcesoNroRadicado())))
				&& (((this.procesoFechaRadicado == null) && (other.getProcesoFechaRadicado() == null))
				|| ((this.procesoFechaRadicado != null)
				&& this.procesoFechaRadicado.equals(other.getProcesoFechaRadicado())))
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
				&& (((this.nitComunidadEtnica == null) && (other.getNitComunidadEtnica() == null))
				|| ((this.nitComunidadEtnica != null) && this.nitComunidadEtnica.equals(other.getNitComunidadEtnica())))
				&& (((this.creadoSNR == null) && (other.getCreadoSNR() == null))
				|| ((this.creadoSNR != null) && this.creadoSNR.equals(other.getCreadoSNR())))
				&& (((this.idUsuario == null) && (other.getIdUsuario() == null))
				|| ((this.idUsuario != null) && this.idUsuario.equals(other.getIdUsuario())));
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

		if(getTipoAlerta() != null)
			_hashCode += getTipoAlerta().hashCode();

		_hashCode += getIdEntidad();

		if(getProcesoNroRadicado() != null)
			_hashCode += getProcesoNroRadicado().hashCode();

		if(getProcesoFechaRadicado() != null)
			_hashCode += getProcesoFechaRadicado().hashCode();

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

		if(getNitComunidadEtnica() != null)
			_hashCode += getNitComunidadEtnica().hashCode();

		if(getCreadoSNR() != null)
			_hashCode += getCreadoSNR().hashCode();

		if(getIdUsuario() != null)
			_hashCode += getIdUsuario().hashCode();

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
