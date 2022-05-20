package com.bachue.snr.prosnr01.model.sdb.png;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de ComunidadesEtnicas.
 *
 * @author Sebastian Sanchez
 */
public class ComunidadesEtnicas extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7567605296002741643L;

	/**  Propiedad is nombre comunidad. */
	private String is_nombreComunidad;

	/** Propiedad is numero documento. */
	private String is_numeroDocumento;

	/** Propiedad is tipo documento. */
	private String is_tipoDocumento;

	/** Propiedad ib editable. */
	private boolean ib_editable;

	/** Propiedad ib editado. */
	private boolean ib_editado;

	/** Propiedad ii id comunidad. */
	private int ii_idComunidad;

	/**
	 * Constructor por defecto.
	 */
	public ComunidadesEtnicas()
	{
	}

	/**
	 * Retorna Objeto o variable de valor id comunidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public int getIdComunidad()
	{
		return ii_idComunidad;
	}

	/**
	 * Modifica el valor de IdComunidad.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setIdComunidad(int ai_i)
	{
		ii_idComunidad = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor nombre comunidad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreComunidad()
	{
		return is_nombreComunidad;
	}

	/**
	 * Modifica el valor de NombreComunidad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreComunidad(String as_s)
	{
		is_nombreComunidad = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoDocumento()
	{
		return is_tipoDocumento;
	}

	/**
	 * Modifica el valor de TipoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoDocumento(String as_s)
	{
		is_tipoDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor numero documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNumeroDocumento()
	{
		return is_numeroDocumento;
	}

	/**
	 * Modifica el valor de NumeroDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNumeroDocumento(String as_s)
	{
		is_numeroDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor editable.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean getEditable()
	{
		return ib_editable;
	}

	/**
	 * Modifica el valor de Editable.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEditable(boolean ab_b)
	{
		ib_editable = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor editado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean getEditado()
	{
		return ib_editado;
	}

	/**
	 * Modifica el valor de Editado.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setEditado(boolean ab_b)
	{
		ib_editado = ab_b;
	}
}
