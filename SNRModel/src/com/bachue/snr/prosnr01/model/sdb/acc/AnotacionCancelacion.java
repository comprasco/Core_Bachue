package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_ANOTACION_CANCELACION.
 *
 * @author mblanco
 */
public class AnotacionCancelacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4769648285846276266L;

	/** Propiedad il id anotacion. */
	private Long il_idAnotacion;

	/** Propiedad il id anotacion 1. */
	private Long il_idAnotacion1;

	/** Propiedad is anotacion 1 String. */
	private String is_anotacion1String;

	/** Propiedad is descripcion. */
	private String is_descripcion;

	/** Propiedad is id anotacion cancelacion. */
	private String is_idAnotacionCancelacion;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad ib solicitud. */
	private boolean ib_solicitud;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/** Propiedad il id turno historia. */
	private long il_idTurnoHistoria;

	/**
	 * Constructor por defecto.
	 */
	public AnotacionCancelacion()
	{
	}

	/**
	 * Constructor que recibe como parametro un objeto de anotacion cancelacion.
	 *
	 * @param aac_ac objeto anotacion cancelacion
	 */
	public AnotacionCancelacion(com.bachue.snr.prosnr01.model.sdb.bng.AnotacionCancelacion aac_ac)
	{
		if(aac_ac != null)
		{
			setIdCirculo(aac_ac.getIdCirculo());
			setIdMatricula(aac_ac.getIdMatricula());
			setIdAnotacion(NumericUtils.getLongWrapper(aac_ac.getIdAnotacion()));
			setIdAnotacion1(NumericUtils.getLongWrapper(aac_ac.getIdAnotacion1()));
			setDescripcion(aac_ac.getDescripcion());
		}
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setAnotacion1String(String as_s)
	{
		is_anotacion1String = as_s;
	}

	/**
	 * Retorna el valor de la propiedad
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getAnotacion1String()
	{
		return is_anotacion1String;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		is_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return is_descripcion;
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(Long al_l)
	{
		il_idAnotacion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdAnotacion()
	{
		return il_idAnotacion;
	}

	/**
	 * Modifica el valor de IdAnotacion1.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion1(Long al_l)
	{
		il_idAnotacion1 = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdAnotacion1()
	{
		return il_idAnotacion1;
	}

	/**
	 * Modifica el valor de IdAnotacionCancelacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacionCancelacion(String as_s)
	{
		is_idAnotacionCancelacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion cancelacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnotacionCancelacion()
	{
		return is_idAnotacionCancelacion;
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
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(long al_l)
	{
		il_idMatricula = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
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
	public void setIdTurnoHistoria(long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id turno historia.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de Solicitud.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setSolicitud(boolean ab_b)
	{
		ib_solicitud = ab_b;
	}

	/**
	 * Valida la propiedad solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isSolicitud()
	{
		return ib_solicitud;
	}
}
