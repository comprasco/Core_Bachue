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
import com.bachue.snr.prosnr21.model.pgn.ExtractoMensual;
import com.bachue.snr.prosnr21.model.pgn.PeriodoCorte;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanExtractoMensual.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanExtractoMensual")
@SessionScoped
public class BeanExtractoMensual extends BeanArchivoDRXC implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 2744086328466812744L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanExtractoMensual.class);

	/** Propiedad list fechas. */
	private Collection<PeriodoCorte> icpc_listFechas;

	/** Propiedad iem extracto mensual. */
	private ExtractoMensual iem_extractoMensual;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad fecha concfrontacion. */
	private String is_fechaMovimiento;

	/** Propiedad mes consulta. */
	private String is_mesConsulta;

	/** Propiedad id tipo cuenta label. */
	private String is_tipoCuentaLabel;

	/** Propiedad id validar modificar. */
	private String is_validarModificar;

	/** Propiedad ib mostrar firmar. */
	private boolean ib_mostrarModificar;

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
	 * Modifica el valor de ExtractoMensual.
	 *
	 * @param aem_extractoMensual de aem extracto mensual
	 */
	public void setExtractoMensual(ExtractoMensual aem_extractoMensual)
	{
		iem_extractoMensual = aem_extractoMensual;
	}

	/**
	 * Retorna Objeto o variable de valor extracto mensual.
	 *
	 * @return el valor de extracto mensual
	 */
	public ExtractoMensual getExtractoMensual()
	{
		return iem_extractoMensual;
	}

	/**
	 * Modifica el valor de FechaMovimiento.
	 *
	 * @param as_fechaMovimiento de id fecha movimiento
	 */
	public void setFechaMovimiento(String as_fechaMovimiento)
	{
		is_fechaMovimiento = as_fechaMovimiento;
	}

	/**
	 * Retorna Objeto o variable de valor fecha movimiento.
	 *
	 * @return Retorna el valor de la propiedad id_fechaMovimiento
	 */
	public String getFechaMovimiento()
	{
		return is_fechaMovimiento;
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
	 * Modifica el valor de MesConsulta.
	 *
	 * @param as_mesConsulta de as mes consulta
	 */
	public void setMesConsulta(String as_mesConsulta)
	{
		is_mesConsulta = as_mesConsulta;
	}

	/**
	 * Retorna Objeto o variable de valor mes consulta.
	 *
	 * @return Retorna el valor de la propiedad is_mesConsulta
	 */
	public String getMesConsulta()
	{
		return is_mesConsulta;
	}

	/**
	 * Modifica el valor de MostrarModificar.
	 *
	 * @param ab_mostrarModificar de ab mostrar modificar
	 */
	public void setMostrarModificar(boolean ab_mostrarModificar)
	{
		ib_mostrarModificar = ab_mostrarModificar;
	}

	/**
	 * Valida la propiedad mostrar modificar.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en mostrar modificar
	 */
	public boolean isMostrarModificar()
	{
		return ib_mostrarModificar;
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
	 * @param as_tipoCuentaLabel de tipo cuenta label
	 */
	public void setTipoCuentaLabel(String as_tipoCuentaLabel)
	{
		is_tipoCuentaLabel = as_tipoCuentaLabel;
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
	 * Modifica el valor de ValidarModificar.
	 *
	 * @param as_validarModificar de as validar modificar
	 */
	public void setValidarModificar(String as_validarModificar)
	{
		is_validarModificar = as_validarModificar;
	}

	/**
	 * Retorna Objeto o variable de valor validar modificar.
	 *
	 * @return el valor de validar modificar
	 */
	public String getValidarModificar()
	{
		return is_validarModificar;
	}

	/**
	 * Método de actualización de tipo cuenta.
	 */
	public void actualizarTipoCuenta()
	{
		Collection<EntidadRecaudadoraCuenta> lcerc_entidadesRCuentas;
		String                               ls_tipoCuenta;

		lcerc_entidadesRCuentas     = getEntidadRecaudadoraCuentas();
		ls_tipoCuenta               = null;

		if(CollectionUtils.isValidCollection(lcerc_entidadesRCuentas))
		{
			boolean                            lb_validado;
			Iterator<EntidadRecaudadoraCuenta> lierc_iterador;
			String                             ls_tipoCuentaActual;

			lb_validado             = false;
			lierc_iterador          = lcerc_entidadesRCuentas.iterator();
			ls_tipoCuentaActual     = getIdCuenta();

			while(!lb_validado && lierc_iterador.hasNext())
			{
				EntidadRecaudadoraCuenta lierc_erc;

				lierc_erc = lierc_iterador.next();

				if(lierc_erc != null)
				{
					String ls_idCuenta;
					ls_idCuenta = lierc_erc.getIdCuenta();

					if(ls_idCuenta.equals(ls_tipoCuentaActual))
					{
						lb_validado       = true;
						ls_tipoCuenta     = lierc_erc.getTipoCuenta();
					}
				}
			}
		}

		cambiarTipoCuenta(ls_tipoCuenta);
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
		setTipoCuentaLabel(null);

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
			boolean      lb_focus;
			FacesContext lfc_context;
			String       ls_idEntidad;
			String       ls_idCuenta;

			lb_focus         = true;
			lfc_context      = FacesContext.getCurrentInstance();
			ls_idEntidad     = getIdEntidadRecaudadora();
			ls_idCuenta      = getIdCuenta();

			setMostrarModificar(false);

			lb_focus     = validateStyles(
				    ":fExtractoMensual:idEntidadRecaudadoraCuenta", lfc_context, ls_idEntidad, lb_focus
				);
			lb_focus     = validateStyles(":fExtractoMensual:idNumeroCuenta", lfc_context, ls_idCuenta, lb_focus);

			if(!StringUtils.isValidString(getIdCuenta()))
			{
				ls_idEntidad     = getFechaMovimiento();
				lb_focus         = validateStyles(":fExtractoMensual:idFecha", lfc_context, ls_idEntidad, lb_focus);
			}

			PrimeFaces.current().ajax().update("fExtractoMensual");

			if(!lb_focus)
				throw new B2BException(ErrorKeys.ERROR_DEBE_DILIGENCIAR_TODOS_LOS_CAMPOS);

			{
				ExtractoMensual objTemp;
				objTemp = new ExtractoMensual();

				objTemp.setIdEntidadRecaudadora(getIdEntidadRecaudadora());
				objTemp.setIdCuenta(getIdCuenta());
				objTemp.setPeriodo(getFechaMovimiento());

				setExtractoMensual(ipr_parameterRemote.buscarExtractoMensual(objTemp));

				setValidarModificar(iem_extractoMensual.getNumeroExtractoSiif());

				if(StringUtils.isValidString(getValidarModificar()))
					setMostrarModificar(true);

				setMostrarPanel(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultar", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":fExtractoMensual:idGrowl");
		}
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setExtractoMensual(null);
		setFechaMovimiento(null);
		setListFechas(null);
		setMesConsulta(null);
		setMostrarModificar(false);
		setMostrarPanel(false);
		setTipoCuentaLabel(null);
		setValidarModificar(null);
		super.limpiar();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr21.web.bean.conciliaciones.BeanArchivoDRXC#salvar()
	 */
	public String salvar()
	{
		String ls_result;
		ls_result = null;

		try
		{
			if(iem_extractoMensual != null)
			{
				ipr_parameterRemote.salvarExtractoMensual(
				    iem_extractoMensual, getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

				if(isMostrarModificar())
					addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);
				else
					addMessageInfo("I", MessagesKeys.INCERSION_EXITOSA);

				ls_result = com.bachue.snr.prosnr21.common.constants.NavegacionCommon.EXTRACTO_MENSUAL;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvar", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":fArchivoDRXC:idGrowl");
		}

		limpiar();

		return ls_result;
	}
}
