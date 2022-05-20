package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.ExtractoDiario;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanExtractoDiario.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanExtractoDiario")
@SessionScoped
public class BeanExtractoDiario extends BeanArchivoDRXC implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 2744086328466812744L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanExtractoDiario.class);

	/** Propiedad fecha concfrontacion. */
	private Date id_fechaMovimiento;

	/** Propiedad ied extracto diario. */
	private ExtractoDiario ied_extractoDiario;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad id tipo cuenta label. */
	private String is_tipoCuentaLabel;

	/** Propiedad ib mostrar panel. */
	private boolean ib_mostrarPanel;

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
	 * Modifica el valor de ExtractoDiario.
	 *
	 * @param aed_extractoDiario de aed extracto diario
	 */
	public void setExtractoDiario(ExtractoDiario aed_extractoDiario)
	{
		ied_extractoDiario = aed_extractoDiario;
	}

	/**
	 * Retorna Objeto o variable de valor extracto diario.
	 *
	 * @return el valor de extracto diario
	 */
	public ExtractoDiario getExtractoDiario()
	{
		return ied_extractoDiario;
	}

	/**
	 * Modifica el valor de FechaMovimiento.
	 *
	 * @param ad_fechaMovimiento de ad fecha movimiento
	 */
	public void setFechaMovimiento(Date ad_fechaMovimiento)
	{
		id_fechaMovimiento = ad_fechaMovimiento;
	}

	/**
	 * Retorna Objeto o variable de valor fecha movimiento.
	 *
	 * @return Retorna el valor de la propiedad id_fechaMovimiento
	 */
	public Date getFechaMovimiento()
	{
		return id_fechaMovimiento;
	}

	/**
	 * Modifica el valor de MostrarPanel.
	 *
	 * @param ab_mostrarPanel de ab mostrar panel
	 */
	public void setMostrarPanel(boolean ab_mostrarPanel)
	{
		ib_mostrarPanel = ab_mostrarPanel;
	}

	/**
	 * Valida la propiedad mostrar panel.
	 *
	 * @return Retorna el valor de la propiedad ib_mostrarPanel
	 */
	public boolean isMostrarPanel()
	{
		return ib_mostrarPanel;
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
	public Collection<EntidadRecaudadoraCuenta> buscarEntidadRecaudadoraLocal()
	{
		return buscarEntidadRecaudadoraCuentaDeUsuario();
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
	 */
	public void consultar()
	{
		try
		{
			FacesContext lfc_context;
			boolean      lb_focus;
			boolean      lb_focus1;
			int          li_validar;
			Date         ld_fecha;
			String       ls_idEntidad;
			String       ls_idCuenta;

			lfc_context      = FacesContext.getCurrentInstance();
			lb_focus         = true;
			lb_focus1        = true;
			li_validar       = 0;
			ld_fecha         = getFechaMovimiento();
			ls_idEntidad     = getIdEntidadRecaudadora();
			ls_idCuenta      = getIdCuenta();

			{
				lb_focus     = validateStyles(
					    ":fExtractoDiario:idEntidadRecaudadoraCuenta", lfc_context, ls_idEntidad, lb_focus
					);
				lb_focus     = validateStyles(":fExtractoDiario:idNumeroCuenta", lfc_context, ls_idCuenta, lb_focus);

				if(ld_fecha == null)
					lb_focus = validateStyles(":fExtractoDiario:idFechaMovimiento", lfc_context, ld_fecha, lb_focus);
				else if(ld_fecha.after(new Date()))
				{
					lb_focus1      = validateStyles(
						    ":fExtractoDiario:idFechaMovimiento", lfc_context, ld_fecha, lb_focus1
						);
					lb_focus1      = false;
					li_validar     = 1;
				}
				else
					lb_focus1 = validateStyles(":fExtractoDiario:idFechaMovimiento", lfc_context, ld_fecha, lb_focus1);
			}

			PrimeFaces.current().ajax().update("fExtractoDiario");

			if(li_validar == 0)
			{
				if(!lb_focus || !lb_focus1)
					throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);
			}
			else
			{
				addMessageInfo("W", MessagesKeys.ERROR_FECHA_MAYOR_A_ACTUAL);
				throw new B2BException("");
			}

			{
				ExtractoDiario led_objTemp;
				led_objTemp = new ExtractoDiario();

				led_objTemp.setIdCuenta(ls_idCuenta);
				led_objTemp.setIdEntidadRecaudadora(ls_idEntidad);
				led_objTemp.setFechaMovimiento(ld_fecha);

				setExtractoDiario(
				    ipr_parameterRemote.buscarExtractoDiarioByCuentaBancoFecha(
				        led_objTemp, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				    )
				);

				setMostrarPanel(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultar", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":fExtractoDiario:idGrowl");
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setFechaMovimiento(null);
		setMostrarPanel(false);
		setTipoCuentaLabel(null);
		super.limpiar();
	}
}
