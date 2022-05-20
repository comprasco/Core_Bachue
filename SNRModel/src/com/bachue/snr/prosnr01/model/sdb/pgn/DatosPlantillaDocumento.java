package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_TRAMITE_VISITA.
 *
 * @author Bryan Márquez
 */
public class DatosPlantillaDocumento extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8266178131947481239L;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is id constante. */
	private String is_idConstante;

	/** Propiedad is id tramite visita. */
	private String is_idDatosPlantillaDocumento;

	/** Propiedad is id tipo tramite. */
	private String is_idTipoTramite;

	/** Propiedad is id tramite. */
	private String is_idTramite;

	/**
	 * @param is_activo Modifica el valor de la propiedad is_activo
	 */
	public void setActivo(String as_a)
	{
		is_activo = as_a;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param is_idConstante Modifica el valor de la propiedad is_idConstante
	 */
	public void setIdConstante(String as_ic)
	{
		is_idConstante = as_ic;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idConstante
	 */
	public String getIdConstante()
	{
		return is_idConstante;
	}

	/**
	 * @param is_idDatosPlantillaDocumento Modifica el valor de la propiedad is_idDatosPlantillaDocumento
	 */
	public void setIdDatosPlantillaDocumento(String as_idpd)
	{
		is_idDatosPlantillaDocumento = as_idpd;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idDatosPlantillaDocumento
	 */
	public String getIdDatosPlantillaDocumento()
	{
		return is_idDatosPlantillaDocumento;
	}

	/**
	 * @param is_idTipoTramite Modifica el valor de la propiedad is_idTipoTramite
	 */
	public void setIdTipoTramite(String as_itt)
	{
		is_idTipoTramite = as_itt;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTipoTramite
	 */
	public String getIdTipoTramite()
	{
		return is_idTipoTramite;
	}

	/**
	 * @param is_idTramite Modifica el valor de la propiedad is_idTramite
	 */
	public void setIdTramite(String as_it)
	{
		is_idTramite = as_it;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idTramite
	 */
	public String getIdTramite()
	{
		return is_idTramite;
	}
}
