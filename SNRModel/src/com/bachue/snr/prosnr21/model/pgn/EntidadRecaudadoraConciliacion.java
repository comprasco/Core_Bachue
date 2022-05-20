package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_ENTIDAD_RECAUDADORA_CONCILIACION
 * @author dbeltran
 *
 */
public class EntidadRecaudadoraConciliacion extends Auditoria implements Serializable
{
	/** Propiedad serialVersionUID */
	private static final long serialVersionUID = 2684093197074890391L;

	/** Propiedad activo*/
	private String is_activo;

	/** Propiedad codigo Entidad Recaudadora*/
	private String is_codigoEntidadRecaudadora;

	/** Propiedad id Entidad Recaudadora Conciliación*/
	private String is_idEntidadRecaudadora;

	/** Propiedad nombre entidad recaudadora*/
	private String is_nombreEntidadRecaudadora;

	/**
	 * Método de obtención del valor de la propiedad id Entidad Recaudadora Conciliación
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Método de asignación del valor de la propiedad id Entidad Recaudadora Conciliación
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setIdEntidadRecaudadora(String as_s)
	{
		is_idEntidadRecaudadora = as_s;
	}

	/**
	 * Método de obtención del valor  de la propiedad nombre entidad recaudadora
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getNombreEntidadRecaudadora()
	{
		return is_nombreEntidadRecaudadora;
	}

	/**
	 * Método de asignación del valor de la propiedad nombre Entidad Recaudadora
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		is_nombreEntidadRecaudadora = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad código entidad Recaudadora
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getCodigoEntidadRecaudadora()
	{
		return is_codigoEntidadRecaudadora;
	}

	/**
	 * Método de asignación del valor de la propiedad código entidad recaudadora
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setCodigoEntidadRecaudadora(String as_s)
	{
		is_codigoEntidadRecaudadora = as_s;
	}

	/**
	 * Método de obtención del valor de la propiedad activo
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * Método de obtención del valor de la propiedad activo
	 * @param as_s de tipo String con el valor a asingar
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}
}
