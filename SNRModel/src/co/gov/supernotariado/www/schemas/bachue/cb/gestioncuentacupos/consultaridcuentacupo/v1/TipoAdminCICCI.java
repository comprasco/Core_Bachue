/**
 * TipoAdminCICCI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1;



/**
 * The Class TipoAdminCICCI.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoAdminCICCI implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7720973896298995088L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoAdminCICCI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "tipoAdminCICCI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocAdmin");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "numDocAdmin"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("correoElectronicoCorporativoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "correoElectronicoCorporativoUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The correo electronico corporativo usuario. */
	private java.lang.String correoElectronicoCorporativoUsuario;

	/** The num doc admin. */
	private java.lang.String numDocAdmin;

	/** The tipo doc admin. */
	private java.lang.String tipoDocAdmin;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo admin CICCI.
	 */
	public TipoAdminCICCI()
	{
	}

	/**
	 * Instantiates a new tipo admin CICCI.
	 *
	 * @param tipoDocAdmin the tipo doc admin
	 * @param numDocAdmin the num doc admin
	 * @param correoElectronicoCorporativoUsuario the correo electronico corporativo usuario
	 */
	public TipoAdminCICCI(
	    java.lang.String tipoDocAdmin, java.lang.String numDocAdmin,
	    java.lang.String correoElectronicoCorporativoUsuario
	)
	{
		this.tipoDocAdmin                            = tipoDocAdmin;
		this.numDocAdmin                             = numDocAdmin;
		this.correoElectronicoCorporativoUsuario     = correoElectronicoCorporativoUsuario;
	}

	/**
	 * Gets the tipo doc admin.
	 *
	 * @return the tipo doc admin
	 */
	public java.lang.String getTipoDocAdmin()
	{
		return tipoDocAdmin;
	}

	/**
	 * Sets the tipo doc admin.
	 *
	 * @param tipoDocAdmin the new tipo doc admin
	 */
	public void setTipoDocAdmin(java.lang.String tipoDocAdmin)
	{
		this.tipoDocAdmin = tipoDocAdmin;
	}

	/**
	 * Gets the num doc admin.
	 *
	 * @return the num doc admin
	 */
	public java.lang.String getNumDocAdmin()
	{
		return numDocAdmin;
	}

	/**
	 * Sets the num doc admin.
	 *
	 * @param numDocAdmin the new num doc admin
	 */
	public void setNumDocAdmin(java.lang.String numDocAdmin)
	{
		this.numDocAdmin = numDocAdmin;
	}

	/**
	 * Gets the correo electronico corporativo usuario.
	 *
	 * @return the correo electronico corporativo usuario
	 */
	public java.lang.String getCorreoElectronicoCorporativoUsuario()
	{
		return correoElectronicoCorporativoUsuario;
	}

	/**
	 * Sets the correo electronico corporativo usuario.
	 *
	 * @param correoElectronicoCorporativoUsuario the new correo electronico corporativo usuario
	 */
	public void setCorreoElectronicoCorporativoUsuario(java.lang.String correoElectronicoCorporativoUsuario)
	{
		this.correoElectronicoCorporativoUsuario = correoElectronicoCorporativoUsuario;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoAdminCICCI))
			return false;

		TipoAdminCICCI other = (TipoAdminCICCI)obj;

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
				|| ((this.numDocAdmin != null) && this.numDocAdmin.equals(other.getNumDocAdmin())))
				&& (((this.correoElectronicoCorporativoUsuario == null)
				&& (other.getCorreoElectronicoCorporativoUsuario() == null))
				|| ((this.correoElectronicoCorporativoUsuario != null)
				&& this.correoElectronicoCorporativoUsuario.equals(other.getCorreoElectronicoCorporativoUsuario())));
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

		if(getCorreoElectronicoCorporativoUsuario() != null)
			_hashCode += getCorreoElectronicoCorporativoUsuario().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}

	/**
	 * Gets the type desc.
	 *
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Gets the serializer.
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
	 * Gets the deserializer.
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
