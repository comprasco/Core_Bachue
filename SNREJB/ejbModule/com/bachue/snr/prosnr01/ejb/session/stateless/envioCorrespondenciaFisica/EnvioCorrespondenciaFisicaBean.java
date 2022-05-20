package com.bachue.snr.prosnr01.ejb.session.stateless.envioCorrespondenciaFisica;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.envioCorrespondenciaFisica.EnvioCorrespondenciaFisicaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades EnvioCorrespondenciaFisicaBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
@javax.ejb.Stateless(name = "EnvioCorrespondenciaFisica", mappedName = "envioCorrespondenciaFisicaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class EnvioCorrespondenciaFisicaBean implements EnvioCorrespondenciaFisicaRemote
{
	/** Propiedad ifm business. */
	private EnvioCorrespondenciaFisicaBusiness iecf_business;

	/** {@inheritdoc} */
	@Override
	public void enviarCorrespondenciaFisica(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarCorrespondenciaFisica(as_remoteIp);

		Logger.log(
		    lsw_watch, "EnvioCorrespondenciaFisicaBean", "enviarCorrespondenciaFisica", null, null, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void entregaDocumentosCorrespondencia(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().entregaDocumentosCorrespondencia(as_remoteIp);

		Logger.log(
		    lsw_watch, "EnvioCorrespondenciaFisicaBean", "entregaDocumentosCorrespondencia", null, null, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public void entregaDocumentosCorreoElectronico(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().entregaDocumentosCorreoElectronico(as_remoteIp);

		Logger.log(
		    lsw_watch, "EnvioCorrespondenciaFisicaBean", "entregaDocumentosCorreoElectronico", null, null, as_remoteIp,
		    null
		);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private EnvioCorrespondenciaFisicaBusiness getBusiness()
	{
		if(iecf_business == null)
			iecf_business = new EnvioCorrespondenciaFisicaBusiness();

		return iecf_business;
	}
}
