/**
 * TipoEntradaConsultaMatriculasCriterioBusqueda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaConsultaMatriculasCriterioBusqueda.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 18/03/2020
 */
public class TipoEntradaConsultaMatriculasCriterioBusqueda implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4196344986034398012L;

	/** Propiedad table. */
	private static java.util.HashMap _table_ = new java.util.HashMap();

	/** Constante _numeroDocumentoCC. */
	public static final java.lang.String _numeroDocumentoCC = "numeroDocumentoCC";

	/** Constante _numeroDocumentoCE. */
	public static final java.lang.String _numeroDocumentoCE = "numeroDocumentoCE";

	/** Constante _numeroDocumentoNUIP. */
	public static final java.lang.String _numeroDocumentoNUIP = "numeroDocumentoNUIP";

	/** Constante _numeroDocumentoPA. */
	public static final java.lang.String _numeroDocumentoPA = "numeroDocumentoPA";

	/** Constante _numeroDocumentoTI. */
	public static final java.lang.String _numeroDocumentoTI = "numeroDocumentoTI";

	/** Constante _numeroDocumentoNIT. */
	public static final java.lang.String _numeroDocumentoNIT = "numeroDocumentoNIT";

	/** Constante _nombres. */
	public static final java.lang.String _nombres = "nombres";

	/** Constante _razonSocial. */
	public static final java.lang.String _razonSocial = "razonSocial";

	/** Constante _direccion. */
	public static final java.lang.String _direccion = "direccion";

	/** Constante numeroDocumentoCC. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda numeroDocumentoCC = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _numeroDocumentoCC
		);

	/** Constante numeroDocumentoCE. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda numeroDocumentoCE = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _numeroDocumentoCE
		);

	/** Constante numeroDocumentoNUIP. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda numeroDocumentoNUIP = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _numeroDocumentoNUIP
		);

	/** Constante numeroDocumentoPA. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda numeroDocumentoPA = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _numeroDocumentoPA
		);

	/** Constante numeroDocumentoTI. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda numeroDocumentoTI = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _numeroDocumentoTI
		);

	/** Constante numeroDocumentoNIT. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda numeroDocumentoNIT = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _numeroDocumentoNIT
		);

	/** Constante nombres. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda nombres = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _nombres
		);

	/** Constante razonSocial. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda razonSocial = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _razonSocial
		);

	/** Constante direccion. */
	public static final TipoEntradaConsultaMatriculasCriterioBusqueda direccion = new TipoEntradaConsultaMatriculasCriterioBusqueda(
		    _direccion
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultaMatriculasCriterioBusqueda.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1",
		        ">tipoEntradaConsultaMatriculas>criterioBusqueda"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada consulta matriculas criterio busqueda.
	 *
	 * @param value de value
	 */

	// Constructor
	protected TipoEntradaConsultaMatriculasCriterioBusqueda(java.lang.String value)
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
	 * @return el valor de tipo entrada consulta matriculas criterio busqueda
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaConsultaMatriculasCriterioBusqueda fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaConsultaMatriculasCriterioBusqueda enumeration = (TipoEntradaConsultaMatriculasCriterioBusqueda)_table_
				.get(value);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	/**
	 * From string.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada consulta matriculas criterio busqueda
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaConsultaMatriculasCriterioBusqueda fromString(java.lang.String value)
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
