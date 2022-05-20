package com.bachue.snr.prosnr01.web.bean.correcciones;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.prosnr01.web.bean.recepcion.documentos.BeanCorreccion;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.CausalCorreccionCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoComplementacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.Apertura;
import com.bachue.snr.prosnr01.model.antiguoSistema.CabidaLinderos;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelApertura;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelAreaPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelCatastral;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelComplementacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDatosDocumento;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaAnotacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleAntSistemaSolicitud;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDetalleIntervinientes;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelDireccionPredio;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelEspecificacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelIntervinientes;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelLinderos;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasAbiertas;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelMatriculasSegregacion;
import com.bachue.snr.prosnr01.model.permisosCorrecciones.PanelUbicacion;
import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.ui.PermisosCorreccionesUI;

import com.bachue.snr.prosnr01.web.bean.antiguo.sistema.BeanAntSistema;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.event.TabChangeEvent;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanPermisosCorrecciones.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanPermisosCorrecciones")
public class BeanPermisosCorrecciones extends BeanAntSistema implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6797580617097245134L;

	/** Constante CS_formulario. */
	private static final String CS_formulario = "fPermisosCorreccion";

	/** Constante CS_dlgCorrecciones. */
	private static final String CS_dlgCorrecciones = "dlgCorrecciones";

	/** Constante CS_messageIdGrowl. */
	private static final String CS_messageIdGrowl = CS_formulario + ":globalMsg";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanPermisosCorrecciones.class);

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad icc causales. */
	private Collection<CausalCorreccion> icc_causales;

	/** Propiedad icsm matriculas masivas complementacion. */
	private Collection<SolicitudMatricula> icsm_matriculasMasivasComplementacion;

	/** Propiedad icsm matriculas masivas documento. */
	private Collection<SolicitudMatricula> icsm_matriculasMasivasDocumento;

	/** Propiedad icsm matriculas masivas intervinientes. */
	private Collection<SolicitudMatricula> icsm_matriculasMasivasIntervinientes;

	/** Propiedad icsm matriculas masivas salvedades. */
	private Collection<SolicitudMatricula> icsm_matriculasMasivasSalvedades;

	/** Propiedad icsm solicitud matriculas. */
	private Collection<SolicitudMatricula> icsm_solicitudMatriculas;

	/** Propiedad lhm predio. */
	private Map<String, Object> lhm_predio;

	/** Propiedad lpcui permisos correcciones. */
	private PermisosCorreccionesUI lpcui_permisosCorrecciones;

	/** Propiedad ips_predio Segregado Agregar. */
	private PredioSegregado ips_predioSegregadoAgregar;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad ism matricula actual. */
	private SolicitudMatricula ism_matriculaActual;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad ib copiar masivos. */
	private boolean ib_copiarMasivos;

	/** Propiedad ib habilitar agregar detalle ant. */
	private boolean ib_habilitarAgregarDetalleAnt;

	/** Propiedad ib mostrar bandeja anotaciones. */
	private boolean ib_mostrarBandejaAnotaciones;

	/** Propiedad ib mostrar boton salvar anotacion. */
	private boolean ib_mostrarBotonSalvarAnotacion;

	/** Propiedad ib mostrar detalle. */
	private boolean ib_mostrarDetalle;

	/** Propiedad ib mostrar detalle anotaciones. */
	private boolean ib_mostrarDetalleAnotaciones;

	/** Propiedad is proceso terminado. */
	private boolean is_procesoTerminado;

	/** Propiedad il id motivo. */
	private long il_idMotivo;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de causales.
	 *
	 * @param acc_cc asigna el valor a la propiedad causales
	 */
	public void setCausales(Collection<CausalCorreccion> acc_cc)
	{
		icc_causales = acc_cc;
	}

	/**
	 * Retorna el valor de causales.
	 *
	 * @return el valor de causales
	 */
	public Collection<CausalCorreccion> getCausales()
	{
		return icc_causales;
	}

	/**
	 * Modifica el valor de copiar masivos.
	 *
	 * @param ab_b asigna el valor a la propiedad copiar masivos
	 */
	public void setCopiarMasivos(boolean ab_b)
	{
		ib_copiarMasivos = ab_b;
	}

	/**
	 * Valida la propiedad copiar masivos.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en copiar masivos
	 */
	public boolean isCopiarMasivos()
	{
		return ib_copiarMasivos;
	}

	/**
	 * Modifica el valor de habilitar agregar detalle ant.
	 *
	 * @param ab_b asigna el valor a la propiedad habilitar agregar detalle ant
	 */
	public void setHabilitarAgregarDetalleAnt(boolean ab_b)
	{
		ib_habilitarAgregarDetalleAnt = ab_b;
	}

	/**
	 * Valida la propiedad habilitar agregar detalle ant.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en habilitar agregar detalle ant
	 */
	public boolean isHabilitarAgregarDetalleAnt()
	{
		return ib_habilitarAgregarDetalleAnt;
	}

	/**
	 * Modifica el valor de id motivo.
	 *
	 * @param al_l asigna el valor a la propiedad id motivo
	 */
	public void setIdMotivo(long al_l)
	{
		il_idMotivo = al_l;
	}

	/**
	 * Retorna el valor de id motivo.
	 *
	 * @return el valor de id motivo
	 */
	public long getIdMotivo()
	{
		return il_idMotivo;
	}

	/**
	 * Modifica el valor de matricula actual.
	 *
	 * @param asm_sm asigna el valor a la propiedad matricula actual
	 */
	public void setMatriculaActual(SolicitudMatricula asm_sm)
	{
		ism_matriculaActual = asm_sm;
	}

	/**
	 * Retorna el valor de matricula actual.
	 *
	 * @return el valor de matricula actual
	 */
	public SolicitudMatricula getMatriculaActual()
	{
		return ism_matriculaActual;
	}

	/**
	 * Modifica el valor de matriculas masivas complementacion.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas masivas complementacion
	 */
	public void setMatriculasMasivasComplementacion(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculasMasivasComplementacion = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas masivas complementacion.
	 *
	 * @return el valor de matriculas masivas complementacion
	 */
	public Collection<SolicitudMatricula> getMatriculasMasivasComplementacion()
	{
		return icsm_matriculasMasivasComplementacion;
	}

	/**
	 * Modifica el valor de matriculas masivas documento.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas masivas documento
	 */
	public void setMatriculasMasivasDocumento(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculasMasivasDocumento = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas masivas documento.
	 *
	 * @return el valor de matriculas masivas documento
	 */
	public Collection<SolicitudMatricula> getMatriculasMasivasDocumento()
	{
		return icsm_matriculasMasivasDocumento;
	}

	/**
	 * Modifica el valor de matriculas masivas intervinientes.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas masivas intervinientes
	 */
	public void setMatriculasMasivasIntervinientes(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculasMasivasIntervinientes = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas masivas intervinientes.
	 *
	 * @return el valor de matriculas masivas intervinientes
	 */
	public Collection<SolicitudMatricula> getMatriculasMasivasIntervinientes()
	{
		return icsm_matriculasMasivasIntervinientes;
	}

	/**
	 * Modifica el valor de matriculas masivas salvedades.
	 *
	 * @param acsm_csm asigna el valor a la propiedad matriculas masivas salvedades
	 */
	public void setMatriculasMasivasSalvedades(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_matriculasMasivasSalvedades = acsm_csm;
	}

	/**
	 * Retorna el valor de matriculas masivas salvedades.
	 *
	 * @return el valor de matriculas masivas salvedades
	 */
	public Collection<SolicitudMatricula> getMatriculasMasivasSalvedades()
	{
		return icsm_matriculasMasivasSalvedades;
	}

	/**
	 * Modifica el valor de mostrar bandeja anotaciones.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar bandeja anotaciones
	 */
	public void setMostrarBandejaAnotaciones(boolean ab_b)
	{
		ib_mostrarBandejaAnotaciones = ab_b;
	}

	/**
	 * Valida la propiedad mostrar bandeja anotaciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar bandeja anotaciones
	 */
	public boolean isMostrarBandejaAnotaciones()
	{
		return ib_mostrarBandejaAnotaciones;
	}

	/**
	 * Modifica el valor de mostrar boton salvar anotacion.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar boton salvar anotacion
	 */
	public void setMostrarBotonSalvarAnotacion(boolean ab_b)
	{
		ib_mostrarBotonSalvarAnotacion = ab_b;
	}

	/**
	 * Valida la propiedad mostrar boton salvar anotacion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar boton salvar anotacion
	 */
	public boolean isMostrarBotonSalvarAnotacion()
	{
		return ib_mostrarBotonSalvarAnotacion;
	}

	/**
	 * Modifica el valor de mostrar detalle.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar detalle
	 */
	public void setMostrarDetalle(boolean ab_b)
	{
		ib_mostrarDetalle = ab_b;
	}

	/**
	 * Valida la propiedad mostrar detalle.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar detalle
	 */
	public boolean isMostrarDetalle()
	{
		return ib_mostrarDetalle;
	}

	/**
	 * Modifica el valor de mostrar detalle anotaciones.
	 *
	 * @param ab_b asigna el valor a la propiedad mostrar detalle anotaciones
	 */
	public void setMostrarDetalleAnotaciones(boolean ab_b)
	{
		ib_mostrarDetalleAnotaciones = ab_b;
	}

	/**
	 * Valida la propiedad mostrar detalle anotaciones.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en mostrar detalle anotaciones
	 */
	public boolean isMostrarDetalleAnotaciones()
	{
		return ib_mostrarDetalleAnotaciones;
	}

	/** {@inheritdoc} */
	public void setObservaciones(String as_s)
	{
		is_observaciones = as_s;
	}

	/** {@inheritdoc} */
	public String getObservaciones()
	{
		return is_observaciones;
	}

	/**
	 * Modifica el valor de permisos correcciones.
	 *
	 * @param apcui_pcui asigna el valor a la propiedad permisos correcciones
	 */
	public void setPermisosCorrecciones(PermisosCorreccionesUI apcui_pcui)
	{
		lpcui_permisosCorrecciones = apcui_pcui;
	}

	/**
	 * Retorna el valor de permisos correcciones.
	 *
	 * @return el valor de permisos correcciones
	 */
	public PermisosCorreccionesUI getPermisosCorrecciones()
	{
		if(lpcui_permisosCorrecciones == null)
			lpcui_permisosCorrecciones = new PermisosCorreccionesUI();

		return lpcui_permisosCorrecciones;
	}

	/**
	 * Sets the predio.
	 *
	 * @param ahm_predio correspondiente al valor del tipo de objeto Map<String,Object>
	 */
	public void setPredio(Map<String, Object> ahm_predio)
	{
		lhm_predio = ahm_predio;

		if(ahm_predio != null)
		{
			Object lo_o;

			lo_o = ahm_predio.get("ID_TURNO");

			if((lo_o != null) && lo_o instanceof String)
				setIdTurno(StringUtils.getString(lo_o));
		}
	}

	/**
	 * Retorna el valor de predio.
	 *
	 * @return el valor de predio
	 */
	public Map<String, Object> getPredio()
	{
		return lhm_predio;
	}

	/**
	 * @param Modifica el valor de la propiedad
	 */
	public void setPredioSegregadoAgregar(PredioSegregado aps_ps)
	{
		ips_predioSegregadoAgregar = aps_ps;
	}

	/**
	 * @return Retorna el valor de la propiedad
	 */
	public PredioSegregado getPredioSegregadoAgregar()
	{
		return ips_predioSegregadoAgregar;
	}

	/**
	 * Modifica el valor de proceso terminado.
	 *
	 * @param as_s asigna el valor a la propiedad proceso terminado
	 */
	public void setProcesoTerminado(boolean as_s)
	{
		is_procesoTerminado = as_s;
	}

	/**
	 * Valida la propiedad proceso terminado.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en proceso terminado
	 */
	public boolean isProcesoTerminado()
	{
		return is_procesoTerminado;
	}

	/**
	 * Modifica el valor de solicitud matriculas.
	 *
	 * @param acsm_csm asigna el valor a la propiedad solicitud matriculas
	 */
	public void setSolicitudMatriculas(Collection<SolicitudMatricula> acsm_csm)
	{
		icsm_solicitudMatriculas = acsm_csm;
	}

	/**
	 * Retorna el valor de solicitud matriculas.
	 *
	 * @return el valor de solicitud matriculas
	 */
	public Collection<SolicitudMatricula> getSolicitudMatriculas()
	{
		return icsm_solicitudMatriculas;
	}

	/**
	 * Método encargado de realizar la navegacion entre pantallas.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolverPermisos()
	{
		String ls_return;

		ls_return = null;

		if(isMostrarDetalle())
		{
			if(isMostrarDetalleAnotaciones())
			{
				setMostrarDetalleAnotaciones(false);
				setMostrarBandejaAnotaciones(true);
				setMostrarBotonSalvarAnotacion(false);
			}
			else
			{
				setMostrarDetalle(false);
				setMostrarBandeja(true);
				datosCorrecciones();
			}
		}
		else if(isMostrarBandeja())
			ls_return = NavegacionCommon.DETALLE_ACTO;

		clean(true);

		return ls_return;
	}

	/**
	 * Verifica la información suministrada para un interviniente y lo agrega al proceso de anotaciones.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void agregarIntervinientes(boolean ab_noValidarGenero)
	    throws B2BException
	{
		try
		{
			agregarIntervinientesData(true, ab_noValidarGenero);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarIntervinientes", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Cargar tabs.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param ab_acc correspondiente al valor del tipo de objeto boolean
	 */
	public void cargarTabs(String as_idCirculo, Long al_idMatricula, boolean ab_acc)
	{
		cargarTabsDetalle(as_idCirculo, al_idMatricula, ab_acc, true);

		{
			CabidaLinderos lcl_dataComplementacion;

			lcl_dataComplementacion = getCabidaLinderos();

			if(lcl_dataComplementacion != null)
			{
				Complementacion             lc_complementacion;
				ComplementacionCalificacion lcc_complementacion;
				String                      ls_complementacion;
				String                      ls_tipo;

				lc_complementacion      = lcl_dataComplementacion.getComplementacion();
				lcc_complementacion     = getComplementacionCalificacion();
				ls_complementacion      = null;
				ls_tipo                 = lcl_dataComplementacion.getTipoComplementacion();

				if(lc_complementacion != null)
					ls_complementacion = lc_complementacion.getComplementacion();

				if(lcc_complementacion != null)
				{
					Complementacion       lc_complementacionData;
					ComplementacionPredio lcp_complementacionPredio;
					String                ls_tipoComplementacion;

					lc_complementacionData        = lcl_dataComplementacion.getComplementacion();
					lcp_complementacionPredio     = lcc_complementacion.getComplementacionPredio();
					ls_tipoComplementacion        = null;

					if(!StringUtils.isValidString(ls_tipo))
						ls_tipo = lcl_dataComplementacion.getTipoComplementacion();

					if(StringUtils.isValidString(ls_tipo))
					{
						if(ls_tipo.equalsIgnoreCase(TipoComplementacionCommon.N))
						{
							ls_tipoComplementacion = TipoComplementacionCommon.NUEVA;
							setHabilitarComplementacion(true);
						}
						else if(ls_tipo.equalsIgnoreCase(TipoComplementacionCommon.C))
							ls_tipoComplementacion = TipoComplementacionCommon.COPIAR;
						else if(ls_tipo.equalsIgnoreCase(TipoComplementacionCommon.A))
							ls_tipoComplementacion = TipoComplementacionCommon.CONSTRUIR;
					}
					else
					{
						ls_tipoComplementacion = TipoComplementacionCommon.NUEVA;
						setHabilitarComplementacion(true);
					}

					if(lcp_complementacionPredio != null)
					{
						if(StringUtils.isValidString(lcp_complementacionPredio.getComplementacion()))
						{
							if(lc_complementacionData != null)
							{
								String ls_complementacionTemp;

								ls_complementacionTemp = lc_complementacionData.getComplementacion();

								if(StringUtils.isValidString(ls_complementacionTemp))
									ls_complementacion = ls_complementacionTemp;
							}
						}

						lcp_complementacionPredio.setComplementacion(ls_complementacion);
					}

					lcc_complementacion.setTipoComplementacion(ls_tipoComplementacion);
				}
			}
		}
	}

	/**
	 * Método encargado de cerrar el Pop Up y limpiar los datos del predio seleccionado.
	 */
	public void cerrarCausalesCorreccion()
	{
		cleanDetalle();
	}

	/**
	 * Método encargado de limpiar el detalle de una matricula seleccionada cuando se validan los permisos para correcciones o se devuelve al resumen de matriculas.
	 */
	public void cleanDetalle()
	{
		setMostrarBandeja(true);
		setMostrarDetalle(false);
		setMostrarBandejaAnotaciones(true);
		setMostrarDetalleAnotaciones(false);
		setCausales(null);
		setIdSolicitud(null);
		setIdCirculo(null);
		setIdMatricula(null);
	}

	/**
	 * Método encargado de limpiar el la pantalla de permisos para correcciones cuando se termina el proceso o se devuelve a la pantalla anterior.
	 */
	public void cleanPantalla()
	{
		cleanDetalle();
		setSolicitudMatriculas(null);
		setIdTurno(null);
		setIdMotivo(-1L);
		super.clean();
	}

	/**
	 * Método encargado de cargar los paneles y sus respectivas selecciones de correcciones para la anotacion seleccionada.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarAnotacion(Anotacion aa_anotacion)
	    throws B2BException
	{
		consultarAnotacion(aa_anotacion, true, false);
	}

	/**
	 * Método encargado de cargar los paneles y sus respectivas selecciones de correcciones para la anotacion seleccionada.
	 *
	 * @param aa_anotacion correspondiente al valor del tipo de objeto Anotacion
	 * @param ab_acc correspondiente al valor del tipo de objeto boolean
	 * @param ab_desdeDividida correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarAnotacion(Anotacion aa_anotacion, boolean ab_acc, boolean ab_desdeDividida)
	    throws B2BException
	{
		try
		{
			if(aa_anotacion != null)
			{
				modificarAnotaciones(aa_anotacion, !ab_acc, ab_desdeDividida);

				if(ab_acc)
					setAnotacion(aa_anotacion);
				else
					setAnotacionBng(aa_anotacion);

				setBloquearModificarIntervenientes(false);

				setMostrarBandejaAnotaciones(false);
				setMostrarDetalleAnotaciones(true);

				consultarAnotacionCancelacion();
			}
			else
				throw new B2BException(ErrorKeys.ANOTACION_VALIDA_SELECCIONAR);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAnotacion", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de consultar las causales de correccion seleccionadas desde la etapa de registro.
	 *
	 * @param asm_sm Objeto de tipo SolicitudMatricula que contiene el id_solicitud, id_circulo y id_matricula para realizar la busqueda.
	 */
	public void consultarCausalesCorrecciones(SolicitudMatricula asm_sm)
	{
		try
		{
			if(asm_sm != null)
			{
				Collection<CausalCorreccion> lcc_causales;
				SolicitudCorreccion          lsc_solicitudCorreccion;
				String                       ls_idSolicitud;
				String                       ls_idCirculo;
				long                         ll_idMatricula;

				lsc_solicitudCorreccion     = new SolicitudCorreccion();
				ls_idSolicitud              = asm_sm.getIdSolicitud();
				ls_idCirculo                = asm_sm.getIdCirculo();
				ll_idMatricula              = asm_sm.getIdMatricula();

				if(StringUtils.isValidString(ls_idSolicitud))
					lsc_solicitudCorreccion.setIdSolicitud(ls_idSolicitud);

				if(StringUtils.isValidString(ls_idCirculo))
					lsc_solicitudCorreccion.setIdCirculo(ls_idCirculo);

				if(ll_idMatricula > NumericUtils.DEFAULT_LONG_VALUE)
					lsc_solicitudCorreccion.setIdMatricula(NumericUtils.getBigInteger(ll_idMatricula));

				lcc_causales = irr_registroRemote.findAllCausales(lsc_solicitudCorreccion);

				if(CollectionUtils.isValidCollection(lcc_causales))
				{
					setCausales(lcc_causales);
					setIdSolicitud(ls_idSolicitud);
					setIdCirculo(ls_idCirculo);
					setIdMatricula(NumericUtils.getLongWrapper(ll_idMatricula));
				}

				abrirDialogo(CS_dlgCorrecciones, CS_dlgCorrecciones);
				setMatriculaActual(asm_sm);
			}
		}
		catch(B2BException lb2be_e)
		{
			cerrarDialogo(CS_dlgCorrecciones, CS_dlgCorrecciones);

			clh_LOGGER.error("consultarCausalesCorrecciones", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Método encargado de obtener las matriculas asociadas.
	 */
	public void datosCorrecciones()
	{
		try
		{
			Collection<SolicitudMatricula> lcsm_matriculas;
			Long                           ll_idTurnoHistoria;
			TurnoHistoria                  lth_turnoHistoria;

			lcsm_matriculas        = irr_calificacionRemote.findMatriculasByIdSolicitud(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
			ll_idTurnoHistoria     = NumericUtils.getLongWrapper(getIdTurnoHistoria());
			lth_turnoHistoria      = irr_referenceRemote.findTurnoHistoriaByid(ll_idTurnoHistoria);
			setProcesoTerminado(irr_calificacionRemote.validarSalvedades(ll_idTurnoHistoria, false));

			{
				boolean lb_matriculas;

				lb_matriculas = CollectionUtils.isValidCollection(lcsm_matriculas);

				if(lb_matriculas)
				{
					setMatriculaActual(null);

					{
						Collection<SolicitudMatricula> lcsm_solicitudMatriculas;

						lcsm_solicitudMatriculas = new ArrayList<SolicitudMatricula>();

						lcsm_solicitudMatriculas.addAll(lcsm_matriculas);

						setSolicitudMatriculas(lcsm_solicitudMatriculas);
					}

					{
						Collection<SolicitudMatricula> lcsm_complementacion;

						lcsm_complementacion = new ArrayList<SolicitudMatricula>();

						lcsm_complementacion.addAll(lcsm_matriculas);

						setMatriculasMasivasComplementacion(lcsm_complementacion);
					}

					{
						Collection<SolicitudMatricula> lcsm_matriculasMasivasDocumento;

						lcsm_matriculasMasivasDocumento = new ArrayList<SolicitudMatricula>();

						lcsm_matriculasMasivasDocumento.addAll(lcsm_matriculas);

						setMatriculasMasivasDocumento(lcsm_matriculasMasivasDocumento);
					}

					{
						Collection<SolicitudMatricula> lcsm_matriculasMasivasIntervinientes;

						lcsm_matriculasMasivasIntervinientes = new ArrayList<SolicitudMatricula>();

						lcsm_matriculasMasivasIntervinientes.addAll(lcsm_matriculas);

						setMatriculasMasivasIntervinientes(lcsm_matriculasMasivasIntervinientes);
					}

					{
						Collection<SolicitudMatricula> lcsm_matriculasMasivasSalvedades;

						lcsm_matriculasMasivasSalvedades = new ArrayList<SolicitudMatricula>();

						lcsm_matriculasMasivasSalvedades.addAll(lcsm_matriculas);

						setMatriculasMasivasSalvedades(lcsm_matriculasMasivasSalvedades);
					}
				}

				setCopiarMasivos(lb_matriculas ? (lcsm_matriculas.size() > 1) : false);
			}

			if(lth_turnoHistoria != null)
				setObservaciones(lth_turnoHistoria.getObservaciones());
		}
		catch(B2BException lb2be_e)
		{
			setSolicitudMatriculas(null);
			clh_LOGGER.error("datosCorrecciones", lb2be_e);
		}
	}

	/**
	 * Método encargado de guardar los check aplicados en el detalle de la anotacion seleccionada.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void guardarCheksAnotaciones()
	    throws B2BException
	{
		try
		{
			Anotacion la_anotacionCargada;

			la_anotacionCargada = getAnotacion();

			if(la_anotacionCargada != null)
			{
				Collection<Anotacion> lca_intervinientes;
				Collection<Anotacion> lca_anotacionesCargadas;
				long                  ll_idAnotacionCargada;

				lca_intervinientes          = getIntervinientesAgregados();
				lca_anotacionesCargadas     = getAnotacionesAgregadas();
				ll_idAnotacionCargada       = la_anotacionCargada.getIdAnotacion();

				if(CollectionUtils.isValidCollection(lca_intervinientes))
					la_anotacionCargada.setIntervinientesAgregados(lca_intervinientes);

				if(CollectionUtils.isValidCollection(lca_anotacionesCargadas))
				{
					AnotacionPredio     lap_anotacion;
					Iterator<Anotacion> lia_iterator;
					boolean             lb_anotacionEncontrada;

					lap_anotacion              = new AnotacionPredio();
					lia_iterator               = lca_anotacionesCargadas.iterator();
					lb_anotacionEncontrada     = false;

					while(lia_iterator.hasNext() && !lb_anotacionEncontrada)
					{
						Anotacion la_iterator;

						la_iterator = lia_iterator.next();

						if(la_iterator != null)
						{
							long ll_idAnotacion;

							ll_idAnotacion = la_iterator.getIdAnotacion();

							if(ll_idAnotacionCargada == ll_idAnotacion)
							{
								la_iterator                = la_anotacionCargada;
								lb_anotacionEncontrada     = true;
								onTabChange(null);

								la_iterator.setCorreccion(true);

								setMostrarDetalleAnotaciones(false);
								addMessage(MessagesKeys.PROCESO_COMPLETADO);
							}
						}
					}

					lap_anotacion.setIdCirculo(getIdCirculo());
					lap_anotacion.setIdMatricula(getIdMatricula());
					lap_anotacion.setIdTurno(getIdTurno());

					irr_calificacionRemote.marcarAnotacionesCorreccion(
					    lap_anotacion, lca_anotacionesCargadas, getUserId(), getLocalIpAddress()
					);
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
	 * Método encargado de guardar y limpiar la selección de la anotación esta visible en pantalla.
	 *
	 * @param atce_event correspondiente al valor del tipo de objeto TabChangeEvent
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void onTabChange(TabChangeEvent atce_event)
	    throws B2BException
	{
		if(isMostrarDetalleAnotaciones() && !isMostrarBandejaAnotaciones())
		{
			limpiarDetalleAnotaciones();
			setMostrarBandejaAnotaciones(true);
			setMostrarDetalleAnotaciones(false);
		}
	}

	/**
	 * Método encargado de actualizar las causales de correccion para solicitud, circulo y matricula seleccionados.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void salvarCausalesCorreccion()
	    throws B2BException
	{
		try
		{
			boolean                         lb_causalSeleccionada;
			Collection<CausalCorreccion>    lccc_causales;
			Collection<SolicitudCorreccion> lcsc_correcciones;

			lb_causalSeleccionada     = false;
			lccc_causales             = getCausales();
			lcsc_correcciones         = null;

			if(CollectionUtils.isValidCollection(lccc_causales))
			{
				BigInteger lbi_idMatricula;
				String     ls_idSolicitud;
				String     ls_idCirculo;

				lcsc_correcciones     = new ArrayList<SolicitudCorreccion>();
				ls_idCirculo          = getIdCirculo();
				lbi_idMatricula       = NumericUtils.getBigInteger(getIdMatricula());
				ls_idSolicitud        = getIdSolicitud();

				if(StringUtils.isValidString(ls_idSolicitud))
				{
					boolean            lb_segregacion;
					Collection<String> lcs_causales;

					lb_segregacion     = false;
					lcs_causales       = new ArrayList<String>();

					for(CausalCorreccion lcc_causal : lccc_causales)
					{
						if((lcc_causal != null))
						{
							BigInteger          lbi_idCausalCorreccion;
							boolean             lb_seleccionado;
							String              ls_activo;
							String              ls_observacion;
							SolicitudCorreccion lsc_correccion;

							lbi_idCausalCorreccion     = lcc_causal.getIdCausalCorreccion();
							lb_seleccionado            = lcc_causal.isSeleccionado();
							ls_activo                  = lb_seleccionado ? EstadoCommon.SI : EstadoCommon.NO;
							ls_observacion             = lcc_causal.getObservaciones();
							lsc_correccion             = new SolicitudCorreccion();

							if(!lb_segregacion && NumericUtils.isValidBigInteger(lbi_idCausalCorreccion))
								lb_segregacion = CausalCorreccionCommon.MATRICULAS_SEGREGADAS.equalsIgnoreCase(
									    StringUtils.getString(lbi_idCausalCorreccion)
									) && lb_seleccionado;

							if(lb_seleccionado)
							{
								if(!StringUtils.isValidString(ls_observacion))
									lcs_causales.add(lcc_causal.getNombre());
								else
									lb_causalSeleccionada = true;
							}

							abrirPanelCausal(lbi_idCausalCorreccion, lb_seleccionado, false);

							if(NumericUtils.isValidBigInteger(lbi_idCausalCorreccion))
								lsc_correccion.setIdCausalCorreccion(lbi_idCausalCorreccion);
							else
								throw new B2BException(ErrorKeys.ERROR_CAUSAL_CORRECCION);

							lsc_correccion.setIdSolicitud(ls_idSolicitud);
							lsc_correccion.setIdCirculo(ls_idCirculo);
							lsc_correccion.setIdMatricula(lbi_idMatricula);
							lsc_correccion.setObservacion(ls_observacion);
							lsc_correccion.setSeleccionado(ls_activo);
							lsc_correccion.setIdUsuarioCreacion(getUserId());
							lsc_correccion.setIdUsuarioModificacion(getUserId());
							lsc_correccion.setIpCreacion(getRemoteIpAddress());
							lsc_correccion.setIpModificacion(getRemoteIpAddress());

							lcsc_correcciones.add(lsc_correccion);
						}
					}

					if(CollectionUtils.isValidCollection(lcs_causales))
					{
						Iterator<String> lis_terator;
						StringBuilder    lsb_sb;

						lis_terator     = lcs_causales.iterator();
						lsb_sb          = new StringBuilder();

						while(lis_terator.hasNext())
						{
							String ls_iterador;

							ls_iterador = lis_terator.next();

							if(StringUtils.isValidString(ls_iterador))
							{
								lsb_sb.append(IdentificadoresCommon.ESPACIO_VACIO);
								lsb_sb.append(ls_iterador);

								if(lis_terator.hasNext())
									lsb_sb.append(IdentificadoresCommon.SIMBOLO_COMA);
							}
						}

						Object[] loa_messageArgs = new String[1];
						loa_messageArgs[0] = lsb_sb.toString();

						throw new B2BException(ErrorKeys.ERROR_SIN_OBSERVACIONES_CAUSAL, loa_messageArgs);
					}

					if(lb_causalSeleccionada)
					{
						Long ll_idMatricula;

						ll_idMatricula = NumericUtils.getLongWrapper(lbi_idMatricula);

						irr_registroRemote.salvarCausalesCorreccion(
						    lcsc_correcciones, ls_idSolicitud,
						    ls_idCirculo + IdentificadoresCommon.SIMBOLO_GUION + lbi_idMatricula, lb_segregacion
						);

						cargarTabs(ls_idCirculo, ll_idMatricula, false);

						{
							Anotacion la_anotacion;

							la_anotacion = getAnotacion();

							if(la_anotacion != null)
							{
								setDatosAntSistemaAnotacion(la_anotacion.getDatosAntiguoSistema());

								Collection<Anotacion> lca_anotacionesAgregadas;

								lca_anotacionesAgregadas = la_anotacion.getAnotacionesAgregadas();

								if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
								{
									Collection<DetalleAntSistema> lcdas_detalles;

									lcdas_detalles = getDetallesAntSistema();

									for(Anotacion la_iterador : lca_anotacionesAgregadas)
									{
										if(la_iterador != null)
											la_iterador.setDetallesAntSistema(lcdas_detalles);
									}

									setAnotacionesAgregadas(lca_anotacionesAgregadas);
									setTotalAnotaciones(lca_anotacionesAgregadas.size());
								}

								cargarTiposOficinaAnotaciones(EstadoCommon.D);
							}
						}

						cargarCheks(ls_idCirculo, ll_idMatricula, ls_idSolicitud, lb_segregacion);
						setMostrarBandeja(false);
						setMostrarDetalle(true);
						setMostrarBandejaAnotaciones(true);
						setMostrarDetalleAnotaciones(false);

						PrimeFaces.current().ajax().update("idFormTabs");
					}
					else
						throw new B2BException(ErrorKeys.ERROR_SIN_CAUSAL_CORRECCION);
				}
				else
					throw new B2BException(ErrorKeys.ID_SOLICITUD_INVALIDO);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CAUSALES_CORRECCION_REGISTRADAS);

			cerrarDialogo(CS_dlgCorrecciones, CS_dlgCorrecciones);

			addMessage(MessagesKeys.PROCESO_COMPLETADO);

			{
				DatosBasicos ldb_datosBasicos;

				ldb_datosBasicos = getDatosBasicos();

				if(ldb_datosBasicos != null)
				{
					Apertura la_apertura;

					la_apertura = ldb_datosBasicos.getApertura();

					if(la_apertura != null)
					{
						String ls_idPaisApertura;

						ls_idPaisApertura = la_apertura.getIdPais();

						if(!StringUtils.isValidString(ls_idPaisApertura))
							la_apertura.setIdPais(IdentificadoresCommon.INDICATIVO_PAIS_DEFAULT);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_formulario + ":TvdetalleRegistroCalif");
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String salvarCheks()
	    throws B2BException
	{
		try
		{
			PermisosCorreccionesUI lpc_datos;

			lpc_datos = getPermisosCorrecciones();

			if(lpc_datos != null)
			{
				Object[] loa_arg;

				loa_arg = new String[2];

				validarPanelUbicacion(lpc_datos, loa_arg);
				validarPanelApertura(lpc_datos, loa_arg);
				validarPanelMatriculasAbiertas(lpc_datos, loa_arg);
				validarPanelCatastral(lpc_datos, loa_arg);
				validarPanelMatriculasSegregacion(lpc_datos, loa_arg);
				validarPanelDatosAntSistema(lpc_datos, loa_arg);
				validarPanelDetalleDatosAntSistema(lpc_datos, loa_arg);
				validarPanelLinderos(lpc_datos, loa_arg);
				validarPanelComplementacion(lpc_datos, loa_arg);
				validarPanelAreaPredio(lpc_datos, loa_arg);
				validarPanelDireccionesPredio(lpc_datos, loa_arg);

				{
					Map<String, Boolean>  lmsb_validarAnotaciones;
					Collection<Anotacion> lca_anotaciones;

					lca_anotaciones             = getAnotacionesAgregadas();
					lmsb_validarAnotaciones     = new HashMap<String, Boolean>();

					if(CollectionUtils.isValidCollection(lca_anotaciones))
					{
						for(Anotacion la_actual : lca_anotaciones)
						{
							if(la_actual != null)
								validarPanelesAnotacion(la_actual, lmsb_validarAnotaciones);
						}
					}

					if(CollectionUtils.isValidCollection(lmsb_validarAnotaciones))
					{
						boolean lb_anotacionModificada;
						String  ls_llaveMensaje;

						lb_anotacionModificada     = false;
						ls_llaveMensaje            = null;

						for(Map.Entry<String, Boolean> lmsb_actual : lmsb_validarAnotaciones.entrySet())
						{
							if(lmsb_actual != null)
							{
								boolean lb_temp;

								ls_llaveMensaje     = lmsb_actual.getKey();
								lb_temp             = BooleanUtils.getBooleanValue(lmsb_actual.getValue());

								if(!lb_anotacionModificada)
									lb_anotacionModificada = lb_temp;
							}
						}

						if(!lb_anotacionModificada)
						{
							if(StringUtils.isValidString(ls_llaveMensaje))
							{
								String[] lsa_data;

								lsa_data = ls_llaveMensaje.split(IdentificadoresCommon.SIMBOLO_GUION);

								if(CollectionUtils.isValidCollection(lsa_data))
								{
									loa_arg[0]     = lsa_data[0];
									loa_arg[1]     = CausalCorreccionCommon.PANEL_ANOTACIONES_NOMBRE;

									throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL_ANOTACION, loa_arg);
								}
							}
						}
					}

					lpc_datos.setAnotacionesAgregadas(lca_anotaciones);
				}

				lpc_datos.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));
				lpc_datos.setIdCirculo(getIdCirculo());
				lpc_datos.setIdMatricula(getIdMatricula());
				lpc_datos.setObservaciones(getObservaciones());

				irr_calificacionRemote.salvarCheksCorrecciones(
				    lpc_datos, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

				setMostrarDetalle(false);
				setMostrarBandeja(true);

				addMessage(MessagesKeys.SALVAR_CHECKS_PERMISOS_CORRECCION);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}

		return null;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String salvarPermisos()
	    throws B2BException
	{
		FacesContext lfc_context;
		String       ls_return;

		lfc_context     = FacesUtils.getFacesContext();
		ls_return       = null;

		try
		{
			BeanCorreccion lbc_bean;

			lbc_bean = (BeanCorreccion)lfc_context.getApplication()
					                                  .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_CORRECCION, BeanCorreccion.class
					);

			irr_calificacionRemote.salvarPermisos(
			    getObservaciones(), getIdTurnoHistoria(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			lbc_bean.setIdTurnoConsulta(null);
			lbc_bean.setNirConsulta(null);
			lbc_bean.generarDatosTramiteCantidad();

			ls_return = NavegacionCommon.ANALISIS_CORRECCION;

			clean();
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update(CS_messageIdGrowl);
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de marcar los detalleAntSistema seleccionado por pantalla para su asociacion con la anotacion seleccionada.
	 *
	 * @param adas_data correspondiente al valor del tipo de objeto DetalleAntSistema
	 */
	public void seleccionarDatosAntSistemaAnotacion(DetalleAntSistema adas_data)
	{
		Anotacion la_anotacioncargada;

		la_anotacioncargada = getAnotacion();

		if(la_anotacioncargada != null)
		{
			Collection<DetalleAntSistema> lcdas_detalles;

			lcdas_detalles = la_anotacioncargada.getDetallesAntSistema();

			if((adas_data != null) && adas_data.isSeleccionado())
			{
				if(CollectionUtils.isValidCollection(lcdas_detalles))
				{
					Iterator<DetalleAntSistema> lidas_iterator;

					lidas_iterator = lcdas_detalles.iterator();

					while(lidas_iterator.hasNext())
					{
						DetalleAntSistema ldas_detalle;

						ldas_detalle = lidas_iterator.next();

						if((ldas_detalle != null) && (ldas_detalle != adas_data))
							ldas_detalle.setSeleccionado(false);
					}
				}
			}
		}
	}

	/**
	 * Método encargado de validar los check dependientes de un check particular.
	 *
	 * @param as_panel Objeto de tipo <code>String</code> que contiene nombre del panel a validar
	 * @param as_campo Objeto de tipo <code>String</code> que contiene nombre del campo a validar
	 * @param ab_accion Variable de tipo <code>boolean</code> que contiene valor del check a validar
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarCheks(String as_panel, String as_campo, boolean ab_accion)
	    throws B2BException
	{
		try
		{
			if(StringUtils.isValidString(as_panel) && StringUtils.isValidString(as_campo))
			{
				PermisosCorreccionesUI lpc_datos;

				lpc_datos = getPermisosCorrecciones();

				if(lpc_datos != null)
				{
					if(as_panel.equalsIgnoreCase("ubicacion"))
					{
						PanelUbicacion lpu_datos;

						lpu_datos = lpc_datos.getUbicacion();

						if(lpu_datos != null)
						{
							boolean lb_municipio;
							boolean lb_vereda;

							lb_municipio     = lpu_datos.isMunicipio();
							lb_vereda        = lpu_datos.isVereda();

							if(as_campo.equalsIgnoreCase("municipio"))
								lpu_datos.setVereda(ab_accion);
							else if(as_campo.equalsIgnoreCase("vereda"))
							{
								if(lb_municipio && !lb_vereda)
								{
									lpu_datos.setVereda(true);

									Object[] loa_arg;

									loa_arg     = new String[2];

									loa_arg[0]     = "Vereda";
									loa_arg[1]     = "Municipio";

									throw new B2BException(ErrorKeys.ERROR_CHEKS, loa_arg);
								}
							}
						}
					}
					else if(as_panel.equalsIgnoreCase("apertura") || as_panel.equalsIgnoreCase("documento"))
					{
						PanelDatosDocumento la_datos;

						la_datos = as_panel.equalsIgnoreCase("apertura") ? lpc_datos.getApertura()
							                                             : getAnotacion().getPanelDatosDocumento();

						if(la_datos != null)
						{
							boolean lb_tipoOficina;
							boolean lb_pais;
							boolean lb_departamento;
							boolean lb_municipio;
							boolean lb_mostrarError;
							String  ls_campoActual;
							String  ls_campos;

							lb_tipoOficina      = la_datos.isTipoOficina();
							lb_pais             = la_datos.isPais();
							lb_departamento     = la_datos.isDepartamento();
							lb_municipio        = la_datos.isMunicipio();
							lb_mostrarError     = false;
							ls_campoActual      = "";
							ls_campos           = "";

							if(as_campo.equalsIgnoreCase("tipoOficina"))
							{
								la_datos.setPais(ab_accion);
								la_datos.setDepartamento(ab_accion);
								la_datos.setMunicipio(ab_accion);
								la_datos.setOficinaOrigen(ab_accion);
							}
							else if(as_campo.equalsIgnoreCase("pais"))
							{
								if(!lb_tipoOficina)
								{
									la_datos.setDepartamento(ab_accion);
									la_datos.setMunicipio(ab_accion);
									la_datos.setOficinaOrigen(ab_accion);
								}
								else
								{
									ls_campoActual     = "país";
									ls_campos          = "tipo oficina";

									la_datos.setPais(true);
									lb_mostrarError = true;
								}
							}
							else if(as_campo.equalsIgnoreCase("departamento"))
							{
								if(!lb_tipoOficina && !lb_pais)
								{
									la_datos.setMunicipio(ab_accion);
									la_datos.setOficinaOrigen(ab_accion);
								}
								else
								{
									ls_campoActual = "departamento";

									if(lb_tipoOficina)
										ls_campos += "tipo oficina, ";

									if(lb_pais)
										ls_campos += "país";

									la_datos.setDepartamento(true);
									lb_mostrarError = true;
								}
							}
							else if(as_campo.equalsIgnoreCase("municipio"))
							{
								if(!lb_tipoOficina && !lb_pais && !lb_departamento)
									la_datos.setOficinaOrigen(ab_accion);
								else
								{
									ls_campoActual = "municipio";

									if(lb_tipoOficina)
										ls_campos += "tipo oficina, ";

									if(lb_pais)
										ls_campos += "país, ";

									if(lb_departamento)
										ls_campos += "departamento";

									la_datos.setMunicipio(true);
									lb_mostrarError = true;
								}
							}
							else if(as_campo.equalsIgnoreCase("oficinaOrigen"))
							{
								if(!lb_tipoOficina && !lb_pais && !lb_departamento && !lb_municipio)
								{
									la_datos.setTipoOficina(ab_accion);
									la_datos.setPais(ab_accion);
									la_datos.setDepartamento(ab_accion);
									la_datos.setMunicipio(ab_accion);
								}
								else if(lb_tipoOficina || lb_pais || lb_departamento || lb_municipio)
								{
									ls_campoActual = "oficina origen";

									if(lb_tipoOficina)
										ls_campos += "tipo oficina, ";

									if(lb_pais)
										ls_campos += "país, ";

									if(lb_departamento)
										ls_campos += "departamento, ";

									if(lb_municipio)
										ls_campos += "municipio";

									la_datos.setOficinaOrigen(true);
									lb_mostrarError = true;
								}
							}
							else if(as_campo.equalsIgnoreCase("tipoDocumento"))
							{
								la_datos.setTipoOficina(ab_accion);
								la_datos.setPais(ab_accion);
								la_datos.setDepartamento(ab_accion);
								la_datos.setMunicipio(ab_accion);
								la_datos.setOficinaOrigen(ab_accion);
							}

							if(lb_mostrarError)
							{
								Object[] loa_arg;

								loa_arg     = new String[2];

								loa_arg[0]     = ls_campoActual;
								loa_arg[1]     = ls_campos;

								throw new B2BException(ErrorKeys.ERROR_CHEKS, loa_arg);
							}
						}
					}
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
	 * Abrir panel causal.
	 *
	 * @param lbi_causal correspondiente al valor del tipo de objeto BigInteger
	 * @param ab_abrir correspondiente al valor del tipo de objeto boolean
	 * @param ab_salvedades correspondiente al valor del tipo de objeto boolean
	 */
	protected void abrirPanelCausal(BigInteger lbi_causal, boolean ab_abrir, boolean ab_salvedades)
	{
		String ls_idCausal;

		ls_idCausal = StringUtils.getString(lbi_causal);

		if(StringUtils.isValidString(ls_idCausal))
		{
			String ls_panel;
			String ls_panel2;
			String ls_panelTemp;

			ls_panel         = null;
			ls_panel2        = null;
			ls_panelTemp     = null;

			switch(ls_idCausal)
			{
				case CausalCorreccionCommon.DATOS_BASICOS:
					ls_panel = "wPanelUbicacion";
					ls_panel2 = "wPanelUbicacionSalvedad";

					break;

				case CausalCorreccionCommon.INFORMACION_DE_APERTURA:
					ls_panel = "wPanelApertura";
					ls_panel2 = "wPanelAperturaSalvedad";

					break;

				case CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS:
					ls_panel = "wPanelMatriculasBase";
					ls_panel2 = "wPanelMatriculasBaseSalvedad";

					break;

				case CausalCorreccionCommon.INFORMACION_CATASTRAL:
					ls_panel = "wPanelCatastral";
					ls_panel2 = "wPanelCatastralSalvedad";

					break;

				case CausalCorreccionCommon.MATRICULAS_SEGREGADAS:
					ls_panel = "wPanelSegregacion";
					ls_panelTemp = "wPanelSegregacionApertura";
					ls_panel2 = "wPanelSegregacionSalvedad";

					break;

				case CausalCorreccionCommon.LINDEROS:
					ls_panel = "wPanelLinderos";
					ls_panel2 = "wPanelLinderosSalvedad";

					break;

				case CausalCorreccionCommon.COMPLEMENTACION:
					ls_panel = "wPanelComplementacion";
					ls_panel2 = "wPanelComplementacionSalvedad";

					break;

				case CausalCorreccionCommon.AREA_DEL_PREDIO:
					ls_panel = "wPanelArea";
					ls_panel2 = "wPanelAreaSalvedad";

					break;

				case CausalCorreccionCommon.DIRECCION_DEL_PREDIO:
					ls_panel = "wPanelDireccion";
					ls_panel2 = "wPanelDireccionSalvedad";

					break;

				case CausalCorreccionCommon.DATOS_ANT_SISTEMA:
					ls_panel = "wPanelAntSistema";
					ls_panel2 = "wPanelAntSistemaSalvedad";

					break;

				case CausalCorreccionCommon.DETALLE_ANT_SISTEMA:
					ls_panel = "wPanelDetalleAntSistema";
					ls_panel2 = "wPanelDetalleAntSistemaSalvedad";

					break;

				case CausalCorreccionCommon.DATOS_ANOTACION:
					ls_panel = "wPanelDatosAnotacion";

					break;

				case CausalCorreccionCommon.DATOS_DEL_DOCUMENTO:
					ls_panel = "wPanelDatosDocumento";

					break;

				case CausalCorreccionCommon.DETALLE_ANT_SISTEMA_ANOTACION:
					ls_panel = "wPanelDetalleAntSistemaAnotacion";

					break;

				case CausalCorreccionCommon.ESPECIFICACION:
					ls_panel = "wPanelEspecificacion";

					break;

				case CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES:
					ls_panel = "wPanelIntervinientes";

					break;

				default:
					break;
			}

			abrirCerrarPanel(ab_abrir, ls_panel);
			abrirCerrarPanel(ab_abrir, ls_panelTemp);

			if(ab_salvedades)
				abrirCerrarPanel(ab_abrir, ls_panel2);
		}
	}

	/**
	 * Cargar cheks.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void cargarCheks(String as_idCirculo, Long al_idMatricula, String as_idSolicitud)
	    throws B2BException
	{
		cargarCheks(as_idCirculo, al_idMatricula, as_idSolicitud, false);
	}

	/**
	 * Cargar cheks.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto Long
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @param ab_segregacion vairbale boolean que indica si la causal segregación está activa.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	protected void cargarCheks(String as_idCirculo, Long al_idMatricula, String as_idSolicitud, boolean ab_segregacion)
	    throws B2BException
	{
		if(
		    StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idCirculo)
			    && NumericUtils.isValidLong(al_idMatricula)
		)
		{
			SolicitudCamposCorreccion lscc_camposCorreccion;
			PermisosCorreccionesUI    lpc_data;

			lscc_camposCorreccion = new SolicitudCamposCorreccion();

			lscc_camposCorreccion.setAnotacionesAgregadas(getAnotacionesAgregadas());
			lscc_camposCorreccion.setIdSolicitud(as_idSolicitud);
			lscc_camposCorreccion.setIdCirculo(as_idCirculo);
			lscc_camposCorreccion.setIdMatricula(al_idMatricula);
			lscc_camposCorreccion.setIdTurnoHistoria(NumericUtils.getLongWrapper(getIdTurnoHistoria()));

			lpc_data = irr_calificacionRemote.cargarCheksCorrecciones(
				    lscc_camposCorreccion, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lpc_data != null)
			{
				if(ab_segregacion)
				{
					PanelUbicacion lp_panel;

					lp_panel = lpc_data.getUbicacion();

					if(lp_panel != null)
					{
						lp_panel.setSeleccionado(true);
						lp_panel.setEstadoPredio(true);
					}
				}

				{
					PanelMatriculasAbiertas lpma_matriculasAbiertas;

					lpma_matriculasAbiertas = lpc_data.getMatriculasAbiertas();

					if(lpma_matriculasAbiertas != null)
					{
						DatosBasicos ldb_datosBasicos;

						ldb_datosBasicos = getDatosBasicos();

						if(ldb_datosBasicos != null)
						{
							if(lpma_matriculasAbiertas.isSeleccionado())
							{
								Map<String, SolicitudCamposCorreccion> lmsscc_cheks;

								lmsscc_cheks = lpma_matriculasAbiertas.getChecks();

								if(CollectionUtils.isValidCollection(lmsscc_cheks))
								{
									Collection<PredioSegregado> lcps_datos;
									MatriculaBase               lmb_matriculaBase;

									lcps_datos            = ldb_datosBasicos.getPredioSegregado();
									lmb_matriculaBase     = ldb_datosBasicos.getMatriculaBase();

									if(lmb_matriculaBase != null)
									{
										Collection<DireccionPredio> ldp_matriculas;

										ldp_matriculas = lmb_matriculaBase.getDireccionPredio();

										if(CollectionUtils.isValidCollection(ldp_matriculas))
										{
											for(DireccionPredio lps_iterator : ldp_matriculas)
											{
												if(lps_iterator != null)
												{
													Long   ll_idMatriculaSeg;
													String ls_idCirculoSeg;

													ll_idMatriculaSeg     = lps_iterator.getIdMatricula();
													ls_idCirculoSeg       = lps_iterator.getIdCirculo();

													if(
													    StringUtils.isValidString(ls_idCirculoSeg)
														    && NumericUtils.isValidLong(ll_idMatriculaSeg)
													)
														lps_iterator.setSeleccionado(
														    lmsscc_cheks.containsKey(
														        ls_idCirculoSeg + IdentificadoresCommon.SIMBOLO_GUION
														        + ll_idMatriculaSeg
														    )
														);
												}
											}
										}
									}

									if(CollectionUtils.isValidCollection(lcps_datos))
									{
										for(PredioSegregado lps_iterador : lcps_datos)
										{
											if(lps_iterador != null)
											{
												Long   ll_idMatriculaSeg;
												String ls_idCirculoSeg;

												ll_idMatriculaSeg     = lps_iterador.getIdMatricula();
												ls_idCirculoSeg       = lps_iterador.getIdCirculo();

												if(
												    StringUtils.isValidString(ls_idCirculoSeg)
													    && NumericUtils.isValidLong(ll_idMatriculaSeg)
												)
													lps_iterador.setSeleccionado(
													    lmsscc_cheks.containsKey(
													        ls_idCirculoSeg + IdentificadoresCommon.SIMBOLO_GUION
													        + ll_idMatriculaSeg
													    )
													);
											}
										}
									}
								}
							}

							if(lpma_matriculasAbiertas.isAgregar())
							{
								MatriculaBase lmb_data;

								lmb_data = ldb_datosBasicos.getMatriculaBase();

								if(lmb_data != null)
									lmb_data.setIdCirculo(as_idCirculo);
							}
						}
					}
				}

				{
					PanelMatriculasSegregacion lpms_matriculasSegregacion;

					lpms_matriculasSegregacion = lpc_data.getMatriculasSegregacion();

					if((lpms_matriculasSegregacion != null) && lpms_matriculasSegregacion.isSeleccionado())
					{
						Collection<PredioSegregado>            lcps_prediosSegregados;
						Map<String, SolicitudCamposCorreccion> lmsscc_cheks;

						lcps_prediosSegregados     = getPrediosSegregados();
						lmsscc_cheks               = lpms_matriculasSegregacion.getChecks();

						if(
						    CollectionUtils.isValidCollection(lcps_prediosSegregados)
							    && CollectionUtils.isValidCollection(lmsscc_cheks)
						)
						{
							for(PredioSegregado lps_iterator : lcps_prediosSegregados)
							{
								if(lps_iterator != null)
								{
									long   ll_idMatriculaSeg;
									String ls_idCirculoSeg;

									ll_idMatriculaSeg     = NumericUtils.getLong(lps_iterator.getIdMatricula1());
									ls_idCirculoSeg       = lps_iterator.getIdCirculo1();

									if(StringUtils.isValidString(ls_idCirculoSeg) && (ll_idMatriculaSeg > 0))
										lps_iterator.setSeleccionado(
										    lmsscc_cheks.containsKey(
										        ls_idCirculoSeg + IdentificadoresCommon.SIMBOLO_GUION
										        + ll_idMatriculaSeg
										    )
										);
								}
							}
						}
					}
				}

				{
					PanelDetalleAntSistemaSolicitud lpdass_antSistema;

					lpdass_antSistema = lpc_data.getDetalleAntSistema();

					if((lpdass_antSistema != null) && lpdass_antSistema.isSeleccionado())
					{
						Collection<DetalleAntSistema>          lcdas_detalles;
						Map<String, SolicitudCamposCorreccion> lmsscc_cheks;

						lcdas_detalles     = getDetallesAntSistema();
						lmsscc_cheks       = lpdass_antSistema.getChecks();

						setHabilitarAgregarDetalleAnt(lpdass_antSistema.isAgregar());

						if(
						    CollectionUtils.isValidCollection(lcdas_detalles)
							    && CollectionUtils.isValidCollection(lmsscc_cheks)
						)
						{
							for(DetalleAntSistema ldas_iterator : lcdas_detalles)
							{
								if(ldas_iterator != null)
								{
									String ls_idDetalle;

									ls_idDetalle = ldas_iterator.getIdDetalleAntSistema();

									if(StringUtils.isValidString(ls_idDetalle))
										ldas_iterator.setSeleccionado(lmsscc_cheks.containsKey(ls_idDetalle));
								}
							}
						}
					}
				}

				{
					PanelDireccionPredio lpdp_direccion;

					lpdp_direccion = lpc_data.getDireccionPredio();

					if((lpdp_direccion != null) && lpdp_direccion.isSeleccionado())
					{
						BeanDireccion                          lbd_beanDireccion;
						Collection<DireccionDelPredio>         lcdp_direcciones;
						Map<String, SolicitudCamposCorreccion> lmsscc_cheks;

						lbd_beanDireccion     = getBeanDireccion();
						lcdp_direcciones      = getDireccionesPredio();
						lmsscc_cheks          = lpdp_direccion.getChecks();

						lbd_beanDireccion.limpiarBeanDireccion();
						lbd_beanDireccion.setForm(CS_formulario);

						if(
						    CollectionUtils.isValidCollection(lcdp_direcciones)
							    && CollectionUtils.isValidCollection(lmsscc_cheks)
						)
						{
							Collection<Direccion> lcd_direcciones;

							lcd_direcciones = new ArrayList<Direccion>();

							for(DireccionDelPredio ldp_iterator : lcdp_direcciones)
							{
								if(ldp_iterator != null)
								{
									DireccionPredio    ldp_direccion;
									DireccionPredioAcc ldpa_direccion;

									ldp_direccion      = ldp_iterator.getDireccionPredioBng();
									ldpa_direccion     = ldp_iterator.getDireccionPredio();

									if(ldp_direccion != null)
									{
										String ls_idDireccion;

										ls_idDireccion = ldp_direccion.getIdDireccion();

										if(StringUtils.isValidString(ls_idDireccion))
										{
											boolean lb_seleccionado;

											lb_seleccionado = lmsscc_cheks.containsKey(ls_idDireccion);

											ldp_iterator.setSeleccionado(lb_seleccionado);
											ldpa_direccion.setSeleccionado(lb_seleccionado);
										}

										lcd_direcciones.add(new Direccion(ldpa_direccion));
									}
								}
							}

							lbd_beanDireccion.setDireccionesPredio(lcd_direcciones);
						}

						if(lpdp_direccion.isAgregar())
						{
							lbd_beanDireccion.setAgregarDireccionPredio(true);
//							lbd_beanDireccion.setDeshabilitarDatosUbicacion(true);
							lbd_beanDireccion.cargarDatosDireccionPredio(getDatosBasicos());
						}
					}
				}

				{
					Collection<Anotacion> lca_anotacionesAgregadas;

					lca_anotacionesAgregadas = lpc_data.getAnotacionesAgregadas();

					if(CollectionUtils.isValidCollection(lca_anotacionesAgregadas))
						llenarAnotacionesAgregadasChecks(lca_anotacionesAgregadas);

					setAnotacionesAgregadas(lca_anotacionesAgregadas);
				}
			}

			setPermisosCorrecciones(lpc_data);
		}
	}

	private void llenarAnotacionesAgregadasChecks(Collection<Anotacion> aca_anotacionesAgregadas)
	{
		if(CollectionUtils.isValidCollection(aca_anotacionesAgregadas))
		{
			for(Anotacion la_iterador : aca_anotacionesAgregadas)
			{
				if(la_iterador != null)
				{
					{
						PanelDetalleAntSistemaAnotacion lp_panel;

						lp_panel = la_iterador.getPanelDetalleAntSistemaAnotacion();

						if((lp_panel != null) && lp_panel.isSeleccionado())
						{
							Collection<DetalleAntSistema>          lcdas_detalles;
							Map<String, SolicitudCamposCorreccion> lmsscc_cheks;

							lcdas_detalles     = la_iterador.getDetallesAntSistema();
							lmsscc_cheks       = lp_panel.getCheks();

							if(
							    CollectionUtils.isValidCollection(lcdas_detalles)
								    && CollectionUtils.isValidCollection(lmsscc_cheks)
							)
							{
								boolean                     lb_iterar;
								Iterator<DetalleAntSistema> lidas_iterator;

								lb_iterar          = true;
								lidas_iterator     = lcdas_detalles.iterator();

								while(lidas_iterator.hasNext() && lb_iterar)
								{
									DetalleAntSistema ldas_iterador;

									ldas_iterador = lidas_iterator.next();

									if(ldas_iterador != null)
									{
										String ls_idDetalle;

										ls_idDetalle = ldas_iterador.getIdDetalleAntSistema();

										if(StringUtils.isValidString(ls_idDetalle))
										{
											lb_iterar = lmsscc_cheks.containsKey(ls_idDetalle);

											if(lb_iterar)
												ldas_iterador.setSeleccionado(lb_iterar);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Validar panel apertura.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelApertura(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelApertura lp_panel;

			lp_panel = apc_datos.getApertura();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				if(
				    !lp_panel.isFechaApertura() && !lp_panel.isRadicacion() && !lp_panel.isTipoDocumento()
					    && !lp_panel.isNumeroDocumento() && !lp_panel.isFechaDocumento() && !lp_panel.isTipoOficina()
					    && !lp_panel.isPais() && !lp_panel.isDepartamento() && !lp_panel.isMunicipio()
					    && !lp_panel.isOficinaOrigen()
				)
				{
					aoa_arg[0]     = CausalCorreccionCommon.INFORMACION_DE_APERTURA_NOMBRE;
					aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

					throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
				}
			}
		}
	}

	/**
	 * Validar panel area predio.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelAreaPredio(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelAreaPredio lp_panel;

			lp_panel = apc_datos.getAreaPredio();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				if(
				    !lp_panel.isAreaPrivada() && !lp_panel.isAreaConstruida() && !lp_panel.isCoeficiente()
					    && !lp_panel.isUsoPredio() && !lp_panel.isAreaTerreno()
				)
				{
					aoa_arg[0]     = CausalCorreccionCommon.AREA_DEL_PREDIO_NOMBRE;
					aoa_arg[1]     = CausalCorreccionCommon.PANEL_DESCRIPCION_PREDIO_NOMBRE;

					throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
				}
			}
		}
	}

	/**
	 * Validar panel catastral.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelCatastral(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelCatastral lp_panel;

			lp_panel = apc_datos.getCatastral();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				if(!lp_panel.isCodigoCatastral() && !lp_panel.isCodigoCatastralAnt())
				{
					aoa_arg[0]     = CausalCorreccionCommon.INFORMACION_CATASTRAL_NOMBRE;
					aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

					throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
				}
			}
		}
	}

	/**
	 * Validar panel complementacion.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelComplementacion(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelComplementacion lp_panel;

			lp_panel = apc_datos.getComplementacion();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				if(!lp_panel.isComplementacion())
				{
					aoa_arg[0]     = CausalCorreccionCommon.COMPLEMENTACION_NOMBRE;
					aoa_arg[1]     = CausalCorreccionCommon.PANEL_DESCRIPCION_PREDIO_NOMBRE;

					throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
				}
			}
		}
	}

	/**
	 * Validar panel datos ant sistema.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelDatosAntSistema(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelAntSistemaSolicitud lp_panel;

			lp_panel = apc_datos.getAntSistema();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				if(
				    !lp_panel.isTipoPredio() && !lp_panel.isNombrePredio() && !lp_panel.isMunicipio()
					    && !lp_panel.isCantidadCertificados()
				)
				{
					aoa_arg[0]     = CausalCorreccionCommon.DATOS_ANT_SISTEMA_NOMBRE;
					aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

					throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
				}
			}
		}
	}

	/**
	 * Validar panel detalle datos ant sistema.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelDetalleDatosAntSistema(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelDetalleAntSistemaSolicitud lp_panel;

			lp_panel = apc_datos.getDetalleAntSistema();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				Collection<DetalleAntSistema> lcdas_datos;

				lcdas_datos = getDetallesAntSistema();

				if(CollectionUtils.isValidCollection(lcdas_datos))
				{
					boolean                       lb_seleccionado;
					Collection<DetalleAntSistema> lcdas_salvar;
					Iterator<DetalleAntSistema>   lidas_iterator;

					lb_seleccionado     = false;
					lcdas_salvar        = new ArrayList<DetalleAntSistema>();
					lidas_iterator      = lcdas_datos.iterator();

					while(lidas_iterator.hasNext() && !lb_seleccionado)
					{
						DetalleAntSistema ldas_iterador;

						ldas_iterador = lidas_iterator.next();

						if(ldas_iterador != null)
						{
							lb_seleccionado = ldas_iterador.isSeleccionado();

							if(lb_seleccionado)
								lcdas_salvar.add(ldas_iterador);
						}
					}

					if(!lb_seleccionado && !lp_panel.isAgregar())
					{
						aoa_arg[0]     = CausalCorreccionCommon.DETALLE_ANT_SISTEMA_NOMBRE;
						aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

						throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
					}

					lp_panel.setDetallesAntSistema(lcdas_salvar);
				}
			}
		}
	}

	/**
	 * Validar panel direcciones predio.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelDireccionesPredio(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelDireccionPredio lp_panel;

			lp_panel = apc_datos.getDireccionPredio();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				Collection<DireccionDelPredio> lcdp_datos;

				lcdp_datos = getDireccionesPredio();

				if(CollectionUtils.isValidCollection(lcdp_datos))
				{
					boolean                        lb_seleccionado;
					Collection<DireccionDelPredio> lcdp_salvar;
					Iterator<DireccionDelPredio>   lidp_iterator;

					lb_seleccionado     = false;
					lcdp_salvar         = new ArrayList<DireccionDelPredio>();
					lidp_iterator       = lcdp_datos.iterator();

					while(lidp_iterator.hasNext() && !lb_seleccionado)
					{
						DireccionDelPredio ldp_iterador;

						ldp_iterador = lidp_iterator.next();

						if(ldp_iterador != null)
						{
							lb_seleccionado = ldp_iterador.isSeleccionado();

							if(lb_seleccionado)
								lcdp_salvar.add(ldp_iterador);
						}
					}

					if(!lb_seleccionado && !lp_panel.isAgregar())
					{
						aoa_arg[0]     = CausalCorreccionCommon.DIRECCION_DEL_PREDIO_NOMBRE;
						aoa_arg[1]     = CausalCorreccionCommon.PANEL_DESCRIPCION_PREDIO_NOMBRE;

						throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
					}

					lp_panel.setDirecciones(lcdp_salvar);
				}
			}
		}
	}

	/**
	 * Validar panel linderos.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelLinderos(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelLinderos lp_panel;

			lp_panel = apc_datos.getLinderos();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				if(!lp_panel.isLinderos())
				{
					aoa_arg[0]     = CausalCorreccionCommon.LINDEROS_NOMBRE;
					aoa_arg[1]     = CausalCorreccionCommon.PANEL_DESCRIPCION_PREDIO_NOMBRE;

					throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
				}
			}
		}
	}

	/**
	 * Validar panel matriculas abiertas.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelMatriculasAbiertas(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelMatriculasAbiertas lp_panel;

			lp_panel = apc_datos.getMatriculasAbiertas();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				DatosBasicos ldb_datos;

				ldb_datos = getDatosBasicos();

				if(ldb_datos != null)
				{
					Collection<PredioSegregado> lcps_datos;

					lcps_datos = ldb_datos.getPredioSegregado();

					if(CollectionUtils.isValidCollection(lcps_datos))
					{
						boolean                     lb_seleccionado;
						Collection<PredioSegregado> lcps_guardar;
						Iterator<PredioSegregado>   lips_iterator;

						lb_seleccionado     = false;
						lcps_guardar        = new ArrayList<PredioSegregado>();
						lips_iterator       = lcps_datos.iterator();

						while(lips_iterator.hasNext())
						{
							PredioSegregado lps_iterador;

							lps_iterador = lips_iterator.next();

							if(lps_iterador != null)
							{
								boolean lb_add;

								lb_add = lps_iterador.isSeleccionado();

								if(lb_add)
								{
									lb_seleccionado = true;

									lcps_guardar.add(lps_iterador);
								}
							}
						}

						if(!lb_seleccionado && !lp_panel.isAgregar())
						{
							aoa_arg[0]     = CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS_NOMBRE;
							aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

							throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
						}

						lp_panel.setMatriculas(lcps_guardar);
					}
					else
					{
						if(!lp_panel.isAgregar())
						{
							aoa_arg[0]     = CausalCorreccionCommon.MATRICULAS_ABIERTAS_CON_BASE_EN_LAS_SIGUIENTES_MATRICULAS_NOMBRE;
							aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

							throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
						}
					}
				}
			}
		}
	}

	/**
	 * Validar panel matriculas segregacion.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelMatriculasSegregacion(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelMatriculasSegregacion lp_panel;

			lp_panel = apc_datos.getMatriculasSegregacion();

			if((lp_panel != null) && lp_panel.isSeleccionado())
			{
				Collection<PredioSegregado> lcps_datos;

				lcps_datos = getPrediosSegregados();

				if(CollectionUtils.isValidCollection(lcps_datos))
				{
					boolean                     lb_seleccionado;
					Collection<PredioSegregado> lcps_salvar;
					Iterator<PredioSegregado>   lips_iterator;

					lb_seleccionado     = false;
					lcps_salvar         = new ArrayList<PredioSegregado>();
					lips_iterator       = lcps_datos.iterator();

					while(lips_iterator.hasNext())
					{
						PredioSegregado lps_iterador;

						lps_iterador = lips_iterator.next();

						if(lps_iterador != null)
						{
							lb_seleccionado = lps_iterador.isSeleccionado();

							if(lb_seleccionado)
								lcps_salvar.add(lps_iterador);
						}
					}

					if(lp_panel.isAgregarNueva() && !NumericUtils.isValidLong(lp_panel.getCantidad(), 1L))
						throw new B2BException(ErrorKeys.ERROR_CANTIDAD_MATRICULAS_INVALIDO);

					if(!lb_seleccionado && !lp_panel.isAgregarExistente() && !lp_panel.isAgregarNueva())
					{
						aoa_arg[0]     = CausalCorreccionCommon.MATRICULAS_SEGREGADAS_NOMBRE;
						aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

						throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
					}

					lp_panel.setMatriculas(lcps_salvar);
				}
				else
				{
					if(!lp_panel.isAgregarExistente() && !lp_panel.isAgregarNueva())
					{
						aoa_arg[0]     = CausalCorreccionCommon.MATRICULAS_SEGREGADAS_NOMBRE;
						aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

						throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
					}
					else if(lp_panel.isAgregarNueva())
					{
						Long ll_cantidad;

						ll_cantidad = lp_panel.getCantidad();

						if(!NumericUtils.isValidLong(ll_cantidad, 1))
							throw new B2BException(ErrorKeys.CANTIDAD_MATRICULAS_APERTURAR_CERO);
					}
				}
			}
		}
	}

	/**
	 * Validar panel ubicacion.
	 *
	 * @param apc_datos correspondiente al valor del tipo de objeto PermisosCorreccionesUI
	 * @param aoa_arg correspondiente al valor del tipo de objeto Object[]
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelUbicacion(PermisosCorreccionesUI apc_datos, Object[] aoa_arg)
	    throws B2BException
	{
		if((apc_datos != null) && CollectionUtils.isValidCollection(aoa_arg))
		{
			PanelUbicacion lp_panel;

			lp_panel = apc_datos.getUbicacion();

			if(lp_panel != null)
			{
				PanelMatriculasSegregacion lp_panelSegregacion;

				lp_panelSegregacion = apc_datos.getMatriculasSegregacion();

				if((lp_panelSegregacion != null) && lp_panelSegregacion.isSeleccionado())
				{
					lp_panel.setSeleccionado(true);
					lp_panel.setEstadoPredio(true);
				}

				if(lp_panel.isSeleccionado())
				{
					if(
					    !lp_panel.isMunicipio() && !lp_panel.isVereda() && !lp_panel.isEstadoPredio()
						    && !lp_panel.isNupre()
					)
					{
						aoa_arg[0]     = CausalCorreccionCommon.DATOS_BASICOS_NOMBRE;
						aoa_arg[1]     = CausalCorreccionCommon.PANEL_DATOS_BASICOS_NOMBRE;

						throw new B2BException(ErrorKeys.ERROR_CHEKS_PANEL, aoa_arg);
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de validar los cheks de los paneles para la anotacion seleccionada.
	 *
	 * @param aa_datos Objeto de tipo <code>Anotacion</code> que contiene informacion a validar
	 * @param amsb_validarAnotaciones Objeto de tipo <code>Map<String, Boolean></code> que relaciona la informacion de los paneles modificados en pantalla
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private void validarPanelesAnotacion(Anotacion aa_datos, Map<String, Boolean> amsb_validarAnotaciones)
	    throws B2BException
	{
		boolean lb_anotacionModificada;

		if((aa_datos != null) && (amsb_validarAnotaciones != null))
		{
			{
				PanelDatosAnotacion lpda_panel;

				lpda_panel                 = aa_datos.getPanelDatosAnotaciones();
				lb_anotacionModificada     = true;

				if((lpda_panel != null) && lpda_panel.isSeleccionado())
				{
					if(
					    !lpda_panel.isNumeroAnotacion() && !lpda_panel.isFechaAnotacion() && !lpda_panel.isRadicacion()
						    && !lpda_panel.isEstadoAnotacion()
					)
						lb_anotacionModificada = false;

					if(lb_anotacionModificada)
						amsb_validarAnotaciones.put(
						    CausalCorreccionCommon.DATOS_ANOTACION_NOMBRE, new Boolean(lb_anotacionModificada)
						);
					else
					{
						boolean lb_b;

						lb_b = BooleanUtils.getBooleanValue(
							    amsb_validarAnotaciones.get(CausalCorreccionCommon.DATOS_ANOTACION_NOMBRE)
							);

						if(!lb_b)
							amsb_validarAnotaciones.put(
							    CausalCorreccionCommon.DATOS_ANOTACION_NOMBRE, new Boolean(lb_anotacionModificada)
							);
					}
				}
			}

			{
				PanelDatosDocumento lpdd_panel;

				lpdd_panel                 = aa_datos.getPanelDatosDocumento();
				lb_anotacionModificada     = true;

				if((lpdd_panel != null) && lpdd_panel.isSeleccionado())
				{
					if(
					    !lpdd_panel.isTipoDocumento() && !lpdd_panel.isNumeroDocumento()
						    && !lpdd_panel.isFechaDocumento() && !lpdd_panel.isTipoOficina() && !lpdd_panel.isPais()
						    && !lpdd_panel.isDepartamento() && !lpdd_panel.isMunicipio()
						    && !lpdd_panel.isOficinaOrigen()
					)
						lb_anotacionModificada = false;

					if(lb_anotacionModificada)
						amsb_validarAnotaciones.put(
						    CausalCorreccionCommon.DATOS_DEL_DOCUMENTO_NOMBRE, new Boolean(lb_anotacionModificada)
						);
					else
					{
						boolean lb_b;

						lb_b = BooleanUtils.getBooleanValue(
							    amsb_validarAnotaciones.get(CausalCorreccionCommon.DATOS_DEL_DOCUMENTO_NOMBRE)
							);

						if(!lb_b)
							amsb_validarAnotaciones.put(
							    CausalCorreccionCommon.DATOS_DEL_DOCUMENTO_NOMBRE, new Boolean(lb_anotacionModificada)
							);
					}
				}
			}

			{
				PanelEspecificacion lpe_panel;

				lpe_panel                  = aa_datos.getPanelEspecificacion();
				lb_anotacionModificada     = true;

				if((lpe_panel != null) && lpe_panel.isSeleccionado())
				{
					if(
					    !lpe_panel.isCodigoActo() && !lpe_panel.isDescripcionActo() && !lpe_panel.isValorActo()
						    && !lpe_panel.isAnotacionCancela() && !lpe_panel.isComentario()
					)
						lb_anotacionModificada = false;

					if(lb_anotacionModificada)
						amsb_validarAnotaciones.put(
						    CausalCorreccionCommon.ESPECIFICACION_NOMBRE, new Boolean(lb_anotacionModificada)
						);
					else
					{
						boolean lb_b;

						lb_b = BooleanUtils.getBooleanValue(
							    amsb_validarAnotaciones.get(CausalCorreccionCommon.ESPECIFICACION_NOMBRE)
							);

						if(!lb_b)
							amsb_validarAnotaciones.put(
							    CausalCorreccionCommon.ESPECIFICACION_NOMBRE, new Boolean(lb_anotacionModificada)
							);
					}
				}
			}

			{
				PanelIntervinientes lpi_panel;

				lpi_panel = aa_datos.getPanelIntervinientes();

				if((lpi_panel != null) && lpi_panel.isSeleccionado())
				{
					Collection<Anotacion> lca_intervinientes;
					boolean               lb_intervinienteModificado;

					lca_intervinientes             = aa_datos.getIntervinientesAgregados();
					lb_intervinienteModificado     = false;

					if(CollectionUtils.isValidCollection(lca_intervinientes))
					{
						for(Anotacion la_actual : lca_intervinientes)
						{
							if((la_actual != null) && !lb_intervinienteModificado)
							{
								PanelDetalleIntervinientes lpdi_panel;

								lpdi_panel = la_actual.getPanelDetalleIntervinientes();

								if(lpdi_panel != null)
								{
									if(
									    lpdi_panel.isDatosPersona() || lpdi_panel.isRol() || lpdi_panel.isPorcentaje()
										    || lpdi_panel.isPropietario() || lpdi_panel.isCalidadInterviniente()
										    || lpi_panel.isAgregar()
									)
										lb_intervinienteModificado = true;
								}
							}
						}

						amsb_validarAnotaciones.put(
						    CausalCorreccionCommon.DATOS_BASICOS_DEL_INTEVINIENTES_NOMBRE
						    + IdentificadoresCommon.ESPACIO_VACIO + IdentificadoresCommon.SIMBOLO_GUION
						    + IdentificadoresCommon.ESPACIO_VACIO + aa_datos.getIdAnotacion(),
						    new Boolean(lb_intervinienteModificado)
						);
					}
				}
			}
		}
	}
}
