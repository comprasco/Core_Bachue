package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase para la abstracción de la tabla SDB_ACC_CONVENIO_CIRCULO_REGISTRAL.
 *
 * @author Sebastian Sanchez
 */
public class AccConvenioCirculoRegistral extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 8514199468009179322L;

	/**  Propiedad is activo. */
	private String is_activo;

	/**  Propiedad is id circulo registral. */
	private String is_idCirculoRegistral;

	/**  Propiedad is id convenio circulo registral. */
	private String is_idConvenioCirculoRegistral;

	/**  Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/**  Propiedad is numero convenio. */
	private String is_numeroConvenio;

	/**
	 * Constructor por defecto.
	 */
	public AccConvenioCirculoRegistral()
	{
	}

	/**
	 * Modifica el valor de is_idCirculoRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad is_idCirculoRegistral
	 */
	public void setIdCirculoRegistral(String as_s)
	{
		is_idCirculoRegistral = as_s;
	}

	/**
	 * Retorna el valor de is_idCirculoRegistral.
	 *
	 * @return el valor de is_idCirculoRegistral
	 */
	public String getIdCirculoRegistral()
	{
		return is_idCirculoRegistral;
	}

	/**
	 * Modifica el valor de is_activo.
	 *
	 * @param as_s asigna el valor a la propiedad is_activo
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna el valor de is_activo.
	 *
	 * @return el valor de is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de is_idEntidadExterna.
	 *
	 * @param as_s asigna el valor a la propiedad is_idEntidadExterna
	 */
	public void setIdEntidadExterna(String as_s)
	{
		is_idEntidadExterna = as_s;
	}

	/**
	 * Retorna el valor de is_idEntidadExterna.
	 *
	 * @return el valor de is_idEntidadExterna
	 */
	public String getIdEntidadExterna()
	{
		return is_idEntidadExterna;
	}

	/**
	 * Modifica el valor de is_numeroConvenio.
	 *
	 * @param as_s asigna el valor a la propiedad is_numeroConvenio
	 */
	public void setNumeroConvenio(String as_s)
	{
		is_numeroConvenio = as_s;
	}

	/**
	 * Retorna el valor de is_numeroConvenio.
	 *
	 * @return el valor de is_numeroConvenio
	 */
	public String getNumeroConvenio()
	{
		return is_numeroConvenio;
	}

	/**
	 * Modifica el valor de is_idConvenioCirculoRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad is_idConvenioCirculoRegistral
	 */
	public void setIdConvenioCirculoRegistral(String as_s)
	{
		is_idConvenioCirculoRegistral = as_s;
	}

	/**
	 * Retorna el valor de is_idConvenioCirculoRegistral.
	 *
	 * @return el valor de is_idConvenioCirculoRegistral
	 */
	public String getIdConvenioCirculoRegistral()
	{
		return is_idConvenioCirculoRegistral;
	}
}
