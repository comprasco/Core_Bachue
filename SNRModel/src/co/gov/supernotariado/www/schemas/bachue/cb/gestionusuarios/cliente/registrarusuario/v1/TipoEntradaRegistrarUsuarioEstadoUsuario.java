/**
 * TipoEntradaRegistrarUsuarioEstadoUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.registrarusuario.v1;

public class TipoEntradaRegistrarUsuarioEstadoUsuario implements java.io.Serializable
{
	private static java.util.HashMap                             _table_ = new java.util.HashMap();
	public static final java.lang.String                         _A      = "A";
	public static final java.lang.String                         _P      = "P";
	public static final java.lang.String                         _L      = "L";
	public static final java.lang.String                         _I      = "I";
	public static final TipoEntradaRegistrarUsuarioEstadoUsuario A       = new TipoEntradaRegistrarUsuarioEstadoUsuario(
		    _A
		);
	public static final TipoEntradaRegistrarUsuarioEstadoUsuario P       = new TipoEntradaRegistrarUsuarioEstadoUsuario(
		    _P
		);
	public static final TipoEntradaRegistrarUsuarioEstadoUsuario L       = new TipoEntradaRegistrarUsuarioEstadoUsuario(
		    _L
		);
	public static final TipoEntradaRegistrarUsuarioEstadoUsuario I       = new TipoEntradaRegistrarUsuarioEstadoUsuario(
		    _I
		);

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarUsuarioEstadoUsuario.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        ">tipoEntradaRegistrarUsuario>estadoUsuario"
		    )
		);
	}

	private java.lang.String _value_;

	// Constructor
	protected TipoEntradaRegistrarUsuarioEstadoUsuario(java.lang.String value)
	{
		_value_ = value;
		_table_.put(_value_, this);
	}

	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.EnumDeserializer(_javaType, _xmlType);
	}

	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.EnumSerializer(_javaType, _xmlType);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	public boolean equals(java.lang.Object obj)
	{
		return (obj == this);
	}

	public static TipoEntradaRegistrarUsuarioEstadoUsuario fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	public static TipoEntradaRegistrarUsuarioEstadoUsuario fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaRegistrarUsuarioEstadoUsuario enumeration = (TipoEntradaRegistrarUsuarioEstadoUsuario)_table_.get(
			    value
			);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	public java.lang.String getValue()
	{
		return _value_;
	}

	public int hashCode()
	{
		return toString().hashCode();
	}

	public java.lang.Object readResolve()
	    throws java.io.ObjectStreamException
	{
		return fromValue(_value_);
	}

	public java.lang.String toString()
	{
		return _value_;
	}
}
