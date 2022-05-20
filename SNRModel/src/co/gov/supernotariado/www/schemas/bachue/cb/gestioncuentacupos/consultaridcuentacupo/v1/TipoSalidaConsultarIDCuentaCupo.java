/**
 * TipoSalidaConsultarIDCuentaCupo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1;



/**
 * The Class TipoSalidaConsultarIDCuentaCupo.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoSalidaConsultarIDCuentaCupo implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9187751589241361810L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultarIDCuentaCupo.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "tipoSalidaConsultarIDCuentaCupo"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("ID");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1", "ID"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estado");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "estado"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaCreacion");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "fechaCreacion"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorMinimo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "valorMinimo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("valorMaximo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "valorMaximo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("saldo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "saldo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultaridcuentacupo/v1",
		        "descripcionMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The id. */
	private java.lang.String ID;

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The codigo mensaje. */
	private java.math.BigInteger codigoMensaje;

	/** The descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** The estado. */
	private java.lang.String estado;

	/** The fecha creacion. */
	private java.util.Calendar fechaCreacion;

	/** The saldo. */
	private java.math.BigDecimal saldo;

	/** The valor maximo. */
	private java.math.BigDecimal valorMaximo;

	/** The valor minimo. */
	private java.math.BigDecimal valorMinimo;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo salida consultar ID cuenta cupo.
	 */
	public TipoSalidaConsultarIDCuentaCupo()
	{
	}

	/**
	 * Instantiates a new tipo salida consultar ID cuenta cupo.
	 *
	 * @param ID the id
	 * @param estado the estado
	 * @param fechaCreacion the fecha creacion
	 * @param valorMinimo the valor minimo
	 * @param valorMaximo the valor maximo
	 * @param saldo the saldo
	 * @param codigoMensaje the codigo mensaje
	 * @param descripcionMensaje the descripcion mensaje
	 */
	public TipoSalidaConsultarIDCuentaCupo(
	    java.lang.String ID, java.lang.String estado, java.util.Calendar fechaCreacion, java.math.BigDecimal valorMinimo,
	    java.math.BigDecimal valorMaximo, java.math.BigDecimal saldo, java.math.BigInteger codigoMensaje,
	    java.lang.String descripcionMensaje
	)
	{
		this.ID                     = ID;
		this.estado                 = estado;
		this.fechaCreacion          = fechaCreacion;
		this.valorMinimo            = valorMinimo;
		this.valorMaximo            = valorMaximo;
		this.saldo                  = saldo;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Gets the ID value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return ID
	 */
	public java.lang.String getID()
	{
		return ID;
	}

	/**
	 * Sets the ID value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param ID the new id
	 */
	public void setID(java.lang.String ID)
	{
		this.ID = ID;
	}

	/**
	 * Gets the estado value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return estado
	 */
	public java.lang.String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(java.lang.String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the fechaCreacion value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return fechaCreacion
	 */
	public java.util.Calendar getFechaCreacion()
	{
		return fechaCreacion;
	}

	/**
	 * Sets the fechaCreacion value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param fechaCreacion the new fecha creacion
	 */
	public void setFechaCreacion(java.util.Calendar fechaCreacion)
	{
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Gets the valorMinimo value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return valorMinimo
	 */
	public java.math.BigDecimal getValorMinimo()
	{
		return valorMinimo;
	}

	/**
	 * Sets the valorMinimo value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param valorMinimo the new valor minimo
	 */
	public void setValorMinimo(java.math.BigDecimal valorMinimo)
	{
		this.valorMinimo = valorMinimo;
	}

	/**
	 * Gets the valorMaximo value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return valorMaximo
	 */
	public java.math.BigDecimal getValorMaximo()
	{
		return valorMaximo;
	}

	/**
	 * Sets the valorMaximo value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param valorMaximo the new valor maximo
	 */
	public void setValorMaximo(java.math.BigDecimal valorMaximo)
	{
		this.valorMaximo = valorMaximo;
	}

	/**
	 * Gets the saldo value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return saldo
	 */
	public java.math.BigDecimal getSaldo()
	{
		return saldo;
	}

	/**
	 * Sets the saldo value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param saldo the new saldo
	 */
	public void setSaldo(java.math.BigDecimal saldo)
	{
		this.saldo = saldo;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return codigoMensaje
	 */
	public java.math.BigInteger getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param codigoMensaje the new codigo mensaje
	 */
	public void setCodigoMensaje(java.math.BigInteger codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @return descripcionMensaje
	 */
	public java.lang.String getDescripcionMensaje()
	{
		return descripcionMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultarIDCuentaCupo.
	 *
	 * @param descripcionMensaje the new descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultarIDCuentaCupo))
			return false;

		TipoSalidaConsultarIDCuentaCupo other = (TipoSalidaConsultarIDCuentaCupo)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.ID == null) && (other.getID() == null))
				|| ((this.ID != null) && this.ID.equals(other.getID())))
				&& (((this.estado == null) && (other.getEstado() == null))
				|| ((this.estado != null) && this.estado.equals(other.getEstado())))
				&& (((this.fechaCreacion == null) && (other.getFechaCreacion() == null))
				|| ((this.fechaCreacion != null) && this.fechaCreacion.equals(other.getFechaCreacion())))
				&& (((this.valorMinimo == null) && (other.getValorMinimo() == null))
				|| ((this.valorMinimo != null) && this.valorMinimo.equals(other.getValorMinimo())))
				&& (((this.valorMaximo == null) && (other.getValorMaximo() == null))
				|| ((this.valorMaximo != null) && this.valorMaximo.equals(other.getValorMaximo())))
				&& (((this.saldo == null) && (other.getSaldo() == null))
				|| ((this.saldo != null) && this.saldo.equals(other.getSaldo())))
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

		if(getID() != null)
			_hashCode += getID().hashCode();

		if(getEstado() != null)
			_hashCode += getEstado().hashCode();

		if(getFechaCreacion() != null)
			_hashCode += getFechaCreacion().hashCode();

		if(getValorMinimo() != null)
			_hashCode += getValorMinimo().hashCode();

		if(getValorMaximo() != null)
			_hashCode += getValorMaximo().hashCode();

		if(getSaldo() != null)
			_hashCode += getSaldo().hashCode();

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
	 * @return the type desc
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer.
	 *
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the serializer
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
	 * @param mechType the mech type
	 * @param _javaType the java type
	 * @param _xmlType the xml type
	 * @return the deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}
}
