package com.bachue.snr.prosnr01.web.bean.parameter;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EventoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.parameter.ParameterRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;

import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;

import com.bachue.snr.prosnr01.web.bean.dispositivos.BeanDispositivos;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import javax.servlet.http.HttpSession;


/**
 * Clase que contiene todos las propiedades y funcionalidad para el Bean de usuarios
 *
 * @author Manuel Blanco
 */
@ManagedBean(name = "beanUsuarios")
@SessionScoped
public class BeanUsuarios extends BeanDispositivos implements Serializable
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanUsuarios.class);

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 770379664585602290L;

	/** Propiedad icu usuarios coleccion. */
	private Collection<Usuario> icu_usuariosColeccion;

	/** Propiedad ipr parameter remote. */
	@EJB
	private ParameterRemote ipr_parameterRemote;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id usuario actualizar. */
	private String is_idUsuarioActualizar;

	/** Propiedad iu usuario. */
	private Usuario iu_usuario;

	/** Propiedad ib doc nit. */
	private boolean ib_docNit;

	/** Propiedad ib es enrolar. */
	private boolean ib_esEnrolar;

	/** Propiedad ib es reinicio clave. */
	private boolean ib_esReinicioClave;

	/** Propiedad ib es reinicio huella. */
	private boolean ib_esReinicioHuella;

	/** Propiedad ib es segundo factor. */
	private boolean ib_esSegundoFactor;

	/** Propiedad ib insertar actualizar. */
	private boolean ib_insertarActualizar;

	/** Propiedad ib no mostrar panel detalle. */
	private boolean ib_noMostrarPanelDetalle;

	/** Propiedad ib rendered. */
	private boolean ib_rendered;

	/** Propiedad ib validacion documento. */
	private boolean ib_validacionDocumento;

	/** Propiedad ii checker. */
	private int ii_checker;

	/** {@inheritdoc} */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Modifica el valor de checker.
	 *
	 * @param ai_checker asigna el valor a la propiedad checker
	 */
	public void setChecker(int ai_checker)
	{
		if(ai_checker == 1)
			setInsertUpdate(true);
		else if(ai_checker == -1)
			setInsertUpdate(false);
	}

	/**
	 * Retorna el valor de checker.
	 *
	 * @return el valor de checker
	 */
	public int getChecker()
	{
		return ii_checker;
	}

	/**
	 * Modifica el valor de doc nit.
	 *
	 * @param ab_b asigna el valor a la propiedad doc nit
	 */
	public void setDocNit(boolean ab_b)
	{
		ib_docNit = ab_b;
	}

	/**
	 * Valida la propiedad doc nit.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en doc nit
	 */
	public boolean isDocNit()
	{
		return ib_docNit;
	}

	/**
	 * @param Modifica el valor de la propiedad esEnrolar por ab_b
	 */
	public void setEsEnrolar(boolean ab_b)
	{
		ib_esEnrolar = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esEnrolar
	 */
	public boolean isEsEnrolar()
	{
		return ib_esEnrolar;
	}

	/**
	 * @param Modifica el valor de la propiedad esReinicioClave por ab_b
	 */
	public void setEsReinicioClave(boolean ab_b)
	{
		ib_esReinicioClave = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esReinicioClave
	 */
	public boolean isEsReinicioClave()
	{
		return ib_esReinicioClave;
	}

	/**
	 * @param Modifica el valor de la propiedad esReinicioHuella por ab_b
	 */
	public void setEsReinicioHuella(boolean ab_b)
	{
		ib_esReinicioHuella = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esReinicioHuella
	 */
	public boolean isEsReinicioHuella()
	{
		return ib_esReinicioHuella;
	}

	/**
	 * @param Modifica el valor de la propiedad esSegundoFactor por ab_b
	 */
	public void setEsSegundoFactor(boolean ab_b)
	{
		ib_esSegundoFactor = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad esSegundoFactor
	 */
	public boolean isEsSegundoFactor()
	{
		return ib_esSegundoFactor;
	}

	/**
	 * Modifica el valor de id usuario actualizar.
	 *
	 * @param as_idUsuarioActualizar asigna el valor a la propiedad id usuario actualizar
	 */
	public void setIdUsuarioActualizar(String as_idUsuarioActualizar)
	{
		is_idUsuarioActualizar = as_idUsuarioActualizar;
	}

	/**
	 * Retorna el valor de id usuario actualizar.
	 *
	 * @return el valor de id usuario actualizar
	 */
	public String getIdUsuarioActualizar()
	{
		return is_idUsuarioActualizar;
	}

	/**
	 * Modifica el valor de insert update.
	 *
	 * @param insertUpdate asigna el valor a la propiedad insert update
	 */
	public void setInsertUpdate(boolean insertUpdate)
	{
		this.ib_insertarActualizar = insertUpdate;
	}

	/**
	 * Valida la propiedad insert update.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en insert update
	 */
	public boolean isInsertUpdate()
	{
		return ib_insertarActualizar;
	}

	/**
	 * @param Modifica el valor de la propiedad noMostrarPanelDetalle por ab_b
	 */
	public void setNoMostrarPanelDetalle(boolean ab_b)
	{
		ib_noMostrarPanelDetalle = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad noMostrarPanelDetalle
	 */
	public boolean isNoMostrarPanelDetalle()
	{
		if(isEsSegundoFactor() || isEsEnrolar() || isEsReinicioClave() || isEsReinicioHuella())
			ib_noMostrarPanelDetalle = false;
		else
			ib_noMostrarPanelDetalle = true;

		return ib_noMostrarPanelDetalle;
	}

	/**
	 * Modifica el valor de rendered.
	 *
	 * @param ab_rendered asigna el valor a la propiedad rendered
	 */
	public void setRendered(boolean ab_rendered)
	{
		ib_rendered = ab_rendered;
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
	 * Modifica el valor de usuario.
	 *
	 * @param au_usuario asigna el valor a la propiedad usuario
	 */
	public void setUsuario(Usuario au_usuario)
	{
		if(au_usuario != null)
			iu_usuario = au_usuario;
		else
			iu_usuario = new Usuario();
	}

	/**
	 * Retorna el valor de usuario.
	 *
	 * @return el valor de usuario
	 */
	public Usuario getUsuario()
	{
		return iu_usuario;
	}

	/**
	 * Modifica el valor de usuarios coleccion.
	 *
	 * @param acu_cu asigna el valor a la propiedad usuarios coleccion
	 */
	public void setUsuariosColeccion(Collection<Usuario> acu_cu)
	{
		if(CollectionUtils.isValidCollection(acu_cu))
			icu_usuariosColeccion = acu_cu;
		else
			icu_usuariosColeccion = new ArrayList<Usuario>();
	}

	/**
	 * Retorna el valor de usuarios coleccion.
	 *
	 * @return el valor de usuarios coleccion
	 */
	public Collection<Usuario> getUsuariosColeccion()
	{
		return icu_usuariosColeccion;
	}

	/**
	 * Modifica el valor de validacion documento.
	 *
	 * @param ab_b asigna el valor a la propiedad validacion documento
	 */
	public void setValidacionDocumento(boolean ab_b)
	{
		ib_validacionDocumento = ab_b;
	}

	/**
	 * Valida la propiedad validacion documento.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en validacion documento
	 */
	public boolean isValidacionDocumento()
	{
		return ib_validacionDocumento;
	}

	/**
	 * Controla la accion del boton actualizar, cargando en pantalla los datos
	 * del usuario seleccionado.
	 *
	 * @param as_userId es el id del usuario seleccionado en pantalla
	 * @return String que contiene la direccion de la pagina de actualizar usuarios
	 *
	 */
	public String botonActualizar(String as_userId)
	{
		setChecker(1);

		Usuario lcu_user;

		lcu_user = new Usuario();

		lcu_user.setIdUsuario(as_userId);

		setUsuario(lcu_user);

		consultarUsuarios();

		setUsuario(getUsuariosColeccion().iterator().next());

		setIdUsuarioActualizar(getUsuario().getIdUsuario());

		addMessage(MessagesKeys.EDITAR_CAMPOS);
		PrimeFaces.current().ajax().update("fUsuariosInsertarActualizar:gUserInsertUpdate");

		setIdUsuarioActualizar(null);

		return NavegacionCommon.USUARIOS_DETALLE;
	}

	/**
	 * Controla la accion del boton segundo factor.
	 * Cambia el valor de la variable booleana segundoFactor a true.
	 */
	public void botonSegundoFactor()
	{
		setEsSegundoFactor(true);
	}

	/**
	 * Limpia todas las variables de instancia que puedan contener informacion
	 * de algun proceso realizado.
	 *
	 * @return String que contiene la direccion de la pagina principal de la pantalla
	 * de usuarios
	 *
	 */
	public String clean()
	{
		setUsuario(null);
		setUsuariosColeccion(null);
		setIdUsuarioActualizar(null);
		setRendered(false);
		setInsertUpdate(false);
		setChecker(0);
		setEsSegundoFactor(false);
		setEsReinicioClave(false);
		setEsReinicioHuella(false);
		setEsEnrolar(false);

		return NavegacionCommon.USUARIOS;
	}

	/**
	 * Consulta la informacion completa del usuario que se seleccionó en pantalla
	 * y la almacena en la coleccion icu_usuariosColeccion.
	 */
	public void consultarUsuarios()
	{
		setUsuariosColeccion(null);

		Collection<Usuario> lcu_usuario;

		lcu_usuario = new ArrayList<Usuario>();

		try
		{
			Usuario lu_usuario;
			lu_usuario = getUsuario();

			if(lu_usuario != null)
				lcu_usuario = irr_referenceRemote.findAllUsers(lu_usuario.getIdUsuario());
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("bean_Usuarios:globalMsg");
		}

		setUsuariosColeccion(lcu_usuario);

		ib_rendered = true;

		PrimeFaces.current().ajax().update("bean_Usuarios");
	}

	/**
	 * Método que instancia algunos objetos de la clase.
	 */
	public void iniciar()
	{
		iu_usuario                 = new Usuario();
		icu_usuariosColeccion      = new ArrayList<Usuario>();
		is_idUsuarioActualizar     = "";
		ib_rendered                = false;
		ib_insertarActualizar      = false;
		ii_checker                 = 0;
		ib_validacionDocumento     = true;
	}

	/**
	 * Valida la información ingresada en pantalla y posteriormente, dependiendo
	 * del proceso seleccionado, inserta o actualiza los datos del usuario en la
	 * base de datos.
	 *
	 * @param ab_proceso es el proceso seleccionado, true para insertar, false
	 * para actualizar
	 * @return Si el proceso se completo sin ningun error, retorna un String que
	 * contiene la direccion de la pagina principal de usuarios, sino retorna null
	 *
	 */
	public String insertarUsuarios(boolean ab_proceso)
	{
		String  ls_result;
		boolean lb_focus;

		ls_result     = null;
		lb_focus      = true;

		try
		{
			Usuario      lu_usuario;
			FacesContext lfc_context;

			lu_usuario      = getUsuario();
			lfc_context     = FacesContext.getCurrentInstance();

			if(lu_usuario == null)
				throw new B2BException(ErrorKeys.USUARIO_INVALIDO);

			{
				String ls_idUsuario;
				ls_idUsuario     = lu_usuario.getIdUsuario();

				lb_focus = validateStyles(":fUsuariosInsertarActualizar:user", lfc_context, ls_idUsuario, lb_focus);

				if(!StringUtils.isValidString(ls_idUsuario))
					throw new B2BException(ErrorKeys.ERROR_SIN_USUARIO);
			}

			{
				String ls_numDoc;
				ls_numDoc     = lu_usuario.getNumeroDocumento();

				lb_focus = validateStyles(":fUsuariosInsertarActualizar:cedula", lfc_context, ls_numDoc, lb_focus);

				if(!StringUtils.isValidString(ls_numDoc))
					throw new B2BException(ErrorKeys.ERROR_SIN_CEDULA);
				else
				{
					if(ls_numDoc.equals("0") || ls_numDoc.startsWith("-"))
						throw new B2BException(ErrorKeys.ERROR_SIN_CEDULA);

					String ls_tipoDocumento;
					ls_tipoDocumento = lu_usuario.getIdDocumentoTipo();

					if(!StringUtils.isValidString(ls_tipoDocumento))
						throw new B2BException(ErrorKeys.TIPO_DOCUMENTO_INVALIDO);

					if(!isValidacionDocumento())
						throw new B2BException(ErrorKeys.ERROR_DOCUMENTO_REPETIDO);
				}
			}

			if(!isDocNit())
			{
				ExpresionRegular ler_validador;
				String           ls_pNombre;
				String           ls_sNombre;
				String           ls_pApellido;
				String           ls_sApellido;

				ler_validador     = ExpresionRegular.getExpresionRegular();
				ls_pNombre        = lu_usuario.getPrimerNombre();
				ls_sNombre        = lu_usuario.getSegundoNombre();
				ls_pApellido      = lu_usuario.getPrimerApellido();
				ls_sApellido      = lu_usuario.getSegundoApellido();

				lb_focus = validateStyles("fUsuariosInsertarActualizar:pNombre", lfc_context, ls_pNombre, lb_focus);

				if(!StringUtils.isValidString(ls_pNombre))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);

				if(!ler_validador.esSoloLetras(ls_pNombre))
					throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_PNOMBRE);

				if(StringUtils.isValidString(ls_sNombre) && !ler_validador.esSoloLetras(ls_sNombre))
					throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_SNOMBRE);

				lb_focus = validateStyles("fUsuariosInsertarActualizar:pApellido", lfc_context, ls_pApellido, lb_focus);

				if(!StringUtils.isValidString(ls_pApellido))
					throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);

				if(!ler_validador.esSoloLetras(ls_pApellido))
					throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_PAPELLIDO);

				if(StringUtils.isValidString(ls_sApellido) && !ler_validador.esSoloLetras(ls_sApellido))
					throw new B2BException(ErrorKeys.ERROR_CARACTERES_ESPECIALES_SAPELLIDO);
			}

			String ls_correo;
			ls_correo     = lu_usuario.getCorreoElectronico();

			lb_focus = validateStyles(":fUsuariosInsertarActualizar:correo", lfc_context, ls_correo, lb_focus);

			if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_correo))
				throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

			{
				Date ld_fechaDesde;

				ld_fechaDesde     = lu_usuario.getFechaDesde();
				lb_focus          = validateStyles(
					    ":fUsuariosInsertarActualizar:idCalFechaDesde", lfc_context, ld_fechaDesde, lb_focus
					);

				if(ld_fechaDesde == null)
					throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_DESDE);
			}

			if(ab_proceso)
				irr_referenceRemote.insertUser(lu_usuario, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
			else
				irr_referenceRemote.updateUser(lu_usuario, getUserId(), getLocalIpAddress(), getRemoteIpAddress());

			ls_result = NavegacionCommon.USUARIOS;

			addMessage(MessagesKeys.PROCESO_COMPLETADO);
			PrimeFaces.current().ajax().update("bean_Usuarios:globalMsg");

			{
				ExternalContext lec_externalContext;

				lec_externalContext = lfc_context.getExternalContext();

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
						lf_flash.setKeepMessages(true);
				}
			}

			clean();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update("bean_Usuarios:globalMsg");
		}

		return ls_result;
	}

	/**
	 * Mostrar panel enrolar
	 */
	public void mostrarEnrolar()
	{
		setEsSegundoFactor(false);
		setEsEnrolar(true);
	}

	/**
	 * Mostrar panel reinicio clave
	 */
	public void mostrarReinicioClave()
	{
		setEsSegundoFactor(false);
		setEsReinicioClave(true);
	}

	/**
	 * Mostrar panel reinicio huella
	 */
	public void mostrarReinicioHuella()
	{
		setEsSegundoFactor(false);
		setEsReinicioHuella(true);
	}

	/**
	 * Realiza la conexion con Bio client para el proceso a realizar
	 */
	public void opcionARealizar(boolean ab_enrolar, boolean ab_reinicioClave)
	{
		HttpSession lhs_httpSession;

		lhs_httpSession = FacesUtils.getSession();

		try
		{
			String ls_idUsuarioLoggeado;

			ls_idUsuarioLoggeado = getUserId();

			Usuario lu_usuarioAEnrolar;

			lu_usuarioAEnrolar = getUsuario();

			if((StringUtils.isValidString(ls_idUsuarioLoggeado)) && (lhs_httpSession != null))
			{
				StringBuilder lsb_builder;

				lsb_builder = new StringBuilder(IdentificadoresCommon.PROTOCOLO_BACHUE);

				if(ab_enrolar)
					lsb_builder.append(EventoCommon.ENROLL);
				else
					lsb_builder.append(EventoCommon.RESET);

				lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
				lsb_builder.append(lu_usuarioAEnrolar.getIdUsuario());
				lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
				lsb_builder.append(ls_idUsuarioLoggeado);

				if(!ab_enrolar)
				{
					lsb_builder.append(IdentificadoresCommon.SEPARADOR_DISPOSITIVOS);
					lsb_builder.append(ab_reinicioClave ? "1" : "2");
				}

				System.out.println(lsb_builder.toString());

				PrimeFaces.current().executeScript("abrirURLBioClient('" + lsb_builder.toString() + "');");
			}
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error(le_e);
		}
	}

	/**
	 * Realiza la conexion con Bio client para el proceso enrolar
	 */
	public void opcionEnrolar()
	{
		opcionARealizar(true, false);
	}

	/**
	 * Realiza la conexion con Bio client para el proceso reinicio clave o huella
	 */
	public void opcionReinicioHuellaOClave(boolean ab_reiniciaClave)
	{
		opcionARealizar(false, ab_reiniciaClave);
	}

	/**
	 * Regresa a opciones de segundo factor
	 */
	public void regresarAOpciones()
	{
		setEsSegundoFactor(true);
		setEsReinicioClave(false);
		setEsReinicioHuella(false);
		setEsEnrolar(false);
	}

	/**
	 * Regresa al detalle del usuario
	 */
	public void regresarDetalleUsuario()
	{
		setEsSegundoFactor(false);
		setEsReinicioClave(false);
		setEsReinicioHuella(false);
		setEsEnrolar(false);
	}

	/**
	 * Permite identificar si el tipo de documento y numero de documento
	 * ingresados por el usuario ya existe o no en la base de datos.
	 *
	 * @param as_tipoDoc es el tipo de documento seleccionado por el usuario
	 * @param as_numDoc es el numero de documento ingresado por el usuario
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDocumento(String as_tipoDoc, String as_numDoc)
	    throws B2BException
	{
		boolean lb_return;
		lb_return = false;

		if(StringUtils.isValidString(as_numDoc) && StringUtils.isValidString(as_tipoDoc))
		{
			Usuario lu_usuario;
			lu_usuario = new Usuario();

			lu_usuario.setIdDocumentoTipo(as_tipoDoc);
			lu_usuario.setNumeroDocumento(as_numDoc);

			lu_usuario = ipr_parameterRemote.findUsuarioByTipoDoc(
				    lu_usuario, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lu_usuario == null)
				lb_return = true;
		}

		setValidacionDocumento(lb_return);
	}

	/**
	 * Su funcion es verificar el tipo de documento seleccionado en pantalla y
	 * habilitar los campos de nombres o razon social, dependiendo de la eleccion.
	 *
	 * @param as_tipoDoc es el tipo de documento seleccionado por el usuario
	 */
	public void validarTipoDocumetal(String as_tipoDoc)
	{
		if(StringUtils.isValidString(as_tipoDoc))
		{
			Usuario      lu_usuario;
			FacesContext lfc_context;
			boolean      lb_focus;

			lb_focus        = true;
			lu_usuario      = getUsuario();
			lfc_context     = FacesContext.getCurrentInstance();

			if(lu_usuario != null)
			{
				if(as_tipoDoc.equals(IdentificadoresCommon.NIT))
				{
					setDocNit(true);

					validateStyles("fUsuariosInsertarActualizar:pNombre", lfc_context, "v", lb_focus);
					validateStyles("fUsuariosInsertarActualizar:pApellido", lfc_context, "v", lb_focus);

					lu_usuario.setPrimerNombre(null);
					lu_usuario.setSegundoNombre(null);
					lu_usuario.setPrimerApellido(null);
					lu_usuario.setSegundoApellido(null);
				}
				else
				{
					setDocNit(false);

					validateStyles("fUsuariosInsertarActualizar:razon", lfc_context, "v", lb_focus);
				}
			}

			setUsuario(lu_usuario);
		}
	}

	/**
	 * Método encargado de dirigir la aplicación a la pantalla principal.
	 */
	public void verify()
	{
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
		}
		catch(Exception le_e)
		{
			addMessage(le_e.getMessage());

			clh_LOGGER.error(le_e);
		}
	}
}
