package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.registro.Direccion;

import java.io.Serializable;

import java.util.Date;



/**
 * Class que contiene todos las propiedades DataConsultaPorCriterio.
 *
 * @author Julian Vaca
 */
public class DataConsultaPorCriterio extends Direccion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7631688879811375258L;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad is id anotacion. */
	private String is_idAnotacion;

	/** Propiedad ib asociar matricula. */
	private boolean ib_asociarMatricula;

	/** Propiedad ib asociar matricula. */
	private boolean ib_seleccione;

	/**
	 * Modifica el valor de asociar matricula.
	 *
	 * @param ab_b asigna el valor a la propiedad asociar matricula
	 */
	public void setAsociarMatricula(boolean ab_b)
	{
		ib_asociarMatricula = ab_b;
	}

	/**
	 * Valida la propiedad asociar matricula.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en asociar matricula
	 */
	public boolean isAsociarMatricula()
	{
		return ib_asociarMatricula;
	}

	/**
	 * Modifica el valor de fecha registro.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha registro
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna el valor de fecha registro.
	 *
	 * @return el valor de fecha registro
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de id anotacion.
	 *
	 * @param as_s asigna el valor a la propiedad id anotacion
	 */
	public void setIdAnotacion(String as_s)
	{
		is_idAnotacion = as_s;
	}

	/**
	 * Retorna el valor de id anotacion.
	 *
	 * @return el valor de id anotacion
	 */
	public String getIdAnotacion()
	{
		return is_idAnotacion;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad.
	 */
	public void setSeleccione(boolean ab_b)
	{
		ib_seleccione = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public boolean isSeleccione()
	{
		return ib_seleccione;
	}
}
