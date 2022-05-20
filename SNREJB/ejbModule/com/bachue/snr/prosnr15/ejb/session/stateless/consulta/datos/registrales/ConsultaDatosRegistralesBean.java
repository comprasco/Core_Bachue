package com.bachue.snr.prosnr15.ejb.session.stateless.consulta.datos.registrales;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoEntradaConsultarDatosRegistrales;
import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.TipoSalidaConsultarDatosRegistrales;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr15.business.consulta.datos.registrales.ConsultaDatosRegistralesBusiness;

import org.perf4j.StopWatch;


/**
 * Clase para la implementación de los metodos de la interfaz remota del servicio Consulta Datos Registrales.
 *
 * @author Gabriel Arias
 *
 */
@javax.ejb.Stateless(name = "ConsultaDatosRegistrales", mappedName = "consultaDatosRegistralesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaDatosRegistralesBean implements ConsultaDatosRegistralesRemote
{
	/** Propiedad icdrb business. */
	private ConsultaDatosRegistralesBusiness icdrb_business;
	
	/** {@inheritdoc} */
	public TipoSalidaConsultarDatosRegistrales consultarDatosRegistrales(
	    TipoEntradaConsultarDatosRegistrales atecdr_entrada, String as_userId, String as_localIpAddress,
	    String                               as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		TipoSalidaConsultarDatosRegistrales lp_persona;

		lsw_watch      = Logger.getNewStopWatch();
		lp_persona     = getBusiness()
				                 .consultarDatosRegistrales(
				    atecdr_entrada, as_userId, as_localIpAddress, as_remoteIpAddress
				);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "consultarDatosRegistrales", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);

		return lp_persona;
	}

	/**
	 * Retorna el valor de generación solicitud business.
	 *
	 * @return el valor de generación solicitud business
	 */
	private ConsultaDatosRegistralesBusiness getBusiness()
	{
		if(icdrb_business == null)
			icdrb_business = new ConsultaDatosRegistralesBusiness();

		return icdrb_business;
	}
}
