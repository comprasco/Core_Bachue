/**
 * TipoEntradaGenerarLiquidacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2;


/**
 * Clase que contiene todos las propiedades TipoEntradaGenerarLiquidacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class TipoEntradaGenerarLiquidacion implements java.io.Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7188099158125695126L;

	/** Propiedad type desc. */
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaGenerarLiquidacion.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoEntradaGenerarLiquidacion"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoConvenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "codigoConvenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoCanal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "codigoCanal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoSucursalCanal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "codigoSucursalCanal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("oripSolicitud");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "oripSolicitud"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoPersona");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoPersona"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
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
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "numeroDocSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombreSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "primerNombreSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombreSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "segundoNombreSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellidoSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "primerApellidoSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellidoSolicitante");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "segundoApellidoSolicitante"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("razonSocial");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "razonSocial"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("servicios");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "servicios"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "tipoServicioGLI"
		    )
		);
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/operacionesfinancieras/generarliquidacion/v2",
		        "servicio"
		    )
		);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object                                                                                           __equalsCalc               =
		null;
	
	/** Propiedad codigo canal. */
	private java.lang.String                                                                                           codigoCanal;
	
	/** Propiedad codigo convenio. */
	private java.lang.String                                                                                           codigoConvenio;
	
	/** Propiedad codigo sucursal canal. */
	private java.lang.String                                                                                           codigoSucursalCanal;
	
	/** Propiedad numero doc solicitante. */
	private java.lang.String                                                                                           numeroDocSolicitante;
	
	/** Propiedad orip solicitud. */
	private java.lang.String                                                                                           oripSolicitud;
	
	/** Propiedad primer apellido solicitante. */
	private java.lang.String                                                                                           primerApellidoSolicitante;
	
	/** Propiedad primer nombre solicitante. */
	private java.lang.String                                                                                           primerNombreSolicitante;
	
	/** Propiedad razon social. */
	private java.lang.String                                                                                           razonSocial;
	
	/** Propiedad segundo apellido solicitante. */
	private java.lang.String                                                                                           segundoApellidoSolicitante;
	
	/** Propiedad segundo nombre solicitante. */
	private java.lang.String                                                                                           segundoNombreSolicitante;
	
	/** Propiedad tipo doc solicitante. */
	private java.lang.String                                                                                           tipoDocSolicitante;
	
	/** Propiedad tipo persona. */
	private java.lang.String                                                                                           tipoPersona;
	
	/** Propiedad servicios. */
	private co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLI[] servicios;
	
	/** Propiedad hash code calc. */
	private boolean                                                                                                    __hashCodeCalc             =
		false;

	/**
	 * Instancia un nuevo objeto tipo entrada generar liquidacion.
	 */
	public TipoEntradaGenerarLiquidacion()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada generar liquidacion.
	 *
	 * @param codigoConvenio de codigo convenio
	 * @param codigoCanal de codigo canal
	 * @param codigoSucursalCanal de codigo sucursal canal
	 * @param oripSolicitud de orip solicitud
	 * @param tipoPersona de tipo persona
	 * @param tipoDocSolicitante de tipo doc solicitante
	 * @param numeroDocSolicitante de numero doc solicitante
	 * @param primerNombreSolicitante de primer nombre solicitante
	 * @param segundoNombreSolicitante de segundo nombre solicitante
	 * @param primerApellidoSolicitante de primer apellido solicitante
	 * @param segundoApellidoSolicitante de segundo apellido solicitante
	 * @param razonSocial de razon social
	 * @param servicios de servicios
	 */
	public TipoEntradaGenerarLiquidacion(
	    java.lang.String codigoConvenio, java.lang.String codigoCanal, java.lang.String codigoSucursalCanal,
	    java.lang.String oripSolicitud, java.lang.String tipoPersona, java.lang.String tipoDocSolicitante,
	    java.lang.String numeroDocSolicitante, java.lang.String primerNombreSolicitante,
	    java.lang.String segundoNombreSolicitante, java.lang.String primerApellidoSolicitante,
	    java.lang.String segundoApellidoSolicitante, java.lang.String razonSocial,
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLI[] servicios
	)
	{
		this.codigoConvenio                 = codigoConvenio;
		this.codigoCanal                    = codigoCanal;
		this.codigoSucursalCanal            = codigoSucursalCanal;
		this.oripSolicitud                  = oripSolicitud;
		this.tipoPersona                    = tipoPersona;
		this.tipoDocSolicitante             = tipoDocSolicitante;
		this.numeroDocSolicitante           = numeroDocSolicitante;
		this.primerNombreSolicitante        = primerNombreSolicitante;
		this.segundoNombreSolicitante       = segundoNombreSolicitante;
		this.primerApellidoSolicitante      = primerApellidoSolicitante;
		this.segundoApellidoSolicitante     = segundoApellidoSolicitante;
		this.razonSocial                    = razonSocial;
		this.servicios                      = servicios;
	}

	/**
	 * Sets the codigoCanal value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param codigoCanal de codigo canal
	 */
	public void setCodigoCanal(java.lang.String codigoCanal)
	{
		this.codigoCanal = codigoCanal;
	}

	/**
	 * Gets the codigoCanal value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return codigoCanal
	 */
	public java.lang.String getCodigoCanal()
	{
		return codigoCanal;
	}

	/**
	 * Sets the codigoConvenio value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param codigoConvenio de codigo convenio
	 */
	public void setCodigoConvenio(java.lang.String codigoConvenio)
	{
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * Gets the codigoConvenio value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return codigoConvenio
	 */
	public java.lang.String getCodigoConvenio()
	{
		return codigoConvenio;
	}

	/**
	 * Sets the codigoSucursalCanal value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param codigoSucursalCanal de codigo sucursal canal
	 */
	public void setCodigoSucursalCanal(java.lang.String codigoSucursalCanal)
	{
		this.codigoSucursalCanal = codigoSucursalCanal;
	}

	/**
	 * Gets the codigoSucursalCanal value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return codigoSucursalCanal
	 */
	public java.lang.String getCodigoSucursalCanal()
	{
		return codigoSucursalCanal;
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
	 * Sets the numeroDocSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param numeroDocSolicitante de numero doc solicitante
	 */
	public void setNumeroDocSolicitante(java.lang.String numeroDocSolicitante)
	{
		this.numeroDocSolicitante = numeroDocSolicitante;
	}

	/**
	 * Gets the numeroDocSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return numeroDocSolicitante
	 */
	public java.lang.String getNumeroDocSolicitante()
	{
		return numeroDocSolicitante;
	}

	/**
	 * Sets the oripSolicitud value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param oripSolicitud de orip solicitud
	 */
	public void setOripSolicitud(java.lang.String oripSolicitud)
	{
		this.oripSolicitud = oripSolicitud;
	}

	/**
	 * Gets the oripSolicitud value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return oripSolicitud
	 */
	public java.lang.String getOripSolicitud()
	{
		return oripSolicitud;
	}

	/**
	 * Sets the primerApellidoSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param primerApellidoSolicitante de primer apellido solicitante
	 */
	public void setPrimerApellidoSolicitante(java.lang.String primerApellidoSolicitante)
	{
		this.primerApellidoSolicitante = primerApellidoSolicitante;
	}

	/**
	 * Gets the primerApellidoSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return primerApellidoSolicitante
	 */
	public java.lang.String getPrimerApellidoSolicitante()
	{
		return primerApellidoSolicitante;
	}

	/**
	 * Sets the primerNombreSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param primerNombreSolicitante de primer nombre solicitante
	 */
	public void setPrimerNombreSolicitante(java.lang.String primerNombreSolicitante)
	{
		this.primerNombreSolicitante = primerNombreSolicitante;
	}

	/**
	 * Gets the primerNombreSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return primerNombreSolicitante
	 */
	public java.lang.String getPrimerNombreSolicitante()
	{
		return primerNombreSolicitante;
	}

	/**
	 * Sets the razonSocial value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param razonSocial de razon social
	 */
	public void setRazonSocial(java.lang.String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the razonSocial value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return razonSocial
	 */
	public java.lang.String getRazonSocial()
	{
		return razonSocial;
	}

	/**
	 * Sets the segundoApellidoSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param segundoApellidoSolicitante de segundo apellido solicitante
	 */
	public void setSegundoApellidoSolicitante(java.lang.String segundoApellidoSolicitante)
	{
		this.segundoApellidoSolicitante = segundoApellidoSolicitante;
	}

	/**
	 * Gets the segundoApellidoSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return segundoApellidoSolicitante
	 */
	public java.lang.String getSegundoApellidoSolicitante()
	{
		return segundoApellidoSolicitante;
	}

	/**
	 * Sets the segundoNombreSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param segundoNombreSolicitante de segundo nombre solicitante
	 */
	public void setSegundoNombreSolicitante(java.lang.String segundoNombreSolicitante)
	{
		this.segundoNombreSolicitante = segundoNombreSolicitante;
	}

	/**
	 * Gets the segundoNombreSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return segundoNombreSolicitante
	 */
	public java.lang.String getSegundoNombreSolicitante()
	{
		return segundoNombreSolicitante;
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
	 * Sets the servicios value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param servicios de servicios
	 */
	public void setServicios(
	    co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLI[] servicios
	)
	{
		this.servicios = servicios;
	}

	/**
	 * Gets the servicios value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return servicios
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.operacionesfinancieras.generarliquidacion.v2.TipoServicioGLI[] getServicios()
	{
		return servicios;
	}

	/**
	 * Sets the tipoDocSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param tipoDocSolicitante de tipo doc solicitante
	 */
	public void setTipoDocSolicitante(java.lang.String tipoDocSolicitante)
	{
		this.tipoDocSolicitante = tipoDocSolicitante;
	}

	/**
	 * Gets the tipoDocSolicitante value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return tipoDocSolicitante
	 */
	public java.lang.String getTipoDocSolicitante()
	{
		return tipoDocSolicitante;
	}

	/**
	 * Sets the tipoPersona value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @param tipoPersona de tipo persona
	 */
	public void setTipoPersona(java.lang.String tipoPersona)
	{
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the tipoPersona value for this TipoEntradaGenerarLiquidacion.
	 *
	 * @return tipoPersona
	 */
	public java.lang.String getTipoPersona()
	{
		return tipoPersona;
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
		if(!(obj instanceof TipoEntradaGenerarLiquidacion))
			return false;

		TipoEntradaGenerarLiquidacion other = (TipoEntradaGenerarLiquidacion)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.codigoConvenio == null) && (other.getCodigoConvenio() == null))
				|| ((this.codigoConvenio != null) && this.codigoConvenio.equals(other.getCodigoConvenio())))
				&& (((this.codigoCanal == null) && (other.getCodigoCanal() == null))
				|| ((this.codigoCanal != null) && this.codigoCanal.equals(other.getCodigoCanal())))
				&& (((this.codigoSucursalCanal == null) && (other.getCodigoSucursalCanal() == null))
				|| ((this.codigoSucursalCanal != null)
				&& this.codigoSucursalCanal.equals(other.getCodigoSucursalCanal())))
				&& (((this.oripSolicitud == null) && (other.getOripSolicitud() == null))
				|| ((this.oripSolicitud != null) && this.oripSolicitud.equals(other.getOripSolicitud())))
				&& (((this.tipoPersona == null) && (other.getTipoPersona() == null))
				|| ((this.tipoPersona != null) && this.tipoPersona.equals(other.getTipoPersona())))
				&& (((this.tipoDocSolicitante == null) && (other.getTipoDocSolicitante() == null))
				|| ((this.tipoDocSolicitante != null) && this.tipoDocSolicitante.equals(other.getTipoDocSolicitante())))
				&& (((this.numeroDocSolicitante == null) && (other.getNumeroDocSolicitante() == null))
				|| ((this.numeroDocSolicitante != null)
				&& this.numeroDocSolicitante.equals(other.getNumeroDocSolicitante())))
				&& (((this.primerNombreSolicitante == null) && (other.getPrimerNombreSolicitante() == null))
				|| ((this.primerNombreSolicitante != null)
				&& this.primerNombreSolicitante.equals(other.getPrimerNombreSolicitante())))
				&& (((this.segundoNombreSolicitante == null) && (other.getSegundoNombreSolicitante() == null))
				|| ((this.segundoNombreSolicitante != null)
				&& this.segundoNombreSolicitante.equals(other.getSegundoNombreSolicitante())))
				&& (((this.primerApellidoSolicitante == null) && (other.getPrimerApellidoSolicitante() == null))
				|| ((this.primerApellidoSolicitante != null)
				&& this.primerApellidoSolicitante.equals(other.getPrimerApellidoSolicitante())))
				&& (((this.segundoApellidoSolicitante == null) && (other.getSegundoApellidoSolicitante() == null))
				|| ((this.segundoApellidoSolicitante != null)
				&& this.segundoApellidoSolicitante.equals(other.getSegundoApellidoSolicitante())))
				&& (((this.razonSocial == null) && (other.getRazonSocial() == null))
				|| ((this.razonSocial != null) && this.razonSocial.equals(other.getRazonSocial())))
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

		if(getCodigoConvenio() != null)
			_hashCode += getCodigoConvenio().hashCode();

		if(getCodigoCanal() != null)
			_hashCode += getCodigoCanal().hashCode();

		if(getCodigoSucursalCanal() != null)
			_hashCode += getCodigoSucursalCanal().hashCode();

		if(getOripSolicitud() != null)
			_hashCode += getOripSolicitud().hashCode();

		if(getTipoPersona() != null)
			_hashCode += getTipoPersona().hashCode();

		if(getTipoDocSolicitante() != null)
			_hashCode += getTipoDocSolicitante().hashCode();

		if(getNumeroDocSolicitante() != null)
			_hashCode += getNumeroDocSolicitante().hashCode();

		if(getPrimerNombreSolicitante() != null)
			_hashCode += getPrimerNombreSolicitante().hashCode();

		if(getSegundoNombreSolicitante() != null)
			_hashCode += getSegundoNombreSolicitante().hashCode();

		if(getPrimerApellidoSolicitante() != null)
			_hashCode += getPrimerApellidoSolicitante().hashCode();

		if(getSegundoApellidoSolicitante() != null)
			_hashCode += getSegundoApellidoSolicitante().hashCode();

		if(getRazonSocial() != null)
			_hashCode += getRazonSocial().hashCode();

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
