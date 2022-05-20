package com.bachue.snr.prosnr01.web.bean.consulta.recibo.liquidacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.recibo.liquidacion.ConsultaReciboLiquidacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.liquidacion.LiquidacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.Liquidacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import com.bachue.snr.prosnr01.web.bean.dispositivos.BeanDispositivos;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FlowEvent;

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
 * Clase que contiene todos las propiedades y acciones de BeanConsultaReciboLiquidacion.
 *
 * @author Gabriel Arias
 */
@SessionScoped
@ManagedBean(name = "beanConsultaReciboLiquidacion")
public class BeanConsultaReciboLiquidacion extends BeanDispositivos implements Serializable
{
	private static final long                   serialVersionUID                     = 638861483758587262L;
	private static final LoggerHandler          clh_LOGGER                           = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanConsultaReciboLiquidacion.class);
	private static final String                 is_messageIdGrowl                    = "fConsultaLiquidacion:globalMsg";
	protected Collection<Liquidacion>           icl_resumenPorOrip;
	protected Collection<Liquidacion>           icl_tabsCirculos;
	private Collection<Liquidacion>             icl_data;
	private Collection<Liquidacion>             icl_dataLiquidacion;
	private Collection<Liquidacion>             icl_liquidacionItem;
	@EJB
	private ConsultaReciboLiquidacionRemote     ircrl_remote;
	private Liquidacion                         il_liquidacion;
	@EJB
	private LiquidacionRemote                   ilr_liquidacionRemote;
	@EJB
	private RegistroRemote                      ir_registroRemote;
	@EJB
	private RegistroRemote                      irr_registroRemote;
	private String                              is_numeroReferencia;
	private String                              is_valorTotalConservacion;
	private String                              is_valorTotalDerechos;
	private String                              is_valorTotalLiquidacion;
	private String                              is_valorTotalLiquidacionConservacion;
	private boolean                             ib_editarLiquidacion;
	private boolean                             ib_pantallaImpresion;
	private boolean                             ib_preliquidacion;
	private boolean                             ib_procesoTerminado;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * @param acl_cl Modifica el valor de la propiedad data por data
	 */
	public void setData(Collection<Liquidacion> acl_cl)
	{
		icl_data                                                                     = acl_cl;
	}

	/**
	 * @return Retorna el valor de la propiedad data
	 */
	public Collection<Liquidacion> getData()
	{
		return icl_data;
	}

	/**
	 * @param acl_cl Modifica el valor de la propiedad dataLiquidacion por dataLiquidacion
	 */
	public void setDataLiquidacion(Collection<Liquidacion> acl_cl)
	{
		icl_dataLiquidacion = acl_cl;
	}

	/**
	 * @return Retorna el valor de la propiedad dataLiquidacion
	 */
	public Collection<Liquidacion> getDataLiquidacion()
	{
		return icl_dataLiquidacion;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad editarLiquidacion por editarLiquidacion
	 */
	public void setEditarLiquidacion(boolean ab_b)
	{
		ib_editarLiquidacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad editarLiquidacion
	 */
	public boolean isEditarLiquidacion()
	{
		return ib_editarLiquidacion;
	}

	/**
	 * @param al_l Modifica el valor de la propiedad liquidacion por liquidacion
	 */
	public void setLiquidacion(Liquidacion al_l)
	{
		il_liquidacion = al_l;
	}

	/**
	 * @return Retorna el valor de la propiedad liquidacion
	 */
	public Liquidacion getLiquidacion()
	{
		return il_liquidacion;
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
	 * @param as_s Modifica el valor de la propiedad numeroReferencia por numeroReferencia
	 */
	public void setNumeroReferencia(String as_s)
	{
		is_numeroReferencia = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad numeroReferencia
	 */
	public String getNumeroReferencia()
	{
		return is_numeroReferencia;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad pantallaImpresion por pantallaImpresion
	 */
	public void setPantallaImpresion(boolean ab_b)
	{
		ib_pantallaImpresion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad pantallaImpresion
	 */
	public boolean isPantallaImpresion()
	{
		return ib_pantallaImpresion;
	}

	/**
	 * Modifica el valor de preliquidacion.
	 *
	 * @param ab_b asigna el valor a la propiedad preliquidacion
	 */
	public void setPreliquidacion(boolean ab_b)
	{
		ib_preliquidacion = ab_b;
	}

	/**
	 * Valida la propiedad preliquidacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en preliquidacion
	 */
	public boolean isPreliquidacion()
	{
		return ib_preliquidacion;
	}

	/**
	 * @param ab_b Modifica el valor de la propiedad procesoTerminado por procesoTerminado
	 */
	public void setProcesoTerminado(boolean ab_b)
	{
		ib_procesoTerminado = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad procesoTerminado
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
	 * Modifica el valor de tabs circulos.
	 *
	 * @param acl_cl asigna el valor a la propiedad tabs circulos
	 */
	public void setTabsCirculos(Collection<Liquidacion> acl_cl)
	{
		icl_tabsCirculos = acl_cl;
	}

	/**
	 * Retorna el valor de tabs circulos.
	 *
	 * @return el valor de tabs circulos
	 */
	public Collection<Liquidacion> getTabsCirculos()
	{
		return icl_tabsCirculos;
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
	 * Metodo encargado de consultar la liquidación detallada generada.
	 * @throws Exception Señala que se ha producido una excepción
	 */
	public void buscarDetalleLiquidacion()
	    throws B2BException
	{
		try
		{
			Liquidacion ll_data;

			ll_data = getLiquidacion();

			if(ll_data != null)
			{
				Liquidacion             ll_liquidacion;
				Collection<Liquidacion> lcl_detalleLiquidacion;

				ll_liquidacion = new Liquidacion();

				ll_liquidacion.setIdSolicitud(ll_data.getIdSolicitud());
				ll_liquidacion.setNumeroReferencia(ll_data.getNumeroReferencia());
				ll_liquidacion.setIdTipoMayorValor(ll_data.getIdTipoMayorValor());

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

					setLiquidacionItem(lcl_detalleLiquidacion);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);

			addMessage(lb2be_e);
		}
	}

	/**
	 * Cambiar respuesta liquidacion.
	 *
	 * @param al_liquidacion correspondiente al valor del tipo de objeto Liquidacion
	 */
	public void cambiarRespuestaLiquidacion(Liquidacion al_liquidacion)
	{
		if(al_liquidacion != null)
		{
			if(al_liquidacion.isRespuestaBoolean())
				al_liquidacion.setRespuesta(EstadoCommon.S);
			else
				al_liquidacion.setRespuesta(EstadoCommon.N);
		}
	}

	/**
	 * Método encargado de reiniciar las variables de la clase
	 */
	public void clean()
	{
		setData(null);
		setNumeroReferencia(null);
		setLiquidacion(null);
		setDataLiquidacion(null);
		setLiquidacionItem(null);
		setValorTotalConservacion(null);
		setValorTotalDerechos(null);
		setValorTotalLiquidacion(null);
		setValorTotalLiquidacionConservacion(null);
		setTabsCirculos(null);
		setPreliquidacion(false);
		setProcesoTerminado(false);
		setPantallaImpresion(false);
		setEditarLiquidacion(false);
	}

	/**
	 * Método encargado de confirmar la eliminación de la liquidación.
	 * @throws B2BException
	 */
	public String confirmarEliminarLiquidacion()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			ircrl_remote.confirmarEliminarLiquidacion(
			    getLiquidacion(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			clean();
			consultar();

			ls_return = NavegacionCommon.CONSULTA_RECIBO_LIQUIDACION;
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Método encargado de consultar la información de la bandeja.
	 * @throws B2BException
	 */
	public void consultar()
	    throws B2BException
	{
		setData(ircrl_remote.consultarBandeja(getNumeroReferencia()));
	}

	/**
	 * Método encargado de editar la liquidación para una solicitud.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void editarLiquidacion()
	    throws B2BException
	{
		try
		{
			Liquidacion ll_liquidacion;

			ll_liquidacion = getLiquidacion();

			if(ll_liquidacion != null)
			{
				Solicitud ls_param;

				ls_param = new Solicitud();

				ls_param.setIdSolicitud(ll_liquidacion.getIdSolicitud());

				setTabsCirculos(ilr_liquidacionRemote.findCondicionesLiquidacion(ls_param));
				setEditarLiquidacion(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);

			addMessage(lb2be_e);
		}
	}

	/**
	 * Controla el flujo entre tabs en la pantalla.
	 *
	 * @param afe_event Objeto contenedor de los eventos que ocurren en pantalla
	 * @return Nombre de tab a mostrar en pantalla
	 *
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_return;

		ls_return = afe_event.getNewStep();

		try
		{
			String ls_oldStep;
			String ls_newStep;

			ls_oldStep     = afe_event.getOldStep();
			ls_newStep     = afe_event.getNewStep();

			if(StringUtils.isValidString(ls_oldStep) && StringUtils.isValidString(ls_newStep))
			{
				if(ls_newStep.equalsIgnoreCase("impresion_id"))
				{
					if(isProcesoTerminado())
					{
						Liquidacion ll_liquidacion;

						ll_liquidacion = getLiquidacion();

						if(ll_liquidacion != null)
						{
							ii_indiceImpresion = 0;

							setDocumentosImprimir(
							    irr_registroRemote.cargarDocumentosSolicitud(
							        IdentificadoresCommon.ANULAR_RECIBO, ll_liquidacion.getIdSolicitud(), getUserId(),
							        getLocalIpAddress(), getRemoteIpAddress()
							    )
							);

							setPantallaImpresion(true);
						}
					}
					else
						throw new B2BException(ErrorKeys.ERROR_TERMINAR_PROCESO);
				}
			}
			else
				throw new B2BException(ErrorKeys.ERROR_INTERNO_SISTEMA);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);

			ls_return = afe_event.getOldStep();
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
		Liquidacion ll_liquidacion;

		ll_liquidacion = getLiquidacion();

		if(ll_liquidacion != null)
			imprimirDocumentos(ab_boton, as_pantalla, ll_liquidacion.getNir(), ":botonFinalizar");
	}

	/**
	 * Pre liquidar.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void preLiquidarRecibo()
	    throws B2BException
	{
		try
		{
			procLiquidacion(IdentificadoresCommon.PRELIQUIDAR);
			buscarDetalleLiquidacion();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Ejecuta la acción de regresar.
	 *
	 * @return pantalla de bandeja principal.
	 * @throws B2BException
	 */
	public String regresar()
	    throws B2BException
	{
		clean();
		consultar();

		return NavegacionCommon.CONSULTA_RECIBO_LIQUIDACION;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @param al_detalle Objeto que contiene el detalle seleccionado.
	 * @return devuelve el valor de String.
	 * @throws B2BException Señala que se ha producido una excepción.
	 */
	public String returnPages(Liquidacion al_detalle)
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		if(al_detalle != null)
		{
			Collection<Liquidacion> lcl_dataLiquidacion;

			lcl_dataLiquidacion     = new ArrayList<Liquidacion>();
			ls_return               = NavegacionCommon.DETALLE_RECIBO_LIQUIDACION;

			lcl_dataLiquidacion.add(al_detalle);

			setLiquidacion(al_detalle);
			setDataLiquidacion(lcl_dataLiquidacion);
			setPreliquidacion(false);
			setProcesoTerminado(false);
			setPantallaImpresion(false);
			buscarDetalleLiquidacion();
		}

		return ls_return;
	}

	/**
	 * Método encargado de liquidar y finalizar el proceso.
	 * @throws B2BException
	 */
	public void terminarProcesoRecibo()
	    throws B2BException
	{
		try
		{
			Liquidacion ll_liquidacion;

			ll_liquidacion = getLiquidacion();

			if(ll_liquidacion != null)
			{
				String ls_indCondicion;

				ls_indCondicion = IdentificadoresCommon.LIQUIDAR;

				procLiquidacion(ls_indCondicion);

				ll_liquidacion.setCondicion(ls_indCondicion);

				ircrl_remote.terminarProceso(ll_liquidacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress());

				setProcesoTerminado(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messageIdGrowl);
		}
	}

	/**
	 * Método encargado de validar si se peude eliminar la liquidación.
	 * @throws B2BException
	 */
	public void validarEliminarLiquidacion()
	    throws B2BException
	{
		try
		{
			if(ircrl_remote.validarEliminarLiquidacion(getLiquidacion()))
				abrirDialogo("dlgConfirmacionEliminar", "dlgConfirmacionEliminar");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Proc liquidacion.
	 *
	 * @param as_procesoInvoncacion correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void procLiquidacion(String as_procesoInvoncacion)
	    throws B2BException
	{
		try
		{
			Liquidacion ll_liquidacion;

			ll_liquidacion = getLiquidacion();

			if(StringUtils.isValidString(as_procesoInvoncacion) && (ll_liquidacion != null))
			{
				Liquidacion ll_param;
				Solicitud   los_tmp;

				ll_param     = new Liquidacion();
				los_tmp      = new Solicitud();

				los_tmp.setIdSolicitud(ll_liquidacion.getIdSolicitud());
				los_tmp.setIdUsuarioCreacion(getUserId());
				los_tmp.setIpCreacion(getRemoteIpAddress());
				ll_param.setSolicitud(los_tmp);
				ll_param.setIdTipoMayorValor(null);
				ll_param.setIdCondicion(as_procesoInvoncacion);
				ll_param.setLiquidacionItemCondicion(getTabsCirculos());

				ilr_liquidacionRemote.procReLiquidacion(
				    ll_param, ll_liquidacion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setPreliquidacion(true);
			}
		}
		catch(B2BException lb2be_e)
		{
			setPreliquidacion(false);
			throw lb2be_e;
		}
	}
}
