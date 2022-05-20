package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;

import java.io.Serializable;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * Clase que contiene todos las propiedades y acciones de beanHeaderDatosDelTurno.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 29/10/2021
 */
@ViewScoped
@ManagedBean(name = "beanPanelSolicitudVisitas")
public class BeanPanelSolicitudVisitas implements Serializable, PanelSolicitudVisitasInterface
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8252289817914140459L;

	/** Propiedad icsv datos panel solicitud visitas. */
	private Collection<SolicitudVisitas> icsv_datosPanelSolicitudVisitas;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/**
	* Retorna Objeto o variable de valor id solicitud.
	*
	* @return el valor de id solicitud
	*/
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor datos panel solicitud visitas.
	 *
	 * @return el valor de datos panel solicitud visitas
	 */
	public Collection<SolicitudVisitas> getDatosPanelSolicitudVisitas()
	{
		return icsv_datosPanelSolicitudVisitas;
	}

	/**
	 * Modifica el valor de DatosPanelSolicitudVisitas.
	 *
	 * @param acsv_csv de acsv csv
	 */
	public void setDatosPanelSolicitudVisitas(Collection<SolicitudVisitas> acsv_csv)
	{
		icsv_datosPanelSolicitudVisitas = acsv_csv;
	}
}
