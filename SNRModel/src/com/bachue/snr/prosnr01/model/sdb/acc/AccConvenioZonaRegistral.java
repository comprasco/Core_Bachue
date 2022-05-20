package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase para la abstracción de la tabla SDB_ACC_CONVENIO_ZONA_REGISTRAL.
 *
 * @author Sebastian Sanchez
 */
public class AccConvenioZonaRegistral extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = -7930094481037032401L;

	/**  Propiedad is activo. */
	private String is_activo;

	/**  Propiedad is id convenio zona registral. */
	private String is_idConvenioZonaRegistral;

	/**  Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/**  Propiedad is id zona registral. */
	private String is_idZonaRegistral;

	/**  Propiedad is numero convenio. */
	private String is_numeroConvenio;

	/**
	 * Constructor por defecto.
	 */
	public AccConvenioZonaRegistral()
	{
	}

	/**
	 * Modifica el valor de is_idZonaRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad is_idZonaRegistral
	 */
	public void setIdZonaRegistral(String as_s)
	{
		is_idZonaRegistral = as_s;
	}

	/**
	 * Retorna el valor de is_idZonaRegistral.
	 *
	 * @return el valor de is_idZonaRegistral
	 */
	public String getIdZonaRegistral()
	{
		return is_idZonaRegistral;
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
	 * Modifica el valor de is_idConvenioZonaRegistral.
	 *
	 * @param as_s asigna el valor a la propiedad is_idConvenioZonaRegistral
	 */
	public void setIdConvenioZonaRegistral(String as_s)
	{
		is_idConvenioZonaRegistral = as_s;
	}

	/**
	 * Retorna el valor de is_idConvenioZonaRegistral.
	 *
	 * @return el valor de is_idConvenioZonaRegistral
	 */
	public String getIdConvenioZonaRegistral()
	{
		return is_idConvenioZonaRegistral;
	}
}
