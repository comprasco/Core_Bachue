package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase para la abstracción de la tabla SDB_ACC_ENTIDAD_EXTERNA_PERSONA.
 *
 * @author Sebastian Sanchez
 */
public class AccEntidadExternaPersona extends Auditoria implements Serializable
{
	
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 8540380008147302486L;

	/**  Propiedad is activo. */
	private String is_activo;

	/**  Propiedad is id entidad externa. */
	private String is_idEntidadExterna;

	/**  Propiedad is id entidad externa persona. */
	private String is_idEntidadExternaPersona;

	/**  Propiedad is id persona. */
	private String is_idPersona;

	/**  Propiedad is id representante legal. */
	private String is_representanteLegal;

	/**  Propiedad is usuario. */
	private String is_usuario;

	/**
	 * Constructor por defecto.
	 */
	public AccEntidadExternaPersona()
	{
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
	 * Modifica el valor de is_idPersona.
	 *
	 * @param as_s asigna el valor a la propiedad is_idPersona
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna el valor de is_idPersona.
	 *
	 * @return el valor de is_idPersona
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de is_idEntidadExternaPersona.
	 *
	 * @param as_s asigna el valor a la propiedad is_idEntidadExterna
	 */
	public void setIdEntidadExternaPersona(String as_s)
	{
		is_idEntidadExternaPersona = as_s;
	}

	/**
	 * Retorna el valor de is_idEntidadExternaPersona.
	 *
	 * @return el valor de is_idEntidadExternaPersona
	 */
	public String getIdEntidadExternaPersona()
	{
		return is_idEntidadExternaPersona;
	}

	/**
	 * Modifica el valor de is_representanteLegal.
	 *
	 * @param as_s asigna el valor a la propiedad is_representanteLegal
	 */
	public void setRepresentanteLegal(String as_s)
	{
		is_representanteLegal = as_s;
	}

	/**
	 * Retorna el valor de is_representanteLegal.
	 *
	 * @return el valor de is_representanteLegal
	 */
	public String getRepresentanteLegal()
	{
		return is_representanteLegal;
	}

	/**
	 * Modifica el valor de is_usuario.
	 *
	 * @param as_s asigna el valor a la propiedad is_usuario
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna el valor de is_usuario.
	 *
	 * @return el valor de is_usuario
	 */
	public String getUsuario()
	{
		return is_usuario;
	}
}
