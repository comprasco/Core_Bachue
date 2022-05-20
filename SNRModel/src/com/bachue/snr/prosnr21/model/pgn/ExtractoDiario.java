package com.bachue.snr.prosnr21.model.pgn;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_EXTRACTO_DIARIO del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ExtractoDiario extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3540623951859615061L;

	/** Propiedad id fecha extracto SIIF. */
	private Date id_fechaExtractoSIIF;

	/** Propiedad id fecha movimiento. */
	private Date id_fechaMovimiento;

	/** Propiedad id ingresos con afec con prest. */
	private Double id_ingresosConAfecConPrest;

	/** Propiedad id ingresos sin afec sin prest. */
	private Double id_ingresosSinAfecSinPrest;

	/** Propiedad id saldo final. */
	private Double id_saldoFinal;

	/** Propiedad id saldoinicial. */
	private Double id_saldoinicial;

	/** Propiedad id total credito. */
	private Double id_totalCredito;

	/** Propiedad id total debito. */
	private Double id_totalDebito;

	/** Propiedad id total transacciones. */
	private Double id_totalTransacciones;

	/** Propiedad is id cuenta. */
	private String is_idCuenta;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is num extracto diario. */
	private String is_numExtractoDiario;

	/** Propiedad is num extracto SIIF. */
	private String is_numExtractoSIIF;

	/**
	 * Modifica el valor de FechaExtractoSIIF.
	 *
	 * @param ad_fechaExtractoSIIF de ad fecha extracto SIIF
	 */
	public void setFechaExtractoSIIF(Date ad_fechaExtractoSIIF)
	{
		this.id_fechaExtractoSIIF = ad_fechaExtractoSIIF;
	}

	/**
	 * Retorna Objeto o variable de valor fecha extracto SIIF.
	 *
	 * @return el valor de fecha extracto SIIF
	 */
	public Date getFechaExtractoSIIF()
	{
		return id_fechaExtractoSIIF;
	}

	/**
	 * Modifica el valor de FechaMovimiento.
	 *
	 * @param ad_fechaMovimiento de ad fecha movimiento
	 */
	public void setFechaMovimiento(Date ad_fechaMovimiento)
	{
		this.id_fechaMovimiento = ad_fechaMovimiento;
	}

	/**
	 * Retorna Objeto o variable de valor fecha movimiento.
	 *
	 * @return el valor de fecha movimiento
	 */
	public Date getFechaMovimiento()
	{
		return id_fechaMovimiento;
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
	 * Modifica el valor de IngresosConAfecConPrest.
	 *
	 * @param ad_ingresosConAfecConPrest de ad ingresos con afec con prest
	 */
	public void setIngresosConAfecConPrest(Double ad_ingresosConAfecConPrest)
	{
		this.id_ingresosConAfecConPrest = ad_ingresosConAfecConPrest;
	}

	/**
	 * Retorna Objeto o variable de valor ingresos con afec con prest.
	 *
	 * @return el valor de ingresos con afec con prest
	 */
	public Double getIngresosConAfecConPrest()
	{
		return id_ingresosConAfecConPrest;
	}

	/**
	 * Modifica el valor de IngresosSinAfecSinPrest.
	 *
	 * @param ad_ingresosSinAfecSinPrest de ad ingresos sin afec sin prest
	 */
	public void setIngresosSinAfecSinPrest(Double ad_ingresosSinAfecSinPrest)
	{
		this.id_ingresosSinAfecSinPrest = ad_ingresosSinAfecSinPrest;
	}

	/**
	 * Retorna Objeto o variable de valor ingresos sin afec sin prest.
	 *
	 * @return el valor de ingresos sin afec sin prest
	 */
	public Double getIngresosSinAfecSinPrest()
	{
		return id_ingresosSinAfecSinPrest;
	}

	/**
	 * Modifica el valor de NumExtractoDiario.
	 *
	 * @param as_numExtractoDiario de as num extracto diario
	 */
	public void setNumExtractoDiario(String as_numExtractoDiario)
	{
		this.is_numExtractoDiario = as_numExtractoDiario;
	}

	/**
	 * Retorna Objeto o variable de valor num extracto diario.
	 *
	 * @return el valor de num extracto diario
	 */
	public String getNumExtractoDiario()
	{
		return is_numExtractoDiario;
	}

	/**
	 * Modifica el valor de NumExtractoSIIF.
	 *
	 * @param as_numExtractoSIIF de as num extracto SIIF
	 */
	public void setNumExtractoSIIF(String as_numExtractoSIIF)
	{
		this.is_numExtractoSIIF = as_numExtractoSIIF;
	}

	/**
	 * Retorna Objeto o variable de valor num extracto SIIF.
	 *
	 * @return el valor de num extracto SIIF
	 */
	public String getNumExtractoSIIF()
	{
		return is_numExtractoSIIF;
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
	 * Modifica el valor de Saldoinicial.
	 *
	 * @param ad_saldoinicial de ad saldoinicial
	 */
	public void setSaldoinicial(Double ad_saldoinicial)
	{
		this.id_saldoinicial = ad_saldoinicial;
	}

	/**
	 * Retorna Objeto o variable de valor saldoinicial.
	 *
	 * @return el valor de saldoinicial
	 */
	public Double getSaldoinicial()
	{
		return id_saldoinicial;
	}

	/**
	 * Modifica el valor de TotalCredito.
	 *
	 * @param ad_totalCredito de ad total credito
	 */
	public void setTotalCredito(Double ad_totalCredito)
	{
		this.id_totalCredito = ad_totalCredito;
	}

	/**
	 * Retorna Objeto o variable de valor total credito.
	 *
	 * @return el valor de total credito
	 */
	public Double getTotalCredito()
	{
		return id_totalCredito;
	}

	/**
	 * Modifica el valor de TotalDebito.
	 *
	 * @param ad_totalDebito de ad total debito
	 */
	public void setTotalDebito(Double ad_totalDebito)
	{
		this.id_totalDebito = ad_totalDebito;
	}

	/**
	 * Retorna Objeto o variable de valor total debito.
	 *
	 * @return el valor de total debito
	 */
	public Double getTotalDebito()
	{
		return id_totalDebito;
	}

	/**
	 * Modifica el valor de TotalTransacciones.
	 *
	 * @param ad_totalTransacciones de ad total transacciones
	 */
	public void setTotalTransacciones(Double ad_totalTransacciones)
	{
		this.id_totalTransacciones = ad_totalTransacciones;
	}

	/**
	 * Retorna Objeto o variable de valor total transacciones.
	 *
	 * @return el valor de total transacciones
	 */
	public Double getTotalTransacciones()
	{
		return id_totalTransacciones;
	}
}
