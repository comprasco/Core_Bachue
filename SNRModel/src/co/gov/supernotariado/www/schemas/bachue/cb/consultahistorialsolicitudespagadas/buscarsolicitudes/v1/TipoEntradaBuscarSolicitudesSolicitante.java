/**
 * TipoEntradaBuscarSolicitudesSolicitante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaBuscarSolicitudesSolicitante.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaBuscarSolicitudesSolicitante implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8274985731905285998L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaBuscarSolicitudesSolicitante.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        ">tipoEntradaBuscarSolicitudes>solicitante"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/buscarsolicitudes/v1",
		        "tipoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc    = null;
	
	/** Propiedad numero documento. */
	private java.lang.String numeroDocumento;
	
	/** Propiedad tipo documento. */
	private java.lang.String tipoDocumento;
	
	/** Propiedad tipo persona. */
	private java.lang.String tipoPersona;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc  = false;

	/**
	 * Instancia un nuevo objeto tipo entrada buscar solicitudes solicitante.
	 */
	public TipoEntradaBuscarSolicitudesSolicitante()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada buscar solicitudes solicitante.
	 *
	 * @param tipoDocumento de tipo documento
	 * @param numeroDocumento de numero documento
	 * @param tipoPersona de tipo persona
	 */
	public TipoEntradaBuscarSolicitudesSolicitante(
	    java.lang.String tipoDocumento, java.lang.String numeroDocumento, java.lang.String tipoPersona
	)
	{
		this.tipoDocumento       = tipoDocumento;
		this.numeroDocumento     = numeroDocumento;
		this.tipoPersona         = tipoPersona;
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
	 * Sets the numeroDocumento value for this TipoEntradaBuscarSolicitudesSolicitante.
	 *
	 * @param numeroDocumento de numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoEntradaBuscarSolicitudesSolicitante.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
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
	 * Sets the tipoDocumento value for this TipoEntradaBuscarSolicitudesSolicitante.
	 *
	 * @param tipoDocumento de tipo documento
	 */
	public void setTipoDocumento(java.lang.String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the tipoDocumento value for this TipoEntradaBuscarSolicitudesSolicitante.
	 *
	 * @return tipoDocumento
	 */
	public java.lang.String getTipoDocumento()
	{
		return tipoDocumento;
	}

	/**
	 * Sets the tipoPersona value for this TipoEntradaBuscarSolicitudesSolicitante.
	 *
	 * @param tipoPersona de tipo persona
	 */
	public void setTipoPersona(java.lang.String tipoPersona)
	{
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the tipoPersona value for this TipoEntradaBuscarSolicitudesSolicitante.
	 *
	 * @return tipoPersona
	 */
	public java.lang.String getTipoPersona()
	{
		return tipoPersona;
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
		if(!(obj instanceof TipoEntradaBuscarSolicitudesSolicitante))
			return false;

		TipoEntradaBuscarSolicitudesSolicitante other = (TipoEntradaBuscarSolicitudesSolicitante)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocumento == null) && (other.getTipoDocumento() == null))
				|| ((this.tipoDocumento != null) && this.tipoDocumento.equals(other.getTipoDocumento())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())))
				&& (((this.tipoPersona == null) && (other.getTipoPersona() == null))
				|| ((this.tipoPersona != null) && this.tipoPersona.equals(other.getTipoPersona())));
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

		if(getTipoDocumento() != null)
			_hashCode += getTipoDocumento().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		if(getTipoPersona() != null)
			_hashCode += getTipoPersona().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
