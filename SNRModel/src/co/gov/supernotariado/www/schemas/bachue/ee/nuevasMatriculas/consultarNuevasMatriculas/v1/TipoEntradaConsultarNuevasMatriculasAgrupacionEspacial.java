/**
 * TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.nuevasMatriculas.consultarNuevasMatriculas.v1;

public class TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial implements java.io.Serializable
{
	private static final long serialVersionUID = 6570068726551639616L;

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        ">tipoEntradaConsultarNuevasMatriculas>agrupacionEspacial"
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
		elemField.setFieldName("fechaInicial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "fechaInicial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaFinal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/nuevasMatriculas/consultarNuevasMatriculas/v1",
		        "fechaFinal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object   __equalsCalc        = null;
	private java.lang.String   codCirculoRegistral;
	private java.lang.String   codDepartamento;
	private java.lang.String   codMunicipio;
	private java.util.Calendar fechaFinal;
	private java.util.Calendar fechaInicial;
	private boolean            __hashCodeCalc      = false;

	public TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial()
	{
	}

	public TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial(
	    java.lang.String codDepartamento, java.lang.String codMunicipio, java.lang.String codCirculoRegistral,
	    java.util.Calendar fechaInicial, java.util.Calendar fechaFinal
	)
	{
		this.codDepartamento         = codDepartamento;
		this.codMunicipio            = codMunicipio;
		this.codCirculoRegistral     = codCirculoRegistral;
		this.fechaInicial            = fechaInicial;
		this.fechaFinal              = fechaFinal;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @param codCirculoRegistral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codDepartamento value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @param codDepartamento
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @return codDepartamento
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMunicipio value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @param codMunicipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @return codMunicipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
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
	 * Sets the fechaFinal value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @param fechaFinal
	 */
	public void setFechaFinal(java.util.Calendar fechaFinal)
	{
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Gets the fechaFinal value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @return fechaFinal
	 */
	public java.util.Calendar getFechaFinal()
	{
		return fechaFinal;
	}

	/**
	 * Sets the fechaInicial value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @param fechaInicial
	 */
	public void setFechaInicial(java.util.Calendar fechaInicial)
	{
		this.fechaInicial = fechaInicial;
	}

	/**
	 * Gets the fechaInicial value for this TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial.
	 *
	 * @return fechaInicial
	 */
	public java.util.Calendar getFechaInicial()
	{
		return fechaInicial;
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
		if(!(obj instanceof TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial))
			return false;

		TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial other = (TipoEntradaConsultarNuevasMatriculasAgrupacionEspacial)obj;

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
				&& (((this.fechaInicial == null) && (other.getFechaInicial() == null))
				|| ((this.fechaInicial != null) && this.fechaInicial.equals(other.getFechaInicial())))
				&& (((this.fechaFinal == null) && (other.getFechaFinal() == null))
				|| ((this.fechaFinal != null) && this.fechaFinal.equals(other.getFechaFinal())));
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

		if(getFechaInicial() != null)
			_hashCode += getFechaInicial().hashCode();

		if(getFechaFinal() != null)
			_hashCode += getFechaFinal().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
