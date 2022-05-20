/**
 * TipoEntradaSalvedadesTipoIdentificacionPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaSalvedadesTipoIdentificacionPredio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaSalvedadesTipoIdentificacionPredio implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long                                         serialVersionUID       = 7340542134321519751L;
	
	/** Propiedad table. */
	private static java.util.HashMap                                  _table_                = new java.util.HashMap();
	
	/** Constante _matricula. */
	public static final java.lang.String                              _matricula             = "matricula";
	
	/** Constante _numeroPredial. */
	public static final java.lang.String                              _numeroPredial         = "numeroPredial";
	
	/** Constante _numeroPredialAnterior. */
	public static final java.lang.String                              _numeroPredialAnterior = "numeroPredialAnterior";
	
	/** Constante _NUPRE. */
	public static final java.lang.String                              _NUPRE                 = "NUPRE";
	
	/** Constante matricula. */
	public static final TipoEntradaSalvedadesTipoIdentificacionPredio matricula              = new TipoEntradaSalvedadesTipoIdentificacionPredio(
		    _matricula
		);
	
	/** Constante numeroPredial. */
	public static final TipoEntradaSalvedadesTipoIdentificacionPredio numeroPredial          = new TipoEntradaSalvedadesTipoIdentificacionPredio(
		    _numeroPredial
		);
	
	/** Constante numeroPredialAnterior. */
	public static final TipoEntradaSalvedadesTipoIdentificacionPredio numeroPredialAnterior  = new TipoEntradaSalvedadesTipoIdentificacionPredio(
		    _numeroPredialAnterior
		);
	
	/** Constante NUPRE. */
	public static final TipoEntradaSalvedadesTipoIdentificacionPredio NUPRE                  = new TipoEntradaSalvedadesTipoIdentificacionPredio(
		    _NUPRE
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaSalvedadesTipoIdentificacionPredio.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/salvedades/consultarSalvedades/v1",
		        ">tipoEntradaSalvedades>tipoIdentificacionPredio"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada salvedades tipo identificacion predio.
	 *
	 * @param value de value
	 */
	// Constructor
	protected TipoEntradaSalvedadesTipoIdentificacionPredio(java.lang.String value)
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
	 * @return el valor de tipo entrada salvedades tipo identificacion predio
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaSalvedadesTipoIdentificacionPredio fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaSalvedadesTipoIdentificacionPredio enumeration = (TipoEntradaSalvedadesTipoIdentificacionPredio)_table_
				.get(value);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	/**
	 * From string.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada salvedades tipo identificacion predio
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaSalvedadesTipoIdentificacionPredio fromString(java.lang.String value)
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
