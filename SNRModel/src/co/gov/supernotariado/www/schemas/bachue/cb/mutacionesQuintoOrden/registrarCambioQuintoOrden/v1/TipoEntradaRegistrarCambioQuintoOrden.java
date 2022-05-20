/**
 * TipoEntradaRegistrarCambioQuintoOrden.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarCambioQuintoOrden.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRegistrarCambioQuintoOrden implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5065469155662309329L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarCambioQuintoOrden.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "tipoEntradaRegistrarCambioQuintoOrden"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "tipoIdentificacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        ">tipoEntradaRegistrarCambioQuintoOrden>tipoIdentificacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("predio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        "predio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/mutacionesQuintoOrden/registrarCambioQuintoOrden/v1",
		        ">tipoEntradaRegistrarCambioQuintoOrden>predio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                              __equalsCalc             =
		null;
	
	/** Propiedad cod departamento. */
	private java.lang.String                                                                                                                                              codDepartamento;
	
	/** Propiedad cod municipio. */
	private java.lang.String                                                                                                                                              codMunicipio;
	
	/** Propiedad num identificacion predio. */
	private java.lang.String                                                                                                                                              numIdentificacionPredio;
	
	/** Propiedad predio. */
	private co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenPredio                   predio;
	
	/** Propiedad tipo identificacion predio. */
	private co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenTipoIdentificacionPredio tipoIdentificacionPredio;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                       __hashCodeCalc           =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar cambio quinto orden.
	 */
	public TipoEntradaRegistrarCambioQuintoOrden()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar cambio quinto orden.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 * @param numIdentificacionPredio de num identificacion predio
	 * @param codDepartamento de cod departamento
	 * @param codMunicipio de cod municipio
	 * @param predio de predio
	 */
	public TipoEntradaRegistrarCambioQuintoOrden(
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenTipoIdentificacionPredio tipoIdentificacionPredio,
	    java.lang.String                                                                                                                                              numIdentificacionPredio,
	    java.lang.String                                                                                                                                              codDepartamento,
	    java.lang.String                                                                                                                                              codMunicipio,
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenPredio                   predio
	)
	{
		this.tipoIdentificacionPredio     = tipoIdentificacionPredio;
		this.numIdentificacionPredio      = numIdentificacionPredio;
		this.codDepartamento              = codDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.predio                       = predio;
	}

	/**
	 * Sets the codDepartamento value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @param codDepartamento de cod departamento
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codDepartamento value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @return codDepartamento
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codMunicipio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @param codMunicipio de cod municipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the codMunicipio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @return codMunicipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
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
	 * Sets the numIdentificacionPredio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @return numIdentificacionPredio
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
	}

	/**
	 * Sets the predio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @param predio de predio
	 */
	public void setPredio(
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenPredio predio
	)
	{
		this.predio = predio;
	}

	/**
	 * Gets the predio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @return predio
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenPredio getPredio()
	{
		return predio;
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
	 * Sets the tipoIdentificacionPredio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 */
	public void setTipoIdentificacionPredio(
	    co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenTipoIdentificacionPredio tipoIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio = tipoIdentificacionPredio;
	}

	/**
	 * Gets the tipoIdentificacionPredio value for this TipoEntradaRegistrarCambioQuintoOrden.
	 *
	 * @return tipoIdentificacionPredio
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrdenTipoIdentificacionPredio getTipoIdentificacionPredio()
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
		if(!(obj instanceof TipoEntradaRegistrarCambioQuintoOrden))
			return false;

		TipoEntradaRegistrarCambioQuintoOrden other = (TipoEntradaRegistrarCambioQuintoOrden)obj;

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
				&& (((this.codDepartamento == null) && (other.getCodDepartamento() == null))
				|| ((this.codDepartamento != null) && this.codDepartamento.equals(other.getCodDepartamento())))
				&& (((this.codMunicipio == null) && (other.getCodMunicipio() == null))
				|| ((this.codMunicipio != null) && this.codMunicipio.equals(other.getCodMunicipio())))
				&& (((this.predio == null) && (other.getPredio() == null))
				|| ((this.predio != null) && this.predio.equals(other.getPredio())));
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

		if(getCodDepartamento() != null)
			_hashCode += getCodDepartamento().hashCode();

		if(getCodMunicipio() != null)
			_hashCode += getCodMunicipio().hashCode();

		if(getPredio() != null)
			_hashCode += getPredio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
