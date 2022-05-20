package com.bachue.snr.prosnr01.model.sdb.acc;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;

import java.io.Serializable;

import java.util.Date;


/**
 * Clase que contiene todos las propiedades SolicitudVisitas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 3/08/2020
 */
public class SolicitudVisitas extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1012971181219320072L;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad is solicitud. */
	private Solicitud is_solicitud;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id dependencia. */
	private String is_idDependencia;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud. */
	private String is_idSolicitudVisitas;

	/** Propiedad is id sub proceso. */
	private String is_idSubProceso;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is nombre dependencia. */
	private String is_nombreDependencia;

	/** Propiedad is nombre sub proceso. */
	private String is_nombreSubProceso;

	/** Propiedad is nombre usuario. */
	private String is_nombreUsuario;

	/**
	 * Modifica el valor de FechaDesde.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return el valor de fecha desde
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de FechaHasta.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return el valor de fecha hasta
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDependencia.
	 *
	 * @param as_s de as s
	 */
	public void setIdDependencia(String as_s)
	{
		is_idDependencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id dependencia.
	 *
	 * @return el valor de id dependencia
	 */
	public String getIdDependencia()
	{
		return is_idDependencia;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s asigna el valor a la propiedad
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * Modifica el valor de IdSolicitudVisitas.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitudVisitas(String as_s)
	{
		is_idSolicitudVisitas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud visitas.
	 *
	 * @return el valor de id solicitud visitas
	 */
	public String getIdSolicitudVisitas()
	{
		return is_idSolicitudVisitas;
	}

	/**
	 * Modifica el valor de IdSubProceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id sub proceso.
	 *
	 * @return el valor de id sub proceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/**
	 * @param is_nombreCirculo Modifica el valor de la propiedad is_nombreCirculo
	 */
	public void setNombreCirculo(String as_nc)
	{
		is_nombreCirculo = as_nc;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nombreCirculo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Modifica el valor de NombreDependencia.
	 *
	 * @param as_s de as s
	 */
	public void setNombreDependencia(String as_s)
	{
		is_nombreDependencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre dependencia.
	 *
	 * @return el valor de nombre dependencia
	 */
	public String getNombreDependencia()
	{
		return is_nombreDependencia;
	}

	/**
	 * Modifica el valor de NombreSubProceso.
	 *
	 * @param as_s de as s
	 */
	public void setNombreSubProceso(String as_s)
	{
		is_nombreSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre sub proceso.
	 *
	 * @return el valor de nombre sub proceso
	 */
	public String getNombreSubProceso()
	{
		return is_nombreSubProceso;
	}

	/**
	 * Modifica el valor de NombreUsuario.
	 *
	 * @param as_s de as s
	 */
	public void setNombreUsuario(String as_s)
	{
		is_nombreUsuario = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre usuario.
	 *
	 * @return el valor de nombre usuario
	 */
	public String getNombreUsuario()
	{
		return is_nombreUsuario;
	}

	/**
	 * Modifica el valor de Solicitud.
	 *
	 * @param as_s de as s
	 */
	public void setSolicitud(Solicitud as_s)
	{
		is_solicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		return is_solicitud;
	}
}
