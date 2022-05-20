/**
 * TipoSalidaConsultarAlertas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarAlertas.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaConsultarAlertas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6817211891467327947L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarAlertas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        "tipoSalidaConsultarAlertas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "codigo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "mensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaAlertas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "listaAlertas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1",
		        ">>tipoSalidaConsultarAlertas>listaAlertas>alerta"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarAlertas/v1", "alerta"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo. */
	private java.lang.String codigo;

	/** Propiedad mensaje. */
	private java.lang.String mensaje;

	/** Propiedad lista alertas. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertasListaAlertasAlerta[] listaAlertas;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar alertas.
	 */
	public TipoSalidaConsultarAlertas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar alertas.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param listaAlertas de lista alertas
	 */
	public TipoSalidaConsultarAlertas(
	    java.lang.String codigo, java.lang.String mensaje,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertasListaAlertasAlerta[] listaAlertas
	)
	{
		this.codigo           = codigo;
		this.mensaje          = mensaje;
		this.listaAlertas     = listaAlertas;
	}

	/**
	 * Gets the codigo value for this TipoSalidaConsultarAlertas.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the codigo value for this TipoSalidaConsultarAlertas.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje value for this TipoSalidaConsultarAlertas.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
	}

	/**
	 * Sets the mensaje value for this TipoSalidaConsultarAlertas.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the listaAlertas value for this TipoSalidaConsultarAlertas.
	 *
	 * @return listaAlertas
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertasListaAlertasAlerta[] getListaAlertas()
	{
		return listaAlertas;
	}

	/**
	 * Sets the listaAlertas value for this TipoSalidaConsultarAlertas.
	 *
	 * @param listaAlertas de lista alertas
	 */
	public void setListaAlertas(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarAlertas.v1.TipoSalidaConsultarAlertasListaAlertasAlerta[] listaAlertas
	)
	{
		this.listaAlertas = listaAlertas;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarAlertas))
			return false;

		TipoSalidaConsultarAlertas other = (TipoSalidaConsultarAlertas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.mensaje == null) && (other.getMensaje() == null))
				|| ((this.mensaje != null) && this.mensaje.equals(other.getMensaje())))
				&& (((this.listaAlertas == null) && (other.getListaAlertas() == null))
				|| ((this.listaAlertas != null) && java.util.Arrays.equals(this.listaAlertas, other.getListaAlertas())));
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

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getMensaje() != null)
			_hashCode += getMensaje().hashCode();

		if(getListaAlertas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaAlertas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaAlertas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

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
