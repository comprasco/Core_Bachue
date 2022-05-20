package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_COMPLETITUD_COMPLEMENTACION.
 *
 * @author Julian Vaca
 */
public class CompletitudComplementacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4472507415241103333L;

	/** Propiedad il id complementacion. */
	private Long il_idComplementacion;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad is id circulo registral destino. */
	private String is_idCirculoRegistralDestino;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is informacion Complementacion. */
	private String is_informacionComplementacion;

	/** Propiedad is justificacion. */
	private String is_justificacion;

	/** Propiedad is respuesta busqueda. */
	private String is_observacionesNoHallazgo;

	/** Propiedad is respuesta busqueda. */
	private String is_respuestaBusqueda;

	/**
	 * Modifica el valor de IdCirculoRegistralDestino.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoRegistralDestino(String as_s)
	{
		is_idCirculoRegistralDestino = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo registral destino.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoRegistralDestino()
	{
		return is_idCirculoRegistralDestino;
	}

	/**
	 * Modifica el valor de IdComplementacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdComplementacion(Long al_l)
	{
		il_idComplementacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id complementacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdComplementacion()
	{
		return il_idComplementacion;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdTurnoHistoria(Long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Sets the informacion complementacion.
	 *
	 * @param as_s the new informacion complementacion
	 */
	public void setInformacionComplementacion(String as_s)
	{
		is_informacionComplementacion = as_s;
	}

	/**
	 * Gets the informacion complementacion.
	 *
	 * @return the informacion complementacion
	 */
	public String getInformacionComplementacion()
	{
		return is_informacionComplementacion;
	}

	/**
	 * @return Retorna el valor de la propiedad is_justificacion
	 */
	public String getJjustificacion()
	{
		return is_justificacion;
	}

	/**
	 * @param is_justificacion Modifica el valor de la propiedad is_justificacion
	 */
	public void setJustificacion(String as_justificacion)
	{
		is_justificacion = as_justificacion;
	}

	/**
	 * Sets the observaciones no hallazgo.
	 *
	 * @param as_s the new observaciones no hallazgo
	 */
	public void setObservacionesNoHallazgo(String as_s)
	{
		is_observacionesNoHallazgo = as_s;
	}

	/**
	 * Gets the observaciones no hallazgo.
	 *
	 * @return the observaciones no hallazgo
	 */
	public String getObservacionesNoHallazgo()
	{
		return is_observacionesNoHallazgo;
	}

	/**
	 * Modifica el valor de RespuestaBusqueda.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRespuestaBusqueda(String as_s)
	{
		is_respuestaBusqueda = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor respuesta busqueda.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRespuestaBusqueda()
	{
		return is_respuestaBusqueda;
	}
}
