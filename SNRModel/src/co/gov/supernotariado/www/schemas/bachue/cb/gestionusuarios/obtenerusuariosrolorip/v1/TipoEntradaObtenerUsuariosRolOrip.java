/**
 * TipoEntradaObtenerUsuariosRolOrip.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obtenerusuariosrolorip.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaObtenerUsuariosRolOrip.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaObtenerUsuariosRolOrip implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6065668463998894855L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaObtenerUsuariosRolOrip.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
		        "tipoEntradaObtenerUsuariosRolOrip"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoCirculo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
		        "codigoCirculo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoRol");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obtenerusuariosrolorip/v1",
		        "codigoRol"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc   = null;
	
	/** Propiedad codigo circulo. */
	private java.lang.String codigoCirculo;
	
	/** Propiedad codigo rol. */
	private java.lang.String codigoRol;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada obtener usuarios rol orip.
	 */
	public TipoEntradaObtenerUsuariosRolOrip()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada obtener usuarios rol orip.
	 *
	 * @param codigoCirculo de codigo circulo
	 * @param codigoRol de codigo rol
	 */
	public TipoEntradaObtenerUsuariosRolOrip(java.lang.String codigoCirculo, java.lang.String codigoRol)
	{
		this.codigoCirculo     = codigoCirculo;
		this.codigoRol         = codigoRol;
	}

	/**
	 * Sets the codigoCirculo value for this TipoEntradaObtenerUsuariosRolOrip.
	 *
	 * @param codigoCirculo de codigo circulo
	 */
	public void setCodigoCirculo(java.lang.String codigoCirculo)
	{
		this.codigoCirculo = codigoCirculo;
	}

	/**
	 * Gets the codigoCirculo value for this TipoEntradaObtenerUsuariosRolOrip.
	 *
	 * @return codigoCirculo
	 */
	public java.lang.String getCodigoCirculo()
	{
		return codigoCirculo;
	}

	/**
	 * Sets the codigoRol value for this TipoEntradaObtenerUsuariosRolOrip.
	 *
	 * @param codigoRol de codigo rol
	 */
	public void setCodigoRol(java.lang.String codigoRol)
	{
		this.codigoRol = codigoRol;
	}

	/**
	 * Gets the codigoRol value for this TipoEntradaObtenerUsuariosRolOrip.
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
		if(!(obj instanceof TipoEntradaObtenerUsuariosRolOrip))
			return false;

		TipoEntradaObtenerUsuariosRolOrip other = (TipoEntradaObtenerUsuariosRolOrip)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoCirculo == null) && (other.getCodigoCirculo() == null))
				|| ((this.codigoCirculo != null) && this.codigoCirculo.equals(other.getCodigoCirculo())))
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

		if(getCodigoCirculo() != null)
			_hashCode += getCodigoCirculo().hashCode();

		if(getCodigoRol() != null)
			_hashCode += getCodigoRol().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
