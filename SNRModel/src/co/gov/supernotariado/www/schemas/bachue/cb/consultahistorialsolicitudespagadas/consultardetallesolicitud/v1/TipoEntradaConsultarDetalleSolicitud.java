/**
 * TipoEntradaConsultarDetalleSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarDetalleSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarDetalleSolicitud implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 9034460240417058276L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarDetalleSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "tipoEntradaConsultarDetalleSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "solicitante"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        ">tipoEntradaConsultarDetalleSolicitud>solicitante"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultahistorialsolicitudespagadas/consultardetallesolicitud/v1",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                                                                             __equalsCalc   =
		null;
	
	/** Propiedad modulo. */
	private java.lang.String                                                                                                                                             modulo;
	
	/** Propiedad nir. */
	private java.lang.String                                                                                                                                             nir;
	
	/** Propiedad solicitante. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitudSolicitante solicitante;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                                                                      __hashCodeCalc =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar detalle solicitud.
	 */
	public TipoEntradaConsultarDetalleSolicitud()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar detalle solicitud.
	 *
	 * @param modulo de modulo
	 * @param solicitante de solicitante
	 * @param nir de nir
	 */
	public TipoEntradaConsultarDetalleSolicitud(
	    java.lang.String modulo,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitudSolicitante solicitante,
	    java.lang.String nir
	)
	{
		this.modulo          = modulo;
		this.solicitante     = solicitante;
		this.nir             = nir;
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
	 * Sets the modulo value for this TipoEntradaConsultarDetalleSolicitud.
	 *
	 * @param modulo de modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the modulo value for this TipoEntradaConsultarDetalleSolicitud.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the nir value for this TipoEntradaConsultarDetalleSolicitud.
	 *
	 * @param nir de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoEntradaConsultarDetalleSolicitud.
	 *
	 * @return nir
	 */
	public java.lang.String getNir()
	{
		return nir;
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
	 * Sets the solicitante value for this TipoEntradaConsultarDetalleSolicitud.
	 *
	 * @param solicitante de solicitante
	 */
	public void setSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitudSolicitante solicitante
	)
	{
		this.solicitante = solicitante;
	}

	/**
	 * Gets the solicitante value for this TipoEntradaConsultarDetalleSolicitud.
	 *
	 * @return solicitante
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitudSolicitante getSolicitante()
	{
		return solicitante;
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
		if(!(obj instanceof TipoEntradaConsultarDetalleSolicitud))
			return false;

		TipoEntradaConsultarDetalleSolicitud other = (TipoEntradaConsultarDetalleSolicitud)obj;

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
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())));
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

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
