package com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.common.constants.EtapaCommon;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanResponsableActuacionesAdmin.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanResponsableActuacionesAdmin")
public class BeanResponsableActuacionesAdmin extends BeanCalificacion implements Serializable
{
	private static final long serialVersionUID = 3487690380057476350L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fResponsableActuacionesAdmin";

	/**
	 * Metodo encargado de consultar los casos asignados a la etapa.
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(
		    CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS, true
		);
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
