package com.bachue.snr.prosnr21.model.pgn;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_DRXC_TOTAL_CTA del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class DRXCTotalCTA extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Propiedad serialVersionUID. */
	private static final long serialVersionUID = -7770029162383446758L;

	/** Propiedad id fecha SIIF. */
	private Date id_fechaSIIF;

	/** Propiedad is afectacion. */
	private String is_afectacion;

	/** Propiedad is conciliado. */
	private String is_conciliado;

	/** Propiedad is consecutivo drxc. */
	private String is_consecutivoDrxc;

	/** Propiedad is id cuenta. */
	private String is_idCuenta;

	/** Propiedad is id drxc total bco. */
	private String is_idDrxcTotalBco;

	/** Propiedad is id drxc total cta. */
	private String is_idDrxcTotalCta;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is id periodo corte. */
	private String is_idPeriodoCorte;

	/** Propiedad is numero SIIF. */
	private String is_numeroSIIF;

	/** Propiedad is prestacion. */
	private String is_prestacion;

	/** Propiedad id monto. */
	private Double id_monto;

	/** Propiedad id monto con afectacion prestacion. */
	private Double id_montoConAfectacionPrestacion;

	/** Propiedad id monto sin afectacion sin prestacion. */
	private Double id_montoSinAfectacionSinPrestacion;

	/** Propiedad id total monto. */
	private Double id_totalMonto;

	/**
	 * Modifica el valor de Afectacion.
	 *
	 * @param as_afectacion de as afectacion
	 */
	public void setAfectacion(String as_afectacion)
	{
		this.is_afectacion = as_afectacion;
	}

	/**
	 * Retorna Objeto o variable de valor afectacion.
	 *
	 * @return el valor de afectacion
	 */
	public String getAfectacion()
	{
		return is_afectacion;
	}

	/**
	 * Modifica el valor de Conciliado.
	 *
	 * @param as_conciliado de as conciliado
	 */
	public void setConciliado(String as_conciliado)
	{
		this.is_conciliado = as_conciliado;
	}

	/**
	 * Retorna Objeto o variable de valor conciliado.
	 *
	 * @return el valor de conciliado
	 */
	public String getConciliado()
	{
		return is_conciliado;
	}

	/**
	 * Modifica el valor de ConsecutivoDrxc.
	 *
	 * @param as_consecutivoDrxc de as consecutivo drxc
	 */
	public void setConsecutivoDrxc(String as_consecutivoDrxc)
	{
		this.is_consecutivoDrxc = as_consecutivoDrxc;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo drxc.
	 *
	 * @return el valor de consecutivo drxc
	 */
	public String getConsecutivoDrxc()
	{
		return is_consecutivoDrxc;
	}

	/**
	 * Modifica el valor de FechaSIIF.
	 *
	 * @param ad_fechaSIIF de ad fecha SIIF
	 */
	public void setFechaSIIF(Date ad_fechaSIIF)
	{
		this.id_fechaSIIF = ad_fechaSIIF;
	}

	/**
	 * Retorna Objeto o variable de valor fecha SIIF.
	 *
	 * @return el valor de fecha SIIF
	 */
	public Date getFechaSIIF()
	{
		return id_fechaSIIF;
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
	 * Modifica el valor de IdDrxcTotalBco.
	 *
	 * @param as_idDrxcTotalBco de as id drxc total bco
	 */
	public void setIdDrxcTotalBco(String as_idDrxcTotalBco)
	{
		this.is_idDrxcTotalBco = as_idDrxcTotalBco;
	}

	/**
	 * Retorna Objeto o variable de valor id drxc total bco.
	 *
	 * @return el valor de id drxc total bco
	 */
	public String getIdDrxcTotalBco()
	{
		return is_idDrxcTotalBco;
	}

	/**
	 * Modifica el valor de IdDrxcTotalCta.
	 *
	 * @param as_idDrxcTotalCta de as id drxc total cta
	 */
	public void setIdDrxcTotalCta(String as_idDrxcTotalCta)
	{
		this.is_idDrxcTotalCta = as_idDrxcTotalCta;
	}

	/**
	 * Retorna Objeto o variable de valor id drxc total cta.
	 *
	 * @return el valor de id drxc total cta
	 */
	public String getIdDrxcTotalCta()
	{
		return is_idDrxcTotalCta;
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
	 * Modifica el valor de IdPeriodoCorte.
	 *
	 * @param as_idPeriodoCorte de as id periodo corte
	 */
	public void setIdPeriodoCorte(String as_idPeriodoCorte)
	{
		this.is_idPeriodoCorte = as_idPeriodoCorte;
	}

	/**
	 * Retorna Objeto o variable de valor id periodo corte.
	 *
	 * @return el valor de id periodo corte
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * Modifica el valor de Monto.
	 *
	 * @param ad_monto de ad monto
	 */
	public void setMonto(Double ad_monto)
	{
		this.id_monto = ad_monto;
	}

	/**
	 * Retorna Objeto o variable de valor monto.
	 *
	 * @return el valor de monto
	 */
	public Double getMonto()
	{
		return id_monto;
	}

	/**
	 * Modifica el valor de MontoConAfectacionPrestacion.
	 *
	 * @param ad_montoConAfectacionPrestacion de ad monto con afectacion prestacion
	 */
	public void setMontoConAfectacionPrestacion(Double ad_montoConAfectacionPrestacion)
	{
		this.id_montoConAfectacionPrestacion = ad_montoConAfectacionPrestacion;
	}

	/**
	 * Retorna Objeto o variable de valor monto con afectacion prestacion.
	 *
	 * @return el valor de monto con afectacion prestacion
	 */
	public Double getMontoConAfectacionPrestacion()
	{
		return id_montoConAfectacionPrestacion;
	}

	/**
	 * Modifica el valor de MontoSinAfectacionSinPrestacion.
	 *
	 * @param ad_montoSinAfectacionSinPrestacion de ad monto sin afectacion sin prestacion
	 */
	public void setMontoSinAfectacionSinPrestacion(Double ad_montoSinAfectacionSinPrestacion)
	{
		this.id_montoSinAfectacionSinPrestacion = ad_montoSinAfectacionSinPrestacion;
	}

	/**
	 * Retorna Objeto o variable de valor monto sin afectacion sin prestacion.
	 *
	 * @return el valor de monto sin afectacion sin prestacion
	 */
	public Double getMontoSinAfectacionSinPrestacion()
	{
		return id_montoSinAfectacionSinPrestacion;
	}

	/**
	 * Modifica el valor de NumeroSIIF.
	 *
	 * @param as_numeroSIIF de as numero SIIF
	 */
	public void setNumeroSIIF(String as_numeroSIIF)
	{
		this.is_numeroSIIF = as_numeroSIIF;
	}

	/**
	 * Retorna Objeto o variable de valor numero SIIF.
	 *
	 * @return el valor de numero SIIF
	 */
	public String getNumeroSIIF()
	{
		return is_numeroSIIF;
	}

	/**
	 * Modifica el valor de Prestacion.
	 *
	 * @param as_prestacion de as prestacion
	 */
	public void setPrestacion(String as_prestacion)
	{
		this.is_prestacion = as_prestacion;
	}

	/**
	 * Retorna Objeto o variable de valor prestacion.
	 *
	 * @return el valor de prestacion
	 */
	public String getPrestacion()
	{
		return is_prestacion;
	}

	/**
	 * Modifica el valor de TotalMonto.
	 *
	 * @param ad_totalMonto de ad total monto
	 */
	public void setTotalMonto(Double ad_totalMonto)
	{
		this.id_totalMonto = ad_totalMonto;
	}

	/**
	 * Retorna Objeto o variable de valor total monto.
	 *
	 * @return el valor de total monto
	 */
	public Double getTotalMonto()
	{
		return id_totalMonto;
	}
}
