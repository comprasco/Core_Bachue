/**
 * TipoEntradaObtenerAccesosPorRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaObtenerAccesosPorRol.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaObtenerAccesosPorRol implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2169033860756998922L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerAccesosPorRol.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "tipoEntradaObtenerAccesosPorRol"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "codigoRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo rol. */
	private java.lang.String codigoRol;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada obtener accesos por rol.
	 */
	public TipoEntradaObtenerAccesosPorRol()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada obtener accesos por rol.
	 *
	 * @param codigoRol de codigo rol
	 */
	public TipoEntradaObtenerAccesosPorRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Sets the codigoRol value for this TipoEntradaObtenerAccesosPorRol.
	 *
	 * @param codigoRol de codigo rol
	 */
	public void setCodigoRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Gets the codigoRol value for this TipoEntradaObtenerAccesosPorRol.
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
		if(!(obj instanceof TipoEntradaObtenerAccesosPorRol))
			return false;

		TipoEntradaObtenerAccesosPorRol other = (TipoEntradaObtenerAccesosPorRol)obj;

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
