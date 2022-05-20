package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TURNO_DERIVADO.
 *
 * @author Julian Vaca
 */
public class TurnoDerivado extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5799000346191528413L;

	/** Propiedad is anio hijo. */
	private String is_anioHijo;

	/** Propiedad is anio padre. */
	private String is_anioPadre;

	/** Propiedad is id circulo hijo. */
	private String is_idCirculoHijo;

	/** Propiedad is id circulo padre. */
	private String is_idCirculoPadre;

	/** Propiedad is id proceso hijo. */
	private String is_idProcesoHijo;

	/** Propiedad is id proceso padre. */
	private String is_idProcesoPadre;

	/** Propiedad is id turno hijo. */
	private String is_idTurnoHijo;

	/** Propiedad is id turno padre. */
	private String is_idTurnoPadre;

	/** Propiedad is indicador vinculado. */
	private String is_indicadorVinculado;

	/** Propiedad ib ind vinculado. */
	private boolean ib_indVinculado;

	/**
	 * Instancia un nuevo objeto turno derivado.
	 */
	public TurnoDerivado()
	{
	}

	/**
	 * Instancia un nuevo objeto turno derivado.
	 *
	 * @param as_turnoPadre de as turno padre
	 */
	public TurnoDerivado(String as_turnoPadre)
	{
		is_idTurnoPadre = as_turnoPadre;
	}

	/**
	 * Modifica el valor de AnioHijo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnioHijo(String as_s)
	{
		is_anioHijo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anio hijo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnioHijo()
	{
		return is_anioHijo;
	}

	/**
	 * Modifica el valor de AnioPadre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnioPadre(String as_s)
	{
		is_anioPadre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anio padre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnioPadre()
	{
		return is_anioPadre;
	}

	/**
	 * Modifica el valor de IdCirculoHijo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoHijo(String as_s)
	{
		is_idCirculoHijo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo hijo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoHijo()
	{
		return is_idCirculoHijo;
	}

	/**
	 * Modifica el valor de IdCirculoPadre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoPadre(String as_s)
	{
		is_idCirculoPadre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo padre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoPadre()
	{
		return is_idCirculoPadre;
	}

	/**
	 * Modifica el valor de IdProcesoHijo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProcesoHijo(String as_s)
	{
		is_idProcesoHijo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso hijo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcesoHijo()
	{
		return is_idProcesoHijo;
	}

	/**
	 * Modifica el valor de IdProcesoPadre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProcesoPadre(String as_s)
	{
		is_idProcesoPadre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso padre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProcesoPadre()
	{
		return is_idProcesoPadre;
	}

	/**
	 * Modifica el valor de IdTurnoHijo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoHijo(String as_s)
	{
		is_idTurnoHijo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno hijo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoHijo()
	{
		return is_idTurnoHijo;
	}

	/**
	 * Modifica el valor de IdTurnoPadre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoPadre(String as_s)
	{
		is_idTurnoPadre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno padre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoPadre()
	{
		return is_idTurnoPadre;
	}

	/**
	 * Modifica el valor de IndVinculado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setIndVinculado(boolean ab_b)
	{
		ib_indVinculado = ab_b;
	}

	/**
	 * Valida la propiedad ind vinculado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isIndVinculado()
	{
		return ib_indVinculado;
	}

	/**
	 * Modifica el valor de IndicadorVinculado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorVinculado(String as_s)
	{
		is_indicadorVinculado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador vinculado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorVinculado()
	{
		return is_indicadorVinculado;
	}
}
