/**
 * TipoSalidaDatosTramites.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1;



/**
 * El esquema define los
 *                         datos de entrada para la operacion consultarAnotaciones.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaDatosTramites implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6241957000946530040L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaDatosTramites.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "tipoSalidaDatosTramites"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1", "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaHoraNIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "fechaHoraNIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("radicacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "radicacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaRadicacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "fechaRadicacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tiposActo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1", "tiposActo"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "tipoTiposActo"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("oficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "oficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomMunicipio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "nomMunicipio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
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
		        "https://www.supernotariado.gov.co/services/bachue/cb/tramitesencurso/consultarTramites/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad nir. */
	/* Número de Identificación Registral del trámite
	 *                                 en curso */
	private java.lang.String NIR;
	
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
	 *                                 en qeue la consulta no fue exitosa, vacío cuando la consulta
	 * es
	 *                                 exitosa */
	private java.lang.String descripcionMensaje;

	/** Propiedad fecha hora NIR. */
	/* Fecha de Solicitud */
	private java.util.Calendar fechaHoraNIR;

	/** Propiedad fecha radicacion. */
	/* Fecha en la que se asignó el turno */
	private java.util.Calendar fechaRadicacion;

	/** Propiedad nom municipio. */
	/* Ciudad de la ORIP que resuelve el trámite */
	private java.lang.String nomMunicipio;

	/** Propiedad oficina origen. */
	/* Entidad donde se radicó el trámite en curso */
	private java.lang.String oficinaOrigen;

	/** Propiedad radicacion. */
	/* Número de turno o radicado del trámite en
	 *                                 curso. */
	private java.lang.String                                                                                  radicacion;
	
	/** Propiedad tipos acto. */
	private co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoTiposActo[] tiposActo;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                           __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida datos tramites.
	 */
	public TipoSalidaDatosTramites()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida datos tramites.
	 *
	 * @param NIR de nir
	 * @param fechaHoraNIR de fecha hora NIR
	 * @param radicacion de radicacion
	 * @param fechaRadicacion de fecha radicacion
	 * @param tiposActo de tipos acto
	 * @param oficinaOrigen de oficina origen
	 * @param nomMunicipio de nom municipio
	 * @param codMensaje de cod mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaDatosTramites(
	    java.lang.String NIR, java.util.Calendar fechaHoraNIR, java.lang.String radicacion,
	    java.util.Calendar fechaRadicacion,
	    co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoTiposActo[] tiposActo,
	    java.lang.String oficinaOrigen, java.lang.String nomMunicipio, java.math.BigInteger codMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.NIR                    = NIR;
		this.fechaHoraNIR           = fechaHoraNIR;
		this.radicacion             = radicacion;
		this.fechaRadicacion        = fechaRadicacion;
		this.tiposActo              = tiposActo;
		this.oficinaOrigen          = oficinaOrigen;
		this.nomMunicipio           = nomMunicipio;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
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
	 * Return type metadata object.
	 *
	 * @return el valor de type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaDatosTramites.
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
	 * Gets the codMensaje value for this TipoSalidaDatosTramites.
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
	 * Sets the descripcionMensaje value for this TipoSalidaDatosTramites.
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
	 * Gets the descripcionMensaje value for this TipoSalidaDatosTramites.
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
	 * Sets the fechaHoraNIR value for this TipoSalidaDatosTramites.
	 *
	 * @param fechaHoraNIR   * Fecha de Solicitud
	 */
	public void setFechaHoraNIR(java.util.Calendar fechaHoraNIR)
	{
		this.fechaHoraNIR = fechaHoraNIR;
	}

	/**
	 * Gets the fechaHoraNIR value for this TipoSalidaDatosTramites.
	 *
	 * @return fechaHoraNIR   * Fecha de Solicitud
	 */
	public java.util.Calendar getFechaHoraNIR()
	{
		return fechaHoraNIR;
	}

	/**
	 * Sets the fechaRadicacion value for this TipoSalidaDatosTramites.
	 *
	 * @param fechaRadicacion   * Fecha en la que se asignó el turno
	 */
	public void setFechaRadicacion(java.util.Calendar fechaRadicacion)
	{
		this.fechaRadicacion = fechaRadicacion;
	}

	/**
	 * Gets the fechaRadicacion value for this TipoSalidaDatosTramites.
	 *
	 * @return fechaRadicacion   * Fecha en la que se asignó el turno
	 */
	public java.util.Calendar getFechaRadicacion()
	{
		return fechaRadicacion;
	}

	/**
	 * Sets the NIR value for this TipoSalidaDatosTramites.
	 *
	 * @param NIR   * Número de Identificación Registral del trámite
	     *                                 en curso
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/**
	 * Gets the NIR value for this TipoSalidaDatosTramites.
	 *
	 * @return NIR   * Número de Identificación Registral del trámite
	     *                                 en curso
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the nomMunicipio value for this TipoSalidaDatosTramites.
	 *
	 * @param nomMunicipio   * Ciudad de la ORIP que resuelve el trámite
	 */
	public void setNomMunicipio(java.lang.String nomMunicipio)
	{
		this.nomMunicipio = nomMunicipio;
	}

	/**
	 * Gets the nomMunicipio value for this TipoSalidaDatosTramites.
	 *
	 * @return nomMunicipio   * Ciudad de la ORIP que resuelve el trámite
	 */
	public java.lang.String getNomMunicipio()
	{
		return nomMunicipio;
	}

	/**
	 * Sets the oficinaOrigen value for this TipoSalidaDatosTramites.
	 *
	 * @param oficinaOrigen   * Entidad donde se radicó el trámite en curso
	 */
	public void setOficinaOrigen(java.lang.String oficinaOrigen)
	{
		this.oficinaOrigen = oficinaOrigen;
	}

	/**
	 * Gets the oficinaOrigen value for this TipoSalidaDatosTramites.
	 *
	 * @return oficinaOrigen   * Entidad donde se radicó el trámite en curso
	 */
	public java.lang.String getOficinaOrigen()
	{
		return oficinaOrigen;
	}

	/**
	 * Sets the radicacion value for this TipoSalidaDatosTramites.
	 *
	 * @param radicacion   * Número de turno o radicado del trámite en
	     *                                 curso.
	 */
	public void setRadicacion(java.lang.String radicacion)
	{
		this.radicacion = radicacion;
	}

	/**
	 * Gets the radicacion value for this TipoSalidaDatosTramites.
	 *
	 * @return radicacion   * Número de turno o radicado del trámite en
	     *                                 curso.
	 */
	public java.lang.String getRadicacion()
	{
		return radicacion;
	}

	/**
	 * Sets the tiposActo value for this TipoSalidaDatosTramites.
	 *
	 * @param tiposActo de tipos acto
	 */
	public void setTiposActo(
	    co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoTiposActo[] tiposActo
	)
	{
		this.tiposActo = tiposActo;
	}

	/**
	 * Cambia el valor de tipos acto.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setTiposActo(
	    int i, co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoTiposActo _value
	)
	{
		this.tiposActo[i] = _value;
	}

	/**
	 * Gets the tiposActo value for this TipoSalidaDatosTramites.
	 *
	 * @return tiposActo
	 */
	public co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoTiposActo[] getTiposActo()
	{
		return tiposActo;
	}

	/**
	 * Retorna Objeto o variable de valor tipos acto.
	 *
	 * @param i de i
	 * @return el valor de tipos acto
	 */
	public co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoTiposActo getTiposActo(
	    int i
	)
	{
		return this.tiposActo[i];
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaDatosTramites))
			return false;

		TipoSalidaDatosTramites other = (TipoSalidaDatosTramites)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())))
				&& (((this.fechaHoraNIR == null) && (other.getFechaHoraNIR() == null))
				|| ((this.fechaHoraNIR != null) && this.fechaHoraNIR.equals(other.getFechaHoraNIR())))
				&& (((this.radicacion == null) && (other.getRadicacion() == null))
				|| ((this.radicacion != null) && this.radicacion.equals(other.getRadicacion())))
				&& (((this.fechaRadicacion == null) && (other.getFechaRadicacion() == null))
				|| ((this.fechaRadicacion != null) && this.fechaRadicacion.equals(other.getFechaRadicacion())))
				&& (((this.tiposActo == null) && (other.getTiposActo() == null))
				|| ((this.tiposActo != null) && java.util.Arrays.equals(this.tiposActo, other.getTiposActo())))
				&& (((this.oficinaOrigen == null) && (other.getOficinaOrigen() == null))
				|| ((this.oficinaOrigen != null) && this.oficinaOrigen.equals(other.getOficinaOrigen())))
				&& (((this.nomMunicipio == null) && (other.getNomMunicipio() == null))
				|| ((this.nomMunicipio != null) && this.nomMunicipio.equals(other.getNomMunicipio())))
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

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

		if(getFechaHoraNIR() != null)
			_hashCode += getFechaHoraNIR().hashCode();

		if(getRadicacion() != null)
			_hashCode += getRadicacion().hashCode();

		if(getFechaRadicacion() != null)
			_hashCode += getFechaRadicacion().hashCode();

		if(getTiposActo() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getTiposActo()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getTiposActo(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getOficinaOrigen() != null)
			_hashCode += getOficinaOrigen().hashCode();

		if(getNomMunicipio() != null)
			_hashCode += getNomMunicipio().hashCode();

		if(getCodMensaje() != null)
			_hashCode += getCodMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
