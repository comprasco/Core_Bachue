package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.png.TarifaAlerta;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase para el manejo de la capa web tarifasParaAlertas.
 *
 * @author Carlos Calderón
 */
@ManagedBean(name = "beanTarifasParaAlertas")
@SessionScoped
public class BeanTarifasParaAlertas extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 8832709105550196165L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTarifasParaAlertas.class);

	/** Propiedad ictpa tarifa alerta collection. */
	private Collection<TarifaAlerta> ictpa_tarifaAlertaCollection;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ita de tarifa alerta temporal */
	private TarifaAlerta ita_tarifaAlertaTemporal;

	/** Propiedad ib solo existe un registro. */
	private boolean ib_soloExisteUnRegistro;

	/** Propiedad ib ultimo registro confirmado. */
	private boolean ib_ultimoRegistroConfirmado;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de solo existe un registro.
	 *
	 * @param ab_b asigna el valor a la propiedad solo existe un registro
	 */
	public void setSoloExisteUnRegistro(boolean ab_b)
	{
		ib_soloExisteUnRegistro = ab_b;
	}

	/**
	 * Valida la propiedad solo existe un registro.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en solo existe un registro
	 */
	public boolean isSoloExisteUnRegistro()
	{
		return ib_soloExisteUnRegistro;
	}

	/**
	 * Modifica el valor de tarifa alerta collection.
	 *
	 * @param actpa_tpa asigna el valor a la propiedad tarifa alerta collection
	 */
	public void setTarifaAlertaCollection(Collection<TarifaAlerta> actpa_tpa)
	{
		ictpa_tarifaAlertaCollection = actpa_tpa;
	}

	/**
	 * Retorna el valor de tarifa alerta collection.
	 *
	 * @return el valor de tarifa alerta collection
	 */
	public Collection<TarifaAlerta> getTarifaAlertaCollection()
	{
		return ictpa_tarifaAlertaCollection;
	}

	/**
	 * Modifica el valor de ultimo registro confirmado.
	 *
	 * @param ab_b asigna el valor a la propiedad ultimo registro confirmado
	 */
	public void setUltimoRegistroConfirmado(boolean ab_b)
	{
		if(ab_b)
			addMessage(MessagesKeys.ULTIMO_REGISTRO_CONFIRMADO);

		ib_ultimoRegistroConfirmado = ab_b;
	}

	/**
	 * Valida la propiedad ultimo registro confirmado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en ultimo registro confirmado
	 */
	public boolean isUltimoRegistroConfirmado()
	{
		return ib_ultimoRegistroConfirmado;
	}

	/**
	 * Obtiene el valor de tarifa alerta temporal.
	 *
	 * @return TarifaAlerta objeto guardado
	 */
	public TarifaAlerta getTarifaAlertaTemporal()
	{
		return ita_tarifaAlertaTemporal;
	}

	/**
	 * Encargado de guardar la TarifaAlerta temporal
	 *
	 * @param ata_ta TarifaAlerta objeto a guardar temporalmente.
	 */
	public void setTarifaAlertaTemporal(TarifaAlerta ata_ta)
	{
		ita_tarifaAlertaTemporal = ata_ta;
	}

	/**
	 * Método encargado de agregar un registro y modificar los demás.
	 *
	 * @param atpa_tpa Objeto TarifaAlerta con la ubicacion de agregar el siguiente registro
	 */
	public void agregarRegistro(TarifaAlerta atpa_tpa)
	{
		if(atpa_tpa != null)
		{
			Collection<TarifaAlerta> lctpa_cllActual;

			lctpa_cllActual = getTarifaAlertaCollection();

			if(CollectionUtils.isValidCollection(lctpa_cllActual))
			{
				Collection<TarifaAlerta> lctpa_cllTempInicial;
				Collection<TarifaAlerta> lctpa_cllTempFinal;
				TarifaAlerta             ltpa_ultimoObjetocllTempInicial;
				boolean                  ib_encontrado;

				lctpa_cllTempInicial                = new ArrayList<TarifaAlerta>();
				lctpa_cllTempFinal                  = new ArrayList<TarifaAlerta>();
				ltpa_ultimoObjetocllTempInicial     = new TarifaAlerta();
				ib_encontrado                       = false;

				for(TarifaAlerta ltpa_tpaTemp : lctpa_cllActual)
				{
					if(ltpa_tpaTemp != null)
					{
						if(!ib_encontrado)
						{
							if(ltpa_tpaTemp != atpa_tpa)
								lctpa_cllTempInicial.add(ltpa_tpaTemp);
							else
							{
								lctpa_cllTempInicial.add(ltpa_tpaTemp);
								ltpa_ultimoObjetocllTempInicial     = ltpa_tpaTemp;
								ib_encontrado                       = true;
							}
						}
						else
							lctpa_cllTempFinal.add(ltpa_tpaTemp);
					}
				}

				if(CollectionUtils.isValidCollection(lctpa_cllTempInicial) && (ltpa_ultimoObjetocllTempInicial != null))
				{
					TarifaAlerta ltpa_nuevoObjetoIngresado;
					int          li_id;
					long         li_cantidadInicial;
					long         li_cantidadFinal;

					ltpa_nuevoObjetoIngresado     = new TarifaAlerta();
					li_id                         = NumericUtils.getInt(ltpa_ultimoObjetocllTempInicial.getIdTarifa());
					li_cantidadInicial            = ltpa_ultimoObjetocllTempInicial.getCantidadFinalMatriculas();
					li_cantidadFinal              = 0;

					li_id++;
					li_cantidadInicial++;
					li_cantidadFinal = NumericUtils.getInt(li_cantidadInicial);
					li_cantidadFinal++;

					ltpa_nuevoObjetoIngresado.setIdTarifa(StringUtils.getString(li_id));
					ltpa_nuevoObjetoIngresado.setCantidadInicialMatriculas(li_cantidadInicial);
					ltpa_nuevoObjetoIngresado.setCantidadFinalMatriculas(li_cantidadFinal);
					ltpa_nuevoObjetoIngresado.setValorTarifa(0);

					if(isSoloExisteUnRegistro())
					{
						ltpa_nuevoObjetoIngresado.setMostrarCamposCantidadFAgregar(false);
						ltpa_nuevoObjetoIngresado.setBloquearCasillaUltimoRegistro(false);
						ltpa_ultimoObjetocllTempInicial.setBloquearCasillaUltimoRegistro(true);
						setSoloExisteUnRegistro(false);
					}
					else
					{
						ltpa_nuevoObjetoIngresado.setMostrarCamposCantidadFAgregar(true);
						ltpa_nuevoObjetoIngresado.setBloquearCasillaUltimoRegistro(true);
					}

					lctpa_cllTempInicial.add(ltpa_nuevoObjetoIngresado);

					boolean lb_primero;

					lb_primero = true;

					for(TarifaAlerta ltpa_tpaTemp : lctpa_cllTempFinal)
					{
						if(ltpa_tpaTemp != null)
						{
							if(lb_primero)
							{
								int  li_idTemp;
								long ll_cantidadInicialTemp;
								long ll_cantidadFinalActual;

								li_idTemp                  = NumericUtils.getInt(ltpa_tpaTemp.getIdTarifa());
								ll_cantidadInicialTemp     = ltpa_nuevoObjetoIngresado.getCantidadFinalMatriculas();
								ll_cantidadFinalActual     = ltpa_tpaTemp.getCantidadFinalMatriculas();

								li_idTemp++;
								ll_cantidadInicialTemp++;

								ltpa_tpaTemp.setIdTarifa(StringUtils.getString(li_idTemp));
								ltpa_tpaTemp.setCantidadInicialMatriculas(ll_cantidadInicialTemp);

								if(ll_cantidadFinalActual <= ll_cantidadInicialTemp)
									ltpa_tpaTemp.setCantidadFinalMatriculas(ll_cantidadInicialTemp + 1);

								lctpa_cllTempInicial.add(ltpa_tpaTemp);

								li_cantidadFinal     = ltpa_tpaTemp.getCantidadFinalMatriculas();
								lb_primero           = false;
							}
							else
							{
								int  li_idTemp;
								long ll_cantidadInicialTemp;
								long ll_cantidadFinalActual;

								li_idTemp                  = NumericUtils.getInt(ltpa_tpaTemp.getIdTarifa());
								ll_cantidadInicialTemp     = li_cantidadFinal;
								ll_cantidadFinalActual     = ltpa_tpaTemp.getCantidadFinalMatriculas();

								li_idTemp++;
								ll_cantidadInicialTemp++;

								ltpa_tpaTemp.setIdTarifa(StringUtils.getString(li_idTemp));
								ltpa_tpaTemp.setCantidadInicialMatriculas(ll_cantidadInicialTemp);

								if(ll_cantidadFinalActual <= ll_cantidadInicialTemp)
									ltpa_tpaTemp.setCantidadFinalMatriculas(ll_cantidadInicialTemp + 1);

								lctpa_cllTempInicial.add(ltpa_tpaTemp);

								li_cantidadFinal = ltpa_tpaTemp.getCantidadFinalMatriculas();
							}
						}
					}

					setTarifaAlertaCollection(lctpa_cllTempInicial);
					addMessage(MessagesKeys.REGISTRO_AGREGADO_CORRECTAMENTE);
				}
			}
		}
	}

	/**
	 * Método encargado de prender booleana del registro para editarlo e inactivar las demás
	 *
	 * @param atpa_tpa Objeto TarifaAlerta a editar
	 */
	public void modificarRegistro(TarifaAlerta atpa_tpa)
	{
		if(atpa_tpa != null)
		{
			Collection<TarifaAlerta> lctpa_cllActual;

			lctpa_cllActual = getTarifaAlertaCollection();

			if(CollectionUtils.isValidCollection(lctpa_cllActual))
			{
				for(TarifaAlerta lta_tarifaAlerta : lctpa_cllActual)
				{
					if(lta_tarifaAlerta != null)
					{
						if(lta_tarifaAlerta == atpa_tpa)
							lta_tarifaAlerta.setEditable(true);
						else
							lta_tarifaAlerta.setEditable(false);
					}
				}
			}
		}
	}

	/**
	 * Metodo que se encarga de cargar variables usado al entrar a la pantalla y al darle lick a cancelar.
	 */
	public void cargarData()
	{
		Collection<TarifaAlerta> lctpa_temp;

		lctpa_temp = new ArrayList<TarifaAlerta>();

		try
		{
			lctpa_temp = ipr_parameterRemote.findAllTarifaAlertaActivo();

			if(CollectionUtils.isValidCollection(lctpa_temp))
			{
				Iterator<TarifaAlerta> litpa_iterator;

				litpa_iterator = lctpa_temp.iterator();

				if(litpa_iterator != null)
				{
					TarifaAlerta ltpa_tarifasParaAlertasUltimo;

					ltpa_tarifasParaAlertasUltimo = new TarifaAlerta();

					while(litpa_iterator.hasNext())
					{
						TarifaAlerta ltpa_tarifasParaAlertas;

						ltpa_tarifasParaAlertas = litpa_iterator.next();

						if(ltpa_tarifasParaAlertas != null)
						{
							ltpa_tarifasParaAlertas.setMostrarCamposCantidadFAgregar(true);
							ltpa_tarifasParaAlertas.setBloquearCasillaUltimoRegistro(true);
							ltpa_tarifasParaAlertasUltimo = ltpa_tarifasParaAlertas;
						}
					}

					ltpa_tarifasParaAlertasUltimo.setMostrarCamposCantidadFAgregar(false);
					ltpa_tarifasParaAlertasUltimo.setBloquearCasillaUltimoRegistro(false);
					setUltimoRegistroConfirmado(false);
				}
			}
			else
			{
				lctpa_temp = new ArrayList<TarifaAlerta>();

				TarifaAlerta    lapa_tarifasParaAlertas;
				ExternalContext lec_externalContext;
				long            ll_long;

				lapa_tarifasParaAlertas     = new TarifaAlerta();
				lec_externalContext         = FacesContext.getCurrentInstance().getExternalContext();
				ll_long                     = 1;

				lapa_tarifasParaAlertas.setIdTarifa(StringUtils.getString(ll_long));
				lapa_tarifasParaAlertas.setCantidadInicialMatriculas(ll_long);
				lapa_tarifasParaAlertas.setCantidadFinalMatriculas(2);
				lapa_tarifasParaAlertas.setValorTarifa(0);
				lapa_tarifasParaAlertas.setMostrarCamposCantidadFAgregar(true);
				lapa_tarifasParaAlertas.setUltimoRegistro(false);
				lapa_tarifasParaAlertas.setBloquearCasillaUltimoRegistro(false);

				lctpa_temp.add(lapa_tarifasParaAlertas);

				setSoloExisteUnRegistro(true);
				setUltimoRegistroConfirmado(false);

				addMessage(MessagesKeys.SIN_REGISTROS_TARIFAS_PARA_ALERTAS);

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
					{
						lf_flash.setKeepMessages(true);
						PrimeFaces.current().ajax().update("fTarifaAlerta:globalMsg");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		setTarifaAlertaCollection(lctpa_temp);
	}

	/**
	 * Metodo que se encarga de reiniciar variables.
	 */
	public void clear()
	{
		setUltimoRegistroConfirmado(false);
		setSoloExisteUnRegistro(false);
	}

	/**
	 * Método encargado de agregar un registro y modificar los demás.
	 *
	 * @param ltpa_tpa Objeto TarifaAlerta con la ubcacion de agregar el siguiente registro
	 */
	public void eliminarRegistro()
	{
		try
		{
			TarifaAlerta ltpa_tpa;

			ltpa_tpa = getTarifaAlertaTemporal();

			if(ltpa_tpa != null)
			{
				Collection<TarifaAlerta> lctpa_cllActual;

				lctpa_cllActual = getTarifaAlertaCollection();

				if(CollectionUtils.isValidCollection(lctpa_cllActual))
				{
					if(lctpa_cllActual.size() == 1)
						throw new B2BException(ErrorKeys.TARIFAS_PARA_ALERTAS_BORRAR);

					if(lctpa_cllActual.size() == 2)
						throw new B2BException(ErrorKeys.TARIFAS_PARA_ALERTAS_BORRAR_DOS);

					Collection<TarifaAlerta> lctpa_cllTempInicial;
					Collection<TarifaAlerta> lctpa_cllTempFinal;
					TarifaAlerta             ltpa_ultimoObjetocllTempInicial;
					boolean                  ib_encontrado;

					lctpa_cllTempInicial                = new ArrayList<TarifaAlerta>();
					lctpa_cllTempFinal                  = new ArrayList<TarifaAlerta>();
					ltpa_ultimoObjetocllTempInicial     = null;
					ib_encontrado                       = false;

					for(TarifaAlerta ltpa_tpaTemp : lctpa_cllActual)
					{
						if(ltpa_tpaTemp != null)
						{
							if(!ib_encontrado)
							{
								if(ltpa_tpaTemp != ltpa_tpa)
								{
									lctpa_cllTempInicial.add(ltpa_tpaTemp);
									ltpa_ultimoObjetocllTempInicial = ltpa_tpaTemp;
								}
								else
									ib_encontrado = true;
							}
							else
								lctpa_cllTempFinal.add(ltpa_tpaTemp);
						}
					}

					if(ltpa_ultimoObjetocllTempInicial != null)
					{
						boolean ab_primerRegistrocllTempFinal;
						long    ll_cantidadFinalDelAnterior;

						ab_primerRegistrocllTempFinal     = true;
						ll_cantidadFinalDelAnterior       = 0;

						for(TarifaAlerta ltpa_tpaTemp : lctpa_cllTempFinal)
						{
							if(ltpa_tpaTemp != null)
							{
								if(ab_primerRegistrocllTempFinal)
								{
									int  li_id;
									long ll_cantidadInicial;

									li_id                  = NumericUtils.getInt(
										    ltpa_ultimoObjetocllTempInicial.getIdTarifa()
										);
									ll_cantidadInicial     = ltpa_ultimoObjetocllTempInicial.getCantidadFinalMatriculas();

									li_id++;
									ll_cantidadInicial++;

									ltpa_tpaTemp.setIdTarifa(StringUtils.getString(li_id));
									ltpa_tpaTemp.setCantidadInicialMatriculas(ll_cantidadInicial);

									lctpa_cllTempInicial.add(ltpa_tpaTemp);

									ll_cantidadFinalDelAnterior     = ltpa_tpaTemp.getCantidadFinalMatriculas();

									ab_primerRegistrocllTempFinal = false;
								}
								else
								{
									int li_id;

									li_id = NumericUtils.getInt(ltpa_tpaTemp.getIdTarifa());

									li_id--;
									ll_cantidadFinalDelAnterior++;

									ltpa_tpaTemp.setIdTarifa(StringUtils.getString(li_id));
									ltpa_tpaTemp.setCantidadInicialMatriculas(ll_cantidadFinalDelAnterior);

									lctpa_cllTempInicial.add(ltpa_tpaTemp);

									ll_cantidadFinalDelAnterior = ltpa_tpaTemp.getCantidadFinalMatriculas();
								}
							}
						}

						setTarifaAlertaCollection(lctpa_cllTempInicial);
					}
					else
					{
						boolean      lb_primeroAEliminar;
						boolean      lb_segundoAModificar;
						TarifaAlerta ltpa_objetoAEliminar;

						lb_primeroAEliminar      = true;
						lb_segundoAModificar     = true;
						ltpa_objetoAEliminar     = null;

						for(TarifaAlerta ltpa_tpaTemp : lctpa_cllActual)
						{
							if(ltpa_tpaTemp != null)
							{
								if(lb_primeroAEliminar)
								{
									ltpa_objetoAEliminar     = ltpa_tpaTemp;

									lb_primeroAEliminar = false;
								}
								else
								{
									int li_id;

									li_id = NumericUtils.getInt(ltpa_tpaTemp.getIdTarifa());

									li_id--;

									ltpa_tpaTemp.setIdTarifa(StringUtils.getString(li_id));

									if(lb_segundoAModificar)
									{
										long ll_cantidadInicialPrimero;

										ll_cantidadInicialPrimero = 1;

										ltpa_tpaTemp.setCantidadInicialMatriculas(ll_cantidadInicialPrimero);

										lb_segundoAModificar = false;
									}
								}
							}
						}

						lctpa_cllActual.remove(ltpa_objetoAEliminar);
					}

					addMessage(MessagesKeys.REGISTRO_ELIMINADO_CORRECTAMENTE);
					PrimeFaces.current().ajax().update("fTarifasParaAlertas");
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fTarifasParaAlertas:globalMsg");
		}
	}

	/**
	 * Metodo encargado de guardar el proceso.
	 */
	public void guardar()
	{
		try
		{
			Collection<TarifaAlerta> lctpa_cll;

			lctpa_cll = getTarifaAlertaCollection();

			if(CollectionUtils.isValidCollection(lctpa_cll))
			{
				if(isUltimoRegistroConfirmado())
				{
					ipr_parameterRemote.salvarTarifaAlerta(
					    lctpa_cll, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					for(TarifaAlerta lta_tarifaAlerta : lctpa_cll)
					{
						if(lta_tarifaAlerta != null)
						{
							String ls_valorTarifaAlertaString;

							ls_valorTarifaAlertaString = StringUtils.getString(lta_tarifaAlerta.getValorTarifa());

							if(StringUtils.isValidString(ls_valorTarifaAlertaString))
								lta_tarifaAlerta.setValorTarifaString(ls_valorTarifaAlertaString);
						}
					}

					PrimeFaces.current().executeScript("PF('cdrResumen').show();");

					addMessage(MessagesKeys.SALVAR_TARIFAS_PARA_ALERTAS);
				}
				else
					throw new B2BException(ErrorKeys.DEBE_CONFIRMAR_ULTIMO_REGISTRO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para revisar si la cantidad final ingresada de un registro es maytor a la actual, si true, mensaje de error.
	 *
	 * @param atpa_tpa correspondiente al valor del tipo de objeto TarifaAlerta
	 */
	public void revisarCantidadFinalIngresada(TarifaAlerta atpa_tpa)
	{
		try
		{
			if(atpa_tpa != null)
			{
				Collection<TarifaAlerta> lctpa_cllActual;
				TarifaAlerta             ltpa_temporal;
				Collection<TarifaAlerta> lctpa_cllTempFinal;

				lctpa_cllActual        = getTarifaAlertaCollection();
				ltpa_temporal          = new TarifaAlerta();
				lctpa_cllTempFinal     = new ArrayList<TarifaAlerta>();

				if(CollectionUtils.isValidCollection(lctpa_cllActual))
				{
					long    ll_cantidadFinalIngresada;
					long    ll_cantidadFinalActual;
					boolean lb_encontrado;

					ll_cantidadFinalIngresada     = atpa_tpa.getCantidadFinalMatriculas();
					ll_cantidadFinalActual        = 0;
					lb_encontrado                 = false;

					for(TarifaAlerta ltpa_tpaTemp : lctpa_cllActual)
					{
						if(ltpa_tpaTemp != null)
						{
							if(!lb_encontrado)
							{
								if(ltpa_tpaTemp != atpa_tpa)
									ltpa_temporal = ltpa_tpaTemp;
								else
									lb_encontrado = true;
							}
							else
								lctpa_cllTempFinal.add(ltpa_tpaTemp);
						}
					}

					TarifaAlerta ltpa_primeroEnLista;
					boolean      lb_primeroEnLista;
					boolean      lb_error;

					ltpa_primeroEnLista     = new TarifaAlerta();
					lb_primeroEnLista       = false;
					lb_error                = false;

					if(!isSoloExisteUnRegistro())
						ll_cantidadFinalActual = ltpa_temporal.getCantidadFinalMatriculas();
					else
						ll_cantidadFinalActual = 1;

					{
						long ll_biCantidadInicialRegistro;

						ll_biCantidadInicialRegistro = atpa_tpa.getCantidadInicialMatriculas();

						if((ll_cantidadFinalIngresada >= 0) && (ll_biCantidadInicialRegistro > 0))
						{
							if(ll_cantidadFinalActual > 0)
							{
								if(
								    (ll_cantidadFinalIngresada <= ll_cantidadFinalActual)
									    || (ll_cantidadFinalIngresada == ll_biCantidadInicialRegistro)
								)
								{
									ll_cantidadFinalActual = ll_cantidadFinalActual + 2;

									atpa_tpa.setCantidadFinalMatriculas(ll_cantidadFinalActual);
									lb_error = true;
								}
							}
							else
							{
								if((ll_cantidadFinalIngresada <= ll_biCantidadInicialRegistro))
								{
									long ll_cantidadFinalTerminada;

									ll_cantidadFinalTerminada = ll_biCantidadInicialRegistro;
									ll_cantidadFinalTerminada++;

									atpa_tpa.setCantidadFinalMatriculas(ll_cantidadFinalTerminada);
									lb_error = true;
								}
							}
						}
					}

					for(TarifaAlerta ltpa_tpaTemp : lctpa_cllTempFinal)
					{
						if((ltpa_tpaTemp != null) && !lb_primeroEnLista)
						{
							ltpa_primeroEnLista     = ltpa_tpaTemp;
							lb_primeroEnLista       = true;
						}
					}

					long ll_cantInicialNueva;

					ll_cantInicialNueva = atpa_tpa.getCantidadFinalMatriculas();

					ll_cantInicialNueva++;

					ltpa_primeroEnLista.setCantidadInicialMatriculas(ll_cantInicialNueva);

					if(lb_error)
					{
						if(!isSoloExisteUnRegistro())
							throw new B2BException(ErrorKeys.CANTIDAD_FINAL_MAYOR);
						else
						{
							long ll_cantFinal;

							ll_cantFinal = 2;

							atpa_tpa.setCantidadFinalMatriculas(ll_cantFinal);
							throw new B2BException(ErrorKeys.CANTIDAD_FINAL_PRIMER_REGISTRO);
						}
					}
					else
						addMessage(MessagesKeys.CANTIDAD_INICIAL_SIGUIENTE);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
	}
}
