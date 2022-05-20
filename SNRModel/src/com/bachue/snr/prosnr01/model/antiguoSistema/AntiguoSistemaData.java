package com.bachue.snr.prosnr01.model.antiguoSistema;

import java.io.Serializable;


/**
 * Class que contiene todos las propiedades AntiguoSistemaData.
 *
 * @author Julian Vaca
 */
public class AntiguoSistemaData implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7178731429633098346L;

	/** Propiedad il anio. */
	private Long il_anio;

	/** Propiedad il folio. */
	private Long il_folio;

	/** Propiedad il libro. */
	private Long il_libro;

	/** Propiedad il tomo. */
	private Long il_tomo;

	/** Propiedad is adquisicion predio. */
	private String is_adquisicionPredio;

	/** Propiedad is circulo registral. */
	private String is_circuloRegistral;

	/** Propiedad is departamento. */
	private String is_departamento;

	/** Propiedad is municipio. */
	private String is_municipio;

	/** Propiedad is nombre libro. */
	private String is_nombreLibro;

	/** Propiedad is nombre predio. */
	private String is_nombrePredio;

	/** Propiedad is observaciones firma libro. */
	private String is_observacionesFirmaLibro;

	/** Propiedad is pais. */
	private String is_pais;

	/** Propiedad is partida. */
	private String is_partida;

	/** Propiedad is tipo predio. */
	private String is_tipoPredio;

	/**
	 * Modifica el valor de adquisicion predio.
	 *
	 * @param as_s asigna el valor a la propiedad adquisicion predio
	 */
	public void setAdquisicionPredio(String as_s)
	{
		is_adquisicionPredio = as_s;
	}

	/**
	 * Retorna el valor de adquisicion predio.
	 *
	 * @return el valor de adquisicion predio
	 */
	public String getAdquisicionPredio()
	{
		return is_adquisicionPredio;
	}

	/**
	 * Modifica el valor de anio.
	 *
	 * @param anio asigna el valor a la propiedad anio
	 */
	public void setAnio(Long anio)
	{
		this.il_anio = anio;
	}

	/**
	 * Retorna el valor de anio.
	 *
	 * @return el valor de anio
	 */
	public Long getAnio()
	{
		return il_anio;
	}

	/**
	 * Modifica el valor de circulo registral.
	 *
	 * @param as_s asigna el valor a la propiedad circulo registral
	 */
	public void setCirculoRegistral(String as_s)
	{
		is_circuloRegistral = as_s;
	}

	/**
	 * Retorna el valor de circulo registral.
	 *
	 * @return el valor de circulo registral
	 */
	public String getCirculoRegistral()
	{
		return is_circuloRegistral;
	}

	/**
	 * Modifica el valor de departamento.
	 *
	 * @param as_s asigna el valor a la propiedad departamento
	 */
	public void setDepartamento(String as_s)
	{
		is_departamento = as_s;
	}

	/**
	 * Retorna el valor de departamento.
	 *
	 * @return el valor de departamento
	 */
	public String getDepartamento()
	{
		return is_departamento;
	}

	/**
	 * Modifica el valor de folio.
	 *
	 * @param al_l asigna el valor a la propiedad folio
	 */
	public void setFolio(Long al_l)
	{
		il_folio = al_l;
	}

	/**
	 * Retorna el valor de folio.
	 *
	 * @return el valor de folio
	 */
	public Long getFolio()
	{
		return il_folio;
	}

	/**
	 * Modifica el valor de libro.
	 *
	 * @param al_l asigna el valor a la propiedad libro
	 */
	public void setLibro(Long al_l)
	{
		il_libro = al_l;
	}

	/**
	 * Retorna el valor de libro.
	 *
	 * @return el valor de libro
	 */
	public Long getLibro()
	{
		return il_libro;
	}

	/**
	 * Modifica el valor de municipio.
	 *
	 * @param as_s asigna el valor a la propiedad municipio
	 */
	public void setMunicipio(String as_s)
	{
		is_municipio = as_s;
	}

	/**
	 * Retorna el valor de municipio.
	 *
	 * @return el valor de municipio
	 */
	public String getMunicipio()
	{
		return is_municipio;
	}

	/**
	 * Modifica el valor de nombre libro.
	 *
	 * @param as_s asigna el valor a la propiedad nombre libro
	 */
	public void setNombreLibro(String as_s)
	{
		is_nombreLibro = as_s;
	}

	/**
	 * Retorna el valor de nombre libro.
	 *
	 * @return el valor de nombre libro
	 */
	public String getNombreLibro()
	{
		return is_nombreLibro;
	}

	/**
	 * Modifica el valor de nombre predio.
	 *
	 * @param as_s asigna el valor a la propiedad nombre predio
	 */
	public void setNombrePredio(String as_s)
	{
		is_nombrePredio = as_s;
	}

	/**
	 * Retorna el valor de nombre predio.
	 *
	 * @return el valor de nombre predio
	 */
	public String getNombrePredio()
	{
		return is_nombrePredio;
	}

	/**
	 * Modifica el valor de Observaciones firma libro.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setObservacionesFirmaLibro(String as_s)
	{
		is_observacionesFirmaLibro = as_s;
	}

	/**
	 * Get observaciones firma libro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getObservacionesFirmaLibro()
	{
		return is_observacionesFirmaLibro;
	}

	/**
	 * Modifica el valor de pais.
	 *
	 * @param as_s asigna el valor a la propiedad pais
	 */
	public void setPais(String as_s)
	{
		is_pais = as_s;
	}

	/**
	 * Retorna el valor de pais.
	 *
	 * @return el valor de pais
	 */
	public String getPais()
	{
		return is_pais;
	}

	/**
	 * Modifica el valor de partida.
	 *
	 * @param as_s asigna el valor a la propiedad partida
	 */
	public void setPartida(String as_s)
	{
		is_partida = as_s;
	}

	/**
	 * Retorna el valor de partida.
	 *
	 * @return el valor de partida
	 */
	public String getPartida()
	{
		return is_partida;
	}

	/**
	 * Modifica el valor de tipo predio.
	 *
	 * @param as_s asigna el valor a la propiedad tipo predio
	 */
	public void setTipoPredio(String as_s)
	{
		is_tipoPredio = as_s;
	}

	/**
	 * Retorna el valor de tipo predio.
	 *
	 * @return el valor de tipo predio
	 */
	public String getTipoPredio()
	{
		return is_tipoPredio;
	}

	/**
	 * Modifica el valor de tomo.
	 *
	 * @param al_l asigna el valor a la propiedad tomo
	 */
	public void setTomo(Long al_l)
	{
		il_tomo = al_l;
	}

	/**
	 * Retorna el valor de tomo.
	 *
	 * @return el valor de tomo
	 */
	public Long getTomo()
	{
		return il_tomo;
	}
}
