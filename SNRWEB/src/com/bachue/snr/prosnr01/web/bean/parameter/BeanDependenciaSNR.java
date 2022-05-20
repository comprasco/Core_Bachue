package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;

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
 * Clase que contiene todos las propiedades y acciones de BeanDependenciaSNR.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanDependenciaSNR")
@SessionScoped
public class BeanDependenciaSNR extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDependenciaSNR.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1272293887096743839L;

	/** Propiedad icdsnr datos auditoria. */
	private Collection<DependenciaSNR> icdsnr_datosAuditoria;

	/** Propiedad idsnr dependencia snr. */
	private DependenciaSNR idsnr_dependenciaSNR;

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
	 * @param acdsnr_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<DependenciaSNR> acdsnr_datosAuditoria)
	{
		icdsnr_datosAuditoria = acdsnr_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<DependenciaSNR> getDatosAuditoria()
	{
		return icdsnr_datosAuditoria;
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
	public void setDependenciaSNR(DependenciaSNR aae_ae)
	{
		idsnr_dependenciaSNR = aae_ae;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public DependenciaSNR getDependenciaSNR()
	{
		return idsnr_dependenciaSNR;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_DEPENDENCIA_SNR
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setDependenciaSNR((new DependenciaSNR()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarDependenciaSNR");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_DEPENDENCIA_SNR por medio de su indicativo
	 *
	 * @param adsnr_dependenciaSNR indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(DependenciaSNR adsnr_dependenciaSNR)
	    throws B2BException
	{
		if(adsnr_dependenciaSNR != null)
		{
			DependenciaSNR ldsnr_dependenciaSNR;

			ldsnr_dependenciaSNR = ipr_parameterRemote.findDependenciaSNRById(
				    adsnr_dependenciaSNR, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ldsnr_dependenciaSNR != null)
			{
				Collection<DependenciaSNR> lcdsnr_cdsnr;

				lcdsnr_cdsnr = new ArrayList<DependenciaSNR>();

				lcdsnr_cdsnr.add(ldsnr_dependenciaSNR);

				setDependenciaSNR(ldsnr_dependenciaSNR);
				setDatosAuditoria(lcdsnr_cdsnr);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_DEPENDENCIA_SNR
	 *
	 * @return Collection de DependenciaSNR resultante de la consulta
	 */
	public Collection<DependenciaSNR> findAllDependenciaSNR()
	{
		Collection<DependenciaSNR> lcdsnr_dependenciaSNR;

		lcdsnr_dependenciaSNR = null;

		try
		{
			lcdsnr_dependenciaSNR = ipr_parameterRemote.findAllDependenciaSNR();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lcdsnr_dependenciaSNR;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_DEPENDENCIA_SNR
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			DependenciaSNR ldsnr_dependenciaSNR;
			boolean        lb_focus;
			FacesContext   lfc_context;

			lb_focus                 = true;
			lfc_context              = FacesContext.getCurrentInstance();
			ldsnr_dependenciaSNR     = getDependenciaSNR();

			if(ldsnr_dependenciaSNR != null)
			{
				String ls_validador;

				{
					ls_validador     = ldsnr_dependenciaSNR.getIdDependencia();

					lb_focus = validateStyles(
						    ":fDependenciaSNRDetalle:idDependencia", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DEPENDENCIA);
				}

				{
					ls_validador     = ldsnr_dependenciaSNR.getNombreDependencia();

					lb_focus = validateStyles(
						    ":fDependenciaSNRDetalle:nombreDependencia", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE);
				}

				{
					ls_validador     = ldsnr_dependenciaSNR.getActivo();

					lb_focus = validateStyles(":fDependenciaSNRDetalle:idActivo", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ACTIVO);
				}
				
				{
					ls_validador     = ldsnr_dependenciaSNR.getIndVisitas();

					lb_focus = validateStyles(":fDependenciaSNRDetalle:indVisitas", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_IND_VISITAS);
				}

				ipr_parameterRemote.salvarDependenciaSNR(
				    ldsnr_dependenciaSNR, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.DEPENDENCIA_SNR;

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
			PrimeFaces.current().ajax().update("fDependenciaSNRDetalle:globalMsg");
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

		ls_return = NavegacionCommon.DEPENDENCIA_SNR;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setDependenciaSNR(null);
		setInsertar(false);
	}
}
