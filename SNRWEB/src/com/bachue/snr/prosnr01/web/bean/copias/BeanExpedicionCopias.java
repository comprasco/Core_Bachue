package com.bachue.snr.prosnr01.web.bean.copias;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.CampoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoConsultaCommon;
import com.bachue.snr.prosnr01.common.constants.SubProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDatoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoDocumentalCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.digitalizacion.DigitalizacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion.LiquidacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;
import com.bachue.snr.prosnr01.model.registro.TipoDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;
import com.bachue.snr.prosnr01.model.sdb.pgn.AnioAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;
import com.bachue.snr.prosnr01.model.sdb.pgn.CriteriosDeBusqueda;
import com.bachue.snr.prosnr01.model.sdb.pgn.FolioAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoPartidaAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.pgn.TomoAntiguoSistema;

import com.bachue.snr.prosnr01.web.bean.consulta.reportes.BeanConsultaIndices;
import com.bachue.snr.prosnr01.web.util.UIUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanExpedicionCopias.
 *
 * @author hcastaneda
 *
 */
@SessionScoped
@ManagedBean(name = "beanExpedicionCopias")
public class BeanExpedicionCopias extends BeanConsultaIndices implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7203414447319969041L;

	/** Constante cs_ID_FORM. */
	public static final String cs_ID_FORM = "fExpedicionCopias";

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":globalMsg";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanExpedicionCopias.class);

	/** Propiedad icl liquidacion item. */
	private Collection<Liquidacion> icl_liquidacionItem;

	/** Propiedad idr digitalizacion remote. */
	@EJB
	private DigitalizacionRemote idr_digitalizacionRemote;

	/** Propiedad irr entrega remote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad ilr liquidacion remote. */
	@EJB
	private LiquidacionRemote ilr_liquidacionRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is observaciones solicitud. */
	private String is_observacionesSolicitud;

	/** Propiedad is tipo criterio seleccionado. */
	private String is_tipoCriterioSeleccionado;

	/** Propiedad ib bloqueo siguiente. */
	private boolean ib_bloqueoSiguiente;

	/** Propiedad ib consultado. */
	private boolean ib_consultado;

	/** Propiedad ib_deshabilitarCampoCriterioBusqueda */
	private boolean ib_deshabilitarCampoCriterioBusqueda;

	/** Propiedad ib mostrar observaciones. */
	private boolean ib_mostrarObservaciones;

	/** Propiedad ib sin información. */
	private boolean ib_sinInformacion;

	/** Propiedad ib solicitud copias insertadas. */
	private boolean ib_solcitudCopiasInsertadas;

	/**
	 * Modifica el valor de consultado.
	 *
	 * @param ab_b asigna el valor a la propiedad consultado
	 */
	public void setConsultado(boolean ab_b)
	{
		ib_consultado = ab_b;
	}

	/**
	 * Retorna el valor de consultado.
	 *
	 * @return el valor de consultado
	 */
	public boolean isConsultado()
	{
		return ib_consultado;
	}

	/**
	 * Modifica el valor de la propiedad.
	 *
	 * @param ab_b asigna el valor a la propiedad
	 */
	public void setDeshabilitarCampoCriterioBusqueda(boolean ab_b)
	{
		ib_deshabilitarCampoCriterioBusqueda = ab_b;
	}

	/**
	 * Retorna el valor de la propiedad.
	 *
	 * @return el valor de la propiedad
	 */
	public boolean isDeshabilitarCampoCriterioBusqueda()
	{
		return ib_deshabilitarCampoCriterioBusqueda;
	}

	/**
	 * Modifica el valor de liquidacion item.
	 *
	 * @param acl_cl asigna el valor a la propiedad liquidacion item
	 */
	public void setLiquidacionItem(Collection<Liquidacion> acl_cl)
	{
		icl_liquidacionItem = acl_cl;
	}

	/**
	 * Retorna el valor de liquidacion item.
	 *
	 * @return el valor de liquidacion item
	 */
	public Collection<Liquidacion> getLiquidacionItem()
	{
		return icl_liquidacionItem;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_mostrarObservaciones por ib_mostrarObservaciones
	 */
	public void setMostrarObservaciones(boolean ab_mo)
	{
		ib_mostrarObservaciones = ab_mo;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_mostrarObservaciones
	 */
	public boolean isMostrarObservaciones()
	{
		return ib_mostrarObservaciones;
	}

	/**
	 * @param Modifica el valor de la propiedad is_observacionesSolicitud por is_observacionesSolicitud
	 */
	public void setObservacionesSolicitud(String ab_os)
	{
		is_observacionesSolicitud = ab_os;
	}

	/**
	 * @return Retorna el valor de la propiedad is_observacionesSolicitud
	 */
	public String getObservacionesSolicitud()
	{
		return is_observacionesSolicitud;
	}

	/**
	 * Modifica el valor de consultado.
	 *
	 * @param ab_b asigna el valor a la propiedad consultado
	 */
	public void setSinInformacion(boolean ab_b)
	{
		ib_sinInformacion = ab_b;
	}

	/**
	 * Retorna el valor de consultado.
	 *
	 * @return el valor de consultado
	 */
	public boolean isSinInformacion()
	{
		return ib_sinInformacion;
	}

	/**
	 * Metodo que asigna el valor a la propiedad.
	 * @param ab_b Valor a asignar.
	 */
	public void setSolcitudCopiasInsertadas(boolean ab_b)
	{
		ib_solcitudCopiasInsertadas = ab_b;
	}

	/** Metodo que trae el valor de la propiedad. */
	public boolean isSolcitudCopiasInsertadas()
	{
		return ib_solcitudCopiasInsertadas;
	}

	/**
	 * Metodo que asigna el valor a la propiedad.
	 * @param as_s Valor a asignar.
	 */
	public void setTipoCriterioSeleccionado(String as_s)
	{
		is_tipoCriterioSeleccionado = as_s;
	}

	/** Metodo que trae el valor de la propiedad. */
	public String getTipoCriterioSeleccionado()
	{
		return is_tipoCriterioSeleccionado;
	}

	/**
	 * Metodo encargado de cargar los campos de criterio de búsqueda para un tipo criterio búsqueda.
	 */
	public void cargarCampoCriterioBusqueda()
	{
		try
		{
			String ls_idTipoCriterioBusqueda;

			ls_idTipoCriterioBusqueda = StringUtils.getStringNotNull(getTipoCriterioSeleccionado());

			if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
			{
				Collection<CriteriosDeBusqueda> lccdb_criterios;
				boolean                         lb_antiguoSistemaSeleccionado;

				lccdb_criterios                   = getCriterios();
				lb_antiguoSistemaSeleccionado     = ls_idTipoCriterioBusqueda.equalsIgnoreCase(
					    TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
					);

				if(CollectionUtils.isValidCollection(lccdb_criterios))
				{
					for(CriteriosDeBusqueda lcdb_iterador : lccdb_criterios)
					{
						if(lcdb_iterador != null)
						{
							String  ls_idTipoCriterioIterado;
							boolean lb_seleccionado;

							ls_idTipoCriterioIterado     = StringUtils.getStringNotNull(
								    lcdb_iterador.getIdTipoCriterio()
								);
							lb_seleccionado              = ls_idTipoCriterioIterado.equalsIgnoreCase(
								    ls_idTipoCriterioBusqueda
								)
									|| (lb_antiguoSistemaSeleccionado
									&& ls_idTipoCriterioIterado.equalsIgnoreCase(
									    TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
									));

							lcdb_iterador.setSeleccion(lb_seleccionado);
						}
					}

					{
						CriteriosDeBusqueda lcb_citeriosConsulta;

						lcb_citeriosConsulta = getCriteriosDeConsulta();

						if(lcb_citeriosConsulta != null)
						{
							String ls_tipoConsulta;

							ls_tipoConsulta = lcb_citeriosConsulta.getTipoConsulta();

							validateStyles(
							    cs_ID_FORM + ":idSOMTipoConsulta", FacesContext.getCurrentInstance(), ls_tipoConsulta,
							    true
							);

							if(!StringUtils.isValidString(ls_tipoConsulta))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_SOLICITUD_COPIAS);

							{
								Collection<CriteriosDeBusqueda> lc_criteriosDeBusqueda;

								lc_criteriosDeBusqueda = getCriterios();

								if(CollectionUtils.isValidCollection(lc_criteriosDeBusqueda))
								{
									lcb_citeriosConsulta.setCopias(true);
									lcb_citeriosConsulta.setIdProcesoConsulta(
									    SubProcesoCommon.COPIAS_COPIA_MEDIO_FISICO
									);
									lcb_citeriosConsulta.setSinCantidad(true);
									lcb_citeriosConsulta.setCriteriosSeleccionados(lc_criteriosDeBusqueda);
									lcb_citeriosConsulta.setDatosSolicitud(getSolicitud());

									irr_registroRemote.guardarCriterios(
									    lcb_citeriosConsulta, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
									);

									mostrarPaneles(null);
									setDocumentosOWCC(null);
								}
							}
						}
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarCampoCriterioBusqueda", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar excel para agregar valoreas a detalle criterio busqueda.
	 *
	 * @param afue_event Argumento de tipo FileUploadEvent que contiene el archivo cargado
	 * y los datos necesarios para realizar la inserción de detalle criterio busqueda.
	 * @return Variable de tipo String para volver a la pantalla sin afectar valores
	 * precargados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String cargarExcelCamposCriteriosCopias(FileUploadEvent afue_event)
	    throws B2BException
	{
		if(afue_event != null)
		{
			try
			{
				cargarExcelCamposCriterios(afue_event, true);
				setConsultado(true);
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriteriosCopias", lb2be_e);
				addMessage(lb2be_e);
			}
			catch(Exception le_e)
			{
				clh_LOGGER.error("cargarExcelCamposCriteriosCopias", le_e);
				addMessage(le_e.getMessage());
			}
			finally
			{
				actualizarComponente(cs_ID_GROWL);
			}
		}

		return null;
	}

	/**
	 * Cargar nir
	 *
	 * @param acc_panel correspondiente al valor del tipo de objeto CamposConsulta
	 */
	public void cargarNir(CamposConsulta acc_panel)
	{
		try
		{
			boolean lb_deshabilitarCampoCriterioBusqueda;

			lb_deshabilitarCampoCriterioBusqueda = false;

			if(acc_panel != null)
			{
				Collection<CamposConsulta> lccc_dataCamposConsulta;

				lccc_dataCamposConsulta = acc_panel.getDataCamposConsulta();

				if(CollectionUtils.isValidCollection(lccc_dataCamposConsulta))
				{
					String ls_nir;

					ls_nir = irr_registroRemote.cargarNir(
						    acc_panel, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					{
						Iterator<CamposConsulta> licc_iterator;
						boolean                  lb_encontrado;

						licc_iterator     = lccc_dataCamposConsulta.iterator();
						lb_encontrado     = false;

						while(licc_iterator.hasNext() && !lb_encontrado)
						{
							CamposConsulta lcc_iterador;

							lcc_iterador = licc_iterator.next();

							if(lcc_iterador != null)
							{
								String ls_idTipoCriterioBusqueda;

								ls_idTipoCriterioBusqueda = lcc_iterador.getIdTipoCriterioBusqueda();

								if(StringUtils.isValidString(ls_idTipoCriterioBusqueda))
								{
									if(ls_idTipoCriterioBusqueda.equalsIgnoreCase(TipoCriterioBusquedaCommon.TURNO))
									{
										String ls_idCampoCriterioBusqueda;

										ls_idCampoCriterioBusqueda     = lcc_iterador.getIdCampoCriterioBusqueda();

										lb_encontrado = StringUtils.isValidString(ls_idCampoCriterioBusqueda)
												&& ls_idCampoCriterioBusqueda.equalsIgnoreCase(
												    CampoCriterioBusquedaCommon.TURNO_NIR
												);

										if(lb_encontrado)
										{
											lb_deshabilitarCampoCriterioBusqueda = StringUtils.isValidString(ls_nir);
											lcc_iterador.setValorCampo(ls_nir);

											if(!StringUtils.isValidString(ls_nir))
												throw new B2BException(ErrorKeys.TURNO_SIN_NIR_ASOCIADO);
										}
									}
								}
							}
						}
					}
				}
			}

			setDeshabilitarCampoCriterioBusqueda(lb_deshabilitarCampoCriterioBusqueda);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarNir", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar los tipos de criterio búsqueda para copias.
	 */
	public void cargarTipoCriterioBusquedaCopias()
	{
		try
		{
			CriteriosDeBusqueda lcdb_criteriosDeBusqueda;
			Solicitud           ls_solicitud;

			String  ls_entidadExenta;
			String  ls_idTipoCriterioBusqueda;
			boolean lb_entidadExenta;

			ls_solicitud     = getSolicitud();

			ls_entidadExenta     = ls_solicitud.getEntidadExenta();
			lb_entidadExenta     = StringUtils.isValidString(ls_entidadExenta)
					&& ls_entidadExenta.equalsIgnoreCase(EstadoCommon.S);

			lcdb_criteriosDeBusqueda      = getCriteriosDeConsulta();
			ls_idTipoCriterioBusqueda     = (lcdb_criteriosDeBusqueda != null)
				? (lb_entidadExenta ? EstadoCommon.MASIVO : lcdb_criteriosDeBusqueda.getTipoConsulta()) : null;

			listarTiposCriteriosBusqueda(
			    ls_idTipoCriterioBusqueda, ProcesoCommon.ID_PROCESO_5, ProcesoConsultaCommon.ID_PROCESO_CONSULTA_11
			);

			setTipoCriterioSeleccionado(null);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarTipoCriterioBusquedaCopias", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de agregar criterios al detalle de criterios.
	 *
	 * @param acc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a agregar.
	 * @throws B2BException Señala que se produjo una excepción
	 */
	public void consultarCriterio()
	    throws B2BException
	{
		consultarCriterio(true);
	}

	/**
	 * Metodo encargado de agregar criterios al detalle de criterios.
	 *
	 * @param acc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a agregar.
	 * @param ab_guardarCriterios Argumento de tipo boolean que determina si se deben guardar criterios o no.
	 * @throws B2BException Señala que se produjo una excepción
	 */
	public void consultarCriterio(boolean ab_guardarCriterios)
	    throws B2BException
	{
		consultarCriterio(ab_guardarCriterios, false);
	}

	/**
	 * Metodo encargado de agregar criterios al detalle de criterios.
	 *
	 * @param acc_camposConsulta Argumento de tipo CamposConsulta que contiene los criterios a agregar.
	 * @param ab_guardarCriterios Argumento de tipo boolean que determina si se deben guardar criterios o no.
	 * @param ab_traerSolicitudCopias Argumento de tipo boolean que determina si se deben traer datos de solicitud copias o no.
	 * @throws B2BException Señala que se produjo una excepción
	 */
	public void consultarCriterio(boolean ab_guardarCriterios, boolean ab_traerSolicitudCopias)
	    throws B2BException
	{
		try
		{
			Collection<CamposConsulta> lccc_camposConsulta;

			lccc_camposConsulta = getCamposConsulta();

			if(CollectionUtils.isValidCollection(lccc_camposConsulta))
			{
				for(CamposConsulta lcc_iterador : lccc_camposConsulta)
				{
					if(lcc_iterador != null)
					{
						Collection<CamposConsulta> lccc_dataCamposConsulta;

						lccc_dataCamposConsulta = lcc_iterador.getDataCamposConsulta();

						if(CollectionUtils.isValidCollection(lccc_dataCamposConsulta))
						{
							for(CamposConsulta lcc_campoPantalla : lccc_dataCamposConsulta)
							{
								if((lcc_campoPantalla != null) && lcc_campoPantalla.isObligatoriedad())
								{
									String ls_tipoDato;

									ls_tipoDato = lcc_campoPantalla.getTipoDato();

									if(StringUtils.isValidString(ls_tipoDato))
									{
										String ls_valor;

										ls_valor = ls_tipoDato.equalsIgnoreCase(TipoDatoCommon.FECHA)
											? StringUtils.getString(
											    lcc_campoPantalla.getValorCampoFecha(), FormatoFechaCommon.DIA_MES_ANIO
											) : lcc_campoPantalla.getValorCampo();

										lcc_campoPantalla.setRojoPantalla(!StringUtils.isValidString(ls_valor));
									}
								}
							}
						}
					}
				}

				{
					CamposConsulta lcc_camposConsulta;

					lcc_camposConsulta = irr_registroRemote.guardarCriteriosYConsultarCopias(
						    lccc_camposConsulta, getSolicitud(), ab_guardarCriterios, ab_traerSolicitudCopias,
						    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
						);

					if(lcc_camposConsulta != null)
					{
						Collection<DocumentoOWCC>              lcdo_documentosOWCC;
						Collection<DataReproduccionConstancia> lcdrc_dataReprodConstancia;

						lcdrc_dataReprodConstancia     = lcc_camposConsulta.getDataReproduccionConstancia();
						lcdo_documentosOWCC            = lcc_camposConsulta.getDocumentosOWCC();

						setDocumentosOWCC(lcdo_documentosOWCC);
						setDataReproduccionConstancia(ab_guardarCriterios ? lcdrc_dataReprodConstancia : null);

						if(
						    !CollectionUtils.isValidCollection(lcdo_documentosOWCC)
							    && !CollectionUtils.isValidCollection(lcdrc_dataReprodConstancia)
						)
						{
							ib_bloqueoSiguiente = false;
							addMessage("W", getErrorMessages().getString(ErrorKeys.ERROR_CONSULTA_SIN_RESULTADOS));
						}
						else
							ib_bloqueoSiguiente = false;
					}
					else
					{
						ib_bloqueoSiguiente = true;
						throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS_CONSULTA);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarCriterio", lb2be_e);

			addMessage(lb2be_e);

			setDocumentosOWCC(null);
			setDataReproduccionConstancia(null);
			ib_bloqueoSiguiente = true;
		}
		finally
		{
			setConsultado(true);
			actualizarComponente(cs_ID_FORM);
		}
	}

	/**
	 * Retorna el valor de Anio.
	 *
	 * @return el valor de Anio
	 */
	public Collection<AnioAntiguoSistema> findAnioAntiguoSistema()
	{
		Collection<AnioAntiguoSistema> lcaas_AnioAntSistema;

		lcaas_AnioAntSistema = new ArrayList<AnioAntiguoSistema>();

		Map<String, String> lmss_mss;

		lmss_mss = UIUtils.getYearsAgo();

		if(CollectionUtils.isValidCollection(lmss_mss))
		{
			for(Map.Entry<String, String> lmess_entry : lmss_mss.entrySet())
			{
				if(lmess_entry != null)
				{
					AnioAntiguoSistema lap_AnioAntSistema;

					lap_AnioAntSistema = new AnioAntiguoSistema();

					lap_AnioAntSistema.setIdAnioAntiguoSistema(lmess_entry.getKey());
					lap_AnioAntSistema.setNombre(lmess_entry.getValue());

					lcaas_AnioAntSistema.add(lap_AnioAntSistema);
				}
			}
		}

		return lcaas_AnioAntSistema;
	}

	/**
	 * Retorna el valor de Folio.
	 *
	 * @return el valor de Folio
	 */
	public Collection<FolioAntiguoSistema> findFolioAntiguoSistema()
	{
		Collection<FolioAntiguoSistema> lcfas_FolioAntSistema;

		lcfas_FolioAntSistema = new ArrayList<FolioAntiguoSistema>();

		for(long ll_folioActual = 1; ll_folioActual <= 500L; ll_folioActual++)
		{
			FolioAntiguoSistema lfas_folioAntSistema;
			String              ls_tmp;

			ls_tmp                   = StringUtils.getString(ll_folioActual);
			lfas_folioAntSistema     = new FolioAntiguoSistema();

			lfas_folioAntSistema.setIdFolioAntiguoSistema(ls_tmp);
			lfas_folioAntSistema.setNombre(ls_tmp);

			lcfas_FolioAntSistema.add(lfas_folioAntSistema);
		}

		return lcfas_FolioAntSistema;
	}

	/**
	 * Retorna el valor de tipo partida.
	 *
	 * @return el valor de tipo partida
	 */
	public Collection<TipoPartidaAntiguoSistema> findTipoPartidaAntiguoSistema()
	{
		Collection<TipoPartidaAntiguoSistema> lctpas_tipoPartidaAntSistema;
		TipoPartidaAntiguoSistema             ltp_tipoPartidaAntSistema;

		lctpas_tipoPartidaAntSistema     = new ArrayList<TipoPartidaAntiguoSistema>();
		ltp_tipoPartidaAntSistema        = new TipoPartidaAntiguoSistema();

		ltp_tipoPartidaAntSistema.setIdTipoPartidaAntiguoSistema("PAR");
		ltp_tipoPartidaAntSistema.setNombre("PAR");

		lctpas_tipoPartidaAntSistema.add(ltp_tipoPartidaAntSistema);

		ltp_tipoPartidaAntSistema = new TipoPartidaAntiguoSistema();

		ltp_tipoPartidaAntSistema.setIdTipoPartidaAntiguoSistema("IMPAR");
		ltp_tipoPartidaAntSistema.setNombre("IMPAR");

		lctpas_tipoPartidaAntSistema.add(ltp_tipoPartidaAntSistema);

		return lctpas_tipoPartidaAntSistema;
	}

	/**
	 * Retorna el valor de tomo.
	 *
	 * @return el valor de tomo
	 */
	public Collection<TomoAntiguoSistema> findTomoAntiguoSistema()
	{
		Collection<TomoAntiguoSistema> lctpas_TomoAntSistema;

		lctpas_TomoAntSistema = new ArrayList<TomoAntiguoSistema>();

		for(long ll_tomoActual = 1; ll_tomoActual <= 100L; ll_tomoActual++)
		{
			TomoAntiguoSistema ltp_TomoAntSistema;
			String             ls_tmp;

			ls_tmp                 = StringUtils.getString(ll_tomoActual);
			ltp_TomoAntSistema     = new TomoAntiguoSistema();

			ltp_TomoAntSistema.setIdTomoAntiguoSistema(ls_tmp);
			ltp_TomoAntSistema.setNombre(ls_tmp);

			lctpas_TomoAntSistema.add(ltp_TomoAntSistema);
		}

		return lctpas_TomoAntSistema;
	}

	/**
	 * Metodo encargado de salvar pestaña a pestaña los valores correspondientes a cada una de ellas.
	 *
	 * @param afe_event Argumento de tipo <code>FlowEvent</code> que contiene los eventos del flow listener.
	 * @return Variable de tipo <code>String</code> que contiene la pestaña a la cual debe navegar el flow listener.
	 */
	public String flowListenerCopias(FlowEvent afe_event)
	{
		String ls_return;
		ls_return = null;

		if(afe_event != null)
		{
			String ls_oldStep;
			String ls_newStep;

			ls_oldStep     = afe_event.getOldStep();
			ls_newStep     = afe_event.getNewStep();
			ls_return      = ls_newStep;

			try
			{
				FacesContext lfc_context;

				lfc_context = FacesContext.getCurrentInstance();

				if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
				{
					String    ls_datosInteresado;
					String    ls_documentosSoporte;
					String    ls_criteriosBusqueda;
					String    ls_liquidacion;
					String    ls_impresion;
					String    ls_observaciones;
					Solicitud ls_solicitud;

					ls_datosInteresado       = "idDatosInteresado";
					ls_documentosSoporte     = "idDocumentosSoporte";
					ls_criteriosBusqueda     = "idCriteriosBusqueda";
					ls_liquidacion           = "idLiquidacion";
					ls_impresion             = "impresion_id";
					ls_solicitud             = getSolicitud();
					ls_observaciones         = "";

					if(ls_oldStep.equalsIgnoreCase(ls_datosInteresado))
					{
						limpiarEstilosDatosInteresado(cs_ID_FORM + IdentificadoresCommon.DOS_PUNTOS, lfc_context, true);

						if(ls_solicitud != null)
						{
							String ls_entidadExenta;

							ls_entidadExenta = ls_solicitud.getEntidadExenta();

							if(!StringUtils.isValidString(ls_entidadExenta))
								throw new B2BException(ErrorKeys.ERROR_SIN_ENTIDAD_EXENTA);

							ls_solicitud.setIdProcedencia("4");
							setSolicitud(ls_solicitud);

							salvarInteresado();

							{
								boolean lb_entidadExenta;

								lb_entidadExenta     = StringUtils.isValidString(ls_entidadExenta)
										&& ls_entidadExenta.equalsIgnoreCase(EstadoCommon.S);

								ls_return = lb_entidadExenta ? ls_documentosSoporte : ls_criteriosBusqueda;

								setMostrarAtras(true);
								setMostrarCancelar(true);

								if(lb_entidadExenta)
								{
									Collection<com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental> lctd_tipoDocumental;
									ls_solicitud.setEsCopias(true);

									lctd_tipoDocumental = ier_entregaRemote.actualizarTiposDocumentales(
										    ls_solicitud, null, ConstanteCommon.TIPOS_DOCUMENTALES_APODERADO_COPIAS,
										    ConstanteCommon.TIPOS_DOCUMENTALES_TERCERO_COPIAS, getUserId(),
										    getLocalIpAddress(), getRemoteIpAddress()
										);

									if(CollectionUtils.isValidCollection(lctd_tipoDocumental))
									{
										Collection<AccCompletitudDocumental> lcacd_completitudDocumental;

										lcacd_completitudDocumental = getTiposCompletitudDocumental();

										if(!CollectionUtils.isValidCollection(lcacd_completitudDocumental))
										{
											lcacd_completitudDocumental = new ArrayList<AccCompletitudDocumental>();

											for(TipoDocumental ltd_iterador : lctd_tipoDocumental)
											{
												if(ltd_iterador != null)
												{
													AccCompletitudDocumental lacd_completitudDocumental;

													lacd_completitudDocumental = new AccCompletitudDocumental();

													lacd_completitudDocumental.setIdTipoDocumental(
													    ltd_iterador.getIdTipoDocumental()
													);
													lacd_completitudDocumental.setSeleccionado(true);

													lcacd_completitudDocumental.add(lacd_completitudDocumental);
												}
											}

											setTiposCompletitudDocumental(lcacd_completitudDocumental);
										}
									}
									else
									{
										Collection<AccCompletitudDocumental> lcacd_completitudDocumental;
										AccCompletitudDocumental             lacd_completitudDocumental;

										lcacd_completitudDocumental     = new ArrayList<AccCompletitudDocumental>();
										lacd_completitudDocumental      = new AccCompletitudDocumental();

										lacd_completitudDocumental.setIdTipoDocumental(TipoDocumentalCommon.OFICIO);
										lcacd_completitudDocumental.add(lacd_completitudDocumental);

										setTiposCompletitudDocumental(lcacd_completitudDocumental);
									}
								}
								else
								{
									setDocumentosOWCC(null);
									setCriteriosDeConsulta(null);
									setCamposConsulta(null);
									setConsultado(false);
								}
							}
						}
					}
					else if(
					    (ls_oldStep.equalsIgnoreCase(ls_documentosSoporte)
						    && ls_newStep.equalsIgnoreCase(ls_datosInteresado))
						    || (ls_oldStep.equalsIgnoreCase(ls_criteriosBusqueda)
						    && ls_newStep.equalsIgnoreCase(ls_datosInteresado))
					)
					{
						setMostrarAtras(false);
						setMostrarCancelar(false);
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(ls_documentosSoporte)
						    && ls_newStep.equalsIgnoreCase(ls_criteriosBusqueda)
					)
					{
						Collection<AccCompletitudDocumental> lcacd_completitudDocumental;
						String                               ls_idCalidadSolicitante;

						ls_idCalidadSolicitante         = ls_solicitud.getIdCalidadSolicitante();
						lcacd_completitudDocumental     = getTiposCompletitudDocumental();

						if(
						    StringUtils.isValidString(ls_idCalidadSolicitante)
							    && !CollectionUtils.isValidCollection(lcacd_completitudDocumental)
							    && (ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.APODERADO)
							    || ls_idCalidadSolicitante.equalsIgnoreCase(CalidadSolicitanteCommon.TERCERO))
						)
							throw new B2BException(ErrorKeys.ERROR_DEBE_AGREGAR_TIPO_DOCUMENTAL);

						if(CollectionUtils.isValidCollection(lcacd_completitudDocumental))
							irr_registroRemote.guardarTiposDocumentales(
							    lcacd_completitudDocumental, ls_solicitud,
							    ConstanteCommon.TIPOS_DOCUMENTALES_APODERADO_COPIAS,
							    ConstanteCommon.TIPOS_DOCUMENTALES_TERCERO_COPIAS, getLocalIpAddress(),
							    getRemoteIpAddress(), getUserId()
							);
						else
						{
							ExternalContext lec_externalContext;

							lec_externalContext = lfc_context.getExternalContext();

							addMessage(MessagesKeys.SIN_DOCUMENTOS_SOPORTE);

							if(lec_externalContext != null)
							{
								Flash lf_flash;

								lf_flash = lec_externalContext.getFlash();

								if(lf_flash != null)
								{
									lf_flash.setKeepMessages(true);
									PrimeFaces.current().ajax().update(cs_ID_GROWL);
								}
							}
						}

						setDocumentosOWCC(null);
						setCriteriosDeConsulta(null);
						setCamposConsulta(null);
						setConsultado(false);
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(ls_criteriosBusqueda)
						    && ls_newStep.equalsIgnoreCase(ls_documentosSoporte)
					)
					{
						String  ls_entidadExenta;
						boolean lb_entidadExenta;

						ls_entidadExenta     = (ls_solicitud != null) ? ls_solicitud.getEntidadExenta() : null;
						lb_entidadExenta     = StringUtils.isValidString(ls_entidadExenta)
								&& ls_entidadExenta.equalsIgnoreCase(EstadoCommon.S);

						ls_return = lb_entidadExenta ? ls_documentosSoporte : ls_datosInteresado;

						setMostrarAtras(true);
						setMostrarCancelar(true);
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(ls_criteriosBusqueda)
						    && ls_newStep.equalsIgnoreCase(ls_liquidacion)
					)
					{
						if(ib_bloqueoSiguiente)
							throw new B2BException("Debe ingresar un criterio de búsqueda válido");
						else
						{
							String ls_tipoCriterioSeleccionado;

							setObservacionesSolicitud(ls_solicitud.getDescripcion());

							ls_tipoCriterioSeleccionado = getTipoCriterioSeleccionado();

							if(StringUtils.isValidString(ls_tipoCriterioSeleccionado))
							{
								CamposConsulta                         lcc_camposConsulta;
								Collection<DocumentoOWCC>              lcdo_documentosOWCC;
								Collection<DataReproduccionConstancia> lcdrc_dataReproduccionConstancia;

								lcc_camposConsulta                   = new CamposConsulta();
								lcdo_documentosOWCC                  = getDocumentosOWCC();
								lcdrc_dataReproduccionConstancia     = getDataReproduccionConstancia();

								lcc_camposConsulta.setDocumentosOWCC(lcdo_documentosOWCC);
								lcc_camposConsulta.setDataReproduccionConstancia(lcdrc_dataReproduccionConstancia);
								lcc_camposConsulta.setIdTipoCriterioBusqueda(ls_tipoCriterioSeleccionado);

								if(
								    !CollectionUtils.isValidCollection(lcdo_documentosOWCC)
									    && !CollectionUtils.isValidCollection(lcdrc_dataReproduccionConstancia)
								)
									validateStyles(
									    cs_ID_FORM + ":idItNumeroCopias", lfc_context, ls_solicitud.getNumeroCopias(),
									    true
									);

								setSinInformacion(
								    irr_registroRemote.salvarDatosCopias(
								        lcc_camposConsulta, ls_solicitud, getUserId(), getLocalIpAddress(),
								        getRemoteIpAddress()
								    )
								);
							}
							else
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DE_CRITERIO_CONSULTA);

							setMostrarObservaciones(false);
							PrimeFaces.current().ajax().update(cs_ID_FORM + ":opObservaciones1");
						}
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(ls_liquidacion)
						    && ls_newStep.equalsIgnoreCase(ls_criteriosBusqueda)
					)
					{
						setOcultarSiguiente(false);
						setMostrarObservaciones(true);

						PrimeFaces.current().ajax().update(cs_ID_FORM + ":opObservaciones1");
					}
					else if(ls_oldStep.equalsIgnoreCase(ls_liquidacion) && ls_newStep.equalsIgnoreCase(ls_impresion))
					{
						if(isProcesoTerminado())
						{
							Solicitud lso_solicitud;

							lso_solicitud = getSolicitud();

							if(lso_solicitud != null)
							{
								ii_indiceImpresion = 0;

								setDocumentosImprimir(
								    irr_registroRemote.cargarDocumentosSolicitud(
								        IdentificadoresCommon.COPIAS_CON_PAGO, lso_solicitud.getIdSolicitud(),
								        getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								    )
								);
								setOcultarSiguiente(true);
							}
						}
						else
							throw new B2BException(ErrorKeys.ERROR_TERMINAR_PROCESO);
					}
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error(lb2be_e);
				addMessage(lb2be_e);
				PrimeFaces.current().ajax().update(cs_ID_GROWL);
				ls_return = ls_oldStep;
			}
		}

		return ls_return;
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(String as_pantalla)
	{
		imprimirDocumentos(false, as_pantalla);
	}

	/**
	 * Imprime los documentos.
	 *
	 * @param ab_boton boolean que indica si se ejecutó mediante la pantalla.
	 * @param as_pantalla Variable que indica desde que pantalla se ejecuta.
	 */
	public void imprimirDocumentos(boolean ab_boton, String as_pantalla)
	{
		imprimirDocumentos(ab_boton, as_pantalla, getNir(), ":fAccionCancelar:botonFinalizar");
	}

	/**
	 * Método encargado de limpiar los valores de copias
	 */
	public void limpiarCopias()
	{
		setConsultado(false);
		setDocumentosOWCC(null);
		setDataReproduccionConstancia(null);
		setLiquidacionItem(null);
		setTipoCriterioSeleccionado(null);
		setSolcitudCopiasInsertadas(false);
		setCriteriosDeConsulta(null);
		setCamposConsulta(null);
		ib_bloqueoSiguiente = false;
	}

	/**
	 * Metodo encargado de limpiar la selección de los documentos.
	 */
	public void limpiarSeleccione(DataReproduccionConstancia arc_reprodConstanciaSeleccionado)
	{
		if(arc_reprodConstanciaSeleccionado != null)
		{
			Collection<DataReproduccionConstancia> lcdrc_dataReproduccionConstancia;

			lcdrc_dataReproduccionConstancia = getDataReproduccionConstancia();

			if(CollectionUtils.isValidCollection(lcdrc_dataReproduccionConstancia))
			{
				Collection<DataReproduccionConstancia> lcdrc_nueva;

				lcdrc_nueva = new ArrayList<DataReproduccionConstancia>();

				for(DataReproduccionConstancia ldrc_iterador : lcdrc_dataReproduccionConstancia)
				{
					if(ldrc_iterador != null)
					{
						DocumentoConstancia ldc_documentoConstancia;
						DocumentoConstancia ldc_documentoConstanciaSeleccionado;

						ldc_documentoConstancia                 = ldrc_iterador.getDocumento();
						ldc_documentoConstanciaSeleccionado     = arc_reprodConstanciaSeleccionado.getDocumento();

						if((ldc_documentoConstancia != null) && (ldc_documentoConstanciaSeleccionado != null))
						{
							String ls_seleccionado;
							String ls_iterado;

							ls_seleccionado     = ldc_documentoConstanciaSeleccionado.getIdDocumento();
							ls_iterado          = ldc_documentoConstancia.getIdDocumento();

							if(
							    StringUtils.isValidString(ls_seleccionado) && StringUtils.isValidString(ls_iterado)
								    && ls_seleccionado.equalsIgnoreCase(ls_iterado)
							)
								ldc_documentoConstancia.setSeleccione(
								    ldc_documentoConstanciaSeleccionado.isSeleccione()
								);
							else
								ldc_documentoConstancia.setSeleccione(false);

							ldc_documentoConstancia.setNumeroCopias(
							    ldc_documentoConstanciaSeleccionado.getNumeroCopias()
							);
						}

						lcdrc_nueva.add(ldrc_iterador);
					}
				}

				setDataReproduccionConstancia(lcdrc_nueva);
			}
		}
	}

	/**
	 * Pre liquidar copias.
	 */
	public void preLiquidarCopias()
	{
		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud != null)
				preLiquidar();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("preLiquidarCopias", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * terminar proceso copias.
	 */
	public void terminarProcesoCopias()
	{
		try
		{
			{
				Collection<DocumentoOWCC> lcdo_documentosOWCC;
				boolean                   lb_conDocumentosOWCC;

				lcdo_documentosOWCC      = getDocumentosOWCC();
				lb_conDocumentosOWCC     = CollectionUtils.isValidCollection(lcdo_documentosOWCC);

				if(lb_conDocumentosOWCC)
				{
					Iterator<DocumentoOWCC> lido_iterator;
					boolean                 lb_conFolios;

					lido_iterator     = lcdo_documentosOWCC.iterator();
					lb_conFolios      = true;

					while(lido_iterator.hasNext() && lb_conFolios)
					{
						DocumentoOWCC ldo_documentoOWCC;

						ldo_documentoOWCC = lido_iterator.next();

						if((ldo_documentoOWCC != null) && ldo_documentoOWCC.isSeleccione())
							lb_conFolios = NumericUtils.getLong(ldo_documentoOWCC.getnumeroFolios()) > 0;
					}

					lb_conDocumentosOWCC = lb_conFolios;
				}

				terminarProcesoConsultas(true, lb_conDocumentosOWCC, getObservacionesSolicitud());
			}

			limpiarCopias();
			buscarDetalleLiquidacion();

			{
				boolean lb_invocarCapture;
				String  ls_userId;
				String  ls_localIp;
				String  ls_remoteIp;

				ls_userId             = getUserId();
				ls_localIp            = getLocalIpAddress();
				ls_remoteIp           = getRemoteIpAddress();
				lb_invocarCapture     = irr_registroRemote.validarInvocacionCapture(
					    getSolicitud(), ls_userId, ls_localIp, ls_remoteIp
					);

				if(lb_invocarCapture)
				{
					String ls_url;

					ls_url = idr_digitalizacionRemote.construirUrlCapture(
						    getNir(), null, ls_userId, ls_localIp, ls_remoteIp
						);

					if(StringUtils.isValidString(ls_url))
						PrimeFaces.current().executeScript("window.open(href ='" + ls_url + "')");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarProcesoCopias", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}
}
