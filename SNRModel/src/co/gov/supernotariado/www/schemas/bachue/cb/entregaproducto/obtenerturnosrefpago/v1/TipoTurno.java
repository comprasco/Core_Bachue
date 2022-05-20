/**
 * TipoTurno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1;


/**
 * Clase que contiene todos las propiedades TipoTurno.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoTurno implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6235682033650957569L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoTurno.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "tipoTurno"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("identificadorTurno");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "identificadorTurno"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc       = null;
	
	/** Propiedad identificador turno. */
	private java.lang.String identificadorTurno;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc     = false;

	/**
	 * Instancia un nuevo objeto tipo turno.
	 */
	public TipoTurno()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo turno.
	 *
	 * @param identificadorTurno de identificador turno
	 */
	public TipoTurno(java.lang.String identificadorTurno)
	{
		this.identificadorTurno = identificadorTurno;
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
	 * Sets the identificadorTurno value for this TipoTurno.
	 *
	 * @param identificadorTurno de identificador turno
	 */
	public void setIdentificadorTurno(java.lang.String identificadorTurno)
	{
		this.identificadorTurno = identificadorTurno;
	}

	/**
	 * Gets the identificadorTurno value for this TipoTurno.
	 *
	 * @return identificadorTurno
	 */
	public java.lang.String getIdentificadorTurno()
	{
		return identificadorTurno;
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
		if(!(obj instanceof TipoTurno))
			return false;

		TipoTurno other = (TipoTurno)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.identificadorTurno == null) && (other.getIdentificadorTurno() == null))
				|| ((this.identificadorTurno != null) && this.identificadorTurno.equals(other.getIdentificadorTurno())));
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

		if(getIdentificadorTurno() != null)
			_hashCode += getIdentificadorTurno().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
