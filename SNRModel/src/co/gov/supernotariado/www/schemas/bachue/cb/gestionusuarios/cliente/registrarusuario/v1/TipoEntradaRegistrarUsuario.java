/**
 * TipoEntradaRegistrarUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.cliente.registrarusuario.v1;

public class TipoEntradaRegistrarUsuario implements java.io.Serializable
{
	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
		    TipoEntradaRegistrarUsuario.class, true
		);

	static
	{
		typeDesc.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "tipoEntradaRegistrarUsuario"
		    )
		);

		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroSolicitud");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "numeroSolicitud"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("loginUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "loginUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "tipoDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("numeroDocumento");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "numeroDocumento"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerNombreUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "primerNombreUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoNombreUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "segundoNombreUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("primerApellidoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "primerApellidoUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoApellidoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "segundoApellidoUsuario"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("correoElectronicoCorporativo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "correoElectronicoCorporativo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("correoElectronicoPersonal");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "correoElectronicoPersonal"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("tipoCambio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1", "tipoCambio"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        ">tipoEntradaRegistrarUsuario>tipoCambio"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("segundoFactor");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "segundoFactor"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        ">tipoEntradaRegistrarUsuario>segundoFactor"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigosRoles");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "codigosRoles"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1", "codigoRol"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigosORIPs");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "codigosORIPs"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		elemField.setItemQName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1", "codigoORIP"
		    )
		);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("codigoConvenio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "codigoConvenio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idEntidadExterna");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "idEntidadExterna"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("estadoUsuario");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "estadoUsuario"
		    )
		);
		elemField.setXmlType(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        ">tipoEntradaRegistrarUsuario>estadoUsuario"
		    )
		);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("cargo");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1", "cargo"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("justificacionCambio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "justificacionCambio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaInicio");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1", "fechaInicio"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaFin");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1", "fechaFin"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("fechaVigenciaSegundaClave");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1",
		        "fechaVigenciaSegundaClave"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	private java.lang.Object                         __equalsCalc                 = null;
	private java.lang.String                         cargo;
	private java.lang.String                         codigoConvenio;
	private java.lang.String                         correoElectronicoCorporativo;
	private java.lang.String                         correoElectronicoPersonal;
	private java.util.Calendar                       fechaFin;
	private java.util.Calendar                       fechaInicio;
	private java.util.Calendar                       fechaVigenciaSegundaClave;
	private java.lang.String                         idEntidadExterna;
	private java.lang.String                         justificacionCambio;
	private java.lang.String                         loginUsuario;
	private java.lang.String                         numeroDocumento;
	private java.lang.String                         numeroSolicitud;
	private java.lang.String                         primerApellidoUsuario;
	private java.lang.String                         primerNombreUsuario;
	private java.lang.String                         segundoApellidoUsuario;
	private java.lang.String                         segundoNombreUsuario;
	private java.lang.String                         tipoDocumento;
	private TipoEntradaRegistrarUsuarioEstadoUsuario estadoUsuario;
	private TipoEntradaRegistrarUsuarioSegundoFactor segundoFactor;
	private TipoEntradaRegistrarUsuarioTipoCambio    tipoCambio;
	private java.lang.String[]                       codigosORIPs;
	private java.lang.String[]                       codigosRoles;
	private boolean                                  __hashCodeCalc               = false;

	public TipoEntradaRegistrarUsuario()
	{
	}

	public TipoEntradaRegistrarUsuario(
	    java.lang.String numeroSolicitud, java.lang.String loginUsuario, java.lang.String tipoDocumento,
	    java.lang.String numeroDocumento, java.lang.String primerNombreUsuario, java.lang.String segundoNombreUsuario,
	    java.lang.String primerApellidoUsuario, java.lang.String segundoApellidoUsuario,
	    java.lang.String correoElectronicoCorporativo, java.lang.String correoElectronicoPersonal,
	    TipoEntradaRegistrarUsuarioTipoCambio tipoCambio, TipoEntradaRegistrarUsuarioSegundoFactor segundoFactor,
	    java.lang.String[] codigosRoles, java.lang.String[] codigosORIPs, java.lang.String codigoConvenio,
	    java.lang.String idEntidadExterna, TipoEntradaRegistrarUsuarioEstadoUsuario estadoUsuario,
	    java.lang.String cargo, java.lang.String justificacionCambio, java.util.Calendar fechaInicio,
	    java.util.Calendar fechaFin, java.util.Calendar fechaVigenciaSegundaClave
	)
	{
		this.numeroSolicitud                  = numeroSolicitud;
		this.loginUsuario                     = loginUsuario;
		this.tipoDocumento                    = tipoDocumento;
		this.numeroDocumento                  = numeroDocumento;
		this.primerNombreUsuario              = primerNombreUsuario;
		this.segundoNombreUsuario             = segundoNombreUsuario;
		this.primerApellidoUsuario            = primerApellidoUsuario;
		this.segundoApellidoUsuario           = segundoApellidoUsuario;
		this.correoElectronicoCorporativo     = correoElectronicoCorporativo;
		this.correoElectronicoPersonal        = correoElectronicoPersonal;
		this.tipoCambio                       = tipoCambio;
		this.segundoFactor                    = segundoFactor;
		this.codigosRoles                     = codigosRoles;
		this.codigosORIPs                     = codigosORIPs;
		this.codigoConvenio                   = codigoConvenio;
		this.idEntidadExterna                 = idEntidadExterna;
		this.estadoUsuario                    = estadoUsuario;
		this.cargo                            = cargo;
		this.justificacionCambio              = justificacionCambio;
		this.fechaInicio                      = fechaInicio;
		this.fechaFin                         = fechaFin;
		this.fechaVigenciaSegundaClave        = fechaVigenciaSegundaClave;
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(
	    java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType
	)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Sets the cargo value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param cargo
	 */
	public void setCargo(java.lang.String cargo)
	{
		this.cargo = cargo;
	}

	/**
	 * Gets the cargo value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return cargo
	 */
	public java.lang.String getCargo()
	{
		return cargo;
	}

	/**
	 * Sets the codigoConvenio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param codigoConvenio
	 */
	public void setCodigoConvenio(java.lang.String codigoConvenio)
	{
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * Gets the codigoConvenio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return codigoConvenio
	 */
	public java.lang.String getCodigoConvenio()
	{
		return codigoConvenio;
	}

	/**
	 * Sets the codigosORIPs value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param codigosORIPs
	 */
	public void setCodigosORIPs(java.lang.String[] codigosORIPs)
	{
		this.codigosORIPs = codigosORIPs;
	}

	/**
	 * Gets the codigosORIPs value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return codigosORIPs
	 */
	public java.lang.String[] getCodigosORIPs()
	{
		return codigosORIPs;
	}

	/**
	 * Sets the codigosRoles value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param codigosRoles
	 */
	public void setCodigosRoles(java.lang.String[] codigosRoles)
	{
		this.codigosRoles = codigosRoles;
	}

	/**
	 * Gets the codigosRoles value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return codigosRoles
	 */
	public java.lang.String[] getCodigosRoles()
	{
		return codigosRoles;
	}

	/**
	 * Sets the correoElectronicoCorporativo value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param correoElectronicoCorporativo
	 */
	public void setCorreoElectronicoCorporativo(java.lang.String correoElectronicoCorporativo)
	{
		this.correoElectronicoCorporativo = correoElectronicoCorporativo;
	}

	/**
	 * Gets the correoElectronicoCorporativo value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return correoElectronicoCorporativo
	 */
	public java.lang.String getCorreoElectronicoCorporativo()
	{
		return correoElectronicoCorporativo;
	}

	/**
	 * Sets the correoElectronicoPersonal value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param correoElectronicoPersonal
	 */
	public void setCorreoElectronicoPersonal(java.lang.String correoElectronicoPersonal)
	{
		this.correoElectronicoPersonal = correoElectronicoPersonal;
	}

	/**
	 * Gets the correoElectronicoPersonal value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return correoElectronicoPersonal
	 */
	public java.lang.String getCorreoElectronicoPersonal()
	{
		return correoElectronicoPersonal;
	}

	/**
	 * Sets the estadoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param estadoUsuario
	 */
	public void setEstadoUsuario(TipoEntradaRegistrarUsuarioEstadoUsuario estadoUsuario)
	{
		this.estadoUsuario = estadoUsuario;
	}

	/**
	 * Gets the estadoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return estadoUsuario
	 */
	public TipoEntradaRegistrarUsuarioEstadoUsuario getEstadoUsuario()
	{
		return estadoUsuario;
	}

	/**
	 * Sets the fechaFin value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param fechaFin
	 */
	public void setFechaFin(java.util.Calendar fechaFin)
	{
		this.fechaFin = fechaFin;
	}

	/**
	 * Gets the fechaFin value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return fechaFin
	 */
	public java.util.Calendar getFechaFin()
	{
		return fechaFin;
	}

	/**
	 * Sets the fechaInicio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param fechaInicio
	 */
	public void setFechaInicio(java.util.Calendar fechaInicio)
	{
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the fechaInicio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return fechaInicio
	 */
	public java.util.Calendar getFechaInicio()
	{
		return fechaInicio;
	}

	/**
	 * Sets the fechaVigenciaSegundaClave value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param fechaVigenciaSegundaClave
	 */
	public void setFechaVigenciaSegundaClave(java.util.Calendar fechaVigenciaSegundaClave)
	{
		this.fechaVigenciaSegundaClave = fechaVigenciaSegundaClave;
	}

	/**
	 * Gets the fechaVigenciaSegundaClave value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return fechaVigenciaSegundaClave
	 */
	public java.util.Calendar getFechaVigenciaSegundaClave()
	{
		return fechaVigenciaSegundaClave;
	}

	/**
	 * Sets the idEntidadExterna value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param idEntidadExterna
	 */
	public void setIdEntidadExterna(java.lang.String idEntidadExterna)
	{
		this.idEntidadExterna = idEntidadExterna;
	}

	/**
	 * Gets the idEntidadExterna value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return idEntidadExterna
	 */
	public java.lang.String getIdEntidadExterna()
	{
		return idEntidadExterna;
	}

	/**
	 * Sets the justificacionCambio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param justificacionCambio
	 */
	public void setJustificacionCambio(java.lang.String justificacionCambio)
	{
		this.justificacionCambio = justificacionCambio;
	}

	/**
	 * Gets the justificacionCambio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return justificacionCambio
	 */
	public java.lang.String getJustificacionCambio()
	{
		return justificacionCambio;
	}

	/**
	 * Sets the loginUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param loginUsuario
	 */
	public void setLoginUsuario(java.lang.String loginUsuario)
	{
		this.loginUsuario = loginUsuario;
	}

	/**
	 * Gets the loginUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return loginUsuario
	 */
	public java.lang.String getLoginUsuario()
	{
		return loginUsuario;
	}

	/**
	 * Sets the numeroDocumento value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param numeroDocumento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the numeroDocumento value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return numeroDocumento
	 */
	public java.lang.String getNumeroDocumento()
	{
		return numeroDocumento;
	}

	/**
	 * Sets the numeroSolicitud value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param numeroSolicitud
	 */
	public void setNumeroSolicitud(java.lang.String numeroSolicitud)
	{
		this.numeroSolicitud = numeroSolicitud;
	}

	/**
	 * Gets the numeroSolicitud value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return numeroSolicitud
	 */
	public java.lang.String getNumeroSolicitud()
	{
		return numeroSolicitud;
	}

	/**
	 * Sets the primerApellidoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param primerApellidoUsuario
	 */
	public void setPrimerApellidoUsuario(java.lang.String primerApellidoUsuario)
	{
		this.primerApellidoUsuario = primerApellidoUsuario;
	}

	/**
	 * Gets the primerApellidoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return primerApellidoUsuario
	 */
	public java.lang.String getPrimerApellidoUsuario()
	{
		return primerApellidoUsuario;
	}

	/**
	 * Sets the primerNombreUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param primerNombreUsuario
	 */
	public void setPrimerNombreUsuario(java.lang.String primerNombreUsuario)
	{
		this.primerNombreUsuario = primerNombreUsuario;
	}

	/**
	 * Gets the primerNombreUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return primerNombreUsuario
	 */
	public java.lang.String getPrimerNombreUsuario()
	{
		return primerNombreUsuario;
	}

	/**
	 * Sets the segundoApellidoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param segundoApellidoUsuario
	 */
	public void setSegundoApellidoUsuario(java.lang.String segundoApellidoUsuario)
	{
		this.segundoApellidoUsuario = segundoApellidoUsuario;
	}

	/**
	 * Gets the segundoApellidoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return segundoApellidoUsuario
	 */
	public java.lang.String getSegundoApellidoUsuario()
	{
		return segundoApellidoUsuario;
	}

	/**
	 * Sets the segundoFactor value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param segundoFactor
	 */
	public void setSegundoFactor(TipoEntradaRegistrarUsuarioSegundoFactor segundoFactor)
	{
		this.segundoFactor = segundoFactor;
	}

	/**
	 * Gets the segundoFactor value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return segundoFactor
	 */
	public TipoEntradaRegistrarUsuarioSegundoFactor getSegundoFactor()
	{
		return segundoFactor;
	}

	/**
	 * Sets the segundoNombreUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param segundoNombreUsuario
	 */
	public void setSegundoNombreUsuario(java.lang.String segundoNombreUsuario)
	{
		this.segundoNombreUsuario = segundoNombreUsuario;
	}

	/**
	 * Gets the segundoNombreUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return segundoNombreUsuario
	 */
	public java.lang.String getSegundoNombreUsuario()
	{
		return segundoNombreUsuario;
	}

	/**
	 * Sets the tipoCambio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param tipoCambio
	 */
	public void setTipoCambio(TipoEntradaRegistrarUsuarioTipoCambio tipoCambio)
	{
		this.tipoCambio = tipoCambio;
	}

	/**
	 * Gets the tipoCambio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return tipoCambio
	 */
	public TipoEntradaRegistrarUsuarioTipoCambio getTipoCambio()
	{
		return tipoCambio;
	}

	/**
	 * Sets the tipoDocumento value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(java.lang.String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the tipoDocumento value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return tipoDocumento
	 */
	public java.lang.String getTipoDocumento()
	{
		return tipoDocumento;
	}

	public synchronized boolean equals(java.lang.Object obj)
	{
		if(!(obj instanceof TipoEntradaRegistrarUsuario))
			return false;

		TipoEntradaRegistrarUsuario other = (TipoEntradaRegistrarUsuario)obj;

		if(obj == null)
			return false;

		if(this == obj)
			return true;

		if(__equalsCalc != null)
			return (__equalsCalc == obj);

		__equalsCalc = obj;

		boolean _equals;
		_equals          = true
				&& (((this.numeroSolicitud == null) && (other.getNumeroSolicitud() == null))
				|| ((this.numeroSolicitud != null) && this.numeroSolicitud.equals(other.getNumeroSolicitud())))
				&& (((this.loginUsuario == null) && (other.getLoginUsuario() == null))
				|| ((this.loginUsuario != null) && this.loginUsuario.equals(other.getLoginUsuario())))
				&& (((this.tipoDocumento == null) && (other.getTipoDocumento() == null))
				|| ((this.tipoDocumento != null) && this.tipoDocumento.equals(other.getTipoDocumento())))
				&& (((this.numeroDocumento == null) && (other.getNumeroDocumento() == null))
				|| ((this.numeroDocumento != null) && this.numeroDocumento.equals(other.getNumeroDocumento())))
				&& (((this.primerNombreUsuario == null) && (other.getPrimerNombreUsuario() == null))
				|| ((this.primerNombreUsuario != null)
				&& this.primerNombreUsuario.equals(other.getPrimerNombreUsuario())))
				&& (((this.segundoNombreUsuario == null) && (other.getSegundoNombreUsuario() == null))
				|| ((this.segundoNombreUsuario != null)
				&& this.segundoNombreUsuario.equals(other.getSegundoNombreUsuario())))
				&& (((this.primerApellidoUsuario == null) && (other.getPrimerApellidoUsuario() == null))
				|| ((this.primerApellidoUsuario != null)
				&& this.primerApellidoUsuario.equals(other.getPrimerApellidoUsuario())))
				&& (((this.segundoApellidoUsuario == null) && (other.getSegundoApellidoUsuario() == null))
				|| ((this.segundoApellidoUsuario != null)
				&& this.segundoApellidoUsuario.equals(other.getSegundoApellidoUsuario())))
				&& (((this.correoElectronicoCorporativo == null) && (other.getCorreoElectronicoCorporativo() == null))
				|| ((this.correoElectronicoCorporativo != null)
				&& this.correoElectronicoCorporativo.equals(other.getCorreoElectronicoCorporativo())))
				&& (((this.correoElectronicoPersonal == null) && (other.getCorreoElectronicoPersonal() == null))
				|| ((this.correoElectronicoPersonal != null)
				&& this.correoElectronicoPersonal.equals(other.getCorreoElectronicoPersonal())))
				&& (((this.tipoCambio == null) && (other.getTipoCambio() == null))
				|| ((this.tipoCambio != null) && this.tipoCambio.equals(other.getTipoCambio())))
				&& (((this.segundoFactor == null) && (other.getSegundoFactor() == null))
				|| ((this.segundoFactor != null) && this.segundoFactor.equals(other.getSegundoFactor())))
				&& (((this.codigosRoles == null) && (other.getCodigosRoles() == null))
				|| ((this.codigosRoles != null) && java.util.Arrays.equals(this.codigosRoles, other.getCodigosRoles())))
				&& (((this.codigosORIPs == null) && (other.getCodigosORIPs() == null))
				|| ((this.codigosORIPs != null) && java.util.Arrays.equals(this.codigosORIPs, other.getCodigosORIPs())))
				&& (((this.codigoConvenio == null) && (other.getCodigoConvenio() == null))
				|| ((this.codigoConvenio != null) && this.codigoConvenio.equals(other.getCodigoConvenio())))
				&& (((this.idEntidadExterna == null) && (other.getIdEntidadExterna() == null))
				|| ((this.idEntidadExterna != null) && this.idEntidadExterna.equals(other.getIdEntidadExterna())))
				&& (((this.estadoUsuario == null) && (other.getEstadoUsuario() == null))
				|| ((this.estadoUsuario != null) && this.estadoUsuario.equals(other.getEstadoUsuario())))
				&& (((this.cargo == null) && (other.getCargo() == null))
				|| ((this.cargo != null) && this.cargo.equals(other.getCargo())))
				&& (((this.justificacionCambio == null) && (other.getJustificacionCambio() == null))
				|| ((this.justificacionCambio != null)
				&& this.justificacionCambio.equals(other.getJustificacionCambio())))
				&& (((this.fechaInicio == null) && (other.getFechaInicio() == null))
				|| ((this.fechaInicio != null) && this.fechaInicio.equals(other.getFechaInicio())))
				&& (((this.fechaFin == null) && (other.getFechaFin() == null))
				|| ((this.fechaFin != null) && this.fechaFin.equals(other.getFechaFin())))
				&& (((this.fechaVigenciaSegundaClave == null) && (other.getFechaVigenciaSegundaClave() == null))
				|| ((this.fechaVigenciaSegundaClave != null)
				&& this.fechaVigenciaSegundaClave.equals(other.getFechaVigenciaSegundaClave())));
		__equalsCalc     = null;

		return _equals;
	}

	public synchronized int hashCode()
	{
		if(__hashCodeCalc)
			return 0;

		__hashCodeCalc = true;

		int _hashCode = 1;

		if(getNumeroSolicitud() != null)
			_hashCode += getNumeroSolicitud().hashCode();

		if(getLoginUsuario() != null)
			_hashCode += getLoginUsuario().hashCode();

		if(getTipoDocumento() != null)
			_hashCode += getTipoDocumento().hashCode();

		if(getNumeroDocumento() != null)
			_hashCode += getNumeroDocumento().hashCode();

		if(getPrimerNombreUsuario() != null)
			_hashCode += getPrimerNombreUsuario().hashCode();

		if(getSegundoNombreUsuario() != null)
			_hashCode += getSegundoNombreUsuario().hashCode();

		if(getPrimerApellidoUsuario() != null)
			_hashCode += getPrimerApellidoUsuario().hashCode();

		if(getSegundoApellidoUsuario() != null)
			_hashCode += getSegundoApellidoUsuario().hashCode();

		if(getCorreoElectronicoCorporativo() != null)
			_hashCode += getCorreoElectronicoCorporativo().hashCode();

		if(getCorreoElectronicoPersonal() != null)
			_hashCode += getCorreoElectronicoPersonal().hashCode();

		if(getTipoCambio() != null)
			_hashCode += getTipoCambio().hashCode();

		if(getSegundoFactor() != null)
			_hashCode += getSegundoFactor().hashCode();

		if(getCodigosRoles() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getCodigosRoles()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getCodigosRoles(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigosORIPs() != null)
		{
			for(int i = 0; i < java.lang.reflect.Array.getLength(getCodigosORIPs()); i++)
			{
				java.lang.Object obj = java.lang.reflect.Array.get(getCodigosORIPs(), i);

				if((obj != null) && !obj.getClass().isArray())
					_hashCode += obj.hashCode();
			}
		}

		if(getCodigoConvenio() != null)
			_hashCode += getCodigoConvenio().hashCode();

		if(getIdEntidadExterna() != null)
			_hashCode += getIdEntidadExterna().hashCode();

		if(getEstadoUsuario() != null)
			_hashCode += getEstadoUsuario().hashCode();

		if(getCargo() != null)
			_hashCode += getCargo().hashCode();

		if(getJustificacionCambio() != null)
			_hashCode += getJustificacionCambio().hashCode();

		if(getFechaInicio() != null)
			_hashCode += getFechaInicio().hashCode();

		if(getFechaFin() != null)
			_hashCode += getFechaFin().hashCode();

		if(getFechaVigenciaSegundaClave() != null)
			_hashCode += getFechaVigenciaSegundaClave().hashCode();

		__hashCodeCalc = false;

		return _hashCode;
	}
}
