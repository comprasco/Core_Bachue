package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoEntradaDatosTramites;
import co.gov.supernotariado.www.services.bachue.cb.tramitesencurso.consultarTramites.v1.TipoSalidaDatosTramites;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.TramitesEnCursoBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades TramitesEnCursoBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 11/03/2020
 */
@javax.ejb.Stateless(name = "tramitesEnCurso", mappedName = "tramitesEnCursoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class TramitesEnCursoBean implements TramitesEnCursoRemote
{
	/** Propiedad itecb business. */
	private TramitesEnCursoBusiness itecb_business;

	/**
	 * Retorna Objeto o variable de valor tramites en curso business.
	 *
	 * @return el valor de tramites en curso business
	 */
	public TramitesEnCursoBusiness getTramitesEnCursoBusiness()
	{
		if(itecb_business == null)
			itecb_business = new TramitesEnCursoBusiness();

		return itecb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaDatosTramites consultarTramites(
	    TipoEntradaDatosTramites atedt_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		TipoSalidaDatosTramites ltsdt_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsdt_return     = getTramitesEnCursoBusiness().consultarTramites(atedt_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "TramitesEnCursoBean", "consultarTramites", as_userId, as_localIp, as_remoteIp, null);

		return ltsdt_return;
	}
}
