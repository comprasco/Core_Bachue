/**
 * TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.RRRMatriculas.consultarRRRMatriculas.v1;

public class TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio implements java.io.Serializable
{
	private static final long                                                     serialVersionUID       = -1635511623353047582L;
	private static java.util.HashMap                                              _table_                = new java.util.HashMap();
	public static final java.lang.String                                          _matricula             = "matricula";
	public static final java.lang.String                                          _numeroPredial         = "numeroPredial";
	public static final java.lang.String                                          _numeroPredialAnterior = "numeroPredialAnterior";
	public static final java.lang.String                                          _NUPRE                 = "NUPRE";
	public static final TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio matricula              = new TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio(
		    _matricula
		);
	public static final TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio numeroPredial          = new TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio(
		    _numeroPredial
		);
	public static final TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio numeroPredialAnterior  = new TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio(
		    _numeroPredialAnterior
		);
	public static final TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio NUPRE                  = new TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio(
		    _NUPRE
		);

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/RRRMatriculas/consultarRRRMatriculas/v1",
		        ">tipoEntradaConsultarRRRMatriculas>tipoIdentificacionPredio"
		    )
		);
	}

	private java.lang.String _value_;

	// Constructor
	protected TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio(java.lang.String value)
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

	public static TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	public static TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio enumeration = (TipoEntradaConsultarRRRMatriculasTipoIdentificacionPredio)_table_
				.get(value);

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
