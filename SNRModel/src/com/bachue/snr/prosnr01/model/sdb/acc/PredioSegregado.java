package com.bachue.snr.prosnr01.model.sdb.acc;

import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_PREDIO_SEGREGADO.
 *
 * @author Julian Vaca
 */
public class PredioSegregado extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID     = -5117782184646016992L;
	
	/** Propiedad il id anotacion. */
	private Long              il_idAnotacion;
	
	/** Propiedad il id anotacion 1. */
	private Long              il_idAnotacion1;
	
	/** Propiedad is descripcion. */
	private String            is_descripcion;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id circulo 1. */
	private String            is_idCirculo1;
	
	/** Propiedad is id predio segregado. */
	private String            is_idPredioSegregado;
	
	/** Propiedad is id turno. */
	private String            is_idTurno;
	
	/** Propiedad is lote. */
	private String            is_lote;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;
	
	/** Propiedad il id matricula 1. */
	private long              il_idMatricula1;
	
	/** Propiedad il id turno historia. */
	private long              il_idTurnoHistoria;

	/**
	 * Instancia un nuevo objeto predio segregado.
	 */
	public PredioSegregado()
	{
	}

	/**
	 * Instancia un nuevo objeto predio segregado.
	 *
	 * @param aps_ps de aps ps
	 */
	public PredioSegregado(com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado aps_ps)
	{
		is_descripcion                             = aps_ps.getDescripcion();
		is_idCirculo                               = aps_ps.getIdCirculo();
		is_idCirculo1                              = aps_ps.getIdCirculo1();
		is_lote                                    = aps_ps.getLote();
		il_idAnotacion                             = aps_ps.getIdAnotacion();
		il_idAnotacion1                            = aps_ps.getIdAnotacion1();
		il_idMatricula                             = NumericUtils.getLong(aps_ps.getIdMatricula());
		il_idMatricula1                            = NumericUtils.getLong(aps_ps.getIdMatricula1());
		il_idTurnoHistoria                         = aps_ps.getIdTurnoHistoria();
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
	 * Modifica el valor de IdCirculo1.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo1(String as_s)
	{
		is_idCirculo1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo1()
	{
		return is_idCirculo1;
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
	 * Modifica el valor de IdMatricula1.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula1(long al_l)
	{
		il_idMatricula1 = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula 1.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdMatricula1()
	{
		return il_idMatricula1;
	}

	/**
	 * Modifica el valor de IdPredioSegregado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPredioSegregado(String as_s)
	{
		is_idPredioSegregado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id predio segregado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPredioSegregado()
	{
		return is_idPredioSegregado;
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
	 * Modifica el valor de Lote.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setLote(String as_s)
	{
		is_lote = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor lote.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getLote()
	{
		return is_lote;
	}
}
