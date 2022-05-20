package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase modelo que contiene todos los atributos de la tabla SDB_ACC_SOPORTE_TRASLADO.
 *
 * @author garias
 */
public class SoporteTraslado extends Auditoria implements Serializable
{
	/** Propiedad serial version UID*/
	private static final long serialVersionUID = 16371720139421314L;

	/** Propiedad fecha documento*/
	private Date id_fechaDocumento;

	/** Propiedad il id turno historia. */
	private Long il_idTurnoHistoria;

	/** Propiedad il id turno historia. */
	private Long il_idVersion;

	/** Propiedad id departamento*/
	private String is_idDepartamento;

	/** Propiedad id municipio*/
	private String is_idMunicipio;

	/** Propiedad id oficina origen*/
	private String is_idOficinaOrigen;

	/** Propiedad id pais*/
	private String is_idPais;

	/** Propiedad id solicitud*/
	private String is_idSolicitud;

	/** Propiedad id soporte traslado*/
	private String is_idSoporteTraslado;

	/** Propiedad id id tipo documento*/
	private String is_idTipoDocumento;

	/** Propiedad id id tipo oficina*/
	private String is_idTipoOficina;

	/** Propiedad id turno*/
	private String is_idTurno;

	/** Propiedad numero*/
	private String is_numero;

	/**
	 * @param Modifica el valor de la propiedad fechaDocumento por fechaDocumento
	 */
	public void setFechaDocumento(Date ad_d)
	{
		id_fechaDocumento = ad_d;
	}

	/**
	 * @return Retorna el valor de la propiedad fechaDocumento
	 */
	public Date getFechaDocumento()
	{
		return id_fechaDocumento;
	}

	/**
	 * @param Modifica el valor de la propiedad idDepartamento por idDepartamento
	 */
	public void setIdDepartamento(String as_s)
	{
		is_idDepartamento = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idDepartamento
	 */
	public String getIdDepartamento()
	{
		return is_idDepartamento;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad idMunicipio
	 */
	public void setIdMunicipio(String as_s)
	{
		is_idMunicipio = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idMunicipio
	 */
	public String getIdMunicipio()
	{
		return is_idMunicipio;
	}

	/**
	 * @param Modifica el valor de la propiedad idOficinaOrigen por idOficinaOrigen
	 */
	public void setIdOficinaOrigen(String as_s)
	{
		is_idOficinaOrigen = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idOficinaOrigen
	 */
	public String getIdOficinaOrigen()
	{
		return is_idOficinaOrigen;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad idPais
	 */
	public void setIdPais(String as_s)
	{
		is_idPais = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idPais
	 */
	public String getIdPais()
	{
		return is_idPais;
	}

	/**
	 * @param Modifica el valor de la propiedad idSolicitud por idSolicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * @param Modifica el valor de la propiedad idSoporteTraslado por idSoporteTraslado
	 */
	public void setIdSoporteTraslado(String as_s)
	{
		is_idSoporteTraslado = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idSoporteTraslado
	 */
	public String getIdSoporteTraslado()
	{
		return is_idSoporteTraslado;
	}

	/**
	 * @param Modifica el valor de la propiedad idTipoDocumento por idTipoDocumento
	 */
	public void setIdTipoDocumento(String as_s)
	{
		is_idTipoDocumento = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idTipoDocumento
	 */
	public String getIdTipoDocumento()
	{
		return is_idTipoDocumento;
	}

	/**
	 * @param as_s Modifica el valor de la propiedad idTipoOficina
	 */
	public void setIdTipoOficina(String as_s)
	{
		is_idTipoOficina = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idTipoOficina
	 */
	public String getIdTipoOficina()
	{
		return is_idTipoOficina;
	}

	/**
	 * @param Modifica el valor de la propiedad idTurno por idTurno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad idTurno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * @param il_idTurnoHistoria Modifica el valor de la propiedad il_idTurnoHistoria
	 */
	public void setIdTurnoHistoria(Long il_idTurnoHistoria)
	{
		this.il_idTurnoHistoria = il_idTurnoHistoria;
	}

	/**
	 * @return Retorna el valor de la propiedad il_idTurnoHistoria
	 */
	public Long getIdTurnoHistoria()
	{
		return il_idTurnoHistoria;
	}

	/**
	 * @param il_idVersion Modifica el valor de la propiedad il_idVersion
	 */
	public void setIdVersion(Long il_idVersion)
	{
		this.il_idVersion = il_idVersion;
	}

	/**
	 * @return Retorna el valor de la propiedad il_idVersion
	 */
	public Long getIdVersion()
	{
		return il_idVersion;
	}

	/**
	 * @param Modifica el valor de la propiedad numero por numero
	 */
	public void setNumero(String as_s)
	{
		is_numero = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad numero
	 */
	public String getNumero()
	{
		return is_numero;
	}
}
