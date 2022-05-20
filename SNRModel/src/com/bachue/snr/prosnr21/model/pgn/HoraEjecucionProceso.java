package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades HoraEjecucionProceso.
 *
 * @author  Andrés Rocha
 * Fecha de Creacion: 11/06/2020
 */
public class HoraEjecucionProceso extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3102567461763279437L;
	private String is_llave;

	/** Propiedad il hora programacion. */
	private long il_horaProgramacion;

	/** Propiedad il minuto programacion. */
	private long il_minutoProgramacion;

	/**
	 * Instancia un nuevo objeto hora ejecucion proceso.
	 */
	public HoraEjecucionProceso()
	{
	}

	/**
	 * Instancia un nuevo objeto hora ejecucion proceso.
	 *
	 * @param al_horaProgramacion de al hora programacion
	 * @param al_minutoProgramacion de al minuto programacion
	 */
	public HoraEjecucionProceso(long al_horaProgramacion, long al_minutoProgramacion)
	{
		super();
		il_horaProgramacion       = al_horaProgramacion;
		il_minutoProgramacion     = al_minutoProgramacion;
	}

	/**
	 * Modifica el valor de HoraProgramacion.
	 *
	 * @param al_horaProgramacion de al hora programacion
	 */
	public void setHoraProgramacion(long al_horaProgramacion)
	{
		il_horaProgramacion = al_horaProgramacion;
	}

	/**
	 * Retorna Objeto o variable de valor hora programacion.
	 *
	 * @return Retorna el valor de la propiedad horaProgramacion
	 */
	public long getHoraProgramacion()
	{
		return il_horaProgramacion;
	}

	/**
	 * @param Modifica el valor de la propiedad llave por llave
	 */
	public void setLlave(String as_s)
	{
		is_llave = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad llave
	 */
	public String getLlave()
	{
		return is_llave;
	}

	/**
	 * Modifica el valor de MinutoProgramacion.
	 *
	 * @param al_minutoProgramacion de al minuto programacion
	 */
	public void setMinutoProgramacion(long al_minutoProgramacion)
	{
		il_minutoProgramacion = al_minutoProgramacion;
	}

	/**
	 * Retorna Objeto o variable de valor minuto programacion.
	 *
	 * @return Retorna el valor de la propiedad minutoProgramacion
	 */
	public long getMinutoProgramacion()
	{
		return il_minutoProgramacion;
	}
}
