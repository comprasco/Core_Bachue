/**
 * TipoSalidaDatosMatriculasDerivadas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion
 *                         consultarDatosMatriculasDerivadas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaDatosMatriculasDerivadas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -566789902538009996L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaDatosMatriculasDerivadas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "tipoSalidaDatosMatriculasDerivadas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculasDerivadas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "matriculasDerivadas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "tipoMatriculaDerivadas"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
		        "codMensaje"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasDerivadas/v1",
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
	
	/** Propiedad matriculas derivadas. */
	private co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoMatriculaDerivadas[] matriculasDerivadas;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida datos matriculas derivadas.
	 */
	public TipoSalidaDatosMatriculasDerivadas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida datos matriculas derivadas.
	 *
	 * @param matriculasDerivadas de matriculas derivadas
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaDatosMatriculasDerivadas(
	    co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoMatriculaDerivadas[] matriculasDerivadas,
	    java.math.BigInteger                                                                                                         codMensaje,
	    java.lang.String                                                                                                             descripcionMensaje
	)
	{
		this.matriculasDerivadas     = matriculasDerivadas;
		this.codMensaje              = codMensaje;
		this.descripcionMensaje      = descripcionMensaje;
	}

	/**
	 * Gets the matriculasDerivadas value for this TipoSalidaDatosMatriculasDerivadas.
	 *
	 * @return matriculasDerivadas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoMatriculaDerivadas[] getMatriculasDerivadas()
	{
		return matriculasDerivadas;
	}

	/**
	 * Sets the matriculasDerivadas value for this TipoSalidaDatosMatriculasDerivadas.
	 *
	 * @param matriculasDerivadas de matriculas derivadas
	 */
	public void setMatriculasDerivadas(
	    co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoMatriculaDerivadas[] matriculasDerivadas
	)
	{
		this.matriculasDerivadas = matriculasDerivadas;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas derivadas.
	 *
	 * @param i de i
	 * @return el valor de matriculas derivadas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoMatriculaDerivadas getMatriculasDerivadas(
	    int i
	)
	{
		return this.matriculasDerivadas[i];
	}

	/**
	 * Cambia el valor de matriculas derivadas.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setMatriculasDerivadas(
	    int i,
	    co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasDerivadas.v1.TipoMatriculaDerivadas _value
	)
	{
		this.matriculasDerivadas[i] = _value;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaDatosMatriculasDerivadas.
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
	 * Sets the codMensaje value for this TipoSalidaDatosMatriculasDerivadas.
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
	 * Gets the descripcionMensaje value for this TipoSalidaDatosMatriculasDerivadas.
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
	 * Sets the descripcionMensaje value for this TipoSalidaDatosMatriculasDerivadas.
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
		if(!(obj instanceof TipoSalidaDatosMatriculasDerivadas))
			return false;

		TipoSalidaDatosMatriculasDerivadas other = (TipoSalidaDatosMatriculasDerivadas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.matriculasDerivadas == null) && (other.getMatriculasDerivadas() == null))
				|| ((this.matriculasDerivadas != null)
				&& java.util.Arrays.equals(this.matriculasDerivadas, other.getMatriculasDerivadas())))
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

		if(getMatriculasDerivadas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculasDerivadas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculasDerivadas(), i);

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
