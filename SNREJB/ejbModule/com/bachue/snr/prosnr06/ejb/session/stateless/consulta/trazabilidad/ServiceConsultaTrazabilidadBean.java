package com.bachue.snr.prosnr06.ejb.session.stateless.consulta.trazabilidad;

import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoEntradaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaActosTurno.v1.TipoSalidaConsultaActosTurno;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoEntradaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultaDetalleCertificado.v1.TipoSalidaConsultaDetalleCertificado;
import co.gov.supernotariado.www.schemas.bachue.cb.consultatrazabilidad.consultartrazabilidad.v1.TipoEntradaConsultarTrazabilidad;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.trazabilidad.ServiceConsultaTrazabilidadBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.ConsultaTrazabilidad;

import org.perf4j.StopWatch;


/**
 * Class que contiene todos las propiedades ServiceConsultaTrazabilidadBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "ServiceConsultaTrazabilidad", mappedName = "serviceConsultaTrazabilidadMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ServiceConsultaTrazabilidadBean implements ConsultaTrazabilidadRemote
{
	/** Propiedad isctb business. */
	private ServiceConsultaTrazabilidadBusiness isctb_business;

	/**
	 * Retorna el valor de service consulta trazabilidad business.
	 *
	 * @return el valor de service consulta trazabilidad business
	 */
	public ServiceConsultaTrazabilidadBusiness getServiceConsultaTrazabilidadBusiness()
	{
		if(isctb_business == null)
			isctb_business = new ServiceConsultaTrazabilidadBusiness();

		return isctb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultaActosTurno consultaActosTurno(
	    TipoEntradaConsultaActosTurno atecat_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		TipoSalidaConsultaActosTurno ltscat_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscat_response = getServiceConsultaTrazabilidadBusiness()
				                  .consultaActosTurno(atecat_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "ServiceConsultaTrazabilidadBean", "consultaActosTurno", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscat_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultaDetalleCertificado consultaDetalleCertificado(
	    TipoEntradaConsultaDetalleCertificado atecdc_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaConsultaDetalleCertificado ltscdc_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscdc_response = getServiceConsultaTrazabilidadBusiness()
				                  .consultaDetalleCertificado(atecdc_peticion, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "ServiceConsultaTrazabilidadBean", "consultaDetalleCertificado", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltscdc_response;
	}

	/** {@inheritdoc} */
	public ConsultaTrazabilidad consultarTrazabilidad(
	    TipoEntradaConsultarTrazabilidad atect_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		ConsultaTrazabilidad lsct_response;

		lsw_watch     = Logger.getNewStopWatch();

		lsct_response = getServiceConsultaTrazabilidadBusiness()
				                .consultarTrazabilidad(atect_entrada, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "ServiceConsultaTrazabilidadBean", "consultarTrazabilidad", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lsct_response;
	}
}
