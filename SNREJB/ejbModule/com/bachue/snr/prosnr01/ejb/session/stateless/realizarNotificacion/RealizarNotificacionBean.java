package com.bachue.snr.prosnr01.ejb.session.stateless.realizarNotificacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.realizarNotificacion.RealizarNotificacionRemote;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;

import com.bachue.snr.prosnr16.business.cyn.motor.notificacion.RealizarNotificacionBusiness;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades RealizarNotificacionBean.
 *
 * @author Manuel Blanco
 */
@javax.ejb.Stateless(name = "RealizarNotificacion", mappedName = "realizarNotificacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class RealizarNotificacionBean implements RealizarNotificacionRemote
{
	/** Propiedad icb business. */
	private RealizarNotificacionBusiness igb_business;

	/** {@inheritdoc} */
	public void cambiarMedioPublicacion(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().cambiarMedioPublicacion(ath_turnoHistoria, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "cambiarMedioPublicacion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void cambiarResponsablePublicacion(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().cambiarResponsablePublicacion(ath_turnoHistoria, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "cambiarResponsablePublicacion", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public DocumentosSalida consultarDocuemntoSalida(
	    long al_idDocumentoSalida, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		DocumentosSalida lds_return;

		lsw_watch     = Logger.getNewStopWatch();

		lds_return = getRealizarNotificacionBusiness().consultarDocumentoSalida(al_idDocumentoSalida);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "consultarDocuemntoSalida", as_userId, as_localIp, as_remoteIp, null
		);

		return lds_return;
	}

	/** {@inheritdoc} */
	public Notificacion consultarInformacionNotificacion(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Notificacion ln_return;

		lsw_watch     = Logger.getNewStopWatch();

		ln_return = getRealizarNotificacionBusiness().consultarInformacionNotificacion(as_idTurno);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "consultarInformacionNotificacion", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ln_return;
	}

	/** {@inheritdoc} */
	public void desfijarAvisos(
	    Collection<TurnoHistoria> acth_turnos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().desfijarAvisos(acth_turnos, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RealizarNotificacionBean", "desfijarAvisos", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void enviarAviso(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().enviarAviso(as_remoteIp);

		Logger.log(lsw_watch, "RealizarNotificacionBean", "enviarAviso", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void enviarAvisoWeb(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().enviarAvisoWeb(as_remoteIp);

		Logger.log(lsw_watch, "RealizarNotificacionBean", "enviarAvisoWeb", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public String envioVisorSGD(String as_idDocumentoSalida, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    lcth_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcth_return = getRealizarNotificacionBusiness().envioVisorSGD(as_idDocumentoSalida);

		Logger.log(lsw_watch, "RealizarNotificacionBean", "envioVisorSGD", as_userId, as_localIp, as_remoteIp, null);

		return lcth_return;
	}

	/** {@inheritdoc} */
	public void fijarAvisos(
	    Collection<TurnoHistoria> acth_turnos, long al_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().fijarAvisos(acth_turnos, al_idEtapa, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "RealizarNotificacionBean", "fijarAvisos", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void insertarDocumentoSalida(
	    TurnoHistoria ath_turnoHistoria, Imagenes li_imagen, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().insertarDocumentoSalida(ath_turnoHistoria, li_imagen, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "insertarDocumentoSalida", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public Collection<TurnoHistoria> obtenerCasosAvisos(
	    long al_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TurnoHistoria> lcth_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcth_return = getRealizarNotificacionBusiness().obtenerCasosAvisos(al_idEtapa, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "obtenerCasosAvisos", as_userId, as_localIp, as_remoteIp, lcth_return
		);

		return lcth_return;
	}

	/** {@inheritdoc} */
	public Collection<TurnoHistoria> obtenerCasosFijacionAvisos(
	    long al_idEtapa, String as_idProceso, String as_subproceso, Aprobacion aa_aprobacion, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<TurnoHistoria> lcth_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcth_return = getRealizarNotificacionBusiness()
				              .obtenerCasosFijacionAvisos(
				    al_idEtapa, as_idProceso, as_subproceso, aa_aprobacion, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "obtenerCasosFijacionAvisos", as_userId, as_localIp, as_remoteIp,
		    lcth_return
		);

		return lcth_return;
	}

	/** {@inheritdoc} */
	public Collection<DocumentosSalida> obtenerDocumentosAviso(
	    Collection<TurnoHistoria> acth_turnos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<DocumentosSalida> lcds_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcds_return = getRealizarNotificacionBusiness().obtenerDocumentosAviso(acth_turnos);

		Logger.log(
		    lsw_watch, "RealizarNotificacionBean", "obtenerDocumentosAviso", as_userId, as_localIp, as_remoteIp,
		    lcds_return
		);

		return lcds_return;
	}

	/** {@inheritdoc} */
	public void realizarNotificacion(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().realizarNotificacion(as_remoteIp);

		Logger.log(lsw_watch, "RealizarNotificacionBean", "realizarNotificacion", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void validarPersonasNotificadas(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getRealizarNotificacionBusiness().validarPersonasNotificadas(as_remoteIp);

		Logger.log(lsw_watch, "RealizarNotificacionBean", "validarPersonasNotificadas", null, null, as_remoteIp, null);
	}

	/**
	 * Retorna el valor de gobernaciones business.
	 *
	 * @return el valor de gobernaciones business
	 */
	private RealizarNotificacionBusiness getRealizarNotificacionBusiness()
	{
		if(igb_business == null)
			igb_business = new RealizarNotificacionBusiness();

		return igb_business;
	}
}
