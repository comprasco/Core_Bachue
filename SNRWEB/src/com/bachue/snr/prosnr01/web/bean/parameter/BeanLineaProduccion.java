package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;

import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.reference.BeanReference;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanLineaProduccion.
 *
 * @author Julian Vaca
 */
@ManagedBean(name = "beanLineaProduccion")
@SessionScoped
public class BeanLineaProduccion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7979588776353642396L;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad lcta tipos actos. */
	private Collection<TipoActo> icta_tiposActos;

	/** Propiedad lcta tipos actos selected. */
	private Collection<TipoActo> icta_tiposActosSelected;

	/** Propiedad ilp parametros. */
	private LineaProduccion ilp_parametros;

	/** Propiedad il equivalencia turno. */
	private Long il_equivalenciaTurno;

	/** Propiedad il id etapa. */
	private Long il_idEtapa;

	/** Propiedad il peso. */
	private Long il_peso;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad ib insertar. */
	private boolean ib_insertar;

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
	 * Modifica el valor de equivalencia turno.
	 *
	 * @param al_l asigna el valor a la propiedad equivalencia turno
	 */
	public void setEquivalenciaTurno(Long al_l)
	{
		il_equivalenciaTurno = al_l;
	}

	/**
	 * Retorna el valor de equivalencia turno.
	 *
	 * @return el valor de equivalencia turno
	 */
	public Long getEquivalenciaTurno()
	{
		return il_equivalenciaTurno;
	}

	/**
	 * Modifica el valor de IdEtapa.
	 *
	 * @param al_l de al l
	 */
	public void setIdEtapa(Long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna Objeto o variable de valor id etapa.
	 *
	 * @return Retorna el valor de la propiedad idEtapa
	 */
	public Long getIdEtapa()
	{
		return il_idEtapa;
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
	 * @param ac_c asigna el valor a la propiedad parametros
	 */
	public void setParametros(LineaProduccion ac_c)
	{
		ilp_parametros = ac_c;
	}

	/**
	 * Retorna el valor de parametros.
	 *
	 * @return el valor de parametros
	 */
	public LineaProduccion getParametros()
	{
		if(ilp_parametros == null)
			ilp_parametros = new LineaProduccion();

		return ilp_parametros;
	}

	/**
	 * Modifica el valor de peso.
	 *
	 * @param al_l asigna el valor a la propiedad peso
	 */
	public void setPeso(Long al_l)
	{
		il_peso = al_l;
	}

	/**
	 * Retorna el valor de peso.
	 *
	 * @return el valor de peso
	 */
	public Long getPeso()
	{
		return il_peso;
	}

	/**
	 * Modifica el valor de TiposActos.
	 *
	 * @param ac_c de ac c
	 */
	public void setTiposActos(Collection<TipoActo> ac_c)
	{
		icta_tiposActos = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor tipos actos.
	 *
	 * @return Retorna el valor de la propiedad tiposActos
	 */
	public Collection<TipoActo> getTiposActos()
	{
		return icta_tiposActos;
	}

	/**
	 * Modifica el valor de TiposActos selected.
	 *
	 * @param ac_c de ac c
	 */
	public void setTiposActosSelected(Collection<TipoActo> ac_c)
	{
		this.icta_tiposActosSelected = ac_c;
	}

	/**
	 * Retorna Objeto o variable de valor tipos actos selected.
	 *
	 * @return Retorna el valor de la propiedad tiposActos
	 */
	public Collection<TipoActo> getTiposActosSelected()
	{
		if(!CollectionUtils.isValidCollection(icta_tiposActosSelected))
			icta_tiposActosSelected = new ArrayList<TipoActo>();

		return icta_tiposActosSelected;
	}

	/**
	 * Actualiza el estado de los tipo acto.
	 *
	 */
	public void actualizarTipoActo(String as_tipoActo)
	{
		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pActualizar");

		if(lb_parametro != null)
		{
			boolean              lb_estado;
			Collection<TipoActo> lcta_temp;
			Collection<TipoActo> lcta_selected;

			lb_estado     = BooleanUtils.getBooleanValue(lb_parametro);

			lcta_temp         = getTiposActos();
			lcta_selected     = getTiposActosSelected();

			for(TipoActo lta_iterador : lcta_temp)
			{
				if(lta_iterador != null)
				{
					try
					{
						String ls_lineaProduccion;

						ls_lineaProduccion = lta_iterador.getLineaProduccion();

						if(lta_iterador.getIdTipoActo().equals(as_tipoActo))
						{
							if(lb_estado)
							{
								if(StringUtils.isValidString(ls_lineaProduccion))
								{
									LineaProduccion llp_linea;
									Object[]        loa_arg;

									loa_arg       = new String[1];
									llp_linea     = new LineaProduccion();
									llp_linea.setIdLineaProduccion(ls_lineaProduccion);

									llp_linea     = ipr_parameterRemote.findLineaProduccionById(llp_linea);

									loa_arg[0] = llp_linea.getNombre();

									throw new B2BException(ErrorKeys.ERROR_YA_TIENE_LINEA_PRODUCCION_ASOCIADA, loa_arg);
								}

								lta_iterador.setActoSelected(lb_estado);
								lcta_selected.add(lta_iterador);
							}
							else
							{
								LineaProduccion llp_lineaActual;

								llp_lineaActual = getParametros();

								if(llp_lineaActual != null)
								{
									String ls_lineaActual;

									ls_lineaActual = llp_lineaActual.getIdLineaProduccion();

									if(
									    StringUtils.isValidString(ls_lineaActual)
										    && !ls_lineaProduccion.equals(ls_lineaActual)
									)
									{
										LineaProduccion llp_linea;
										Object[]        loa_arg;

										loa_arg       = new String[1];
										llp_linea     = new LineaProduccion();
										llp_linea.setIdLineaProduccion(ls_lineaProduccion);

										llp_linea     = ipr_parameterRemote.findLineaProduccionById(llp_linea);

										loa_arg[0] = llp_linea.getNombre();

										throw new B2BException(
										    ErrorKeys.ERROR_YA_TIENE_LINEA_PRODUCCION_ASOCIADA, loa_arg
										);
									}
								}

								lta_iterador.setActoSelected(lb_estado);
								lcta_selected.add(lta_iterador);
							}
						}
					}
					catch(B2BException lb2be_e)
					{
						addMessage(lb2be_e);
					}
				}
			}
		}
	}

	/**
	 * Cambiar estado.
	 */
	public void cambiarEstado()
	{
		Boolean lb_parametro;

		lb_parametro = FacesUtils.getBooleanFacesParameter("pInsertar");

		if(lb_parametro != null)
		{
			boolean lb_estado;

			lb_estado = BooleanUtils.getBooleanValue(lb_parametro);

			if(lb_estado)
			{
				setParametros(null);
				setPeso(null);
				setEquivalenciaTurno(null);
				setIdEtapa(null);
			}

			setInsertar(lb_estado);

			try
			{
				Collection<TipoActo> lcta_tipoActo;

				lcta_tipoActo = ipr_parameterRemote.findAllTiposActoAplicaDesborde(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				setTiposActos(lcta_tipoActo);
				setTiposActosSelected(null);
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setTiposActos(null);
		setParametros(null);
		setInsertar(false);
		setPeso(null);
		setEquivalenciaTurno(null);
		setIdEtapa(null);
	}

	/**
	 * Consulta detallada.
	 */
	public void consultaDetallada()
	{
		try
		{
			LineaProduccion llp_parametros;
			llp_parametros = getParametros();

			if(llp_parametros != null)
			{
				String ls_idLineaProduccion;
				ls_idLineaProduccion = FacesUtils.getStringFacesParameter("idLineaProduccion");

				if(StringUtils.isValidString(ls_idLineaProduccion))
				{
					LineaProduccion llp_datos;

					llp_parametros.setIdLineaProduccion(ls_idLineaProduccion);

					llp_datos = ipr_parameterRemote.findLineaProduccionById(llp_parametros);

					if(llp_datos != null)
					{
						long ll_idEtapa;

						ll_idEtapa = NumericUtils.getLong(llp_datos.getIdEtapa());

						setPeso(NumericUtils.getLongWrapper(llp_datos.getPeso()));
						setEquivalenciaTurno(NumericUtils.getLongWrapper(llp_datos.getEquivalenciaTurno()));
						setIdEtapa(llp_datos.getIdEtapa());

						if(ll_idEtapa == EtapaCommon.ID_ETAPA_CALIFICACION)
						{
							Collection<TipoActo> lcta_tipoActo;
							lcta_tipoActo = ipr_parameterRemote.findAllTiposActoAplicaDesborde(
								    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
								);

							setTiposActos(lcta_tipoActo);
							setTiposActosSelected(null);
						}

						setParametros(llp_datos);
					}

					setInsertar(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 */
	public Collection<LineaProduccion> findAllLineaProduccion()
	{
		Collection<LineaProduccion> llp_datos;
		llp_datos = null;

		try
		{
			llp_datos = ipr_parameterRemote.findAllLineasProduccion();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return llp_datos;
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String salvar()
	{
		String  ls_return;
		boolean lb_focus;
		ls_return     = NavegacionCommon.LINEA_PRODUCCION;
		lb_focus      = true;

		try
		{
			LineaProduccion llp_parametros;
			FacesContext    lfc_context;

			llp_parametros     = getParametros();
			lfc_context        = FacesContext.getCurrentInstance();

			if(llp_parametros != null)
			{
				{
					String ls_tmp;
					ls_tmp     = llp_parametros.getNombre();

					lb_focus = validateStyles(":fLineaProduccionDetalle:itNombre", lfc_context, ls_tmp, lb_focus);

					if(!StringUtils.isValidString(ls_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_NOMBRE_LINEA_PRODUCCION);
				}

				{
					Long ll_tmp;

					ll_tmp     = getPeso();

					lb_focus = validateStyles(":fLineaProduccionDetalle:inPeso", lfc_context, ll_tmp, lb_focus);

					if(!NumericUtils.isValidLong(ll_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PESO_LINEA_PRODUCCION);

					llp_parametros.setPeso(NumericUtils.getLong(ll_tmp));
				}

				{
					Long ll_tmp;

					ll_tmp     = getEquivalenciaTurno();

					lb_focus = validateStyles(
						    ":fLineaProduccionDetalle:inEquivalenciaTurno", lfc_context, ll_tmp, lb_focus
						);

					if(!NumericUtils.isValidLong(ll_tmp))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_EQUIVALENCIA_TURNO_LINEA_PRODUCCION);

					llp_parametros.setEquivalenciaTurno(NumericUtils.getLong(ll_tmp));
				}

				{
					Long ll_tmp;

					ll_tmp     = getIdEtapa();

					lb_focus = validateStyles(":fLineaProduccionDetalle:somIdEtapa", lfc_context, ll_tmp, lb_focus);

					if(!NumericUtils.isValidLong(ll_tmp))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ETAPA);

					llp_parametros.setIdEtapa(ll_tmp);
				}

				llp_parametros.setTiposActos(getTiposActosSelected());

				ipr_parameterRemote.salvarLineaProduccion(
				    llp_parametros, isInsertar(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				{
					BeanReference lbr_beanReference;

					lbr_beanReference = getBeanReference();

					lbr_beanReference.setRol(null);
					lbr_beanReference.setCodigo4Y5Digitos(null);
				}

				setParametros(null);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			ls_return = null;
		}

		return ls_return;
	}
}
