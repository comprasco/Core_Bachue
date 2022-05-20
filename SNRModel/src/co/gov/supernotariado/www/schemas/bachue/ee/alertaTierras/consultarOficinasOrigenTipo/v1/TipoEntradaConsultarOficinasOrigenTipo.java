/**
 * TipoEntradaConsultarOficinasOrigenTipo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.consultarOficinasOrigenTipo.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarOficinasOrigenTipo.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaConsultarOficinasOrigenTipo implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3050284761873343250L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarOficinasOrigenTipo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1",
		        "tipoEntradaConsultarOficinasOrigenTipo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoOficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1",
		        "tipoOficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoOficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1",
		        "codigoOficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nomParcialOficinaOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/consultarOficinasOrigenTipo/v1",
		        "nomParcialOficinaOrigen"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo oficina origen. */
	private java.lang.String codigoOficinaOrigen;

	/** Propiedad nom parcial oficina origen. */
	private java.lang.String nomParcialOficinaOrigen;

	/** Propiedad tipo oficina origen. */
	private java.lang.String tipoOficinaOrigen;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar oficinas origen tipo.
	 */
	public TipoEntradaConsultarOficinasOrigenTipo()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar oficinas origen tipo.
	 *
	 * @param tipoOficinaOrigen de tipo oficina origen
	 * @param codigoOficinaOrigen de codigo oficina origen
	 * @param nomParcialOficinaOrigen de nom parcial oficina origen
	 */
	public TipoEntradaConsultarOficinasOrigenTipo(
	    java.lang.String tipoOficinaOrigen, java.lang.String codigoOficinaOrigen,
	    java.lang.String nomParcialOficinaOrigen
	)
	{
		this.tipoOficinaOrigen           = tipoOficinaOrigen;
		this.codigoOficinaOrigen         = codigoOficinaOrigen;
		this.nomParcialOficinaOrigen     = nomParcialOficinaOrigen;
	}

	/**
	 * Gets the tipoOficinaOrigen value for this TipoEntradaConsultarOficinasOrigenTipo.
	 *
	 * @return tipoOficinaOrigen
	 */
	public java.lang.String getTipoOficinaOrigen()
	{
		return tipoOficinaOrigen;
	}

	/**
	 * Sets the tipoOficinaOrigen value for this TipoEntradaConsultarOficinasOrigenTipo.
	 *
	 * @param tipoOficinaOrigen de tipo oficina origen
	 */
	public void setTipoOficinaOrigen(java.lang.String tipoOficinaOrigen)
	{
		this.tipoOficinaOrigen = tipoOficinaOrigen;
	}

	/**
	 * Gets the codigoOficinaOrigen value for this TipoEntradaConsultarOficinasOrigenTipo.
	 *
	 * @return codigoOficinaOrigen
	 */
	public java.lang.String getCodigoOficinaOrigen()
	{
		return codigoOficinaOrigen;
	}

	/**
	 * Sets the codigoOficinaOrigen value for this TipoEntradaConsultarOficinasOrigenTipo.
	 *
	 * @param codigoOficinaOrigen de codigo oficina origen
	 */
	public void setCodigoOficinaOrigen(java.lang.String codigoOficinaOrigen)
	{
		this.codigoOficinaOrigen = codigoOficinaOrigen;
	}

	/**
	 * Gets the nomParcialOficinaOrigen value for this TipoEntradaConsultarOficinasOrigenTipo.
	 *
	 * @return nomParcialOficinaOrigen
	 */
	public java.lang.String getNomParcialOficinaOrigen()
	{
		return nomParcialOficinaOrigen;
	}

	/**
	 * Sets the nomParcialOficinaOrigen value for this TipoEntradaConsultarOficinasOrigenTipo.
	 *
	 * @param nomParcialOficinaOrigen de nom parcial oficina origen
	 */
	public void setNomParcialOficinaOrigen(java.lang.String nomParcialOficinaOrigen)
	{
		this.nomParcialOficinaOrigen = nomParcialOficinaOrigen;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarOficinasOrigenTipo))
			return false;

		TipoEntradaConsultarOficinasOrigenTipo other = (TipoEntradaConsultarOficinasOrigenTipo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoOficinaOrigen == null) && (other.getTipoOficinaOrigen() == null))
				|| ((this.tipoOficinaOrigen != null) && this.tipoOficinaOrigen.equals(other.getTipoOficinaOrigen())))
				&& (((this.codigoOficinaOrigen == null) && (other.getCodigoOficinaOrigen() == null))
				|| ((this.codigoOficinaOrigen != null)
				&& this.codigoOficinaOrigen.equals(other.getCodigoOficinaOrigen())))
				&& (((this.nomParcialOficinaOrigen == null) && (other.getNomParcialOficinaOrigen() == null))
				|| ((this.nomParcialOficinaOrigen != null)
				&& this.nomParcialOficinaOrigen.equals(other.getNomParcialOficinaOrigen())));
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

		if(getTipoOficinaOrigen() != null)
			_hashCode += getTipoOficinaOrigen().hashCode();

		if(getCodigoOficinaOrigen() != null)
			_hashCode += getCodigoOficinaOrigen().hashCode();

		if(getNomParcialOficinaOrigen() != null)
			_hashCode += getNomParcialOficinaOrigen().hashCode();

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
