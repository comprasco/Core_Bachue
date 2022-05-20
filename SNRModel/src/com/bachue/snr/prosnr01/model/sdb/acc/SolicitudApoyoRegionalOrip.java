package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades SolicitudApoyoRegionalOrip.
 *
 * @author Luis Chacón
 * Fecha de Creacion: 15/09/2020
 */
public class SolicitudApoyoRegionalOrip extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2586552517048611433L;

	/** Propiedad is correo electronico. */
	private String is_correoElectronico;

	/** Propiedad is habilitar. */
	private String is_habilitar;

	/** Propiedad is id circulo regional. */
	private String is_idCirculoRegional;

	/** Propiedad is id regional. */
	private String is_idRegional;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud apoyo regional orip. */
	private String is_idSolicitudApoyoRegionalOrip;

	/** Propiedad is nombre orip. */
	private String is_nombreOrip;

	/** Propiedad is nombre regional. */
	private String is_nombreRegional;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is parametrizacion calificadores. */
	private String is_parametrizacionCalificadores;

	/** Propiedad il orden. */
	private long il_orden;

	/**
	 * Modifica el valor de CorreoElectronico.
	 *
	 * @param as_s Modifica el valor de la propiedad correoElectronico
	 */
	public void setCorreoElectronico(String as_s)
	{
		is_correoElectronico = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor correo electronico.
	 *
	 * @return Retorna el valor de la propiedad correoElectronico
	 */
	public String getCorreoElectronico()
	{
		return is_correoElectronico;
	}

	/**
	 * Modifica el valor de Habilitar.
	 *
	 * @param habilitar Modifica el valor de la propiedad habilitar
	 */
	public void setHabilitar(String habilitar)
	{
		is_habilitar = habilitar;
	}

	/**
	 * Retorna Objeto o variable de valor habilitar.
	 *
	 * @return Retorna el valor de la propiedad habilitar
	 */
	public String getHabilitar()
	{
		return is_habilitar;
	}

	/**
	 * Modifica el valor de IdCirculoRegional.
	 *
	 * @param idCirculoRegional Modifica el valor de la propiedad idCirculoRegional
	 */
	public void setIdCirculoRegional(String idCirculoRegional)
	{
		is_idCirculoRegional = idCirculoRegional;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo regional.
	 *
	 * @return Retorna el valor de la propiedad idCirculoRegional
	 */
	public String getIdCirculoRegional()
	{
		return is_idCirculoRegional;
	}

	/**
	 * Modifica el valor de IdRegional.
	 *
	 * @param as_s Modifica el valor de la propiedad idRegional
	 */
	public void setIdRegional(String as_s)
	{
		is_idRegional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id regional.
	 *
	 * @return Retorna el valor de la propiedad idRegional
	 */
	public String getIdRegional()
	{
		return is_idRegional;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s Modifica el valor de la propiedad idSolicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return Retorna el valor de la propiedad idSolicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSolicitudApoyoRegionalOrip.
	 *
	 * @param idSolicitudApoyoRegionalOrip Modifica el valor de la propiedad idSolicitudApoyoRegionalOrip
	 */
	public void setIdSolicitudApoyoRegionalOrip(String idSolicitudApoyoRegionalOrip)
	{
		is_idSolicitudApoyoRegionalOrip = idSolicitudApoyoRegionalOrip;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud apoyo regional orip.
	 *
	 * @return Retorna el valor de la propiedad idSolicitudApoyoRegionalOrip
	 */
	public String getIdSolicitudApoyoRegionalOrip()
	{
		return is_idSolicitudApoyoRegionalOrip;
	}

	/**
	 * Modifica el valor de NombreOrip.
	 *
	 * @param as_nombreOrip Modifica el valor de la propiedad nombreOrip
	 */
	public void setNombreOrip(String as_nombreOrip)
	{
		is_nombreOrip = as_nombreOrip;
	}

	/**
	 * Retorna Objeto o variable de valor nombre orip.
	 *
	 * @return Retorna el valor de la propiedad nombreOrip
	 */
	public String getNombreOrip()
	{
		return is_nombreOrip;
	}

	/**
	 * Modifica el valor de NombreRegional.
	 *
	 * @param as_s Modifica el valor de la propiedad nombreRegional
	 */
	public void setNombreRegional(String as_s)
	{
		is_nombreRegional = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre regional.
	 *
	 * @return Retorna el valor de la propiedad nombreRegional
	 */
	public String getNombreRegional()
	{
		return is_nombreRegional;
	}

	/**
	 * Modifica el valor de Observaciones.
	 *
	 * @param observaciones Modifica el valor de la propiedad observaciones
	 */
	public void setObservaciones(String observaciones)
	{
		is_observaciones = observaciones;
	}

	/**
	 * Retorna Objeto o variable de valor observaciones.
	 *
	 * @return Retorna el valor de la propiedad observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de Orden.
	 *
	 * @param orden Modifica el valor de la propiedad orden
	 */
	public void setOrden(long orden)
	{
		il_orden = orden;
	}

	/**
	 * Retorna Objeto o variable de valor orden.
	 *
	 * @return Retorna el valor de la propiedad orden
	 */
	public long getOrden()
	{
		return il_orden;
	}

	/**
	 * Modifica el valor de ParametrizacionCalificadores.
	 *
	 * @param as_s Modifica el valor de la propiedad parametrizacionCalificadores
	 */
	public void setParametrizacionCalificadores(String as_s)
	{
		is_parametrizacionCalificadores = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor parametrizacion calificadores.
	 *
	 * @return Retorna el valor de la propiedad parametrizacionCalificadores
	 */
	public String getParametrizacionCalificadores()
	{
		return is_parametrizacionCalificadores;
	}
}
