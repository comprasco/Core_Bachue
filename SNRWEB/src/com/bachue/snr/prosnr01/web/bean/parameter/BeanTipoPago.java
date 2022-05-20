package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TipoPago;

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
 * Clase que contiene todos las propiedades y funcionalidad de BeanTipoPago.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTipoPago")
@SessionScoped
public class BeanTipoPago extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTipoPago.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4903603478073131491L;

	/** Propiedad ictp all pagos. */
	private Collection<TipoPago> ictp_allPagos;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad iotp tipoPago. */
	private TipoPago iotp_tipoPago;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/**
	 * Modifica el valor de all pagos.
	 *
	 * @param aoctp_tp asigna el valor a la propiedad all pagos
	 */
	public void setAllPagos(Collection<TipoPago> aoctp_tp)
	{
		ictp_allPagos = aoctp_tp;
	}

	/**
	 * Retorna el valor de all pagos.
	 *
	 * @return el valor de all pagos
	 */
	public Collection<TipoPago> getAllPagos()
	{
		return ictp_allPagos;
	}

	/**
	 * Modifica el valor de tipo pago.
	 *
	 * @param aotp_tp asigna el valor a la propiedad tipo pago
	 */
	public void setTipoPago(TipoPago aotp_tp)
	{
		iotp_tipoPago = aotp_tp;
	}

	/**
	 * Retorna el valor de tipo pago.
	 *
	 * @return el valor de tipo pago
	 */
	public TipoPago getTipoPago()
	{
		return iotp_tipoPago;
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

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Método para cambiar estado para saber si se desea insertar o modificar un registro
	 * de la tabla SDB_ACC_TIPO_PAGO
	 */
	public void cambiarEstado()
	{
		setInsertar(true);
		setTipoPago((new TipoPago()));

		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertarTipoPago");

		if(lb_parametro != null)
			setInsertar(BooleanUtils.getBooleanValue(lb_parametro));
	}

	/**
	 * Método de consultar un registro detalladamente de SDB_ACC_TIPO_PAGO por medio de su indicativo
	 *
	 * @param atp_tipoPago indicativo de los datos solicitados
	 * @throws B2BException
	 */
	public void consultaDetallada(TipoPago atp_tipoPago)
	    throws B2BException
	{
		if(atp_tipoPago != null)
		{
			TipoPago ltp_tipoPago;

			ltp_tipoPago = ipr_parameterRemote.findTiposPagos(
				    atp_tipoPago, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ltp_tipoPago != null)
			{
				Collection<TipoPago> lcae_cae;

				lcae_cae = new ArrayList<TipoPago>();

				lcae_cae.add(ltp_tipoPago);

				setTipoPago(ltp_tipoPago);
				setAllPagos(lcae_cae);
			}

			setInsertar(false);
		}
	}

	/**
	 *  Método para encontrar todos los registros de la tabla SDB_ACC_TIPO_PAGO
	 *
	 * @return Collection de TipoPago resultante de la consulta
	 */
	public Collection<TipoPago> findAllTipoPago()
	{
		Collection<TipoPago> lctp_tipoPago;

		lctp_tipoPago = null;

		try
		{
			TipoPago ltp_tipoPago;

			ltp_tipoPago = ipr_parameterRemote.findTiposPagos(
				    null, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ltp_tipoPago != null)
			{
				Collection<TipoPago> lctp_allTipoPago;

				lctp_allTipoPago = ltp_tipoPago.getInfoAll();

				if(CollectionUtils.isValidCollection(lctp_allTipoPago))
					lctp_tipoPago = lctp_allTipoPago;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return lctp_tipoPago;
	}

	/**
	 * Método para salvar la inserción o actualización de un registro en la tabla SDB_ACC_TIPO_PAGO
	 *
	 * @return String contenedor de la pagina a regresar
	 */
	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			TipoPago     ltp_tipoPago;
			boolean      lb_focus;
			FacesContext lfc_context;

			lb_focus         = true;
			lfc_context      = FacesContext.getCurrentInstance();
			ltp_tipoPago     = getTipoPago();

			if(ltp_tipoPago != null)
			{
				String ls_validador;

				{
					ls_validador     = ltp_tipoPago.getDescripcion();

					lb_focus = validateStyles(":fTipoPagoDetalle:descripcion", lfc_context, ls_validador, lb_focus);

					if(!StringUtils.isValidString(ls_validador))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_DESCRIPCION);
				}
				
				ltp_tipoPago.setAccion(isInsertar());

				ipr_parameterRemote.gestionTipoPago(
				    ltp_tipoPago, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				clear();

				ls_result = NavegacionCommon.TIPO_PAGO;

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
			PrimeFaces.current().ajax().update("fTipoPagoDetalle:globalMsg");
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

		ls_return = NavegacionCommon.TIPO_PAGO;

		clear();

		return ls_return;
	}

	/**
	 * Método para limpiar la pantalla
	 */
	public void clear()
	{
		setAllPagos(null);
		setTipoPago(null);
		setInsertar(false);
	}
}
