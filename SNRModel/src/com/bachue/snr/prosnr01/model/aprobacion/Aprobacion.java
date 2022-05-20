package com.bachue.snr.prosnr01.model.aprobacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.CausalAprobacionDevolucion;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;


/**
 * Class que contiene todos las propiedades Aprobacion.
 *
 * @author lchacon
 */
public class Aprobacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6584649510570766977L;

	/** Propiedad icad causal devolucion. */
	private CausalAprobacionDevolucion icad_causalDevolucion;

	/** Propiedad ica data zip. */
	private Collection<Aprobacion> ica_dataZip;

	/** Propiedad ica datos bandeja. */
	private Collection<Aprobacion> ica_datosBandeja;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/** Propiedad il cantidad. */
	private Long il_cantidad;

	/** Propiedad il id etapa. */
	private Long il_idEtapa;

	/** Propiedad il id etapa anterior. */
	private Long il_idEtapaAnterior;

	/** Propiedad il id image. */
	private Long il_idImage;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad il id turno. */
	private String il_idTurno;

	/** Propiedad is estado actividad. */
	private String is_estadoActividad;

	/** Propiedad is firmar. */
	private String is_firmar;

	/** Propiedad is id motivos aprobacion ejecucion. */
	private String is_idMotivosAprobacionEjecucion;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id tipo decision recurso. */
	private String is_idTipoDecisionRecurso;

	/** Propiedad is matriculas relacionadas. */
	private String is_matriculasRelacionadas;

	/** Propiedad is motivo. */
	private String is_motivo;

	/** Propiedad is motivo no tramite. */
	private String is_motivoNoTramite;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre proceso. */
	private String is_nombreProceso;

	/** Propiedad is nombre subproceso. */
	private String is_nombreSubproceso;

	/** Propiedad is nombre tipo decision recurso. */
	private String is_nombreTipoDecisionRecurso;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is observaciones aprobador. */
	private String is_observacionesAprobador;

	/** Propiedad is observaciones proceso anterior. */
	private String is_observacionesProcesoAnterior;

	/** Propiedad is proceso. */
	private String is_proceso;

	/** Propiedad is tipificacion de turno. */
	private String is_tipificacionTurno;

	/** Propiedad is turnos asociados. */
	private String is_turnosAsociados;

	/** Propiedad is usuario etapa. */
	private String is_usuarioEtapa;

	/** Propiedad it turno. */
	private Turno it_turno;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad ib zip final. */
	private byte[] ib_zipFinal;

	/** Propiedad ab viene aprobacion apoyo nacional. */
	private boolean ab_vieneAprobacionApoyoNacional;

	/** Propiedad ib bloquear check. */
	private boolean ib_bloquearCheck;

	/** Propiedad ib_conMotivoNoTramite. */
	private boolean ib_conMotivoNoTramite;

	/** Propiedad ib consulta auto. */
	private boolean ib_consultaAuto;

	/** Propiedad ib consulta detalle. */
	private boolean ib_consultaDetalle;

	/** Propiedad ib devolucion antiguo sistema. */
	private boolean ib_devolucionAntiguoSistema;

	/** Propiedad ib recursos. */
	private boolean ib_esRecursos;

	/** Propiedad ib etapa anterior 100 O 110. */
	private boolean ib_etapaAnterior100O110;

	/** Propiedad ib etapa anterior 180. */
	private boolean ib_etapaAnterior180;

	/** Propiedad ib generar relacion. */
	private boolean ib_generarRelacion;

	/** Propiedad ib image valida. */
	private boolean ib_imageValida;

	/** Propiedad ib mostrar observaciones. */
	private boolean ib_mostrarObservaciones;

	/** Propiedad ib no es obligatorio recursos. */
	private boolean ib_noEsObligatorioRecursos;

	/** Propiedad ib preselección rechazo recursos. */
	private boolean ib_preseleccionRechazoRecursos;

	/** Propiedad ib proceso certificados. */
	private boolean ib_procesoCertificados;

	/** Propiedad ib proceso coordinador juridico. */
	private boolean ib_procesoCoordinadorJuridico;

	/** Propiedad ib proceso correcciones. */
	private boolean ib_procesoCorrecciones;

	/** Propiedad ib rojo tiempo vencimiento. */
	private boolean ib_rojoTiempoVencimiento;

	/** Propiedad ib viene de antiguo sistema. */
	private boolean ib_vieneDeAntiguoSistema;

	/** Propiedad ib viene de aprobacion. */
	private boolean ib_vieneDeAprobacion;

	/** Propiedad ib viene de autorizacion firma. */
	private boolean ib_vieneDeAutorizacionFirma;

	/** Propiedad ib viene de calificacion. */
	private boolean ib_vieneDeCalificacion;

	/** Propiedad ib viene de ejecutor traslados. */
	private boolean ib_vieneDeEjecutorTraslados;

	/** Propiedad ii item. */
	private int ii_item;

	/** Propiedad id tiempo vencimiento. */
	private long id_tiempoVencimiento;

	/** Propiedad il total bandeja. */
	private long il_totalBandeja;

	/**
	 * Modifica el valor de bloquear check.
	 *
	 * @param ab_b asigna el valor a la propiedad bloquear check
	 */
	public void setBloquearCheck(boolean ab_b)
	{
		ib_bloquearCheck = ab_b;
	}

	/**
	 * Valida la propiedad bloquear check.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en bloquear check
	 */
	public boolean isBloquearCheck()
	{
		return ib_bloquearCheck;
	}

	/**
	 * Modifica el valor de cantidad.
	 *
	 * @param al_l asigna el valor a la propiedad cantidad
	 */
	public void setCantidad(Long al_l)
	{
		il_cantidad = al_l;
	}

	/**
	 * Retorna el valor de cantidad.
	 *
	 * @return el valor de cantidad
	 */
	public Long getCantidad()
	{
		return il_cantidad;
	}

	/**
	 * Modifica el valor de causal devolucion.
	 *
	 * @param icad_cad asigna el valor a la propiedad causal devolucion
	 */
	public void setCausalDevolucion(CausalAprobacionDevolucion icad_cad)
	{
		icad_causalDevolucion = icad_cad;
	}

	/**
	 * Retorna el valor de causal devolucion.
	 *
	 * @return el valor de causal devolucion
	 */
	public CausalAprobacionDevolucion getCausalDevolucion()
	{
		return icad_causalDevolucion;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param ab_b modifica el valor de la propiedad.
	 */
	public void setConMotivoNoTramite(boolean ab_b)
	{
		ib_conMotivoNoTramite = ab_b;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean getConMotivoNoTramite()
	{
		return ib_conMotivoNoTramite;
	}

	/**
	 * Modifica el valor de consulta detalle.
	 *
	 * @param ab_b asigna el valor a la propiedad consulta detalle
	 */
	public void setConsultaDetalle(boolean ab_b)
	{
		ib_consultaDetalle = ab_b;
	}

	/**
	 * Valida la propiedad consulta detalle.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en consulta detalle
	 */
	public boolean isConsultaDetalle()
	{
		return ib_consultaDetalle;
	}

	/**
	 * Modifica el valor de data zip.
	 *
	 * @param aca_ca asigna el valor a la propiedad data zip
	 */
	public void setDataZip(Collection<Aprobacion> aca_ca)
	{
		ica_dataZip = aca_ca;
	}

	/**
	 * Retorna el valor de data zip.
	 *
	 * @return el valor de data zip
	 */
	public Collection<Aprobacion> getDataZip()
	{
		return ica_dataZip;
	}

	/**
	 * Modifica el valor de datos bandeja.
	 *
	 * @param aca_ca asigna el valor a la propiedad datos bandeja
	 */
	public void setDatosBandeja(Collection<Aprobacion> aca_ca)
	{
		ica_datosBandeja = aca_ca;
	}

	/**
	 * Retorna el valor de datos bandeja.
	 *
	 * @return el valor de datos bandeja
	 */
	public Collection<Aprobacion> getDatosBandeja()
	{
		return ica_datosBandeja;
	}

	/**
	 * Modifica el valor de devolucion antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad devolucion antiguo sistema
	 */
	public void setDevolucionAntiguoSistema(boolean ab_b)
	{
		ib_devolucionAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad devolucion antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en devolucion antiguo sistema
	 */
	public boolean isDevolucionAntiguoSistema()
	{
		return ib_devolucionAntiguoSistema;
	}

	/**
	 * Modifica el valor de EsRecursos.
	 *
	 * @param ab_b Modifica el valor de la propiedad esRecursos por ab_b
	 */
	public void setEsRecursos(boolean ab_b)
	{
		ib_esRecursos = ab_b;
	}

	/**
	 * Valida la propiedad es recursos.
	 *
	 * @return Retorna el valor de la propiedad esRecursos
	 */
	public boolean isEsRecursos()
	{
		return ib_esRecursos;
	}

	/**
	 * Modifica el valor de estado actividad.
	 *
	 * @param as_s asigna el valor a la propiedad estado actividad
	 */
	public void setEstadoActividad(String as_s)
	{
		is_estadoActividad = as_s;
	}

	/**
	 * Retorna el valor de estado actividad.
	 *
	 * @return el valor de estado actividad
	 */
	public String getEstadoActividad()
	{
		return is_estadoActividad;
	}

	/**
	 * Modifica el valor de etapa anterior 100 O 110.
	 *
	 * @param ab_b asigna el valor a la propiedad etapa anterior 100 O 110
	 */
	public void setEtapaAnterior100O110(boolean ab_b)
	{
		ib_etapaAnterior100O110 = ab_b;
	}

	/**
	 * Valida la propiedad etapa anterior 100 O 110.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en etapa anterior 100 O 110
	 */
	public boolean isEtapaAnterior100O110()
	{
		return ib_etapaAnterior100O110;
	}

	/**
	 * Modifica el valor de etapa anterior 180.
	 *
	 * @param ab_b asigna el valor a la propiedad etapa anterior 180
	 */
	public void setEtapaAnterior180(boolean ab_b)
	{
		ib_etapaAnterior180 = ab_b;
	}

	/**
	 * Valida la propiedad etapa anterior 180.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en etapa anterior 180
	 */
	public boolean isEtapaAnterior180()
	{
		return ib_etapaAnterior180;
	}

	/**
	 * @param id_fechaVencimiento Modifica el valor de la propiedad id_fechaVencimiento
	 */
	public void setFechaVencimiento(Date ad_fv)
	{
		id_fechaVencimiento = ad_fv;
	}

	/**
	 * @return Retorna el valor de la propiedad id_fechaVencimiento
	 */
	public Date getFechaVencimiento()
	{
		return id_fechaVencimiento;
	}

	/**
	 * Modifica el valor de firmar.
	 *
	 * @param as_s asigna el valor a la propiedad firmar
	 */
	public void setFirmar(String as_s)
	{
		is_firmar = as_s;
	}

	/**
	 * Retorna el valor de firmar.
	 *
	 * @return el valor de firmar
	 */
	public String getFirmar()
	{
		return is_firmar;
	}

	/**
	 * Modifica el valor de generar relacion.
	 *
	 * @param ab_b asigna el valor a la propiedad generar relacion
	 */
	public void setGenerarRelacion(boolean ab_b)
	{
		ib_generarRelacion = ab_b;
	}

	/**
	 * Valida la propiedad generar relacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en generar relacion
	 */
	public boolean isGenerarRelacion()
	{
		return ib_generarRelacion;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(Long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public Long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de id etapa anterior.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa anterior
	 */
	public void setIdEtapaAnterior(Long al_l)
	{
		il_idEtapaAnterior = al_l;
	}

	/**
	 * Retorna el valor de id etapa anterior.
	 *
	 * @return el valor de id etapa anterior
	 */
	public Long getIdEtapaAnterior()
	{
		return il_idEtapaAnterior;
	}

	/**
	 * Modifica el valor de id image.
	 *
	 * @param al_l asigna el valor a la propiedad id image
	 */
	public void setIdImage(Long al_l)
	{
		il_idImage = al_l;
	}

	/**
	 * Retorna el valor de id image.
	 *
	 * @return el valor de id image
	 */
	public Long getIdImage()
	{
		return il_idImage;
	}

	/**
	 * Modifica el valor de id proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id proceso
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna el valor de id proceso.
	 *
	 * @return el valor de id proceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdTipoDecisionRecurso.
	 *
	 * @param as_s Modifica el valor de la propiedad idTipoDecisionRecurso por as_s
	 */
	public void setIdTipoDecisionRecurso(String as_s)
	{
		is_idTipoDecisionRecurso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo decision recurso.
	 *
	 * @return Retorna el valor de la propiedad idTipoDecisionRecurso
	 */
	public String getIdTipoDecisionRecurso()
	{
		return is_idTipoDecisionRecurso;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		il_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return il_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de image valida.
	 *
	 * @param ab_b asigna el valor a la propiedad image valida
	 */
	public void setImageValida(boolean ab_b)
	{
		ib_imageValida = ab_b;
	}

	/**
	 * Valida la propiedad image valida.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en image valida
	 */
	public boolean isImageValida()
	{
		return ib_imageValida;
	}

	/**
	 * Modifica el valor de item.
	 *
	 * @param ai_i asigna el valor a la propiedad item
	 */
	public void setItem(int ai_i)
	{
		ii_item = ai_i;
	}

	/**
	 * Retorna el valor de item.
	 *
	 * @return el valor de item
	 */
	public int getItem()
	{
		return ii_item;
	}

	/**
	 * Modifica el valor de matriculas relacionadas.
	 *
	 * @param as_s asigna el valor a la propiedad matriculas relacionadas
	 */
	public void setMatriculasRelacionadas(String as_s)
	{
		is_matriculasRelacionadas = as_s;
	}

	/**
	 * Retorna el valor de matriculas relacionadas.
	 *
	 * @return el valor de matriculas relacionadas
	 */
	public String getMatriculasRelacionadas()
	{
		return is_matriculasRelacionadas;
	}

	/**
	 * Modifica el valor de mostrar observaciones.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar observaciones
	 */
	public void setMostrarObservaciones(boolean ab_b)
	{
		ib_mostrarObservaciones = ab_b;
	}

	/**
	 * Valida la propiedad mostrar observaciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar observaciones
	 */
	public boolean isMostrarObservaciones()
	{
		return ib_mostrarObservaciones;
	}

	/**
	 * Modifica el valor de motivo.
	 *
	 * @param as_s asigna el valor a la propiedad motivo
	 */
	public void setMotivo(String as_s)
	{
		is_motivo = as_s;
	}

	/**
	 * Retorna el valor de motivo.
	 *
	 * @return el valor de motivo
	 */
	public String getMotivo()
	{
		return is_motivo;
	}

	/**
	 * Modifica el valor de motivo no tramite.
	 *
	 * @param as_s asigna el valor a la propiedad motivo no tramite
	 */
	public void setMotivoNoTramite(String as_s)
	{
		is_motivoNoTramite = as_s;
	}

	/**
	 * Retorna el valor de motivo no tramite.
	 *
	 * @return el valor de motivo no tramite
	 */
	public String getMotivoNoTramite()
	{
		return is_motivoNoTramite;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NoEsObligatorioRecursos.
	 *
	 * @param ab_b Modifica el valor de la propiedad noEsObligatorioRecursos por ab_b
	 */
	public void setNoEsObligatorioRecursos(boolean ab_b)
	{
		ib_noEsObligatorioRecursos = ab_b;
	}

	/**
	 * Valida la propiedad no es obligatorio recursos.
	 *
	 * @return Retorna el valor de la propiedad noEsObligatorioRecursos
	 */
	public boolean isNoEsObligatorioRecursos()
	{
		return ib_noEsObligatorioRecursos;
	}

	/**
	 * Modifica el valor de nombre proceso.
	 *
	 * @param as_s asigna el valor a la propiedad nombre proceso
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Retorna el valor de nombre proceso.
	 *
	 * @return el valor de nombre proceso
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de nombre subproceso.
	 *
	 * @param as_s asigna el valor a la propiedad nombre subproceso
	 */
	public void setNombreSubproceso(String as_s)
	{
		is_nombreSubproceso = as_s;
	}

	/**
	 * Retorna el valor de nombre subproceso.
	 *
	 * @return el valor de nombre subproceso
	 */
	public String getNombreSubproceso()
	{
		return is_nombreSubproceso;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setNombreTipoDecisionRecurso(String as_s)
	{
		is_nombreTipoDecisionRecurso = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getNombreTipoDecisionRecurso()
	{
		return is_nombreTipoDecisionRecurso;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de observaciones aprobador.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones aprobador
	 */
	public void setObservacionesAprobador(String as_s)
	{
		is_observacionesAprobador = as_s;
	}

	/**
	 * Retorna el valor de observaciones aprobador.
	 *
	 * @return el valor de observaciones aprobador
	 */
	public String getObservacionesAprobador()
	{
		return is_observacionesAprobador;
	}

	/**
	 * Modifica el valor de observaciones proceso anterior.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones proceso anterior
	 */
	public void setObservacionesProcesoAnterior(String as_s)
	{
		is_observacionesProcesoAnterior = as_s;
	}

	/**
	 * Retorna el valor de observaciones proceso anterior.
	 *
	 * @return el valor de observaciones proceso anterior
	 */
	public String getObservacionesProcesoAnterior()
	{
		return is_observacionesProcesoAnterior;
	}

	/**
	 * Modifica el valor de PreseleccionRechazoRecursos.
	 *
	 * @param ab_b Modifica el valor de la propiedad preseleccionRechazoRecursos por ab_b
	 */
	public void setPreseleccionRechazoRecursos(boolean ab_b)
	{
		ib_preseleccionRechazoRecursos = ab_b;
	}

	/**
	 * Valida la propiedad preseleccion rechazo recursos.
	 *
	 * @return Retorna el valor de la propiedad preseleccionRechazoRecursos
	 */
	public boolean isPreseleccionRechazoRecursos()
	{
		return ib_preseleccionRechazoRecursos;
	}

	/**
	 * Modifica el valor de proceso.
	 *
	 * @param as_s asigna el valor a la propiedad proceso
	 */
	public void setProceso(String as_s)
	{
		is_proceso = as_s;
	}

	/**
	 * Retorna el valor de proceso.
	 *
	 * @return el valor de proceso
	 */
	public String getProceso()
	{
		return is_proceso;
	}

	/**
	 * Modifica el valor de proceso certificados.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso certificados
	 */
	public void setProcesoCertificados(boolean ab_b)
	{
		ib_procesoCertificados = ab_b;
	}

	/**
	 * Valida la propiedad proceso certificados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso certificados
	 */
	public boolean isProcesoCertificados()
	{
		return ib_procesoCertificados;
	}

	/**
	 * Modifica el valor de ProcesoCoordinadorJuridico.
	 *
	 * @param ab_b Modifica el valor de la propiedad procesoCoordinadorJuridico por ab_b
	 */
	public void setProcesoCoordinadorJuridico(boolean ab_b)
	{
		ib_procesoCoordinadorJuridico = ab_b;
	}

	/**
	 * Valida la propiedad proceso coordinador juridico.
	 *
	 * @return Retorna el valor de la propiedad procesoCoordinadorJuridico
	 */
	public boolean isProcesoCoordinadorJuridico()
	{
		return ib_procesoCoordinadorJuridico;
	}

	/**
	 * Modifica el valor de proceso correcciones.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso correcciones
	 */
	public void setProcesoCorrecciones(boolean ab_b)
	{
		ib_procesoCorrecciones = ab_b;
	}

	/**
	 * Valida la propiedad proceso correcciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso correcciones
	 */
	public boolean isProcesoCorrecciones()
	{
		return ib_procesoCorrecciones;
	}

	/**
	 * @param ib_rojoTiempoVencimiento Modifica el valor de la propiedad ib_rojoTiempoVencimiento
	 */
	public void setRojoTiempoVencimiento(boolean ab_rtv)
	{
		ib_rojoTiempoVencimiento = ab_rtv;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_rojoTiempoVencimiento
	 */
	public boolean isRojoTiempoVencimiento()
	{
		return ib_rojoTiempoVencimiento;
	}

	/**
	 * @param id_tiempoVencimiento Modifica el valor de la propiedad id_tiempoVencimiento
	 */
	public void setTiempoVencimiento(long al_tv)
	{
		id_tiempoVencimiento = al_tv;
	}

	/**
	 * @return Retorna el valor de la propiedad id_tiempoVencimiento
	 */
	public long getTiempoVencimiento()
	{
		return id_tiempoVencimiento;
	}

	/**
	 * Modifica el valor de tipificación de turno.
	 *
	 * @param as_s asigna el valor a la propiedad tipificación de turno.
	 */
	public void setTipificacionTurno(String as_s)
	{
		is_tipificacionTurno = as_s;
	}

	/**
	 * Retorna el valor de tipificación de turno.
	 *
	 * @return el valor de tipificación de turno
	 */
	public String getTipificacionTurno()
	{
		return is_tipificacionTurno;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(long ai_i)
	{
		il_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public long getTotalBandeja()
	{
		return il_totalBandeja;
	}

	/**
	 * Modifica el valor de turno.
	 *
	 * @param at_t asigna el valor a la propiedad turno
	 */
	public void setTurno(Turno at_t)
	{
		it_turno = at_t;
	}

	/**
	 * Retorna el valor de turno.
	 *
	 * @return el valor de turno
	 */
	public Turno getTurno()
	{
		if(it_turno == null)
			it_turno = new Turno();

		return it_turno;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param turnoHistoria asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria turnoHistoria)
	{
		ith_turnoHistoria = turnoHistoria;
	}

	/**
	 * Retorna el valor de turno historia.
	 *
	 * @return el valor de turno historia
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return ith_turnoHistoria;
	}

	/**
	 * Modifica el valor de turnos asociados.
	 *
	 * @param as_s asigna el valor a la propiedad turnos asociados
	 */
	public void setTurnosAsociados(String as_s)
	{
		is_turnosAsociados = as_s;
	}

	/**
	 * Retorna el valor de turnos asociados.
	 *
	 * @return el valor de turnos asociados
	 */
	public String getTurnosAsociados()
	{
		return is_turnosAsociados;
	}

	/**
	 * Modifica el valor de usuario etapa.
	 *
	 * @param as_s asigna el valor a la propiedad usuario etapa
	 */
	public void setUsuarioEtapa(String as_s)
	{
		is_usuarioEtapa = as_s;
	}

	/**
	 * Retorna el valor de usuario etapa.
	 *
	 * @return el valor de usuario etapa
	 */
	public String getUsuarioEtapa()
	{
		return is_usuarioEtapa;
	}

	/**
	 * Modifica el valor de VieneAprobacionApoyoNacional.
	 *
	 * @param ab_b Modifica el valor de la propiedad ab_b
	 */
	public void setVieneAprobacionApoyoNacional(boolean ab_b)
	{
		ab_vieneAprobacionApoyoNacional = ab_b;
	}

	/**
	 * Valida la propiedad viene aprobacion apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad vieneAprobacionApoyoNacional
	 */
	public boolean isVieneAprobacionApoyoNacional()
	{
		return ab_vieneAprobacionApoyoNacional;
	}

	/**
	 * Modifica el valor de viene de antiguo sistema.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de antiguo sistema
	 */
	public void setVieneDeAntiguoSistema(boolean ab_b)
	{
		ib_vieneDeAntiguoSistema = ab_b;
	}

	/**
	 * Valida la propiedad viene de antiguo sistema.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de antiguo sistema
	 */
	public boolean isVieneDeAntiguoSistema()
	{
		return ib_vieneDeAntiguoSistema;
	}

	/**
	 * Modifica el valor de viene de aprobacion.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de aprobacion
	 */
	public void setVieneDeAprobacion(boolean ab_b)
	{
		ib_vieneDeAprobacion = ab_b;
	}

	/**
	 * Valida la propiedad viene de aprobacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de aprobacion
	 */
	public boolean isVieneDeAprobacion()
	{
		return ib_vieneDeAprobacion;
	}

	/**
	 * Modifica el valor de VieneDeAutorizacionFirma.
	 *
	 * @param ab_b Modifica el valor de la propiedad vieneDeAutorizacionFirma por vieneDeAutorizacionFirma
	 */
	public void setVieneDeAutorizacionFirma(boolean ab_b)
	{
		ib_vieneDeAutorizacionFirma = ab_b;
	}

	/**
	 * Valida la propiedad viene de autorizacion firma.
	 *
	 * @return Retorna el valor de la propiedad vieneDeAutorizacionFirma
	 */
	public boolean isVieneDeAutorizacionFirma()
	{
		return ib_vieneDeAutorizacionFirma;
	}

	/**
	 * Modifica el valor de viene de calificacion.
	 *
	 * @param ab_b asigna el valor a la propiedad viene de calificacion
	 */
	public void setVieneDeCalificacion(boolean ab_b)
	{
		ib_vieneDeCalificacion = ab_b;
	}

	/**
	 * Valida la propiedad viene de calificacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en viene de calificacion
	 */
	public boolean isVieneDeCalificacion()
	{
		return ib_vieneDeCalificacion;
	}

	/**
	 * Modifica el valor de VieneDeEjecutorTraslados.
	 *
	 * @param ab_vieneDeEjecutorTraslados Modifica el valor de la propiedad vieneDeEjecutorTraslados por vieneDeEjecutorTraslados
	 */
	public void setVieneDeEjecutorTraslados(boolean ab_vieneDeEjecutorTraslados)
	{
		ib_vieneDeEjecutorTraslados = ab_vieneDeEjecutorTraslados;
	}

	/**
	 * Valida la propiedad viene de ejecutor traslados.
	 *
	 * @return Retorna el valor de la propiedad vieneDeEjecutorTraslados
	 */
	public boolean isVieneDeEjecutorTraslados()
	{
		return ib_vieneDeEjecutorTraslados;
	}

	/**
	 * Modifica el valor de zip final.
	 *
	 * @param ab_b asigna el valor a la propiedad zip final
	 */
	public void setZipFinal(byte[] ab_b)
	{
		ib_zipFinal = ab_b;
	}

	/**
	 * Retorna el valor de zip final.
	 *
	 * @return el valor de zip final
	 */
	public byte[] getZipFinal()
	{
		return ib_zipFinal;
	}

	/**
	 * Retorna Objeto o variable de valor id motivos aprobacion ejecucion.
	 *
	 * @return el valor de id motivos aprobacion ejecucion
	 */
	public String getIdMotivosAprobacionEjecucion()
	{
		return is_idMotivosAprobacionEjecucion;
	}

	/**
	 * Modifica el valor de IdMotivosAprobacionEjecucion.
	 *
	 * @param as_s de as s
	 */
	public void setIdMotivosAprobacionEjecucion(String as_s)
	{
		is_idMotivosAprobacionEjecucion = as_s;
	}

	/**
	 * Valida la propiedad consulta auto.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en consulta auto
	 */
	public boolean isConsultaAuto()
	{
		return ib_consultaAuto;
	}

	/**
	 * Modifica el valor de ConsultaAuto.
	 *
	 * @param ab_b de ab b
	 */
	public void setConsultaAuto(boolean ab_b)
	{
		ib_consultaAuto = ab_b;
	}
}
