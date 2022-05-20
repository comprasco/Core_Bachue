/**
 * TipoEntradaIngresoSolicitudInteresado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaIngresoSolicitudInteresado.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaIngresoSolicitudInteresado implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6799270720896098181L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIngresoSolicitudInteresado.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        ">tipoEntradaIngresoSolicitud>interesado"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "tipoDocumentoPersona"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        ">>tipoEntradaIngresoSolicitud>interesado>tipoDocumentoPersona"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "numDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "primerNombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "primerApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellido");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "segundoApellido"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codGenero");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        "codGenero"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("email");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1", "email"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;
	
	/** Propiedad cod genero. */
	private java.lang.String codGenero;
	
	/** Propiedad email. */
	private java.lang.String email;
	
	/** Propiedad num documento persona. */
	private java.lang.String numDocumentoPersona;
	
	/** Propiedad primer apellido. */
	private java.lang.String primerApellido;
	
	/** Propiedad primer nombre. */
	private java.lang.String primerNombre;
	
	/** Propiedad segundo apellido. */
	private java.lang.String segundoApellido;
	
	/** Propiedad segundo nombre. */
	private java.lang.String segundoNombre;
	
	/** Propiedad tipo documento persona. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona tipoDocumentoPersona;
	
	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud interesado.
	 */
	public TipoEntradaIngresoSolicitudInteresado()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud interesado.
	 *
	 * @param tipoDocumentoPersona de tipo documento persona
	 * @param numDocumentoPersona de num documento persona
	 * @param primerNombre de primer nombre
	 * @param segundoNombre de segundo nombre
	 * @param primerApellido de primer apellido
	 * @param segundoApellido de segundo apellido
	 * @param codGenero de cod genero
	 * @param email de email
	 */
	public TipoEntradaIngresoSolicitudInteresado(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona tipoDocumentoPersona,
	    java.lang.String                                                                                                                                numDocumentoPersona,
	    java.lang.String                                                                                                                                primerNombre,
	    java.lang.String                                                                                                                                segundoNombre,
	    java.lang.String                                                                                                                                primerApellido,
	    java.lang.String                                                                                                                                segundoApellido,
	    java.lang.String                                                                                                                                codGenero,
	    java.lang.String                                                                                                                                email
	)
	{
		this.tipoDocumentoPersona     = tipoDocumentoPersona;
		this.numDocumentoPersona      = numDocumentoPersona;
		this.primerNombre             = primerNombre;
		this.segundoNombre            = segundoNombre;
		this.primerApellido           = primerApellido;
		this.segundoApellido          = segundoApellido;
		this.codGenero                = codGenero;
		this.email                    = email;
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
	 * Sets the codGenero value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param codGenero de cod genero
	 */
	public void setCodGenero(java.lang.String codGenero)
	{
		this.codGenero = codGenero;
	}

	/**
	 * Gets the codGenero value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return codGenero
	 */
	public java.lang.String getCodGenero()
	{
		return codGenero;
	}

	/**
	 * Sets the email value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param email de email
	 */
	public void setEmail(java.lang.String email)
	{
		this.email = email;
	}

	/**
	 * Gets the email value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return email
	 */
	public java.lang.String getEmail()
	{
		return email;
	}

	/**
	 * Sets the numDocumentoPersona value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param numDocumentoPersona de num documento persona
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return numDocumentoPersona
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the primerApellido value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param primerApellido de primer apellido
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the primerApellido value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return primerApellido
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerNombre value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param primerNombre de primer nombre
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the primerNombre value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return primerNombre
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the segundoApellido value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param segundoApellido de segundo apellido
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return segundoApellido
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoNombre value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param segundoNombre de segundo nombre
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return segundoNombre
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
	}

	/**
	 * Sets the tipoDocumentoPersona value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @param tipoDocumentoPersona de tipo documento persona
	 */
	public void setTipoDocumentoPersona(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona tipoDocumentoPersona
	)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this TipoEntradaIngresoSolicitudInteresado.
	 *
	 * @return tipoDocumentoPersona
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaIngresoSolicitudInteresado))
			return false;

		TipoEntradaIngresoSolicitudInteresado other = (TipoEntradaIngresoSolicitudInteresado)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
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
				&& (((this.codGenero == null) && (other.getCodGenero() == null))
				|| ((this.codGenero != null) && this.codGenero.equals(other.getCodGenero())))
				&& (((this.email == null) && (other.getEmail() == null))
				|| ((this.email != null) && this.email.equals(other.getEmail())));
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

		if(getCodGenero() != null)
			_hashCode += getCodGenero().hashCode();

		if(getEmail() != null)
			_hashCode += getEmail().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
