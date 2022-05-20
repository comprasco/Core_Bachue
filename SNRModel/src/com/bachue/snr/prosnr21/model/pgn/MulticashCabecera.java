package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_MULTICASH_CABECERA del módulo de conciliaciones.
 *
 * @author Edgar Prieto
 */
public class MulticashCabecera extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Propiedad  serialVersionUID. */
	private static final long serialVersionUID = -519771052527005919L;

	/** The fecha origen movimiento. */
	private Date id_fechaOrigenMovimiento;

	/**  Saldo final del corte. */
	private Double id_saldoFinal;

	/**  Saldo inicial del corte. */
	private Double id_saldoInicial;

	/**  Valor total de los creditos. */
	private Double id_totalCreditos;

	/**  Valor total de los debitos. */
	private Double id_totalDebitos;

	/**  Cantidad movimientos en el corte. */
	private Integer id_cantidadMovimientos;

	/** The clave banco. */
	private String is_claveBanco;

	/**  Moneda de los movimentos reflejados en el arhivo detalle. */
	private String is_claveMoneda;

	/** The cuenta bancaria. */
	private String is_cuentaBancaria;

	/**  Estado de cargue del archivo. */
	private String is_estado;

	/** The fila. */
	private String is_fila;

	/**  Id del archivo asociado al registro. */
	private String is_idArchivo;

	/**  Id de la cuenta asociada al registro. */
	private String is_idCuenta;

	/**  Id de perido de corte asociado al registro. */
	private String is_idPeriodoCorte;

	/** The is tipo cuenta. */
	private String is_tipoCuenta;

	/**
	 * Asigna la cantidad movimientos en el corte.
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setCantidadMovimientos(Integer ai_i)
	{
		id_cantidadMovimientos = ai_i;
	}

	/**
	 * Gets the cantidad movimientos.
	 *
	 * @return Obtiene la cantidad movimientos en el corte
	 */
	public Integer getCantidadMovimientos()
	{
		return id_cantidadMovimientos;
	}

	/**
	 * Sets the clave banco.
	 *
	 * @param as_claveBanco Modifica el valor de la propiedad claveBanco
	 */
	public void setClaveBanco(String as_claveBanco)
	{
		is_claveBanco = as_claveBanco;
	}

	/**
	 * Gets the clave banco.
	 *
	 * @return Retorna el valor de la propiedad claveBanco
	 */
	public String getClaveBanco()
	{
		return is_claveBanco;
	}

	/**
	 * Asigna la moneda de los movimentos reflejados en el arhivo detalle.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setClaveMoneda(String as_s)
	{
		is_claveMoneda = StringUtils.getString(as_s);
	}

	/**
	 * Gets the clave moneda.
	 *
	 * @return Obtiene la moneda de los movimentos reflejados en el arhivo detalle
	 */
	public String getClaveMoneda()
	{
		return is_claveMoneda;
	}

	/**
	 * Sets the cuenta bancaria.
	 *
	 * @param as_cuentaBancaria Modifica el valor de la propiedad cuentaBancaria
	 */
	public void setCuentaBancaria(String as_cuentaBancaria)
	{
		is_cuentaBancaria = as_cuentaBancaria;
	}

	/**
	 * Gets the cuenta bancaria.
	 *
	 * @return Retorna el valor de la propiedad cuentaBancaria
	 */
	public String getCuentaBancaria()
	{
		return is_cuentaBancaria;
	}

	/**
	 * Asigna el estado de cargue del archivo.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setEstado(String as_s)
	{
		is_estado = StringUtils.getString(as_s);
	}

	/**
	 * Gets the estado.
	 *
	 * @return Obtiene el estado de cargue del archivo
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Sets the fecha origen movimiento.
	 *
	 * @param ad_fechaOrigenMovimiento Modifica el valor de la propiedad fechaOrigenMovimiento
	 */
	public void setFechaOrigenMovimiento(Date ad_fechaOrigenMovimiento)
	{
		id_fechaOrigenMovimiento = ad_fechaOrigenMovimiento;
	}

	/**
	 * Gets the fecha origen movimiento.
	 *
	 * @return Retorna el valor de la propiedad fechaOrigenMovimiento
	 */
	public Date getFechaOrigenMovimiento()
	{
		return id_fechaOrigenMovimiento;
	}

	/**
	 * Sets the fila.
	 *
	 * @param as_fila Modifica el valor de la propiedad fila
	 */
	public void setFila(String as_fila)
	{
		is_fila = as_fila;
	}

	/**
	 * Gets the fila.
	 *
	 * @return Retorna el valor de la propiedad fila
	 */
	public String getFila()
	{
		return is_fila;
	}

	/**
	 * Asigna el Id del archivo asociado al registro.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdArchivo(String as_s)
	{
		is_idArchivo = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id archivo.
	 *
	 * @return Obtiene el Id del archivo asociado al registro
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * Asigna el id de la cuenta asociada al registro.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdCuenta(String as_s)
	{
		is_idCuenta = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return Obtiene el id de la cuenta asociada al registro
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * Asigna el id de perido de corte asociado al registro.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdPeriodoCorte(String as_s)
	{
		is_idPeriodoCorte = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id periodo corte.
	 *
	 * @return Obtiene el id de perido de corte asociado al registro
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * Asigna el saldo final del corte.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setSaldoFinal(Double ad_d)
	{
		id_saldoFinal = ad_d;
	}

	/**
	 * Gets the saldo final.
	 *
	 * @return Obtiene el saldo final del corte
	 */
	public Double getSaldoFinal()
	{
		return id_saldoFinal;
	}

	/**
	 * Asigna el saldo inicial del corte.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setSaldoInicial(Double ad_d)
	{
		id_saldoInicial = ad_d;
	}

	/**
	 * Gets the saldo inicial.
	 *
	 * @return Obtiene el saldo inicial del corte
	 */
	public Double getSaldoInicial()
	{
		return id_saldoInicial;
	}

	/**
	 * @param as_tipoCuenta Modifica el valor de la propiedad tipoCuenta
	 */
	public void setTipoCuenta(String as_tipoCuenta)
	{
		is_tipoCuenta = as_tipoCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad tipoCuenta
	 */
	public String getTipoCuenta()
	{
		return is_tipoCuenta;
	}

	/**
	 * Asigna el valor total de los creditos.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setTotalCreditos(Double ad_d)
	{
		id_totalCreditos = ad_d;
	}

	/**
	 * Gets the total creditos.
	 *
	 * @return Obtiene el valor total de los creditos
	 */
	public Double getTotalCreditos()
	{
		return id_totalCreditos;
	}

	/**
	 * Asigna el valor total de los debitos.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setTotalDebitos(Double ad_d)
	{
		id_totalDebitos = ad_d;
	}

	/**
	 * Gets the total debitos.
	 *
	 * @return Obtiene el valor total de los debitos
	 */
	public Double getTotalDebitos()
	{
		return id_totalDebitos;
	}
}
