package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoEntradaIndicePropietarios;
import co.gov.supernotariado.www.services.bachue.cb.indicepropietarios.consultar.v1.TipoSalidaIndicePropietarios;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.IndicePropietariosBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades IndicePropietariosBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
@javax.ejb.Stateless(name = "indicePropietarios", mappedName = "indicePropietariosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class IndicePropietariosBean implements IndicePropietariosRemote
{
	/** Propiedad iip business. */
	private IndicePropietariosBusiness iip_business;

	/**
	 * Retorna Objeto o variable de valor indice propietarios business.
	 *
	 * @return el valor de indice propietarios business
	 */
	public IndicePropietariosBusiness getIndicePropietariosBusiness()
	{
		if(iip_business == null)
			iip_business = new IndicePropietariosBusiness();

		return iip_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaIndicePropietarios consultar(
	    TipoEntradaIndicePropietarios ateip_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		TipoSalidaIndicePropietarios ltsdi_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltsdi_return     = getIndicePropietariosBusiness().consultar(ateip_entrada, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "InmueblesBean", "consultarDatosInmueble", as_userId, as_localIp, as_remoteIp, null);

		return ltsdi_return;
	}
}
