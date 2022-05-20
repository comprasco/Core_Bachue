/**
 * TipoEntradaActivarAlertaTierras.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.activarAlertaTierras.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaActivarAlertaTierras.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoEntradaActivarAlertaTierras implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1901492357351346052L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaActivarAlertaTierras.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1",
		        "tipoEntradaActivarAlertaTierras"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAlerta");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/activarAlertaTierras/v1", "idAlerta"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad id alerta. */
	private int idAlerta;

	/**
	 * Instancia un nuevo objeto tipo entrada activar alerta tierras.
	 */
	public TipoEntradaActivarAlertaTierras()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada activar alerta tierras.
	 *
	 * @param idAlerta de id alerta
	 */
	public TipoEntradaActivarAlertaTierras(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/**
	 * Gets the idAlerta value for this TipoEntradaActivarAlertaTierras.
	 *
	 * @return idAlerta
	 */
	public int getIdAlerta()
	{
		return idAlerta;
	}

	/**
	 * Sets the idAlerta value for this TipoEntradaActivarAlertaTierras.
	 *
	 * @param idAlerta de id alerta
	 */
	public void setIdAlerta(int idAlerta)
	{
		this.idAlerta = idAlerta;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaActivarAlertaTierras))
			return false;

		TipoEntradaActivarAlertaTierras other = (TipoEntradaActivarAlertaTierras)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true && (this.idAlerta == other.getIdAlerta());
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
		_hashCode += getIdAlerta();
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
