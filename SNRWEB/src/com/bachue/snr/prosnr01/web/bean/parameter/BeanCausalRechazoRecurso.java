package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CausalRechazoRecurso;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanCausalRechazoRecurso.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCausalRechazoRecurso")
@SessionScoped
public class BeanCausalRechazoRecurso extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCausalRechazoRecurso.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2337907255583200062L;

	/** Propiedad icrr causal rechazo recurso. */
	private CausalRechazoRecurso icrr_causalRechazoRecurso;

	/** Propiedad icrr datos auditoria. */
	private Collection<CausalRechazoRecurso> icrr_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acrr_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CausalRechazoRecurso> acrr_datosAuditoria)
	{
		icrr_datosAuditoria = acrr_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CausalRechazoRecurso> getDatosAuditoria()
	{
		return icrr_datosAuditoria;
	}

	/**
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * @param acrr_crr asigna el valor a la propiedad
	 */
	public void setCausalRechazoRecurso(CausalRechazoRecurso acrr_crr)
	{
		icrr_causalRechazoRecurso = acrr_crr;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CausalRechazoRecurso getCausalRechazoRecurso()
	{
		return icrr_causalRechazoRecurso;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCausalRechazoRecurso((new CausalRechazoRecurso()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCausalRechazoRecurso");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_CAUSAL_RECHAZO_RECURSO por medio de su indicativo
	 *
	 * @param acrr_causalRechazoRecurso indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(CausalRechazoRecurso acrr_causalRechazoRecurso)
	    throws B2BException
	{
		if(acrr_causalRechazoRecurso != null)
		{
			CausalRechazoRecurso lcrr_causalRechazoRecurso;

			lcrr_causalRechazoRecurso = ipr_parameterRemote.findCausalRechazoRecursoById(
				    acrr_causalRechazoRecurso, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcrr_causalRechazoRecurso != null)
			{
				Collection<CausalRechazoRecurso> lcrr_crr;

				lcrr_crr = new ArrayList<CausalRechazoRecurso>();

				lcrr_crr.add(lcrr_causalRechazoRecurso);

				setCausalRechazoRecurso(lcrr_causalRechazoRecurso);
				setDatosAuditoria(lcrr_crr);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO
	 *
	 * @return Collection de CausalRechazoRecurso resultante de la consulta
	 */
	public Collection<CausalRechazoRecurso> findAllCausalRechazoRecurso()
	{
		Collection<CausalRechazoRecurso> lcrr_causalRechazoRecurso;

		lcrr_causalRechazoRecurso = null;

		try
		{
			lcrr_causalRechazoRecurso = ipr_parameterRemote.findAllCausalRechazoRecurso(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcrr_causalRechazoRecurso;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_CAUSAL_RECHAZO_RECURSO
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			CausalRechazoRecurso lcrr_causalRechazoRecurso;
			boolean              lb_focus;
			FacesContext         lfc_context;

			lb_focus                      = true;
			lfc_context                   = FacesContext.getCurrentInstance();
			lcrr_causalRechazoRecurso     = getCausalRechazoRecurso();

			if(lcrr_causalRechazoRecurso != null)
			{
				String ls_validador;

				{
					ls_validador     = lcrr_causalRechazoRecurso.getIdCausalRechazoRecurso();

					lb_focus = validateStyles(
						    ":fCausalRechazoRecursoDetalle:idCausalRechazoRecurso", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_ID_CAUSAL_RECHAZO_RECURSO);
				}

				{
					ls_validador     = lcrr_causalRechazoRecurso.getNombre();

					lb_focus = validateStyles(
						    ":fCausalRechazoRecursoDetalle:nombre", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					ls_validador     = lcrr_causalRechazoRecurso.getActivo();

					lb_focus = validateStyles(
						    ":fCausalRechazoRecursoDetalle:idActivo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCausalRechazoRecurso(
				    lcrr_causalRechazoRecurso, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.CAUSAL_RECHAZO_RECURSO;

				addMessage(MessagesKeys.PROCESO_COMPLETADO);

				{
					ExternalContext lec_externalContext;

					lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

					if(lec_externalContext != null)
					{
						Flash lf_flash;

						lf_flash = lec_externalContext.getFlash();

						if(lf_flash != null)
							lf_flash.setKeepMessages(true);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fCausalRechazoRecursoDetalle:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.CAUSAL_RECHAZO_RECURSO;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setCausalRechazoRecurso(null);
		setInsertar(false);
	}
}
