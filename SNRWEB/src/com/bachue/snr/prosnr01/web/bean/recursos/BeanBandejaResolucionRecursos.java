package com.bachue.snr.prosnr01.web.bean.recursos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de beanBandejaResolucionRecursos.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanBandejaResolucionRecursos")
public class BeanBandejaResolucionRecursos extends BeanCalificacion implements Serializable
{
	private static final long serialVersionUID = 2106733437019291743L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fBandejaResolucionRecursos";

	/** Propiedad ib_vieneDeAnalisisSegundaInstancia.*/
	private boolean ib_vieneDeAnalisisSegundaInstancia;

	/** Propiedad il etapa a buscar.*/
	private long il_etapaABuscar;

	/**
	 * Modifica el valor de etapa a a buscar.
	 *
	 * @param al_l asigna el valor a la propiedad etapa a a buscar.
	 */
	public void setEtapaABuscar(long al_l)
	{
		il_etapaABuscar                        = al_l;
	}

	/**
	 * Retorna el valor de etapa a buscar
	 *
	 * @return Retorna el valor de la propiedad etapa a buscar
	 */
	public long getEtapaABuscar()
	{
		return il_etapaABuscar;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad vieneDeAnalisisSegundaInstancia por vieneDeAnalisisSegundaInstancia
	 */
	public void setVieneDeAnalisisSegundaInstancia(boolean ab_b)
	{
		ib_vieneDeAnalisisSegundaInstancia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad vieneDeAnalisisSegundaInstancia
	 */
	public boolean isVieneDeAnalisisSegundaInstancia()
	{
		return ib_vieneDeAnalisisSegundaInstancia;
	}

	/**
	 * Metodo encargado de consultar los casos asignados a la etapa.
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(
		    CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS, true
		);
	}

	/**
	 * Metodo encargado de consultar los casos asignados a la etapa.
	 */
	public void generarDatosTramiteCantidadByEtapa()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(CS_ID_FORM, "" + il_etapaABuscar, true);
	}

	/**
	 * Generar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PieChartModel generarTorta()
	    throws B2BException
	{
		return getModeloTorta();
	}

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}

	/**
	 * Mostrar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}
}
