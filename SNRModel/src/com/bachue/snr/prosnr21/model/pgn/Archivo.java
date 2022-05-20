package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_CON_ARCHIVO
 * @author dbeltran
 *
 */
public class Archivo extends Auditoria implements Serializable
{
	/**Propiedad serialVersionUID*/
	private static final long serialVersionUID = -933506890209439772L;

	/** Propiedad ibd version. */
	private BigDecimal ibd_version;

	/**Propiedad  estado archivo*/
	private String is_estadoArchivo;

	/**Propiedad id archivo*/
	private String is_idArchivo;

	/**Propiedad id cuenta*/
	private String is_idCuenta;

	/**Propiedad id periodo Corte*/
	private String is_idPeriodoCorte;

	/**Propiedad nombre archivo*/
	private String is_nombreArchivo;

	/**Propiedad tipo archivo*/
	private String is_tipoArchivo;

	/** Propiedad ib archivo. */
	private byte[] ib_archivo;

	/**
	 * @return Retorna el valor de la propiedad is_idArchivo
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_idArchivo
	 */
	public void setIdArchivo(String as_s)
	{
		is_idArchivo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_idCuenta
	 */
	public void setIdCuenta(String as_s)
	{
		is_idCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idPeriodoCorte
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_idPeriodoCorte
	 */
	public void setIdPeriodoCorte(String as_s)
	{
		is_idPeriodoCorte = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_tipoArchivo
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_tipoArchivo
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreArchivo
	 */
	public String getNombreArchivo()
	{
		return is_nombreArchivo;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_nombreArchivo
	 */
	public void setNombreArchivo(String as_s)
	{
		is_nombreArchivo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_estadoArchivo
	 */
	public String getEstadoArchivo()
	{
		return is_estadoArchivo;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_estadoArchivo
	 */
	public void setEstadoArchivo(String as_s)
	{
		is_estadoArchivo = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad ibd_version
	 */
	public BigDecimal getVersion()
	{
		return ibd_version;
	}

	/**
	 * @param abd_bd Modifica el valor de la propiedad ibd_version
	 */
	public void setVersion(BigDecimal abd_bd)
	{
		ibd_version = abd_bd;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_archivo
	 */
	public byte[] getArchivo()
	{
		return ib_archivo;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad ib_archivo
	 */
	public void setArchivo(byte[] ab_b)
	{
		ib_archivo = ab_b;
	}
}
