package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaTipo;

import java.util.Collection;


/**
 * Interface que contiene todos las propiedades PanelTipoTramitesARealizarVisitasInterface.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 23/10/2021
 */
public interface PanelTipoTramitesARealizarVisitasInterface
{
	/**
	 * Modifica el valor de DelegadoRegistro.
	 *
	 * @param ab_b de ab b
	 */
	public void setDelegadoRegistro(boolean ab_b);

	/**
	 * Valida la propiedad delegado registro.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en delegado registro
	 */
	public boolean isDelegadoRegistro();

	/**
	 * Modifica el valor de LiderVigilanciaControl.
	 *
	 * @param ab_b de ab b
	 */
	public void setLiderVigilanciaControl(boolean ab_b);

	/**
	 * Valida la propiedad lider vigilancia control.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en lider vigilancia control
	 */
	public boolean isLiderVigilanciaControl();

	/**
	 * Modifica el valor de ResponsableSeguimientoVisita.
	 *
	 * @param ab_b correspondiente al valor de ab b
	 */
	public void setResponsableSeguimientoVisita(boolean ab_b);

	/**
	 * Valida la propiedad responsable seguimiento visita.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en responsable seguimiento visita
	 */
	public boolean isResponsableSeguimientoVisita();

	/**
	 * Modifica el valor de TipoTramiteSeleccionado.
	 *
	 * @param as_s de as s
	 */
	public void setTipoTramiteSeleccionado(String as_s);

	/**
	 * Retorna Objeto o variable de valor tipo tramite seleccionado.
	 *
	 * @return el valor de tipo tramite seleccionado
	 */
	public String getTipoTramiteSeleccionado();

	/**
	 * Modifica el valor de TipoTramitesARealizar.
	 *
	 * @param actvt_ctvt de actvt ctvt
	 */
	public void setTipoTramitesARealizar(Collection<TramiteVisitaTipo> actvt_ctvt);

	/**
	 * Retorna Objeto o variable de valor tipo tramites A realizar.
	 *
	 * @return el valor de tipo tramites A realizar
	 */
	public Collection<TramiteVisitaTipo> getTipoTramitesARealizar();

	/**
	 * Modifica el valor de Visitador.
	 *
	 * @param ab_b de ab b
	 */
	public void setVisitador(boolean ab_b);

	/**
	 * Valida la propiedad visitador.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en visitador
	 */
	public boolean isVisitador();
}
