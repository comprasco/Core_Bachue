/**
 * TipoSalidaIndicePropietarios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1;



/**
 * El esquema define los
 *                         datos de salida para la generación de la Consulta de
 *                         Índice de
 *                         Propietarios.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
public class TipoSalidaIndicePropietarios implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 576404513990884098L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaIndicePropietarios.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        "tipoSalidaIndicePropietarios"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cantidadResultados");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        "cantidadResultados"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("datosInmueble");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "datosInmueble"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "inmuebleDTO"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1", "codMensaje"
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
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

	/** Propiedad cantidad resultados. */
	/* Corresponde a la cantidad de resultados
	 *                                 obtenidos
	 *                                 en la consulta */
	private java.lang.Long cantidadResultados;

	/** Propiedad cod mensaje. */
	/* 200 cuando no hay error, 4* por errores de
	 *                                 validación de campos de entrada o validación de negocio y
	 * 500
	 *                                 cuando existen errores de sistema. */
	private java.math.BigInteger codMensaje;

	/** Propiedad descripcion mensaje. */
	/* Corresponde a al mensaje de error para los
	 *                                 casos
	 *                                 en qeue la consulta no fue exitosa, vacío cuando la consulta
	 * es
	 *                                 exitosa */
	private java.lang.String descripcionMensaje;

	/** Propiedad datos inmueble. */
	/* Corresponde a la información del inmueble */
	private co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO[] datosInmueble;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida indice propietarios.
	 */
	public TipoSalidaIndicePropietarios()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida indice propietarios.
	 *
	 * @param cantidadResultados de cantidad resultados
	 * @param datosInmueble de datos inmueble
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaIndicePropietarios(
	    java.lang.Long cantidadResultados,
	    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO[] datosInmueble,
	    java.math.BigInteger codMensaje, java.lang.String descripcionMensaje
	)
	{
		this.cantidadResultados     = cantidadResultados;
		this.datosInmueble          = datosInmueble;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the cantidadResultados value for this TipoSalidaIndicePropietarios.
	 *
	 * @return cantidadResultados   * Corresponde a la cantidad de resultados
	     *                                 obtenidos
	     *                                 en la consulta
	 */
	public java.lang.Long getCantidadResultados()
	{
		return cantidadResultados;
	}

	/**
	 * Sets the cantidadResultados value for this TipoSalidaIndicePropietarios.
	 *
	 * @param cantidadResultados   * Corresponde a la cantidad de resultados
	     *                                 obtenidos
	     *                                 en la consulta
	 */
	public void setCantidadResultados(java.lang.Long cantidadResultados)
	{
		this.cantidadResultados = cantidadResultados;
	}

	/**
	 * Gets the datosInmueble value for this TipoSalidaIndicePropietarios.
	 *
	 * @return datosInmueble   * Corresponde a la información del inmueble
	 */
	public co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO[] getDatosInmueble()
	{
		return datosInmueble;
	}

	/**
	 * Sets the datosInmueble value for this TipoSalidaIndicePropietarios.
	 *
	 * @param datosInmueble   * Corresponde a la información del inmueble
	 */
	public void setDatosInmueble(
	    co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO[] datosInmueble
	)
	{
		this.datosInmueble = datosInmueble;
	}

	/**
	 * Retorna Objeto o variable de valor datos inmueble.
	 *
	 * @param i de i
	 * @return el valor de datos inmueble
	 */
	public co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO getDatosInmueble(
	    int i
	)
	{
		return this.datosInmueble[i];
	}

	/**
	 * Cambia el valor de datos inmueble.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setDatosInmueble(
	    int i, co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.InmuebleDTO _value
	)
	{
		this.datosInmueble[i] = _value;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaIndicePropietarios.
	 *
	 * @return codMensaje   * 200 cuando no hay error, 4* por errores de
	     *                                 validación de campos de entrada o validación de negocio y
	     * 500
	     *                                 cuando existen errores de sistema.
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaIndicePropietarios.
	 *
	 * @param codMensaje   * 200 cuando no hay error, 4* por errores de
	     *                                 validación de campos de entrada o validación de negocio y
	     * 500
	     *                                 cuando existen errores de sistema.
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaIndicePropietarios.
	 *
	 * @return descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la consulta
	     * es
	     *                                 exitosa
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaIndicePropietarios.
	 *
	 * @param descripcionMensaje   * Corresponde a al mensaje de error para los
	     *                                 casos
	     *                                 en qeue la consulta no fue exitosa, vacío cuando la consulta
	     * es
	     *                                 exitosa
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaIndicePropietarios))
			return false;

		TipoSalidaIndicePropietarios other = (TipoSalidaIndicePropietarios)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.cantidadResultados == null) && (other.getCantidadResultados() == null))
				|| ((this.cantidadResultados != null) && this.cantidadResultados.equals(other.getCantidadResultados())))
				&& (((this.datosInmueble == null) && (other.getDatosInmueble() == null))
				|| ((this.datosInmueble != null)
				&& java.util.Arrays.equals(this.datosInmueble, other.getDatosInmueble())))
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

		if(getCantidadResultados() != null)
			_hashCode += getCantidadResultados().hashCode();

		if(getDatosInmueble() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDatosInmueble()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDatosInmueble(), i);

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
