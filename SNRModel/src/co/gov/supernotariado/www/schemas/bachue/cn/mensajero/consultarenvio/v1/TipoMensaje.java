/**
 * TipoSalidaConsultarEnvioListaMensajesMensaje.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarEnvioListaMensajesMensaje.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2020
 */
public class TipoMensaje implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6394767958498314984L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoMensaje.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        ">>tipoSalidaConsultarEnvio>listaMensajes>mensaje"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "turno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orip");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "orip"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificadorEE");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "identificadorEE"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaEnvio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "fechaEnvio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("asunto");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "asunto"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cuerpo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "cuerpo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("para");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "para"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("documentos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "documentos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        ">>>>tipoSalidaConsultarEnvio>listaMensajes>mensaje>documentos>documento"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "documento"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad asunto. */
	private java.lang.String asunto;

	/** Propiedad cuerpo. */
	private java.lang.String cuerpo;

	/** Propiedad fecha envio. */
	private java.util.Date fechaEnvio;

	/** Propiedad identificador EE. */
	private java.lang.String identificadorEE;

	/** Propiedad nir. */
	private java.lang.String nir;

	/** Propiedad orip. */
	private java.lang.String orip;

	/** Propiedad para. */
	private java.lang.String para;

	/** Propiedad turno. */
	private java.lang.String turno;

	/** Propiedad documentos. */
	private co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoDocumentos[] documentos;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar envio lista mensajes mensaje.
	 */
	public TipoMensaje()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar envio lista mensajes mensaje.
	 *
	 * @param nir correspondiente al valor de nir
	 * @param turno correspondiente al valor de turno
	 * @param orip correspondiente al valor de orip
	 * @param identificadorEE correspondiente al valor de identificador EE
	 * @param fechaEnvio correspondiente al valor de fecha envio
	 * @param asunto correspondiente al valor de asunto
	 * @param cuerpo correspondiente al valor de cuerpo
	 * @param para correspondiente al valor de para
	 * @param documentos correspondiente al valor de documentos
	 */
	public TipoMensaje(
	    java.lang.String nir, java.lang.String turno, java.lang.String orip, java.lang.String identificadorEE,
	    java.util.Date fechaEnvio, java.lang.String asunto, java.lang.String cuerpo, java.lang.String para,
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoDocumentos[] documentos
	)
	{
		this.nir                 = nir;
		this.turno               = turno;
		this.orip                = orip;
		this.identificadorEE     = identificadorEE;
		this.fechaEnvio          = fechaEnvio;
		this.asunto              = asunto;
		this.cuerpo              = cuerpo;
		this.para                = para;
		this.documentos          = documentos;
	}

	/**
	 * Sets the asunto value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param asunto correspondiente al valor de asunto
	 */
	public void setAsunto(java.lang.String asunto)
	{
		this.asunto = asunto;
	}

	/**
	 * Gets the asunto value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return asunto
	 */
	public java.lang.String getAsunto()
	{
		return asunto;
	}

	/**
	 * Sets the cuerpo value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param cuerpo correspondiente al valor de cuerpo
	 */
	public void setCuerpo(java.lang.String cuerpo)
	{
		this.cuerpo = cuerpo;
	}

	/**
	 * Gets the cuerpo value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return cuerpo
	 */
	public java.lang.String getCuerpo()
	{
		return cuerpo;
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
	 * Sets the documentos value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param documentos correspondiente al valor de documentos
	 */
	public void setDocumentos(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoDocumentos[] documentos
	)
	{
		this.documentos = documentos;
	}

	/**
	 * Gets the documentos value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return documentos
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoDocumentos[] getDocumentos()
	{
		return documentos;
	}

	/**
	 * Sets the fechaEnvio value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param fechaEnvio correspondiente al valor de fecha envio
	 */
	public void setFechaEnvio(java.util.Date fechaEnvio)
	{
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * Gets the fechaEnvio value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return fechaEnvio
	 */
	public java.util.Date getFechaEnvio()
	{
		return fechaEnvio;
	}

	/**
	 * Sets the identificadorEE value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param identificadorEE correspondiente al valor de identificador EE
	 */
	public void setIdentificadorEE(java.lang.String identificadorEE)
	{
		this.identificadorEE = identificadorEE;
	}

	/**
	 * Gets the identificadorEE value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return identificadorEE
	 */
	public java.lang.String getIdentificadorEE()
	{
		return identificadorEE;
	}

	/**
	 * Sets the nir value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param nir correspondiente al valor de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
	}

	/**
	 * Sets the orip value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param orip correspondiente al valor de orip
	 */
	public void setOrip(java.lang.String orip)
	{
		this.orip = orip;
	}

	/**
	 * Gets the orip value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return orip
	 */
	public java.lang.String getOrip()
	{
		return orip;
	}

	/**
	 * Sets the para value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param para correspondiente al valor de para
	 */
	public void setPara(java.lang.String para)
	{
		this.para = para;
	}

	/**
	 * Gets the para value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return para
	 */
	public java.lang.String getPara()
	{
		return para;
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
	 * Sets the turno value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @param turno correspondiente al valor de turno
	 */
	public void setTurno(java.lang.String turno)
	{
		this.turno = turno;
	}

	/**
	 * Gets the turno value for this TipoSalidaConsultarEnvioListaMensajesMensaje.
	 *
	 * @return turno
	 */
	public java.lang.String getTurno()
	{
		return turno;
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
		if(!(obj instanceof TipoMensaje))
			return false;

		TipoMensaje other = (TipoMensaje)obj;

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
				&& (((this.turno == null) && (other.getTurno() == null))
				|| ((this.turno != null) && this.turno.equals(other.getTurno())))
				&& (((this.orip == null) && (other.getOrip() == null))
				|| ((this.orip != null) && this.orip.equals(other.getOrip())))
				&& (((this.identificadorEE == null) && (other.getIdentificadorEE() == null))
				|| ((this.identificadorEE != null) && this.identificadorEE.equals(other.getIdentificadorEE())))
				&& (((this.fechaEnvio == null) && (other.getFechaEnvio() == null))
				|| ((this.fechaEnvio != null) && this.fechaEnvio.equals(other.getFechaEnvio())))
				&& (((this.asunto == null) && (other.getAsunto() == null))
				|| ((this.asunto != null) && this.asunto.equals(other.getAsunto())))
				&& (((this.cuerpo == null) && (other.getCuerpo() == null))
				|| ((this.cuerpo != null) && this.cuerpo.equals(other.getCuerpo())))
				&& (((this.para == null) && (other.getPara() == null))
				|| ((this.para != null) && this.para.equals(other.getPara())))
				&& (((this.documentos == null) && (other.getDocumentos() == null))
				|| ((this.documentos != null) && java.util.Arrays.equals(this.documentos, other.getDocumentos())));
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

		if(getTurno() != null)
			_hashCode += getTurno().hashCode();

		if(getOrip() != null)
			_hashCode += getOrip().hashCode();

		if(getIdentificadorEE() != null)
			_hashCode += getIdentificadorEE().hashCode();

		if(getFechaEnvio() != null)
			_hashCode += getFechaEnvio().hashCode();

		if(getAsunto() != null)
			_hashCode += getAsunto().hashCode();

		if(getCuerpo() != null)
			_hashCode += getCuerpo().hashCode();

		if(getPara() != null)
			_hashCode += getPara().hashCode();

		if(getDocumentos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDocumentos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDocumentos(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
