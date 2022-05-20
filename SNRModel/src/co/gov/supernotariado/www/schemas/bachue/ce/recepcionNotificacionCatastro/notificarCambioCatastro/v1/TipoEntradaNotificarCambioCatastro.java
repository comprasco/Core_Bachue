/**
 * TipoEntradaNotificarCambioCatastro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaNotificarCambioCatastro.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/03/2020
 */
public class TipoEntradaNotificarCambioCatastro implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -31667623968824979L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaNotificarCambioCatastro.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "tipoEntradaNotificarCambioCatastro"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codCatastro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "codCatastro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codTransaccion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "codTransaccion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("operacionRegistro");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "operacionRegistro"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaNotificacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "fechaNotificacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("orips");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "orips"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/ce/recepcionNotificacionCatastro/notificarCambioCatastro/v1",
		        "tipoORP"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cod catastro. */
	private java.lang.String codCatastro;

	/** Propiedad cod transaccion. */
	private java.lang.String codTransaccion;

	/** Propiedad fecha notificacion. */
	private java.lang.String fechaNotificacion;

	/** Propiedad operacion registro. */
	private java.lang.String operacionRegistro;

	/** Propiedad orips. */
	private co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP[] orips;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada notificar cambio catastro.
	 */
	public TipoEntradaNotificarCambioCatastro()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada notificar cambio catastro.
	 *
	 * @param codCatastro de cod catastro
	 * @param codTransaccion de cod transaccion
	 * @param operacionRegistro de operacion registro
	 * @param fechaNotificacion de fecha notificacion
	 * @param orips de orips
	 */
	public TipoEntradaNotificarCambioCatastro(
	    java.lang.String codCatastro, java.lang.String codTransaccion, java.lang.String operacionRegistro,
	    java.lang.String fechaNotificacion,
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP[] orips
	)
	{
		this.codCatastro           = codCatastro;
		this.codTransaccion        = codTransaccion;
		this.operacionRegistro     = operacionRegistro;
		this.fechaNotificacion     = fechaNotificacion;
		this.orips                 = orips;
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
	 * Sets the codCatastro value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @param codCatastro de cod catastro
	 */
	public void setCodCatastro(java.lang.String codCatastro)
	{
		this.codCatastro = codCatastro;
	}

	/**
	 * Gets the codCatastro value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @return codCatastro
	 */
	public java.lang.String getCodCatastro()
	{
		return codCatastro;
	}

	/**
	 * Sets the codTransaccion value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @param codTransaccion de cod transaccion
	 */
	public void setCodTransaccion(java.lang.String codTransaccion)
	{
		this.codTransaccion = codTransaccion;
	}

	/**
	 * Gets the codTransaccion value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @return codTransaccion
	 */
	public java.lang.String getCodTransaccion()
	{
		return codTransaccion;
	}

	/**
	 * Sets the fechaNotificacion value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @param fechaNotificacion de fecha notificacion
	 */
	public void setFechaNotificacion(java.lang.String fechaNotificacion)
	{
		this.fechaNotificacion = fechaNotificacion;
	}

	/**
	 * Gets the fechaNotificacion value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @return fechaNotificacion
	 */
	public java.lang.String getFechaNotificacion()
	{
		return fechaNotificacion;
	}

	/**
	 * Sets the operacionRegistro value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @param operacionRegistro de operacion registro
	 */
	public void setOperacionRegistro(java.lang.String operacionRegistro)
	{
		this.operacionRegistro = operacionRegistro;
	}

	/**
	 * Gets the operacionRegistro value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @return operacionRegistro
	 */
	public java.lang.String getOperacionRegistro()
	{
		return operacionRegistro;
	}

	/**
	 * Sets the orips value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @param orips de orips
	 */
	public void setOrips(
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP[] orips
	)
	{
		this.orips = orips;
	}

	/**
	 * Cambia el valor de orips.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setOrips(
	    int i,
	    co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP _value
	)
	{
		this.orips[i] = _value;
	}

	/**
	 * Gets the orips value for this TipoEntradaNotificarCambioCatastro.
	 *
	 * @return orips
	 */
	public co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP[] getOrips()
	{
		return orips;
	}

	/**
	 * Retorna Objeto o variable de valor orips.
	 *
	 * @param i de i
	 * @return el valor de orips
	 */
	public co.gov.supernotariado.www.schemas.bachue.ce.recepcionNotificacionCatastro.notificarCambioCatastro.v1.TipoORP getOrips(
	    int i
	)
	{
		return this.orips[i];
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaNotificarCambioCatastro))
			return false;

		TipoEntradaNotificarCambioCatastro other = (TipoEntradaNotificarCambioCatastro)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codCatastro == null) && (other.getCodCatastro() == null))
				|| ((this.codCatastro != null) && this.codCatastro.equals(other.getCodCatastro())))
				&& (((this.codTransaccion == null) && (other.getCodTransaccion() == null))
				|| ((this.codTransaccion != null) && this.codTransaccion.equals(other.getCodTransaccion())))
				&& (((this.operacionRegistro == null) && (other.getOperacionRegistro() == null))
				|| ((this.operacionRegistro != null) && this.operacionRegistro.equals(other.getOperacionRegistro())))
				&& (((this.fechaNotificacion == null) && (other.getFechaNotificacion() == null))
				|| ((this.fechaNotificacion != null) && this.fechaNotificacion.equals(other.getFechaNotificacion())))
				&& (((this.orips == null) && (other.getOrips() == null))
				|| ((this.orips != null) && java.util.Arrays.equals(this.orips, other.getOrips())));
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

		if(getCodCatastro() != null)
			_hashCode += getCodCatastro().hashCode();

		if(getCodTransaccion() != null)
			_hashCode += getCodTransaccion().hashCode();

		if(getOperacionRegistro() != null)
			_hashCode += getOperacionRegistro().hashCode();

		if(getFechaNotificacion() != null)
			_hashCode += getFechaNotificacion().hashCode();

		if(getOrips() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getOrips()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getOrips(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		__hashCodeCalc = false;

		return _hashCode;
	}
}
