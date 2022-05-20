/**
 * TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaConsultarListaMatriculasListaMatriculasMatricula implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2726380156688577300L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        ">>tipoSalidaConsultarListaMatriculas>listaMatriculas>matricula"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("esDerivada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        "esDerivada"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        "numPredial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	private java.lang.String codCirculoRegistral;

	/** Propiedad es derivada. */
	private java.lang.String esDerivada;

	/** Propiedad num predial. */
	private java.lang.String numPredial;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad num matricula inmobiliaria. */
	private int numMatriculaInmobiliaria;

	/**
	 * Instancia un nuevo objeto tipo salida consultar lista matriculas lista matriculas matricula.
	 */
	public TipoSalidaConsultarListaMatriculasListaMatriculasMatricula()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar lista matriculas lista matriculas matricula.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param esDerivada de es derivada
	 * @param numPredial de num predial
	 */
	public TipoSalidaConsultarListaMatriculasListaMatriculasMatricula(
	    java.lang.String codCirculoRegistral, int numMatriculaInmobiliaria, java.lang.String esDerivada,
	    java.lang.String numPredial
	)
	{
		this.codCirculoRegistral          = codCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.esDerivada                   = esDerivada;
		this.numPredial                   = numPredial;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @return codCirculoRegistral
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public int getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(int numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the esDerivada value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @return esDerivada
	 */
	public java.lang.String getEsDerivada()
	{
		return esDerivada;
	}

	/**
	 * Sets the esDerivada value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @param esDerivada de es derivada
	 */
	public void setEsDerivada(java.lang.String esDerivada)
	{
		this.esDerivada = esDerivada;
	}

	/**
	 * Gets the numPredial value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @return numPredial
	 */
	public java.lang.String getNumPredial()
	{
		return numPredial;
	}

	/**
	 * Sets the numPredial value for this TipoSalidaConsultarListaMatriculasListaMatriculasMatricula.
	 *
	 * @param numPredial de num predial
	 */
	public void setNumPredial(java.lang.String numPredial)
	{
		this.numPredial = numPredial;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarListaMatriculasListaMatriculasMatricula))
			return false;

		TipoSalidaConsultarListaMatriculasListaMatriculasMatricula other = (TipoSalidaConsultarListaMatriculasListaMatriculasMatricula)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (this.numMatriculaInmobiliaria == other.getNumMatriculaInmobiliaria())
				&& (((this.esDerivada == null) && (other.getEsDerivada() == null))
				|| ((this.esDerivada != null) && this.esDerivada.equals(other.getEsDerivada())))
				&& (((this.numPredial == null) && (other.getNumPredial() == null))
				|| ((this.numPredial != null) && this.numPredial.equals(other.getNumPredial())));
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

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		_hashCode += getNumMatriculaInmobiliaria();

		if(getEsDerivada() != null)
			_hashCode += getEsDerivada().hashCode();

		if(getNumPredial() != null)
			_hashCode += getNumPredial().hashCode();

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
