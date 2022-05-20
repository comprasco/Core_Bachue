/**
 * TipoEntradaConsultarListaMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarListaMatriculas.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaConsultarListaMatriculas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6119245110740686403L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarListaMatriculas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        "tipoEntradaConsultarListaMatriculas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaMatricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        "listaMatricula"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        ">>tipoEntradaConsultarListaMatriculas>listaMatricula>matricula"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarListaMatriculas/v1",
		        "matricula"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad lista matricula. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculasListaMatriculaMatricula[] listaMatricula;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar lista matriculas.
	 */
	public TipoEntradaConsultarListaMatriculas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar lista matriculas.
	 *
	 * @param listaMatricula de lista matricula
	 */
	public TipoEntradaConsultarListaMatriculas(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculasListaMatriculaMatricula[] listaMatricula
	)
	{
		this.listaMatricula = listaMatricula;
	}

	/**
	 * Gets the listaMatricula value for this TipoEntradaConsultarListaMatriculas.
	 *
	 * @return listaMatricula
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculasListaMatriculaMatricula[] getListaMatricula()
	{
		return listaMatricula;
	}

	/**
	 * Sets the listaMatricula value for this TipoEntradaConsultarListaMatriculas.
	 *
	 * @param listaMatricula de lista matricula
	 */
	public void setListaMatricula(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarListaMatriculas.v1.TipoEntradaConsultarListaMatriculasListaMatriculaMatricula[] listaMatricula
	)
	{
		this.listaMatricula = listaMatricula;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarListaMatriculas))
			return false;

		TipoEntradaConsultarListaMatriculas other = (TipoEntradaConsultarListaMatriculas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.listaMatricula == null) && (other.getListaMatricula() == null))
				|| ((this.listaMatricula != null)
				&& java.util.Arrays.equals(this.listaMatricula, other.getListaMatricula())));
		__equalsCalc     = null;

		return _equals;
	}

	/** {@inheritdoc} */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getListaMatricula() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaMatricula()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaMatricula(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
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
	 * Get Custom Serializer.
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
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer.
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
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
