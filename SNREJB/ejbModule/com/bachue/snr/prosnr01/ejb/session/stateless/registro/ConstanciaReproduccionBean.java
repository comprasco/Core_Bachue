package com.bachue.snr.prosnr01.ejb.session.stateless.registro;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.registro.ConstanciaReproduccionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.ConstanciaReproduccionRemote;

import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConstanciaReproduccionBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "ConstanciaReproduccion", mappedName = "constanciaReproduccionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConstanciaReproduccionBean implements ConstanciaReproduccionRemote
{
	/** Propiedad ifm business. */
	private ConstanciaReproduccionBusiness ifm_business;

	/** {@inheritdoc} */
	@Override
	public void constanciaReproduccion(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().findConstanciaReproduccion(as_remoteIp);

		Logger.log(lsw_watch, "ConstanciaReproduccionBean", "constanciaReproduccion", null, null, null, null);
	}

	/** {@inheritdoc} */
	public byte[] generarConstanciaInscripcionRepConstancia(
	    ConstanciaReproduccion acr_constanciaReproduccion, boolean ab_firmaMasiva, String as_idUsuario,
	    String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lb_archivo;
		lsw_watch     = Logger.getNewStopWatch();

		lb_archivo = getBusiness()
				             .generarConstanciaInscripcionRepConstancia(
				    acr_constanciaReproduccion, ab_firmaMasiva, as_idUsuario, as_ipRemota
				);

		Logger.log(
		    lsw_watch, "ConstanciaReproduccionBean", "generarConstanciaInscripcionRepConstancia", null, null, null, null
		);

		return lb_archivo;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private ConstanciaReproduccionBusiness getBusiness()
	{
		if(ifm_business == null)
			ifm_business = new ConstanciaReproduccionBusiness();

		return ifm_business;
	}
}
