/**
 * TipoMatriculaConsultada.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1;


/**
 * Clase que contiene todos las propiedades TipoMatriculaConsultada.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoMatriculaConsultada implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8777820246925678553L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoMatriculaConsultada.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "tipoMatriculaConsultada"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "matricula"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad matricula. */
	/* tres primeros caractares para código del
	 *                                 circulo
	 *                                 registral, a partir del cuarto caracter el número de
	 *                                 matrícula */
	private java.lang.String matricula;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo matricula consultada.
	 */
	public TipoMatriculaConsultada()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo matricula consultada.
	 *
	 * @param matricula de matricula
	 */
	public TipoMatriculaConsultada(java.lang.String matricula)
	{
		this.matricula = matricula;
	}

	/**
	 * Gets the matricula value for this TipoMatriculaConsultada.
	 *
	 * @return matricula   * tres primeros caractares para código del
	     *                                 circulo
	     *                                 registral, a partir del cuarto caracter el número de
	     *                                 matrícula
	 */
	public java.lang.String getMatricula()
	{
		return matricula;
	}

	/**
	 * Sets the matricula value for this TipoMatriculaConsultada.
	 *
	 * @param matricula   * tres primeros caractares para código del
	     *                                 circulo
	     *                                 registral, a partir del cuarto caracter el número de
	     *                                 matrícula
	 */
	public void setMatricula(java.lang.String matricula)
	{
		this.matricula = matricula;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoMatriculaConsultada))
			return false;

		TipoMatriculaConsultada other = (TipoMatriculaConsultada)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.matricula == null) && (other.getMatricula() == null))
				|| ((this.matricula != null) && this.matricula.equals(other.getMatricula())));
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

		if(getMatricula() != null)
			_hashCode += getMatricula().hashCode();

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
