package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_MULTICASH_CABECERA del m�dulo de conciliaciones
 *
 * @author Edgar Prieto
 */
public class MulticashDetalle extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/**Propiedad  serialVersionUID */
	private static final long serialVersionUID = -8635453951453688850L;

	/** Fecha de afectaci�n del saldo de la transacci�n */
	private Date id_fechaAfectacionSaldo;
	private Date id_fechaOrigenMovimiento;

	/**Fecha SIIF de la transacci�n*/
	private Date id_fechaSIIF;

	/** Monto por el que se realiz� la transacci�n */
	private Double id_monto;

	/**Afectacion de la transacci�n*/
	private String is_afectacion;

	/**Campos tipo A de la transacci�n*/
	private String is_camposTipoA;

	/** Causal de rechazo de la transacci�n */
	private String is_causalRechazo;

	/** Clave banco de la transacci�n */
	private String is_claveBanco;

	/** Identificador interno del banco para la transacci�n */
	private String is_claveTransaccion;

	/** C�digo �nico de la transacci�n */
	private String is_codigoUnicoTransaccion;

	/** Cuenta bancaria de la transacci�n */
	private String is_cuentaBancaria;

	/** Estado de cargue del archivo */
	private String is_estado;

	/** Fila del archivo */
	private String is_fila;

	/** N�mero de formato de consignaci�n para la transacci�n */
	private String is_formatoConsignacion;

	/** Id del archivo asociado al registro */
	private String is_idArchivo;

	/** Id del archivo cabecera */
	private String is_idArchivoCabecera;

	/** Id de la cuenta asociada al registro */
	private String is_idCuenta;

	/** Id de perido de corte asociado al registro */
	private String is_idPeriodoCorte;

	/** Identificador de cr�dito de la transacci�n */
	private String is_identificadorCredito;

	/** N�mero del cheque con el que se reaiz� la transacci�n */
	private String is_numeroCheque;

	/**numero SIIF de la transacci�n*/
	private String is_numeroSIIF;

	/** Observaciones partida tipo A de la trasacci�n */
	private String is_observacionesPartidasTipoA;

	/**Prestacion de servicio de la transacci�n*/
	private String is_prestacionServicio;

	/** Referencia de la transacci�n */
	private String is_referencia;

	/** Referencia o NIT de la transacci�n */
	private String is_referenciaNit;

	/** Concepto de la trasacci�n */
	private String is_tipoRecaudo;

	/** N�mero de linea en el archivo de detalle */
	private int ii_registro;

	/**
	 * Asigna el valor de la afectaci�n de la transacci�n
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
	 * Asigna el valor a alos campos tipo A de la transacci�n
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
	 * Asigna la causal de rechazo de la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setCausalRechazo(String as_s)
	{
		is_causalRechazo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene la causal de rechazo de la transacci�n
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
	 * Asigna el identificador interno del banco para la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setClaveTransaccion(String as_s)
	{
		is_claveTransaccion = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador interno del banco para la transacci�n
	 */
	public String getClaveTransaccion()
	{
		return is_claveTransaccion;
	}

	/**
	 * Asigna el c�digo �nico de la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setCodigoUnicoTransaccion(String as_s)
	{
		is_codigoUnicoTransaccion = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el c�digo �nico de la transacci�n
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
	 * Asigna la fecha de afectaci�n del saldo de la transacci�n
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaAfectacionSaldo(Date ad_d)
	{
		id_fechaAfectacionSaldo = ad_d;
	}

	/**
	 * @return Obtiene la fecha de afectaci�n del saldo de la transacci�n
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
	 * Asigna el valor de la fecha SIIF de la transacci�n
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
	 * Asigna el n�mero de formato de consignaci�n para la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setFormatoConsignacion(String as_s)
	{
		is_formatoConsignacion = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el n�mero de formato de consignaci�n para la transacci�n
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
	 * Asigna el identificador de cr�dito de la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdentificadorCredito(String as_s)
	{
		is_identificadorCredito = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador de cr�dito de la transacci�n
	 */
	public String getIdentificadorCredito()
	{
		return is_identificadorCredito;
	}

	/**
	 * Asigna el monto por el que se realiz� la transacci�n
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setMonto(Double ad_d)
	{
		id_monto = ad_d;
	}

	/**
	 * @return Obtiene el monto por el que se realiz� la transacci�n
	 */
	public Double getMonto()
	{
		return id_monto;
	}

	/**
	 * Asigna el n�mero del cheque con el que se reaiz� la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNumeroCheque(String as_s)
	{
		is_numeroCheque = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el n�mero del cheque con el que se reaiz� la transacci�n
	 */
	public String getNumeroCheque()
	{
		return is_numeroCheque;
	}

	/**
	 * Asigna el valor del n�mero SIIF de la transacci�n
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
	 * @return Retorna el valor de las observaciones tipo a de la transacci�n
	 */
	public String getObservacionesPartidasTipoA()
	{
		return is_observacionesPartidasTipoA;
	}

	/**
	 * Asigna el valor a la prestaci�n de servicio de la transacci�n
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
	 * Asigna la referencia de la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setReferencia(String as_s)
	{
		is_referencia = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene la referencia de la transacci�n
	 */
	public String getReferencia()
	{
		return is_referencia;
	}

	/**
	 * Asigna la referencia o NIT de la transacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setReferenciaNit(String as_s)
	{
		is_referenciaNit = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene la referencia o NIT de la transacci�n
	 */
	public String getReferenciaNit()
	{
		return is_referenciaNit;
	}

	/**
	 * Asigna el n�mero de linea en el archivo de detalle
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setRegistro(int ai_i)
	{
		ii_registro = ai_i;
	}

	/**
	 * @return Obtiene el n�mero de linea en el archivo de detalle
	 */
	public int getRegistro()
	{
		return ii_registro;
	}

	/**
	 * Asigna el concepto de la trasacci�n
	 *
	 * @param as_s Valor a asignar
	 */
	public void setTipoRecaudo(String as_s)
	{
		is_tipoRecaudo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el concepto de la trasacci�n
	 */
	public String getTipoRecaudo()
	{
		return is_tipoRecaudo;
	}
}
