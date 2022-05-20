package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Proceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Subproceso;
import com.bachue.snr.prosnr01.model.sdb.pgn.TarifaFija;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanTarifaFija.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanTarifaFija")
@SessionScoped
public class BeanTarifaFija extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3675738176941867214L;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad ir parametros. */
	private TarifaFija ir_parametros;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de insertar.
	 *
	 * @param ab_b asigna el valor a la propiedad insertar
	 */
	public void setInsertar(boolean ab_b)
	{
		ib_insertar = ab_b;
	}

	/**
	 * Valida la propiedad insertar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insertar
	 */
	public boolean isInsertar()
	{
		return ib_insertar;
	}

	/**
	 * Modifica el valor de parametros.
	 *
	 * @param ar_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(TarifaFija ar_c)
	{
		ir_parametros = ar_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public TarifaFija getParametros()
	{
		if(ir_parametros == null)
			ir_parametros = new TarifaFija();

		return ir_parametros;
	}

	/**
	 * Modifica el valor de tarifa fija.
	 *
	 * @param acr_cr asigna el valor a la propiedad tarifa fija
	 */
	public void setTarifaFija(TarifaFija acr_cr)
	{
		ir_parametros = acr_cr;
	}

	/**
	 * Retorna el valor de tarifa fija.
	 *
	 * @return el valor de tarifa fija
	 */
	public TarifaFija getTarifaFija()
	{
		return ir_parametros;
	}

	/**
	 * Metodo que indica si se desea insertar un TarifaFija.
	 *
	 * @param arc_tarifaFija seleccionado
	 *            objeto el cual se va a insertar o modificar
	 * @param ab_proceso            indica si se va a insetar o se va a modificar
	 * @return devuelve el valor de String
	 */
	public String botonInsertar(TarifaFija arc_tarifaFija, boolean ab_proceso)
	{
		String ls_return;
		ls_return = null;

		if(ab_proceso)
		{
			arc_tarifaFija = new TarifaFija();

			setTarifaFija(arc_tarifaFija);
			setInsertar(true);
		}
		else
		{
			setTarifaFija(arc_tarifaFija);
			setInsertar(false);
		}

		ls_return = NavegacionCommon.TARIFA_FIJA_DETALLE;

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<TarifaFija> cargarTarifaFija()
	{
		Collection<TarifaFija> lrc_constantes;
		lrc_constantes = null;

		try
		{
			lrc_constantes = ipr_parameterRemote.findAllTarifaFija(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lrc_constantes;
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setParametros(null);
		setInsertar(false);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<Proceso> findAllProcesos()
	{
		Collection<Proceso> lcp_datos;
		lcp_datos = null;

		try
		{
			lcp_datos = irr_referenceRemote.findAllProcesosActivos(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcp_datos;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param as_idProceso correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Collection
	 */
	public Collection<Subproceso> findAllSubprocesos(String as_idProceso)
	{
		Collection<Subproceso> lcs_datos;
		lcs_datos = null;

		try
		{
			if(StringUtils.isValidString(as_idProceso))
			{
				Subproceso ls_subproceso;

				ls_subproceso = new Subproceso();

				ls_subproceso.setIdProceso(as_idProceso);

				lcs_datos = irr_referenceRemote.findSubprocesosByProceso(
					    ls_subproceso, false, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcs_datos;
	}

	/**
	 * Metodo para el manejo de inserciones o actualizaciones de TarifaFija.
	 *
	 * @param ab_insertar            indica si se desea insertar o actualizar dependiendo su valor, si
	 *            su valor es true el inserta un nuevo registro, en cambio si su
	 *            valor es false realiza una actualizacion en la base de datos.
	 * @return devuelve el valor de String
	 */
	public String insertUpdateTarifaFija(boolean ab_insertar)
	{
		FacesContext lfc_context;
		boolean      lb_focus;

		lfc_context     = FacesContext.getCurrentInstance();
		lb_focus        = true;

		try
		{
			TarifaFija ltf_TarifaFija;

			ltf_TarifaFija = getParametros();

			if(ltf_TarifaFija != null)
			{
				{
					String ls_idTarifa;

					ls_idTarifa     = ltf_TarifaFija.getIdTarifaFija();
					lb_focus        = validateStyles(
						    ":fsTarifaFijaDetalle:idTarifaFija", lfc_context, ls_idTarifa, lb_focus
						);

					if(!StringUtils.isValidString(ls_idTarifa))
						throw new B2BException(ErrorKeys.ERROR_ID_TARIFA_FIJA);
				}

				{
					Long ll_version;

					ll_version     = ltf_TarifaFija.getVersion();
					lb_focus       = validateStyles(
						    ":fsTarifaFijaDetalle:idItVersion", lfc_context, ll_version, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_version))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_VERSION);
				}

				{
					Integer li_valor;

					li_valor     = ltf_TarifaFija.getValor();
					lb_focus     = validateStyles(
						    ":fsTarifaFijaDetalle:idItValor", lfc_context, NumericUtils.getDoubleWrapper(li_valor),
						    lb_focus
						);

					if(!NumericUtils.isValidInteger(li_valor))
					{
						lb_focus = validateStyles(":fsTarifaFijaDetalle:idItValor", lfc_context, "", lb_focus);

						throw new B2BException(ErrorKeys.ERROR_SIN_VALOR);
					}
				}

				{
					Date ls_fechaDesde;
					ls_fechaDesde     = ltf_TarifaFija.getFechaDesde();

					lb_focus = validateStyles(
						    ":fsTarifaFijaDetalle:idItFechaDesde", lfc_context, ls_fechaDesde, lb_focus
						);

					if(ls_fechaDesde == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
				}

				{
					String ls_activo;
					ls_activo     = ltf_TarifaFija.getActivo();

					lb_focus = validateStyles(":fsTarifaFijaDetalle:idSOMActivo", lfc_context, ls_activo, lb_focus);

					if(!StringUtils.isValidString(ls_activo))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_ACTIVO);
				}

				ipr_parameterRemote.insertUpdateTarifaFija(
				    ltf_TarifaFija, ab_insertar, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			return null;
		}

		return NavegacionCommon.TARIFA_FIJA;
	}
}
