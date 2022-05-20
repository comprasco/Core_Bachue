/**
 * TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaObtenerCausalesCorreccionListaCausalesCausal implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6931242533525726250L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        ">>tipoSalidaObtenerCausalesCorreccion>listaCausales>Causal"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idGrupoCausal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "idGrupoCausal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descGrupoCausal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "descGrupoCausal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idCausalCorreccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "idCausalCorreccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descCausalCorreccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "descCausalCorreccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionCausal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "descripcionCausal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc         = null;
	
	/** Propiedad desc causal correccion. */
	private java.lang.String descCausalCorreccion;
	
	/** Propiedad desc grupo causal. */
	private java.lang.String descGrupoCausal;
	
	/** Propiedad descripcion causal. */
	private java.lang.String descripcionCausal;
	
	/** Propiedad id causal correccion. */
	private java.lang.String idCausalCorreccion;
	
	/** Propiedad id grupo causal. */
	private java.lang.String idGrupoCausal;
	
	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida obtener causales correccion lista causales causal.
	 */
	public TipoSalidaObtenerCausalesCorreccionListaCausalesCausal()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida obtener causales correccion lista causales causal.
	 *
	 * @param idGrupoCausal de id grupo causal
	 * @param descGrupoCausal de desc grupo causal
	 * @param idCausalCorreccion de id causal correccion
	 * @param descCausalCorreccion de desc causal correccion
	 * @param descripcionCausal de descripcion causal
	 */
	public TipoSalidaObtenerCausalesCorreccionListaCausalesCausal(
	    java.lang.String idGrupoCausal, java.lang.String descGrupoCausal, java.lang.String idCausalCorreccion,
	    java.lang.String descCausalCorreccion, java.lang.String descripcionCausal
	)
	{
		this.idGrupoCausal            = idGrupoCausal;
		this.descGrupoCausal          = descGrupoCausal;
		this.idCausalCorreccion       = idCausalCorreccion;
		this.descCausalCorreccion     = descCausalCorreccion;
		this.descripcionCausal        = descripcionCausal;
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

	/**
	 * Sets the descCausalCorreccion value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @param descCausalCorreccion de desc causal correccion
	 */
	public void setDescCausalCorreccion(java.lang.String descCausalCorreccion)
	{
		this.descCausalCorreccion = descCausalCorreccion;
	}

	/**
	 * Gets the descCausalCorreccion value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @return descCausalCorreccion
	 */
	public java.lang.String getDescCausalCorreccion()
	{
		return descCausalCorreccion;
	}

	/**
	 * Sets the descGrupoCausal value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @param descGrupoCausal de desc grupo causal
	 */
	public void setDescGrupoCausal(java.lang.String descGrupoCausal)
	{
		this.descGrupoCausal = descGrupoCausal;
	}

	/**
	 * Gets the descGrupoCausal value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @return descGrupoCausal
	 */
	public java.lang.String getDescGrupoCausal()
	{
		return descGrupoCausal;
	}

	/**
	 * Sets the descripcionCausal value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @param descripcionCausal de descripcion causal
	 */
	public void setDescripcionCausal(java.lang.String descripcionCausal)
	{
		this.descripcionCausal = descripcionCausal;
	}

	/**
	 * Gets the descripcionCausal value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @return descripcionCausal
	 */
	public java.lang.String getDescripcionCausal()
	{
		return descripcionCausal;
	}

	/**
	 * Sets the idCausalCorreccion value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @param idCausalCorreccion de id causal correccion
	 */
	public void setIdCausalCorreccion(java.lang.String idCausalCorreccion)
	{
		this.idCausalCorreccion = idCausalCorreccion;
	}

	/**
	 * Gets the idCausalCorreccion value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @return idCausalCorreccion
	 */
	public java.lang.String getIdCausalCorreccion()
	{
		return idCausalCorreccion;
	}

	/**
	 * Sets the idGrupoCausal value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @param idGrupoCausal de id grupo causal
	 */
	public void setIdGrupoCausal(java.lang.String idGrupoCausal)
	{
		this.idGrupoCausal = idGrupoCausal;
	}

	/**
	 * Gets the idGrupoCausal value for this TipoSalidaObtenerCausalesCorreccionListaCausalesCausal.
	 *
	 * @return idGrupoCausal
	 */
	public java.lang.String getIdGrupoCausal()
	{
		return idGrupoCausal;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaObtenerCausalesCorreccionListaCausalesCausal))
			return false;

		TipoSalidaObtenerCausalesCorreccionListaCausalesCausal other = (TipoSalidaObtenerCausalesCorreccionListaCausalesCausal)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idGrupoCausal == null) && (other.getIdGrupoCausal() == null))
				|| ((this.idGrupoCausal != null) && this.idGrupoCausal.equals(other.getIdGrupoCausal())))
				&& (((this.descGrupoCausal == null) && (other.getDescGrupoCausal() == null))
				|| ((this.descGrupoCausal != null) && this.descGrupoCausal.equals(other.getDescGrupoCausal())))
				&& (((this.idCausalCorreccion == null) && (other.getIdCausalCorreccion() == null))
				|| ((this.idCausalCorreccion != null) && this.idCausalCorreccion.equals(other.getIdCausalCorreccion())))
				&& (((this.descCausalCorreccion == null) && (other.getDescCausalCorreccion() == null))
				|| ((this.descCausalCorreccion != null)
				&& this.descCausalCorreccion.equals(other.getDescCausalCorreccion())))
				&& (((this.descripcionCausal == null) && (other.getDescripcionCausal() == null))
				|| ((this.descripcionCausal != null) && this.descripcionCausal.equals(other.getDescripcionCausal())));
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

		if(getIdGrupoCausal() != null)
			_hashCode += getIdGrupoCausal().hashCode();

		if(getDescGrupoCausal() != null)
			_hashCode += getDescGrupoCausal().hashCode();

		if(getIdCausalCorreccion() != null)
			_hashCode += getIdCausalCorreccion().hashCode();

		if(getDescCausalCorreccion() != null)
			_hashCode += getDescCausalCorreccion().hashCode();

		if(getDescripcionCausal() != null)
			_hashCode += getDescripcionCausal().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
