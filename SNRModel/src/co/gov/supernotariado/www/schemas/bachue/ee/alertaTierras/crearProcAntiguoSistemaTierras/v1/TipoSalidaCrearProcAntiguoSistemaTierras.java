/**
 * TipoSalidaCrearProcAntiguoSistemaTierras.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.crearProcAntiguoSistemaTierras.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaCrearProcAntiguoSistemaTierras.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaCrearProcAntiguoSistemaTierras implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6529137944763650430L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaCrearProcAntiguoSistemaTierras.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "tipoSalidaCrearProcAntiguoSistemaTierras"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "codigo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "mensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idAntiguoSistemaTierras");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/crearProcAntiguoSistemaTierras/v1",
		        "idAntiguoSistemaTierras"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo. */
	private java.lang.String codigo;

	/** Propiedad mensaje. */
	private java.lang.String mensaje;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/** Propiedad id antiguo sistema tierras. */
	private int idAntiguoSistemaTierras;

	/**
	 * Instancia un nuevo objeto tipo salida crear proc antiguo sistema tierras.
	 */
	public TipoSalidaCrearProcAntiguoSistemaTierras()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida crear proc antiguo sistema tierras.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param idAntiguoSistemaTierras de id antiguo sistema tierras
	 */
	public TipoSalidaCrearProcAntiguoSistemaTierras(
	    java.lang.String codigo, java.lang.String mensaje, int idAntiguoSistemaTierras
	)
	{
		this.codigo                      = codigo;
		this.mensaje                     = mensaje;
		this.idAntiguoSistemaTierras     = idAntiguoSistemaTierras;
	}

	/**
	 * Gets the codigo value for this TipoSalidaCrearProcAntiguoSistemaTierras.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the codigo value for this TipoSalidaCrearProcAntiguoSistemaTierras.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje value for this TipoSalidaCrearProcAntiguoSistemaTierras.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
	}

	/**
	 * Sets the mensaje value for this TipoSalidaCrearProcAntiguoSistemaTierras.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the idAntiguoSistemaTierras value for this TipoSalidaCrearProcAntiguoSistemaTierras.
	 *
	 * @return idAntiguoSistemaTierras
	 */
	public int getIdAntiguoSistemaTierras()
	{
		return idAntiguoSistemaTierras;
	}

	/**
	 * Sets the idAntiguoSistemaTierras value for this TipoSalidaCrearProcAntiguoSistemaTierras.
	 *
	 * @param idAntiguoSistemaTierras de id antiguo sistema tierras
	 */
	public void setIdAntiguoSistemaTierras(int idAntiguoSistemaTierras)
	{
		this.idAntiguoSistemaTierras = idAntiguoSistemaTierras;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaCrearProcAntiguoSistemaTierras))
			return false;

		TipoSalidaCrearProcAntiguoSistemaTierras other = (TipoSalidaCrearProcAntiguoSistemaTierras)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.mensaje == null) && (other.getMensaje() == null))
				|| ((this.mensaje != null) && this.mensaje.equals(other.getMensaje())))
				&& (this.idAntiguoSistemaTierras == other.getIdAntiguoSistemaTierras());
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

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getMensaje() != null)
			_hashCode += getMensaje().hashCode();

		_hashCode += getIdAntiguoSistemaTierras();
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
