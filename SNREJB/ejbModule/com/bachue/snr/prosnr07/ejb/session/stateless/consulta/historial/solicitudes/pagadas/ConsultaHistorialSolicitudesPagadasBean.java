package com.bachue.snr.prosnr07.ejb.session.stateless.consulta.historial.solicitudes.pagadas;

import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoEntradaBuscarSolicitudes;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.buscarsolicitudes.v1.TipoSalidaBuscarSolicitudes;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoEntradaConsultarDetalleSolicitud;
import co.gov.supernotariado.www.schemas.bachue.cb.consultahistorialsolicitudespagadas.consultardetallesolicitud.v1.TipoSalidaConsultarDetalleSolicitud;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr07.business.consulta.historial.solicitudes.pagadas.ConsultaHistorialSolicitudesPagadasBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConsultaHistorialSolicitudesPagadasBean.
 *
 * @author Gabriel Arias
 */
@javax.ejb.Stateless(name = "ConsultaHistorialSolicitudesPagadas", mappedName = "consultaHistorialSolicitudesPagadasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaHistorialSolicitudesPagadasBean implements ConsultaHistorialSolicitudesPagadasRemote
{
	/** Propiedad ischsp business. */
	private ConsultaHistorialSolicitudesPagadasBusiness ischsp_business;

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	public ConsultaHistorialSolicitudesPagadasBusiness getBusiness()
	{
		if(ischsp_business == null)
			ischsp_business = new ConsultaHistorialSolicitudesPagadasBusiness();

		return ischsp_business;
	}

	/**
	 * Buscar solicitudes.
	 *
	 * @param atbs_param de atbs param
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida buscar solicitudes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaBuscarSolicitudes buscarSolicitudes(
	    TipoEntradaBuscarSolicitudes atbs_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		TipoSalidaBuscarSolicitudes ltsa_response;

		lsw_watch         = Logger.getNewStopWatch();
		ltsa_response     = getBusiness().buscarSolicitudes(atbs_param, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "ConsultaHistorialSolicitudesPagadasBean", "buscarSolicitudes", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltsa_response;
	}

	/**
	 * Consultar detalle solicitud.
	 *
	 * @param atecds_entrada de atecds entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar detalle solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarDetalleSolicitud consultarDetalleSolicitud(
	    TipoEntradaConsultarDetalleSolicitud atecds_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		TipoSalidaConsultarDetalleSolicitud ltscds_response;

		lsw_watch           = Logger.getNewStopWatch();
		ltscds_response     = getBusiness().consultarDetalleSolicitud(
			    atecds_entrada, as_userId, as_localIp, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "ConsultaHistorialSolicitudesPagadasBean", "consultarDetalleSolicitud", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltscds_response;
	}
}
