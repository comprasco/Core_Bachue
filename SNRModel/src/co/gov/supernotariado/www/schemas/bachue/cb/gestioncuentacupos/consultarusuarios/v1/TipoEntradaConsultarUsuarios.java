/**
 * TipoEntradaConsultarUsuarios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1;



/**
 * The Class TipoEntradaConsultarUsuarios.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarUsuarios implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6108108876349808805L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarUsuarios.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoEntradaConsultarUsuarios"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1", "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("IDCuentaCupo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "IDCuentaCupo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("admin");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1", "admin"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarusuarios/v1",
		        "tipoAdminCUSI"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The ID cuenta cupo. */
	private java.lang.String IDCuentaCupo;

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The admin. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoAdminCUSI admin;

	/** The modulo. */
	private java.lang.String modulo;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo entrada consultar usuarios.
	 */
	public TipoEntradaConsultarUsuarios()
	{
	}

	/**
	 * Instantiates a new tipo entrada consultar usuarios.
	 *
	 * @param modulo the modulo
	 * @param IDCuentaCupo the ID cuenta cupo
	 * @param admin the admin
	 */
	public TipoEntradaConsultarUsuarios(
	    java.lang.String modulo, java.lang.String IDCuentaCupo,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoAdminCUSI admin
	)
	{
		this.modulo           = modulo;
		this.IDCuentaCupo     = IDCuentaCupo;
		this.admin            = admin;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarUsuarios.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the modulo value for this TipoEntradaConsultarUsuarios.
	 *
	 * @param modulo the new modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the IDCuentaCupo value for this TipoEntradaConsultarUsuarios.
	 *
	 * @return IDCuentaCupo
	 */
	public java.lang.String getIDCuentaCupo()
	{
		return IDCuentaCupo;
	}

	/**
	 * Sets the IDCuentaCupo value for this TipoEntradaConsultarUsuarios.
	 *
	 * @param IDCuentaCupo the new ID cuenta cupo
	 */
	public void setIDCuentaCupo(java.lang.String IDCuentaCupo)
	{
		this.IDCuentaCupo = IDCuentaCupo;
	}

	/**
	 * Gets the admin value for this TipoEntradaConsultarUsuarios.
	 *
	 * @return admin
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoAdminCUSI getAdmin()
	{
		return admin;
	}

	/**
	 * Sets the admin value for this TipoEntradaConsultarUsuarios.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoAdminCUSI admin
	)
	{
		this.admin = admin;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarUsuarios))
			return false;

		TipoEntradaConsultarUsuarios other = (TipoEntradaConsultarUsuarios)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.modulo == null) && (other.getModulo() == null))
				|| ((this.modulo != null) && this.modulo.equals(other.getModulo())))
				&& (((this.IDCuentaCupo == null) && (other.getIDCuentaCupo() == null))
				|| ((this.IDCuentaCupo != null) && this.IDCuentaCupo.equals(other.getIDCuentaCupo())))
				&& (((this.admin == null) && (other.getAdmin() == null))
				|| ((this.admin != null) && this.admin.equals(other.getAdmin())));
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

		if(getModulo() != null)
			_hashCode += getModulo().hashCode();

		if(getIDCuentaCupo() != null)
			_hashCode += getIDCuentaCupo().hashCode();

		if(getAdmin() != null)
			_hashCode += getAdmin().hashCode();

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
