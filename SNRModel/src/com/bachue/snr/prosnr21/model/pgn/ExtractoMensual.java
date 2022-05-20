package com.bachue.snr.prosnr21.model.pgn;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_EXTRACTO_MENSUAL del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ExtractoMensual extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7107315980394522644L;

	/** Propiedad id fecha extracto siif. */
	private Date id_fechaExtractoSiif;

	/** Propiedad id fecha final. */
	private Date id_fechaFinal;

	/** Propiedad id fecha inicial. */
	private Date id_fechaInicial;

	/** Propiedad id creditos. */
	private Double id_creditos;

	/** Propiedad id debitos. */
	private Double id_debitos;

	/** Propiedad id saldo final. */
	private Double id_saldoFinal;

	/** Propiedad id saldo inicial. */
	private Double id_saldoInicial;

	/** Propiedad is id cuenta. */
	private String is_idCuenta;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is numero extracto mensual. */
	private String is_numeroExtractoMensual;

	/** Propiedad is numero extracto siif. */
	private String is_numeroExtractoSiif;

	/** Propiedad is periodo. */
	private String is_periodo;

	/**
	 * Modifica el valor de Creditos.
	 *
	 * @param ad_creditos de ad creditos
	 */
	public void setCreditos(Double ad_creditos)
	{
		this.id_creditos = ad_creditos;
	}

	/**
	 * Retorna Objeto o variable de valor creditos.
	 *
	 * @return el valor de creditos
	 */
	public Double getCreditos()
	{
		return id_creditos;
	}

	/**
	 * Modifica el valor de Debitos.
	 *
	 * @param ad_debitos de ad debitos
	 */
	public void setDebitos(Double ad_debitos)
	{
		this.id_debitos = ad_debitos;
	}

	/**
	 * Retorna Objeto o variable de valor debitos.
	 *
	 * @return el valor de debitos
	 */
	public Double getDebitos()
	{
		return id_debitos;
	}

	/**
	 * Modifica el valor de FechaExtractoSiif.
	 *
	 * @param ad_fechaExtractoSiif de ad fecha extracto siif
	 */
	public void setFechaExtractoSiif(Date ad_fechaExtractoSiif)
	{
		this.id_fechaExtractoSiif = ad_fechaExtractoSiif;
	}

	/**
	 * Retorna Objeto o variable de valor fecha extracto siif.
	 *
	 * @return el valor de fecha extracto siif
	 */
	public Date getFechaExtractoSiif()
	{
		return id_fechaExtractoSiif;
	}

	/**
	 * Modifica el valor de FechaFinal.
	 *
	 * @param ad_fechaFinal de ad fecha final
	 */
	public void setFechaFinal(Date ad_fechaFinal)
	{
		this.id_fechaFinal = ad_fechaFinal;
	}

	/**
	 * Retorna Objeto o variable de valor fecha final.
	 *
	 * @return el valor de fecha final
	 */
	public Date getFechaFinal()
	{
		return id_fechaFinal;
	}

	/**
	 * Modifica el valor de FechaInicial.
	 *
	 * @param ad_fechaInicial de ad fecha inicial
	 */
	public void setFechaInicial(Date ad_fechaInicial)
	{
		this.id_fechaInicial = ad_fechaInicial;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicial.
	 *
	 * @return el valor de fecha inicial
	 */
	public Date getFechaInicial()
	{
		return id_fechaInicial;
	}

	/**
	 * Modifica el valor de IdCuenta.
	 *
	 * @param as_idCuenta de as id cuenta
	 */
	public void setIdCuenta(String as_idCuenta)
	{
		this.is_idCuenta = as_idCuenta;
	}

	/**
	 * Retorna Objeto o variable de valor id cuenta.
	 *
	 * @return el valor de id cuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * Modifica el valor de IdEntidadRecaudadora.
	 *
	 * @param as_idEntidadRecaudadora de as id entidad recaudadora
	 */
	public void setIdEntidadRecaudadora(String as_idEntidadRecaudadora)
	{
		this.is_idEntidadRecaudadora = as_idEntidadRecaudadora;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad recaudadora.
	 *
	 * @return el valor de id entidad recaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de NumeroExtractoMensual.
	 *
	 * @param as_numeroExtractoMensual de as numero extracto mensual
	 */
	public void setNumeroExtractoMensual(String as_numeroExtractoMensual)
	{
		this.is_numeroExtractoMensual = as_numeroExtractoMensual;
	}

	/**
	 * Retorna Objeto o variable de valor numero extracto mensual.
	 *
	 * @return el valor de numero extracto mensual
	 */
	public String getNumeroExtractoMensual()
	{
		return is_numeroExtractoMensual;
	}

	/**
	 * Modifica el valor de NumeroExtractoSiif.
	 *
	 * @param as_numeroExtractoSiif de as numero extracto siif
	 */
	public void setNumeroExtractoSiif(String as_numeroExtractoSiif)
	{
		this.is_numeroExtractoSiif = as_numeroExtractoSiif;
	}

	/**
	 * Retorna Objeto o variable de valor numero extracto siif.
	 *
	 * @return el valor de numero extracto siif
	 */
	public String getNumeroExtractoSiif()
	{
		return is_numeroExtractoSiif;
	}

	/**
	 * Modifica el valor de Periodo.
	 *
	 * @param as_periodo de as periodo
	 */
	public void setPeriodo(String as_periodo)
	{
		this.is_periodo = as_periodo;
	}

	/**
	 * Retorna Objeto o variable de valor periodo.
	 *
	 * @return el valor de periodo
	 */
	public String getPeriodo()
	{
		return is_periodo;
	}

	/**
	 * Modifica el valor de SaldoFinal.
	 *
	 * @param ad_saldoFinal de ad saldo final
	 */
	public void setSaldoFinal(Double ad_saldoFinal)
	{
		this.id_saldoFinal = ad_saldoFinal;
	}

	/**
	 * Retorna Objeto o variable de valor saldo final.
	 *
	 * @return el valor de saldo final
	 */
	public Double getSaldoFinal()
	{
		return id_saldoFinal;
	}

	/**
	 * Modifica el valor de SaldoInicial.
	 *
	 * @param ad_saldoInicial de ad saldo inicial
	 */
	public void setSaldoInicial(Double ad_saldoInicial)
	{
		this.id_saldoInicial = ad_saldoInicial;
	}

	/**
	 * Retorna Objeto o variable de valor saldo inicial.
	 *
	 * @return el valor de saldo inicial
	 */
	public Double getSaldoInicial()
	{
		return id_saldoInicial;
	}
}
