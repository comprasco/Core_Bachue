package com.bachue.snr.prosnr13.ejb.session.stateless.generacionSolicitud;

import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoEntradaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.consultarestadosolicitud.v1.TipoSalidaConsultarEstadoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoEntradaGenerarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.generacionsolicitud.generarsolicitud.v1.TipoSalidaGenerarSolicitud;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr13.business.generacionSolicitud.GeneracionSolicitudBusiness;

import org.perf4j.StopWatch;


/**
 * Clase para la implementación de los metodos de la interfaz remota del servicio Generación Solicitud.
 *
 * @author Manuel Blanco
 *
 */
@javax.ejb.Stateless(name = "GeneracionSolicitud", mappedName = "generacionSolicitudMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GeneracionSolicitudBean implements GeneracionSolicitudRemote
{
	/** Propiedad igsb business. */
	private GeneracionSolicitudBusiness igsb_business;

	/**
	 * Retorna el valor de generación solicitud business.
	 *
	 * @return el valor de generación solicitud business
	 */
	private GeneracionSolicitudBusiness getBusiness()
	{
		if(igsb_business == null)
			igsb_business = new GeneracionSolicitudBusiness();

		return igsb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaGenerarSolicitud generarSolicitud(
	    TipoEntradaGenerarSolicitud ateiu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		TipoSalidaGenerarSolicitud ltsiu_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsiu_response = getBusiness().generarSolicitud(ateiu_request, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "GeneracionSolicitudBean", "generarSolicitud", as_userId, as_localIp, as_remoteIp, null);

		return ltsiu_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarEstadoSolicitud consultarEstadoSolicitud(
	    TipoEntradaConsultarEstadoSolicitud ateces_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaConsultarEstadoSolicitud ltsces_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsces_response = getBusiness().consultarEstadoSolicitud(ateces_request);

		Logger.log(
		    lsw_watch, "GeneracionSolicitudBean", "consultarEstadoSolicitud", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsces_response;
	}
}
