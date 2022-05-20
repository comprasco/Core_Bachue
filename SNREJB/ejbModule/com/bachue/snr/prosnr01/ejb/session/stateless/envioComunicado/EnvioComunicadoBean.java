package com.bachue.snr.prosnr01.ejb.session.stateless.envioComunicado;

import com.b2bsg.common.exception.B2BException;
import com.bachue.snr.prosnr01.business.envioComunicado.EnvioComunicadoBusiness;
import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades EnvioComunicadoBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/06/2020
 */
@javax.ejb.Stateless(name = "EnvioComunicado", mappedName = "envioComunicadoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class EnvioComunicadoBean implements EnvioComunicadoRemote
{
	
	/** Propiedad iecb business. */
	private EnvioComunicadoBusiness iecb_business;

	/** {@inheritdoc} */
	@Override
	public void enviarComunicado(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarComunicado(as_remoteIp);

		Logger.log(lsw_watch, "EnvioComunicadoBean", "enviarComunicado", null, as_remoteIp, as_remoteIp, null);
	}

	/**
	 * Retorna Objeto o variable de valor business.
	 *
	 * @return el valor de business
	 */
	private EnvioComunicadoBusiness getBusiness()
	{
		if(iecb_business == null)
			iecb_business = new EnvioComunicadoBusiness();

		return iecb_business;
	}
}
