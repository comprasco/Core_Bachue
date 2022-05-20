/**
 * TipoEntradaPagarCuentaCupo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1;



/**
 * The Class TipoEntradaPagarCuentaCupo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaPagarCuentaCupo implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5589550798309149548L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaPagarCuentaCupo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
		        "tipoEntradaPagarCuentaCupo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1", "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("IDCuentaCupo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
		        "IDCuentaCupo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("correoElectronicoCorporativoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
		        "correoElectronicoCorporativoUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("montoPago");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1", "montoPago"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaPago");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1", "fechaPago"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("referenciaPago");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/pagarcuentacupo/v1",
		        "referenciaPago"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The ID cuenta cupo. */
	private java.lang.String IDCuentaCupo;

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The correo electronico corporativo usuario. */
	private java.lang.String correoElectronicoCorporativoUsuario;

	/** The fecha pago. */
	private java.util.Calendar fechaPago;

	/** The modulo. */
	private java.lang.String modulo;

	/** The monto pago. */
	private java.math.BigDecimal montoPago;

	/** The referencia pago. */
	private java.lang.String referenciaPago;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo entrada pagar cuenta cupo.
	 */
	public TipoEntradaPagarCuentaCupo()
	{
	}

	/**
	 * Instantiates a new tipo entrada pagar cuenta cupo.
	 *
	 * @param modulo the modulo
	 * @param IDCuentaCupo the ID cuenta cupo
	 * @param correoElectronicoCorporativoUsuario the correo electronico corporativo usuario
	 * @param montoPago the monto pago
	 * @param fechaPago the fecha pago
	 * @param referenciaPago the referencia pago
	 */
	public TipoEntradaPagarCuentaCupo(
	    java.lang.String modulo, java.lang.String IDCuentaCupo, java.lang.String correoElectronicoCorporativoUsuario,
	    java.math.BigDecimal montoPago, java.util.Calendar fechaPago, java.lang.String referenciaPago
	)
	{
		this.modulo                                  = modulo;
		this.IDCuentaCupo                            = IDCuentaCupo;
		this.correoElectronicoCorporativoUsuario     = correoElectronicoCorporativoUsuario;
		this.montoPago                               = montoPago;
		this.fechaPago                               = fechaPago;
		this.referenciaPago                          = referenciaPago;
	}

	/**
	 * Gets the modulo value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the modulo value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @param modulo the new modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the IDCuentaCupo value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @return IDCuentaCupo
	 */
	public java.lang.String getIDCuentaCupo()
	{
		return IDCuentaCupo;
	}

	/**
	 * Sets the IDCuentaCupo value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @param IDCuentaCupo the new ID cuenta cupo
	 */
	public void setIDCuentaCupo(java.lang.String IDCuentaCupo)
	{
		this.IDCuentaCupo = IDCuentaCupo;
	}

	/**
	 * Gets the correoElectronicoCorporativoUsuario value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @return correoElectronicoCorporativoUsuario
	 */
	public java.lang.String getCorreoElectronicoCorporativoUsuario()
	{
		return correoElectronicoCorporativoUsuario;
	}

	/**
	 * Sets the correoElectronicoCorporativoUsuario value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @param correoElectronicoCorporativoUsuario the new correo electronico corporativo usuario
	 */
	public void setCorreoElectronicoCorporativoUsuario(java.lang.String correoElectronicoCorporativoUsuario)
	{
		this.correoElectronicoCorporativoUsuario = correoElectronicoCorporativoUsuario;
	}

	/**
	 * Gets the montoPago value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @return montoPago
	 */
	public java.math.BigDecimal getMontoPago()
	{
		return montoPago;
	}

	/**
	 * Sets the montoPago value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @param montoPago the new monto pago
	 */
	public void setMontoPago(java.math.BigDecimal montoPago)
	{
		this.montoPago = montoPago;
	}

	/**
	 * Gets the fechaPago value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @return fechaPago
	 */
	public java.util.Calendar getFechaPago()
	{
		return fechaPago;
	}

	/**
	 * Sets the fechaPago value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @param fechaPago the new fecha pago
	 */
	public void setFechaPago(java.util.Calendar fechaPago)
	{
		this.fechaPago = fechaPago;
	}

	/**
	 * Gets the referenciaPago value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @return referenciaPago
	 */
	public java.lang.String getReferenciaPago()
	{
		return referenciaPago;
	}

	/**
	 * Sets the referenciaPago value for this TipoEntradaPagarCuentaCupo.
	 *
	 * @param referenciaPago the new referencia pago
	 */
	public void setReferenciaPago(java.lang.String referenciaPago)
	{
		this.referenciaPago = referenciaPago;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaPagarCuentaCupo))
			return false;

		TipoEntradaPagarCuentaCupo other = (TipoEntradaPagarCuentaCupo)obj;

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
				&& (((this.correoElectronicoCorporativoUsuario == null)
				&& (other.getCorreoElectronicoCorporativoUsuario() == null))
				|| ((this.correoElectronicoCorporativoUsuario != null)
				&& this.correoElectronicoCorporativoUsuario.equals(other.getCorreoElectronicoCorporativoUsuario())))
				&& (((this.montoPago == null) && (other.getMontoPago() == null))
				|| ((this.montoPago != null) && this.montoPago.equals(other.getMontoPago())))
				&& (((this.fechaPago == null) && (other.getFechaPago() == null))
				|| ((this.fechaPago != null) && this.fechaPago.equals(other.getFechaPago())))
				&& (((this.referenciaPago == null) && (other.getReferenciaPago() == null))
				|| ((this.referenciaPago != null) && this.referenciaPago.equals(other.getReferenciaPago())));
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

		if(getCorreoElectronicoCorporativoUsuario() != null)
			_hashCode += getCorreoElectronicoCorporativoUsuario().hashCode();

		if(getMontoPago() != null)
			_hashCode += getMontoPago().hashCode();

		if(getFechaPago() != null)
			_hashCode += getFechaPago().hashCode();

		if(getReferenciaPago() != null)
			_hashCode += getReferenciaPago().hashCode();

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
