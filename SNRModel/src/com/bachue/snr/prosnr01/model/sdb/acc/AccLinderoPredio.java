package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_LINDERO_PREDIO.
 *
 * @author asantos
 */
public class AccLinderoPredio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -210858792159003794L;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id lindero predio. */
	private String is_idLinderoPredio;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is lindero. */
	private String is_lindero;

	/** Propiedad ib digitador masivo. */
	private boolean ib_digitadorMasivo;

	/** Propiedad ib lindero nuevo. */
	private boolean ib_linderoNuevo;

	/**
	 * Constructor por defecto.
	 */
	public AccLinderoPredio()
	{
	}

	/**
	 * Modifica el valor de DigitadorMasivo.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDigitadorMasivo(boolean ab_b)
	{
		ib_digitadorMasivo = ab_b;
	}

	/**
	 * Valida la propiedad digitador masivo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isDigitadorMasivo()
	{
		return ib_digitadorMasivo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
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
		is_idLinderoPredio = as_s;
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
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
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
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de Lindero.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLindero(String as_s)
	{
		is_lindero = as_s;
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

	/**
	 * Modifica el valor de LinderoNuevo.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setLinderoNuevo(boolean ab_b)
	{
		ib_linderoNuevo = ab_b;
	}

	/**
	 * Valida la propiedad lindero nuevo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isLinderoNuevo()
	{
		return ib_linderoNuevo;
	}
}
