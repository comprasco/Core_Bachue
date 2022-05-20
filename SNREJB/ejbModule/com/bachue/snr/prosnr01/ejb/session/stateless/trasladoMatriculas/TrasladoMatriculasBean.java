package com.bachue.snr.prosnr01.ejb.session.stateless.trasladoMatriculas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.trasladoMatriculas.TrasladoMatriculasBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import org.perf4j.StopWatch;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades TrasladoMatriculasBean.
 *
 * @author Sebastian Sanchez
 */
@javax.ejb.Stateless(name = "TrasladoMatriculas", mappedName = "trasladoMatriculasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class TrasladoMatriculasBean implements TrasladoMatriculasRemote
{
	/** Propiedad itmb business. */
	private TrasladoMatriculasBusiness itmb_business;

	/** {@inheritdoc} */
	public void actualizarEtapaYCrearSiguiente(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().actualizarEtapaYCrearSiguiente(ath_parametros, amt_motivoTramite, as_usuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "actualizarEtapaYCrearSiguiente", as_usuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public List<Map<String, Object>> findDetailInbox(
	    String as_userId, Long al_idEtapa, String as_idTurno, String as_nir
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findDetailInbox(as_userId, al_idEtapa, as_idTurno, as_nir);

		Logger.log(lsw_watch, "TrasladoMatriculasBusiness", "findDetailInbox", as_userId, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> findInboxByUserId(String as_userId, String as_idTurno, String as_nir)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findInboxByUserId(as_userId, as_idTurno, as_nir);

		Logger.log(lsw_watch, "TrasladoMatriculasBusiness", "findInboxByUserId", as_userId, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public List<Map<String, Object>> saveDetails(
	    List<Map<String, Object>> alm_data, String as_usuario, String as_localIp, long al_idEtapa, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> llm_data;

		lsw_watch     = Logger.getNewStopWatch();
		llm_data      = getBusiness().saveDetails(alm_data, as_usuario, al_idEtapa, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobacionBean", "actualizarEtapaYCrearSiguiente", as_usuario, as_localIp, as_remoteIp, null
		);

		return llm_data;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private TrasladoMatriculasBusiness getBusiness()
	{
		if(itmb_business == null)
			itmb_business = new TrasladoMatriculasBusiness();

		return itmb_business;
	}
}
