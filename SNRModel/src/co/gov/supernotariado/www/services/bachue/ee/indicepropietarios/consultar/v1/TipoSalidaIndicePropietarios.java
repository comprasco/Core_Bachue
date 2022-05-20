/**
 * TipoSalidaIndicePropietarios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1;


/**
 * El esquema define los
 *                         datos de salida para la generación de la Consulta de
 *                         Índice de
 *                         Propietarios.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public class TipoSalidaIndicePropietarios implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1195953417402759563L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaIndicePropietarios.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "tipoSalidaIndicePropietarios"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cantidadResultados");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "datosInmueble"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "inmuebleDTO"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaHoraConsulta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "fechaHoraConsulta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "codMensaje"
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
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroConsulta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1", "numeroConsulta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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

	/** Propiedad fecha hora consulta. */
	/* la fecha y hora en la que hubo una respuesta
	 *                                 de
	 *                                 la consulta. */
	private java.lang.String fechaHoraConsulta;

	/** Propiedad is id proceso consulta. */
	private String is_idProcesoConsulta;

	/** Propiedad numero consulta. */
	/* Corresponde a una seuencia o id que identifica
	 *                                 cada consulta. */
	private String numeroConsulta;

	/** Propiedad datos inmueble. */
	/* Corresponde a la información del inmueble */
	private co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.InmuebleDTO[] datosInmueble;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad il consecutivo consulta. */
	private long il_consecutivoConsulta;

	/** Propiedad il consecutivo consulta detalle. */
	private long il_consecutivoConsultaDetalle;

	/**
	 * Instancia un nuevo objeto tipo salida indice propietarios.
	 */
	public TipoSalidaIndicePropietarios()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida indice propietarios.
	 *
	 * @param cantidadResultados correspondiente al valor de cantidad resultados
	 * @param datosInmueble correspondiente al valor de datos inmueble
	 * @param fechaHoraConsulta correspondiente al valor de fecha hora consulta
	 * @param codMensaje correspondiente al valor de cod mensaje
	 * @param descripcionMensaje correspondiente al valor de descripcion mensaje
	 * @param numeroConsulta correspondiente al valor de numero consulta
	 */
	public TipoSalidaIndicePropietarios(
	    java.lang.Long cantidadResultados,
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.InmuebleDTO[] datosInmueble,
	    java.lang.String fechaHoraConsulta, java.math.BigInteger codMensaje, java.lang.String descripcionMensaje,
	    String numeroConsulta
	)
	{
		this.cantidadResultados     = cantidadResultados;
		this.datosInmueble          = datosInmueble;
		this.fechaHoraConsulta      = fechaHoraConsulta;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
		this.numeroConsulta         = numeroConsulta;
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
	 * Sets the datosInmueble value for this TipoSalidaIndicePropietarios.
	 *
	 * @param datosInmueble   * Corresponde a la información del inmueble
	 */
	public void setDatosInmueble(
	    co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.InmuebleDTO[] datosInmueble
	)
	{
		this.datosInmueble = datosInmueble;
	}

	/**
	 * Sets the datos inmueble.
	 *
	 * @param i correspondiente al valor de i
	 * @param _value correspondiente al valor de value
	 */
	public void setDatosInmueble(
	    int i, co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.InmuebleDTO _value
	)
	{
		this.datosInmueble[i] = _value;
	}

	/**
	 * Gets the datosInmueble value for this TipoSalidaIndicePropietarios.
	 *
	 * @return datosInmueble   * Corresponde a la información del inmueble
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.InmuebleDTO[] getDatosInmueble()
	{
		return datosInmueble;
	}

	/**
	 * Retorna Objeto o variable de valor datos inmueble.
	 *
	 * @param i correspondiente al valor de i
	 * @return el valor de datos inmueble
	 */
	public co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1.InmuebleDTO getDatosInmueble(
	    int i
	)
	{
		return this.datosInmueble[i];
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
	 * Get Custom Deserializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
	 * @return el valor de deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the fechaHoraConsulta value for this TipoSalidaIndicePropietarios.
	 *
	 * @param fechaHoraConsulta   * la fecha y hora en la que hubo una respuesta
	     *                                 de
	     *                                 la consulta.
	 */
	public void setFechaHoraConsulta(java.lang.String fechaHoraConsulta)
	{
		this.fechaHoraConsulta = fechaHoraConsulta;
	}

	/**
	 * Gets the fechaHoraConsulta value for this TipoSalidaIndicePropietarios.
	 *
	 * @return fechaHoraConsulta   * la fecha y hora en la que hubo una respuesta
	     *                                 de
	     *                                 la consulta.
	 */
	public java.lang.String getFechaHoraConsulta()
	{
		return fechaHoraConsulta;
	}

	/**
	 * Sets the numeroConsulta value for this TipoSalidaIndicePropietarios.
	 *
	 * @param numeroConsulta   * Corresponde a una seuencia o id que identifica
	     *                                 cada consulta.
	 */
	public void setNumeroConsulta(String numeroConsulta)
	{
		this.numeroConsulta = numeroConsulta;
	}

	/**
	 * Gets the numeroConsulta value for this TipoSalidaIndicePropietarios.
	 *
	 * @return numeroConsulta   * Corresponde a una seuencia o id que identifica
	     *                                 cada consulta.
	 */
	public String getNumeroConsulta()
	{
		return numeroConsulta;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
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
	 *  {@inheritdoc}.
	 *
	 * @param obj de obj
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
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
				&& (((this.fechaHoraConsulta == null) && (other.getFechaHoraConsulta() == null))
				|| ((this.fechaHoraConsulta != null) && this.fechaHoraConsulta.equals(other.getFechaHoraConsulta())))
				&& (((this.codMensaje == null) && (other.getCodMensaje() == null))
				|| ((this.codMensaje != null) && this.codMensaje.equals(other.getCodMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())))
				&& (this.numeroConsulta == other.getNumeroConsulta());
		__equalsCalc     = null;

		return _equals;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de int
	 */
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

		if(getFechaHoraConsulta() != null)
			_hashCode += getFechaHoraConsulta().hashCode();

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		_hashCode += new Long(getNumeroConsulta()).hashCode();
		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo consulta.
	 *
	 * @return Retorna el valor de la propiedad consecutivoConsulta
	 */
	public long getConsecutivoConsulta()
	{
		return il_consecutivoConsulta;
	}

	/**
	 * Modifica el valor de ConsecutivoConsulta.
	 *
	 * @param al_l de al l
	 */
	public void setConsecutivoConsulta(long al_l)
	{
		il_consecutivoConsulta = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo consulta detalle.
	 *
	 * @return Retorna el valor de la propiedad consecutivoConsultaDetalle
	 */
	public long getConsecutivoConsultaDetalle()
	{
		return il_consecutivoConsultaDetalle;
	}

	/**
	 * Modifica el valor de ConsecutivoConsultaDetalle.
	 *
	 * @param al_l de al l
	 */
	public void setConsecutivoConsultaDetalle(long al_l)
	{
		il_consecutivoConsultaDetalle = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso consulta.
	 *
	 * @return Retorna el valor de la propiedad idProcesoConsulta
	 */
	public String getIdProcesoConsulta()
	{
		return is_idProcesoConsulta;
	}

	/**
	 * Modifica el valor de IdProcesoConsulta.
	 *
	 * @param as_s de as s
	 */
	public void setIdProcesoConsulta(String as_s)
	{
		is_idProcesoConsulta = as_s;
	}
}
