package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;


/**
 * Clase que representa un registro en la vista SDB_CON_PLANTILLA_MENSAJE  del módulo de conciliaciones.
 *
 * @author Gabriel Arias
 */
public class ConPlantillaMensaje extends Auditoria implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8989836703794593866L;

	/** The activo. */
	private String is_activo;

	/** The asunto. */
	private String is_asunto;

	/** The comentario. */
	private String is_comentario;

	/** The cuerpo. */
	private String is_cuerpo;

	/** The id plantilla. */
	private String is_idPlantilla;

	/**
	 * Sets the activo.
	 *
	 * @param as_activo Modifica el valor de la propiedad activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * Gets the activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Sets the asunto.
	 *
	 * @param as_asunto Modifica el valor de la propiedad asunto
	 */
	public void setAsunto(String as_asunto)
	{
		is_asunto = as_asunto;
	}

	/**
	 * Gets the asunto.
	 *
	 * @return Retorna el valor de la propiedad asunto
	 */
	public String getAsunto()
	{
		return is_asunto;
	}

	/**
	 * Sets the comentario.
	 *
	 * @param as_comentario Modifica el valor de la propiedad comentario
	 */
	public void setComentario(String as_comentario)
	{
		is_comentario = as_comentario;
	}

	/**
	 * Gets the comentario.
	 *
	 * @return Retorna el valor de la propiedad comentario
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Sets the cuerpo.
	 *
	 * @param as_cuerpo Modifica el valor de la propiedad cuerpo
	 */
	public void setCuerpo(String as_cuerpo)
	{
		is_cuerpo = as_cuerpo;
	}

	/**
	 * Gets the cuerpo.
	 *
	 * @return Retorna el valor de la propiedad cuerpo
	 */
	public String getCuerpo()
	{
		return is_cuerpo;
	}

	/**
	 * Sets the id plantilla.
	 *
	 * @param as_idPlantilla Modifica el valor de la propiedad idPlantilla
	 */
	public void setIdPlantilla(String as_idPlantilla)
	{
		is_idPlantilla = as_idPlantilla;
	}

	/**
	 * Gets the id plantilla.
	 *
	 * @return Retorna el valor de la propiedad idPlantilla
	 */
	public String getIdPlantilla()
	{
		return is_idPlantilla;
	}
}
