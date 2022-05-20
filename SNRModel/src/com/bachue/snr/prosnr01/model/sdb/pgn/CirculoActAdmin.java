package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase de abstracción para la tabla SDB_PGN_CIRCULO_ACT_ADMIN.
 *
 * @author Gabriel Arias
 */
public class CirculoActAdmin extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8095779257491993874L;

	/** Propiedad il consecutivo. */
	private Long il_consecutivo;

	/** Propiedad is activo string. */
	private String is_activoString;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is tipo expediente. */
	private String is_tipoExpediente;

	/** Propiedad is vigencia. */
	private String is_vigencia;

	/** Propiedad ib activo. */
	private boolean ib_activo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param ab_b de ab b
	 */
	public void setActivo(boolean ab_b)
	{
		ib_activo = ab_b;
	}

	/**
	 * Valida la propiedad activo.
	 *
	 * @return Retorna el valor de la propiedad activo
	 */
	public boolean isActivo()
	{
		return ib_activo;
	}

	/**
	 * Modifica el valor de activo string.
	 *
	 * @param is_s de is s
	 */
	public void setActivoString(String is_s)
	{
		is_activoString = is_s;
	}

	/**
	 * Retorna Objeto o variable de valor activo string.
	 *
	 * @return Retorna el valor de la propiedad activoString.
	 */
	public String getActivoString()
	{
		return is_activoString;
	}

	/**
	 * Modifica el valor de Consecutivo.
	 *
	 * @param al_l de al l
	 */
	public void setConsecutivo(Long al_l)
	{
		il_consecutivo = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor consecutivo.
	 *
	 * @return Retorna el valor de la propiedad consecutivo
	 */
	public Long getConsecutivo()
	{
		return il_consecutivo;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param is_s de is s
	 */
	public void setIdCirculo(String is_s)
	{
		is_idCirculo = is_s;
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
	 * Modifica el valor de TipoExpediente.
	 *
	 * @param is_s de is s
	 */
	public void setTipoExpediente(String is_s)
	{
		is_tipoExpediente = is_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo expediente.
	 *
	 * @return Retorna el valor de la propiedad tipoExpediente
	 */
	public String getTipoExpediente()
	{
		return is_tipoExpediente;
	}

	/**
	 * Modifica el valor de Vigencia.
	 *
	 * @param is_s de is s
	 */
	public void setVigencia(String is_s)
	{
		is_vigencia = is_s;
	}

	/**
	 * Retorna Objeto o variable de valor vigencia.
	 *
	 * @return Retorna el valor de la propiedad vigencia
	 */
	public String getVigencia()
	{
		return is_vigencia;
	}
}
