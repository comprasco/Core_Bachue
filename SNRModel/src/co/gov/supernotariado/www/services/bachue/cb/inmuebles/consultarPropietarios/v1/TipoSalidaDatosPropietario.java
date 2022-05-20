/**
 * TipoSalidaDatosPropietario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1;



/**
 * El esquema define los
 *                         datos de salida para la operacion consultarPropietarios.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class TipoSalidaDatosPropietario implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 408263372734254360L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaDatosPropietario.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
		        "tipoSalidaDatosPropietario"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("propietarios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
		        "propietarios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
		        "tipoPropietario"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1", "codMensaje"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarPropietarios/v1",
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

	/** Propiedad propietarios. */
	private co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario[] propietarios;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida datos propietario.
	 */
	public TipoSalidaDatosPropietario()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida datos propietario.
	 *
	 * @param propietarios de propietarios
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaDatosPropietario(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario[] propietarios,
	    java.math.BigInteger                                                                              codMensaje,
	    java.lang.String                                                                                  descripcionMensaje
	)
	{
		this.propietarios           = propietarios;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the propietarios value for this TipoSalidaDatosPropietario.
	 *
	 * @return propietarios
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario[] getPropietarios()
	{
		return propietarios;
	}

	/**
	 * Sets the propietarios value for this TipoSalidaDatosPropietario.
	 *
	 * @param propietarios de propietarios
	 */
	public void setPropietarios(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario[] propietarios
	)
	{
		this.propietarios = propietarios;
	}

	/**
	 * Retorna Objeto o variable de valor propietarios.
	 *
	 * @param i de i
	 * @return el valor de propietarios
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario getPropietarios(
	    int i
	)
	{
		return this.propietarios[i];
	}

	/**
	 * Cambia el valor de propietarios.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setPropietarios(
	    int i, co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarPropietarios.v1.TipoPropietario _value
	)
	{
		this.propietarios[i] = _value;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaDatosPropietario.
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
	 * Sets the codMensaje value for this TipoSalidaDatosPropietario.
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
	 * Gets the descripcionMensaje value for this TipoSalidaDatosPropietario.
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
	 * Sets the descripcionMensaje value for this TipoSalidaDatosPropietario.
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
		if(!(obj instanceof TipoSalidaDatosPropietario))
			return false;

		TipoSalidaDatosPropietario other = (TipoSalidaDatosPropietario)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.propietarios == null) && (other.getPropietarios() == null))
				|| ((this.propietarios != null) && java.util.Arrays.equals(this.propietarios, other.getPropietarios())))
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

		if(getPropietarios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getPropietarios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getPropietarios(), i);

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
