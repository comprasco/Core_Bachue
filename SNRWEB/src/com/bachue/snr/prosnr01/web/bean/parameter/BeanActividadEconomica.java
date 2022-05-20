package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.ActividadEconomica;

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
 * Clase que contiene todos las propiedades y acciones de BeanActividadEconomica.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanActividadEconomica")
@SessionScoped
public class BeanActividadEconomica extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanActividadEconomica.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5392214602653515517L;

	/** Propiedad iae actividad economica. */
	private ActividadEconomica iae_actividadEconomica;

	/** Propiedad icae datos auditoria. */
	private Collection<ActividadEconomica> icae_datosAuditoria;

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
	 * @param acae_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<ActividadEconomica> acae_datosAuditoria)
	{
		icae_datosAuditoria = acae_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<ActividadEconomica> getDatosAuditoria()
	{
		return icae_datosAuditoria;
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
	 * @param aae_ae asigna el valor a la propiedad
	 */
	public void setActividadEconomica(ActividadEconomica aae_ae)
	{
		iae_actividadEconomica = aae_ae;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public ActividadEconomica getActividadEconomica()
	{
		return iae_actividadEconomica;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_ACTIVIDAD_ECONOMICA
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setActividadEconomica((new ActividadEconomica()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarActividadEconomica");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_ACTIVIDAD_ECONOMICA por medio de su indicativo
	 *
	 * @param aae_actividadEconomica indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(ActividadEconomica aae_actividadEconomica)
	    throws B2BException
	{
		if(aae_actividadEconomica != null)
		{
			ActividadEconomica lae_actividadEconomica;

			lae_actividadEconomica = ipr_parameterRemote.findActividadEconomicaById(
				    aae_actividadEconomica, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lae_actividadEconomica != null)
			{
				Collection<ActividadEconomica> lcae_cae;

				lcae_cae = new ArrayList<ActividadEconomica>();

				lcae_cae.add(lae_actividadEconomica);

				setActividadEconomica(lae_actividadEconomica);
				setDatosAuditoria(lcae_cae);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_ACTIVIDAD_ECONOMICA
	 *
	 * @return Collection de ActividadEconomica resultante de la consulta
	 */
	public Collection<ActividadEconomica> findAllActividadEconomica()
	{
		Collection<ActividadEconomica> lcae_actividadEconomica;

		lcae_actividadEconomica = null;

		try
		{
			lcae_actividadEconomica = ipr_parameterRemote.findAllActividadEconomica();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcae_actividadEconomica;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_ACTIVIDAD_ECONOMICA
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			ActividadEconomica lae_actividadEconomica;
			boolean            lb_focus;
			FacesContext       lfc_context;

			lb_focus                   = true;
			lfc_context                = FacesContext.getCurrentInstance();
			lae_actividadEconomica     = getActividadEconomica();

			if(lae_actividadEconomica != null)
			{
				String ls_validador;

				{
					ls_validador     = lae_actividadEconomica.getIdActividadEconomica();

					lb_focus = validateStyles(
						    ":fActividadEconomicaDetalle:idActividadEconomica", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVIDAD_ECONOMICA);
				}

				{
					ls_validador     = lae_actividadEconomica.getIdDivision();

					lb_focus = validateStyles(
						    ":fActividadEconomicaDetalle:idDivision", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DIVISION);
				}

				{
					ls_validador     = lae_actividadEconomica.getIdGrupo();

					lb_focus = validateStyles(
						    ":fActividadEconomicaDetalle:idGrupo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_GRUPO);
				}

				{
					ls_validador     = lae_actividadEconomica.getDescripcion();

					lb_focus = validateStyles(
						    ":fActividadEconomicaDetalle:idDescripcion", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					ls_validador     = lae_actividadEconomica.getActivo();

					lb_focus = validateStyles(
						    ":fActividadEconomicaDetalle:idActivo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}

				ipr_parameterRemote.salvarActividadEconomica(
				    lae_actividadEconomica, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.ACTIVIDAD_ECONOMICA;

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
			PrimeFaces.current().ajax().update("fActividadEconomicaDetalle:globalMsg");
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

		ls_return = NavegacionCommon.ACTIVIDAD_ECONOMICA;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setActividadEconomica(null);
		setInsertar(false);
	}
}
