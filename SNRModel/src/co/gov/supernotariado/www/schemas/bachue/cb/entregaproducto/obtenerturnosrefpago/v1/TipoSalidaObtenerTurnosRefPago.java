/**
 * TipoSalidaObtenerTurnosRefPago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaObtenerTurnosRefPago.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaObtenerTurnosRefPago implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7703383218521136083L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaObtenerTurnosRefPago.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "tipoSalidaObtenerTurnosRefPago"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("turnos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1", "turnos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "tipoTurno"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1", "turno"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/entregaproducto/obtenerturnosrefpago/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                __equalsCalc       =
		null;
	
	/** Propiedad codigo mensaje. */
	private java.math.BigInteger                                                                            codigoMensaje;
	
	/** Propiedad descripcion mensaje. */
	private java.lang.String                                                                                descripcionMensaje;
	
	/** Propiedad turnos. */
	private co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoTurno[] turnos;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                         __hashCodeCalc     =
		false;

	/**
	 * Instancia un nuevo objeto tipo salida obtener turnos ref pago.
	 */
	public TipoSalidaObtenerTurnosRefPago()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida obtener turnos ref pago.
	 *
	 * @param turnos de turnos
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaObtenerTurnosRefPago(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoTurno[] turnos,
	    java.math.BigInteger                                                                            codigoMensaje,
	    java.lang.String                                                                                descripcionMensaje
	)
	{
		this.turnos                 = turnos;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaObtenerTurnosRefPago.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaObtenerTurnosRefPago.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaObtenerTurnosRefPago.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaObtenerTurnosRefPago.
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
	 * Sets the turnos value for this TipoSalidaObtenerTurnosRefPago.
	 *
	 * @param turnos de turnos
	 */
	public void setTurnos(
	    co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoTurno[] turnos
	)
	{
		this.turnos = turnos;
	}

	/**
	 * Gets the turnos value for this TipoSalidaObtenerTurnosRefPago.
	 *
	 * @return turnos
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.entregaproducto.obtenerturnosrefpago.v1.TipoTurno[] getTurnos()
	{
		return turnos;
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
		if(!(obj instanceof TipoSalidaObtenerTurnosRefPago))
			return false;

		TipoSalidaObtenerTurnosRefPago other = (TipoSalidaObtenerTurnosRefPago)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.turnos == null) && (other.getTurnos() == null))
				|| ((this.turnos != null) && java.util.Arrays.equals(this.turnos, other.getTurnos())))
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

		if(getTurnos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getTurnos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getTurnos(), i);

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
