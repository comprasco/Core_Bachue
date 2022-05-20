package com.bachue.snr.prosnr01.model.sdb.pgn;

import java.io.Serializable;


/**
 * Clase de abstraccion de la base de datos para la tabla SDB_PGN_PLANTILLA_COMUNICACION
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 16/06/2020
 */
public class PlantillaComunicacion extends PlantillaNotificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5958306685275613458L;

	/** Propiedad is canal. */
	private String is_canal;

	/** Propiedad is id autorizacion comunicacion. */
	private String is_idAutorizacionComunicacion;

	/** Propiedad is id plantilla comunicación. */
	private String is_idPlantillaComunicacion;

	/** Propiedad is id sub proceso. */
	private String is_idSubProceso;

	/** Propiedad is id tipo recepcion. */
	private String is_idTipoRecepcion;

	/** Propiedad is nombre de autorización comunicación. */
	private String is_nombreAutorizacionComunicacion;

	/** Propiedad is nombre canal. */
	private String is_nombreCanal;

	/** Propiedad is nombre etapa actual. */
	private String is_nombreEtapaActual;

	/** Propiedad is numbre subproceso. */
	private String is_nombreSubProceso;

	/** Propiedad is id etapa actual. */
	private long is_idEtapaActual;

	/**
	 * Modifica el valor de Canal.
	 *
	 * @param as_s de as s
	 */
	public void setCanal(String as_s)
	{
		is_canal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor canal.
	 *
	 * @return el valor de canal
	 */
	public String getCanal()
	{
		return is_canal;
	}

	/**
	 * Modifica el valor de IdAutorizacionComunicacion.
	 *
	 * @param as_s de as s
	 */
	public void setIdAutorizacionComunicacion(String as_s)
	{
		is_idAutorizacionComunicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id autorizacion comunicacion.
	 *
	 * @return el valor de id autorizacion comunicacion
	 */
	public String getIdAutorizacionComunicacion()
	{
		return is_idAutorizacionComunicacion;
	}

	/**
	 * Modifica el valor de IdEtapaActual.
	 *
	 * @param as_s de as s
	 */
	public void setIdEtapaActual(long as_s)
	{
		is_idEtapaActual = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa actual.
	 *
	 * @return el valor de id etapa actual
	 */
	public long getIdEtapaActual()
	{
		return is_idEtapaActual;
	}

	/**
	 * Modifica el valor de id plantilla comunicación
	 *
	 * @param as_s objeto que se le asigna a la propiedad
	 */
	public void setIdPlantillaComunicacion(String as_s)
	{
		is_idPlantillaComunicacion = as_s;
	}

	/**
	 * Retorna objeto o variable del id plantilla comunicación
	 *
	 * @return el valor de id plantilla comunicación
	 */
	public String getIdPlantillaComunicacion()
	{
		return is_idPlantillaComunicacion;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return el valor de id subproceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * Modifica el valor de IdTipoRecepcion.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoRecepcion(String as_s)
	{
		is_idTipoRecepcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo recepcion.
	 *
	 * @return el valor de id tipo recepcion
	 */
	public String getIdTipoRecepcion()
	{
		return is_idTipoRecepcion;
	}

	/**
	 * Método encargado de asignar un nuevo valor a la propiedad
	 * @param as_s Argumento nuevo valor de la propiedad.
	 */
	public void setNombreAutorizacionComunicacion(String as_s)
	{
		is_nombreAutorizacionComunicacion = as_s;
	}

	/**
	 * Método encargado de devolverl el valor de la propiedad.
	 * @return Objeto valor de la propiedad
	 */
	public String getNombreAutorizacionComunicacion()
	{
		return is_nombreAutorizacionComunicacion;
	}

	/**
	 * Modifica el valor de Canal.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCanal(String as_s)
	{
		is_nombreCanal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor canal.
	 *
	 * @return el valor de canal
	 */
	public String getNombreCanal()
	{
		return is_nombreCanal;
	}

	/**
	 * Método encargado de asignar un nuevo valor a la propiedad
	 * @param as_s Argumento nuevo valor de la propiedad.
	 */
	public void setNombreEtapaActual(String as_s)
	{
		is_nombreEtapaActual = as_s;
	}

	/**
	 * Método encargado de devolverl el valor de la propiedad.
	 * @return Objeto valor de la propiedad
	 */
	public String getNombreEtapaActual()
	{
		return is_nombreEtapaActual;
	}

	/**
	 * Método encargado de asignar un nuevo valor a la propiedad
	 * @param as_s Argumento nuevo valor de la propiedad.
	 */
	public void setNombreSubProceso(String as_s)
	{
		is_nombreSubProceso = as_s;
	}

	/**
	 * Método encargado de devolverl el valor de la propiedad.
	 * @return Objeto valor de la propiedad
	 */
	public String getNombreSubProceso()
	{
		return is_nombreSubProceso;
	}
}
