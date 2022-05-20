/**
 * TipoVariableEMI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1;


/**
 * Clase que contiene todos las propiedades TipoVariableEMI.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 25/03/2020
 */
public class TipoVariableEMI implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4256068951837341947L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoVariableEMI.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipoVariableEMI"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "tipo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "codigo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("criterio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cn/mensajero/enviarmensaje/v1", "criterio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo. */
	private java.lang.String codigo;

	/** Propiedad criterio. */
	private java.lang.String criterio;

	/** Propiedad tipo. */
	private java.lang.String tipo;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo variable EMI.
	 */
	public TipoVariableEMI()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo variable EMI.
	 *
	 * @param tipo de tipo
	 * @param codigo de codigo
	 * @param criterio de criterio
	 */
	public TipoVariableEMI(java.lang.String tipo, java.lang.String codigo, java.lang.String criterio)
	{
		this.tipo         = tipo;
		this.codigo       = codigo;
		this.criterio     = criterio;
	}

	/**
	 * Sets the codigo value for this TipoVariableEMI.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo value for this TipoVariableEMI.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the criterio value for this TipoVariableEMI.
	 *
	 * @param criterio de criterio
	 */
	public void setCriterio(java.lang.String criterio)
	{
		this.criterio = criterio;
	}

	/**
	 * Gets the criterio value for this TipoVariableEMI.
	 *
	 * @return criterio
	 */
	public java.lang.String getCriterio()
	{
		return criterio;
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
	 * Sets the tipo value for this TipoVariableEMI.
	 *
	 * @param tipo de tipo
	 */
	public void setTipo(java.lang.String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * Gets the tipo value for this TipoVariableEMI.
	 *
	 * @return tipo
	 */
	public java.lang.String getTipo()
	{
		return tipo;
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
		if(!(obj instanceof TipoVariableEMI))
			return false;

		TipoVariableEMI other = (TipoVariableEMI)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipo == null) && (other.getTipo() == null))
				|| ((this.tipo != null) && this.tipo.equals(other.getTipo())))
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.criterio == null) && (other.getCriterio() == null))
				|| ((this.criterio != null) && this.criterio.equals(other.getCriterio())));
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

		if(getTipo() != null)
			_hashCode += getTipo().hashCode();

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getCriterio() != null)
			_hashCode += getCriterio().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
