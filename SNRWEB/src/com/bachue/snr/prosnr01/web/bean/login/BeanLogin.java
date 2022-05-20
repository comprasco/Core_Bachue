package com.bachue.snr.prosnr01.web.bean.login;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ComponentesCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.EventoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;
import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.BeanMenu;
import com.bachue.snr.prosnr01.web.util.ComponenteUI;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import java.io.IOException;
import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Clase que contiene todos las propiedades BeanLogin.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@ManagedBean(name = "beanLogin")
@SessionScoped
public class BeanLogin extends BaseBean implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanLogin.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 3925968743437497419L;

	

	/** Propiedad ic_colleccionComponente. */
	private List<ComponenteUI> ic_permisosMenuHome;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is nombre usuario. */
	private String is_nombreUsuario;

	/** Propiedad is usuario. */
	private String is_usuario;

	/** Propiedad is version actual. */
	private String is_versionActual;

	/** Propiedad iu usuario sesion. */
	private Usuario iu_usuarioSesion;

	/** Propiedad ib estado cerrar sesion. */
	private boolean ib_estadoCerrarSesion = false;

	/**
	 * Instancia un nuevo objeto bean login.
	 */
	public BeanLogin()
	{
		String ls_idUsuario;

		ls_idUsuario = FacesUtils.getRequest().getRemoteUser();
		setUsuario(ls_idUsuario);
		setUsuarioSesion(new Usuario(ls_idUsuario));
	}

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de estado cerrar sesion.
	 *
	 * @param ab_b asigna el valor a la propiedad estado cerrar sesion
	 */
	public void setEstadoCerrarSesion(boolean ab_b)
	{
		ib_estadoCerrarSesion = ab_b;
	}

	/**
	 * Valida la propiedad estado cerrar sesion.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en estado cerrar sesion
	 */
	public boolean isEstadoCerrarSesion()
	{
		return ib_estadoCerrarSesion;
	}

	/**
	 * Modifica el valor de nombre usuario.
	 *
	 * @param au_u asigna el valor a la propiedad nombre usuario
	 */
	public void setNombreUsuario(Usuario au_u)
	{
		String ls_s1;

		ls_s1 = null;

		if(au_u != null)
		{
			String ls_pNombre;
			String ls_sNombre;
			String ls_pApellido;
			String ls_sApellido;

			ls_pNombre       = au_u.getPrimerNombre();
			ls_sNombre       = au_u.getSegundoNombre();
			ls_pApellido     = au_u.getPrimerApellido();
			ls_sApellido     = au_u.getSegundoApellido();

			ls_s1 = StringUtils.isValidString(ls_pNombre) ? ls_pNombre : "";
			ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");
			ls_s1 += (StringUtils.isValidString(ls_sNombre) ? ls_sNombre : "");
			ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");
			ls_s1 += (StringUtils.isValidString(ls_pApellido) ? ls_pApellido : "");
			ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");
			ls_s1 += (StringUtils.isValidString(ls_sApellido) ? ls_sApellido : "");
			ls_s1 += (StringUtils.isValidString(ls_s1) ? " " : "");
		}

		is_nombreUsuario = ls_s1;
	}

	/**
	 * Retorna el valor de nombre usuario.
	 *
	 * @return el valor de nombre usuario
	 */
	public String getNombreUsuario()
	{
		return is_nombreUsuario;
	}

	/**
	 * Método de asignación del valor de la propiedad.
	 *
	 * @param alcui_lcui con el valor a asignar
	 */
	public void setPermisosMenuHome(List<ComponenteUI> alcui_lcui)
	{
		ic_permisosMenuHome = alcui_lcui;
	}

	/**
	 * Método de obtención del valor ded la propiedad getPermisosMenuHome.
	 *
	 * @return List con el valor solicitado
	 */
	public List<ComponenteUI> getPermisosMenuHome()
	{
		return ic_permisosMenuHome;
	}

	/**
	 * Retorna el valor de time out.
	 *
	 * @return el valor de time out
	 */
	public int getTimeOut()
	{
		int li_timeOut = FacesUtils.getSession(false).getMaxInactiveInterval() * 1000;

		return li_timeOut;
	}

	/**
	 * Retorna el valor de time out medio.
	 *
	 * @return el valor de time out medio
	 */
	public int getTimeOutMedio()
	{
		int li_timeOut = FacesUtils.getSession(false).getMaxInactiveInterval() / 2;

		return li_timeOut;
	}

	/**
	 * Modifica el valor de usuario.
	 *
	 * @param as_s asigna el valor a la propiedad usuario
	 */
	public void setUsuario(String as_s)
	{
		is_usuario = as_s;
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public String getUsuario()
	{
		return is_usuario;
	}

	/**
	 * Método de cambio de idioma validando los properties de la aplicación
	 * @throws IOException
	 * @throws B2BException
	 */
	public void cambiarIdioma()
	    throws IOException, B2BException
	{
		cambiarIdiomaBachue();
		construirMenuPrincipal(getOpciones());
		construirMenuPrincipalAyuda(getOpciones(), true);
	}

	/**
	 * Modifica el valor de usuario sesion.
	 *
	 * @param au_u asigna el valor a la propiedad usuario sesion
	 */
	public void setUsuarioSesion(Usuario au_u)
	{
		if(au_u != null)
		{
			String ls_idUsuario;

			ls_idUsuario = au_u.getIdUsuario();

			if(!StringUtils.isValidString(ls_idUsuario))
			{
				HttpSession        lhs_httpSession;
				HttpServletRequest https_request;

				lhs_httpSession     = FacesUtils.getSession(false);
				https_request       = FacesUtils.getRequest();
				ls_idUsuario        = https_request.getRemoteUser();

				lhs_httpSession.setAttribute(IdentificadoresCommon.SESION_USUARIO, ls_idUsuario);

				iu_usuarioSesion = new Usuario(ls_idUsuario);
			}
		}

		iu_usuarioSesion = au_u;
	}

	/**
	 * Retorna el valor de usuario sesion.
	 *
	 * @return el valor de usuario sesion
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public Usuario getUsuarioSesion()
	    throws IOException
	{
		if(iu_usuarioSesion == null)
			iu_usuarioSesion = new Usuario();

		return iu_usuarioSesion;
	}

	/**
	 * Modifica el valor de version actual.
	 *
	 * @param as_s asigna el valor a la propiedad version actual
	 */
	public void setVersionActual(String as_s)
	{
		is_versionActual = as_s;
	}

	/**
	 * Retorna el valor de version actual.
	 *
	 * @return el valor de version actual
	 */
	public String getVersionActual()
	{
		if(!StringUtils.isValidString(is_versionActual))
		{
			String ls_version;
			int    li_inicio;
			int    li_fin;

			li_inicio      = 0;
			li_fin         = 0;
			ls_version     = BeanLogin.class.getProtectionDomain().getCodeSource().getLocation().toString();

			if(StringUtils.isValidString(ls_version))
			{
				li_inicio = ls_version.indexOf("SNREAR_");

				if(li_inicio > 0)
					ls_version = ls_version.substring(li_inicio);

				li_fin = ls_version.indexOf("/");

				if(li_fin > 0)
					ls_version = ls_version.substring(0, li_fin);

				if(StringUtils.isValidString(ls_version))
					setVersionActual(ls_version);
			}
		}

		return is_versionActual;
	}

	/**
	 * Activar sesion.
	 */
	public void activarSesion()
	{
		new Date();
	}

	/**
	 * Cargar menu core bachue.
	 *
	 * @param acui_componente de as opcion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws IOException Objeto de tipo IOException, se produce cuando se encuentra algun error de tipo I/O.
	 */
	public void cargarMenuCoreBachue(ComponenteUI acui_componente)
	    throws B2BException, IOException
	{
		try
		{
			if(acui_componente != null)
			{
				String ls_url;

				ls_url = acui_componente.getUrl();

				if(StringUtils.isValidString(ls_url))
				{
					if(ls_url.equalsIgnoreCase(ComponentesCommon.REDIRECCION_PRINCIPAL))
					{
						Collection<Opcion> lco_opciones;

						lco_opciones = irr_referenceRemote.cargarOpcionesMenu(
							    getUsuario(), acui_componente.getIdComponente(), getLocalIpAddress(),
							    getRemoteIpAddress()
							);

						setOpciones(lco_opciones);
						construirMenuPrincipal(lco_opciones);
						construirMenuPrincipalAyuda(lco_opciones, true);
					}

					FacesContext.getCurrentInstance().getExternalContext().redirect(ls_url);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SIN_URL_COMPONENTE);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_URL_COMPONENTE);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("idGrowl");
		}
	}

	/**
	 * Método de sobrecarga del método de cerrar sesión que retorna el valor del objeto de String con la vista.
	 *
	 * @return devuelve el valor de String
	 */
	public String cerrarSesion()
	{
		return cerrarSesion(false);
	}

	/**
	 * Retorna el valor del objeto de String con la vista.
	 *
	 * @param ab_home de ab home
	 * @return devuelve el valor de String
	 */
	public String cerrarSesion(boolean ab_home)
	{
		HttpServletRequest lhsr_request;
		String             ls_return;

		lhsr_request     = FacesUtils.getRequest();
		ls_return        = null;

		try
		{
			if(lhsr_request != null)
			{
				HttpSession lhs_httpSession;

				lhs_httpSession = lhsr_request.getSession(false);

				lhs_httpSession.invalidate();
				lhsr_request.logout();

				setUsuario(null);
				setUsuarioSesion(null);
				setNombreUsuario(null);
				setEstadoCerrarSesion(false);

				weblogic.servlet.security.ServletAuthentication.logout(lhsr_request);

				{
					String ls_constante;

					ls_constante = irr_referenceRemote.obtenerCaracterConstante(ConstanteCommon.URL_CERRAR_SESION);

					if(StringUtils.isValidString(ls_constante))
						FacesContext.getCurrentInstance().getExternalContext().redirect(ls_constante);
				}
			}
		}
		catch(Exception ex)
		{
			Logger.getLogger(BeanLogin.class.getName()).log(Level.SEVERE, null, ex);

			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public String cerrarSesionExpirada()
	    throws IOException
	{
		HttpServletRequest lhsr_request;
		String             ls_return;

		lhsr_request     = FacesUtils.getRequest();
		ls_return        = null;

		try
		{
			if(lhsr_request != null)
			{
				HttpSession lhs_httpSession;

				lhs_httpSession = lhsr_request.getSession(false);

				lhs_httpSession.invalidate();
				lhsr_request.logout();

				setUsuario(null);
				setUsuarioSesion(null);
				setNombreUsuario(null);
				setEstadoCerrarSesion(false);

				weblogic.servlet.security.ServletAuthentication.logout(lhsr_request);

				{
					String ls_constante;

					ls_constante = irr_referenceRemote.obtenerCaracterConstante(ConstanteCommon.URL_CERRAR_SESION);

					if(StringUtils.isValidString(ls_constante))
						FacesContext.getCurrentInstance().getExternalContext().redirect(ls_constante);
				}
			}
		}
		catch(Exception ex)
		{
			Logger.getLogger(BeanLogin.class.getName()).log(Level.SEVERE, null, ex);

			ls_return = null;
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de String con la vista del login.
	 *
	 * @return devuelve el valor de String
	 * @throws IOException
	 */
	private String login()
	    throws IOException
	{
		String ls_s;

		ls_s = null;

		setIdiomaEspanol(!FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(Locale.ENGLISH));

		try
		{
			Usuario lu_usuario;
			String  ls_idUsuario;

			ls_idUsuario = getUsuario();

			validarIdioma();

			if(StringUtils.isValidString(ls_idUsuario))
			{
				lu_usuario = irr_referenceRemote.findUserById(new Usuario(ls_idUsuario));

				if(lu_usuario != null)
				{
					if(BooleanUtils.getBooleanValue(lu_usuario.getActivo()))
					{
						{
							List<Componente> lco_componente;

							lco_componente = irr_referenceRemote.cargarComponentesMenu(
								    ls_idUsuario, getLocalIpAddress(), getRemoteIpAddress()
								);

							if(lco_componente != null)
							{
								List<ComponenteUI> lcoui_componente;

								lcoui_componente = new LinkedList<ComponenteUI>();

								//FORMATO
								lco_componente.forEach(lc_componente -> lcoui_componente.add(new ComponenteUI(lc_componente)));
								setPermisosMenuHome(lcoui_componente);
							}
							else
								throw new B2BException(ErrorKeys.ERROR_USUARIO_SIN_OPCIONES);
						}

						HttpSession https_sesion = FacesUtils.getSession();

						setNombreUsuario(lu_usuario);
						setUsuarioSesion(lu_usuario);
						setEstadoCerrarSesion(true);
						https_sesion.setAttribute(IdentificadoresCommon.ENTREGAR_RECURSO, "");
						https_sesion.setAttribute(IdentificadoresCommon.SESION_USUARIO, ls_idUsuario);
						https_sesion.setAttribute(IdentificadoresCommon.SESION_NOMBRE_USUARIO, getNombreUsuario());
						https_sesion.setAttribute(IdentificadoresCommon.SESION_VERSION_ACTUAL, getVersionActual());

						ls_s = NavegacionCommon.SEGUNDO_FACTOR_PAGE;
					}
					else
						FacesContext.getCurrentInstance()
							            .addMessage(
							    null,
							    new FacesMessage(
							        FacesMessage.SEVERITY_ERROR, "ERROR: ",
							        "El usuario " + lu_usuario.getPrimerNombre() + " " + lu_usuario.getPrimerApellido()
							        + " esta INACTIVO en Bachué"
							    )
							);
				}
				else
					FacesContext.getCurrentInstance()
						            .addMessage(
						    null,
						    new FacesMessage(
							        FacesMessage.SEVERITY_ERROR, "ERROR: ", getMessages().getString(MessagesKeys.MENSAJE_ERROR_LOGIN)
							    )
						);
			}
			else
				FacesContext.getCurrentInstance()
					            .addMessage(
					    null,
					    new FacesMessage(
					    		FacesMessage.SEVERITY_ERROR, "ERROR: ", getMessages().getString(MessagesKeys.MENSAJE_ERROR_LOGIN)
					    )
					);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("login", lb2be_e);
		}
		catch(Exception le_e)
		{
			FacesContext.getCurrentInstance()
				            .addMessage(
				    null,
				    new FacesMessage(
				        FacesMessage.SEVERITY_FATAL, "Error fatal: ", "Por favor contacte con su administrador " + le_e
				    )
				);
		}

		return ls_s;
	}

	/**
	 * Retorna el valor del objeto de String con la vista del login.
	 *
	 */
	@Override
	public void segundaVerificacion()
	{
		HttpSession https_sesion;

		https_sesion = FacesUtils.getSession(false);

		try
		{
			Usuario lu_usuarioLogueado;

			lu_usuarioLogueado = getUsuarioSesion();

			if((lu_usuarioLogueado != null) && (https_sesion != null))
			{
				StringBuilder lsb_builder;
				String        ls_idSesion;

				ls_idSesion     = https_sesion.getId().replace(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS, "");

				lsb_builder = new StringBuilder(IdentificadoresCommon.PROTOCOLO_BACHUE);
				lsb_builder.append(EventoCommon.VERIFY);
				lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
				lsb_builder.append(ls_idSesion);
				lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
				lsb_builder.append(lu_usuarioLogueado.getIdUsuario());
				lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
				lsb_builder.append(
				    StringUtils.getStringNotNull(lu_usuarioLogueado.getSegundoFactorAutenticacion())
					               .equalsIgnoreCase("HUELLA") ? "1" : "0"
				);

				https_sesion.setAttribute(IdentificadoresCommon.ID_SESION, ls_idSesion);

				PrimeFaces.current().executeScript("abrirURLBioClient('" + lsb_builder.toString() + "');");
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error(le_e);
		}
	}

	/**
	 * Metodo encargado de redireccionar la aplicacion al recurso solicitado
	 * si el segundo factor de autenticacion fue verificado exitosamente.
	 */
	public void verify()
	{
		try
		{
			HttpSession https_sesion;
			String      ls_entregaRecurso;

			https_sesion          = FacesUtils.getSession(false);
			ls_entregaRecurso     = (String)https_sesion.getAttribute(IdentificadoresCommon.ENTREGAR_RECURSO);
			login();

			{
				Boolean lb_supero;

				lb_supero = BooleanUtils.getBoolean(
					    (String)https_sesion.getAttribute(IdentificadoresCommon.SUPERO_SEGUNDO_FACTOR)
					);

				if(lb_supero != null)
				{
					if(!lb_supero.booleanValue())
					{
						PrimeFaces.current()
							          .executeScript(
							    "PF('idGrowl').renderMessage({ summary: 'Advertencia', detail: 'Ocurrió un error al verificar la sesión, intente nuevamente o contacte con soporte', severity: 'warn'});"
							);
						throw new B2BException(
						    "Ocurrio un error al verificar la sesión, intente nuevamente o contacte con soporte"
						);
					}
				}
			}

			if(StringUtils.isValidString(ls_entregaRecurso))
			{
				https_sesion.setAttribute(IdentificadoresCommon.ENTREGAR_RECURSO, null);
				FacesContext.getCurrentInstance().getExternalContext().redirect(ls_entregaRecurso);
			}
			else
				FacesContext.getCurrentInstance().getExternalContext().redirect(NavegacionCommon.HOME);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			clh_LOGGER.error("verify", lb2be_e);
		}
		catch(Exception le_e)
		{
			addMessage(new B2BException("Exception", le_e));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			clh_LOGGER.error("verify", le_e);
		}
		finally
		{
			PrimeFaces.current().ajax().update("fSegundoFactor:idGrowl");
		}
	}

	/**
	 * Construye el menú principal a partir de las opciones que tenga habilitadas el usuario que inicia sesión.
	 *
	 * @param aco_opciones Colección de opciones disponibles para el usuario
	 * @throws B2BException Si ocurre un error en la construcción del menú
	 */
	private void construirMenuPrincipal(Collection<Opcion> aco_opciones)
	    throws B2BException
	{
		construirMenuPrincipal(aco_opciones, false);
	}

	/**
	 * Construye el menú de ayuda a partir de las opciones que tenga habilitadas el usuario que inicia sesión.
	 *
	 * @param aco_opciones Colección de opciones disponibles para el usuario
	 * @throws B2BException Si ocurre un error en la construcción del menú
	 */
	private void construirMenuPrincipalAyuda(Collection<Opcion> aco_opciones, boolean ab_menuAyuda)
	    throws B2BException
	{
		MenuModel          lmm_menu;
		Collection<Opcion> lco_opcionesMenuAyuda;

		lmm_menu                  = new DefaultMenuModel();
		lco_opcionesMenuAyuda     = new LinkedList<Opcion>();

		aco_opciones.stream()
		.filter(lo_opcionTmp -> lo_opcionTmp != null && StringUtils.isValidString(lo_opcionTmp.getOpcionFrecuente()) && lo_opcionTmp.getOpcionFrecuente().equals("S"))
		.forEach(lo_opcion -> lco_opcionesMenuAyuda.add(lo_opcion));
		if(CollectionUtils.isValidCollection(lco_opcionesMenuAyuda))
		{
			DefaultSubMenu ldsm_subMenu;

			ldsm_subMenu = new DefaultSubMenu();

			ldsm_subMenu.setLabel(getMessages().getString("commonLabelTareasFrecuentes"));

			ldsm_subMenu.setExpanded(true);
			ldsm_subMenu.setStyleClass("estiloSubMenu");

			for(Opcion lo_opcion : lco_opcionesMenuAyuda)
				ldsm_subMenu.addElement(
				    llenarPantalla(
				        lo_opcion.getUrl(), lo_opcion.getOpcion(), lo_opcion.getIdOpcion(), lo_opcion.getVentanaNueva()
				    )
				);

			lmm_menu.addElement(ldsm_subMenu);
		}

		BeanMenu lbm_beanMenu;

		lbm_beanMenu = (BeanMenu)obtenerInstanciaBean(BeanMenu.class, BeanNameCommon.BEAN_MENU);

		lbm_beanMenu.setMenuAyuda(lmm_menu);
	}

	/**
	 * Construye el menú principal a partir de las opciones que tenga habilitadas el usuario que inicia sesión.
	 *
	 * @param aco_opciones Colección de opciones disponibles para el usuario
	 * @throws B2BException Si ocurre un error en la construcción del menú
	 */
	private void construirMenuPrincipal(Collection<Opcion> aco_opciones, boolean ab_menuAyuda)
	    throws B2BException
	{
		if(!CollectionUtils.isValidCollection(aco_opciones))
			throw new B2BException(ErrorKeys.ERROR_USUARIO_SIN_OPCIONES);

		MenuModel          lmm_menu;
		Collection<Opcion> lco_opcionesPrincipales;

		lmm_menu                    = new DefaultMenuModel();
		lco_opcionesPrincipales     = new LinkedList<Opcion>();

		if(!ab_menuAyuda)
			lmm_menu.addElement(generarSubMenuPredeterminado("commonOpcionInicio", NavegacionCommon.PRINCIPAL));

		//FORMATO comentariar para  formatear
		aco_opciones.stream()
			.filter(lo_opcionTmp -> lo_opcionTmp != null && !StringUtils.isValidString(lo_opcionTmp.getIdOpcionPadre()))
			.forEach(lo_opcion -> lco_opcionesPrincipales.add(lo_opcion));
		if(CollectionUtils.isValidCollection(lco_opcionesPrincipales))
		{
			for(Opcion lo_opcionPrincipal : lco_opcionesPrincipales)
			{
				if(lo_opcionPrincipal != null)
				{
					String ls_tipo;

					ls_tipo = StringUtils.getStringNotNull(lo_opcionPrincipal.getTipo());

					if(!ab_menuAyuda)
					{
						if(ls_tipo.equals(IdentificadoresCommon.MENU))
							lmm_menu.addElement(
							    llenarSubMenu(
							        lo_opcionPrincipal.getOpcion(), lo_opcionPrincipal.getIdOpcion(), aco_opciones, true
							    )
							);
						else if(ls_tipo.equals(IdentificadoresCommon.PANTALLA))
							lmm_menu.addElement(
							    llenarPantalla(
							        lo_opcionPrincipal.getUrl(), lo_opcionPrincipal.getOpcion(),
							        lo_opcionPrincipal.getIdOpcion(), lo_opcionPrincipal.getVentanaNueva()
							    )
							);
					}
					else
					{
						String ls_opcionFrecuente;

						ls_opcionFrecuente = StringUtils.getStringNotNull(lo_opcionPrincipal.getOpcionFrecuente());

						if(
						    ls_tipo.equals(IdentificadoresCommon.MENU)
							    && ls_opcionFrecuente.equals(IdentificadoresCommon.S)
						)
							lmm_menu.addElement(
							    llenarSubMenu(
							        lo_opcionPrincipal.getOpcion(), lo_opcionPrincipal.getIdOpcion(), aco_opciones, true,
							        ab_menuAyuda
							    )
							);
						else if(
						    ls_tipo.equals(IdentificadoresCommon.PANTALLA)
							    && ls_opcionFrecuente.equals(IdentificadoresCommon.S)
						)
							lmm_menu.addElement(
							    llenarPantalla(
							        lo_opcionPrincipal.getUrl(), lo_opcionPrincipal.getOpcion(),
							        lo_opcionPrincipal.getIdOpcion(), lo_opcionPrincipal.getVentanaNueva()
							    )
							);
					}
				}
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_SIN_OPCIONES_PRINCIPALES_MENU);

		if(!ab_menuAyuda)
			lmm_menu.addElement(generarSubMenuPredeterminado("commonOptionCerrarSesion", "#{beanLogin.cerrarSesion}"));

		BeanMenu lbm_beanMenu;

		lbm_beanMenu = (BeanMenu)obtenerInstanciaBean(BeanMenu.class, BeanNameCommon.BEAN_MENU);

		if(ab_menuAyuda)
			lbm_beanMenu.setMenuAyuda(lmm_menu);
		else
			lbm_beanMenu.setMenu(lmm_menu);
	}

	/**
	 * Construye un sub menú con sus opciones de pantalla.
	 *
	 * @param adsm_subMenuPadre Objeto contenedor de la inormación del sub menú padre
	 * @param aco_opciones Coleccion de opciones habilitadas para el usuario
	 * @param as_idOpcionPadre Id opción del sub menú padre
	 */
	private void construirSubMenu(
	    DefaultSubMenu adsm_subMenuPadre, Collection<Opcion> aco_opciones, String as_idOpcionPadre, boolean ab_menuAyuda
	)
	{
		if(
		    CollectionUtils.isValidCollection(aco_opciones) && StringUtils.isValidString(as_idOpcionPadre)
			    && (adsm_subMenuPadre != null)
		)
		{
			Collection<Opcion> lco_opcionesHijo;

			lco_opcionesHijo = new LinkedList<Opcion>();

			//FORMATO comentariar para  formatear
			aco_opciones.stream()
				.filter(lo_opcionTmp -> lo_opcionTmp != null && StringUtils.isValidString(lo_opcionTmp.getIdOpcionPadre()) 
						&& lo_opcionTmp.getIdOpcionPadre().equals(as_idOpcionPadre))
				.forEach(lo_opcion -> lco_opcionesHijo.add(lo_opcion));
			if(CollectionUtils.isValidCollection(lco_opcionesHijo))
			{
				for(Opcion lo_opcion : lco_opcionesHijo)
				{
					if(lo_opcion != null)
					{
						String ls_tipo;

						ls_tipo = StringUtils.getStringNotNull(lo_opcion.getTipo());

						if(!ab_menuAyuda)
						{
							if(ls_tipo.equals(IdentificadoresCommon.MENU))
								adsm_subMenuPadre.addElement(
								    llenarSubMenu(lo_opcion.getOpcion(), lo_opcion.getIdOpcion(), aco_opciones, false)
								);
							else if(ls_tipo.equals(IdentificadoresCommon.PANTALLA))
								adsm_subMenuPadre.addElement(
								    llenarPantalla(
								        lo_opcion.getUrl(), lo_opcion.getOpcion(), lo_opcion.getIdOpcion(),
								        lo_opcion.getVentanaNueva()
								    )
								);
						}
						else
						{
							String ls_opcionFrecuente;

							ls_opcionFrecuente = StringUtils.getStringNotNull(lo_opcion.getOpcionFrecuente());

							if(
							    ls_tipo.equals(IdentificadoresCommon.MENU)
								    && ls_opcionFrecuente.equals(IdentificadoresCommon.S)
							)
								adsm_subMenuPadre.addElement(
								    llenarSubMenu(
								        lo_opcion.getOpcion(), lo_opcion.getIdOpcion(), aco_opciones, false,
								        ab_menuAyuda
								    )
								);
							else if(
							    ls_tipo.equals(IdentificadoresCommon.PANTALLA)
								    && ls_opcionFrecuente.equals(IdentificadoresCommon.S)
							)
								adsm_subMenuPadre.addElement(
								    llenarPantalla(
								        lo_opcion.getUrl(), lo_opcion.getOpcion(), lo_opcion.getIdOpcion(),
								        lo_opcion.getVentanaNueva()
								    )
								);
						}
					}
				}
			}
		}
	}

	/**
	 * Construye un sub menú con una opción de igual nombre.
	 *
	 * @param as_nombreOpcion Nombre a dar al sub menú y a la opción
	 * @param as_url Comando o dirección a ejecutar al interactuar con la opción
	 * @return el valor de default sub menu
	 */
	private DefaultSubMenu generarSubMenuPredeterminado(String as_nombreOpcion, String as_url)
	{
		DefaultSubMenu  ldsm_subMenu;
		DefaultMenuItem ldmi_item;

		ldsm_subMenu     = llenarSubMenu(as_nombreOpcion, IdentificadoresCommon.NO_APLICA, null, true);
		ldmi_item        = llenarPantalla(as_url, as_nombreOpcion, IdentificadoresCommon.NO_APLICA, null);

		ldsm_subMenu.addElement(ldmi_item);

		return ldsm_subMenu;
	}

	/**
	 * Ingresa la información necesaria a un item del menú para funcionar correctamente.
	 *
	 * @param as_url Url a la cual va a redirigir el item del menú
	 * @param as_opcion Nombre del item
	 * @param as_idOpcion Id único del item
	 * @param as_ventanaNueva de as ventana nueva
	 * @return Item del menú con la información cargada
	 */
	private DefaultMenuItem llenarPantalla(String as_url, String as_opcion, String as_idOpcion, String as_ventanaNueva)
	{
		DefaultMenuItem ldmi_pantalla;

		ldmi_pantalla = new DefaultMenuItem();

		if(
		    StringUtils.isValidString(as_url) && StringUtils.isValidString(as_opcion)
			    && StringUtils.isValidString(as_idOpcion)
		)
		{
			ldmi_pantalla.setValue(getMessagesOpciones().getString(as_opcion));
			ldmi_pantalla.setId(as_idOpcion);
			ldmi_pantalla.setAjax(false);

			if(StringUtils.isValidString(as_ventanaNueva) && as_ventanaNueva.equals(EstadoCommon.S))
			{
				StringBuilder lsb_command;

				lsb_command = new StringBuilder("window.open('");

				lsb_command.append(as_url);
				lsb_command.append("')");

				ldmi_pantalla.setOnclick(lsb_command.toString());
				ldmi_pantalla.setCommand("#{beanMenu.pages('')}");
			}
			else
			{
				if(as_opcion.equals("commonOptionCerrarSesion"))
				{
					ldmi_pantalla.setCommand(as_url);
					ldmi_pantalla.setAjax(true);
				}
				else
				{
					StringBuilder lsb_command;

					lsb_command = new StringBuilder("#{beanMenu.pages('");

					lsb_command.append(as_url);
					lsb_command.append("')}");

					ldmi_pantalla.setCommand(lsb_command.toString());
				}
			}

			ldmi_pantalla.setParam("opcionSeleccionada", as_opcion);
		}

		return ldmi_pantalla;
	}

	/**
	 * Ingresa la información necesaria a un sub menú para funcionar correctamente.
	 *
	 * @param as_opcion Nombre del sub menú
	 * @param as_idOpcion Id único del sub menú
	 * @param aco_opciones Opciones del menú disponibles para el usuario
	 * @param ab_menuPrincipal true para indicar si es un menú principal, false para sub menú
	 * @return Sub menú con la información cargada
	 */
	private DefaultSubMenu llenarSubMenu(
	    String as_opcion, String as_idOpcion, Collection<Opcion> aco_opciones, boolean ab_menuPrincipal
	)
	{
		return llenarSubMenu(as_opcion, as_idOpcion, aco_opciones, ab_menuPrincipal, false);
	}

	/**
	 * Ingresa la información necesaria a un sub menú para funcionar correctamente.
	 *
	 * @param as_opcion Nombre del sub menú
	 * @param as_idOpcion Id único del sub menú
	 * @param aco_opciones Opciones del menú disponibles para el usuario
	 * @param ab_menuPrincipal true para indicar si es un menú principal, false para sub menú
	 * @return Sub menú con la información cargada
	 */
	private DefaultSubMenu llenarSubMenu(
	    String as_opcion, String as_idOpcion, Collection<Opcion> aco_opciones, boolean ab_menuPrincipal,
	    boolean ab_menuAyuda
	)
	{
		DefaultSubMenu ldsm_subMenu;

		ldsm_subMenu = new DefaultSubMenu();

		if(StringUtils.isValidString(as_opcion) && StringUtils.isValidString(as_idOpcion))
		{
			ldsm_subMenu.setLabel(getMessagesOpciones().getString(as_opcion));

			if(!ab_menuAyuda)
				ldsm_subMenu.setStyleClass(ab_menuPrincipal ? "estiloMenuPrincipal" : "estiloSubMenu");
			else
				ldsm_subMenu.setStyleClass(ab_menuPrincipal ? "estiloMenuAyuda" : "estiloSubMenuAyuda");

			construirSubMenu(ldsm_subMenu, aco_opciones, as_idOpcion, ab_menuAyuda);
		}

		return ldsm_subMenu;
	}

	
}
