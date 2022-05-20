package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_CAMBIO_ESTADO_PREDIO.
 *
 * @author Julian Vaca
 */
public class CambioEstadoPredio extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = -4764503405920357480L;
	
	/** Propiedad il id matricula. */
	private Long              il_idMatricula;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is estado predio. */
	private String            is_estadoPredio;
	
	/** Propiedad is id cambio estado predio. */
	private String            is_idCambioEstadoPredio;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is justificacion cambio. */
	private String            is_justificacionCambio;
	
	/** Propiedad is motivo cambio estado. */
	private String            is_motivoCambioEstado;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo                                     = as_s;
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
	 * Modifica el valor de EstadoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstadoPredio(String as_s)
	{
		is_estadoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoPredio()
	{
		return is_estadoPredio;
	}

	/**
	 * Modifica el valor de IdCambioEstadoPredio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCambioEstadoPredio(String as_s)
	{
		is_idCambioEstadoPredio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id cambio estado predio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCambioEstadoPredio()
	{
		return is_idCambioEstadoPredio;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l de al l
	 */
	public void setIdMatricula(Long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatricula()
	{
		return il_idMatricula;
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
	 * Modifica el valor de JustificacionCambio.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setJustificacionCambio(String as_s)
	{
		is_justificacionCambio = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor justificacion cambio.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getJustificacionCambio()
	{
		return is_justificacionCambio;
	}

	/**
	 * Modifica el valor de MotivoCambioEstado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMotivoCambioEstado(String as_s)
	{
		is_motivoCambioEstado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo cambio estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivoCambioEstado()
	{
		return is_motivoCambioEstado;
	}
}
