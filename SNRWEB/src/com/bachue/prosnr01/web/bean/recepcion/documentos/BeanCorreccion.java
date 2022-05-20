package com.bachue.prosnr01.web.bean.recepcion.documentos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCorreccion.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanCorreccion")
public class BeanCorreccion extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6797580617097245134L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fCorreccion";

	/** {@inheritdoc} */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_CORRECCIONES, true);
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
