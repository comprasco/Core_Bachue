package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;

import java.util.Date;


/**
 * Clase que representa un registro en la vista SDB_VW_CRPS_ENC_CONCILIAR del módulo de conciliaciones
 *
 * @author Edgar Prieto
 */
public class CRPSCabecera extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/**Propiedad  serialVersionUID */
	private static final long serialVersionUID = -6054815058876260688L;

	/** Fecha bancaria de la transaccion */
	private Date id_fechaBancaria;

	/** Fecha de generación de recibio de la liquidacion */
	private Date id_fechaGeneracionRecibo;

	/** Fecha de liquidacion */
	private Date id_fechaLiquidacion;

	/** Fecha de creación de la solicitud */
	private Date id_fechaSolicitud;

	/** Fecha de la transacción */
	private Date id_fechaTransaccion;

	/** Monto de la transaccion */
	private Double id_montoTransaccion;

	/** Valor por conservación documental */
	private Double id_valorConservacionDocumental;

	/** Valor por impuestos */
	private Double id_valorImpuestoLiquidacion;

	/** Valor del servicio prestado */
	private Double id_valorLiquidacion;

	/** Valor total de la liquidación */
	private Double id_valorTotalLiquidacion;

	/** Consecutivo de la liquidación */
	private Integer ii_consecutivoLiquidacion;

	/** Codigo interno de la transacción  */
	private String is_codigoTransaccionRecaudador;

	/** Indicador de digitalización */
	private String is_digitalizada;

	/** Estado de pago de la liquidación */
	private String is_estadoPago;

	/** Id del archivo asociado al registro */
	private String is_idArchivo;

	/** Tipo de documento del solicitante */
	private String is_idDocumentoTipo;

	/** Identificador de la entidad que reporta el pago */
	private String is_idEntidadRecaudo;

	/** Identificador de la liquidación */
	private String is_idLiquidacion;

	/** Identificador del proceso al que pertenece la solicitud */
	private String is_idProceso;

	/** Identificador del registro de pago */
	private String is_idRegistroPago;

	/** Identificaddor de la solicitud */
	private String is_idSolicitud;

	/** Identificador del subproceso al que pertenece la solicitud */
	private String is_idSubproceso;

	/** Identificador de la sucursal de la entidad qeu reporta el pago */
	private String is_idSucursalRecaudo;

	/** Identificador del tipo de canal de pago */
	private String is_idTipoCanal;

	/** Identificador del tipo persona del solicitante */
	private String is_idTipoPersona;

	/** Identificador del tipo de recaudo */
	private String is_idTipoRecaudo;

	/** Identificador del usuario con el que se crea la solicitud */
	private String is_idUsuarioCreacionSolicitud;

	/** Codigo NIR de la solicitud */
	private String is_nir;

	/** Nombre del proceso al que pertenece la solicitud */
	private String is_nombreProceso;

	/** Nombre del subproceso al que pertenece la solicitud */
	private String is_nombreSubproceso;

	/** Número de cuenta donde se hizo el recuaudo */
	private String is_numeroCuentaRecaudo;

	/** Número de documento del solicitante */
	private String is_numeroDocumento;

	/** Número de recibo de caja de la liquidación */
	private String is_numeroReciboCaja;

	/** Número de referencia de la liquidación */
	private String is_numeroReferencia;

	/** Primer apellido del solicitante */
	private String is_primerApellido;

	/** Primer nombre del solicitante */
	private String is_primerNombre;

	/** Razon social solicitante */
	private String is_razonSocial;

	/** Segundo apellido del solicitante */
	private String is_segundoApellido;

	/** Segundo nombre del solicitante */
	private String is_segundoNombre;

	/** Número de linea en el archivo de detalle */
	private int ii_registro;

	/**
	 * Asigna el codigo interno de la transacción
	 *
	 * @param as_s Valor a asignar
	 */
	public void setCodigoTransaccionRecaudador(String as_s)
	{
		is_codigoTransaccionRecaudador = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el codigo interno de la transacción
	 */
	public String getCodigoTransaccionRecaudador()
	{
		return is_codigoTransaccionRecaudador;
	}

	/**
	 * Asigna el consecutivo de la liquidación
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setConsecutivoLiquidacion(Integer ai_i)
	{
		ii_consecutivoLiquidacion = ai_i;
	}

	/**
	 * @return Obtiene el consecutivo de la liquidación
	 */
	public Integer getConsecutivoLiquidacion()
	{
		return ii_consecutivoLiquidacion;
	}

	/**
	 * Asigna el indicador de digitalización
	 *
	 * @param as_s Valor a asignar
	 */
	public void setDigitalizada(String as_s)
	{
		is_digitalizada = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el indicador de digitalización
	 */
	public String getDigitalizada()
	{
		return is_digitalizada;
	}

	/**
	 * Asigna el estado de pago de la liquidación
	 *
	 * @param as_s Valor a asignar
	 */
	public void setEstadoPago(String as_s)
	{
		is_estadoPago = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el estado de pago de la liquidación
	 */
	public String getEstadoPago()
	{
		return is_estadoPago;
	}

	/**
	 * Asigna la fecha bancaria de la transaccion
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaBancaria(Date ad_d)
	{
		id_fechaBancaria = ad_d;
	}

	/**
	 * @return Obtiene la fecha bancaria de la transaccion
	 */
	public Date getFechaBancaria()
	{
		return id_fechaBancaria;
	}

	/**
	 * Asigna la fecha de generación de recibio de la liquidacion
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaGeneracionRecibo(Date ad_d)
	{
		id_fechaGeneracionRecibo = ad_d;
	}

	/**
	 * @return Obtiene la fecha de generación de recibio de la liquidacion
	 */
	public Date getFechaGeneracionRecibo()
	{
		return id_fechaGeneracionRecibo;
	}

	/**
	 * Asigna la fecha de liquidacion
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaLiquidacion(Date ad_d)
	{
		id_fechaLiquidacion = ad_d;
	}

	/**
	 * @return Obtiene la fecha de liquidacion
	 */
	public Date getFechaLiquidacion()
	{
		return id_fechaLiquidacion;
	}

	/**
	 * Asigna la fecha de creación de la solicitud
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaSolicitud(Date ad_d)
	{
		id_fechaSolicitud = ad_d;
	}

	/**
	 * @return Obtiene la fecha de creación de la solicitud
	 */
	public Date getFechaSolicitud()
	{
		return id_fechaSolicitud;
	}

	/**
	 * Asigna la fecha de la transacción
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaTransaccion(Date ad_d)
	{
		id_fechaTransaccion = ad_d;
	}

	/**
	 * @return Obtiene la fecha de la transacción
	 */
	public Date getFechaTransaccion()
	{
		return id_fechaTransaccion;
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
	 * Asigna el tipo de documento del solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdDocumentoTipo(String as_s)
	{
		is_idDocumentoTipo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el tipo de documento del solicitante
	 */
	public String getIdDocumentoTipo()
	{
		return is_idDocumentoTipo;
	}

	/**
	 * Asigna el identificador de la entidad que reporta el pago
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdEntidadRecaudo(String as_s)
	{
		is_idEntidadRecaudo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador de la entidad que reporta el pago
	 */
	public String getIdEntidadRecaudo()
	{
		return is_idEntidadRecaudo;
	}

	/**
	 * Asigna el identificador de la liquidación
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdLiquidacion(String as_s)
	{
		is_idLiquidacion = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador de la liquidación
	 */
	public String getIdLiquidacion()
	{
		return is_idLiquidacion;
	}

	/**
	 * Asigna el identificador del proceso al que pertenece la solicitud
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador del proceso al que pertenece la solicitud
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Asigna el identificador del registro de pago
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdRegistroPago(String as_s)
	{
		is_idRegistroPago = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador del registro de pago
	 */
	public String getIdRegistroPago()
	{
		return is_idRegistroPago;
	}

	/**
	 * Asigna el identificaddor de la solicitud
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene identificaddor de la solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Asigna el identificador del subproceso al que pertenece la solicitud
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador del subproceso al que pertenece la solicitud
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Asigna el identificador de la sucursal de la entidad qeu reporta el pago
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdSucursalRecaudo(String as_s)
	{
		is_idSucursalRecaudo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador de la sucursal de la entidad qeu reporta el pago
	 */
	public String getIdSucursalRecaudo()
	{
		return is_idSucursalRecaudo;
	}

	/**
	 * Asigna el identificador del tipo de canal de pago
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdTipoCanal(String as_s)
	{
		is_idTipoCanal = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador del tipo de canal de pago
	 */
	public String getIdTipoCanal()
	{
		return is_idTipoCanal;
	}

	/**
	 * Asigna el identificador del tipo persona del solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdTipoPersona(String as_s)
	{
		is_idTipoPersona = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador del tipo persona del solicitante
	 */
	public String getIdTipoPersona()
	{
		return is_idTipoPersona;
	}

	/**
	 * Asigna el identificador del tipo de recaudo
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdTipoRecaudo(String as_s)
	{
		is_idTipoRecaudo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador del tipo de recaudo
	 */
	public String getIdTipoRecaudo()
	{
		return is_idTipoRecaudo;
	}

	/**
	 * Asigna el identificador del usuario con el que se crea la solicitud
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdUsuarioCreacionSolicitud(String as_s)
	{
		is_idUsuarioCreacionSolicitud = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el identificador del usuario con el que se crea la solicitud
	 */
	public String getIdUsuarioCreacionSolicitud()
	{
		return is_idUsuarioCreacionSolicitud;
	}

	/**
	 * Asigna el monto de la transaccion
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setMontoTransaccion(Double ad_d)
	{
		id_montoTransaccion = ad_d;
	}

	/**
	 * @return Obtiene el monto de la transaccion
	 */
	public Double getMontoTransaccion()
	{
		return id_montoTransaccion;
	}

	/**
	 * Asigna el codigo NIR de la solicitud
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNir(String as_s)
	{
		is_nir = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el codigo NIR de la solicitud
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Asigna el nombre del proceso al que pertenece la solicitud
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el nombre del proceso al que pertenece la solicitud
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Asigna el nombre del subproceso al que pertenece la solicitud
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreSubproceso(String as_s)
	{
		is_nombreSubproceso = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el nombre del subproceso al que pertenece la solicitud
	 */
	public String getNombreSubproceso()
	{
		return is_nombreSubproceso;
	}

	/**
	 * Asigna el número de cuenta donde se hizo el recuaudo
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNumeroCuentaRecaudo(String as_s)
	{
		is_numeroCuentaRecaudo = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el número de cuenta donde se hizo el recuaudo
	 */
	public String getNumeroCuentaRecaudo()
	{
		return is_numeroCuentaRecaudo;
	}

	/**
	 * Asigna el número de documento del solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el número de documento del solicitante
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Asigna el número de recibo de caja de la liquidación
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNumeroReciboCaja(String as_s)
	{
		is_numeroReciboCaja = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el número de recibo de caja de la liquidación
	 */
	public String getNumeroReciboCaja()
	{
		return is_numeroReciboCaja;
	}

	/**
	 * Asigna el número de referencia de la liquidación
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNumeroReferencia(String as_s)
	{
		is_numeroReferencia = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el número de referencia de la liquidación
	 */
	public String getNumeroReferencia()
	{
		return is_numeroReferencia;
	}

	/**
	 * Asigna el primer apellido del solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setPrimerApellido(String as_s)
	{
		is_primerApellido = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el primer apellido del solicitante
	 */
	public String getPrimerApellido()
	{
		return is_primerApellido;
	}

	/**
	 * Asigna el primer nombre del solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setPrimerNombre(String as_s)
	{
		is_primerNombre = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el primer nombre del solicitante
	 */
	public String getPrimerNombre()
	{
		return is_primerNombre;
	}

	/**
	 * Asigna la razon social solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setRazonSocial(String as_s)
	{
		is_razonSocial = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene la razon social solicitante
	 */
	public String getRazonSocial()
	{
		return is_razonSocial;
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
	 * Asigna el segundo apellido del solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setSegundoApellido(String as_s)
	{
		is_segundoApellido = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el segundo apellido del solicitante
	 */
	public String getSegundoApellido()
	{
		return is_segundoApellido;
	}

	/**
	 * Asigna el segundo nombre del solicitante
	 *
	 * @param as_s Valor a asignar
	 */
	public void setSegundoNombre(String as_s)
	{
		is_segundoNombre = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el segundo nombre del solicitante
	 */
	public String getSegundoNombre()
	{
		return is_segundoNombre;
	}

	/**
	 * Asigna el valor por conservación documental
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorConservacionDocumental(Double ad_d)
	{
		id_valorConservacionDocumental = ad_d;
	}

	/**
	 * @return Obtiene el valor por conservación documental
	 */
	public Double getValorConservacionDocumental()
	{
		return id_valorConservacionDocumental;
	}

	/**
	 * Asigna el valor por impuestos
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorImpuestoLiquidacion(Double ad_d)
	{
		id_valorImpuestoLiquidacion = ad_d;
	}

	/**
	 * @return Obtiene el valor por impuestos
	 */
	public Double getValorImpuestoLiquidacion()
	{
		return id_valorImpuestoLiquidacion;
	}

	/**
	 * Asigna el valor del servicio prestado
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorLiquidacion(Double ad_d)
	{
		id_valorLiquidacion = ad_d;
	}

	/**
	 * @return Obtiene el valor del servicio prestado
	 */
	public Double getValorLiquidacion()
	{
		return id_valorLiquidacion;
	}

	/**
	 * Asigna el valor total de la liquidación
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValorTotalLiquidacion(Double ad_d)
	{
		id_valorTotalLiquidacion = ad_d;
	}

	/**
	 * @return Obtiene el valor total de la liquidación
	 */
	public Double getValorTotalLiquidacion()
	{
		return id_valorTotalLiquidacion;
	}
}
