package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;


/**
 * Class que contiene todos las propiedades TramiteCantidad.
 *
 * @author ccalderon
 */
public class TramiteCantidad extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -953874867994612692L;

	/** Propiedad ictc turnos. */
	private Collection<TramiteCantidad> ictc_turnos;

	/** Propiedad ictc turnos desistimiento. */
	private Collection<TramiteCantidad> ictc_turnosDesistimiento;

	/** Propiedad ictc turnos suspendidos. */
	private Collection<TramiteCantidad> ictc_turnosProhibicion;

	/** Propiedad ictc turnos suspendidos. */
	private Collection<TramiteCantidad> ictc_turnosSuspendidos;

	/** Propiedad ictc turnos vinculados. */
	private Collection<TramiteCantidad> ictc_turnosVinculados;

	/** Propiedad id fecha creacion. */
	private Date id_fechaCreacion;

	/** Propiedad id fecha reparto. */
	private Date id_fechaReparto;

	/** Propiedad id fecha vencimiento. */
	private Date id_fechaVencimiento;

	/** Propiedad ii cantidad. */
	private Integer ii_cantidad;

	/** Propiedad il id etapa. */
	private Long il_idEtapa;

	/** Propiedad il idmotivo. */
	private Long il_idmotivo;

	/** Propiedad is calificacion. */
	private String is_calificacion;

	/** Propiedad is documento. */
	private String is_documento;

	/** Propiedad is estado actividad. */
	private String is_estadoActividad;

	/** Propiedad is estado actual turno. */
	private String is_estadoActualTurno;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id sub proceso. */
	private String is_idSubProceso;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is matriculas relacionadas. */
	private String is_matriculasRelacionadas;

	/** Propiedad is motivo. */
	private String is_motivo;

	/** Propiedad is motivo no tramite. */
	private String is_motivoNoTramite;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre proceso. */
	private String is_nombreProceso;

	/** Propiedad is nombre sub proceso. */
	private String is_nombreSubProceso;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is observaciones respuesta. */
	private String is_observacionesRespuesta;

	/** Propiedad is tipificacion del turno. */
	private String is_tipificacionTurno;

	/** Propiedad is tramite vinculado. */
	private String is_tramiteVinculado;

	/** Propiedad is turnos asociados. */
	private String is_turnosAsociados;

	/** Propiedad is turnos relacionados. */
	private String is_turnosRelacionados;

	/** Propiedad is urlDigitalizacion. */
	private String is_urlDigitalizacion;

	/** Propiedad is usuario. */
	private String is_usuario;

	/** Propiedad ib homologado. */
	private boolean ib_homologado;

	/** Propiedad ib migrado. */
	private boolean ib_migrado;

	/** Propiedad ib rojo tiempo vencimiento. */
	private boolean ib_rojoTiempoVencimiento;

	/** Propiedad id tiempo vencimiento. */
	private long id_tiempoVencimiento;

	/** Propiedad il id etapa ant. */
	private long il_idEtapaAnt;

	/**
	 * Instancia un nuevo objeto tramite cantidad.
	 */
	public TramiteCantidad()
	{
	}

	/**
	 * Instancia un nuevo objeto tramite cantidad.
	 *
	 * @param cantidad de cantidad
	 * @param idEtapa de id etapa
	 * @param nombre de nombre
	 */
	public TramiteCantidad(Integer cantidad, Long idEtapa, String nombre)
	{
		ii_cantidad     = cantidad;
		il_idEtapa      = idEtapa;
		is_nombre       = nombre;
	}

	/**
	 * Modifica el valor de calificacion.
	 *
	 * @param as_s asigna el valor a la propiedad calificacion
	 */
	public void setCalificacion(String as_s)
	{
		is_calificacion = as_s;
	}

	/**
	 * Retorna el valor de calificacion.
	 *
	 * @return el valor de calificacion
	 */
	public String getCalificacion()
	{
		return is_calificacion;
	}

	/**
	 * Modifica el valor de cantidad.
	 *
	 * @param ai_i asigna el valor a la propiedad cantidad
	 */
	public void setCantidad(Integer ai_i)
	{
		ii_cantidad = ai_i;
	}

	/**
	 * Retorna el valor de cantidad.
	 *
	 * @return el valor de cantidad
	 */
	public Integer getCantidad()
	{
		return ii_cantidad;
	}

	/**
	 * Modifica el valor de is_documento.
	 *
	 * @param as_s asigna el valor a la propiedad is_documento
	 */
	public void setDocumento(String as_s)
	{
		is_documento = as_s;
	}

	/**
	 * Retorna el valor de is_documento.
	 *
	 * @return el valor de is_documento
	 */
	public String getDocumento()
	{
		return is_documento;
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
	 * Modifica el valor de EstadoActualTurno.
	 *
	 * @param as_s de as s
	 */
	public void setEstadoActualTurno(String as_s)
	{
		is_estadoActualTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado actual turno.
	 *
	 * @return Retorna el valor de la propiedad estadoActualTurno
	 */
	public String getEstadoActualTurno()
	{
		return is_estadoActualTurno;
	}

	/**
	 * Modifica el valor de FechaCreacion.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaCreacion(Date ad_d)
	{
		id_fechaCreacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha creacion.
	 *
	 * @return Retorna el valor de la propiedad fechaCreacion
	 */
	public Date getFechaCreacion()
	{
		return id_fechaCreacion;
	}

	/**
	 * Modifica el valor de fecha reparto.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha reparto
	 */
	public void setFechaReparto(Date ad_d)
	{
		id_fechaReparto = ad_d;
	}

	/**
	 * Retorna el valor de fecha reparto.
	 *
	 * @return el valor de fecha reparto
	 */
	public Date getFechaReparto()
	{
		return id_fechaReparto;
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
	 * Modifica el valor de Homologado.
	 *
	 * @param ab_b de ab b
	 */
	public void setHomologado(boolean ab_b)
	{
		ib_homologado = ab_b;
	}

	/**
	 * Valida la propiedad homologado.
	 *
	 * @return Retorna el valor de la propiedad homologado
	 */
	public boolean isHomologado()
	{
		return ib_homologado;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
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
	 * Modifica el valor de id etapa ant.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa ant
	 */
	public void setIdEtapaAnt(long al_l)
	{
		il_idEtapaAnt = al_l;
	}

	/**
	 * Retorna el valor de id etapa ant.
	 *
	 * @return el valor de id etapa ant
	 */
	public long getIdEtapaAnt()
	{
		return il_idEtapaAnt;
	}

	/**
	 * Modifica el valor de id motivo.
	 *
	 * @param al_l asigna el valor a la propiedad id motivo
	 */
	public void setIdMotivo(Long al_l)
	{
		il_idmotivo = al_l;
	}

	/**
	 * Retorna el valor de id motivo.
	 *
	 * @return el valor de id motivo
	 */
	public Long getIdMotivo()
	{
		return il_idmotivo;
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
	 * Modifica el valor de id sub proceso.
	 *
	 * @param as_s asigna el valor a la propiedad id sub proceso
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna el valor de id sub proceso.
	 *
	 * @return el valor de id sub proceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de MatriculasRelacionadas.
	 *
	 * @param as_s de as s
	 */
	public void setMatriculasRelacionadas(String as_s)
	{
		is_matriculasRelacionadas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas relacionadas.
	 *
	 * @return Retorna el valor de la propiedad matriculasRelacionadas
	 */
	public String getMatriculasRelacionadas()
	{
		return is_matriculasRelacionadas;
	}

	/**
	 * Modifica el valor de Migrado.
	 *
	 * @param ab_b de ab b
	 */
	public void setMigrado(boolean ab_b)
	{
		ib_migrado = ab_b;
	}

	/**
	 * Valida la propiedad migrado.
	 *
	 * @return Retorna el valor de la propiedad migrado
	 */
	public boolean isMigrado()
	{
		return ib_migrado;
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
	 * Modifica el valor de nombre.
	 *
	 * @param as_s asigna el valor a la propiedad nombre
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna el valor de nombre.
	 *
	 * @return el valor de nombre
	 */
	public String getNombre()
	{
		return is_nombre;
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
	 * Modifica el valor de nombre sub proceso.
	 *
	 * @param as_s asigna el valor a la propiedad nombre sub proceso
	 */
	public void setNombreSubProceso(String as_s)
	{
		is_nombreSubProceso = as_s;
	}

	/**
	 * Retorna el valor de nombre sub proceso.
	 *
	 * @return el valor de nombre sub proceso
	 */
	public String getNombreSubProceso()
	{
		return is_nombreSubProceso;
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
	 * @param Modifica el valor de la propiedad observacionesRespuesta por as_s
	 */
	public void setObservacionesRespuesta(String as_s)
	{
		is_observacionesRespuesta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad observacionesRespuesta
	 */
	public String getObservacionesRespuesta()
	{
		return is_observacionesRespuesta;
	}

	/**
	 * Modifica el valor de RojoTiempoVencimiento.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setRojoTiempoVencimiento(boolean ab_b)
	{
		ib_rojoTiempoVencimiento = ab_b;
	}

	/**
	 * Valida la propiedad rojo tiempo vencimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isRojoTiempoVencimiento()
	{
		return ib_rojoTiempoVencimiento;
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
	 * Modifica el valor de usuario.
	 *
	 * @param as_s asigna el valor a la propiedad usuario
	 */
	public void setTipificacionTurno(String as_s)
	{
		is_tipificacionTurno = as_s;
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public String getTipificacionTurno()
	{
		return is_tipificacionTurno;
	}

	/**
	 * Modifica el valor de tramite vinculado.
	 *
	 * @param as_s asigna el valor a la propiedad tramite vinculado
	 */
	public void setTramiteVinculado(String as_s)
	{
		is_tramiteVinculado = as_s;
	}

	/**
	 * Retorna el valor de tramite vinculado.
	 *
	 * @return el valor de tramite vinculado
	 */
	public String getTramiteVinculado()
	{
		return is_tramiteVinculado;
	}

	/**
	 * Modifica el valor de turnos.
	 *
	 * @param actc_ctc asigna el valor a la propiedad turnos
	 */
	public void setTurnos(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_turnos = actc_ctc;
	}

	/**
	 * Retorna el valor de turnos.
	 *
	 * @return el valor de turnos
	 */
	public Collection<TramiteCantidad> getTurnos()
	{
		return ictc_turnos;
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
	 * Modifica el valor de turnos desistimiento.
	 *
	 * @param actc_ctc asigna el valor a la propiedad turnos desistimiento
	 */
	public void setTurnosDesistimiento(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_turnosDesistimiento = actc_ctc;
	}

	/**
	 * Retorna el valor de turnos desistimiento.
	 *
	 * @return el valor de turnos desistimiento
	 */
	public Collection<TramiteCantidad> getTurnosDesistimiento()
	{
		return ictc_turnosDesistimiento;
	}

	/**
	 * Modifica el valor de turnos relacionados.
	 *
	 * @param as_s asigna el valor a la propiedad turnos relacionados
	 */
	public void setTurnosRelacionados(String as_s)
	{
		is_turnosRelacionados = as_s;
	}

	/**
	 * Retorna el valor de turnos relacionados.
	 *
	 * @return el valor de turnos relacionados
	 */
	public String getTurnosRelacionados()
	{
		return is_turnosRelacionados;
	}

	/**
	 * Modifica el valor de turnos suspendidos.
	 *
	 * @param actc_ctc asigna el valor a la propiedad turnos suspendidos
	 */
	public void setTurnosSuspendidos(Collection<TramiteCantidad> actc_ctc)
	{
		ictc_turnosSuspendidos = actc_ctc;
	}

	/**
	 * Retorna el valor de turnos suspendidos.
	 *
	 * @return el valor de turnos suspendidos
	 */
	public Collection<TramiteCantidad> getTurnosSuspendidos()
	{
		return ictc_turnosSuspendidos;
	}

	/**
	 * Modifica el valor de turnos vinculados.
	 *
	 * @param actc_ct asigna el valor a la propiedad turnos vinculados
	 */
	public void setTurnosVinculados(Collection<TramiteCantidad> actc_ct)
	{
		ictc_turnosVinculados = actc_ct;
	}

	/**
	 * Retorna el valor de turnos vinculados.
	 *
	 * @return el valor de turnos vinculados
	 */
	public Collection<TramiteCantidad> getTurnosVinculados()
	{
		return ictc_turnosVinculados;
	}

	/**
	 * Modifica el valor de url digitalizacion.
	 *
	 * @param as_s asigna el valor a la propiedad url digitalizacion
	 */
	public void setUrlDigitalizacion(String as_s)
	{
		is_urlDigitalizacion = as_s;
	}

	/**
	 * Retorna la url digitalizacion.
	 *
	 * @return la url digitalizacion
	 */
	public String getUrlDigitalizacion()
	{
		return is_urlDigitalizacion;
	}

	/**
	 * Modifica el valor de usuario.
	 *
	 * @param as_s asigna el valor a la propiedad usuario
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * @return Retorna el valor de la propiedad ictc_turnosProhibicion
	 */
	public Collection<TramiteCantidad> getTurnosProhibicion()
	{
		return ictc_turnosProhibicion;
	}

	/**
	 * @param ictc_turnosProhibicion Modifica el valor de la propiedad ictc_turnosProhibicion
	 */
	public void setTurnosProhibicion(Collection<TramiteCantidad> ictc_turnosProhibicion)
	{
		this.ictc_turnosProhibicion = ictc_turnosProhibicion;
	}
}
