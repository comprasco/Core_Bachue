package com.bachue.snr.prosnr01.ejb.session.stateless.testamentos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.testamentos.TestamentosBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.registro.ReproduccionConstanciaTestamento;
import com.bachue.snr.prosnr01.model.registro.SolicitudTestamento;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades CertificadosBean.
 *
 * @author dbeltran
 */
@javax.ejb.Stateless(name = "Testamentos", mappedName = "testamentosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class TestamentosBean implements TestamentosRemote
{
	/** Propiedad icb business. */
	private TestamentosBusiness icb_business;

	/** {@inheritdoc} */
	public ReproduccionConstanciaTestamento buscarRepConstanciaDocumento(
	    Documento ad_documento, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		ReproduccionConstanciaTestamento lrct_return;
		StopWatch                        lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lrct_return = getTestamentosBusiness().buscarRepConstanciaDocumento(ad_documento);

		Logger.log(
		    lsw_watch, "TestamnetosBean", "buscarRepConstanciaDocumento", as_userId, as_localIp, as_remoteIp, null
		);

		return lrct_return;
	}

	/** {@inheritdoc} */
	public ReproduccionConstanciaTestamento buscarRepConstanciaTurno(
	    Turno at_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		ReproduccionConstanciaTestamento lrct_return;
		StopWatch                        lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lrct_return = getTestamentosBusiness().buscarRepConstanciaTurno(at_turno);

		Logger.log(lsw_watch, "TestamnetosBean", "consultarTurno", as_userId, as_localIp, as_remoteIp, null);

		return lrct_return;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> consultaBandeja(
	    TramiteCantidad atc_tc, boolean ab_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lctc_tramitesCantidad;

		lsw_watch                 = Logger.getNewStopWatch();
		lctc_tramitesCantidad     = getTestamentosBusiness().consultaBandeja(atc_tc, ab_detalle);

		Logger.log(
		    lsw_watch, "CertificadosBean", "consultaBandeja", as_userId, as_localIp, as_remoteIp, lctc_tramitesCantidad
		);

		return lctc_tramitesCantidad;
	}

	/** {@inheritdoc} */
	public Turno consultarTurno(String as_idTurno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		Turno     lt_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lt_return = getTestamentosBusiness().consultarTurno(as_idTurno);

		Logger.log(lsw_watch, "TestamnetosBean", "consultarTurno", as_userId, as_localIp, as_remoteIp, null);

		return lt_return;
	}

	/** {@inheritdoc} */
	public void enviarAEtapaPosterior(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getTestamentosBusiness().enviarAEtapaPosterior(ath_turnoHistoria);

		Logger.log(lsw_watch, "TestamnetosBean", "enviarAAntSistema", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public LibroTestamento findLibroTestamento(
	    LibroTestamento ath_param, String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		LibroTestamento lth_response;

		lsw_watch     = Logger.getNewStopWatch();

		lth_response = getTestamentosBusiness().findLibroTestamento(ath_param, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "TestamentosBusiness", "findTurnoHistoriaById", as_userId, as_localIp, as_remoteIp, null);

		return lth_response;
	}

	/** {@inheritdoc} */
	public TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_response;

		lsw_watch     = Logger.getNewStopWatch();

		lth_response = getTestamentosBusiness().findTurnoHistoriaById(ath_param);

		Logger.log(lsw_watch, "TestamentosBusiness", "findTurnoHistoriaById", null, null, null, null);

		return lth_response;
	}

	/** {@inheritdoc} */
	public byte[] generarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, String as_nir, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_file;

		lsw_watch     = Logger.getNewStopWatch();
		lba_file      = getTestamentosBusiness()
				                .generarConstanciaTestamento(
				    ast_solicitudTestamento, as_nir, as_idTurnoHistoria, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "TestamnetosBean", "generarConstanciaTestamento", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_file;
	}

	/** {@inheritdoc} */
	public SolicitudTestamento generarDataTestamentos(
	    String as_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		SolicitudTestamento lctc_tramitesCantidad;

		lsw_watch                 = Logger.getNewStopWatch();
		lctc_tramitesCantidad     = getTestamentosBusiness().generarDataTestamentos(as_turno);

		Logger.log(lsw_watch, "TestamnetosBean", "generarDataTestamentos", as_userId, as_localIp, as_remoteIp, null);

		return lctc_tramitesCantidad;
	}

	/** {@inheritdoc} */
	public byte[] generarReproduccionConstanciaTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_file;

		lsw_watch     = Logger.getNewStopWatch();
		lba_file      = getTestamentosBusiness()
				                .generarConstanciaReproduccionTestamento(
				    ast_solicitudTestamento, as_idTurnoHistoria, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "TestamentosBusiness", "generarReproduccionConstanciaTestamento", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return lba_file;
	}

	/** {@inheritdoc} */
	public byte[] guardarConstanciaReproduccionTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_file;

		lsw_watch     = Logger.getNewStopWatch();
		lba_file      = getTestamentosBusiness()
				                .guardarConstanciaReproduccionTestamento(
				    ast_solicitudTestamento, as_idTurnoHistoria, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "TestamentosBusiness", "guardarConstanciaTestamento", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_file;
	}

	/** {@inheritdoc} */
	public byte[] guardarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, String as_nir, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_file;

		lsw_watch     = Logger.getNewStopWatch();
		lba_file      = getTestamentosBusiness()
				                .guardarConstanciaTestamento(
				    ast_solicitudTestamento, as_nir, as_idTurnoHistoria, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "TestamentosBusiness", "guardarConstanciaTestamento", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_file;
	}

	/**
	 * Método para guardar la solicitud de reproducción de constancia de testamento y testamento con el número de copias
	 *
	 */
	public void guardarSolicitudReproduccion(
	    ReproduccionConstanciaTestamento arct_reproduccionConstanciaTestamento, String as_cantidadCopias,
	    boolean ab_copias, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getTestamentosBusiness()
			    .guardarSolicitudReproduccion(
			    arct_reproduccionConstanciaTestamento, as_cantidadCopias, ab_copias, as_userId, as_localIp, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "TestamentosBusiness", "guardarSolicitudReproduccion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void terminarTurnoHistoriaYCrearEtapa(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getTestamentosBusiness()
			    .actualizarEtapaYCrearSiguiente(ath_parametros, amt_motivoTramite, as_usuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "TestamentosBusiness", "actualizarEtapaYCrearSiguiente", as_usuario, as_localIp, as_remoteIp,
		    null
		);
	}

	/**
	 * Retorna el valor de certificados business.
	 *
	 * @return el valor de certificados business
	 */
	private TestamentosBusiness getTestamentosBusiness()
	{
		if(icb_business == null)
			icb_business = new TestamentosBusiness();

		return icb_business;
	}
}
