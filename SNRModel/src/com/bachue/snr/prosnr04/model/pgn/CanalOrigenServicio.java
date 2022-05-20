package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
 *
 * @author Carlos Calderón
 */
public class CanalOrigenServicio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID             = -8306224797829819168L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is codigo canal origen servicio. */
	private String            is_codigoCanalOrigenServicio;
	
	/** Propiedad is id canal origen servicio. */
	private String            is_idCanalOrigenServicio;
	
	/** Propiedad is nombre canal origen servicio. */
	private String            is_nombreCanalOrigenServicio;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                          = as_s;
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
	 * Modifica el valor de CodigoCanalOrigenServicio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCodigoCanalOrigenServicio(String as_s)
	{
		is_codigoCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor codigo canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCodigoCanalOrigenServicio()
	{
		return is_codigoCanalOrigenServicio;
	}

	/**
	 * Modifica el valor de IdCanalOrigenServicio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCanalOrigenServicio(String as_s)
	{
		is_idCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCanalOrigenServicio()
	{
		return is_idCanalOrigenServicio;
	}

	/**
	 * Modifica el valor de NombreCanalOrigenServicio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreCanalOrigenServicio(String as_s)
	{
		is_nombreCanalOrigenServicio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre canal origen servicio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreCanalOrigenServicio()
	{
		return is_nombreCanalOrigenServicio;
	}
}
