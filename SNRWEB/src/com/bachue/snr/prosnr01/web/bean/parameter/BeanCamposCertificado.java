package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCertificado;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

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
 * Clase que contiene todos las propiedades y acciones de BeanCamposCertificado.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCamposCertificado")
@SessionScoped
public class BeanCamposCertificado extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCamposCertificado.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1519721896019797746L;

	/** Propiedad icc campos certificado. */
	private CamposCertificado icc_camposCertificado;

	/** Propiedad iccc datos auditoria. */
	private Collection<CamposCertificado> iccc_datosAuditoria;

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
	 * @param accc_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<CamposCertificado> accc_datosAuditoria)
	{
		iccc_datosAuditoria = accc_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CamposCertificado> getDatosAuditoria()
	{
		return iccc_datosAuditoria;
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
	 * @param acc_cc asigna el valor a la propiedad
	 */
	public void setCamposCertificado(CamposCertificado acc_cc)
	{
		icc_camposCertificado = acc_cc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CamposCertificado getCamposCertificado()
	{
		return icc_camposCertificado;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_CAMPOS_CERTIFICADO
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCamposCertificado((new CamposCertificado()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCamposCertificado");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_CAMPOS_CERTIFICADO por medio de su indicativo
	 *
	 * @param acc_camposCertificado indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(CamposCertificado acc_camposCertificado)
	    throws B2BException
	{
		if(acc_camposCertificado != null)
		{
			CamposCertificado lcc_camposCertificado;

			lcc_camposCertificado = ipr_parameterRemote.findCamposCertificadoById(
				    acc_camposCertificado, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcc_camposCertificado != null)
			{
				Collection<CamposCertificado> lccc_ccc;

				lccc_ccc = new ArrayList<CamposCertificado>();

				lccc_ccc.add(lcc_camposCertificado);

				setCamposCertificado(lcc_camposCertificado);
				setDatosAuditoria(lccc_ccc);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAMPOS_CERTIFICADO
	 *
	 * @return Collection de CamposCertificado resultante de la consulta
	 */
	public Collection<CamposCertificado> findAllCamposCertificado()
	{
		Collection<CamposCertificado> lccc_camposCertificado;

		lccc_camposCertificado = null;

		try
		{
			lccc_camposCertificado = ipr_parameterRemote.findAllCamposCertificado();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lccc_camposCertificado;
	}

	/**
	 * Método para traer de la base de datos todos los registros de la tabla SDB_PGN_CONSTANTES.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Constantes> findAllConstantes()
	{
		Collection<Constantes> lcc_constantes;

		lcc_constantes = null;

		try
		{
			lcc_constantes = ipr_parameterRemote.findAllConstants();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcc_constantes;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_CAMPOS_CERTIFICADO
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			CamposCertificado lcc_camposCertificado;
			boolean           lb_focus;
			FacesContext      lfc_context;

			lb_focus                  = true;
			lfc_context               = FacesContext.getCurrentInstance();
			lcc_camposCertificado     = getCamposCertificado();

			if(lcc_camposCertificado != null)
			{
				String ls_validador;

				{
					ls_validador     = lcc_camposCertificado.getPlantilla();

					lb_focus = validateStyles(
						    ":fCamposCertificadoDetalle:idPlantilla", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_PLANTILLA);
				}

				{
					ls_validador     = lcc_camposCertificado.getCampo();

					lb_focus = validateStyles(
						    ":fCamposCertificadoDetalle:idCampo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_CAMPO);
				}

				{
					ls_validador     = lcc_camposCertificado.getActivo();

					lb_focus = validateStyles(
						    ":fCamposCertificadoDetalle:idActivo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarCamposCertificado(
				    lcc_camposCertificado, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.CAMPOS_CERTIFICADO;

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
			PrimeFaces.current().ajax().update("fCamposCertificadoDetalle:globalMsg");
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

		ls_return = NavegacionCommon.CAMPOS_CERTIFICADO;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setCamposCertificado(null);
		setInsertar(false);
	}
}
