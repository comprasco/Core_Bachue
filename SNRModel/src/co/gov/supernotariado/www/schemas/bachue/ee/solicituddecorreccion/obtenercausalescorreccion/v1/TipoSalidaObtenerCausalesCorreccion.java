/**
 * TipoSalidaObtenerCausalesCorreccion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaObtenerCausalesCorreccion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaObtenerCausalesCorreccion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1643423686604229855L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaObtenerCausalesCorreccion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "tipoSalidaObtenerCausalesCorreccion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("listaCausales");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "listaCausales"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        ">>tipoSalidaObtenerCausalesCorreccion>listaCausales>Causal"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "Causal"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "codigo"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        ">tipoSalidaObtenerCausalesCorreccion>codigo"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("mensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ee/solicituddecorreccion/obtenercausalescorreccion/v1",
		        "mensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                         __equalsCalc =
		null;
	
	/** Propiedad codigo. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionCodigo codigo;
	
	/** Propiedad mensaje. */
	private java.lang.String mensaje;
	
	/** Propiedad lista causales. */
	private co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionListaCausalesCausal[] listaCausales;
	
	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida obtener causales correccion.
	 */
	public TipoSalidaObtenerCausalesCorreccion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida obtener causales correccion.
	 *
	 * @param listaCausales de lista causales
	 * @param codigo de codigo
	 * @param mensaje de mensaje
	 */
	public TipoSalidaObtenerCausalesCorreccion(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionListaCausalesCausal[] listaCausales,
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionCodigo                codigo,
	    java.lang.String                                                                                                                                        mensaje
	)
	{
		this.listaCausales     = listaCausales;
		this.codigo            = codigo;
		this.mensaje           = mensaje;
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
	 * Sets the codigo value for this TipoSalidaObtenerCausalesCorreccion.
	 *
	 * @param codigo de codigo
	 */
	public void setCodigo(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionCodigo codigo
	)
	{
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo value for this TipoSalidaObtenerCausalesCorreccion.
	 *
	 * @return codigo
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionCodigo getCodigo()
	{
		return codigo;
	}

	/**
	 * Sets the listaCausales value for this TipoSalidaObtenerCausalesCorreccion.
	 *
	 * @param listaCausales de lista causales
	 */
	public void setListaCausales(
	    co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionListaCausalesCausal[] listaCausales
	)
	{
		this.listaCausales = listaCausales;
	}

	/**
	 * Gets the listaCausales value for this TipoSalidaObtenerCausalesCorreccion.
	 *
	 * @return listaCausales
	 */
	public co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccionListaCausalesCausal[] getListaCausales()
	{
		return listaCausales;
	}

	/**
	 * Sets the mensaje value for this TipoSalidaObtenerCausalesCorreccion.
	 *
	 * @param mensaje de mensaje
	 */
	public void setMensaje(java.lang.String mensaje)
	{
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mensaje value for this TipoSalidaObtenerCausalesCorreccion.
	 *
	 * @return mensaje
	 */
	public java.lang.String getMensaje()
	{
		return mensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaObtenerCausalesCorreccion))
			return false;

		TipoSalidaObtenerCausalesCorreccion other = (TipoSalidaObtenerCausalesCorreccion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.listaCausales == null) && (other.getListaCausales() == null))
				|| ((this.listaCausales != null)
				&& java.util.Arrays.equals(this.listaCausales, other.getListaCausales())))
				&& (((this.codigo == null) && (other.getCodigo() == null))
				|| ((this.codigo != null) && this.codigo.equals(other.getCodigo())))
				&& (((this.mensaje == null) && (other.getMensaje() == null))
				|| ((this.mensaje != null) && this.mensaje.equals(other.getMensaje())));
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

		if(getListaCausales() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getListaCausales()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getListaCausales(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigo() != null)
			_hashCode += getCodigo().hashCode();

		if(getMensaje() != null)
			_hashCode += getMensaje().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
