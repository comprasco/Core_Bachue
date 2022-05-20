package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades ProcesoConciliacion.
 *
 * @author  Andrés Rocha
 * Fecha de Creacion: 10/06/2020
 */
public class ProcesoConciliacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7644353558938977L;
	private HoraEjecucionProceso ihep_horaEjecucionProceso;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is codigo entidad recaudadora. */
	private String is_codigoEntidadRecaudadora;

	/** Propiedad is hora proceso. */
	private String is_horaProceso;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is id proceso conciliacion. */
	private String is_idProcesoConciliacion;

	/** Propiedad il minuto programacion. */
	private String is_nombreEntidadRecaudadora;

	/** Propiedad il hora programacion. */
	private long il_horaProgramacion;

	/** Propiedad il minuto programacion. */
	private long il_minutoProgramacion;

	/**
	 * Modifica el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param Modifica el valor de la propiedad horaEjecucionProceso por horaEjecucionProceso
	 */
	public void setHoraEjecucionProceso(HoraEjecucionProceso ahep_hep)
	{
		ihep_horaEjecucionProceso = ahep_hep;
	}

	/**
	 * @return Retorna el valor de la propiedad horaEjecucionProceso
	 */
	public HoraEjecucionProceso getHoraEjecucionProceso()
	{
		return ihep_horaEjecucionProceso;
	}

	/**
	 * @param Modifica el valor de la propiedad horaProceso por horaProceso
	 */
	public void setHoraProceso(String as_s)
	{
		is_horaProceso = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad horaProceso
	 */
	public String getHoraProceso()
	{
		return is_horaProceso;
	}

	/**
	 * Modifica el valor de la propiedad.
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setHoraProgramacion(long al_l)
	{
		il_horaProgramacion = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public long getHoraProgramacion()
	{
		return il_horaProgramacion;
	}

	/**
	 * Modifica el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdEntidadRecaudadora(String as_s)
	{
		is_idEntidadRecaudadora = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdProcesoConciliacion(String as_s)
	{
		is_idProcesoConciliacion = as_s;
	}

	/**
	 * Retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public String getIdProcesoConciliacion()
	{
		return is_idProcesoConciliacion;
	}

	/**
	 * Modifica el valor de la propiedad.
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setMinutoProgramacion(long al_l)
	{
		il_minutoProgramacion = al_l;
	}

	/**
	 * Retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public long getMinutoProgramacion()
	{
		return il_minutoProgramacion;
	}

	/**
	 * @param Modifica el valor de la propiedad nombreEntidadRecaudadora por nombreEntidadRecaudadora
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		is_nombreEntidadRecaudadora = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad nombreEntidadRecaudadora
	 */
	public String getNombreEntidadRecaudadora()
	{
		return is_nombreEntidadRecaudadora;
	}

	/**
	 * @return Retorna el valor de la propiedad is_codigoEntidadRecaudadora
	 */
	public String getCodigoEntidadRecaudadora()
	{
		return is_codigoEntidadRecaudadora;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_codigoEntidadRecaudadora
	 */
	public void setCodigoEntidadRecaudadora(String as_s)
	{
		is_codigoEntidadRecaudadora = as_s;
	}
}
