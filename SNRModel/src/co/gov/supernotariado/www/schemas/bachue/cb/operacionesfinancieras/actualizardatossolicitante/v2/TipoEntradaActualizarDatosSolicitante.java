/**
 * TipoEntradaActualizarDatosSolicitante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.actualizardatossolicitante.v2;


/**
 * Clase que contiene todos las propiedades TipoEntradaActualizarDatosSolicitante.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaActualizarDatosSolicitante implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3873751965150037086L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaActualizarDatosSolicitante.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "tipoEntradaActualizarDatosSolicitante"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroReferencia");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "numeroReferencia"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "tipoDocSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "numeroDocSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombreSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "primerNombreSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombreSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "segundoNombreSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellidoSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "primerApellidoSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellidoSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "segundoApellidoSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/actualizardatossolicitante/v2",
		        "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc               = null;
	
	/** Propiedad numero doc solicitante. */
	private java.lang.String numeroDocSolicitante;
	
	/** Propiedad numero referencia. */
	private java.lang.String numeroReferencia;
	
	/** Propiedad primer apellido solicitante. */
	private java.lang.String primerApellidoSolicitante;
	
	/** Propiedad primer nombre solicitante. */
	private java.lang.String primerNombreSolicitante;
	
	/** Propiedad razon social. */
	private java.lang.String razonSocial;
	
	/** Propiedad segundo apellido solicitante. */
	private java.lang.String segundoApellidoSolicitante;
	
	/** Propiedad segundo nombre solicitante. */
	private java.lang.String segundoNombreSolicitante;
	
	/** Propiedad tipo doc solicitante. */
	private java.lang.String tipoDocSolicitante;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc             = false;

	/**
	 * Instancia un nuevo objeto tipo entrada actualizar datos solicitante.
	 */
	public TipoEntradaActualizarDatosSolicitante()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada actualizar datos solicitante.
	 *
	 * @param numeroReferencia de numero referencia
	 * @param tipoDocSolicitante de tipo doc solicitante
	 * @param numeroDocSolicitante de numero doc solicitante
	 * @param primerNombreSolicitante de primer nombre solicitante
	 * @param segundoNombreSolicitante de segundo nombre solicitante
	 * @param primerApellidoSolicitante de primer apellido solicitante
	 * @param segundoApellidoSolicitante de segundo apellido solicitante
	 * @param razonSocial de razon social
	 */
	public TipoEntradaActualizarDatosSolicitante(
	    java.lang.String numeroReferencia, java.lang.String tipoDocSolicitante, java.lang.String numeroDocSolicitante,
	    java.lang.String primerNombreSolicitante, java.lang.String segundoNombreSolicitante,
	    java.lang.String primerApellidoSolicitante, java.lang.String segundoApellidoSolicitante,
	    java.lang.String razonSocial
	)
	{
		this.numeroReferencia               = numeroReferencia;
		this.tipoDocSolicitante             = tipoDocSolicitante;
		this.numeroDocSolicitante           = numeroDocSolicitante;
		this.primerNombreSolicitante        = primerNombreSolicitante;
		this.segundoNombreSolicitante       = segundoNombreSolicitante;
		this.primerApellidoSolicitante      = primerApellidoSolicitante;
		this.segundoApellidoSolicitante     = segundoApellidoSolicitante;
		this.razonSocial                    = razonSocial;
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
	 * Sets the numeroDocSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param numeroDocSolicitante de numero doc solicitante
	 */
	public void setNumeroDocSolicitante(java.lang.String numeroDocSolicitante)
	{
		this.numeroDocSolicitante = numeroDocSolicitante;
	}

	/**
	 * Gets the numeroDocSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return numeroDocSolicitante
	 */
	public java.lang.String getNumeroDocSolicitante()
	{
		return numeroDocSolicitante;
	}

	/**
	 * Sets the numeroReferencia value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param numeroReferencia de numero referencia
	 */
	public void setNumeroReferencia(java.lang.String numeroReferencia)
	{
		this.numeroReferencia = numeroReferencia;
	}

	/**
	 * Gets the numeroReferencia value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return numeroReferencia
	 */
	public java.lang.String getNumeroReferencia()
	{
		return numeroReferencia;
	}

	/**
	 * Sets the primerApellidoSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param primerApellidoSolicitante de primer apellido solicitante
	 */
	public void setPrimerApellidoSolicitante(java.lang.String primerApellidoSolicitante)
	{
		this.primerApellidoSolicitante = primerApellidoSolicitante;
	}

	/**
	 * Gets the primerApellidoSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return primerApellidoSolicitante
	 */
	public java.lang.String getPrimerApellidoSolicitante()
	{
		return primerApellidoSolicitante;
	}

	/**
	 * Sets the primerNombreSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param primerNombreSolicitante de primer nombre solicitante
	 */
	public void setPrimerNombreSolicitante(java.lang.String primerNombreSolicitante)
	{
		this.primerNombreSolicitante = primerNombreSolicitante;
	}

	/**
	 * Gets the primerNombreSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return primerNombreSolicitante
	 */
	public java.lang.String getPrimerNombreSolicitante()
	{
		return primerNombreSolicitante;
	}

	/**
	 * Sets the razonSocial value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param razonSocial de razon social
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return razonSocial
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellidoSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param segundoApellidoSolicitante de segundo apellido solicitante
	 */
	public void setSegundoApellidoSolicitante(java.lang.String segundoApellidoSolicitante)
	{
		this.segundoApellidoSolicitante = segundoApellidoSolicitante;
	}

	/**
	 * Gets the segundoApellidoSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return segundoApellidoSolicitante
	 */
	public java.lang.String getSegundoApellidoSolicitante()
	{
		return segundoApellidoSolicitante;
	}

	/**
	 * Sets the segundoNombreSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param segundoNombreSolicitante de segundo nombre solicitante
	 */
	public void setSegundoNombreSolicitante(java.lang.String segundoNombreSolicitante)
	{
		this.segundoNombreSolicitante = segundoNombreSolicitante;
	}

	/**
	 * Gets the segundoNombreSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return segundoNombreSolicitante
	 */
	public java.lang.String getSegundoNombreSolicitante()
	{
		return segundoNombreSolicitante;
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
	 * Sets the tipoDocSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @param tipoDocSolicitante de tipo doc solicitante
	 */
	public void setTipoDocSolicitante(java.lang.String tipoDocSolicitante)
	{
		this.tipoDocSolicitante = tipoDocSolicitante;
	}

	/**
	 * Gets the tipoDocSolicitante value for this TipoEntradaActualizarDatosSolicitante.
	 *
	 * @return tipoDocSolicitante
	 */
	public java.lang.String getTipoDocSolicitante()
	{
		return tipoDocSolicitante;
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
		if(!(obj instanceof TipoEntradaActualizarDatosSolicitante))
			return false;

		TipoEntradaActualizarDatosSolicitante other = (TipoEntradaActualizarDatosSolicitante)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numeroReferencia == null) && (other.getNumeroReferencia() == null))
				|| ((this.numeroReferencia != null) && this.numeroReferencia.equals(other.getNumeroReferencia())))
				&& (((this.tipoDocSolicitante == null) && (other.getTipoDocSolicitante() == null))
				|| ((this.tipoDocSolicitante != null) && this.tipoDocSolicitante.equals(other.getTipoDocSolicitante())))
				&& (((this.numeroDocSolicitante == null) && (other.getNumeroDocSolicitante() == null))
				|| ((this.numeroDocSolicitante != null)
				&& this.numeroDocSolicitante.equals(other.getNumeroDocSolicitante())))
				&& (((this.primerNombreSolicitante == null) && (other.getPrimerNombreSolicitante() == null))
				|| ((this.primerNombreSolicitante != null)
				&& this.primerNombreSolicitante.equals(other.getPrimerNombreSolicitante())))
				&& (((this.segundoNombreSolicitante == null) && (other.getSegundoNombreSolicitante() == null))
				|| ((this.segundoNombreSolicitante != null)
				&& this.segundoNombreSolicitante.equals(other.getSegundoNombreSolicitante())))
				&& (((this.primerApellidoSolicitante == null) && (other.getPrimerApellidoSolicitante() == null))
				|| ((this.primerApellidoSolicitante != null)
				&& this.primerApellidoSolicitante.equals(other.getPrimerApellidoSolicitante())))
				&& (((this.segundoApellidoSolicitante == null) && (other.getSegundoApellidoSolicitante() == null))
				|| ((this.segundoApellidoSolicitante != null)
				&& this.segundoApellidoSolicitante.equals(other.getSegundoApellidoSolicitante())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())));
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

		if(getNumeroReferencia() != null)
			_hashCode += getNumeroReferencia().hashCode();

		if(getTipoDocSolicitante() != null)
			_hashCode += getTipoDocSolicitante().hashCode();

		if(getNumeroDocSolicitante() != null)
			_hashCode += getNumeroDocSolicitante().hashCode();

		if(getPrimerNombreSolicitante() != null)
			_hashCode += getPrimerNombreSolicitante().hashCode();

		if(getSegundoNombreSolicitante() != null)
			_hashCode += getSegundoNombreSolicitante().hashCode();

		if(getPrimerApellidoSolicitante() != null)
			_hashCode += getPrimerApellidoSolicitante().hashCode();

		if(getSegundoApellidoSolicitante() != null)
			_hashCode += getSegundoApellidoSolicitante().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
