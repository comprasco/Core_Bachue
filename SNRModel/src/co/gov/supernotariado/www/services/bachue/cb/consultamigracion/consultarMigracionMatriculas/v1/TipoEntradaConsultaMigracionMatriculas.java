/**
 * TipoEntradaConsultaMigracionMatriculas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1;



/**
 * El esquema define los
 *                         datos de entrada para la operacion Consulta Migracion Matriculas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultaMigracionMatriculas implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1887973150755067896L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultaMigracionMatriculas.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "tipoEntradaConsultaMigracionMatriculas"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("matriculasConsultadas");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "matriculasConsultadas"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "tipoMatriculaConsultada"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("usuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionMatriculas/v1",
		        "usuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;
	
	/** Propiedad usuario. */
	private java.lang.String usuario;
	
	/** Propiedad matriculas consultadas. */
	private co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada[] matriculasConsultadas;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consulta migracion matriculas.
	 */
	public TipoEntradaConsultaMigracionMatriculas()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consulta migracion matriculas.
	 *
	 * @param matriculasConsultadas de matriculas consultadas
	 * @param usuario de usuario
	 */
	public TipoEntradaConsultaMigracionMatriculas(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada[] matriculasConsultadas,
	    java.lang.String                                                                                                         usuario
	)
	{
		this.matriculasConsultadas     = matriculasConsultadas;
		this.usuario                   = usuario;
	}

	/**
	 * Gets the matriculasConsultadas value for this TipoEntradaConsultaMigracionMatriculas.
	 *
	 * @return matriculasConsultadas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada[] getMatriculasConsultadas()
	{
		return matriculasConsultadas;
	}

	/**
	 * Sets the matriculasConsultadas value for this TipoEntradaConsultaMigracionMatriculas.
	 *
	 * @param matriculasConsultadas de matriculas consultadas
	 */
	public void setMatriculasConsultadas(
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada[] matriculasConsultadas
	)
	{
		this.matriculasConsultadas = matriculasConsultadas;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas consultadas.
	 *
	 * @param i de i
	 * @return el valor de matriculas consultadas
	 */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada getMatriculasConsultadas(
	    int i
	)
	{
		return this.matriculasConsultadas[i];
	}

	/**
	 * Cambia el valor de matriculas consultadas.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setMatriculasConsultadas(
	    int i,
	    co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoMatriculaConsultada _value
	)
	{
		this.matriculasConsultadas[i] = _value;
	}

	/**
	 * Gets the usuario value for this TipoEntradaConsultaMigracionMatriculas.
	 *
	 * @return usuario
	 */
	public java.lang.String getUsuario()
	{
		return usuario;
	}

	/**
	 * Sets the usuario value for this TipoEntradaConsultaMigracionMatriculas.
	 *
	 * @param usuario de usuario
	 */
	public void setUsuario(java.lang.String usuario)
	{
		this.usuario = usuario;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultaMigracionMatriculas))
			return false;

		TipoEntradaConsultaMigracionMatriculas other = (TipoEntradaConsultaMigracionMatriculas)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.matriculasConsultadas == null) && (other.getMatriculasConsultadas() == null))
				|| ((this.matriculasConsultadas != null)
				&& java.util.Arrays.equals(this.matriculasConsultadas, other.getMatriculasConsultadas())))
				&& (((this.usuario == null) && (other.getUsuario() == null))
				|| ((this.usuario != null) && this.usuario.equals(other.getUsuario())));
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

		if(getMatriculasConsultadas() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getMatriculasConsultadas()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getMatriculasConsultadas(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getUsuario() != null)
			_hashCode += getUsuario().hashCode();

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
