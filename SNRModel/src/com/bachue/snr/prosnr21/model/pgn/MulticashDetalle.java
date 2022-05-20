package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_MULTICASH_CABECERA del módulo de conciliaciones
 *
 * @author Edgar Prieto
 */
public class MulticashDetalle extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/**Propiedad  serialVersionUID */
	private static final long serialVersionUID = -8635453951453688850L;

	/** Fecha de afectación del saldo de la transacción */
	private Date id_fechaAfectacionSaldo;
	private Date id_fechaOrigenMovimiento;

	/**Fecha SIIF de la transacción*/
	private Date id_fechaSIIF;

	/** Monto por el que se realizó la transacción */
	private Double id_monto;

	/**Afectacion de la transacción*/
	private String is_afectacion;

	/**Campos tipo A de la transacción*/
	private String is_camposTipoA;

	/** Causal de rechazo de la transacción */
	private String is_causalRechazo;

	/** Clave banco de la transacción */
	private String is_claveBanco;

	/** Identificador interno del banco para la transacción */
	private String is_claveTransaccion;

	/** Código único de la transacción */
	private String is_codigoUnicoTransaccion;

	/** Cuenta bancaria de la transacción */
	private String is_cuentaBancaria;

	/** Estado de cargue del archivo */
	private String is_estado;

	/** Fila del archivo */
	private String is_fila;

	/** Número de formato de consignación para la transacción */
	private String is_formatoConsignacion;

	/** Id del archivo asociado al registro */
	private String is_idArchivo;

	/** Id del archivo cabecera */
	private String is_idArchivoCabecera;

	/** Id de la cuenta asociada al registro */
	private String is_idCuenta;

	/** Id de perido de corte asociado al registro */
	private String is_idPeriodoCorte;

	/** Identificador de crédito de la transacción */
	private String is_identificadorCredito;

	/** Número del cheque con el que se reaizó la transacción */
	private String is_numeroCheque;

	/**numero SIIF de la transacción*/
	private String is_numeroSIIF;

	/** Observaciones partida tipo A de la trasacción */
	private String is_observacionesPartidasTipoA;

	/**Prestacion de servicio de la transacción*/
	private String is_prestacionServicio;

	/** Referencia de la transacción */
	private String is_referencia;

	/** Referencia o NIT de la transacción */
	private String is_referenciaNit;

	/** Concepto de la trasacción */
	private String is_tipoRecaudo;

	/** Número de linea en el archivo de detalle */
	private int ii_registro;

	/**
	 * Asigna el valor de la afectación de la transacción
	 * @param as_s valor a asignar
	 */
	public void setAfectacion(String as_s)
	{
		is_afectacion = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_afectacion
	 */
	public String getAfectacion()
	{
		return is_afectacion;
	}

	/**
	 * Asigna el valor a alos campos tipo A de la transacción
	 * @param as_s valor a asignar
	 */
	public void setCamposTipoA(String as_s)
	{
		is_camposTipoA = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_camposTipoA
	 */
	public String getCamposTipoA()
	{
		return is_camposTipoA;
	}

	/**
	 * Asigna la causal de rechazo de la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setCausalRechazo(String as_s)
	{
		is_causalRechazo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene la causal de rechazo de la transacción
	 */
	public String getCausalRechazo()
	{
		return is_causalRechazo;
	}

	/**
	 * @param as_claveBanco Modifica el valor de la propiedad claveBanco
	 */
	public void setClaveBanco(String as_claveBanco)
	{
		this.is_claveBanco = as_claveBanco;
	}

	/**
	 * @return Retorna el valor de la propiedad claveBanco
	 */
	public String getClaveBanco()
	{
		return is_claveBanco;
	}

	/**
	 * Asigna el identificador interno del banco para la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setClaveTransaccion(String as_s)
	{
		is_claveTransaccion = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador interno del banco para la transacción
	 */
	public String getClaveTransaccion()
	{
		return is_claveTransaccion;
	}

	/**
	 * Asigna el código único de la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setCodigoUnicoTransaccion(String as_s)
	{
		is_codigoUnicoTransaccion = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el código único de la transacción
	 */
	public String getCodigoUnicoTransaccion()
	{
		return is_codigoUnicoTransaccion;
	}

	/**
	 * @param as_cuentaBancaria Modifica el valor de la propiedad cuentaBancaria
	 */
	public void setCuentaBancaria(String as_cuentaBancaria)
	{
		this.is_cuentaBancaria = as_cuentaBancaria;
	}

	/**
	 * @return Retorna el valor de la propiedad cuentaBancaria
	 */
	public String getCuentaBancaria()
	{
		return is_cuentaBancaria;
	}

	/**
	 * Asigna el estado de cargue del archivo
	 *
	 * @param as_s Valor a asignar
	 */
	public void setEstado(String as_s)
	{
		is_estado = StringUtils.getString(as_s);
	}

	/** @return Obtiene el estado de cargue del archivo */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Asigna la fecha de afectación del saldo de la transacción
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaAfectacionSaldo(Date ad_d)
	{
		id_fechaAfectacionSaldo = ad_d;
	}

	/**
	 * @return Obtiene la fecha de afectación del saldo de la transacción
	 */
	public Date getFechaAfectacionSaldo()
	{
		return id_fechaAfectacionSaldo;
	}

	/**
	 * @param ad_fechaOrigenMovimiento Modifica el valor de la propiedad fechaOrigenMovimiento
	 */
	public void setFechaOrigenMovimiento(Date ad_fechaOrigenMovimiento)
	{
		id_fechaOrigenMovimiento = ad_fechaOrigenMovimiento;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaOrigenMovimiento
	 */
	public Date getFechaOrigenMovimiento()
	{
		return id_fechaOrigenMovimiento;
	}

	/**
	 * Asigna el valor de la fecha SIIF de la transacción
	 * @param ad_d valor a asignar
	 */
	public void setFechaSIIF(Date ad_d)
	{
		id_fechaSIIF = ad_d;
	}

	/**
	 * @return Retorna el valor de la propiedad id_fechaSIIF
	 */
	public Date getFechaSIIF()
	{
		return id_fechaSIIF;
	}

	/**
	 * @param as_fila Modifica el valor de la propiedad fila
	 */
	public void setFila(String as_fila)
	{
		is_fila = as_fila;
	}

	/**
	 * @return Retorna el valor de la propiedad fila
	 */
	public String getFila()
	{
		return is_fila;
	}

	/**
	 * Asigna el número de formato de consignación para la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setFormatoConsignacion(String as_s)
	{
		is_formatoConsignacion = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el número de formato de consignación para la transacción
	 */
	public String getFormatoConsignacion()
	{
		return is_formatoConsignacion;
	}

	/**
	 * Asigna el Id del archivo asociado al registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdArchivo(String as_s)
	{
		is_idArchivo = StringUtils.getString(as_s);
	}

	/** @return Obtiene el Id del archivo asociado al registro */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * Asigna el Id del archivo cabecera
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdArchivoCabecera(String as_s)
	{
		is_idArchivoCabecera = StringUtils.getString(as_s);
	}

	/** @return Obtiene el Id del archivo cabecera */
	public String getIdArchivoCabecera()
	{
		return is_idArchivoCabecera;
	}

	/**
	 * Asigna el id de la cuenta asociada al registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdCuenta(String as_s)
	{
		is_idCuenta = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el id de la cuenta asociada al registro
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * Asigna el id de perido de corte asociado al registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdPeriodoCorte(String as_s)
	{
		is_idPeriodoCorte = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el id de perido de corte asociado al registro
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * Asigna el identificador de crédito de la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdentificadorCredito(String as_s)
	{
		is_identificadorCredito = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador de crédito de la transacción
	 */
	public String getIdentificadorCredito()
	{
		return is_identificadorCredito;
	}

	/**
	 * Asigna el monto por el que se realizó la transacción
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setMonto(Double ad_d)
	{
		id_monto = ad_d;
	}

	/**
	 * @return Obtiene el monto por el que se realizó la transacción
	 */
	public Double getMonto()
	{
		return id_monto;
	}

	/**
	 * Asigna el número del cheque con el que se reaizó la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNumeroCheque(String as_s)
	{
		is_numeroCheque = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el número del cheque con el que se reaizó la transacción
	 */
	public String getNumeroCheque()
	{
		return is_numeroCheque;
	}

	/**
	 * Asigna el valor del número SIIF de la transacción
	 * @param as_s valor a asignar
	 */
	public void setNumeroSIIF(String as_s)
	{
		is_numeroSIIF = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroSIIF
	 */
	public String getNumeroSIIF()
	{
		return is_numeroSIIF;
	}

	/**
	 * Asigna el valor de las observaciones tipo A de la transaccion
	 * @param as_s valor a asignar
	 */
	public void setObservacionesPartidasTipoA(String as_s)
	{
		is_observacionesPartidasTipoA = as_s;
	}

	/**
	 * @return Retorna el valor de las observaciones tipo a de la transacción
	 */
	public String getObservacionesPartidasTipoA()
	{
		return is_observacionesPartidasTipoA;
	}

	/**
	 * Asigna el valor a la prestación de servicio de la transacción
	 * @param as_s valor a asignar
	 */
	public void setPrestacionServicio(String as_s)
	{
		is_prestacionServicio = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_prestacionServicio
	 */
	public String getPrestacionServicio()
	{
		return is_prestacionServicio;
	}

	/**
	 * Asigna la referencia de la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene la referencia de la transacción
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Asigna la referencia o NIT de la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setReferenciaNit(String as_s)
	{
		is_referenciaNit = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene la referencia o NIT de la transacción
	 */
	public String getReferenciaNit()
	{
		return is_referenciaNit;
	}

	/**
	 * Asigna el número de linea en el archivo de detalle
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setRegistro(int ai_i)
	{
		ii_registro = ai_i;
	}

	/**
	 * @return Obtiene el número de linea en el archivo de detalle
	 */
	public int getRegistro()
	{
		return ii_registro;
	}

	/**
	 * Asigna el concepto de la trasacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setTipoRecaudo(String as_s)
	{
		is_tipoRecaudo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el concepto de la trasacción
	 */
	public String getTipoRecaudo()
	{
		return is_tipoRecaudo;
	}
}
