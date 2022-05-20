/**
 * TipoEntradaConsultarEstadoSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1;



/**
 * The Class TipoEntradaConsultarEstadoSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarEstadoSolicitud implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4503109986745420459L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarEstadoSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "tipoEntradaConsultarEstadoSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("solicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "solicitante"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "tipoSolicitanteCESI"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("NIR");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/consultarestadosolicitud/v1",
		        "NIR"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** The nir. */
	private java.lang.String NIR;

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The modulo. */
	private java.lang.String modulo;

	/** The solicitante. */
	private co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSolicitanteCESI solicitante;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo entrada consultar estado solicitud.
	 */
	public TipoEntradaConsultarEstadoSolicitud()
	{
	}

	/**
	 * Instantiates a new tipo entrada consultar estado solicitud.
	 *
	 * @param modulo the modulo
	 * @param solicitante the solicitante
	 * @param NIR the nir
	 */
	public TipoEntradaConsultarEstadoSolicitud(
	    java.lang.String modulo,
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSolicitanteCESI solicitante,
	    java.lang.String NIR
	)
	{
		this.modulo          = modulo;
		this.solicitante     = solicitante;
		this.NIR             = NIR;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarEstadoSolicitud.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the modulo value for this TipoEntradaConsultarEstadoSolicitud.
	 *
	 * @param modulo the new modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the solicitante value for this TipoEntradaConsultarEstadoSolicitud.
	 *
	 * @return solicitante
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSolicitanteCESI getSolicitante()
	{
		return solicitante;
	}

	/**
	 * Sets the solicitante value for this TipoEntradaConsultarEstadoSolicitud.
	 *
	 * @param solicitante the new solicitante
	 */
	public void setSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSolicitanteCESI solicitante
	)
	{
		this.solicitante = solicitante;
	}

	/**
	 * Gets the NIR value for this TipoEntradaConsultarEstadoSolicitud.
	 *
	 * @return NIR
	 */
	public java.lang.String getNIR()
	{
		return NIR;
	}

	/**
	 * Sets the NIR value for this TipoEntradaConsultarEstadoSolicitud.
	 *
	 * @param NIR the new nir
	 */
	public void setNIR(java.lang.String NIR)
	{
		this.NIR = NIR;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaConsultarEstadoSolicitud))
			return false;

		TipoEntradaConsultarEstadoSolicitud other = (TipoEntradaConsultarEstadoSolicitud)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.modulo == null) && (other.getModulo() == null))
				|| ((this.modulo != null) && this.modulo.equals(other.getModulo())))
				&& (((this.solicitante == null) && (other.getSolicitante() == null))
				|| ((this.solicitante != null) && this.solicitante.equals(other.getSolicitante())))
				&& (((this.NIR == null) && (other.getNIR() == null))
				|| ((this.NIR != null) && this.NIR.equals(other.getNIR())));
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

		if(getModulo() != null)
			_hashCode += getModulo().hashCode();

		if(getSolicitante() != null)
			_hashCode += getSolicitante().hashCode();

		if(getNIR() != null)
			_hashCode += getNIR().hashCode();

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
