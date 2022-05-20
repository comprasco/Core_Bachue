package com.bachue.snr.prosnr01.web.bean.digitador.masivo;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.ItemSelectEvent;

import org.primefaces.model.chart.PieChartModel;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanDigitadorMasivo.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanDigitadorMasivo")
public class BeanDigitadorMasivo extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 2237625330347817596L;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad ll data. */
	private List<Map<String, Object>> ll_data;

	/** Propiedad ll id etapa. */
	private Long ll_idEtapa;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Sets the data.
	 *
	 * @param all_ll correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setData(List<Map<String, Object>> all_ll)
	{
		ll_data = all_ll;
	}

	/**
	 * Retorna el valor de data.
	 *
	 * @return el valor de data
	 */
	public List<Map<String, Object>> getData()
	{
		return ll_data;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(Long al_l)
	{
		ll_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public Long getIdEtapa()
	{
		if((ll_idEtapa == null) || (NumericUtils.getLong(ll_idEtapa) == 0))
		{
			String tmp = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
					.getParameter("idEtapa");

			if(StringUtils.isValidString(tmp))
				ll_idEtapa = NumericUtils.getLongWrapper(tmp);
		}

		return ll_idEtapa;
	}

	/**
	 * Modifica el valor de id turno.
	 *
	 * @param as_s asigna el valor a la propiedad id turno
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Retorna el valor de id turno.
	 *
	 * @return el valor de id turno
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Modifica el valor de nir.
	 *
	 * @param as_s asigna el valor a la propiedad nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna el valor de nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param as_s asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Retorna el valor de observaciones.
	 *
	 * @return el valor de observaciones
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Clear.
	 */
	public void clear()
	{
		setData(null);
		setNir(null);
		setIdTurno(null);
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String detalleDigitadorMasivo()
	    throws B2BException
	{
		String       ls_result;
		FacesContext lfc_context;

		ls_result       = null;
		lfc_context     = FacesUtils.getFacesContext();

		String ls_idTurnoHistoria = FacesUtils.getStringFacesParameter("idTurnoHistoria");
		String ls_idTurno = FacesUtils.getStringFacesParameter("idTurno");

		BeanDetalleDigitadorMasivo lbddm_bean;
		lbddm_bean = (BeanDetalleDigitadorMasivo)lfc_context.getApplication()
				                                                .evaluateExpressionGet(
				    lfc_context, BeanNameCommon.BEAN_DETALLE_DIGITADOR_MASIVO, BeanDetalleDigitadorMasivo.class
				);

		if(lbddm_bean != null)
		{
			lbddm_bean.clean();
			lbddm_bean.setIdTurnoHistoria(ls_idTurnoHistoria);
			lbddm_bean.setMatriculasInformacion(null);
			lbddm_bean.setRevisionMatriculas(null);
			lbddm_bean.setTurno(ls_idTurno);
			lbddm_bean.findMatriculas(false);
			ls_result = NavegacionCommon.DETALLE_DIGITADOR_MASIVO;
		}

		{
			TurnoHistoria lth_th = new TurnoHistoria();

			lth_th.setIdTurnoHistoria(NumericUtils.getLongWrapper(ls_idTurnoHistoria));

			validarFechaInicialEtapa(lth_th);
		}

		return ls_result;
	}

	/**
	 * Generar data.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void generarData()
	    throws B2BException
	{
		List<Map<String, Object>> llmso_lm;
		llmso_lm     = getData();

		llmso_lm = iasr_antiguoSistemaRemote.findDetailInbox(getUserId(), getIdEtapa(), getIdTurno(), getNir());

		if(CollectionUtils.isValidCollection(llmso_lm))
			setData(llmso_lm);
		else
			addMessage(MessagesKeys.NO_SE_ENCONTRARON_COINCIDENCIAS);
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
	 * Item seleccionado.
	 *
	 * @param event correspondiente al valor del tipo de objeto ItemSelectEvent
	 */
	public void itemSeleccionado(ItemSelectEvent event)
	{
		itemSelect(event);
	}

	/**
	 * Método para poder guardar las observaciones del
	 * proceso anterior.
	 *
	 * @param as_observaciones correspondiente al valor del tipo de objeto String
	 */
	public void mostrarObservacionesProcesoAnterior(String as_observaciones)
	{
		if(StringUtils.isValidString(as_observaciones))
		{
			setObservaciones(as_observaciones);
			PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
			PrimeFaces.current().ajax().update("fDialogo:id_observacionesProcesoAnterior");
		}
		else
			addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);

		PrimeFaces.current().ajax().update("fTurnosDigitadorMasivo:idGrowl");
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
}
