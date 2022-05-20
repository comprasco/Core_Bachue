package com.bachue.snr.prosnr01.model.consulta.solicitud;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades Persona.
 *
 * @author Julian Vaca
 */
public class Persona extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1814796030270411407L;

	/** Propiedad is id correo. */
	private String is_idCorreo;

	/** Propiedad is id correo comunicacion. */
	private String is_idCorreoComunicacion;

	/** Propiedad is id direccion. */
	private String is_idDireccion;

	/** Propiedad is id direccion comunicacion. */
	private String is_idDireccionComunicacion;

	/** Propiedad is id documento tipo. */
	private String is_idDocumentoTipo;

	/** Propiedad is id natural genero. */
	private String is_idNaturalGenero;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is id persona. */
	private String is_idPersona;

	/** Propiedad is id tipo persona. */
	private String is_idTipoPersona;

	/** Propiedad is medio comunicacion. */
	private String is_medioComunicacion;

	/** Propiedad is medio notificacion. */
	private String is_medioNotificacion;

	/** Propiedad is numero documento. */
	private String is_numeroDocumento;

	/** Propiedad is primer apellido. */
	private String is_primerApellido;

	/** Propiedad is primer nombre. */
	private String is_primerNombre;

	/** Propiedad is razon social. */
	private String is_razonSocial;

	/** Propiedad is segundo apellido. */
	private String is_segundoApellido;

	/** Propiedad is segundo nombre. */
	private String is_segundoNombre;

	/** Propiedad is tipo doc identidad. */
	private String is_tipoDocIdentidad;

	/** Propiedad is tipo medio comunicacion. */
	private String is_tipoMedioComunicacion;

	/** Propiedad is tipo medio notificacion. */
	private String is_tipoMedioNotificacion;

	/** Propiedad ib documento valid. */
	private boolean ib_documentoValid;

	/** Propiedad ib primer apellido valid. */
	private boolean ib_primerApellidoValid;

	/** Propiedad ib primer nombre valid. */
	private boolean ib_primerNombreValid;

	/** Propiedad ib razon social valid. */
	private boolean ib_razonSocialValid;

	/** Propiedad ib segundo apellido valid. */
	private boolean ib_segundoApellidoValid;

	/** Propiedad ib segundo nombre valid. */
	private boolean ib_segundoNombreValid;

	/**
	 * Retorna Objeto o variable de valor id correo comunicacion.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public String getIdCorreoComunicacion()
	{
		return is_idCorreoComunicacion;
	}

	/**
	 * Modifica el valor de IdCorreoComunicacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdCorreoComunicacion(String as_s)
	{
		is_idCorreoComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion comunicacion.
	 *
	 * @return Retorna el valor de la propiedad
	 */
	public String getIdDireccionComunicacion()
	{
		return is_idDireccionComunicacion;
	}

	/**
	 * Modifica el valor de IdDireccionComunicacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdDireccionComunicacion(String as_s)
	{
		is_idDireccionComunicacion = as_s;
	}

	/**
	 * Modifica el valor de documento valid.
	 *
	 * @param ab_b asigna el valor a la propiedad documento valid
	 */
	public void setDocumentoValid(boolean ab_b)
	{
		ib_documentoValid = ab_b;
	}

	/**
	 * Valida la propiedad documento valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en documento valid
	 */
	public boolean isDocumentoValid()
	{
		return ib_documentoValid;
	}

	/**
	 * Modifica el valor de id correo.
	 *
	 * @param as_s asigna el valor a la propiedad id correo
	 */
	public void setIdCorreo(String as_s)
	{
		is_idCorreo = as_s;
	}

	/**
	 * Retorna el valor de id correo.
	 *
	 * @return el valor de id correo
	 */
	public String getIdCorreo()
	{
		return is_idCorreo;
	}

	/**
	 * Modifica el valor de id direccion.
	 *
	 * @param as_s asigna el valor a la propiedad id direccion
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	 * Retorna el valor de id direccion.
	 *
	 * @return el valor de id direccion
	 */
	public String getIdDireccion()
	{
		return is_idDireccion;
	}

	/**
	 * Modifica el valor de id documento tipo.
	 *
	 * @param as_s asigna el valor a la propiedad id documento tipo
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna el valor de id documento tipo.
	 *
	 * @return el valor de id documento tipo
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de id natural genero.
	 *
	 * @param as_s asigna el valor a la propiedad id natural genero
	 */
	public void setIdNaturalGenero(String as_s)
	{
		is_idNaturalGenero = as_s;
	}

	/**
	 * Retorna el valor de id natural genero.
	 *
	 * @return el valor de id natural genero
	 */
	public String getIdNaturalGenero()
	{
		return is_idNaturalGenero;
	}

	/**
	 * Modifica el valor de id pais.
	 *
	 * @param as_s asigna el valor a la propiedad id pais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna el valor de id pais.
	 *
	 * @return el valor de id pais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de id persona.
	 *
	 * @param as_s asigna el valor a la propiedad id persona
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna el valor de id persona.
	 *
	 * @return el valor de id persona
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de id tipo persona.
	 *
	 * @param as_s asigna el valor a la propiedad id tipo persona
	 */
	public void setIdTipoPersona(String as_s)
	{
		is_idTipoPersona = as_s;
	}

	/**
	 * Retorna el valor de id tipo persona.
	 *
	 * @return el valor de id tipo persona
	 */
	public String getIdTipoPersona()
	{
		return is_idTipoPersona;
	}

	/**
	 * Modifica el valor de medio comunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad medio comunicacion
	 */
	public void setMedioComunicacion(String as_s)
	{
		is_medioComunicacion = as_s;
	}

	/**
	 * Retorna el valor de medio comunicacion.
	 *
	 * @return el valor de medio comunicacion
	 */
	public String getMedioComunicacion()
	{
		return is_medioComunicacion;
	}

	/**
	 * Modifica el valor de medio notificacion.
	 *
	 * @param as_s asigna el valor a la propiedad medio notificacion
	 */
	public void setMedioNotificacion(String as_s)
	{
		is_medioNotificacion = as_s;
	}

	/**
	 * Retorna el valor de medio notificacion.
	 *
	 * @return el valor de medio notificacion
	 */
	public String getMedioNotificacion()
	{
		return is_medioNotificacion;
	}

	/**
	 * Modifica el valor de numero documento.
	 *
	 * @param as_s asigna el valor a la propiedad numero documento
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna el valor de numero documento.
	 *
	 * @return el valor de numero documento
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
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
	 * Modifica el valor de primer apellido valid.
	 *
	 * @param ab_b asigna el valor a la propiedad primer apellido valid
	 */
	public void setPrimerApellidoValid(boolean ab_b)
	{
		ib_primerApellidoValid = ab_b;
	}

	/**
	 * Valida la propiedad primer apellido valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en primer apellido valid
	 */
	public boolean isPrimerApellidoValid()
	{
		return ib_primerApellidoValid;
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
	 * Modifica el valor de primer nombre valid.
	 *
	 * @param ab_b asigna el valor a la propiedad primer nombre valid
	 */
	public void setPrimerNombreValid(boolean ab_b)
	{
		ib_primerNombreValid = ab_b;
	}

	/**
	 * Valida la propiedad primer nombre valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en primer nombre valid
	 */
	public boolean isPrimerNombreValid()
	{
		return ib_primerNombreValid;
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
	 * Modifica el valor de razon social valid.
	 *
	 * @param ab_b asigna el valor a la propiedad razon social valid
	 */
	public void setRazonSocialValid(boolean ab_b)
	{
		ib_razonSocialValid = ab_b;
	}

	/**
	 * Valida la propiedad razon social valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en razon social valid
	 */
	public boolean isRazonSocialValid()
	{
		return ib_razonSocialValid;
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
	 * Modifica el valor de segundo apellido valid.
	 *
	 * @param ab_b asigna el valor a la propiedad segundo apellido valid
	 */
	public void setSegundoApellidoValid(boolean ab_b)
	{
		ib_segundoApellidoValid = ab_b;
	}

	/**
	 * Valida la propiedad segundo apellido valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en segundo apellido valid
	 */
	public boolean isSegundoApellidoValid()
	{
		return ib_segundoApellidoValid;
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
	 * Modifica el valor de segundo nombre valid.
	 *
	 * @param ab_b asigna el valor a la propiedad segundo nombre valid
	 */
	public void setSegundoNombreValid(boolean ab_b)
	{
		ib_segundoNombreValid = ab_b;
	}

	/**
	 * Valida la propiedad segundo nombre valid.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en segundo nombre valid
	 */
	public boolean isSegundoNombreValid()
	{
		return ib_segundoNombreValid;
	}

	/**
	 * Modifica el valor de tipo doc identidad.
	 *
	 * @param as_s asigna el valor a la propiedad tipo doc identidad
	 */
	public void setTipoDocIdentidad(String as_s)
	{
		is_tipoDocIdentidad = as_s;
	}

	/**
	 * Retorna el valor de tipo doc identidad.
	 *
	 * @return el valor de tipo doc identidad
	 */
	public String getTipoDocIdentidad()
	{
		return is_tipoDocIdentidad;
	}

	/**
	 * Modifica el valor de tipo medio comunicacion.
	 *
	 * @param as_s asigna el valor a la propiedad tipo medio comunicacion
	 */
	public void setTipoMedioComunicacion(String as_s)
	{
		is_tipoMedioComunicacion = as_s;
	}

	/**
	 * Retorna el valor de tipo medio comunicacion.
	 *
	 * @return el valor de tipo medio comunicacion
	 */
	public String getTipoMedioComunicacion()
	{
		return is_tipoMedioComunicacion;
	}

	/**
	 * Modifica el valor de tipo medio notificacion.
	 *
	 * @param as_s asigna el valor a la propiedad tipo medio notificacion
	 */
	public void setTipoMedioNotificacion(String as_s)
	{
		is_tipoMedioNotificacion = as_s;
	}

	/**
	 * Retorna el valor de tipo medio notificacion.
	 *
	 * @return el valor de tipo medio notificacion
	 */
	public String getTipoMedioNotificacion()
	{
		return is_tipoMedioNotificacion;
	}
}
