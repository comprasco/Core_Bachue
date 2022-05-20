/**
 * TipoSalidaConsultarCatalogos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarCatalogos.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarCatalogos implements java.io.Serializable
{
	
	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarCatalogos.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "tipoSalidaConsultarCatalogos"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("catalogos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "catalogos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "tiposCatalogos"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "catalogo"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        ">tipoSalidaConsultarCatalogos>codigoMensaje"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultacatalogos/consultarcatalogos/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;
	
	/** Propiedad codigo mensaje. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogosCodigoMensaje codigoMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String descripcionMensaje;
	
	/** Propiedad catalogos. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos[] catalogos;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar catalogos.
	 */
	public TipoSalidaConsultarCatalogos()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar catalogos.
	 *
	 * @param catalogos de catalogos
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaConsultarCatalogos(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos[]                          catalogos,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogosCodigoMensaje codigoMensaje,
	    java.lang.String                                                                                                              descripcionMensaje
	)
	{
		this.catalogos              = catalogos;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the catalogos value for this TipoSalidaConsultarCatalogos.
	 *
	 * @param catalogos de catalogos
	 */
	public void setCatalogos(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos[] catalogos
	)
	{
		this.catalogos = catalogos;
	}

	/**
	 * Gets the catalogos value for this TipoSalidaConsultarCatalogos.
	 *
	 * @return catalogos
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos[] getCatalogos()
	{
		return catalogos;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarCatalogos.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogosCodigoMensaje codigoMensaje
	)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarCatalogos.
	 *
	 * @return codigoMensaje
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoSalidaConsultarCatalogosCodigoMensaje getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarCatalogos.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarCatalogos.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
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
		if(!(obj instanceof TipoSalidaConsultarCatalogos))
			return false;

		TipoSalidaConsultarCatalogos other = (TipoSalidaConsultarCatalogos)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.catalogos == null) && (other.getCatalogos() == null))
				|| ((this.catalogos != null) && java.util.Arrays.equals(this.catalogos, other.getCatalogos())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
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

		if(getCatalogos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getCatalogos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getCatalogos(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigoMensaje() != null)
			_hashCode += getCodigoMensaje().hashCode();

		if(getDescripcionMensaje() != null)
			_hashCode += getDescripcionMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
