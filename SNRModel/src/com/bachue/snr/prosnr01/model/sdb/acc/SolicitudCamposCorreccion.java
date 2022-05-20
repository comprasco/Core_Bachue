package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Collection;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOLICITUD_CAMPOS_CORRECCION.
 *
 * @author Julian Vaca
 */
public class SolicitudCamposCorreccion extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long     serialVersionUID               = 8727767561507884906L;
	
	/** Propiedad ica anotaciones agregadas. */
	private Collection<Anotacion> ica_anotacionesAgregadas;
	
	/** Propiedad il cantidad aperturar. */
	private Long                  il_cantidadAperturar;
	
	/** Propiedad il id causal correccion. */
	private Long                  il_idCausalCorreccion;
	
	/** Propiedad il id matricula. */
	private Long                  il_idMatricula;
	
	/** Propiedad il id matricula relacionado. */
	private Long                  il_idMatriculaRelacionado;
	
	/** Propiedad il id turno historia. */
	private Long                  il_idTurnoHistoria;
	
	/** Propiedad is id anotacion relacionado. */
	private String                is_idAnotacionRelacionado;
	
	/** Propiedad is id campos correccion. */
	private String                is_idCamposCorreccion;
	
	/** Propiedad is id circulo. */
	private String                is_idCirculo;
	
	/** Propiedad is id circulo relacionado. */
	private String                is_idCirculoRelacionado;
	
	/** Propiedad is id detalle ant sistema. */
	private String                is_idDetalleAntSistema;
	
	/** Propiedad is id direccion. */
	private String                is_idDireccion;
	
	/** Propiedad is id persona. */
	private String                is_idPersona;
	
	/** Propiedad is id solicitud. */
	private String                is_idSolicitud;
	
	/** Propiedad is id solicitud campos correccion. */
	private String                is_idSolicitudCamposCorreccion;
	
	/** Propiedad is id solicitud correccion. */
	private String                is_idSolicitudCorreccion;
	
	/** Propiedad is rol persona. */
	private String                is_rolPersona;

	/**
	 * Modifica el valor de AnotacionesAgregadas.
	 *
	 * @param aca_ca asigna el valor a la propiedad
	 */
	public void setAnotacionesAgregadas(Collection<Anotacion> aca_ca)
	{
		ica_anotacionesAgregadas                                 = aca_ca;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones agregadas.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<Anotacion> getAnotacionesAgregadas()
	{
		return ica_anotacionesAgregadas;
	}

	/**
	 * Modifica el valor de CantidadAperturar.
	 *
	 * @param al_l de al l
	 */
	public void setCantidadAperturar(Long al_l)
	{
		il_cantidadAperturar = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor cantidad aperturar.
	 *
	 * @return Retorna el valor de la propiedad cantidadAperturar
	 */
	public Long getCantidadAperturar()
	{
		return il_cantidadAperturar;
	}

	/**
	 * Modifica el valor de IdAnotacionRelacionado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdAnotacionRelacionado(String as_s)
	{
		is_idAnotacionRelacionado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id anotacion relacionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdAnotacionRelacionado()
	{
		return is_idAnotacionRelacionado;
	}

	/**
	 * Modifica el valor de IdCamposCorreccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCamposCorreccion(String as_s)
	{
		is_idCamposCorreccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id campos correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCamposCorreccion()
	{
		return is_idCamposCorreccion;
	}

	/**
	 * Modifica el valor de IdCausalCorreccion.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdCausalCorreccion(Long al_l)
	{
		il_idCausalCorreccion = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id causal correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdCausalCorreccion()
	{
		return il_idCausalCorreccion;
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
	 * Modifica el valor de IdCirculoRelacionado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCirculoRelacionado(String as_s)
	{
		is_idCirculoRelacionado = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo relacionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCirculoRelacionado()
	{
		return is_idCirculoRelacionado;
	}

	/**
	 * Modifica el valor de IdDetalleAntSistema.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDetalleAntSistema(String as_s)
	{
		is_idDetalleAntSistema = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id detalle ant sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDetalleAntSistema()
	{
		return is_idDetalleAntSistema;
	}

	/**
	 * Modifica el valor de IdDireccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdDireccion(String as_s)
	{
		is_idDireccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id direccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdDireccion()
	{
		return is_idDireccion;
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
	 * Modifica el valor de IdMatriculaRelacionado.
	 *
	 * @param al_l asigna el valor a la propiedad
	 */
	public void setIdMatriculaRelacionado(Long al_l)
	{
		il_idMatriculaRelacionado = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula relacionado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdMatriculaRelacionado()
	{
		return il_idMatriculaRelacionado;
	}

	/**
	 * Modifica el valor de IdPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdPersona(String as_s)
	{
		is_idPersona = as_s;
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
	 * Modifica el valor de IdSolicitudCamposCorreccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitudCamposCorreccion(String as_s)
	{
		is_idSolicitudCamposCorreccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud campos correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitudCamposCorreccion()
	{
		return is_idSolicitudCamposCorreccion;
	}

	/**
	 * Modifica el valor de IdSolicitudCorreccion.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitudCorreccion(String as_s)
	{
		is_idSolicitudCorreccion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud correccion.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitudCorreccion()
	{
		return is_idSolicitudCorreccion;
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
	 * Modifica el valor de RolPersona.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setRolPersona(String as_s)
	{
		is_rolPersona = as_s;
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
