package com.bachue.snr.prosnr01.model.consulta.trazabilidad;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades ConsultaTrazabilidad.
 *
 * @author hcastaneda
 */
public class ConsultaTrazabilidad extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8825764094255804760L;

	/** Propiedad ict trazabilidad. */
	private Collection<Trazabilidad> ict_trazabilidad;

	/** Propiedad inv nir vinculados. */
	private Collection<NirVinculado> inv_nirVinculados;

	/** Propiedad inv turnos vinculados nir vinculados. */
	private Collection<NirVinculado> inv_turnosVinculadosNirVinculados;

	/** Propiedad ie etapa. */
	private Etapa ie_etapa;

	/** Propiedad inp nir principal. */
	private NirPrincipal inp_nirPrincipal;

	/** Propiedad inp turnos vinculados nir principal. */
	private NirPrincipal inp_turnosVinculadosNirPrincipal;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad is mensaje. */
	private String is_mensaje;

	/** Propiedad is turno afectado. */
	private String is_turnoAfectado;

	/** Propiedad it trazabilidad info. */
	private Trazabilidad it_trazabilidadInfo;

	/** Propiedad it turno solicitud. */
	private Turno it_turnoSolicitud;

	/** Propiedad ith turno historia. */
	private TurnoHistoria ith_turnoHistoria;

	/** Propiedad ib campo tificacion turno. */
	private boolean ib_campoTificacionTurno;

	/** Propiedad ib estado. */
	private boolean ib_estado;

	/**
	 * Modifica el valor de campo tificacion turno.
	 *
	 * @param ab_b asigna el valor a la propiedad campo tificacion turno
	 */
	public void setCampoTificacionTurno(boolean ab_b)
	{
		ib_campoTificacionTurno = ab_b;
	}

	/**
	 * Valida la propiedad campo tificacion turno.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en campo tificacion turno
	 */
	public boolean isCampoTificacionTurno()
	{
		return ib_campoTificacionTurno;
	}

	/**
	 * Modifica el valor de estado.
	 *
	 * @param ab_b asigna el valor a la propiedad estado
	 */
	public void setEstado(boolean ab_b)
	{
		ib_estado = ab_b;
	}

	/**
	 * Valida la propiedad estado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en estado
	 */
	public boolean isEstado()
	{
		return ib_estado;
	}

	/**
	 * Modifica el valor de etapa.
	 *
	 * @param ae_e asigna el valor a la propiedad etapa
	 */
	public void setEtapa(Etapa ae_e)
	{
		ie_etapa = ae_e;
	}

	/**
	 * Retorna el valor de etapa.
	 *
	 * @return el valor de etapa
	 */
	public Etapa getEtapa()
	{
		return ie_etapa;
	}

	/**
	 * Modifica el valor de mensaje.
	 *
	 * @param as_s asigna el valor a la propiedad mensaje
	 */
	public void setMensaje(String as_s)
	{
		is_mensaje = as_s;
	}

	/**
	 * Retorna el valor de mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje()
	{
		return is_mensaje;
	}

	/**
	 * Modifica el valor de nir principal.
	 *
	 * @param anp_np asigna el valor a la propiedad nir principal
	 */
	public void setNirPrincipal(NirPrincipal anp_np)
	{
		inp_nirPrincipal = anp_np;
	}

	/**
	 * Retorna el valor de nir principal.
	 *
	 * @return el valor de nir principal
	 */
	public NirPrincipal getNirPrincipal()
	{
		return inp_nirPrincipal;
	}

	/**
	 * Modifica el valor de nir vinculados.
	 *
	 * @param anr_nr asigna el valor a la propiedad nir vinculados
	 */
	public void setNirVinculados(Collection<NirVinculado> anr_nr)
	{
		inv_nirVinculados = anr_nr;
	}

	/**
	 * Retorna el valor de nir vinculados.
	 *
	 * @return el valor de nir vinculados
	 */
	public Collection<NirVinculado> getNirVinculados()
	{
		return inv_nirVinculados;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}

	/**
	 * Modifica el valor de trazabilidad.
	 *
	 * @param act_ct asigna el valor a la propiedad trazabilidad
	 */
	public void setTrazabilidad(Collection<Trazabilidad> act_ct)
	{
		ict_trazabilidad = act_ct;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @return el valor de trazabilidad
	 */
	public Collection<Trazabilidad> getTrazabilidad()
	{
		return ict_trazabilidad;
	}

	/**
	 * Modifica el valor de trazabilidad info.
	 *
	 * @param at_t asigna el valor a la propiedad trazabilidad info
	 */
	public void setTrazabilidadInfo(Trazabilidad at_t)
	{
		it_trazabilidadInfo = at_t;
	}

	/**
	 * Retorna el valor de trazabilidad info.
	 *
	 * @return el valor de trazabilidad info
	 */
	public Trazabilidad getTrazabilidadInfo()
	{
		return it_trazabilidadInfo;
	}

	/**
	 * Modifica el valor de turno historia.
	 *
	 * @param ath_th asigna el valor a la propiedad turno historia
	 */
	public void setTurnoHistoria(TurnoHistoria ath_th)
	{
		ith_turnoHistoria = ath_th;
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
	 * Modifica el valor de turno solicitud.
	 *
	 * @param at_t asigna el valor a la propiedad turno solicitud
	 */
	public void setTurnoSolicitud(Turno at_t)
	{
		it_turnoSolicitud = at_t;
	}

	/**
	 * Retorna el valor de turno solicitud.
	 *
	 * @return el valor de turno solicitud
	 */
	public Turno getTurnoSolicitud()
	{
		return it_turnoSolicitud;
	}

	/**
	 * Modifica el valor de turnos vinculados nir principal.
	 *
	 * @param anp_np asigna el valor a la propiedad turnos vinculados nir principal
	 */
	public void setTurnosVinculadosNirPrincipal(NirPrincipal anp_np)
	{
		inp_turnosVinculadosNirPrincipal = anp_np;
	}

	/**
	 * Retorna el valor de turnos vinculados nir principal.
	 *
	 * @return el valor de turnos vinculados nir principal
	 */
	public NirPrincipal getTurnosVinculadosNirPrincipal()
	{
		return inp_turnosVinculadosNirPrincipal;
	}

	/**
	 * Modifica el valor de turnos vinculados nir vinculados.
	 *
	 * @param anv_nv asigna el valor a la propiedad turnos vinculados nir vinculados
	 */
	public void setTurnosVinculadosNirVinculados(Collection<NirVinculado> anv_nv)
	{
		inv_turnosVinculadosNirVinculados = anv_nv;
	}

	/**
	 * Retorna el valor de turnos vinculados nir vinculados.
	 *
	 * @return el valor de turnos vinculados nir vinculados
	 */
	public Collection<NirVinculado> getTurnosVinculadosNirVinculados()
	{
		return inv_turnosVinculadosNirVinculados;
	}

	/**
	 * Retorna el valor de turno afectado.
	 *
	 * @return el valor de turno afectado.
	 */
	public String getTurnoAfectado()
	{
		return is_turnoAfectado;
	}

	/**
	 * Modifica el valor de turno afectado.
	 *
	 * @param as_s asigna el valor a la propiedad turno afectado.
	 */
	public void setTurnoAfectado(String as_s)
	{
		is_turnoAfectado = as_s;
	}
}
