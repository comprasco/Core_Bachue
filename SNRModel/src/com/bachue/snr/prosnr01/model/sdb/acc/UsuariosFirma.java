package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_USUARIOS_FIRMA.
 *
 * @author Julian Vaca
 */
public class UsuariosFirma extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1658139416803209966L;
	
	/** Propiedad is clave. */
	private String            is_clave;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;

	/**
	 * Modifica el valor de Clave.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setClave(String as_s)
	{
		this.is_clave                          = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor clave.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getClave()
	{
		return is_clave;
	}

	/**
	 * Modifica el valor de IdUsuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdUsuario(String as_s)
	{
		this.is_idUsuario = as_s;
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
