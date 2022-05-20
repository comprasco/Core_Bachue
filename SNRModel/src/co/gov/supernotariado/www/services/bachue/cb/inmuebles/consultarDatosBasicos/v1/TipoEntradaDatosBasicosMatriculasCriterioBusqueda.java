/**
 * TipoEntradaDatosBasicosMatriculasCriterioBusqueda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarDatosBasicos.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaDatosBasicosMatriculasCriterioBusqueda.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 19/03/2020
 */
public class TipoEntradaDatosBasicosMatriculasCriterioBusqueda implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6178393411722922210L;

	/** Propiedad table. */
	private static java.util.HashMap _table_ = new java.util.HashMap();

	/** Constante _numeroPredial. */
	public static final java.lang.String _numeroPredial = "numeroPredial";

	/** Constante _numeroPredialAnterior. */
	public static final java.lang.String _numeroPredialAnterior = "numeroPredialAnterior";

	/** Constante _matricula. */
	public static final java.lang.String _matricula = "matricula";

	/** Constante _NUPRE. */
	public static final java.lang.String _NUPRE = "NUPRE";

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

	/** Constante numeroPredial. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroPredial = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroPredial
		);

	/** Constante numeroPredialAnterior. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroPredialAnterior = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroPredialAnterior
		);

	/** Constante matricula. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda matricula = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _matricula
		);

	/** Constante NUPRE. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda NUPRE = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _NUPRE
		);

	/** Constante numeroDocumentoCC. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroDocumentoCC = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroDocumentoCC
		);

	/** Constante numeroDocumentoCE. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroDocumentoCE = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroDocumentoCE
		);

	/** Constante numeroDocumentoNUIP. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroDocumentoNUIP = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroDocumentoNUIP
		);

	/** Constante numeroDocumentoPA. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroDocumentoPA = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroDocumentoPA
		);

	/** Constante numeroDocumentoTI. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroDocumentoTI = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroDocumentoTI
		);

	/** Constante numeroDocumentoNIT. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda numeroDocumentoNIT = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _numeroDocumentoNIT
		);

	/** Constante nombres. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda nombres = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _nombres
		);

	/** Constante razonSocial. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda razonSocial = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _razonSocial
		);

	/** Constante direccion. */
	public static final TipoEntradaDatosBasicosMatriculasCriterioBusqueda direccion = new TipoEntradaDatosBasicosMatriculasCriterioBusqueda(
		    _direccion
		);

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaDatosBasicosMatriculasCriterioBusqueda.class
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarDatosBasicos/v1",
		        ">tipoEntradaDatosBasicosMatriculas>criterioBusqueda"
		    )
		);
	}

	/** Propiedad value. */
	private java.lang.String _value_;

	/**
	 * Instancia un nuevo objeto tipo entrada datos basicos matriculas criterio busqueda.
	 *
	 * @param value de value
	 */

	// Constructor
	protected TipoEntradaDatosBasicosMatriculasCriterioBusqueda(java.lang.String value)
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
	 * @return el valor de tipo entrada datos basicos matriculas criterio busqueda
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaDatosBasicosMatriculasCriterioBusqueda fromValue(java.lang.String value)
	    throws java.lang.IllegalArgumentException
	{
		TipoEntradaDatosBasicosMatriculasCriterioBusqueda enumeration = (TipoEntradaDatosBasicosMatriculasCriterioBusqueda)_table_
				.get(value);

		if(enumeration == null)
			throw new java.lang.IllegalArgumentException();

		return enumeration;
	}

	/**
	 * From string.
	 *
	 * @param value de value
	 * @return el valor de tipo entrada datos basicos matriculas criterio busqueda
	 * @throws IllegalArgumentException cuando se produce algun error en el proceso
	 */
	public static TipoEntradaDatosBasicosMatriculasCriterioBusqueda fromString(java.lang.String value)
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
