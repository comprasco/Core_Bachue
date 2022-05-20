package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;


/**
 * Clase que representa un registro en la vista SDB_PGN_INC_AVI_ALE_MEN_NOT del módulo de conciliaciones.
 *
 * @author Gabriel Arias
 */
public class IncAviAleMenNot extends Auditoria implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2955597969827086997L;

	/** The is estado. */
	private String is_estado;

	/** The is id inc. */
	private String is_idInc;

	/** The is id plantilla. */
	private String is_idPlantilla;

	/** The is mensaje. */
	private String is_mensaje;

	/** The is observacion. */
	private String is_observacion;

	/** The is proceso. */
	private String is_proceso;

	/** The is tipo. */
	private String is_tipo;

	/**
	 * Sets the estado.
	 *
	 * @param estado Modifica el valor de la propiedad estado
	 */
	public void setEstado(String estado)
	{
		is_estado = estado;
	}

	/**
	 * Gets the estado.
	 *
	 * @return Retorna el valor de la propiedad estado
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Sets the id inc.
	 *
	 * @param as_idInc Modifica el valor de la propiedad idInc
	 */
	public void setIdInc(String as_idInc)
	{
		is_idInc = as_idInc;
	}

	/**
	 * Gets the id inc.
	 *
	 * @return Retorna el valor de la propiedad idInc
	 */
	public String getIdInc()
	{
		return is_idInc;
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

	/**
	 * Sets the mensaje.
	 *
	 * @param as_mensaje Modifica el valor de la propiedad mensaje
	 */
	public void setMensaje(String as_mensaje)
	{
		is_mensaje = as_mensaje;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return Retorna el valor de la propiedad mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Sets the observacion.
	 *
	 * @param observacion Modifica el valor de la propiedad observacion
	 */
	public void setObservacion(String observacion)
	{
		is_observacion = observacion;
	}

	/**
	 * Gets the observacion.
	 *
	 * @return Retorna el valor de la propiedad observacion
	 */
	public String getObservacion()
	{
		return is_observacion;
	}

	/**
	 * Sets the proceso.
	 *
	 * @param as_proceso Modifica el valor de la propiedad proceso
	 */
	public void setProceso(String as_proceso)
	{
		is_proceso = as_proceso;
	}

	/**
	 * Gets the proceso.
	 *
	 * @return Retorna el valor de la propiedad proceso
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param as_tipo Modifica el valor de la propiedad tipo
	 */
	public void setTipo(String as_tipo)
	{
		is_tipo = as_tipo;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return Retorna el valor de la propiedad tipo
	 */
	public String getTipo()
	{
		return is_tipo;
	}
}
