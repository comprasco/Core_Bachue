package com.bachue.snr.prosnr01.web.bean.traslados;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanTraslados.
 *
 * @author Gabriel Arias
 */
@SessionScoped
@ManagedBean(name = "beanTraslados")
public class BeanTraslados extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6418016845896456878L;

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = "fTraslados";

	/** Propiedad is viene de analisis traslados. */
	private boolean ib_vieneDeAnalisisTraslados;

	/** Propiedad is viene de análisis traslados oficina destino. */
	private boolean ib_vieneDeAnalisisTrasladosOficinaDestino;

	/** Propiedad is viene de publicacion resolucion planeacion. */
	private boolean ib_vieneDePublicacionResolucionPlaneacion;

	/**
	 * Modifica el valor de VieneDeAnalisisTraslados.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDeAnalisisTraslados(boolean ab_b)
	{
		ib_vieneDeAnalisisTraslados = ab_b;
	}

	/**
	 * Valida la propiedad viene de analisis traslados.
	 *
	 * @return Retorna el valor de la propiedad isVieneDeAnalisisTraslados
	 */
	public boolean isVieneDeAnalisisTraslados()
	{
		return ib_vieneDeAnalisisTraslados;
	}

	/**
	 * @param Modifica el valor de la propiedad vieneDeAnalisisTrasladosOficinaDestino por vieneDeAnalisisTrasladosOficinaDestino
	 */
	public void setVieneDeAnalisisTrasladosOficinaDestino(boolean ab_b)
	{
		ib_vieneDeAnalisisTrasladosOficinaDestino = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad vieneDeAnalisisTrasladosOficinaDestino
	 */
	public boolean isVieneDeAnalisisTrasladosOficinaDestino()
	{
		return ib_vieneDeAnalisisTrasladosOficinaDestino;
	}

	/**
	 * Modifica el valor de VieneDePublicacionResolucionPlaneacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setVieneDePublicacionResolucionPlaneacion(boolean ab_b)
	{
		ib_vieneDePublicacionResolucionPlaneacion = ab_b;
	}

	/**
	 * Valida la propiedad viene de publicacion resolucion planeacion.
	 *
	 * @return Retorna el valor de la propiedad isVieneDePublicacionResolucionPlaneacion
	 */
	public boolean isVieneDePublicacionResolucionPlaneacion()
	{
		return ib_vieneDePublicacionResolucionPlaneacion;
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
		setVieneDeAnalisisTraslados(false);
		setVieneDePublicacionResolucionPlaneacion(false);
		setVieneDeAnalisisTrasladosOficinaDestino(false);
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
