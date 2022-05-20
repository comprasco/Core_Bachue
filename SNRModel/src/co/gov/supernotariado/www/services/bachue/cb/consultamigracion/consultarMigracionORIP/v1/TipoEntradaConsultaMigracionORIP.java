/**
 * TipoEntradaConsultaMigracionORIP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1;



/**
 * El esquema define los
 *                         datos de entrada para saber si se busca en Nodo Central o en
 * los
 *                         Servicios de Bachué dado el código de la ORIP.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultaMigracionORIP implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3057916422809528549L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultaMigracionORIP.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
		        "tipoEntradaConsultaMigracionORIP"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCirculoRegistral");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/services/bachue/cb/consultamigracion/consultarMigracionORIP/v1",
		        "codCirculoRegistral"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod circulo registral. */
	/* Código de la ORIP */
	private java.lang.String codCirculoRegistral;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consulta migracion ORIP.
	 */
	public TipoEntradaConsultaMigracionORIP()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consulta migracion ORIP.
	 *
	 * @param codCirculoRegistral de cod circulo registral
	 */
	public TipoEntradaConsultaMigracionORIP(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Sets the codCirculoRegistral value for this TipoEntradaConsultaMigracionORIP.
	 *
	 * @param codCirculoRegistral   * Código de la ORIP
	 */
	public void setCodCirculoRegistral(java.lang.String codCirculoRegistral)
	{
		this.codCirculoRegistral = codCirculoRegistral;
	}

	/**
	 * Gets the codCirculoRegistral value for this TipoEntradaConsultaMigracionORIP.
	 *
	 * @return codCirculoRegistral   * Código de la ORIP
	 */
	public java.lang.String getCodCirculoRegistral()
	{
		return codCirculoRegistral;
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
		if(!(obj instanceof TipoEntradaConsultaMigracionORIP))
			return false;

		TipoEntradaConsultaMigracionORIP other = (TipoEntradaConsultaMigracionORIP)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codCirculoRegistral == null) && (other.getCodCirculoRegistral() == null))
				|| ((this.codCirculoRegistral != null)
				&& this.codCirculoRegistral.equals(other.getCodCirculoRegistral())));
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

		if(getCodCirculoRegistral() != null)
			_hashCode += getCodCirculoRegistral().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
