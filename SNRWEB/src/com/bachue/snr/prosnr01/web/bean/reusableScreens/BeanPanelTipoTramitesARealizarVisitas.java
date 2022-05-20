package com.bachue.snr.prosnr01.web.bean.reusableScreens;

import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaTipo;

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
@ManagedBean(name = "beanPanelTipoTramitesARealizarVisitas")
public class BeanPanelTipoTramitesARealizarVisitas implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8356221084852924751L;

	/** Propiedad ictvt tipo tramites A realizar. */
	private Collection<TramiteVisitaTipo> ictvt_tipoTramitesARealizar;

	/** Propiedad is tipo tramite seleccionado. */
	private String is_tipoTramiteSeleccionado;

	/** Propiedad ib delegado registro. */
	private boolean ib_delegadoRegistro;

	/** Propiedad ib lider vigilancia control. */
	private boolean ib_liderVigilanciaControl;

	/** Propiedad ib responsable seguimiento visita. */
	private boolean ib_responsableSeguimientoVisita;

	/** Propiedad ib visitador. */
	private boolean ib_visitador;

/**
 * Modifica el valor de DelegadoRegistro.
 *
 * @param ab_b de ib delegado registro
 */
	public void setDelegadoRegistro(boolean ab_b)
	{
		ib_delegadoRegistro = ab_b;
	}

/**
 * Valida la propiedad delegado registro.
 *
 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en delegado registro
 */
	public boolean isDelegadoRegistro()
	{
		return ib_delegadoRegistro;
	}

/**
 * Modifica el valor de LiderVigilanciaControl.
 *
 * @param ab_b de ib lider vigilancia control
 */
	public void setLiderVigilanciaControl(boolean ab_b)
	{
		ib_liderVigilanciaControl = ab_b;
	}

/**
 * Valida la propiedad lider vigilancia control.
 *
 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en lider vigilancia control
 */
	public boolean isLiderVigilanciaControl()
	{
		return ib_liderVigilanciaControl;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_responsableSeguimientoVisita por ib_responsableSeguimientoVisita
	 */
	public void setResponsableSeguimientoVisita(boolean ab_b)
	{
		ib_responsableSeguimientoVisita = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_responsableSeguimientoVisita
	 */
	public boolean isResponsableSeguimientoVisita()
	{
		return ib_responsableSeguimientoVisita;
	}

	/**
	 * Modifica el valor de TramiteSeleccionado.
	 *
	 * @param as_s de as s
	 */
	public void setTipoTramiteSeleccionado(String as_s)
	{
		is_tipoTramiteSeleccionado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tramite seleccionado.
	 *
	 * @return el valor de tramite seleccionado
	 */
	public String getTipoTramiteSeleccionado()
	{
		return is_tipoTramiteSeleccionado;
	}

/**
 * Modifica el valor de TipoTramitesARealizar.
 *
 * @param actvt_ctvt de actvt ctvt
 */
	public void setTipoTramitesARealizar(Collection<TramiteVisitaTipo> actvt_ctvt)
	{
		ictvt_tipoTramitesARealizar = actvt_ctvt;
	}

/**
 * Retorna Objeto o variable de valor tipo tramites A realizar.
 *
 * @return el valor de tipo tramites A realizar
 */
	public Collection<TramiteVisitaTipo> getTipoTramitesARealizar()
	{
		return ictvt_tipoTramitesARealizar;
	}

/**
 * Modifica el valor de Visitador.
 *
 * @param ab_b de ib visitador
 */
	public void setVisitador(boolean ab_b)
	{
		ib_visitador = ab_b;
	}

/**
 * Valida la propiedad visitador.
 *
 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en visitador
 */
	public boolean isVisitador()
	{
		return ib_visitador;
	}
}
