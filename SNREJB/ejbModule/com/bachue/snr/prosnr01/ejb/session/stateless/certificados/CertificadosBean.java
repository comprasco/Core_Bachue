package com.bachue.snr.prosnr01.ejb.session.stateless.certificados;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.certificados.CertificadosBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.certificados.Certificados;
import com.bachue.snr.prosnr01.model.registro.NuevaEntrada;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades CertificadosBean.
 *
 * @author Manuel Blanco
 */
@javax.ejb.Stateless(name = "Certificados", mappedName = "certificadosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CertificadosBean implements CertificadosRemote
{
	/** Propiedad icb business. */
	private CertificadosBusiness icb_business;

	/** {@inheritdoc} */
	public PredioRegistro buscarMatriculaPorNumeroPredial(
	    String as_numeroPredial, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		PredioRegistro lpr_return;
		StopWatch      lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lpr_return = getCertificadosBusiness().buscarMatriculaPorNumeroPredial(as_numeroPredial);

		Logger.log(
		    lsw_watch, "CertificadosBean", "buscarMatriculaPorNumeroPredial", as_userId, as_localIp, as_remoteIp, null
		);

		return lpr_return;
	}

	/** {@inheritdoc} */
	public NuevaEntrada cargarDatosMatriculaNuevaEntrada(
	    Turno at_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		NuevaEntrada lne_return;
		StopWatch    lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lne_return = getCertificadosBusiness().cargarDatosMatriculaNuevaEntrada(at_turno);

		Logger.log(
		    lsw_watch, "CertificadosBean", "cargarDatosMatriculaNuevaEntrada", as_userId, as_localIp, as_remoteIp, null
		);

		return lne_return;
	}

	/** {@inheritdoc} */
	public void certificados(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCertificadosBusiness().certificados(as_remoteIp);

		Logger.log(lsw_watch, "CertificadosBean", "certificados", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public TramiteCantidad consultaBandeja(
	    TramiteCantidad atc_tc, boolean ab_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		TramiteCantidad lctc_tramitesCantidad;

		lsw_watch                 = Logger.getNewStopWatch();
		lctc_tramitesCantidad     = getCertificadosBusiness().consultaBandeja(atc_tc, ab_detalle);

		Logger.log(lsw_watch, "CertificadosBean", "consultaBandeja", as_userId, as_localIp, as_remoteIp, null);

		return lctc_tramitesCantidad;
	}

	/** {@inheritdoc} */
	public Certificados consultarDatosCertificadoEspecial(
	    Certificados ac_certificado, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Certificados lc_certificado;

		lsw_watch          = Logger.getNewStopWatch();
		lc_certificado     = getCertificadosBusiness().consultarDatosCertificadoEspecial(ac_certificado);

		Logger.log(
		    lsw_watch, "CertificadosBean", "consultarDatosCertificadoEspecial", as_userId, as_localIp, as_remoteIp, null
		);

		return lc_certificado;
	}

	/** {@inheritdoc} */
	public Solicitud consultarNirAsociadoNuevaEntrada(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Solicitud ls_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		ls_return = getCertificadosBusiness().consultarNirAsociadoNuevaEntrada(as_idTurno);

		Logger.log(
		    lsw_watch, "CertificadosBean", "consultarNirAsociadoNuevaEntrada", as_userId, as_localIp, as_remoteIp, null
		);

		return ls_return;
	}

	/** {@inheritdoc} */
	public Turno consultarTurno(String as_idTurno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		Turno     lt_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lt_return = getCertificadosBusiness().consultarTurno(as_idTurno);

		Logger.log(lsw_watch, "CertificadosBean", "consultarTurno", as_userId, as_localIp, as_remoteIp, null);

		return lt_return;
	}

	/** {@inheritdoc} */
	public Collection<TurnoHistoria> consultarTurnosAsociadosNuevaEntrada(
	    String as_nir, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<TurnoHistoria> lcth_return;
		StopWatch                 lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lcth_return = getCertificadosBusiness().consultarTurnosAsociadosNuevaEntrada(as_nir);

		Logger.log(
		    lsw_watch, "CertificadosBean", "consultarTurnosAsociadosNuevaEntrada", as_userId, as_localIp, as_remoteIp,
		    lcth_return
		);

		return lcth_return;
	}

	/** {@inheritdoc} */
	public void enviarAEtapaPosterior(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCertificadosBusiness().enviarAEtapaPosterior(ath_turnoHistoria);

		Logger.log(lsw_watch, "CertificadosBean", "enviarAAntSistema", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Certificados generarCertificadosEspeciales(
	    Certificados ac_certificado, boolean ab_definitivo, boolean ab_salvar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		Certificados lc_certificado;

		lsw_watch          = Logger.getNewStopWatch();
		lc_certificado     = getCertificadosBusiness()
				                     .generarCertificadosEspeciales(ac_certificado, false, ab_definitivo, ab_salvar);

		Logger.log(
		    lsw_watch, "CertificadosBean", "generarCertificadosEspeciales", as_userId, as_localIp, as_remoteIp, null
		);

		return lc_certificado;
	}

	/** {@inheritdoc} */
	public void guardarDatosTramite(
	    Certificados ac_certificado, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getCertificadosBusiness().guardarDatosTramite(ac_certificado, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CertificadosBean", "guardarDatosTramite", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public boolean validarNumeroAnotacionesPredioInconsistente(
	    String as_idCirculo, Long al_idMatricula, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lpr_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lpr_return = getCertificadosBusiness().validarNumeroAnotacionesPredioInconsistente(
			    as_idCirculo, al_idMatricula
			);

		Logger.log(
		    lsw_watch, "CertificadosBean", "validarNumeroAnotacionesPredioInconsistente", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return lpr_return;
	}

	/**
	 * Verifica si el número es par o impar y si corresponde al tipo partida ingresado.
	 *
	 * @param as_tipoPartida tipo de partida ingresado
	 * @param al_numeroPartida el número de partida ingresado
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si ambos corresponden, de lo contario retorna falso
	 * @throws B2BException
	 */
	public boolean verificarParImpar(
	    String as_tipoPartida, Long al_numeroPartida, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lpr_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lpr_return = getCertificadosBusiness().verificarParImpar(as_tipoPartida, al_numeroPartida);

		Logger.log(lsw_watch, "CertificadosBean", "verificarParImpar", as_userId, as_localIp, as_remoteIp, null);

		return lpr_return;
	}

	/**
	 * Retorna el valor de certificados business.
	 *
	 * @return el valor de certificados business
	 */
	private CertificadosBusiness getCertificadosBusiness()
	{
		if(icb_business == null)
			icb_business = new CertificadosBusiness();

		return icb_business;
	}
}
