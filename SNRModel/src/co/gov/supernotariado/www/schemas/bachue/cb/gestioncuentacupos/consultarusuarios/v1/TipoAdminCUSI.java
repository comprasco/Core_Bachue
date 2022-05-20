/**
 * TipoAdminCUSI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1;



/**
 * The Class TipoAdminCUSI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoAdminCUSI implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 619193711266131333L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoAdminCUSI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoAdminCUSI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocAdmin");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoDocAdmin"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numDocAdmin");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "numDocAdmin"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The num doc admin. */
	private java.lang.String numDocAdmin;

	/** The tipo doc admin. */
	private java.lang.String tipoDocAdmin;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo admin CUSI.
	 */
	public TipoAdminCUSI()
	{
	}

	/**
	 * Instantiates a new tipo admin CUSI.
	 *
	 * @param tipoDocAdmin the tipo doc admin
	 * @param numDocAdmin the num doc admin
	 */
	public TipoAdminCUSI(java.lang.String tipoDocAdmin, java.lang.String numDocAdmin)
	{
		this.tipoDocAdmin     = tipoDocAdmin;
		this.numDocAdmin      = numDocAdmin;
	}

	/**
	 * Gets the tipoDocAdmin value for this TipoAdminCUSI.
	 *
	 * @return tipoDocAdmin
	 */
	public java.lang.String getTipoDocAdmin()
	{
		return tipoDocAdmin;
	}

	/**
	 * Sets the tipoDocAdmin value for this TipoAdminCUSI.
	 *
	 * @param tipoDocAdmin the new tipo doc admin
	 */
	public void setTipoDocAdmin(java.lang.String tipoDocAdmin)
	{
		this.tipoDocAdmin = tipoDocAdmin;
	}

	/**
	 * Gets the numDocAdmin value for this TipoAdminCUSI.
	 *
	 * @return numDocAdmin
	 */
	public java.lang.String getNumDocAdmin()
	{
		return numDocAdmin;
	}

	/**
	 * Sets the numDocAdmin value for this TipoAdminCUSI.
	 *
	 * @param numDocAdmin the new num doc admin
	 */
	public void setNumDocAdmin(java.lang.String numDocAdmin)
	{
		this.numDocAdmin = numDocAdmin;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoAdminCUSI))
			return false;

		TipoAdminCUSI other = (TipoAdminCUSI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocAdmin == null) && (other.getTipoDocAdmin() == null))
				|| ((this.tipoDocAdmin != null) && this.tipoDocAdmin.equals(other.getTipoDocAdmin())))
				&& (((this.numDocAdmin == null) && (other.getNumDocAdmin() == null))
				|| ((this.numDocAdmin != null) && this.numDocAdmin.equals(other.getNumDocAdmin())));
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

		if(getTipoDocAdmin() != null)
			_hashCode += getTipoDocAdmin().hashCode();

		if(getNumDocAdmin() != null)
			_hashCode += getNumDocAdmin().hashCode();

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
