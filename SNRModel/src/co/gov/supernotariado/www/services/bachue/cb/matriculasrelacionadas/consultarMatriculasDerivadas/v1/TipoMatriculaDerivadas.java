/**
 * TipoMatriculaDerivadas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1;


/**
 * Clase que contiene todos las propiedades TipoMatriculaDerivadas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoMatriculaDerivadas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4598787305960469984L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoMatriculaDerivadas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "tipoMatriculaDerivadas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numMatriculaInmobiliaria");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "numMatriculaInmobiliaria"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "direccionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("radicacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "radicacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anotacionRelacionada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "anotacionRelacionada"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nir. */
	private java.lang.String NIR;
	
	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad anotacion relacionada. */
	/* corresponde al número de anotación de la
	 *                                 matricula relacionada */
	private java.lang.String anotacionRelacionada;

	/** Propiedad cod circulo registral. */
	/* Corresponde al círculo donde registró el
	 *                                 predio */
	private java.lang.String codCirculoRegistral;

	/** Propiedad direccion predio. */
	/* corresponde a la descripción de la matricula
	 *                                 relacionada */
	private java.lang.String direccionPredio;

	/** Propiedad num matricula inmobiliaria. */
	/* corresponde a un número de matrícula de las
	 *                                 matrículas derivadas */
	private java.lang.String numMatriculaInmobiliaria;

	/** Propiedad radicacion. */
	/* corresponde a la radicación de la anotación
	 *                                 sobre la matricula relacionada */
	private java.lang.String radicacion;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo matricula derivadas.
	 */
	public TipoMatriculaDerivadas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo matricula derivadas.
	 *
	 * @param numMatriculaInmobiliaria de num matricula inmobiliaria
	 * @param codCirculoRegistral de cod circulo registral
	 * @param direccionPredio de direccion predio
	 * @param NIR de nir
	 * @param radicacion de radicacion
	 * @param anotacionRelacionada de anotacion relacionada
	 */
	public TipoMatriculaDerivadas(
	    java.lang.String numMatriculaInmobiliaria, java.lang.String codCirculoRegistral,
	    java.lang.String direccionPredio, java.lang.String NIR, java.lang.String radicacion,
	    java.lang.String anotacionRelacionada
	)
	{
		this.numMatriculaInmobiliaria     = numMatriculaInmobiliaria;
		this.codCirculoRegistral          = codCirculoRegistral;
		this.direccionPredio              = direccionPredio;
		this.NIR                          = NIR;
		this.radicacion                   = radicacion;
		this.anotacionRelacionada         = anotacionRelacionada;
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

	/**
	 * Sets the anotacionRelacionada value for this TipoMatriculaDerivadas.
	 *
	 * @param anotacionRelacionada   * corresponde al número de anotación de la
	 *                                 matricula relacionada
	 */
	public void setAnotacionRelacionada(java.lang.String anotacionRelacionada)
	{
		this.anotacionRelacionada = anotacionRelacionada;
	}

	/**
	 * Gets the anotacionRelacionada value for this TipoMatriculaDerivadas.
	 *
	 * @return anotacionRelacionada   * corresponde al número de anotación de la
	 *                                 matricula relacionada
	 */
	public java.lang.String getAnotacionRelacionada()
	{
		return anotacionRelacionada;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoMatriculaDerivadas.
	 *
	 * @param codCirculoRegistral   * Corresponde al círculo donde registró el
	 *                                 predio
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoMatriculaDerivadas.
	 *
	 * @return codCirculoRegistral   * Corresponde al círculo donde registró el
	 *                                 predio
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
	}

	/**
	 * Sets the direccionPredio value for this TipoMatriculaDerivadas.
	 *
	 * @param direccionPredio   * corresponde a la descripción de la matricula
	 *                                 relacionada
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the direccionPredio value for this TipoMatriculaDerivadas.
	 *
	 * @return direccionPredio   * corresponde a la descripción de la matricula
	 *                                 relacionada
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
	}

	/**
	 * Sets the NIR value for this TipoMatriculaDerivadas.
	 *
	 * @param NIR de nir
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the NIR value for this TipoMatriculaDerivadas.
	 *
	 * @return NIR
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the numMatriculaInmobiliaria value for this TipoMatriculaDerivadas.
	 *
	 * @param numMatriculaInmobiliaria   * corresponde a un número de matrícula de las
	 *                                 matrículas derivadas
	 */
	public void setNumMatriculaInmobiliaria(java.lang.String numMatriculaInmobiliaria)
	{
		this.numMatriculaInmobiliaria = numMatriculaInmobiliaria;
	}

	/**
	 * Gets the numMatriculaInmobiliaria value for this TipoMatriculaDerivadas.
	 *
	 * @return numMatriculaInmobiliaria   * corresponde a un número de matrícula de las
	 *                                 matrículas derivadas
	 */
	public java.lang.String getNumMatriculaInmobiliaria()
	{
		return numMatriculaInmobiliaria;
	}

	/**
	 * Sets the radicacion value for this TipoMatriculaDerivadas.
	 *
	 * @param radicacion   * corresponde a la radicación de la anotación
	 *                                 sobre la matricula relacionada
	 */
	public void setRadicacion(java.lang.String radicacion)
	{
		this.radicacion = radicacion;
	}

	/**
	 * Gets the radicacion value for this TipoMatriculaDerivadas.
	 *
	 * @return radicacion   * corresponde a la radicación de la anotación
	 *                                 sobre la matricula relacionada
	 */
	public java.lang.String getRadicacion()
	{
		return radicacion;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoMatriculaDerivadas))
			return false;

		TipoMatriculaDerivadas other = (TipoMatriculaDerivadas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numMatriculaInmobiliaria == null) && (other.getNumMatriculaInmobiliaria() == null))
				|| ((this.numMatriculaInmobiliaria != null)
				&& this.numMatriculaInmobiliaria.equals(other.getNumMatriculaInmobiliaria())))
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())))
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())))
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())))
				&& (((this.radicacion == null) && (other.getRadicacion() == null))
				|| ((this.radicacion != null) && this.radicacion.equals(other.getRadicacion())))
				&& (((this.anotacionRelacionada == null) && (other.getAnotacionRelacionada() == null))
				|| ((this.anotacionRelacionada != null)
				&& this.anotacionRelacionada.equals(other.getAnotacionRelacionada())));
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

		if(getNumMatriculaInmobiliaria() != null)
			_hashCode += getNumMatriculaInmobiliaria().hashCode();

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

		if(getRadicacion() != null)
			_hashCode += getRadicacion().hashCode();

		if(getAnotacionRelacionada() != null)
			_hashCode += getAnotacionRelacionada().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
