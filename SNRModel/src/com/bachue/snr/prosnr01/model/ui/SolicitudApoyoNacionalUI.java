package com.bachue.snr.prosnr01.model.ui;

import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoRegionalOrip;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades SolicitudApoyoNacionalUI.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 4/09/2020
 */
public class SolicitudApoyoNacionalUI implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3013255135632675304L;

/** Propiedad lcsan solicitudes apoyo nacional. */
	private Collection<SolicitudApoyoNacional> lcsan_solicitudesApoyoNacional;

	/** Propiedad lcsaro solicitudes regional orip. */
	private Collection<SolicitudApoyoRegionalOrip> lcsaro_solicitudesRegionalOrip;

	/** Propiedad lct turnos. */
	private Collection<Turno> lct_turnos;

	/** Propiedad ld documento. */
	private Documento ld_documento;

	/** Propiedad solicitud. */
	private Solicitud ls_solicitud;

	/** Propiedad is nir proceso. */
	private String is_nirProceso;

	/** Propiedad ls id circulo. */
	private String ls_idCirculo;

	/** Propiedad ls id turno historia. */
	private String ls_idTurnoHistoria;

	/** Propiedad lth turno historia. */
	private TurnoHistoria lth_turnoHistoria;

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param ad_d Modifica el valor de la propiedad documento
	 */
	public void setDocumento(Documento ad_d)
	{
		ld_documento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return Retorna el valor de la propiedad documento
	 */
	public Documento getDocumento()
	{
		return ld_documento;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s Modifica el valor de la propiedad idCirculo
	 */
	public void setIdCirculo(String as_s)
	{
		ls_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return ls_idCirculo;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param as_s Modifica el valor de la propiedad idTurnoHistoria
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		ls_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return Retorna el valor de la propiedad idTurnoHistoria
	 */
	public String getIdTurnoHistoria()
	{
		return ls_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de nir proceso.
	 *
	 * @param as_s asigna el valor a la propiedad nir proceso
	 */
	public void setNirProceso(String as_s)
	{
		is_nirProceso = as_s;
	}

	/**
	 * Retorna el valor de nir proceso.
	 *
	 * @return el valor de nir proceso
	 */
	public String getNirProceso()
	{
		return is_nirProceso;
	}

	/**
	 * Modifica el valor de Solicitud.
	 *
	 * @param as_s Modifica el valor de la propiedad solicitud
	 */
	public void setSolicitud(Solicitud as_s)
	{
		ls_solicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud.
	 *
	 * @return Retorna el valor de la propiedad solicitud
	 */
	public Solicitud getSolicitud()
	{
		return ls_solicitud;
	}

	/**
	 * Modifica el valor de SolicitudApoyoNacional.
	 *
	 * @param ac_c de ac c
	 */
	public void setSolicitudesApoyoNacional(Collection<SolicitudApoyoNacional> ac_c)
	{
		lcsan_solicitudesApoyoNacional = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud apoyo nacional.
	 *
	 * @return Retorna el valor de la propiedad solicitudApoyoNacional
	 */
	public Collection<SolicitudApoyoNacional> getSolicitudesApoyoNacional()
	{
		return lcsan_solicitudesApoyoNacional;
	}

	/**
	 * Modifica el valor de SolicitudesRegionalOrip.
	 *
	 * @param solicitudesRegionalOrip Modifica el valor de la propiedad solicitudesRegionalOrip
	 */
	public void setSolicitudesRegionalOrip(Collection<SolicitudApoyoRegionalOrip> solicitudesRegionalOrip)
	{
		this.lcsaro_solicitudesRegionalOrip = solicitudesRegionalOrip;
	}

	/**
	 * Retorna Objeto o variable de valor solicitudes regional orip.
	 *
	 * @return Retorna el valor de la propiedad solicitudesRegionalOrip
	 */
	public Collection<SolicitudApoyoRegionalOrip> getSolicitudesRegionalOrip()
	{
		return lcsaro_solicitudesRegionalOrip;
	}

	/**
	 * Modifica el valor de TurnoHistoria.
	 *
	 * @param at_t Modifica el valor de la propiedad turnoHistoria
	 */
	public void setTurnoHistoria(TurnoHistoria at_t)
	{
		lth_turnoHistoria = at_t;
	}

	/**
	 * Retorna Objeto o variable de valor turno historia.
	 *
	 * @return Retorna el valor de la propiedad turnoHistoria
	 */
	public TurnoHistoria getTurnoHistoria()
	{
		return lth_turnoHistoria;
	}

	/**
	 * Modifica el valor de Turnos.
	 *
	 * @param at_t Modifica el valor de la propiedad turnos
	 */
	public void setTurnos(Collection<Turno> at_t)
	{
		lct_turnos = at_t;
	}

	/**
	 * Retorna Objeto o variable de valor turnos.
	 *
	 * @return Retorna el valor de la propiedad turnos
	 */
	public Collection<Turno> getTurnos()
	{
		return lct_turnos;
	}
}
