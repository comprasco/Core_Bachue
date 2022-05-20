package com.bachue.snr.prosnr01.model.sdb.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 *
 * Clase modelo de Folio antiguo sistema.
 *
 * @author hcastaneda
 *
 */
public class FolioAntiguoSistema extends Auditoria implements Serializable
{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID         = 646780487851700667L;
	
	/** Propiedad il id folio antiguo sistema. */
	private String            il_idFolioAntiguoSistema;
	
	/** Propiedad is nombre. */
	private String            is_nombre;

	/**
	 * Constructor por defecto.
	 */
	public FolioAntiguoSistema()
	{
	}

	/**
	 * Constructor que recibe como parametro sin informacion.
	 *
	 * @param as_sinInformacion de as sin informacion
	 */
	public FolioAntiguoSistema(String as_sinInformacion)
	{
		setNombre(as_sinInformacion);
	}

	/**
	 * Modifica el valor de IdFolioAntiguoSistema.
	 *
	 * @param idFolioAntiguoSistema asigna el valor a la propiedad
	 */
	public void setIdFolioAntiguoSistema(String idFolioAntiguoSistema)
	{
		this.il_idFolioAntiguoSistema                  = idFolioAntiguoSistema;
	}

	/**
	 * Retorna Objeto o variable de valor id folio antiguo sistema.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdFolioAntiguoSistema()
	{
		return il_idFolioAntiguoSistema;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		this.is_nombre = nombre;
	}

	/**
	 * Retorna Objeto o variable de valor nombre.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getNombre()
	{
		return is_nombre;
	}
}
