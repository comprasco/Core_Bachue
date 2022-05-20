package com.bachue.snr.prosnr01.model.calificacion;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades RegistroParcialCalificacion.
 *
 * @author Julian Vaca
 */
public class RegistroParcialCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5673967950873419611L;

	/** Propiedad il id anotacion. */
	private Long il_idAnotacion;

	/** Propiedad is especificacion. */
	private String is_especificacion;

	/** Propiedad is id anotacion predio. */
	private String is_idAnotacionPredio;

	/** Propiedad is id naturaleza juridica. */
	private String is_idNaturalezaJuridica;

	/** Propiedad is matricula completa. */
	private String is_matriculaCompleta;

	/** Propiedad ib registro parcial. */
	private boolean ib_registroParcial;

	/**
	 * Modifica el valor de especificacion.
	 *
	 * @param as_s asigna el valor a la propiedad especificacion
	 */
	public void setEspecificacion(String as_s)
	{
		is_especificacion = as_s;
	}

	/**
	 * Retorna el valor de especificacion.
	 *
	 * @return el valor de especificacion
	 */
	public String getEspecificacion()
	{
		return is_especificacion;
	}

	/**
	 * Modifica el valor de id anotacion.
	 *
	 * @param al_l asigna el valor a la propiedad id anotacion
	 */
	public void setIdAnotacion(Long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna el valor de id anotacion.
	 *
	 * @return el valor de id anotacion
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de id anotacion predio.
	 *
	 * @param as_s asigna el valor a la propiedad id anotacion predio
	 */
	public void setIdAnotacionPredio(String as_s)
	{
		is_idAnotacionPredio = as_s;
	}

	/**
	 * Retorna el valor de id anotacion predio.
	 *
	 * @return el valor de id anotacion predio
	 */
	public String getIdAnotacionPredio()
	{
		return is_idAnotacionPredio;
	}

	/**
	 * Modifica el valor de id naturaleza juridica.
	 *
	 * @param as_s asigna el valor a la propiedad id naturaleza juridica
	 */
	public void setIdNaturalezaJuridica(String as_s)
	{
		is_idNaturalezaJuridica = as_s;
	}

	/**
	 * Retorna el valor de id naturaleza juridica.
	 *
	 * @return el valor de id naturaleza juridica
	 */
	public String getIdNaturalezaJuridica()
	{
		return is_idNaturalezaJuridica;
	}

	/**
	 * Modifica el valor de matricula completa.
	 *
	 * @param as_s asigna el valor a la propiedad matricula completa
	 */
	public void setMatriculaCompleta(String as_s)
	{
		is_matriculaCompleta = as_s;
	}

	/**
	 * Retorna el valor de matricula completa.
	 *
	 * @return el valor de matricula completa
	 */
	public String getMatriculaCompleta()
	{
		return is_matriculaCompleta;
	}

	/**
	 * Modifica el valor de registro parcial.
	 *
	 * @param ab_b asigna el valor a la propiedad registro parcial
	 */
	public void setRegistroParcial(boolean ab_b)
	{
		ib_registroParcial = ab_b;
	}

	/**
	 * Valida la propiedad registro parcial.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en registro parcial
	 */
	public boolean isRegistroParcial()
	{
		return ib_registroParcial;
	}
}
