/**
 * TipoEntradaConsultarSaldo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1;



/**
 * The Class TipoEntradaConsultarSaldo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarSaldo implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3417009741871973121L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarSaldo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "tipoEntradaConsultarSaldo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1", "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("IDCuentaCupo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1", "admin"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "tipoAdminCSI"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("usuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1", "usuario"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarsaldo/v1",
		        "tipoUsuarioCSI"
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
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoAdminCSI admin;

	/** The modulo. */
	private java.lang.String modulo;

	/** The usuario. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoUsuarioCSI usuario;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo entrada consultar saldo.
	 */
	public TipoEntradaConsultarSaldo()
	{
	}

	/**
	 * Instantiates a new tipo entrada consultar saldo.
	 *
	 * @param modulo the modulo
	 * @param IDCuentaCupo the ID cuenta cupo
	 * @param admin the admin
	 * @param usuario the usuario
	 */
	public TipoEntradaConsultarSaldo(
	    java.lang.String modulo, java.lang.String IDCuentaCupo,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoAdminCSI admin,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoUsuarioCSI usuario
	)
	{
		this.modulo           = modulo;
		this.IDCuentaCupo     = IDCuentaCupo;
		this.admin            = admin;
		this.usuario          = usuario;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarSaldo.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the modulo value for this TipoEntradaConsultarSaldo.
	 *
	 * @param modulo the new modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the IDCuentaCupo value for this TipoEntradaConsultarSaldo.
	 *
	 * @return IDCuentaCupo
	 */
	public java.lang.String getIDCuentaCupo()
	{
		return IDCuentaCupo;
	}

	/**
	 * Sets the IDCuentaCupo value for this TipoEntradaConsultarSaldo.
	 *
	 * @param IDCuentaCupo the new ID cuenta cupo
	 */
	public void setIDCuentaCupo(java.lang.String IDCuentaCupo)
	{
		this.IDCuentaCupo = IDCuentaCupo;
	}

	/**
	 * Gets the admin value for this TipoEntradaConsultarSaldo.
	 *
	 * @return admin
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoAdminCSI getAdmin()
	{
		return admin;
	}

	/**
	 * Sets the admin value for this TipoEntradaConsultarSaldo.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoAdminCSI admin
	)
	{
		this.admin = admin;
	}

	/**
	 * Gets the usuario value for this TipoEntradaConsultarSaldo.
	 *
	 * @return usuario
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoUsuarioCSI getUsuario()
	{
		return usuario;
	}

	/**
	 * Sets the usuario value for this TipoEntradaConsultarSaldo.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoUsuarioCSI usuario
	)
	{
		this.usuario = usuario;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarSaldo))
			return false;

		TipoEntradaConsultarSaldo other = (TipoEntradaConsultarSaldo)obj;

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
				|| ((this.admin != null) && this.admin.equals(other.getAdmin())))
				&& (((this.usuario == null) && (other.getUsuario() == null))
				|| ((this.usuario != null) && this.usuario.equals(other.getUsuario())));
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

		if(getUsuario() != null)
			_hashCode += getUsuario().hashCode();

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
