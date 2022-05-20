package com.bachue.snr.prosnr01.model.registro;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades Interviniente.
 *
 * @author hcastaneda
 */
public class Interviniente implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8366531945857244458L;

	/** Propiedad id direccion correspondencia. */
	private Direccion id_direccionCorrespondencia;

	/** Propiedad id direccion residencia. */
	private Direccion id_direccionResidencia;

	/** Propiedad id porcentaje participacion. */
	private Double id_porcentajeParticipacion;

	/** Propiedad il departamento. */
	private Long il_departamento;

	/** Propiedad il documento. */
	private Long il_documento;

	/** Propiedad il municipio. */
	private Long il_municipio;

	/** Propiedad is acto. */
	private String is_acto;

	/** Propiedad is calidad actua. */
	private String is_calidadActua;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is calidad actua. */
	private String is_documentoError;

	/** Propiedad is error interviniente masivo. */
	private String is_errorIntervinienteMasivo;

	/** Propiedad is genero. */
	private String is_genero;

	/** Propiedad is indicativo. */
	private String is_indicativo;

	/** Propiedad is medio notificar. */
	private String is_medioNotificar;

	/** Propiedad is misma direccion correspondencia. */
	private String is_mismaDireccionCorrespondencia;

	/** Propiedad is nacionalidad. */
	private String is_nacionalidad;

	/** Propiedad is primer apellido. */
	private String is_primerApellido;

	/** Propiedad is primer nombre. */
	private String is_primerNombre;

	/** Propiedad is razon social. */
	private String is_razonSocial;

	/** Propiedad is rol. */
	private String is_rol;

	/** Propiedad is segundo apellido. */
	private String is_segundoApellido;

	/** Propiedad is segundo nombre. */
	private String is_segundoNombre;

	/** Propiedad is telefono fijo. */
	private String is_telefonoFijo;

	/** Propiedad is telefono movil. */
	private String is_telefonoMovil;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/** Propiedad is tipo persona. */
	private String is_tipoPersona;

	/** Propiedad ib autoriza not email. */
	private boolean ib_autorizaNotEmail;

	/** Propiedad ib interviene acto. */
	private boolean ib_intervieneActo;

	/**
	 * Modifica el valor de acto.
	 *
	 * @param as_s asigna el valor a la propiedad acto
	 */
	public void setActo(String as_s)
	{
		is_acto = as_s;
	}

	/**
	 * Retorna el valor de acto.
	 *
	 * @return el valor de acto
	 */
	public String getActo()
	{
		return is_acto;
	}

	/**
	 * Modifica el valor de autoriza not email.
	 *
	 * @param ab_b asigna el valor a la propiedad autoriza not email
	 */
	public void setAutorizaNotEmail(boolean ab_b)
	{
		ib_autorizaNotEmail = ab_b;
	}

	/**
	 * Valida la propiedad autoriza not email.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en autoriza not email
	 */
	public boolean isAutorizaNotEmail()
	{
		return ib_autorizaNotEmail;
	}

	/**
	 * Modifica el valor de calidad actua.
	 *
	 * @param as_s asigna el valor a la propiedad calidad actua
	 */
	public void setCalidadActua(String as_s)
	{
		is_calidadActua = as_s;
	}

	/**
	 * Retorna el valor de calidad actua.
	 *
	 * @return el valor de calidad actua
	 */
	public String getCalidadActua()
	{
		return is_calidadActua;
	}

	/**
	 * Modifica el valor de correo electronico.
	 *
	 * @param as_s asigna el valor a la propiedad correo electronico
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna el valor de correo electronico.
	 *
	 * @return el valor de correo electronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param al_l asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(Long al_l)
	{
		il_departamento = al_l;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public Long getDepartamento()
	{
		return il_departamento;
	}

	/**
	 * Modifica el valor de direccion correspondencia.
	 *
	 * @param ad_d asigna el valor a la propiedad direccion correspondencia
	 */
	public void setDireccionCorrespondencia(Direccion ad_d)
	{
		id_direccionCorrespondencia = ad_d;
	}

	/**
	 * Retorna el valor de direccion correspondencia.
	 *
	 * @return el valor de direccion correspondencia
	 */
	public Direccion getDireccionCorrespondencia()
	{
		return id_direccionCorrespondencia;
	}

	/**
	 * Modifica el valor de direccion residencia.
	 *
	 * @param ad_d asigna el valor a la propiedad direccion residencia
	 */
	public void setDireccionResidencia(Direccion ad_d)
	{
		id_direccionResidencia = ad_d;
	}

	/**
	 * Retorna el valor de direccion residencia.
	 *
	 * @return el valor de direccion residencia
	 */
	public Direccion getDireccionResidencia()
	{
		return id_direccionResidencia;
	}

	/**
	 * Modifica el valor de documento.
	 *
	 * @param al_l asigna el valor a la propiedad documento
	 */
	public void setDocumento(Long al_l)
	{
		il_documento = al_l;
	}

	/**
	 * Retorna el valor de documento.
	 *
	 * @return el valor de documento
	 */
	public Long getDocumento()
	{
		return il_documento;
	}

	/**
	 * Modifica el valor de error interviniente masivo.
	 *
	 * @param as_errorIntervinienteMasivo asigna el valor a la propiedad error interviniente masivo
	 */
	public void setErrorIntervinienteMasivo(String as_errorIntervinienteMasivo)
	{
		this.is_errorIntervinienteMasivo = as_errorIntervinienteMasivo;
	}

	/**
	 * Retorna el valor de error interviniente masivo.
	 *
	 * @return el valor de error interviniente masivo
	 */
	public String getErrorIntervinienteMasivo()
	{
		return is_errorIntervinienteMasivo;
	}

	/**
	 * Modifica el valor de genero.
	 *
	 * @param as_s asigna el valor a la propiedad genero
	 */
	public void setGenero(String as_s)
	{
		is_genero = as_s;
	}

	/**
	 * Retorna el valor de genero.
	 *
	 * @return el valor de genero
	 */
	public String getGenero()
	{
		return is_genero;
	}

	/**
	 * Modifica el valor de indicativo.
	 *
	 * @param as_s asigna el valor a la propiedad indicativo
	 */
	public void setIndicativo(String as_s)
	{
		is_indicativo = as_s;
	}

	/**
	 * Retorna el valor de indicativo.
	 *
	 * @return el valor de indicativo
	 */
	public String getIndicativo()
	{
		return is_indicativo;
	}

	/**
	 * Modifica el valor de interviene acto.
	 *
	 * @param ab_b asigna el valor a la propiedad interviene acto
	 */
	public void setIntervieneActo(boolean ab_b)
	{
		ib_intervieneActo = ab_b;
	}

	/**
	 * Valida la propiedad interviene acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en interviene acto
	 */
	public boolean isIntervieneActo()
	{
		return ib_intervieneActo;
	}

	/**
	 * Modifica el valor de medio notificar.
	 *
	 * @param as_s asigna el valor a la propiedad medio notificar
	 */
	public void setMedioNotificar(String as_s)
	{
		is_medioNotificar = as_s;
	}

	/**
	 * Retorna el valor de medio notificar.
	 *
	 * @return el valor de medio notificar
	 */
	public String getMedioNotificar()
	{
		return is_medioNotificar;
	}

	/**
	 * Modifica el valor de misma direccion correspondencia.
	 *
	 * @param as_s asigna el valor a la propiedad misma direccion correspondencia
	 */
	public void setMismaDireccionCorrespondencia(String as_s)
	{
		is_mismaDireccionCorrespondencia = as_s;
	}

	/**
	 * Retorna el valor de misma direccion correspondencia.
	 *
	 * @return el valor de misma direccion correspondencia
	 */
	public String getMismaDireccionCorrespondencia()
	{
		return is_mismaDireccionCorrespondencia;
	}

	/**
	 * Modifica el valor de municipio.
	 *
	 * @param al_l asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(Long al_l)
	{
		il_municipio = al_l;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public Long getMunicipio()
	{
		return il_municipio;
	}

	/**
	 * Modifica el valor de nacionalidad.
	 *
	 * @param as_s asigna el valor a la propiedad nacionalidad
	 */
	public void setNacionalidad(String as_s)
	{
		is_nacionalidad = as_s;
	}

	/**
	 * Retorna el valor de nacionalidad.
	 *
	 * @return el valor de nacionalidad
	 */
	public String getNacionalidad()
	{
		return is_nacionalidad;
	}

	/**
	 * Modifica el valor de porcentaje participacion.
	 *
	 * @param ad_d asigna el valor a la propiedad porcentaje participacion
	 */
	public void setPorcentajeParticipacion(Double ad_d)
	{
		id_porcentajeParticipacion = ad_d;
	}

	/**
	 * Retorna el valor de porcentaje participacion.
	 *
	 * @return el valor de porcentaje participacion
	 */
	public Double getPorcentajeParticipacion()
	{
		return id_porcentajeParticipacion;
	}

	/**
	 * Modifica el valor de primer apellido.
	 *
	 * @param as_s asigna el valor a la propiedad primer apellido
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = as_s;
	}

	/**
	 * Retorna el valor de primer apellido.
	 *
	 * @return el valor de primer apellido
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Modifica el valor de primer nombre.
	 *
	 * @param as_s asigna el valor a la propiedad primer nombre
	 */
	public void setPrimerNombre(String as_s)
	{
		is_primerNombre = as_s;
	}

	/**
	 * Retorna el valor de primer nombre.
	 *
	 * @return el valor de primer nombre
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Modifica el valor de razon social.
	 *
	 * @param as_s asigna el valor a la propiedad razon social
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = as_s;
	}

	/**
	 * Retorna el valor de razon social.
	 *
	 * @return el valor de razon social
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Modifica el valor de rol.
	 *
	 * @param as_s asigna el valor a la propiedad rol
	 */
	public void setRol(String as_s)
	{
		is_rol = as_s;
	}

	/**
	 * Retorna el valor de rol.
	 *
	 * @return el valor de rol
	 */
	public String getRol()
	{
		return is_rol;
	}

	/**
	 * Modifica el valor de segundo apellido.
	 *
	 * @param as_s asigna el valor a la propiedad segundo apellido
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = as_s;
	}

	/**
	 * Retorna el valor de segundo apellido.
	 *
	 * @return el valor de segundo apellido
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Modifica el valor de segundo nombre.
	 *
	 * @param as_s asigna el valor a la propiedad segundo nombre
	 */
	public void setSegundoNombre(String as_s)
	{
		is_segundoNombre = as_s;
	}

	/**
	 * Retorna el valor de segundo nombre.
	 *
	 * @return el valor de segundo nombre
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Modifica el valor de telefono fijo.
	 *
	 * @param as_s asigna el valor a la propiedad telefono fijo
	 */
	public void setTelefonoFijo(String as_s)
	{
		is_telefonoFijo = as_s;
	}

	/**
	 * Retorna el valor de telefono fijo.
	 *
	 * @return el valor de telefono fijo
	 */
	public String getTelefonoFijo()
	{
		return is_telefonoFijo;
	}

	/**
	 * Modifica el valor de telefono movil.
	 *
	 * @param as_s asigna el valor a la propiedad telefono movil
	 */
	public void setTelefonoMovil(String as_s)
	{
		is_telefonoMovil = as_s;
	}

	/**
	 * Retorna el valor de telefono movil.
	 *
	 * @return el valor de telefono movil
	 */
	public String getTelefonoMovil()
	{
		return is_telefonoMovil;
	}

	/**
	 * Modifica el valor de tipo documento.
	 *
	 * @param as_s asigna el valor a la propiedad tipo documento
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna el valor de tipo documento.
	 *
	 * @return el valor de tipo documento
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}

	/**
	 * Modifica el valor de tipo persona.
	 *
	 * @param as_s asigna el valor a la propiedad tipo persona
	 */
	public void setTipoPersona(String as_s)
	{
		is_tipoPersona = as_s;
	}

	/**
	 * Retorna el valor de tipo persona.
	 *
	 * @return el valor de tipo persona
	 */
	public String getTipoPersona()
	{
		return is_tipoPersona;
	}

	/**
	 * Retorna el valor de documento error.
	 *
	 * @return el valor de tipo persona
	 */
	public String getDocumentoError()
	{
		return is_documentoError;
	}

	/**
	 * Modifica el valor de documento errora.
	 *
	 * @param as_s asigna el valor a la propiedad documento error
	 */
	public void setDocumentoError(String as_s)
	{
		is_documentoError = as_s;
	}
}
