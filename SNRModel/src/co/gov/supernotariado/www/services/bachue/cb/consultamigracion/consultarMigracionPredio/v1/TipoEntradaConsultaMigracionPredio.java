/**
 * TipoEntradaConsultaMigracionPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1;



/**
 * El esquema define los
 *                         datos de entrada para saber si la información del Predio ha
 * sido
 *                         migrada a Bachue o si todavía se debe consultar en Nodo Central.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultaMigracionPredio implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4789396254704496376L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultaMigracionPredio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
		        "tipoEntradaConsultaMigracionPredio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
		        "tipoIdentificacionPredio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
		        ">tipoEntradaConsultaMigracionPredio>tipoIdentificacionPredio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numIdentificacionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionPredio/v1",
		        "numIdentificacionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad num identificacion predio. */
	/* Al seleccionar un tipo de Identificación
	 *                                 predio,
	 *                                 se espera en este campo el número correspondiente; para
	 *                                 el caso
	 *                                 de
	 *                                 matricula, el sistema toma los tres primeros caracteres
	 *                                 como
	 *                                 Código Circulo Registral y los siguientes como el Número de
	 *                                 Matrícula Inmobiliaria. */
	private java.lang.String numIdentificacionPredio;

	/** Propiedad tipo identificacion predio. */
	/* Corresponde al tipo de identificacion por el
	 *                                 cual podemos buscar un predio. */
	private co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredioTipoIdentificacionPredio tipoIdentificacionPredio;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                               __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada consulta migracion predio.
	 */
	public TipoEntradaConsultaMigracionPredio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consulta migracion predio.
	 *
	 * @param tipoIdentificacionPredio de tipo identificacion predio
	 * @param numIdentificacionPredio de num identificacion predio
	 */
	public TipoEntradaConsultaMigracionPredio(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredioTipoIdentificacionPredio tipoIdentificacionPredio,
	    java.lang.String                                                                                                                                      numIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio     = tipoIdentificacionPredio;
		this.numIdentificacionPredio      = numIdentificacionPredio;
	}

	/**
	 * Gets the tipoIdentificacionPredio value for this TipoEntradaConsultaMigracionPredio.
	 *
	 * @return tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
	     *                                 cual podemos buscar un predio.
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredioTipoIdentificacionPredio getTipoIdentificacionPredio()
	{
		return tipoIdentificacionPredio;
	}

	/**
	 * Sets the tipoIdentificacionPredio value for this TipoEntradaConsultaMigracionPredio.
	 *
	 * @param tipoIdentificacionPredio   * Corresponde al tipo de identificacion por el
	     *                                 cual podemos buscar un predio.
	 */
	public void setTipoIdentificacionPredio(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredioTipoIdentificacionPredio tipoIdentificacionPredio
	)
	{
		this.tipoIdentificacionPredio = tipoIdentificacionPredio;
	}

	/**
	 * Gets the numIdentificacionPredio value for this TipoEntradaConsultaMigracionPredio.
	 *
	 * @return numIdentificacionPredio   * Al seleccionar un tipo de Identificación
	     *                                 predio,
	     *                                 se espera en este campo el número correspondiente; para
	     *                                 el caso
	     *                                 de
	     *                                 matricula, el sistema toma los tres primeros caracteres
	     *                                 como
	     *                                 Código Circulo Registral y los siguientes como el Número de
	     *                                 Matrícula Inmobiliaria.
	 */
	public java.lang.String getNumIdentificacionPredio()
	{
		return numIdentificacionPredio;
	}

	/**
	 * Sets the numIdentificacionPredio value for this TipoEntradaConsultaMigracionPredio.
	 *
	 * @param numIdentificacionPredio   * Al seleccionar un tipo de Identificación
	     *                                 predio,
	     *                                 se espera en este campo el número correspondiente; para
	     *                                 el caso
	     *                                 de
	     *                                 matricula, el sistema toma los tres primeros caracteres
	     *                                 como
	     *                                 Código Circulo Registral y los siguientes como el Número de
	     *                                 Matrícula Inmobiliaria.
	 */
	public void setNumIdentificacionPredio(java.lang.String numIdentificacionPredio)
	{
		this.numIdentificacionPredio = numIdentificacionPredio;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultaMigracionPredio))
			return false;

		TipoEntradaConsultaMigracionPredio other = (TipoEntradaConsultaMigracionPredio)obj;

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
				&& this.numIdentificacionPredio.equals(other.getNumIdentificacionPredio())));
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
