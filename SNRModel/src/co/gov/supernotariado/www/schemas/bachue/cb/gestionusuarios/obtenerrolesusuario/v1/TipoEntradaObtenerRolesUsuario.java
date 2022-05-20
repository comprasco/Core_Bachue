/**
 * TipoEntradaObtenerRolesUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerrolesusuario.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaObtenerRolesUsuario.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaObtenerRolesUsuario implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3613988091105323728L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerRolesUsuario.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
		        "tipoEntradaObtenerRolesUsuario"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("loginUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
		        "loginUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("componente");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerrolesusuario/v1",
		        "componente"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad componente. */
	private java.lang.String componente;
	
	/** Propiedad login usuario. */
	private java.lang.String loginUsuario;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada obtener roles usuario.
	 */
	public TipoEntradaObtenerRolesUsuario()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada obtener roles usuario.
	 *
	 * @param loginUsuario de login usuario
	 * @param componente de componente
	 */
	public TipoEntradaObtenerRolesUsuario(java.lang.String loginUsuario, java.lang.String componente)
	{
		this.loginUsuario     = loginUsuario;
		this.componente       = componente;
	}

	/**
	 * Sets the componente value for this TipoEntradaObtenerRolesUsuario.
	 *
	 * @param componente de componente
	 */
	public void setComponente(java.lang.String componente)
	{
		this.componente = componente;
	}

	/**
	 * Gets the componente value for this TipoEntradaObtenerRolesUsuario.
	 *
	 * @return componente
	 */
	public java.lang.String getComponente()
	{
		return componente;
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
	 * Sets the loginUsuario value for this TipoEntradaObtenerRolesUsuario.
	 *
	 * @param loginUsuario de login usuario
	 */
	public void setLoginUsuario(java.lang.String loginUsuario)
	{
		this.loginUsuario = loginUsuario;
	}

	/**
	 * Gets the loginUsuario value for this TipoEntradaObtenerRolesUsuario.
	 *
	 * @return loginUsuario
	 */
	public java.lang.String getLoginUsuario()
	{
		return loginUsuario;
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
		if(!(obj instanceof TipoEntradaObtenerRolesUsuario))
			return false;

		TipoEntradaObtenerRolesUsuario other = (TipoEntradaObtenerRolesUsuario)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.loginUsuario == null) && (other.getLoginUsuario() == null))
				|| ((this.loginUsuario != null) && this.loginUsuario.equals(other.getLoginUsuario())))
				&& (((this.componente == null) && (other.getComponente() == null))
				|| ((this.componente != null) && this.componente.equals(other.getComponente())));
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

		if(getLoginUsuario() != null)
			_hashCode += getLoginUsuario().hashCode();

		if(getComponente() != null)
			_hashCode += getComponente().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
