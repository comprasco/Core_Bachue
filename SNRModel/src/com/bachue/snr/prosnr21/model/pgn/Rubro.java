package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_RUBRO
 * @author dbeltran
 *
 */
public class Rubro extends Auditoria implements Serializable
{
	/**Propiedad serialVersionUID*/
	private static final long serialVersionUID = 6841341258465626121L;

	/**Propiedad activo*/
	private String is_activo;

	/**Propiedad id rubro*/
	private String is_idRubro;

	/**Propiedad nombre*/
	private String is_nombre;

	/** Propiedad is rubro. */
	private String is_numeroRubro;

	/**
	 * @param Modifica el valor de la propiedad is_activo por is_activo
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idRubro por as_s
	 */
	public void setIdRubro(String as_s)
	{
		is_idRubro = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idRubro
	 */
	public String getIdRubro()
	{
		return is_idRubro;
	}

	/**
	 * @param Modifica el valor de la propiedad is_nombre por as_s
	 */
	public void setNombre(String as_s)
	{
		is_nombre = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombre
	 */
	public String getNombre()
	{
		return (is_nombre != null) ? is_nombre : new String();
	}

	/**
	 * @param is_rubro Modifica el valor de la propiedad is_rubro
	 */
	public void setNumeroRubro(String as_r)
	{
		is_numeroRubro = as_r;
	}

	/**
	 * @return Retorna el valor de la propiedad is_rubro
	 */
	public String getNumeroRubro()
	{
		return is_numeroRubro;
	}
}
