package com.bachue.snr.prosnr01.web.bean.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmActos;
import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.PagosUI;

import com.bachue.snr.prosnr01.web.bean.registro.BeanRegistro;
import com.bachue.snr.prosnr01.web.util.FuncionalidadesHomologacionActos;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades y acciones de BeanHomologacionNuevaEntrada.
 *
 * @author Julian Vaca
 */
@SessionScoped
@ManagedBean(name = "beanHomologacionNuevaEntrada")
public class BeanHomologacionNuevaEntrada extends BeanRegistro implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -2119782597117059407L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanHomologacionNuevaEntrada.class);

	/** Constante cs_ID_GROWL. */
	private static final String cs_ID_GROWL = "fHomologacionActosLiquidacion:idGrowl";

	/** Propiedad aa acto A eliminar. */
	private Acto aa_actoAEliminar;

	/** Propiedad iah acto A homologar. */
	private AdmHomologacion iah_actoAHomologar;

	/** Propiedad ica datos acto homologados. */
	private Collection<Acto> ica_datosActoHomologados;

	/** Propiedad icah actos homologados Y agregados. */
	private Collection<AdmHomologacion> icah_actosHomologadosYAgregados;

	/** Propiedad icah adm homologacion. */
	private Collection<AdmHomologacion> icah_admHomologacion;

	/** Propiedad icah homologacion. */
	private Collection<AdmHomologacion> icah_homologacion;

	/** Propiedad lcpgui pagos. */
	private Collection<PagosUI> lcpgui_pagos;

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

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de datos acto homologados.
	 *
	 * @param aca_ca asigna el valor a la propiedad datos acto homologados
	 */
	public void setDatosActoHomologados(Collection<com.bachue.snr.prosnr01.model.sdb.acc.Acto> aca_ca)
	{
		ica_datosActoHomologados = aca_ca;
	}

	/**
	 * Retorna el valor de datos acto homologados.
	 *
	 * @return el valor de datos acto homologados
	 */
	public Collection<com.bachue.snr.prosnr01.model.sdb.acc.Acto> getDatosActoHomologados()
	{
		return ica_datosActoHomologados;
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

	/** {@inheritdoc} */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/** {@inheritdoc} */
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

	/** {@inheritdoc} */
	public void setIdTurno(String as_s)
	{
		is_idTurno = as_s;
	}

	/** {@inheritdoc} */
	public String getIdTurno()
	{
		return is_idTurno;
	}

	/** {@inheritdoc} */
	public void setIdTurnoHistoria(String as_s)
	{
		is_idTurnoHistoria = as_s;
	}

	/** {@inheritdoc} */
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
	 * Modifica el valor de Pagos.
	 *
	 * @param acpg_pui de acpg pui
	 */
	public void setPagos(Collection<PagosUI> acpg_pui)
	{
		lcpgui_pagos = acpg_pui;
	}

	/**
	 * Retorna Objeto o variable de valor pagos.
	 *
	 * @return Retorna el valor de la propiedad lcpgui_pagos
	 */
	public Collection<PagosUI> getPagos()
	{
		return lcpgui_pagos;
	}

	/**
	 * Metodo encargado de agregar el acto seleccionado en pantalla.
	 *
	 * @param aah_registro correspondiente al valor del tipo de objeto AdmHomologacion
	 */
	public void agregarActo(AdmHomologacion aah_registro)
	{
		try
		{
			if(aah_registro != null)
			{
				FuncionalidadesHomologacionActos lfha_funcionalidades;

				lfha_funcionalidades = (FuncionalidadesHomologacionActos)obtenerInstanciaBean(
					    FuncionalidadesHomologacionActos.class, BeanNameCommon.BEAN_FUNCIONALIDADES_HOMOLOGACION_ACTO
					);

				if(lfha_funcionalidades != null)
				{
					aah_registro.setSolicitud(getSolicitud());

					setActosHomologadosYAgregados(
					    lfha_funcionalidades.agregarActo(aah_registro, getIdTurno(), getActosHomologadosYAgregados())
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
	 * Metodo encargado de consultar todos los actos de una solicitud y circulo.
	 */
	public void consultarActosPorIdSolicitudCirculo()
	{
		try
		{
			BeanRegistro lbr_registro;
			FacesContext lfc_context;

			lfc_context     = FacesContext.getCurrentInstance();

			lbr_registro = (BeanRegistro)lfc_context.getApplication()
					                                    .evaluateExpressionGet(
					    lfc_context, BeanNameCommon.BEAN_REGISTRO, BeanRegistro.class
					);
			lbr_registro.iniciar();
			lbr_registro.setDatosActo(generarDatosActos(getSolicitud(), getIdCirculo()));
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
			FuncionalidadesHomologacionActos lfha_funcionalidades;

			lfha_funcionalidades = (FuncionalidadesHomologacionActos)obtenerInstanciaBean(
				    FuncionalidadesHomologacionActos.class, BeanNameCommon.BEAN_FUNCIONALIDADES_HOMOLOGACION_ACTO
				);

			if(lfha_funcionalidades != null)
				setAdmHomologacion(lfha_funcionalidades.consultarAdmHomologacion(getIdTurno()));
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
	 * Método para consultar pagos con el id turno.
	 */
	public void consultarPagos()
	{
		try
		{
			FuncionalidadesHomologacionActos lfha_funcionalidades;

			lfha_funcionalidades = (FuncionalidadesHomologacionActos)obtenerInstanciaBean(
				    FuncionalidadesHomologacionActos.class, BeanNameCommon.BEAN_FUNCIONALIDADES_HOMOLOGACION_ACTO
				);

			if(lfha_funcionalidades != null)
				setPagos(lfha_funcionalidades.consultarPagos(getIdTurno()));
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarPagos", lb2be_e);
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
	 * Encuentra todos los tipos de actos activos en la base de datos.
	 *
	 * @param adh_registro de adh registro
	 */
	public void findAllTiposActoActivos(AdmHomologacion adh_registro)
	{
		findAllTiposActoActivos(adh_registro, false);
	}

	/**
	 * Metodo encargado de buscar todos los tipos de actos activos en el sistema.
	 *
	 * @param adh_registro Argumento de tipo AdmHomologacion que contiene el registro
	 * que será homologado.
	 * @param ab_registro de ab registro
	 */
	public void findAllTiposActoActivos(AdmHomologacion adh_registro, boolean ab_registro)
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

						if(ab_registro)
						{
							lp_current.ajax().update("fRegistro:idDtHomologacion");
							lp_current.ajax().update("fTiposActos");
						}
						else
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
		setHomologacion(null);
		setIdCirculo(null);
		setIdSolicitud(null);
		setIdTurno(null);
		setIdTurnoHistoria(null);
		setObservaciones(null);
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
}
