package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Logica de modelo Campos Correccion siendo una abstracción de la tabla SDB_PGN_CAMPOS_CORRECCION en la base de datos.
 *
 * @author Gabriel Arias
 */
public class CamposCorreccion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6694514268295121275L;

	/** Propiedad il id campo correccion. */
	private Long il_idCampoCorreccion;

	/** Propiedad il id causal correccion. */
	private Long il_idCausalCorreccion;

	/** Propiedad is descripcion campo. */
	private String is_descripcionCampo;

	/** Propiedad is nomnbre causal. */
	private String is_nombreCausal;

	/**
	 * Modifica el valor de DescripcionCampo.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionCampo(String as_s)
	{
		is_descripcionCampo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion campo.
	 *
	 * @return Retorna el valor de la propiedad descripcionCampo
	 */
	public String getDescripcionCampo()
	{
		return is_descripcionCampo;
	}

	/**
	 * Modifica el valor de IdCampoCorreccion.
	 *
	 * @param al_l de al l
	 */
	public void setIdCampoCorreccion(Long al_l)
	{
		il_idCampoCorreccion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id campo correccion.
	 *
	 * @return Retorna el valor de la propiedad idCampoCorreccion
	 */
	public Long getIdCampoCorreccion()
	{
		return il_idCampoCorreccion;
	}

	/**
	 * Modifica el valor de IdCausalCorreccion.
	 *
	 * @param al_l de al l
	 */
	public void setIdCausalCorreccion(Long al_l)
	{
		il_idCausalCorreccion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id causal correccion.
	 *
	 * @return Retorna el valor de la propiedad idCausalCorreccion
	 */
	public Long getIdCausalCorreccion()
	{
		return il_idCausalCorreccion;
	}

	/**
	 * Retorna Objeto o variable de valor nombre causal.
	 *
	 * @return Retorna el valor de la propiedad nombre causal
	 */
	public String getNombreCausal()
	{
		return is_nombreCausal;
	}

	/**
	 * Modifica el valor de nombre causal.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCausal(String as_s)
	{
		is_nombreCausal = as_s;
	}
}
