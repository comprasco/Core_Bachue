package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigInteger;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_CAUSAL_CORRECCION.
 *
 * @author garias
 */
public class CausalCorreccion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID       = -4779663181699123463L;
	
	/** Propiedad ibi id causal correccion. */
	private BigInteger        ibi_idCausalCorreccion;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is grupo causal. */
	private String            is_grupoCausal;
	
	/** Propiedad is nombre. */
	private String            is_nombre;
	
	/** Propiedad is observaciones. */
	private String            is_observaciones;
	
	/** Propiedad ib seleccionado. */
	private boolean           ib_seleccionado;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                               = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		this.is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de GrupoCausal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setGrupoCausal(String as_s)
	{
		this.is_grupoCausal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor grupo causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getGrupoCausal()
	{
		return is_grupoCausal;
	}

	/**
	 * Modifica el valor de IdCausalCorreccion.
	 *
	 * @param abi_bi asigna el valor a la propiedad
	 */
	public void setIdCausalCorreccion(BigInteger abi_bi)
	{
		this.ibi_idCausalCorreccion = abi_bi;
	}

	/**
	 * Retorna Objeto o variable de valor id causal correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public BigInteger getIdCausalCorreccion()
	{
		return ibi_idCausalCorreccion;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombre(String as_s)
	{
		this.is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservaciones(String as_s)
	{
		this.is_observaciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ab_b)
	{
		this.ib_seleccionado = ab_b;
	}

	/**
	 * Valida la propiedad seleccionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSeleccionado()
	{
		return ib_seleccionado;
	}
}
