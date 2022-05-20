package com.bachue.snr.prosnr10.model.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades ServicioActualizacionHaciaCatastrosDetalle.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/06/2020
 */
public class ServicioActualizacionHaciaCatastrosDetalle extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2246464704321396772L;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id detalle transaccion. */
	private String is_idDetalleTransaccion;

	/** Propiedad is id naturaleza juridica. */
	private String is_idNaturalezaJuridica;

	/** Propiedad is id transaccion. */
	private String is_idTransaccion;

	/** Propiedad il id matricula. */
	private long il_idMatricula;

	/** Propiedad il version naturaleza juridica. */
	private long il_versionNaturalezaJuridica;

	/**
	 * Instancia un nuevo objeto servicio actualizacion hacia catastros detalle.
	 */
	public ServicioActualizacionHaciaCatastrosDetalle()
	{
	}

	/**
	 * Instancia un nuevo objeto servicio actualizacion hacia catastros detalle.
	 *
	 * @param as_idTransaccion de id transaccion
	 * @param as_idDetalleTransaccion de id detalle transaccion
	 * @param al_idMatricula de id matricula
	 * @param as_idCirculo de id circulo
	 * @param as_idNaturalezaJuridica de id naturaleza juridica
	 * @param al_versionNaturalezaJuridica de version naturaleza juridica
	 * @param as_userId de id usuario creacion
	 * @param as_remoteIp de ip creacion
	 */
	public ServicioActualizacionHaciaCatastrosDetalle(
	    String as_idTransaccion, String as_idDetalleTransaccion, long al_idMatricula, String as_idCirculo,
	    String as_idNaturalezaJuridica, long al_versionNaturalezaJuridica, String as_userId, String as_remoteIp
	)
	{
		is_idTransaccion                 = as_idTransaccion;
		is_idDetalleTransaccion          = as_idDetalleTransaccion;
		il_idMatricula                   = al_idMatricula;
		is_idCirculo                     = as_idCirculo;
		is_idNaturalezaJuridica          = as_idNaturalezaJuridica;
		il_versionNaturalezaJuridica     = al_versionNaturalezaJuridica;
		setIdUsuarioCreacion(as_userId);
		setIpCreacion(as_remoteIp);
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_idCirculo de as id circulo
	 */
	public void setIdCirculo(String as_idCirculo)
	{
		is_idCirculo = as_idCirculo;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return Retorna el valor de la propiedad idCirculo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDetalleTransaccion.
	 *
	 * @param as_idDetalleTransaccion de as id detalle transaccion
	 */
	public void setIdDetalleTransaccion(String as_idDetalleTransaccion)
	{
		is_idDetalleTransaccion = as_idDetalleTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor id detalle transaccion.
	 *
	 * @return Retorna el valor de la propiedad idDetalleTransaccion
	 */
	public String getIdDetalleTransaccion()
	{
		return is_idDetalleTransaccion;
	}

	/**
	 * Modifica el valor de IdMatricula.
	 *
	 * @param al_idMatricula de al id matricula
	 */
	public void setIdMatricula(long al_idMatricula)
	{
		il_idMatricula = al_idMatricula;
	}

	/**
	 * Retorna Objeto o variable de valor id matricula.
	 *
	 * @return Retorna el valor de la propiedad idMatricula
	 */
	public long getIdMatricula()
	{
		return il_idMatricula;
	}

	/**
	 * Modifica el valor de IdNaturalezaJuridica.
	 *
	 * @param as_idNaturalezaJuridica de as id naturaleza juridica
	 */
	public void setIdNaturalezaJuridica(String as_idNaturalezaJuridica)
	{
		is_idNaturalezaJuridica = as_idNaturalezaJuridica;
	}

	/**
	 * Retorna Objeto o variable de valor id naturaleza juridica.
	 *
	 * @return Retorna el valor de la propiedad idNaturalezaJuridica
	 */
	public String getIdNaturalezaJuridica()
	{
		return is_idNaturalezaJuridica;
	}

	/**
	 * Modifica el valor de IdTransaccion.
	 *
	 * @param as_idTransaccion de as id transaccion
	 */
	public void setIdTransaccion(String as_idTransaccion)
	{
		is_idTransaccion = as_idTransaccion;
	}

	/**
	 * Retorna Objeto o variable de valor id transaccion.
	 *
	 * @return Retorna el valor de la propiedad idTransaccion
	 */
	public String getIdTransaccion()
	{
		return is_idTransaccion;
	}

	/**
	 * Modifica el valor de VersionNaturalezaJuridica.
	 *
	 * @param al_versionNaturalezaJuridica de al version naturaleza juridica
	 */
	public void setVersionNaturalezaJuridica(long al_versionNaturalezaJuridica)
	{
		il_versionNaturalezaJuridica = al_versionNaturalezaJuridica;
	}

	/**
	 * Retorna Objeto o variable de valor version naturaleza juridica.
	 *
	 * @return Retorna el valor de la propiedad versionNaturalezaJuridica
	 */
	public long getVersionNaturalezaJuridica()
	{
		return il_versionNaturalezaJuridica;
	}
}
