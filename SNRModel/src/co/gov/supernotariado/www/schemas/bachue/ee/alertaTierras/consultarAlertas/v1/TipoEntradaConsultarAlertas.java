/**
 * TipoEntradaConsultarAlertas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarAlertas.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaConsultarAlertas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8511397055027822163L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarAlertas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        "tipoEntradaConsultarAlertas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("esSNR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "esSNR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoEstado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "codigoEstado"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        ">tipoEntradaConsultarAlertas>codigoEstado"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "idEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaInscripcion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        "fechaInscripcion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codTipoDocumentoPublico");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        "codTipoDocumentoPublico"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreComunidadEtnica");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        "nombreComunidadEtnica"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	private java.lang.String codCirculoRegistral;

	/** Propiedad cod tipo documento publico. */
	private java.lang.String codTipoDocumentoPublico;

	/** Propiedad codigo estado. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertasCodigoEstado codigoEstado;

	/** Propiedad es SNR. */
	private java.lang.String esSNR;

	/** Propiedad fecha inscripcion. */
	private java.util.Calendar fechaInscripcion;

	/** Propiedad id entidad. */
	private java.lang.String idEntidad;

	/** Propiedad nombre comunidad etnica. */
	private java.lang.String nombreComunidadEtnica;

	/** Propiedad num matricula inmobiliaria. */
	private java.lang.Integer numMatriculaInmobiliaria;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar alertas.
	 */
	public TipoEntradaConsultarAlertas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar alertas.
	 *
	 * @param esSNR de es SNR
	 * @param codigoEstado de codigo estado
	 * @param idEntidad de id entidad
	 * @param fechaInscripcion de fecha inscripcion
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 * @param codCirculoRegistral de cod circulo registral
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param nombreComunidadEtnica de nombre comunidad etnica
	 */
	public TipoEntradaConsultarAlertas(
	    java.lang.String esSNR,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertasCodigoEstado codigoEstado,
	    java.lang.String idEntidad, java.util.Calendar fechaInscripcion, java.lang.String codTipoDocumentoPublico,
	    java.lang.String codCirculoRegistral, java.lang.Integer numMatriculaInmobiliaria,
	    java.lang.String nombreComunidadEtnica
	)
	{
		this.esSNR                        = esSNR;
		this.codigoEstado                 = codigoEstado;
		this.idEntidad                    = idEntidad;
		this.fechaInscripcion             = fechaInscripcion;
		this.codTipoDocumentoPublico      = codTipoDocumentoPublico;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.nombreComunidadEtnica        = nombreComunidadEtnica;
	}

	/**
	 * Gets the esSNR value for this TipoEntradaConsultarAlertas.
	 *
	 * @return esSNR
	 */
	public java.lang.String getEsSNR()
	{
		return esSNR;
	}

	/**
	 * Sets the esSNR value for this TipoEntradaConsultarAlertas.
	 *
	 * @param esSNR de es SNR
	 */
	public void setEsSNR(java.lang.String esSNR)
	{
		this.esSNR = esSNR;
	}

	/**
	 * Gets the codigoEstado value for this TipoEntradaConsultarAlertas.
	 *
	 * @return codigoEstado
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertasCodigoEstado getCodigoEstado()
	{
		return codigoEstado;
	}

	/**
	 * Sets the codigoEstado value for this TipoEntradaConsultarAlertas.
	 *
	 * @param codigoEstado de codigo estado
	 */
	public void setCodigoEstado(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoEntradaConsultarAlertasCodigoEstado codigoEstado
	)
	{
		this.codigoEstado = codigoEstado;
	}

	/**
	 * Gets the idEntidad value for this TipoEntradaConsultarAlertas.
	 *
	 * @return idEntidad
	 */
	public java.lang.String getIdEntidad()
	{
		return idEntidad;
	}

	/**
	 * Sets the idEntidad value for this TipoEntradaConsultarAlertas.
	 *
	 * @param idEntidad de id entidad
	 */
	public void setIdEntidad(java.lang.String idEntidad)
	{
		this.idEntidad = idEntidad;
	}

	/**
	 * Gets the fechaInscripcion value for this TipoEntradaConsultarAlertas.
	 *
	 * @return fechaInscripcion
	 */
	public java.util.Calendar getFechaInscripcion()
	{
		return fechaInscripcion;
	}

	/**
	 * Sets the fechaInscripcion value for this TipoEntradaConsultarAlertas.
	 *
	 * @param fechaInscripcion de fecha inscripcion
	 */
	public void setFechaInscripcion(java.util.Calendar fechaInscripcion)
	{
		this.fechaInscripcion = fechaInscripcion;
	}

	/**
	 * Gets the codTipoDocumentoPublico value for this TipoEntradaConsultarAlertas.
	 *
	 * @return codTipoDocumentoPublico
	 */
	public java.lang.String getCodTipoDocumentoPublico()
	{
		return codTipoDocumentoPublico;
	}

	/**
	 * Sets the codTipoDocumentoPublico value for this TipoEntradaConsultarAlertas.
	 *
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 */
	public void setCodTipoDocumentoPublico(java.lang.String codTipoDocumentoPublico)
	{
		this.codTipoDocumentoPublico = codTipoDocumentoPublico;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaConsultarAlertas.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaConsultarAlertas.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoEntradaConsultarAlertas.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public java.lang.Integer getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoEntradaConsultarAlertas.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(java.lang.Integer numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the nombreComunidadEtnica value for this TipoEntradaConsultarAlertas.
	 *
	 * @return nombreComunidadEtnica
	 */
	public java.lang.String getNombreComunidadEtnica()
	{
		return nombreComunidadEtnica;
	}

	/**
	 * Sets the nombreComunidadEtnica value for this TipoEntradaConsultarAlertas.
	 *
	 * @param nombreComunidadEtnica de nombre comunidad etnica
	 */
	public void setNombreComunidadEtnica(java.lang.String nombreComunidadEtnica)
	{
		this.nombreComunidadEtnica = nombreComunidadEtnica;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarAlertas))
			return false;

		TipoEntradaConsultarAlertas other = (TipoEntradaConsultarAlertas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.esSNR == null) && (other.getEsSNR() == null))
				|| ((this.esSNR != null) && this.esSNR.equals(other.getEsSNR())))
				&& (((this.codigoEstado == null) && (other.getCodigoEstado() == null))
				|| ((this.codigoEstado != null) && this.codigoEstado.equals(other.getCodigoEstado())))
				&& (((this.idEntidad == null) && (other.getIdEntidad() == null))
				|| ((this.idEntidad != null) && this.idEntidad.equals(other.getIdEntidad())))
				&& (((this.fechaInscripcion == null) && (other.getFechaInscripcion() == null))
				|| ((this.fechaInscripcion != null) && this.fechaInscripcion.equals(other.getFechaInscripcion())))
				&& (((this.codTipoDocumentoPublico == null) && (other.getCodTipoDocumentoPublico() == null))
				|| ((this.codTipoDocumentoPublico != null)
				&& this.codTipoDocumentoPublico.equals(other.getCodTipoDocumentoPublico())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.nombreComunidadEtnica == null) && (other.getNombreComunidadEtnica() == null))
				|| ((this.nombreComunidadEtnica != null)
				&& this.nombreComunidadEtnica.equals(other.getNombreComunidadEtnica())));
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

		if(getEsSNR() != null)
			_hashCode += getEsSNR().hashCode();

		if(getCodigoEstado() != null)
			_hashCode += getCodigoEstado().hashCode();

		if(getIdEntidad() != null)
			_hashCode += getIdEntidad().hashCode();

		if(getFechaInscripcion() != null)
			_hashCode += getFechaInscripcion().hashCode();

		if(getCodTipoDocumentoPublico() != null)
			_hashCode += getCodTipoDocumentoPublico().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getNombreComunidadEtnica() != null)
			_hashCode += getNombreComunidadEtnica().hashCode();

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
