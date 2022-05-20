/**
 * TipoEntradaRegistrarCambioCuartoOrden.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarCambioCuartoOrden.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 7/05/2020
 */
public class TipoEntradaRegistrarCambioCuartoOrden implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2680669541804383059L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarCambioCuartoOrden.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "tipoEntradaRegistrarCambioCuartoOrden"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "codMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "tipoIdentificacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        ">tipoEntradaRegistrarCambioCuartoOrden>tipoIdentificacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("avaluoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "avaluoPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaAvaluo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/mutacionesCuartoOrden/registrarCambioCuartoOrden/v1",
		        "fechaAvaluo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad avaluo predio. */
	private java.lang.String avaluoPredio;

	/** Propiedad cod departamento. */
	private java.lang.String codDepartamento;

	/** Propiedad cod municipio. */
	private java.lang.String codMunicipio;

	/** Propiedad fecha avaluo. */
	private java.util.Calendar fechaAvaluo;

	/** Propiedad num identificacion predio. */
	private java.lang.String numIdentificacionPredio;

	/** Propiedad tipo identificacion predio. */
	private co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrdenTipoIdentificacionPredio tipoIdentificacionPredio;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar cambio cuarto orden.
	 */
	public TipoEntradaRegistrarCambioCuartoOrden()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar cambio cuarto orden.
	 *
	 * @param codDepartamento de cod departamento
	 * @param codMunicipio de cod municipio
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 * @param numIdentificacionPredio de num identificacion predio
	 * @param avaluoPredio de avaluo predio
	 * @param fechaAvaluo de fecha avaluo
	 */
	public TipoEntradaRegistrarCambioCuartoOrden(
	    java.lang.String codDepartamento, java.lang.String codMunicipio,
	    co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrdenTipoIdentificacionPredio tipoIdentificacionPredio,
	    java.lang.String numIdentificacionPredio, java.lang.String avaluoPredio, java.util.Calendar fechaAvaluo
	)
	{
		this.codDepartamento              = codDepartamento;
		this.codMunicipio                 = codMunicipio;
		this.tipoIdentificacionPredio     = tipoIdentificacionPredio;
		this.numIdentificacionPredio      = numIdentificacionPredio;
		this.avaluoPredio                 = avaluoPredio;
		this.fechaAvaluo                  = fechaAvaluo;
	}

	/**
	 * Gets the codDepartamento value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @return codDepartamento
	 */
	public java.lang.String getCodDepartamento()
	{
		return codDepartamento;
	}

	/**
	 * Sets the codDepartamento value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @param codDepartamento de cod departamento
	 */
	public void setCodDepartamento(java.lang.String codDepartamento)
	{
		this.codDepartamento = codDepartamento;
	}

	/**
	 * Gets the codMunicipio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @return codMunicipio
	 */
	public java.lang.String getCodMunicipio()
	{
		return codMunicipio;
	}

	/**
	 * Sets the codMunicipio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @param codMunicipio de cod municipio
	 */
	public void setCodMunicipio(java.lang.String codMunicipio)
	{
		this.codMunicipio = codMunicipio;
	}

	/**
	 * Gets the tipoIdentificacionPredio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @return tipoIdentificacionPredio
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrdenTipoIdentificacionPredio getTipoIdentificacionPredio()
	{
		return tipoIdentificacionPredio;
	}

	/**
	 * Sets the tipoIdentificacionPredio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 */
	public void setTipoIdentificacionPredio(
	    co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrdenTipoIdentificacionPredio tipoIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio = tipoIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @return numIdentificacionPredio
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
	}

	/**
	 * Sets the numIdentificacionPredio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Gets the avaluoPredio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @return avaluoPredio
	 */
	public java.lang.String getAvaluoPredio()
	{
		return avaluoPredio;
	}

	/**
	 * Sets the avaluoPredio value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @param avaluoPredio de avaluo predio
	 */
	public void setAvaluoPredio(java.lang.String avaluoPredio)
	{
		this.avaluoPredio = avaluoPredio;
	}

	/**
	 * Gets the fechaAvaluo value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @return fechaAvaluo
	 */
	public java.util.Calendar getFechaAvaluo()
	{
		return fechaAvaluo;
	}

	/**
	 * Sets the fechaAvaluo value for this TipoEntradaRegistrarCambioCuartoOrden.
	 *
	 * @param fechaAvaluo de fecha avaluo
	 */
	public void setFechaAvaluo(java.util.Calendar fechaAvaluo)
	{
		this.fechaAvaluo = fechaAvaluo;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaRegistrarCambioCuartoOrden))
			return false;

		TipoEntradaRegistrarCambioCuartoOrden other = (TipoEntradaRegistrarCambioCuartoOrden)obj;

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
				&& (((this.tipoIdentificacionPredio == null) && (other.getTipoIdentificacionPredio() == null))
				|| ((this.tipoIdentificacionPredio != null)
				&& this.tipoIdentificacionPredio.equals(other.getTipoIdentificacionPredio())))
				&& (((this.numIdentificacionPredio == null) && (other.getNumIdentificacionPredio() == null))
				|| ((this.numIdentificacionPredio != null)
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())))
				&& (((this.avaluoPredio == null) && (other.getAvaluoPredio() == null))
				|| ((this.avaluoPredio != null) && this.avaluoPredio.equals(other.getAvaluoPredio())))
				&& (((this.fechaAvaluo == null) && (other.getFechaAvaluo() == null))
				|| ((this.fechaAvaluo != null) && this.fechaAvaluo.equals(other.getFechaAvaluo())));
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

		if(getTipoIdentificacionPredio() != null)
			_hashCode += getTipoIdentificacionPredio().hashCode();

		if(getNumIdentificacionPredio() != null)
			_hashCode += getNumIdentificacionPredio().hashCode();

		if(getAvaluoPredio() != null)
			_hashCode += getAvaluoPredio().hashCode();

		if(getFechaAvaluo() != null)
			_hashCode += getFechaAvaluo().hashCode();

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
