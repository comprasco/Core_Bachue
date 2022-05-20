package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

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
 * Clase para el manejo de eventos de la pantalla de actuaciones administrativas param.
 *
 * @author Bhrayan Roa
 *
 */
@ManagedBean(name = "beanActuacionesAdministrativasParam")
@SessionScoped
public class BeanActuacionesAdministrativasParam extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1860152287870806526L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanActuacionesAdministrativasParam.class);

	/** Propiedad icaa datos auditoria. */
	private Collection<TagPlantillaResolucion> icaa_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iaa actuaciones administrativas. */
	private TagPlantillaResolucion iaa_actuacionesAdministrativas;

	/** Propiedad iaa parametros. */
	private TagPlantillaResolucion iaa_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * Modifica el valor de ActuacionesAdministrativas.
	 *
	 * @param aaa_aa de aaa aa
	 */
	public void setActuacionesAdministrativas(TagPlantillaResolucion aaa_aa)
	{
		iaa_actuacionesAdministrativas = aaa_aa;
	}

	/**
	 * Retorna Objeto o variable de valor actuaciones administrativas.
	 *
	 * @return el valor de actuaciones administrativas
	 */
	public TagPlantillaResolucion getActuacionesAdministrativas()
	{
		return iaa_actuacionesAdministrativas;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de DatosAuditoria.
	 *
	 * @param acaa_aa de acaa aa
	 */
	public void setDatosAuditoria(Collection<TagPlantillaResolucion> acaa_aa)
	{
		icaa_datosAuditoria = acaa_aa;
	}

	/**
	 * Retorna Objeto o variable de valor datos auditoria.
	 *
	 * @return el valor de datos auditoria
	 */
	public Collection<TagPlantillaResolucion> getDatosAuditoria()
	{
		return icaa_datosAuditoria;
	}

	/**
	 * Modifica el valor de Insertar.
	 *
	 * @param ab_b de ab b
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de Parametros.
	 *
	 * @param aaa_aa de aaa aa
	 */
	public void setParametros(TagPlantillaResolucion aaa_aa)
	{
		iaa_parametros = aaa_aa;
	}

	/**
	 * Retorna Objeto o variable de valor parametros.
	 *
	 * @return el valor de parametros
	 */
	public TagPlantillaResolucion getParametros()
	{
		if(iaa_parametros == null)
			iaa_parametros = new TagPlantillaResolucion();

		return iaa_parametros;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar una nueva
	 * Unidad de Tiempo Vencimiento.
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setActuacionesAdministrativas(new TagPlantillaResolucion());

		Boolean lb_parametro;
		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarActuacionesAdministrativas");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
	}

	/**
	 * Consulta detallada.
	 *
	 * @param as_idActuacionesAdministrativas de as id actuaciones administrativas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultaDetallada(String as_idActuacionesAdministrativas)
	    throws B2BException
	{
		try
		{
			if(as_idActuacionesAdministrativas != null)
			{
				TagPlantillaResolucion laa_datos;

				laa_datos = ipr_parameterRemote.findActuacionesAdministrativasById(
					    as_idActuacionesAdministrativas, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(laa_datos != null)
				{
					Collection<TagPlantillaResolucion> lcaa_coleccionActuaciones;

					lcaa_coleccionActuaciones = new ArrayList<TagPlantillaResolucion>();

					lcaa_coleccionActuaciones.add(laa_datos);

					setActuacionesAdministrativas(laa_datos);

					setDatosAuditoria(lcaa_coleccionActuaciones);
				}

				setInsertar(false);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("consultaDetallada", lb2be_e);
		}
	}

	/**
	 * Find actuaciones administrativas.
	 *
	 * @return el valor de collection
	 */
	public Collection<TagPlantillaResolucion> findActuacionesAdministrativas()
	{
		Collection<TagPlantillaResolucion> lcaa_constantes;
		lcaa_constantes = null;

		try
		{
			lcaa_constantes = ipr_parameterRemote.findAllActuacionesAdministrativas(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("findActuacionesAdministrativas", lb2be_e);
		}

		return lcaa_constantes;
	}

	/**
	 * Método para salvar la inserción o actualización.
	 */
	public String salvar()
	{
		boolean      lb_focus;
		FacesContext lfc_context;
		String       ls_result;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();
		ls_result       = null;

		try
		{
			TagPlantillaResolucion ltpr_parametros;

			ltpr_parametros = getActuacionesAdministrativas();

			{
				String ls_idPlantilla;
				ls_idPlantilla     = ltpr_parametros.getIdPlantilla();

				lb_focus = validateStyles(
					    ":fActuacionesAdministrativasParamDetalle:idPlantilla", lfc_context, ls_idPlantilla, lb_focus
					);

				if(!StringUtils.isValidString(ls_idPlantilla))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_PLANTILLA);
			}

			ipr_parameterRemote.salvarActuacionesAdministrativas(
			    ltpr_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			setParametros(null);

			ls_result = NavegacionCommon.TAG_PLANTILLA_RESOLUCION;

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
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("salvar", lb2be_e);

			PrimeFaces.current().ajax().update("fActuacionesAdministrativasParam:globalMsg");
		}

		return ls_result;
	}
}
