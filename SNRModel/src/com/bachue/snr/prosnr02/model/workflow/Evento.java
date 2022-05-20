package com.bachue.snr.prosnr02.model.workflow;

import com.bachue.snr.prosnr02.model.acc.EtapaTrabajo;



/**
 * Clase que contiene todos las propiedades Evento.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public abstract class Evento extends Nodo
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5266928531837386458L;
	
	/** Propiedad iet etapa. */
	private EtapaTrabajo      iet_etapa;
	
	/** Propiedad ib fin etapa. */
	private boolean           ib_finEtapa;
	
	/** Propiedad ib fin proceso. */
	private boolean           ib_finProceso;
	
	/** Propiedad ib inicio proceso. */
	private boolean           ib_inicioProceso;

	/**
	 * Instancia un nuevo objeto evento.
	 */
	public Evento()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto evento.
	 *
	 * @param as_id de as id
	 */
	public Evento(String as_id)
	{
		setId(as_id);
		setFinEtapa(false);
		setFinProceso(false);
		setInicioProceso(false);
	}

	/**
	 * Modifica el valor de Etapa.
	 *
	 * @param aet_et de aet et
	 */
	public void setEtapa(EtapaTrabajo aet_et)
	{
		iet_etapa                              = aet_et;
	}

	/**
	 * Retorna Objeto o variable de valor etapa.
	 *
	 * @return el valor de etapa
	 */
	public EtapaTrabajo getEtapa()
	{
		return iet_etapa;
	}

	/**
	 * Modifica el valor de FinEtapa.
	 *
	 * @param ab_b de ab b
	 */
	public void setFinEtapa(boolean ab_b)
	{
		ib_finEtapa = ab_b;
	}

	/**
	 * Valida la propiedad fin etapa.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en fin etapa
	 */
	public boolean isFinEtapa()
	{
		return ib_finEtapa;
	}

	/**
	 * Valida la propiedad fin proceso.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en fin proceso
	 */
	public boolean isFinProceso()
	{
		return ib_finProceso;
	}

	/**
	 * Valida la propiedad inicio proceso.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en inicio proceso
	 */
	public boolean isInicioProceso()
	{
		return ib_inicioProceso;
	}

	/**
	 * Modifica el valor de FinProceso.
	 *
	 * @param ab_b de ab b
	 */
	protected void setFinProceso(boolean ab_b)
	{
		ib_finProceso = ab_b;
	}

	/**
	 * Modifica el valor de InicioProceso.
	 *
	 * @param ab_b de ab b
	 */
	protected void setInicioProceso(boolean ab_b)
	{
		ib_inicioProceso = ab_b;
	}
}
