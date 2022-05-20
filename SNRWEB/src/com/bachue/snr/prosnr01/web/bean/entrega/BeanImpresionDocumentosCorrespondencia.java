package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;

import java.io.Serializable;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades de ImpresionDocumentosCorrespondencia.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 29/03/2020
 */
@SessionScoped
@ManagedBean(name = "beanImpresionDocumentosCorrespondencia")
public class BeanImpresionDocumentosCorrespondencia extends BeanEntrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8540277817285238490L;

	/** Propiedad il id etapaCorrespondencia. */
	private long il_idEtapaCorrespondencia;

	/**
	 * @param ab_b the idEtapaCorrespondencia to set
	 */
	public void setIdEtapaCorrespondencia(long ab_b)
	{
		il_idEtapaCorrespondencia = ab_b;
	}

	/**
	 * @return the idEtapaCorrespondencia
	 */
	public long getIdEtapaCorrespondencia()
	{
		return il_idEtapaCorrespondencia;
	}

	/**
	 * Generar datos tramite cantidad.
	 */
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

			ltc_tc.setIdEtapa(NumericUtils.getLongWrapper(getIdEtapaCorrespondencia()));

			ltc_tc.setUsuario(getUserId());
			ltc_tc.setIdTurno(getIdTurnoConsulta());
			ltc_tc.setNir(getNirConsulta());

			lctc_tramitesCantidad = ier_entregaRemote.findInboxByTurnoNirCorrespondencia(
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

			if(!CollectionUtils.isValidCollection(lctc_tramitesCantidad))
				addMessageInfo(EstadoCommon.W, MessagesKeys.NO_SE_ENCONTRARON_REGISTROS);
		}
		catch(B2BException lb2be_e)
		{
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
		return returnPages(true);
	}
}
