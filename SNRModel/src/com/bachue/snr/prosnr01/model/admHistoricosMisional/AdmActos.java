package com.bachue.snr.prosnr01.model.admHistoricosMisional;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades AdmActos.
 *
 * @author mblanco
 */
public class AdmActos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 607861330114004589L;

	/** Propiedad id porcentajeinc. */
	private Double id_porcentajeinc;

	/** Propiedad is codigo. */
	private String is_codigo;

	/** Propiedad is cuantia. */
	private String is_cuantia;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is incremento. */
	private String is_incremento;

	/** Propiedad id porcentaje. */
	private double id_porcentaje;

	/** Propiedad il cargofijo. */
	private long il_cargofijo;

	/**
	 * Modifica el valor de cargofijo.
	 *
	 * @param al_l asigna el valor a la propiedad cargofijo
	 */
	public void setCargofijo(long al_l)
	{
		il_cargofijo = al_l;
	}

	/**
	 * Retorna el valor de cargofijo.
	 *
	 * @return el valor de cargofijo
	 */
	public long getCargofijo()
	{
		return il_cargofijo;
	}

	/**
	 * Modifica el valor de codigo.
	 *
	 * @param as_s asigna el valor a la propiedad codigo
	 */
	public void setCodigo(String as_s)
	{
		is_codigo = as_s;
	}

	/**
	 * Retorna el valor de codigo.
	 *
	 * @return el valor de codigo
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Modifica el valor de cuantia.
	 *
	 * @param as_s asigna el valor a la propiedad cuantia
	 */
	public void setCuantia(String as_s)
	{
		is_cuantia = as_s;
	}

	/**
	 * Retorna el valor de cuantia.
	 *
	 * @return el valor de cuantia
	 */
	public String getCuantia()
	{
		return is_cuantia;
	}

	/**
	 * Modifica el valor de descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad descripcion
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna el valor de descripcion.
	 *
	 * @return el valor de descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de incremento.
	 *
	 * @param as_s asigna el valor a la propiedad incremento
	 */
	public void setIncremento(String as_s)
	{
		is_incremento = as_s;
	}

	/**
	 * Retorna el valor de incremento.
	 *
	 * @return el valor de incremento
	 */
	public String getIncremento()
	{
		return is_incremento;
	}

	/**
	 * Modifica el valor de porcentaje.
	 *
	 * @param ad_d asigna el valor a la propiedad porcentaje
	 */
	public void setPorcentaje(double ad_d)
	{
		id_porcentaje = ad_d;
	}

	/**
	 * Retorna el valor de porcentaje.
	 *
	 * @return el valor de porcentaje
	 */
	public double getPorcentaje()
	{
		return id_porcentaje;
	}

	/**
	 * Modifica el valor de porcentajeinc.
	 *
	 * @param ad_d asigna el valor a la propiedad porcentajeinc
	 */
	public void setPorcentajeinc(Double ad_d)
	{
		id_porcentajeinc = ad_d;
	}

	/**
	 * Retorna el valor de porcentajeinc.
	 *
	 * @return el valor de porcentajeinc
	 */
	public Double getPorcentajeinc()
	{
		return id_porcentajeinc;
	}
}
