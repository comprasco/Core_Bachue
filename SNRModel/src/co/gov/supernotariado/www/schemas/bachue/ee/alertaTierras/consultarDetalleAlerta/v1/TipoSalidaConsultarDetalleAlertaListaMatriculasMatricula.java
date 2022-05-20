/**
 * TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarDetalleAlerta.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2488055745827129143L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        ">>tipoSalidaConsultarDetalleAlerta>listaMatriculas>matricula"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "nomCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "numMatriculaInmobiliaria"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numPredial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarDetalleAlerta/v1",
		        "numPredial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad nom circulo registral. */
	private java.lang.String nomCirculoRegistral;

	/** Propiedad num matricula inmobiliaria. */
	private java.lang.String numMatriculaInmobiliaria;

	/** Propiedad num predial. */
	private java.lang.String numPredial;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar detalle alerta lista matriculas matricula.
	 */
	public TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar detalle alerta lista matriculas matricula.
	 *
	 * @param nomCirculoRegistral de nom circulo registral
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param numPredial de num predial
	 */
	public TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula(
	    java.lang.String nomCirculoRegistral, java.lang.String numMatriculaInmobiliaria, java.lang.String numPredial
	)
	{
		this.nomCirculoRegistral          = nomCirculoRegistral;
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.numPredial                   = numPredial;
	}

	/**
	 * Gets the nomCirculoRegistral value for this TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.
	 *
	 * @return nomCirculoRegistral
	 */
	public java.lang.String getNomCirculoRegistral()
	{
		return nomCirculoRegistral;
	}

	/**
	 * Sets the nomCirculoRegistral value for this TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.
	 *
	 * @param nomCirculoRegistral de nom circulo registral
	 */
	public void setNomCirculoRegistral(java.lang.String nomCirculoRegistral)
	{
		this.nomCirculoRegistral = nomCirculoRegistral;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.
	 *
	 * @return numMatriculaInmobiliaria
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numPredial value for this TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.
	 *
	 * @return numPredial
	 */
	public java.lang.String getNumPredial()
	{
		return numPredial;
	}

	/**
	 * Sets the numPredial value for this TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula.
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
		if(!(obj instanceof TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula))
			return false;

		TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula other = (TipoSalidaConsultarDetalleAlertaListaMatriculasMatricula)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.nomCirculoRegistral == null) && (other.getNomCirculoRegistral() == null))
				|| ((this.nomCirculoRegistral != null)
				&& this.nomCirculoRegistral.equals(other.getNomCirculoRegistral())))
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
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

		if(getNomCirculoRegistral() != null)
			_hashCode += getNomCirculoRegistral().hashCode();

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

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
