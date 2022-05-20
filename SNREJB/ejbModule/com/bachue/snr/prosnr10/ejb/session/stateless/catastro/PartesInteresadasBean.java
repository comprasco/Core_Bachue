package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoEntradaConsultarPartesInteresadas;
import co.gov.supernotariado.www.schemas.bachue.ee.partesinteresadas.consultarpartesinteresadas.v1.TipoSalidaConsultarPartesInteresadas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.PartesInteresadasBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades PartesInteresadasBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 4/03/2020
 */
@javax.ejb.Stateless(name = "PartesInteresadas", mappedName = "PartesInteresadasMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class PartesInteresadasBean implements PartesInteresadasRemote
{
	/** Propiedad imrrrb business. */
	private PartesInteresadasBusiness ipib_business;

	/**
	 * Retorna Objeto o variable de valor partes interesadas bussines.
	 *
	 * @return el valor de matricuas RRR bussines
	 */
	public PartesInteresadasBusiness getPartesInteresadasBussines()
	{
		if(ipib_business == null)
			ipib_business = new PartesInteresadasBusiness();

		return ipib_business;
	}

	/**
	 * Consultar partes interesadas.
	 *
	 * @param atecpi_entrada de atecpi entrada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo salida consultar partes interesadas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarPartesInteresadas consultarPartesInteresadas(
	    TipoEntradaConsultarPartesInteresadas atecpi_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaConsultarPartesInteresadas ltscpi_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltscpi_return     = getPartesInteresadasBussines()
				                    .consultarPartesInteresadas(atecpi_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "PartesInteresadasBean", "consultarPartesInteresadas", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscpi_return;
	}
}
