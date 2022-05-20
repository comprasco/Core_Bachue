/**
 * TipoEntradaConsultarEnvio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarEnvio.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2020
 */
public class TipoEntradaConsultarEnvio implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3977332010841236729L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarEnvio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        "tipoEntradaConsultarEnvio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaInicial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "fechaInicial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaFinal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "fechaFinal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoID");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "tipoID"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificador");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "identificador"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("pagina");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "pagina"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionCorreoElectronicoDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        "direccionCorreoElectronicoDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroTelefonoDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        "numeroTelefonoDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad direccion correo electronico destinatario. */
	private java.lang.String direccionCorreoElectronicoDestinatario;

	/** Propiedad fecha final. */
	private java.util.Calendar fechaFinal;

	/** Propiedad fecha inicial. */
	private java.util.Calendar fechaInicial;

	/** Propiedad identificador. */
	private java.lang.String identificador;

	/** Propiedad numero telefono destinatario. */
	private java.math.BigInteger numeroTelefonoDestinatario;

	/** Propiedad pagina. */
	private java.lang.String pagina;

	/** Propiedad tipo ID. */
	private java.lang.String tipoID;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar envio.
	 */
	public TipoEntradaConsultarEnvio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar envio.
	 *
	 * @param fechaInicial correspondiente al valor de fecha inicial
	 * @param fechaFinal correspondiente al valor de fecha final
	 * @param tipoID correspondiente al valor de tipo ID
	 * @param identificador correspondiente al valor de identificador
	 * @param pagina correspondiente al valor de pagina
	 * @param direccionCorreoElectronicoDestinatario correspondiente al valor de direccion correo electronico destinatario
	 * @param numeroTelefonoDestinatario correspondiente al valor de numero telefono destinatario
	 */
	public TipoEntradaConsultarEnvio(
	    java.util.Calendar fechaInicial, java.util.Calendar fechaFinal, java.lang.String tipoID,
	    java.lang.String identificador, java.lang.String pagina, java.lang.String direccionCorreoElectronicoDestinatario,
	    java.math.BigInteger numeroTelefonoDestinatario
	)
	{
		this.fechaInicial                               = fechaInicial;
		this.fechaFinal                                 = fechaFinal;
		this.tipoID                                     = tipoID;
		this.identificador                              = identificador;
		this.pagina                                     = pagina;
		this.direccionCorreoElectronicoDestinatario     = direccionCorreoElectronicoDestinatario;
		this.numeroTelefonoDestinatario                 = numeroTelefonoDestinatario;
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
	 * Sets the direccionCorreoElectronicoDestinatario value for this TipoEntradaConsultarEnvio.
	 *
	 * @param direccionCorreoElectronicoDestinatario correspondiente al valor de direccion correo electronico destinatario
	 */
	public void setDireccionCorreoElectronicoDestinatario(java.lang.String direccionCorreoElectronicoDestinatario)
	{
		this.direccionCorreoElectronicoDestinatario = direccionCorreoElectronicoDestinatario;
	}

	/**
	 * Gets the direccionCorreoElectronicoDestinatario value for this TipoEntradaConsultarEnvio.
	 *
	 * @return direccionCorreoElectronicoDestinatario
	 */
	public java.lang.String getDireccionCorreoElectronicoDestinatario()
	{
		return direccionCorreoElectronicoDestinatario;
	}

	/**
	 * Sets the fechaFinal value for this TipoEntradaConsultarEnvio.
	 *
	 * @param fechaFinal correspondiente al valor de fecha final
	 */
	public void setFechaFinal(java.util.Calendar fechaFinal)
	{
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Gets the fechaFinal value for this TipoEntradaConsultarEnvio.
	 *
	 * @return fechaFinal
	 */
	public java.util.Calendar getFechaFinal()
	{
		return fechaFinal;
	}

	/**
	 * Sets the fechaInicial value for this TipoEntradaConsultarEnvio.
	 *
	 * @param fechaInicial correspondiente al valor de fecha inicial
	 */
	public void setFechaInicial(java.util.Calendar fechaInicial)
	{
		this.fechaInicial = fechaInicial;
	}

	/**
	 * Gets the fechaInicial value for this TipoEntradaConsultarEnvio.
	 *
	 * @return fechaInicial
	 */
	public java.util.Calendar getFechaInicial()
	{
		return fechaInicial;
	}

	/**
	 * Sets the identificador value for this TipoEntradaConsultarEnvio.
	 *
	 * @param identificador correspondiente al valor de identificador
	 */
	public void setIdentificador(java.lang.String identificador)
	{
		this.identificador = identificador;
	}

	/**
	 * Gets the identificador value for this TipoEntradaConsultarEnvio.
	 *
	 * @return identificador
	 */
	public java.lang.String getIdentificador()
	{
		return identificador;
	}

	/**
	 * Sets the numeroTelefonoDestinatario value for this TipoEntradaConsultarEnvio.
	 *
	 * @param numeroTelefonoDestinatario correspondiente al valor de numero telefono destinatario
	 */
	public void setNumeroTelefonoDestinatario(java.math.BigInteger numeroTelefonoDestinatario)
	{
		this.numeroTelefonoDestinatario = numeroTelefonoDestinatario;
	}

	/**
	 * Gets the numeroTelefonoDestinatario value for this TipoEntradaConsultarEnvio.
	 *
	 * @return numeroTelefonoDestinatario
	 */
	public java.math.BigInteger getNumeroTelefonoDestinatario()
	{
		return numeroTelefonoDestinatario;
	}

	/**
	 * Sets the pagina value for this TipoEntradaConsultarEnvio.
	 *
	 * @param pagina correspondiente al valor de pagina
	 */
	public void setPagina(java.lang.String pagina)
	{
		this.pagina = pagina;
	}

	/**
	 * Gets the pagina value for this TipoEntradaConsultarEnvio.
	 *
	 * @return pagina
	 */
	public java.lang.String getPagina()
	{
		return pagina;
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
	 * Sets the tipoID value for this TipoEntradaConsultarEnvio.
	 *
	 * @param tipoID correspondiente al valor de tipo ID
	 */
	public void setTipoID(java.lang.String tipoID)
	{
		this.tipoID = tipoID;
	}

	/**
	 * Gets the tipoID value for this TipoEntradaConsultarEnvio.
	 *
	 * @return tipoID
	 */
	public java.lang.String getTipoID()
	{
		return tipoID;
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
		if(!(obj instanceof TipoEntradaConsultarEnvio))
			return false;

		TipoEntradaConsultarEnvio other = (TipoEntradaConsultarEnvio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.fechaInicial == null) && (other.getFechaInicial() == null))
				|| ((this.fechaInicial != null) && this.fechaInicial.equals(other.getFechaInicial())))
				&& (((this.fechaFinal == null) && (other.getFechaFinal() == null))
				|| ((this.fechaFinal != null) && this.fechaFinal.equals(other.getFechaFinal())))
				&& (((this.tipoID == null) && (other.getTipoID() == null))
				|| ((this.tipoID != null) && this.tipoID.equals(other.getTipoID())))
				&& (((this.identificador == null) && (other.getIdentificador() == null))
				|| ((this.identificador != null) && this.identificador.equals(other.getIdentificador())))
				&& (((this.pagina == null) && (other.getPagina() == null))
				|| ((this.pagina != null) && this.pagina.equals(other.getPagina())))
				&& (((this.direccionCorreoElectronicoDestinatario == null)
				&& (other.getDireccionCorreoElectronicoDestinatario() == null))
				|| ((this.direccionCorreoElectronicoDestinatario != null)
				&& this.direccionCorreoElectronicoDestinatario.equals(
				    other.getDireccionCorreoElectronicoDestinatario()
				)))
				&& (((this.numeroTelefonoDestinatario == null) && (other.getNumeroTelefonoDestinatario() == null))
				|| ((this.numeroTelefonoDestinatario != null)
				&& this.numeroTelefonoDestinatario.equals(other.getNumeroTelefonoDestinatario())));
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

		if(getFechaInicial() != null)
			_hashCode += getFechaInicial().hashCode();

		if(getFechaFinal() != null)
			_hashCode += getFechaFinal().hashCode();

		if(getTipoID() != null)
			_hashCode += getTipoID().hashCode();

		if(getIdentificador() != null)
			_hashCode += getIdentificador().hashCode();

		if(getPagina() != null)
			_hashCode += getPagina().hashCode();

		if(getDireccionCorreoElectronicoDestinatario() != null)
			_hashCode += getDireccionCorreoElectronicoDestinatario().hashCode();

		if(getNumeroTelefonoDestinatario() != null)
			_hashCode += getNumeroTelefonoDestinatario().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
