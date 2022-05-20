package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_LIBRO_TESTAMENTO.
 *
 * @author dbeltran
 */
public class LibroTestamento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6899814735767411977L;

	/** Propiedad il ano. */
	private Long il_ano;

	/** Propiedad il folio. */
	private Long il_folio;

	/** Propiedad il libro. */
	private Long il_libro;

	/** Propiedad il libro ant sistema. */
	private Long il_libroAntSistema;

	/** Propiedad il tomo. */
	private Long il_tomo;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nombre libro ant sistema. */
	private String is_nombreLibroAntSistema;

	/**
	 * Metodo de obtenci�n del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Long getFolio()
	{
		return il_folio;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad.
	 *
	 * @param al_l con el valor
	 */
	public void setFolio(Long al_l)
	{
		il_folio = al_l;
	}

	/**
	 * M�todo de obtenci�n del valor de la prtopiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Long getLibro()
	{
		return il_libro;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad.
	 *
	 * @param al_l con el valor a asignar
	 */
	public void setLibro(Long al_l)
	{
		il_libro = al_l;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad.
	 *
	 * @return con el valor a asignar
	 */
	public Long getTomo()
	{
		return il_tomo;
	}

	/**
	 * M�todo de asignai�n del valor de la propiedad.
	 *
	 * @param al_l con el valor de la propiedad
	 */
	public void setTomo(Long al_l)
	{
		il_tomo = al_l;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Long getAno()
	{
		return il_ano;
	}

	/**
	 * M�todo de asiganci�n con el valor de la propiedad.
	 *
	 * @param al_l con el valor a asignar
	 */
	public void setAno(Long al_l)
	{
		il_ano = al_l;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * M�todo de asignaci�n del valor  de la propiedad.
	 *
	 * @param as_s con el valor a asignar
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public Long getLibroAntSistema()
	{
		return il_libroAntSistema;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad.
	 *
	 * @param al_l con el valor a asignar
	 */
	public void setLibroAntSistema(Long al_l)
	{
		il_libroAntSistema = al_l;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * M�todo de asignaci�n del valor  de la propiedad.
	 *
	 * @param as_s con el valor a asignar
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad.
	 *
	 * @return con el valor de la propiedad
	 */
	public String getNombreLibroAntSistema()
	{
		return is_nombreLibroAntSistema;
	}

	/**
	 * M�todo de asignaci�n del valor  de la propiedad.
	 *
	 * @param as_s con el valor a asignar
	 */
	public void setNombreLibroAntSistema(String as_s)
	{
		is_nombreLibroAntSistema = as_s;
	}
}
