package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;

import java.util.Date;


/**
 * Clase que representa un registro en la tabla SDB_CON_ACH del módulo de conciliaciones.
 *
 * @author Edgar Prieto
 */
public class ACH extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/**  Nombre de columna Banco Originador. */
	public static final String CAMPO_BANCO_ORIGINADOR = "BANCO_ORIGINADOR";

	/**  Nombre de columna Banco Recaudador. */
	public static final String CAMPO_BANCO_RECAUDADOR = "BANCO_RECAUDADOR";

	/**  Nombre de columna Ciclo Origen. */
	public static final String CAMPO_CICLO_ORIGEN = "CICLO_ORIGEN";

	/**  Nombre de columna Ciclo Transacción. */
	public static final String CAMPO_CICLO_TRANSACCION = "CICLO_TRANSACCION";

	/**  Nombre de columna. */
	public static final String CAMPO_COD_DE_AUTORIZACION_RECHAZO_O_FALLIDA = "COD_DE_AUTORIZACION_RECHAZO_O_FALLIDA";

	/** The Constant CAMPO_CUS. */
	public static final String CAMPO_CUS = "CUS";

	/** The Constant CAMPO_DESCRIPCION. */
	public static final String CAMPO_DESCRIPCION = "DESCRIPCION";

	/** The Constant CAMPO_EMPRESA. */
	public static final String CAMPO_EMPRESA = "EMPRESA";

	/** The Constant CAMPO_ESTADO. */
	public static final String CAMPO_ESTADO = "ESTADO";

	/** The Constant CAMPO_FECHA_AUTORIZACION. */
	public static final String CAMPO_FECHA_AUTORIZACION = "FECHA_AUTORIZACION";

	/** The Constant CAMPO_FECHA_HORA_CREADA. */
	public static final String CAMPO_FECHA_HORA_CREADA = "FECHA_HORA_CREADA";

	/** The Constant CAMPO_FECHA_HORA_RESOLUCION_DE_LA_TRANSACCION. */
	public static final String CAMPO_FECHA_HORA_RESOLUCION_DE_LA_TRANSACCION = "FECHA_HORA_RESOLUCION_DE_LA_TRANSACCION";

	/** The Constant CAMPO_FECHA_HORA_ULTIMO_ESTADO. */
	public static final String CAMPO_FECHA_HORA_ULTIMO_ESTADO = "FECHA_HORA_ULTIMO_ESTADO";

	/** The Constant CAMPO_ID_FUNCIONALIDAD. */
	public static final String CAMPO_ID_FUNCIONALIDAD = "ID_FUNCIONALIDAD";

	/** The Constant CAMPO_ID_TAQUILLA. */
	public static final String CAMPO_ID_TAQUILLA = "ID_TAQUILLA";

	/** The Constant CAMPO_IMPUESTO. */
	public static final String CAMPO_IMPUESTO = "IMPUESTO";

	/** The Constant CAMPO_MODALIDAD_DE_VINCULACION. */
	public static final String CAMPO_MODALIDAD_DE_VINCULACION = "MODALIDAD_DE_VINCULACION";

	/** The Constant CAMPO_NIT. */
	public static final String CAMPO_NIT = "NIT";

	/** The Constant CAMPO_NOMBRE_FUNCIONALIDAD. */
	public static final String CAMPO_NOMBRE_FUNCIONALIDAD = "NOMBRE_FUNCIONALIDAD";

	/** The Constant CAMPO_NOMBRE_TAQUILLA. */
	public static final String CAMPO_NOMBRE_TAQUILLA = "NOMBRE_TAQUILLA";

	/** The Constant CAMPO_REFERENCIA_1. */
	public static final String CAMPO_REFERENCIA_1 = "REFERENCIA_1";

	/** The Constant CAMPO_REFERENCIA_2. */
	public static final String CAMPO_REFERENCIA_2 = "REFERENCIA_2";

	/** The Constant CAMPO_REFERENCIA_3. */
	public static final String CAMPO_REFERENCIA_3 = "REFERENCIA_3";

	/** The Constant CAMPO_SERVICIO_CODIGO. */
	public static final String CAMPO_SERVICIO_CODIGO = "SERVICIO_CODIGO";

	/** The Constant CAMPO_SERVICIO_NIT. */
	public static final String CAMPO_SERVICIO_NIT = "SERVICIO_NIT";

	/** The Constant CAMPO_SERVICIO_NOMBRE. */
	public static final String CAMPO_SERVICIO_NOMBRE = "SERVICIO_NOMBRE";

	/** The Constant CAMPO_TICKET_ID. */
	public static final String CAMPO_TICKET_ID = "TICKET_ID";

	/** The Constant CAMPO_TIPO_DE_AUTORIZACION. */
	public static final String CAMPO_TIPO_DE_AUTORIZACION = "TIPO_DE_AUTORIZACION";

	/** The Constant CAMPO_TIPO_DE_TRANSACCIONES. */
	public static final String CAMPO_TIPO_DE_TRANSACCIONES = "TIPO_DE_TRANSACCIONES";

	/** The Constant CAMPO_TIPO_DE_USUARIO. */
	public static final String CAMPO_TIPO_DE_USUARIO = "TIPO_DE_USUARIO";

	/** The Constant CAMPO_VALOR. */
	public static final String CAMPO_VALOR = "VALOR";

	/** Propiedad  serialVersionUID. */
	private static final long serialVersionUID = 7475431766717153826L;

	/**  Fecha de autorizacion. */
	private Date id_fechaAutorizacion;

	/**  Fecha de creación. */
	private Date id_fechaCreada;

	/**  Fecha de resolucion de la transaccion. */
	private Date id_fechaResolucion;

	/**  Fecha de ultimo estado. */
	private Date id_fechaUltimoEstado;

	/**  Valor de impuesto de la transacción. */
	private Double id_impuesto;

	/**  Valor por el que se realizó la transacción. */
	private Double id_valor;

	/**  Ciclo de origen. */
	private Long il_cicloOrigen;

	/**  Ciclo de transaccion. */
	private Long il_cicloTransaccion;

	/**  Codigo de servicio. */
	private Long il_codigoServicio;

	/**  Valor CUS para el registro. */
	private Long il_cus;

	/**  Id de ticket de la transaccion. */
	private Long il_ticketId;

	/**  Banco de origen. */
	private String is_bancoOrigen;

	/**  Banco de recuadador. */
	private String is_bancoRecaudador;

	/**  Codigo de autorizacion. */
	private String is_codigoAutorizacion;

	/**  Nit de la empresa. */
	private String is_codigoComercio;

	/**  Descripcion. */
	private String is_descripcion;

	/**  Estado de la empresa. */
	private String is_estadoComercio;

	/**  Id del archivo asociado al registro. */
	private String is_idArchivo;

	/**  Id de la cuenta asociada al registro. */
	private String is_idCuenta;

	/**  Id de funcionalidad. */
	private String is_idFuncionalidad;

	/**  Id de perido de corte asociado al registro. */
	private String is_idPeriodoCorte;

	/**  Id de taquilla. */
	private String is_idTaquilla;

	/**  Modalidad de vinculacion. */
	private String is_modalidadVinculacion;

	/**  Nit de servicio. */
	private String is_nitServicio;

	/**  Nombre de la empresa. */
	private String is_nombreComercio;

	/**  Nombre de funcionalidad. */
	private String is_nombreFuncionalidad;

	/**  Nombre de servicio. */
	private String is_nombreServicio;

	/**  Nombre de taquilla. */
	private String is_nombreTaquilla;

	/**  Referencia 1. */
	private String is_referencia1;

	/**  Referencia 2. */
	private String is_referencia2;

	/**  Referencia 3. */
	private String is_referencia3;

	/**  Tipo de autorizacion. */
	private String is_tipoAutorizacion;

	/**  Tipo de transaccion. */
	private String is_tipoTransaccion;

	/**  Tipo de usuario. */
	private String is_tipoUsuario;

	/**  Número de linea en el archivo de detalle. */
	private int ii_registro;

	/**
	 * Asigna el banco de origen.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setBancoOrigen(String as_s)
	{
		is_bancoOrigen = StringUtils.getString(as_s);
	}

	/**
	 * Gets the banco origen.
	 *
	 * @return Obtiene el banco de origen
	 */
	public String getBancoOrigen()
	{
		return is_bancoOrigen;
	}

	/**
	 * Asigna el banco recuadador.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setBancoRecaudador(String as_s)
	{
		is_bancoRecaudador = StringUtils.getString(as_s);
	}

	/**
	 * Gets the banco recaudador.
	 *
	 * @return Obtiene el banco recuadador
	 */
	public String getBancoRecaudador()
	{
		return is_bancoRecaudador;
	}

	/**
	 * Asigna el ciclo de origen.
	 *
	 * @param al_l Valor a asignar
	 */
	public void setCicloOrigen(Long al_l)
	{
		il_cicloOrigen = al_l;
	}

	/**
	 * Gets the ciclo origen.
	 *
	 * @return Obtiene el ciclo de origen
	 */
	public Long getCicloOrigen()
	{
		return il_cicloOrigen;
	}

	/**
	 * Asigna el ciclo de transaccion.
	 *
	 * @param al_l Valor a asignar
	 */
	public void setCicloTransaccion(Long al_l)
	{
		il_cicloTransaccion = al_l;
	}

	/**
	 * Gets the ciclo transaccion.
	 *
	 * @return Obtiene el ciclo de transaccion
	 */
	public Long getCicloTransaccion()
	{
		return il_cicloTransaccion;
	}

	/**
	 * Asigna el codigo de autorizacion.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setCodigoAutorizacion(String as_s)
	{
		is_codigoAutorizacion = StringUtils.getString(as_s);
	}

	/**
	 * Gets the codigo autorizacion.
	 *
	 * @return Obtiene el codigo de autorizacion
	 */
	public String getCodigoAutorizacion()
	{
		return is_codigoAutorizacion;
	}

	/**
	 * Asigna el nit de la empresa.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setCodigoComercio(String as_s)
	{
		is_codigoComercio = StringUtils.getString(as_s);
	}

	/**
	 * Gets the codigo comercio.
	 *
	 * @return Obtiene el nit de la empresa
	 */
	public String getCodigoComercio()
	{
		return is_codigoComercio;
	}

	/**
	 * Asigna el codigo de servicio.
	 *
	 * @param al_l Valor a asignar
	 */
	public void setCodigoServicio(Long al_l)
	{
		il_codigoServicio = al_l;
	}

	/**
	 * Gets the codigo servicio.
	 *
	 * @return Obtiene el codigo de servicio
	 */
	public Long getCodigoServicio()
	{
		return il_codigoServicio;
	}

	/**
	 * Asigna el valor CUS para el registro.
	 *
	 * @param al_l Valor a asignar
	 */
	public void setCus(Long al_l)
	{
		il_cus = al_l;
	}

	/**
	 * Gets the cus.
	 *
	 * @return Obtiene el valor CUS para el registro
	 */
	public Long getCus()
	{
		return il_cus;
	}

	/**
	 * Asigna la descripcion.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = StringUtils.getString(as_s);
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return Obtiene la descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Asigna el estado de la empresa.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setEstadoComercio(String as_s)
	{
		is_estadoComercio = StringUtils.getString(as_s);
	}

	/**
	 * Gets the estado comercio.
	 *
	 * @return Obtiene el estado de la empresa
	 */
	public String getEstadoComercio()
	{
		return is_estadoComercio;
	}

	/**
	 * Asigna la fecha de autorizacion.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaAutorizacion(Date ad_d)
	{
		id_fechaAutorizacion = ad_d;
	}

	/**
	 * Gets the fecha autorizacion.
	 *
	 * @return Obtiene la fecha de autorizacion
	 */
	public Date getFechaAutorizacion()
	{
		return id_fechaAutorizacion;
	}

	/**
	 * Asigna la fecha de creación.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaCreada(Date ad_d)
	{
		id_fechaCreada = ad_d;
	}

	/**
	 * Gets the fecha creada.
	 *
	 * @return Obtiene la fecha de creación
	 */
	public Date getFechaCreada()
	{
		return id_fechaCreada;
	}

	/**
	 * Asigna la fecha de resolucion de la transaccion.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaResolucion(Date ad_d)
	{
		id_fechaResolucion = ad_d;
	}

	/**
	 * Gets the fecha resolucion.
	 *
	 * @return Obtiene la fecha de resolucion de la transaccion
	 */
	public Date getFechaResolucion()
	{
		return id_fechaResolucion;
	}

	/**
	 * Asigna la fecha de ultimo estado.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setFechaUltimoEstado(Date ad_d)
	{
		id_fechaUltimoEstado = ad_d;
	}

	/**
	 * Gets the fecha ultimo estado.
	 *
	 * @return Obtiene la fecha de ultimo estado
	 */
	public Date getFechaUltimoEstado()
	{
		return id_fechaUltimoEstado;
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
	 * Asigna el Id de funcionalidad.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdFuncionalidad(String as_s)
	{
		is_idFuncionalidad = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id funcionalidad.
	 *
	 * @return Obtiene el Id de funcionalidad
	 */
	public String getIdFuncionalidad()
	{
		return is_idFuncionalidad;
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
	 * Asigna el Id de taquilla.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdTaquilla(String as_s)
	{
		is_idTaquilla = StringUtils.getString(as_s);
	}

	/**
	 * Gets the id taquilla.
	 *
	 * @return Obtiene el Id de taquilla
	 */
	public String getIdTaquilla()
	{
		return is_idTaquilla;
	}

	/**
	 * Asigna el valor de impuesto de la transacción.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setImpuesto(Double ad_d)
	{
		id_impuesto = ad_d;
	}

	/**
	 * Gets the impuesto.
	 *
	 * @return Obtiene el valor de impuesto de la transacción
	 */
	public Double getImpuesto()
	{
		return id_impuesto;
	}

	/**
	 * Asigna la modalidad de vinculacion.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setModalidadVinculacion(String as_s)
	{
		is_modalidadVinculacion = StringUtils.getString(as_s);
	}

	/**
	 * Gets the modalidad vinculacion.
	 *
	 * @return Obtiene la modalidad de vinculacion
	 */
	public String getModalidadVinculacion()
	{
		return is_modalidadVinculacion;
	}

	/**
	 * Asigna el Nit de servicio.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNitServicio(String as_s)
	{
		is_nitServicio = StringUtils.getString(as_s);
	}

	/**
	 * Gets the nit servicio.
	 *
	 * @return Obtiene el nit de servicio
	 */
	public String getNitServicio()
	{
		return is_nitServicio;
	}

	/**
	 * Asigna el nombre de la empresa.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreComercio(String as_s)
	{
		is_nombreComercio = StringUtils.getString(as_s);
	}

	/**
	 * Gets the nombre comercio.
	 *
	 * @return Obtiene el nombre de la empresa
	 */
	public String getNombreComercio()
	{
		return is_nombreComercio;
	}

	/**
	 * Asigna el nombre de funcionalidad.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreFuncionalidad(String as_s)
	{
		is_nombreFuncionalidad = StringUtils.getString(as_s);
	}

	/**
	 * Gets the nombre funcionalidad.
	 *
	 * @return Obtiene el nombre de funcionalidad
	 */
	public String getNombreFuncionalidad()
	{
		return is_nombreFuncionalidad;
	}

	/**
	 * Asigna el nombre de servicio.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreServicio(String as_s)
	{
		is_nombreServicio = StringUtils.getString(as_s);
	}

	/**
	 * Gets the nombre servicio.
	 *
	 * @return Obtiene el nombre de servicio
	 */
	public String getNombreServicio()
	{
		return is_nombreServicio;
	}

	/**
	 * Asigna el nombre de taquilla.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreTaquilla(String as_s)
	{
		is_nombreTaquilla = StringUtils.getString(as_s);
	}

	/**
	 * Gets the nombre taquilla.
	 *
	 * @return Obtiene el nombre de taquilla
	 */
	public String getNombreTaquilla()
	{
		return is_nombreTaquilla;
	}

	/**
	 * Asigna la referencia 1.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setReferencia1(String as_s)
	{
		is_referencia1 = StringUtils.getString(as_s);
	}

	/**
	 * Gets the referencia 1.
	 *
	 * @return Obtiene la referencia 1
	 */
	public String getReferencia1()
	{
		return is_referencia1;
	}

	/**
	 * Asigna la referencia 2.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setReferencia2(String as_s)
	{
		is_referencia2 = StringUtils.getString(as_s);
	}

	/**
	 * Gets the referencia 2.
	 *
	 * @return Obtiene la referencia 2
	 */
	public String getReferencia2()
	{
		return is_referencia2;
	}

	/**
	 * Asigna la referencia 3.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setReferencia3(String as_s)
	{
		is_referencia3 = StringUtils.getString(as_s);
	}

	/**
	 * Gets the referencia 3.
	 *
	 * @return Obtiene la referencia 3
	 */
	public String getReferencia3()
	{
		return is_referencia3;
	}

	/**
	 * Asigna el número de linea en el archivo de detalle.
	 *
	 * @param ai_i Valor a asignar
	 */
	public void setRegistro(int ai_i)
	{
		ii_registro = ai_i;
	}

	/**
	 * Gets the registro.
	 *
	 * @return Obtiene el número de linea en el archivo de detalle
	 */
	public int getRegistro()
	{
		return ii_registro;
	}

	/**
	 * Asigna el id de ticket de la transaccion.
	 *
	 * @param al_l Valor a asignar
	 */
	public void setTicketId(Long al_l)
	{
		il_ticketId = al_l;
	}

	/**
	 * Gets the ticket id.
	 *
	 * @return Obtiene el id de ticket de la transaccion
	 */
	public Long getTicketId()
	{
		return il_ticketId;
	}

	/**
	 * Asigna el tipo de autorizacion.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setTipoAutorizacion(String as_s)
	{
		is_tipoAutorizacion = StringUtils.getString(as_s);
	}

	/**
	 * Gets the tipo autorizacion.
	 *
	 * @return Obtiene el tipo de autorizacion
	 */
	public String getTipoAutorizacion()
	{
		return is_tipoAutorizacion;
	}

	/**
	 * Asigna el tipo de transaccion.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setTipoTransaccion(String as_s)
	{
		is_tipoTransaccion = StringUtils.getString(as_s);
	}

	/**
	 * Gets the tipo transaccion.
	 *
	 * @return Obtiene el tipo de transaccion
	 */
	public String getTipoTransaccion()
	{
		return is_tipoTransaccion;
	}

	/**
	 * Asigna el tipo de usuario.
	 *
	 * @param as_s Valor a asignar
	 */
	public void setTipoUsuario(String as_s)
	{
		is_tipoUsuario = StringUtils.getString(as_s);
	}

	/**
	 * Gets the tipo usuario.
	 *
	 * @return Obtiene el tipo de usuario
	 */
	public String getTipoUsuario()
	{
		return is_tipoUsuario;
	}

	/**
	 * Asigna el valor por el que se realizó la transacción.
	 *
	 * @param ad_d Valor a asignar
	 */
	public void setValor(Double ad_d)
	{
		id_valor = ad_d;
	}

	/**
	 * Gets the valor.
	 *
	 * @return Obtiene el valor por el que se realizó la transacción
	 */
	public Double getValor()
	{
		return id_valor;
	}
}
