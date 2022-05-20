/**
 * TipoServicioCTSO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2;


/**
 * Clase que contiene todos las propiedades TipoServicioCTSO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoServicioCTSO implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2362025694179791784L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoServicioCTSO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoServicioCTSO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("subtipoServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "subtipoServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorServicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "valorServicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("direccionPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "direccionPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoPredio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "estadoPredio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object     __equalsCalc    = null;
	
	/** Propiedad direccion predio. */
	private java.lang.String     direccionPredio;
	
	/** Propiedad estado predio. */
	private java.lang.String     estadoPredio;
	
	/** Propiedad subtipo servicio. */
	private java.lang.String     subtipoServicio;
	
	/** Propiedad tipo servicio. */
	private java.lang.String     tipoServicio;
	
	/** Propiedad valor servicio. */
	private java.math.BigDecimal valorServicio;
	
	/** Propiedad hash code calc. */
	private boolean              __hashCodeCalc  = false;

	/**
	 * Instancia un nuevo objeto tipo servicio CTSO.
	 */
	public TipoServicioCTSO()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo servicio CTSO.
	 *
	 * @param tipoServicio de tipo servicio
	 * @param subtipoServicio de subtipo servicio
	 * @param valorServicio de valor servicio
	 * @param direccionPredio de direccion predio
	 * @param estadoPredio de estado predio
	 */
	public TipoServicioCTSO(
	    java.lang.String tipoServicio, java.lang.String subtipoServicio, java.math.BigDecimal valorServicio,
	    java.lang.String direccionPredio, java.lang.String estadoPredio
	)
	{
		this.tipoServicio        = tipoServicio;
		this.subtipoServicio     = subtipoServicio;
		this.valorServicio       = valorServicio;
		this.direccionPredio     = direccionPredio;
		this.estadoPredio        = estadoPredio;
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
	 * Sets the direccionPredio value for this TipoServicioCTSO.
	 *
	 * @param direccionPredio de direccion predio
	 */
	public void setDireccionPredio(java.lang.String direccionPredio)
	{
		this.direccionPredio = direccionPredio;
	}

	/**
	 * Gets the direccionPredio value for this TipoServicioCTSO.
	 *
	 * @return direccionPredio
	 */
	public java.lang.String getDireccionPredio()
	{
		return direccionPredio;
	}

	/**
	 * Sets the estadoPredio value for this TipoServicioCTSO.
	 *
	 * @param estadoPredio de estado predio
	 */
	public void setEstadoPredio(java.lang.String estadoPredio)
	{
		this.estadoPredio = estadoPredio;
	}

	/**
	 * Gets the estadoPredio value for this TipoServicioCTSO.
	 *
	 * @return estadoPredio
	 */
	public java.lang.String getEstadoPredio()
	{
		return estadoPredio;
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
	 * Sets the subtipoServicio value for this TipoServicioCTSO.
	 *
	 * @param subtipoServicio de subtipo servicio
	 */
	public void setSubtipoServicio(java.lang.String subtipoServicio)
	{
		this.subtipoServicio = subtipoServicio;
	}

	/**
	 * Gets the subtipoServicio value for this TipoServicioCTSO.
	 *
	 * @return subtipoServicio
	 */
	public java.lang.String getSubtipoServicio()
	{
		return subtipoServicio;
	}

	/**
	 * Sets the tipoServicio value for this TipoServicioCTSO.
	 *
	 * @param tipoServicio de tipo servicio
	 */
	public void setTipoServicio(java.lang.String tipoServicio)
	{
		this.tipoServicio = tipoServicio;
	}

	/**
	 * Gets the tipoServicio value for this TipoServicioCTSO.
	 *
	 * @return tipoServicio
	 */
	public java.lang.String getTipoServicio()
	{
		return tipoServicio;
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
	 * Sets the valorServicio value for this TipoServicioCTSO.
	 *
	 * @param valorServicio de valor servicio
	 */
	public void setValorServicio(java.math.BigDecimal valorServicio)
	{
		this.valorServicio = valorServicio;
	}

	/**
	 * Gets the valorServicio value for this TipoServicioCTSO.
	 *
	 * @return valorServicio
	 */
	public java.math.BigDecimal getValorServicio()
	{
		return valorServicio;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoServicioCTSO))
			return false;

		TipoServicioCTSO other = (TipoServicioCTSO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoServicio == null) && (other.getTipoServicio() == null))
				|| ((this.tipoServicio != null) && this.tipoServicio.equals(other.getTipoServicio())))
				&& (((this.subtipoServicio == null) && (other.getSubtipoServicio() == null))
				|| ((this.subtipoServicio != null) && this.subtipoServicio.equals(other.getSubtipoServicio())))
				&& (((this.valorServicio == null) && (other.getValorServicio() == null))
				|| ((this.valorServicio != null) && this.valorServicio.equals(other.getValorServicio())))
				&& (((this.direccionPredio == null) && (other.getDireccionPredio() == null))
				|| ((this.direccionPredio != null) && this.direccionPredio.equals(other.getDireccionPredio())))
				&& (((this.estadoPredio == null) && (other.getEstadoPredio() == null))
				|| ((this.estadoPredio != null) && this.estadoPredio.equals(other.getEstadoPredio())));
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

		if(getTipoServicio() != null)
			_hashCode += getTipoServicio().hashCode();

		if(getSubtipoServicio() != null)
			_hashCode += getSubtipoServicio().hashCode();

		if(getValorServicio() != null)
			_hashCode += getValorServicio().hashCode();

		if(getDireccionPredio() != null)
			_hashCode += getDireccionPredio().hashCode();

		if(getEstadoPredio() != null)
			_hashCode += getEstadoPredio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
