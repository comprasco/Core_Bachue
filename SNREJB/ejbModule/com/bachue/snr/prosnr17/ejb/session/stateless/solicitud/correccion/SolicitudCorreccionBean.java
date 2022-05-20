package com.bachue.snr.prosnr17.ejb.session.stateless.solicitud.correccion;

import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoEntradaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.ingresosolicitud.v1.TipoSalidaIngresoSolicitud;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoObtenerCausalesCorreccion;
import co.gov.supernotariado.www.schemas.bachue.ee.solicituddecorreccion.obtenercausalescorreccion.v1.TipoSalidaObtenerCausalesCorreccion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr17.business.solicitud.correccion.SolicitudCorreccionBusiness;

import org.perf4j.StopWatch;


/**
 * Clase para la implementación de los metodos de la interfaz remota del servicio Solicitud de Corrección.
 *
 * @author Gabriel Arias
 *
 */
@javax.ejb.Stateless(name = "SolicitudCorreccion", mappedName = "solicitudCorreccionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class SolicitudCorreccionBean implements SolicitudCorreccionRemote
{
	/** Propiedad icdrb business. */
	private SolicitudCorreccionBusiness iscb_business;

	/** {@inheritdoc} */
	public TipoSalidaIngresoSolicitud ingresoSolicitud(
	    TipoEntradaIngresoSolicitud ateis_teis, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws Exception
	{
		StopWatch                  lsw_watch;
		TipoSalidaIngresoSolicitud itsis_return;

		lsw_watch        = Logger.getNewStopWatch();
		itsis_return     = getBusiness().ingresoSolicitud(ateis_teis, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "ingresoSolicitud", as_userId, as_localIp, as_remoteIp, null);

		return itsis_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaObtenerCausalesCorreccion obtenerCausalesCorreccion(
	    TipoObtenerCausalesCorreccion atocc_tocc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		TipoSalidaObtenerCausalesCorreccion itsocc_return;

		lsw_watch         = Logger.getNewStopWatch();
		itsocc_return     = getBusiness().consultarDatosRegistrales(atocc_tocc, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "obtenerCausalesCorreccion", as_userId, as_localIp, as_remoteIp, null
		);

		return itsocc_return;
	}

	/**
	 * Retorna el valor de solicitud corrección business.
	 *
	 * @return el valor de solicitud corrección business
	 */
	private SolicitudCorreccionBusiness getBusiness()
	{
		if(iscb_business == null)
			iscb_business = new SolicitudCorreccionBusiness();

		return iscb_business;
	}
}
