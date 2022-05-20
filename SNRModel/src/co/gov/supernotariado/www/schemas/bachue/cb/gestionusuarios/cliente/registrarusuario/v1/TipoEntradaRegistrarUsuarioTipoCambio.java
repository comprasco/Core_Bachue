/**
 * TipoEntradaRegistrarUsuarioTipoCambio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.registrarusuario.v1;

public class TipoEntradaRegistrarUsuarioTipoCambio implements java.io.Serializable
{
	private static java.util.HashMap                          _table_       = new java.util.HashMap();
	public static final java.lang.String                      _Creacion     = "Creacion";
	public static final java.lang.String                      _Modificacion = "Modificacion";
	public static final TipoEntradaRegistrarUsuarioTipoCambio Creacion      = new TipoEntradaRegistrarUsuarioTipoCambio(
		    _Creacion
		);
	public static final TipoEntradaRegistrarUsuarioTipoCambio Modificacion  = new TipoEntradaRegistrarUsuarioTipoCambio(
		    _Modificacion
		);

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarUsuarioTipoCambio.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        ">tipoEntradaRegistrarUsuario>tipoCambio"
		    )
		);
	}

	private java.lang.String _value_;

	// Constructor
	protected TipoEntradaRegistrarUsuarioTipoCambio(java.lang.String value)
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

	public static TipoEntradaRegistrarUsuarioTipoCambio fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	public static TipoEntradaRegistrarUsuarioTipoCambio fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaRegistrarUsuarioTipoCambio enumeration = (TipoEntradaRegistrarUsuarioTipoCambio)_table_.get(value);

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
