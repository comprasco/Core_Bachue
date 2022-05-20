/**
 * TipoDatosMatriculasMigracion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1;


/**
 * Clase que contiene todos las propiedades TipoDatosMatriculasMigracion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoDatosMatriculasMigracion implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8604345795354242609L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDatosMatriculasMigracion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "tipoDatosMatriculasMigracion"
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
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("infoMatriculaMigrada");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "infoMatriculaMigrada"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad info matricula migrada. */
	/* True cuando la información de la matrícula
	 *                                 ha
	 *                                 sido migrada,
	 *                                 false en cualquier otro caso */
	private java.lang.Boolean infoMatriculaMigrada;

	/** Propiedad matricula. */
	/* En cada de las matrículas recibidas,
	 *                                 los tres
	 *                                 primeros caractares deben corresponder al circulo
	 *                                 registral; a
	 *                                 partir del
	 *                                 cuarto caracter el número de matrícula */
	private java.lang.String matricula;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo datos matriculas migracion.
	 */
	public TipoDatosMatriculasMigracion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo datos matriculas migracion.
	 *
	 * @param matricula de matricula
	 * @param infoMatriculaMigrada de info matricula migrada
	 */
	public TipoDatosMatriculasMigracion(java.lang.String matricula, java.lang.Boolean infoMatriculaMigrada)
	{
		this.matricula                = matricula;
		this.infoMatriculaMigrada     = infoMatriculaMigrada;
	}

	/**
	 * Gets the matricula value for this TipoDatosMatriculasMigracion.
	 *
	 * @return matricula   * En cada de las matrículas recibidas,
	     *                                 los tres
	     *                                 primeros caractares deben corresponder al circulo
	     *                                 registral; a
	     *                                 partir del
	     *                                 cuarto caracter el número de matrícula
	 */
	public java.lang.String getMatricula()
	{
		return matricula;
	}

	/**
	 * Sets the matricula value for this TipoDatosMatriculasMigracion.
	 *
	 * @param matricula   * En cada de las matrículas recibidas,
	     *                                 los tres
	     *                                 primeros caractares deben corresponder al circulo
	     *                                 registral; a
	     *                                 partir del
	     *                                 cuarto caracter el número de matrícula
	 */
	public void setMatricula(java.lang.String matricula)
	{
		this.matricula = matricula;
	}

	/**
	 * Gets the infoMatriculaMigrada value for this TipoDatosMatriculasMigracion.
	 *
	 * @return infoMatriculaMigrada   * True cuando la información de la matrícula
	     *                                 ha
	     *                                 sido migrada,
	     *                                 false en cualquier otro caso
	 */
	public java.lang.Boolean getInfoMatriculaMigrada()
	{
		return infoMatriculaMigrada;
	}

	/**
	 * Sets the infoMatriculaMigrada value for this TipoDatosMatriculasMigracion.
	 *
	 * @param infoMatriculaMigrada   * True cuando la información de la matrícula
	     *                                 ha
	     *                                 sido migrada,
	     *                                 false en cualquier otro caso
	 */
	public void setInfoMatriculaMigrada(java.lang.Boolean infoMatriculaMigrada)
	{
		this.infoMatriculaMigrada = infoMatriculaMigrada;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoDatosMatriculasMigracion))
			return false;

		TipoDatosMatriculasMigracion other = (TipoDatosMatriculasMigracion)obj;

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
				|| ((this.matricula != null) && this.matricula.equals(other.getMatricula())))
				&& (((this.infoMatriculaMigrada == null) && (other.getInfoMatriculaMigrada() == null))
				|| ((this.infoMatriculaMigrada != null)
				&& this.infoMatriculaMigrada.equals(other.getInfoMatriculaMigrada())));
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

		if(getInfoMatriculaMigrada() != null)
			_hashCode += getInfoMatriculaMigrada().hashCode();

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
