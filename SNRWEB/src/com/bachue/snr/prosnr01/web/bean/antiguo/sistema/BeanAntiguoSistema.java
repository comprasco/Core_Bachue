package com.bachue.snr.prosnr01.web.bean.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanAntiguoSistema.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanAntiguoSistema")
public class BeanAntiguoSistema extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 813982364361051531L;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad ictc datos tramite cantidad. */
	private Collection<TramiteCantidad> ictc_datosTramiteCantidad;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/** Propiedad id Etapa. */
	private long il_idEtapa;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos tramite cantidad.
	 *
	 * @param adtc_dtc asigna el valor a la propiedad datos tramite cantidad
	 */
	public void setDatosTramiteCantidad(Collection<TramiteCantidad> adtc_dtc)
	{
		ictc_datosTramiteCantidad = adtc_dtc;
	}

	/**
	 * Retorna el valor de datos tramite cantidad.
	 *
	 * @return el valor de datos tramite cantidad
	 */
	public Collection<TramiteCantidad> getDatosTramiteCantidad()
	{
		return ictc_datosTramiteCantidad;
	}

	/**
	 * Modifica el valor de id turno consulta.
	 *
	 * @param as_s asigna el valor a la propiedad id turno consulta
	 */
	public void setIdTurnoConsulta(String as_s)
	{
		is_idTurnoConsulta = as_s;
	}

	/**
	 * Retorna el valor de id turno consulta.
	 *
	 * @return el valor de id turno consulta
	 */
	public String getIdTurnoConsulta()
	{
		return is_idTurnoConsulta;
	}

	/**
	 * Modifica el valor de nir consulta.
	 *
	 * @param as_s asigna el valor a la propiedad nir consulta
	 */
	public void setNirConsulta(String as_s)
	{
		is_nirConsulta = as_s;
	}

	/**
	 * Retorna el valor de nir consulta.
	 *
	 * @return el valor de nir consulta
	 */
	public String getNirConsulta()
	{
		return is_nirConsulta;
	}

	/**
	 * Modifica el valor de total bandeja.
	 *
	 * @param ai_i asigna el valor a la propiedad total bandeja
	 */
	public void setTotalBandeja(int ai_i)
	{
		ii_totalBandeja = ai_i;
	}

	/**
	 * Retorna el valor de total bandeja.
	 *
	 * @return el valor de total bandeja
	 */
	public int getTotalBandeja()
	{
		return ii_totalBandeja;
	}

	/**
	 * @return Retorna el valor de la propiedad il_idEtapa
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/**
	 * @param Modifica el valor de la propiedad il_idEtapa por al_l
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setNirConsulta(null);
		setIdTurnoConsulta(null);
		ictc_datosTramiteCantidad = null;
		setTotalBandeja(0);
		setIdEtapa(0);
	}

	/**
	 * Busca en la base de datos todos los tramites disponibles para el usuario por cada etapa de
	 * antiguo sistema.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		try
		{
			int    li_cantidadPorEtapas;
			String ls_nir;

			ls_nir                   = getNirConsulta();
			li_cantidadPorEtapas     = 0;

			if(StringUtils.isValidString(ls_nir))
				ls_nir = ls_nir.toUpperCase();

			setDatosTramiteCantidad(
			    iasr_antiguoSistemaRemote.findInboxByUserId(getUserId(), getIdTurnoConsulta(), ls_nir, getIdEtapa())
			);

			if(CollectionUtils.isValidCollection(ictc_datosTramiteCantidad))
			{
				for(TramiteCantidad ltc_actual : ictc_datosTramiteCantidad)
					li_cantidadPorEtapas = li_cantidadPorEtapas
						+ (NumericUtils.isValidInteger(ltc_actual.getCantidad(), 0)
						? NumericUtils.getInt(ltc_actual.getCantidad()) : 0);

				setTotalBandeja(li_cantidadPorEtapas);
			}

			if(li_cantidadPorEtapas <= 0)
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			else
				addMessage(MessagesKeys.CONSULTA_EXITOSA_2);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		PrimeFaces.current().ajax().update("fAntiguoSistema:idGrowl");
	}

	/**
	 * Generar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PieChartModel generarTorta()
	    throws B2BException
	{
		return getModeloTorta();
	}

	/**
	 * Mostrar torta.
	 *
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean mostrarTorta()
	    throws B2BException
	{
		return isMostrarGrafica();
	}

	/**
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}

	/**
	 * Envia la informacion de la etapa seleccionada para mostrar su detalle y los turnos disponibles en ella.
	 *
	 * @param al_idEtapa id de la etapa seleccionada en pantalla
	 * @return enlace a la pagina de detalle de la etapa
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String returnPages(Long al_idEtapa)
	    throws B2BException
	{
		if(!NumericUtils.isValidLong(al_idEtapa))
			throw new B2BException(ErrorKeys.ERROR_ETAPA_INVALIDA);

		long         ll_idEtapa;
		FacesContext lfc_context;

		ll_idEtapa      = NumericUtils.getLong(al_idEtapa);
		lfc_context     = FacesUtils.getFacesContext();

		if(ll_idEtapa > 0)
		{
			BeanDetalleAntiguoSistema lbrc_bean;
			lbrc_bean = (BeanDetalleAntiguoSistema)lfc_context.getApplication()
					                                              .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_DETALLE_ANTIGUO_SISTEMA, BeanDetalleAntiguoSistema.class
					);

			if(lbrc_bean != null)
			{
				lbrc_bean.setBandejaentrada(true);
				lbrc_bean.setConsultaIndividual(StringUtils.isValidString(getIdTurnoConsulta()));
				lbrc_bean.setData(null);
				lbrc_bean.setIdEtapa(ll_idEtapa);
				lbrc_bean.setIdTurnoHistoria(getIdTurnoConsulta());
				lbrc_bean.setNir(getNirConsulta());

				lbrc_bean.generarData();
			}
		}

		return NavegacionCommon.DETALLE_ANTIGUO_SISTEMA;
	}
}
