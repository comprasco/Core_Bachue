package com.bachue.snr.prosnr01.web.bean.digitalizacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.digitalizacion.DigitalizacionRemote;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import com.bachue.snr.prosnr01.web.bean.consulta.trazabilidad.BeanConsultaTrazabilidad;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y funcionalidad de BeanCoreBachue.
 *
 * @author hcastaneda
 */
@ManagedBean(name = "beanCoreBachue")
@SessionScoped
public class BeanCoreBachue extends BeanConsultaTrazabilidad implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 9201069019973596656L;

	/** Constante cs_ID_FORM. */
	private static final String cs_ID_FORM = "fCoreBachue";

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = cs_ID_FORM + ":idGrowl";

	/**Propiedad irr calificación remote */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ictc datos digitalizacion. */
	private Collection<TramiteCantidad> ictc_datosDigitalizacion;

	/** Propiedad idr digitalizacion remote. */
	@EJB
	private DigitalizacionRemote idr_digitalizacionRemote;

	/** Propiedad ii turnos Asg. */
	private Integer ii_turnosAsg;

	/** Propiedad ii turnos Blog. */
	private Integer ii_turnosBloq;

	/** Propiedad ii turnos Ter. */
	private Integer ii_turnosTer;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/** Propiedad ii turnos Ter. */
	private String is_observaciones;

	/** Propiedad ib mostrar Turnos. */
	private boolean ib_mostrarTurnos;

	/** Propiedad il id etapa. */
	private long il_idEtapa;

	/**
	 * Modifica el valor de datos digitalizacion.
	 *
	 * @param actc_tc asigna el valor a la propiedad datos digitalizacion
	 */
	public void setDatosDigitalizacion(Collection<TramiteCantidad> actc_tc)
	{
		ictc_datosDigitalizacion = actc_tc;
	}

	/**
	 * Retorna el valor de datos digitalizacion.
	 *
	 * @return el valor de datos digitalizacion
	 */
	public Collection<TramiteCantidad> getDatosDigitalizacion()
	{
		return ictc_datosDigitalizacion;
	}

	/**
	 * Modifica el valor de id etapa
	 *
	 * @param al_l asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(long al_l)
	{
		il_idEtapa = al_l;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public long getIdEtapa()
	{
		return il_idEtapa;
	}

	/** {@inheritdoc} */
	public void setIdTurnoConsulta(String as_s)
	{
		is_idTurnoConsulta = as_s;
	}

	/** {@inheritdoc} */
	public String getIdTurnoConsulta()
	{
		return is_idTurnoConsulta;
	}

	/**
	 * Modifica el valor de mostrar turnos
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar turnos
	 */
	public void setMostrarTurnos(boolean ab_b)
	{
		ib_mostrarTurnos = ab_b;
	}

	/**
	 * Retorna el valor de mostrar Turnos.
	 *
	 * @return el valor de mostrar Turnos
	 */
	public boolean isMostrarTurnos()
	{
		return ib_mostrarTurnos;
	}

	/** {@inheritdoc} */
	public void setNirConsulta(String as_s)
	{
		is_nirConsulta = as_s;
	}

	/** {@inheritdoc} */
	public String getNirConsulta()
	{
		return is_nirConsulta;
	}

	/**
	 * Modifica el valor de observaciones
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
	 * Modifica el valor de turnos asg
	 *
	 * @param ai_i asigna el valor a la propiedad turnos asg
	 */
	public void setTurnosAsg(Integer ai_i)
	{
		ii_turnosAsg = ai_i;
	}

	/**
	 * Retorna el valor de Turnos asg.
	 *
	 * @return el valor de Turnos asg
	 */
	public Integer getTurnosAsg()
	{
		return ii_turnosAsg;
	}

	/**
	 * Modifica el valor de turnos blog
	 *
	 * @param ai_i asigna el valor a la propiedad turnos blog
	 */
	public void setTurnosBloq(Integer ai_i)
	{
		ii_turnosBloq = ai_i;
	}

	/**
	 * Retorna el valor de Turnos blog.
	 *
	 * @return el valor de Turnos blog
	 */
	public Integer getTurnosBloq()
	{
		return ii_turnosBloq;
	}

	/**
	 * Modifica el valor de turnos ter
	 *
	 * @param ai_i asigna el valor a la propiedad turnos ter
	 */
	public void setTurnosTer(Integer ai_i)
	{
		ii_turnosTer = ai_i;
	}

	/**
	 * Retorna el valor de Turnos Ter.
	 *
	 * @return el valor de Turnos Ter
	 */
	public Integer getTurnosTer()
	{
		return ii_turnosTer;
	}

	/**
	 * Metodo encargado de actualizar el estado de la digitalización y de abrir el hipervínculo de CAPTURE.
	 * @param atc_parametros Argumento de tipo <code>TramiteCantidad</code> que contiene los parametros a actualizar.
	 */
	public void actualizarEstadoDigitalizacion(TramiteCantidad atc_parametros)
	{
		if(atc_parametros != null)
		{
			try
			{
				PrimeFaces.current().executeScript(
				    "window.open(href ='" + atc_parametros.getUrlDigitalizacion() + "')"
				);

				{
					TurnoHistoria lth_parametros;

					lth_parametros = new TurnoHistoria();

					lth_parametros.setIdTurnoHistoria(NumericUtils.getLongWrapper(atc_parametros.getIdTurnoHistoria()));
					lth_parametros.setIdTurno(atc_parametros.getIdTurno());
					lth_parametros.setCalificacion(EstadoCommon.EN_PROCESO);

					idr_digitalizacionRemote.actualizarEstadoDigitalizacion(
					    lth_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

					realizarConsulta();
					actualizarComponente(cs_ID_FORM + ":idDtTurnos");
				}
			}
			catch(B2BException lb2be_e)
			{
				addMessage(lb2be_e);
				actualizarComponente(cs_ID_GROWL);
			}
		}
	}

	/**
	 * Metodo encargado de mostrar los turnos de un nir.
	 */
	public void cargarTurnos()
	{
		setMostrarTurnos(StringUtils.isValidString(getNir()));
	}

	/**
	 * Metodo para limpiar variables.
	 */
	public void clean()
	{
		setIdTurnoConsulta(null);
		setNir(null);
		setExpedienteConsulta(null);
		setMostrarTurnos(false);
		setMostrarGrafica(false);
	}

	/**
	 * Método encargado de consultar los turnos para un nir y estado actividad de etapas menores a las ingresadas.
	 * @return Collection<Turno> Colección de datos de tipo turnos con todos los registros encontrados.
	 */
	public Collection<Turno> consultarTurnosNirEtapaEstado()
	{
		Collection<Turno> lct_turnos;

		lct_turnos = null;

		try
		{
			Turno lt_turno;

			lt_turno = new Turno();

			{
				TurnoHistoria lth_turnoHistoria;

				lth_turnoHistoria = new TurnoHistoria();

				lth_turnoHistoria.setIdEtapa(
				    NumericUtils.getBigDecimal(EtapaCommon.ID_ETAPA_PROCESO_DE_REGISTRO_FINALIZADO)
				);
				lth_turnoHistoria.setEstadoActividad(EstadoCommon.ASIGNADA);

				lt_turno.setTurnoHistoria(lth_turnoHistoria);
			}

			lt_turno.setNir(getNir());

			lct_turnos = idr_digitalizacionRemote.consultarTurnosNirEtapaEstado(
				    lt_turno, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}

		return lct_turnos;
	}

	/**
	 * Metodo encargado de mostrar observaciones proceso anterior.
	 *
	 * @param atc_tc Argumento de tipo <code>TramiteCantidad</code> que contiene los parametros para mostrar observaciones.
	 */
	public void mostrarObservacionesProcesoAnterior(TramiteCantidad atc_tc)
	{
		if(atc_tc != null)
		{
			if(StringUtils.isValidString(atc_tc.getObservaciones()))
			{
				setObservaciones(atc_tc.getObservaciones());
				PrimeFaces.current().executeScript("PF('obsProcesoAnterior').show();");
				actualizarComponente("fDialog:id_observacionesProcesoAnterior");
			}
			else
				addMessage(MessagesKeys.SIN_OBSERVACIONES_PROCESO_ANTERIOR);

			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo para consultar la informacion y llenar la tabla de core Bachue.
	 */
	public void realizarConsulta()
	{
		try
		{
			TramiteCantidad ltc_tc;
			Turno           lt_t;

			lt_t = new Turno();

			lt_t.setNir(getNir());
			lt_t.setIdUsuarioCreacion(getUserId());

			{
				String ls_idTurno;
				String ls_id;

				ls_idTurno     = getIdTurnoConsulta();
				ls_id          = cs_ID_FORM + ":idTurno";

				validateStyles(ls_id, FacesContext.getCurrentInstance(), ls_idTurno, true);
				actualizarComponente(ls_id);

				lt_t.setIdTurno(ls_idTurno);
			}

			lt_t.setValidarTurno(true);

			ltc_tc = idr_digitalizacionRemote.findTurnos(lt_t, getUserId(), getLocalIpAddress(), getRemoteIpAddress());

			if(ltc_tc != null)
			{
				setDatosDigitalizacion(ltc_tc.getTurnos());

				if(!CollectionUtils.isValidCollection(getDatosDigitalizacion()))
					throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
				else
					actualizarComponente(cs_ID_GROWL);
			}
			else
			{
				setDatosDigitalizacion(null);
				throw new B2BException(ErrorKeys.NO_SE_ENCONTRARON_REGISTROS);
			}

			findAll();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(cs_ID_GROWL);
		}
	}
}
