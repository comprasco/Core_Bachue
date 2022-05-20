/**
 * TipoDestinatarioEMI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1;


/**
 * Clase que contiene todos las propiedades TipoDestinatarioEMI.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/03/2020
 */
public class TipoDestinatarioEMI implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5020861017188109655L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoDestinatarioEMI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoDestinatarioEMI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionCorreoElectronicoDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "direccionCorreoElectronicoDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionCorreoFisicoDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "direccionCorreoFisicoDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroTelefonoDestinatario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1",
		        "numeroTelefonoDestinatario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad direccion correo electronico destinatario. */
	private java.lang.String direccionCorreoElectronicoDestinatario;

	/** Propiedad direccion correo fisico destinatario. */
	private java.lang.String direccionCorreoFisicoDestinatario;

	/** Propiedad numero telefono destinatario. */
	private java.math.BigInteger numeroTelefonoDestinatario;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo destinatario EMI.
	 */
	public TipoDestinatarioEMI()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo destinatario EMI.
	 *
	 * @param direccionCorreoElectronicoDestinatario de direccion correo electronico destinatario
	 * @param direccionCorreoFisicoDestinatario de direccion correo fisico destinatario
	 * @param numeroTelefonoDestinatario de numero telefono destinatario
	 */
	public TipoDestinatarioEMI(
	    java.lang.String direccionCorreoElectronicoDestinatario, java.lang.String direccionCorreoFisicoDestinatario,
	    java.math.BigInteger numeroTelefonoDestinatario
	)
	{
		this.direccionCorreoElectronicoDestinatario     = direccionCorreoElectronicoDestinatario;
		this.direccionCorreoFisicoDestinatario          = direccionCorreoFisicoDestinatario;
		this.numeroTelefonoDestinatario                 = numeroTelefonoDestinatario;
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
	 * Sets the direccionCorreoElectronicoDestinatario value for this TipoDestinatarioEMI.
	 *
	 * @param direccionCorreoElectronicoDestinatario de direccion correo electronico destinatario
	 */
	public void setDireccionCorreoElectronicoDestinatario(java.lang.String direccionCorreoElectronicoDestinatario)
	{
		this.direccionCorreoElectronicoDestinatario = direccionCorreoElectronicoDestinatario;
	}

	/**
	 * Gets the direccionCorreoElectronicoDestinatario value for this TipoDestinatarioEMI.
	 *
	 * @return direccionCorreoElectronicoDestinatario
	 */
	public java.lang.String getDireccionCorreoElectronicoDestinatario()
	{
		return direccionCorreoElectronicoDestinatario;
	}

	/**
	 * Sets the direccionCorreoFisicoDestinatario value for this TipoDestinatarioEMI.
	 *
	 * @param direccionCorreoFisicoDestinatario de direccion correo fisico destinatario
	 */
	public void setDireccionCorreoFisicoDestinatario(java.lang.String direccionCorreoFisicoDestinatario)
	{
		this.direccionCorreoFisicoDestinatario = direccionCorreoFisicoDestinatario;
	}

	/**
	 * Gets the direccionCorreoFisicoDestinatario value for this TipoDestinatarioEMI.
	 *
	 * @return direccionCorreoFisicoDestinatario
	 */
	public java.lang.String getDireccionCorreoFisicoDestinatario()
	{
		return direccionCorreoFisicoDestinatario;
	}

	/**
	 * Sets the numeroTelefonoDestinatario value for this TipoDestinatarioEMI.
	 *
	 * @param numeroTelefonoDestinatario de numero telefono destinatario
	 */
	public void setNumeroTelefonoDestinatario(java.math.BigInteger numeroTelefonoDestinatario)
	{
		this.numeroTelefonoDestinatario = numeroTelefonoDestinatario;
	}

	/**
	 * Gets the numeroTelefonoDestinatario value for this TipoDestinatarioEMI.
	 *
	 * @return numeroTelefonoDestinatario
	 */
	public java.math.BigInteger getNumeroTelefonoDestinatario()
	{
		return numeroTelefonoDestinatario;
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
		if(!(obj instanceof TipoDestinatarioEMI))
			return false;

		TipoDestinatarioEMI other = (TipoDestinatarioEMI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.direccionCorreoElectronicoDestinatario == null)
				&& (other.getDireccionCorreoElectronicoDestinatario() == null))
				|| ((this.direccionCorreoElectronicoDestinatario != null)
				&& this.direccionCorreoElectronicoDestinatario.equals(
				    other.getDireccionCorreoElectronicoDestinatario()
				)))
				&& (((this.direccionCorreoFisicoDestinatario == null)
				&& (other.getDireccionCorreoFisicoDestinatario() == null))
				|| ((this.direccionCorreoFisicoDestinatario != null)
				&& this.direccionCorreoFisicoDestinatario.equals(other.getDireccionCorreoFisicoDestinatario())))
				&& (((this.numeroTelefonoDestinatario == null) && (other.getNumeroTelefonoDestinatario() == null))
				|| ((this.numeroTelefonoDestinatario != null)
				&& this.numeroTelefonoDestinatario.equals(other.getNumeroTelefonoDestinatario())));
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

		if(getDireccionCorreoElectronicoDestinatario() != null)
			_hashCode += getDireccionCorreoElectronicoDestinatario().hashCode();

		if(getDireccionCorreoFisicoDestinatario() != null)
			_hashCode += getDireccionCorreoFisicoDestinatario().hashCode();

		if(getNumeroTelefonoDestinatario() != null)
			_hashCode += getNumeroTelefonoDestinatario().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
