/**
 * TipoEntradaRegistrarUsuarioSegundoFactor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarUsuarioSegundoFactor.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaRegistrarUsuarioSegundoFactor implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2149203198411215924L;

	/** Propiedad table. */
	private static java.util.HashMap _table_ = new java.util.HashMap();

	/** Constante _Huella. */
	public static final java.lang.String _Huella = "Huella";

	/** Constante _SegundaClave. */
	public static final java.lang.String _SegundaClave = "SegundaClave";

	/** Constante Huella. */
	public static final TipoEntradaRegistrarUsuarioSegundoFactor Huella = new TipoEntradaRegistrarUsuarioSegundoFactor(
		    _Huella
		);

	/** Constante SegundaClave. */
	public static final TipoEntradaRegistrarUsuarioSegundoFactor SegundaClave = new TipoEntradaRegistrarUsuarioSegundoFactor(
		    _SegundaClave
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarUsuarioSegundoFactor.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        ">tipoEntradaRegistrarUsuario>segundoFactor"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar usuario segundo factor.
	 *
	 * @param value de value
	 */
	// Constructor
	protected TipoEntradaRegistrarUsuarioSegundoFactor(java.lang.String value)
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
	 * @return el valor de tipo entrada registrar usuario segundo factor
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaRegistrarUsuarioSegundoFactor fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	/**
	 * From value.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada registrar usuario segundo factor
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaRegistrarUsuarioSegundoFactor fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaRegistrarUsuarioSegundoFactor enumeration = (TipoEntradaRegistrarUsuarioSegundoFactor)_table_.get(
			    value
			);

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
