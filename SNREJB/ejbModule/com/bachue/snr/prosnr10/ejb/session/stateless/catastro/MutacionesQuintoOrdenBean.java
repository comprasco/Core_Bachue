package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoSalidaRegistrarCambioQuintoOrden;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.MutacionesQuintoOrdenBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MutacionesQuintoOrden.
 *
 * @author Carlos Calderon
 *
 */
@javax.ejb.Stateless(name = "MutacionesQuintoOrden", mappedName = "mutacionesQuintoOrdenMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MutacionesQuintoOrdenBean implements MutacionesQuintoOrdenRemote
{
	/** Propiedad imqorb business. */
	private MutacionesQuintoOrdenBusiness imqorb_business;

	/**
	 * Retorna el valor de mutaciones quinto orden business.
	 *
	 * @return el valor de mutaciones quinto orden business
	 */
	public MutacionesQuintoOrdenBusiness getMutacionesQuintoOrdenBusiness()
	{
		if(imqorb_business == null)
			imqorb_business = new MutacionesQuintoOrdenBusiness();

		return imqorb_business;
	}

	/**
	 * Permite informar a Bachué los cambios que ocurren como consecuencia de la inscripción de predios o mejoras por
	 * edificaciones no declarados u omitidos durante la formación catastral o la actualización de la formación del catastro.
	 *
	 * @param atercqo_entrada de atercqo entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return de tipo salida registrar cambio quinto orden
	 * @throws B2BException de b 2 B exception
	 */
	public TipoSalidaRegistrarCambioQuintoOrden registraCambioQuintoOrden(
	    TipoEntradaRegistrarCambioQuintoOrden atercqo_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaRegistrarCambioQuintoOrden ltsrcqo_return;

		lsw_watch          = Logger.getNewStopWatch();
		ltsrcqo_return     = getMutacionesQuintoOrdenBusiness()
				                     .registraCambioQuintoOrden(atercqo_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "MutacionesQuintoOrdenBean", "registraCambioQuintoOrden", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsrcqo_return;
	}
}
