/**
 * TipoSalidaConsultarPartesInteresadas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarPartesInteresadas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarPartesInteresadas implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4789741520558844081L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarPartesInteresadas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "tipoSalidaConsultarPartesInteresadas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("interesados");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "interesados"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        ">>tipoSalidaConsultarPartesInteresadas>interesados>interesado"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "interesado"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/partesinteresadas/consultarpartesinteresadas/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                        __equalsCalc             =
		null;
	
	/** Propiedad cod circulo registral. */
	private java.lang.String                                                                                                                                        codCirculoRegistral;
	
	/** Propiedad cod departamento. */
	private java.lang.String                                                                                                                                        codDepartamento;
	
	/** Propiedad cod municipio. */
	private java.lang.String                                                                                                                                        codMunicipio;
	
	/** Propiedad codigo mensaje. */
	private java.math.BigInteger                                                                                                                                    codigoMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String                                                                                                                                        descripcionMensaje;
	
	/** Propiedad num matricula inmobiliaria. */
	private java.lang.String                                                                                                                                        numMatriculaInmobiliaria;
	
	/** Propiedad interesados. */
	private co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadasInteresadosInteresado[] interesados;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                 __hashCodeCalc           =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar partes interesadas.
	 */
	public TipoSalidaConsultarPartesInteresadas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar partes interesadas.
	 *
	 * @param codDepartamento de cod departamento
	 * @param codMunicipio de cod municipio
	 * @param codCirculoRegistral de cod circulo registral
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param interesados de interesados
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaConsultarPartesInteresadas(
	    java.lang.String codDepartamento, java.lang.String codMunicipio, java.lang.String codCirculoRegistral,
	    java.lang.String numMatriculaInmobiliaria,
	    co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadasInteresadosInteresado[] interesados,
	    java.math.BigInteger codigoMensaje, java.lang.String descripcionMensaje
	)
	{
		this.codDepartamento              = codDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.interesados                  = interesados;
		this.codigoMensaje                = codigoMensaje;
		this.descripcionMensaje           = descripcionMensaje;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codDepartamento value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @param codDepartamento de cod departamento
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @return codDepartamento
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMunicipio value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @param codMunicipio de cod municipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @return codMunicipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarPartesInteresadas.
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
	 * Sets the interesados value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @param interesados de interesados
	 */
	public void setInteresados(
	    co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadasInteresadosInteresado[] interesados
	)
	{
		this.interesados = interesados;
	}

	/**
	 * Gets the interesados value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @return interesados
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadasInteresadosInteresado[] getInteresados()
	{
		return interesados;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoSalidaConsultarPartesInteresadas.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoSalidaConsultarPartesInteresadas.
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
		if(!(obj instanceof TipoSalidaConsultarPartesInteresadas))
			return false;

		TipoSalidaConsultarPartesInteresadas other = (TipoSalidaConsultarPartesInteresadas)obj;

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
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.interesados == null) && (other.getInteresados() == null))
				|| ((this.interesados != null) && java.util.Arrays.equals(this.interesados, other.getInteresados())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
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

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getInteresados() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getInteresados()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getInteresados(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
