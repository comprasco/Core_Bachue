package com.bachue.snr.prosnr02.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades ReglaNegocio.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 6/07/2020
 */
public class ReglaNegocio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4161398810243274410L;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id regla negocio. */
	private String is_idReglaNegocio;

	/** Propiedad is id topologia regla. */
	private String is_idTopologiaRegla;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre procedimiento. */
	private String is_nombreProcedimiento;

	/** Propiedad is nombre topologia. */
	private String is_nombreTopologia;

	/** Propiedad is texto validacion. */
	private String is_textoValidacion;

	/** Propiedad is tipo regla. */
	private String is_tipoRegla;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo.
	 *
	 * @return el valor de activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s de as s
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return el valor de descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return el valor de fecha desde
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return el valor de fecha hasta
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de IdReglaNegocio.
	 *
	 * @param as_s de as s
	 */
	public void setIdReglaNegocio(String as_s)
	{
		is_idReglaNegocio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id regla negocio.
	 *
	 * @return el valor de id regla negocio
	 */
	public String getIdReglaNegocio()
	{
		return is_idReglaNegocio;
	}

	/**
	 * Modifica el valor de IdTopologiaRegla.
	 *
	 * @param as_s de as s
	 */
	public void setIdTopologiaRegla(String as_s)
	{
		is_idTopologiaRegla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id topologia regla.
	 *
	 * @return el valor de id topologia regla
	 */
	public String getIdTopologiaRegla()
	{
		return is_idTopologiaRegla;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param as_s de as s
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return el valor de nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de NombreProcedimiento.
	 *
	 * @param as_s de as s
	 */
	public void setNombreProcedimiento(String as_s)
	{
		is_nombreProcedimiento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre procedimiento.
	 *
	 * @return el valor de nombre procedimiento
	 */
	public String getNombreProcedimiento()
	{
		return is_nombreProcedimiento;
	}

	/**
	 * Modifica el valor de NombreTopologia.
	 *
	 * @param as_s de as s
	 */
	public void setNombreTopologia(String as_s)
	{
		is_nombreTopologia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre topología.
	 *
	 * @return el valor de nombre topología
	 */
	public String getNombreTopologia()
	{
		return is_nombreTopologia;
	}

	/**
	 * Modifica el valor de TextoValidacion.
	 *
	 * @param as_s de as s
	 */
	public void setTextoValidacion(String as_s)
	{
		is_textoValidacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor texto validacion.
	 *
	 * @return el valor de texto validacion
	 */
	public String getTextoValidacion()
	{
		return is_textoValidacion;
	}

	/**
	 * Modifica el valor de TipoRegla.
	 *
	 * @param as_a de as a
	 */
	public void setTipoRegla(String as_a)
	{
		is_tipoRegla = as_a;
	}

	/**
	 * Retorna Objeto o variable de valor tipo regla.
	 *
	 * @return el valor de tipo regla
	 */
	public String getTipoRegla()
	{
		return is_tipoRegla;
	}

	/** {@inheritdoc} */
	@Override
	public String toString()
	{
		return is_idReglaNegocio;
	}
}
