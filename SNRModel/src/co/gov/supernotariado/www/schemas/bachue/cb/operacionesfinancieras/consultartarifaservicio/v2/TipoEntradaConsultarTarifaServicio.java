/**
 * TipoEntradaConsultarTarifaServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2;


/**
 * Clase que contiene todos las propiedades TipoEntradaConsultarTarifaServicio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaConsultarTarifaServicio implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3445102928366298506L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaConsultarTarifaServicio.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoEntradaConsultarTarifaServicio"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoDocSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "numeroDocSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoConvenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "codigoConvenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("canalOrigen");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "canalOrigen"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        ">tipoEntradaConsultarTarifaServicio>canalOrigen"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("servicios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "servicios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "tipoServicioCTSI"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/consultartarifaservicio/v2",
		        "servicio"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;
	
	/** Propiedad canal origen. */
	private co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicioCanalOrigen canalOrigen;
	
	/** Propiedad codigo convenio. */
	private java.lang.String codigoConvenio;
	
	/** Propiedad numero doc solicitante. */
	private java.lang.String numeroDocSolicitante;
	
	/** Propiedad tipo doc solicitante. */
	private java.lang.String tipoDocSolicitante;
	
	/** Propiedad servicios. */
	private co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSI[] servicios;
	
	/** Propiedad hash code calc. */
	private boolean          __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada consultar tarifa servicio.
	 */
	public TipoEntradaConsultarTarifaServicio()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada consultar tarifa servicio.
	 *
	 * @param tipoDocSolicitante de tipo doc solicitante
	 * @param numeroDocSolicitante de numero doc solicitante
	 * @param codigoConvenio de codigo convenio
	 * @param canalOrigen de canal origen
	 * @param servicios de servicios
	 */
	public TipoEntradaConsultarTarifaServicio(
	    java.lang.String tipoDocSolicitante, java.lang.String numeroDocSolicitante, java.lang.String codigoConvenio,
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicioCanalOrigen canalOrigen,
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSI[] servicios
	)
	{
		this.tipoDocSolicitante       = tipoDocSolicitante;
		this.numeroDocSolicitante     = numeroDocSolicitante;
		this.codigoConvenio           = codigoConvenio;
		this.canalOrigen              = canalOrigen;
		this.servicios                = servicios;
	}

	/**
	 * Sets the canalOrigen value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @param canalOrigen de canal origen
	 */
	public void setCanalOrigen(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicioCanalOrigen canalOrigen
	)
	{
		this.canalOrigen = canalOrigen;
	}

	/**
	 * Gets the canalOrigen value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @return canalOrigen
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoEntradaConsultarTarifaServicioCanalOrigen getCanalOrigen()
	{
		return canalOrigen;
	}

	/**
	 * Sets the codigoConvenio value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @param codigoConvenio de codigo convenio
	 */
	public void setCodigoConvenio(java.lang.String codigoConvenio)
	{
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * Gets the codigoConvenio value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @return codigoConvenio
	 */
	public java.lang.String getCodigoConvenio()
	{
		return codigoConvenio;
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
	 * Sets the numeroDocSolicitante value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @param numeroDocSolicitante de numero doc solicitante
	 */
	public void setNumeroDocSolicitante(java.lang.String numeroDocSolicitante)
	{
		this.numeroDocSolicitante = numeroDocSolicitante;
	}

	/**
	 * Gets the numeroDocSolicitante value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @return numeroDocSolicitante
	 */
	public java.lang.String getNumeroDocSolicitante()
	{
		return numeroDocSolicitante;
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
	 * Sets the servicios value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @param servicios de servicios
	 */
	public void setServicios(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSI[] servicios
	)
	{
		this.servicios = servicios;
	}

	/**
	 * Gets the servicios value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @return servicios
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.consultartarifaservicio.v2.TipoServicioCTSI[] getServicios()
	{
		return servicios;
	}

	/**
	 * Sets the tipoDocSolicitante value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @param tipoDocSolicitante de tipo doc solicitante
	 */
	public void setTipoDocSolicitante(java.lang.String tipoDocSolicitante)
	{
		this.tipoDocSolicitante = tipoDocSolicitante;
	}

	/**
	 * Gets the tipoDocSolicitante value for this TipoEntradaConsultarTarifaServicio.
	 *
	 * @return tipoDocSolicitante
	 */
	public java.lang.String getTipoDocSolicitante()
	{
		return tipoDocSolicitante;
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
		if(!(obj instanceof TipoEntradaConsultarTarifaServicio))
			return false;

		TipoEntradaConsultarTarifaServicio other = (TipoEntradaConsultarTarifaServicio)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.tipoDocSolicitante == null) && (other.getTipoDocSolicitante() == null))
				|| ((this.tipoDocSolicitante != null) && this.tipoDocSolicitante.equals(other.getTipoDocSolicitante())))
				&& (((this.numeroDocSolicitante == null) && (other.getNumeroDocSolicitante() == null))
				|| ((this.numeroDocSolicitante != null)
				&& this.numeroDocSolicitante.equals(other.getNumeroDocSolicitante())))
				&& (((this.codigoConvenio == null) && (other.getCodigoConvenio() == null))
				|| ((this.codigoConvenio != null) && this.codigoConvenio.equals(other.getCodigoConvenio())))
				&& (((this.canalOrigen == null) && (other.getCanalOrigen() == null))
				|| ((this.canalOrigen != null) && this.canalOrigen.equals(other.getCanalOrigen())))
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

		if(getTipoDocSolicitante() != null)
			_hashCode += getTipoDocSolicitante().hashCode();

		if(getNumeroDocSolicitante() != null)
			_hashCode += getNumeroDocSolicitante().hashCode();

		if(getCodigoConvenio() != null)
			_hashCode += getCodigoConvenio().hashCode();

		if(getCanalOrigen() != null)
			_hashCode += getCanalOrigen().hashCode();

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
}
