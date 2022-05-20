package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_LIQUIDACION_ITEM_CONDICION.
 *
 * @author hcastaneda
 *
 */
public class LiquidacionItemCondicion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5991888817907847772L;

	/** Propiedad is_activo. */
	private String is_activo;

	/** Propiedad is_automatico. */
	private String is_automatico;

	/** Propiedad is_idLiquidacion. */
	private String is_idLiquidacion;

	/** Propiedad is_idTipoActoCondicion. */
	private String is_idTipoActoCondicion;

	/** Propiedad is_respuesta. */
	private String is_respuesta;

	/** Propiedad ii_consecutivo. */
	private int ii_consecutivo;

	/** Propiedad il_idItem. */
	private long il_idItem;

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setAutomatico(String as_s)
	{
		is_automatico = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getAutomatico()
	{
		return is_automatico;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setConsecutivo(int ai_i)
	{
		ii_consecutivo = ai_i;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public int getConsecutivo()
	{
		return ii_consecutivo;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdItem(long al_l)
	{
		il_idItem = al_l;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdItem()
	{
		return il_idItem;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdLiquidacion(String as_s)
	{
		is_idLiquidacion = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdLiquidacion()
	{
		return is_idLiquidacion;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setIdTipoActoCondicion(String as_s)
	{
		is_idTipoActoCondicion = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTipoActoCondicion()
	{
		return is_idTipoActoCondicion;
	}

	/**
	 * Método que modifica el valor de la propiedad.
	 *
	 * @param as_s modifica el valor de la propiedad.
	 */
	public void setRespuesta(String as_s)
	{
		is_respuesta = as_s;
	}

	/**
	 * Método que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getRespuesta()
	{
		return is_respuesta;
	}
}
