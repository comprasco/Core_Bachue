/**
 * TipoSalidaConsultarTrazabilidadCodigoMensaje.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarTrazabilidadCodigoMensaje.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/09/2019
 */
public class TipoSalidaConsultarTrazabilidadCodigoMensaje implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3822191175662659540L;

	/** Propiedad table. */
	private static java.util.HashMap _table_ = new java.util.HashMap();

	/** Constante _value1. */
	public static final java.lang.String _value1 = "200";

	/** Constante _value2. */
	public static final java.lang.String _value2 = "409";

	/** Constante value1. */
	public static final TipoSalidaConsultarTrazabilidadCodigoMensaje value1 = new TipoSalidaConsultarTrazabilidadCodigoMensaje(
		    _value1
		);

	/** Constante value2. */
	public static final TipoSalidaConsultarTrazabilidadCodigoMensaje value2 = new TipoSalidaConsultarTrazabilidadCodigoMensaje(
		    _value2
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarTrazabilidadCodigoMensaje.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultartrazabilidad/v1",
		        ">tipoSalidaConsultarTrazabilidad>codigoMensaje"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo salida consultar trazabilidad codigo mensaje.
	 *
	 * @param value correspondiente al valor de value
	 */

	// Constructor
	protected TipoSalidaConsultarTrazabilidadCodigoMensaje(java.lang.String value)
	{
		_value_ = value;
		_table_.put(_value_, this);
	}

	/**
	 * Retorna Objeto o variable de valor deserializer.
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
		return new org.apache.axis.encoding.ser.EnumDeserializer(_javaType, _xmlType);
	}

	/**
	 * Retorna Objeto o variable de valor serializer.
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
		return new org.apache.axis.encoding.ser.EnumSerializer(_javaType, _xmlType);
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
	 * Retorna Objeto o variable de valor value.
	 *
	 * @return el valor de value
	 */
	public java.lang.String getValue()
	{
		return _value_;
	}

	/**
	 * From string.
	 *
	 * @param value correspondiente al valor de value
	 * @return el valor de tipo salida consultar trazabilidad codigo mensaje
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoSalidaConsultarTrazabilidadCodigoMensaje fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	/**
	 * From value.
	 *
	 * @param value correspondiente al valor de value
	 * @return el valor de tipo salida consultar trazabilidad codigo mensaje
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoSalidaConsultarTrazabilidadCodigoMensaje fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoSalidaConsultarTrazabilidadCodigoMensaje enumeration = (TipoSalidaConsultarTrazabilidadCodigoMensaje)_table_
				.get(value);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	/** {@inheritdoc} */
	public boolean equals(java.lang.Object obj)
	{
		return (obj == this);
	}

	/** {@inheritdoc} */
	public int hashCode()
	{
		return toString().hashCode();
	}

	/**
	 * Read resolve.
	 *
	 * @return el valor de java.lang. object
	 * @throws ObjectStreamException cuando se produce algun error en el proceso
	 */
	public java.lang.Object readResolve()
	    throws java.io.ObjectStreamException
	{
		return fromValue(_value_);
	}

	/** {@inheritdoc} */
	public java.lang.String toString()
	{
		return _value_;
	}
}
