package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla  SDB_BNG_LINDERO_PREDIO.
 *
 * @author Julian Vaca
 */
public class LinderoPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = -3247512882163922798L;
	
	/** Propiedad il id matricula. */
	private Long              il_idMatricula;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id lindero predio. */
	private String            is_idLinderoPredio;
	
	/** Propiedad is id usuario. */
	private String            is_idUsuario;
	
	/** Propiedad is lindero. */
	private String            is_lindero;

	/**
	 * Constructor por defecto.
	 */
	public LinderoPredio()
	{
	}

	/**
	 * Constructor que recibe como parametro un objeto de tipo AccLinderoPredio.
	 *
	 * @param aalp_alp objeto de AccLinderoPredio
	 * @see com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio
	 */
	public LinderoPredio(AccLinderoPredio aalp_alp)
	{
		il_idMatricula                           = aalp_alp.getIdMatricula();
		is_idCirculo                             = aalp_alp.getIdCirculo();
		is_idLinderoPredio                       = aalp_alp.getIdLinderoPredio();
		is_lindero                               = aalp_alp.getLindero();
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		this.is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdLinderoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdLinderoPredio(String as_s)
	{
		this.is_idLinderoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id lindero predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLinderoPredio()
	{
		return is_idLinderoPredio;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long as_s)
	{
		this.il_idMatricula = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
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

	/**
	 * Modifica el valor de Lindero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLindero(String as_s)
	{
		this.is_lindero = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor lindero.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLindero()
	{
		return is_lindero;
	}
}
