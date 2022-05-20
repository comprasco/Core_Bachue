package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Abstraccion de tabla de SDB_PGN_PLANTILLA_NOTIFICACION.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 14/04/2020
 */
public class PlantillaNotificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8649965044889321774L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is clasificacion. */
	private String is_clasificacion;

	/** Propiedad is id P lantilla. */
	private String is_idPlantilla;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is nombre de la etapa.*/
	private String is_nombreEtapa;

	/** Propiedad is nombre del motivo.*/
	private String is_nombreMotivo;

	/** Propiedad is nombre de la plantilla.*/
	private String is_nombrePlantilla;

	/** Propiedad is nombre del proceso.*/
	private String is_nombreProceso;

	/** Propiedad is tramite. */
	private String is_tramite;

	/** Propiedad is nombre del activo*/
	private boolean is_nombreActivo;

	/** Propiedad il id etapa anterior. */
	private long il_idEtapaAnterior;

	/** Propiedad il id motivo. */
	private long il_idMotivo;

	/**
	 * Constructor por defecto.
	 */
	public PlantillaNotificacion()
	{
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
 * @return Retorna el valor de la propiedad activo
 */
	public String getActivo()
	{
		return is_activo;
	}

/**
 * Modifica el valor de Clasificacion.
 *
 * @param as_s de as s
 */
	public void setClasificacion(String as_s)
	{
		is_clasificacion = as_s;
	}

/**
 * Retorna Objeto o variable de valor clasificacion.
 *
 * @return Retorna el valor de la propiedad clasificacion
 */
	public String getClasificacion()
	{
		return is_clasificacion;
	}

/**
 * Modifica el valor de IdEtapaAnterior.
 *
 * @param al_l de al l
 */
	public void setIdEtapaAnterior(long al_l)
	{
		il_idEtapaAnterior = al_l;
	}

/**
 * Retorna Objeto o variable de valor id etapa anterior.
 *
 * @return Retorna el valor de la propiedad idEtapaAnterior
 */
	public long getIdEtapaAnterior()
	{
		return il_idEtapaAnterior;
	}

/**
 * Modifica el valor de IdMotivo.
 *
 * @param al_l de al l
 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

/**
 * Retorna Objeto o variable de valor id motivo.
 *
 * @return Retorna el valor de la propiedad idMotivo
 */
	public long getIdMotivo()
	{
		return il_idMotivo;
	}

/**
 * Modifica el valor de IdPLantilla.
 *
 * @param as_s de as s
 */
	public void setIdPlantilla(String as_s)
	{
		is_idPlantilla = as_s;
	}

/**
 * Retorna Objeto o variable de valor id P lantilla.
 *
 * @return Retorna el valor de la propiedad idPLantilla
 */
	public String getIdPlantilla()
	{
		return is_idPlantilla;
	}

/**
 * Modifica el valor de IdProceso.
 *
 * @param as_s de as s
 */
	public void setIdProceso(String as_s)
	{
		is_idProceso = as_s;
	}

/**
 * Retorna Objeto o variable de valor id proceso.
 *
 * @return Retorna el valor de la propiedad idProceso
 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setNombreActivo(boolean as_s)
	{
		is_nombreActivo = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public boolean getNombreActivo()
	{
		return is_nombreActivo;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setNombreEtapa(String as_s)
	{
		is_nombreEtapa = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public String getNombreEtapa()
	{
		return is_nombreEtapa;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setNombreMotivo(String as_s)
	{
		is_nombreMotivo = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public String getNombreMotivo()
	{
		return is_nombreMotivo;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setNombrePlantilla(String as_s)
	{
		is_nombrePlantilla = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public String getNombrePlantilla()
	{
		return is_nombrePlantilla;
	}

	/**
	 * Método encargado de modificar el valor de la propiedad.
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setNombreProceso(String as_s)
	{
		is_nombreProceso = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 * @return Retorna el valor de la propiedad.
	 */
	public String getNombreProceso()
	{
		return is_nombreProceso;
	}

/**
 * Modifica el valor de Tramite.
 *
 * @param as_s de as s
 */
	public void setTramite(String as_s)
	{
		is_tramite = as_s;
	}

/**
 * Retorna Objeto o variable de valor tramite.
 *
 * @return Retorna el valor de la propiedad tramite
 */
	public String getTramite()
	{
		return is_tramite;
	}
}
