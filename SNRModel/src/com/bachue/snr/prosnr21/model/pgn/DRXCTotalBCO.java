package com.bachue.snr.prosnr21.model.pgn;

/**
 * Clase que representa un registro en la tabla SDB_CON_DRXC_TOTAL_BCO del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class DRXCTotalBCO extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Propiedad serialVersionUID. */
	private static final long serialVersionUID = -2917649380726631696L;

	/** Propiedad is afectacion. */
	private String is_afectacion;

	/** Propiedad is conciliado. */
	private String is_conciliado;

	/** Propiedad is id consecutivo DRXC. */
	private String is_idConsecutivoDRXC;

	/** Propiedad is id drx total bco. */
	private Double id_idDrxTotalBco;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is id periodo corte. */
	private String is_idPeriodoCorte;

	/** Propiedad is prestacion. */
	private String is_prestacion;

	/** Propiedad id total afectacion prestacion. */
	private Double id_totalAfectacionPrestacion;

	/** Propiedad id total bco monto. */
	private Double id_totalBcoMonto;

	/** Propiedad id total sin afectacion sin prestacion. */
	private Double id_totalSinAfectacionSinPrestacion;

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
	 * Modifica el valor de IdConsecutivoDRXC.
	 *
	 * @param as_idConsecutivoDRXC de as id consecutivo DRXC
	 */
	public void setIdConsecutivoDRXC(String as_idConsecutivoDRXC)
	{
		this.is_idConsecutivoDRXC = as_idConsecutivoDRXC;
	}

	/**
	 * Retorna Objeto o variable de valor id consecutivo DRXC.
	 *
	 * @return el valor de id consecutivo DRXC
	 */
	public String getIdConsecutivoDRXC()
	{
		return is_idConsecutivoDRXC;
	}

	/**
	 * Modifica el valor de IdDrxTotalBco.
	 *
	 * @param ad_idDrxTotalBco de as id drx total bco
	 */
	public void setIdDrxTotalBco(Double ad_idDrxTotalBco)
	{
		this.id_idDrxTotalBco = ad_idDrxTotalBco;
	}

	/**
	 * Retorna Objeto o variable de valor id drx total bco.
	 *
	 * @return el valor de id drx total bco
	 */
	public Double getIdDrxTotalBco()
	{
		return id_idDrxTotalBco;
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
	 * Modifica el valor de TotalAfectacionPrestacion.
	 *
	 * @param ad_totalAfectacionPrestacion de ad total afectacion prestacion
	 */
	public void setTotalAfectacionPrestacion(Double ad_totalAfectacionPrestacion)
	{
		this.id_totalAfectacionPrestacion = ad_totalAfectacionPrestacion;
	}

	/**
	 * Retorna Objeto o variable de valor total afectacion prestacion.
	 *
	 * @return el valor de total afectacion prestacion
	 */
	public Double getTotalAfectacionPrestacion()
	{
		return id_totalAfectacionPrestacion;
	}

	/**
	 * Modifica el valor de TotalBcoMonto.
	 *
	 * @param ad_totalBcoMonto de ad total bco monto
	 */
	public void setTotalBcoMonto(Double ad_totalBcoMonto)
	{
		this.id_totalBcoMonto = ad_totalBcoMonto;
	}

	/**
	 * Retorna Objeto o variable de valor total bco monto.
	 *
	 * @return el valor de total bco monto
	 */
	public Double getTotalBcoMonto()
	{
		return id_totalBcoMonto;
	}

	/**
	 * Modifica el valor de TotalSinAfectacionSinPrestacion.
	 *
	 * @param ad_totalSinAfectacionSinPrestacion de ad total sin afectacion sin prestacion
	 */
	public void setTotalSinAfectacionSinPrestacion(Double ad_totalSinAfectacionSinPrestacion)
	{
		this.id_totalSinAfectacionSinPrestacion = ad_totalSinAfectacionSinPrestacion;
	}

	/**
	 * Retorna Objeto o variable de valor total sin afectacion sin prestacion.
	 *
	 * @return el valor de total sin afectacion sin prestacion
	 */
	public Double getTotalSinAfectacionSinPrestacion()
	{
		return id_totalSinAfectacionSinPrestacion;
	}
}
