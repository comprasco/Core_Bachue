package com.bachue.snr.prosnr01.ejb.session.stateless.apoyoNacional;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.apoyoNacional.ApoyoNacionalBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.apoyoNacional.ApoyoNacionalRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudApoyoNacional;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.ui.SolicitudApoyoNacionalUI;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ApoyoNacionalBean.
 *
 * @author  Luis Chacón
 * Fecha de Creacion: 4/09/2020
 */
@javax.ejb.Stateless(name = "ApoyoNacional", mappedName = "ApoyoNacionalMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ApoyoNacionalBean implements ApoyoNacionalRemote
{
	/** Propiedad ipnb_business. */
	private ApoyoNacionalBusiness ipnb_business;

	/** {@inheritdoc} */
	public Collection<Turno> consultarSolicitudesApoyoNacional(
	    String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		Collection<Turno> lct_turnos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_turnos = getApoyoNacionalBusiness().consultarSolicitudesApoyoNacional(as_idCirculo);

		Logger.log(
		    lsw_watch, "ApoyoNacionalBean", "consultarSolicitudesApoyoNacional", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lct_turnos;
	}

	/** {@inheritdoc} */
	public SolicitudApoyoNacionalUI enviarADireccionTecnica(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNac, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		SolicitudApoyoNacionalUI lsan_solicitudApoyoNac;

		lsw_watch     = Logger.getNewStopWatch();

		lsan_solicitudApoyoNac = getApoyoNacionalBusiness()
				                         .enviarADireccionTecnica(asanui_solicitudApoyoNac, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "ApoyoNacionalBean", "enviarADireccionTecnica", as_userId, as_localIp, as_remoteIp, null);

		return lsan_solicitudApoyoNac;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudApoyoNacional> findByNir(
	    String as_nir, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<SolicitudApoyoNacional> lct_turnos;

		lsw_watch     = Logger.getNewStopWatch();

		lct_turnos = getApoyoNacionalBusiness().findByNir(as_nir);

		Logger.log(lsw_watch, "ApoyoNacionalBean", "findBynir", as_userId, as_localIp, as_remoteIp, null);

		return lct_turnos;
	}

	/** {@inheritdoc} */
	public SolicitudApoyoNacionalUI generarSolicitudApoyoNacional(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNac, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		SolicitudApoyoNacionalUI lsan_solicitudApoyoNac;

		lsw_watch     = Logger.getNewStopWatch();

		lsan_solicitudApoyoNac = getApoyoNacionalBusiness()
				                         .generarSolicitudApoyoNacional(
				    asanui_solicitudApoyoNac, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "ApoyoNacionalBean", "generarSolicitudApoyoNacional", as_userId, as_localIp, as_remoteIp, null
		);

		return lsan_solicitudApoyoNac;
	}

	/** {@inheritdoc} */
	public void terminarSolicitudApoyoNacional(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getApoyoNacionalBusiness().terminarSolicitudApoyoNacional(as_idSolicitud, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ApoyoNacionalBean", "generarSolicitudApoyoNacional", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/**
	 * Retorna Objeto o variable de valor apoyo nacional business.
	 *
	 * @return el valor de apoyo nacional business
	 */
	private ApoyoNacionalBusiness getApoyoNacionalBusiness()
	{
		if(ipnb_business == null)
			ipnb_business = new ApoyoNacionalBusiness();

		return ipnb_business;
	}
}
