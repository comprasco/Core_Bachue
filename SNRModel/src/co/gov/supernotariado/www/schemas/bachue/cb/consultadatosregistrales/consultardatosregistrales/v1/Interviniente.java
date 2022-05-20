/**
 * Interviniente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1;


/**
 * Clase que contiene todos las propiedades Interviniente.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class Interviniente implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5813388717877242336L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    Interviniente.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "interviniente"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "tipoIdentificacionInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificacionInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "identificacionInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombreInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "primerNombreInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombreInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "segundoNombreInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellidoInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "primerApellidoInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellidoInterviniente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "segundoApellidoInterviniente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("rol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "rol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc                    = null;
	
	/** Propiedad identificacion interviniente. */
	private java.lang.String identificacionInterviniente;
	
	/** Propiedad primer apellido interviniente. */
	private java.lang.String primerApellidoInterviniente;
	
	/** Propiedad primer nombre interviniente. */
	private java.lang.String primerNombreInterviniente;
	
	/** Propiedad rol. */
	private java.lang.String rol;
	
	/** Propiedad segundo apellido interviniente. */
	private java.lang.String segundoApellidoInterviniente;
	
	/** Propiedad segundo nombre interviniente. */
	private java.lang.String segundoNombreInterviniente;
	
	/** Propiedad tipo identificacion interviniente. */
	private java.lang.String tipoIdentificacionInterviniente;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc                  = false;

	/**
	 * Instancia un nuevo objeto interviniente.
	 */
	public Interviniente()
	{
	}

	/**
	 * Instancia un nuevo objeto interviniente.
	 *
	 * @param tipoIdentificacionInterviniente de tipo identificacion interviniente
	 * @param identificacionInterviniente de identificacion interviniente
	 * @param primerNombreInterviniente de primer nombre interviniente
	 * @param segundoNombreInterviniente de segundo nombre interviniente
	 * @param primerApellidoInterviniente de primer apellido interviniente
	 * @param segundoApellidoInterviniente de segundo apellido interviniente
	 * @param rol de rol
	 */
	public Interviniente(
	    java.lang.String tipoIdentificacionInterviniente, java.lang.String identificacionInterviniente,
	    java.lang.String primerNombreInterviniente, java.lang.String segundoNombreInterviniente,
	    java.lang.String primerApellidoInterviniente, java.lang.String segundoApellidoInterviniente,
	    java.lang.String rol
	)
	{
		this.tipoIdentificacionInterviniente     = tipoIdentificacionInterviniente;
		this.identificacionInterviniente         = identificacionInterviniente;
		this.primerNombreInterviniente           = primerNombreInterviniente;
		this.segundoNombreInterviniente          = segundoNombreInterviniente;
		this.primerApellidoInterviniente         = primerApellidoInterviniente;
		this.segundoApellidoInterviniente        = segundoApellidoInterviniente;
		this.rol                                 = rol;
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
	 * Sets the identificacionInterviniente value for this Interviniente.
	 *
	 * @param identificacionInterviniente de identificacion interviniente
	 */
	public void setIdentificacionInterviniente(java.lang.String identificacionInterviniente)
	{
		this.identificacionInterviniente = identificacionInterviniente;
	}

	/**
	 * Gets the identificacionInterviniente value for this Interviniente.
	 *
	 * @return identificacionInterviniente
	 */
	public java.lang.String getIdentificacionInterviniente()
	{
		return identificacionInterviniente;
	}

	/**
	 * Sets the primerApellidoInterviniente value for this Interviniente.
	 *
	 * @param primerApellidoInterviniente de primer apellido interviniente
	 */
	public void setPrimerApellidoInterviniente(java.lang.String primerApellidoInterviniente)
	{
		this.primerApellidoInterviniente = primerApellidoInterviniente;
	}

	/**
	 * Gets the primerApellidoInterviniente value for this Interviniente.
	 *
	 * @return primerApellidoInterviniente
	 */
	public java.lang.String getPrimerApellidoInterviniente()
	{
		return primerApellidoInterviniente;
	}

	/**
	 * Sets the primerNombreInterviniente value for this Interviniente.
	 *
	 * @param primerNombreInterviniente de primer nombre interviniente
	 */
	public void setPrimerNombreInterviniente(java.lang.String primerNombreInterviniente)
	{
		this.primerNombreInterviniente = primerNombreInterviniente;
	}

	/**
	 * Gets the primerNombreInterviniente value for this Interviniente.
	 *
	 * @return primerNombreInterviniente
	 */
	public java.lang.String getPrimerNombreInterviniente()
	{
		return primerNombreInterviniente;
	}

	/**
	 * Sets the rol value for this Interviniente.
	 *
	 * @param rol de rol
	 */
	public void setRol(java.lang.String rol)
	{
		this.rol = rol;
	}

	/**
	 * Gets the rol value for this Interviniente.
	 *
	 * @return rol
	 */
	public java.lang.String getRol()
	{
		return rol;
	}

	/**
	 * Sets the segundoApellidoInterviniente value for this Interviniente.
	 *
	 * @param segundoApellidoInterviniente de segundo apellido interviniente
	 */
	public void setSegundoApellidoInterviniente(java.lang.String segundoApellidoInterviniente)
	{
		this.segundoApellidoInterviniente = segundoApellidoInterviniente;
	}

	/**
	 * Gets the segundoApellidoInterviniente value for this Interviniente.
	 *
	 * @return segundoApellidoInterviniente
	 */
	public java.lang.String getSegundoApellidoInterviniente()
	{
		return segundoApellidoInterviniente;
	}

	/**
	 * Sets the segundoNombreInterviniente value for this Interviniente.
	 *
	 * @param segundoNombreInterviniente de segundo nombre interviniente
	 */
	public void setSegundoNombreInterviniente(java.lang.String segundoNombreInterviniente)
	{
		this.segundoNombreInterviniente = segundoNombreInterviniente;
	}

	/**
	 * Gets the segundoNombreInterviniente value for this Interviniente.
	 *
	 * @return segundoNombreInterviniente
	 */
	public java.lang.String getSegundoNombreInterviniente()
	{
		return segundoNombreInterviniente;
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
	 * Sets the tipoIdentificacionInterviniente value for this Interviniente.
	 *
	 * @param tipoIdentificacionInterviniente de tipo identificacion interviniente
	 */
	public void setTipoIdentificacionInterviniente(java.lang.String tipoIdentificacionInterviniente)
	{
		this.tipoIdentificacionInterviniente = tipoIdentificacionInterviniente;
	}

	/**
	 * Gets the tipoIdentificacionInterviniente value for this Interviniente.
	 *
	 * @return tipoIdentificacionInterviniente
	 */
	public java.lang.String getTipoIdentificacionInterviniente()
	{
		return tipoIdentificacionInterviniente;
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
		if(!(obj instanceof Interviniente))
			return false;

		Interviniente other = (Interviniente)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoIdentificacionInterviniente == null)
				&& (other.getTipoIdentificacionInterviniente() == null))
				|| ((this.tipoIdentificacionInterviniente != null)
				&& this.tipoIdentificacionInterviniente.equals(other.getTipoIdentificacionInterviniente())))
				&& (((this.identificacionInterviniente == null) && (other.getIdentificacionInterviniente() == null))
				|| ((this.identificacionInterviniente != null)
				&& this.identificacionInterviniente.equals(other.getIdentificacionInterviniente())))
				&& (((this.primerNombreInterviniente == null) && (other.getPrimerNombreInterviniente() == null))
				|| ((this.primerNombreInterviniente != null)
				&& this.primerNombreInterviniente.equals(other.getPrimerNombreInterviniente())))
				&& (((this.segundoNombreInterviniente == null) && (other.getSegundoNombreInterviniente() == null))
				|| ((this.segundoNombreInterviniente != null)
				&& this.segundoNombreInterviniente.equals(other.getSegundoNombreInterviniente())))
				&& (((this.primerApellidoInterviniente == null) && (other.getPrimerApellidoInterviniente() == null))
				|| ((this.primerApellidoInterviniente != null)
				&& this.primerApellidoInterviniente.equals(other.getPrimerApellidoInterviniente())))
				&& (((this.segundoApellidoInterviniente == null) && (other.getSegundoApellidoInterviniente() == null))
				|| ((this.segundoApellidoInterviniente != null)
				&& this.segundoApellidoInterviniente.equals(other.getSegundoApellidoInterviniente())))
				&& (((this.rol == null) && (other.getRol() == null))
				|| ((this.rol != null) && this.rol.equals(other.getRol())));
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

		if(getTipoIdentificacionInterviniente() != null)
			_hashCode += getTipoIdentificacionInterviniente().hashCode();

		if(getIdentificacionInterviniente() != null)
			_hashCode += getIdentificacionInterviniente().hashCode();

		if(getPrimerNombreInterviniente() != null)
			_hashCode += getPrimerNombreInterviniente().hashCode();

		if(getSegundoNombreInterviniente() != null)
			_hashCode += getSegundoNombreInterviniente().hashCode();

		if(getPrimerApellidoInterviniente() != null)
			_hashCode += getPrimerApellidoInterviniente().hashCode();

		if(getSegundoApellidoInterviniente() != null)
			_hashCode += getSegundoApellidoInterviniente().hashCode();

		if(getRol() != null)
			_hashCode += getRol().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
