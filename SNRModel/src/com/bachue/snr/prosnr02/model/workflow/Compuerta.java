package com.bachue.snr.prosnr02.model.workflow;


/**
 * Clase que contiene todos las propiedades Compuerta.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/06/2020
 */
public abstract class Compuerta extends Nodo
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7343804907513163222L;
	
	/** Propiedad ib exclusiva. */
	private boolean           ib_exclusiva;
	
	/** Propiedad ib paralela. */
	private boolean           ib_paralela;

	/**
	 * Instancia un nuevo objeto compuerta.
	 */
	public Compuerta()
	{
		this(null);
	}

	/**
	 * Instancia un nuevo objeto compuerta.
	 *
	 * @param as_id de as id
	 */
	public Compuerta(String as_id)
	{
		setId(as_id);
		setParalela(false);
		setExclusiva(false);
	}

	/**
	 * Valida la propiedad exclusiva.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en exclusiva
	 */
	public boolean isExclusiva()
	{
		return ib_exclusiva;
	}

	/**
	 * Valida la propiedad paralela.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en paralela
	 */
	public boolean isParalela()
	{
		return ib_paralela;
	}

	/**
	 * Modifica el valor de Exclusiva.
	 *
	 * @param ab_b de ab b
	 */
	protected void setExclusiva(boolean ab_b)
	{
		ib_exclusiva                           = ab_b;
	}

	/**
	 * Modifica el valor de Paralela.
	 *
	 * @param ab_b de ab b
	 */
	protected void setParalela(boolean ab_b)
	{
		ib_paralela = ab_b;
	}
}
