package com.bachue.snr.prosnr01.ejb.session.stateless.notificaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.business.notificaciones.NotificacionesBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades NotificacionesBean.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 18/03/2020
 */
@javax.ejb.Stateless(name = "Notificaciones", mappedName = "notificacionesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class NotificacionesBean implements NotificacionesRemote
{
	/** Propiedad inb business. */
	private NotificacionesBusiness inb_business;

	/** {@inheritdoc} */
	public void actualizarRenunciaTerminos(
	    String as_renuncia, String as_idTurno, long al_idEtapa, String as_idUsuario, String as_localIp,
	    String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().actualizarRenunciaTerminos(as_renuncia, as_idTurno, al_idEtapa, as_idUsuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "NotificacionesBean", "actualizarRenunciaTerminos", as_idUsuario, as_localIp, as_ipRemota, null
		);
	}

	/** {@inheritdoc} */
	public Notificacion cargarDatosNotificaciones(
	    Entrega ae_entrega, long al_idEtapa, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Notificacion ln_notificacion;

		lsw_watch     = Logger.getNewStopWatch();

		ln_notificacion = getBusiness().cargarDatosNotificaciones(ae_entrega, al_idEtapa);

		Logger.log(
		    lsw_watch, "NotificacionesBean", "cargarDatosNotificaciones", as_idUsuario, as_localIp, as_remoteIp, null
		);

		return ln_notificacion;
	}

	/** {@inheritdoc} */
	@Override
	public void cierreExpediente(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().cierreExpediente(as_remoteIp);

		Logger.log(lsw_watch, "NotificacionesBean", "cierreExpediente", null, null, null, null);
	}

	/** {@inheritdoc} */
	@Override
	public void envioDocumentoElectronico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().envioDocumentoElectronico(as_remoteIp);

		Logger.log(lsw_watch, "NotificacionesBean", "envioDocumentoElectronico", null, null, null, null);
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> findInboxByTurnoNirNotificacionPersonal(
	    TramiteCantidad atc_tc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lctc_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lctc_datos     = getBusiness().findInboxByTurnoNirNotificacionPersonal(atc_tc);

		Logger.log(
		    lsw_watch, "NotificacionesBean", "findInboxByTurnoNirNotificacionPersonal", as_userId, as_localIp,
		    as_remoteIp, lctc_datos
		);

		return lctc_datos;
	}

	/** {@inheritdoc} */
	@Override
	public void firmarDocumentoElectronico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().firmarDocumentoElectronico(as_remoteIp);

		Logger.log(lsw_watch, "NotificacionesBean", "solicitudDocumentoElectronico", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void generarActaNotificacionPersonal(
	    String as_turno, Solicitud as_solicitud, String as_userId, String as_localIp, String as_remoteIp,
	    ObtenerFirmaDTO aofd_param
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().generarActaNotificacionPersonal(as_turno, as_solicitud, as_userId, as_remoteIp, aofd_param);

		Logger.log(
		    lsw_watch, "NotificacionesBean", "generarActaNotificacionPersonal", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void guardarActaOWCC(
	    String as_idTurno, ObtenerFirmaDTO aofdto_firma, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().guardarDocumentoOWCC(as_idTurno, aofdto_firma, as_idUsuario, as_remoteIp, true);

		Logger.log(lsw_watch, "EntregaBusiness", "guardarActaOWCC", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarNotificacion(Long al_turnoHistoria, String as_remoteIp, String as_localIp, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarNotificacion(al_turnoHistoria, as_remoteIp, as_userId);

		Logger.log(lsw_watch, "NotificacionesBean", "salvarNotificacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	@Override
	public void solicitudDocumentoElectronico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().solicitudDocumentoElectronico(as_remoteIp);

		Logger.log(lsw_watch, "NotificacionesBean", "solicitudDocumentoElectronico", null, null, null, null);
	}

	/**
	 * Retorna Objeto o variable de valor business.
	 *
	 * @return el valor de business
	 */
	private NotificacionesBusiness getBusiness()
	{
		if(inb_business == null)
			inb_business = new NotificacionesBusiness();

		return inb_business;
	}
}
