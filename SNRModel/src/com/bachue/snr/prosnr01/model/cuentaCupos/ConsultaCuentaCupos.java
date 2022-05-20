package com.bachue.snr.prosnr01.model.cuentaCupos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;



/**
 * Clase para el manejo de información en la consulta de cuenta cupos.
 *
 * @author Manuel Blanco
 */
public class ConsultaCuentaCupos implements Serializable
{
	/** Constante Constant serialVersionUID. */
	private static final long serialVersionUID = -8483789480242580796L;

	/** Constante ibd saldo actual. */
	private BigDecimal ibd_saldoActual;

	/** Constante ibd valor consumo total. */
	private BigDecimal ibd_valorConsumoTotal;

	/** Constante ibd valor recarga total. */
	private BigDecimal ibd_valorRecargaTotal;

	/** Constante ibd valor saldo total. */
	private BigDecimal ibd_valorSaldoTotal;

	/** Constante ibd valor ultima recarga. */
	private BigDecimal ibd_valorUltimaRecarga;

	/** Constante icdccc detalle movimientos. */
	private Collection<DetalleConsultaCuentaCupos> icdccc_detalleMovimientos;

	/** Constante ildt fecha final. */
	private Date id_fechaFinal;

	/** Constante ildt fecha inicial. */
	private Date id_fechaInicial;

	/** Constante id fecha ultima recarga. */
	private Date id_fechaUltimaRecarga;

	/** Constante is id cuenta cupo. */
	private String is_idCuentaCupo;

	/** Constante is tipo archivo reporte. */
	private String is_tipoArchivoReporte;

	/**
	 * Obtiene el valor de fecha inicial.
	 *
	 * @return el valor de fecha inicial
	 */
	public Date getFechaInicial()
	{
		return id_fechaInicial;
	}

	/**
	 * Cambia el valor de fecha inicial.
	 *
	 * @param aldt_ldt el nuevo valor de fecha inicial
	 */
	public void setFechaInicial(Date aldt_ldt)
	{
		id_fechaInicial = aldt_ldt;
	}

	/**
	 * Obtiene el valor de fecha final.
	 *
	 * @return el valor de fecha final
	 */
	public Date getFechaFinal()
	{
		return id_fechaFinal;
	}

	/**
	 * Cambia el valor de fecha final.
	 *
	 * @param aldt_ldt el nuevo valor de fecha final
	 */
	public void setFechaFinal(Date aldt_ldt)
	{
		id_fechaFinal = aldt_ldt;
	}

	/**
	 * Obtiene el valor de id cuenta cupo.
	 *
	 * @return el valor de id cuenta cupo
	 */
	public String getIdCuentaCupo()
	{
		return is_idCuentaCupo;
	}

	/**
	 * Cambia el valor de id cuenta cupo.
	 *
	 * @param as_s el nuevo valor de id cuenta cupo
	 */
	public void setIdCuentaCupo(String as_s)
	{
		is_idCuentaCupo = as_s;
	}

	/**
	 * Obtiene el valor de detalle movimientos.
	 *
	 * @return el valor de detalle movimientos
	 */
	public Collection<DetalleConsultaCuentaCupos> getDetalleMovimientos()
	{
		return icdccc_detalleMovimientos;
	}

	/**
	 * Cambia el valor de detalle movimientos.
	 *
	 * @param acdccc_cdccc el nuevo valor de detalle movimientos
	 */
	public void setDetalleMovimientos(Collection<DetalleConsultaCuentaCupos> acdccc_cdccc)
	{
		icdccc_detalleMovimientos = acdccc_cdccc;
	}

	/**
	 * Obtiene el valor de valor recarga total.
	 *
	 * @return el valor de valor recarga total
	 */
	public BigDecimal getValorRecargaTotal()
	{
		return ibd_valorRecargaTotal;
	}

	/**
	 * Cambia el valor de valor recarga total.
	 *
	 * @param abd_bd el nuevo valor de valor recarga total
	 */
	public void setValorRecargaTotal(BigDecimal abd_bd)
	{
		ibd_valorRecargaTotal = abd_bd;
	}

	/**
	 * Obtiene el valor de valor consumo total.
	 *
	 * @return el valor de valor consumo total
	 */
	public BigDecimal getValorConsumoTotal()
	{
		return ibd_valorConsumoTotal;
	}

	/**
	 * Cambia el valor de valor consumo total.
	 *
	 * @param abd_bd el nuevo valor de valor consumo total
	 */
	public void setValorConsumoTotal(BigDecimal abd_bd)
	{
		ibd_valorConsumoTotal = abd_bd;
	}

	/**
	 * Obtiene el valor de valor saldo total.
	 *
	 * @return el valor de valor saldo total
	 */
	public BigDecimal getValorSaldoTotal()
	{
		return ibd_valorSaldoTotal;
	}

	/**
	 * Cambia el valor de valor saldo total.
	 *
	 * @param abd_bd el nuevo valor de valor saldo total
	 */
	public void setValorSaldoTotal(BigDecimal abd_bd)
	{
		ibd_valorSaldoTotal = abd_bd;
	}

	/**
	 * Obtiene el valor de fecha ultima recarga.
	 *
	 * @return el valor de fecha ultima recarga
	 */
	public Date getFechaUltimaRecarga()
	{
		return id_fechaUltimaRecarga;
	}

	/**
	 * Cambia el valor de fecha ultima recarga.
	 *
	 * @param ad_d el nuevo valor de fecha ultima recarga
	 */
	public void setFechaUltimaRecarga(Date ad_d)
	{
		id_fechaUltimaRecarga = ad_d;
	}

	/**
	 * Obtiene el valor de valor ultima recarga.
	 *
	 * @return el valor de valor ultima recarga
	 */
	public BigDecimal getValorUltimaRecarga()
	{
		return ibd_valorUltimaRecarga;
	}

	/**
	 * Cambia el valor de valor ultima recarga.
	 *
	 * @param abd_bd el nuevo valor de valor ultima recarga
	 */
	public void setValorUltimaRecarga(BigDecimal abd_bd)
	{
		ibd_valorUltimaRecarga = abd_bd;
	}

	/**
	 * Obtiene el valor de saldo actual.
	 *
	 * @return el valor de saldo actual
	 */
	public BigDecimal getSaldoActual()
	{
		return ibd_saldoActual;
	}

	/**
	 * Cambia el valor de saldo actual.
	 *
	 * @param abd_bd el nuevo valor de saldo actual
	 */
	public void setSaldoActual(BigDecimal abd_bd)
	{
		ibd_saldoActual = abd_bd;
	}

	/**
	 * Obtiene el valor de tipo archivo reporte.
	 *
	 * @return el valor de tipo archivo reporte
	 */
	public String getTipoArchivoReporte()
	{
		return is_tipoArchivoReporte;
	}

	/**
	 * Cambia el valor de tipo archivo reporte.
	 *
	 * @param as_s el nuevo valor de tipo archivo reporte
	 */
	public void setTipoArchivoReporte(String as_s)
	{
		is_tipoArchivoReporte = as_s;
	}
}
