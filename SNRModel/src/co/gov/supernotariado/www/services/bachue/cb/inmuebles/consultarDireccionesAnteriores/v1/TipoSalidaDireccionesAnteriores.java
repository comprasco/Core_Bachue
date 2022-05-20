/**
 * TipoSalidaDireccionesAnteriores.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1;



/**
 * El esquema define los
 *                         datos de salida para la operación
 *                         consultarDireccionesAnteriores.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 13/03/2020
 */
public class TipoSalidaDireccionesAnteriores implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1548594614011371397L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaDireccionesAnteriores.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "tipoSalidaDireccionesAnteriores"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionesPredioAnteriores");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "direccionesPredioAnteriores"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
		        "tipoDireccionAnterior"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDireccionesAnteriores/v1",
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

	/** Propiedad direcciones predio anteriores. */
	private co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior[] direccionesPredioAnteriores;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida direcciones anteriores.
	 */
	public TipoSalidaDireccionesAnteriores()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida direcciones anteriores.
	 *
	 * @param direccionesPredioAnteriores de direcciones predio anteriores
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaDireccionesAnteriores(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior[] direccionesPredioAnteriores,
	    java.math.BigInteger                                                                                             codMensaje,
	    java.lang.String                                                                                                 descripcionMensaje
	)
	{
		this.direccionesPredioAnteriores     = direccionesPredioAnteriores;
		this.codMensaje                      = codMensaje;
		this.descripcionMensaje              = descripcionMensaje;
	}

	/**
	 * Gets the direccionesPredioAnteriores value for this TipoSalidaDireccionesAnteriores.
	 *
	 * @return direccionesPredioAnteriores
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior[] getDireccionesPredioAnteriores()
	{
		return direccionesPredioAnteriores;
	}

	/**
	 * Sets the direccionesPredioAnteriores value for this TipoSalidaDireccionesAnteriores.
	 *
	 * @param direccionesPredioAnteriores de direcciones predio anteriores
	 */
	public void setDireccionesPredioAnteriores(
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior[] direccionesPredioAnteriores
	)
	{
		this.direccionesPredioAnteriores = direccionesPredioAnteriores;
	}

	/**
	 * Retorna Objeto o variable de valor direcciones predio anteriores.
	 *
	 * @param i de i
	 * @return el valor de direcciones predio anteriores
	 */
	public co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior getDireccionesPredioAnteriores(
	    int i
	)
	{
		return this.direccionesPredioAnteriores[i];
	}

	/**
	 * Cambia el valor de direcciones predio anteriores.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setDireccionesPredioAnteriores(
	    int i,
	    co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDireccionesAnteriores.v1.TipoDireccionAnterior _value
	)
	{
		this.direccionesPredioAnteriores[i] = _value;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaDireccionesAnteriores.
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
	 * Sets the codMensaje value for this TipoSalidaDireccionesAnteriores.
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
	 * Gets the descripcionMensaje value for this TipoSalidaDireccionesAnteriores.
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
	 * Sets the descripcionMensaje value for this TipoSalidaDireccionesAnteriores.
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
		if(!(obj instanceof TipoSalidaDireccionesAnteriores))
			return false;

		TipoSalidaDireccionesAnteriores other = (TipoSalidaDireccionesAnteriores)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.direccionesPredioAnteriores == null) && (other.getDireccionesPredioAnteriores() == null))
				|| ((this.direccionesPredioAnteriores != null)
				&& java.util.Arrays.equals(this.direccionesPredioAnteriores, other.getDireccionesPredioAnteriores())))
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

		if(getDireccionesPredioAnteriores() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDireccionesPredioAnteriores()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDireccionesPredioAnteriores(), i);

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
