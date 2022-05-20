package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.IntentoEnvioJob;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.io.Serializable;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_TURNO.
 *
 * @author mblanco
 */
public class Turno extends IntentoEnvioJob implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3304132459068249375L;

	/** Propiedad ibd consecutivo turno. */
	private BigDecimal ibd_consecutivoTurno;

	/** Propiedad ibd consistencia wf. */
	private BigDecimal ibd_consistenciaWf;

	/** Propiedad ibd equivalencia turnos. */
	private BigDecimal ibd_equivalenciaTurnos;

	/** Propiedad ibd error. */
	private BigDecimal ibd_error;

	/** Propiedad ibd id nturno. */
	private BigDecimal ibd_idNturno;

	/** Propiedad ibd id usuario. */
	private BigDecimal ibd_idUsuario;

	/** Propiedad ibd id usuario anulacion. */
	private BigDecimal ibd_idUsuarioAnulacion;

	/** Propiedad ibd last id historia. */
	private BigDecimal ibd_lastIdHistoria;

	/** Propiedad ibd last id nota. */
	private BigDecimal ibd_lastIdNota;

	/** Propiedad ibd modo bloqueo. */
	private BigDecimal ibd_modoBloqueo;

	/** Propiedad ibd nanio. */
	private BigDecimal ibd_nanio;

	/** Propiedad ibd revocatoria. */
	private BigDecimal ibd_revocatoria;

	/** Propiedad ibi cantidad matriculas. */
	private BigInteger ibi_cantidadMatriculas;

	/** Propiedad ibi secuencia. */
	private BigInteger ibi_secuencia;

	/** Propiedad ictd tipo documental. */
	private Collection<TipoDocumental> ictd_tipoDocumental;

	/** Propiedad id fecha acuse recibo aviso. */
	private Date id_fechaAcuseReciboAviso;

	/** Propiedad id fecha creacion. */
	private Date id_fechaCreacion;

	/** Propiedad id fecha fin. */
	private Date id_fechaFin;

	/** Propiedad id fecha inicio. */
	private Date id_fechaInicio;

	/** Propiedad id fecha modificacion. */
	private Date id_fechaModificacion;

	/** Propiedad id fecha notificacion. */
	private Date id_fechaNotificacion;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/** Propiedad il id etapa actual. */
	private Long il_idEtapaActual;

	/** Propiedad il termino. */
	private Long il_termino;

	/** Propiedad is acto principal. */
	private String is_actoPrincipal;

	/** Propiedad is_idActosApoyoNacional. */
	private String is_actosApoyoNacional;

	/** Propiedad is anio. */
	private String is_anio;

	/** Propiedad is anulado. */
	private String is_anulado;

	/** Propiedad is crear turno. */
	private String is_crearTurno;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is digitalizado. */
	private String is_digitalizado;

	/** Propiedad is expediente. */
	private String is_expediente;

	/** Propiedad is expediente SI. */
	private String is_expedienteSI;

	/** Propiedad is fecha creacion string. */
	private String is_fechaCreacionString;

	/** Propiedad is firma justificacion. */
	private String is_firmaJustificacion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id circulos Y matriculas. */
	private String is_idCirculosYMatriculas;

	/** Propiedad is id datos ant sistema. */
	private String is_idDatosAntSistema;

	/** Propiedad is id documento. */
	private String is_idDocumento;

	/** Propiedad is id fase. */
	private String is_idFase;

	/** Propiedad is id fase relacion. */
	private String is_idFaseRelacion;

	/** Propiedad is id liquidacion. */
	private String is_idLiquidacion;

	/** Propiedad is id matricula certificado. */
	private String is_idMatriculaCertificado;

	/** Propiedad is id matricula ultima. */
	private String is_idMatriculaUltima;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id sub proceso. */
	private String is_idSubProceso;

	/** Propiedad is id tipo notificacion. */
	private String is_idTipoNotificacion;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno ant. */
	private String is_idTurnoAnt;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is id unidad tiempo. */
	private String is_idUnidadTiempo;

	/** Propiedad is id workflow. */
	private String is_idWorkflow;

	/** Propiedad is info documento. */
	private String is_infoDocumento;

	/** Propiedad is interviene entrega. */
	private String is_intervieneEntrega;

	/** Propiedad is linea produccion. */
	private String is_lineaProduccion;

	/** Propiedad is_idMatriculasApoyoNacional. */
	private String is_matriculasApoyoNacional;

	/** Propiedad is migrado. */
	private String is_migrado;

	/** Propiedad is nacional. */
	private String is_nacional;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nombre etapa actual. */
	private String is_nombreEtapaActual;

	/** Propiedad is nombre oficina origen. */
	private String is_nombreOficinaOrigen;

	/** Propiedad is nombre proceso. */
	private String is_nombreProceso;

	/** Propiedad is nombre tipo expediente. */
	private String is_nombreTipoExpediente;

	/** Propiedad is nombre tipo expediente SI. */
	private String is_nombreTipoExpedienteSI;

	/** Propiedad is nomemclatura expediente AA. */
	private String is_nomemclaturaExpedienteAA;

	/** Propiedad is nomemclatura expediente SI. */
	private String is_nomemclaturaExpedienteSI;

	/** Propiedad is nroradica. */
	private String is_nroradica;

	/** Propiedad is observaciones anulacion. */
	private String is_observacionesAnulacion;

	/** Propiedad is observaciones habilitar. */
	private String is_observacionesHabilitar;

	/** Propiedad is reasignado especial. */
	private String is_reasignadoEspecial;

	/** Propiedad is relacion. */
	private String is_relacion;

	/** Propiedad is_solicitudApoyo. */
	private String is_solicitudApoyo;

	/** Propiedad is tipo expediente. */
	private String is_tipoExpediente;

	/** Propiedad is tipo expediente SI. */
	private String is_tipoExpedienteSI;

	/** Propiedad is tipos acto por circulo. */
	private String is_tiposActoPorCirculo;

	/** Propiedad is ultima respuesta. */
	private String is_ultimaRespuesta;

	/** Propiedad is usuario ant. */
	private String is_usuarioAnt;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad ib detalle prohibiciones. */
	private boolean ib_detalleProhibiciones;

	/** Propiedad ib detalle recepcion documentos. */
	private boolean ib_detalleRecepcionDocumentos;

	/** Propiedad ib es bloquear tipos doc. */
	private boolean ib_esBloquearTiposDoc;

	/** Propiedad ib folio. */
	private boolean ib_folio;

	/** Propiedad ib ind vinculado. */
	private boolean ib_indVinculado;
	
	/** Propiedad ib ind prohibicion. */
	private boolean ib_indProhibicion;

	/** Propiedad ib sir. */
	private boolean ib_sir;

	/** Propiedad ib turno seleccionado. */
	private boolean ib_turnoSeleccionado;

	/** Propiedad ib turno suspension con etapa TER. */
	private boolean ib_turnoSuspensionConEtapaTER;

	/** Propiedad ib turno tipo acto permuta. */
	private boolean ib_turnoTipoActoPermuta;

	/** Propiedad ib validar turno. */
	private boolean ib_validarTurno;

	/** Propiedad id tiempo vencimiento. */
	private long id_tiempoVencimiento;

	/** Propiedad il id etapa ant. */
	private long il_idEtapaAnt;

	/** Propiedad il id etapa menu. */
	private long il_idEtapaMenu;

	/** Propiedad is consecutivo liquidacion. */
	private long is_consecutivoLiquidacion;

	/**
	 * Constructor que recibe como parametro el turno.
	 *
	 * @param as_turno de as turno
	 */
	public Turno(String as_turno)
	{
		setIdTurno(as_turno);
	}

	/**
	 * Constructor que recibe como parametro el id de la solicitud y el id del proceso.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idProceso de as id proceso
	 */
	public Turno(String as_idSolicitud, String as_idProceso)
	{
		setIdSolicitud(as_idSolicitud);
		setIdProceso(as_idProceso);
	}

	/**
	 * Constructor por defecto.
	 */
	public Turno()
	{
	}

	/**
	 * Modifica el valor de ActoPrincipal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActoPrincipal(String as_s)
	{
		is_actoPrincipal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor acto principal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActoPrincipal()
	{
		return is_actoPrincipal;
	}

	/**
	 * Modifica el valor de ActosApoyoNacional.
	 *
	 * @param as_s Modifica el valor de la propiedad actosApoyoNacional
	 */
	public void setActosApoyoNacional(String as_s)
	{
		is_actosApoyoNacional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor actos apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad actosApoyoNacional
	 */
	public String getActosApoyoNacional()
	{
		return is_actosApoyoNacional;
	}

	/**
	 * Modifica el valor de actosui.
	 *
	 * @param atd_tipoDocumental asigna el valor a la propiedad actosui
	 */
	public void setActosui(Collection<TipoDocumental> atd_tipoDocumental)
	{
		ictd_tipoDocumental = atd_tipoDocumental;
	}

	/**
	 * Retorna el valor de actosui.
	 *
	 * @return el valor de actosui
	 */
	public Collection<TipoDocumental> getActosui()
	{
		return ictd_tipoDocumental;
	}

	/**
	 * Modifica el valor de Anio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnio(String as_s)
	{
		is_anio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnio()
	{
		return is_anio;
	}

	/**
	 * Modifica el valor de Anulado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnulado(String as_s)
	{
		is_anulado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor anulado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnulado()
	{
		return is_anulado;
	}

	/**
	 * Modifica el valor de CantidadMatriculas.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setCantidadMatriculas(BigInteger abi_bi)
	{
		ibi_cantidadMatriculas = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getCantidadMatriculas()
	{
		return ibi_cantidadMatriculas;
	}

	/**
	 * Modifica el valor de ConsecutivoLiquidacion.
	 *
	 * @param al_l de al l
	 */
	public void setConsecutivoLiquidacion(long al_l)
	{
		is_consecutivoLiquidacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo liquidacion.
	 *
	 * @return Retorna el valor de la propiedad consecutivoLiquidacion
	 */
	public long getConsecutivoLiquidacion()
	{
		return is_consecutivoLiquidacion;
	}

	/**
	 * Modifica el valor de ConsecutivoTurno.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setConsecutivoTurno(BigDecimal abd_bd)
	{
		ibd_consecutivoTurno = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getConsecutivoTurno()
	{
		return ibd_consecutivoTurno;
	}

	/**
	 * Modifica el valor de ConsistenciaWf.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setConsistenciaWf(BigDecimal abd_bd)
	{
		ibd_consistenciaWf = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor consistencia wf.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getConsistenciaWf()
	{
		return ibd_consistenciaWf;
	}

	/**
	 * Modifica el valor de CrearTurno.
	 *
	 * @param as_s de as s
	 */
	public void setCrearTurno(String as_s)
	{
		is_crearTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor crear turno.
	 *
	 * @return Retorna el valor de la propiedad crearTurno
	 */
	public String getCrearTurno()
	{
		return is_crearTurno;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de DetalleRecepcionDocumentos.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDetalleRecepcionDocumentos(boolean ab_b)
	{
		ib_detalleRecepcionDocumentos = ab_b;
	}

	/**
	 * Valida la propiedad detalle recepcion documentos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDetalleRecepcionDocumentos()
	{
		return ib_detalleRecepcionDocumentos;
	}

	/**
	 * Modifica el valor de Digitalizado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDigitalizado(String as_s)
	{
		is_digitalizado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor digitalizado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDigitalizado()
	{
		return is_digitalizado;
	}

	/**
	 * Modifica el valor de EquivalenciaTurnos.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setEquivalenciaTurnos(BigDecimal abd_bd)
	{
		ibd_equivalenciaTurnos = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor equivalencia turnos.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getEquivalenciaTurnos()
	{
		return ibd_equivalenciaTurnos;
	}

	/**
	 * Modifica el valor de Error.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setError(BigDecimal abd_bd)
	{
		ibd_error = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor error.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getError()
	{
		return ibd_error;
	}

	/**
	 * Modifica el valor de EsBloquearTiposDoc.
	 *
	 * @param ab_b de ab b
	 */
	public void setEsBloquearTiposDoc(boolean ab_b)
	{
		ib_esBloquearTiposDoc = ab_b;
	}

	/**
	 * Valida la propiedad es bloquear tipos doc.
	 *
	 * @return Retorna el valor de la propiedad esBloquearTiposDoc
	 */
	public boolean isEsBloquearTiposDoc()
	{
		return ib_esBloquearTiposDoc;
	}

	/**
	 * Modifica el valor de Expediente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setExpediente(String as_s)
	{
		is_expediente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor expediente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getExpediente()
	{
		return is_expediente;
	}

	/**
	 * Modifica el valor de Expediente SI.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setExpedienteSI(String as_s)
	{
		is_expedienteSI = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor expediente SI.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getExpedienteSI()
	{
		return is_expedienteSI;
	}

	/**
	 * Modifica el valor de FechaAcuseReciboAviso.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaAcuseReciboAviso(Date ad_d)
	{
		id_fechaAcuseReciboAviso = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha acuse recibo aviso.
	 *
	 * @return el valor de fecha acuse recibo aviso
	 */
	public Date getFechaAcuseReciboAviso()
	{
		return id_fechaAcuseReciboAviso;
	}

	/**
	 * Modifica el valor de FechaCreacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaCreacion(Date ad_d)
	{
		id_fechaCreacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaCreacion()
	{
		return id_fechaCreacion;
	}

	/**
	 * Modifica el valor de FechaCreacionString.
	 *
	 * @param as_s de as s
	 */
	public void setFechaCreacionString(String as_s)
	{
		is_fechaCreacionString = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion string.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFechaCreacionString()
	{
		return is_fechaCreacionString;
	}

	/**
	 * Modifica el valor de FechaFin.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaFin(Date ad_d)
	{
		id_fechaFin = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha fin.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaFin()
	{
		return id_fechaFin;
	}

	/**
	 * Modifica el valor de FechaInicio.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaInicio(Date ad_d)
	{
		id_fechaInicio = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha inicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaInicio()
	{
		return id_fechaInicio;
	}

	/**
	 * Modifica el valor de FechaModificacion.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaModificacion(Date ad_d)
	{
		id_fechaModificacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha modificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaModificacion()
	{
		return id_fechaModificacion;
	}

	/**
	 * Modifica el valor de FechaNotificacion.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaNotificacion(Date ad_d)
	{
		id_fechaNotificacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha notificacion.
	 *
	 * @return el valor de fecha notificacion
	 */
	public Date getFechaNotificacion()
	{
		return id_fechaNotificacion;
	}

	/**
	 * Modifica el valor de FechaVencimiento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaVencimiento(Date ad_d)
	{
		id_fechaVencimiento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha vencimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}

	/**
	 * Modifica el valor de FirmaJustificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setFirmaJustificacion(String as_s)
	{
		is_firmaJustificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor firma justificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getFirmaJustificacion()
	{
		return is_firmaJustificacion;
	}

	/**
	 * Modifica el valor de Folio.
	 *
	 * @param ab_b de ab b
	 */
	public void setFolio(boolean ab_b)
	{
		ib_folio = ab_b;
	}

	/**
	 * Valida la propiedad folio.
	 *
	 * @return Retorna el valor de la propiedad folio
	 */
	public boolean isFolio()
	{
		return ib_folio;
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
	 * Modifica el valor de IdCirculosYMatriculas.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculosYMatriculas(String as_s)
	{
		is_idCirculosYMatriculas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulos Y matriculas.
	 *
	 * @return Retorna el valor de la propiedad idCirculosYMatriculas
	 */
	public String getIdCirculosYMatriculas()
	{
		return is_idCirculosYMatriculas;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de IdEtapaActual.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapaActual(Long al_l)
	{
		il_idEtapaActual = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa actual.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdEtapaActual()
	{
		return il_idEtapaActual;
	}

	/**
	 * Modifica el valor de IdEtapaAnt.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapaAnt(long al_l)
	{
		il_idEtapaAnt = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapaAnt()
	{
		return il_idEtapaAnt;
	}

	/**
	 * Modifica el valor de IdEtapaMenu.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapaMenu(long al_l)
	{
		il_idEtapaMenu = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa menu.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapaMenu()
	{
		return il_idEtapaMenu;
	}

	/**
	 * Modifica el valor de IdFase.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdFase(String as_s)
	{
		is_idFase = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id fase.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdFase()
	{
		return is_idFase;
	}

	/**
	 * Modifica el valor de IdFaseRelacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdFaseRelacion(String as_s)
	{
		is_idFaseRelacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id fase relacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdFaseRelacion()
	{
		return is_idFaseRelacion;
	}

	/**
	 * Modifica el valor de IdLiquidacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdLiquidacion(String as_s)
	{
		is_idLiquidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id liquidacion.
	 *
	 * @return Retorna el valor de la propiedad idLiquidacion
	 */
	public String getIdLiquidacion()
	{
		return is_idLiquidacion;
	}

	/**
	 * Modifica el valor de IdMatriculaCertificado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMatriculaCertificado(String as_s)
	{
		is_idMatriculaCertificado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMatriculaCertificado()
	{
		return is_idMatriculaCertificado;
	}

	/**
	 * Modifica el valor de IdMatriculaUltima.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMatriculaUltima(String as_s)
	{
		is_idMatriculaUltima = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula ultima.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMatriculaUltima()
	{
		return is_idMatriculaUltima;
	}

	/**
	 * Modifica el valor de IdNturno.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdNturno(BigDecimal abd_bd)
	{
		ibd_idNturno = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdNturno()
	{
		return ibd_idNturno;
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
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IdTipoNotificacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoNotificacion(String as_s)
	{
		is_idTipoNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo notificacion.
	 *
	 * @return Retorna el valor de la propiedad idTipoNotificacion
	 */
	public String getIdTipoNotificacion()
	{
		return is_idTipoNotificacion;
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
	 * Modifica el valor de IdTurnoAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurnoAnt(String as_s)
	{
		is_idTurnoAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurnoAnt()
	{
		return is_idTurnoAnt;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param idTurnoHistoria Modifica el valor de la propiedad idTurnoHistoria
	 */
	public void setIdTurnoHistoria(String idTurnoHistoria)
	{
		is_idTurnoHistoria = idTurnoHistoria;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return Retorna el valor de la propiedad idTurnoHistoria
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de IdUnidadTiempo.
	 *
	 * @param as_s de as s
	 */
	public void setIdUnidadTiempo(String as_s)
	{
		is_idUnidadTiempo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id unidad tiempo.
	 *
	 * @return el valor de id unidad tiempo
	 */
	public String getIdUnidadTiempo()
	{
		return is_idUnidadTiempo;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdUsuario(BigDecimal abd_bd)
	{
		ibd_idUsuario = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdUsuario()
	{
		return ibd_idUsuario;
	}

	/**
	 * Modifica el valor de IdUsuarioAnulacion.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdUsuarioAnulacion(BigDecimal abd_bd)
	{
		ibd_idUsuarioAnulacion = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario anulacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdUsuarioAnulacion()
	{
		return ibd_idUsuarioAnulacion;
	}

	/**
	 * Modifica el valor de IdWorkflow.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdWorkflow(String as_s)
	{
		is_idWorkflow = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id workflow.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdWorkflow()
	{
		return is_idWorkflow;
	}

	/**
	 * Modifica el valor de IndVinculado.
	 *
	 * @param indVinculado asigna el valor a la propiedad
	 */
	public void setIndVinculado(boolean indVinculado)
	{
		ib_indVinculado = indVinculado;
	}

	/**
	 * Valida la propiedad ind vinculado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isIndVinculado()
	{
		return ib_indVinculado;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_indProhibicion
	 */
	public boolean isIndProhibicion() {
		return ib_indProhibicion;
	}

	/**
	 * @param ib_indProhibicion Modifica el valor de la propiedad ib_indProhibicion
	 */
	public void setIndProhibicion(boolean ib_indProhibicion) {
		this.ib_indProhibicion = ib_indProhibicion;
	}

	/**
	 * Modifica el valor de InfoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setInfoDocumento(String as_s)
	{
		is_infoDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor info documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getInfoDocumento()
	{
		return is_infoDocumento;
	}

	/**
	 * Modifica el valor de IntervieneEntrega.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIntervieneEntrega(String as_s)
	{
		is_intervieneEntrega = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor interviene entrega.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIntervieneEntrega()
	{
		return is_intervieneEntrega;
	}

	/**
	 * Modifica el valor de LastIdHistoria.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setLastIdHistoria(BigDecimal abd_bd)
	{
		ibd_lastIdHistoria = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor last id historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getLastIdHistoria()
	{
		return ibd_lastIdHistoria;
	}

	/**
	 * Modifica el valor de LastIdNota.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setLastIdNota(BigDecimal abd_bd)
	{
		ibd_lastIdNota = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor last id nota.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getLastIdNota()
	{
		return ibd_lastIdNota;
	}

	/**
	 * Modifica el valor de LineaProduccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLineaProduccion(String as_s)
	{
		is_lineaProduccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor linea produccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLineaProduccion()
	{
		return is_lineaProduccion;
	}

	/**
	 * Modifica el valor de MatriculasApoyoNacional.
	 *
	 * @param as_s Modifica el valor de la propiedad idMatriculasApoyoNacional
	 */
	public void setMatriculasApoyoNacional(String as_s)
	{
		is_matriculasApoyoNacional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad idMatriculasApoyoNacional
	 */
	public String getMatriculasApoyoNacional()
	{
		return is_matriculasApoyoNacional;
	}

	/**
	 * Modifica el valor de Migrado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMigrado(String as_s)
	{
		is_migrado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor migrado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMigrado()
	{
		return is_migrado;
	}

	/**
	 * Modifica el valor de ModoBloqueo.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setModoBloqueo(BigDecimal abd_bd)
	{
		ibd_modoBloqueo = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor modo bloqueo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getModoBloqueo()
	{
		return ibd_modoBloqueo;
	}

	/**
	 * Modifica el valor de Nacional.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNacional(String as_s)
	{
		is_nacional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nacional.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNacional()
	{
		return is_nacional;
	}

	/**
	 * Modifica el valor de Nanio.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setNanio(BigDecimal abd_bd)
	{
		ibd_nanio = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor nanio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getNanio()
	{
		return ibd_nanio;
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
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
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
	 * Modifica el valor de NombreEtapaActual.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreEtapaActual(String as_s)
	{
		is_nombreEtapaActual = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre etapa actual.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreEtapaActual()
	{
		return is_nombreEtapaActual;
	}

	/**
	 * Modifica el valor de NombreOficinaOrigen.
	 *
	 * @param as_s Modifica el valor de la propiedad nombreOficinaOrigen
	 */
	public void setNombreOficinaOrigen(String as_s)
	{
		is_nombreOficinaOrigen = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre oficina origen.
	 *
	 * @return Retorna el valor de la propiedad nombreOficinaOrigen
	 */
	public String getNombreOficinaOrigen()
	{
		return is_nombreOficinaOrigen;
	}

	/**
	 * Modifica el valor de NombreProceso.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de NombreTipoExpediente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTipoExpediente(String as_s)
	{
		is_nombreTipoExpediente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo expediente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoExpediente()
	{
		return is_nombreTipoExpediente;
	}

	/**
	 * Modifica el valor de NombreTipoExpediente SI.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTipoExpedienteSI(String as_s)
	{
		is_nombreTipoExpedienteSI = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo expediente SI.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoExpedienteSI()
	{
		return is_nombreTipoExpedienteSI;
	}

	/**
	 * Modifica el valor de actosui.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNomemclaturaExpedienteAA(String as_s)
	{
		is_nomemclaturaExpedienteAA = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nomemclatura expediente AA.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNomemclaturaExpedienteAA()
	{
		return is_nomemclaturaExpedienteAA;
	}

	/**
	 * Modifica el valor de nomenclatura expediente SI.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNomemclaturaExpedienteSI(String as_s)
	{
		is_nomemclaturaExpedienteSI = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nomemclatura expediente SI.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNomemclaturaExpedienteSI()
	{
		return is_nomemclaturaExpedienteSI;
	}

	/**
	 * Modifica el valor de Nroradica.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNroradica(String as_s)
	{
		is_nroradica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nroradica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNroradica()
	{
		return is_nroradica;
	}

	/**
	 * Modifica el valor de ObservacionesAnulacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesAnulacion(String as_s)
	{
		is_observacionesAnulacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones anulacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesAnulacion()
	{
		return is_observacionesAnulacion;
	}

	/**
	 * Modifica el valor de ObservacionesHabilitar.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesHabilitar(String as_s)
	{
		is_observacionesHabilitar = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones habilitar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesHabilitar()
	{
		return is_observacionesHabilitar;
	}

	/**
	 * Modifica el valor de ReasignadoEspecial.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setReasignadoEspecial(String as_s)
	{
		is_reasignadoEspecial = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor reasignado especial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getReasignadoEspecial()
	{
		return is_reasignadoEspecial;
	}

	/**
	 * Modifica el valor de Relacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRelacion(String as_s)
	{
		is_relacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor relacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRelacion()
	{
		return is_relacion;
	}

	/**
	 * Modifica el valor de Revocatoria.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setRevocatoria(BigDecimal abd_bd)
	{
		ibd_revocatoria = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor revocatoria.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getRevocatoria()
	{
		return ibd_revocatoria;
	}

	/**
	 * Modifica el valor de Secuencia.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setSecuencia(BigInteger abi_bi)
	{
		ibi_secuencia = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor secuencia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getSecuencia()
	{
		return ibi_secuencia;
	}

	/**
	 * Modifica el valor de Sir.
	 *
	 * @param ab_b de ab b
	 */
	public void setSir(boolean ab_b)
	{
		ib_sir = ab_b;
	}

	/**
	 * Valida la propiedad sir.
	 *
	 * @return Retorna el valor de la propiedad sir
	 */
	public boolean isSir()
	{
		return ib_sir;
	}

	/**
	 * Modifica el valor de SolicitudApoyo.
	 *
	 * @param as_s Modifica el valor de la propiedad solicitudApoyo
	 */
	public void setSolicitudApoyo(String as_s)
	{
		is_solicitudApoyo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud apoyo.
	 *
	 * @return Retorna el valor de la propiedad solicitudApoyo
	 */
	public String getSolicitudApoyo()
	{
		return is_solicitudApoyo;
	}

	/**
	 * Modifica el valor de Termino.
	 *
	 * @param al_l de al l
	 */
	public void setTermino(Long al_l)
	{
		il_termino = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor termino.
	 *
	 * @return Retorna el valor de la propiedad termino
	 */
	public Long getTermino()
	{
		return il_termino;
	}

	/**
	 * Modifica el valor de TiempoVencimiento.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setTiempoVencimiento(long al_l)
	{
		id_tiempoVencimiento = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor tiempo vencimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getTiempoVencimiento()
	{
		return id_tiempoVencimiento;
	}

	/**
	 * Modifica el valor de TipoExpediente.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoExpediente(String as_s)
	{
		is_tipoExpediente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo expediente.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoExpediente()
	{
		return is_tipoExpediente;
	}

	/**
	 * Modifica el valor de Tipo Expediente SI.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoExpedienteSI(String as_s)
	{
		is_tipoExpedienteSI = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo expediente SI.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoExpedienteSI()
	{
		return is_tipoExpedienteSI;
	}

	/**
	 * Modifica el valor de TiposActoPorCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setTiposActoPorCirculo(String as_s)
	{
		is_tiposActoPorCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipos acto por circulo.
	 *
	 * @return Retorna el valor de la propiedad tiposActoPorCirculo
	 */
	public String getTiposActoPorCirculo()
	{
		return is_tiposActoPorCirculo;
	}

	/**
	 * Modifica el valor de TurnoHistoria.
	 *
	 * @param ath_th asigna el valor a la propiedad
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
	}

	/**
	 * Retorna Objeto o variable de valor turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}

	/**
	 * Modifica el valor de TurnoSeleccionado.
	 *
	 * @param ab_B asigna el valor a la propiedad
	 */
	public void setTurnoSeleccionado(boolean ab_B)
	{
		ib_turnoSeleccionado = ab_B;
	}

	/**
	 * Valida la propiedad turno seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTurnoSeleccionado()
	{
		return ib_turnoSeleccionado;
	}

	/**
	 * Modifica el valor de TurnoSuspensionConEtapaTER.
	 *
	 * @param ab_B asigna el valor a la propiedad
	 */
	public void setTurnoSuspensionConEtapaTER(boolean ab_B)
	{
		ib_turnoSuspensionConEtapaTER = ab_B;
	}

	/**
	 * Valida la propiedad turno suspension con etapa TER.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTurnoSuspensionConEtapaTER()
	{
		return ib_turnoSuspensionConEtapaTER;
	}

	/**
	 * Modifica el valor de TurnoTipoActoPermuta.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setTurnoTipoActoPermuta(boolean ab_b)
	{
		ib_turnoTipoActoPermuta = ab_b;
	}

	/**
	 * Valida la propiedad turno tipo acto permuta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isTurnoTipoActoPermuta()
	{
		return ib_turnoTipoActoPermuta;
	}

	/**
	 * Modifica el valor de UltimaRespuesta.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUltimaRespuesta(String as_s)
	{
		is_ultimaRespuesta = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor ultima respuesta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUltimaRespuesta()
	{
		return is_ultimaRespuesta;
	}

	/**
	 * Modifica el valor de UsuarioAnt.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuarioAnt(String as_s)
	{
		is_usuarioAnt = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario ant.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuarioAnt()
	{
		return is_usuarioAnt;
	}

	/**
	 * Modifica el valor de ValidarTurno.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setValidarTurno(boolean ab_b)
	{
		ib_validarTurno = ab_b;
	}

	/**
	 * Valida la propiedad validar turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isValidarTurno()
	{
		return ib_validarTurno;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_detalleProhibiciones
	 */
	public boolean isDetalleProhibiciones()
	{
		return ib_detalleProhibiciones;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad ib_detalleProhibiciones
	 */
	public void setDetalleProhibiciones(boolean ab_b)
	{
		ib_detalleProhibiciones = ab_b;
	}
}
