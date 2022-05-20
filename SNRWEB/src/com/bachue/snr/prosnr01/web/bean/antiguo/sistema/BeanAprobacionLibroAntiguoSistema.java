package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanAprobacionLibroAntiguoSistema.
 *
 * @author Sebastian Sanchez
 */
@SessionScoped
@ManagedBean(name = "beanAprobacionLibroAntiguoSistema")
public class BeanAprobacionLibroAntiguoSistema extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3092183313994487971L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fAprobacionLibroAntSistema";

	/**
	 * Generar datos tramite cantidad.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(CS_ID_FORM, "" + getIdEtapa(), true);
	}

	/**
	 * Generar torta.
	 *
	 * @return el valor de pie chart model
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
	 * Limpiar bandeja.
	 */
	public void limpiarBandeja()
	{
	}

	/**
	 * Mostrar torta.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}
}
