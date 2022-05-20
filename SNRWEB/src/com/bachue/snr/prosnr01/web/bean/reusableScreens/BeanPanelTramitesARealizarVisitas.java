package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisita;

import java.io.Serializable;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * Clase que contiene todos las propiedades y acciones de beanHeaderDatosDelTurno.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 29/10/2021
 */
@ViewScoped
@ManagedBean(name = "beanPanelTramitesARealizarVisitas")
public class BeanPanelTramitesARealizarVisitas implements Serializable, PanelTramitesARealizarVisitasInterface
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5326763588913124810L;

	/** Propiedad ics tramites A realizar. */
	private Collection<TramiteVisita> itv_tramitesARealizar;

	/** Propiedad is tramite seleccionado. */
	private String is_tramiteSeleccionado;

	/**
	 * Retorna Objeto o variable de valor tramites A realizar.
	 *
	 * @return el valor de tramites A realizar
	 */
	public Collection<TramiteVisita> getTramitesARealizar()
	{
		return itv_tramitesARealizar;
	}

	/**
	 * Modifica el valor de TramitesARealizar.
	 *
	 * @param acs_cs de acs cs
	 */
	public void setTramitesARealizar(Collection<TramiteVisita> acs_cs)
	{
		itv_tramitesARealizar = acs_cs;
	}

	/**
	 * Retorna Objeto o variable de valor tramite seleccionado.
	 *
	 * @return el valor de tramite seleccionado
	 */
	public String getTramiteSeleccionado()
	{
		return is_tramiteSeleccionado;
	}

	/**
	 * Modifica el valor de TramiteSeleccionado.
	 *
	 * @param as_s de as s
	 */
	public void setTramiteSeleccionado(String as_s)
	{
		is_tramiteSeleccionado = as_s;
	}
}
