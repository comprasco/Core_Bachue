package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import java.util.Collection;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisita;


/**
 * Interface que contiene todos las propiedades HeaderDatosDelTurnoInterface.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 23/10/2021
 */
public interface PanelTramitesARealizarVisitasInterface
{
	/**
	 * Retorna Objeto o variable de valor tramites A realizar.
	 *
	 * @return el valor de tramites A realizar
	 */
	public Collection<TramiteVisita> getTramitesARealizar();

	/**
	 * Modifica el valor de TramitesARealizar.
	 *
	 * @param acs_cs de acs cs
	 */
	public void setTramitesARealizar(Collection<TramiteVisita> acs_cs);

	/**
	* Retorna Objeto o variable de valor tramite seleccionado.
	*
	* @return el valor de tramite seleccionado
	*/
	public String getTramiteSeleccionado();

	/**
	 * Modifica el valor de TramiteSeleccionado.
	 *
	 * @param as_s de as s
	 */
	public void setTramiteSeleccionado(String as_s);
}
