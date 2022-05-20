package com.bachue.snr.prosnr01.web.bean.homologacionActosLiquidacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.admHistoricosMisional.AdmHistoricosMisionalRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmActos;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.PagosUI;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanTurnos;
import com.bachue.snr.prosnr01.web.util.FuncionalidadesHomologacionActos;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * Clase que contiene todos las propiedades y acciones de BeanHomologacionActosLiquidacion.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanHomologacionActosLiquidacion")
public class BeanHomologacionActosLiquidacion extends BeanCalificacion implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6310431050777140648L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanHomologacionActosLiquidacion.class);

	/** Constante CS_ID_FORM. */
	private static final String CS_ID_FORM = ":fHomologacionActosLiquidacion";

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = "fHomologacionActosLiquidacion:idGrowl";

	/** Propiedad aa acto A eliminar. */
	private Acto aa_actoAEliminar;

	/** Propiedad iahmr adm historicos misional remote. */
	@EJB
	private AdmHistoricosMisionalRemote iahmr_admHistoricosMisionalRemote;

	/** Propiedad iah acto A homologar. */
	private AdmHomologacion iah_actoAHomologar;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad ica datos acto. */
	private Collection<Acto> ica_datosActo;

	/** Propiedad icah actos homologados Y agregados. */
	private Collection<AdmHomologacion> icah_actosHomologadosYAgregados;

	/** Propiedad icah adm homologacion. */
	private Collection<AdmHomologacion> icah_admHomologacion;

	/** Propiedad icah homologacion. */
	private Collection<AdmHomologacion> icah_homologacion;

	/** Propiedad icpuo pagos. */
	private Collection<PagosUI> icpuo_pagos;

	/** Propiedad icsm matriculas. */
	private Collection<SolicitudMatricula> icsm_matriculas;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id turno. */
	private String is_idTurno;

	/** Propiedad is id turno historia. */
	private String is_idTurnoHistoria;

	/** Propiedad is observaciones. */
	private String is_observaciones;

	/** Propiedad ib administracion homologacion actos liquidacion. */
	private boolean ib_administracionHomologacionActosLiquidacion;

	/**
	 * Modifica el valor de acto A eliminar.
	 *
	 * @param aa_a asigna el valor a la propiedad acto A eliminar
	 */
	public void setActoAEliminar(com.bachue.snr.prosnr01.model.sdb.acc.Acto aa_a)
	{
		aa_actoAEliminar = aa_a;
	}

	/**
	 * Retorna el valor de acto A eliminar.
	 *
	 * @return el valor de acto A eliminar
	 */
	public Acto getActoAEliminar()
	{
		return aa_actoAEliminar;
	}

	/**
	 * Modifica el valor de acto A homologar.
	 *
	 * @param aah_ah asigna el valor a la propiedad acto A homologar
	 */
	public void setActoAHomologar(AdmHomologacion aah_ah)
	{
		iah_actoAHomologar = aah_ah;
	}

	/**
	 * Retorna el valor de acto A homologar.
	 *
	 * @return el valor de acto A homologar
	 */
	public AdmHomologacion getActoAHomologar()
	{
		return iah_actoAHomologar;
	}

	/**
	 * Modifica el valor de actos homologados Y agregados.
	 *
	 * @param acah_cah asigna el valor a la propiedad actos homologados Y agregados
	 */
	public void setActosHomologadosYAgregados(Collection<AdmHomologacion> acah_cah)
	{
		icah_actosHomologadosYAgregados = acah_cah;
	}

	/**
	 * Retorna el valor de actos homologados Y agregados.
	 *
	 * @return el valor de actos homologados Y agregados
	 */
	public Collection<AdmHomologacion> getActosHomologadosYAgregados()
	{
		return icah_actosHomologadosYAgregados;
	}

	/**
	 * Modifica el valor de adm homologacion.
	 *
	 * @param acah_cah asigna el valor a la propiedad adm homologacion
	 */
	public void setAdmHomologacion(Collection<AdmHomologacion> acah_cah)
	{
		icah_admHomologacion = acah_cah;
	}

	/**
	 * Retorna el valor de adm homologacion.
	 *
	 * @return el valor de adm homologacion
	 */
	public Collection<AdmHomologacion> getAdmHomologacion()
	{
		return icah_admHomologacion;
	}

	/**
	 * Retorna Objeto o variable de valor pagos.
	 *
	 * @return Retorna el valor de la propiedad icpuo_pagos
	 */
	public Collection<PagosUI> getPagos()
	{
		return icpuo_pagos;
	}

	/**
	 * Modifica el valor de Pagos.
	 *
	 * @param acpui_pui de acpui pui
	 */
	public void setPagos(Collection<PagosUI> acpui_pui)
	{
		icpuo_pagos = acpui_pui;
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos acto.
	 *
	 * @param aca_ca asigna el valor a la propiedad datos acto
	 */
	public void setDatosActo(Collection<com.bachue.snr.prosnr01.model.sdb.acc.Acto> aca_ca)
	{
		ica_datosActo = aca_ca;
	}

	/**
	 * Retorna el valor de datos acto.
	 *
	 * @return el valor de datos acto
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.acc.Acto> getDatosActo()
	{
		return ica_datosActo;
	}

	/**
	 * Modifica el valor de homologacion.
	 *
	 * @param acah_cah asigna el valor a la propiedad homologacion
	 */
	public void setHomologacion(Collection<AdmHomologacion> acah_cah)
	{
		icah_homologacion = acah_cah;
	}

	/**
	 * Retorna el valor de homologacion.
	 *
	 * @return el valor de homologacion
	 */
	public Collection<AdmHomologacion> getHomologacion()
	{
		return icah_homologacion;
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
	 * Modifica el valor de id solicitud.
	 *
	 * @param as_s asigna el valor a la propiedad id solicitud
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna el valor de id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
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
	 * Valida la propiedad administracion homologacion actos liquidacion.
	 *
	 * @return Retorna el valor de la propiedad administracionHomologacionActosLiquidacion
	 */
	public boolean isAdministracionHomologacionActosLiquidacion()
	{
		return ib_administracionHomologacionActosLiquidacion;
	}

	/**
	 * Modifica el valor de AdministracionHomologacionActosLiquidacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setAdministracionHomologacionActosLiquidacion(boolean ab_b)
	{
		ib_administracionHomologacionActosLiquidacion = ab_b;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas.
	 *
	 * @return Retorna el valor de la propiedad matriculas
	 */
	public Collection<SolicitudMatricula> getMatriculas()
	{
		return icsm_matriculas;
	}

	/**
	 * Modifica el valor de Matriculas.
	 *
	 * @param acsm_sm de acsm sm
	 */
	public void setMatriculas(Collection<SolicitudMatricula> acsm_sm)
	{
		icsm_matriculas = acsm_sm;
	}

	/**
	 * Metodo encargado de agregar el acto seleccionado en pantalla.
	 *
	 * @param aah_admHomologacion correspondiente al valor del tipo de objeto AdmHomologacion
	 */
	public void agregarActo(AdmHomologacion aah_admHomologacion)
	{
		try
		{
			if(aah_admHomologacion != null)
			{
				FuncionalidadesHomologacionActos lfha_funcionalidades;

				lfha_funcionalidades = (FuncionalidadesHomologacionActos)obtenerInstanciaBean(
					    FuncionalidadesHomologacionActos.class, BeanNameCommon.BEAN_FUNCIONALIDADES_HOMOLOGACION_ACTO
					);

				if(lfha_funcionalidades != null)
				{
					setActosHomologadosYAgregados(
					    lfha_funcionalidades.agregarActo(
					        aah_admHomologacion, getIdTurno(), getActosHomologadosYAgregados()
					    )
					);

					consultarActosPorIdSolicitudCirculo();

					addMessage(MessagesKeys.ACTO_AGREGADO);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("agregarActo", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Validar acto nat juridica acto migrado.
	 *
	 * @param as_idTipoActo de as id tipo acto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarActoNatJuridicaActoMigrado(String as_idTipoActo)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idTipoActo))
		{
			AdmHomologacion lah_admHomologacion;

			lah_admHomologacion = getActoAHomologar();

			if(lah_admHomologacion != null)
			{
				AdmActos laa_admActos;

				laa_admActos = lah_admHomologacion.getAdmActos();

				if(laa_admActos != null)
				{
					String ls_cuantia;

					ls_cuantia = laa_admActos.getCuantia();

					if(StringUtils.isValidString(ls_cuantia))
					{
						boolean lb_validacion;

						lb_validacion = irr_registroRemote.validarActoNatJuridicaActoMigrado(
							    as_idTipoActo, ls_cuantia, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

						if(lb_validacion)
							addMessage(MessagesKeys.ACTO_HOMOLOGACION);
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de consultar todos los actos de una solicitud y circulo.
	 */
	public void consultarActosPorIdSolicitudCirculo()
	{
		try
		{
			setDatosActo(
			    irr_calificacionRemote.findActosByIdSolicitudCirculo(
			        getIdSolicitud(), getIdCirculo(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarActosPorIdSolicitudCirculo", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de consultar los actos a homologar en el sistema.
	 */
	public void consultarAdmHomologacion()
	{
		try
		{
			Collection<AdmHomologacion> lcah_homologacion;

			lcah_homologacion = iahmr_admHistoricosMisionalRemote.consultarAdmHomologacion(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(CollectionUtils.isValidCollection(lcah_homologacion))
			{
				int li_contador;

				li_contador = 1;

				for(AdmHomologacion lah_iterador : lcah_homologacion)
				{
					if(lah_iterador != null)
						lah_iterador.setCodigoActoHomologado(li_contador++);
				}
			}

			setAdmHomologacion(lcah_homologacion);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAdmHomologacion", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Consultar pagos.
	 */
	public void consultarPagos()
	{
		try
		{
			Collection<PagosUI> lcpui_pagos;

			lcpui_pagos = iahmr_admHistoricosMisionalRemote.consultarPagos(
				    getIdTurno(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			setPagos(lcpui_pagos);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAdmHomologacion", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de eliminar el acto seleccionado.
	 */
	public void eliminarActo()
	{
		try
		{
			FuncionalidadesHomologacionActos lfha_funcionalidades;

			lfha_funcionalidades = (FuncionalidadesHomologacionActos)obtenerInstanciaBean(
				    FuncionalidadesHomologacionActos.class, BeanNameCommon.BEAN_FUNCIONALIDADES_HOMOLOGACION_ACTO
				);

			if(lfha_funcionalidades != null)
			{
				lfha_funcionalidades.eliminarActo(getActoAEliminar());

				consultarActosPorIdSolicitudCirculo();

				addMessage(MessagesKeys.REGISTRO_ELIMINADO);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("eliminarActo", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de buscar todos los tipos de actos activos en el sistema.
	 * @param adh_registro Argumento de tipo AdmHomologacion que contiene el registro
	 * que será homologado.
	 */
	public void findAllTiposActoActivos(AdmHomologacion adh_registro)
	{
		try
		{
			if(adh_registro != null)
			{
				Collection<AdmHomologacion> lcah_homologacion;
				Collection<TipoActo>        lcta_cta;

				lcah_homologacion     = new ArrayList<AdmHomologacion>();
				lcta_cta              = irr_referenceRemote.findAllTiposActoActivos(
					    getUserId(), getLocalIpAddress(), getRemoteIpAddress(), true, false, false
					);

				if(CollectionUtils.isValidCollection(lcta_cta))
				{
					for(TipoActo lta_iterador : lcta_cta)
					{
						if(lta_iterador != null)
						{
							AdmHomologacion lah_homologacion;

							lah_homologacion = new AdmHomologacion();

							lah_homologacion.setTipoActo(lta_iterador);

							lcah_homologacion.add(lah_homologacion);
						}
					}

					setHomologacion(lcah_homologacion);
					setActoAHomologar(adh_registro);

					{
						PrimeFaces lp_current;

						lp_current = PrimeFaces.current();

						lp_current.executeScript("PF('wVTiposActos').show();");
						lp_current.ajax().update("fTiposActos:idDtHomologacion");
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("findAllTiposActoActivos", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/** {@inheritdoc} */
	public void generarDatosTramiteCantidad()
	    throws B2BException
	{
		super.generarDatosTramiteCantidad(CS_ID_FORM, "" + EtapaCommon.ID_ETAPA_HOMOLOGACION_ACTOS_LIQUIDACION, true);
	}

	/**
	 * Metodo encargado de homologar el acto seleccionado en pantalla.
	 */
	public void homologarActo()
	{
		try
		{
			FuncionalidadesHomologacionActos lfha_funcionalidades;

			lfha_funcionalidades = (FuncionalidadesHomologacionActos)obtenerInstanciaBean(
				    FuncionalidadesHomologacionActos.class, BeanNameCommon.BEAN_FUNCIONALIDADES_HOMOLOGACION_ACTO
				);

			if(lfha_funcionalidades != null)
			{
				setAdmHomologacion(
				    lfha_funcionalidades.homologarActo(getHomologacion(), getActoAHomologar(), getAdmHomologacion())
				);

				PrimeFaces.current().executeScript("PF('wVTiposActos').hide();");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("homologarActo", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}
	}

	/**
	 * Metodo encargado de limpiar los datos cargados en sesión.
	 */
	public void limpiar()
	{
		setActoAEliminar(null);
		setActoAHomologar(null);
		setDatosActo(null);
		setActosHomologadosYAgregados(null);
		setAdmHomologacion(null);
		setAdministracionHomologacionActosLiquidacion(false);
		setHomologacion(null);
		setIdCirculo(null);
		setIdSolicitud(null);
		setIdTurno(null);
		setIdTurnoHistoria(null);
		setObservaciones(null);
		setMatriculas(null);
	}

	/**
	 * Metodo encargado de mostrar dialogo con mensaje para eliminar el acto seleccionado.
	 *
	 * @param ap_parametros Argumento de tipo Acto que contiene los datos necesarios
	 * para eliminar el acto.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void mostrarMensajeActoEliminar(Acto ap_parametros)
	    throws B2BException
	{
		if(ap_parametros != null)
		{
			setActoAEliminar(ap_parametros);

			PrimeFaces.current().executeScript("PF('wVEliminar').show();");
		}
	}

	/**
	 * Guardar matriculas asociadas.
	 *
	 * @param afha_funcionalidades de afha funcionalidades
	 * @param lb_administracionHomologacionActosLiquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarMatriculasAsociadas(
	    FuncionalidadesHomologacionActos afha_funcionalidades, boolean ab_administracionHomologacionActosLiquidacion
	)
	    throws B2BException
	{
		if(!ab_administracionHomologacionActosLiquidacion)
		{
			Collection<SolicitudMatricula> lcsm_solicitudMatricula;

			lcsm_solicitudMatricula = getMatriculas();

			if(CollectionUtils.isValidCollection(lcsm_solicitudMatricula))
			{
				for(SolicitudMatricula lsm_tmp : lcsm_solicitudMatricula)
				{
					if((lsm_tmp != null) && !StringUtils.isValidString(lsm_tmp.getIdActoSeleccionado()))
						throw new B2BException(ErrorKeys.ERROR_RELACIONAR_ACTOS_A_MATRICULAS);
				}

				afha_funcionalidades.guardarMatriculasAsociadas(lcsm_solicitudMatricula, getIdTurno());
			}
		}
	}

	/**
	 * Metodo encargado de terminar el proceso de homologación de actos.
	 * @return Regla de navegación cuando termina el proceso.
	 */
	public String terminarProcesoHomologacion()
	{
		String ls_retorno;

		ls_retorno = null;

		try
		{
			FuncionalidadesHomologacionActos lfha_funcionalidades;

			lfha_funcionalidades = (FuncionalidadesHomologacionActos)obtenerInstanciaBean(
				    FuncionalidadesHomologacionActos.class, BeanNameCommon.BEAN_FUNCIONALIDADES_HOMOLOGACION_ACTO
				);

			if(lfha_funcionalidades != null)
			{
				Collection<AdmHomologacion> lcadm_param;

				lcadm_param = getActosHomologadosYAgregados();

				if(CollectionUtils.isValidCollection(lcadm_param))
				{
					boolean lb_administracionHomologacionActosLiquidacion;

					lb_administracionHomologacionActosLiquidacion = isAdministracionHomologacionActosLiquidacion();

					guardarMatriculasAsociadas(lfha_funcionalidades, lb_administracionHomologacionActosLiquidacion);

					lfha_funcionalidades.terminarProcesoHomologacion(
					    lcadm_param, getAdmHomologacion(), getIdTurnoHistoria(),
					    lb_administracionHomologacionActosLiquidacion, getObservaciones(), getIdTurno()
					);
					limpiar();

					if(lb_administracionHomologacionActosLiquidacion)
					{
						BeanAdministracionHomologacionActosLiquidacion lbahal_bean;

						lbahal_bean = (BeanAdministracionHomologacionActosLiquidacion)obtenerInstanciaBean(
							    BeanAdministracionHomologacionActosLiquidacion.class,
							    BeanNameCommon.BEAN_ADMINISTRACION_HOMOLOGACION_ACTOS_LIQUIDACION
							);

						if(lbahal_bean != null)
						{
							lbahal_bean.setIdTurnoConsulta(null);
							lbahal_bean.setNirConsulta(null);
							lbahal_bean.setDatosTramiteCantidad(null);

							ls_retorno = NavegacionCommon.ADMINISTRACION_HOMOLOGACION_ACTOS_LIQUIDACION;
						}
					}
					else
					{
						BeanTurnos lbt_bean;

						lbt_bean = (BeanTurnos)obtenerInstanciaBean(BeanTurnos.class, BeanNameCommon.BEAN_TURNOS);

						if(lbt_bean != null)
						{
							lbt_bean.setNirConsulta(null);
							lbt_bean.setIdTurnoConsulta(null);
							lbt_bean.generarData();

							ls_retorno = NavegacionCommon.TURNOS;
						}
					}
				}
				else
					throw new B2BException(ErrorKeys.DEBE_HOMOLOGAR_TODOS_LOS_ACTOS);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("terminarProcesoHomologacion", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(cs_ID_GROWL);
		}

		return ls_retorno;
	}

	/**
	 * Consultar matriculas por solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void consultarMatriculasPorSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_idSolicitud))
			setMatriculas(
			    iahmr_admHistoricosMisionalRemote.consultarMatriculasPorSolicitud(
			        as_idSolicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
	}
}
