package com.bachue.snr.prosnr16.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla SDB_ACC_PARAMETROS_MENSAJE.
 *
 * @author Sebastian Sanchez
 */
public class ParametrosMensaje extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -5814942630247053162L;

	/** Propiedad is id mensaje. */
	private String is_idMensaje;

	/** Propiedad is id parametro. */
	private String is_idParametro;

	/** Propiedad is llave. */
	private String is_llave;

	/** Propiedad is tipo. */
	private String is_tipo;

	/** Propiedad is valor. */
	private String is_valor;

	/**
	 * Constructor por defecto.
	 */
	public ParametrosMensaje()
	{
	}

	/**
	 * Instancia un nuevo objeto parametros mensaje.
	 *
	 * @param as_codigo de as codigo
	 * @param as_criterio de as criterio
	 * @param as_tipo de as tipo
	 */
	public ParametrosMensaje(String as_codigo, String as_criterio, String as_tipo)
	{
		is_llave     = as_codigo;
		is_valor     = as_criterio;
		is_tipo      = as_tipo;
	}

	/**
	 * Modifica el valor de IdMensaje.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMensaje(String as_s)
	{
		is_idMensaje = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id mensaje.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMensaje()
	{
		return is_idMensaje;
	}

	/**
	 * Modifica el valor de IdParametro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdParametro(String as_s)
	{
		is_idParametro = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id parametro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdParametro()
	{
		return is_idParametro;
	}

	/**
	 * Modifica el valor de Llave.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLlave(String as_s)
	{
		is_llave = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor llave.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLlave()
	{
		return is_llave;
	}

	/**
	 * Modifica el valor de Tipo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipo(String as_s)
	{
		is_tipo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipo()
	{
		return is_tipo;
	}

	/**
	 * Modifica el valor de Valor.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setValor(String as_s)
	{
		is_valor = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getValor()
	{
		return is_valor;
	}
}
