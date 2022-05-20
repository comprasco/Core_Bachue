package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Class que contiene todos las propiedades Complementacion.
 *
 * @author asantos
 */
public class Complementacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4506658495299144557L;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad is complementacion. */
	private String is_complementacion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id complementacion. */
	private String is_idComplementacion;

	/** Propiedad is tipo complementacion. */
	private String is_tipoComplementacion;

	/** Propiedad ib copiar editar. */
	private boolean ib_copiarEditar;

	/**
	 * Modifica el valor de complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad complementacion
	 */
	public void setComplementacion(String as_s)
	{
		is_complementacion = as_s;
	}

	/**
	 * Retorna el valor de complementacion.
	 *
	 * @return el valor de complementacion
	 */
	public String getComplementacion()
	{
		return is_complementacion;
	}

	/**
	 * Modifica el valor de copiar editar.
	 *
	 * @param ab_b asigna el valor a la propiedad copiar editar
	 */
	public void setCopiarEditar(boolean ab_b)
	{
		ib_copiarEditar = ab_b;
	}

	/**
	 * Valida la propiedad copiar editar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en copiar editar
	 */
	public boolean isCopiarEditar()
	{
		return ib_copiarEditar;
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
	 * Modifica el valor de id complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad id complementacion
	 */
	public void setIdComplementacion(String as_s)
	{
		is_idComplementacion = as_s;
	}

	/**
	 * Retorna el valor de id complementacion.
	 *
	 * @return el valor de id complementacion
	 */
	public String getIdComplementacion()
	{
		return is_idComplementacion;
	}

	/**
	 * Modifica el valor de id matricula.
	 *
	 * @param as_s asigna el valor a la propiedad id matricula
	 */
	public void setIdMatricula(Long as_s)
	{
		il_idMatricula = as_s;
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
	 * Modifica el valor de tipo complementacion.
	 *
	 * @param as_s asigna el valor a la propiedad tipo complementacion
	 */
	public void setTipoComplementacion(String as_s)
	{
		is_tipoComplementacion = as_s;
	}

	/**
	 * Retorna el valor de tipo complementacion.
	 *
	 * @return el valor de tipo complementacion
	 */
	public String getTipoComplementacion()
	{
		return is_tipoComplementacion;
	}
}
