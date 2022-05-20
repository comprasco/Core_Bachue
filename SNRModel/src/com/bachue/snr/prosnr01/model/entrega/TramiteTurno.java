package com.bachue.snr.prosnr01.model.entrega;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Class que contiene todos las propiedades TramiteTurno.
 *
 * @author Julian Vaca
 */
public class TramiteTurno extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2271964033808180676L;

	/** Propiedad id fecha asignacion. */
	private Date id_fechaAsignacion;

	/** Propiedad id fecha creacion etapa. */
	private Date id_fechaCreacionEtapa;

	/** Propiedad al id etapa. */
	private Long al_idEtapa;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad is estado actividad. */
	private String is_estadoActividad;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is motivoNoTramite. */
	private String is_motivoNoTramite;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is tipificacion de turno. */
	private String is_tipificacionTurno;

	/** Propiedad is turnos asociados. */
	private String is_turnosAsociados;

	/** Propiedad ib generar relacion. */
	private boolean ib_generarRelacion;

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
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return Retorna el valor de la propiedad observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s de as s
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
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
	 * Modifica el valor de fecha asignacion.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha asignacion
	 */
	public void setFechaAsignacion(Date ad_d)
	{
		id_fechaAsignacion = ad_d;
	}

	/**
	 * Retorna el valor de fecha asignacion.
	 *
	 * @return el valor de fecha asignacion
	 */
	public Date getFechaAsignacion()
	{
		return id_fechaAsignacion;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(Long al_l)
	{
		al_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public Long getIdEtapa()
	{
		return al_idEtapa;
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
	 * Modifica el valor de motivo no tramite.
	 *
	 * @param as_s de as s
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
	 * Modifica el valor de Nir.
	 *
	 * @param as_nir de as nir
	 */
	public void setNir(String as_nir)
	{
		is_nir = as_nir;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
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
	 * Retorna Objeto o variable de valor fecha creacion etapa.
	 *
	 * @return Retorna el valor de la propiedad fechaCreacionEtapa
	 */
	public Date getFechaCreacionEtapa()
	{
		return id_fechaCreacionEtapa;
	}

	/**
	 * Modifica el valor de FechaCreacionEtapa.
	 *
	 * @param ad_D de ad D
	 */
	public void setFechaCreacionEtapa(Date ad_D)
	{
		id_fechaCreacionEtapa = ad_D;
	}
}
