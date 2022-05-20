package com.bachue.snr.prosnr01.web.bean.devolucionDinero;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanActoAdministrativos.
 *
 * @author Duvan Beltrán
 */
@SessionScoped
@ManagedBean(name = "beanActoAdministrativos")
public class BeanActoAdministrativos extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8532241839022192729L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fActoAdministrativo";

	/** Propiedad ib viene De Acto Administrativo DD. */
	private boolean ib_vieneDeActoAdministrativoDD;

	/**
	 * Modifica el valor de VieneDeAnalisisTraslados.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeActoAdministrativoDD(boolean ab_b)
	{
		ib_vieneDeActoAdministrativoDD = ab_b;
	}

	/**
	 * Valida la propiedad viene de analisis traslados.
	 *
	 * @return Retorna el valor de la propiedad isVieneDeAnalisisTraslados
	 */
	public boolean isVieneDeActoAdministrativoDD()
	{
		return ib_vieneDeActoAdministrativoDD;
	}

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
		setVieneDeActoAdministrativoDD(false);
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
