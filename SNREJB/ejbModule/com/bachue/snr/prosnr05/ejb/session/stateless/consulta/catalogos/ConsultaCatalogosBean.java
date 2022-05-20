package com.bachue.snr.prosnr05.ejb.session.stateless.consulta.catalogos;

import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TipoEntradaConsultarCatalogos;
import co.gov.supernotariado.www.schemas.bachue.cb.consultacatalogos.consultarcatalogos.v1.TiposCatalogos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr05.business.consulta.catalogos.ConsultaCatalogosBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConsultaCatalogosBean.
 *
 * @author Manuel Blanco
 */
@javax.ejb.Stateless(name = "ConsultaCatalogos", mappedName = "consultaCatalogosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaCatalogosBean implements ConsultaCatalogosRemote
{
	/** Propiedad irb business. */
	private ConsultaCatalogosBusiness irb_business;

	/**
	 * Retorna el valor de consulta catalogos business.
	 *
	 * @return el valor de consulta catalogos business
	 */
	public ConsultaCatalogosBusiness getConsultaCatalogosBusiness()
	{
		if(irb_business == null)
			irb_business = new ConsultaCatalogosBusiness();

		return irb_business;
	}

	/** {@inheritdoc} */
	public TiposCatalogos[] consultarCatalogos(
	    TipoEntradaConsultarCatalogos atecc_param, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		TiposCatalogos[] lceec_return;

		lsw_watch        = Logger.getNewStopWatch();
		lceec_return     = getConsultaCatalogosBusiness().consultarCatalogos(atecc_param);

		Logger.log(lsw_watch, "ConsultaCatalogosBean", "consultarCatalogos", as_userId, as_localIp, as_remoteIp, null);

		return lceec_return;
	}
}
