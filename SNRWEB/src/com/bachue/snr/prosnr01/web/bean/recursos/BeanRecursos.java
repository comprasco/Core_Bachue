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
 * Clase que contiene todos las propiedades y acciones de BeanRecursos.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanRecursos")
public class BeanRecursos extends BeanCalificacion implements Serializable
{
	private static final long serialVersionUID = -1112195845180821648L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fBandejaRecursos";

	/** Propiedad ib viene de aprobación asesoría jurídica. */
	private boolean ib_vieneDeCoordinadorSegundaInstancia;
	
	/** Propiedad il etapa a buscar.*/
	private long il_etapaABuscar;

	/**
	 * Modifica el valor de etapa a a buscar.
	 *
	 * @param lb_b asigna el valor a la propiedad etapa a a buscar.
	 */
	public void setEtapaABuscar(long al_l)
	{
		il_etapaABuscar                        = al_l;
	}

	/**
	 * Retorna el valor de etapa a buscar
	 * @return Retorna el valor de la propiedad etapa a buscar
	 */
	public long getEtapaABuscar()
	{
		return il_etapaABuscar;
	}

	/**
	 * Modifica el valor de viene de coordinacion de segunda instancia.
	 *
	 * @param lb_b asigna el valor a la propiedad viene de ccordinacion segunda instancia.
	 */
	public void setVieneDeCoordinadorSegundaInstancia(boolean lb_b)
	{
		ib_vieneDeCoordinadorSegundaInstancia = lb_b;
	}

	/**
	 * Valida la propiedad es viene de coordinacion segunda instancia.
	 *
	 * @return Retorna el valor de la propiedad viene de coordinacion segunda instancia.
	 */
	public boolean isVieneDeCoordinadorSegundaInstancia()
	{
		return ib_vieneDeCoordinadorSegundaInstancia;
	}

	/**
	 * Metodo encargado de consultar los casos asignados a la etapa.
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(
		    CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS, true
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
