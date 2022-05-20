/**
 * TipoMatricula.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.inmuebles.consultarMatriculas.v1;



/**
 * Clase que contiene todos las propiedades TipoMatricula.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 14/04/2020
 */
public class TipoMatricula implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7081863882625299038L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoMatricula.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "tipoMatricula"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matricula");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/inmuebles/consultarMatriculas/v1", "matricula"
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
	/* En cada una de las matrículas recibidas,
	 *                                 los
	 *                                 tres
	 *                                 primeros caractares deben corresponder al circulo
	 *                                 registral;
	 *                                 a
	 *                                 partir del
	 *                                 cuarto caracter el número de matrícula */
	private java.lang.String matricula;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo matricula.
	 */
	public TipoMatricula()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo matricula.
	 *
	 * @param matricula de matricula
	 */
	public TipoMatricula(java.lang.String matricula)
	{
		this.matricula = matricula;
	}

	/**
	 * Gets the matricula value for this TipoMatricula.
	 *
	 * @return matricula   * En cada una de las matrículas recibidas,
	 *                                 los
	 *                                 tres
	 *                                 primeros caractares deben corresponder al circulo
	 *                                 registral;
	 *                                 a
	 *                                 partir del
	 *                                 cuarto caracter el número de matrícula
	 */
	public java.lang.String getMatricula()
	{
		return matricula;
	}

	/**
	 * Sets the matricula value for this TipoMatricula.
	 *
	 * @param matricula   * En cada una de las matrículas recibidas,
	 *                                 los
	 *                                 tres
	 *                                 primeros caractares deben corresponder al circulo
	 *                                 registral;
	 *                                 a
	 *                                 partir del
	 *                                 cuarto caracter el número de matrícula
	 */
	public void setMatricula(java.lang.String matricula)
	{
		this.matricula = matricula;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoMatricula))
			return false;

		TipoMatricula other = (TipoMatricula)obj;

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
