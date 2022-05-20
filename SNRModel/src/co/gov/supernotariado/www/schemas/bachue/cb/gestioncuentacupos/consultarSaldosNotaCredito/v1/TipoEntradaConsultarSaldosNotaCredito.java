/**
 * TipoEntradaConsultarSaldosNotaCredito.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarSaldosNotaCredito.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/09/2020
 */
public class TipoEntradaConsultarSaldosNotaCredito implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1342799909523791857L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarSaldosNotaCredito.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "tipoEntradaConsultarSaldosNotaCredito"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("IDCuentaCupo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "admin"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "tipoAdminCSI"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad ID cuenta cupo. */
	private java.lang.String IDCuentaCupo;

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad admin. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoAdminCSI admin;

	/** Propiedad modulo. */
	private java.lang.String modulo;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar saldos nota credito.
	 */
	public TipoEntradaConsultarSaldosNotaCredito()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar saldos nota credito.
	 *
	 * @param modulo de modulo
	 * @param IDCuentaCupo de ID cuenta cupo
	 * @param admin de admin
	 */
	public TipoEntradaConsultarSaldosNotaCredito(
	    java.lang.String modulo, java.lang.String IDCuentaCupo,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoAdminCSI admin
	)
	{
		this.modulo           = modulo;
		this.IDCuentaCupo     = IDCuentaCupo;
		this.admin            = admin;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarSaldosNotaCredito.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the modulo value for this TipoEntradaConsultarSaldosNotaCredito.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the IDCuentaCupo value for this TipoEntradaConsultarSaldosNotaCredito.
	 *
	 * @return IDCuentaCupo
	 */
	public java.lang.String getIDCuentaCupo()
	{
		return IDCuentaCupo;
	}

	/**
	 * Sets the IDCuentaCupo value for this TipoEntradaConsultarSaldosNotaCredito.
	 *
	 * @param IDCuentaCupo de ID cuenta cupo
	 */
	public void setIDCuentaCupo(java.lang.String IDCuentaCupo)
	{
		this.IDCuentaCupo = IDCuentaCupo;
	}

	/**
	 * Gets the admin value for this TipoEntradaConsultarSaldosNotaCredito.
	 *
	 * @return admin
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoAdminCSI getAdmin()
	{
		return admin;
	}

	/**
	 * Sets the admin value for this TipoEntradaConsultarSaldosNotaCredito.
	 *
	 * @param admin de admin
	 */
	public void setAdmin(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoAdminCSI admin
	)
	{
		this.admin = admin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarSaldosNotaCredito))
			return false;

		TipoEntradaConsultarSaldosNotaCredito other = (TipoEntradaConsultarSaldosNotaCredito)obj;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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
