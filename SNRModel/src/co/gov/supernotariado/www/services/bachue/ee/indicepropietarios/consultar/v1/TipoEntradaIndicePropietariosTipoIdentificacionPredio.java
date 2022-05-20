/**
 * TipoEntradaIndicePropietariosTipoIdentificacionPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.ee.indicepropietarios.consultar.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaIndicePropietariosTipoIdentificacionPredio.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/07/2020
 */
public class TipoEntradaIndicePropietariosTipoIdentificacionPredio implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1743067730029357866L;

	/** Propiedad table. */
	private static java.util.HashMap _table_ = new java.util.HashMap();

	/** Constante _matricula. */
	public static final java.lang.String _matricula = "matricula";

	/** Constante _numeroPredial. */
	public static final java.lang.String _numeroPredial = "numeroPredial";

	/** Constante _numeroPredialAnterior. */
	public static final java.lang.String _numeroPredialAnterior = "numeroPredialAnterior";

	/** Constante _NUPRE. */
	public static final java.lang.String _NUPRE = "NUPRE";

	/** Constante matricula. */
	public static final TipoEntradaIndicePropietariosTipoIdentificacionPredio matricula = new TipoEntradaIndicePropietariosTipoIdentificacionPredio(
		    _matricula
		);

	/** Constante numeroPredial. */
	public static final TipoEntradaIndicePropietariosTipoIdentificacionPredio numeroPredial = new TipoEntradaIndicePropietariosTipoIdentificacionPredio(
		    _numeroPredial
		);

	/** Constante numeroPredialAnterior. */
	public static final TipoEntradaIndicePropietariosTipoIdentificacionPredio numeroPredialAnterior = new TipoEntradaIndicePropietariosTipoIdentificacionPredio(
		    _numeroPredialAnterior
		);

	/** Constante NUPRE. */
	public static final TipoEntradaIndicePropietariosTipoIdentificacionPredio NUPRE = new TipoEntradaIndicePropietariosTipoIdentificacionPredio(
		    _NUPRE
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIndicePropietariosTipoIdentificacionPredio.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/ee/indicepropietarios/consultar/v1",
		        ">tipoEntradaIndicePropietarios>tipoIdentificacionPredio"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada indice propietarios tipo identificacion predio.
	 *
	 * @param value correspondiente al valor de value
	 */

	// Constructor
	protected TipoEntradaIndicePropietariosTipoIdentificacionPredio(java.lang.String value)
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

	/** {@inheritdoc} */
	public boolean equals(java.lang.Object obj)
	{
		return (obj == this);
	}

	/**
	 * From string.
	 *
	 * @param value correspondiente al valor de value
	 * @return el valor de tipo entrada indice propietarios tipo identificacion predio
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaIndicePropietariosTipoIdentificacionPredio fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	/**
	 * From value.
	 *
	 * @param value correspondiente al valor de value
	 * @return el valor de tipo entrada indice propietarios tipo identificacion predio
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaIndicePropietariosTipoIdentificacionPredio fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaIndicePropietariosTipoIdentificacionPredio enumeration = (TipoEntradaIndicePropietariosTipoIdentificacionPredio)_table_
				.get(value);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
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
