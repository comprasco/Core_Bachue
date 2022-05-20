package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.sql.Timestamp;



/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOLICITUD_MATRICULA_ACTO.
 *
 * @author garias
 */
public class SolicitudMatriculaActo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID   = -8185879281745327067L;
	
	/** Propiedad is estado. */
	private String            is_estado;
	
	/** Propiedad is id acto. */
	private String            is_idActo;
	
	/** Propiedad is id circulo. */
	private String            is_idCirculo;
	
	/** Propiedad is id grupo juridica. */
	private String            is_idGrupoJuridica;
	
	/** Propiedad is id solicitud. */
	private String            is_idSolicitud;
	
	/** Propiedad is idturno. */
	private String            is_idturno;
	
	/** Propiedad is motivo tramite. */
	private String            is_motivoTramite;
	
	/** Propiedad is usuario. */
	private String            is_usuario;
	
	/** Propiedad it fecha. */
	private Timestamp         it_fecha;
	
	/** Propiedad il id matricula. */
	private long              il_idMatricula;

	/**
	 * Constructor por defecto.
	 */
	public SolicitudMatriculaActo()
	{
	}

	/**
	 * Constructor por que recibe como parametro la solicitud de la matricula.
	 *
	 * @param asm_sm solicitud de la matricula
	 */
	public SolicitudMatriculaActo(SolicitudMatricula asm_sm)
	{
		if(asm_sm != null)
		{
			setIdSolicitud(asm_sm.getIdSolicitud());
			setIdCirculo(asm_sm.getIdCirculo());
			setIdMatricula(asm_sm.getIdMatricula());
		}
	}

	/**
	 * Modifica el valor de Estado.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setEstado(String as_s)
	{
		is_estado                                = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor estado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstado()
	{
		return is_estado;
	}

	/**
	 * Modifica el valor de Fecha.
	 *
	 * @param at_t asigna el valor a la propiedad
	 */
	public void setFecha(Timestamp at_t)
	{
		it_fecha = at_t;
	}

	/**
	 * Retorna Objeto o variable de valor fecha.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Timestamp getFecha()
	{
		return it_fecha;
	}

	/**
	 * Modifica el valor de IdActo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdActo(String as_s)
	{
		is_idActo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id acto.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdActo()
	{
		return is_idActo;
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
	 * Modifica el valor de IdGrupoJuridica.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdGrupoJuridica(String as_s)
	{
		is_idGrupoJuridica = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id grupo juridica.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdGrupoJuridica()
	{
		return is_idGrupoJuridica;
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
		is_idturno = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id turno.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdTurno()
	{
		return is_idturno;
	}

	/**
	 * Modifica el valor de MotivoTramite.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setMotivoTramite(String as_s)
	{
		is_motivoTramite = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor motivo tramite.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getMotivoTramite()
	{
		return is_motivoTramite;
	}

	/**
	 * Modifica el valor de Usuario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor usuario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getUsuario()
	{
		return is_usuario;
	}
}
