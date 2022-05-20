/**
 * TipoSalidaConsultarSaldosNotaCredito.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultarSaldosNotaCredito.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/09/2020
 */
public class TipoSalidaConsultarSaldosNotaCredito implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3674582187691301515L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarSaldosNotaCredito.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "tipoSalidaConsultarSaldosNotaCredito"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("notasCreditos");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "notasCreditos"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        ">tipoSalidaConsultarSaldosNotaCredito>notasCreditos"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		elemField.setMaxOccursUnbounded(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarSaldosNotaCredito/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad codigo mensaje. */
	private java.math.BigInteger codigoMensaje;

	/** Propiedad descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** Propiedad notas creditos. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditos[] notasCreditos;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consultar saldos nota credito.
	 */
	public TipoSalidaConsultarSaldosNotaCredito()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consultar saldos nota credito.
	 *
	 * @param notasCreditos de notas creditos
	 * @param codigoMensaje de codigo mensaje
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public TipoSalidaConsultarSaldosNotaCredito(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditos[] notasCreditos,
	    java.math.BigInteger                                                                                                                             codigoMensaje,
	    java.lang.String                                                                                                                                 descripcionMensaje
	)
	{
		this.notasCreditos          = notasCreditos;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the notasCreditos value for this TipoSalidaConsultarSaldosNotaCredito.
	 *
	 * @return notasCreditos
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditos[] getNotasCreditos()
	{
		return notasCreditos;
	}

	/**
	 * Sets the notasCreditos value for this TipoSalidaConsultarSaldosNotaCredito.
	 *
	 * @param notasCreditos de notas creditos
	 */
	public void setNotasCreditos(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditos[] notasCreditos
	)
	{
		this.notasCreditos = notasCreditos;
	}

	/**
	 * Retorna Objeto o variable de valor notas creditos.
	 *
	 * @param i de i
	 * @return el valor de notas creditos
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditos getNotasCreditos(
	    int i
	)
	{
		return this.notasCreditos[i];
	}

	/**
	 * Cambia el valor de notas creditos.
	 *
	 * @param i de i
	 * @param _value de value
	 */
	public void setNotasCreditos(
	    int i,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCreditoNotasCreditos _value
	)
	{
		this.notasCreditos[i] = _value;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarSaldosNotaCredito.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarSaldosNotaCredito.
	 *
	 * @param codigoMensaje de codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarSaldosNotaCredito.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarSaldosNotaCredito.
	 *
	 * @param descripcionMensaje de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarSaldosNotaCredito))
			return false;

		TipoSalidaConsultarSaldosNotaCredito other = (TipoSalidaConsultarSaldosNotaCredito)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.notasCreditos == null) && (other.getNotasCreditos() == null))
				|| ((this.notasCreditos != null)
				&& java.util.Arrays.equals(this.notasCreditos, other.getNotasCreditos())))
				&& (((this.codigoMensaje == null) && (other.getCodigoMensaje() == null))
				|| ((this.codigoMensaje != null) && this.codigoMensaje.equals(other.getCodigoMensaje())))
				&& (((this.descripcionMensaje == null) && (other.getDescripcionMensaje() == null))
				|| ((this.descripcionMensaje != null) && this.descripcionMensaje.equals(other.getDescripcionMensaje())));
		__equalsCalc     = null;

		return _equals;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getNotasCreditos() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getNotasCreditos()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getNotasCreditos(), i);

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
