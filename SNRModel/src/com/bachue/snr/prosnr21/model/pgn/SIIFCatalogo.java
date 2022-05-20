package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_SIIF_CATALOGO.
 *
 * @author Gabriel Arias
 */
public class SIIFCatalogo extends Auditoria implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4276694616578023798L;

	/** The activo. */
	private String is_activo;

	/** The archivo. */
	private String is_archivo;

	/** The codigo. */
	private String is_codigo;

	/** The descripcion. */
	private String is_descripcion;

	/** The id siif catalogo. */
	private String is_idSiifCatalogo;

	/** The nemotecnico. */
	private String is_nemotecnico;

	/** The nombre. */
	private String is_nombre;

	/**
	 * Sets the activo.
	 *
	 * @param as_activo the new activo
	 */
	public void setActivo(String as_activo)
	{
		is_activo = as_activo;
	}

	/**
	 * Gets the activo.
	 *
	 * @return the activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Sets the archivo.
	 *
	 * @param as_archivo the new archivo
	 */
	public void setArchivo(String as_archivo)
	{
		is_archivo = as_archivo;
	}

	/**
	 * Gets the archivo.
	 *
	 * @return the archivo
	 */
	public String getArchivo()
	{
		return is_archivo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param as_codigo the new codigo
	 */
	public void setCodigo(String as_codigo)
	{
		is_codigo = as_codigo;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return is_codigo;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param as_descripcion the new descripcion
	 */
	public void setDescripcion(String as_descripcion)
	{
		is_descripcion = as_descripcion;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Sets the id siif catalogo.
	 *
	 * @param as_idSiifCatalogo the new id siif catalogo
	 */
	public void setIdSiifCatalogo(String as_idSiifCatalogo)
	{
		is_idSiifCatalogo = as_idSiifCatalogo;
	}

	/**
	 * Gets the id siif catalogo.
	 *
	 * @return the id siif catalogo
	 */
	public String getIdSiifCatalogo()
	{
		return is_idSiifCatalogo;
	}

	/**
	 * Sets the nemotecnico.
	 *
	 * @param as_nemotecnico the new nemotecnico
	 */
	public void setNemotecnico(String as_nemotecnico)
	{
		is_nemotecnico = as_nemotecnico;
	}

	/**
	 * Gets the nemotecnico.
	 *
	 * @return the nemotecnico
	 */
	public String getNemotecnico()
	{
		return is_nemotecnico;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param as_nombre the new nombre
	 */
	public void setNombre(String as_nombre)
	{
		is_nombre = as_nombre;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
