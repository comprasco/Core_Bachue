package com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia;

import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoEntradaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropiedades.v1.TipoSalidaConsultarHistoricoPropiedades;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoEntradaConsultarHistoricoPropietarios;
import co.gov.supernotariado.www.services.bachue.cb.SBB_CB_HistoricoPropietarios.consultarHistoricoPropietarios.v1.TipoSalidaConsultarHistoricoPropietarios;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr14.business.coexistencia.HistoricoPropietariosBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades HistoricoPropietariosBean.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/03/2020
 */
@javax.ejb.Stateless(name = "historicoPropietarios", mappedName = "historicoPropietariosMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class HistoricoPropietariosBean implements HistoricoPropietariosRemote
{
	/** Propiedad ihpb business. */
	private HistoricoPropietariosBusiness ihpb_business;

	/**
	 * Retorna Objeto o variable de valor historico propietarios business.
	 *
	 * @return el valor de historico propietarios business
	 */
	public HistoricoPropietariosBusiness getHistoricoPropietariosBusiness()
	{
		if(ihpb_business == null)
			ihpb_business = new HistoricoPropietariosBusiness();

		return ihpb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarHistoricoPropiedades consultarHistoricoPropiedades(
	    TipoEntradaConsultarHistoricoPropiedades atechp_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                               lsw_watch;
		TipoSalidaConsultarHistoricoPropiedades ltchp_return;

		lsw_watch        = Logger.getNewStopWatch();
		ltchp_return     = getHistoricoPropietariosBusiness()
				                   .consultarHistoricoPropiedades(atechp_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "SalvedadesBean", "consultarHistoricoPropiedades", as_userId, as_localIp, as_remoteIp, null
		);

		return ltchp_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarHistoricoPropietarios consultarHistoricoPropietarios(
	    TipoEntradaConsultarHistoricoPropietarios atechp_entrada, String as_userId, String as_localIp,
	    String                                    as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                lsw_watch;
		TipoSalidaConsultarHistoricoPropietarios ltschp_return;

		lsw_watch         = Logger.getNewStopWatch();
		ltschp_return     = getHistoricoPropietariosBusiness()
				                    .consultarHistoricoPropietarios(atechp_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "SalvedadesBean", "consultarHistoricoPropietarios", as_userId, as_localIp, as_remoteIp, null
		);

		return ltschp_return;
	}
}
