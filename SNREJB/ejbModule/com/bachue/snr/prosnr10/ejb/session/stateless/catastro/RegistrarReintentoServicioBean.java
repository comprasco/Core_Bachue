package com.bachue.snr.prosnr10.ejb.session.stateless.catastro;

import co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoEntradaRegistrarReintentoServicio;
import co.gov.supernotariado.www.schemas.bachue.ci.recepcionNotificacionBachue.registrarReintentoServicio.v1.TipoSalidaRegistrarReintentoServicio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr10.business.catastro.RegistrarReintentoServicioBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades RegistrarReintentoServicioBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 12/05/2020
 */
@javax.ejb.Stateless(name = "RegistrarReintentoServicio", mappedName = "registrarReintentoServicioBeanMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class RegistrarReintentoServicioBean implements RegistrarReintentoServicioRemote
{
	/** Propiedad irrsb business. */
	private RegistrarReintentoServicioBusiness irrsb_business;

	/**
	 * Retorna Objeto o variable de valor registrar reintento servicio business.
	 *
	 * @return el valor de registrar reintento servicio business
	 */
	public RegistrarReintentoServicioBusiness getRegistrarReintentoServicioBusiness()
	{
		if(irrsb_business == null)
			irrsb_business = new RegistrarReintentoServicioBusiness();

		return irrsb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaRegistrarReintentoServicio registrarReintentoServicio(
	    TipoEntradaRegistrarReintentoServicio aterrs_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaRegistrarReintentoServicio ltsrrs_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltsrrs_return     = getRegistrarReintentoServicioBusiness()
				                    .registrarReintentoServicio(aterrs_entrada, as_userId, as_remoteIp);
		Logger.log(
		    lsw_watch, "RegistrarReintentoServicioBean", "registrarReintentoServicio", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltsrrs_return;
	}
}
