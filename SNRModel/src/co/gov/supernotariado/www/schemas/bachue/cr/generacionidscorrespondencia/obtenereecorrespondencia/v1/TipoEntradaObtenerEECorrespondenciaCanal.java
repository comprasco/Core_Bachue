/**
 * TipoEntradaObtenerEECorrespondenciaCanal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cr.generacionidscorrespondencia.obtenereecorrespondencia.v1;

public class TipoEntradaObtenerEECorrespondenciaCanal implements java.io.Serializable
{
	private static final long                                    serialVersionUID = -3309824627117043276L;
	private static java.util.HashMap                             _table_          = new java.util.HashMap();
	public static final java.lang.String                         _fisico          = "fisico";
	public static final java.lang.String                         _electronico     = "electronico";
	public static final java.lang.String                         _sms             = "sms";
	public static final TipoEntradaObtenerEECorrespondenciaCanal fisico           = new TipoEntradaObtenerEECorrespondenciaCanal(
		    _fisico
		);
	public static final TipoEntradaObtenerEECorrespondenciaCanal electronico      = new TipoEntradaObtenerEECorrespondenciaCanal(
		    _electronico
		);
	public static final TipoEntradaObtenerEECorrespondenciaCanal sms              = new TipoEntradaObtenerEECorrespondenciaCanal(
		    _sms
		);

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerEECorrespondenciaCanal.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cr/generacionidscorrespondencia/obtenereecorrespondencia/v1",
		        ">tipoEntradaObtenerEECorrespondencia>canal"
		    )
		);
	}

	private java.lang.String _value_;

	// Constructor
	protected TipoEntradaObtenerEECorrespondenciaCanal(java.lang.String value)
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

	public java.lang.String getValue()
	{
		return _value_;
	}

	public static TipoEntradaObtenerEECorrespondenciaCanal fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	public static TipoEntradaObtenerEECorrespondenciaCanal fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaObtenerEECorrespondenciaCanal enumeration = (TipoEntradaObtenerEECorrespondenciaCanal)_table_.get(
			    value
			);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	public boolean equals(java.lang.Object obj)
	{
		return (obj == this);
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
