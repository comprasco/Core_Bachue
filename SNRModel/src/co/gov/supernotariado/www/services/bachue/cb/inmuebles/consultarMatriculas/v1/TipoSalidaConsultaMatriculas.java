/**
 * TipoSalidaConsultaMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion consultarMatriculas dado el
 * dato
 *                         de una persona.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class TipoSalidaConsultaMatriculas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5518070396100083401L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultaMatriculas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "tipoSalidaConsultaMatriculas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "matriculas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "tipoMatricula"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod mensaje. */
	/* 200 cuando no hay error, 4* por errores de
	 *                                 validación de campos de entrada o validación de negocio y
	 * 500
	 *                                 cuando existen errores de sistema */
	private java.math.BigInteger codMensaje;

	/** Propiedad descripcion mensaje. */
	/* Corresponde a al mensaje de error para los
	 *                                 casos
	 *                                 en qeue la consulta no fue exitosa, vacío cuando la
	 *                                 consulta es
	 *                                 exitosa */
	private java.lang.String descripcionMensaje;

	/** Propiedad matriculas. */
	private co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula[] matriculas;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consulta matriculas.
	 */
	public TipoSalidaConsultaMatriculas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consulta matriculas.
	 *
	 * @param matriculas de matriculas
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaConsultaMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula[] matriculas,
	    java.math.BigInteger                                                                          codMensaje,
	    java.lang.String                                                                              descripcionMensaje
	)
	{
		this.matriculas             = matriculas;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the matriculas value for this TipoSalidaConsultaMatriculas.
	 *
	 * @return matriculas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula[] getMatriculas()
	{
		return matriculas;
	}

	/**
	 * Sets the matriculas value for this TipoSalidaConsultaMatriculas.
	 *
	 * @param matriculas de matriculas
	 */
	public void setMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula[] matriculas
	)
	{
		this.matriculas = matriculas;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas.
	 *
	 * @param i de i
	 * @return el valor de matriculas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula getMatriculas(
	    int i
	)
	{
		return this.matriculas[i];
	}

	/**
	 * Cambia el valor de matriculas.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setMatriculas(
	    int i, co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1.TipoMatricula _value
	)
	{
		this.matriculas[i] = _value;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaConsultaMatriculas.
	 *
	 * @return codMensaje   * 200 cuando no hay error, 4* por errores de
	     *                                 validación de campos de entrada o validación de negocio y
	     * 500
	     *                                 cuando existen errores de sistema
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaConsultaMatriculas.
	 *
	 * @param codMensaje   * 200 cuando no hay error, 4* por errores de
	     *                                 validación de campos de entrada o validación de negocio y
	     * 500
	     *                                 cuando existen errores de sistema
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultaMatriculas.
	 *
	 * @return descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la
	     *                                 consulta es
	     *                                 exitosa
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultaMatriculas.
	 *
	 * @param descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la
	     *                                 consulta es
	     *                                 exitosa
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultaMatriculas))
			return false;

		TipoSalidaConsultaMatriculas other = (TipoSalidaConsultaMatriculas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.matriculas == null) && (other.getMatriculas() == null))
				|| ((this.matriculas != null) && java.util.Arrays.equals(this.matriculas, other.getMatriculas())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getMatriculas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

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
