package com.bachue.snr.prosnr01.web.bean.consulta.reportes;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDatoCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.reportes.ConsultaReportesRemote;

import com.bachue.snr.prosnr01.model.sdb.col.InteresadoDocumentoTipo;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.Consultas;
import com.bachue.snr.prosnr01.model.sdb.pgn.ResultadoConsulta;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import org.primefaces.component.calendar.Calendar;

import org.primefaces.component.commandbutton.CommandButton;

import org.primefaces.component.panel.Panel;

import org.primefaces.component.panelgrid.PanelGrid;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y acciones de BeanConsultaReportes.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanConsultaReportes")
public class BeanConsultaReportes extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaReportes.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3049638354872673934L;

	/** Propiedad icr consulta reportes remote. */
	@EJB
	private ConsultaReportesRemote icr_consultaReportesRemote;

	/** Propiedad iop data panel. */
	private Panel iop_dataPanel;

	/** Propiedad isc imagen. */
	private StreamedContent isc_imagen;

	/** Propiedad ib render. */
	private boolean ib_render;

	/** Propiedad il tipo reporte. */
	private long il_tipoReporte;

	/**
	 * Retorna el valor del objeto de Object.
	 *
	 * @param clase correspondiente al valor del tipo de objeto String
	 * @param metodoLlamado correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de Object
	 * @throws ClassNotFoundException Señala que se ha producido una excepción
	 * @throws InstantiationException Señala que se ha producido una excepción
	 * @throws IllegalAccessException Señala que se ha producido una excepción
	 * @throws SecurityException Señala que se ha producido una excepción
	 * @throws NoSuchMethodException Señala que se ha producido una excepción
	 * @throws IllegalArgumentException Señala que se ha producido una excepción
	 * @throws InvocationTargetException Señala que se ha producido una excepción
	 */
	public static Object cargaDinamica(String clase, String metodoLlamado)
	    throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException,
		    NoSuchMethodException, IllegalArgumentException, InvocationTargetException
	{
		Object tempClass = Class.forName(clase).newInstance();

		Class  claseCargada = tempClass.getClass();

		//Firma del metodo.
		Class[] argumentos = new Class[1];
		argumentos[0] = String.class;

		//Busqueda del metodo a ejecutar
		Method metodo = claseCargada.getDeclaredMethod(metodoLlamado);
		Object lo_o;
		//Ejecucion del m?todo pasandole la clase de este y los parametros.
		lo_o          = metodo.invoke(tempClass);

		return lo_o;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de data panel.
	 *
	 * @param apg_pg asigna el valor a la propiedad data panel
	 */
	public void setDataPanel(Panel apg_pg)
	{
		iop_dataPanel = apg_pg;
	}

	/**
	 * Retorna el valor de data panel.
	 *
	 * @return el valor de data panel
	 */
	public Panel getDataPanel()
	{
		return iop_dataPanel;
	}

	/**
	 * Modifica el valor de imagen.
	 *
	 * @param asc_sc asigna el valor a la propiedad imagen
	 */
	public void setImagen(StreamedContent asc_sc)
	{
		isc_imagen = asc_sc;
	}

	/**
	 * Retorna el valor de imagen.
	 *
	 * @return el valor de imagen
	 */
	public StreamedContent getImagen()
	{
		return isc_imagen;
	}

	/**
	 * Modifica el valor de render.
	 *
	 * @param ab_b asigna el valor a la propiedad render
	 */
	public void setRender(boolean ab_b)
	{
		ib_render = ab_b;
	}

	/**
	 * Valida la propiedad render.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en render
	 */
	public boolean isRender()
	{
		return ib_render;
	}

	/**
	 * Modifica el valor de tipo reporte.
	 *
	 * @param al_l asigna el valor a la propiedad tipo reporte
	 */
	public void setTipoReporte(long al_l)
	{
		il_tipoReporte = al_l;
	}

	/**
	 * Retorna el valor de tipo reporte.
	 *
	 * @return el valor de tipo reporte
	 */
	public long getTipoReporte()
	{
		return il_tipoReporte;
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param aocc_cc correspondiente al valor del tipo de objeto CamposConsulta
	 * @param al_tipoReporte correspondiente al valor del tipo de objeto long
	 * @return devuelve el valor de Collection
	 */
	public Collection<UIComponent> camposDinamicos(CamposConsulta aocc_cc, long al_tipoReporte)
	{
		String                  ls_tipoCampo;
		boolean                 lb_obligaorio;
		String                  ls_nombreCampo;
		long                    id_campo;
		Collection<UIComponent> lcuic_components;
		String                  ls_id;
		String                  ls_style;
		String                  ls_idObligatorio;

		lcuic_components     = new ArrayList<UIComponent>();
		lb_obligaorio        = false;
		ls_style             = "font-weight: bold;font-size: 13px;";
		ls_idObligatorio     = "*";

		if((aocc_cc != null) && (al_tipoReporte > 0))
		{
			ls_tipoCampo      = StringUtils.getStringNotNull(aocc_cc.getTipoCampo());
			lb_obligaorio     = aocc_cc.isObligatoriedad();
			id_campo          = NumericUtils.getLong(aocc_cc.getIdCampo());

			ls_id = "TR" + al_tipoReporte + "IC" + id_campo;

			if(!lb_obligaorio)
				ls_idObligatorio = "";

			ls_nombreCampo = StringUtils.getStringNotNull(aocc_cc.getNombreCampo() + ls_idObligatorio);

			if(
			    ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.TEXTO)
				    || ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.ENTERO)
			)
			{
				HtmlOutputText lhot_label = new HtmlOutputText();
				lhot_label.setEscape(false);
				lhot_label.setStyle(ls_style);
				lhot_label.setValue(ls_nombreCampo);

				lcuic_components.add(lhot_label);

				HtmlInputText lit_inputText = new HtmlInputText();
				lit_inputText.setId(ls_id);
				lit_inputText.setMaxlength(40);

				if(ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.TEXTO))
					lit_inputText.setOnblur("value=value.toUpperCase();");

				if(ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.ENTERO))
					lit_inputText.setOnkeypress("return soloNumeros(event);");

				lcuic_components.add(lit_inputText);
			}

			else if(ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.FECHA))
			{
				HtmlOutputText lhot_tittle = new HtmlOutputText();
				lhot_tittle.setEscape(false);
				lhot_tittle.setStyle(ls_style);
				lhot_tittle.setValue(ls_nombreCampo);

				lcuic_components.add(lhot_tittle);

				Calendar loc_calendar = new Calendar();
				loc_calendar.setPattern(FormatoFechaCommon.DIA_MES_ANIO);
				loc_calendar.setShowOn("button");
				loc_calendar.setLocale(new Locale("es"));
				loc_calendar.setId(ls_id);
				loc_calendar.setPlaceholder(FormatoFechaCommon.DIA_MES_ANIO);
				loc_calendar.setMaxlength(10);
				loc_calendar.setOnkeydown("formatDate(this,event);");

				lcuic_components.add(loc_calendar);
			}
			else if(ls_tipoCampo.equalsIgnoreCase(TipoDatoCommon.MULTIVALOR))
			{
			}
		}

		return lcuic_components;
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setDataPanel(null);
		setRender(false);
		setImagen(null);
		setTipoReporte(-1);

		ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();

		try
		{
			lec_ec.redirect(((HttpServletRequest)lec_ec.getRequest()).getRequestURI());
		}
		catch(IOException le_e)
		{
			clh_LOGGER.error("clean", le_e);
		}
	}

	/**
	 * Consulta reporte.
	 *
	 * @param aui_childremPanel correspondiente al valor del tipo de objeto List<UIComponent>
	 * @param as_idPanel correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultaReporte(List<UIComponent> aui_childremPanel, String as_idPanel)
	    throws B2BException
	{
		String                     ls_valor;
		Collection<CamposConsulta> lccc_dataReporte;
		String                     ls_id;
		CamposConsulta             locc_tmp;
		String                     ls_idCampo;
		String                     ls_idConsulta;
		int                        li_idConsulta;
		long                       ll_tipoReporte;
		int                        li_idCampo;
		ResultadoConsulta          lorc_rc;

		lccc_dataReporte     = new ArrayList<CamposConsulta>();
		ls_valor             = null;
		ll_tipoReporte       = 0;

		try
		{
			for(UIComponent lui_childPanel : aui_childremPanel)
			{
				if(lui_childPanel instanceof UIInput)
				{
					HtmlInputText loi_oi = (HtmlInputText)lui_childPanel;

					if(loi_oi.getValue() != null)
						ls_valor = loi_oi.getValue().toString();

					ls_id        = lui_childPanel.getId();
					locc_tmp     = new CamposConsulta();

					if(StringUtils.isValidString(ls_valor))
						locc_tmp.setValorCampo(ls_valor);

					if(StringUtils.isValidString(ls_id))
					{
						li_idConsulta     = ls_id.indexOf("TR");
						li_idCampo        = ls_id.indexOf("IC");

						if((li_idConsulta > -1) && (li_idCampo > -1))
						{
							ls_idConsulta     = ls_id.substring(li_idConsulta + 2, li_idCampo);
							ls_idCampo        = ls_id.substring(li_idCampo + 2);

							if(StringUtils.isValidString(ls_idCampo) && StringUtils.isValidString(ls_idConsulta))
							{
								locc_tmp.setIdConsulta(NumericUtils.getLongWrapper(ls_idConsulta));
								ll_tipoReporte = NumericUtils.getLong(locc_tmp.getIdConsulta());
								locc_tmp.setIdCampo(NumericUtils.getLongWrapper(ls_idCampo));

								lccc_dataReporte.add(locc_tmp);
							}
						}
					}
				}
			}

			locc_tmp = new CamposConsulta();
			locc_tmp.setIdUsuarioCreacion(getUserId());
			locc_tmp.setIpCreacion(getRemoteIpAddress());
			locc_tmp.setDataCamposConsulta(lccc_dataReporte);
			locc_tmp.setIdConsulta(NumericUtils.getLongWrapper(ll_tipoReporte));

			lorc_rc = icr_consultaReportesRemote.generacionReporte(locc_tmp);

			if(lorc_rc != null)
			{
				InputStream stream = new ByteArrayInputStream(lorc_rc.getResultadoBlob());
				isc_imagen = new DefaultStreamedContent(
					    stream, TipoContenidoCommon.XLS_XLSX_XXLS, "RESULTADO_CONSULTA.XLSX"
					);
			}
			else
			{
				setImagen(null);

				throw new B2BException("Sin registros consultados.");
			}
		}
		catch(B2BException lbe_lbe)
		{
			addMessage(lbe_lbe);
			PrimeFaces.current().ajax().update("fConsultaReportes:globalMsg");
		}
	}

	/**
	 * Iniciar.
	 */
	public void iniciar()
	{
		iop_dataPanel = null;
		setRender(false);
	}

	/**
	 * Mostrar panel.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void mostrarPanel()
	    throws B2BException
	{
		long                    ll_tipoReporte;
		CamposConsulta          locc_cc;
		Consultas               loc_dataConsulta;
		Collection<UIComponent> lcuic_components;

		lcuic_components     = new ArrayList<UIComponent>();
		ll_tipoReporte       = getTipoReporte();
		locc_cc              = new CamposConsulta();

		if(ll_tipoReporte > 0)
		{
			loc_dataConsulta = new Consultas();

			Object lo_o;

			try
			{
				lo_o = cargaDinamica(
					    "com.bachue.snr.prosnr01.web.bean.reference.BeanReference", "findTipoDocumentoActivo"
					);

				if((lo_o != null) && lo_o instanceof Collection)
				{
					Collection lc_c;
					lc_c = (Collection)lo_o;

					for(Object lo : lc_c)
					{
						if(lo != null)
						{
							if(lo instanceof InteresadoDocumentoTipo)
							{
								InteresadoDocumentoTipo ltmoo;
								ltmoo = (InteresadoDocumentoTipo)lo;

								if(ltmoo != null)
								{
								}
							}
						}
					}
				}
			}
			catch(Exception le_e)
			{
				throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
			}

			Panel lcpp_cp = new Panel();
			lcpp_cp.setHeader("CONSULTA REPORTES");

			PanelGrid lcp_cp = new PanelGrid();
			lcp_cp.setId("IDPANEL" + ll_tipoReporte);
			lcp_cp.setStyle("height:100px; width:400px");
			lcp_cp.setColumns(4);

			loc_dataConsulta.setIdConsulta(ll_tipoReporte);

			locc_cc = icr_consultaReportesRemote.findCamposReportes(loc_dataConsulta);

			if(locc_cc != null)
			{
				Collection<CamposConsulta> lccc_dataCampos;
				lccc_dataCampos = locc_cc.getDataCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_dataCampos))
				{
					for(CamposConsulta locc_tmp : lccc_dataCampos)
					{
						if(locc_tmp != null)
						{
							lcuic_components = camposDinamicos(locc_tmp, ll_tipoReporte);

							if(CollectionUtils.isValidCollection(lcuic_components))
							{
								for(UIComponent louic_tmp : lcuic_components)
									if(louic_tmp != null)
										lcp_cp.getChildren().add(louic_tmp);

								lcuic_components = new ArrayList<UIComponent>();
							}
						}
					}

					CommandButton lohmb_button = new CommandButton();
					lohmb_button.setAjax(false);
					lohmb_button.setValue("Consulta reporte");
					lohmb_button.setUpdate("fConsultaReportes");

					((UICommand)lohmb_button).addActionListener(
					    new ActionListener()
						{
							@Override
							public void processAction(ActionEvent aae_ae)
							{
								List<UIComponent> lui_ui;
								String            ls_idPanel;

								HtmlCommandButton lhcb_cb;
								lhcb_cb     = (HtmlCommandButton)aae_ae.getSource();

								lui_ui = lhcb_cb.getParent().getChildren();

								for(UIComponent lui_childPanel : lui_ui)
								{
									if(lui_childPanel instanceof PanelGrid)
									{
										PanelGrid loi_oi = (PanelGrid)lui_childPanel;

										lui_ui = loi_oi.getChildren();

										break;
									}
								}

								ls_idPanel = lhcb_cb.getParent().getId();

								try
								{
									consultaReporte(lui_ui, ls_idPanel);
								}
								catch(B2BException lbe_lbe)
								{
									addMessage(lbe_lbe);
									PrimeFaces.current().ajax().update("fConsultaReportes:globalMsg");
								}
							}
						}
					);

					lcpp_cp.getChildren().add(lcp_cp);
					lcpp_cp.getChildren().add(lohmb_button);
				}
			}

			setDataPanel(lcpp_cp);
			setRender(true);
		}
		else
		{
			setDataPanel(null);
			setRender(false);
		}

		ExternalContext lec_ec = FacesContext.getCurrentInstance().getExternalContext();

		try
		{
			lec_ec.redirect(((HttpServletRequest)lec_ec.getRequest()).getRequestURI());
		}
		catch(IOException le_e)
		{
			clh_LOGGER.error("mostrarPanel", le_e);
		}
	}
}
