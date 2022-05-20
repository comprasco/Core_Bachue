/**
 * TipoAdminCSI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1;


/**
 * Clase que contiene todos las propiedades TipoAdminCSI.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/09/2020
 */
public class TipoAdminCSI implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4754662975705767588L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoAdminCSI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "tipoAdminCSI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocAdmin");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "numDocAdmin"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad num doc admin. */
	private java.lang.String numDocAdmin;

	/** Propiedad tipo doc admin. */
	private java.lang.String tipoDocAdmin;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo admin CSI.
	 */
	public TipoAdminCSI()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo admin CSI.
	 *
	 * @param tipoDocAdmin de tipo doc admin
	 * @param numDocAdmin de num doc admin
	 */
	public TipoAdminCSI(java.lang.String tipoDocAdmin, java.lang.String numDocAdmin)
	{
		this.tipoDocAdmin     = tipoDocAdmin;
		this.numDocAdmin      = numDocAdmin;
	}

	/**
	 * Gets the tipoDocAdmin value for this TipoAdminCSI.
	 *
	 * @return tipoDocAdmin
	 */
	public java.lang.String getTipoDocAdmin()
	{
		return tipoDocAdmin;
	}

	/**
	 * Sets the tipoDocAdmin value for this TipoAdminCSI.
	 *
	 * @param tipoDocAdmin de tipo doc admin
	 */
	public void setTipoDocAdmin(java.lang.String tipoDocAdmin)
	{
		this.tipoDocAdmin = tipoDocAdmin;
	}

	/**
	 * Gets the numDocAdmin value for this TipoAdminCSI.
	 *
	 * @return numDocAdmin
	 */
	public java.lang.String getNumDocAdmin()
	{
		return numDocAdmin;
	}

	/**
	 * Sets the numDocAdmin value for this TipoAdminCSI.
	 *
	 * @param numDocAdmin de num doc admin
	 */
	public void setNumDocAdmin(java.lang.String numDocAdmin)
	{
		this.numDocAdmin = numDocAdmin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoAdminCSI))
			return false;

		TipoAdminCSI other = (TipoAdminCSI)obj;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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
