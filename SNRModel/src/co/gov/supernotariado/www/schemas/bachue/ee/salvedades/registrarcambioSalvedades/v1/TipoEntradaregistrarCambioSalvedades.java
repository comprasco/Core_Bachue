/**
 * TipoEntradaregistrarCambioSalvedades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaregistrarCambioSalvedades.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaregistrarCambioSalvedades implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3320170838609851L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaregistrarCambioSalvedades.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "tipoEntradaregistrarCambioSalvedades"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaPredios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "listaPredios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        ">>tipoEntradaregistrarCambioSalvedades>listaPredios>predio"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1", "predio"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("documentoSoporte");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/salvedades/registrarcambioSalvedades/v1",
		        "documentoSoporte"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                             __equalsCalc     =
		null;
	
	/** Propiedad documento soporte. */
	private byte[]                                                                                                                                       documentoSoporte;
	
	/** Propiedad lista predios. */
	private co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredio[] listaPredios;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                      __hashCodeCalc   =
		false;

	/**
	 * Instancia un nuevo objeto tipo entradaregistrar cambio salvedades.
	 */
	public TipoEntradaregistrarCambioSalvedades()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entradaregistrar cambio salvedades.
	 *
	 * @param listaPredios de lista predios
	 * @param documentoSoporte de documento soporte
	 */
	public TipoEntradaregistrarCambioSalvedades(
	    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredio[] listaPredios,
	    byte[]                                                                                                                                       documentoSoporte
	)
	{
		this.listaPredios         = listaPredios;
		this.documentoSoporte     = documentoSoporte;
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
	 * Sets the documentoSoporte value for this TipoEntradaregistrarCambioSalvedades.
	 *
	 * @param documentoSoporte de documento soporte
	 */
	public void setDocumentoSoporte(byte[] documentoSoporte)
	{
		this.documentoSoporte = documentoSoporte;
	}

	/**
	 * Gets the documentoSoporte value for this TipoEntradaregistrarCambioSalvedades.
	 *
	 * @return documentoSoporte
	 */
	public byte[] getDocumentoSoporte()
	{
		return documentoSoporte;
	}

	/**
	 * Sets the listaPredios value for this TipoEntradaregistrarCambioSalvedades.
	 *
	 * @param listaPredios de lista predios
	 */
	public void setListaPredios(
	    co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredio[] listaPredios
	)
	{
		this.listaPredios = listaPredios;
	}

	/**
	 * Gets the listaPredios value for this TipoEntradaregistrarCambioSalvedades.
	 *
	 * @return listaPredios
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.salvedades.registrarcambioSalvedades.v1.TipoEntradaregistrarCambioSalvedadesListaPrediosPredio[] getListaPredios()
	{
		return listaPredios;
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
		if(!(obj instanceof TipoEntradaregistrarCambioSalvedades))
			return false;

		TipoEntradaregistrarCambioSalvedades other = (TipoEntradaregistrarCambioSalvedades)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.listaPredios == null) && (other.getListaPredios() == null))
				|| ((this.listaPredios != null) && java.util.Arrays.equals(this.listaPredios, other.getListaPredios())))
				&& (((this.documentoSoporte == null) && (other.getDocumentoSoporte() == null))
				|| ((this.documentoSoporte != null)
				&& java.util.Arrays.equals(this.documentoSoporte, other.getDocumentoSoporte())));
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

		if(getListaPredios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaPredios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaPredios(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getDocumentoSoporte() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getDocumentoSoporte()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getDocumentoSoporte(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
