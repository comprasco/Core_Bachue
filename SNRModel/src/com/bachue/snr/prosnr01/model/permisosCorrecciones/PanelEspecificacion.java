package com.bachue.snr.prosnr01.model.permisosCorrecciones;

import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades PanelEspecificacion.
 *
 * @author garias
 */
public class PanelEspecificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1194870486185917207L;

	/** Constante is_idCausal. */
	public static final String is_idCausal = CausalCorreccionCommon.ESPECIFICACION;

	/** Propiedad ib anotacion cancela. */
	private boolean ib_anotacionCancela;

	/** Propiedad ib codigo acto. */
	private boolean ib_codigoActo;

	/** Propiedad ib comentario. */
	private boolean ib_comentario;

	/** Propiedad ib descripcion acto. */
	private boolean ib_descripcionActo;

	/** Propiedad ib seleccionado. */
	private boolean ib_seleccionado;

	/** Propiedad ib valor acto. */
	private boolean ib_valorActo;

	/**
	 * Modifica el valor de anotacion cancela.
	 *
	 * @param ab_b asigna el valor a la propiedad anotacion cancela
	 */
	public void setAnotacionCancela(boolean ab_b)
	{
		ib_anotacionCancela = ab_b;
	}

	/**
	 * Valida la propiedad anotacion cancela.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en anotacion cancela
	 */
	public boolean isAnotacionCancela()
	{
		return ib_anotacionCancela;
	}

	/**
	 * Modifica el valor de codigo acto.
	 *
	 * @param ab_b asigna el valor a la propiedad codigo acto
	 */
	public void setCodigoActo(boolean ab_b)
	{
		ib_codigoActo = ab_b;
	}

	/**
	 * Valida la propiedad codigo acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en codigo acto
	 */
	public boolean isCodigoActo()
	{
		return ib_codigoActo;
	}

	/**
	 * Modifica el valor de comentario.
	 *
	 * @param ab_b asigna el valor a la propiedad comentario
	 */
	public void setComentario(boolean ab_b)
	{
		ib_comentario = ab_b;
	}

	/**
	 * Valida la propiedad comentario.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en comentario
	 */
	public boolean isComentario()
	{
		return ib_comentario;
	}

	/**
	 * Modifica el valor de descripcion acto.
	 *
	 * @param ab_b asigna el valor a la propiedad descripcion acto
	 */
	public void setDescripcionActo(boolean ab_b)
	{
		ib_descripcionActo = ab_b;
	}

	/**
	 * Valida la propiedad descripcion acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en descripcion acto
	 */
	public boolean isDescripcionActo()
	{
		return ib_descripcionActo;
	}

	/**
	 * Modifica el valor de seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad seleccionado
	 */
	public void setSeleccionado(boolean ab_b)
	{
		ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en seleccionado
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}

	/**
	 * Modifica el valor de valor acto.
	 *
	 * @param ab_b asigna el valor a la propiedad valor acto
	 */
	public void setValorActo(boolean ab_b)
	{
		ib_valorActo = ab_b;
	}

	/**
	 * Valida la propiedad valor acto.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valor acto
	 */
	public boolean isValorActo()
	{
		return ib_valorActo;
	}
}
