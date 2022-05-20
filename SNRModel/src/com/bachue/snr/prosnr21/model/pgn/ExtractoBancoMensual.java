package com.bachue.snr.prosnr21.model.pgn;


// TODO: Auto-generated Javadoc
/**
 * Clase que representa un registro en la tabla SDB_CON_EXTRACTO_BANCO_MENSUAL del módulo de conciliaciones.
 *
 * @author Kevin Pulido
 */
public class ExtractoBancoMensual extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6005376239258473585L;

	/** Propiedad id valor. */
	private Double id_valor;

	/** Propiedad is saldo dia. */
	private Double is_saldoDia;

	/** Propiedad id codigo transaccion. */
	private Integer id_codigoTransaccion;

	/** Propiedad id consecutivo. */
	private Integer id_consecutivo;

	/** Propiedad id documento. */
	private Integer id_documento;

	/** Propiedad id id registro. */
	private Integer id_idRegistro;

	/** Propiedad id oficina origen. */
	private Integer id_oficinaOrigen;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is cuenta. */
	private String is_cuenta;

	/** Propiedad is fecha. */
	private String is_fecha;

	/** Propiedad is id archivo. */
	private String is_idArchivo;

	/** Propiedad is id cuenta. */
	private String is_idCuenta;

	/** Propiedad is id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is nombre archivo. */
	private String is_nombreArchivo;

	/** Propiedad is nombre corto transaccion. */
	private String is_nombreCortoTransaccion;

	/** Propiedad is nombre extracto transaccion. */
	private String is_nombreExtractoTransaccion;

	/** Propiedad is oficina. */
	private String is_oficina;

	/** Propiedad is periodo. */
	private String is_periodo;

	/** Propiedad is signo saldo. */
	private String is_signoSaldo;

	/** Propiedad is signo valor. */
	private String is_signoValor;

	/** Propiedad is tipo cuenta. */
	private String is_tipoCuenta;

	/** Propiedad is tipo transaccion. */
	private String is_tipoTransaccion;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_activo de as activo
	 */
	public void setActivo(String as_activo)
	{
		this.is_activo = as_activo;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de CodigoTransaccion.
	 *
	 * @param ad_codigoTransaccion de ad codigo transaccion
	 */
	public void setCodigoTransaccion(Integer ad_codigoTransaccion)
	{
		this.id_codigoTransaccion = ad_codigoTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor codigo transaccion.
	 *
	 * @return el valor de codigo transaccion
	 */
	public Integer getCodigoTransaccion()
	{
		return id_codigoTransaccion;
	}

	/**
	 * Modifica el valor de Consecutivo.
	 *
	 * @param ad_consecutivo de ad consecutivo
	 */
	public void setConsecutivo(Integer ad_consecutivo)
	{
		this.id_consecutivo = ad_consecutivo;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo.
	 *
	 * @return el valor de consecutivo
	 */
	public Integer getConsecutivo()
	{
		return id_consecutivo;
	}

	/**
	 * Modifica el valor de Cuenta.
	 *
	 * @param as_cuenta de as cuenta
	 */
	public void setCuenta(String as_cuenta)
	{
		this.is_cuenta = as_cuenta;
	}

	/**
	 * Retorna Objeto o variable de valor cuenta.
	 *
	 * @return el valor de cuenta
	 */
	public String getCuenta()
	{
		return is_cuenta;
	}

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param ad_documento de ad documento
	 */
	public void setDocumento(Integer ad_documento)
	{
		this.id_documento = ad_documento;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return el valor de documento
	 */
	public Integer getDocumento()
	{
		return id_documento;
	}

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param as_fecha de as fecha
	 */
	public void setFecha(String as_fecha)
	{
		this.is_fecha = as_fecha;
	}

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return el valor de fecha
	 */
	public String getFecha()
	{
		return is_fecha;
	}

	/**
	 * Modifica el valor de IdArchivo.
	 *
	 * @param as_idArchivo de as id archivo
	 */
	public void setIdArchivo(String as_idArchivo)
	{
		this.is_idArchivo = as_idArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor id archivo.
	 *
	 * @return el valor de id archivo
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
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
	 * Modifica el valor de IdRegistro.
	 *
	 * @param ad_idRegistro de ad id registro
	 */
	public void setIdRegistro(Integer ad_idRegistro)
	{
		this.id_idRegistro = ad_idRegistro;
	}

	/**
	 * Retorna Objeto o variable de valor id registro.
	 *
	 * @return el valor de id registro
	 */
	public Integer getIdRegistro()
	{
		return id_idRegistro;
	}

	/**
	 * Modifica el valor de NombreArchivo.
	 *
	 * @param as_nombreArchivo de as nombre archivo
	 */
	public void setNombreArchivo(String as_nombreArchivo)
	{
		this.is_nombreArchivo = as_nombreArchivo;
	}

	/**
	 * Retorna Objeto o variable de valor nombre archivo.
	 *
	 * @return el valor de nombre archivo
	 */
	public String getNombreArchivo()
	{
		return is_nombreArchivo;
	}

	/**
	 * Modifica el valor de NombreCortoTransaccion.
	 *
	 * @param as_nombreCortoTransaccion de as nombre corto transaccion
	 */
	public void setNombreCortoTransaccion(String as_nombreCortoTransaccion)
	{
		this.is_nombreCortoTransaccion = as_nombreCortoTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor nombre corto transaccion.
	 *
	 * @return el valor de nombre corto transaccion
	 */
	public String getNombreCortoTransaccion()
	{
		return is_nombreCortoTransaccion;
	}

	/**
	 * Modifica el valor de NombreExtractoTransaccion.
	 *
	 * @param as_nombreExtractoTransaccion de as nombre extracto transaccion
	 */
	public void setNombreExtractoTransaccion(String as_nombreExtractoTransaccion)
	{
		this.is_nombreExtractoTransaccion = as_nombreExtractoTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor nombre extracto transaccion.
	 *
	 * @return el valor de nombre extracto transaccion
	 */
	public String getNombreExtractoTransaccion()
	{
		return is_nombreExtractoTransaccion;
	}

	/**
	 * Modifica el valor de Oficina.
	 *
	 * @param as_oficina de as oficina
	 */
	public void setOficina(String as_oficina)
	{
		this.is_oficina = as_oficina;
	}

	/**
	 * Retorna Objeto o variable de valor oficina.
	 *
	 * @return el valor de oficina
	 */
	public String getOficina()
	{
		return is_oficina;
	}

	/**
	 * Modifica el valor de OficinaOrigen.
	 *
	 * @param ad_oficinaOrigen de ad oficina origen
	 */
	public void setOficinaOrigen(Integer ad_oficinaOrigen)
	{
		this.id_oficinaOrigen = ad_oficinaOrigen;
	}

	/**
	 * Retorna Objeto o variable de valor oficina origen.
	 *
	 * @return el valor de oficina origen
	 */
	public Integer getOficinaOrigen()
	{
		return id_oficinaOrigen;
	}

	/**
	 * Modifica el valor de Periodo.
	 *
	 * @param as_periodo de as periodo
	 */
	public void setPeriodo(String as_periodo)
	{
		this.is_periodo = as_periodo;
	}

	/**
	 * Retorna Objeto o variable de valor periodo.
	 *
	 * @return el valor de periodo
	 */
	public String getPeriodo()
	{
		return is_periodo;
	}

	/**
	 * Modifica el valor de SaldoDia.
	 *
	 * @param ad_saldoDia de ad saldo dia
	 */
	public void setSaldoDia(Double ad_saldoDia)
	{
		this.is_saldoDia = ad_saldoDia;
	}

	/**
	 * Retorna Objeto o variable de valor saldo dia.
	 *
	 * @return el valor de saldo dia
	 */
	public Double getSaldoDia()
	{
		return is_saldoDia;
	}

	/**
	 * Modifica el valor de SignoSaldo.
	 *
	 * @param as_signoSaldo de as signo saldo
	 */
	public void setSignoSaldo(String as_signoSaldo)
	{
		this.is_signoSaldo = as_signoSaldo;
	}

	/**
	 * Retorna Objeto o variable de valor signo saldo.
	 *
	 * @return el valor de signo saldo
	 */
	public String getSignoSaldo()
	{
		return is_signoSaldo;
	}

	/**
	 * Modifica el valor de SignoValor.
	 *
	 * @param as_signoValor de as signo valor
	 */
	public void setSignoValor(String as_signoValor)
	{
		this.is_signoValor = as_signoValor;
	}

	/**
	 * Retorna Objeto o variable de valor signo valor.
	 *
	 * @return el valor de signo valor
	 */
	public String getSignoValor()
	{
		return is_signoValor;
	}

	/**
	 * Modifica el valor de TipoCuenta.
	 *
	 * @param as_tipoCuenta de as tipo cuenta
	 */
	public void setTipoCuenta(String as_tipoCuenta)
	{
		this.is_tipoCuenta = as_tipoCuenta;
	}

	/**
	 * Retorna Objeto o variable de valor tipo cuenta.
	 *
	 * @return el valor de tipo cuenta
	 */
	public String getTipoCuenta()
	{
		return is_tipoCuenta;
	}

	/**
	 * Modifica el valor de TipoTransaccion.
	 *
	 * @param as_tipoTransaccion de as tipo transaccion
	 */
	public void setTipoTransaccion(String as_tipoTransaccion)
	{
		this.is_tipoTransaccion = as_tipoTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor tipo transaccion.
	 *
	 * @return el valor de tipo transaccion
	 */
	public String getTipoTransaccion()
	{
		return is_tipoTransaccion;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param ad_valor de ad valor
	 */
	public void setValor(Double ad_valor)
	{
		this.id_valor = ad_valor;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return el valor de valor
	 */
	public Double getValor()
	{
		return id_valor;
	}
}
