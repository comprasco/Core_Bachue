/**
 * TipoSalidaObtenerAccesosPorRol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.obteneraccesosporrol.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaObtenerAccesosPorRol.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaObtenerAccesosPorRol implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8714221568480944001L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaObtenerAccesosPorRol.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "tipoSalidaObtenerAccesosPorRol"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("accesos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1", "accesos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "tipoAcceso"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1", "acceso"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/obteneraccesosporrol/v1",
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
	private java.math.BigInteger codigoMensaje;

	/** Propiedad descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** Propiedad accesos. */
	private TipoAcceso[] accesos;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida obtener accesos por rol.
	 */
	public TipoSalidaObtenerAccesosPorRol()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida obtener accesos por rol.
	 *
	 * @param accesos de accesos
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaObtenerAccesosPorRol(
	    TipoAcceso[] accesos, java.math.BigInteger codigoMensaje, java.lang.String descripcionMensaje
	)
	{
		this.accesos                = accesos;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
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
	 * Sets the accesos value for this TipoSalidaObtenerAccesosPorRol.
	 *
	 * @param accesos de accesos
	 */
	public void setAccesos(TipoAcceso[] accesos)
	{
		this.accesos = accesos;
	}

	/**
	 * Gets the accesos value for this TipoSalidaObtenerAccesosPorRol.
	 *
	 * @return accesos
	 */
	public TipoAcceso[] getAccesos()
	{
		return accesos;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaObtenerAccesosPorRol.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaObtenerAccesosPorRol.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaObtenerAccesosPorRol.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaObtenerAccesosPorRol.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaObtenerAccesosPorRol))
			return false;

		TipoSalidaObtenerAccesosPorRol other = (TipoSalidaObtenerAccesosPorRol)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.accesos == null) && (other.getAccesos() == null))
				|| ((this.accesos != null) && java.util.Arrays.equals(this.accesos, other.getAccesos())))
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

		if(getAccesos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getAccesos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getAccesos(), i);

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
