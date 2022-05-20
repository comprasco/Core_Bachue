package com.bachue.snr.prosnr01.model.antiguoSistema;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;



/**
 * Class que contiene todos las propiedades CausalDevolucion.
 *
 * @author Julian Vaca
 */
public class CausalDevolucion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4272609028293591996L;

	/** Propiedad id fecha registro. */
	private Date id_fechaRegistro;

	/** Propiedad is id causal. */
	private String is_idCausal;

	/** Propiedad is id causal devolucion. */
	private String is_idCausalDevolucion;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad il id turno historia. */
	private long il_idTurnoHistoria;

	/** Propiedad il version. */
	private long il_version;

	/**
	 * Modifica el valor de fecha registro.
	 *
	 * @param ad_d asigna el valor a la propiedad fecha registro
	 */
	public void setFechaRegistro(Date ad_d)
	{
		id_fechaRegistro = ad_d;
	}

	/**
	 * Retorna el valor de fecha registro.
	 *
	 * @return el valor de fecha registro
	 */
	public Date getFechaRegistro()
	{
		return id_fechaRegistro;
	}

	/**
	 * Modifica el valor de id causal.
	 *
	 * @param as_s asigna el valor a la propiedad id causal
	 */
	public void setIdCausal(String as_s)
	{
		is_idCausal = as_s;
	}

	/**
	 * Retorna el valor de id causal.
	 *
	 * @return el valor de id causal
	 */
	public String getIdCausal()
	{
		return is_idCausal;
	}

	/**
	 * Modifica el valor de id causal devolucion.
	 *
	 * @param as_s asigna el valor a la propiedad id causal devolucion
	 */
	public void setIdCausalDevolucion(String as_s)
	{
		is_idCausalDevolucion = as_s;
	}

	/**
	 * Retorna el valor de id causal devolucion.
	 *
	 * @return el valor de id causal devolucion
	 */
	public String getIdCausalDevolucion()
	{
		return is_idCausalDevolucion;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de id turno historia.
	 *
	 * @param al_l asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(long al_l)
	{
		il_idTurnoHistoria = al_l;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de version.
	 *
	 * @param al_l asigna el valor a la propiedad version
	 */
	public void setVersion(long al_l)
	{
		il_version = al_l;
	}

	/**
	 * Retorna el valor de version.
	 *
	 * @return el valor de version
	 */
	public long getVersion()
	{
		return il_version;
	}
}
