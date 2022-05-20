package com.bachue.snr.prosnr01.web.bean.entrega;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;

import com.bachue.snr.prosnr01.web.util.EntregaUI;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades BeanDetalleNotificacionPersonalOrip.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 4/04/2020
 */
@SessionScoped
@ManagedBean(name = "beanDetalleNotificacionPersonalOrip")
public class BeanDetalleNotificacionPersonalOrip extends BeanDetalleEntrega implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7815322351241731338L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleNotificacionPersonalOrip.class);

	/**
	 *  {@inheritdoc}.
	 */
	@Override
	public void abrirPadFirmas()
	{
		boolean lb_interviene;
		String  ls_tipoDocumento;
		String  ls_numeroDocumento;

		lb_interviene            = intervieneEnEntrega();
		ls_tipoDocumento       = null;
		ls_numeroDocumento     = null;

		if(lb_interviene)
		{
			EntregaUI le_entrega;

			le_entrega = getEntrega();

			if(le_entrega != null)
			{
				SolicitudInterviniente lsi_interviniente;

				lsi_interviniente = le_entrega.getDatosInterSelect();

				if(lsi_interviniente != null)
				{
					Persona lp_persona;

					lp_persona = lsi_interviniente.getPersona();

					if(lp_persona != null)
					{
						ls_tipoDocumento       = lp_persona.getIdDocumentoTipo();
						ls_numeroDocumento     = lp_persona.getNumeroDocumento();
					}
				}
			}
		}
		else
		{
			Persona lpt_personaEntrega;

			lpt_personaEntrega = getPersonaTercero();

			if(lpt_personaEntrega != null)
			{
				ls_tipoDocumento       = lpt_personaEntrega.getIdDocumentoTipo();
				ls_numeroDocumento     = lpt_personaEntrega.getNumeroDocumento();
			}
		}

		if(StringUtils.isValidString(ls_numeroDocumento) && StringUtils.isValidString(ls_tipoDocumento))
		{
			String ls_idTurno;

			ls_idTurno = obtenerIdTurno();

			if(StringUtils.isValidString(ls_idTurno))
			{
				String ls_url;

				ls_url = generarURLBioClient(ls_idTurno, ls_numeroDocumento, ls_tipoDocumento);

				if(StringUtils.isValidString(ls_url))
					PrimeFaces.current().executeScript("abrirURLBioClient('" + ls_url + "');");
			}
		}
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de string
	 */
	@Override
	public String accionSalvar()
	{
		String ls_return;

		ls_return = NavegacionCommon.DETALLE_NOTIFICACION_PERSONAL;

		try
		{
			inr_notificacionesRemote.salvarNotificacion(
			    getIdTurnoHistoriaEntrega(), getRemoteIpAddress(), getLocalIpAddress(), getUserId()
			);

			ls_return = NavegacionCommon.PRINCIPAL;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("accionSalvar", lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public String accionVolver()
	    throws B2BException
	{
		String                       ls_return;
		BeanNotificacionPersonalOrip lbidc_bean;

		ls_return      = NavegacionCommon.NOTIFICACION_PERSONAL;
		lbidc_bean     = (BeanNotificacionPersonalOrip)FacesUtils.obtenerInstanciaBean(
			    BeanNotificacionPersonalOrip.class, BeanNameCommon.BEAN_NOTIFICACION_PERSONAL_ORIP
			);

		if(lbidc_bean != null)
		{
			try
			{
				lbidc_bean.generarDatosTramiteCantidad();
				clear(false);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("accionVolver", lb2be_e);
				ls_return = null;
				addMessage(lb2be_e);
			}
		}

		return ls_return;
	}

	/**
	 * Actualizar renuncia terminos turno.
	 */
	public void actualizarRenunciaTerminosTurno()
	{
		try
		{
			String ls_interviene;

			ls_interviene = getRenunciaTerminos();

			inr_notificacionesRemote.actualizarRenunciaTerminos(
			    ls_interviene, getIdTurno(), getIdEtapa(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	@Override
	public void firma()
	{
		String    ls_numeroDocumento;
		EntregaUI le_entrega;

		le_entrega     = getEntrega();

		ls_numeroDocumento = null;

		if(le_entrega != null)
		{
			if(intervieneEnEntrega())
			{
				SolicitudInterviniente lsi_interviniente;

				lsi_interviniente = le_entrega.getDatosInterSelect();

				if(lsi_interviniente != null)
				{
					Persona lp_persona;

					lp_persona = lsi_interviniente.getPersona();

					if(lp_persona != null)
						ls_numeroDocumento = lp_persona.getNumeroDocumento();
				}
			}
			else
			{
				Persona lpt_personaEntrega;

				lpt_personaEntrega = getPersonaTercero();

				if(lpt_personaEntrega != null)
					ls_numeroDocumento = lpt_personaEntrega.getNumeroDocumento();
			}

			if(StringUtils.isValidString(ls_numeroDocumento))
			{
				String ls_idTurno;

				ls_idTurno = le_entrega.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno) && StringUtils.isValidString(ls_numeroDocumento))
				{
					StringBuilder lsb_builder;

					lsb_builder = new StringBuilder(ls_idTurno);

					lsb_builder.append(ls_numeroDocumento);
					setIdFirma(lsb_builder.toString());
					PrimeFaces.current()
						          .executeScript(
						    "accionBotonPadFirmasNotificaciones();PF('statusDispositivo').hide();"
						);
				}
			}
		}
	}

	/**
	 * Generar acta notificacion personal.
	 */
	public void generarActaNotificacionPersonal()
	{
		try
		{
			inr_notificacionesRemote.generarActaNotificacionPersonal(
			    obtenerIdTurno(), getSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress(),
			    new ObtenerFirmaDTO(getUserId(), getIdFirma())
			);
			PrimeFaces.current().executeScript("accionBotonGenerarActa();");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarActaNotificacionPersonal", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 *  {@inheritdoc}.
	 */
	@Override
	public void generarDocumentoEntrega()
	{
		try
		{
			EntregaUI le_entrega;

			le_entrega = getEntrega();

			if(le_entrega != null)
			{
				Persona lp_persona;

				lp_persona = null;

				if(!renunciaTerminos())
				{
					SolicitudInterviniente lsi_interviniente;

					lsi_interviniente = le_entrega.getDatosInterSelect();

					if(lsi_interviniente != null)
						lp_persona = lsi_interviniente.getPersona();

					if((lp_persona != null) && (lp_persona.getIdPersona() == null))
						lp_persona.setIdPersona(lsi_interviniente.getIdPersona());
				}
				else
					lp_persona = getPersonaTercero();

				{
					ObtenerFirmaDTO lefg_param;

					lefg_param = new ObtenerFirmaDTO(getUserId(), getIdFirma());

					setDocumentoEntrega(
					    ier_entregaRemote.generarDocumentoEntrega(
					        lefg_param, le_entrega.getIdTurno(), lp_persona, !renunciaTerminos(), getUserId(),
					        getLocalIpAddress(), getRemoteIpAddress()
					    )
					);
					PrimeFaces.current().executeScript("accionBotonGenerarDocumentoEntrega()");
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("generarDocumentoEntrega", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}
	}

	/**
	 * Método encargado de guardar acta OWCC.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarActaOWCC()
	    throws B2BException
	{
		try
		{
			Entrega le_entrega;

			le_entrega = getEntrega();

			if(le_entrega != null)
			{
				String ls_idTurno;

				ls_idTurno = le_entrega.getIdTurno();

				if(StringUtils.isValidString(ls_idTurno))
				{
					inr_notificacionesRemote.guardarActaOWCC(
					    ls_idTurno, null, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
					setDocumentosImprimir(
					    ier_entregaRemote.cargarDocumentosImpresion(
					        ls_idTurno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
					);
					setDocumentoEnOwcc(true);
					PrimeFaces.current().executeScript("accionGuardarActa();");
					addMessage(MessagesKeys.PROCESO_COMPLETADO);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("guardarDocumentoEntrega", le_e);
			addMessage(new B2BException(le_e.getMessage()));
		}
	}

	/**
	 * Renuncia terminos.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	public boolean renunciaTerminos()
	{
		String ls_renuncia;

		ls_renuncia = getRenunciaTerminos();

		return StringUtils.isValidString(ls_renuncia) && ls_renuncia.equalsIgnoreCase(EstadoCommon.S);
	}

	/**
	 * Interviene en entrega.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 */
	public boolean intervieneEnEntrega()
	{
		String ls_interviene;

		ls_interviene = getIntervieneEnEntrega();

		return StringUtils.isValidString(ls_interviene) && ls_interviene.equalsIgnoreCase(EstadoCommon.S);
	}
}
