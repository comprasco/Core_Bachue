/**
 * TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaregistrarCambioSalvedadesListaPrediosPredio implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3381483987392258466L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        ">>tipoEntradaregistrarCambioSalvedades>listaPredios>predio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "tipoIdentificacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        ">>>tipoEntradaregistrarCambioSalvedades>listaPredios>predio>tipoIdentificacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "numIdentificacionPredio"
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "direccionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("comentarioSalvedad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "comentarioSalvedad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionSalvedad");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "descripcionSalvedad"
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "fechaSalvedad"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                                   __equalsCalc             =
		null;
	
	/** Propiedad cod circulo registral. */
	private java.lang.String                                                                                                                                                   codCirculoRegistral;
	
	/** Propiedad comentario salvedad. */
	private java.lang.String                                                                                                                                                   comentarioSalvedad;
	
	/** Propiedad descripcion salvedad. */
	private java.lang.String                                                                                                                                                   descripcionSalvedad;
	
	/** Propiedad direccion predio. */
	private java.lang.String                                                                                                                                                   direccionPredio;
	
	/** Propiedad fecha salvedad. */
	private java.util.Calendar                                                                                                                                                 fechaSalvedad;
	
	/** Propiedad num identificacion predio. */
	private java.lang.String                                                                                                                                                   numIdentificacionPredio;
	
	/** Propiedad num matricula inmobiliaria. */
	private java.lang.String                                                                                                                                                   numMatriculaInmobiliaria;
	
	/** Propiedad tipo identificacion predio. */
	private co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredioTipoIdentificacionPredio tipoIdentificacionPredio;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                            __hashCodeCalc           =
		false;

	/**
	 * Instancia un nuevo objeto tipo entradaregistrar cambio salvedades lista predios predio.
	 */
	public TipoEntradaregistrarCambioSalvedadesListaPrediosPredio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entradaregistrar cambio salvedades lista predios predio.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 * @param numIdentificacionPredio de num identificacion predio
	 * @param codCirculoRegistral de cod circulo registral
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param direccionPredio de direccion predio
	 * @param comentarioSalvedad de comentario salvedad
	 * @param descripcionSalvedad de descripcion salvedad
	 * @param fechaSalvedad de fecha salvedad
	 */
	public TipoEntradaregistrarCambioSalvedadesListaPrediosPredio(
	    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredioTipoIdentificacionPredio tipoIdentificacionPredio,
	    java.lang.String                                                                                                                                                   numIdentificacionPredio,
	    java.lang.String                                                                                                                                                   codCirculoRegistral,
	    java.lang.String                                                                                                                                                   numMatriculaInmobiliaria,
	    java.lang.String                                                                                                                                                   direccionPredio,
	    java.lang.String                                                                                                                                                   comentarioSalvedad,
	    java.lang.String                                                                                                                                                   descripcionSalvedad,
	    java.util.Calendar                                                                                                                                                 fechaSalvedad
	)
	{
		this.tipoIdentificacionPredio     = tipoIdentificacionPredio;
		this.numIdentificacionPredio      = numIdentificacionPredio;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.direccionPredio              = direccionPredio;
		this.comentarioSalvedad           = comentarioSalvedad;
		this.descripcionSalvedad          = descripcionSalvedad;
		this.fechaSalvedad                = fechaSalvedad;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the comentarioSalvedad value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param comentarioSalvedad de comentario salvedad
	 */
	public void setComentarioSalvedad(java.lang.String comentarioSalvedad)
	{
		this.comentarioSalvedad = comentarioSalvedad;
	}

	/**
	 * Gets the comentarioSalvedad value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return comentarioSalvedad
	 */
	public java.lang.String getComentarioSalvedad()
	{
		return comentarioSalvedad;
	}

	/**
	 * Sets the descripcionSalvedad value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param descripcionSalvedad de descripcion salvedad
	 */
	public void setDescripcionSalvedad(java.lang.String descripcionSalvedad)
	{
		this.descripcionSalvedad = descripcionSalvedad;
	}

	/**
	 * Gets the descripcionSalvedad value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return descripcionSalvedad
	 */
	public java.lang.String getDescripcionSalvedad()
	{
		return descripcionSalvedad;
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
	 * Sets the direccionPredio value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param direccionPredio de direccion predio
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the direccionPredio value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return direccionPredio
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
	}

	/**
	 * Sets the fechaSalvedad value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param fechaSalvedad de fecha salvedad
	 */
	public void setFechaSalvedad(java.util.Calendar fechaSalvedad)
	{
		this.fechaSalvedad = fechaSalvedad;
	}

	/**
	 * Gets the fechaSalvedad value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return fechaSalvedad
	 */
	public java.util.Calendar getFechaSalvedad()
	{
		return fechaSalvedad;
	}

	/**
	 * Sets the numIdentificacionPredio value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return numIdentificacionPredio
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
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
	 * Sets the tipoIdentificacionPredio value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 */
	public void setTipoIdentificacionPredio(
	    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredioTipoIdentificacionPredio tipoIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio = tipoIdentificacionPredio;
	}

	/**
	 * Gets the tipoIdentificacionPredio value for this TipoEntradaregistrarCambioSalvedadesListaPrediosPredio.
	 *
	 * @return tipoIdentificacionPredio
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredioTipoIdentificacionPredio getTipoIdentificacionPredio()
	{
		return tipoIdentificacionPredio;
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
		if(!(obj instanceof TipoEntradaregistrarCambioSalvedadesListaPrediosPredio))
			return false;

		TipoEntradaregistrarCambioSalvedadesListaPrediosPredio other = (TipoEntradaregistrarCambioSalvedadesListaPrediosPredio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoIdentificacionPredio == null) && (other.getTipoIdentificacionPredio() == null))
				|| ((this.tipoIdentificacionPredio != null)
				&& this.tipoIdentificacionPredio.equals(other.getTipoIdentificacionPredio())))
				&& (((this.numIdentificacionPredio == null) && (other.getNumIdentificacionPredio() == null))
				|| ((this.numIdentificacionPredio != null)
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())))
				&& (((this.comentarioSalvedad == null) && (other.getComentarioSalvedad() == null))
				|| ((this.comentarioSalvedad != null) && this.comentarioSalvedad.equals(other.getComentarioSalvedad())))
				&& (((this.descripcionSalvedad == null) && (other.getDescripcionSalvedad() == null))
				|| ((this.descripcionSalvedad != null)
				&& this.descripcionSalvedad.equals(other.getDescripcionSalvedad())))
				&& (((this.fechaSalvedad == null) && (other.getFechaSalvedad() == null))
				|| ((this.fechaSalvedad != null) && this.fechaSalvedad.equals(other.getFechaSalvedad())));
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

		if(getTipoIdentificacionPredio() != null)
			_hashCode += getTipoIdentificacionPredio().hashCode();

		if(getNumIdentificacionPredio() != null)
			_hashCode += getNumIdentificacionPredio().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		if(getComentarioSalvedad() != null)
			_hashCode += getComentarioSalvedad().hashCode();

		if(getDescripcionSalvedad() != null)
			_hashCode += getDescripcionSalvedad().hashCode();

		if(getFechaSalvedad() != null)
			_hashCode += getFechaSalvedad().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
