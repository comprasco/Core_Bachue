package com.bachue.snr.prosnr10.model.catastro;

import java.io.Serializable;



/**
 * Clase que contiene todos las propiedades IntervinienteCatastro.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/03/2020
 */
public class IntervinienteCatastro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7047552628501610856L;

	/** Propiedad is cod genero. */
	private String is_codGenero;

	/** Propiedad is descripcion genero. */
	private String is_descripcionGenero;

	/** Propiedad is id documento tipo. */
	private String is_idDocumentoTipo;

	/** Propiedad is nombre persona. */
	private String is_nombrePersona;

	/** Propiedad is num documento persona. */
	private String is_numDocumentoPersona;

	/** Propiedad is primer apellido. */
	private String is_primerApellido;

	/** Propiedad is primer nombre. */
	private String is_primerNombre;

	/** Propiedad is razon social. */
	private String is_razonSocial;

	/** Propiedad is rol interviniente. */
	private String is_rolInterviniente;

	/** Propiedad is segundo apellido. */
	private String is_segundoApellido;

	/** Propiedad is segundo nombre. */
	private String is_segundoNombre;

	/** Propiedad is tipo documento persona. */
	private String is_tipoDocumentoPersona;

	/** Propiedad is tipo parte interesada. */
	private String is_tipoParteInteresada;

	/** Propiedad is tipo persona. */
	private String is_tipoPersona;

	/**
	 * Instancia un nuevo objeto interviniente catastro.
	 */
	public IntervinienteCatastro()
	{
	}

	/**
	 * Instancia un nuevo objeto interviniente catastro.
	 *
	 * @param as_tipoPersona de as tipo persona
	 * @param as_tipoDocumentoPersona de tipo documento persona
	 * @param as_numDocumentoPersona de num documento persona
	 * @param as_primerNombre de primer nombre
	 * @param as_segundoNombre de segundo nombre
	 * @param as_primerApellido de primer apellido
	 * @param as_segundoApellido de segundo apellido
	 * @param as_razonSocial de razon social
	 * @param as_rolInterviniente de rol interviniente
	 * @param as_tipoParteInteresada de tipo parte interesada
	 * @param as_idNaturalGenero de as id natural genero
	 */
	public IntervinienteCatastro(
	    String as_tipoPersona, String as_tipoDocumentoPersona, String as_numDocumentoPersona, String as_primerNombre,
	    String as_segundoNombre, String as_primerApellido, String as_segundoApellido, String as_razonSocial,
	    String as_rolInterviniente, String as_tipoParteInteresada, String as_idNaturalGenero
	)
	{
		is_tipoPersona              = as_tipoPersona;
		is_tipoDocumentoPersona     = as_tipoDocumentoPersona;
		is_numDocumentoPersona      = as_numDocumentoPersona;
		is_primerNombre             = as_primerNombre;
		is_segundoNombre            = as_segundoNombre;
		is_primerApellido           = as_primerApellido;
		is_segundoApellido          = as_segundoApellido;
		is_razonSocial              = as_razonSocial;
		is_rolInterviniente         = as_rolInterviniente;
		is_tipoParteInteresada      = as_tipoParteInteresada;
		is_codGenero                = as_idNaturalGenero;
	}

	/**
	 * Modifica el valor de NumDocumentoPersona.
	 *
	 * @param as_s de as s
	 */
	public void setNumDocumentoPersona(String as_s)
	{
		is_numDocumentoPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor num documento persona.
	 *
	 * @return el valor de num documento persona
	 */
	public String getNumDocumentoPersona()
	{
		return is_numDocumentoPersona;
	}

	/**
	 * Modifica el valor de PrimerApellido.
	 *
	 * @param as_s de as s
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer apellido.
	 *
	 * @return el valor de primer apellido
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Modifica el valor de PrimerNombre.
	 *
	 * @param as_s de as s
	 */
	public void setPrimerNombre(String as_s)
	{
		is_primerNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor primer nombre.
	 *
	 * @return el valor de primer nombre
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Modifica el valor de RazonSocial.
	 *
	 * @param as_s de as s
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor razon social.
	 *
	 * @return el valor de razon social
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
	}

	/**
	 * Modifica el valor de RolInterviniente.
	 *
	 * @param as_s de as s
	 */
	public void setRolInterviniente(String as_s)
	{
		is_rolInterviniente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor rol interviniente.
	 *
	 * @return el valor de rol interviniente
	 */
	public String getRolInterviniente()
	{
		return is_rolInterviniente;
	}

	/**
	 * Modifica el valor de SegundoApellido.
	 *
	 * @param as_s de as s
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo apellido.
	 *
	 * @return el valor de segundo apellido
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Modifica el valor de SegundoNombre.
	 *
	 * @param as_s de as s
	 */
	public void setSegundoNombre(String as_s)
	{
		is_segundoNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor segundo nombre.
	 *
	 * @return el valor de segundo nombre
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Modifica el valor de TipoDocumentoPersona.
	 *
	 * @param as_s de as s
	 */
	public void setTipoDocumentoPersona(String as_s)
	{
		is_tipoDocumentoPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documento persona.
	 *
	 * @return el valor de tipo documento persona
	 */
	public String getTipoDocumentoPersona()
	{
		return is_tipoDocumentoPersona;
	}

	/**
	 * Modifica el valor de TipoParteInteresada.
	 *
	 * @param as_s de as s
	 */
	public void setTipoParteInteresada(String as_s)
	{
		is_tipoParteInteresada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo parte interesada.
	 *
	 * @return el valor de tipo parte interesada
	 */
	public String getTipoParteInteresada()
	{
		return is_tipoParteInteresada;
	}

	/**
	 * Modifica el valor de TipoPersona.
	 *
	 * @param as_s de as s
	 */
	public void setTipoPersona(String as_s)
	{
		is_tipoPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo persona.
	 *
	 * @return el valor de tipo persona
	 */
	public String getTipoPersona()
	{
		return is_tipoPersona;
	}

	/**
	 * Retorna Objeto o variable de valor id documento tipo.
	 *
	 * @return Retorna el valor de la propiedad idDocumentoTipo
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Modifica el valor de IdDocumentoTipo.
	 *
	 * @param as_s de as s
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		is_idDocumentoTipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre persona.
	 *
	 * @return Retorna el valor de la propiedad nombrePersona
	 */
	public String getNombrePersona()
	{
		return is_nombrePersona;
	}

	/**
	 * Modifica el valor de NombrePersona.
	 *
	 * @param as_s de as s
	 */
	public void setNombrePersona(String as_s)
	{
		is_nombrePersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion genero.
	 *
	 * @return Retorna el valor de la propiedad descripcionGenero
	 */
	public String getDescripcionGenero()
	{
		return is_descripcionGenero;
	}

	/**
	 * Modifica el valor de DescripcionGenero.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionGenero(String as_s)
	{
		is_descripcionGenero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cod genero.
	 *
	 * @return Retorna el valor de la propiedad codGenero
	 */
	public String getCodGenero()
	{
		return is_codGenero;
	}

	/**
	 * Modifica el valor de CodGenero.
	 *
	 * @param as_s de as s
	 */
	public void setCodGenero(String as_s)
	{
		is_codGenero = as_s;
	}
}
