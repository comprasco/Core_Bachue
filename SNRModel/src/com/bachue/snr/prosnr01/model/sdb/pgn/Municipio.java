package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_MUNICIPIO.
 *
 * @author Julian Vaca
 */
public class Municipio extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6941168524436058626L;

	/** Propiedad id sdb pgn departamento. */
	private Departamento id_sdbPgnDepartamento;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id departamento. */
	private String is_idDepartamento;

	/** Propiedad is id municipio. */
	private String is_idMunicipio;

	/** Propiedad is id pais. */
	private String is_idPais;

	/** Propiedad is implementado nupre. */
	private String is_implementadoNupre;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is nombre departamento pantalla. */
	private String is_nombreDepartamentoPantalla;

	/** Propiedad is nombre municipio. */
	private String is_nombreMunicipio;

	/** Propiedad is nombre pais pantalla. */
	private String is_nombrePaisPantalla;

	/** Propiedad is zona riesgo. */
	private String is_zonaRiesgo;

	/**
	 * Constructor por defecto.
	 */
	public Municipio()
	{
	}

	/**
	 * Constructor que recive como parametro sin informacion.
	 *
	 * @param as_s de as s
	 */
	public Municipio(String as_s)
	{
		setNombre(as_s);
	}

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
	 * @return devuelve el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Modifica el valor de IdDepartamento.
	 *
	 * @param as_s de as s
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * Modifica el valor de IdMunicipio.
	 *
	 * @param as_s de as s
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * Modifica el valor de IdPais.
	 *
	 * @param as_s de as s
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id pais.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * Modifica el valor de ImplementadoNupre.
	 *
	 * @param as_s de as s
	 */
	public void setImplementadoNupre(String as_s)
	{
		is_implementadoNupre = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor implementado nupre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getImplementadoNupre()
	{
		return is_implementadoNupre;
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
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}

	/**
	 * Modifica el valor de NombreMunicipio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreMunicipio(String as_s)
	{
		is_nombreMunicipio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre municipio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreMunicipio()
	{
		return is_nombreMunicipio;
	}

	/**
	 * Modifica el valor de NombrePaisPantalla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombrePaisPantalla(String as_s)
	{
		is_nombrePaisPantalla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre pais pantalla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombrePaisPantalla()
	{
		return is_nombrePaisPantalla;
	}

	/**
	 * Modifica el valor de NombreDepartamentoPantalla.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setNombreDepartamentoPantalla(String as_s)
	{
		is_nombreDepartamentoPantalla = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre departamento pantalla.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreDepartamentoPantalla()
	{
		return is_nombreDepartamentoPantalla;
	}

	/**
	 * Modifica el valor de SdbPgnDepartamento.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setSdbPgnDepartamento(Departamento ad_d)
	{
		id_sdbPgnDepartamento = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor sdb pgn departamento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Departamento getSdbPgnDepartamento()
	{
		return id_sdbPgnDepartamento;
	}

	/**
	 * Modifica el valor de ZonaRiesgo.
	 *
	 * @param as_s de as s
	 */
	public void setZonaRiesgo(String as_s)
	{
		is_zonaRiesgo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor zona riesgo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getZonaRiesgo()
	{
		return is_zonaRiesgo;
	}
}
