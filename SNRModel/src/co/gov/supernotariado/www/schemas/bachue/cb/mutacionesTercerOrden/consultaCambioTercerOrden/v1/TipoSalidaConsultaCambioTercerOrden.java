/**
 * TipoSalidaConsultaCambioTercerOrden.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultaCambioTercerOrden.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultaCambioTercerOrden implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6444673116346152253L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultaCambioTercerOrden.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "tipoSalidaConsultaCambioTercerOrden"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "codDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "codMunicipio"
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "tipoPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anotacionesPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "anotacionesPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        ">>tipoSalidaConsultaCambioTercerOrden>anotacionesPredio>anotacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "anotacionPredio"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesTercerOrden/consultaCambioTercerOrden/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                                     __equalsCalc            =
		null;
	
	/** Propiedad cod circulo registral. */
	private java.lang.String                                                                                                                                                     codCirculoRegistral;
	
	/** Propiedad cod departamento. */
	private java.lang.String                                                                                                                                                     codDepartamento;
	
	/** Propiedad cod mensaje. */
	private java.math.BigInteger                                                                                                                                                 codMensaje;
	
	/** Propiedad cod municipio. */
	private java.lang.String                                                                                                                                                     codMunicipio;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String                                                                                                                                                     descripcionMensaje;
	
	/** Propiedad num identificacion predio. */
	private java.lang.String                                                                                                                                                     numIdentificacionPredio;
	
	/** Propiedad tipo predio. */
	private java.lang.String                                                                                                                                                     tipoPredio;
	
	/** Propiedad anotaciones predio. */
	private co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[] anotacionesPredio;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                              __hashCodeCalc          =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida consulta cambio tercer orden.
	 */
	public TipoSalidaConsultaCambioTercerOrden()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consulta cambio tercer orden.
	 *
	 * @param codDepartamento de cod departamento
	 * @param codMunicipio de cod municipio
	 * @param codCirculoRegistral de cod circulo registral
	 * @param numIdentificacionPredio de num identificacion predio
	 * @param tipoPredio de tipo predio
	 * @param anotacionesPredio de anotaciones predio
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaConsultaCambioTercerOrden(
	    java.lang.String codDepartamento, java.lang.String codMunicipio, java.lang.String codCirculoRegistral,
	    java.lang.String numIdentificacionPredio, java.lang.String tipoPredio,
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[] anotacionesPredio,
	    java.math.BigInteger codMensaje, java.lang.String descripcionMensaje
	)
	{
		this.codDepartamento             = codDepartamento;
		this.codMunicipio                = codMunicipio;
		this.codCirculoRegistral         = codCirculoRegistral;
		this.numIdentificacionPredio     = numIdentificacionPredio;
		this.tipoPredio                  = tipoPredio;
		this.anotacionesPredio           = anotacionesPredio;
		this.codMensaje                  = codMensaje;
		this.descripcionMensaje          = descripcionMensaje;
	}

	/**
	 * Sets the anotacionesPredio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param anotacionesPredio de anotaciones predio
	 */
	public void setAnotacionesPredio(
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[] anotacionesPredio
	)
	{
		this.anotacionesPredio = anotacionesPredio;
	}

	/**
	 * Gets the anotacionesPredio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return anotacionesPredio
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrdenAnotacionesPredioAnotacionPredio[] getAnotacionesPredio()
	{
		return anotacionesPredio;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codDepartamento value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param codDepartamento de cod departamento
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return codDepartamento
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param codMensaje de cod mensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the codMunicipio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param codMunicipio de cod municipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return codMunicipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
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
	 * Sets the numIdentificacionPredio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return numIdentificacionPredio
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
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
	 * Sets the tipoPredio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @param tipoPredio de tipo predio
	 */
	public void setTipoPredio(java.lang.String tipoPredio)
	{
		this.tipoPredio = tipoPredio;
	}

	/**
	 * Gets the tipoPredio value for this TipoSalidaConsultaCambioTercerOrden.
	 *
	 * @return tipoPredio
	 */
	public java.lang.String getTipoPredio()
	{
		return tipoPredio;
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
		if(!(obj instanceof TipoSalidaConsultaCambioTercerOrden))
			return false;

		TipoSalidaConsultaCambioTercerOrden other = (TipoSalidaConsultaCambioTercerOrden)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.numIdentificacionPredio == null) && (other.getNumIdentificacionPredio() == null))
				|| ((this.numIdentificacionPredio != null)
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())))
				&& (((this.tipoPredio == null) && (other.getTipoPredio() == null))
				|| ((this.tipoPredio != null) && this.tipoPredio.equals(other.getTipoPredio())))
				&& (((this.anotacionesPredio == null) && (other.getAnotacionesPredio() == null))
				|| ((this.anotacionesPredio != null)
				&& java.util.Arrays.equals(this.anotacionesPredio, other.getAnotacionesPredio())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getNumIdentificacionPredio() != null)
			_hashCode += getNumIdentificacionPredio().hashCode();

		if(getTipoPredio() != null)
			_hashCode += getTipoPredio().hashCode();

		if(getAnotacionesPredio() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getAnotacionesPredio()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getAnotacionesPredio(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
