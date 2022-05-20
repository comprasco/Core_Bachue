/**
 * RolTypeORC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenerrolescomponente.v1;

public class RolTypeORC implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    RolTypeORC.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
		        "rolTypeORC"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
		        "codigoRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
		        "nombreRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("componente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolescomponente/v1",
		        "componente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc   = null;
	private java.lang.String codigoRol;
	private java.lang.String componente;
	private java.lang.String nombreRol;
	private boolean          __hashCodeCalc = false;

	public RolTypeORC()
	{
	}

	public RolTypeORC(java.lang.String codigoRol, java.lang.String nombreRol, java.lang.String componente)
	{
		this.codigoRol      = codigoRol;
		this.nombreRol      = nombreRol;
		this.componente     = componente;
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the codigoRol value for this RolTypeORC.
	 *
	 * @param codigoRol
	 */
	public void setCodigoRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Gets the codigoRol value for this RolTypeORC.
	 *
	 * @return codigoRol
	 */
	public java.lang.String getCodigoRol()
	{
		return codigoRol;
	}

	/**
	 * Sets the componente value for this RolTypeORC.
	 *
	 * @param componente
	 */
	public void setComponente(java.lang.String componente)
	{
		this.componente = componente;
	}

	/**
	 * Gets the componente value for this RolTypeORC.
	 *
	 * @return componente
	 */
	public java.lang.String getComponente()
	{
		return componente;
	}

	/**
	 * Sets the nombreRol value for this RolTypeORC.
	 *
	 * @param nombreRol
	 */
	public void setNombreRol(java.lang.String nombreRol)
	{
		this.nombreRol = nombreRol;
	}

	/**
	 * Gets the nombreRol value for this RolTypeORC.
	 *
	 * @return nombreRol
	 */
	public java.lang.String getNombreRol()
	{
		return nombreRol;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof RolTypeORC))
			return false;

		RolTypeORC other = (RolTypeORC)obj;

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
				|| ((this.codigoRol != null) && this.codigoRol.equals(other.getCodigoRol())))
				&& (((this.nombreRol == null) && (other.getNombreRol() == null))
				|| ((this.nombreRol != null) && this.nombreRol.equals(other.getNombreRol())))
				&& (((this.componente == null) && (other.getComponente() == null))
				|| ((this.componente != null) && this.componente.equals(other.getComponente())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getCodigoRol() != null)
			_hashCode += getCodigoRol().hashCode();

		if(getNombreRol() != null)
			_hashCode += getNombreRol().hashCode();

		if(getComponente() != null)
			_hashCode += getComponente().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
