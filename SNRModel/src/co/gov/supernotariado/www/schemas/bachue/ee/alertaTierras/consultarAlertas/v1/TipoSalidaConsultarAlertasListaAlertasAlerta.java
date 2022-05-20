/**
 * TipoSalidaConsultarAlertasListaAlertasAlerta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarAlertasListaAlertasAlerta.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaConsultarAlertasListaAlertasAlerta implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5419164513013159749L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarAlertasListaAlertasAlerta.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        ">>tipoSalidaConsultarAlertas>listaAlertas>alerta"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "idAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "estadoAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreEntidad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "nombreEntidad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod tipo documento publico. */
	private java.lang.String codTipoDocumentoPublico;

	/** Propiedad estado alerta. */
	private java.lang.String estadoAlerta;

	/** Propiedad fecha inscripcion. */
	private java.util.Calendar fechaInscripcion;

	/** Propiedad nombre comunidad etnica. */
	private java.lang.String nombreComunidadEtnica;

	/** Propiedad nombre entidad. */
	private java.lang.String nombreEntidad;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad id alerta. */
	private int idAlerta;

	/**
	 * Instancia un nuevo objeto tipo salida consultar alertas lista alertas alerta.
	 */
	public TipoSalidaConsultarAlertasListaAlertasAlerta()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar alertas lista alertas alerta.
	 *
	 * @param idAlerta de id alerta
	 * @param estadoAlerta de estado alerta
	 * @param nombreEntidad de nombre entidad
	 * @param fechaInscripcion de fecha inscripcion
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 * @param nombreComunidadEtnica de nombre comunidad etnica
	 */
	public TipoSalidaConsultarAlertasListaAlertasAlerta(
	    int idAlerta, java.lang.String estadoAlerta, java.lang.String nombreEntidad, java.util.Calendar fechaInscripcion,
	    java.lang.String codTipoDocumentoPublico, java.lang.String nombreComunidadEtnica
	)
	{
		this.idAlerta                    = idAlerta;
		this.estadoAlerta                = estadoAlerta;
		this.nombreEntidad               = nombreEntidad;
		this.fechaInscripcion            = fechaInscripcion;
		this.codTipoDocumentoPublico     = codTipoDocumentoPublico;
		this.nombreComunidadEtnica       = nombreComunidadEtnica;
	}

	/**
	 * Gets the idAlerta value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @return idAlerta
	 */
	public int getIdAlerta()
	{
		return idAlerta;
	}

	/**
	 * Sets the idAlerta value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @param idAlerta de id alerta
	 */
	public void setIdAlerta(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/**
	 * Gets the estadoAlerta value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @return estadoAlerta
	 */
	public java.lang.String getEstadoAlerta()
	{
		return estadoAlerta;
	}

	/**
	 * Sets the estadoAlerta value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @param estadoAlerta de estado alerta
	 */
	public void setEstadoAlerta(java.lang.String estadoAlerta)
	{
		this.estadoAlerta = estadoAlerta;
	}

	/**
	 * Gets the nombreEntidad value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @return nombreEntidad
	 */
	public java.lang.String getNombreEntidad()
	{
		return nombreEntidad;
	}

	/**
	 * Sets the nombreEntidad value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @param nombreEntidad de nombre entidad
	 */
	public void setNombreEntidad(java.lang.String nombreEntidad)
	{
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * Gets the fechaInscripcion value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @return fechaInscripcion
	 */
	public java.util.Calendar getFechaInscripcion()
	{
		return fechaInscripcion;
	}

	/**
	 * Sets the fechaInscripcion value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @param fechaInscripcion de fecha inscripcion
	 */
	public void setFechaInscripcion(java.util.Calendar fechaInscripcion)
	{
		this.fechaInscripcion = fechaInscripcion;
	}

	/**
	 * Gets the codTipoDocumentoPublico value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @return codTipoDocumentoPublico
	 */
	public java.lang.String getCodTipoDocumentoPublico()
	{
		return codTipoDocumentoPublico;
	}

	/**
	 * Sets the codTipoDocumentoPublico value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @param codTipoDocumentoPublico de cod tipo documento publico
	 */
	public void setCodTipoDocumentoPublico(java.lang.String codTipoDocumentoPublico)
	{
		this.codTipoDocumentoPublico = codTipoDocumentoPublico;
	}

	/**
	 * Gets the nombreComunidadEtnica value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
	 *
	 * @return nombreComunidadEtnica
	 */
	public java.lang.String getNombreComunidadEtnica()
	{
		return nombreComunidadEtnica;
	}

	/**
	 * Sets the nombreComunidadEtnica value for this TipoSalidaConsultarAlertasListaAlertasAlerta.
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
		if(!(obj instanceof TipoSalidaConsultarAlertasListaAlertasAlerta))
			return false;

		TipoSalidaConsultarAlertasListaAlertasAlerta other = (TipoSalidaConsultarAlertasListaAlertasAlerta)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true && (this.idAlerta == other.getIdAlerta())
				&& (((this.estadoAlerta == null) && (other.getEstadoAlerta() == null))
				|| ((this.estadoAlerta != null) && this.estadoAlerta.equals(other.getEstadoAlerta())))
				&& (((this.nombreEntidad == null) && (other.getNombreEntidad() == null))
				|| ((this.nombreEntidad != null) && this.nombreEntidad.equals(other.getNombreEntidad())))
				&& (((this.fechaInscripcion == null) && (other.getFechaInscripcion() == null))
				|| ((this.fechaInscripcion != null) && this.fechaInscripcion.equals(other.getFechaInscripcion())))
				&& (((this.codTipoDocumentoPublico == null) && (other.getCodTipoDocumentoPublico() == null))
				|| ((this.codTipoDocumentoPublico != null)
				&& this.codTipoDocumentoPublico.equals(other.getCodTipoDocumentoPublico())))
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
		_hashCode += getIdAlerta();

		if(getEstadoAlerta() != null)
			_hashCode += getEstadoAlerta().hashCode();

		if(getNombreEntidad() != null)
			_hashCode += getNombreEntidad().hashCode();

		if(getFechaInscripcion() != null)
			_hashCode += getFechaInscripcion().hashCode();

		if(getCodTipoDocumentoPublico() != null)
			_hashCode += getCodTipoDocumentoPublico().hashCode();

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
