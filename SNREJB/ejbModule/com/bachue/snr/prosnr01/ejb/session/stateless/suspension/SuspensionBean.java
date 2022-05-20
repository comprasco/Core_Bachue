package com.bachue.snr.prosnr01.ejb.session.stateless.suspension;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.calificacion.SuspensionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.suspension.SuspensionRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosEtapaAnterior;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades SuspensionBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Suspension", mappedName = "suspensionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class SuspensionBean implements SuspensionRemote
{
	/** Propiedad isb business. */
	private SuspensionBusiness isb_business;

	/** {@inheritdoc} */
	public void actualizarObservacionesTurnoHistoria(
	    TurnoHistoria lth_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getSuspensionBusiness().actualizarObservacionesTurnoHistoria(lth_param, as_usuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "SuspensionBean", "actualizarObservacionesTurnoHistoria", as_usuario, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public DatosEtapaAnterior buscarEtapa80Anterior(
	    String lth_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DatosEtapaAnterior ldea_return;

		lsw_watch     = Logger.getNewStopWatch();

		ldea_return = getSuspensionBusiness().buscarEtapa80Anterior(lth_param);

		Logger.log(lsw_watch, "SuspensionBean", "buscarEtapa80Anterior", as_usuario, as_localIp, as_remoteIp, null);

		return ldea_return;
	}

	/** {@inheritdoc} */
	public OficiosTexto buscarOficiosTextoPorTurno(
	    String ls_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		OficiosTexto lot_return;

		lsw_watch     = Logger.getNewStopWatch();

		lot_return = getSuspensionBusiness().buscarOficiosTextoPorTurno(ls_param);

		Logger.log(
		    lsw_watch, "SuspensionBean", "buscarOficiosTextoPorTurno", as_usuario, as_localIp, as_remoteIp, null
		);

		return lot_return;
	}

	/** {@inheritdoc} */
	public Suspension findDataSuspensionOfTheProcedure(
	    TurnoHistoria ath_parametros, boolean ab_solicitudDocumentacion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Suspension lsu_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lsu_datos = getSuspensionBusiness()
				            .findDataSuspensionOfTheProcedure(
				    ath_parametros, ab_solicitudDocumentacion, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "SuspensionBean", "findDataSuspensionOfTheProcedure", as_userId, as_localIp, as_remoteIp, null
		);

		return lsu_datos;
	}

	/** {@inheritdoc} */
	public Suspension generarDocumentosSuspensiones(
	    Collection<Suspension> acs_parametros, String as_userId, String as_localIp, String as_ipRemote
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Suspension lsu_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lsu_datos     = getSuspensionBusiness().generarDocumentosSuspensiones(acs_parametros, as_userId, as_ipRemote);

		Logger.log(
		    lsw_watch, "SuspensionBean", "generarDocumentosSuspenciones", as_userId, as_localIp, as_ipRemote, null
		);

		return lsu_datos;
	}

	/** {@inheritdoc} */
	public Suspension terminarProcesoSuspensiones(
	    Collection<Suspension> acs_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Suspension lsu_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lsu_datos = getSuspensionBusiness().terminarProcesoSuspensiones(acs_parametros, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "SuspensionBean", "terminarProcesoSuspensiones", as_userId, as_localIp, as_remoteIp, null
		);

		return lsu_datos;
	}

	/** {@inheritdoc} */
	public boolean validarTramiteSuspension(
	    String as_circulo, String as_matricula, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_suspensionEnTramite;

		lsw_watch                  = Logger.getNewStopWatch();
		lb_suspensionEnTramite     = getSuspensionBusiness().validarTramiteSuspension(as_circulo, as_matricula);

		Logger.log(lsw_watch, "SuspensionBean", "validarTramiteSuspension", as_usuario, as_localIp, as_remoteIp, null);

		return lb_suspensionEnTramite;
	}

	/** {@inheritdoc} */
	public boolean validarTramiteSuspension(
	    String as_circulo, String as_matricula, boolean ab_actoProhibicion, String as_usuario, String as_localIp,
	    String as_remoteIp, Collection<String> acc_alertas
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_suspensionEnTramite;

		lsw_watch                  = Logger.getNewStopWatch();
		lb_suspensionEnTramite     = getSuspensionBusiness()
				                             .validarTramiteSuspension(
				    as_circulo, as_matricula, ab_actoProhibicion, acc_alertas
				);

		Logger.log(lsw_watch, "SuspensionBean", "validarTramiteSuspension", as_usuario, as_localIp, as_remoteIp, null);

		return lb_suspensionEnTramite;
	}

	/** {@inheritdoc} */
	public Collection<String> validarTramiteSuspension2(
	    String as_circulo, String as_matricula, boolean ab_actoProhibicion, String as_usuario, String as_localIp,
	    String as_remoteIp, Collection<String> acc_alertas
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> acc_alertas2;

		lsw_watch        = Logger.getNewStopWatch();
		acc_alertas2     = getSuspensionBusiness()
				                   .validarTramiteSuspension2(
				    as_circulo, as_matricula, ab_actoProhibicion, acc_alertas
				);

		Logger.log(lsw_watch, "SuspensionBean", "validarTramiteSuspension", as_usuario, as_localIp, as_remoteIp, null);

		return acc_alertas2;
	}

	/**
	 * Retorna el valor de suspension business.
	 *
	 * @return el valor de suspension business
	 */
	private SuspensionBusiness getSuspensionBusiness()
	{
		if(isb_business == null)
			isb_business = new SuspensionBusiness();

		return isb_business;
	}
}
