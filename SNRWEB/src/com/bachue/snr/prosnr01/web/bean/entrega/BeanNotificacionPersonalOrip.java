package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;

import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanNotificacionPersonalOrip.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 2/04/2020
 */
@SessionScoped
@ManagedBean(name = "beanNotificacionPersonalOrip")
public class BeanNotificacionPersonalOrip extends BeanEntrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 99449644735583798L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanNotificacionPersonalOrip.class);

	/** {@inheritdoc} */
	@Override
	public void generarDatosTramiteCantidad()
	{
		Collection<TramiteCantidad> lctc_tramitesCantidad;

		lctc_tramitesCantidad = null;

		try
		{
			TramiteCantidad ltc_tc;
			int             li_cantidadPorEtapas;

			ltc_tc                   = new TramiteCantidad();
			li_cantidadPorEtapas     = 0;

			ltc_tc.setUsuario(getUserId());
			ltc_tc.setIdTurno(getIdTurnoConsulta());
			ltc_tc.setNir(getNirConsulta());

			lctc_tramitesCantidad = inr_notificacionesRemote.findInboxByTurnoNirNotificacionPersonal(
				    ltc_tc, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lctc_tramitesCantidad))
			{
				for(TramiteCantidad ltc_actual : lctc_tramitesCantidad)
				{
					if(ltc_actual != null)
						li_cantidadPorEtapas = li_cantidadPorEtapas
							+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
							? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);
				}
			}

			setTotalBandeja(li_cantidadPorEtapas);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDatosTramiteCantidad", lb2be_e);
			addMessage(lb2be_e);
		}

		setDatosTramiteCantidad(lctc_tramitesCantidad);
	}

	/**
	 * Return pages.
	 *
	 * @return el valor de string
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = null;

		try
		{
			long ll_idEtapa;

			ll_idEtapa = getIdEtapa();

			if(ll_idEtapa > 0)
			{
				BeanDetalleNotificacionPersonalOrip lbdnpo_bean;

				lbdnpo_bean = (BeanDetalleNotificacionPersonalOrip)FacesUtils.obtenerInstanciaBean(
					    BeanDetalleNotificacionPersonalOrip.class,
					    BeanNameCommon.BEAN_DETALLE_NOTIFICACION_PERSONAL_ORIP
					);

				if(lbdnpo_bean != null)
				{
					lbdnpo_bean.iniciar();
					lbdnpo_bean.setIdEtapa(ll_idEtapa);
					lbdnpo_bean.setBandejaentrada(true);
					lbdnpo_bean.setDatosTramiteTurno(null);
					lbdnpo_bean.setIdTurno(getIdTurnoConsulta());
					lbdnpo_bean.setNir(getNirConsulta());
					lbdnpo_bean.setEntrega(null);
					lbdnpo_bean.obtenerTurnos(true);
					ls_return = NavegacionCommon.DETALLE_NOTIFICACION_PERSONAL;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("returnPages", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}
}
