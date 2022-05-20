package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_CAUSAL_MAYOR_VALOR.
 *
 * @author Julian Vaca
 */
public class CausalMayorValor extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long            serialVersionUID       = -8032708665754969394L;
	
	/** Propiedad iccmv causal mayor valor. */
	private Collection<CausalMayorValor> iccmv_causalMayorValor;
	
	/** Propiedad is accion. */
	private String                       is_accion;
	
	/** Propiedad is activo. */
	private String                       is_activo;
	
	/** Propiedad is descripcion. */
	private String                       is_descripcion;
	
	/** Propiedad is nombre. */
	private String                       is_nombre;
	
	/** Propiedad ib seleccionado. */
	private boolean                      ib_seleccionado;
	
	/** Propiedad il id causal mayor valor. */
	private long                         il_idCausalMayorValor;

	/**
	 * Modifica el valor de Accion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAccion(String as_s)
	{
		is_accion                                               = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor accion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAccion()
	{
		return is_accion;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo = as_s;
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
	 * Modifica el valor de CausalMayorValor.
	 *
	 * @param accmv_causalMayorValor de accmv causal mayor valor
	 */
	public void setCausalMayorValor(Collection<CausalMayorValor> accmv_causalMayorValor)
	{
		iccmv_causalMayorValor = accmv_causalMayorValor;
	}

	/**
	 * Retorna Objeto o variable de valor causal mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CausalMayorValor> getCausalMayorValor()
	{
		return iccmv_causalMayorValor;
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
	 * Modifica el valor de IdCausalMayorValor.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdCausalMayorValor(long al_l)
	{
		this.il_idCausalMayorValor = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id causal mayor valor.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdCausalMayorValor()
	{
		return il_idCausalMayorValor;
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
	 * Modifica el valor de Seleccionado.
	 *
	 * @param ib_b asigna el valor a la propiedad
	 */
	public void setSeleccionado(boolean ib_b)
	{
		this.ib_seleccionado = ib_b;
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
