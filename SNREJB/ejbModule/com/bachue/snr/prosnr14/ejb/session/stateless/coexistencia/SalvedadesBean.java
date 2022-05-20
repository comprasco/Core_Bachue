package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoEntradaSalvedades;
import co.gov.supernotariado.www.services.bachue.cb.salvedades.consultarSalvedades.v1.TipoSalidaSalvedades;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.SalvedadesBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MatriculasRelacionadasBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 10/03/2020
 */
@javax.ejb.Stateless(name = "salvedadesCX", mappedName = "salvedadesCXMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class SalvedadesBean implements SalvedadesRemote
{
	/** Propiedad isb business. */
	private SalvedadesBusiness isb_business;

	/**
	 * Retorna Objeto o variable de valor salvedades business.
	 *
	 * @return el valor de salvedades business
	 */
	public SalvedadesBusiness getSalvedadesBusiness()
	{
		if(isb_business == null)
			isb_business = new SalvedadesBusiness();

		return isb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaSalvedades consultarSalvedades(
	    TipoEntradaSalvedades ates_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		TipoSalidaSalvedades ltsdmm_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsdmm_return     = getSalvedadesBusiness().consultarSalvedades(ates_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "SalvedadesBean", "consultarSalvedades", as_userId, as_localIp, as_remoteIp, null);

		return ltsdmm_return;
	}
}
