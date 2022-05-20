package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Map;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_INSTANCIA_CONSULTA.
 *
 * @author ccalderon
 */
public class InstanciaConsulta extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8939554354961333706L;

	/** Propiedad ic data. */
	private Collection<Map> ic_data;

	/** Propiedad ic data columns. */
	private Collection<Map<String, Object>> icmso_dataColumns;

	/** Propiedad is valor. */
	private String is_valor;

	/** Propiedad il id campo. */
	private long il_idCampo;

	/** Propiedad il id consulta. */
	private long il_idConsulta;

	/** Propiedad il id instancia. */
	private long il_idInstancia;

	/**
	 * Constructor por defecto.
	 */
	public InstanciaConsulta()
	{
	}

	/**
	 * Modifica el valor de Data.
	 *
	 * @param data asigna el valor a la propiedad
	 */
	public void setData(Collection<Map> data)
	{
		this.ic_data = data;
	}

	/**
	 * Retorna Objeto o variable de valor data.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Map> getData()
	{
		return ic_data;
	}

	/**
	 * Modifica el valor de DataColumns.
	 *
	 * @param dataColumns asigna el valor a la propiedad
	 */
	public void setDataColumns(Collection<Map<String, Object>> dataColumns)
	{
		this.icmso_dataColumns = dataColumns;
	}

	/**
	 * Retorna Objeto o variable de valor data columns.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Map<String, Object>> getDataColumns()
	{
		return icmso_dataColumns;
	}

	/**
	 * Modifica el valor de IdCampo.
	 *
	 * @param idCampo asigna el valor a la propiedad
	 */
	public void setIdCampo(long idCampo)
	{
		this.il_idCampo = idCampo;
	}

	/**
	 * Retorna Objeto o variable de valor id campo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdCampo()
	{
		return il_idCampo;
	}

	/**
	 * Modifica el valor de IdConsulta.
	 *
	 * @param idConsulta asigna el valor a la propiedad
	 */
	public void setIdConsulta(long idConsulta)
	{
		this.il_idConsulta = idConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor id consulta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdConsulta()
	{
		return il_idConsulta;
	}

	/**
	 * Modifica el valor de IdInstancia.
	 *
	 * @param idInstancia asigna el valor a la propiedad
	 */
	public void setIdInstancia(long idInstancia)
	{
		this.il_idInstancia = idInstancia;
	}

	/**
	 * Retorna Objeto o variable de valor id instancia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdInstancia()
	{
		return il_idInstancia;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param valor asigna el valor a la propiedad
	 */
	public void setValor(String valor)
	{
		this.is_valor = valor;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValor()
	{
		return is_valor;
	}
}
