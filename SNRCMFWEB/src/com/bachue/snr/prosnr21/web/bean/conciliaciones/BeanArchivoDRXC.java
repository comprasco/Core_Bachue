package com.bachue.snr.prosnr21.web.bean.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr21.common.constants.MessagesKeys;

import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ConciliacionesRemote;
import com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones.ParameterRemote;

import com.bachue.snr.prosnr21.model.pgn.ArchivoDRXC;
import com.bachue.snr.prosnr21.model.pgn.DRXCTotalBCO;
import com.bachue.snr.prosnr21.model.pgn.DRXCTotalCTA;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraConciliacion;
import com.bachue.snr.prosnr21.model.pgn.EntidadRecaudadoraCuenta;
import com.bachue.snr.prosnr21.model.pgn.TransaccionIngresos;

import com.bachue.snr.prosnr21.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanArchivoDRXC.
 *
 * @author  Duvan Beltrán
 */
@ManagedBean(name = "beanArchivoDRXC")
@SessionScoped
public class BeanArchivoDRXC extends BaseBean implements Serializable
{
	/**  Constante serialVersionUID. */
	private static final long serialVersionUID = 1162778887344306906L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanArchivoDRXC.class);

	/**  Propiedad DRXC. */
	private ArchivoDRXC iad_DRXC;

	/** Propiedad Entidad recaaudadoraCuenta. */
	private Collection<EntidadRecaudadoraCuenta> ic_entidadRecaudadoraCuentas;

	/** Propiedad list archivo drxc banco. */
	private Collection<DRXCTotalBCO> ic_listArchivoDrxcBanco;

	/** Propiedad list archivo drxc cuenta. */
	private Collection<DRXCTotalCTA> ic_listArchivoDrxcCuenta;

	/** Propiedad ipr conciliaciones remote. */
	@EJB
	private ConciliacionesRemote ipr_conciliacionesRemote;

	/** Propiedad list archivo drxc banco. */
	private DRXCTotalBCO idtb_archivoDrxcBanco;

	/** Propiedad list archivo drxc cuenta. */
	private DRXCTotalCTA idtc_archivoDrxcCuenta;

	/** Propiedad fecha conciliar. */
	private Date id_fechaConciliar;

	/** Propiedad Entidad Recaudadora cuenta. */
	private EntidadRecaudadoraCuenta ir_EntidadRecaudadoraCuenta;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad id banco. */
	private String is_idBanco;

	/**  Propiedad id cuenta. */
	private String is_idCuenta;

	/**  Propiedad id entidad recaudadora. */
	private String is_idEntidadRecaudadora;

	/** Propiedad is periodo. */
	private String is_periodo;

	/**  Propiedad iti transaccionIngresos. */
	private TransaccionIngresos iti_transaccionIngresos;

	/**  Propiedad iti transaccionIngresosBancoCuenta. */
	private TransaccionIngresos iti_transaccionIngresosBancoCuenta;

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
	 * Modifica el valor de ArchivoDrxcBanco.
	 *
	 * @param ldtb_archivoDrxcBanco de ldtb archivo drxc banco
	 */
	public void setArchivoDrxcBanco(DRXCTotalBCO ldtb_archivoDrxcBanco)
	{
		this.idtb_archivoDrxcBanco = ldtb_archivoDrxcBanco;
	}

	/**
	 * Retorna Objeto o variable de valor archivo drxc banco.
	 *
	 * @return el valor de archivo drxc banco
	 */
	public DRXCTotalBCO getArchivoDrxcBanco()
	{
		return idtb_archivoDrxcBanco;
	}

	/**
	 * Modifica el valor de ArchivoDrxcCuenta.
	 *
	 * @param ldtc_archivoDrxcCuenta de ldtc archivo drxc cuenta
	 */
	public void setArchivoDrxcCuenta(DRXCTotalCTA ldtc_archivoDrxcCuenta)
	{
		this.idtc_archivoDrxcCuenta = ldtc_archivoDrxcCuenta;
	}

	/**
	 * Retorna Objeto o variable de valor archivo drxc cuenta.
	 *
	 * @return el valor de archivo drxc cuenta
	 */
	public DRXCTotalCTA getArchivoDrxcCuenta()
	{
		return idtc_archivoDrxcCuenta;
	}

	/**
	 * Modifica el valor de DRXC.
	 *
	 * @param aad_DRXC de aad DRXC
	 */
	public void setDRXC(ArchivoDRXC aad_DRXC)
	{
		iad_DRXC = aad_DRXC;
	}

	/**
	 * Retorna Objeto o variable de valor drxc.
	 *
	 * @return Retorna el valor de la propiedad iad_DRXC
	 */
	public ArchivoDRXC getDRXC()
	{
		return iad_DRXC;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param ar_EntidadRecaudadoraCuenta asigna el valor a la propiedad
	 */
	public void setEntidadRecaudadoraCuenta(EntidadRecaudadoraCuenta ar_EntidadRecaudadoraCuenta)
	{
		ir_EntidadRecaudadoraCuenta = ar_EntidadRecaudadoraCuenta;
	}

	/**
	 * Método de obtencion del valor de la propiedad.
	 *
	 * @return devuelve el valor de la propiedad
	 */
	public EntidadRecaudadoraCuenta getEntidadRecaudadoraCuenta()
	{
		return ir_EntidadRecaudadoraCuenta;
	}

	/**
	 * Modifica el valor de EntidadRecaudadoraCuentas.
	 *
	 * @param ac_entidadRecaudadoraCuentas de ac entidad recaudadora cuentas
	 */
	public void setEntidadRecaudadoraCuentas(Collection<EntidadRecaudadoraCuenta> ac_entidadRecaudadoraCuentas)
	{
		ic_entidadRecaudadoraCuentas = ac_entidadRecaudadoraCuentas;
	}

	/**
	 * Retorna Objeto o variable de valor entidad recaudadora cuentas.
	 *
	 * @return Retorna el valor de la propiedad ic_entidadRecaudadoraCuentas
	 */
	public Collection<EntidadRecaudadoraCuenta> getEntidadRecaudadoraCuentas()
	{
		return ic_entidadRecaudadoraCuentas;
	}

	/**
	 * Modifica el valor de FechaConciliar.
	 *
	 * @param ad_fechaConciliar de ad fecha conciliar
	 */
	public void setFechaConciliar(Date ad_fechaConciliar)
	{
		id_fechaConciliar = ad_fechaConciliar;
	}

	/**
	 * Retorna Objeto o variable de valor fecha conciliar.
	 *
	 * @return Retorna el valor de la propiedad id_fechaConciliar
	 */
	public Date getFechaConciliar()
	{
		return id_fechaConciliar;
	}

	/**
	 * Modifica el valor de IdBanco.
	 *
	 * @param as_idBanco de as id banco
	 */
	public void setIdBanco(String as_idBanco)
	{
		is_idBanco = as_idBanco;
	}

	/**
	 * Retorna Objeto o variable de valor id banco.
	 *
	 * @return Retorna el valor de la propiedad is_idBanco
	 */
	public String getIdBanco()
	{
		return is_idBanco;
	}

	/**
	 * Modifica el valor de IdCuenta.
	 *
	 * @param as_idCuenta de as id cuenta
	 */
	public void setIdCuenta(String as_idCuenta)
	{
		is_idCuenta = as_idCuenta;
	}

	/**
	 * Retorna Objeto o variable de valor id cuenta.
	 *
	 * @return Retorna el valor de la propiedad is_idCuenta
	 */
	public String getIdCuenta()
	{
		return is_idCuenta;
	}

	/**
	 * Modifica el valor de IdEntidadRecaudadora.
	 *
	 * @param as_idEntidadRecaudadora de as id entidad recaudadora
	 */
	public void setIdEntidadRecaudadora(String as_idEntidadRecaudadora)
	{
		is_idEntidadRecaudadora = as_idEntidadRecaudadora;
	}

	/**
	 * Retorna Objeto o variable de valor id entidad recaudadora.
	 *
	 * @return Retorna el valor de la propiedad is_idEntidadRecaudadora
	 */
	public String getIdEntidadRecaudadora()
	{
		return is_idEntidadRecaudadora;
	}

	/**
	 * Modifica el valor de ListArchivoDrxcBanco.
	 *
	 * @param ac_listArchivoDrxcBanco de ac list archivo drxc banco
	 */
	public void setListArchivoDrxcBanco(Collection<DRXCTotalBCO> ac_listArchivoDrxcBanco)
	{
		this.ic_listArchivoDrxcBanco = ac_listArchivoDrxcBanco;
	}

	/**
	 * Retorna Objeto o variable de valor list archivo drxc banco.
	 *
	 * @return el valor de list archivo drxc banco
	 */
	public Collection<DRXCTotalBCO> getListArchivoDrxcBanco()
	{
		return ic_listArchivoDrxcBanco;
	}

	/**
	 * Modifica el valor de ListArchivoDrxcCuenta.
	 *
	 * @param ac_listArchivoDrxcCuenta de ac list archivo drxc cuenta
	 */
	public void setListArchivoDrxcCuenta(Collection<DRXCTotalCTA> ac_listArchivoDrxcCuenta)
	{
		this.ic_listArchivoDrxcCuenta = ac_listArchivoDrxcCuenta;
	}

	/**
	 * Retorna Objeto o variable de valor list archivo drxc cuenta.
	 *
	 * @return el valor de list archivo drxc cuenta
	 */
	public Collection<DRXCTotalCTA> getListArchivoDrxcCuenta()
	{
		return ic_listArchivoDrxcCuenta;
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
	 * @param Modifica el valor de la propiedad periodo por periodo
	 */
	public void setPeriodo(String as_periodo)
	{
		is_periodo = as_periodo;
	}

	/**
	 * @return Retorna el valor de la propiedad periodo
	 */
	public String getPeriodo()
	{
		return is_periodo;
	}

	/**
	 * Modifica el valor de TransaccionIngresos.
	 *
	 * @param ati_transaccionIngresos de ati transaccion ingresos
	 */
	public void setTransaccionIngresos(TransaccionIngresos ati_transaccionIngresos)
	{
		iti_transaccionIngresos = ati_transaccionIngresos;
	}

	/**
	 * Retorna Objeto o variable de valor transaccion ingresos.
	 *
	 * @return Retorna el valor de la propiedad iti_transaccionIngresos
	 */
	public TransaccionIngresos getTransaccionIngresos()
	{
		return iti_transaccionIngresos;
	}

	/**
	 * Modifica el valor de TransaccionIngresosBancoCuenta.
	 *
	 * @param ati_transaccionIngresosBancoCuenta de ati transaccion ingresos banco cuenta
	 */
	public void setTransaccionIngresosBancoCuenta(TransaccionIngresos ati_transaccionIngresosBancoCuenta)
	{
		iti_transaccionIngresosBancoCuenta = ati_transaccionIngresosBancoCuenta;
	}

	/**
	 * Retorna Objeto o variable de valor transaccion ingresos banco cuenta.
	 *
	 * @return Retorna el valor de la propiedad iti_transaccionIngresosBancoCuenta
	 */
	public TransaccionIngresos getTransaccionIngresosBancoCuenta()
	{
		return iti_transaccionIngresosBancoCuenta;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación asignadas a un usuario.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraConciliacion> buscarEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcpc_procesoConciliacion;
		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_conciliacionesRemote.findEntidadRecaudadoraConciliacionByUser(getUserId());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 *  Método que retorna todas las entidades recaudadoras de conciliación asiganadas a un usuario y una entidad.
	 *
	 * Retorna un objeto de tipo <code>Collection</code> que contiene los resultados que coincidieron con los criterios de búsqueda.
	 *
	 * @return el valor de collection
	 */
	public Collection<EntidadRecaudadoraCuenta> buscarEntidadRecaudadoraCuentaDeUsuario()
	{
		Collection<EntidadRecaudadoraCuenta> lcpc_procesoConciliacion;
		lcpc_procesoConciliacion = null;

		try
		{
			lcpc_procesoConciliacion = ipr_conciliacionesRemote.findEntidadRecaudadoraCuentaByUser(
				    getIdEntidadRecaudadora(), getUserId()
				);

			setEntidadRecaudadoraCuentas(lcpc_procesoConciliacion);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcpc_procesoConciliacion;
	}

	/**
	 * Consultar alertas.
	 */
	@SuppressWarnings("unchecked")
	public void consultarArchivoDRXC()
	{
		try
		{
			boolean lb_focus;
			boolean lb_focus1;
			Date    ld_date;
			String  ls_idEntidad;
			String  ls_idCuenta;

			lb_focus         = true;
			lb_focus1        = true;
			ld_date          = getFechaConciliar();
			ls_idEntidad     = getIdEntidadRecaudadora();
			ls_idCuenta      = getIdCuenta();

			{
				FacesContext lfc_context;

				lfc_context     = FacesContext.getCurrentInstance();
				lb_focus        = validateStyles(
					    ":fArchivoDRXC:idEntidadRecaudadoraCuenta", lfc_context, ls_idEntidad, lb_focus
					);

				lb_focus = validateStyles(":fArchivoDRXC:idNumeroCuenta", lfc_context, ls_idCuenta, lb_focus);

				if(ld_date == null)
					lb_focus = validateStyles(":fArchivoDRXC:idFechaAConciliar", lfc_context, ld_date, lb_focus);
				else if(ld_date.after(new Date()))
				{
					lb_focus1 = validateStyles(":fArchivoDRXC:idFechaAConciliar", lfc_context, ld_date, lb_focus1);
					addMessageInfo("W", MessagesKeys.ERROR_FECHA_MAYOR_A_ACTUAL);
					lb_focus1 = false;
				}
				else
					lb_focus1 = validateStyles(":fArchivoDRXC:idFechaAConciliar", lfc_context, ld_date, lb_focus1);
			}

			PrimeFaces.current().ajax().update("fArchivoDRXC");

			if(!lb_focus || !lb_focus1)
				throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);

			{
				EntidadRecaudadoraCuenta ler_objTemp;
				Map<String, Object>      lmsc_data;

				ler_objTemp = new EntidadRecaudadoraCuenta();

				ler_objTemp.setIdEntidadRecaudadora(ls_idEntidad);
				ler_objTemp.setIdCuenta(ls_idCuenta);
				ler_objTemp.setFechaConfrontar(ld_date);

				lmsc_data = ipr_conciliacionesRemote.realizarBusquedaFechaConciliarDRXC(
					    ler_objTemp, getUserId(), getRemoteIpAddress(), getLocalIpAddress()
					);

				if(lmsc_data != null)
				{
					{
						Object lo_data;

						lo_data = lmsc_data.get(IdentificadoresCommon.DRXC_CUENTA);

						if(lo_data instanceof Collection<?>)
						{
							Collection<DRXCTotalCTA> lcdtc_cuenta;

							lcdtc_cuenta = (Collection<DRXCTotalCTA>)lo_data;

							setListArchivoDrxcCuenta(lcdtc_cuenta);

							if(CollectionUtils.isValidCollection(lcdtc_cuenta))
								setArchivoDrxcCuenta(lcdtc_cuenta.iterator().next());
						}
					}

					{
						Object lo_data;

						lo_data = lmsc_data.get(IdentificadoresCommon.DRXC_BANCO);

						if(lo_data instanceof Collection<?>)
						{
							Collection<DRXCTotalBCO> lcdtb_banco;

							lcdtb_banco = (Collection<DRXCTotalBCO>)lo_data;

							setListArchivoDrxcBanco(lcdtb_banco);

							if(CollectionUtils.isValidCollection(lcdtb_banco))
								setArchivoDrxcBanco(lcdtb_banco.iterator().next());
						}
					}

					setMostrarPanel(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			setListArchivoDrxcCuenta(null);
			setArchivoDrxcCuenta(null);
			setListArchivoDrxcBanco(null);
			setArchivoDrxcBanco(null);

			clh_LOGGER.error("consultarArchivoDRXC", lb2be_e);
			addMessage(lb2be_e);
			actualizarComponente(":fArchivoDRXC:idGrowl");
		}
	}

	/**
	 * Método de consulta de todos los EntidadRecaudadoraConciliacions.
	 *
	 * @return una colleccion de tipo EntidadRecaudadoraConciliacion
	 */
	public Collection<EntidadRecaudadoraConciliacion> findAllEntidadRecaudadoraConciliacion()
	{
		Collection<EntidadRecaudadoraConciliacion> lcerc_erc;

		lcerc_erc = null;

		try
		{
			lcerc_erc = ipr_parameterRemote.findAllEntidadRecaudadoraConciliacion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcerc_erc;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar()
	{
		setArchivoDrxcBanco(null);
		setArchivoDrxcCuenta(null);
		setDRXC(null);
		setEntidadRecaudadoraCuenta(null);
		setEntidadRecaudadoraCuentas(null);
		setFechaConciliar(null);
		setIdBanco(null);
		setIdCuenta(null);
		setIdEntidadRecaudadora(null);
		setListArchivoDrxcBanco(null);
		setListArchivoDrxcCuenta(null);
		setMostrarPanel(false);
		setTransaccionIngresos(null);
		setTransaccionIngresosBancoCuenta(null);
		setPeriodo(null);
	}

	public String salvar()
	{
		String ls_result;

		ls_result = null;

		try
		{
			if(
			    (idtc_archivoDrxcCuenta.getIdDrxcTotalCta() != null)
				    && (idtc_archivoDrxcCuenta.getIdEntidadRecaudadora() != null)
				    && (idtc_archivoDrxcCuenta.getIdCuenta() != null)
				    && (idtc_archivoDrxcCuenta.getNumeroSIIF() != null)
				    && (idtc_archivoDrxcCuenta.getFechaSIIF() != null)
			)
			{
				ipr_conciliacionesRemote.fillNumeroFechaSIIF(
				    idtc_archivoDrxcCuenta, getUserId(), getRemoteIpAddress(), getLocalIpAddress()
				);

				addMessageInfo("I", MessagesKeys.MODIFICACION_EXITOSA);

				ls_result = com.bachue.snr.prosnr21.common.constants.NavegacionCommon.ARCHIVO_DRXC;
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SELECCION_INFORMACION_CONSULTA);
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
