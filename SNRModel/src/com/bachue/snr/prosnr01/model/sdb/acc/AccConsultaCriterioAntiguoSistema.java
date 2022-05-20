package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_CONSULTA_CRITERIO_ANT_SISTEMA.
 *
 * @author Julian Vaca
 */
public class AccConsultaCriterioAntiguoSistema extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID        = 5567276434665225055L;
	
	/** Propiedad il id anotacion. */
	private Long              il_idAnotacion;
	
	/** Propiedad il id matricula. */
	private Long              il_idMatricula;
	
	/** Propiedad il id turno historia. */
	private Long              il_idTurnoHistoria;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id criterio ant sistema. */
	private String            is_idCriterioAntSistema;
	
	/** Propiedad is id datos ant sistema. */
	private String            is_idDatosAntSistema;
	
	/** Propiedad is id documento. */
	private String            is_idDocumento;

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacion(Long as_s)
	{
		il_idAnotacion                                = as_s;
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
	 * Modifica el valor de IdCriterioAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCriterioAntSistema(String as_s)
	{
		is_idCriterioAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id criterio ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCriterioAntSistema()
	{
		return is_idCriterioAntSistema;
	}

	/**
	 * Modifica el valor de IdDatosAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDatosAntSistema(String as_s)
	{
		is_idDatosAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id datos ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDatosAntSistema()
	{
		return is_idDatosAntSistema;
	}

	/**
	 * Modifica el valor de IdDocumento.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDocumento(String as_s)
	{
		is_idDocumento = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id documento.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDocumento()
	{
		return is_idDocumento;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdMatricula(Long as_s)
	{
		il_idMatricula = as_s;
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
}
