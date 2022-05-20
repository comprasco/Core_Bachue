package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;

import java.io.Serializable;

import java.util.Map;



/**
 * Class que contiene todos las propiedades PanelDetalleAntSistemaAnotacion.
 *
 * @author hcastaneda
 */
public class PanelDetalleAntSistemaAnotacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1616828630297148427L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION;

	/** Propiedad idas detalle ant sistema anotacion. */
	private DetalleAntSistema idas_detalleAntSistemaAnotacion;

	/** Propiedad imsscc cheks. */
	private Map<String, SolicitudCamposCorreccion> imsscc_cheks;

	/** Propiedad ib detalle seleccionado. */
	private boolean ib_detalleSeleccionado;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/**
	 * Sets the cheks.
	 *
	 * @param amsscc_msscc de amsscc msscc
	 */
	public void setCheks(Map<String, SolicitudCamposCorreccion> amsscc_msscc)
	{
		imsscc_cheks = amsscc_msscc;
	}

	/**
	 * Retorna el valor de cheks.
	 *
	 * @return el valor de cheks
	 */
	public Map<String, SolicitudCamposCorreccion> getCheks()
	{
		return imsscc_cheks;
	}

	/**
	 * Modifica el valor de detalle ant sistema anotacion.
	 *
	 * @param adas_das asigna el valor a la propiedad detalle ant sistema anotacion
	 */
	public void setDetalleAntSistemaAnotacion(DetalleAntSistema adas_das)
	{
		idas_detalleAntSistemaAnotacion = adas_das;
	}

	/**
	 * Retorna el valor de detalle ant sistema anotacion.
	 *
	 * @return el valor de detalle ant sistema anotacion
	 */
	public DetalleAntSistema getDetalleAntSistemaAnotacion()
	{
		return idas_detalleAntSistemaAnotacion;
	}

	/**
	 * Modifica el valor de detalle seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad detalle seleccionado
	 */
	public void setDetalleSeleccionado(boolean ab_b)
	{
		ib_detalleSeleccionado = ab_b;
	}

	/**
	 * Valida la propiedad detalle seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en detalle seleccionado
	 */
	public boolean isDetalleSeleccionado()
	{
		return ib_detalleSeleccionado;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}
}
