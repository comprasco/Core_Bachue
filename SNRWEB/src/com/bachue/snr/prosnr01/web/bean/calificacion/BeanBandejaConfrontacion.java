package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanBandejaConfrontacion.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanBandejaConfrontacion")
public class BeanBandejaConfrontacion extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4320263722628985100L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = ":fBandejaConfrontacion";

	/** {@inheritdoc} */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_CONFRONTACION_CORRECTIVA, true);
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

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}
}
