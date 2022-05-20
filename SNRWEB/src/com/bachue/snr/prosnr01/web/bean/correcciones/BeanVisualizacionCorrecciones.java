package com.bachue.snr.prosnr01.web.bean.correcciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio.ConsultaPredioRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.Matricula;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.CabidaLinderos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ConsultaSalvedad;
import com.bachue.snr.prosnr01.model.sdb.bng.LinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.bng.SalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.col.TipoUsoSuelo;
import com.bachue.snr.prosnr01.model.sdb.pgn.AlertaNaturalezaJuridica;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;

import org.primefaces.PrimeFaces;

import org.primefaces.component.tabview.Tab;

import org.primefaces.event.TabChangeEvent;

import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanVisualizacionCorrecciones.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanVisualizacionCorrecciones")
public class BeanVisualizacionCorrecciones extends BeanEjecucionCorrecciones implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8893457723351212372L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanVisualizacionCorrecciones.class);

	/** Constante cs_tableMatriculas. */
	private static final String cs_tableMatriculas = "confrontacion:idDtMatriculas";

	/** Constante cs_ladoDerecho. */
	private static final String cs_ladoDerecho = "changeTvdetalleRegistroCalif";

	/** Constante cs_ladoIzquierdo. */
	private static final String cs_ladoIzquierdo = "TvdetalleRegistroCalif";

	/** Constante cs_tabAnotaciones. */
	private static final String cs_tabAnotaciones = "Anotaciones_id";

	/** Constante cs_tabChangeAnotaciones. */
	private static final String cs_tabChangeAnotaciones = "changeAnotaciones_id";

	/** Constante cs_tabChangeSalvedades. */
	private static final String cs_tabChangeSalvedades = "changeSalvedades_id";

	/** Constante cs_tabSalvedades. */
	private static final String cs_tabSalvedades = "Salvedades_id";

	/** Constante cs_tabDatosBasicos. */
	private static final String cs_tabDatosBasicos = "datosBasicos_id";

	/** Constante cs_tabChangeDatosBasicos. */
	private static final String cs_tabChangeDatosBasicos = "changedatosBasicos_id";

	/** Constante cs_tabDescripcion. */
	private static final String cs_tabDescripcion = "Descripcion_predio";

	/** Constante cs_tabChangeDescripcion. */
	private static final String cs_tabChangeDescripcion = "changeDescripcion_predio";

	/** Constante cs_indexDatosBasicos. */
	private static final int cs_indexDatosBasicos = 0;

	/** Constante cs_indexDescripcionPredio. */
	private static final int cs_indexDescripcionPredio = 1;

	/** Constante cs_indexAnotaciones. */
	private static final int cs_indexAnotaciones = 2;

	/** Constante cs_indexSalvedades. */
	private static final int cs_indexSalvedades = 3;

	/** Propiedad iaaui area UI bng. */
	private AccAreaUI iaaui_areaUIBng;

	/** Propiedad iant alerta naturaleza juridica bng. */
	private AlertaNaturalezaJuridica iant_alertaNaturalezaJuridicaBng;

	/** Propiedad iap anotacion predio bng. */
	private AnotacionPredio iap_anotacionPredioBng;

	/** Propiedad iap area predio bng. */
	private AreaPredio iap_areaPredioBng;

	/** Propiedad icl cabida linderos bng. */
	private CabidaLinderos icl_cabidaLinderosBng;

	/** Propiedad iccs salvedades bng. */
	private Collection<ConsultaSalvedad> iccs_salvedadesBng;

	/** Propiedad icddp direcciones temporales bng. */
	private Collection<DireccionDelPredio> icddp_direccionesTemporalesBng;

	/** Propiedad icps predios segregados bng. */
	private Collection<PredioSegregado> icps_prediosSegregadosBng;

	/** Propiedad icsp salvedades predio. */
	private Collection<AccSalvedadPredio> icsp_salvedadesPredio;

	/** Propiedad icsp salvedades predio bng. */
	private Collection<SalvedadPredio> icsp_salvedadesPredioBng;

	/** Propiedad iddp direcciones predio bng. */
	private Collection<DireccionDelPredio> iddp_direccionesPredioBng;

	/** Propiedad icepe consulta predio remote. */
	@EJB
	private ConsultaPredioRemote icepe_consultaPredioRemote;

	/** Propiedad idas antiguo sistema data bng. */
	private DatosAntSistema idas_antiguoSistemaDataBng;

	/** Propiedad idb datos basicos bng. */
	private DatosBasicos idb_datosBasicosBng;

	/** Propiedad ier entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad li row active. */
	private Integer li_rowActive;

	/** Propiedad il matricula link bng. */
	private Long il_matriculaLinkBng;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ipr predio registro bng. */
	private PredioRegistro ipr_predioRegistroBng;

	/** Propiedad is circulo link bng. */
	private String is_circuloLinkBng;

	/** Propiedad is descripcion tipo uso suelo bng. */
	private String is_descripcionTipoUsoSueloBng;

	/** Propiedad ib renderizar. */
	private boolean ib_renderizar;

	/** Propiedad ii tab active. */
	private int ii_tabActive;

	/** Propiedad ii total anotaciones bng. */
	private int ii_totalAnotacionesBng;

	/**
	 * Modifica el valor de alerta naturaleza juridica bng.
	 *
	 * @param aanj_anj asigna el valor a la propiedad alerta naturaleza juridica bng
	 */
	public void setAlertaNaturalezaJuridicaBng(AlertaNaturalezaJuridica aanj_anj)
	{
		iant_alertaNaturalezaJuridicaBng = aanj_anj;
	}

	/**
	 * Retorna el valor de alerta naturaleza juridica bng.
	 *
	 * @return el valor de alerta naturaleza juridica bng
	 */
	public AlertaNaturalezaJuridica getAlertaNaturalezaJuridicaBng()
	{
		return iant_alertaNaturalezaJuridicaBng;
	}

	/**
	 * Modifica el valor de anotacion predio bng.
	 *
	 * @param aap_ap asigna el valor a la propiedad anotacion predio bng
	 */
	public void setAnotacionPredioBng(AnotacionPredio aap_ap)
	{
		iap_anotacionPredioBng = aap_ap;
	}

	/**
	 * Retorna el valor de anotacion predio bng.
	 *
	 * @return el valor de anotacion predio bng
	 */
	public AnotacionPredio getAnotacionPredioBng()
	{
		if(iap_anotacionPredioBng == null)
		{
			iap_anotacionPredioBng = new AnotacionPredio();
			iap_anotacionPredioBng.setIdEstadoAnotacion(EstadoCommon.V);
		}

		return iap_anotacionPredioBng;
	}

	/**
	 * Modifica el valor de antiguo sistema data bng.
	 *
	 * @param adas_das asigna el valor a la propiedad antiguo sistema data bng
	 */
	public void setAntiguoSistemaDataBng(DatosAntSistema adas_das)
	{
		idas_antiguoSistemaDataBng = adas_das;
	}

	/**
	 * Retorna el valor de antiguo sistema data bng.
	 *
	 * @return el valor de antiguo sistema data bng
	 */
	public DatosAntSistema getAntiguoSistemaDataBng()
	{
		return idas_antiguoSistemaDataBng;
	}

	/**
	 * Modifica el valor de area predio bng.
	 *
	 * @param aap_ap asigna el valor a la propiedad area predio bng
	 */
	public void setAreaPredioBng(AreaPredio aap_ap)
	{
		iap_areaPredioBng = aap_ap;
	}

	/**
	 * Retorna el valor de area predio bng.
	 *
	 * @return el valor de area predio bng
	 */
	public AreaPredio getAreaPredioBng()
	{
		return iap_areaPredioBng;
	}

	/**
	 * Modifica el valor de area UI bng.
	 *
	 * @param aaaui_aaui asigna el valor a la propiedad area UI bng
	 */
	public void setAreaUIBng(AccAreaUI aaaui_aaui)
	{
		iaaui_areaUIBng = aaaui_aaui;
	}

	/**
	 * Retorna el valor de area UI bng.
	 *
	 * @return el valor de area UI bng
	 */
	public AccAreaUI getAreaUIBng()
	{
		return iaaui_areaUIBng;
	}

	/**
	 * Modifica el valor de cabida linderos bng.
	 *
	 * @param acl_cl asigna el valor a la propiedad cabida linderos bng
	 */
	public void setCabidaLinderosBng(CabidaLinderos acl_cl)
	{
		icl_cabidaLinderosBng = acl_cl;
	}

	/**
	 * Retorna el valor de cabida linderos bng.
	 *
	 * @return el valor de cabida linderos bng
	 */
	public CabidaLinderos getCabidaLinderosBng()
	{
		if(icl_cabidaLinderosBng == null)
			icl_cabidaLinderosBng = new CabidaLinderos();

		return icl_cabidaLinderosBng;
	}

	/**
	 * Modifica el valor de circulo link bng.
	 *
	 * @param as_s asigna el valor a la propiedad circulo link bng
	 */
	public void setCirculoLinkBng(String as_s)
	{
		is_circuloLinkBng = as_s;
	}

	/**
	 * Retorna el valor de circulo link bng.
	 *
	 * @return el valor de circulo link bng
	 */
	public String getCirculoLinkBng()
	{
		return is_circuloLinkBng;
	}

	/**
	 * Modifica el valor de datos basicos bng.
	 *
	 * @param adb_db asigna el valor a la propiedad datos basicos bng
	 */
	public void setDatosBasicosBng(DatosBasicos adb_db)
	{
		idb_datosBasicosBng = adb_db;
	}

	/**
	 * Retorna el valor de datos basicos bng.
	 *
	 * @return el valor de datos basicos bng
	 */
	public DatosBasicos getDatosBasicosBng()
	{
		return idb_datosBasicosBng;
	}

	/**
	 * Modifica el valor de descripcion tipo uso suelo bng.
	 *
	 * @param as_s asigna el valor a la propiedad descripcion tipo uso suelo bng
	 */
	public void setDescripcionTipoUsoSueloBng(String as_s)
	{
		is_descripcionTipoUsoSueloBng = as_s;
	}

	/**
	 * Retorna el valor de descripcion tipo uso suelo bng.
	 *
	 * @return el valor de descripcion tipo uso suelo bng
	 */
	public String getDescripcionTipoUsoSueloBng()
	{
		if(!StringUtils.isValidString(is_descripcionTipoUsoSueloBng))
		{
			try
			{
				AccAreaUI laau_areaUI = getAreaUIBng();

				if(laau_areaUI != null)
				{
					AccAreaPredio laap_areaPredio;

					laap_areaPredio = laau_areaUI.getAreaPredio();

					if(laap_areaPredio != null)
					{
						TipoUsoSuelo ltus_tusTemp;

						ltus_tusTemp = new TipoUsoSuelo();

						ltus_tusTemp.setIdTipoUsoSuelo(laap_areaPredio.getTipoSuelo());

						ltus_tusTemp = ipr_parameterRemote.findTipoUsoPredioById(ltus_tusTemp);

						if(ltus_tusTemp != null)
							setDescripcionTipoUsoSueloBng(ltus_tusTemp.getDescription());
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}

		return is_descripcionTipoUsoSueloBng;
	}

	/**
	 * Modifica el valor de direcciones predio bng.
	 *
	 * @param addp_ddp asigna el valor a la propiedad direcciones predio bng
	 */
	public void setDireccionesPredioBng(Collection<DireccionDelPredio> addp_ddp)
	{
		iddp_direccionesPredioBng = addp_ddp;
	}

	/**
	 * Retorna el valor de direcciones predio bng.
	 *
	 * @return el valor de direcciones predio bng
	 */
	public Collection<DireccionDelPredio> getDireccionesPredioBng()
	{
		return iddp_direccionesPredioBng;
	}

	/**
	 * Modifica el valor de direcciones temporales bng.
	 *
	 * @param acddp_cddp asigna el valor a la propiedad direcciones temporales bng
	 */
	public void setDireccionesTemporalesBng(Collection<DireccionDelPredio> acddp_cddp)
	{
		icddp_direccionesTemporalesBng = acddp_cddp;
	}

	/**
	 * Retorna el valor de direcciones temporales bng.
	 *
	 * @return el valor de direcciones temporales bng
	 */
	public Collection<DireccionDelPredio> getDireccionesTemporalesBng()
	{
		return icddp_direccionesTemporalesBng;
	}

	/**
	 * Modifica el valor de matricula link bng.
	 *
	 * @param al_l asigna el valor a la propiedad matricula link bng
	 */
	public void setMatriculaLinkBng(Long al_l)
	{
		il_matriculaLinkBng = al_l;
	}

	/**
	 * Retorna el valor de matricula link bng.
	 *
	 * @return el valor de matricula link bng
	 */
	public Long getMatriculaLinkBng()
	{
		return il_matriculaLinkBng;
	}

	/**
	 * Modifica el valor de predio registro bng.
	 *
	 * @param apr_pr asigna el valor a la propiedad predio registro bng
	 */
	public void setPredioRegistroBng(PredioRegistro apr_pr)
	{
		ipr_predioRegistroBng = apr_pr;
	}

	/**
	 * Retorna el valor de predio registro bng.
	 *
	 * @return el valor de predio registro bng
	 */
	public PredioRegistro getPredioRegistroBng()
	{
		return ipr_predioRegistroBng;
	}

	/**
	 * Modifica el valor de predios segregados bng.
	 *
	 * @param acps_cps asigna el valor a la propiedad predios segregados bng
	 */
	public void setPrediosSegregadosBng(Collection<PredioSegregado> acps_cps)
	{
		icps_prediosSegregadosBng = acps_cps;
	}

	/**
	 * Retorna el valor de predios segregados bng.
	 *
	 * @return el valor de predios segregados bng
	 */
	public Collection<PredioSegregado> getPrediosSegregadosBng()
	{
		return icps_prediosSegregadosBng;
	}

	/**
	 * Modifica el valor de renderizar.
	 *
	 * @param ab_b asigna el valor a la propiedad renderizar
	 */
	public void setRenderizar(boolean ab_b)
	{
		ib_renderizar = ab_b;
	}

	/**
	 * Valida la propiedad renderizar.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en renderizar
	 */
	public boolean isRenderizar()
	{
		return ib_renderizar;
	}

	/**
	 * Modifica el valor de row active.
	 *
	 * @param ai_i asigna el valor a la propiedad row active
	 */
	public void setRowActive(Integer ai_i)
	{
		li_rowActive = ai_i;
	}

	/**
	 * Retorna el valor de row active.
	 *
	 * @return el valor de row active
	 */
	public Integer getRowActive()
	{
		return li_rowActive;
	}

	/**
	 * Modifica el valor de salvedades bng.
	 *
	 * @param accs_ccs asigna el valor a la propiedad salvedades bng
	 */
	public void setSalvedadesBng(Collection<ConsultaSalvedad> accs_ccs)
	{
		iccs_salvedadesBng = accs_ccs;
	}

	/**
	 * Retorna el valor de salvedades bng.
	 *
	 * @return el valor de salvedades bng
	 */
	public Collection<ConsultaSalvedad> getSalvedadesBng()
	{
		return iccs_salvedadesBng;
	}

	/**
	 * Modifica el valor de salvedades predio.
	 *
	 * @param acsp_csp asigna el valor a la propiedad salvedades predio
	 */
	public void setSalvedadesPredio(Collection<AccSalvedadPredio> acsp_csp)
	{
		icsp_salvedadesPredio = acsp_csp;
	}

	/**
	 * Retorna el valor de salvedades predio.
	 *
	 * @return el valor de salvedades predio
	 */
	public Collection<AccSalvedadPredio> getSalvedadesPredio()
	{
		return icsp_salvedadesPredio;
	}

	/**
	 * Modifica el valor de salvedades predio bng.
	 *
	 * @param acsp_csp asigna el valor a la propiedad salvedades predio bng
	 */
	public void setSalvedadesPredioBng(Collection<SalvedadPredio> acsp_csp)
	{
		icsp_salvedadesPredioBng = acsp_csp;
	}

	/**
	 * Retorna el valor de salvedades predio bng.
	 *
	 * @return el valor de salvedades predio bng
	 */
	public Collection<SalvedadPredio> getSalvedadesPredioBng()
	{
		return icsp_salvedadesPredioBng;
	}

	/**
	 * Modifica el valor de tab active.
	 *
	 * @param ai_i asigna el valor a la propiedad tab active
	 */
	public void setTabActive(int ai_i)
	{
		ii_tabActive = ai_i;
	}

	/**
	 * Retorna el valor de tab active.
	 *
	 * @return el valor de tab active
	 */
	public int getTabActive()
	{
		return ii_tabActive;
	}

	/**
	 * Modifica el valor de total anotaciones bng.
	 *
	 * @param ai_i asigna el valor a la propiedad total anotaciones bng
	 */
	public void setTotalAnotacionesBng(int ai_i)
	{
		ii_totalAnotacionesBng = ai_i;
	}

	/**
	 * Retorna el valor de total anotaciones bng.
	 *
	 * @return el valor de total anotaciones bng
	 */
	public int getTotalAnotacionesBng()
	{
		return ii_totalAnotacionesBng;
	}

	/**
	 * Método que al abrir el detalle de una matricula, carga la informacion de los dos lados de la pantalla dividida.
	 *
	 * @param as_matricula parametro con el cual se buscará información
	 * @param ai_row correspondiente al valor del tipo de objeto Integer
	 */
	public void accionLupa(String as_matricula, Integer ai_row)
	{
		try
		{
			if(StringUtils.isValidString(as_matricula))
			{
				Matricula lm_matricula;

				lm_matricula = Matricula.getMatricula(as_matricula);

				if(lm_matricula != null)
				{
					Turno lt_turno;

					lt_turno = ier_entregaRemote.findTurnoById(new Turno(getIdTurno()), getUserId());

					if(lt_turno != null)
					{
						setIdCirculo(lm_matricula.getCirculo());
						setIdMatricula(lm_matricula.getMatricula());
						setIdSolicitud(lt_turno.getIdSolicitud());
						setIdEtapa(NumericUtils.getLongWrapper(EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES));
						cargarInformacionPantallas(true);
						cargarInformacionPantallas(false);
						setTabActive(cs_indexDatosBasicos);
						setRowActive(ai_row);
						PrimeFaces.current().ajax().update("confrontacion:layoutDiferencias");
					}
					else
						throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método que hace el cambio de tab simultaneamente para los dos lados de la pantalla dividida.
	 *
	 * @param atce_event argumento del cual se extraerán los datos necesarios para llevar acabo la operación
	 */
	public void cambioTab(TabChangeEvent atce_event)
	{
		try
		{
			if(atce_event != null)
			{
				Tab lt_tab;

				lt_tab = atce_event.getTab();

				if(lt_tab != null)
				{
					String ls_tabId;

					ls_tabId = lt_tab.getId();

					if(StringUtils.isValidString(ls_tabId))
					{
						if(
						    ls_tabId.equalsIgnoreCase(cs_tabDatosBasicos)
							    || ls_tabId.equalsIgnoreCase(cs_tabChangeDatosBasicos)
						)
							setTabActive(cs_indexDatosBasicos);
						else if(
						    ls_tabId.equalsIgnoreCase(cs_tabDescripcion)
							    || ls_tabId.equalsIgnoreCase(cs_tabChangeDescripcion)
						)
							setTabActive(cs_indexDescripcionPredio);
						else if(
						    ls_tabId.equalsIgnoreCase(cs_tabAnotaciones)
							    || ls_tabId.equalsIgnoreCase(cs_tabChangeAnotaciones)
						)
						{
							setTabActive(cs_indexAnotaciones);
							onTabChange(atce_event);
						}
						else if(
						    ls_tabId.equalsIgnoreCase(cs_tabSalvedades)
							    || ls_tabId.equalsIgnoreCase(cs_tabChangeSalvedades)
						)
						{
							setTabActive(cs_indexSalvedades);
							onTabChange(atce_event);
						}
					}

					actualizarPantallaDividida();
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de limpiar la informacion del bean.
	 */
	public void clean()
	{
		setRenderizar(false);
		super.clean();
	}

	/**
	 * Método que consulta anotaciones de la tabla bng y acc dependiendo de donde se accione.
	 *
	 * @param aa_anotacion argumento anotacion del cual se extraeran datos necesarios para la consulta
	 * @param ab_desdeAcc argumento que indica si la accion fue desde la parte acc de la pantalla dividiva es decir el lado derecho de la misma
	 */
	public void consultarAnotacion(Anotacion aa_anotacion, boolean ab_desdeAcc)
	{
		try
		{
			if(aa_anotacion != null)
			{
				setAnotacion(null);
				setAnotacionBng(null);
				super.consultarAnotacion(aa_anotacion, ab_desdeAcc, true);

				{
					Collection<Anotacion> lca_anotaciones;

					lca_anotaciones = ab_desdeAcc ? getAnotacionesAgregadasBng() : getAnotacionesAgregadas();

					if(CollectionUtils.isValidCollection(lca_anotaciones))
					{
						Iterator<Anotacion> lidap_iterator;

						lidap_iterator = lca_anotaciones.iterator();

						if(lidap_iterator != null)
						{
							Anotacion la_anotacion;
							boolean   lb_encontro;

							la_anotacion     = null;
							lb_encontro      = false;

							while(lidap_iterator.hasNext() && !lb_encontro)
							{
								la_anotacion = lidap_iterator.next();

								if(la_anotacion != null)
								{
									if(la_anotacion.getIdAnotacion() == aa_anotacion.getIdAnotacion())
										lb_encontro = true;
								}
							}

							if(lb_encontro)
								super.consultarAnotacion(la_anotacion, !ab_desdeAcc, true);
							else
								addMessage("mensaje");
						}
					}
				}
			}

			actualizarPantallaDividida();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/** {@inheritdoc} */
	public void modificarInterviniente(Anotacion aa_anotacion)
	{
		modificarInterviniente(aa_anotacion, false);
	}

	/** {@inheritdoc} */
	public void modificarInterviniente(Anotacion aa_anotacion, boolean ab_bng)
	{
		super.modificarInterviniente(aa_anotacion, ab_bng);

		try
		{
			if(aa_anotacion != null)
			{
				setAnotacion(null);
				setAnotacionBng(null);
				super.consultarAnotacion(aa_anotacion, ab_bng, true);

				{
					Collection<Anotacion> lca_anotaciones;

					lca_anotaciones = ab_bng ? getIntervinientesAgregados() : getIntervinientesAgregadosBng();

					if(CollectionUtils.isValidCollection(lca_anotaciones))
					{
						Iterator<Anotacion> lidap_iterator;

						lidap_iterator = lca_anotaciones.iterator();

						if(lidap_iterator != null)
						{
							Anotacion la_anotacion;
							boolean   lb_encontro;

							la_anotacion     = null;
							lb_encontro      = false;

							while(lidap_iterator.hasNext() && !lb_encontro)
							{
								la_anotacion = lidap_iterator.next();

								if(la_anotacion != null)
								{
									if(la_anotacion.getIdAnotacion() == aa_anotacion.getIdAnotacion())
										lb_encontro = true;
								}
							}

							if(lb_encontro)
								super.modificarInterviniente(la_anotacion, !ab_bng);
							else
								addMessage("mensaje");
						}
					}
				}
			}

			actualizarPantallaDividida();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		actualizarPantallaDividida();
	}

	/**
	 * Método que se encarga de la accion de regresar para las dos pantallas de la pantalla dividida.
	 */
	public void regresarAnotacion()
	{
		if(isMostrarDetalleAnotaciones())
		{
			setMostrarDetalleAnotaciones(false);
			setMostrarBandejaAnotaciones(true);
		}
		else
		{
			setMostrarDetalle(false);
			setMostrarBandeja(true);
		}

		actualizarPantallaDividida();
	}

	/**
	 * Método encargado de actualizar el componente de la pantalla dividida de <code>DetalleActoInclude.xhtml</code>
	 */
	protected void actualizarPantallaDividida()
	{
		Integer li_row;

		li_row = getRowActive();

		PrimeFaces.current().ajax().update(calcularRutaFila(li_row, true));
		PrimeFaces.current().ajax().update(calcularRutaFila(li_row, false));
	}

	/**
	 * Método encargado de formar la cadena <code>String</code> de la ruta de acceso a la pantalla dividida.
	 * @param ai_fila fila del datatable en el cual estamos interactuando
	 * @param ab_izquierdo bandera que indica si la operacion debe hacerse para el lado izquierdo
	 * @return <code>String</code> con el resultado de la concatenación.
	 */
	protected String calcularRutaFila(Integer ai_fila, boolean ab_izquierdo)
	{
		StringBuilder lsb_builder;

		lsb_builder = new StringBuilder(cs_tableMatriculas);

		lsb_builder.append(IdentificadoresCommon.DOS_PUNTOS);
		lsb_builder.append(StringUtils.getString(ai_fila));
		lsb_builder.append(IdentificadoresCommon.DOS_PUNTOS);
		lsb_builder.append(ab_izquierdo ? cs_ladoIzquierdo : cs_ladoDerecho);

		return lsb_builder.toString();
	}

	/**
	 * Método que carga informacion de las tablas acc y bng para las dos partes de la pantalla.
	 *
	 * @param ab_bng argumento que indica si debe buscar en la bng  <code>true</code>Busca en bng<code>false</code>Busca en acc
	 */
	protected void cargarInformacionPantallas(boolean ab_bng)
	{
		try
		{
			ConsultaPredio lcp_cp;
			PredioRegistro lpr_apr;
			String         ls_circulo;
			Long           ll_matricula;
			String         ls_idSolicitud;

			lpr_apr            = new PredioRegistro();
			ls_circulo         = getIdCirculo();
			ll_matricula       = getIdMatricula();
			ls_idSolicitud     = getIdSolicitud();

			lpr_apr.setIdCirculo(ls_circulo);
			lpr_apr.setIdMatricula(NumericUtils.getLong(ll_matricula));
			lpr_apr.setConsultaPredio(true);
			lpr_apr.setEtapa(EtapaCommon.ID_ETAPA_RESPONSABLE_CORRECCIONES);

			{
				if(ab_bng)
					lcp_cp = icepe_consultaPredioRemote.findInfoTabsBng(lpr_apr, getUserId(), false, false, false);
				else
				{
					lpr_apr.setIdTipoPredio(IdentificadoresCommon.TEMPORAL);
					lpr_apr.setIdTurno(getIdTurno());
					lcp_cp = icepe_consultaPredioRemote.findInfoTabs(lpr_apr, getUserId(), true);
				}

				if(lcp_cp != null)
				{
					if(ab_bng)
					{
						setCirculoLinkBng(ls_circulo);
						setMatriculaLinkBng(ll_matricula);
						setTotalAnotacionesBng(lcp_cp.getTotalAnotaciones());
						setPredioRegistroBng(lcp_cp.getPredioRegistro());
						setDatosBasicosBng(lcp_cp.getDatosbasicos());
						setAntiguoSistemaDataBng(lcp_cp.getAntiguoSistemaData());
						setDetallesAntSistemaBng(lcp_cp.getDetallesAntSistema());
						setSalvedadesPredioBng(lcp_cp.getSalvedadesPredioBng());
					}
					else
					{
						setCirculoLink(ls_circulo);
						setMatriculaLink(ll_matricula);
						setTotalAnotaciones(lcp_cp.getTotalAnotaciones());
						setPredioRegistro(lcp_cp.getPredioRegistro());
						setDatosBasicos(lcp_cp.getDatosbasicos());
						setAntiguoSistemaData(lcp_cp.getAntiguoSistemaData());
						setDetallesAntSistema(lcp_cp.getDetallesAntSistema());
						setSalvedadesPredio(lcp_cp.getSalvedadesPredio());
					}

					{
						ConsultaPredio locp_datos;

						lcp_cp.setIdCirculo(ls_circulo);
						lcp_cp.setIdMatricula(ll_matricula);
						locp_datos = icepe_consultaPredioRemote.consultar(lcp_cp);

						if(locp_datos != null)
						{
							setEstadoPredio(StringUtils.getStringNotNull(locp_datos.getEstadoPredio()));
							setIdCirculo(ab_bng ? getCirculoLinkBng() : getCirculoLink());
							setIdMatricula(ab_bng ? getMatriculaLinkBng() : getMatriculaLink());
						}
					}

					{
						LinderoPredio         lalp_lp;
						ComplementacionPredio lacp_cp;
						CabidaLinderos        lcl_cl;

						if(ab_bng)
							lcl_cl = getCabidaLinderosBng();
						else
							lcl_cl = getCabidaLinderos();

						lacp_cp     = lcp_cp.getComplementacionPredio();
						lalp_lp     = lcp_cp.getLinderoPredio();

						if(lacp_cp != null)
						{
							Long ll_complementacionOriginal;

							ll_complementacionOriginal = NumericUtils.getLongWrapper(lacp_cp.getIdComplementacion());

							if(NumericUtils.isValidLong(ll_complementacionOriginal))
							{
								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.COPIAR);
								lcl_cl.getComplementacion().setComplementacion(lacp_cp.getComplementacion());
								lcl_cl.getComplementacionPredio()
									      .setIdComplementacion(
									    String.valueOf(NumericUtils.getLong(lacp_cp.getIdComplementacion()))
									);
							}
							else
								lcl_cl.setTipoComplementacion(TipoComplementacionCommon.NUEVA);
						}

						if(lalp_lp != null)
							lcl_cl.setLinderos(lalp_lp.getLindero());
					}

					if(ab_bng)
					{
						setAreaUIBng(lcp_cp.getAreaUI());
						setAreaPredioBng(lcp_cp.getAreaPredio());
						setDireccionesPredioBng(lcp_cp.getDireccionesDelPredio());
						setAnotacionBng(lcp_cp.getAnotacion());
						setDireccionesTemporalesBng(getDireccionesPredio());
						setPrediosSegregadosBng(lcp_cp.getPrediosSegregados());
						setAlertaNaturalezaJuridicaBng(lcp_cp.getInfoAlertas());
						getDocumentoCriterios().setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						setSalvedadesBng(lcp_cp.getSalvedades());
					}
					else
					{
						setAreaUI(lcp_cp.getAreaUI());
						setAreaPredio(lcp_cp.getAreaPredio());
						setDireccionesPredio(lcp_cp.getDireccionesDelPredio());
						setAnotacion(lcp_cp.getAnotacion());
						setDireccionesTemporales(getDireccionesPredio());
						setPrediosSegregados(lcp_cp.getPrediosSegregados());
						setAlertaNaturalezaJuridica(lcp_cp.getInfoAlertas());
						getDocumentoCriterios().setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
						setSalvedades(lcp_cp.getSalvedades());
					}

					{
						Anotacion la_anotacion;

						if(ab_bng)
							la_anotacion = getAnotacionBng();
						else
							la_anotacion = getAnotacion();

						if(la_anotacion != null)
						{
							Collection<Anotacion> lca_anotacionesAgregadas;

							lca_anotacionesAgregadas = la_anotacion.getAnotacionesAgregadas();

							setDatosAntSistemaAnotacion(la_anotacion.getDatosAntiguoSistema());

							if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
							{
								Collection<DetalleAntSistema> lcdas_detalles;

								if(ab_bng)
									lcdas_detalles = getDetallesAntSistemaBng();
								else
									lcdas_detalles = getDetallesAntSistema();

								for(Anotacion la_iterador : lca_anotacionesAgregadas)
								{
									if(la_iterador != null)
										la_iterador.setDetallesAntSistema(lcdas_detalles);
								}

								if(ab_bng)
									setAnotacionesAgregadasBng(lca_anotacionesAgregadas);
								else
									setAnotacionesAgregadas(lca_anotacionesAgregadas);
							}

							cargarTiposOficinaAnotaciones(EstadoCommon.D);
						}

						if(!ab_bng)
							cargarCheks(ls_circulo, ll_matricula, ls_idSolicitud);

						setMostrarBandejaAnotaciones(true);
						setMostrarDetalleAnotaciones(false);
					}
				}
				else
					throw new B2BException(ErrorKeys.ERROR_CARGUE_MATRICULA);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}
}
