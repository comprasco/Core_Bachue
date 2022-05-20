package com.bachue.snr.prosnr01.model.admHistoricosMisional;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades AdmLiquidaciones.
 *
 * @author mblanco
 */
public class AdmLiquidaciones implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7829002615342439583L;

	/** Propiedad is acto. */
	private String is_acto;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is nroradica. */
	private String is_nroradica;

	/** Propiedad is tipo liq. */
	private String is_tipoLiq;

	/** Propiedad is turno actualizado. */
	private String is_turnoActualizado;

	/** Propiedad id liquidacion. */
	private double id_liquidacion;

	/** Propiedad id valor. */
	private double id_valor;

	/**
	 * Modifica el valor de acto.
	 *
	 * @param as_s asigna el valor a la propiedad acto
	 */
	public void setActo(String as_s)
	{
		is_acto = as_s;
	}

	/**
	 * Retorna el valor de acto.
	 *
	 * @return el valor de acto
	 */
	public String getActo()
	{
		return is_acto;
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
	 * Modifica el valor de liquidacion.
	 *
	 * @param as_s asigna el valor a la propiedad liquidacion
	 */
	public void setLiquidacion(double as_s)
	{
		id_liquidacion = as_s;
	}

	/**
	 * Retorna el valor de liquidacion.
	 *
	 * @return el valor de liquidacion
	 */
	public double getLiquidacion()
	{
		return id_liquidacion;
	}

	/**
	 * Modifica el valor de nroradica.
	 *
	 * @param as_s asigna el valor a la propiedad nroradica
	 */
	public void setNroradica(String as_s)
	{
		is_nroradica = as_s;
	}

	/**
	 * Retorna el valor de nroradica.
	 *
	 * @return el valor de nroradica
	 */
	public String getNroradica()
	{
		return is_nroradica;
	}

	/**
	 * Modifica el valor de tipo liq.
	 *
	 * @param as_s asigna el valor a la propiedad tipo liq
	 */
	public void setTipoLiq(String as_s)
	{
		is_tipoLiq = as_s;
	}

	/**
	 * Retorna el valor de tipo liq.
	 *
	 * @return el valor de tipo liq
	 */
	public String getTipoLiq()
	{
		return is_tipoLiq;
	}

	/**
	 * Modifica el valor de turno actualizado.
	 *
	 * @param as_s asigna el valor a la propiedad turno actualizado
	 */
	public void setTurnoActualizado(String as_s)
	{
		is_turnoActualizado = as_s;
	}

	/**
	 * Retorna el valor de turno actualizado.
	 *
	 * @return el valor de turno actualizado
	 */
	public String getTurno_actualizado()
	{
		return is_turnoActualizado;
	}

	/**
	 * Modifica el valor de valor.
	 *
	 * @param ad_d asigna el valor a la propiedad valor
	 */
	public void setValor(double ad_d)
	{
		id_valor = ad_d;
	}

	/**
	 * Retorna el valor de valor.
	 *
	 * @return el valor de valor
	 */
	public double getValor()
	{
		return id_valor;
	}
}
