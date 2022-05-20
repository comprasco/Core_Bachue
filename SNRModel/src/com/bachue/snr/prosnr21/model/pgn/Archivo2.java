package com.bachue.snr.prosnr21.model.pgn;

import com.b2bsg.common.util.StringUtils;

import java.util.Collection;


/**
 * Clase que representa un registro en la tabla SDB_CON_ARCHIVO del módulo de conciliaciones
 *
 * @author Edgar Prieto
 */
public class Archivo2 extends com.bachue.snr.prosnr01.model.auditoria.Auditoria implements java.io.Serializable
{
	/**Propiedad  serialVersionUID */
	private static final long serialVersionUID = 7999203056270685780L;

	/** Contante para identificar los archivos tipo CRPS Cabecera */
	public static final String TIPO_ARCHIVO_CRPS_CABECERA = "CRPSC";

	/** Contante para identificar los archivos tipo CRPS Detalle*/
	public static final String TIPO_ARCHIVO_CRPS_DETALLE = "CRPSD";

	/** Contante para identificar los archivos tipo Multicash Cabecera */
	public static final String TIPO_ARCHIVO_MULTICASH_CABECERA = "MUC";

	/** Contante para identificar los archivos tipo Multicash Detalle*/
	public static final String TIPO_ARCHIVO_MULTICASH_DETALLE = "MUD";

	/** Contante para identificar los archivos tipo ACH*/
	public static final String TIPO_ARCHIVO_ACH = "ACH";

	/** Contante para identificar los archivos tipo ASOBANCARIA*/
	public static final String TIPO_ARCHIVO_ASOBANCARIA = "ASO";

	/** Contante para identificar los archivos tipo SERVICIO BACHUE */
	public static final String TIPO_ARCHIVO_BACHUE = "BAC";

	/** Lineas de texto contenidas en el archivo */
	private Collection<String> ics_lineas;

	/** Versión del archivo dentro de un mismo corte */
	private Long il_version;

	/** Nombre de archivo cargado en el registro */
	private String id_nombreArchivo;

	/** Estado de cargue del archivo */
	private String is_estadoArchivo;

	/** Identificación del registro */
	private String is_id;

	/** Id de la cuenta asociada al registro*/
	private String is_idCuenta;

	/** Id de perido de corte asociado al registro */
	private String is_idPeriodoCorte;

	/** Tipo de archivo al que representa el registro */
	private String is_tipoArchivo;

	/** Representación binaria del archvio cargado en el registro */
	private byte[] iba_archivo;

	/**
	 * Asigna la representación binaria del archvio cargado en el registro
	 *
	 * @param aba_ba Valor a asignar
	 */
	public void setArchivo(byte[] aba_ba)
	{
		iba_archivo = aba_ba;
	}

	/** @return Obtiene la representación binaria del archvio cargado en el registro */
	public byte[] getArchivo()
	{
		return iba_archivo;
	}

	/**
	 * Asigna el estado de cargue del archivo
	 *
	 * @param as_s Valor a asignar
	 */
	public void setEstadoArchivo(String as_s)
	{
		is_estadoArchivo = StringUtils.getString(as_s);
	}

	/** @return Obtiene el estado de cargue del archivo */
	public String getEstadoArchivo()
	{
		return is_estadoArchivo;
	}

	/**
	 * Asigna el id del registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setId(String as_s)
	{
		is_id = StringUtils.getString(as_s);
	}

	/** @return Obtiene el id del registro */
	public String getId()
	{
		return is_id;
	}

	/**
	 * Asigna el id de la cuenta asociada al registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdCuenta(String as_s)
	{
		is_idCuenta = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el id de la cuenta asociada al registro
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * Asigna el id de perido de corte asociado al registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setIdPeriodoCorte(String as_s)
	{
		is_idPeriodoCorte = StringUtils.getString(as_s);
	}

	/**
	 * @return Obtiene el id de perido de corte asociado al registro
	 */
	public String getIdPeriodoCorte()
	{
		return is_idPeriodoCorte;
	}

	/**
	 * Asigna las lineas de texto contenidas en el archivo
	 *
	 * @param acs_cs Valor a asignar
	 */
	public void setLineas(Collection<String> acs_cs)
	{
		ics_lineas = acs_cs;
	}

	/** @return las lineas de texto contenidas en el archivo */
	public Collection<String> getLineas()
	{
		return ics_lineas;
	}

	/**
	 * Asigna el nombre de archivo cargado en el registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setNombreArchivo(String as_s)
	{
		id_nombreArchivo = StringUtils.getString(as_s);
	}

	/** @return Obtiene el nombre de archivo cargado en el registro */
	public String getNombreArchivo()
	{
		return id_nombreArchivo;
	}

	/**
	 * Asigna el tipo de archivo al que representa el registro
	 *
	 * @param as_s Valor a asignar
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = StringUtils.getString(as_s);
	}

	/** @return Obtiene el tipo de archivo al que representa el registro */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * Asigna la versión del archivo dentro de un mismo corte
	 *
	 * @param al_l Valor a asignar
	 */
	public void setVersion(Long al_l)
	{
		il_version = al_l;
	}

	/**
	 * @return Versión del archivo dentro de un mismo corte
	 */
	public Long getVersion()
	{
		return il_version;
	}
}
