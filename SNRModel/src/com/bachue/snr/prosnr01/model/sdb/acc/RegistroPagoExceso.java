package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades RegistroPagoExceso.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 31/07/2020
 */
public class RegistroPagoExceso extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3634880379982683712L;

	/** Propiedad ibd total saldo favor. */
	private BigDecimal ibd_totalSaldoFavor;

	/** Propiedad ibd valor total liquidado. */
	private BigDecimal ibd_valorTotalLiquidado;

	/** Propiedad id fecha cambio estado. */
	private Date id_fechaCambioEstado;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad al id item. */
	private Long al_idItem;

	/** Propiedad al id liquidacion. */
	private String al_idLiquidacion;

	/** Propiedad is concepto pago exceso. */
	private String is_conceptoPagoExceso;

	/** Propiedad is estado pago exceso. */
	private String is_estadoPagoExceso;

	/** Propiedad is id registro pago exceso. */
	private String is_idRegistroPagoExceso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad ii consecutivo. */
	private int ii_consecutivo;

	/**
	 * Modifica el valor de ConceptoPagoExceso.
	 *
	 * @param as_s de as s
	 */
	public void setConceptoPagoExceso(String as_s)
	{
		is_conceptoPagoExceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor concepto pago exceso.
	 *
	 * @return Retorna el valor de la propiedad conceptoPagoExceso
	 */
	public String getConceptoPagoExceso()
	{
		return is_conceptoPagoExceso;
	}

	/**
	 * Modifica el valor de Consecutivo.
	 *
	 * @param ai_i de ai i
	 */
	public void setConsecutivo(int ai_i)
	{
		ii_consecutivo = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo.
	 *
	 * @return Retorna el valor de la propiedad consecutivo
	 */
	public int getConsecutivo()
	{
		return ii_consecutivo;
	}

	/**
	 * Modifica el valor de EstadoPagoExceso.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoPagoExceso(String as_s)
	{
		is_estadoPagoExceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado pago exceso.
	 *
	 * @return Retorna el valor de la propiedad estadoPagoExceso
	 */
	public String getEstadoPagoExceso()
	{
		return is_estadoPagoExceso;
	}

	/**
	 * Modifica el valor de FechaCambioEstado.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaCambioEstado(Date ad_d)
	{
		id_fechaCambioEstado = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha cambio estado.
	 *
	 * @return Retorna el valor de la propiedad fechaCambioEstado
	 */
	public Date getFechaCambioEstado()
	{
		return id_fechaCambioEstado;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return Retorna el valor de la propiedad fechaRegistro
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de IdItem.
	 *
	 * @param al_l de al l
	 */
	public void setIdItem(Long al_l)
	{
		al_idItem = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id item.
	 *
	 * @return Retorna el valor de la propiedad idItem
	 */
	public Long getIdItem()
	{
		return al_idItem;
	}

	/**
	 * Modifica el valor de IdLiquidacion.
	 *
	 * @param al_l de al l
	 */
	public void setIdLiquidacion(String al_l)
	{
		al_idLiquidacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id liquidacion.
	 *
	 * @return Retorna el valor de la propiedad idLiquidacion
	 */
	public String getIdLiquidacion()
	{
		return al_idLiquidacion;
	}

	/**
	 * Modifica el valor de IdRegistroPagoExceso.
	 *
	 * @param is_s de is s
	 */
	public void setIdRegistroPagoExceso(String is_s)
	{
		is_idRegistroPagoExceso = is_s;
	}

	/**
	 * Retorna Objeto o variable de valor id registro pago exceso.
	 *
	 * @return Retorna el valor de la propiedad idRegistroPagoExceso
	 */
	public String getIdRegistroPagoExceso()
	{
		return is_idRegistroPagoExceso;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param is_s de is s
	 */
	public void setIdSolicitud(String is_s)
	{
		is_idSolicitud = is_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return Retorna el valor de la propiedad idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param is_s de is s
	 */
	public void setIdTurno(String is_s)
	{
		is_idTurno = is_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de TotalSaldoFavor.
	 *
	 * @param ibd_bd de ibd bd
	 */
	public void setTotalSaldoFavor(BigDecimal ibd_bd)
	{
		ibd_totalSaldoFavor = ibd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor total saldo favor.
	 *
	 * @return Retorna el valor de la propiedad totalSaldoFavor
	 */
	public BigDecimal getTotalSaldoFavor()
	{
		return ibd_totalSaldoFavor;
	}

	/**
	 * Modifica el valor de ValorTotalLiquidado.
	 *
	 * @param ibd_bd de ibd bd
	 */
	public void setValorTotalLiquidado(BigDecimal ibd_bd)
	{
		ibd_valorTotalLiquidado = ibd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor valor total liquidado.
	 *
	 * @return Retorna el valor de la propiedad valorTotalLiquidado
	 */
	public BigDecimal getValorTotalLiquidado()
	{
		return ibd_valorTotalLiquidado;
	}
}
