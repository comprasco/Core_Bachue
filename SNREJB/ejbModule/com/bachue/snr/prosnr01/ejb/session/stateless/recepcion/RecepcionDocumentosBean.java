package com.bachue.snr.prosnr01.ejb.session.stateless.recepcion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.recepcion.RecepcionDocumentosBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades RecepcionDocumentosBean.
 *
 * @author Gabriel Arias
 */
@javax.ejb.Stateless(name = "RecepcionDocumentos", mappedName = "recepcionDocumentosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class RecepcionDocumentosBean implements RecepcionDocumentosRemote
{
	/** Propiedad ifm business. */
	private RecepcionDocumentosBusiness ird_business;

	/** {@inheritdoc} */
	@Override
	public void recepcionDocumentos(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().recepcionDocumentos(as_remoteIp);

		Logger.log(lsw_watch, "RecepcionDocumentosBean", "recepcionDocumentos", null, null, null, null);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private RecepcionDocumentosBusiness getBusiness()
	{
		if(ird_business == null)
			ird_business = new RecepcionDocumentosBusiness();

		return ird_business;
	}
}
