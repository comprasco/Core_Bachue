package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TIPO_DOCUMENTO_PUBLICO.
 *
 * @author Julian Vaca
 */
public class TipoDocumentoPublico extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = -2347804656498734049L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id tipo documento. */
	private String            is_idTipoDocumento;
	
	/** Propiedad is nombre. */
	private String            is_nombre;
	
	/** Propiedad is tipo oficina. */
	private String            is_tipoOficina;

	/**
	 * Constructor por defecto.
	 */
	public TipoDocumentoPublico()
	{
	}

	/**
	 * Constructor con argumento de tipo documento publico.
	 *
	 * @param as_s de as s
	 */
	public TipoDocumentoPublico(String as_s)
	{
		setNombre(as_s);
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                           = as_s;
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
	 * Modifica el valor de TipoOficina.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setTipoOficina(String as_s)
	{
		this.is_tipoOficina = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo oficina.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getTipoOficina()
	{
		return is_tipoOficina;
	}
}
