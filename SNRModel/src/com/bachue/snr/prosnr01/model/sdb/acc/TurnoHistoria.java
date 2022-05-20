package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.IntentoEnvioJob;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TURNO_HISTORIA.
 *
 * @author garias
 */
public class TurnoHistoria extends IntentoEnvioJob implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3546591376469773389L;

	/** Propiedad ibd id anterior. */
	private BigDecimal ibd_idAnterior;

	/** Propiedad ibd id etapa. */
	private BigDecimal ibd_idEtapa;

	/** Propiedad id fecha desfijacion aviso. */
	private Date id_fechaDesfijacionAviso;

	/** Propiedad id fecha documento. */
	private Date id_fechaDocumento;

	/** Propiedad id fecha final. */
	private Date id_fechaFinal;

	/** Propiedad id fecha inicial. */
	private Date id_fechaInicial;

	/** Propiedad id fecha publicacion aviso web. */
	private Date id_fechaPublicacionAvisoWeb;

	/** Propiedad id fecha reparto. */
	private Date id_fechaReparto;

	/** Propiedad id fecha respuesta mensaje. */
	private Date id_fechaRespuestaMensaje;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/** Propiedad ii turno hist. */
	private Integer ii_turnoHist;

	/** Propiedad il id motivo. */
	private Long il_idMotivo;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad il lversion subproceso. */
	private Long il_lversionSubproceso;

	/** Propiedad il motivo tramite. */
	private Long il_motivoTramite;

	/** Propiedad il numero doc. */
	private Long il_numeroDoc;

	/** Propiedad il resultado avance. */
	private Long il_resultadoAvance;

	/** Propiedad ip persona. */
	private Persona ip_persona;

	/** Propiedad is calificacion. */
	private String is_calificacion;

	/** Propiedad is consecutivo oficio antiguo sistema. */
	private String is_consecutivoOficioAntiguoSistema;

	/** Propiedad is consecutivo resolucion antiguo sistema. */
	private String is_consecutivoResolucionAntiguoSistema;

	/** Propiedad is consideraciones antiguo sistema. */
	private String is_consideracionesAntiguoSistema;

	/** Propiedad is descripcion respuesta. */
	private String is_descripcionRespuesta;

	/** Propiedad is destinatario. */
	private String is_destinatario;

	/** Propiedad is estado actividad. */
	private String is_estadoActividad;

	/** Propiedad is generar relacion. */
	private String is_generarRelacion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id circulo usuario. */
	private String is_idCirculoUsuario;

	/** Propiedad is id documento salida. */
	private String is_idDocumentoSalida;

	/** Propiedad is id mensaje. */
	private String is_idMensaje;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno hijo. */
	private String is_idTurnoHijo;

	/** Propiedad is id usuario. */
	private String is_idUsuario;

	/** Propiedad is id usuario asignacion. */
	private String is_idUsuarioAsignacion;

	/** Propiedad is indicador devolucion. */
	private String is_indicadorDevolucion;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is medio publicacion. */
	private String is_medioPublicacion;

	/** Propiedad is motivo. */
	private String is_motivo;

	/** Propiedad is motivo no tramite. */
	private String is_motivoNoTramite;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nombre proceso. */
	private String is_nombreProceso;

	/** Propiedad is nombre subproceso. */
	private String is_nombreSubproceso;

	/** Propiedad is nombre tipo doc. */
	private String is_nombreTipoDoc;

	/** Propiedad is numero relacion. */
	private String is_numeroRelacion;

	/** Propiedad is observacion avance. */
	private String is_observacionAvance;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is observaciones no tramite. */
	private String is_observacionesNoTramite;

	/** Propiedad is observaciones proceso anterior. */
	private String is_observacionesProcesoAnterior;

	/** Propiedad is oficina origen. */
	private String is_oficinaOrigen;

	/** Propiedad is renuncia terminos. */
	private String is_renunciaTerminos;

	/** Propiedad is responsable publicacion. */
	private String is_responsablePublicacion;

	/** Propiedad is secuencia documentos. */
	private String is_secuenciaDocumentos;

	/** Propiedad is usuario asignado. */
	private String is_usuarioAsignado;

	/** Propiedad nombre etapa. */
	private String nombreEtapa;

	/** Propiedad ib detalle rep constancia. */
	private boolean ib_detalleRepConstancia;

	/** Propiedad ib documento generado. */
	private boolean ib_documentoGenerado;

	/** Propiedad ib esHomologarActosLiquidacion. */
	private boolean ib_esHomologarActosLiquidacion;

	/** Propiedad ib esTurnoMigrado. */
	private boolean ib_esTurnoMigrado;

	/** Propiedad ib fecha etapa valida. */
	private boolean ib_fechaEtapaValida;

	/** Propiedad ib fecha fijacion valida. */
	private boolean ib_fechaFijacionNoValida;

	/** Propiedad ib invocar procedimiento gobernaciones. */
	private boolean ib_invocarProcedimientoGobernaciones;

	/** Propiedad ib invocar procedimiento gobernaciones. */
	private boolean ib_noGuardarNotaExcesoDePago;

	/** Propiedad ib_notaExcesoCalificacion. */
	private boolean ib_notaExcesoCalificacion;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ii codigo calificacion. */
	private int ii_codigoCalificacion;

	/** Propiedad ii secuencia imagenes. */
	private int ii_secuenciaImagenes;

	/** Propiedad ii tamano image. */
	private int ii_tamanoImage;

	/** Propiedad il id etapa salvar. */
	private long il_idEtapaSalvar;

	/** Propiedad il id motivo salvar. */
	private long il_idMotivoSalvar;

	/**
	 * Constructor por defecto.
	 */
	public TurnoHistoria()
	{
	}

	/**
	 * Constructor que recibe como parametro el id del turno.
	 *
	 * @param as_idTurno id del turno
	 */
	public TurnoHistoria(String as_idTurno)
	{
		setIdTurno(as_idTurno);
	}

	/**
	 * Constructor que recibe como parametro el id del turno y el id de la etapa.
	 *
	 * @param as_idTurno id del turno
	 * @param al_idEtapa id de la etapa
	 */
	public TurnoHistoria(String as_idTurno, long al_idEtapa)
	{
		setIdTurno(as_idTurno);
		setIdEtapa(BigDecimal.valueOf(al_idEtapa));
	}

	/**
	 * Constructor que recibe como parametro el id de la solicitud y el id de la etapa.
	 *
	 * @param as_idSolicitud id de la solicitud
	 * @param al_idEtapa id de la etapa
	 */
	public TurnoHistoria(String as_idSolicitud, BigDecimal al_idEtapa)
	{
		setIdSolicitud(as_idSolicitud);
		setIdEtapa(al_idEtapa);
	}

	/**
	 * Instancia un nuevo objeto turno historia.
	 *
	 * @param as_idSolicitud correspondiente al valor de as id solicitud
	 * @param as_idUsuario correspondiente al valor de as id usuario
	 * @param as_ipRemote correspondiente al valor de as ip remote
	 */
	public TurnoHistoria(String as_idSolicitud, String as_idUsuario, String as_ipRemote)
	{
		setIdSolicitud(as_idSolicitud);
		setIdUsuarioModificacion(as_idUsuario);
		setIpModificacion(as_ipRemote);
	}

	/**
	 * Constructor  que recibe el id turno anterior.
	 *
	 * @param abd_idAnterior con el id anterior del turno historia
	 */
	public TurnoHistoria(BigDecimal abd_idAnterior)
	{
		this(abd_idAnterior, false);
	}

	/**
	 * Constructor que recibe como parametro el id del turno anterior.
	 *
	 * @param abd_idAnterior de abd id anterior
	 * @param ab_b de ab b
	 */
	public TurnoHistoria(BigDecimal abd_idAnterior, boolean ab_b)
	{
		if(!ab_b)
			setIdTurnoHistoria(NumericUtils.getLongWrapper(abd_idAnterior));
		else
			ibd_idAnterior = abd_idAnterior;
	}

	/**
	 * Constructor que recibe como parametro el id del turno.
	 *
	 * @param al_idTurnoHistoria id del turno
	 */
	public TurnoHistoria(Long al_idTurnoHistoria)
	{
		setIdTurnoHistoria(al_idTurnoHistoria);
	}

	/**
	 * Modifica el valor de Calificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCalificacion(String as_s)
	{
		is_calificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor calificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCalificacion()
	{
		return is_calificacion;
	}

	/**
	 * Modifica el valor de CodigoCalificacion.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setCodigoCalificacion(int ai_i)
	{
		ii_codigoCalificacion = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor codigo calificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getCodigoCalificacion()
	{
		return ii_codigoCalificacion;
	}

	/**
	 * Modifica el valor de ConsecutivoOficioAntiguoSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConsecutivoOficioAntiguoSistema(String as_s)
	{
		is_consecutivoOficioAntiguoSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo oficio antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsecutivoOficioAntiguoSistema()
	{
		return is_consecutivoOficioAntiguoSistema;
	}

	/**
	 * Modifica el valor de ConsecutivoResolucionAntiguoSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConsecutivoResolucionAntiguoSistema(String as_s)
	{
		is_consecutivoResolucionAntiguoSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo resolucion antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsecutivoResolucionAntiguoSistema()
	{
		return is_consecutivoResolucionAntiguoSistema;
	}

	/**
	 * Modifica el valor de ConsideracionesAntiguoSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setConsideracionesAntiguoSistema(String as_s)
	{
		is_consideracionesAntiguoSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consideraciones antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getConsideracionesAntiguoSistema()
	{
		return is_consideracionesAntiguoSistema;
	}

	/**
	 * Modifica el valor de DescripcionRespuesta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionRespuesta(String as_s)
	{
		is_descripcionRespuesta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion respuesta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionRespuesta()
	{
		return is_descripcionRespuesta;
	}

	/**
	 * Modifica el valor de Destinatario.
	 *
	 * @param destinatario de destinatario
	 */
	public void setDestinatario(String destinatario)
	{
		is_destinatario = destinatario;
	}

	/**
	 * Retorna Objeto o variable de valor destinatario.
	 *
	 * @return Retorna el valor de la propiedad destinatario
	 */
	public String getDestinatario()
	{
		return is_destinatario;
	}

	/**
	 * Modifica el valor de DetalleRepConstancia.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDetalleRepConstancia(boolean ab_b)
	{
		ib_detalleRepConstancia = ab_b;
	}

	/**
	 * Valida la propiedad detalle rep constancia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDetalleRepConstancia()
	{
		return ib_detalleRepConstancia;
	}

	/**
	 * Modifica el valor de DocumentoGenerado.
	 *
	 * @param ab_ab Modifica el valor de la propiedad documentoGenerado
	 */
	public void setDocumentoGenerado(boolean ab_ab)
	{
		ib_documentoGenerado = ab_ab;
	}

	/**
	 * Valida la propiedad documento generado.
	 *
	 * @return Retorna el valor de la propiedad documentoGenerado
	 */
	public boolean isDocumentoGenerado()
	{
		return ib_documentoGenerado;
	}

	/**
	 * Modifica el valor de EsHomologarActosLiquidacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsHomologarActosLiquidacion(boolean ab_b)
	{
		ib_esHomologarActosLiquidacion = ab_b;
	}

	/**
	 * Valida la propiedad es homologar actos liquidacion.
	 *
	 * @return Retorna el valor de la propiedad esHomologarActosLiquidacion
	 */
	public boolean isEsHomologarActosLiquidacion()
	{
		return ib_esHomologarActosLiquidacion;
	}

	/**
	 * Modifica el valor de EsTurnoMigrado.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsTurnoMigrado(boolean ab_b)
	{
		ib_esTurnoMigrado = ab_b;
	}

	/**
	 * Valida la propiedad es turno migrado.
	 *
	 * @return Retorna el valor de la propiedad esTurnoMigrado
	 */
	public boolean isEsTurnoMigrado()
	{
		return ib_esTurnoMigrado;
	}

	/**
	 * Modifica el valor de EstadoActividad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoActividad(String as_s)
	{
		is_estadoActividad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado actividad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoActividad()
	{
		return is_estadoActividad;
	}

	/**
	 * Modifica el valor de FechaDesfijacionAviso.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaDesfijacionAviso(Date ad_d)
	{
		id_fechaDesfijacionAviso = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desfijacion aviso.
	 *
	 * @return Retorna el valor de la propiedad fechaDesfijacionAviso
	 */
	public Date getFechaDesfijacionAviso()
	{
		return id_fechaDesfijacionAviso;
	}

	/**
	 * Modifica el valor de FechaDocumento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * Modifica el valor de FechaEtapaValida.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setFechaEtapaValida(boolean ab_b)
	{
		ib_fechaEtapaValida = ab_b;
	}

	/**
	 * Valida la propiedad fecha etapa valida.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isFechaEtapaValida()
	{
		return ib_fechaEtapaValida;
	}

	/**
	 * Modifica el valor de FechaFijacionValida.
	 *
	 * @param ab_b de ab b
	 */
	public void setFechaFijacionNoValida(boolean ab_b)
	{
		ib_fechaFijacionNoValida = ab_b;
	}

	/**
	 * Valida la propiedad fecha fijacion valida.
	 *
	 * @return Retorna el valor de la propiedad fechaFijacionValida
	 */
	public boolean isFechaFijacionNoValida()
	{
		return ib_fechaFijacionNoValida;
	}

	/**
	 * Modifica el valor de FechaFinal.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaFinal(Date ad_d)
	{
		id_fechaFinal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha final.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaFinal()
	{
		return id_fechaFinal;
	}

	/**
	 * Modifica el valor de FechaInicial.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaInicial(Date ad_d)
	{
		id_fechaInicial = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaInicial()
	{
		return id_fechaInicial;
	}

	/**
	 * Modifica el valor de FechaPublicacionAvisoWeb.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaPublicacionAvisoWeb(Date ad_d)
	{
		id_fechaPublicacionAvisoWeb = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha publicacion aviso web.
	 *
	 * @return Retorna el valor de la propiedad fechaPublicacionAvisoWeb
	 */
	public Date getFechaPublicacionAvisoWeb()
	{
		return id_fechaPublicacionAvisoWeb;
	}

	/**
	 * Modifica el valor de FechaReparto.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaReparto(Date ad_d)
	{
		id_fechaReparto = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha reparto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaReparto()
	{
		return id_fechaReparto;
	}

	/**
	 * Modifica el valor de FechaRespuestaMensaje.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaRespuestaMensaje(Date ad_d)
	{
		id_fechaRespuestaMensaje = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha respuesta mensaje.
	 *
	 * @return Retorna el valor de la propiedad fechaRespuestaMensaje
	 */
	public Date getFechaRespuestaMensaje()
	{
		return id_fechaRespuestaMensaje;
	}

	/**
	 * Modifica el valor de FechaVencimiento.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		id_fechaVencimiento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha vencimiento.
	 *
	 * @return Retorna el valor de la propiedad fechaVencimiento
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}

	/**
	 * Modifica el valor de GenerarRelacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setGenerarRelacion(String as_s)
	{
		is_generarRelacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor generar relacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGenerarRelacion()
	{
		return is_generarRelacion;
	}

	/**
	 * Modifica el valor de IdAnterior.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdAnterior(BigDecimal abd_bd)
	{
		ibd_idAnterior = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id anterior.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdAnterior()
	{
		return ibd_idAnterior;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculoUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoUsuario(String as_s)
	{
		is_idCirculoUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoUsuario()
	{
		return is_idCirculoUsuario;
	}

	/**
	 * Modifica el valor de IdDocumentoSalida.
	 *
	 * @param as_s de as s
	 */
	public void setIdDocumentoSalida(String as_s)
	{
		is_idDocumentoSalida = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento salida.
	 *
	 * @return el valor de id documento salida
	 */
	public String getIdDocumentoSalida()
	{
		return is_idDocumentoSalida;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdEtapa(BigDecimal abd_bd)
	{
		ibd_idEtapa = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdEtapa()
	{
		return ibd_idEtapa;
	}

	/**
	 * Modifica el valor de IdEtapaSalvar.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapaSalvar(long al_l)
	{
		il_idEtapaSalvar = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa salvar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapaSalvar()
	{
		return il_idEtapaSalvar;
	}

	/**
	 * Modifica el valor de IdMensaje.
	 *
	 * @param as_s de as s
	 */
	public void setIdMensaje(String as_s)
	{
		is_idMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje.
	 *
	 * @return el valor de id mensaje
	 */
	public String getIdMensaje()
	{
		return is_idMensaje;
	}

	/**
	 * Modifica el valor de IdMotivo.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMotivo(Long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id motivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Modifica el valor de IdMotivoSalvar.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMotivoSalvar(long al_l)
	{
		il_idMotivoSalvar = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id motivo salvar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMotivoSalvar()
	{
		return il_idMotivoSalvar;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s the idSubproceso to set
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return the idSubproceso
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHijo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoHijo(String as_s)
	{
		is_idTurnoHijo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno hijo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoHijo()
	{
		return is_idTurnoHijo;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}

	/**
	 * Modifica el valor de IdUsuarioAsignacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuarioAsignacion(String as_s)
	{
		is_idUsuarioAsignacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario asignacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuarioAsignacion()
	{
		return is_idUsuarioAsignacion;
	}

	/**
	 * Modifica el valor de IndicadorDevolucion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorDevolucion(String as_s)
	{
		is_indicadorDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador devolucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorDevolucion()
	{
		return is_indicadorDevolucion;
	}

	/**
	 * Modifica el valor de InvocarProcedimientoGobernaciones.
	 *
	 * @param ab_b de ab b
	 */
	public void setInvocarProcedimientoGobernaciones(boolean ab_b)
	{
		ib_invocarProcedimientoGobernaciones = ab_b;
	}

	/**
	 * Valida la propiedad invocar procedimiento gobernaciones.
	 *
	 * @return Retorna el valor de la propiedad invocarProcedimientoGobernaciones
	 */
	public boolean isInvocarProcedimientoGobernaciones()
	{
		return ib_invocarProcedimientoGobernaciones;
	}

	/**
	 * Modifica el valor de Justificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setJustificacion(String as_s)
	{
		is_justificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor justificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getJustificacion()
	{
		return is_justificacion;
	}

	/**
	 * Modifica el valor de MedioPublicacion.
	 *
	 * @param as_s de as s
	 */
	public void setMedioPublicacion(String as_s)
	{
		is_medioPublicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor medio publicacion.
	 *
	 * @return el valor de medio publicacion
	 */
	public String getMedioPublicacion()
	{
		return is_medioPublicacion;
	}

	/**
	 * Modifica el valor de Motivo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMotivo(String as_s)
	{
		is_motivo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivo()
	{
		return is_motivo;
	}

	/**
	 * Modifica el valor de MotivoNoTramite.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMotivoNoTramite(String as_s)
	{
		is_motivoNoTramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo no tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivoNoTramite()
	{
		return is_motivoNoTramite;
	}

	/**
	 * Modifica el valor de MotivoTramite.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setMotivoTramite(Long al_l)
	{
		il_motivoTramite = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor motivo tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getMotivoTramite()
	{
		return il_motivoTramite;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NoGuardarNotaExcesoDePago.
	 *
	 * @param ab_b de ab b
	 */
	public void setNoGuardarNotaExcesoDePago(boolean ab_b)
	{
		ib_noGuardarNotaExcesoDePago = ab_b;
	}

	/**
	 * Valida la propiedad no guardar nota exceso de pago.
	 *
	 * @return Retorna el valor de la propiedad noGuardarNotaExcesoDePago
	 */
	public boolean isNoGuardarNotaExcesoDePago()
	{
		return ib_noGuardarNotaExcesoDePago;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreEtapa.
	 *
	 * @param nombreEtapa Modifica el valor de la propiedad nombreEtapa
	 */
	public void setNombreEtapa(String nombreEtapa)
	{
		this.nombreEtapa = nombreEtapa;
	}

	/**
	 * Retorna Objeto o variable de valor nombre etapa.
	 *
	 * @return Retorna el valor de la propiedad nombreEtapa
	 */
	public String getNombreEtapa()
	{
		return nombreEtapa;
	}

	/**
	 * Modifica el valor de NombreProceso.
	 *
	 * @param nombreProceso de nombre proceso
	 */
	public void setNombreProceso(String nombreProceso)
	{
		is_nombreProceso = nombreProceso;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proceso.
	 *
	 * @return Retorna el valor de la propiedad nombreProceso
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de NombreSubproceso.
	 *
	 * @param nombreSubproceso de nombre subproceso
	 */
	public void setNombreSubproceso(String nombreSubproceso)
	{
		is_nombreSubproceso = nombreSubproceso;
	}

	/**
	 * Retorna Objeto o variable de valor nombre subproceso.
	 *
	 * @return Retorna el valor de la propiedad nombreSubproceso
	 */
	public String getNombreSubproceso()
	{
		return is_nombreSubproceso;
	}

	/**
	 * Modifica el valor de NombreTipoDoc.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTipoDoc(String as_s)
	{
		is_nombreTipoDoc = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo doc.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoDoc()
	{
		return is_nombreTipoDoc;
	}

	/**
	 * Modifica el valor de NotaExcesoCalificacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setNotaExcesoCalificacion(boolean ab_b)
	{
		ib_notaExcesoCalificacion = ab_b;
	}

	/**
	 * Valida la propiedad nota exceso calificacion.
	 *
	 * @return Retorna el valor de la propiedad notaExcesoCalificacion
	 */
	public boolean isNotaExcesoCalificacion()
	{
		return ib_notaExcesoCalificacion;
	}

	/**
	 * Modifica el valor de NumeroDoc.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setNumeroDoc(Long al_l)
	{
		il_numeroDoc = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor numero doc.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getNumeroDoc()
	{
		return il_numeroDoc;
	}

	/**
	 * Modifica el valor de NumeroRelacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroRelacion(String as_s)
	{
		is_numeroRelacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero relacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroRelacion()
	{
		return is_numeroRelacion;
	}

	/**
	 * Modifica el valor de ObservacionAvance.
	 *
	 * @param as_s the observacionAvance to set
	 */
	public void setObservacionAvance(String as_s)
	{
		is_observacionAvance = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observacion avance.
	 *
	 * @return the observacionAvance
	 */
	public String getObservacionAvance()
	{
		return is_observacionAvance;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de ObservacionesNoTramite.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesNoTramite(String as_s)
	{
		is_observacionesNoTramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones no tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesNoTramite()
	{
		return is_observacionesNoTramite;
	}

	/**
	 * Modifica el valor de ObservacionesProcesoAnterior.
	 *
	 * @param as_s de as s
	 */
	public void setObservacionesProcesoAnterior(String as_s)
	{
		is_observacionesProcesoAnterior = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones proceso anterior.
	 *
	 * @return Retorna el valor de la propiedad observacionesProcesoAnterior
	 */
	public String getObservacionesProcesoAnterior()
	{
		return is_observacionesProcesoAnterior;
	}

	/**
	 * Modifica el valor de OficinaOrigen.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setOficinaOrigen(String as_s)
	{
		is_oficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor oficina origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getOficinaOrigen()
	{
		return is_oficinaOrigen;
	}

	/**
	 * Modifica el valor de Persona.
	 *
	 * @param ap_p asigna el valor a la propiedad
	 */
	public void setPersona(Persona ap_p)
	{
		ip_persona = ap_p;
	}

	/**
	 * Retorna Objeto o variable de valor persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Persona getPersona()
	{
		return ip_persona;
	}

	/**
	 * Modifica el valor de RenunciaTerminos.
	 *
	 * @param as_s de as s
	 */
	public void setRenunciaTerminos(String as_s)
	{
		is_renunciaTerminos = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor renuncia terminos.
	 *
	 * @return Retorna el valor de la propiedad renunciaTerminos
	 */
	public String getRenunciaTerminos()
	{
		return is_renunciaTerminos;
	}

	/**
	 * Modifica el valor de ResponsablePublicacion.
	 *
	 * @param as_s Modifica el valor de la propiedad responsablePublicacion
	 */
	public void setResponsablePublicacion(String as_s)
	{
		is_responsablePublicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor responsable publicacion.
	 *
	 * @return Retorna el valor de la propiedad responsablePublicacion
	 */
	public String getResponsablePublicacion()
	{
		return is_responsablePublicacion;
	}

	/**
	 * Modifica el valor de ResultadoAvance.
	 *
	 * @param al_l the resultadoAvance to set
	 */
	public void setResultadoAvance(Long al_l)
	{
		il_resultadoAvance = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor resultado avance.
	 *
	 * @return the resultadoAvance
	 */
	public Long getResultadoAvance()
	{
		return il_resultadoAvance;
	}

	/**
	 * Modifica el valor de SecuenciaDocumentos.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setSecuenciaDocumentos(String as_s)
	{
		is_secuenciaDocumentos = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor secuencia documentos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSecuenciaDocumentos()
	{
		return is_secuenciaDocumentos;
	}

	/**
	 * Modifica el valor de SecuenciaImagenes.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setSecuenciaImagenes(int ai_i)
	{
		ii_secuenciaImagenes = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor secuencia imagenes.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getSecuenciaImagenes()
	{
		return ii_secuenciaImagenes;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b de ab b
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return Retorna el valor de la propiedad seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de TamanoImage.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setTamanoImage(int ai_i)
	{
		ii_tamanoImage = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor tamano image.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getTamanoImage()
	{
		return ii_tamanoImage;
	}

	/**
	 * Modifica el valor de TurnoHist.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setTurnoHist(Integer ai_i)
	{
		ii_turnoHist = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor turno hist.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getTurnoHist()
	{
		return ii_turnoHist;
	}

	/**
	 * Modifica el valor de UsuarioAsignado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuarioAsignado(String as_s)
	{
		is_usuarioAsignado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario asignado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuarioAsignado()
	{
		return is_usuarioAsignado;
	}

	/**
	 * Modifica el valor de VersionSubproceso.
	 *
	 * @param al_l the versionSubproceso to set
	 */
	public void setVersionSubproceso(Long al_l)
	{
		il_lversionSubproceso = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version subproceso.
	 *
	 * @return the versionSubproceso
	 */
	public Long getVersionSubproceso()
	{
		return il_lversionSubproceso;
	}
}
