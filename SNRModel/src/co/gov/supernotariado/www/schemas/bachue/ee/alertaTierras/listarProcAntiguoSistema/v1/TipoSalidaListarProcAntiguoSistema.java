/**
 * TipoSalidaListarProcAntiguoSistema.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1;



/**
 * Clase que contiene todos las propiedades TipoSalidaListarProcAntiguoSistema.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class TipoSalidaListarProcAntiguoSistema implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4338481214219405527L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaListarProcAntiguoSistema.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "tipoSalidaListarProcAntiguoSistema"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "mensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaProcesosAS");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "listaProcesosAS"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        ">>tipoSalidaListarProcAntiguoSistema>listaProcesosAS>procesoAS"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/alertaTierras/listarProcAntiguoSistema/v1",
		        "procesoAS"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo. */
	private java.lang.String codigo;

	/** Propiedad mensaje. */
	private java.lang.String mensaje;

	/** Propiedad lista procesos AS. */
	private co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[] listaProcesosAS;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida listar proc antiguo sistema.
	 */
	public TipoSalidaListarProcAntiguoSistema()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida listar proc antiguo sistema.
	 *
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 * @param listaProcesosAS de lista procesos AS
	 */
	public TipoSalidaListarProcAntiguoSistema(
	    java.lang.String codigo, java.lang.String mensaje,
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[] listaProcesosAS
	)
	{
		this.codigo              = codigo;
		this.mensaje             = mensaje;
		this.listaProcesosAS     = listaProcesosAS;
	}

	/**
	 * Gets the codigo value for this TipoSalidaListarProcAntiguoSistema.
	 *
	 * @return codigo
	 */
	public java.lang.String getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the codigo value for this TipoSalidaListarProcAntiguoSistema.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(java.lang.String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje value for this TipoSalidaListarProcAntiguoSistema.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
	}

	/**
	 * Sets the mensaje value for this TipoSalidaListarProcAntiguoSistema.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the listaProcesosAS value for this TipoSalidaListarProcAntiguoSistema.
	 *
	 * @return listaProcesosAS
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[] getListaProcesosAS()
	{
		return listaProcesosAS;
	}

	/**
	 * Sets the listaProcesosAS value for this TipoSalidaListarProcAntiguoSistema.
	 *
	 * @param listaProcesosAS de lista procesos AS
	 */
	public void setListaProcesosAS(
	    co.gov.supernotariado.www.schemas.bachue.ee.alertaTierras.listarProcAntiguoSistema.v1.TipoSalidaListarProcAntiguoSistemaListaProcesosASProcesoAS[] listaProcesosAS
	)
	{
		this.listaProcesosAS = listaProcesosAS;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaListarProcAntiguoSistema))
			return false;

		TipoSalidaListarProcAntiguoSistema other = (TipoSalidaListarProcAntiguoSistema)obj;

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
				&& (((this.listaProcesosAS == null) && (other.getListaProcesosAS() == null))
				|| ((this.listaProcesosAS != null)
				&& java.util.Arrays.equals(this.listaProcesosAS, other.getListaProcesosAS())));
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

		if(getListaProcesosAS() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaProcesosAS()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaProcesosAS(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

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
