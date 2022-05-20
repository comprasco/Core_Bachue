package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoEntradaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionConCambioPropietario.v1.TipoSalidaConsultaSegregacionConCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoEntradaConsultaSegregacionSinCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioLinderosPredios.consultaSegregacionSinCambioPropietario.v1.TipoSalidaConsultaSegregacionSinCambioPropietario;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.CambioLinderosPredioBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades CambioLinderosPredioBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/03/2020
 */
@javax.ejb.Stateless(name = "CambioLinderosPredioBean", mappedName = "cambioLinderosPredioBeanMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CambioLinderosPredioBean implements CambioLinderosPredioRemote
{
	/** Propiedad iclpb business. */
	private CambioLinderosPredioBusiness iclpb_business;

	/**
	 * Retorna Objeto o variable de valor cambio linderos predio business.
	 *
	 * @return el valor de cambio linderos predio business
	 */
	public CambioLinderosPredioBusiness getCambioLinderosPredioBusiness()
	{
		if(iclpb_business == null)
			iclpb_business = new CambioLinderosPredioBusiness();

		return iclpb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultaSegregacionConCambioPropietario consultaSegregacionConCambioPropietario(
	    TipoEntradaConsultaSegregacionConCambioPropietario atecsccp_entrada, String as_userId, String as_localIp,
	    String                                             as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                         lsw_watch;
		TipoSalidaConsultaSegregacionConCambioPropietario ltscsccp_return;

		lsw_watch           = Logger.getNewStopWatch();
		ltscsccp_return     = getCambioLinderosPredioBusiness()
				                      .consultaSegregacionConCambioPropietario(
				    atecsccp_entrada, as_userId, as_remoteIp
				);
		Logger.log(
		    lsw_watch, "CambioLinderosPredioBean", "consultaSegregacionConCambioPropietario", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltscsccp_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultaSegregacionSinCambioPropietario consultaSegregacionSinCambioPropietario(
	    TipoEntradaConsultaSegregacionSinCambioPropietario atecsscp_entrada, String as_userId, String as_localIp,
	    String                                             as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                         lsw_watch;
		TipoSalidaConsultaSegregacionSinCambioPropietario ltscsscp_return;

		lsw_watch           = Logger.getNewStopWatch();
		ltscsscp_return     = getCambioLinderosPredioBusiness()
				                      .consultaSegregacionSinCambioPropietario(
				    atecsscp_entrada, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "CambioLinderosPredioBean", "consultaSegregacionSinCambioPropietario", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltscsscp_return;
	}
}
