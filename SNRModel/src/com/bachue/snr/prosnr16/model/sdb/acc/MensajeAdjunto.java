package com.bachue.snr.prosnr16.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla SDB_ACC_MENSAJE_ADJUNTO.
 *
 * @author Sebastian Sanchez
 */
public class MensajeAdjunto extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -309852394318482671L;

	/** Propiedad is dID. */
	private String is_dID;

	/** Propiedad is doc name. */
	private String is_docName;

	/** Propiedad is id adjunto. */
	private String is_idAdjunto;

	/** Propiedad is id mensaje. */
	private String is_idMensaje;

	/**
	 * Constructor por defecto.
	 */
	public MensajeAdjunto()
	{
	}

	/**
	 * Instancia un nuevo objeto mensaje adjunto.
	 *
	 * @param as_dID de as d ID
	 * @param as_docName de as doc name
	 */
	public MensajeAdjunto(String as_dID, String as_docName)
	{
		is_dID         = as_dID;
		is_docName     = as_docName;
	}

	/**
	 * Modifica el valor de DId.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDId(String as_s)
	{
		is_dID = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor d id.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDId()
	{
		return is_dID;
	}

	/**
	 * Modifica el valor de DocName.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDocName(String as_s)
	{
		is_docName = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor doc name.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDocName()
	{
		return is_docName;
	}

	/**
	 * Modifica el valor de IdAdjunto.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAdjunto(String as_s)
	{
		is_idAdjunto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id adjunto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAdjunto()
	{
		return is_idAdjunto;
	}

	/**
	 * Modifica el valor de IdMensaje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMensaje(String as_s)
	{
		is_idMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMensaje()
	{
		return is_idMensaje;
	}
}
