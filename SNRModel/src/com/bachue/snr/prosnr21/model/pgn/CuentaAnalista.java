package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_CUENTA_ANALISTA
 * @author dbeltran
 *
 */
public class CuentaAnalista extends Auditoria implements Serializable
{
	/**Propiedad serialVersionUID*/
	private static final long serialVersionUID = 7655577751039068545L;

	/**Propeidad fecha saldo inicial*/
	private Date id_fechaSaldoInicial;

	/**Propeidad saldo inicial*/
	private Double id_saldoInicial;

	/**Propeidad activo*/
	private String is_activo;

	/**Propeidad correo electronico analista*/
	private String is_correoElectronicoAnalista;

	/**Propeidad id cuenta*/
	private String is_idCuenta;

	/**Propeidad id cuenta analista*/
	private String is_idCuentaAnalista;

	/**Propeidad id entidad recaudadora*/
	private String is_idEntidadRecaudadora;

	/**Propeidad id usuario*/
	private String is_idUsuario;

	/**Propeidad nombre entidad recaudadora*/
	private String is_nombreEntidadRecaudadora;

	/**Propeidad numeroCuenta*/
	private String is_numeroCuenta;

	/**
	 * @param Modifica el valor de la propiedad is_activo por as_s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_correoElectronicoAnalista por as_correoElectronicoAnalista
	 */
	public void setCorreoElectronicoAnalista(String as_correoElectronicoAnalista)
	{
		is_correoElectronicoAnalista = as_correoElectronicoAnalista;
	}

	/**
	 * @return Retorna el valor de la propiedad is_correoElectronicoAnalista
	 */
	public String getCorreoElectronicoAnalista()
	{
		return is_correoElectronicoAnalista;
	}

	/**
	 * @param Modifica el valor de la propiedad id_fechaSaldoInicial por ad_d
	 */
	public void setFechaSaldoInicial(Date ad_d)
	{
		id_fechaSaldoInicial = ad_d;
	}

	/**
	 * @return Retorna el valor de la propiedad id_fechaSaldoInicial
	 */
	public Date getFechaSaldoInicial()
	{
		return id_fechaSaldoInicial;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCuenta por as_s
	 */
	public void setIdCuenta(String as_s)
	{
		is_idCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCuentaAnalista por as_s
	 */
	public void setIdCuentaAnalista(String as_s)
	{
		is_idCuentaAnalista = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuentaAnalista
	 */
	public String getIdCuentaAnalista()
	{
		return is_idCuentaAnalista;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_idEntidadRecaudadora
	 */
	public void setIdEntidadRecaudadora(String as_s)
	{
		is_idEntidadRecaudadora = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idEntidadRecaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idUsuario por as_s
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idUsuario
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_nombreEntidadRecaudadora
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		is_nombreEntidadRecaudadora = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreEntidadRecaudadora
	 */
	public String getNombreEntidadRecaudadora()
	{
		return is_nombreEntidadRecaudadora;
	}

	/**
	 * @param Modifica el valor de la propiedad is_numeroCuenta por as_numeroCuenta
	 */
	public void setNumeroCuenta(String as_numeroCuenta)
	{
		is_numeroCuenta = as_numeroCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroCuenta
	 */
	public String getNumeroCuenta()
	{
		return is_numeroCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad ibd_saldoInicial por abd_bd
	 */
	public void setSaldoInicial(Double ad_bd)
	{
		id_saldoInicial = ad_bd;
	}

	/**
	 * @return Retorna el valor de la propiedad ibd_saldoInicial
	 */
	public Double getSaldoInicial()
	{
		return NumericUtils.isValidDouble(id_saldoInicial) ? id_saldoInicial : new Double(0);
	}
}
