package com.bachue.snr.prosnr01.model.admHistoricosMisional;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;



/**
 * Class que contiene todos las propiedades AdmPagos.
 *
 * @author garias
 */
public class AdmPagos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7119197727253756878L;

	/** Propiedad ibi aprobacion. */
	private BigInteger ibi_aprobacion;

	/** Propiedad ibi cus. */
	private BigInteger ibi_cus;

	/** Propiedad ibi recibo. */
	private BigInteger ibi_recibo;

	/** Propiedad id fecha. */
	private Date id_fecha;

	/** Propiedad id fecsis. */
	private Date id_fecsis;

	/** Propiedad il canal. */
	private Long il_canal;

	/** Propiedad is banco. */
	private String is_banco;

	/** Propiedad is cheque. */
	private String is_cheque;

	/** Propiedad is concepto. */
	private String is_concepto;

	/** Propiedad is forma. */
	private String is_forma;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is nroradica. */
	private String is_nroradica;

	/** Propiedad is turno actualizado. */
	private String is_turnoActualizado;

	/** Propiedad is usuario. */
	private String is_usuario;

	/** Propiedad id valor. */
	private double id_valor;

	/**
	 * Modifica el valor de aprobacion.
	 *
	 * @param abi_bi asigna el valor a la propiedad aprobacion
	 */
	public void setAprobacion(BigInteger abi_bi)
	{
		ibi_aprobacion = abi_bi;
	}

	/**
	 * Retorna el valor de aprobacion.
	 *
	 * @return el valor de aprobacion
	 */
	public BigInteger getAprobacion()
	{
		return ibi_aprobacion;
	}

	/**
	 * Modifica el valor de banco.
	 *
	 * @param as_s asigna el valor a la propiedad banco
	 */
	public void setBanco(String as_s)
	{
		is_banco = as_s;
	}

	/**
	 * Retorna el valor de banco.
	 *
	 * @return el valor de banco
	 */
	public String getBanco()
	{
		return is_banco;
	}

	/**
	 * Modifica el valor de canal.
	 *
	 * @param al_l asigna el valor a la propiedad canal
	 */
	public void setCanal(Long al_l)
	{
		il_canal = al_l;
	}

	/**
	 * Retorna el valor de canal.
	 *
	 * @return el valor de canal
	 */
	public Long getCanal()
	{
		return il_canal;
	}

	/**
	 * Modifica el valor de cheque.
	 *
	 * @param as_s asigna el valor a la propiedad cheque
	 */
	public void setCheque(String as_s)
	{
		this.is_cheque = as_s;
	}

	/**
	 * Retorna el valor de cheque.
	 *
	 * @return el valor de cheque
	 */
	public String getCheque()
	{
		return is_cheque;
	}

	/**
	 * Modifica el valor de concepto.
	 *
	 * @param as_s asigna el valor a la propiedad concepto
	 */
	public void setConcepto(String as_s)
	{
		is_concepto = as_s;
	}

	/**
	 * Retorna el valor de concepto.
	 *
	 * @return el valor de concepto
	 */
	public String getConcepto()
	{
		return is_concepto;
	}

	/**
	 * Modifica el valor de cus.
	 *
	 * @param abi_bi asigna el valor a la propiedad cus
	 */
	public void setCus(BigInteger abi_bi)
	{
		ibi_cus = abi_bi;
	}

	/**
	 * Retorna el valor de cus.
	 *
	 * @return el valor de cus
	 */
	public BigInteger getCus()
	{
		return ibi_cus;
	}

	/**
	 * Modifica el valor de fecha.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha
	 */
	public void setFecha(Date ad_d)
	{
		id_fecha = ad_d;
	}

	/**
	 * Retorna el valor de fecha.
	 *
	 * @return el valor de fecha
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de fecsis.
	 *
	 * @param ad_d asigna el valor a la propiedad fecsis
	 */
	public void setFecsis(Date ad_d)
	{
		id_fecsis = ad_d;
	}

	/**
	 * Retorna el valor de fecsis.
	 *
	 * @return el valor de fecsis
	 */
	public Date getFecsis()
	{
		return id_fecsis;
	}

	/**
	 * Modifica el valor de forma.
	 *
	 * @param as_s asigna el valor a la propiedad forma
	 */
	public void setForma(String as_s)
	{
		is_forma = as_s;
	}

	/**
	 * Retorna el valor de forma.
	 *
	 * @return el valor de forma
	 */
	public String getForma()
	{
		return is_forma;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de nroradica.
	 *
	 * @param as_s asigna el valor a la propiedad nroradica
	 */
	public void setNroradica(String as_s)
	{
		is_nroradica = as_s;
	}

	/**
	 * Retorna el valor de nroradica.
	 *
	 * @return el valor de nroradica
	 */
	public String getNroradica()
	{
		return is_nroradica;
	}

	/**
	 * Modifica el valor de recibo.
	 *
	 * @param abi_bi asigna el valor a la propiedad recibo
	 */
	public void setRecibo(BigInteger abi_bi)
	{
		ibi_recibo = abi_bi;
	}

	/**
	 * Retorna el valor de recibo.
	 *
	 * @return el valor de recibo
	 */
	public BigInteger getRecibo()
	{
		return ibi_recibo;
	}

	/**
	 * Modifica el valor de turno actualizado.
	 *
	 * @param as_s asigna el valor a la propiedad turno actualizado
	 */
	public void setTurnoActualizado(String as_s)
	{
		is_turnoActualizado = as_s;
	}

	/**
	 * Retorna el valor de turno actualizado.
	 *
	 * @return el valor de turno actualizado
	 */
	public String getTurnoActualizado()
	{
		return is_turnoActualizado;
	}

	/**
	 * Modifica el valor de usuario.
	 *
	 * @param as_s asigna el valor a la propiedad usuario
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * Modifica el valor de valor.
	 *
	 * @param ad_d asigna el valor a la propiedad valor
	 */
	public void setValor(double ad_d)
	{
		id_valor = ad_d;
	}

	/**
	 * Retorna el valor de valor.
	 *
	 * @return el valor de valor
	 */
	public double getValor()
	{
		return id_valor;
	}
}
