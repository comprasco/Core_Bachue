package com.bachue.snr.prosnr01.web.bean.reasignar;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reasignar.ReasignarTurnosRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.aut.UsuarioRol;
import com.bachue.snr.prosnr01.model.sdb.pgn.LineaProduccion;

import com.bachue.snr.prosnr01.web.bean.actuacionesAdministrativas.BeanResponsableActuacionesAdmin;
import com.bachue.snr.prosnr01.web.bean.recursos.BeanRecursos;

import java.io.Serializable;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos la funcionalidad para el Bean de asignación de turnos.
 *
 * @author hcastaneda
 */
@ManagedBean(name = "beanAsignarTurno")
@SessionScoped
public class BeanAsignarTurno extends BeanReasignarTurno implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4057714890575435152L;

	/** Constante cs_ID_FORM. */
	private static final String cs_ID_FORM = "reasignarTurnos";

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/**  Propiedad irt reasignar Turnos Remote. */
	@EJB
	private ReasignarTurnosRemote irt_reasignarTurnosRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is expediente. */
	private String is_expediente;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad mensaje predio inconsistente. */
	private String is_mensajePredioInconsistente;

	/** Propiedad is nombre circulo. */
	private String is_nombreCirculo;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is tipo expediente. */
	private String is_tipoExpediente;

	/**
	 * Propiedad il id etapa turno
	 */
	private long il_idEtapaTurno;

	/**
	 * Metodo que modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setExpediente(String as_s)
	{
		is_expediente = as_s;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getExpediente()
	{
		return is_expediente;
	}

	/**
	 * Metodo que modifica el valor de la propiedad.
	 *
	 * @param al_l Argumento que modifica el valor de la propiedad.
	 */
	public void setIdEtapaTurno(long al_l)
	{
		il_idEtapaTurno = al_l;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public long getIdEtapaTurno()
	{
		return il_idEtapaTurno;
	}

	/**
	 * Metodo que modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/**
	 * Metodo que modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getIdTurnoHistoria()
	{
		return is_idTurnoHistoria;
	}

	/**
	 * Modifica el valor de MensajePredioInconsistente.
	 *
	 * @param as_s de as s
	 */
	public void setMensajePredioInconsistente(String as_s)
	{
		is_mensajePredioInconsistente = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor mensaje predio inconsistente.
	 *
	 * @return Retorna el valor de la propiedad mensajePredioInconsistente
	 */
	public String getMensajePredioInconsistente()
	{
		return is_mensajePredioInconsistente;
	}

	/**
	 * Modifica el valor de NombreCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setNombreCirculo(String as_s)
	{
		is_nombreCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre circulo.
	 *
	 * @return Retorna el valor de la propiedad nombreCirculo
	 */
	public String getNombreCirculo()
	{
		return is_nombreCirculo;
	}

	/**
	 * Metodo que modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Metodo que modifica el valor de la propiedad.
	 *
	 * @param as_s Argumento que modifica el valor de la propiedad.
	 */
	public void setTipoExpediente(String as_s)
	{
		is_tipoExpediente = as_s;
	}

	/**
	 * Metodo que retorna el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoExpediente()
	{
		return is_tipoExpediente;
	}

	/**
	 * Método encargado de consultar los tipo expediente para la etapa actual.
	 *
	 * @return devuelve el valor de la colección de LineaProduccion
	 *
	 */
	public Collection<LineaProduccion> cargarTipoExpediente()
	{
		Collection<LineaProduccion> lclp_lineasProduccion;

		lclp_lineasProduccion = new LinkedList<LineaProduccion>();

		try
		{
			lclp_lineasProduccion = cargarTipoExpediente(getIdEtapaTurno());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lclp_lineasProduccion;
	}

	/**
	 * Enviar abogado sustanciador.
	 *
	 * @return el valor de string
	 */
	public String enviarAbogadoSustanciador()
	{
		String ls_return;

		ls_return = null;

		try
		{
			ReasignarTurnos lrt_reasignarTurnos;
			FacesContext    lfc_context;

			lrt_reasignarTurnos     = new ReasignarTurnos();
			lfc_context             = FacesContext.getCurrentInstance();

			{
				String ls_idCirculo;

				ls_idCirculo = getIdCirculo();

				validateStyles(cs_ID_FORM + ":idCirculoReasignadoA", lfc_context, ls_idCirculo, true);

				if(!StringUtils.isValidString(ls_idCirculo))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL_CONSTANCIA);

				lrt_reasignarTurnos.setIdCirculo(ls_idCirculo);
			}

			{
				String ls_idRol;

				ls_idRol = getIdRol();

				validateStyles(cs_ID_FORM + ":idRolReasignadoA", lfc_context, ls_idRol, true);

				if(!StringUtils.isValidString(ls_idRol))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);

				lrt_reasignarTurnos.setIdRol(ls_idRol);
			}

			{
				String ls_idTipoExpediente;

				ls_idTipoExpediente = getTipoExpediente();

				validateStyles(cs_ID_FORM + ":idTipoExpediente", lfc_context, ls_idTipoExpediente, true);

				if(!StringUtils.isValidString(ls_idTipoExpediente))
					throw new B2BException(ErrorKeys.ERROR_SIN_TIPO_EXPENDIENTE);

				lrt_reasignarTurnos.setIdTipoExpediente(ls_idTipoExpediente);
			}

			{
				String ls_usuarioAsignar;

				ls_usuarioAsignar = getUsuarioAsignar();

				validateStyles(cs_ID_FORM + ":idUsuarioAsignar", lfc_context, ls_usuarioAsignar, true);
				validateStyles(cs_ID_FORM + ":idUsuarioAsignar2", lfc_context, ls_usuarioAsignar, true);

				if(!StringUtils.isValidString(ls_usuarioAsignar))
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NOMBRE_USUARIO_ASIGNAR);

				lrt_reasignarTurnos.setUsuarioReasignacion(ls_usuarioAsignar);
			}

			{
				String ls_observaciones;

				ls_observaciones = getObservaciones();

				validateStyles(cs_ID_FORM + ":idITObservaciones", lfc_context, ls_observaciones, true);

				if(!StringUtils.isValidString(ls_observaciones))
					throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);
				else
				{
					String ls_mensajePredioInconsistente;
					ls_mensajePredioInconsistente = getMensajePredioInconsistente();

					if(StringUtils.isValidString(ls_mensajePredioInconsistente))
					{
						lrt_reasignarTurnos.setEsPredioInconsistente(true);

						if(!ls_observaciones.contains(ls_mensajePredioInconsistente))
							ls_observaciones = ls_mensajePredioInconsistente + ls_observaciones;
					}
				}

				lrt_reasignarTurnos.setObservaciones(ls_observaciones);
			}

			lrt_reasignarTurnos.setIdTurno(getIdTurno());
			lrt_reasignarTurnos.setIdTurnoHistoria(NumericUtils.getBigDecimal(getIdTurnoHistoria()));
			lrt_reasignarTurnos.setIdEtapa(getIdEtapaTurno());
			lrt_reasignarTurnos.setExpediente(getExpediente());

			irt_reasignarTurnosRemote.enviarAbogadoSustanciador(
			    lrt_reasignarTurnos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			limpiarAsignarTurno();

			if(getIdEtapaTurno() == EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS)
			{
				BeanRecursos lbr_bean;

				lbr_bean = (BeanRecursos)obtenerInstanciaBean(BeanRecursos.class, BeanNameCommon.BEAN_RECURSOS);

				lbr_bean.clear();
				lbr_bean.generarDatosTramiteCantidad();
				lbr_bean.generarGraficoDeTorta(
				    EtapaCommon.ID_ETAPA_ASIGNACION_DE_TURNOS_MANUAL_RECHAZO_DE_RECURSOS, false
				);

				ls_return = NavegacionCommon.BANDEJA_RECURSOS;
			}
			else
			{
				BeanResponsableActuacionesAdmin lbc_bean;

				lbc_bean = (BeanResponsableActuacionesAdmin)obtenerInstanciaBean(
					    BeanResponsableActuacionesAdmin.class, BeanNameCommon.BEAN_RESPONSABLE_ACTUACIONES_ADMIN
					);

				lbc_bean.clear();
				lbc_bean.generarDatosTramiteCantidad();
				lbc_bean.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS, false);

				ls_return = NavegacionCommon.RESPONSABLE_ACTUACIONES_ADMINISTRATIVAS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del usuario a asignar.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<UsuarioRol> findUsuarioRolByRolLineaProduccion()
	{
		Collection<UsuarioRol> lcur_usuarioRol;

		lcur_usuarioRol = new LinkedList<UsuarioRol>();

		try
		{
			lcur_usuarioRol = ipr_parameterRemote.findUsuarioRolByRolLineaProduccion(
				    getIdEtapaTurno(), getTipoExpediente(), getIdCirculo(), getIdRol(), getUserId(), getLocalIpAddress(),
				    getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcur_usuarioRol;
	}

	/**
	 * Metodo que se encarga de limpiar las variables de sesión.
	 */
	public void limpiarAsignarTurno()
	{
		clear();
		setMensajePredioInconsistente(null);
		setTipoExpediente(null);
		setExpediente(null);
		setObservaciones(null);
		setIdTurno(null);
		setIdTurnoHistoria(null);
	}

	/**
	 * Limpia la propiedad UsuarioAsignar.
	 */
	public void limpiarUsuarioAsignar()
	{
		setUsuarioAsignar(null);
	}
}
