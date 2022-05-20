package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoEntradaRegistrarCambioCuartoOrden;
import co.gov.supernotariado.www.schemas.bachue.ee.mutacionesCuartoOrden.registrarCambioCuartoOrden.v1.TipoSalidaRegistrarCambioCuartoOrden;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.MutacionesCuartoOrdenBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MutacionesCuartoOrden.
 *
 * @author Carlos Calderon
 *
 */
@javax.ejb.Stateless(name = "MutacionesCuartoOrden", mappedName = "mutacionesCuartoOrdenMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MutacionesCuartoOrdenBean implements MutacionesCuartoOrdenRemote
{
	/** Propiedad imcorb business. */
	private MutacionesCuartoOrdenBusiness imcorb_business;

	/**
	 * Retorna Objeto o variable de valor mutaciones cuarto orden business.
	 *
	 * @return el valor de mutaciones cuarto orden business
	 */
	public MutacionesCuartoOrdenBusiness getMutacionesCuartoOrdenBusiness()
	{
		if(imcorb_business == null)
			imcorb_business = new MutacionesCuartoOrdenBusiness();

		return imcorb_business;
	}

	/**
	 * Permite informar a Bachué los cambios que ocurren como consecuencia de la inscripción de predios o mejoras por
	 * edificaciones no declarados u omitidos durante la formación catastral o la actualización de la formación del catastro.
	 *
	 * @param atercco_entrada de atercco entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return de tipo salida registrar cambio cuarto orden
	 * @throws B2BException de b 2 B exception
	 */
	public TipoSalidaRegistrarCambioCuartoOrden registraCambioCuartoOrden(
	    TipoEntradaRegistrarCambioCuartoOrden atercco_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaRegistrarCambioCuartoOrden ltsrcco_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltsrcco_return     = getMutacionesCuartoOrdenBusiness()
				                     .registraCambioCuartoOrden(atercco_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "MutacionesCuartoOrdenBean", "registraCambioCuartoOrden", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsrcco_return;
	}
}
