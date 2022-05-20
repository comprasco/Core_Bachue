/**
 * TipoSalidaConsultarNuevasMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1;

public class TipoSalidaConsultarNuevasMatriculas implements java.io.Serializable
{
	private static final long serialVersionUID = 5170130354405398045L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarNuevasMatriculas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "tipoSalidaConsultarNuevasMatriculas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nuevasMatriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "nuevasMatriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        ">>tipoSalidaConsultarNuevasMatriculas>nuevasMatriculas>nuevaMatricula"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "nuevaMatricula"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                                                                                                                                              __equalsCalc        =
		null;
	private java.lang.String                                                                                                                                              codCirculoRegistral;
	private java.lang.String                                                                                                                                              codDepartamento;
	private java.math.BigInteger                                                                                                                                          codMensaje;
	private java.lang.String                                                                                                                                              codMunicipio;
	private java.lang.String                                                                                                                                              descripcionMensaje;
	private co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula[] nuevasMatriculas;
	private boolean                                                                                                                                                       __hashCodeCalc      =
		false;

	public TipoSalidaConsultarNuevasMatriculas()
	{
	}

	public TipoSalidaConsultarNuevasMatriculas(
	    java.lang.String codDepartamento, java.lang.String codMunicipio, java.lang.String codCirculoRegistral,
	    co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula[] nuevasMatriculas,
	    java.math.BigInteger codMensaje, java.lang.String descripcionMensaje
	)
	{
		this.codDepartamento         = codDepartamento;
		this.codMunicipio            = codMunicipio;
		this.codCirculoRegistral     = codCirculoRegistral;
		this.nuevasMatriculas        = nuevasMatriculas;
		this.codMensaje              = codMensaje;
		this.descripcionMensaje      = descripcionMensaje;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @param codCirculoRegistral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codDepartamento value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @param codDepartamento
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @return codDepartamento
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @param codMensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the codMunicipio value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @param codMunicipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @return codMunicipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @param descripcionMensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the nuevasMatriculas value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @param nuevasMatriculas
	 */
	public void setNuevasMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula[] nuevasMatriculas
	)
	{
		this.nuevasMatriculas = nuevasMatriculas;
	}

	/**
	 * Gets the nuevasMatriculas value for this TipoSalidaConsultarNuevasMatriculas.
	 *
	 * @return nuevasMatriculas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1.TipoSalidaConsultarNuevasMatriculasNuevasMatriculasNuevaMatricula[] getNuevasMatriculas()
	{
		return nuevasMatriculas;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarNuevasMatriculas))
			return false;

		TipoSalidaConsultarNuevasMatriculas other = (TipoSalidaConsultarNuevasMatriculas)obj;

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
				&& (((this.nuevasMatriculas == null) && (other.getNuevasMatriculas() == null))
				|| ((this.nuevasMatriculas != null)
				&& java.util.Arrays.equals(this.nuevasMatriculas, other.getNuevasMatriculas())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
		__equalsCalc     = null;

		return _equals;
	}

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

		if(getNuevasMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getNuevasMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getNuevasMatriculas(), i);

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
