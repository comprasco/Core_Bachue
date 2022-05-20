package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanAnalistaCopias;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoCriterioBusquedaCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion.LiquidacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.BuscarAntiguoSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanAntSistemaCalificacion;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanBuscarAntiguoSistema.
 *
 * @author hcastaneda
 */
@SessionScoped
@ManagedBean(name = "beanBuscarAntiguoSistema")
public class BeanBuscarAntiguoSistema extends BeanAntSistemaCalificacion implements Serializable
{
	private static final long serialVersionUID = 7653402287214303041L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanBuscarAntiguoSistema.class);

	/** Constante is_idForm. */
	public static final String cs_ID_FORM = "fBuscarAntSistema";

	/** Constante is_messageIdGrowl. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":idGrowl";

	/** Propiedad icl resumen por orip. */
	protected Collection<Liquidacion> icl_resumenPorOrip;

	/** Propiedad icdo documentos OWCC. */
	private Collection<DocumentoOWCC> icdo_documentosOWCC;

	/** Propiedad icl liquidacion item. */
	private Collection<Liquidacion> icl_liquidacionItem;

	/** Propiedad ilr liquidacion remote. */
	@EJB
	private LiquidacionRemote ilr_liquidacionRemote;

	/** Propiedad ir registroRemote. */
	@EJB
	private RegistroRemote ir_registroRemote;

	/** Propiedad iso solicitud. */
	private Solicitud iso_solicitud;

	/** Propiedad is valor total conservacion. */
	private String is_valorTotalConservacion;

	/** Propiedad is valor total derechos. */
	private String is_valorTotalDerechos;

	/** Propiedad is valor total liquidacion. */
	private String is_valorTotalLiquidacion;

	/** Propiedad is valor total liquidacion conservacion. */
	private String is_valorTotalLiquidacionConservacion;

	/** Propiedad it turno. */
	private Turno it_turno;
	private boolean ib_digitalizarDocumento;

	/** Propiedad ib proceso terminado. */
	private boolean ib_procesoTerminado;

	/** Propiedad il_idMotivo. */
	private long il_idMotivo;

	/**
	 * Metodo que asigna el valor a la propiedad.
	 * @param ab_b Valor a asignar.
	 */
	public void setDigitalizarDocumento(boolean ab_b)
	{
		ib_digitalizarDocumento                = ab_b;
	}

	/**
	 *  Metodo que trae el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public boolean isDigitalizarDocumento()
	{
		return ib_digitalizarDocumento;
	}

	/**
	 * Metodo que asigna el valor a la propiedad.
	 * @param acdo_cdo Valor a asignar.
	 */
	public void setDocumentosOWCC(Collection<DocumentoOWCC> acdo_cdo)
	{
		icdo_documentosOWCC = acdo_cdo;
	}

	/**
	 *  Metodo que trae el valor de la propiedad.
	 *
	 * @return the documentos OWCC
	 */
	public Collection<DocumentoOWCC> getDocumentosOWCC()
	{
		return icdo_documentosOWCC;
	}

	/**
	 * Metodo que asigna el valor a la propiedad.
	 * @param al_l Valor a asignar.
	 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 *  Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdMotivo()
	{
		return il_idMotivo;
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
	 * Modifica el valor de proceso terminado.
	 *
	 * @param ab_b asigna el valor a la propiedad proceso terminado
	 */
	public void setProcesoTerminado(boolean ab_b)
	{
		ib_procesoTerminado = ab_b;
	}

	/**
	 * Valida la propiedad proceso terminado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso terminado
	 */
	public boolean isProcesoTerminado()
	{
		return ib_procesoTerminado;
	}

	/**
	 * Modifica el valor de resumen por orip.
	 *
	 * @param as_s Objeto o Variable de tipo Collection que asigna un valor a la propiedad resumen por orip
	 */
	public void setResumenPorOrip(Collection<Liquidacion> as_s)
	{
		icl_resumenPorOrip = as_s;
	}

	/**
	 * Retorna la propiedad resumen por orip.
	 *
	 * @return Retorna el valor de la propiedad resumen por orip
	 */
	public Collection<Liquidacion> getResumenPorOrip()
	{
		return icl_resumenPorOrip;
	}

	/**
	 * Modifica el valor de solicitud.
	 *
	 * @param aso_so asigna el valor a la propiedad solicitud
	 */
	public void setSolicitud(Solicitud aso_so)
	{
		iso_solicitud = aso_so;
	}

	/**
	 * Retorna el valor de solicitud.
	 *
	 * @return el valor de solicitud
	 */
	public Solicitud getSolicitud()
	{
		if(iso_solicitud == null)
			iso_solicitud = new Solicitud();

		return iso_solicitud;
	}

	/**
	 * Metodo que asigna el valor a la propiedad.
	 * @param at_t Valor a asignar.
	 */
	public void setTurno(Turno at_t)
	{
		it_turno = at_t;
	}

	/**
	 *  Metodo que retorna el valor de la propiedad.
	 *
	 * @return Retorna el valor de la propiedad.
	 */
	public Turno getTurno()
	{
		return it_turno;
	}

	/**
	 * Modifica el valor total conservacion.
	 *
	 * @param as_s asigna el valor a la propiedad alor total conservacion
	 */
	public void setValorTotalConservacion(String as_s)
	{
		is_valorTotalConservacion = as_s;
	}

	/**
	 * Valida la propiedad valor total conservación.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valor total conservación
	 */
	public String getValorTotalConservacion()
	{
		return is_valorTotalConservacion;
	}

	/**
	 * Modifica el valor total derechos.
	 *
	 * @param as_s asigna el valor a la propiedad alor total derechos
	 */
	public void setValorTotalDerechos(String as_s)
	{
		is_valorTotalDerechos = as_s;
	}

	/**
	 * Valida la propiedad valor total derechos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valor total derechos
	 */
	public String getValorTotalDerechos()
	{
		return is_valorTotalDerechos;
	}

	/**
	 * Modifica el valor total liquidación.
	 *
	 * @param as_s asigna el valor a la propiedad alor total liquidación
	 */
	public void setValorTotalLiquidacion(String as_s)
	{
		is_valorTotalLiquidacion = as_s;
	}

	/**
	 * Valida la propiedad valor total liquidación.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valor total liquidación
	 */
	public String getValorTotalLiquidacion()
	{
		return is_valorTotalLiquidacion;
	}

	/**
	 * Modifica el valor total liquidación conservación.
	 *
	 * @param as_s asigna el valor a la propiedad valor total liquidación conservación
	 */
	public void setValorTotalLiquidacionConservacion(String as_s)
	{
		is_valorTotalLiquidacionConservacion = as_s;
	}

	/**
	 * Valida la propiedad valor total liquidación conservación.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en valor total liquidación conservación
	 */
	public String getValorTotalLiquidacionConservacion()
	{
		return is_valorTotalLiquidacionConservacion;
	}

	/**
	 * Metodo encargado de consultar y validar si se modificaron datos de antiguo sistema para copias.
	 */
	public void abriUrlCapture()
	{
		try
		{
			BuscarAntiguoSistema lbas_parametros;
			String               ls_urlCapture;

			lbas_parametros = new BuscarAntiguoSistema();

			lbas_parametros.setSolicitud(getSolicitud());
			lbas_parametros.setIdTurno(getIdTurno());
			lbas_parametros.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			ls_urlCapture = ir_registroRemote.crearYObtenerUrlDigitalizacion(
				    lbas_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(StringUtils.isValidString(ls_urlCapture))
			{
				PrimeFaces lpf_current;

				lpf_current = PrimeFaces.current();

				lpf_current.executeScript("window.open(href ='" + ls_urlCapture + "')");
				lpf_current.executeScript("PF('wvPoll').start();");

				setDigitalizarDocumento(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abriUrlCapture", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar la liquidación detallada generada.
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public void buscarDetalleLiquidacion()
	    throws B2BException
	{
		try
		{
			Solicitud ls_solicitud;

			ls_solicitud = getSolicitud();

			if(ls_solicitud != null)
			{
				Liquidacion             ll_liquidacion;
				Collection<Liquidacion> lcl_detalleLiquidacion;

				ll_liquidacion = new Liquidacion();

				ll_liquidacion.setIdSolicitud(ls_solicitud.getIdSolicitud());

				lcl_detalleLiquidacion = ilr_liquidacionRemote.buscarDetalleLiquidacion(
					    ll_liquidacion, true, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(lcl_detalleLiquidacion))
				{
					double ld_valorTotalDerechos;
					double ld_valorTotalConservacion;
					double ld_valorTotalLiquidado;

					Map<String, Double> lmsd_valoresResumen;

					ld_valorTotalDerechos         = 0D;
					ld_valorTotalConservacion     = 0D;
					ld_valorTotalLiquidado        = 0D;

					lmsd_valoresResumen = new LinkedHashMap<String, Double>();

					for(Liquidacion ll_iterador : lcl_detalleLiquidacion)
					{
						if(ll_iterador != null)
						{
							{
								String ls_idProceso;
								String ls_reglaCuantiaCantidad;

								ls_idProceso                = ll_iterador.getIdProceso();
								ls_reglaCuantiaCantidad     = null;

								if(
								    StringUtils.isValidString(ls_idProceso)
									    && ls_idProceso.equalsIgnoreCase(IdentificadoresCommon.PROCESO_CERTIFICADOS)
								)
									ls_reglaCuantiaCantidad = IdentificadoresCommon.PROCESO_CERTIFICADOS;
								else
									ls_reglaCuantiaCantidad = (StringUtils.getStringNotNull(
										    ll_iterador.getRequiereCuantia()
										).equals(EstadoCommon.N)) ? "CANTIDAD" : "CUANTIA";

								ll_iterador.setReglaCuantiaCantidad(ls_reglaCuantiaCantidad);
								ll_iterador.setCuantiaActoDouble(NumericUtils.getDouble(ll_iterador.getCuantiaActo()));
							}

							{
								Double ld_valorFinal;

								ld_valorFinal = ll_iterador.getValorFinal();

								if(NumericUtils.isValidDouble(ld_valorFinal))
									ld_valorTotalDerechos += ld_valorFinal.doubleValue();
								else
									ll_iterador.setValorFinal(NumericUtils.getDoubleWrapper(0D));
							}

							{
								BigDecimal lbd_valorDocumental;
								BigDecimal lbd_valorLiquidacion;
								String     ls_circuloRegistral;

								lbd_valorDocumental      = ll_iterador.getValorDocumental();
								lbd_valorLiquidacion     = ll_iterador.getValorTotal();
								ls_circuloRegistral      = ll_iterador.getCirculosRegistrales();

								if(NumericUtils.isValidBigDecimal(lbd_valorDocumental))
									ld_valorTotalConservacion += lbd_valorDocumental.doubleValue();

								if(NumericUtils.isValidBigDecimal(lbd_valorLiquidacion))
									ld_valorTotalLiquidado += lbd_valorLiquidacion.doubleValue();

								if(StringUtils.isValidString(ls_circuloRegistral))
								{
									double ld_valorIterado;

									ld_valorIterado = NumericUtils.getDouble(
										    NumericUtils.getDoubleWrapper(lbd_valorLiquidacion)
										);

									if(lmsd_valoresResumen.containsKey(ls_circuloRegistral))
									{
										double ld_valorTmp;

										ld_valorTmp = NumericUtils.getDouble(
											    lmsd_valoresResumen.get(ls_circuloRegistral)
											);

										lmsd_valoresResumen.replace(
										    ls_circuloRegistral,
										    NumericUtils.getDoubleWrapper(ld_valorTmp + ld_valorIterado)
										);
									}
									else
										lmsd_valoresResumen.put(
										    ls_circuloRegistral, NumericUtils.getDoubleWrapper(ld_valorIterado)
										);
								}
							}
						}
					}

					{
						DecimalFormat ldf_decimalFormat;

						ldf_decimalFormat = new DecimalFormat("#,###.00");

						setValorTotalDerechos(
						    NumericUtils.conversionCientifica(
						        NumericUtils.getDoubleWrapper(ld_valorTotalDerechos), ldf_decimalFormat
						    )
						);
						setValorTotalConservacion(
						    NumericUtils.conversionCientifica(
						        NumericUtils.getDoubleWrapper(ld_valorTotalConservacion), ldf_decimalFormat
						    )
						);

						{
							String ls_valorLiquidado;

							ls_valorLiquidado = NumericUtils.conversionCientifica(
								    NumericUtils.getDoubleWrapper(ld_valorTotalLiquidado), ldf_decimalFormat
								);

							setValorTotalLiquidacion(ls_valorLiquidado);
							setValorTotalLiquidacionConservacion(ls_valorLiquidado);
						}
					}

					if(CollectionUtils.isValidCollection(lmsd_valoresResumen))
					{
						Collection<Liquidacion> lcl_resumenPorOrip;

						lcl_resumenPorOrip = new ArrayList<Liquidacion>();

						for(Map.Entry<String, Double> lmesd_iterador : lmsd_valoresResumen.entrySet())
						{
							if(lmesd_iterador != null)
							{
								Liquidacion ll_resumenPorOrip;

								ll_resumenPorOrip = new Liquidacion();

								ll_resumenPorOrip.setCirculosRegistrales(lmesd_iterador.getKey());
								ll_resumenPorOrip.setValorTotal(NumericUtils.getBigDecimal(lmesd_iterador.getValue()));

								lcl_resumenPorOrip.add(ll_resumenPorOrip);
							}
						}

						setResumenPorOrip(lcl_resumenPorOrip);
					}

					setLiquidacionItem(lcl_detalleLiquidacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("buscarDetalleLiquidacion", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar la información capturada en expedición de copias para antiguo sistema en buscar antiguo sistema.
	 */
	public void cargarInformacionAntiguoSistema()
	{
		try
		{
			BuscarAntiguoSistema lbas_buscarAntiguoSistema;

			lbas_buscarAntiguoSistema = ir_registroRemote.cargarInformacionAntiguoSistema(
				    NumericUtils.getLongWrapper(getIdTurnoHistoria()), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);

			if(lbas_buscarAntiguoSistema != null)
			{
				setConsultaCriteriosCalificacionDocumento(
				    lbas_buscarAntiguoSistema.getConsultaCriteriosCalificacionDocumento()
				);
				setDetalleAntSistemaConsulta(lbas_buscarAntiguoSistema.getDetalleAntSistema());
				setSolicitud(lbas_buscarAntiguoSistema.getSolicitud());

				tipoOficina(true, false);
				cambioCirculoBusqueda();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarInformacionAntiguoSistema", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar datos de antiguo sistema para copias.
	 */
	public void consultaDetalleAntSistemaCopias()
	{
		consultaDetalleAntSistema(false, ProcesoCommon.ID_PROCESO_5);
	}

	/**
	 * Metodo encargado de consultar si ya se digitalizó el documento.
	 */
	public void consultarDocumentoDigitalizado()
	{
		try
		{
			Turno lt_turno;

			lt_turno = ir_registroRemote.buscarTurnoPorId(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lt_turno != null)
			{
				String ls_digitalizado;

				ls_digitalizado = lt_turno.getDigitalizado();

				if(StringUtils.isValidString(ls_digitalizado) && ls_digitalizado.equalsIgnoreCase(EstadoCommon.S))
				{
					PrimeFaces.current().executeScript("PF('wvPoll').stop();");

					setTurno(lt_turno);
					setDigitalizarDocumento(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("abriUrlCapture", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar la información capturada en expedicón de copias para antiguo sistema en buscar antiguo sistema.
	 *
	 * @param ab_antiguoSistema Argumento de tipo <code>boolean</code> que determina si es tipo criterio busqueda antiguo sistema o no.
	 */
	public void consultarOWCCAntiguoSistemaCopias(boolean ab_antiguoSistema)
	{
		try
		{
			BuscarAntiguoSistema      lbas_buscarAntiguoSistema;
			Collection<DocumentoOWCC> ldo_documentosOWCC;

			lbas_buscarAntiguoSistema = new BuscarAntiguoSistema();

			lbas_buscarAntiguoSistema.setMatriculas(getMatriculasPorDetalle());
			lbas_buscarAntiguoSistema.setDataConsultaPorDocumento(getDataConsultaDatosDocumento());
			lbas_buscarAntiguoSistema.setIdTipoCriterioBusqueda(
			    ab_antiguoSistema ? TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
			                      : TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
			);

			ldo_documentosOWCC = ir_registroRemote.consultarOWCCAntiguoSistemaCopias(
				    lbas_buscarAntiguoSistema, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(ldo_documentosOWCC))
			{
				addMessage(MessagesKeys.CONSULTA_EXITOSA);
				actualizarComponente(cs_ID_GROWL);
				setDetallesAntSistemaConsulta(null);
				setDataConsultaDatosDocumento(null);
			}
			else
				abrirDialogo("wvSinDatosOWCC", "fSinDatosOWCC");

			setDocumentosOWCC(ldo_documentosOWCC);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarInformacionAntiguoSistema", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar y validar si se modificaron datos de antiguo sistema para copias.
	 */
	public void consultarYModificarAntSistema(boolean ab_antiguoSistema)
	{
		try
		{
			BuscarAntiguoSistema lbas_buscarAntiguoSistema;

			lbas_buscarAntiguoSistema = new BuscarAntiguoSistema();

			lbas_buscarAntiguoSistema.setDetalleAntSistema(getDetalleAntSistemaConsulta());
			lbas_buscarAntiguoSistema.setConsultaCriteriosCalificacionDocumento(
			    getConsultaCriteriosCalificacionDocumento()
			);
			lbas_buscarAntiguoSistema.setIdTipoCriterioBusqueda(
			    ab_antiguoSistema ? TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
			                      : TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
			);
			lbas_buscarAntiguoSistema.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			if(ab_antiguoSistema)
				consultaDetalleAntSistema(false, ProcesoCommon.ID_PROCESO_5);
			else
				validarExistenciaDocumento();

			ir_registroRemote.modificarDatosAntiguoSistema(
			    lbas_buscarAntiguoSistema, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarYValidarModificacionAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar y validar si se modificaron datos de antiguo sistema para copias.
	 */
	public void consultarYValidarModificacionAntSistema(boolean ab_antiguoSistema)
	{
		try
		{
			BuscarAntiguoSistema lbas_buscarAntiguoSistema;
			boolean              lb_modificado;

			lbas_buscarAntiguoSistema = new BuscarAntiguoSistema();

			lbas_buscarAntiguoSistema.setDetalleAntSistema(getDetalleAntSistemaConsulta());
			lbas_buscarAntiguoSistema.setConsultaCriteriosCalificacionDocumento(
			    getConsultaCriteriosCalificacionDocumento()
			);
			lbas_buscarAntiguoSistema.setIdTipoCriterioBusqueda(
			    ab_antiguoSistema ? TipoCriterioBusquedaCommon.ANTIGUO_SISTEMA
			                      : TipoCriterioBusquedaCommon.DOCUMENTO_ANTIGUO_SISTEMA
			);
			lbas_buscarAntiguoSistema.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			lb_modificado = ir_registroRemote.validaModificacionDatosAntiguoSistema(
				    lbas_buscarAntiguoSistema, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lb_modificado)
			{
				String ls_idWidgedVar;
				String ls_idFormWidget;

				ls_idWidgedVar      = ab_antiguoSistema ? "wvDatosModificadosAntSistema" : "wvDatosModificadosDocumento";
				ls_idFormWidget     = ab_antiguoSistema ? "fDatosModificadosAntSistema" : "fDatosModificadosDocumento";

				abrirDialogo(ls_idWidgedVar, ls_idFormWidget);
			}
			else
			{
				if(ab_antiguoSistema)
					consultaDetalleAntSistema(false, ProcesoCommon.ID_PROCESO_5);
				else
					validarExistenciaDocumento();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarYValidarModificacionAntSistema", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de cargar la información capturada en expedicón de copias para antiguo sistema en buscar antiguo sistema.
	 */
	public void limpiarDatosCalculados()
	{
		setDocumentosOWCC(null);
		setLiquidacionItem(null);
	}

	/**
	 * Metodo encargado de preliquidar la información consultada del owcc.
	 */
	public void preliquidar()
	{
		preliquidar(false);
	}

	/**
	 * Metodo encargado de preliquidar la información consultada del owcc.
	 * @param ab_validarNumeroFolios Argumento de tipo <code>boolean</code> que determina si se debe validar número de folios.
	 */
	public void preliquidar(boolean ab_validarNumeroFolios)
	{
		try
		{
			BuscarAntiguoSistema lbas_buscarAntiguoSistema;

			lbas_buscarAntiguoSistema = new BuscarAntiguoSistema();

			lbas_buscarAntiguoSistema.setMatriculas(getMatriculasPorDetalle());
			lbas_buscarAntiguoSistema.setDocumentosOWCC(getDocumentosOWCC());
			lbas_buscarAntiguoSistema.setSolicitud(getSolicitud());
			lbas_buscarAntiguoSistema.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lbas_buscarAntiguoSistema.setIdTurno(getIdTurno());
			lbas_buscarAntiguoSistema.setValidarNumeroFolios(ab_validarNumeroFolios);

			ir_registroRemote.guardarSolicitudCopiasPreliquidar(
			    lbas_buscarAntiguoSistema, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			buscarDetalleLiquidacion();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("preliquidar", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de seleccionar un solo criterio de documento.
	 *
	 * @param adcpc_seleccionado Argumento de tipo <code>DataConsultaPorCriterio</code> que contiene los criterios seleccionados.
	 */
	public void seleccionarUnSoloCriterioDocumento(DataConsultaPorCriterio adcpc_seleccionado)
	{
		if(adcpc_seleccionado != null)
		{
			Collection<DataConsultaPorCriterio> lcdcpc_documentos;

			lcdcpc_documentos = getDataConsultaDatosDocumento();

			if(CollectionUtils.isValidCollection(lcdcpc_documentos))
			{
				for(DataConsultaPorCriterio ldcpc_iterador : lcdcpc_documentos)
				{
					if(ldcpc_iterador != null)
					{
						String ls_idCirculoIterador;
						String ls_idCirculoSeleccionado;

						long ll_idMatriculaIterador;
						long ll_idMatriculaSeleccionada;

						ls_idCirculoIterador         = StringUtils.getStringNotNull(ldcpc_iterador.getIdCirculo());
						ls_idCirculoSeleccionado     = StringUtils.getStringNotNull(adcpc_seleccionado.getIdCirculo());

						ll_idMatriculaIterador         = NumericUtils.getLong(ldcpc_iterador.getIdMatricula());
						ll_idMatriculaSeleccionada     = NumericUtils.getLong(adcpc_seleccionado.getIdMatricula());

						if(
						    ls_idCirculoIterador.equalsIgnoreCase(ls_idCirculoSeleccionado)
							    && (ll_idMatriculaIterador == ll_idMatriculaSeleccionada)
						)
							ldcpc_iterador.setSeleccione(adcpc_seleccionado.isSeleccione());
						else
							ldcpc_iterador.setSeleccione(false);
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de terminar proceso copias.
	 */
	public String terminarProcesoCopias()
	{
		String ls_retorno;

		ls_retorno = null;

		try
		{
			BuscarAntiguoSistema lbas_parametros;

			lbas_parametros = new BuscarAntiguoSistema();

			lbas_parametros.setSolicitud(getSolicitud());
			lbas_parametros.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
			lbas_parametros.setIdMotivo(getIdMotivo());

			ir_registroRemote.terminarProcesoCopiasAntSistema(
			    lbas_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				BeanAnalistaCopias lbc_bean;

				lbc_bean = (BeanAnalistaCopias)obtenerInstanciaBean(
					    BeanAnalistaCopias.class, BeanNameCommon.BEAN_ANALISTA_COPIAS
					);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();

				ls_retorno = NavegacionCommon.ANALISTA_DE_COPIAS;
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

		return ls_retorno;
	}
}
