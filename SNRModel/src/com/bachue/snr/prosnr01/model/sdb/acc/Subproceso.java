package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;



/**
 * Abstraccion de tabla de Subproceso SDB_ACC_SUBPROCESO.
 *
 * @author Manuel Blanco
 */
public class Subproceso extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7291711994960567658L;

	/** Propiedad il id etapa inicial. */
	private Long il_idEtapaInicial;

	/** Propiedad is activo. */
	private String is_activo;

	/** Propiedad is estado actividad. */
	private String is_estadoActividad;

	/** Propiedad is id proceso. */
	private String is_idProceso;

	/** Propiedad is id subproceso. */
	private String is_idSubproceso;

	/** Propiedad is nombre. */
	private String is_nombre;

	/** Propiedad is solicitud certificado. */
	private String is_solicitudCertificado;

	/**
	 * Constructor por defecto.
	 */
	public Subproceso()
	{
	}

	/**
	 * Constructor que recibe como parametro del id del proceso y el id del subproceso.
	 *
	 * @param as_idProceso id del proceso
	 * @param as_idSubproceso id del subproceso
	 */
	public Subproceso(String as_idProceso, String as_idSubproceso)
	{
		is_idProceso        = as_idProceso;
		is_idSubproceso     = as_idSubproceso;
	}

	/**
	 * Modifica el valor de Activo.
	 *
	 * @param activo asigna el valor a la propiedad
	 */
	public void setActivo(String activo)
	{
		is_activo = activo;
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
	 * Modifica el valor de EstadoActividad.
	 *
	 * @param estadoActividad asigna el valor a la propiedad
	 */
	public void setEstadoActividad(String estadoActividad)
	{
		is_estadoActividad = estadoActividad;
	}

	/**
	 * Retorna Objeto o variable de valor estado actividad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getEstadoActividad()
	{
		return is_estadoActividad;
	}

	/**
	 * Modifica el valor de IdEtapaInicial.
	 *
	 * @param idEtapaInicial asigna el valor a la propiedad
	 */
	public void setIdEtapaInicial(Long idEtapaInicial)
	{
		il_idEtapaInicial = idEtapaInicial;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa inicial.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public Long getIdEtapaInicial()
	{
		return il_idEtapaInicial;
	}

	/**
	 * Modifica el valor de IdProceso.
	 *
	 * @param idProceso asigna el valor a la propiedad
	 */
	public void setIdProceso(String idProceso)
	{
		is_idProceso = idProceso;
	}

	/**
	 * Retorna Objeto o variable de valor id proceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdProceso()
	{
		return is_idProceso;
	}

	/**
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param idSubproceso asigna el valor a la propiedad
	 */
	public void setIdSubproceso(String idSubproceso)
	{
		is_idSubproceso = idSubproceso;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSubproceso()
	{
		return is_idSubproceso;
	}

	/**
	 * Modifica el valor de Nombre.
	 *
	 * @param nombre asigna el valor a la propiedad
	 */
	public void setNombre(String nombre)
	{
		is_nombre = nombre;
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

	/**
	 * Modifica el valor de SolicitudCertificado.
	 *
	 * @param solicitudCertificado asigna el valor a la propiedad
	 */
	public void setSolicitudCertificado(String solicitudCertificado)
	{
		is_solicitudCertificado = solicitudCertificado;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud certificado.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getSolicitudCertificado()
	{
		return is_solicitudCertificado;
	}
}
