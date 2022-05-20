package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.TipoCriterioBusquedaPGN;

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
 * Clase que contiene todos las propiedades y acciones de BeanTipoCriterioBusquedaPGN.
 *
 * @author Sebastian Sanchez
 */
@ManagedBean(name = "beanTipoCriterioBusquedaPGN")
@SessionScoped
public class BeanTipoCriterioBusquedaPGN extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTipoCriterioBusquedaPGN.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -6585135445356347494L;

	/** Propiedad ictcb datos auditoria. */
	private Collection<TipoCriterioBusquedaPGN> ictcb_datosAuditoria;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad itcb tipo criterio busqueda pgn. */
	private TipoCriterioBusquedaPGN itcb_tipoCriterioBusquedaPGN;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param actcb_datosAuditoria asigna el valor a la propiedad
	 */
	public void setDatosAuditoria(Collection<TipoCriterioBusquedaPGN> actcb_datosAuditoria)
	{
		ictcb_datosAuditoria = actcb_datosAuditoria;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public Collection<TipoCriterioBusquedaPGN> getDatosAuditoria()
	{
		return ictcb_datosAuditoria;
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
	 * @param atcb_tcb asigna el valor a la propiedad
	 */
	public void setTipoCriterioBusquedaPGN(TipoCriterioBusquedaPGN atcb_tcb)
	{
		itcb_tipoCriterioBusquedaPGN = atcb_tcb;
	}

	/**
	 * @return devuelve el valor de la propiedad
	 */
	public TipoCriterioBusquedaPGN getTipoCriterioBusquedaPGN()
	{
		return itcb_tipoCriterioBusquedaPGN;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoCriterioBusquedaPGN((new TipoCriterioBusquedaPGN()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoCriterioBusquedaPGN");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setDatosAuditoria(null);
		setTipoCriterioBusquedaPGN(null);
		setInsertar(false);
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_PGN_TIPO_CRITERIO_BUSQUEDA por medio de su indicativo
	 *
	 * @param aae_actividadEconomica indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(TipoCriterioBusquedaPGN atcb_tipoCriterioBusqueda)
	    throws B2BException
	{
		if(atcb_tipoCriterioBusqueda != null)
		{
			TipoCriterioBusquedaPGN ltcb_tipoCriterioBusqueda;

			ltcb_tipoCriterioBusqueda = ipr_parameterRemote.findTipoCriterioBusquedaPGNById(
				    atcb_tipoCriterioBusqueda, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ltcb_tipoCriterioBusqueda != null)
			{
				Collection<TipoCriterioBusquedaPGN> lctcb_ctcb;

				lctcb_ctcb = new ArrayList<TipoCriterioBusquedaPGN>();

				lctcb_ctcb.add(ltcb_tipoCriterioBusqueda);

				setTipoCriterioBusquedaPGN(ltcb_tipoCriterioBusqueda);
				setDatosAuditoria(lctcb_ctcb);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_PGN_TIPO_CRITERIO_BUSQUEDA
	 *
	 * @return Collection de TipoCriterioBusquedaPGN resultante de la consulta
	 */
	public Collection<TipoCriterioBusquedaPGN> findAllTipoCriterioBusquedaPGN()
	{
		Collection<TipoCriterioBusquedaPGN> lctcb_tipoCriterioBusqueda;

		lctcb_tipoCriterioBusqueda = null;

		try
		{
			lctcb_tipoCriterioBusqueda = ipr_parameterRemote.findAllTipoCriterioBusquedaPGN();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lctcb_tipoCriterioBusqueda;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String returnPages()
	{
		String ls_return;

		ls_return = NavegacionCommon.TIPO_CRITERIO_BUSQUEDA;

		clear();

		return ls_return;
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
			TipoCriterioBusquedaPGN ltcb_tipoCriterioBusqueda;
			boolean                 lb_focus;
			FacesContext            lfc_context;

			lb_focus                      = true;
			lfc_context                   = FacesContext.getCurrentInstance();
			ltcb_tipoCriterioBusqueda     = getTipoCriterioBusquedaPGN();

			if(ltcb_tipoCriterioBusqueda != null)
			{
				String ls_validador;

				{
					ls_validador     = ltcb_tipoCriterioBusqueda.getDescripcion();

					lb_focus = validateStyles(
						    ":fTipoCriterioBusquedaDetalle:idDescripcion", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}

				{
					ls_validador     = ltcb_tipoCriterioBusqueda.getActivo();

					lb_focus = validateStyles(
						    ":fTipoCriterioBusquedaDetalle:idActivo", lfc_context, ls_validador, lb_focus
						);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.salvarTipoCriterioBusquedaPGN(
				    ltcb_tipoCriterioBusqueda, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				getBeanReference().setTipoCriterioBusqueda(null);

				clear();

				ls_result = NavegacionCommon.TIPO_CRITERIO_BUSQUEDA;

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
			PrimeFaces.current().ajax().update("fTipoCriterioBusquedaDetalle:globalMsg");
		}

		return ls_result;
	}
}
