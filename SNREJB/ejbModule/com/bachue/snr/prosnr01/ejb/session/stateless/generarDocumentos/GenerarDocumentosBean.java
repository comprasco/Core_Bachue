package com.bachue.snr.prosnr01.ejb.session.stateless.generarDocumentos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.generarDocumentos.GenerarDocumentosBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades GenerarDocumentosBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "GenerarDocumentos", mappedName = "generarDocumentosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GenerarDocumentosBean implements GenerarDocumentosRemote
{
	/** Propiedad igd business. */
	private GenerarDocumentosBusiness igd_business;

	/** {@inheritdoc} */
	public void enviarAEntrega(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarAEntrega(ath_parametros, amt_motivoTramite, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "GenerarDocumentosBean", "enviarAEntrega", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void generarDocumentos(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().generarDocumentos(as_remoteIp);

		Logger.log(lsw_watch, "GenerarDocumentosBean", "GenerarDocumentos", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public byte[] generarDocumentosRespuesta(
	    TurnoHistoria ath_turnoHistoria, Solicitud as_solicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_archivo;

		lsw_watch     = Logger.getNewStopWatch();

		lba_archivo = getBusiness().generarDocumentosRespuesta(ath_turnoHistoria, as_solicitud, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "GenerarDocumentosBean", "generarDocumentosRespuesta", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_archivo;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private GenerarDocumentosBusiness getBusiness()
	{
		if(igd_business == null)
			igd_business = new GenerarDocumentosBusiness();

		return igd_business;
	}
}
