package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_PGN_ARCHIVO
 * @author dbeltran
 *
 */
public class ArchivoDRXC extends Auditoria implements Serializable
{
	/**Propiedad serialVersionUID*/
	private static final long serialVersionUID = -933506890209439772L;

	/**Propiedad fechaSIIF*/
	private Date id_fechaSIIF;

	/**Propiedad activo*/
	private String is_activo;

	/**Propiedad numeroConsecutivoSIIF*/
	private String is_numeroConsecutivoSIIF;

	/**Propiedad is_numeroExtracto */
	private String is_numeroExtracto;

	/**Propiedad id numeroSIIF*/
	private String is_numeroSIIF;

	/**Propiedad ib confirma No Siif*/
	private boolean ib_confirmaNoSiif;

	/**
	 * @param as_s Modifica el valor de la propiedad is_activo por is_activo
	 */
	public void setActivo(String as_s)
	{
		is_activo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_activo
	 */
	public String getActivo()
	{
		return is_activo;
	}

	/**
	 * @param ab_confirmaNoSiif Modifica el valor de la propiedad confirmaNoSiif
	 */
	public void setConfirmaNoSiif(boolean ab_confirmaNoSiif)
	{
		ib_confirmaNoSiif = ab_confirmaNoSiif;
	}

	/**
	 * @return Retorna el valor de la propiedad confirmaNoSiif
	 */
	public boolean isConfirmaNoSiif()
	{
		return ib_confirmaNoSiif;
	}

	/**
	 * @param ad_fechaSIIF Modifica el valor de la propiedad id_fechaSIIF por id_fechaSIIF
	 */
	public void setFechaSIIF(Date ad_fechaSIIF)
	{
		id_fechaSIIF = ad_fechaSIIF;
	}

	/**
	 * @return Retorna el valor de la propiedad id_fechaSIIF
	 */
	public Date getFechaSIIF()
	{
		return id_fechaSIIF;
	}

	/**
	 * @param as_numeroConsecutivoSIIF Modifica el valor de la propiedad is_numeroConsecutivoSIIF por is_numeroConsecutivoSIIF
	 */
	public void setNumeroConsecutivoSIIF(String as_numeroConsecutivoSIIF)
	{
		is_numeroConsecutivoSIIF = as_numeroConsecutivoSIIF;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroConsecutivoSIIF
	 */
	public String getNumeroConsecutivoSIIF()
	{
		return is_numeroConsecutivoSIIF;
	}

	/**
	 * @param as_numeroExtracto Modifica el valor de la propiedad is_numeroExtracto por is_numeroExtracto
	 */
	public void setNumeroExtracto(String as_numeroExtracto)
	{
		is_numeroExtracto = as_numeroExtracto;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroExtracto
	 */
	public String getNumeroExtracto()
	{
		return is_numeroExtracto;
	}

	/**
	 * @param as_numeroSIIF Modifica el valor de la propiedad is_numeroSIIF por is_numeroSIIF
	 */
	public void setNumeroSIIF(String as_numeroSIIF)
	{
		is_numeroSIIF = as_numeroSIIF;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroSIIF
	 */
	public String getNumeroSIIF()
	{
		return is_numeroSIIF;
	}
}
