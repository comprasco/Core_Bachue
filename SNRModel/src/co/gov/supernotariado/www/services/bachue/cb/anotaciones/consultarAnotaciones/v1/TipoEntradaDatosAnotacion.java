/**
 * TipoEntradaDatosAnotacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1;



/**
 * El esquema define los
 *                         datos de entrada para la operacion consultarAnotaciones.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class TipoEntradaDatosAnotacion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5314484778279357611L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaDatosAnotacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoEntradaDatosAnotacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("convenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1", "convenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "tipoIdentificacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        ">tipoEntradaDatosAnotacion>tipoIdentificacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("claseAnotacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        "claseAnotacion"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/anotaciones/consultarAnotaciones/v1",
		        ">tipoEntradaDatosAnotacion>claseAnotacion"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad clase anotacion. */
	/* Al seleccionar una clase de anotación, el
	 *                                 sistema podrá distinguir entre una anotación normal y una
	 *                                 anotación creada como Medida Cautelar */
	private co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionClaseAnotacion claseAnotacion;

	/** Propiedad convenio. */
	/* Código del nombre de convenio que tendría la
	 *                                 SNR
	 *                                 con la
	 *                                 Entidad que consume el servicio */
	private java.lang.String convenio;

	/** Propiedad num identificacion predio. */
	/* Al seleccionar un tipo de Identificación
	 *                                 predio,
	 *                                 se espera en este campo el número correspondiente; para el
	 * caso
	 *                                 de
	 *                                 matricula, el sistema toma los tres primeros caracteres como
	 *                                 Código Circulo Registral y los siguientes como el Número de
	 *                                 Matrícula Inmobiliaria. */
	private java.lang.String numIdentificacionPredio;

	/** Propiedad tipo identificacion predio. */
	/* Corresponde al tipo de identificacion por el
	 *                                 cual podemos buscar un predio. */
	private co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionTipoIdentificacionPredio tipoIdentificacionPredio;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                            __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada datos anotacion.
	 */
	public TipoEntradaDatosAnotacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada datos anotacion.
	 *
	 * @param convenio de convenio
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 * @param numIdentificacionPredio de num identificacion predio
	 * @param claseAnotacion de clase anotacion
	 */
	public TipoEntradaDatosAnotacion(
	    java.lang.String convenio,
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionTipoIdentificacionPredio tipoIdentificacionPredio,
	    java.lang.String numIdentificacionPredio,
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionClaseAnotacion claseAnotacion
	)
	{
		this.convenio                     = convenio;
		this.tipoIdentificacionPredio     = tipoIdentificacionPredio;
		this.numIdentificacionPredio      = numIdentificacionPredio;
		this.claseAnotacion               = claseAnotacion;
	}

	/**
	 * Gets the convenio value for this TipoEntradaDatosAnotacion.
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
	 * Sets the convenio value for this TipoEntradaDatosAnotacion.
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
	 * Gets the tipoIdentificacionPredio value for this TipoEntradaDatosAnotacion.
	 *
	 * @return tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
	     *                                 cual podemos buscar un predio.
	 */
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionTipoIdentificacionPredio getTipoIdentificacionPredio()
	{
		return tipoIdentificacionPredio;
	}

	/**
	 * Sets the tipoIdentificacionPredio value for this TipoEntradaDatosAnotacion.
	 *
	 * @param tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
	     *                                 cual podemos buscar un predio.
	 */
	public void setTipoIdentificacionPredio(
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionTipoIdentificacionPredio tipoIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio = tipoIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoEntradaDatosAnotacion.
	 *
	 * @return numIdentificacionPredio   * Al seleccionar un tipo de Identificación
	     *                                 predio,
	     *                                 se espera en este campo el número correspondiente; para el
	     * caso
	     *                                 de
	     *                                 matricula, el sistema toma los tres primeros caracteres como
	     *                                 Código Circulo Registral y los siguientes como el Número de
	     *                                 Matrícula Inmobiliaria.
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
	}

	/**
	 * Sets the numIdentificacionPredio value for this TipoEntradaDatosAnotacion.
	 *
	 * @param numIdentificacionPredio   * Al seleccionar un tipo de Identificación
	     *                                 predio,
	     *                                 se espera en este campo el número correspondiente; para el
	     * caso
	     *                                 de
	     *                                 matricula, el sistema toma los tres primeros caracteres como
	     *                                 Código Circulo Registral y los siguientes como el Número de
	     *                                 Matrícula Inmobiliaria.
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/**
	 * Gets the claseAnotacion value for this TipoEntradaDatosAnotacion.
	 *
	 * @return claseAnotacion   * Al seleccionar una clase de anotación, el
	     *                                 sistema podrá distinguir entre una anotación normal y una
	     *                                 anotación creada como Medida Cautelar
	 */
	public co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionClaseAnotacion getClaseAnotacion()
	{
		return claseAnotacion;
	}

	/**
	 * Sets the claseAnotacion value for this TipoEntradaDatosAnotacion.
	 *
	 * @param claseAnotacion   * Al seleccionar una clase de anotación, el
	     *                                 sistema podrá distinguir entre una anotación normal y una
	     *                                 anotación creada como Medida Cautelar
	 */
	public void setClaseAnotacion(
	    co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacionClaseAnotacion claseAnotacion
	)
	{
		this.claseAnotacion = claseAnotacion;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaDatosAnotacion))
			return false;

		TipoEntradaDatosAnotacion other = (TipoEntradaDatosAnotacion)obj;

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
				&& (((this.tipoIdentificacionPredio == null) && (other.getTipoIdentificacionPredio() == null))
				|| ((this.tipoIdentificacionPredio != null)
				&& this.tipoIdentificacionPredio.equals(other.getTipoIdentificacionPredio())))
				&& (((this.numIdentificacionPredio == null) && (other.getNumIdentificacionPredio() == null))
				|| ((this.numIdentificacionPredio != null)
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())))
				&& (((this.claseAnotacion == null) && (other.getClaseAnotacion() == null))
				|| ((this.claseAnotacion != null) && this.claseAnotacion.equals(other.getClaseAnotacion())));
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

		if(getTipoIdentificacionPredio() != null)
			_hashCode += getTipoIdentificacionPredio().hashCode();

		if(getNumIdentificacionPredio() != null)
			_hashCode += getNumIdentificacionPredio().hashCode();

		if(getClaseAnotacion() != null)
			_hashCode += getClaseAnotacion().hashCode();

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
