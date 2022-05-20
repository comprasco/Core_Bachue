/**
 * TipoEntradaCierreTurno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaCierreTurno.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/05/2020
 */
public class TipoEntradaCierreTurno implements java.io.Serializable
{
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaCierreTurno.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1",
		        "tipoEntradaCierreTurno"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sistemaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1",
		        "sistemaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("parametros");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1",
		        "parametros"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/co/cierreaperturaexpediente/cierreturno/v1",
		        "parametro"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad sistema origen. */
	private java.lang.String sistemaOrigen;

	/** Propiedad parametros. */
	private co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro[] parametros;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada cierre turno.
	 */
	public TipoEntradaCierreTurno()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada cierre turno.
	 *
	 * @param sistemaOrigen de sistema origen
	 * @param parametros de parametros
	 */
	public TipoEntradaCierreTurno(
	    java.lang.String sistemaOrigen,
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro[] parametros
	)
	{
		this.sistemaOrigen     = sistemaOrigen;
		this.parametros        = parametros;
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

	/**
	 * Sets the parametros value for this TipoEntradaCierreTurno.
	 *
	 * @param parametros de parametros
	 */
	public void setParametros(
	    co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro[] parametros
	)
	{
		this.parametros = parametros;
	}

	/**
	 * Gets the parametros value for this TipoEntradaCierreTurno.
	 *
	 * @return parametros
	 */
	public co.gov.supernotariado.www.schemas.bachue.co.cierreaperturaexpediente.cierreturno.v1.Parametro[] getParametros()
	{
		return parametros;
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
	 * Sets the sistemaOrigen value for this TipoEntradaCierreTurno.
	 *
	 * @param sistemaOrigen de sistema origen
	 */
	public void setSistemaOrigen(java.lang.String sistemaOrigen)
	{
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * Gets the sistemaOrigen value for this TipoEntradaCierreTurno.
	 *
	 * @return sistemaOrigen
	 */
	public java.lang.String getSistemaOrigen()
	{
		return sistemaOrigen;
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
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaCierreTurno))
			return false;

		TipoEntradaCierreTurno other = (TipoEntradaCierreTurno)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.sistemaOrigen == null) && (other.getSistemaOrigen() == null))
				|| ((this.sistemaOrigen != null) && this.sistemaOrigen.equals(other.getSistemaOrigen())))
				&& (((this.parametros == null) && (other.getParametros() == null))
				|| ((this.parametros != null) && java.util.Arrays.equals(this.parametros, other.getParametros())));
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

		if(getSistemaOrigen() != null)
			_hashCode += getSistemaOrigen().hashCode();

		if(getParametros() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getParametros()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getParametros(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
