package com.bachue.snr.prosnr01.model.notificaciones;

import com.bachue.snr.prosnr01.model.auditoria.Auditoria;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;



/**
 * Clase que contiene todos las propiedades Notificacion.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 6/04/2020
 */
public class Notificacion extends Auditoria implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -559957830028819032L;

	/** Propiedad icds documentos notificaciones. */
	private Collection<DocumentosSalida> icds_documentosNotificaciones;

	/** Propiedad icp personas notificacion. */
	private Collection<Persona> icp_personasNotificacion;

	/** Propiedad id fecha notificacion. */
	private Date id_fechaNotificacion;

	/** Propiedad is tipo notificacion. */
	private String is_tipoNotificacion;

	/**
	 * Modifica el valor de DocumentosNotificaciones.
	 *
	 * @param acds_cds de acds cds
	 */
	public void setDocumentosNotificaciones(Collection<DocumentosSalida> acds_cds)
	{
		icds_documentosNotificaciones = acds_cds;
	}

	/**
	 * Retorna Objeto o variable de valor documentos notificaciones.
	 *
	 * @return el valor de documentos notificaciones
	 */
	public Collection<DocumentosSalida> getDocumentosNotificaciones()
	{
		return icds_documentosNotificaciones;
	}

	/**
	 * Modifica el valor de FechaNotificacion.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaNotificacion(Date ad_d)
	{
		id_fechaNotificacion = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha notificacion.
	 *
	 * @return el valor de fecha notificacion
	 */
	public Date getFechaNotificacion()
	{
		return id_fechaNotificacion;
	}

	/**
	 * Modifica el valor de PersonaNotificacion.
	 *
	 * @param acp_cp de acp cp
	 */
	public void setPersonaNotificacion(Collection<Persona> acp_cp)
	{
		icp_personasNotificacion = acp_cp;
	}

	/**
	 * Retorna Objeto o variable de valor persona notificacion.
	 *
	 * @return el valor de persona notificacion
	 */
	public Collection<Persona> getPersonaNotificacion()
	{
		return icp_personasNotificacion;
	}

	/**
	 * Modifica el valor de TipoNotificacion.
	 *
	 * @param as_s de as s
	 */
	public void setTipoNotificacion(String as_s)
	{
		is_tipoNotificacion = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor tipo notificacion.
	 *
	 * @return el valor de tipo notificacion
	 */
	public String getTipoNotificacion()
	{
		return is_tipoNotificacion;
	}
}
