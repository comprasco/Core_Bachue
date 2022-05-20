package com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.apoyoNacional;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.ui.SolicitudApoyoNacionalUI;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ApoyoNacionalReference.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 3/09/2020
 */
@Remote
public interface ApoyoNacionalRemote
{
	/**
	 * Consultar solicitudes apoyo nacional.
	 *
	 * @param as_idCirculo de as id circulo
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> consultarSolicitudesApoyoNacional(
	    String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar A direccion tecnica.
	 *
	 * @param asanui_solicitudApoyoNac de asanui solicitud apoyo nac
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudApoyoNacionalUI enviarADireccionTecnica(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNac, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar solicitudes apoyo nacional.
	 *
	 * @param as_nir de as nir
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudApoyoNacional> findByNir(
	    String as_nir, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar solicitud de apoyo nacional.
	 *
	 * @param asanui_solicitudApoyoNac de lsanui solicitud apoyo nacional
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudApoyoNacionalUI generarSolicitudApoyoNacional(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNac, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Termina el proceso de solicitud apoyo nacional.
	 *
	 * @param ls_idSolicitud de id de la solicitud
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void terminarSolicitudApoyoNacional(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
