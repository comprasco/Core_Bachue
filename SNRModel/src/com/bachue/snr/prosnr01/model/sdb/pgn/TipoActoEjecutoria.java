package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_ACTO_EJECUTORIA.
 *
 * @author Julian Vaca
 */
public class TipoActoEjecutoria extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2336151540709750956L;

	/** Propiedad ii tiempo vencimiento. */
	private Integer ii_tiempoVencimiento;

	/** Propiedad ii version. */
	private Integer ii_version;

	/** Propiedad ii version acto. */
	private Integer ii_versionActo;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is habiles. */
	private String is_habiles;

	/** Propiedad is id tipo acto. */
	private String is_idTipoActo;

	/** Propiedad is id tipo documento. */
	private String is_idTipoDocumento;

	/** Propiedad is nombre tipo documento. */
	private String is_nombreTipoDocumento;

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
	 * Modifica el valor de Habiles.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setHabiles(String as_s)
	{
		this.is_habiles = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor habiles.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getHabiles()
	{
		return is_habiles;
	}

	/**
	 * Modifica el valor de IdTipoActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoActo(String as_s)
	{
		this.is_idTipoActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoActo()
	{
		return is_idTipoActo;
	}

	/**
	 * Modifica el valor de IdTipoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTipoDocumento(String as_s)
	{
		this.is_idTipoDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTipoDocumento()
	{
		return is_idTipoDocumento;
	}

	/**
	 * Modifica el valor de NombreTipoDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreTipoDocumento(String as_s)
	{
		is_nombreTipoDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre tipo documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreTipoDocumento()
	{
		return is_nombreTipoDocumento;
	}

	/**
	 * Modifica el valor de TiempoVencimiento.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setTiempoVencimiento(Integer ai_i)
	{
		this.ii_tiempoVencimiento = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor tiempo vencimiento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getTiempoVencimiento()
	{
		return ii_tiempoVencimiento;
	}

	/**
	 * Modifica el valor de Version.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setVersion(Integer ai_i)
	{
		this.ii_version = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getVersion()
	{
		return ii_version;
	}

	/**
	 * Modifica el valor de VersionActo.
	 *
	 * @param ai_i asigna el valor a la propiedad
	 */
	public void setVersionActo(Integer ai_i)
	{
		this.ii_versionActo = ai_i;
	}

	/**
	 * Retorna Objeto o variable de valor version acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Integer getVersionActo()
	{
		return ii_versionActo;
	}
}
