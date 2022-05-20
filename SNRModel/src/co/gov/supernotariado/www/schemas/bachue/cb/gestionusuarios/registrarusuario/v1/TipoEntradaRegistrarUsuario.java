/**
 * TipoEntradaRegistrarUsuario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1;



/**
 * Clase que contiene todos las propiedades TipoEntradaRegistrarUsuario.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 28/07/2019
 */
public class TipoEntradaRegistrarUsuario implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2310515831722117061L;

	/** Propiedad type desc. */
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
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idPolitica");
		elemField.setXmlName(
		    new javax.xml.namespace.QName(
		        "https://www.supernotariado.gov.co/schemas/bachue/cb/gestionusuarios/registrarusuario/v1", "idPolitica"
		    )
		);
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/** Propiedad equals calc. */
	private java.lang.Object __equalsCalc = null;

	/** Propiedad cargo. */
	private java.lang.String cargo;

	/** Propiedad codigo convenio. */
	private java.lang.String codigoConvenio;

	/** Propiedad correo electronico corporativo. */
	private java.lang.String correoElectronicoCorporativo;

	/** Propiedad correo electronico personal. */
	private java.lang.String correoElectronicoPersonal;

	/** Propiedad estado usuario. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioEstadoUsuario estadoUsuario;

	/** Propiedad fecha fin. */
	private java.util.Calendar fechaFin;

	/** Propiedad fecha inicio. */
	private java.util.Calendar fechaInicio;

	/** Propiedad fecha vigencia segunda clave. */
	private java.util.Calendar fechaVigenciaSegundaClave;

	/** Propiedad id entidad externa. */
	private java.lang.String idEntidadExterna;

	/** Propiedad id politica. */
	private java.lang.String idPolitica;

	/** Propiedad justificacion cambio. */
	private java.lang.String justificacionCambio;

	/** Propiedad login usuario. */
	private java.lang.String loginUsuario;

	/** Propiedad numero documento. */
	private java.lang.String numeroDocumento;

	/** Propiedad numero solicitud. */
	private java.lang.String numeroSolicitud;

	/** Propiedad primer apellido usuario. */
	private java.lang.String primerApellidoUsuario;

	/** Propiedad primer nombre usuario. */
	private java.lang.String primerNombreUsuario;

	/** Propiedad segundo apellido usuario. */
	private java.lang.String segundoApellidoUsuario;

	/** Propiedad segundo factor. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioSegundoFactor segundoFactor;

	/** Propiedad segundo nombre usuario. */
	private java.lang.String segundoNombreUsuario;

	/** Propiedad tipo cambio. */
	private co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioTipoCambio tipoCambio;

	/** Propiedad tipo documento. */
	private java.lang.String tipoDocumento;

	/** Propiedad codigos ORI ps. */
	private java.lang.String[] codigosORIPs;

	/** Propiedad codigos roles. */
	private java.lang.String[] codigosRoles;

	/** Propiedad hash code calc. */
	private boolean __hashCodeCalc = false;

	/**
	 * Instancia un nuevo objeto tipo entrada registrar usuario.
	 */
	public TipoEntradaRegistrarUsuario()
	{
	}

	/**
	 * Instancia un nuevo objeto tipo entrada registrar usuario.
	 *
	 * @param numeroSolicitud de numero solicitud
	 * @param loginUsuario de login usuario
	 * @param tipoDocumento de tipo documento
	 * @param numeroDocumento de numero documento
	 * @param primerNombreUsuario de primer nombre usuario
	 * @param segundoNombreUsuario de segundo nombre usuario
	 * @param primerApellidoUsuario de primer apellido usuario
	 * @param segundoApellidoUsuario de segundo apellido usuario
	 * @param correoElectronicoCorporativo de correo electronico corporativo
	 * @param correoElectronicoPersonal de correo electronico personal
	 * @param tipoCambio de tipo cambio
	 * @param segundoFactor de segundo factor
	 * @param codigosRoles de codigos roles
	 * @param codigosORIPs de codigos ORI ps
	 * @param codigoConvenio de codigo convenio
	 * @param idEntidadExterna de id entidad externa
	 * @param estadoUsuario de estado usuario
	 * @param cargo de cargo
	 * @param justificacionCambio de justificacion cambio
	 * @param fechaInicio de fecha inicio
	 * @param fechaFin de fecha fin
	 * @param fechaVigenciaSegundaClave de fecha vigencia segunda clave
	 * @param idPolitica de id politica
	 */
	public TipoEntradaRegistrarUsuario(
	    java.lang.String numeroSolicitud, java.lang.String loginUsuario, java.lang.String tipoDocumento,
	    java.lang.String numeroDocumento, java.lang.String primerNombreUsuario, java.lang.String segundoNombreUsuario,
	    java.lang.String primerApellidoUsuario, java.lang.String segundoApellidoUsuario,
	    java.lang.String correoElectronicoCorporativo, java.lang.String correoElectronicoPersonal,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioTipoCambio tipoCambio,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioSegundoFactor segundoFactor,
	    java.lang.String[] codigosRoles, java.lang.String[] codigosORIPs, java.lang.String codigoConvenio,
	    java.lang.String idEntidadExterna,
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioEstadoUsuario estadoUsuario,
	    java.lang.String cargo, java.lang.String justificacionCambio, java.util.Calendar fechaInicio,
	    java.util.Calendar fechaFin, java.util.Calendar fechaVigenciaSegundaClave, java.lang.String idPolitica
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
		this.idPolitica                       = idPolitica;
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
	 * Sets the numeroSolicitud value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param numeroSolicitud de numero solicitud
	 */
	public void setNumeroSolicitud(java.lang.String numeroSolicitud)
	{
		this.numeroSolicitud = numeroSolicitud;
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
	 * Sets the loginUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param loginUsuario de login usuario
	 */
	public void setLoginUsuario(java.lang.String loginUsuario)
	{
		this.loginUsuario = loginUsuario;
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

	/**
	 * Sets the tipoDocumento value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param tipoDocumento de tipo documento
	 */
	public void setTipoDocumento(java.lang.String tipoDocumento)
	{
		this.tipoDocumento = tipoDocumento;
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
	 * Sets the numeroDocumento value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param numeroDocumento de numero documento
	 */
	public void setNumeroDocumento(java.lang.String numeroDocumento)
	{
		this.numeroDocumento = numeroDocumento;
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
	 * Sets the primerNombreUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param primerNombreUsuario de primer nombre usuario
	 */
	public void setPrimerNombreUsuario(java.lang.String primerNombreUsuario)
	{
		this.primerNombreUsuario = primerNombreUsuario;
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
	 * Sets the segundoNombreUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param segundoNombreUsuario de segundo nombre usuario
	 */
	public void setSegundoNombreUsuario(java.lang.String segundoNombreUsuario)
	{
		this.segundoNombreUsuario = segundoNombreUsuario;
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
	 * Sets the primerApellidoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param primerApellidoUsuario de primer apellido usuario
	 */
	public void setPrimerApellidoUsuario(java.lang.String primerApellidoUsuario)
	{
		this.primerApellidoUsuario = primerApellidoUsuario;
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
	 * Sets the segundoApellidoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param segundoApellidoUsuario de segundo apellido usuario
	 */
	public void setSegundoApellidoUsuario(java.lang.String segundoApellidoUsuario)
	{
		this.segundoApellidoUsuario = segundoApellidoUsuario;
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
	 * Sets the correoElectronicoCorporativo value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param correoElectronicoCorporativo de correo electronico corporativo
	 */
	public void setCorreoElectronicoCorporativo(java.lang.String correoElectronicoCorporativo)
	{
		this.correoElectronicoCorporativo = correoElectronicoCorporativo;
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
	 * Sets the correoElectronicoPersonal value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param correoElectronicoPersonal de correo electronico personal
	 */
	public void setCorreoElectronicoPersonal(java.lang.String correoElectronicoPersonal)
	{
		this.correoElectronicoPersonal = correoElectronicoPersonal;
	}

	/**
	 * Gets the tipoCambio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return tipoCambio
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioTipoCambio getTipoCambio()
	{
		return tipoCambio;
	}

	/**
	 * Sets the tipoCambio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param tipoCambio de tipo cambio
	 */
	public void setTipoCambio(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioTipoCambio tipoCambio
	)
	{
		this.tipoCambio = tipoCambio;
	}

	/**
	 * Gets the segundoFactor value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return segundoFactor
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioSegundoFactor getSegundoFactor()
	{
		return segundoFactor;
	}

	/**
	 * Sets the segundoFactor value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param segundoFactor de segundo factor
	 */
	public void setSegundoFactor(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioSegundoFactor segundoFactor
	)
	{
		this.segundoFactor = segundoFactor;
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
	 * Sets the codigosRoles value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param codigosRoles de codigos roles
	 */
	public void setCodigosRoles(java.lang.String[] codigosRoles)
	{
		this.codigosRoles = codigosRoles;
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
	 * Sets the codigosORIPs value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param codigosORIPs de codigos ORI ps
	 */
	public void setCodigosORIPs(java.lang.String[] codigosORIPs)
	{
		this.codigosORIPs = codigosORIPs;
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
	 * Sets the codigoConvenio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param codigoConvenio de codigo convenio
	 */
	public void setCodigoConvenio(java.lang.String codigoConvenio)
	{
		this.codigoConvenio = codigoConvenio;
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
	 * Sets the idEntidadExterna value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param idEntidadExterna de id entidad externa
	 */
	public void setIdEntidadExterna(java.lang.String idEntidadExterna)
	{
		this.idEntidadExterna = idEntidadExterna;
	}

	/**
	 * Gets the estadoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return estadoUsuario
	 */
	public co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioEstadoUsuario getEstadoUsuario()
	{
		return estadoUsuario;
	}

	/**
	 * Sets the estadoUsuario value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param estadoUsuario de estado usuario
	 */
	public void setEstadoUsuario(
	    co.gov.supernotariado.www.schemas.bachue.cb.gestionusuarios.registrarusuario.v1.TipoEntradaRegistrarUsuarioEstadoUsuario estadoUsuario
	)
	{
		this.estadoUsuario = estadoUsuario;
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
	 * Sets the cargo value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param cargo de cargo
	 */
	public void setCargo(java.lang.String cargo)
	{
		this.cargo = cargo;
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
	 * Sets the justificacionCambio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param justificacionCambio de justificacion cambio
	 */
	public void setJustificacionCambio(java.lang.String justificacionCambio)
	{
		this.justificacionCambio = justificacionCambio;
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
	 * Sets the fechaInicio value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param fechaInicio de fecha inicio
	 */
	public void setFechaInicio(java.util.Calendar fechaInicio)
	{
		this.fechaInicio = fechaInicio;
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
	 * Sets the fechaFin value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param fechaFin de fecha fin
	 */
	public void setFechaFin(java.util.Calendar fechaFin)
	{
		this.fechaFin = fechaFin;
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
	 * Sets the fechaVigenciaSegundaClave value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param fechaVigenciaSegundaClave de fecha vigencia segunda clave
	 */
	public void setFechaVigenciaSegundaClave(java.util.Calendar fechaVigenciaSegundaClave)
	{
		this.fechaVigenciaSegundaClave = fechaVigenciaSegundaClave;
	}

	/**
	 * Gets the idPolitica value for this TipoEntradaRegistrarUsuario.
	 *
	 * @return idPolitica
	 */
	public java.lang.String getIdPolitica()
	{
		return idPolitica;
	}

	/**
	 * Sets the idPolitica value for this TipoEntradaRegistrarUsuario.
	 *
	 * @param idPolitica de id politica
	 */
	public void setIdPolitica(java.lang.String idPolitica)
	{
		this.idPolitica = idPolitica;
	}

	/** {@inheritdoc} */
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
				&& this.fechaVigenciaSegundaClave.equals(other.getFechaVigenciaSegundaClave())))
				&& (((this.idPolitica == null) && (other.getIdPolitica() == null))
				|| ((this.idPolitica != null) && this.idPolitica.equals(other.getIdPolitica())));
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

		if(getIdPolitica() != null)
			_hashCode += getIdPolitica().hashCode();

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
