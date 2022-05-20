/**
 * TipoSalvedad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1;



/**
 * Clase que contiene todos las propiedades TipoSalvedad.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class TipoSalvedad implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4602973396612121511L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalvedad.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1", "tipoSalvedad"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1", "numAnotacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroCorreccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
		        "numeroCorreccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1", "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("radicacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1", "radicacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaSalvedad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
		        "fechaSalvedad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionSalvedad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
		        "descripcionSalvedad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nir. */
	/* Corresponde al NIR de la salvedad. */
	private java.lang.String NIR;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad descripcion salvedad. */
	/* descripción de la salvedad */
	private java.lang.String descripcionSalvedad;

	/** Propiedad fecha salvedad. */
	/* Corresponde a la fecha de la salvedad. */
	private java.util.Calendar fechaSalvedad;

	/** Propiedad num anotacion. */
	/* Corresponde al número de la anotación de la
	 *                                 salvedad. */
	private java.lang.String numAnotacion;

	/** Propiedad numero correccion. */
	/* Corresponde al número de la corrección de la
	 *                                 salvedad. */
	private java.lang.String numeroCorreccion;

	/** Propiedad radicacion. */
	/* Corresponde a la radicacion de la salvedad. */
	private java.lang.String radicacion;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salvedad.
	 */
	public TipoSalvedad()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salvedad.
	 *
	 * @param numAnotacion de num anotacion
	 * @param numeroCorreccion de numero correccion
	 * @param NIR de nir
	 * @param radicacion de radicacion
	 * @param fechaSalvedad de fecha salvedad
	 * @param descripcionSalvedad de descripcion salvedad
	 */
	public TipoSalvedad(
	    java.lang.String numAnotacion, java.lang.String numeroCorreccion, java.lang.String NIR,
	    java.lang.String radicacion, java.util.Calendar fechaSalvedad, java.lang.String descripcionSalvedad
	)
	{
		this.numAnotacion            = numAnotacion;
		this.numeroCorreccion        = numeroCorreccion;
		this.NIR                     = NIR;
		this.radicacion              = radicacion;
		this.fechaSalvedad           = fechaSalvedad;
		this.descripcionSalvedad     = descripcionSalvedad;
	}

	/**
	 * Gets the numAnotacion value for this TipoSalvedad.
	 *
	 * @return numAnotacion   * Corresponde al número de la anotación de la
	 *                                 salvedad.
	 */
	public java.lang.String getNumAnotacion()
	{
		return numAnotacion;
	}

	/**
	 * Sets the numAnotacion value for this TipoSalvedad.
	 *
	 * @param numAnotacion   * Corresponde al número de la anotación de la
	 *                                 salvedad.
	 */
	public void setNumAnotacion(java.lang.String numAnotacion)
	{
		this.numAnotacion = numAnotacion;
	}

	/**
	 * Gets the numeroCorreccion value for this TipoSalvedad.
	 *
	 * @return numeroCorreccion   * Corresponde al número de la corrección de la
	 *                                 salvedad.
	 */
	public java.lang.String getNumeroCorreccion()
	{
		return numeroCorreccion;
	}

	/**
	 * Sets the numeroCorreccion value for this TipoSalvedad.
	 *
	 * @param numeroCorreccion   * Corresponde al número de la corrección de la
	 *                                 salvedad.
	 */
	public void setNumeroCorreccion(java.lang.String numeroCorreccion)
	{
		this.numeroCorreccion = numeroCorreccion;
	}

	/**
	 * Gets the NIR value for this TipoSalvedad.
	 *
	 * @return NIR   * Corresponde al NIR de la salvedad.
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the NIR value for this TipoSalvedad.
	 *
	 * @param NIR   * Corresponde al NIR de la salvedad.
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the radicacion value for this TipoSalvedad.
	 *
	 * @return radicacion   * Corresponde a la radicacion de la salvedad.
	 */
	public java.lang.String getRadicacion()
	{
		return radicacion;
	}

	/**
	 * Sets the radicacion value for this TipoSalvedad.
	 *
	 * @param radicacion   * Corresponde a la radicacion de la salvedad.
	 */
	public void setRadicacion(java.lang.String radicacion)
	{
		this.radicacion = radicacion;
	}

	/**
	 * Gets the fechaSalvedad value for this TipoSalvedad.
	 *
	 * @return fechaSalvedad   * Corresponde a la fecha de la salvedad.
	 */
	public java.util.Calendar getFechaSalvedad()
	{
		return fechaSalvedad;
	}

	/**
	 * Sets the fechaSalvedad value for this TipoSalvedad.
	 *
	 * @param fechaSalvedad   * Corresponde a la fecha de la salvedad.
	 */
	public void setFechaSalvedad(java.util.Calendar fechaSalvedad)
	{
		this.fechaSalvedad = fechaSalvedad;
	}

	/**
	 * Gets the descripcionSalvedad value for this TipoSalvedad.
	 *
	 * @return descripcionSalvedad   * descripción de la salvedad
	 */
	public java.lang.String getDescripcionSalvedad()
	{
		return descripcionSalvedad;
	}

	/**
	 * Sets the descripcionSalvedad value for this TipoSalvedad.
	 *
	 * @param descripcionSalvedad   * descripción de la salvedad
	 */
	public void setDescripcionSalvedad(java.lang.String descripcionSalvedad)
	{
		this.descripcionSalvedad = descripcionSalvedad;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalvedad))
			return false;

		TipoSalvedad other = (TipoSalvedad)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numAnotacion == null) && (other.getNumAnotacion() == null))
				|| ((this.numAnotacion != null) && this.numAnotacion.equals(other.getNumAnotacion())))
				&& (((this.numeroCorreccion == null) && (other.getNumeroCorreccion() == null))
				|| ((this.numeroCorreccion != null) && this.numeroCorreccion.equals(other.getNumeroCorreccion())))
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())))
				&& (((this.radicacion == null) && (other.getRadicacion() == null))
				|| ((this.radicacion != null) && this.radicacion.equals(other.getRadicacion())))
				&& (((this.fechaSalvedad == null) && (other.getFechaSalvedad() == null))
				|| ((this.fechaSalvedad != null) && this.fechaSalvedad.equals(other.getFechaSalvedad())))
				&& (((this.descripcionSalvedad == null) && (other.getDescripcionSalvedad() == null))
				|| ((this.descripcionSalvedad != null)
				&& this.descripcionSalvedad.equals(other.getDescripcionSalvedad())));
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

		if(getNumAnotacion() != null)
			_hashCode += getNumAnotacion().hashCode();

		if(getNumeroCorreccion() != null)
			_hashCode += getNumeroCorreccion().hashCode();

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

		if(getRadicacion() != null)
			_hashCode += getRadicacion().hashCode();

		if(getFechaSalvedad() != null)
			_hashCode += getFechaSalvedad().hashCode();

		if(getDescripcionSalvedad() != null)
			_hashCode += getDescripcionSalvedad().hashCode();

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
