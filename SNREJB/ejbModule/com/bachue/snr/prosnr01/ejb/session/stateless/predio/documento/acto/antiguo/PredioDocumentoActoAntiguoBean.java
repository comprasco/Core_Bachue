package com.bachue.snr.prosnr01.ejb.session.stateless.predio.documento.acto.antiguo;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.predio.documento.acto.antiguo.PredioDocumentoActoAntiguoBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.predio.documento.acto.antiguo.PredioDocumentoActoAntiguoRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.CalificacionAntiguoSistema;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades PredioDocumentoActoAntiguoBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "PredioDocumentoActoAntiguo", mappedName = "predioDocumentoActoAntiguoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class PredioDocumentoActoAntiguoBean implements PredioDocumentoActoAntiguoRemote
{
	/** Propiedad iaasb business. */
	private PredioDocumentoActoAntiguoBusiness iaasb_business;

	/** {@inheritdoc} */
	public String getObservacionesCalificacion(String aoa_oa, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().getObservacionesCalificacion(aoa_oa, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "PredioDocumentoActoAntiguoBean", "datosEtapa101", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public CalificacionAntiguoSistema datosEtapa101(
	    CalificacionAntiguoSistema aoa_oa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		CalificacionAntiguoSistema lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().datosEtapa101(aoa_oa, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "PredioDocumentoActoAntiguoBean", "datosEtapa101", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private PredioDocumentoActoAntiguoBusiness getBusiness()
	{
		if(iaasb_business == null)
			iaasb_business = new PredioDocumentoActoAntiguoBusiness();

		return iaasb_business;
	}
}
