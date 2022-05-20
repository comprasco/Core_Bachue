package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_PERIODO_CORTE
 * @author dbeltran
 *
 */
public class PeriodoCorte extends Auditoria implements Serializable
{
	/**
	 * Propiedad serialVersionUID
	 */
	private static final long serialVersionUID = 4833442474164548852L;

	/**Propiedad Dia Corte*/
	private Date id_diaCorte;

	/**Propiedad Dia Desde*/
	private Date id_diaDesde;

	/**Propiedad Dia Hasta*/
	private Date id_diaHasta;

	/**Propiedad Activo*/
	private String is_activo;

	/**Propiedad Fecha Str*/
	private String is_fechaStr;

	/**Propiedada id Periodo Corte*/
	private String is_idPeriodoCorte;

	/**Propiedada periodo Anio Mes*/
	private String is_periodoAnioMes;

	/**Propiedad Corte*/
	private long il_corte;

	/**Propiedad Corte desde*/
	private long il_corteDesde;

	/**Propiedad Corte hasta*/
	private long il_corteHasta;

	/**Propiedad Mes*/
	private long il_mes;

	/**Propiedad Periodo*/
	private long il_periodo;

	/**
	 * Método de asignación del valor de la propiedad activo
	 * @param as_activo de tipo String con el valor a asignar
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

/**
 * Método de obtención del valor del valor de la propiedad activo
 * @return de tipo String con el valor de la propiedad
 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Método de asignación de la propiedad corte
	 * @param al_corte con el valor a asignar
	 */
	public void setCorte(long al_corte)
	{
		il_corte = al_corte;
	}

	/**
	 * Método de obtención del valor de la propiedad corte
	 * @return de tipo long con el valor de la propiedad
	 */
	public long getCorte()
	{
		return il_corte;
	}

	/**
	 * @param Modifica el valor de la propiedad il_corteDesde por il_corteDesde
	 */
	public void setCorteDesde(long al_corteDesde)
	{
		il_corteDesde = al_corteDesde;
	}

	/**
	 * @return Retorna el valor de la propiedad il_corteDesde
	 */
	public long getCorteDesde()
	{
		return il_corteDesde;
	}

	/**
	 * @param Modifica el valor de la propiedad il_corteHasta por il_corteHasta
	 */
	public void setCorteHasta(long al_corteHasta)
	{
		il_corteHasta = al_corteHasta;
	}

	/**
	 * @return Retorna el valor de la propiedad il_corteHasta
	 */
	public long getCorteHasta()
	{
		return il_corteHasta;
	}

	/**
	 * Método de asignacion del valor de la propiedad día corte
	 * @param ad_diaCorte de tipo Date con el valor a asignar
	 */
	public void setDiaCorte(Date ad_diaCorte)
	{
		id_diaCorte = ad_diaCorte;
	}

	/**
	 * Método de obtención del valor de la propiedad dia corte
	 * @return de tipo date con el valor de la propiedad
	 */
	public Date getDiaCorte()
	{
		return id_diaCorte;
	}

	/**
	 * @param Modifica el valor de la propiedad id_diaDesde por id_diaDesde
	 */
	public void setDiaDesde(Date ad_diaDesde)
	{
		id_diaDesde = ad_diaDesde;
	}

	/**
	 * @return Retorna el valor de la propiedad id_diaDesde
	 */
	public Date getDiaDesde()
	{
		return id_diaDesde;
	}

	/**
	 * @param Modifica el valor de la propiedad id_diaHasta por id_diaHasta
	 */
	public void setDiaHasta(Date ad_diaHasta)
	{
		id_diaHasta = ad_diaHasta;
	}

	/**
	 * @return Retorna el valor de la propiedad id_diaHasta
	 */
	public Date getDiaHasta()
	{
		return id_diaHasta;
	}

	/**
	 * @param as_fechaStr Modifica el valor de la propiedad fechaStr
	 */
	public void setFechaStr(String as_fechaStr)
	{
		is_fechaStr = as_fechaStr;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaStr
	 */
	public String getFechaStr()
	{
		return is_fechaStr;
	}

	/**
	 * Método de asignacion del valor del valor de la propiedad
	 * @param as_idPeriodoCorte de tipo String con el valor a asignar
	 */
	public void setIdPeriodoCorte(String as_idPeriodoCorte)
	{
		is_idPeriodoCorte = as_idPeriodoCorte;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return is_idPeriodoCorte de tipo String con el valor de la propiedad
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * @param al_mes Modifica el valor de la propiedad mes
	 */
	public void setMes(long al_mes)
	{
		il_mes = al_mes;
	}

	/**
	 * @return Retorna el valor de la propiedad mes
	 */
	public long getMes()
	{
		return il_mes;
	}

	/**
	 * Método de asignación del valor de la propiedad periodo
	 * @param al_periodo de tipo long con el valor a asignar
	 */
	public void setPeriodo(long al_periodo)
	{
		il_periodo = al_periodo;
	}

	/**
	 * Método de obtención del valor de la propiedad
	 * @return de tipo long con el valor de la propiedad periodo
	 */
	public long getPeriodo()
	{
		return il_periodo;
	}

	/**
	 * @param as_periodoAnioMes Modifica el valor de la propiedad periodoAnioMes
	 */
	public void setPeriodoAnioMes(String as_periodoAnioMes)
	{
		is_periodoAnioMes = as_periodoAnioMes;
	}

	/**
	 * @return Retorna el valor de la propiedad periodoAnioMes
	 */
	public String getPeriodoAnioMes()
	{
		return is_periodoAnioMes;
	}
}
