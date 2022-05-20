package com.bachue.snr.prosnr02.model.workflow;


/**
 * Clase que contiene todos las propiedades EventoFinProceso.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class EventoFinProceso extends Evento
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3944926583801473757L;

	/**
	 * Instancia un nuevo objeto evento fin proceso.
	 */
	public EventoFinProceso()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto evento fin proceso.
	 *
	 * @param as_id de as id
	 */
	public EventoFinProceso(String as_id)
	{
		super(as_id);

		setFinProceso(true);
		setTipo(TIPO_EVENTO_FIN_PROCESO);
	}
}
