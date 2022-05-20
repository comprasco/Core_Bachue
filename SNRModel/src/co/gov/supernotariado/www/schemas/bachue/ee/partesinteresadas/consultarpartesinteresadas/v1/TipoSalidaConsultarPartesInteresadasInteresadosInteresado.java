/**
 * TipoSalidaConsultarPartesInteresadasInteresadosInteresado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarPartesInteresadasInteresadosInteresado implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4866749321203730883L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarPartesInteresadasInteresadosInteresado.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        ">>tipoSalidaConsultarPartesInteresadas>interesados>interesado"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "tipoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "tipoDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "numDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "primerNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "segundoNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "primerApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoParteInteresada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "tipoParteInteresada"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc         = null;
	
	/** Propiedad num documento persona. */
	private java.lang.String numDocumentoPersona;
	
	/** Propiedad primer apellido. */
	private java.lang.String primerApellido;
	
	/** Propiedad primer nombre. */
	private java.lang.String primerNombre;
	
	/** Propiedad razon social. */
	private java.lang.String razonSocial;
	
	/** Propiedad segundo apellido. */
	private java.lang.String segundoApellido;
	
	/** Propiedad segundo nombre. */
	private java.lang.String segundoNombre;
	
	/** Propiedad tipo documento persona. */
	private java.lang.String tipoDocumentoPersona;
	
	/** Propiedad tipo parte interesada. */
	private java.lang.String tipoParteInteresada;
	
	/** Propiedad tipo persona. */
	private java.lang.String tipoPersona;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc       = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar partes interesadas interesados interesado.
	 */
	public TipoSalidaConsultarPartesInteresadasInteresadosInteresado()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar partes interesadas interesados interesado.
	 *
	 * @param tipoPersona de tipo persona
	 * @param tipoDocumentoPersona de tipo documento persona
	 * @param numDocumentoPersona de num documento persona
	 * @param primerNombre de primer nombre
	 * @param segundoNombre de segundo nombre
	 * @param primerApellido de primer apellido
	 * @param segundoApellido de segundo apellido
	 * @param razonSocial de razon social
	 * @param tipoParteInteresada de tipo parte interesada
	 */
	public TipoSalidaConsultarPartesInteresadasInteresadosInteresado(
	    java.lang.String tipoPersona, java.lang.String tipoDocumentoPersona, java.lang.String numDocumentoPersona,
	    java.lang.String primerNombre, java.lang.String segundoNombre, java.lang.String primerApellido,
	    java.lang.String segundoApellido, java.lang.String razonSocial, java.lang.String tipoParteInteresada
	)
	{
		this.tipoPersona              = tipoPersona;
		this.tipoDocumentoPersona     = tipoDocumentoPersona;
		this.numDocumentoPersona      = numDocumentoPersona;
		this.primerNombre             = primerNombre;
		this.segundoNombre            = segundoNombre;
		this.primerApellido           = primerApellido;
		this.segundoApellido          = segundoApellido;
		this.razonSocial              = razonSocial;
		this.tipoParteInteresada      = tipoParteInteresada;
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
	 * Sets the numDocumentoPersona value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param numDocumentoPersona de num documento persona
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return numDocumentoPersona
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the primerApellido value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param primerApellido de primer apellido
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return primerApellido
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param primerNombre de primer nombre
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return primerNombre
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the razonSocial value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param razonSocial de razon social
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return razonSocial
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellido value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param segundoApellido de segundo apellido
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return segundoApellido
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param segundoNombre de segundo nombre
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return segundoNombre
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
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
	 * Sets the tipoDocumentoPersona value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param tipoDocumentoPersona de tipo documento persona
	 */
	public void setTipoDocumentoPersona(java.lang.String tipoDocumentoPersona)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return tipoDocumentoPersona
	 */
	public java.lang.String getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
	}

	/**
	 * Sets the tipoParteInteresada value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param tipoParteInteresada de tipo parte interesada
	 */
	public void setTipoParteInteresada(java.lang.String tipoParteInteresada)
	{
		this.tipoParteInteresada = tipoParteInteresada;
	}

	/**
	 * Gets the tipoParteInteresada value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @return tipoParteInteresada
	 */
	public java.lang.String getTipoParteInteresada()
	{
		return tipoParteInteresada;
	}

	/**
	 * Sets the tipoPersona value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
	 *
	 * @param tipoPersona de tipo persona
	 */
	public void setTipoPersona(java.lang.String tipoPersona)
	{
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the tipoPersona value for this TipoSalidaConsultarPartesInteresadasInteresadosInteresado.
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
		if(!(obj instanceof TipoSalidaConsultarPartesInteresadasInteresadosInteresado))
			return false;

		TipoSalidaConsultarPartesInteresadasInteresadosInteresado other = (TipoSalidaConsultarPartesInteresadasInteresadosInteresado)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoPersona == null) && (other.getTipoPersona() == null))
				|| ((this.tipoPersona != null) && this.tipoPersona.equals(other.getTipoPersona())))
				&& (((this.tipoDocumentoPersona == null) && (other.getTipoDocumentoPersona() == null))
				|| ((this.tipoDocumentoPersona != null)
				&& this.tipoDocumentoPersona.equals(other.getTipoDocumentoPersona())))
				&& (((this.numDocumentoPersona == null) && (other.getNumDocumentoPersona() == null))
				|| ((this.numDocumentoPersona != null)
				&& this.numDocumentoPersona.equals(other.getNumDocumentoPersona())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())))
				&& (((this.tipoParteInteresada == null) && (other.getTipoParteInteresada() == null))
				|| ((this.tipoParteInteresada != null)
				&& this.tipoParteInteresada.equals(other.getTipoParteInteresada())));
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

		if(getTipoPersona() != null)
			_hashCode += getTipoPersona().hashCode();

		if(getTipoDocumentoPersona() != null)
			_hashCode += getTipoDocumentoPersona().hashCode();

		if(getNumDocumentoPersona() != null)
			_hashCode += getNumDocumentoPersona().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		if(getTipoParteInteresada() != null)
			_hashCode += getTipoParteInteresada().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
