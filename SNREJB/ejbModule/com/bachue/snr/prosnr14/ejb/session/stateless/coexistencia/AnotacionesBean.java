package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoAnotacion;
import co.gov.supernotariado.www.services.bachue.cb.anotaciones.consultarAnotaciones.v1.TipoEntradaDatosAnotacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.AnotacionesBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades AnotacionesBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
@javax.ejb.Stateless(name = "anotaciones", mappedName = "anotacionesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class AnotacionesBean implements AnotacionesRemote
{
	/** Propiedad ia business. */
	private AnotacionesBusiness ia_business;

	/**
	 * Retorna Objeto o variable de valor anotaciones business.
	 *
	 * @return el valor de anotaciones business
	 */
	public AnotacionesBusiness getAnotacionesBusiness()
	{
		if(ia_business == null)
			ia_business = new AnotacionesBusiness();

		return ia_business;
	}

	/** {@inheritdoc} */
	public TipoAnotacion[] consultarAnotaciones(
	    TipoEntradaDatosAnotacion ateda_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		TipoAnotacion[] lta_return;

		lsw_watch      = Logger.getNewStopWatch();
		lta_return     = getAnotacionesBusiness().consultarAnotaciones(ateda_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "AnotacionesBean", "consultarAnotaciones", as_userId, as_localIp, as_remoteIp, null);

		return lta_return;
	}
}
