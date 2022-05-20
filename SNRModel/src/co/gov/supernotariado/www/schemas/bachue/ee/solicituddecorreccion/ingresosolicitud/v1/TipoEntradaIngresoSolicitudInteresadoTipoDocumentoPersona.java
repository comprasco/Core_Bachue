/**
 * TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long        serialVersionUID = -8792290705877487748L;
	
	/** Propiedad table. */
	private static java.util.HashMap _table_          = new java.util.HashMap();
	
	/** Constante _RE. */
	public static final java.lang.String                                          _RE   = "RE";
	
	/** Constante _TI. */
	public static final java.lang.String                                          _TI   = "TI";
	
	/** Constante _CC. */
	public static final java.lang.String                                          _CC   = "CC";
	
	/** Constante _CE. */
	public static final java.lang.String                                          _CE   = "CE";
	
	/** Constante _PE. */
	public static final java.lang.String                                          _PE   = "PE";
	
	/** Constante _PTP. */
	public static final java.lang.String                                          _PTP  = "PTP";
	
	/** Constante _PA. */
	public static final java.lang.String                                          _PA   = "PA";
	
	/** Constante _NUIP. */
	public static final java.lang.String                                          _NUIP = "NUIP";
	
	/** Constante RE. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona RE    = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _RE
		);
	
	/** Constante TI. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona TI    = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _TI
		);
	
	/** Constante CC. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona CC    = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _CC
		);
	
	/** Constante CE. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona CE    = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _CE
		);
	
	/** Constante PE. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona PE    = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _PE
		);
	
	/** Constante PTP. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona PTP   = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _PTP
		);
	
	/** Constante PA. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona PA    = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _PA
		);
	
	/** Constante NUIP. */
	public static final TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona NUIP  = new TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(
		    _NUIP
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/ingresosolicitud/v1",
		        ">>tipoEntradaIngresoSolicitud>interesado>tipoDocumentoPersona"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada ingreso solicitud interesado tipo documento persona.
	 *
	 * @param value de value
	 */
	// Constructor
	protected TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona(java.lang.String value)
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

	/** {@inheritdoc} */
	public boolean equals(java.lang.Object obj)
	{
		return (obj == this);
	}

	/**
	 * From string.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada ingreso solicitud interesado tipo documento persona
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona fromString(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		return fromValue(value);
	}

	/**
	 * From value.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada ingreso solicitud interesado tipo documento persona
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona enumeration = (TipoEntradaIngresoSolicitudInteresadoTipoDocumentoPersona)_table_
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
