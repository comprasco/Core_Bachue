package com.bachue.snr.prosnr01.ejb.session.stateless.crearTurno;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.crearTurno.CrearTurnoBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades CrearTurnoBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
@javax.ejb.Stateless(name = "CrearTurno", mappedName = "crearTurnoMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CrearTurnoBean implements CrearTurnoRemote
{
	/** Propiedad ictb business. */
	private CrearTurnoBusiness ictb_business;

	/** {@inheritdoc} */
	@Override
	public void crearTurno(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		lsw_watch = Logger.getNewStopWatch();

		getBusiness().crearTurno(as_remoteIp);

		Logger.log(lsw_watch, "CrearTurnoBean", "crearTurno", null, null, null, null);
	}

	/**
	 * Retorna Objeto o variable de valor business.
	 *
	 * @return el valor de business
	 */
	private CrearTurnoBusiness getBusiness()
	{
		if(ictb_business == null)
			ictb_business = new CrearTurnoBusiness();

		return ictb_business;
	}
}
