package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_CON_ARCHIVO
 * @author dbeltran
 *
 */
public class ConArchivo extends Auditoria implements Serializable
{
	/**Propiedad serialVersionUID*/
	private static final long serialVersionUID = -2080000007078361042L;

	/** Propiedad ibd version. */
	private BigDecimal ibd_version;

	/**Propiedad Dia Corte*/
	private Date id_diaCorte;

	/**Propiedad estado Archivo*/
	private String is_estadoArchivo;

	/**Propiedad id Archivo*/
	private String is_idArchivo;

	/**Propiedad id Cuenta*/
	private String is_idCuenta;

	/**Propiedad id Periodo Corte*/
	private String is_idPeriodoCorte;

	/**Propiedad nombre Archivo*/
	private String is_nombreArchivo;

	/**Propiedad numero cuenta*/
	private String is_numeroCuenta;

	/**Propiedad tipo Archivo*/
	private String is_tipoArchivo;

	/** Propiedad ib archivo. */
	private byte[] ib_archivo;

	/**
	 * @return Retorna el valor de la propiedad ibd_version
	 */
	public BigDecimal getVersion()
	{
		return ibd_version;
	}

	/**
	 * @param Modifica el valor de la propiedad ibd_version por abd_version
	 */
	public void setVersion(BigDecimal abd_version)
	{
		ibd_version = abd_version;
	}

	/**
	 * @return Retorna el valor de la propiedad is_estadoArchivo
	 */
	public String getEstadoArchivo()
	{
		return is_estadoArchivo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_estadoArchivo por as_estadoArchivo
	 */
	public void setEstadoArchivo(String as_estadoArchivo)
	{
		is_estadoArchivo = as_estadoArchivo;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idArchivo
	 */
	public String getIdArchivo()
	{
		return is_idArchivo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idArchivo por as_idArchivo
	 */
	public void setIdArchivo(String as_idArchivo)
	{
		is_idArchivo = as_idArchivo;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idCuenta por is_idCuenta
	 */
	public void setIdCuenta(String as_idCuenta)
	{
		is_idCuenta = as_idCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idPeriodoCorte
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idPeriodoCorte por as_idPeriodoCorte
	 */
	public void setIdPeriodoCorte(String as_idPeriodoCorte)
	{
		is_idPeriodoCorte = as_idPeriodoCorte;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreArchivo
	 */
	public String getNombreArchivo()
	{
		return is_nombreArchivo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_nombreArchivo por is_nombreArchivo
	 */
	public void setNombreArchivo(String as_nombreArchivo)
	{
		is_nombreArchivo = as_nombreArchivo;
	}

	/**
	 * @return Retorna el valor de la propiedad is_tipoArchivo
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * @param Modifica el valor de la propiedad is_tipoArchivo por as_tipoArchivo
	 */
	public void setTipoArchivo(String as_tipoArchivo)
	{
		is_tipoArchivo = as_tipoArchivo;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_archivo
	 */
	public byte[] getArchivo()
	{
		return ib_archivo;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_archivo por ab_archivo
	 */
	public void setArchivo(byte[] ab_archivo)
	{
		ib_archivo = ab_archivo;
	}

	/**
	 * @return Retorna el valor de la propiedad is_numeroCuenta
	 */
	public String getNumeroCuenta()
	{
		return is_numeroCuenta;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad is_numeroCuenta
	 */
	public void setNumeroCuenta(String as_s)
	{
		is_numeroCuenta = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad id_diaCorte
	 */
	public Date getDiaCorte()
	{
		return id_diaCorte;
	}

	/**
	 * @param ad_d Modifica el valor de la propiedad id_diaCorte
	 */
	public void setDiaCorte(Date ad_d)
	{
		id_diaCorte = ad_d;
	}
}
