package com.bachue.snr.prosnr01.ejb.session.stateless.envioCorrespondenciaElectronica;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.envioCorrespondenciaElectronica.EnvioCorrespondenciaElectronicaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades EnvioCorrespondenciaFisicaBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
@javax.ejb.Stateless(name = "envioCorrespondenciaElectronica", mappedName = "envioCorrespondenciaElectronicaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class EnvioCorrespondenciaElectronicaBean implements EnvioCorrespondenciaElectronicaRemote
{
	/** Propiedad ifm business. */
	private EnvioCorrespondenciaElectronicaBusiness iecf_business;

	/** {@inheritdoc} */
	@Override
	public void enviarCorrespondenciaElectronica(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarCorrespondenciaElectronica(as_remoteIp);

		Logger.log(
		    lsw_watch, "envioCorrespondenciaElectronicaBean", "enviarCorrespondenciaElectronica", null, null,
		    as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void enviarCorrespondenciaElectronica206(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarCorrespondenciaElectronica206(as_remoteIp);

		Logger.log(
		    lsw_watch, "envioCorrespondenciaElectronicaBean", "enviarCorrespondenciaElectronica206", null, null,
		    as_remoteIp, null
		);
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private EnvioCorrespondenciaElectronicaBusiness getBusiness()
	{
		if(iecf_business == null)
			iecf_business = new EnvioCorrespondenciaElectronicaBusiness();

		return iecf_business;
	}
}
