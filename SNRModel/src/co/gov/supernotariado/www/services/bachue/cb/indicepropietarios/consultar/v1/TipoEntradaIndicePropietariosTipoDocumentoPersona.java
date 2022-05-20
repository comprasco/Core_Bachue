/**
 * TipoEntradaIndicePropietariosTipoDocumentoPersona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaIndicePropietariosTipoDocumentoPersona.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 18/03/2020
 */
public class TipoEntradaIndicePropietariosTipoDocumentoPersona implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3513921977470201636L;

	/** Propiedad table. */
	private static java.util.HashMap _table_ = new java.util.HashMap();

	/** Constante _CC. */
	public static final java.lang.String _CC = "CC";

	/** Constante _CE. */
	public static final java.lang.String _CE = "CE";

	/** Constante _PA. */
	public static final java.lang.String _PA = "PA";

	/** Constante _TI. */
	public static final java.lang.String _TI = "TI";

	/** Constante _NUIP. */
	public static final java.lang.String _NUIP = "NUIP";

	/** Constante CC. */
	public static final TipoEntradaIndicePropietariosTipoDocumentoPersona CC = new TipoEntradaIndicePropietariosTipoDocumentoPersona(
		    _CC
		);

	/** Constante CE. */
	public static final TipoEntradaIndicePropietariosTipoDocumentoPersona CE = new TipoEntradaIndicePropietariosTipoDocumentoPersona(
		    _CE
		);

	/** Constante PA. */
	public static final TipoEntradaIndicePropietariosTipoDocumentoPersona PA = new TipoEntradaIndicePropietariosTipoDocumentoPersona(
		    _PA
		);

	/** Constante TI. */
	public static final TipoEntradaIndicePropietariosTipoDocumentoPersona TI = new TipoEntradaIndicePropietariosTipoDocumentoPersona(
		    _TI
		);

	/** Constante NUIP. */
	public static final TipoEntradaIndicePropietariosTipoDocumentoPersona NUIP = new TipoEntradaIndicePropietariosTipoDocumentoPersona(
		    _NUIP
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIndicePropietariosTipoDocumentoPersona.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/indicepropietarios/consultar/v1",
		        ">tipoEntradaIndicePropietarios>tipoDocumentoPersona"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada indice propietarios tipo documento persona.
	 *
	 * @param value de value
	 */

	// Constructor
	protected TipoEntradaIndicePropietariosTipoDocumentoPersona(java.lang.String value)
	{
		_value_ = value;
		_table_.put(_value_, this);
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
	 * From value.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada indice propietarios tipo documento persona
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaIndicePropietariosTipoDocumentoPersona fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaIndicePropietariosTipoDocumentoPersona enumeration = (TipoEntradaIndicePropietariosTipoDocumentoPersona)_table_
				.get(value);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	/**
	 * From string.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada indice propietarios tipo documento persona
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaIndicePropietariosTipoDocumentoPersona fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
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

	/** {@inheritdoc} */
	public java.lang.String toString()
	{
		return _value_;
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
	 * Return type metadata object.
	 *
	 * @return el valor de type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}
}
