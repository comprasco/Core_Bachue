package com.bachue.snr.prosnr02.model.workflow;


/**
 * Clase que contiene todos las propiedades EventoInicioProceso.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class EventoInicioProceso extends Evento
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5426348980962046583L;

	/**
	 * Instancia un nuevo objeto evento inicio proceso.
	 */
	public EventoInicioProceso()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto evento inicio proceso.
	 *
	 * @param as_id de as id
	 */
	public EventoInicioProceso(String as_id)
	{
		super(as_id);

		setInicioProceso(true);
		setTipo(TIPO_EVENTO_INICIO_PROCESO);
	}
}
