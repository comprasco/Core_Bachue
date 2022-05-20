/**
 * TipoEntradaGenerarSolicitud.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1;



/**
 * The Class TipoEntradaGenerarSolicitud.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaGenerarSolicitud implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5998593783140589772L;

	/** The type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaGenerarSolicitud.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoEntradaGenerarSolicitud"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("modulo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1", "modulo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("solicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "solicitante"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoSolicitanteGSI"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("servicios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "servicios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "tipoServicioGSI"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/generacionsolicitud/generarsolicitud/v1",
		        "servicio"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** The equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** The modulo. */
	private java.lang.String modulo;

	/** The solicitante. */
	private co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSI solicitante;

	/** The servicios. */
	private co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoServicioGSI[] servicios;

	/** The hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instantiates a new tipo entrada generar solicitud.
	 */
	public TipoEntradaGenerarSolicitud()
	{
	}

	/**
	 * Instantiates a new tipo entrada generar solicitud.
	 *
	 * @param modulo the modulo
	 * @param solicitante the solicitante
	 * @param servicios the servicios
	 */
	public TipoEntradaGenerarSolicitud(
	    java.lang.String modulo,
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSI solicitante,
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoServicioGSI[] servicios
	)
	{
		this.modulo          = modulo;
		this.solicitante     = solicitante;
		this.servicios       = servicios;
	}

	/**
	 * Gets the modulo value for this TipoEntradaGenerarSolicitud.
	 *
	 * @return modulo
	 */
	public java.lang.String getModulo()
	{
		return modulo;
	}

	/**
	 * Sets the modulo value for this TipoEntradaGenerarSolicitud.
	 *
	 * @param modulo the new modulo
	 */
	public void setModulo(java.lang.String modulo)
	{
		this.modulo = modulo;
	}

	/**
	 * Gets the solicitante value for this TipoEntradaGenerarSolicitud.
	 *
	 * @return solicitante
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSI getSolicitante()
	{
		return solicitante;
	}

	/**
	 * Sets the solicitante value for this TipoEntradaGenerarSolicitud.
	 *
	 * @param solicitante the new solicitante
	 */
	public void setSolicitante(
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSolicitanteGSI solicitante
	)
	{
		this.solicitante = solicitante;
	}

	/**
	 * Gets the servicios value for this TipoEntradaGenerarSolicitud.
	 *
	 * @return servicios
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoServicioGSI[] getServicios()
	{
		return servicios;
	}

	/**
	 * Sets the servicios value for this TipoEntradaGenerarSolicitud.
	 *
	 * @param servicios the new servicios
	 */
	public void setServicios(
	    co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoServicioGSI[] servicios
	)
	{
		this.servicios = servicios;
	}

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaGenerarSolicitud))
			return false;

		TipoEntradaGenerarSolicitud other = (TipoEntradaGenerarSolicitud)obj;

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
				&& (((this.servicios == null) && (other.getServicios() == null))
				|| ((this.servicios != null) && java.util.Arrays.equals(this.servicios, other.getServicios())));
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

		if(getServicios() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getServicios()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getServicios(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

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
