/**
 * TipoMovimientoCMO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1;



/**
 * The Class TipoMovimientoCMO.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoMovimientoCMO implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4942488423366001220L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoMovimientoCMO.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "tipoMovimientoCMO"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idMovimiento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "idMovimiento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaMovimiento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "fechaMovimiento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1", "tipo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("notaCredito");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "notaCredito"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "tipoNotaCreditoCMO"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("reciboCaja");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "reciboCaja"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestioncuentacupos/consultarmovimientos/v1",
		        "tipoReciboCajaCMO"
		    )
		);
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The fecha movimiento. */
	private java.util.Calendar fechaMovimiento;

	/** The id movimiento. */
	private java.lang.String idMovimiento;

	/** The nota credito. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoNotaCreditoCMO notaCredito;

	/** The recibo caja. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoReciboCajaCMO reciboCaja;

	/** The tipo. */
	private java.lang.String tipo;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo movimiento CMO.
	 */
	public TipoMovimientoCMO()
	{
	}

	/**
	 * Instantiates a new tipo movimiento CMO.
	 *
	 * @param idMovimiento the id movimiento
	 * @param fechaMovimiento the fecha movimiento
	 * @param tipo the tipo
	 * @param notaCredito the nota credito
	 * @param reciboCaja the recibo caja
	 */
	public TipoMovimientoCMO(
	    java.lang.String idMovimiento, java.util.Calendar fechaMovimiento, java.lang.String tipo,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoNotaCreditoCMO notaCredito,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoReciboCajaCMO reciboCaja
	)
	{
		this.idMovimiento        = idMovimiento;
		this.fechaMovimiento     = fechaMovimiento;
		this.tipo                = tipo;
		this.notaCredito         = notaCredito;
		this.reciboCaja          = reciboCaja;
	}

	/**
	 * Gets the idMovimiento value for this TipoMovimientoCMO.
	 *
	 * @return idMovimiento
	 */
	public java.lang.String getIdMovimiento()
	{
		return idMovimiento;
	}

	/**
	 * Sets the idMovimiento value for this TipoMovimientoCMO.
	 *
	 * @param idMovimiento the new id movimiento
	 */
	public void setIdMovimiento(java.lang.String idMovimiento)
	{
		this.idMovimiento = idMovimiento;
	}

	/**
	 * Gets the fechaMovimiento value for this TipoMovimientoCMO.
	 *
	 * @return fechaMovimiento
	 */
	public java.util.Calendar getFechaMovimiento()
	{
		return fechaMovimiento;
	}

	/**
	 * Sets the fechaMovimiento value for this TipoMovimientoCMO.
	 *
	 * @param fechaMovimiento the new fecha movimiento
	 */
	public void setFechaMovimiento(java.util.Calendar fechaMovimiento)
	{
		this.fechaMovimiento = fechaMovimiento;
	}

	/**
	 * Gets the tipo value for this TipoMovimientoCMO.
	 *
	 * @return tipo
	 */
	public java.lang.String getTipo()
	{
		return tipo;
	}

	/**
	 * Sets the tipo value for this TipoMovimientoCMO.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(java.lang.String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * Gets the notaCredito value for this TipoMovimientoCMO.
	 *
	 * @return notaCredito
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoNotaCreditoCMO getNotaCredito()
	{
		return notaCredito;
	}

	/**
	 * Sets the notaCredito value for this TipoMovimientoCMO.
	 *
	 * @param notaCredito the new nota credito
	 */
	public void setNotaCredito(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoNotaCreditoCMO notaCredito
	)
	{
		this.notaCredito = notaCredito;
	}

	/**
	 * Gets the reciboCaja value for this TipoMovimientoCMO.
	 *
	 * @return reciboCaja
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoReciboCajaCMO getReciboCaja()
	{
		return reciboCaja;
	}

	/**
	 * Sets the reciboCaja value for this TipoMovimientoCMO.
	 *
	 * @param reciboCaja the new recibo caja
	 */
	public void setReciboCaja(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoReciboCajaCMO reciboCaja
	)
	{
		this.reciboCaja = reciboCaja;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoMovimientoCMO))
			return false;

		TipoMovimientoCMO other = (TipoMovimientoCMO)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.idMovimiento == null) && (other.getIdMovimiento() == null))
				|| ((this.idMovimiento != null) && this.idMovimiento.equals(other.getIdMovimiento())))
				&& (((this.fechaMovimiento == null) && (other.getFechaMovimiento() == null))
				|| ((this.fechaMovimiento != null) && this.fechaMovimiento.equals(other.getFechaMovimiento())))
				&& (((this.tipo == null) && (other.getTipo() == null))
				|| ((this.tipo != null) && this.tipo.equals(other.getTipo())))
				&& (((this.notaCredito == null) && (other.getNotaCredito() == null))
				|| ((this.notaCredito != null) && this.notaCredito.equals(other.getNotaCredito())))
				&& (((this.reciboCaja == null) && (other.getReciboCaja() == null))
				|| ((this.reciboCaja != null) && this.reciboCaja.equals(other.getReciboCaja())));
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

		if(getIdMovimiento() != null)
			_hashCode += getIdMovimiento().hashCode();

		if(getFechaMovimiento() != null)
			_hashCode += getFechaMovimiento().hashCode();

		if(getTipo() != null)
			_hashCode += getTipo().hashCode();

		if(getNotaCredito() != null)
			_hashCode += getNotaCredito().hashCode();

		if(getReciboCaja() != null)
			_hashCode += getReciboCaja().hashCode();

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
