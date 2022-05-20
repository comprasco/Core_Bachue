package com.bachue.snr.prosnr01.model.antiguoSistema;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades AreaPredio.
 *
 * @author Julian Vaca
 */
public class AreaPredio implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6482259427949136667L;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad is area construida. */
	private String is_areaConstruida;

	/** Propiedad is area del terreno. */
	private String is_areaDelTerreno;

	/** Propiedad is area privada. */
	private String is_areaPrivada;

	/** Propiedad is coeficiente. */
	private String is_coeficiente;

	/** Propiedad is id area. */
	private String is_idArea;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is uso del predio. */
	private String is_usoDelPredio;

	/**
	 * Modifica el valor de area construida.
	 *
	 * @param as_s asigna el valor a la propiedad area construida
	 */
	public void setAreaConstruida(String as_s)
	{
		is_areaConstruida = as_s;
	}

	/**
	 * Retorna el valor de area construida.
	 *
	 * @return el valor de area construida
	 */
	public String getAreaConstruida()
	{
		return is_areaConstruida;
	}

	/**
	 * Modifica el valor de area del terreno.
	 *
	 * @param as_s asigna el valor a la propiedad area del terreno
	 */
	public void setAreaDelTerreno(String as_s)
	{
		is_areaDelTerreno = as_s;
	}

	/**
	 * Retorna el valor de area del terreno.
	 *
	 * @return el valor de area del terreno
	 */
	public String getAreaDelTerreno()
	{
		return is_areaDelTerreno;
	}

	/**
	 * Modifica el valor de area privada.
	 *
	 * @param as_s asigna el valor a la propiedad area privada
	 */
	public void setAreaPrivada(String as_s)
	{
		is_areaPrivada = as_s;
	}

	/**
	 * Retorna el valor de area privada.
	 *
	 * @return el valor de area privada
	 */
	public String getAreaPrivada()
	{
		return is_areaPrivada;
	}

	/**
	 * Modifica el valor de coeficiente.
	 *
	 * @param as_s asigna el valor a la propiedad coeficiente
	 */
	public void setCoeficiente(String as_s)
	{
		is_coeficiente = as_s;
	}

	/**
	 * Retorna el valor de coeficiente.
	 *
	 * @return el valor de coeficiente
	 */
	public String getCoeficiente()
	{
		return is_coeficiente;
	}

	/**
	 * Modifica el valor de id area.
	 *
	 * @param as_s asigna el valor a la propiedad id area
	 */
	public void setIdArea(String as_s)
	{
		is_idArea = as_s;
	}

	/**
	 * Retorna el valor de id area.
	 *
	 * @return el valor de id area
	 */
	public String getIdArea()
	{
		return is_idArea;
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
	 * Modifica el valor de id matricula.
	 *
	 * @param al_l asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna el valor de id matricula.
	 *
	 * @return el valor de id matricula
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de uso del predio.
	 *
	 * @param as_s asigna el valor a la propiedad uso del predio
	 */
	public void setUsoDelPredio(String as_s)
	{
		is_usoDelPredio = as_s;
	}

	/**
	 * Retorna el valor de uso del predio.
	 *
	 * @return el valor de uso del predio
	 */
	public String getUsoDelPredio()
	{
		return is_usoDelPredio;
	}
}
