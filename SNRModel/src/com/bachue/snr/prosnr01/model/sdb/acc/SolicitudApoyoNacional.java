package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades SolicitudApoyoNacional.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 15/09/2020
 */
public class SolicitudApoyoNacional extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6129418701030832176L;

	/** Propiedad id fecha asignacion. */
	private Date id_fechaAsignacion;

	/** Propiedad is id circulo asignado. */
	private String is_idCirculoAsignado;

	/** Propiedad is id circulo solicitante. */
	private String is_idCirculoSolicitante;

	/** Propiedad is id regional. */
	private String is_idRegional;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud apoyo nacional. */
	private String is_idSolicitudApoyoNacional;

	/** Propiedad is id solicitud registro. */
	private String is_idSolicitudRegistro;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno registro. */
	private String is_idTurnoRegistro;

	/** Propiedad is id usuario asignado. */
	private String is_idUsuarioAsignado;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nir registro. */
	private String is_nirRegistro;

	/** Propiedad nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad nombre procesp. */
	private String is_nombreProceso;

	/** Propiedad is observacion. */
	private String is_observacion;

	/** Propiedad is reasignacion exitosa. */
	private String is_reasignacionExitosa;

	/** Propiedad ib finalizada. */
	private boolean ib_finalizada;

	/** Propiedad etapa actual. */
	private long il_etapaActual;

	/** Propiedad il id turno historia registro. */
	private long il_idTurnoHistoriaRegistro;

	/**
	 * Modifica el valor de estapaActual.
	 *
	 * @param al_l the estapaActual to set
	 */
	public void setEtapaActual(long al_l)
	{
		il_etapaActual = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor etapa actual.
	 *
	 * @return the etapa actual.
	 */
	public long getEtapaActual()
	{
		return il_etapaActual;
	}

	/**
	 * Modifica el valor de FechaAsignacion.
	 *
	 * @param fechaAsignacion the fechaAsignacion to set
	 */
	public void setFechaAsignacion(Date fechaAsignacion)
	{
		id_fechaAsignacion = fechaAsignacion;
	}

	/**
	 * Retorna Objeto o variable de valor fecha asignacion.
	 *
	 * @return the fechaAsignacion
	 */
	public Date getFechaAsignacion()
	{
		return id_fechaAsignacion;
	}

	/**
	 * Modifica el valor de finalizada.
	 *
	 * @param lb_b the finalizada to set
	 */
	public void setFinalizada(boolean lb_b)
	{
		ib_finalizada = lb_b;
	}

	/**
	 * Retorna Objeto o variable de valor finalizada.
	 *
	 * @return the finalizada
	 */
	public boolean isFinalizada()
	{
		return ib_finalizada;
	}

	/**
	 * Modifica el valor de IdCirculoAsignado.
	 *
	 * @param as_s the idCirculoAsignado to set
	 */
	public void setIdCirculoAsignado(String as_s)
	{
		is_idCirculoAsignado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo asignado.
	 *
	 * @return the idCirculoAsignado
	 */
	public String getIdCirculoAsignado()
	{
		return is_idCirculoAsignado;
	}

	/**
	 * Modifica el valor de IdCirculoSolicitante.
	 *
	 * @param as_s the idCirculoSolicitante to set
	 */
	public void setIdCirculoSolicitante(String as_s)
	{
		is_idCirculoSolicitante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo solicitante.
	 *
	 * @return the idCirculoSolicitante
	 */
	public String getIdCirculoSolicitante()
	{
		return is_idCirculoSolicitante;
	}

	/**
	 * Modifica el valor de IdRegional.
	 *
	 * @param as_s the idRegional to set
	 */
	public void setIdRegional(String as_s)
	{
		is_idRegional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id regional.
	 *
	 * @return the idRegional
	 */
	public String getIdRegional()
	{
		return is_idRegional;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s the idSolicitud to set
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return the idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSolicitudApoyoNacional.
	 *
	 * @param as_s the idSolicitudApoyoNacional to set
	 */
	public void setIdSolicitudApoyoNacional(String as_s)
	{
		is_idSolicitudApoyoNacional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud apoyo nacional.
	 *
	 * @return the idSolicitudApoyoNacional
	 */
	public String getIdSolicitudApoyoNacional()
	{
		return is_idSolicitudApoyoNacional;
	}

	/**
	 * Modifica el valor de IdSolicitudRegistro.
	 *
	 * @param as_s the idSolicitudRegistro to set
	 */
	public void setIdSolicitudRegistro(String as_s)
	{
		is_idSolicitudRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud registro.
	 *
	 * @return the idSolicitudRegistro
	 */
	public String getIdSolicitudRegistro()
	{
		return is_idSolicitudRegistro;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s the idTurno to set
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return the idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHistoriaRegistro.
	 *
	 * @param al_l the idTurnoHistoriaRegistro to set
	 */
	public void setIdTurnoHistoriaRegistro(long al_l)
	{
		il_idTurnoHistoriaRegistro = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia registro.
	 *
	 * @return the idTurnoHistoriaRegistro
	 */
	public long getIdTurnoHistoriaRegistro()
	{
		return il_idTurnoHistoriaRegistro;
	}

	/**
	 * Modifica el valor de IdTurnoRegistro.
	 *
	 * @param as_s the idTurnoRegistro to set
	 */
	public void setIdTurnoRegistro(String as_s)
	{
		is_idTurnoRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno registro.
	 *
	 * @return the idTurnoRegistro
	 */
	public String getIdTurnoRegistro()
	{
		return is_idTurnoRegistro;
	}

	/**
	 * Modifica el valor de IdUsuarioAsignado.
	 *
	 * @param as_s the idUsuarioAsignado to set
	 */
	public void setIdUsuarioAsignado(String as_s)
	{
		is_idUsuarioAsignado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario asignado.
	 *
	 * @return the idUsuarioAsignado
	 */
	public String getIdUsuarioAsignado()
	{
		return is_idUsuarioAsignado;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s the nir to set
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return the nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de NirRegistro.
	 *
	 * @param as_s Modifica el valor de la propiedad nirRegistro
	 */
	public void setNirRegistro(String as_s)
	{
		is_nirRegistro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir registro.
	 *
	 * @return Retorna el valor de la propiedad nirRegistro
	 */
	public String getNirRegistro()
	{
		return is_nirRegistro;
	}

	/**
	 * Modifica el valor de nombre circulo.
	 *
	 * @param as_s the nombre circulo to set
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return the nombre circulo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de nombre proceso.
	 *
	 * @param as_s the nombre proceso. to set
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre proceso
	 *
	 * @return the nombre proceso.
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

	/**
	 * Modifica el valor de Observacion.
	 *
	 * @param as_s the observacion to set
	 */
	public void setObservacion(String as_s)
	{
		is_observacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion()
	{
		return is_observacion;
	}

	/**
	 * Modifica el valor de ReasignacionExitosa.
	 *
	 * @param as_s the reasignacionExitosa to set
	 */
	public void setReasignacionExitosa(String as_s)
	{
		is_reasignacionExitosa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor reasignacion exitosa.
	 *
	 * @return the reasignacionExitosa
	 */
	public String getReasignacionExitosa()
	{
		return is_reasignacionExitosa;
	}
}
