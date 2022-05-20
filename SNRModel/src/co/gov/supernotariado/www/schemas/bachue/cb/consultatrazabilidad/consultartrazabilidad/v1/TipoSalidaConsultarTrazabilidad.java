/**
 * TipoSalidaConsultarTrazabilidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1;


/**
 * Salida de la
 *                         consultar trazabilidadad usada desde Sede electrónica.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/09/2019
 */
public class TipoSalidaConsultarTrazabilidad implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -9040790948189487757L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarTrazabilidad.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoSalidaConsultarTrazabilidad"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("faseActualNIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "faseActualNIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nirVinculado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "nirVinculado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tramiteVinculado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tramiteVinculado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("elementosnir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "elementosnir"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "tipoElementoNir"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "elementonir"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        ">tipoSalidaConsultarTrazabilidad>codigoMensaje"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo mensaje. */
	/* Código que representa el mensaje de salida */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidadCodigoMensaje codigoMensaje;

	/** Propiedad descripcion mensaje. */
	/* Descripción del mensaje de salida. Vacío si no
	 *                                 hubo error. */
	private java.lang.String descripcionMensaje;

	/** Propiedad fase actual NIR. */
	/* Fase actual de la solicitud */
	private java.lang.String faseActualNIR;

	/** Propiedad nir. */
	/* Número de identificación registral asociado a
	 *                                 la solicitud */
	private java.lang.String nir;

	/** Propiedad nir vinculado. */
	/* Número de identificación registral asociado a
	 *                                 la solicitud */
	private java.lang.String nirVinculado;

	/** Propiedad tramite vinculado. */
	/* Circunstancia dentro del proceso */
	private java.lang.String tramiteVinculado;

	/** Propiedad elementosnir. */
	/* Lista detallada de NIRs */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoElementoNir[] elementosnir;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar trazabilidad.
	 */
	public TipoSalidaConsultarTrazabilidad()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar trazabilidad.
	 *
	 * @param nir correspondiente al valor de nir
	 * @param faseActualNIR correspondiente al valor de fase actual NIR
	 * @param nirVinculado correspondiente al valor de nir vinculado
	 * @param tramiteVinculado correspondiente al valor de tramite vinculado
	 * @param elementosnir correspondiente al valor de elementosnir
	 * @param codigoMensaje correspondiente al valor de codigo mensaje
	 * @param descripcionMensaje correspondiente al valor de descripcion mensaje
	 */
	public TipoSalidaConsultarTrazabilidad(
	    java.lang.String nir, java.lang.String faseActualNIR, java.lang.String nirVinculado,
	    java.lang.String tramiteVinculado,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoElementoNir[] elementosnir,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidadCodigoMensaje codigoMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.nir                    = nir;
		this.faseActualNIR          = faseActualNIR;
		this.nirVinculado           = nirVinculado;
		this.tramiteVinculado       = tramiteVinculado;
		this.elementosnir           = elementosnir;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @param codigoMensaje   * Código que representa el mensaje de salida
	 */
	public void setCodigoMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidadCodigoMensaje codigoMensaje
	)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @return codigoMensaje   * Código que representa el mensaje de salida
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoSalidaConsultarTrazabilidadCodigoMensaje getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @param descripcionMensaje   * Descripción del mensaje de salida. Vacío si no
	     *                                 hubo error.
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @return descripcionMensaje   * Descripción del mensaje de salida. Vacío si no
	     *                                 hubo error.
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
	 * Sets the elementosnir value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @param elementosnir   * Lista detallada de NIRs
	 */
	public void setElementosnir(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoElementoNir[] elementosnir
	)
	{
		this.elementosnir = elementosnir;
	}

	/**
	 * Gets the elementosnir value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @return elementosnir   * Lista detallada de NIRs
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoElementoNir[] getElementosnir()
	{
		return elementosnir;
	}

	/**
	 * Sets the faseActualNIR value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @param faseActualNIR   * Fase actual de la solicitud
	 */
	public void setFaseActualNIR(java.lang.String faseActualNIR)
	{
		this.faseActualNIR = faseActualNIR;
	}

	/**
	 * Gets the faseActualNIR value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @return faseActualNIR   * Fase actual de la solicitud
	 */
	public java.lang.String getFaseActualNIR()
	{
		return faseActualNIR;
	}

	/**
	 * Sets the nir value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @param nir   * Número de identificación registral asociado a
	     *                                 la solicitud
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @return nir   * Número de identificación registral asociado a
	     *                                 la solicitud
	 */
	public java.lang.String getNir()
	{
		return nir;
	}

	/**
	 * Sets the nirVinculado value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @param nirVinculado   * Número de identificación registral asociado a
	     *                                 la solicitud
	 */
	public void setNirVinculado(java.lang.String nirVinculado)
	{
		this.nirVinculado = nirVinculado;
	}

	/**
	 * Gets the nirVinculado value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @return nirVinculado   * Número de identificación registral asociado a
	     *                                 la solicitud
	 */
	public java.lang.String getNirVinculado()
	{
		return nirVinculado;
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
	 * Sets the tramiteVinculado value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @param tramiteVinculado   * Circunstancia dentro del proceso
	 */
	public void setTramiteVinculado(java.lang.String tramiteVinculado)
	{
		this.tramiteVinculado = tramiteVinculado;
	}

	/**
	 * Gets the tramiteVinculado value for this TipoSalidaConsultarTrazabilidad.
	 *
	 * @return tramiteVinculado   * Circunstancia dentro del proceso
	 */
	public java.lang.String getTramiteVinculado()
	{
		return tramiteVinculado;
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarTrazabilidad))
			return false;

		TipoSalidaConsultarTrazabilidad other = (TipoSalidaConsultarTrazabilidad)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.faseActualNIR == null) && (other.getFaseActualNIR() == null))
				|| ((this.faseActualNIR != null) && this.faseActualNIR.equals(other.getFaseActualNIR())))
				&& (((this.nirVinculado == null) && (other.getNirVinculado() == null))
				|| ((this.nirVinculado != null) && this.nirVinculado.equals(other.getNirVinculado())))
				&& (((this.tramiteVinculado == null) && (other.getTramiteVinculado() == null))
				|| ((this.tramiteVinculado != null) && this.tramiteVinculado.equals(other.getTramiteVinculado())))
				&& (((this.elementosnir == null) && (other.getElementosnir() == null))
				|| ((this.elementosnir != null) && java.util.Arrays.equals(this.elementosnir, other.getElementosnir())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
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

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getFaseActualNIR() != null)
			_hashCode += getFaseActualNIR().hashCode();

		if(getNirVinculado() != null)
			_hashCode += getNirVinculado().hashCode();

		if(getTramiteVinculado() != null)
			_hashCode += getTramiteVinculado().hashCode();

		if(getElementosnir() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getElementosnir()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getElementosnir(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
