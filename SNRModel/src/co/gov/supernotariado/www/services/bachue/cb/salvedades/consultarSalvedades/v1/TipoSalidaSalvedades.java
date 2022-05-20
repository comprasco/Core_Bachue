/**
 * TipoSalidaSalvedades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion consultarSalvedades.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
public class TipoSalidaSalvedades implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8767948736355722038L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaSalvedades.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
		        "tipoSalidaSalvedades"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("salvedades");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1", "salvedades"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1", "tipoSalvedad"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1", "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
	 *                                 casos en que la consulta no fue exitosa, vacío cuando la
	 *                                 consulta es exitosa */
	private java.lang.String descripcionMensaje;

	/** Propiedad salvedades. */
	private co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalvedad[] salvedades;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida salvedades.
	 */
	public TipoSalidaSalvedades()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida salvedades.
	 *
	 * @param salvedades de salvedades
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaSalvedades(
	    co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalvedad[] salvedades,
	    java.math.BigInteger                                                                          codMensaje,
	    java.lang.String                                                                              descripcionMensaje
	)
	{
		this.salvedades             = salvedades;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the salvedades value for this TipoSalidaSalvedades.
	 *
	 * @return salvedades
	 */
	public co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalvedad[] getSalvedades()
	{
		return salvedades;
	}

	/**
	 * Sets the salvedades value for this TipoSalidaSalvedades.
	 *
	 * @param salvedades de salvedades
	 */
	public void setSalvedades(
	    co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalvedad[] salvedades
	)
	{
		this.salvedades = salvedades;
	}

	/**
	 * Retorna Objeto o variable de valor salvedades.
	 *
	 * @param i de i
	 * @return el valor de salvedades
	 */
	public co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalvedad getSalvedades(
	    int i
	)
	{
		return this.salvedades[i];
	}

	/**
	 * Cambia el valor de salvedades.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setSalvedades(
	    int i, co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalvedad _value
	)
	{
		this.salvedades[i] = _value;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaSalvedades.
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
	 * Sets the codMensaje value for this TipoSalidaSalvedades.
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
	 * Gets the descripcionMensaje value for this TipoSalidaSalvedades.
	 *
	 * @return descripcionMensaje   * Corresponde a al mensaje de error para los
	 *                                 casos en que la consulta no fue exitosa, vacío cuando la
	 *                                 consulta es exitosa
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaSalvedades.
	 *
	 * @param descripcionMensaje   * Corresponde a al mensaje de error para los
	 *                                 casos en que la consulta no fue exitosa, vacío cuando la
	 *                                 consulta es exitosa
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaSalvedades))
			return false;

		TipoSalidaSalvedades other = (TipoSalidaSalvedades)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.salvedades == null) && (other.getSalvedades() == null))
				|| ((this.salvedades != null) && java.util.Arrays.equals(this.salvedades, other.getSalvedades())))
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

		if(getSalvedades() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getSalvedades()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getSalvedades(), i);

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
