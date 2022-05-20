package com.bachue.snr.prosnr01.model.business;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisita;

import java.io.Serializable;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades EjecucionVisitas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 30/10/2021
 */
public class EjecucionVisitas extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7514984164793339436L;

	/** Propiedad icsv informacion solicitud visita. */
	private Collection<SolicitudVisitas> icsv_informacionSolicitudVisita;

	/** Propiedad ics tramites A realizar. */
	private Collection<TramiteVisita> ictv_tramitesARealizar;

	/** Propiedad imsb permisos roles. */
	private Map<String, Boolean> imsb_permisosRoles;

	/** Propiedad is id subproceso. */
	private String is_idSubProceso;

	/**
	 * Retorna Objeto o variable de valor informacion solicitud visita.
	 *
	 * @return el valor de informacion solicitud visita
	 */
	public Collection<SolicitudVisitas> getInformacionSolicitudVisita()
	{
		return icsv_informacionSolicitudVisita;
	}

	/**
	 * Modifica el valor de InformacionSolicitudVisita.
	 *
	 * @param acsv_csv de acsv csv
	 */
	public void setInformacionSolicitudVisita(Collection<SolicitudVisitas> acsv_csv)
	{
		icsv_informacionSolicitudVisita = acsv_csv;
	}

	/**
	 * Retorna Objeto o variable de valor tramites A realizar.
	 *
	 * @return el valor de tramites A realizar
	 */
	public Collection<TramiteVisita> getTramitesARealizar()
	{
		return ictv_tramitesARealizar;
	}

	/**
	 * Modifica el valor de TramitesARealizar.
	 *
	 * @param acs_cs de acs cs
	 */
	public void setTramitesARealizar(Collection<TramiteVisita> acs_cs)
	{
		ictv_tramitesARealizar = acs_cs;
	}

	/**
	 * Retorna Objeto o variable de valor permisos roles.
	 *
	 * @return el valor de permisos roles
	 */
	public Map<String, Boolean> getPermisosRoles()
	{
		return imsb_permisosRoles;
	}

	/**
	 * Cambia el valor de permisos roles.
	 *
	 * @param amsb_msb de amsb msb
	 */
	public void setPermisosRoles(Map<String, Boolean> amsb_msb)
	{
		imsb_permisosRoles = amsb_msb;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return el valor de id subproceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}
}
