/**
 * TipoEntradaInactivarAlertaTipoDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaInactivarAlertaTipoDocumento.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaInactivarAlertaTipoDocumento implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7945655985344156280L;
	
	/** Propiedad table. */
	private static java.util.HashMap                            _table_ = new java.util.HashMap();
	
	/** Constante _CC. */
	public static final java.lang.String                        _CC     = "CC";
	
	/** Constante _CE. */
	public static final java.lang.String                        _CE     = "CE";
	
	/** Constante _NIT. */
	public static final java.lang.String                        _NIT    = "NIT";
	
	/** Constante _PS. */
	public static final java.lang.String                        _PS     = "PS";
	
	/** Constante _TI. */
	public static final java.lang.String                        _TI     = "TI";
	
	/** Constante _NU. */
	public static final java.lang.String                        _NU     = "NU";
	
	/** Constante _SE. */
	public static final java.lang.String                        _SE     = "SE";
	
	/** Constante CC. */
	public static final TipoEntradaInactivarAlertaTipoDocumento CC      = new TipoEntradaInactivarAlertaTipoDocumento(
		    _CC
		);
	
	/** Constante CE. */
	public static final TipoEntradaInactivarAlertaTipoDocumento CE      = new TipoEntradaInactivarAlertaTipoDocumento(
		    _CE
		);
	
	/** Constante NIT. */
	public static final TipoEntradaInactivarAlertaTipoDocumento NIT     = new TipoEntradaInactivarAlertaTipoDocumento(
		    _NIT
		);
	
	/** Constante PS. */
	public static final TipoEntradaInactivarAlertaTipoDocumento PS      = new TipoEntradaInactivarAlertaTipoDocumento(
		    _PS
		);
	
	/** Constante TI. */
	public static final TipoEntradaInactivarAlertaTipoDocumento TI      = new TipoEntradaInactivarAlertaTipoDocumento(
		    _TI
		);
	
	/** Constante NU. */
	public static final TipoEntradaInactivarAlertaTipoDocumento NU      = new TipoEntradaInactivarAlertaTipoDocumento(
		    _NU
		);
	
	/** Constante SE. */
	public static final TipoEntradaInactivarAlertaTipoDocumento SE      = new TipoEntradaInactivarAlertaTipoDocumento(
		    _SE
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaInactivarAlertaTipoDocumento.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionalertastitulares/inactivaralerta/v1",
		        ">tipoEntradaInactivarAlerta>tipoDocumento"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada inactivar alerta tipo documento.
	 *
	 * @param value de value
	 */
	// Constructor
	protected TipoEntradaInactivarAlertaTipoDocumento(java.lang.String value)
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
	 * @return el valor de tipo entrada inactivar alerta tipo documento
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaInactivarAlertaTipoDocumento fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	/**
	 * From value.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada inactivar alerta tipo documento
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaInactivarAlertaTipoDocumento fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaInactivarAlertaTipoDocumento enumeration = (TipoEntradaInactivarAlertaTipoDocumento)_table_.get(
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
