package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;


/**
 * Lógica de modelo Etapa siendo una abstracción de la tabla SDB_PGN_LINEA_PRODUCCION en la base de datos.
 *
 * @author Heiner Castañeda
 */
public class LineaProduccion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8480787064407271514L;

	/** Propiedad lcta tipos actos. */
	private Collection<TipoActo> lcta_tiposActos;

	/** Propiedad il id etapa. */
	private Long il_idEtapa;

	/** Propiedad is id linea produccion. */
	private String is_idLineaProduccion;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nomenclatura. */
	private String is_nomenclatura;

	/** Propiedad il equivalencia turno. */
	private long il_equivalenciaTurno;

	/** Propiedad il peso. */
	private long il_peso;

	/**
	 * Modifica el valor de EquivalenciaTurno.
	 *
	 * @param equivalenciaTurno asigna el valor a la propiedad
	 */
	public void setEquivalenciaTurno(long equivalenciaTurno)
	{
		il_equivalenciaTurno = equivalenciaTurno;
	}

	/**
	 * Retorna Objeto o variable de valor equivalencia turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getEquivalenciaTurno()
	{
		return il_equivalenciaTurno;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param al_l de al l
	 */
	public void setIdEtapa(Long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return Retorna el valor de la propiedad idEtapa
	 */
	public Long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * Modifica el valor de IdLineaProduccion.
	 *
	 * @param idLineaProduccion asigna el valor a la propiedad
	 */
	public void setIdLineaProduccion(String idLineaProduccion)
	{
		is_idLineaProduccion = idLineaProduccion;
	}

	/**
	 * Retorna Objeto o variable de valor id linea produccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdLineaProduccion()
	{
		return is_idLineaProduccion;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		is_nombre = nombre;
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
	 * Modifica el valor de Nomenclatura.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNomenclatura(String as_s)
	{
		is_nomenclatura = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nomenclatura.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNomenclatura()
	{
		return is_nomenclatura;
	}

	/**
	 * Modifica el valor de Peso.
	 *
	 * @param peso asigna el valor a la propiedad
	 */
	public void setPeso(long peso)
	{
		il_peso = peso;
	}

	/**
	 * Retorna Objeto o variable de valor peso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getPeso()
	{
		return il_peso;
	}

	/**
	 * @param ac_c Modifica el valor de la propiedad tiposActos
	 */
	public void setTiposActos(Collection<TipoActo> ac_c)
	{
		lcta_tiposActos = ac_c;
	}

	/**
	 * @return Retorna el valor de la propiedad tiposActos
	 */
	public Collection<TipoActo> getTiposActos()
	{
		return lcta_tiposActos;
	}
}
