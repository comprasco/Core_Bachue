package com.bachue.snr.prosnr01.model.view;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades TrazabilidadTurnoSirNotaDevolutiva.
 *
 * @author  Sebastian Sanchez
 */
public class TrazabilidadTurnoSirNotaDevolutiva implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2166715425188101966L;

	/** Propiedad id fecha. */
	private Date id_fecha;

	/** Propiedad is causal devolucion comentario. */
	private String is_causalDevolucionComentario;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is turno actualizado. */
	private String is_turnoActualizado;

	/** Propiedad id codigo. */
	private double id_codigo;

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return Retorna el valor de la propiedad fecha
	 */
	public Date getFecha()
	{
		return id_fecha;
	}

	/**
	 * Modifica el valor de fecha.
	 *
	 * @param ad_d de ad d
	 */
	public void setFecha(Date ad_d)
	{
		id_fecha = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor turno actualizado.
	 *
	 * @return Retorna el valor de la propiedad turnoActualizado
	 */
	public String getTurnoActualizado()
	{
		return is_turnoActualizado;
	}

	/**
	 * Modifica el valor de TurnoActualizado.
	 *
	 * @param as_s de as s
	 */
	public void setTurnoActualizado(String as_s)
	{
		is_turnoActualizado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return Retorna el valor de la propiedad tipo
	 */
	public String getTipo()
	{
		return is_tipo;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param as_s de as s
	 */
	public void setTipo(String as_s)
	{
		is_tipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo.
	 *
	 * @return Retorna el valor de la propiedad codigo
	 */
	public double getCodigo()
	{
		return id_codigo;
	}

	/**
	 * Modifica el valor de Codigo.
	 *
	 * @param ad_d de ad d
	 */
	public void setCodigo(double ad_d)
	{
		id_codigo = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor causal devolucion comentario.
	 *
	 * @return Retorna el valor de la propiedad causalDevolucionComentario
	 */
	public String getCausalDevolucionComentario()
	{
		return is_causalDevolucionComentario;
	}

	/**
	 * Modifica el valor de CausalDevolucionComentario.
	 *
	 * @param as_s de as s
	 */
	public void setCausalDevolucionComentario(String as_s)
	{
		is_causalDevolucionComentario = as_s;
	}
}
