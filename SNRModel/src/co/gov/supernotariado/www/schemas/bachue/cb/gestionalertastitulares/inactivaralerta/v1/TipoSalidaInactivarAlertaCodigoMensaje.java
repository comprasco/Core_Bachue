/**
 * TipoSalidaInactivarAlertaCodigoMensaje.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaInactivarAlertaCodigoMensaje.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaInactivarAlertaCodigoMensaje implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5781002821982589247L;
	
	/** Propiedad table. */
	private static java.util.HashMap                           _table_ = new java.util.HashMap();
	
	/** Constante _value1. */
	public static final java.lang.String                       _value1 = "200";
	
	/** Constante _value2. */
	public static final java.lang.String                       _value2 = "453";
	
	/** Constante _value3. */
	public static final java.lang.String                       _value3 = "454";
	
	/** Constante _value4. */
	public static final java.lang.String                       _value4 = "455";
	
	/** Constante _value5. */
	public static final java.lang.String                       _value5 = "456";
	
	/** Constante _value6. */
	public static final java.lang.String                       _value6 = "461";
	
	/** Constante _value7. */
	public static final java.lang.String                       _value7 = "469";
	
	/** Constante _value8. */
	public static final java.lang.String                       _value8 = "500";
	
	/** Constante value1. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value1  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value1
		);
	
	/** Constante value2. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value2  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value2
		);
	
	/** Constante value3. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value3  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value3
		);
	
	/** Constante value4. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value4  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value4
		);
	
	/** Constante value5. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value5  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value5
		);
	
	/** Constante value6. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value6  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value6
		);
	
	/** Constante value7. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value7  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value7
		);
	
	/** Constante value8. */
	public static final TipoSalidaInactivarAlertaCodigoMensaje value8  = new TipoSalidaInactivarAlertaCodigoMensaje(
		    _value8
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaInactivarAlertaCodigoMensaje.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        ">tipoSalidaInactivarAlerta>codigoMensaje"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo salida inactivar alerta codigo mensaje.
	 *
	 * @param value de value
	 */
	// Constructor
	protected TipoSalidaInactivarAlertaCodigoMensaje(java.lang.String value)
	{
		_value_ = value;
		_table_.put(_value_, this);
	}

	/**
	 * Retorna Objeto o variable de valor deserializer.
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
		return new org.apache.axis.encoding.ser.EnumDeserializer(_javaType, _xmlType);
	}

	/**
	 * Retorna Objeto o variable de valor serializer.
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
	 * @param value de value
	 * @return el valor de tipo salida inactivar alerta codigo mensaje
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoSalidaInactivarAlertaCodigoMensaje fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	/**
	 * From value.
	 *
	 * @param value de value
	 * @return el valor de tipo salida inactivar alerta codigo mensaje
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoSalidaInactivarAlertaCodigoMensaje fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoSalidaInactivarAlertaCodigoMensaje enumeration = (TipoSalidaInactivarAlertaCodigoMensaje)_table_.get(value);

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
