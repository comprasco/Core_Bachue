package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoEntradaConsultarCambioPropietario;
import co.gov.supernotariado.www.schemas.bachue.ee.cambioPropietario.consultarCambioPropietario.v1.TipoSalidaConsultarCambioPropietario;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.CambioPropietarioBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades CambioPropietarioBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 21/04/2020
 */
@javax.ejb.Stateless(name = "CambioPropietario", mappedName = "cambioPropietarioBeanMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CambioPropietarioBean implements CambioPropietarioRemote
{
	/** Propiedad icpb business. */
	private CambioPropietarioBusiness icpb_business;

	/**
	 * Retorna Objeto o variable de valor cambio propietario business.
	 *
	 * @return el valor de cambio propietario business
	 */
	public CambioPropietarioBusiness getCambioPropietarioBusiness()
	{
		if(icpb_business == null)
			icpb_business = new CambioPropietarioBusiness();

		return icpb_business;
	}

	/**
	 * Consultar cambio propietario.
	 *
	 * @param ateccp_entrada de atecsccp entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar cambio propietario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarCambioPropietario consultarCambioPropietario(
	    TipoEntradaConsultarCambioPropietario ateccp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaConsultarCambioPropietario ltsccp_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsccp_return     = getCambioPropietarioBusiness()
				                    .consultarCambioPropietario(ateccp_entrada, as_userId, as_remoteIp);
		Logger.log(
		    lsw_watch, "CambioPropietario", "consultarCambioPropietario", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsccp_return;
	}
}
