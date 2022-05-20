package com.bachue.snr.prosnr01.web.bean.reasignar;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reasignar.ReasignarTurnosRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.reasignar.ReasignarTurnos;
import com.bachue.snr.prosnr01.model.sdb.aut.Rol;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.Etapa;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos la funcionalidad para el Bean de reasignacion de turnos.
 *
 * @author Carlos Calderon
 */
@ManagedBean(name = "beanReasignarTurno")
@SessionScoped
public class BeanReasignarTurno extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2910212182914012585L;

	/** Propiedad icr cllRoles. */
	private Collection<Rol> cllRoles;

	/** Propiedad ic usuarios. */
	private Collection<Usuario> ic_usuarios;

	/** Propiedad ic usuarios A. */
	private Collection<Usuario> ic_usuariosA;

	/** Propiedad icrt data. */
	private Collection<ReasignarTurnos> icrt_data;

	/**  Propiedad lccr circulos Registrales. */
	private Collection<CirculoRegistral> lccr_circulosRegistrales;

	/** Propiedad lccr circulos registrales reasignado A. */
	private Collection<CirculoRegistral> lccr_circulosRegistralesReasignadoA;

	/** Propiedad lce cll etapa. */
	private Collection<Etapa> lce_cllEtapa;

	/** Propiedad ii cantidad turnos rep automatico. */
	private Integer ii_cantidadTurnosRepAutomatico;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irt reasignar turnos remote. */
	@EJB
	private ReasignarTurnosRemote irt_reasignarTurnosRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is descripcion respuesta. */
	private String is_descripcionRespuesta;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id circulo reasignado A. */
	private String is_idCirculoReasignadoA;

	/** Propiedad is id etapa. */
	private String is_idEtapa;

	/** Propiedad is id rol. */
	private String is_idRol;

	/** Propiedad is justificacionReasignadoEspecial. */
	private String is_justificacionReasignadoEspecial;

	/** Propiedad is nombre etapa. */
	private String is_nombreEtapa;

	/** Propiedad is nombre rol. */
	private String is_nombreRol;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad is opciones reparto automatico value. */
	private String is_opcionesRepartoAutomaticoValue;

	/** Propiedad is usuario asignar. */
	private String is_usuarioAsignar;

	/** Propiedad is usuario selected. */
	private String is_usuarioSelected;

	/** Propiedad ib activo. */
	private boolean ib_activo;

	/** Propiedad ib cantidad turnos. */
	private boolean ib_cantidadTurnos;

	/** Propiedad ib habilitar seccion reasignado A. */
	private boolean ib_habilitarSeccionReasignadoA;

	/** Propiedad ib justificacion Turno. */
	private boolean ib_justificacionTurno;

	/** Propiedad ib mostrar boton salvar Y observaciones. */
	private boolean ib_mostrarBotonSalvarYObservaciones;

	/** Propiedad ib mostrar campo reasignar turnos table. */
	private boolean ib_mostrarCampoReasignarTurnosTable;

	/** Propiedad ib mostrar respuesta. */
	private boolean ib_mostrarRespuesta;

	/** Propiedad ib mostrar tabla turnos. */
	private boolean ib_mostrarTablaTurnos;

	/** Propiedad ib opciones boton reparto automatico. */
	private boolean ib_opcionesBotonRepartoAutomatico;

	/** Propiedad ib param automatico. */
	private boolean ib_paramAutomatico;

	/** Propiedad ib deshabilitar opcion aleatorio. */
	private boolean ib_tipoRepartoL;

	/** Propiedad ii constante cant registros tabla. */
	private int ii_constanteCantRegistrosTabla;

	/**
	 * Modifica el valor de activo.
	 *
	 * @param activo asigna el valor a la propiedad activo
	 */
	public void setActivo(boolean activo)
	{
		ib_activo = activo;
	}

	/**
	 * Valida la propiedad activo.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en activo
	 */
	public boolean isActivo()
	{
		return ib_activo;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de cantidad turnos.
	 *
	 * @param ab_b asigna el valor a la propiedad cantidad turnos
	 */
	public void setCantidadTurnos(boolean ab_b)
	{
		ib_cantidadTurnos = ab_b;
	}

	/**
	 * Valida la propiedad cantidad turnos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en cantidad turnos
	 */
	public boolean isCantidadTurnos()
	{
		return ib_cantidadTurnos;
	}

	/**
	 * Modifica el valor de cantidad turnos rep automatico.
	 *
	 * @param ai_i asigna el valor a la propiedad cantidad turnos rep automatico
	 */
	public void setCantidadTurnosRepAutomatico(Integer ai_i)
	{
		ii_cantidadTurnosRepAutomatico = ai_i;
	}

	/**
	 * Retorna el valor de cantidad turnos rep automatico.
	 *
	 * @return el valor de cantidad turnos rep automatico
	 */
	public Integer getCantidadTurnosRepAutomatico()
	{
		return ii_cantidadTurnosRepAutomatico;
	}

	/**
	 * Modifica el valor de CirculosRegistrales.
	 *
	 * @param accr_cr de accr cr
	 */
	public void setCirculosRegistrales(Collection<CirculoRegistral> accr_cr)
	{
		lccr_circulosRegistrales = accr_cr;
	}

	/**
	 * Retorna Objeto o variable de valor circulos registrales.
	 *
	 * @return Retorna el valor de la propiedad circulosRegistrales
	 */
	public Collection<CirculoRegistral> getCirculosRegistrales()
	{
		return lccr_circulosRegistrales;
	}

	/**
	 * Modifica el valor de CirculosRegistralesReasignadoA.
	 *
	 * @param accr_ccr de accr ccr
	 */
	public void setCirculosRegistralesReasignadoA(Collection<CirculoRegistral> accr_ccr)
	{
		lccr_circulosRegistralesReasignadoA = accr_ccr;
	}

	/**
	 * Retorna Objeto o variable de valor circulos registrales reasignado A.
	 *
	 * @return Retorna el valor de la propiedad circulosRegistralesReasignadoA
	 */
	public Collection<CirculoRegistral> getCirculosRegistralesReasignadoA()
	{
		return lccr_circulosRegistralesReasignadoA;
	}

	/**
	 * Modifica el valor de CllEtapa.
	 *
	 * @param ace_ce de ace ce
	 */
	public void setCllEtapa(Collection<Etapa> ace_ce)
	{
		lce_cllEtapa = ace_ce;
	}

	/**
	 * Retorna Objeto o variable de valor cll etapa.
	 *
	 * @return Retorna el valor de la propiedad cllEtapa
	 */
	public Collection<Etapa> getCllEtapa()
	{
		return lce_cllEtapa;
	}

	/**
	 * Modifica el valor de cllRoles.
	 *
	 * @param data de data
	 */
	public void setCllRoles(Collection<Rol> data)
	{
		cllRoles = data;
	}

	/**
	 * Retorna el valor de cllRoles.
	 *
	 * @return el valor de cllRoles
	 */
	public Collection<Rol> getCllRoles()
	{
		return cllRoles;
	}

	/**
	 * Modifica el valor de constante cant registros tabla.
	 *
	 * @param is_s asigna el valor a la propiedad constante cant registros tabla
	 */
	public void setConstanteCantRegistrosTabla(int is_s)
	{
		ii_constanteCantRegistrosTabla = is_s;
	}

	/**
	 * Retorna el valor de constante cant registros tabla.
	 *
	 * @return el valor de constante cant registros tabla
	 */
	public int getConstanteCantRegistrosTabla()
	{
		return ii_constanteCantRegistrosTabla;
	}

	/**
	 * Modifica el valor de data.
	 *
	 * @param data asigna el valor a la propiedad data
	 */
	public void setData(Collection<ReasignarTurnos> data)
	{
		icrt_data = data;
	}

	/**
	 * Retorna el valor de data.
	 *
	 * @return el valor de data
	 */
	public Collection<ReasignarTurnos> getData()
	{
		return icrt_data;
	}

	/**
	 * Modifica el valor de descripcion respuesta.
	 *
	 * @param as_s asigna el valor a la propiedad descripcion respuesta
	 */
	public void setDescripcionRespuesta(String as_s)
	{
		is_descripcionRespuesta = as_s;
	}

	/**
	 * Retorna el valor de descripcion respuesta.
	 *
	 * @return el valor de descripcion respuesta
	 */
	public String getDescripcionRespuesta()
	{
		return is_descripcionRespuesta;
	}

	/**
	 * Modifica el valor de HabilitarSeccionReasignadoA.
	 *
	 * @param ab_b de ab b
	 */
	public void setHabilitarSeccionReasignadoA(boolean ab_b)
	{
		ib_habilitarSeccionReasignadoA = ab_b;
	}

	/**
	 * Valida la propiedad habilitar seccion reasignado A.
	 *
	 * @return Retorna el valor de la propiedad habilitarSeccionReasignadoA
	 */
	public boolean isHabilitarSeccionReasignadoA()
	{
		return ib_habilitarSeccionReasignadoA;
	}

	/**
	 * Modifica el valor de id circulo.
	 *
	 * @param as_s asigna el valor a la propiedad id circulo
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna el valor de id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdCirculoReasignadoA.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculoReasignadoA(String as_s)
	{
		is_idCirculoReasignadoA = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo reasignado A.
	 *
	 * @return Retorna el valor de la propiedad idCirculoReasignadoA
	 */
	public String getIdCirculoReasignadoA()
	{
		return is_idCirculoReasignadoA;
	}

	/**
	 * Modifica el valor de id etapa.
	 *
	 * @param as_s asigna el valor a la propiedad id etapa
	 */
	public void setIdEtapa(String as_s)
	{
		is_idEtapa = as_s;
	}

	/**
	 * Retorna el valor de id etapa.
	 *
	 * @return el valor de id etapa
	 */
	public String getIdEtapa()
	{
		return is_idEtapa;
	}

	/**
	 * Modifica el valor de id rol.
	 *
	 * @param as_s asigna el valor a la propiedad id rol
	 */
	public void setIdRol(String as_s)
	{
		is_idRol = as_s;
	}

	/**
	 * Retorna el valor de id rol.
	 *
	 * @return el valor de id rol
	 */
	public String getIdRol()
	{
		return is_idRol;
	}

	/**
	 * Modifica el valor de justificación reasignado especial.
	 *
	 * @param as_s asigna el valor a la propiedad justificacionReasignadoEspecial
	 */
	public void setJustificacionReasignadoEspecial(String as_s)
	{
		is_justificacionReasignadoEspecial = as_s;
	}

	/**
	 * Retorna el valor de justificación reasignado especial.
	 *
	 * @return el valor de justificación reasignado especial
	 */
	public String getJustificacionReasignadoEspecial()
	{
		return is_justificacionReasignadoEspecial;
	}

	/**
	 * Modifica el valor de JustificacionTurno.
	 *
	 * @param ab_b asigna el valor a la propiedad JustificacionTurno
	 */
	public void setJustificacionTurno(boolean ab_b)
	{
		ib_justificacionTurno = ab_b;
	}

	/**
	 * Valida la propiedad justificación turno.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en justificaciónturno
	 */
	public boolean isJustificacionTurno()
	{
		return ib_justificacionTurno;
	}

	/**
	 * Modifica el valor de mostrar boton salvar Y observaciones.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar boton salvar Y observaciones
	 */
	public void setMostrarBotonSalvarYObservaciones(boolean ab_b)
	{
		ib_mostrarBotonSalvarYObservaciones = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton salvar Y observaciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar boton salvar Y observaciones
	 */
	public boolean isMostrarBotonSalvarYObservaciones()
	{
		return ib_mostrarBotonSalvarYObservaciones;
	}

	/**
	 * Modifica el valor de mostrar campo reasignar turnos table.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar campo reasignar turnos table
	 */
	public void setMostrarCampoReasignarTurnosTable(boolean ab_b)
	{
		ib_mostrarCampoReasignarTurnosTable = ab_b;
	}

	/**
	 * Valida la propiedad mostrar campo reasignar turnos table.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar campo reasignar turnos table
	 */
	public boolean isMostrarCampoReasignarTurnosTable()
	{
		return ib_mostrarCampoReasignarTurnosTable;
	}

	/**
	 * Modifica el valor de mostrar respuesta.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar respuesta
	 */
	public void setMostrarRespuesta(boolean ab_b)
	{
		ib_mostrarRespuesta = ab_b;
	}

	/**
	 * Valida la propiedad mostrar respuesta.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar respuesta
	 */
	public boolean isMostrarRespuesta()
	{
		return ib_mostrarRespuesta;
	}

	/**
	 * Modifica el valor de mostrar tabla turnos.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar tabla turnos
	 */
	public void setMostrarTablaTurnos(boolean ab_b)
	{
		ib_mostrarTablaTurnos = ab_b;
	}

	/**
	 * Valida la propiedad mostrar tabla turnos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar tabla turnos
	 */
	public boolean isMostrarTablaTurnos()
	{
		return ib_mostrarTablaTurnos;
	}

	/**
	 * Modifica el valor de NombreEtapa.
	 *
	 * @param as_s de as s
	 */
	public void setNombreEtapa(String as_s)
	{
		is_nombreEtapa = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nombre etapa.
	 *
	 * @return Retorna el valor de la propiedad nombreEtapa
	 */
	public String getNombreEtapa()
	{
		String ls_idEtapaSelected;

		ls_idEtapaSelected = getIdEtapa();

		if(StringUtils.isValidString(ls_idEtapaSelected))
		{
			Collection<Etapa> lce_etapa;

			lce_etapa = getCllEtapa();

			if(CollectionUtils.isValidCollection(lce_etapa))
			{
				for(Etapa lcr_rol : lce_etapa)
				{
					if(lcr_rol != null)
					{
						long ll_idEtapa;

						ll_idEtapa = lcr_rol.getIdEtapa();

						if(ll_idEtapa == NumericUtils.getLong(ls_idEtapaSelected))
							is_nombreEtapa = lcr_rol.getNombre();
					}
				}
			}
		}

		return is_nombreEtapa;
	}

	/**
	 * Modifica el valor de nombre rol.
	 *
	 * @param as_s asigna el valor a la propiedad nombre rol
	 */
	public void setNombreRol(String as_s)
	{
		is_nombreRol = as_s;
	}

	/**
	 * Retorna el valor de nombre rol.
	 *
	 * @return el valor de nombre rol
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String getNombreRol()
	    throws B2BException
	{
		String ls_idRolSelected;

		ls_idRolSelected = getIdRol();

		if(StringUtils.isValidString(ls_idRolSelected))
		{
			Collection<Rol> lcr_cllRol;

			lcr_cllRol = getCllRoles();

			if(CollectionUtils.isValidCollection(lcr_cllRol))
			{
				for(Rol lcr_rol : lcr_cllRol)
				{
					if(lcr_rol != null)
					{
						String ls_idRol;

						ls_idRol = lcr_rol.getIdRol();

						if(StringUtils.isValidString(ls_idRol) && ls_idRol.equalsIgnoreCase(ls_idRolSelected))
							is_nombreRol = lcr_rol.getNombre();
					}
				}
			}
		}

		return is_nombreRol;
	}

	/**
	 * Modifica el valor de observaciones.
	 *
	 * @param observaciones asigna el valor a la propiedad observaciones
	 */
	public void setObservaciones(String observaciones)
	{
		is_observaciones = observaciones;
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
	 * Modifica el valor de opciones boton reparto automatico.
	 *
	 * @param ab_b asigna el valor a la propiedad opciones boton reparto automatico
	 */
	public void setOpcionesBotonRepartoAutomatico(boolean ab_b)
	{
		ib_opcionesBotonRepartoAutomatico = ab_b;
	}

	/**
	 * Retorna el valor de opciones boton reparto automatico.
	 *
	 * @return el valor de opciones boton reparto automatico
	 */
	public boolean getOpcionesBotonRepartoAutomatico()
	{
		return ib_opcionesBotonRepartoAutomatico;
	}

	/**
	 * Método para obtener turnos a reasignar del usuario seleccionado sobreescrito A como ALEATORIO y DEFAULT
	 * Valida si el usuario estpa activo o no.
	 *
	 */
	public void getOpcionesReasignacion()
	{
		boolean      lb_focus;
		FacesContext lfc_context;

		lb_focus        = true;
		lfc_context     = FacesContext.getCurrentInstance();

		try
		{
			String ls_usuarioSeleccionado;

			ls_usuarioSeleccionado = getUsuarioSelected();

			{
				String ls_idCirculo;

				ls_idCirculo     = getIdCirculo();

				lb_focus = validateStyles(
					    ":reasignarTurnos:idCirculoReasignadoDE", lfc_context, ls_idCirculo, lb_focus
					);

				if(!lb_focus)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);
			}

			{
				String ls_idRol;

				ls_idRol     = getIdRol();

				lb_focus = validateStyles(":reasignarTurnos:idRolReasignar", lfc_context, ls_idRol, lb_focus);

				if(!lb_focus)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);
			}

			{
				String ls_idEtapa;

				ls_idEtapa     = getIdEtapa();

				lb_focus = validateStyles(":reasignarTurnos:idEtapaReasignar", lfc_context, ls_idEtapa, lb_focus);

				if(!lb_focus)
					throw new B2BException(ErrorKeys.ERROR_SIN_ETAPA_SELECCIONADA);
			}

			{
				lb_focus     = validateStyles(
					    ":reasignarTurnos:lstUsuarios", lfc_context, ls_usuarioSeleccionado, lb_focus
					);
				lb_focus     = validateStyles(
					    ":reasignarTurnos:lstUsuarios2", lfc_context, ls_usuarioSeleccionado, lb_focus
					);

				if(!lb_focus)
					throw new B2BException(ErrorKeys.SIN_REASIGNACION_USUARIO);
			}

			{
				Usuario lu_user;

				lu_user = new Usuario();

				lu_user.setIdUsuario(ls_usuarioSeleccionado);

				lu_user = irr_referenceRemote.findUserById(lu_user);

				if(lu_user != null)
				{
					String ls_activo;

					ls_activo = lu_user.getActivo();

					if(StringUtils.isValidString(ls_activo))
					{
						if(ls_activo.equals(EstadoCommon.S))
						{
							setActivo(true);
							setHabilitarSeccionReasignadoA(true);
							setParamAutomatico(false);
							setOpcionesRepartoAutomaticoValue(null);
							setOpcionesBotonRepartoAutomatico(true);

							setMostrarTablaTurnos(false);

							setMostrarBotonSalvarYObservaciones(false);

							addMessage(MessagesKeys.SELECCIONE_OPCION_REASIGNACION);
						}
						else
						{
							setActivo(false);
							setHabilitarSeccionReasignadoA(false);

							setMostrarBotonSalvarYObservaciones(true);

							PrimeFaces.current().executeScript("PF('cdrRepartoAutomaticoUsuarioInactivo').show();");
							PrimeFaces.current().ajax().update("fDialog:cuadroRepartoAutomaticoUsuarioInactivo");
						}

						setObservaciones(null);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Modifica el valor de opciones reparto automatico value.
	 *
	 * @param as_s asigna el valor a la propiedad opciones reparto automatico value
	 */
	public void setOpcionesRepartoAutomaticoValue(String as_s)
	{
		is_opcionesRepartoAutomaticoValue = as_s;
	}

	/**
	 * Retorna el valor de opciones reparto automatico value.
	 *
	 * @return el valor de opciones reparto automatico value
	 */
	public String getOpcionesRepartoAutomaticoValue()
	{
		return is_opcionesRepartoAutomaticoValue;
	}

	/**
	 * Modifica el valor de param automatico.
	 *
	 * @param ab_b asigna el valor a la propiedad param automatico
	 */
	public void setParamAutomatico(boolean ab_b)
	{
		ib_paramAutomatico = ab_b;
	}

	/**
	 * Valida la propiedad param automatico.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en param automatico
	 */
	public boolean isParamAutomatico()
	{
		return ib_paramAutomatico;
	}

	/**
	 * Modifica el valor de deshabilitar opcion aleatorio.
	 *
	 * @param ab_b asigna el valor a la propiedad deshabilitar opcion aleatorio
	 */
	public void setTipoRepartoL(boolean ab_b)
	{
		ib_tipoRepartoL = ab_b;
	}

	/**
	 * Valida la propiedad deshabilitar opcion aleatorio.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en deshabilitar opcion aleatorio
	 */
	public boolean isTipoRepartoL()
	{
		return ib_tipoRepartoL;
	}

	/**
	 * Modifica el valor de usuario asignar.
	 *
	 * @param usuarioAsignar asigna el valor a la propiedad usuario asignar
	 */
	public void setUsuarioAsignar(String usuarioAsignar)
	{
		is_usuarioAsignar = usuarioAsignar;
	}

	/**
	 * Retorna el valor de usuario asignar.
	 *
	 * @return el valor de usuario asignar
	 */
	public String getUsuarioAsignar()
	{
		return is_usuarioAsignar;
	}

	/**
	 * Modifica el valor de usuario selected.
	 *
	 * @param au_usuarioSelected asigna el valor a la propiedad usuario selected
	 */
	public void setUsuarioSelected(String au_usuarioSelected)
	{
		is_usuarioSelected = au_usuarioSelected;
	}

	/**
	 * Retorna el valor de usuario selected.
	 *
	 * @return el valor de usuario selected
	 */
	public String getUsuarioSelected()
	{
		return is_usuarioSelected;
	}

	/**
	 * Modifica el valor de usuarios.
	 *
	 * @param usuarios asigna el valor a la propiedad usuarios
	 */
	public void setUsuarios(Collection<Usuario> usuarios)
	{
		ic_usuarios = usuarios;
	}

	/**
	 * Retorna el valor de usuarios.
	 *
	 * @return el valor de usuarios
	 */
	public Collection<Usuario> getUsuarios()
	{
		return ic_usuarios;
	}

	/**
	 * Modifica el valor de usuarios A.
	 *
	 * @param acu_cu asigna el valor a la propiedad usuarios A
	 */
	public void setUsuariosA(Collection<Usuario> acu_cu)
	{
		ic_usuariosA = acu_cu;
	}

	/**
	 * Retorna el valor de usuarios A.
	 *
	 * @return el valor de usuarios A
	 */
	public Collection<Usuario> getUsuariosA()
	{
		return ic_usuariosA;
	}

	/**
	 * Método para bloquear las demás opciones dependiendo del usuario.
	 */
	public void bloquearOpciones()
	{
		setParamAutomatico(true);

		setIdCirculoReasignadoA(null);

		setUsuariosA(null);
		setUsuarioAsignar(null);

		setMostrarBotonSalvarYObservaciones(true);

		consultarTurnos(EstadoCommon.T);
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<CirculoRegistral> cargarCirculos()
	{
		Collection<CirculoRegistral> lccr_circulos;

		lccr_circulos = new LinkedList<CirculoRegistral>();

		try
		{
			lccr_circulos = irr_referenceRemote.findAllActivoByUsuario(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lccr_circulos))
				setCirculosRegistrales(lccr_circulos);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_circulos;
	}

	/**
	 * Cargar circulos reasignado A.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarCirculosReasignadoA()
	    throws B2BException
	{
		String ls_idCirculoSelected;

		ls_idCirculoSelected = getIdCirculo();

		setIdCirculoReasignadoA(null);

		if(StringUtils.isValidString(ls_idCirculoSelected))
			setCirculosRegistralesReasignadoA(irt_reasignarTurnosRemote.findCirculosDesborde(ls_idCirculoSelected));
	}

	/**
	 * Método para limpiar las opciones de reparto automático
	 *    Separado del método consultarTurnos para usarse desde el xhtml y separado del método ocultarPaneles().
	 */
	public void cleanRepartoAutomaticoOptions()
	{
		setOpcionesRepartoAutomaticoValue(null);
		setCantidadTurnosRepAutomatico(null);
		setCantidadTurnos(false);
	}

	/**
	 * Metodo que se encarga de limpiar las variables de sesión.
	 */
	public void clear()
	{
		setCllRoles(null);
		setUsuarios(null);
		setUsuariosA(null);
		setData(null);
		setCirculosRegistrales(null);
		setCllEtapa(null);
		setCantidadTurnosRepAutomatico(null);
		setDescripcionRespuesta(null);
		setIdCirculo(null);
		setIdEtapa(null);
		setIdRol(null);
		setJustificacionReasignadoEspecial(null);
		setIdCirculoReasignadoA(null);
		setCirculosRegistralesReasignadoA(null);
		setNombreEtapa(null);
		setNombreRol(null);
		setObservaciones(null);
		setOpcionesRepartoAutomaticoValue(null);
		setUsuarioAsignar(null);
		setUsuarioSelected(null);
		setActivo(false);
		setCantidadTurnos(false);
		setJustificacionTurno(false);
		setMostrarBotonSalvarYObservaciones(false);
		setMostrarCampoReasignarTurnosTable(false);
		setMostrarRespuesta(false);
		setMostrarTablaTurnos(false);
		setOpcionesBotonRepartoAutomatico(false);
		setParamAutomatico(false);
		setConstanteCantRegistrosTabla(0);
	}

	/**
	 * Método encargado de actualizar el campo reasignado especial en SDB_ACC_TURNO y mostrar el campo Justiifcacion Turno en pantalla.
	 */
	public void confirmacionAutorizacionDirectorTecnicoRegistro()
	{
		try
		{
			irt_reasignarTurnosRemote.actualizarReasignadoEspecial(
			    icrt_data, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			addMessageInfo("W", MessagesKeys.INGRESAR_JUSTIFICACION_REASIGNACION);
			setJustificacionTurno(true);
			setJustificacionReasignadoEspecial(null);
			PrimeFaces.current().ajax().update("reasignarTurnos:idObsBtnSalvar");
		}
		catch(B2BException lbe_e)
		{
			addMessage(lbe_e);
			PrimeFaces.current().ajax().update(":reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Método encargado de consultar roles al seleccionar circulo.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultarEtapas()
	    throws B2BException
	{
		try
		{
			String ls_idRol;

			ls_idRol = getIdRol();

			if(StringUtils.isValidString(ls_idRol))
			{
				Collection<Etapa> lce_cllEtapas;

				lce_cllEtapas = irt_reasignarTurnosRemote.consultarEtapas(ls_idRol);

				setIdEtapa(null);
				setCllEtapa(lce_cllEtapas);

				setUsuarioSelected(null);
				setUsuarios(null);

				setIdCirculoReasignadoA(null);
				setNombreEtapa(null);

				setUsuarioAsignar(null);
				setUsuariosA(null);

				ocultarPaneles();

				if(!CollectionUtils.isValidCollection(lce_cllEtapas))
					throw new B2BException(ErrorKeys.ERROR_SIN_ETAPAS_ROL);
			}
		}
		catch(B2BException lbe_e)
		{
			addMessage(lbe_e);
			PrimeFaces.current().ajax().update(":reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Método encargado de consultar roles al seleccionar circulo.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultarRoles()
	    throws B2BException
	{
		try
		{
			cargarCirculosReasignadoA();

			Collection<Rol> lcr_cllRol;

			lcr_cllRol = irt_reasignarTurnosRemote.findAllRoles();

			setIdRol(null);
			setCllRoles(lcr_cllRol);

			setIdEtapa(null);
			setCllEtapa(null);

			setUsuarioSelected(null);
			setUsuarios(null);

			setIdCirculoReasignadoA(null);
			setNombreRol(null);
			setNombreEtapa(null);

			setUsuarioAsignar(null);
			setUsuariosA(null);

			ocultarPaneles();

			if(!CollectionUtils.isValidCollection(lcr_cllRol))
				throw new B2BException(ErrorKeys.ERROR_ROLES_VALIDOS);
		}
		catch(B2BException lbe_e)
		{
			addMessage(lbe_e);
			PrimeFaces.current().ajax().update(":reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Método saber el tipo de reparto de la etapa seleccionada
	 */
	public void consultarTipoReparto()
	{
		String is_idEtapa;

		is_idEtapa = getIdEtapa();

		try
		{
			if(StringUtils.isValidString(is_idEtapa))
			{
				String ls_tipoReparto;

				ls_tipoReparto = irr_referenceRemote.findTipoRepartoByEtapa(NumericUtils.getLong(is_idEtapa));

				if(StringUtils.isValidString(ls_tipoReparto))
				{
					if(ls_tipoReparto.equals(EstadoCommon.L))
						setTipoRepartoL(true);
					else
						setTipoRepartoL(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método para obtener turnos a reasignar del usuario seleccionado.
	 *
	 * @param as_s correspondiente al valor del tipo de objeto String
	 */
	public void consultarTurnos(String as_s)
	{
		try
		{
			if(StringUtils.isValidString(as_s))
			{
				String  ls_idEtapa;
				String  ls_idCirculo;
				String  ls_usuarioSeleccionado;
				boolean lb_porCantidad;
				Integer li_cantidadTurnosRepAutomatico;

				ls_idEtapa                         = getIdEtapa();
				ls_idCirculo                       = getIdCirculo();
				ls_usuarioSeleccionado             = getUsuarioSelected();
				lb_porCantidad                     = isCantidadTurnos();
				li_cantidadTurnosRepAutomatico     = getCantidadTurnosRepAutomatico();

				if(
				    StringUtils.isValidString(ls_idEtapa) && StringUtils.isValidString(ls_idCirculo)
					    && StringUtils.isValidString(ls_usuarioSeleccionado)
				)
				{
					Collection<ReasignarTurnos> lcrt_reasignarTurnos;

					lcrt_reasignarTurnos = new ArrayList<ReasignarTurnos>();

					switch(as_s)
					{
						//Opción de reasignación ALEATORIO y DEFAULT
						case EstadoCommon.A:

							if(StringUtils.isValidString(is_idEtapa))
								lcrt_reasignarTurnos = irt_reasignarTurnosRemote.findAllByEtapaUsuario(
									    NumericUtils.getLong(ls_idEtapa), ls_idCirculo, ls_usuarioSeleccionado,
									    lb_porCantidad, li_cantidadTurnosRepAutomatico
									);

							break;

						//Opción de reasignación TODOS // También puede entrar si es usuario inactivo 
						case EstadoCommon.T:

							if(StringUtils.isValidString(is_idEtapa))
								lcrt_reasignarTurnos = irt_reasignarTurnosRemote.findAllByEtapaUsuario(
									    NumericUtils.getLong(ls_idEtapa), ls_idCirculo, ls_usuarioSeleccionado,
									    lb_porCantidad, li_cantidadTurnosRepAutomatico, true
									);

							break;

						//Opción de reasignación POR CANTIDAD
						case EstadoCommon.C:

							if(NumericUtils.getInt(getCantidadTurnosRepAutomatico()) == 0)
								throw new B2BException(ErrorKeys.ERROR_NUMERO_DE_TURNOS_INGRESADO);

							if(StringUtils.isValidString(is_idEtapa))
								lcrt_reasignarTurnos = irt_reasignarTurnosRemote.findAllByEtapaUsuario(
									    NumericUtils.getLong(ls_idEtapa), ls_idCirculo, ls_usuarioSeleccionado,
									    lb_porCantidad, li_cantidadTurnosRepAutomatico, true
									);

							break;

						default:
							break;
					}

					if(lcrt_reasignarTurnos.isEmpty())
						lcrt_reasignarTurnos = null;

					if(CollectionUtils.isValidCollection(lcrt_reasignarTurnos))
					{
						setData(lcrt_reasignarTurnos);

						if(as_s.equalsIgnoreCase(EstadoCommon.A))
							addMessage(MessagesKeys.SIN_OPCION_TURNOS_ALEATORIOS);

						if(NumericUtils.getInt(getCantidadTurnosRepAutomatico()) > lcrt_reasignarTurnos.size())
						{
							setMostrarTablaTurnos(false);
							setMostrarBotonSalvarYObservaciones(false);

							throw new B2BException(ErrorKeys.ERROR_CANTIDAD_TURNOS);
						}
						else
						{
							setOpcionesBotonRepartoAutomatico(true);
							setMostrarTablaTurnos(true);
							setMostrarBotonSalvarYObservaciones(true);
						}

						setConstanteCantRegistrosTabla(cargarConstanteTabla());
						setMostrarRespuesta(false);
						setMostrarCampoReasignarTurnosTable(true);
					}
					else
					{
						setOpcionesBotonRepartoAutomatico(false);
						setMostrarTablaTurnos(false);
						setMostrarBotonSalvarYObservaciones(false);
						setCantidadTurnos(false);

						throw new B2BException(ErrorKeys.ERROR_NO_TIENE_TURNOS_PARA_REASIGNAR);
					}

					setJustificacionTurno(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Consultar usuarios.
	 */
	public void consultarUsuarios()
	{
		try
		{
			String ls_idEtapa;
			String ls_idRol;
			String ls_idCirculo;

			ls_idEtapa       = getIdEtapa();
			ls_idRol         = getIdRol();
			ls_idCirculo     = getIdCirculo();

			if(
			    StringUtils.isValidString(ls_idEtapa) && StringUtils.isValidString(ls_idRol)
				    && StringUtils.isValidString(ls_idCirculo)
			)
			{
				consultarTipoReparto();

				Collection<Usuario> lcu_cllUsuarios;

				lcu_cllUsuarios = irt_reasignarTurnosRemote.findUsuariosByEtapaRolCirculo(
					    NumericUtils.getLong(ls_idEtapa), ls_idRol, ls_idCirculo, false
					);

				setUsuarioSelected(null);
				setUsuarios(lcu_cllUsuarios);

				setIdCirculoReasignadoA(null);

				setUsuarioAsignar(null);
				setUsuariosA(null);

				ocultarPaneles();

				if(!CollectionUtils.isValidCollection(lcu_cllUsuarios))
					throw new B2BException(ErrorKeys.ERROR_SIN_USUARIOS_CRITERIOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(":reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Consultar usuarios reasignado A.
	 */
	public void consultarUsuariosReasignadoA()
	{
		try
		{
			if(!isParamAutomatico())
			{
				String ls_idEtapa;
				String ls_idRol;
				String ls_idCirculoReasignadoA;

				ls_idEtapa                  = getIdEtapa();
				ls_idRol                    = getIdRol();
				ls_idCirculoReasignadoA     = getIdCirculoReasignadoA();

				if(
				    StringUtils.isValidString(getIdEtapa()) && StringUtils.isValidString(ls_idRol)
					    && StringUtils.isValidString(ls_idCirculoReasignadoA)
				)
				{
					Collection<Usuario> lcu_cllUsuarios;

					lcu_cllUsuarios = irt_reasignarTurnosRemote.findUsuariosByEtapaRolCirculo(
						    NumericUtils.getLong(ls_idEtapa), ls_idRol, ls_idCirculoReasignadoA, true
						);

					setUsuarioAsignar(null);

					if(CollectionUtils.isValidCollection(lcu_cllUsuarios))
					{
						String              lu_usuarioSelected;
						Collection<Usuario> lc_usuariosFiltrada;

						lu_usuarioSelected      = getUsuarioSelected();
						lc_usuariosFiltrada     = new ArrayList<Usuario>();

						if(StringUtils.isValidString(lu_usuarioSelected))
						{
							for(Usuario lu_tmp : lcu_cllUsuarios)
							{
								if(lu_tmp != null)
								{
									String ls_idUsuario;

									ls_idUsuario = lu_tmp.getIdUsuario();

									if(
									    StringUtils.isValidString(ls_idUsuario)
										    && !ls_idUsuario.equalsIgnoreCase(lu_usuarioSelected)
									)
										lc_usuariosFiltrada.add(lu_tmp);
								}
							}

							setUsuariosA(lc_usuariosFiltrada);
						}
					}
					else
					{
						setUsuariosA(null);

						throw new B2BException(ErrorKeys.ERROR_SIN_USUARIOS_CRITERIOS);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(":reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Método para deshabilitar los demás turnos en opción Aleatorio.
	 *
	 * @param ab_seleccionado de ab seleccionado
	 */
	public void desactivarTurnosPorSeleccionado(boolean ab_seleccionado)
	{
		Collection<ReasignarTurnos> crt_rt;

		crt_rt = getData();

		if(CollectionUtils.isValidCollection(crt_rt))
		{
			for(ReasignarTurnos lrt_rt : crt_rt)
			{
				if(lrt_rt != null)
				{
					if(ab_seleccionado)
						lrt_rt.setInhabilitado(!lrt_rt.isSeleccionado());
					else
						lrt_rt.setInhabilitado(false);
				}
			}

			if(ab_seleccionado)
			{
				ReasignarTurnos lrt_turnoSeleccionado;

				lrt_turnoSeleccionado = irt_reasignarTurnosRemote.extraerSeleccionado(getData());

				if(lrt_turnoSeleccionado != null)
				{
					String ls_turnosVinculados;

					ls_turnosVinculados = lrt_turnoSeleccionado.getIdTurnoHijo();

					if(
					    StringUtils.isValidString(ls_turnosVinculados)
						    && !ls_turnosVinculados.equalsIgnoreCase(IdentificadoresCommon.SIN_TURNOS_VINCULADOS)
					)
					{
						PrimeFaces.current().executeScript("PF('cdrTurnosVinculados').show();");
						PrimeFaces.current().ajax().update("fDialog:cuadroTurnosVinculados");
					}
				}

				addMessage(MessagesKeys.TURNO_MARCADO);
			}
			else
				addMessage(MessagesKeys.TURNO_DESMARCADO);

			setJustificacionTurno(false);
			PrimeFaces.current().ajax().update("reasignarTurnos:idObsBtnSalvar");
			PrimeFaces.current().ajax().update("reasignarTurnos:globalMsg");
			PrimeFaces.current().ajax().update("reasignarTurnos:tblTurnos");
		}
	}

	/**
	 * Metodo encargado de desmarcar los turnos.
	 */
	public void desmarcarTurnoSeleccionado()
	{
		Collection<ReasignarTurnos> lcrt_rt;

		lcrt_rt = getData();

		if(CollectionUtils.isValidCollection(lcrt_rt))
		{
			for(ReasignarTurnos lrt_temp : lcrt_rt)
			{
				if(lrt_temp != null)
				{
					lrt_temp.setSeleccionado(false);
					lrt_temp.setInhabilitado(false);
				}
			}

			addMessage(MessagesKeys.TURNO_DESMARCADO);
		}
	}

	/**
	 * Método para hallar todas las etapas activas.
	 *
	 * @return devuelve el valor de Collection
	 *
	 */
	public Collection<Etapa> findAllEtapasActivos()
	{
		Collection<Etapa> lccr_datos;
		lccr_datos = null;

		try
		{
			lccr_datos = irr_referenceRemote.findAllEtapasActivo(
				    getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lccr_datos;
	}

	/**
	 * Método encargado de consultar roles de recursos.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Rol> findAllRolesRecursos()
	{
		Collection<Rol> lcr_cllRol;

		lcr_cllRol = null;

		try
		{
			lcr_cllRol = irt_reasignarTurnosRemote.findAllRolesRecursos();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_cllRol;
	}

	/**
	 * Método encargado de consultar roles de segunda instancia.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Rol> findAllRolesSegundaInstancia()
	{
		Collection<Rol> lcr_cllRol;

		lcr_cllRol = null;

		try
		{
			lcr_cllRol = irt_reasignarTurnosRemote.findAllRolesSegundaInstancia();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcr_cllRol;
	}

	/**
	 * Método desbloquear opciones de la pantalla de un usuario inactivo al retractarse en la pregunta final de reasignar.
	 */
	public void habilitarReasignadoAyOpciones()
	{
		if(!isActivo())
		{
			setParamAutomatico(false);

			PrimeFaces.current().ajax().update("reasignarTurnos:idCirculoReasignadoA");
			PrimeFaces.current().ajax().update("reasignarTurnos:radioRepartoAut");
			PrimeFaces.current().ajax().update("reasignarTurnos:idUsuarioAsignar");
			PrimeFaces.current().ajax().update("reasignarTurnos:idUsuarioAsignar2");
		}
	}

	/**
	 * Método que instancia algunos objetos de la clase.
	 */
	public void iniciar()
	{
		ib_opcionesBotonRepartoAutomatico       = false;
		ib_mostrarBotonSalvarYObservaciones     = false;
		ib_mostrarTablaTurnos                   = false;
		ib_mostrarCampoReasignarTurnosTable     = true;
		ib_mostrarRespuesta                     = false;
		ib_paramAutomatico                      = false;
		ib_justificacionTurno                   = false;
		ib_habilitarSeccionReasignadoA          = false;
		is_idCirculo                            = null;
		is_idRol                                = null;
		is_idEtapa                              = null;
		is_usuarioSelected                      = null;
		is_idCirculoReasignadoA                 = null;
		lccr_circulosRegistralesReasignadoA     = null;
		is_nombreRol                            = null;
		is_nombreEtapa                          = null;
		is_usuarioAsignar                       = null;
	}

	/**
	 * Validación de campos antes de mostrar cuadro de confirmación Reparto
	 *
	 * Actualiza el formulario de reasignarTurnos para mostrar cuadro de confirmación Reparto.
	 */
	public void mostrarCdrConfirmacionRepartoAutomatico()
	{
		FacesContext lfc_context;

		lfc_context = FacesContext.getCurrentInstance();

		try
		{
			String ls_idEtapa;

			ls_idEtapa = getIdEtapa();

			if(!validateStyles(":reasignarTurnos:idRolReasignar", lfc_context, getIdRol(), true))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ROL);

			if(!validateStyles(":reasignarTurnos:idEtapaReasignar", lfc_context, ls_idEtapa, true))
				throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_ETAPA);

			if(!validateStyles(":reasignarTurnos:lstUsuarios", lfc_context, getUsuarioSelected(), true))
			{
				if(!validateStyles(":reasignarTurnos:lstUsuarios2", lfc_context, getUsuarioSelected(), true))
					throw new B2BException(ErrorKeys.SIN_REASIGNACION_USUARIO);
			}

			validateStyles(":reasignarTurnos:lstUsuarios2", lfc_context, getUsuarioSelected(), true);

			if(!isParamAutomatico())
			{
				if(
				    !validateStyles(
					        ":reasignarTurnos:idCirculoReasignadoA", lfc_context, getIdCirculoReasignadoA(), true
					    )
				)
					throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

				if(!validateStyles(":reasignarTurnos:idUsuarioAsignar", lfc_context, getUsuarioAsignar(), true))
				{
					if(!validateStyles(":reasignarTurnos:idUsuarioAsignar2", lfc_context, getUsuarioAsignar(), true))
						throw new B2BException(ErrorKeys.SIN_REASIGNACION_USUARIO_A_REASIGNAR);
				}

				validateStyles(":reasignarTurnos:idUsuarioAsignar2", lfc_context, getUsuarioAsignar(), true);
			}

			{
				Collection<ReasignarTurnos> lcrt_data;
				int                         li_seleccionado;

				lcrt_data           = getData();
				li_seleccionado     = 0;

				if(CollectionUtils.isValidCollection(lcrt_data))
				{
					for(ReasignarTurnos lrt_tmp : lcrt_data)
					{
						if(lrt_tmp != null)
						{
							if(lrt_tmp.isSeleccionado())
								li_seleccionado++;
						}
					}

					if(li_seleccionado <= 0)
						throw new B2BException(ErrorKeys.SIN_REASIGNACION);
				}
			}

			if(!validateStyles(":reasignarTurnos:observaciones", lfc_context, getObservaciones(), true))
				throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES);

			{
				String ls_observaciones;

				ls_observaciones = getObservaciones();

				if(StringUtils.isValidString(ls_observaciones) && (ls_observaciones.length() < 100))
					throw new B2BException(ErrorKeys.ERROR_CANTIDAD_CARACTERES_OBSERVACION);
			}

			{
				if(isJustificacionTurno())
				{
					if(
					    !validateStyles(
						        ":reasignarTurnos:justificacionReasignacionEspecial", lfc_context,
						        is_justificacionReasignadoEspecial, true
						    )
					)
						throw new B2BException(ErrorKeys.ERROR_SIN_JUSTIFICACION_REASIGNADO_ESPECIAL);

					{
						String ls_justificacionReasignadoEspecial;

						ls_justificacionReasignadoEspecial = getJustificacionReasignadoEspecial();

						if(
						    StringUtils.isValidString(ls_justificacionReasignadoEspecial)
							    && (ls_justificacionReasignadoEspecial.length() < 100)
						)
							throw new B2BException(ErrorKeys.ERROR_CARACTERES_JUSTIFICACION_REASIGNADO_ESPECIAL);
					}
				}
			}

			String ls_opcionReasignacionSeleccionada;

			ls_opcionReasignacionSeleccionada = getOpcionesRepartoAutomaticoValue();

			if(StringUtils.isValidString(ls_opcionReasignacionSeleccionada))
			{
				if(
				    ls_opcionReasignacionSeleccionada.equalsIgnoreCase(EstadoCommon.A) && !isJustificacionTurno()
					    && isTipoRepartoL()
				)
				{
					PrimeFaces.current().executeScript("PF('cdrAutorizacionDirectorTecnico').show();");
					PrimeFaces.current().ajax().update("fDialog:cuadroAutorizacionDirectorTecnico");
				}
				else
				{
					PrimeFaces.current().executeScript("PF('cdrRepartoAutomatico').show();");
					PrimeFaces.current().ajax().update("fDialog:cuadroRepartoAutomatico");
				}
			}

			if(isParamAutomatico())
			{
				PrimeFaces.current().executeScript("PF('cdrRepartoAutomatico').show();");
				PrimeFaces.current().ajax().update("fDialog:cuadroRepartoAutomatico");
			}
		}
		catch(B2BException lbe_e)
		{
			addMessage(lbe_e);
			PrimeFaces.current().ajax().update(":reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Muestra u oculta InputNumber de la segunda opción de reparto automático
	 *
	 * Si la opción es "Todos" ejecuta  el método mostrarCdrConfirmacionRepartoAutomatico para mostrar cuadro
	 * de confirmación.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void mostrarOpcionesRepartoAutomatico()
	    throws B2BException
	{
		String ls_opcionesBotonRepartoAutomatico;

		ls_opcionesBotonRepartoAutomatico = getOpcionesRepartoAutomaticoValue();

		if(StringUtils.isValidString(ls_opcionesBotonRepartoAutomatico))
		{
			//Opción de reasignación DEFAULT, ALEATORIO y TODOS
			if(
			    ls_opcionesBotonRepartoAutomatico.equalsIgnoreCase(EstadoCommon.A)
				    || ls_opcionesBotonRepartoAutomatico.equalsIgnoreCase(EstadoCommon.T)
			)
			{
				setCantidadTurnos(false);
				setCantidadTurnosRepAutomatico(null);
				consultarTurnos(ls_opcionesBotonRepartoAutomatico);
			}

			//Opción de reasignación POR CANTIDAD
			else if(ls_opcionesBotonRepartoAutomatico.equalsIgnoreCase(EstadoCommon.C))
			{
				setCantidadTurnos(true);
				setMostrarTablaTurnos(false);
				setData(null);
				setMostrarBotonSalvarYObservaciones(false);
			}
		}
	}

	/**
	 * Método usado por otros para ocultar partes de la pantalla Reasignación y limpiar campos.
	 */
	public void ocultarPaneles()
	{
		setOpcionesBotonRepartoAutomatico(false);
		setOpcionesRepartoAutomaticoValue(null);
		setCantidadTurnos(false);
		setCantidadTurnosRepAutomatico(null);
		setMostrarTablaTurnos(false);
		setMostrarBotonSalvarYObservaciones(false);
		setJustificacionTurno(false);
	}

	/**
	 * Método de "Sí" en cuadro de confirmación de reparto para reasignar y renderizar campos luego de reasignar.
	 */
	public void salvar()
	{
		try
		{
			Collection<ReasignarTurnos> icrt_data;

			icrt_data = getData();

			if(CollectionUtils.isValidCollection(icrt_data))
			{
				Collection<ReasignarTurnos> turnosConDescripcionTemp;

				boolean                     lb_paramAutomatico;

				lb_paramAutomatico     = isParamAutomatico();

				turnosConDescripcionTemp = irt_reasignarTurnosRemote.reasignarTurnos(
					    icrt_data, lb_paramAutomatico ? getIdCirculo() : getIdCirculoReasignadoA(), getIdRol(),
					    getIdEtapa(), getUsuarioSelected(), getUsuarioAsignar(), getObservaciones(),
					    getJustificacionReasignadoEspecial(), lb_paramAutomatico, getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(turnosConDescripcionTemp))
					setData(turnosConDescripcionTemp);
			}

			ocultarPaneles();
			setMostrarTablaTurnos(true);
			setMostrarRespuesta(true);
			setMostrarCampoReasignarTurnosTable(false);

			addMessage(MessagesKeys.REASIGNACION_COMPLETADA);
			addMessage(MessagesKeys.SELECCIONE_USUARIO_NUEVAMENTE);
		}
		catch(B2BException lbe_e)
		{
			addMessage(lbe_e);
			PrimeFaces.current().ajax().update(":reasignarTurnos:globalMsg");
		}
	}

	/**
	 * Seleccion usuario reasignado de.
	 */
	public void seleccionUsuarioReasignadoDe()
	{
		setIdCirculoReasignadoA(null);

		setUsuarioAsignar(null);
		setUsuariosA(null);

		ocultarPaneles();
		cleanRepartoAutomaticoOptions();
	}

	/**
	 * Método que valida si los turnos vinculados del turno seleccionado están bloqueados.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validacionTurnosVinculadosBloqueados()
	    throws B2BException
	{
		boolean lb_tieneTurnosVinculadosBloqueados;

		lb_tieneTurnosVinculadosBloqueados = irt_reasignarTurnosRemote.validarTurnosVinculadosBloqueados(getData());

		if(lb_tieneTurnosVinculadosBloqueados)
		{
			PrimeFaces.current().executeScript("PF('cdrTurnosVinculados2').show();");
			PrimeFaces.current().ajax().update("fDialog:cuadroTurnosVinculados2");
		}
	}
}
