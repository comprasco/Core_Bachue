package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PERSONA_CORREO_ELECTRONICO.
 *
 * @author Julian Vaca
 */
public class PersonaCorreoElectronico extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = -8038449030680692228L;
	
	/** Propiedad id fecha desde. */
	private Date              id_fechaDesde;
	
	/** Propiedad id fecha hasta. */
	private Date              id_fechaHasta;
	
	/** Propiedad is correo electronico. */
	private String            is_correoElectronico;
	
	/** Propiedad is id correo electronico. */
	private String            is_idCorreoElectronico;
	
	/** Propiedad is id persona. */
	private String            is_idPersona;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;

	/**
	 * Constructor por defecto.
	 */
	public PersonaCorreoElectronico()
	{
	}

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico                         = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de IdCorreoElectronico.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCorreoElectronico(String as_s)
	{
		is_idCorreoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id correo electronico.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCorreoElectronico()
	{
		return is_idCorreoElectronico;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		is_idUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdUsuario()
	{
		return is_idUsuario;
	}
}
