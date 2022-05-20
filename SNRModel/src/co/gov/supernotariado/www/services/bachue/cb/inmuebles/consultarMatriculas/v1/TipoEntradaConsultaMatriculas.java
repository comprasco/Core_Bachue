/**
 * TipoEntradaConsultaMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1;



/**
 * El esquema define los
 *                         datos de entrada para la operacion consultarMatriculas dado
 * el
 *                         dato
 *                         de una persona.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class TipoEntradaConsultaMatriculas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8102386590018692101L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultaMatriculas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "tipoEntradaConsultaMatriculas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("convenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "convenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("criterioBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "criterioBusqueda"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        ">tipoEntradaConsultaMatriculas>criterioBusqueda"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorCriterioBusqueda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "valorCriterioBusqueda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod departamento. */
	/* Código del departamento según DANE. */
	private java.lang.String codDepartamento;

	/** Propiedad cod municipio. */
	/* Código de municipio según DANE. */
	private java.lang.String codMunicipio;

	/** Propiedad convenio. */
	/* Código del nombre de convenio que tendría la
	 *                                 SNR
	 *                                 con la
	 *                                 Entidad que consume el servicio */
	private java.lang.String convenio;

	/** Propiedad criterio busqueda. */
	/* Corresponde al tipo de búsqueda o firltro que
	 *                                 el
	 *                                 usuario utiliza
	 *                                 para realizar la consulta */
	private co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculasCriterioBusqueda criterioBusqueda;

	/** Propiedad valor criterio busqueda. */
	/* Campo que identifica el valor del criterio de
	 *                                 búsqueda dependiendo del campo criterio de búsqueda */
	private java.lang.String valorCriterioBusqueda;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consulta matriculas.
	 */
	public TipoEntradaConsultaMatriculas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consulta matriculas.
	 *
	 * @param convenio de convenio
	 * @param codDepartamento de cod departamento
	 * @param codMunicipio de cod municipio
	 * @param criterioBusqueda de criterio busqueda
	 * @param valorCriterioBusqueda de valor criterio busqueda
	 */
	public TipoEntradaConsultaMatriculas(
	    java.lang.String convenio, java.lang.String codDepartamento, java.lang.String codMunicipio,
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculasCriterioBusqueda criterioBusqueda,
	    java.lang.String valorCriterioBusqueda
	)
	{
		this.convenio                  = convenio;
		this.codDepartamento           = codDepartamento;
		this.codMunicipio              = codMunicipio;
		this.criterioBusqueda          = criterioBusqueda;
		this.valorCriterioBusqueda     = valorCriterioBusqueda;
	}

	/**
	 * Gets the convenio value for this TipoEntradaConsultaMatriculas.
	 *
	 * @return convenio   * Código del nombre de convenio que tendría la
	     *                                 SNR
	     *                                 con la
	     *                                 Entidad que consume el servicio
	 */
	public java.lang.String getConvenio()
	{
		return convenio;
	}

	/**
	 * Sets the convenio value for this TipoEntradaConsultaMatriculas.
	 *
	 * @param convenio   * Código del nombre de convenio que tendría la
	     *                                 SNR
	     *                                 con la
	     *                                 Entidad que consume el servicio
	 */
	public void setConvenio(java.lang.String convenio)
	{
		this.convenio = convenio;
	}

	/**
	 * Gets the codDepartamento value for this TipoEntradaConsultaMatriculas.
	 *
	 * @return codDepartamento   * Código del departamento según DANE.
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codDepartamento value for this TipoEntradaConsultaMatriculas.
	 *
	 * @param codDepartamento   * Código del departamento según DANE.
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codMunicipio value for this TipoEntradaConsultaMatriculas.
	 *
	 * @return codMunicipio   * Código de municipio según DANE.
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the codMunicipio value for this TipoEntradaConsultaMatriculas.
	 *
	 * @param codMunicipio   * Código de municipio según DANE.
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the criterioBusqueda value for this TipoEntradaConsultaMatriculas.
	 *
	 * @return criterioBusqueda   * Corresponde al tipo de búsqueda o firltro que
	     *                                 el
	     *                                 usuario utiliza
	     *                                 para realizar la consulta
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculasCriterioBusqueda getCriterioBusqueda()
	{
		return criterioBusqueda;
	}

	/**
	 * Sets the criterioBusqueda value for this TipoEntradaConsultaMatriculas.
	 *
	 * @param criterioBusqueda   * Corresponde al tipo de búsqueda o firltro que
	     *                                 el
	     *                                 usuario utiliza
	     *                                 para realizar la consulta
	 */
	public void setCriterioBusqueda(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoEntradaConsultaMatriculasCriterioBusqueda criterioBusqueda
	)
	{
		this.criterioBusqueda = criterioBusqueda;
	}

	/**
	 * Gets the valorCriterioBusqueda value for this TipoEntradaConsultaMatriculas.
	 *
	 * @return valorCriterioBusqueda   * Campo que identifica el valor del criterio de
	     *                                 búsqueda dependiendo del campo criterio de búsqueda
	 */
	public java.lang.String getValorCriterioBusqueda()
	{
		return valorCriterioBusqueda;
	}

	/**
	 * Sets the valorCriterioBusqueda value for this TipoEntradaConsultaMatriculas.
	 *
	 * @param valorCriterioBusqueda   * Campo que identifica el valor del criterio de
	     *                                 búsqueda dependiendo del campo criterio de búsqueda
	 */
	public void setValorCriterioBusqueda(java.lang.String valorCriterioBusqueda)
	{
		this.valorCriterioBusqueda = valorCriterioBusqueda;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultaMatriculas))
			return false;

		TipoEntradaConsultaMatriculas other = (TipoEntradaConsultaMatriculas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.convenio == null) && (other.getConvenio() == null))
				|| ((this.convenio != null) && this.convenio.equals(other.getConvenio())))
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.criterioBusqueda == null) && (other.getCriterioBusqueda() == null))
				|| ((this.criterioBusqueda != null) && this.criterioBusqueda.equals(other.getCriterioBusqueda())))
				&& (((this.valorCriterioBusqueda == null) && (other.getValorCriterioBusqueda() == null))
				|| ((this.valorCriterioBusqueda != null)
				&& this.valorCriterioBusqueda.equals(other.getValorCriterioBusqueda())));
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

		if(getConvenio() != null)
			_hashCode += getConvenio().hashCode();

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getCriterioBusqueda() != null)
			_hashCode += getCriterioBusqueda().hashCode();

		if(getValorCriterioBusqueda() != null)
			_hashCode += getValorCriterioBusqueda().hashCode();

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
