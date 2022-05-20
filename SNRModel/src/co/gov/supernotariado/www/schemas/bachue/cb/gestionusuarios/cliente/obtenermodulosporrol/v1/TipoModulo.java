/**
 * TipoModulo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.obtenermodulosporrol.v1;

public class TipoModulo implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoModulo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
		        "tipoModulo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idComponente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1",
		        "idComponente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombre");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1", "nombre"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("activo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenermodulosporrol/v1", "activo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object __equalsCalc   = null;
	private java.lang.String activo;
	private java.lang.String idComponente;
	private java.lang.String nombre;
	private boolean          __hashCodeCalc = false;

	public TipoModulo()
	{
	}

	public TipoModulo(java.lang.String idComponente, java.lang.String nombre, java.lang.String activo)
	{
		this.idComponente     = idComponente;
		this.nombre           = nombre;
		this.activo           = activo;
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
	 * Sets the activo value for this TipoModulo.
	 *
	 * @param activo
	 */
	public void setActivo(java.lang.String activo)
	{
		this.activo = activo;
	}

	/**
	 * Gets the activo value for this TipoModulo.
	 *
	 * @return activo
	 */
	public java.lang.String getActivo()
	{
		return activo;
	}

	/**
	 * Sets the idComponente value for this TipoModulo.
	 *
	 * @param idComponente
	 */
	public void setIdComponente(java.lang.String idComponente)
	{
		this.idComponente = idComponente;
	}

	/**
	 * Gets the idComponente value for this TipoModulo.
	 *
	 * @return idComponente
	 */
	public java.lang.String getIdComponente()
	{
		return idComponente;
	}

	/**
	 * Sets the nombre value for this TipoModulo.
	 *
	 * @param nombre
	 */
	public void setNombre(java.lang.String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Gets the nombre value for this TipoModulo.
	 *
	 * @return nombre
	 */
	public java.lang.String getNombre()
	{
		return nombre;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoModulo))
			return false;

		TipoModulo other = (TipoModulo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idComponente == null) && (other.getIdComponente() == null))
				|| ((this.idComponente != null) && this.idComponente.equals(other.getIdComponente())))
				&& (((this.nombre == null) && (other.getNombre() == null))
				|| ((this.nombre != null) && this.nombre.equals(other.getNombre())))
				&& (((this.activo == null) && (other.getActivo() == null))
				|| ((this.activo != null) && this.activo.equals(other.getActivo())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getIdComponente() != null)
			_hashCode += getIdComponente().hashCode();

		if(getNombre() != null)
			_hashCode += getNombre().hashCode();

		if(getActivo() != null)
			_hashCode += getActivo().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
