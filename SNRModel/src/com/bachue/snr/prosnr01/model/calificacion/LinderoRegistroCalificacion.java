package com.bachue.snr.prosnr01.model.calificacion;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;

import java.io.Serializable;

import java.util.Collection;



/**
 * Class que contiene todos las propiedades LinderoRegistroCalificacion.
 *
 * @author ccalderon
 */
public class LinderoRegistroCalificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2826875711041968214L;

	/** Propiedad iclp lindero predios. */
	private Collection<AccLinderoPredio> iclp_linderoPredios;

	/** Propiedad il id matricula. */
	private Long il_idMatricula;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is lindero. */
	private String is_lindero;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is tipo lindero. */
	private String is_tipoLindero;

	/** Propiedad ib nuevo lindero. */
	private boolean ib_nuevoLindero;

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
	 * Modifica el valor de lindero.
	 *
	 * @param lindero asigna el valor a la propiedad lindero
	 */
	public void setLindero(String lindero)
	{
		is_lindero = lindero;
	}

	/**
	 * Retorna el valor de lindero.
	 *
	 * @return el valor de lindero
	 */
	public String getLindero()
	{
		return is_lindero;
	}

	/**
	 * Modifica el valor de lindero predios.
	 *
	 * @param linderoPredios asigna el valor a la propiedad lindero predios
	 */
	public void setLinderoPredios(Collection<AccLinderoPredio> linderoPredios)
	{
		iclp_linderoPredios = linderoPredios;
	}

	/**
	 * Retorna el valor de lindero predios.
	 *
	 * @return el valor de lindero predios
	 */
	public Collection<AccLinderoPredio> getLinderoPredios()
	{
		return iclp_linderoPredios;
	}

	/**
	 * Modifica el valor de nuevo lindero.
	 *
	 * @param ab_b asigna el valor a la propiedad nuevo lindero
	 */
	public void setNuevoLindero(boolean ab_b)
	{
		ib_nuevoLindero = ab_b;
	}

	/**
	 * Valida la propiedad nuevo lindero.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en nuevo lindero
	 */
	public boolean isNuevoLindero()
	{
		return ib_nuevoLindero;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de tipo lindero.
	 *
	 * @param as_s asigna el valor a la propiedad tipo lindero
	 */
	public void setTipoLindero(String as_s)
	{
		is_tipoLindero = as_s;
	}

	/**
	 * Retorna el valor de tipo lindero.
	 *
	 * @return el valor de tipo lindero
	 */
	public String getTipoLindero()
	{
		return is_tipoLindero;
	}
}
