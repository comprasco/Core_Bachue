package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;

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
 * Clase que contiene todos las propiedades y acciones de BeanCamposCorreccion.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanCamposCorreccion")
@SessionScoped
public class BeanCamposCorreccion extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanCamposCorreccion.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1519721896019797746L;

	/** Propiedad icc campos correccion. */
	private CamposCorreccion icc_camposCorreccion;

	/** Propiedad iccc datos auditoria. */
	private Collection<CamposCorreccion> iccc_datosAuditoria;

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
	public void setDatosAuditoria(Collection<CamposCorreccion> accc_datosAuditoria)
	{
		iccc_datosAuditoria = accc_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<CamposCorreccion> getDatosAuditoria()
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
	public void setCamposCorreccion(CamposCorreccion acc_cc)
	{
		icc_camposCorreccion = acc_cc;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public CamposCorreccion getCamposCorreccion()
	{
		return icc_camposCorreccion;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_CAMPOS_CORRECCION
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setCamposCorreccion((new CamposCorreccion()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarCamposCorreccion");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_CAMPOS_CORRECCION por medio de su indicativo
	 *
	 * @param acc_camposCertificado indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(CamposCorreccion acc_camposCertificado)
	    throws B2BException
	{
		if(acc_camposCertificado != null)
		{
			CamposCorreccion lcc_camposCorreccion;

			lcc_camposCorreccion = ipr_parameterRemote.findCamposCorreccionById(
				    acc_camposCertificado, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lcc_camposCorreccion != null)
			{
				Collection<CamposCorreccion> lccc_ccc;

				lccc_ccc = new ArrayList<CamposCorreccion>();

				lccc_ccc.add(lcc_camposCorreccion);

				setCamposCorreccion(lcc_camposCorreccion);
				setDatosAuditoria(lccc_ccc);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAMPOS_CORRECCION
	 *
	 * @return Collection de CamposCorreccion resultante de la consulta
	 */
	public Collection<CamposCorreccion> findAllCamposCorreccion()
	{
		Collection<CamposCorreccion> lccc_camposCorreccion;

		lccc_camposCorreccion = null;

		try
		{
			lccc_camposCorreccion = ipr_parameterRemote.findAllCamposCorreccion();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lccc_camposCorreccion;
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_CAUSAL_CORRECCION.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<CausalCorreccion> findAllCausalCorreccion()
	{
		Collection<CausalCorreccion> lccc_constantes;

		lccc_constantes = null;

		try
		{
			lccc_constantes = ipr_parameterRemote.findAllCausalCorreccion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccc_constantes;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_PGN_CAMPOS_CORRECCION
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			CamposCorreccion lcc_camposCorreccion;
			boolean          lb_focus;
			FacesContext     lfc_context;

			lb_focus                 = true;
			lfc_context              = FacesContext.getCurrentInstance();
			lcc_camposCorreccion     = getCamposCorreccion();

			if(lcc_camposCorreccion != null)
			{
				Long ll_validador;

				{
					ll_validador     = lcc_camposCorreccion.getIdCampoCorreccion();

					lb_focus = validateStyles(
						    ":fCamposCorreccionDetalle:idCampoCorreccion", lfc_context, ll_validador, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ID_CAMPO_CORRECCION);
				}

				{
					ll_validador     = lcc_camposCorreccion.getIdCausalCorreccion();

					lb_focus = validateStyles(
						    ":fCamposCorreccionDetalle:idCausalCorreccion", lfc_context, ll_validador, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_validador))
						throw new B2BException(ErrorKeys.ERROR_CAUSAL_CORRECCION);
				}

				{
					String ls_validador;

					ls_validador     = lcc_camposCorreccion.getDescripcionCampo();

					lb_focus = validateStyles(
						    ":fCamposCorreccionDetalle:descripcionCampo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.ERROR_DESCRIPCION);
				}

				ipr_parameterRemote.salvarCamposCorreccion(
				    lcc_camposCorreccion, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.CAMPOS_CORRECCION;

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
			PrimeFaces.current().ajax().update("fCamposCorreccionDetalle:globalMsg");
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

		ls_return = NavegacionCommon.CAMPOS_CORRECCION;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setCamposCorreccion(null);
		setInsertar(false);
	}
}
