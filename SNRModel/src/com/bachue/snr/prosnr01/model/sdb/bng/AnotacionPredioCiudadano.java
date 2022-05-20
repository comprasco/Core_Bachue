package com.bachue.snr.prosnr01.model.sdb.bng;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_BNG_ANOTACION_PREDIO_CIUDADANO.
 *
 * @author garias
 */
public class AnotacionPredioCiudadano extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID           = 8661759776784245220L;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id interesada rrr. */
	private String            is_idInteresadaRrr;
	
	/** Propiedad is id persona. */
	private String            is_idPersona;
	
	/** Propiedad is porcentaje participacion. */
	private String            is_porcentajeParticipacion;
	
	/** Propiedad is propietario. */
	private String            is_propietario;
	
	/** Propiedad is rol persona. */
	private String            is_rolPersona;
	
	/** Propiedad il id anotacion. */
	private long              il_idAnotacion;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public AnotacionPredioCiudadano()
	{
	}

	/**
	 * Constructor que recibe como parametro id circulo, id matricula y id anotacion.
	 *
	 * @param as_idCirculo id circulo
	 * @param al_idMatricula id matricula
	 * @param al_idAnotacion id anotacion
	 */
	public AnotacionPredioCiudadano(String as_idCirculo, long al_idMatricula, long al_idAnotacion)
	{
		setIdCirculo(as_idCirculo);
		setIdMatricula(al_idMatricula);
		setIdAnotacion(al_idAnotacion);
	}

	/**
	 * Instancia un nuevo objeto anotacion predio ciudadano.
	 *
	 * @param aapc_apc de aapc apc
	 */
	public AnotacionPredioCiudadano(com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano aapc_apc)
	{
		if(aapc_apc != null)
		{
			setIdCirculo(aapc_apc.getIdCirculo());
			setIdMatricula(aapc_apc.getIdMatricula());
			setIdAnotacion(aapc_apc.getIdAnotacion());
			setIdPersona(aapc_apc.getIdPersona());
			setRolPersona(aapc_apc.getRolPersona());
			setPropietario(aapc_apc.getPropietario());
			setPorcentajeParticipacion(aapc_apc.getPorcentajeParticipacion());
			setIdInteresadaRrr(aapc_apc.getIdInteresadaRrr());
		}
	}

	/**
	 * Modifica el valor de IdAnotacion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdAnotacion(long al_l)
	{
		this.il_idAnotacion                              = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public long getIdAnotacion()
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
		this.is_idCirculo = as_s;
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
	 * Modifica el valor de IdInteresadaRrr.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdInteresadaRrr(String as_s)
	{
		this.is_idInteresadaRrr = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id interesada rrr.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdInteresadaRrr()
	{
		return is_idInteresadaRrr;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatricula(long al_l)
	{
		this.il_idMatricula = al_l;
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
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		this.is_idPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdPersona()
	{
		return is_idPersona;
	}

	/**
	 * Modifica el valor de PorcentajeParticipacion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPorcentajeParticipacion(String as_s)
	{
		this.is_porcentajeParticipacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor porcentaje participacion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPorcentajeParticipacion()
	{
		return is_porcentajeParticipacion;
	}

	/**
	 * Modifica el valor de Propietario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setPropietario(String as_s)
	{
		this.is_propietario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor propietario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getPropietario()
	{
		return is_propietario;
	}

	/**
	 * Modifica el valor de RolPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRolPersona(String as_s)
	{
		this.is_rolPersona = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor rol persona.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getRolPersona()
	{
		return is_rolPersona;
	}
}
