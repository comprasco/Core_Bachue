package com.bachue.snr.prosnr16.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de SDB_PGN_PLANTILLA.
 *
 * @author Sebastian Sanchez
 */
public class Plantilla extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6873030380935684277L;

	/** Propiedad is asunto. */
	private String is_asunto;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is cuerpo. */
	private String is_cuerpo;

	/** Propiedad is id plantilla. */
	private String is_idPlantilla;

	/**
	 * Constructor por defecto.
	 */
	public Plantilla()
	{
	}

	/**
	 * Modifica el valor de IdPlantilla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPlantilla(String as_s)
	{
		is_idPlantilla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id plantilla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPlantilla()
	{
		return is_idPlantilla;
	}

	/**
	 * Retorna Objeto o variable de valor asunto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAsunto()
	{
		return is_asunto;
	}

	/**
	 * Modifica el valor de Asunto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAsunto(String as_s)
	{
		is_asunto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cuerpo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCuerpo()
	{
		return is_cuerpo;
	}

	/**
	 * Modifica el valor de Cuerpo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCuerpo(String as_s)
	{
		is_cuerpo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de Comentario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}
}
