package com.bachue.snr.prosnr01.model.ui;

import java.io.Serializable;

import java.util.Collection;


/**
 * Clase modelo que contiene todos los atributos de FoliosSirUI.
 *
 * @author Sebastian Sanchez
 */
public class FoliosSirUI implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2655375263040669142L;

	/** Propiedad iclhui liquidacionesHistoricosUI. */
	private Collection<LiquidacionesHistoricosUI> iclhui_liquidacionesHistoricosUI;

	/** Propiedad icpui pagosUI. */
	private Collection<PagosUI> icpui_pagosUI;

	/** Propiedad icttui trazabilidadTurnoUI. */
	private Collection<TrazabilidadTurnoUI> icttui_trazabilidadTurnoUI;

	/**
	 * Instancia un nuevo objeto folios sir UI.
	 *
	 * @param acpui_cpui de acpui cpui
	 * @param aclhui_clhui de aclhui clhui
	 * @param acttui_cttui de acttui cttui
	 *
	 */
	public FoliosSirUI(
	    Collection<PagosUI> acpui_cpui, Collection<LiquidacionesHistoricosUI> aclhui_clhui,
	    Collection<TrazabilidadTurnoUI> acttui_cttui
	)
	{
		icpui_pagosUI                        = acpui_cpui;
		iclhui_liquidacionesHistoricosUI     = aclhui_clhui;
		icttui_trazabilidadTurnoUI           = acttui_cttui;
	}

	/**
	 * @param aclhui_clhui asigna el valor a la propiedad
	 */
	public void setLiquidacionesHistoricosUI(Collection<LiquidacionesHistoricosUI> aclhui_clhui)
	{
		iclhui_liquidacionesHistoricosUI = aclhui_clhui;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<LiquidacionesHistoricosUI> getLiquidacionesHistoricosUI()
	{
		return iclhui_liquidacionesHistoricosUI;
	}

	/**
	 * @param acpui_cpui asigna el valor a la propiedad
	 */
	public void setPagosUI(Collection<PagosUI> acpui_cpui)
	{
		icpui_pagosUI = acpui_cpui;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<PagosUI> getPagosUI()
	{
		return icpui_pagosUI;
	}

	/**
	 * @param acttui_cttui asigna el valor a la propiedad
	 */
	public void setTrazabilidadTurnoUI(Collection<TrazabilidadTurnoUI> acttui_cttui)
	{
		icttui_trazabilidadTurnoUI = acttui_cttui;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TrazabilidadTurnoUI> getTrazabilidadTurnoUI()
	{
		return icttui_trazabilidadTurnoUI;
	}
}
