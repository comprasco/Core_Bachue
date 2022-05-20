package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_NOTA_DEVOLUTIVA.
 *
 * @author jpatino
 */
public class NotaDevolutiva extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5634695571599304791L;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad il version. */
	private Long il_version;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is comentario. */
	private String is_comentario;

	/** Propiedad is descripcion causal. */
	private String is_descripcionCausal;

	/** Propiedad is id causal. */
	private String is_idCausal;

	/** Propiedad is id nota devolutiva. */
	private String is_idNotaDevolutiva;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/**
	 * Constructor por defecto.
	 */
	public NotaDevolutiva()
	{
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
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
	 * Modifica el valor de Comentario.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setComentario(String as_s)
	{
		is_comentario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor comentario.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getComentario()
	{
		return is_comentario;
	}

	/**
	 * Modifica el valor de DescripcionCausal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setDescripcionCausal(String as_s)
	{
		is_descripcionCausal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor descripcion causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getDescripcionCausal()
	{
		return is_descripcionCausal;
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
	 * Modifica el valor de IdCausal.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdCausal(String as_s)
	{
		is_idCausal = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id causal.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdCausal()
	{
		return is_idCausal;
	}

	/**
	 * Modifica el valor de IdNotaDevolutiva.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdNotaDevolutiva(String as_s)
	{
		is_idNotaDevolutiva = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id nota devolutiva.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdNotaDevolutiva()
	{
		return is_idNotaDevolutiva;
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
	 * Modifica el valor de Version.
	 *
	 * @param al_l de al l
	 */
	public void setVersion(Long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor version.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getVersion()
	{
		return il_version;
	}
}
