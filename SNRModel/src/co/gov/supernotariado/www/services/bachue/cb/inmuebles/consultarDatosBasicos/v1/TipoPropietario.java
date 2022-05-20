/**
 * TipoPropietario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1;



/**
 * Clase que contiene todos las propiedades TipoPropietario.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 19/03/2020
 */
public class TipoPropietario implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3617535010331945201L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoPropietario.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "tipoPropietario"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumentoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "numDocumentoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numNIT");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1", "numNIT"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "segundoApellido"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "segundoNombre"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1", "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("porcentajeParticipacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        "porcentajeParticipacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad num documento persona. */
	/* Número de documento del propietario cuando es:
	 *                                 tipo de persona: Natural */
	private java.lang.String numDocumentoPersona;

	/** Propiedad num NIT. */
	/* Número de Identificación Tributaria */
	private java.lang.String numNIT;

	/** Propiedad porcentaje participacion. */
	/* porcentaje de participación */
	private java.lang.String porcentajeParticipacion;

	/** Propiedad primer apellido. */
	/* primer apellido del propietario */
	private java.lang.String primerApellido;

	/** Propiedad primer nombre. */
	/* primer nombre del propietario */
	private java.lang.String primerNombre;

	/** Propiedad razon social. */
	/* razon social del propietario */
	private java.lang.String razonSocial;

	/** Propiedad segundo apellido. */
	/* segundo apellido del propietario */
	private java.lang.String segundoApellido;

	/** Propiedad segundo nombre. */
	/* segundo nombre del propietario */
	private java.lang.String segundoNombre;

	/** Propiedad tipo documento persona. */
	/* Tipos de documento posibles de un propietario:
	 *                                 CC, CE, PA, TI, NUIP */
	private java.lang.String tipoDocumentoPersona;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo propietario.
	 */
	public TipoPropietario()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo propietario.
	 *
	 * @param tipoDocumentoPersona de tipo documento persona
	 * @param numDocumentoPersona de num documento persona
	 * @param numNIT de num NIT
	 * @param primerApellido de primer apellido
	 * @param segundoApellido de segundo apellido
	 * @param primerNombre de primer nombre
	 * @param segundoNombre de segundo nombre
	 * @param razonSocial de razon social
	 * @param porcentajeParticipacion de porcentaje participacion
	 */
	public TipoPropietario(
	    java.lang.String tipoDocumentoPersona, java.lang.String numDocumentoPersona, java.lang.String numNIT,
	    java.lang.String primerApellido, java.lang.String segundoApellido, java.lang.String primerNombre,
	    java.lang.String segundoNombre, java.lang.String razonSocial, java.lang.String porcentajeParticipacion
	)
	{
		this.tipoDocumentoPersona        = tipoDocumentoPersona;
		this.numDocumentoPersona         = numDocumentoPersona;
		this.numNIT                      = numNIT;
		this.primerApellido              = primerApellido;
		this.segundoApellido             = segundoApellido;
		this.primerNombre                = primerNombre;
		this.segundoNombre               = segundoNombre;
		this.razonSocial                 = razonSocial;
		this.porcentajeParticipacion     = porcentajeParticipacion;
	}

	/**
	 * Gets the tipoDocumentoPersona value for this TipoPropietario.
	 *
	 * @return tipoDocumentoPersona   * Tipos de documento posibles de un propietario:
	     *                                 CC, CE, PA, TI, NUIP
	 */
	public java.lang.String getTipoDocumentoPersona()
	{
		return tipoDocumentoPersona;
	}

	/**
	 * Sets the tipoDocumentoPersona value for this TipoPropietario.
	 *
	 * @param tipoDocumentoPersona   * Tipos de documento posibles de un propietario:
	     *                                 CC, CE, PA, TI, NUIP
	 */
	public void setTipoDocumentoPersona(java.lang.String tipoDocumentoPersona)
	{
		this.tipoDocumentoPersona = tipoDocumentoPersona;
	}

	/**
	 * Gets the numDocumentoPersona value for this TipoPropietario.
	 *
	 * @return numDocumentoPersona   * Número de documento del propietario cuando es:
	     *                                 tipo de persona: Natural
	 */
	public java.lang.String getNumDocumentoPersona()
	{
		return numDocumentoPersona;
	}

	/**
	 * Sets the numDocumentoPersona value for this TipoPropietario.
	 *
	 * @param numDocumentoPersona   * Número de documento del propietario cuando es:
	     *                                 tipo de persona: Natural
	 */
	public void setNumDocumentoPersona(java.lang.String numDocumentoPersona)
	{
		this.numDocumentoPersona = numDocumentoPersona;
	}

	/**
	 * Gets the numNIT value for this TipoPropietario.
	 *
	 * @return numNIT   * Número de Identificación Tributaria
	 */
	public java.lang.String getNumNIT()
	{
		return numNIT;
	}

	/**
	 * Sets the numNIT value for this TipoPropietario.
	 *
	 * @param numNIT   * Número de Identificación Tributaria
	 */
	public void setNumNIT(java.lang.String numNIT)
	{
		this.numNIT = numNIT;
	}

	/**
	 * Gets the primerApellido value for this TipoPropietario.
	 *
	 * @return primerApellido   * primer apellido del propietario
	 */
	public java.lang.String getPrimerApellido()
	{
		return primerApellido;
	}

	/**
	 * Sets the primerApellido value for this TipoPropietario.
	 *
	 * @param primerApellido   * primer apellido del propietario
	 */
	public void setPrimerApellido(java.lang.String primerApellido)
	{
		this.primerApellido = primerApellido;
	}

	/**
	 * Gets the segundoApellido value for this TipoPropietario.
	 *
	 * @return segundoApellido   * segundo apellido del propietario
	 */
	public java.lang.String getSegundoApellido()
	{
		return segundoApellido;
	}

	/**
	 * Sets the segundoApellido value for this TipoPropietario.
	 *
	 * @param segundoApellido   * segundo apellido del propietario
	 */
	public void setSegundoApellido(java.lang.String segundoApellido)
	{
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Gets the primerNombre value for this TipoPropietario.
	 *
	 * @return primerNombre   * primer nombre del propietario
	 */
	public java.lang.String getPrimerNombre()
	{
		return primerNombre;
	}

	/**
	 * Sets the primerNombre value for this TipoPropietario.
	 *
	 * @param primerNombre   * primer nombre del propietario
	 */
	public void setPrimerNombre(java.lang.String primerNombre)
	{
		this.primerNombre = primerNombre;
	}

	/**
	 * Gets the segundoNombre value for this TipoPropietario.
	 *
	 * @return segundoNombre   * segundo nombre del propietario
	 */
	public java.lang.String getSegundoNombre()
	{
		return segundoNombre;
	}

	/**
	 * Sets the segundoNombre value for this TipoPropietario.
	 *
	 * @param segundoNombre   * segundo nombre del propietario
	 */
	public void setSegundoNombre(java.lang.String segundoNombre)
	{
		this.segundoNombre = segundoNombre;
	}

	/**
	 * Gets the razonSocial value for this TipoPropietario.
	 *
	 * @return razonSocial   * razon social del propietario
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the razonSocial value for this TipoPropietario.
	 *
	 * @param razonSocial   * razon social del propietario
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the porcentajeParticipacion value for this TipoPropietario.
	 *
	 * @return porcentajeParticipacion   * porcentaje de participación
	 */
	public java.lang.String getPorcentajeParticipacion()
	{
		return porcentajeParticipacion;
	}

	/**
	 * Sets the porcentajeParticipacion value for this TipoPropietario.
	 *
	 * @param porcentajeParticipacion   * porcentaje de participación
	 */
	public void setPorcentajeParticipacion(java.lang.String porcentajeParticipacion)
	{
		this.porcentajeParticipacion = porcentajeParticipacion;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoPropietario))
			return false;

		TipoPropietario other = (TipoPropietario)obj;

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
				&& (((this.numNIT == null) && (other.getNumNIT() == null))
				|| ((this.numNIT != null) && this.numNIT.equals(other.getNumNIT())))
				&& (((this.primerApellido == null) && (other.getPrimerApellido() == null))
				|| ((this.primerApellido != null) && this.primerApellido.equals(other.getPrimerApellido())))
				&& (((this.segundoApellido == null) && (other.getSegundoApellido() == null))
				|| ((this.segundoApellido != null) && this.segundoApellido.equals(other.getSegundoApellido())))
				&& (((this.primerNombre == null) && (other.getPrimerNombre() == null))
				|| ((this.primerNombre != null) && this.primerNombre.equals(other.getPrimerNombre())))
				&& (((this.segundoNombre == null) && (other.getSegundoNombre() == null))
				|| ((this.segundoNombre != null) && this.segundoNombre.equals(other.getSegundoNombre())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())))
				&& (((this.porcentajeParticipacion == null) && (other.getPorcentajeParticipacion() == null))
				|| ((this.porcentajeParticipacion != null)
				&& this.porcentajeParticipacion.equals(other.getPorcentajeParticipacion())));
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

		if(getNumNIT() != null)
			_hashCode += getNumNIT().hashCode();

		if(getPrimerApellido() != null)
			_hashCode += getPrimerApellido().hashCode();

		if(getSegundoApellido() != null)
			_hashCode += getSegundoApellido().hashCode();

		if(getPrimerNombre() != null)
			_hashCode += getPrimerNombre().hashCode();

		if(getSegundoNombre() != null)
			_hashCode += getSegundoNombre().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

		if(getPorcentajeParticipacion() != null)
			_hashCode += getPorcentajeParticipacion().hashCode();

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
