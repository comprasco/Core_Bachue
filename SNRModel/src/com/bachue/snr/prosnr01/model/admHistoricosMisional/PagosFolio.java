package com.bachue.snr.prosnr01.model.admHistoricosMisional;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades PagosFolio.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 18/06/2020
 */
public class PagosFolio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1788208753124274203L;

	/** Propiedad ibi aprobacion. */
	private BigInteger ibi_aprobacion;

	/** Propiedad ibi cus. */
	private BigInteger ibi_cus;

	/** Propiedad ibi recibo. */
	private BigInteger ibi_recibo;

	/** Propiedad id fecha. */
	private Date id_fecha;

	/** Propiedad il canal. */
	private Long il_canal;

	/** Propiedad is banco. */
	private String is_banco;

	/** Propiedad is cheque. */
	private String is_cheque;

	/** Propiedad is concepto. */
	private String is_concepto;

	/** Propiedad is descripcion canal. */
	private String is_descripcionCanal;

	/** Propiedad is descripcion concepto. */
	private String is_descripcionConcepto;

	/** Propiedad is descripcion forma pago. */
	private String is_descripcionFormaPago;

	/** Propiedad is forma. */
	private String is_forma;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is nombre banco. */
	private String is_nombreBanco;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nro radica. */
	private String is_nroRadica;

	/** Propiedad is turno actualizado. */
	private String is_turnoActualizado;

	/** Propiedad is turno actualizado asociado. */
	private String is_turnoActualizadoAsociado;

	/** Propiedad id valor. */
	private double id_valor;

	/** Propiedad id valor conservacion documental. */
	private double id_valorConservacionDocumental;

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return Retorna el valor de la propiedad fecha
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param ad_d de ad d
	 */
	public void setFecha(Date ad_d)
	{
		id_fecha = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor banco.
	 *
	 * @return Retorna el valor de la propiedad banco
	 */
	public String getBanco()
	{
		return is_banco;
	}

	/**
	 * Modifica el valor de Banco.
	 *
	 * @param as_s de as s
	 */
	public void setBanco(String as_s)
	{
		is_banco = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor concepto.
	 *
	 * @return Retorna el valor de la propiedad concepto
	 */
	public String getConcepto()
	{
		return is_concepto;
	}

	/**
	 * Modifica el valor de Concepto.
	 *
	 * @param as_s de as s
	 */
	public void setConcepto(String as_s)
	{
		is_concepto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion concepto.
	 *
	 * @return Retorna el valor de la propiedad descripcionConcepto
	 */
	public String getDescripcionConcepto()
	{
		return is_descripcionConcepto;
	}

	/**
	 * Modifica el valor de DescripcionConcepto.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionConcepto(String as_s)
	{
		is_descripcionConcepto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion canal.
	 *
	 * @return Retorna el valor de la propiedad descripcionCanal
	 */
	public String getDescripcionCanal()
	{
		return is_descripcionCanal;
	}

	/**
	 * Modifica el valor de DescripcionCanal.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionCanal(String as_s)
	{
		is_descripcionCanal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor cheque.
	 *
	 * @return Retorna el valor de la propiedad cheque
	 */
	public String getCheque()
	{
		return is_cheque;
	}

	/**
	 * Modifica el valor de Cheque.
	 *
	 * @param as_s de as s
	 */
	public void setCheque(String as_s)
	{
		is_cheque = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor forma.
	 *
	 * @return Retorna el valor de la propiedad forma
	 */
	public String getForma()
	{
		return is_forma;
	}

	/**
	 * Modifica el valor de Forma.
	 *
	 * @param as_s de as s
	 */
	public void setForma(String as_s)
	{
		is_forma = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre banco.
	 *
	 * @return Retorna el valor de la propiedad nombreBanco
	 */
	public String getNombreBanco()
	{
		return is_nombreBanco;
	}

	/**
	 * Modifica el valor de NombreBanco.
	 *
	 * @param as_s de as s
	 */
	public void setNombreBanco(String as_s)
	{
		is_nombreBanco = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nro radica.
	 *
	 * @return Retorna el valor de la propiedad nroRadica
	 */
	public String getNroRadica()
	{
		return is_nroRadica;
	}

	/**
	 * Modifica el valor de NroRadica.
	 *
	 * @param as_s de as s
	 */
	public void setNroRadica(String as_s)
	{
		is_nroRadica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno actualizado.
	 *
	 * @return Retorna el valor de la propiedad turnoActualizado
	 */
	public String getTurnoActualizado()
	{
		return is_turnoActualizado;
	}

	/**
	 * Modifica el valor de TurnoActualizado.
	 *
	 * @param as_s de as s
	 */
	public void setTurnoActualizado(String as_s)
	{
		is_turnoActualizado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor aprobacion.
	 *
	 * @return Retorna el valor de la propiedad aprobacion
	 */
	public BigInteger getAprobacion()
	{
		return ibi_aprobacion;
	}

	/**
	 * Modifica el valor de Aprobacion.
	 *
	 * @param abi_bi de abi bi
	 */
	public void setAprobacion(BigInteger abi_bi)
	{
		ibi_aprobacion = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor cus.
	 *
	 * @return Retorna el valor de la propiedad cus
	 */
	public BigInteger getCus()
	{
		return ibi_cus;
	}

	/**
	 * Modifica el valor de Cus.
	 *
	 * @param abi_bi de abi bi
	 */
	public void setCus(BigInteger abi_bi)
	{
		ibi_cus = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor turno actualizado asociado.
	 *
	 * @return Retorna el valor de la propiedad turnoActualizadoAsociado
	 */
	public String getTurnoActualizadoAsociado()
	{
		return is_turnoActualizadoAsociado;
	}

	/**
	 * Modifica el valor de TurnoActualizadoAsociado.
	 *
	 * @param as_s de as s
	 */
	public void setTurnoActualizadoAsociado(String as_s)
	{
		is_turnoActualizadoAsociado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreCirculo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor canal.
	 *
	 * @return Retorna el valor de la propiedad canal
	 */
	public Long getCanal()
	{
		return il_canal;
	}

	/**
	 * Modifica el valor de Canal.
	 *
	 * @param al_l de al l
	 */
	public void setCanal(Long al_l)
	{
		il_canal = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor recibo.
	 *
	 * @return Retorna el valor de la propiedad recibo
	 */
	public BigInteger getRecibo()
	{
		return ibi_recibo;
	}

	/**
	 * Modifica el valor de Recibo.
	 *
	 * @param abi_bi de abi bi
	 */
	public void setRecibo(BigInteger abi_bi)
	{
		ibi_recibo = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor valor conservacion documental.
	 *
	 * @return Retorna el valor de la propiedad valorConservacionDocumental
	 */
	public double getValorConservacionDocumental()
	{
		return id_valorConservacionDocumental;
	}

	/**
	 * Modifica el valor de ValorConservacionDocumental.
	 *
	 * @param ad_d de ad d
	 */
	public void setValorConservacionDocumental(double ad_d)
	{
		id_valorConservacionDocumental = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return Retorna el valor de la propiedad valor
	 */
	public double getValor()
	{
		return id_valor;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param ad_d de ad d
	 */
	public void setValor(double ad_d)
	{
		id_valor = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion forma pago.
	 *
	 * @return Retorna el valor de la propiedad descripcionFormaPago
	 */
	public String getDescripcionFormaPago()
	{
		return is_descripcionFormaPago;
	}

	/**
	 * Modifica el valor de DescripcionFormaPago.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcionFormaPago(String as_s)
	{
		is_descripcionFormaPago = as_s;
	}
}
