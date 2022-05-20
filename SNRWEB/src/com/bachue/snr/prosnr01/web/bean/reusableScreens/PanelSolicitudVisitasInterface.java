package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;

import java.util.Collection;


/**
 * Interface que contiene todos las propiedades HeaderDatosDelTurnoInterface.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 23/10/2021
 */
public interface PanelSolicitudVisitasInterface
{
	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud();

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s);

	/**
	* Retorna Objeto o variable de valor datos panel solicitud visitas.
	*
	* @return el valor de datos panel solicitud visitas
	*/
	public Collection<SolicitudVisitas> getDatosPanelSolicitudVisitas();

	/**
	 * Modifica el valor de DatosPanelSolicitudVisitas.
	 *
	 * @param acsv_csv de acsv csv
	 */
	public void setDatosPanelSolicitudVisitas(Collection<SolicitudVisitas> acsv_csv);
}
