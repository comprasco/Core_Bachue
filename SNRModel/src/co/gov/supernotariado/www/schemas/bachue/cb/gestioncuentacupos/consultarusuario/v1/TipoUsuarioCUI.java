/**
 * TipoUsuarioCUI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1;



/**
 * The Class TipoUsuarioCUI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoUsuarioCUI implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4191702202045860376L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoUsuarioCUI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuario/v1",
		        "tipoUsuarioCUI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuario/v1",
		        "tipoDocUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuario/v1",
		        "numDocUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The num doc usuario. */
	private java.lang.String numDocUsuario;

	/** The tipo doc usuario. */
	private java.lang.String tipoDocUsuario;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo usuario CUI.
	 */
	public TipoUsuarioCUI()
	{
	}

	/**
	 * Instantiates a new tipo usuario CUI.
	 *
	 * @param tipoDocUsuario the tipo doc usuario
	 * @param numDocUsuario the num doc usuario
	 */
	public TipoUsuarioCUI(java.lang.String tipoDocUsuario, java.lang.String numDocUsuario)
	{
		this.tipoDocUsuario     = tipoDocUsuario;
		this.numDocUsuario      = numDocUsuario;
	}

	/**
	 * Gets the tipoDocUsuario value for this TipoUsuarioCUI.
	 *
	 * @return tipoDocUsuario
	 */
	public java.lang.String getTipoDocUsuario()
	{
		return tipoDocUsuario;
	}

	/**
	 * Sets the tipoDocUsuario value for this TipoUsuarioCUI.
	 *
	 * @param tipoDocUsuario the new tipo doc usuario
	 */
	public void setTipoDocUsuario(java.lang.String tipoDocUsuario)
	{
		this.tipoDocUsuario = tipoDocUsuario;
	}

	/**
	 * Gets the numDocUsuario value for this TipoUsuarioCUI.
	 *
	 * @return numDocUsuario
	 */
	public java.lang.String getNumDocUsuario()
	{
		return numDocUsuario;
	}

	/**
	 * Sets the numDocUsuario value for this TipoUsuarioCUI.
	 *
	 * @param numDocUsuario the new num doc usuario
	 */
	public void setNumDocUsuario(java.lang.String numDocUsuario)
	{
		this.numDocUsuario = numDocUsuario;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoUsuarioCUI))
			return false;

		TipoUsuarioCUI other = (TipoUsuarioCUI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocUsuario == null) && (other.getTipoDocUsuario() == null))
				|| ((this.tipoDocUsuario != null) && this.tipoDocUsuario.equals(other.getTipoDocUsuario())))
				&& (((this.numDocUsuario == null) && (other.getNumDocUsuario() == null))
				|| ((this.numDocUsuario != null) && this.numDocUsuario.equals(other.getNumDocUsuario())));
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

		if(getTipoDocUsuario() != null)
			_hashCode += getTipoDocUsuario().hashCode();

		if(getNumDocUsuario() != null)
			_hashCode += getNumDocUsuario().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Return type metadata object.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
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
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
