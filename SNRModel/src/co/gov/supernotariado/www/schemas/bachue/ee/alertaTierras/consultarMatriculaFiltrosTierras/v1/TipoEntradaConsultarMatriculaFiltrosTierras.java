/**
 * TipoEntradaConsultarMatriculaFiltrosTierras.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarMatriculaFiltrosTierras.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarMatriculaFiltrosTierras.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaConsultarMatriculaFiltrosTierras implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 962139229720549421L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarMatriculaFiltrosTierras.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1",
		        "tipoEntradaConsultarMatriculaFiltrosTierras"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idDepartamento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1",
		        "idDepartamento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1",
		        "idMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idVereda");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1",
		        "idVereda"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombrePredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1",
		        "nombrePredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("areaPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarMatriculaFiltrosTierras/v1",
		        "areaPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad id departamento. */
	private java.lang.String idDepartamento;

	/** Propiedad id municipio. */
	private java.lang.String idMunicipio;

	/** Propiedad id vereda. */
	private java.lang.String idVereda;

	/** Propiedad nombre predio. */
	private java.lang.String nombrePredio;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad area predio. */
	private int areaPredio;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar matricula filtros tierras.
	 */
	public TipoEntradaConsultarMatriculaFiltrosTierras()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar matricula filtros tierras.
	 *
	 * @param idDepartamento de id departamento
	 * @param idMunicipio de id municipio
	 * @param idVereda de id vereda
	 * @param nombrePredio de nombre predio
	 * @param areaPredio de area predio
	 */
	public TipoEntradaConsultarMatriculaFiltrosTierras(
	    java.lang.String idDepartamento, java.lang.String idMunicipio, java.lang.String idVereda,
	    java.lang.String nombrePredio, int areaPredio
	)
	{
		this.idDepartamento     = idDepartamento;
		this.idMunicipio        = idMunicipio;
		this.idVereda           = idVereda;
		this.nombrePredio       = nombrePredio;
		this.areaPredio         = areaPredio;
	}

	/**
	 * Gets the idDepartamento value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @return idDepartamento
	 */
	public java.lang.String getIdDepartamento()
	{
		return idDepartamento;
	}

	/**
	 * Sets the idDepartamento value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @param idDepartamento de id departamento
	 */
	public void setIdDepartamento(java.lang.String idDepartamento)
	{
		this.idDepartamento = idDepartamento;
	}

	/**
	 * Gets the idMunicipio value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @return idMunicipio
	 */
	public java.lang.String getIdMunicipio()
	{
		return idMunicipio;
	}

	/**
	 * Sets the idMunicipio value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @param idMunicipio de id municipio
	 */
	public void setIdMunicipio(java.lang.String idMunicipio)
	{
		this.idMunicipio = idMunicipio;
	}

	/**
	 * Gets the idVereda value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @return idVereda
	 */
	public java.lang.String getIdVereda()
	{
		return idVereda;
	}

	/**
	 * Sets the idVereda value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @param idVereda de id vereda
	 */
	public void setIdVereda(java.lang.String idVereda)
	{
		this.idVereda = idVereda;
	}

	/**
	 * Gets the nombrePredio value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @return nombrePredio
	 */
	public java.lang.String getNombrePredio()
	{
		return nombrePredio;
	}

	/**
	 * Sets the nombrePredio value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @param nombrePredio de nombre predio
	 */
	public void setNombrePredio(java.lang.String nombrePredio)
	{
		this.nombrePredio = nombrePredio;
	}

	/**
	 * Gets the areaPredio value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @return areaPredio
	 */
	public int getAreaPredio()
	{
		return areaPredio;
	}

	/**
	 * Sets the areaPredio value for this TipoEntradaConsultarMatriculaFiltrosTierras.
	 *
	 * @param areaPredio de area predio
	 */
	public void setAreaPredio(int areaPredio)
	{
		this.areaPredio = areaPredio;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarMatriculaFiltrosTierras))
			return false;

		TipoEntradaConsultarMatriculaFiltrosTierras other = (TipoEntradaConsultarMatriculaFiltrosTierras)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idDepartamento == null) && (other.getIdDepartamento() == null))
				|| ((this.idDepartamento != null) && this.idDepartamento.equals(other.getIdDepartamento())))
				&& (((this.idMunicipio == null) && (other.getIdMunicipio() == null))
				|| ((this.idMunicipio != null) && this.idMunicipio.equals(other.getIdMunicipio())))
				&& (((this.idVereda == null) && (other.getIdVereda() == null))
				|| ((this.idVereda != null) && this.idVereda.equals(other.getIdVereda())))
				&& (((this.nombrePredio == null) && (other.getNombrePredio() == null))
				|| ((this.nombrePredio != null) && this.nombrePredio.equals(other.getNombrePredio())))
				&& (this.areaPredio == other.getAreaPredio());
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

		if(getIdDepartamento() != null)
			_hashCode += getIdDepartamento().hashCode();

		if(getIdMunicipio() != null)
			_hashCode += getIdMunicipio().hashCode();

		if(getIdVereda() != null)
			_hashCode += getIdVereda().hashCode();

		if(getNombrePredio() != null)
			_hashCode += getNombrePredio().hashCode();

		_hashCode += getAreaPredio();
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
