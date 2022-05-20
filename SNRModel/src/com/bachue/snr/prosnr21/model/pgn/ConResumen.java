package com.bachue.snr.prosnr21.model.pgn;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.util.Date;


/**
 * Clase que representa un registro en la vista SDB_CON_RESUMEN del módulo de conciliaciones.
 *
 * @author Gabriel Arias
 */
public class ConResumen extends Auditoria implements java.io.Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1552750742318874395L;

	/** The fecha estado ach. */
	private Date id_fechaEstadoAch;

	/** The fecha estado aso. */
	private Date id_fechaEstadoAso;

	/** The fecha estado crps. */
	private Date id_fechaEstadoCrps;

	/** The fecha estado mu. */
	private Date id_fechaEstadoMu;

	/** The saldo final. */
	private Double id_saldoFinal;

	/** The saldo inicial. */
	private Double id_saldoInicial;

	/** The version archivo ach. */
	private Long il_versionArchivoAch;

	/** The version archivo aso. */
	private Long il_versionArchivoAso;

	/** The version archivo crps. */
	private Long il_versionArchivoCrps;

	/** The version archivo mu. */
	private Long il_versionArchivoMu;

	/** The estado ach. */
	private String is_estadoAch;

	/** The estado aso. */
	private String is_estadoAso;

	/** The estado crps. */
	private String is_estadoCrps;

	/** The estado mu. */
	private String is_estadoMu;

	/** The id archivo ach. */
	private String is_idArchivoAch;

	/** The id archivo aso. */
	private String is_idArchivoAso;

	/** The id archivo crps. */
	private String is_idArchivoCrps;

	/** The id archivo mu. */
	private String is_idArchivoMu;

	/** The id cuenta. */
	private String is_idCuenta;

	/** The id periodo corte. */
	private String is_idPeriodoCorte;

	/**
	 * @param as_estadoAch Modifica el valor de la propiedad estadoAch
	 */
	public void setEstadoAch(String as_estadoAch)
	{
		is_estadoAch = as_estadoAch;
	}

	/**
	 * @return Retorna el valor de la propiedad estadoAch
	 */
	public String getEstadoAch()
	{
		return is_estadoAch;
	}

	/**
	 * @param as_estadoAso Modifica el valor de la propiedad estadoAso
	 */
	public void setEstadoAso(String as_estadoAso)
	{
		is_estadoAso = as_estadoAso;
	}

	/**
	 * @return Retorna el valor de la propiedad estadoAso
	 */
	public String getEstadoAso()
	{
		return is_estadoAso;
	}

	/**
	 * @param as_estadoCrps Modifica el valor de la propiedad estadoCrps
	 */
	public void setEstadoCrps(String as_estadoCrps)
	{
		is_estadoCrps = as_estadoCrps;
	}

	/**
	 * @return Retorna el valor de la propiedad estadoCrps
	 */
	public String getEstadoCrps()
	{
		return is_estadoCrps;
	}

	/**
	 * @param as_estadoMu Modifica el valor de la propiedad estadoMu
	 */
	public void setEstadoMu(String as_estadoMu)
	{
		is_estadoMu = as_estadoMu;
	}

	/**
	 * @return Retorna el valor de la propiedad estadoMu
	 */
	public String getEstadoMu()
	{
		return is_estadoMu;
	}

	/**
	 * @param ad_fechaEstadoAch Modifica el valor de la propiedad fechaEstadoAch
	 */
	public void setFechaEstadoAch(Date ad_fechaEstadoAch)
	{
		id_fechaEstadoAch = ad_fechaEstadoAch;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaEstadoAch
	 */
	public Date getFechaEstadoAch()
	{
		return id_fechaEstadoAch;
	}

	/**
	 * @param ad_fechaEstadoAso Modifica el valor de la propiedad fechaEstadoAso
	 */
	public void setFechaEstadoAso(Date ad_fechaEstadoAso)
	{
		id_fechaEstadoAso = ad_fechaEstadoAso;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaEstadoAso
	 */
	public Date getFechaEstadoAso()
	{
		return id_fechaEstadoAso;
	}

	/**
	 * @param ad_fechaEstadoCrps Modifica el valor de la propiedad fechaEstadoCrps
	 */
	public void setFechaEstadoCrps(Date ad_fechaEstadoCrps)
	{
		id_fechaEstadoCrps = ad_fechaEstadoCrps;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaEstadoCrps
	 */
	public Date getFechaEstadoCrps()
	{
		return id_fechaEstadoCrps;
	}

	/**
	 * @param ad_fechaEstadoMu Modifica el valor de la propiedad fechaEstadoMu
	 */
	public void setFechaEstadoMu(Date ad_fechaEstadoMu)
	{
		id_fechaEstadoMu = ad_fechaEstadoMu;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaEstadoMu
	 */
	public Date getFechaEstadoMu()
	{
		return id_fechaEstadoMu;
	}

	/**
	 * @param as_idArchivoAch Modifica el valor de la propiedad idArchivoAch
	 */
	public void setIdArchivoAch(String as_idArchivoAch)
	{
		is_idArchivoAch = as_idArchivoAch;
	}

	/**
	 * @return Retorna el valor de la propiedad idArchivoAch
	 */
	public String getIdArchivoAch()
	{
		return is_idArchivoAch;
	}

	/**
	 * @param as_idArchivoAso Modifica el valor de la propiedad idArchivoAso
	 */
	public void setIdArchivoAso(String as_idArchivoAso)
	{
		is_idArchivoAso = as_idArchivoAso;
	}

	/**
	 * @return Retorna el valor de la propiedad idArchivoAso
	 */
	public String getIdArchivoAso()
	{
		return is_idArchivoAso;
	}

	/**
	 * @param as_idArchivoCrps Modifica el valor de la propiedad idArchivoCrps
	 */
	public void setIdArchivoCrps(String as_idArchivoCrps)
	{
		is_idArchivoCrps = as_idArchivoCrps;
	}

	/**
	 * @return Retorna el valor de la propiedad idArchivoCrps
	 */
	public String getIdArchivoCrps()
	{
		return is_idArchivoCrps;
	}

	/**
	 * @param as_idArchivoMu Modifica el valor de la propiedad idArchivoMu
	 */
	public void setIdArchivoMu(String as_idArchivoMu)
	{
		is_idArchivoMu = as_idArchivoMu;
	}

	/**
	 * @return Retorna el valor de la propiedad idArchivoMu
	 */
	public String getIdArchivoMu()
	{
		return is_idArchivoMu;
	}

	/**
	 * @param as_idCuenta Modifica el valor de la propiedad idCuenta
	 */
	public void setIdCuenta(String as_idCuenta)
	{
		is_idCuenta = as_idCuenta;
	}

	/**
	 * @return Retorna el valor de la propiedad idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * @param as_idPeriodoCorte Modifica el valor de la propiedad idPeriodoCorte
	 */
	public void setIdPeriodoCorte(String as_idPeriodoCorte)
	{
		is_idPeriodoCorte = as_idPeriodoCorte;
	}

	/**
	 * @return Retorna el valor de la propiedad idPeriodoCorte
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * @param ad_saldoFinal Modifica el valor de la propiedad saldoFinal
	 */
	public void setSaldoFinal(Double ad_saldoFinal)
	{
		id_saldoFinal = ad_saldoFinal;
	}

	/**
	 * @return Retorna el valor de la propiedad saldoFinal
	 */
	public Double getSaldoFinal()
	{
		return id_saldoFinal;
	}

	/**
	 * @param ad_saldoInicial Modifica el valor de la propiedad saldoInicial
	 */
	public void setSaldoInicial(Double ad_saldoInicial)
	{
		id_saldoInicial = ad_saldoInicial;
	}

	/**
	 * @return Retorna el valor de la propiedad saldoInicial
	 */
	public Double getSaldoInicial()
	{
		return id_saldoInicial;
	}

	/**
	 * @param al_versionArchivoAch Modifica el valor de la propiedad versionArchivoAch
	 */
	public void setVersionArchivoAch(Long al_versionArchivoAch)
	{
		il_versionArchivoAch = al_versionArchivoAch;
	}

	/**
	 * @return Retorna el valor de la propiedad versionArchivoAch
	 */
	public Long getVersionArchivoAch()
	{
		return il_versionArchivoAch;
	}

	/**
	 * @param al_versionArchivoAso Modifica el valor de la propiedad versionArchivoAso
	 */
	public void setVersionArchivoAso(Long al_versionArchivoAso)
	{
		il_versionArchivoAso = al_versionArchivoAso;
	}

	/**
	 * @return Retorna el valor de la propiedad versionArchivoAso
	 */
	public Long getVersionArchivoAso()
	{
		return il_versionArchivoAso;
	}

	/**
	 * @param al_versionArchivoCrps Modifica el valor de la propiedad versionArchivoCrps
	 */
	public void setVersionArchivoCrps(Long al_versionArchivoCrps)
	{
		il_versionArchivoCrps = al_versionArchivoCrps;
	}

	/**
	 * @return Retorna el valor de la propiedad versionArchivoCrps
	 */
	public Long getVersionArchivoCrps()
	{
		return il_versionArchivoCrps;
	}

	/**
	 * @param al_versionArchivoMu Modifica el valor de la propiedad versionArchivoMu
	 */
	public void setVersionArchivoMu(Long al_versionArchivoMu)
	{
		il_versionArchivoMu = al_versionArchivoMu;
	}

	/**
	 * @return Retorna el valor de la propiedad versionArchivoMu
	 */
	public Long getVersionArchivoMu()
	{
		return il_versionArchivoMu;
	}
}
