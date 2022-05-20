/**
 * TipoSalidaDatosMatriculasMatriz.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion consultarDatosMatriculasMatriz.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaDatosMatriculasMatriz implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2581312665508538551L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaDatosMatriculasMatriz.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1",
		        "tipoSalidaDatosMatriculasMatriz"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculasMatriz");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1",
		        "matriculasMatriz"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1",
		        "tipoMatriculaMatriz"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/matriculasrelacionadas/consultarMatriculasMatriz/v1",
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
	private java.lang.String                                                                                                       descripcionMensaje;
	
	/** Propiedad matriculas matriz. */
	private co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoMatriculaMatriz[] matriculasMatriz;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida datos matriculas matriz.
	 */
	public TipoSalidaDatosMatriculasMatriz()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida datos matriculas matriz.
	 *
	 * @param matriculasMatriz de matriculas matriz
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaDatosMatriculasMatriz(
	    co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoMatriculaMatriz[] matriculasMatriz,
	    java.math.BigInteger                                                                                                   codMensaje,
	    java.lang.String                                                                                                       descripcionMensaje
	)
	{
		this.matriculasMatriz       = matriculasMatriz;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the matriculasMatriz value for this TipoSalidaDatosMatriculasMatriz.
	 *
	 * @return matriculasMatriz
	 */
	public co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoMatriculaMatriz[] getMatriculasMatriz()
	{
		return matriculasMatriz;
	}

	/**
	 * Sets the matriculasMatriz value for this TipoSalidaDatosMatriculasMatriz.
	 *
	 * @param matriculasMatriz de matriculas matriz
	 */
	public void setMatriculasMatriz(
	    co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoMatriculaMatriz[] matriculasMatriz
	)
	{
		this.matriculasMatriz = matriculasMatriz;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas matriz.
	 *
	 * @param i de i
	 * @return el valor de matriculas matriz
	 */
	public co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoMatriculaMatriz getMatriculasMatriz(
	    int i
	)
	{
		return this.matriculasMatriz[i];
	}

	/**
	 * Cambia el valor de matriculas matriz.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setMatriculasMatriz(
	    int i,
	    co.gov.supernotariado.www.services.bachue.cb.matriculasrelacionadas.consultarMatriculasMatriz.v1.TipoMatriculaMatriz _value
	)
	{
		this.matriculasMatriz[i] = _value;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaDatosMatriculasMatriz.
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
	 * Sets the codMensaje value for this TipoSalidaDatosMatriculasMatriz.
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
	 * Gets the descripcionMensaje value for this TipoSalidaDatosMatriculasMatriz.
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
	 * Sets the descripcionMensaje value for this TipoSalidaDatosMatriculasMatriz.
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
		if(!(obj instanceof TipoSalidaDatosMatriculasMatriz))
			return false;

		TipoSalidaDatosMatriculasMatriz other = (TipoSalidaDatosMatriculasMatriz)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.matriculasMatriz == null) && (other.getMatriculasMatriz() == null))
				|| ((this.matriculasMatriz != null)
				&& java.util.Arrays.equals(this.matriculasMatriz, other.getMatriculasMatriz())))
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

		if(getMatriculasMatriz() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculasMatriz()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculasMatriz(), i);

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
