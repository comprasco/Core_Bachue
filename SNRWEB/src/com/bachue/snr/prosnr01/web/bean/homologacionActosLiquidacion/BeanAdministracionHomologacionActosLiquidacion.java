package com.bachue.snr.prosnr01.web.bean.homologacionActosLiquidacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.admHistoricosMisional.AdmHistoricosMisionalRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanAdministracionHomologacionActosLiquidacion.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 16/06/2020
 */
@SessionScoped
@ManagedBean(name = "beanAdministracionHomologacionActosLiquidacion")
public class BeanAdministracionHomologacionActosLiquidacion extends BeanHomologacionActosLiquidacion
    implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6361991893499425576L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanAdministracionHomologacionActosLiquidacion.class);

	/** Propiedad iahmr adm historicos misional remote. */
	@EJB
	private AdmHistoricosMisionalRemote iahmr_admHistoricosMisionalRemote;

	/**
	 * Generar datos tramite cantidad turnos homologacion.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarDatosTramiteCantidadTurnosHomologacion()
	    throws B2BException
	{
		try
		{
			FacesContext lfc_context;
			boolean      lb_focus;

			lfc_context     = FacesContext.getCurrentInstance();
			lb_focus        = true;

			String ls_turnoConsulta;

			ls_turnoConsulta = getIdTurnoConsulta();

			lb_focus = validateStyles(
				    ":fHomologacionActosLiquidacion:idTurno", lfc_context, ls_turnoConsulta, lb_focus
				);

			if(!lb_focus)
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_TURNO_VALIDO);

			{
				TramiteCantidad ltc_tc;

				ltc_tc = iahmr_admHistoricosMisionalRemote.generarDatosTramiteCantidadTurnosHomologacion(
					    ls_turnoConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(ltc_tc != null)
				{
					String ls_nir;

					ls_nir = ltc_tc.getNir();

					if(StringUtils.isValidString(ls_nir))
					{
						Collection<TramiteCantidad> lctc_ctc;

						lctc_ctc = ltc_tc.getTurnos();
						setNirConsulta(ls_nir);

						if(CollectionUtils.isValidCollection(lctc_ctc))
						{
							setDatosTramiteCantidad(lctc_ctc);
							setTotalBandeja(lctc_ctc.size());

							addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
						}
						else
							throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
					}
					else
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
				}
				else
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_INTENTE_NUEVAMENTE);
			}
		}
		catch(B2BException lb2be_e)
		{
			setNirConsulta(null);
			setDatosTramiteCantidad(null);
			setTotalBandeja(0);
			clh_LOGGER.error("generarDatosTramiteCantidadTurnosHomologacion", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Terminar proceso.
	 */
	public void terminarProceso()
	{
		try
		{
			Collection<TramiteCantidad> ltc_tc;

			ltc_tc = getDatosTramiteCantidad();

			if(CollectionUtils.isValidCollection(ltc_tc))
			{
				for(TramiteCantidad ltc_tmp : ltc_tc)
				{
					if((ltc_tmp != null) && !ltc_tmp.isHomologado())
						throw new B2BException(ErrorKeys.ERROR_HOMOLOGAR_TURNOS_ACTOS);
				}

				addMessage(MessagesKeys.HOMOLOGACION_ACTOS_TURNOS_SATISFACTORIA);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarProceso", lb2be_e);
			addMessage(lb2be_e);
		}
	}
}
