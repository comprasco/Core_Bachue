package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Abstraccion de tabla SDB_ACC_SALVEDAD_ANOTACION.
 *
 * @author Manuel Blanco
 */
public class AccSalvedadAnotacion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long              serialVersionUID       = -9195763417428585426L;
	
	/** Propiedad ia anotacion. */
	private Anotacion                      ia_anotacion;
	
	/** Propiedad icsm matriculas. */
	private Collection<SolicitudMatricula> icsm_matriculas;
	
	/** Propiedad id fecha registro. */
	private Date                           id_fechaRegistro;
	
	/** Propiedad il id anotacion. */
	private Long                           il_idAnotacion;
	
	/** Propiedad il id matricula. */
	private Long                           il_idMatricula;
	
	/** Propiedad il id turno historia. */
	private Long                           il_idTurnoHistoria;
	
	/** Propiedad il descripcion. */
	private String                         il_descripcion;
	
	/** Propiedad il id circulo. */
	private String                         il_idCirculo;
	
	/** Propiedad il id turno. */
	private String                         il_idTurno;
	
	/** Propiedad il radicacion. */
	private String                         il_radicacion;
	
	/** Propiedad is copiar. */
	private String                         is_copiar;
	
	/** Propiedad is copiar seleccionadas. */
	private String                         is_copiarSeleccionadas;
	
	/** Propiedad is id salvedad anotacion. */
	private String                         is_idSalvedadAnotacion;

	/**
	 * Modifica el valor de Anotacion.
	 *
	 * @param aa_a de aa a
	 */
	public void setAnotacion(Anotacion aa_a)
	{
		ia_anotacion                                              = aa_a;
	}

	/**
	 * Retorna Objeto o variable de valor anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Anotacion getAnotacion()
	{
		return ia_anotacion;
	}

	/**
	 * Modifica el valor de Copiar.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCopiar(String as_s)
	{
		is_copiar = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor copiar.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCopiar()
	{
		return is_copiar;
	}

	/**
	 * Modifica el valor de CopiarSeleccionadas.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setCopiarSeleccionadas(String as_s)
	{
		is_copiarSeleccionadas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor copiar seleccionadas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getCopiarSeleccionadas()
	{
		return is_copiarSeleccionadas;
	}

	/**
	 * Modifica el valor de Descripcion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcion(String as_s)
	{
		il_descripcion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcion()
	{
		return il_descripcion;
	}

	/**
	 * Modifica el valor de FechaRegistro.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha registro.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
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
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculo(String as_s)
	{
		il_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculo()
	{
		return il_idCirculo;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
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
	 * Modifica el valor de IdSalvedadAnotacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSalvedadAnotacion(String as_s)
	{
		is_idSalvedadAnotacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id salvedad anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSalvedadAnotacion()
	{
		return is_idSalvedadAnotacion;
	}

	/**
	 * Modifica el valor de IdTurno.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdTurno(String as_s)
	{
		il_idTurno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return il_idTurno;
	}

	/**
	 * Modifica el valor de IdTurnoHistoria.
	 *
	 * @param al_l de al l
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
	 * Modifica el valor de Matriculas.
	 *
	 * @param acsm_csm asigna el valor a la propiedad
	 */
	public void setMatriculas(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculas = acsm_csm;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<SolicitudMatricula> getMatriculas()
	{
		return icsm_matriculas;
	}

	/**
	 * Modifica el valor de Radicacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRadicacion(String as_s)
	{
		il_radicacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor radicacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRadicacion()
	{
		return il_radicacion;
	}
}
