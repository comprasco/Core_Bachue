/**
 * TipoSalidaConsultarEnvio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarEnvio.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2020
 */
public class TipoSalidaConsultarEnvio implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6291957077548107431L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarEnvio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        "tipoSalidaConsultarEnvio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroRegistros");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "numeroRegistros"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaMensajes");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "listaMensajes"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1",
		        ">>tipoSalidaConsultarEnvio>listaMensajes>mensaje"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "mensaje"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "codMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/consultarenvio/v1", "descripcionMensaje"
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
	private java.math.BigInteger codMensaje;

	/** Propiedad descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** Propiedad numero registros. */
	private java.math.BigInteger numeroRegistros;

	/** Propiedad lista mensajes. */
	private co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoMensaje[] listaMensajes;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar envio.
	 */
	public TipoSalidaConsultarEnvio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar envio.
	 *
	 * @param numeroRegistros correspondiente al valor de numero registros
	 * @param listaMensajes correspondiente al valor de lista mensajes
	 * @param codMensaje correspondiente al valor de cod mensaje
	 * @param descripcionMensaje correspondiente al valor de descripcion mensaje
	 */
	public TipoSalidaConsultarEnvio(
	    java.math.BigInteger numeroRegistros,
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoMensaje[] listaMensajes,
	    java.math.BigInteger codMensaje, java.lang.String descripcionMensaje
	)
	{
		this.numeroRegistros        = numeroRegistros;
		this.listaMensajes          = listaMensajes;
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar envio.
	 *
	 * @param codMensaje correspondiente al valor de cod mensaje
	 * @param descripcionMensaje correspondiente al valor de descripcion mensaje
	 */
	public TipoSalidaConsultarEnvio(java.math.BigInteger codMensaje, java.lang.String descripcionMensaje)
	{
		this.codMensaje             = codMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the codMensaje value for this TipoSalidaConsultarEnvio.
	 *
	 * @param codMensaje correspondiente al valor de cod mensaje
	 */
	public void setCodMensaje(java.math.BigInteger codMensaje)
	{
		this.codMensaje = codMensaje;
	}

	/**
	 * Gets the codMensaje value for this TipoSalidaConsultarEnvio.
	 *
	 * @return codMensaje
	 */
	public java.math.BigInteger getCodMensaje()
	{
		return codMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarEnvio.
	 *
	 * @param descripcionMensaje correspondiente al valor de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarEnvio.
	 *
	 * @return descripcionMensaje
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
	 * Sets the listaMensajes value for this TipoSalidaConsultarEnvio.
	 *
	 * @param listaMensajes correspondiente al valor de lista mensajes
	 */
	public void setListaMensajes(
	    co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoMensaje[] listaMensajes
	)
	{
		this.listaMensajes = listaMensajes;
	}

	/**
	 * Gets the listaMensajes value for this TipoSalidaConsultarEnvio.
	 *
	 * @return listaMensajes
	 */
	public co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoMensaje[] getListaMensajes()
	{
		return listaMensajes;
	}

	/**
	 * Sets the numeroRegistros value for this TipoSalidaConsultarEnvio.
	 *
	 * @param numeroRegistros correspondiente al valor de numero registros
	 */
	public void setNumeroRegistros(java.math.BigInteger numeroRegistros)
	{
		this.numeroRegistros = numeroRegistros;
	}

	/**
	 * Gets the numeroRegistros value for this TipoSalidaConsultarEnvio.
	 *
	 * @return numeroRegistros
	 */
	public java.math.BigInteger getNumeroRegistros()
	{
		return numeroRegistros;
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarEnvio))
			return false;

		TipoSalidaConsultarEnvio other = (TipoSalidaConsultarEnvio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numeroRegistros == null) && (other.getNumeroRegistros() == null))
				|| ((this.numeroRegistros != null) && this.numeroRegistros.equals(other.getNumeroRegistros())))
				&& (((this.listaMensajes == null) && (other.getListaMensajes() == null))
				|| ((this.listaMensajes != null)
				&& java.util.Arrays.equals(this.listaMensajes, other.getListaMensajes())))
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

		if(getNumeroRegistros() != null)
			_hashCode += getNumeroRegistros().hashCode();

		if(getListaMensajes() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaMensajes()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaMensajes(), i);

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
}
