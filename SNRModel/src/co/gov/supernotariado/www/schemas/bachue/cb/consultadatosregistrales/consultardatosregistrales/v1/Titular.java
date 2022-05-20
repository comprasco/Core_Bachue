/**
 * Titular.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1;


/**
 * Clase que contiene todos las propiedades Titular.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class Titular implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1229327604287556048L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    Titular.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "titular"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionTitular");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "tipoIdentificacionTitular"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificacionTitular");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "identificacionTitular"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombreTitular");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "primerNombreTitular"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombreTitular");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "segundoNombreTitular"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellidoTitular");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "primerApellidoTitular"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellidoTitular");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "segundoApellidoTitular"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("porcentajeParticipacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultadatosregistrales/consultardatosregistrales/v1",
		        "porcentajeParticipacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc              = null;
	
	/** Propiedad identificacion titular. */
	private java.lang.String identificacionTitular;
	
	/** Propiedad porcentaje participacion. */
	private java.lang.String porcentajeParticipacion;
	
	/** Propiedad primer apellido titular. */
	private java.lang.String primerApellidoTitular;
	
	/** Propiedad primer nombre titular. */
	private java.lang.String primerNombreTitular;
	
	/** Propiedad segundo apellido titular. */
	private java.lang.String segundoApellidoTitular;
	
	/** Propiedad segundo nombre titular. */
	private java.lang.String segundoNombreTitular;
	
	/** Propiedad tipo identificacion titular. */
	private java.lang.String tipoIdentificacionTitular;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc            = false;

	/**
	 * Instancia un nuevo objeto titular.
	 */
	public Titular()
	{
	}

	/**
	 * Instancia un nuevo objeto titular.
	 *
	 * @param tipoIdentificacionTitular de tipo identificacion titular
	 * @param identificacionTitular de identificacion titular
	 * @param primerNombreTitular de primer nombre titular
	 * @param segundoNombreTitular de segundo nombre titular
	 * @param primerApellidoTitular de primer apellido titular
	 * @param segundoApellidoTitular de segundo apellido titular
	 * @param porcentajeParticipacion de porcentaje participacion
	 */
	public Titular(
	    java.lang.String tipoIdentificacionTitular, java.lang.String identificacionTitular,
	    java.lang.String primerNombreTitular, java.lang.String segundoNombreTitular,
	    java.lang.String primerApellidoTitular, java.lang.String segundoApellidoTitular,
	    java.lang.String porcentajeParticipacion
	)
	{
		this.tipoIdentificacionTitular     = tipoIdentificacionTitular;
		this.identificacionTitular         = identificacionTitular;
		this.primerNombreTitular           = primerNombreTitular;
		this.segundoNombreTitular          = segundoNombreTitular;
		this.primerApellidoTitular         = primerApellidoTitular;
		this.segundoApellidoTitular        = segundoApellidoTitular;
		this.porcentajeParticipacion       = porcentajeParticipacion;
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
	 * Sets the identificacionTitular value for this Titular.
	 *
	 * @param identificacionTitular de identificacion titular
	 */
	public void setIdentificacionTitular(java.lang.String identificacionTitular)
	{
		this.identificacionTitular = identificacionTitular;
	}

	/**
	 * Gets the identificacionTitular value for this Titular.
	 *
	 * @return identificacionTitular
	 */
	public java.lang.String getIdentificacionTitular()
	{
		return identificacionTitular;
	}

	/**
	 * Sets the porcentajeParticipacion value for this Titular.
	 *
	 * @param porcentajeParticipacion de porcentaje participacion
	 */
	public void setPorcentajeParticipacion(java.lang.String porcentajeParticipacion)
	{
		this.porcentajeParticipacion = porcentajeParticipacion;
	}

	/**
	 * Gets the porcentajeParticipacion value for this Titular.
	 *
	 * @return porcentajeParticipacion
	 */
	public java.lang.String getPorcentajeParticipacion()
	{
		return porcentajeParticipacion;
	}

	/**
	 * Sets the primerApellidoTitular value for this Titular.
	 *
	 * @param primerApellidoTitular de primer apellido titular
	 */
	public void setPrimerApellidoTitular(java.lang.String primerApellidoTitular)
	{
		this.primerApellidoTitular = primerApellidoTitular;
	}

	/**
	 * Gets the primerApellidoTitular value for this Titular.
	 *
	 * @return primerApellidoTitular
	 */
	public java.lang.String getPrimerApellidoTitular()
	{
		return primerApellidoTitular;
	}

	/**
	 * Sets the primerNombreTitular value for this Titular.
	 *
	 * @param primerNombreTitular de primer nombre titular
	 */
	public void setPrimerNombreTitular(java.lang.String primerNombreTitular)
	{
		this.primerNombreTitular = primerNombreTitular;
	}

	/**
	 * Gets the primerNombreTitular value for this Titular.
	 *
	 * @return primerNombreTitular
	 */
	public java.lang.String getPrimerNombreTitular()
	{
		return primerNombreTitular;
	}

	/**
	 * Sets the segundoApellidoTitular value for this Titular.
	 *
	 * @param segundoApellidoTitular de segundo apellido titular
	 */
	public void setSegundoApellidoTitular(java.lang.String segundoApellidoTitular)
	{
		this.segundoApellidoTitular = segundoApellidoTitular;
	}

	/**
	 * Gets the segundoApellidoTitular value for this Titular.
	 *
	 * @return segundoApellidoTitular
	 */
	public java.lang.String getSegundoApellidoTitular()
	{
		return segundoApellidoTitular;
	}

	/**
	 * Sets the segundoNombreTitular value for this Titular.
	 *
	 * @param segundoNombreTitular de segundo nombre titular
	 */
	public void setSegundoNombreTitular(java.lang.String segundoNombreTitular)
	{
		this.segundoNombreTitular = segundoNombreTitular;
	}

	/**
	 * Gets the segundoNombreTitular value for this Titular.
	 *
	 * @return segundoNombreTitular
	 */
	public java.lang.String getSegundoNombreTitular()
	{
		return segundoNombreTitular;
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
	 * Sets the tipoIdentificacionTitular value for this Titular.
	 *
	 * @param tipoIdentificacionTitular de tipo identificacion titular
	 */
	public void setTipoIdentificacionTitular(java.lang.String tipoIdentificacionTitular)
	{
		this.tipoIdentificacionTitular = tipoIdentificacionTitular;
	}

	/**
	 * Gets the tipoIdentificacionTitular value for this Titular.
	 *
	 * @return tipoIdentificacionTitular
	 */
	public java.lang.String getTipoIdentificacionTitular()
	{
		return tipoIdentificacionTitular;
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
		if(!(obj instanceof Titular))
			return false;

		Titular other = (Titular)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoIdentificacionTitular == null) && (other.getTipoIdentificacionTitular() == null))
				|| ((this.tipoIdentificacionTitular != null)
				&& this.tipoIdentificacionTitular.equals(other.getTipoIdentificacionTitular())))
				&& (((this.identificacionTitular == null) && (other.getIdentificacionTitular() == null))
				|| ((this.identificacionTitular != null)
				&& this.identificacionTitular.equals(other.getIdentificacionTitular())))
				&& (((this.primerNombreTitular == null) && (other.getPrimerNombreTitular() == null))
				|| ((this.primerNombreTitular != null)
				&& this.primerNombreTitular.equals(other.getPrimerNombreTitular())))
				&& (((this.segundoNombreTitular == null) && (other.getSegundoNombreTitular() == null))
				|| ((this.segundoNombreTitular != null)
				&& this.segundoNombreTitular.equals(other.getSegundoNombreTitular())))
				&& (((this.primerApellidoTitular == null) && (other.getPrimerApellidoTitular() == null))
				|| ((this.primerApellidoTitular != null)
				&& this.primerApellidoTitular.equals(other.getPrimerApellidoTitular())))
				&& (((this.segundoApellidoTitular == null) && (other.getSegundoApellidoTitular() == null))
				|| ((this.segundoApellidoTitular != null)
				&& this.segundoApellidoTitular.equals(other.getSegundoApellidoTitular())))
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

		if(getTipoIdentificacionTitular() != null)
			_hashCode += getTipoIdentificacionTitular().hashCode();

		if(getIdentificacionTitular() != null)
			_hashCode += getIdentificacionTitular().hashCode();

		if(getPrimerNombreTitular() != null)
			_hashCode += getPrimerNombreTitular().hashCode();

		if(getSegundoNombreTitular() != null)
			_hashCode += getSegundoNombreTitular().hashCode();

		if(getPrimerApellidoTitular() != null)
			_hashCode += getPrimerApellidoTitular().hashCode();

		if(getSegundoApellidoTitular() != null)
			_hashCode += getSegundoApellidoTitular().hashCode();

		if(getPorcentajeParticipacion() != null)
			_hashCode += getPorcentajeParticipacion().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
