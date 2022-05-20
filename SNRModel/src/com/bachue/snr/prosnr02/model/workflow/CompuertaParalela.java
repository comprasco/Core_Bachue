package com.bachue.snr.prosnr02.model.workflow;


/**
 * Clase que contiene todos las propiedades CompuertaParalela.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public class CompuertaParalela extends Compuerta
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8569928378026844518L;

	/**
	 * Instancia un nuevo objeto compuerta paralela.
	 */
	public CompuertaParalela()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto compuerta paralela.
	 *
	 * @param as_id de as id
	 */
	public CompuertaParalela(String as_id)
	{
		super(as_id);

		setParalela(true);
		setTipo(TIPO_COMPUERTA_PARALELA);
	}
}
