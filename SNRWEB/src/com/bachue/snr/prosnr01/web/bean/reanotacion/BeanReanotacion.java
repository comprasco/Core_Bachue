package com.bachue.snr.prosnr01.web.bean.reanotacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reanotacion.ReanotacionRemote;

import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase para el manejo de la capa web para el trámite de reanotación.
 *
 * @author jpatino
 */
@SessionScoped
@ManagedBean(name = "beanReanotacion")
public class BeanReanotacion extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 5344798427558801509L;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ict trazabilidad. */
	private Collection<Trazabilidad> ict_trazabilidad;

	/** Propiedad ictr consulta trazabilidad remote. */
	@EJB
	private ConsultaTrazabilidadRemote ictr_consultaTrazabilidadRemote;

	/** Propiedad ilmso turnos en curso. */
	private List<Map<String, Object>> ilmso_turnosEnCurso;

	/** Propiedad irr reanotacion remote. */
	@EJB
	private ReanotacionRemote irr_reanotacionRemote;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad it trazabilidad info. */
	private Trazabilidad it_trazabilidadInfo;

	/** Propiedad ib rendered. */
	private boolean ib_rendered;

	/** Propiedad ii total bandeja. */
	private int ii_totalBandeja;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
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
	 * Modifica el valor de id turno historia.
	 *
	 * @param as_s asigna el valor a la propiedad id turno historia
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Retorna el valor de id turno historia.
	 *
	 * @return el valor de id turno historia
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
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
	 * Modifica el valor de rendered.
	 *
	 * @param ab_b asigna el valor a la propiedad rendered
	 */
	public void setRendered(boolean ab_b)
	{
		ib_rendered = ab_b;
	}

	/**
	 * Valida la propiedad rendered.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en rendered
	 */
	public boolean isRendered()
	{
		return ib_rendered;
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
	 * Modifica el valor de trazabilidad.
	 *
	 * @param act_ct asigna el valor a la propiedad trazabilidad
	 */
	public void setTrazabilidad(Collection<Trazabilidad> act_ct)
	{
		ict_trazabilidad = act_ct;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @return el valor de trazabilidad
	 */
	public Collection<Trazabilidad> getTrazabilidad()
	{
		return ict_trazabilidad;
	}

	/**
	 * Modifica el valor de trazabilidad info.
	 *
	 * @param at_t asigna el valor a la propiedad trazabilidad info
	 */
	public void setTrazabilidadInfo(Trazabilidad at_t)
	{
		it_trazabilidadInfo = at_t;
	}

	/**
	 * Retorna el valor de trazabilidad info.
	 *
	 * @return el valor de trazabilidad info
	 */
	public Trazabilidad getTrazabilidadInfo()
	{
		return it_trazabilidadInfo;
	}

	/**
	 * Sets the turnos en curso.
	 *
	 * @param almso_lmso correspondiente al valor del tipo de objeto List<Map<String,Object>>
	 */
	public void setTurnosEnCurso(List<Map<String, Object>> almso_lmso)
	{
		ilmso_turnosEnCurso = almso_lmso;
	}

	/**
	 * Retorna el valor de turnos en curso.
	 *
	 * @return el valor de turnos en curso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public List<Map<String, Object>> getTurnosEnCurso()
	    throws B2BException
	{
		return ilmso_turnosEnCurso;
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setTurnosEnCurso(null);
		setRendered(false);
		setIdTurnoConsulta(null);
		setObservaciones(null);
	}

	/**
	 * Método usado para realizar la consulta de un TurnoHistoria.
	 */
	public void consultaTurnoHistoria()
	{
		Long         ll_turnoHistoria;
		String       ls_inputTurno;
		FacesContext lfc_fc;
		String       ls_turno;

		ll_turnoHistoria     = NumericUtils.getLongWrapper(0L);
		ls_inputTurno        = "fReanotacion:idTurno";
		lfc_fc               = FacesUtils.getFacesContext();
		ls_turno             = getIdTurnoConsulta();

		try
		{
			setTurnosEnCurso(null);
			setRendered(false);
			ll_turnoHistoria = irr_reanotacionRemote.consultaByTurno(
				    ls_turno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(ll_turnoHistoria != null)
			{
				setIdTurnoHistoria(StringUtils.getString(ll_turnoHistoria));

				{
					List<Map<String, Object>> llmso_turnosAsociados;
					llmso_turnosAsociados = getTurnosEnCurso();

					if(!CollectionUtils.isValidCollection(llmso_turnosAsociados))
					{
						setTurnosEnCurso(
						    irr_calificacionRemote.findPredioDocumentoByTurno(
						        getUserId(), getIdTurnoHistoria(), IdentificadoresCommon.TURNO_CON_TRAMITES_EN_CURSO
						    )
						);
						setRendered(true);
					}
				}
			}

			{
				setTrazabilidad(null);

				Solicitud    ls_solicitud;
				Turno        lt_turno;
				Trazabilidad lt_trazabilidad;

				ls_solicitud        = new Solicitud();
				lt_turno            = new Turno();
				lt_trazabilidad     = new Trazabilidad();

				lt_turno.setIdTurno(ls_turno);

				lt_trazabilidad.setSolicitud(ls_solicitud);
				lt_trazabilidad.setTurno(lt_turno);

				setTrazabilidad(ictr_consultaTrazabilidadRemote.findTrazabilidad(lt_trazabilidad, true));

				setTrazabilidadInfo(ictr_consultaTrazabilidadRemote.findProceso(lt_trazabilidad));

				setIdTurnoConsulta(null);
				validateStyles(ls_inputTurno, lfc_fc, ls_turno, true);
			}
		}
		catch(B2BException lb2be_e)
		{
			validateStyles(ls_inputTurno, lfc_fc, "", true);
			setRendered(false);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método usado para realizar el envio a la etapa de entrega.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void enviarAEntrega()
	    throws B2BException
	{
		try
		{
			String       ls_observaciones;
			String       ls_observacionesArea;
			FacesContext lfc_fc;

			ls_observaciones         = getObservaciones();
			ls_observacionesArea     = "fReanotacion:observacionesReanotacion";
			lfc_fc                   = FacesUtils.getFacesContext();

			if(
			    StringUtils.isValidString(ls_observaciones)
				    && (ls_observaciones.length() >= ConstanteCommon.CANTIDAD_CARACTERES_OBSERVACIONES_REANOTACION)
			)
			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				validateStyles(ls_observacionesArea, lfc_fc, ls_observaciones, true);
				lth_turnoHistoria.setIdTurno(getIdTurnoConsulta());
				lth_turnoHistoria.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lth_turnoHistoria.setObservaciones(ls_observaciones);

				irr_reanotacionRemote.enviarAEntrega(
				    lth_turnoHistoria, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
				setRendered(false);
				setObservaciones(null);
				addMessage(MessagesKeys.PROCESO_COMPLETADO);
			}
			else
			{
				validateStyles(ls_observacionesArea, lfc_fc, "", true);
				throw new B2BException(ErrorKeys.ERROR_CANTIDAD_CARACTERES_OBSERVACIONES);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("fReanotacion:idGrowl");
		}
	}
}
