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

	/** Propiedad id Entidad Recaudadora Conciliaci�n*/
	private String is_idEntidadRecaudadora;

	/** Propiedad nombre entidad recaudadora*/
	private String is_nombreEntidadRecaudadora;

	/**
	 * M�todo de obtenci�n del valor de la propiedad id Entidad Recaudadora Conciliaci�n
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad id Entidad Recaudadora Conciliaci�n
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setIdEntidadRecaudadora(String as_s)
	{
		is_idEntidadRecaudadora = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor  de la propiedad nombre entidad recaudadora
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getNombreEntidadRecaudadora()
	{
		return is_nombreEntidadRecaudadora;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad nombre Entidad Recaudadora
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setNombreEntidadRecaudadora(String as_s)
	{
		is_nombreEntidadRecaudadora = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad c�digo entidad Recaudadora
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getCodigoEntidadRecaudadora()
	{
		return is_codigoEntidadRecaudadora;
	}

	/**
	 * M�todo de asignaci�n del valor de la propiedad c�digo entidad recaudadora
	 * @param as_s de tipo String con el valor a asignar
	 */
	public void setCodigoEntidadRecaudadora(String as_s)
	{
		is_codigoEntidadRecaudadora = as_s;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad activo
	 * @return de tipo String con el valor de la propiedad
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * M�todo de obtenci�n del valor de la propiedad activo
	 * @param as_s de tipo String con el valor a asingar
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}
}
