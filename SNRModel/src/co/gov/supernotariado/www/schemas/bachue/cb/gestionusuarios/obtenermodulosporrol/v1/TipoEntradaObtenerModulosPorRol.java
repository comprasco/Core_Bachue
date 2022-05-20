/**
 * TipoEntradaObtenerModulosPorRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenermodulosporrol.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaObtenerModulosPorRol.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaObtenerModulosPorRol implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5724095765523840995L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerModulosPorRol.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
		        "tipoEntradaObtenerModulosPorRol"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
		        "codigoRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad codigo rol. */
	private java.lang.String codigoRol;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada obtener modulos por rol.
	 */
	public TipoEntradaObtenerModulosPorRol()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada obtener modulos por rol.
	 *
	 * @param codigoRol de codigo rol
	 */
	public TipoEntradaObtenerModulosPorRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Sets the codigoRol value for this TipoEntradaObtenerModulosPorRol.
	 *
	 * @param codigoRol de codigo rol
	 */
	public void setCodigoRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Gets the codigoRol value for this TipoEntradaObtenerModulosPorRol.
	 *
	 * @return codigoRol
	 */
	public java.lang.String getCodigoRol()
	{
		return codigoRol;
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
		if(!(obj instanceof TipoEntradaObtenerModulosPorRol))
			return false;

		TipoEntradaObtenerModulosPorRol other = (TipoEntradaObtenerModulosPorRol)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoRol == null) && (other.getCodigoRol() == null))
				|| ((this.codigoRol != null) && this.codigoRol.equals(other.getCodigoRol())));
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

		if(getCodigoRol() != null)
			_hashCode += getCodigoRol().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
