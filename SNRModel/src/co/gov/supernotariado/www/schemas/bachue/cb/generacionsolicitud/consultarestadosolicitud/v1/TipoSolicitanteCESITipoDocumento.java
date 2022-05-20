/**
 * TipoSolicitanteCESITipoDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1;



/**
 * The Class TipoSolicitanteCESITipoDocumento.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSolicitanteCESITipoDocumento implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4297229803889108227L;

	/** The table. */
	private static java.util.HashMap _table_ = new java.util.HashMap();

	/** The Constant _CC. */
	public static final java.lang.String _CC = "CC";

	/** The Constant _CE. */
	public static final java.lang.String _CE = "CE";

	/** The Constant _NIT. */
	public static final java.lang.String _NIT = "NIT";

	/** The Constant _PS. */
	public static final java.lang.String _PS = "PS";

	/** The Constant _TI. */
	public static final java.lang.String _TI = "TI";

	/** The Constant _NU. */
	public static final java.lang.String _NU = "NU";

	/** The Constant _SE. */
	public static final java.lang.String _SE = "SE";

	/** The Constant CC. */
	public static final TipoSolicitanteCESITipoDocumento CC = new TipoSolicitanteCESITipoDocumento(_CC);

	/** The Constant CE. */
	public static final TipoSolicitanteCESITipoDocumento CE = new TipoSolicitanteCESITipoDocumento(_CE);

	/** The Constant NIT. */
	public static final TipoSolicitanteCESITipoDocumento NIT = new TipoSolicitanteCESITipoDocumento(_NIT);

	/** The Constant PS. */
	public static final TipoSolicitanteCESITipoDocumento PS = new TipoSolicitanteCESITipoDocumento(_PS);

	/** The Constant TI. */
	public static final TipoSolicitanteCESITipoDocumento TI = new TipoSolicitanteCESITipoDocumento(_TI);

	/** The Constant NU. */
	public static final TipoSolicitanteCESITipoDocumento NU = new TipoSolicitanteCESITipoDocumento(_NU);

	/** The Constant SE. */
	public static final TipoSolicitanteCESITipoDocumento SE = new TipoSolicitanteCESITipoDocumento(_SE);

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSolicitanteCESITipoDocumento.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        ">tipoSolicitanteCESI>tipoDocumento"
		    )
		);
	}

	/** The value. */
	private java.lang.String _value_;

	/**
	 * Instantiates a new tipo solicitante CESI tipo documento.
	 *
	 * @param value the value
	 */

	// Constructor
	protected TipoSolicitanteCESITipoDocumento(java.lang.String value)
	{
		_value_ = value;
		_table_.put(_value_, this);
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public java.lang.String getValue()
	{
		return _value_;
	}

	/**
	 * From value.
	 *
	 * @param value the value
	 * @return the tipo solicitante CESI tipo documento
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static TipoSolicitanteCESITipoDocumento fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoSolicitanteCESITipoDocumento enumeration = (TipoSolicitanteCESITipoDocumento)_table_.get(value);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	/**
	 * From string.
	 *
	 * @param value the value
	 * @return the tipo solicitante CESI tipo documento
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static TipoSolicitanteCESITipoDocumento fromString(java.lang.String value)
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
	 * @return the java.lang. object
	 * @throws ObjectStreamException the object stream exception
	 */
	public java.lang.Object readResolve()
	    throws java.io.ObjectStreamException
	{
		return fromValue(_value_);
	}

	/**
	 * Gets the serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.EnumSerializer(_javaType, _xmlType);
	}

	/**
	 * Gets the deserializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
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
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}
}
