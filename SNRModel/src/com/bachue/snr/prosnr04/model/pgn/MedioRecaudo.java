package com.bachue.snr.prosnr04.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Clase de abstracción para la tabla SDB_PGN_MEDIO_RECAUDO.
 *
 * @author Carlos Calderón
 */
public class MedioRecaudo extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID      = 2766058646051414704L;
	
	/** Propiedad is activo. */
	private String            is_activo;
	
	/** Propiedad is id medio recaudo. */
	private String            is_idMedioRecaudo;
	
	/** Propiedad is nombre medio recaudo. */
	private String            is_nombreMedioRecaudo;

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param as_s de as s
	 */
	public void setActivo(String as_s)
	{
		this.is_activo                              = as_s;
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
	 * Modifica el valor de IdMedioRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setIdMedioRecaudo(String as_s)
	{
		this.is_idMedioRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id medio recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdMedioRecaudo()
	{
		return is_idMedioRecaudo;
	}

	/**
	 * Modifica el valor de NombreMedioRecaudo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreMedioRecaudo(String as_s)
	{
		this.is_nombreMedioRecaudo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre medio recaudo.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombreMedioRecaudo()
	{
		return is_nombreMedioRecaudo;
	}
}
