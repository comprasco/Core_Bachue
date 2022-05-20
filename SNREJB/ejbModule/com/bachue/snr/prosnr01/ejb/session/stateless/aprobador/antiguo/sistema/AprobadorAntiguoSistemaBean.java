package com.bachue.snr.prosnr01.ejb.session.stateless.aprobador.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.aprobador.antiguo.sistema.AprobadorAntiguoSistemaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.aprobador.antiguo.sistema.AprobadorAntiguoSistemaRemote;

import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades AprobadorAntiguoSistemaBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "AprobadorAntiguoSistema", mappedName = "aprobadorAntiguoSistemaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AprobadorAntiguoSistemaBean implements AprobadorAntiguoSistemaRemote
{
	/** Propiedad iaasb business. */
	private AprobadorAntiguoSistemaBusiness iaasb_business;

	/** {@inheritdoc} */
	public Collection<Aprobacion> findAllData(
	    Aprobacion aa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		Collection<Aprobacion> lca_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lca_registro = getBusiness().findAllData(aa_oa, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobadorAntiguoSistemaBean", "findAllData", as_userId, as_localIp, as_remoteIp, lca_registro
		);

		return lca_registro;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> findInboxByUserId(String as_userId, String as_idTurno, String as_nir)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findInboxByUserId(as_userId, as_idTurno, as_nir);

		Logger.log(lsw_watch, "AprobadorAntiguoSistemaBean", "findInboxByUserId", as_userId, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Aprobacion generateZip(Aprobacion aoa_oa, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Aprobacion lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().generateZip(aoa_oa, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "AprobadorAntiguoSistemaBean", "generateZip", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Aprobacion salvarAprobacion(
	    Collection<Aprobacion> aca_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Aprobacion la_datos;

		lsw_watch     = Logger.getNewStopWatch();

		la_datos = getBusiness().salvarAprobacion(aca_parametros, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "AprobadorAntiguoSistemaBean", "salvarAprobacion", as_userId, as_localIp, as_remoteIp, null
		);

		return la_datos;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private AprobadorAntiguoSistemaBusiness getBusiness()
	{
		if(iaasb_business == null)
			iaasb_business = new AprobadorAntiguoSistemaBusiness();

		return iaasb_business;
	}
}
