/**
 * TipoSalidaConsultaDetalleCertificado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1;


/**
 * Clase que contiene todos las propiedades TipoSalidaConsultaDetalleCertificado.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/09/2020
 */
public class TipoSalidaConsultaDetalleCertificado implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4165084143088685575L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoSalidaConsultaDetalleCertificado.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "tipoSalidaConsultaDetalleCertificado"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nir");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "nir"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("certificados");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "certificados"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        ">>tipoSalidaConsultaDetalleCertificado>certificados>certificado"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "certificado"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
		        "codigoMensaje"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("descripcionMensaje");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/consultatrazabilidad/consultaDetalleCertificado/v1",
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
	private java.lang.String codigoMensaje;

	/** Propiedad descripcion mensaje. */
	private java.lang.String descripcionMensaje;

	/** Propiedad nir. */
	private java.lang.String nir;

	/** Propiedad certificados. */
	private co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[] certificados;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo salida consulta detalle certificado.
	 */
	public TipoSalidaConsultaDetalleCertificado()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo salida consulta detalle certificado.
	 *
	 * @param nir correspondiente al valor de nir
	 * @param certificados correspondiente al valor de certificados
	 * @param codigoMensaje correspondiente al valor de codigo mensaje
	 * @param descripcionMensaje correspondiente al valor de descripcion mensaje
	 */
	public TipoSalidaConsultaDetalleCertificado(
	    java.lang.String nir,
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[] certificados,
	    java.lang.String codigoMensaje, java.lang.String descripcionMensaje
	)
	{
		this.nir                    = nir;
		this.certificados           = certificados;
		this.codigoMensaje          = codigoMensaje;
		this.descripcionMensaje     = descripcionMensaje;
	}

	/**
	 * Sets the certificados value for this TipoSalidaConsultaDetalleCertificado.
	 *
	 * @param certificados correspondiente al valor de certificados
	 */
	public void setCertificados(
	    co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[] certificados
	)
	{
		this.certificados = certificados;
	}

	/**
	 * Gets the certificados value for this TipoSalidaConsultaDetalleCertificado.
	 *
	 * @return certificados
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificadoCertificadosCertificado[] getCertificados()
	{
		return certificados;
	}

	/**
	 * Sets the codigoMensaje value for this TipoSalidaConsultaDetalleCertificado.
	 *
	 * @param codigoMensaje correspondiente al valor de codigo mensaje
	 */
	public void setCodigoMensaje(java.lang.String codigoMensaje)
	{
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigoMensaje value for this TipoSalidaConsultaDetalleCertificado.
	 *
	 * @return codigoMensaje
	 */
	public java.lang.String getCodigoMensaje()
	{
		return codigoMensaje;
	}

	/**
	 * Sets the descripcionMensaje value for this TipoSalidaConsultaDetalleCertificado.
	 *
	 * @param descripcionMensaje correspondiente al valor de descripcion mensaje
	 */
	public void setDescripcionMensaje(java.lang.String descripcionMensaje)
	{
		this.descripcionMensaje = descripcionMensaje;
	}

	/**
	 * Gets the descripcionMensaje value for this TipoSalidaConsultaDetalleCertificado.
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
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
	 * @return el valor de deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Sets the nir value for this TipoSalidaConsultaDetalleCertificado.
	 *
	 * @param nir correspondiente al valor de nir
	 */
	public void setNir(java.lang.String nir)
	{
		this.nir = nir;
	}

	/**
	 * Gets the nir value for this TipoSalidaConsultaDetalleCertificado.
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
	 * @param mechType correspondiente al valor de mech type
	 * @param _javaType correspondiente al valor de java type
	 * @param _xmlType correspondiente al valor de xml type
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

	/** {@inheritdoc} */
	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoSalidaConsultaDetalleCertificado))
			return false;

		TipoSalidaConsultaDetalleCertificado other = (TipoSalidaConsultaDetalleCertificado)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.nir == null) && (other.getNir() == null))
				|| ((this.nir != null) && this.nir.equals(other.getNir())))
				&& (((this.certificados == null) && (other.getCertificados() == null))
				|| ((this.certificados != null) && java.util.Arrays.equals(this.certificados, other.getCertificados())))
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

		if(getNir() != null)
			_hashCode += getNir().hashCode();

		if(getCertificados() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getCertificados()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getCertificados(), i);

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
