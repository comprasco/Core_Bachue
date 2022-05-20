package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;



/**
 * Clase de abstracción para la tabla SDB_PGN_MOTIVO_TRAMITE.
 *
 * @author Nicolas Guaneme
 */
public class MotivoTramite extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID          = -4998780281267794430L;
	
	/** Propiedad ibd id etapa posterior. */
	private BigDecimal        ibd_idEtapaPosterior;
	
	/** Propiedad il version subproceso. */
	private Long              il_versionSubproceso;
	
	/** Propiedad is decision calificacion. */
	private String            is_decisionCalificacion;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is descriptor fin. */
	private String            is_descriptorFin;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is estado actividad. */
	private String            is_estadoActividad;
	
	/** Propiedad is etapa nombre. */
	private String            is_etapaNombre;
	
	/** Propiedad is flujo defecto. */
	private String            is_flujoDefecto;
	
	/** Propiedad is id motivo tramite. */
	private String            is_idMotivoTramite;
	
	/** Propiedad is id proceso. */
	private String            is_idProceso;
	
	/** Propiedad is id subproceso. */
	private String            is_idSubproceso;
	
	/** Propiedad is is indicador devolucion. */
	private String            is_is_indicadorDevolucion;
	
	/** Propiedad is tipo compuerta llegada. */
	private String            is_tipoCompuertaLlegada;
	
	/** Propiedad il id etapa origen. */
	private long              il_idEtapaOrigen;
	
	/** Propiedad il id motivo. */
	private long              il_idMotivo;

	/**
	 * Instancia un nuevo objeto motivo tramite.
	 */
	public MotivoTramite()
	{
	}

	/**
	 * Instancia un nuevo objeto motivo tramite.
	 *
	 * @param al_idEtapa de al id etapa
	 * @param al_idMotivo de al id motivo
	 */
	public MotivoTramite(long al_idEtapa, long al_idMotivo)
	{
		setIdEtapaOrigen(al_idEtapa);
		setIdMotivo(al_idMotivo);
	}

	/**
	 * Modifica el valor de DecisionCalificacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDecisionCalificacion(String as_s)
	{
		is_decisionCalificacion                         = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor decision calificacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDecisionCalificacion()
	{
		return is_decisionCalificacion;
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
	 * Modifica el valor de DescriptorFin.
	 *
	 * @param as_s de as s
	 */
	public void setDescriptorFin(String as_s)
	{
		is_descriptorFin = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descriptor fin.
	 *
	 * @return Retorna el valor de la propiedad descriptorFin
	 */
	public String getDescriptorFin()
	{
		return is_descriptorFin;
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
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
	 * Modifica el valor de EtapaNombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEtapaNombre(String as_s)
	{
		is_etapaNombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor etapa nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEtapaNombre()
	{
		return is_etapaNombre;
	}

	/**
	 * Modifica el valor de FlujoDefecto.
	 *
	 * @param as_s de as s
	 */
	public void setFlujoDefecto(String as_s)
	{
		is_flujoDefecto = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor flujo defecto.
	 *
	 * @return Retorna el valor de la propiedad flujoDefecto
	 */
	public String getFlujoDefecto()
	{
		return is_flujoDefecto;
	}

	/**
	 * Modifica el valor de IdEtapaOrigen.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdEtapaOrigen(long al_l)
	{
		il_idEtapaOrigen = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa origen.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdEtapaOrigen()
	{
		return il_idEtapaOrigen;
	}

	/**
	 * Modifica el valor de IdEtapaPosterior.
	 *
	 * @param abd_bd asigna el valor a la propiedad
	 */
	public void setIdEtapaPosterior(BigDecimal abd_bd)
	{
		ibd_idEtapaPosterior = abd_bd;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa posterior.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigDecimal getIdEtapaPosterior()
	{
		return ibd_idEtapaPosterior;
	}

	/**
	 * Modifica el valor de IdMotivo.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id motivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Modifica el valor de IdMotivoTramite.
	 *
	 * @param as_s de as s
	 */
	public void setIdMotivoTramite(String as_s)
	{
		is_idMotivoTramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id motivo tramite.
	 *
	 * @return Retorna el valor de la propiedad idMotivoTramite
	 */
	public String getIdMotivoTramite()
	{
		return is_idMotivoTramite;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return Retorna el valor de la propiedad idProceso
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubproceso(String as_s)
	{
		is_idSubproceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return Retorna el valor de la propiedad idSubproceso
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de IndicadorDevolucion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIndicadorDevolucion(String as_s)
	{
		is_is_indicadorDevolucion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor indicador devolucion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIndicadorDevolucion()
	{
		return is_is_indicadorDevolucion;
	}

	/**
	 * Modifica el valor de TipoCompuertaLlegada.
	 *
	 * @param as_s de as s
	 */
	public void setTipoCompuertaLlegada(String as_s)
	{
		is_tipoCompuertaLlegada = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo compuerta llegada.
	 *
	 * @return Retorna el valor de la propiedad tipoCompuertaLlegada
	 */
	public String getTipoCompuertaLlegada()
	{
		return is_tipoCompuertaLlegada;
	}

	/**
	 * Modifica el valor de VersionSubproceso.
	 *
	 * @param al_l de al l
	 */
	public void setVersionSubproceso(Long al_l)
	{
		il_versionSubproceso = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version subproceso.
	 *
	 * @return Retorna el valor de la propiedad versionSubproceso
	 */
	public Long getVersionSubproceso()
	{
		return il_versionSubproceso;
	}
}
