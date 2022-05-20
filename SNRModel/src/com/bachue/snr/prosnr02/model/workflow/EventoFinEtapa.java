package com.bachue.snr.prosnr02.model.workflow;


/**
 * Clase que contiene todos las propiedades EventoFinEtapa.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class EventoFinEtapa extends Evento
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3944926583801473757L;

	/**
	 * Instancia un nuevo objeto evento fin etapa.
	 */
	public EventoFinEtapa()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto evento fin etapa.
	 *
	 * @param as_id de as id
	 */
	public EventoFinEtapa(String as_id)
	{
		super(as_id);

		setFinEtapa(true);
		setTipo(TIPO_EVENTO_FIN_ETAPA);
	}
}
