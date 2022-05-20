package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.ExtractoBancoMensual;
import com.bachue.snr.prosnr21.model.pgn.PeriodoCorte;

import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;

import java.text.ParseException;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanExtractoBancoMensual.
 *
 * @author  Kevin Pulido
 */
@ManagedBean(name = "beanExtractoBancoMensual")
@SessionScoped
public class BeanExtractoBancoMensual extends BeanArchivoDRXC implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3862964659218917169L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanExtractoMensual.class);

	/** Propiedad list fechas. */
	private Collection<PeriodoCorte> icpc_listFechas;

	/** Propiedad ipr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote ipr_conciliacionesRemote;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad id tipo cuenta label. */
	private String is_tipoCuentaLabel;

	/**
	 *  {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de ListFechas.
	 *
	 * @param apc_listFechas de list fechas
	 */
	public void setListFechas(Collection<PeriodoCorte> apc_listFechas)
	{
		icpc_listFechas = apc_listFechas;
	}

	/**
	 * Retorna Objeto o variable de valor list fechas.
	 *
	 * @return el valor de list fechas
	 * @throws B2BException
	 */
	public Collection<PeriodoCorte> getListFechas()
	    throws B2BException
	{
		if(icpc_listFechas == null)
			icpc_listFechas = ipr_parameterRemote.findPeriodosFecha(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

		return icpc_listFechas;
	}

	/**
	 * Modifica el valor de TipoCuentaLabel.
	 *
	 * @param tipoCuentaLabel de tipo cuenta label
	 */
	public void setTipoCuentaLabel(String tipoCuentaLabel)
	{
		is_tipoCuentaLabel = tipoCuentaLabel;
	}

	/**
	 * Retorna Objeto o variable de valor tipo cuenta label.
	 *
	 * @return el valor de tipo cuenta label
	 */
	public String getTipoCuentaLabel()
	{
		return is_tipoCuentaLabel;
	}

	/**
	 * Método de actualización de tipo cuenta.
	 */
	public void actualizarTipoCuenta()
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_entidadesRCuentas;
		lcerc_entidadesRCuentas = getEntidadRecaudadoraCuentas();

		if(CollectionUtils.isValidCollection(lcerc_entidadesRCuentas))
		{
			for(EntidadRecaudadoraCuenta lierc_erc : lcerc_entidadesRCuentas)
			{
				if(lierc_erc != null)
				{
					String ls_idCuenta;
					ls_idCuenta = lierc_erc.getIdCuenta();

					if(ls_idCuenta.equals(getIdCuenta()))
						cambiarTipoCuenta(lierc_erc.getTipoCuenta());
				}
			}
		}
	}

	/**
	 * Buscar entidad recaudadora conciliacion local.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacionLocal()
	{
		return buscarEntidadRecaudadoraConciliacion();
	}

	/**
	 * Buscar entidad recaudadora local.
	 *
	 * @return el valor de collection
	 */
	public void buscarEntidadRecaudadoraLocal()
	{
		buscarEntidadRecaudadoraCuentaDeUsuario();
		setTipoCuentaLabel(null);
		setIdCuenta(null);
	}

	/**
	 * Cambiar tipo cuenta.
	 *
	 * @param as_tipoCuenta the as tipo cuenta
	 */
	public void cambiarTipoCuenta(String as_tipoCuenta)
	{
		String ls_label;

		ls_label = null;

		if(StringUtils.isValidString(as_tipoCuenta))
		{
			if(as_tipoCuenta.equalsIgnoreCase("C"))
				ls_label = "Corriente";
			else if(as_tipoCuenta.equalsIgnoreCase("A"))
				ls_label = "Ahorros";
		}

		setTipoCuentaLabel(ls_label);
	}

	/**
	 * Consultar alertas.
	 * @throws IOException
	 * @throws ParseException
	 */
	public void cargarExtractoBancario()
	    throws IOException, ParseException
	{
		try
		{
			FacesContext         lfc_context;
			ExtractoBancoMensual lebm_extractoBancoMensual;
			String               ls_periodo;
			boolean              lb_focus;
			int                  li_resultado;

			lfc_context                   = FacesContext.getCurrentInstance();
			ls_periodo                    = getPeriodo();
			lebm_extractoBancoMensual     = new ExtractoBancoMensual();
			lb_focus                      = true;

			{
				String ls_paramBusqueda;

				ls_paramBusqueda     = getIdEntidadRecaudadora();
				lb_focus             = validateStyles(
					    ":fExtractoBancoMensual:idEntidadRecaudadoraCuenta", lfc_context, ls_paramBusqueda, lb_focus
					);
				ls_paramBusqueda     = getIdCuenta();
				lb_focus             = validateStyles(
					    ":fExtractoBancoMensual:idNumeroCuenta", lfc_context, ls_paramBusqueda, lb_focus
					);

				if(StringUtils.isValidString(getIdCuenta()))
				{
					ls_paramBusqueda     = ls_periodo;
					lb_focus             = validateStyles(
						    ":fExtractoBancoMensual:idPeriodo", lfc_context, ls_paramBusqueda, lb_focus
						);
				}

				PrimeFaces.current().ajax().update("fExtractoBancoMensual");

				if(!lb_focus)
					throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);

				lebm_extractoBancoMensual.setIdCuenta(getIdCuenta());
				lebm_extractoBancoMensual.setIdEntidadRecaudadora(getIdEntidadRecaudadora());
				lebm_extractoBancoMensual.setPeriodo(ls_periodo);

				li_resultado = ipr_conciliacionesRemote.uploadFileExtractoBancoMensual(
					    lebm_extractoBancoMensual, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(li_resultado == 0)
				{
					addMessage(MessagesKeys.EXTRACTO_CARGADO_EXITOSAMENTE);
					limpiar();
				}
				else
					addMessage(MessagesKeys.EXTRACTO_NO_PUDO_SER_CARGADO);

				actualizarComponente(":fExtractoBancoMensual");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarExtractoBancario", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":fExtractoBancoMensual:idGrowl");
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setListFechas(null);
		setTipoCuentaLabel(null);
		super.limpiar();
	}

	public void test()
	{
		System.out.println("Entró");
	}
}
