package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoEntradaConsultaMigracionMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionMatriculas.v1.TipoSalidaConsultaMigracionMatriculas;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoEntradaConsultaMigracionORIP;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoEntradaConsultaMigracionPredio;
import co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionPredio.v1.TipoSalidaConsultaMigracionPredio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.ConsultaMigracionBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConsultaMigracionBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
@javax.ejb.Stateless(name = "consultaMigracion", mappedName = "consultaMigracionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaMigracionBean implements ConsultaMigracionRemote
{
	/** Propiedad icmb business. */
	private ConsultaMigracionBusiness icmb_business;

	/**
	 * Retorna Objeto o variable de valor consulta migracion business.
	 *
	 * @return el valor de consulta migracion business
	 */
	public ConsultaMigracionBusiness getConsultaMigracionBusiness()
	{
		if(icmb_business == null)
			icmb_business = new ConsultaMigracionBusiness();

		return icmb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultaMigracionMatriculas consultarMigracionMatriculas(
	    TipoEntradaConsultaMigracionMatriculas atecmm_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                             lsw_watch;
		TipoSalidaConsultaMigracionMatriculas ltcmm_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltcmm_return     = getConsultaMigracionBusiness()
				                   .consultarMigracionMatriculas(atecmm_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ConsultaMigracionBean", "consultarMigracionMatriculas", as_userId, as_localIp, as_remoteIp, null
		);

		return ltcmm_return;
	}

	/** {@inheritdoc} */
	public co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP consultarMigracionORIP(
	    TipoEntradaConsultaMigracionORIP atecmo_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                                                                                                lsw_watch;
		co.gov.supernotariado.www.services.bachue.cb.consultamigracion.consultarMigracionORIP.v1.TipoSalidaConsultaMigracionORIP ltscmp_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltscmp_return     = getConsultaMigracionBusiness().consultarMigracionORIP(
			    atecmo_entrada, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "ConsultaMigracionBean", "consultarMigracionORIP", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscmp_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultaMigracionPredio consultarMigracionPredio(
	    TipoEntradaConsultaMigracionPredio atecmp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		TipoSalidaConsultaMigracionPredio ltscmp_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltscmp_return     = getConsultaMigracionBusiness()
				                    .consultarMigracionPredio(atecmp_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "ConsultaMigracionBean", "consultarMigracionPredio", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscmp_return;
	}
}
