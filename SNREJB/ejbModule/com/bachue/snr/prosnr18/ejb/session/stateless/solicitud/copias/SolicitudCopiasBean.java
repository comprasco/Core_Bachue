package com.bachue.snr.prosnr18.ejb.session.stateless.solicitud.copias;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoEntradaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.consultarsolicitud.v1.TipoSalidaConsultarSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecopias.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr18.business.solicitud.copias.SolicitudCopiasBusiness;

import org.perf4j.StopWatch;


/**
 * Clase para la implementación de los metodos de la interfaz remota del servicio Solicitud de Copias.
 *
 * @author Gabriel Arias
 *
 */
@javax.ejb.Stateless(name = "SolicitudCopias", mappedName = "solicitudCopiasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class SolicitudCopiasBean implements SolicitudCopiasRemote
{
	/** Propiedad icdrb business. */
	private SolicitudCopiasBusiness iscb_business;

	/** {@inheritdoc} */
	public TipoSalidaConsultarSolicitud consultarSolicitud(
	    TipoEntradaConsultarSolicitud atecs_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		TipoSalidaConsultarSolicitud ltscs_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltscs_return     = getBusiness().consultarSolicitud(atecs_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "consultarSolicitud", as_userId, as_localIp, as_remoteIp, null);

		return ltscs_return;
	}

	/**
	 * Método encargado de realizar la operacion ingresoSolicitud.
	 *
	 * @param ateis_data Objeto que contiene la información de entrada.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip local
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la información de salida.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaIngresoSolicitud ingresoSolicitud(
	    TipoEntradaIngresoSolicitud ateis_teis, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		TipoSalidaIngresoSolicitud itsis_return;

		lsw_watch        = Logger.getNewStopWatch();
		itsis_return     = getBusiness().ingresoSolicitud(ateis_teis, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "ingresoSolicitud", as_userId, as_localIp, as_remoteIp, null);

		return itsis_return;
	}

	/**
	 * Retorna el valor de solicitud corrección business.
	 *
	 * @return el valor de solicitud corrección business
	 */
	private SolicitudCopiasBusiness getBusiness()
	{
		if(iscb_business == null)
			iscb_business = new SolicitudCopiasBusiness();

		return iscb_business;
	}
}
