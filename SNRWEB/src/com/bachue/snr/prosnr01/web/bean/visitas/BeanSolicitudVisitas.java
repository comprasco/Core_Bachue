package com.bachue.snr.prosnr01.web.bean.visitas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.CalidadSolicitanteCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EstadoCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.constants.TipoRecepcionCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.visitas.VisitasRemote;

import com.bachue.snr.prosnr01.model.integration.OWCC.DocumentoOWCC;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.DependenciaSNR;

import com.bachue.snr.prosnr01.web.bean.BaseBean;

import com.bachue.snr.prosnr02.common.constants.ScriptsCommon;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FlowEvent;

import org.primefaces.model.DefaultStreamedContent;

import java.io.Serializable;

import java.text.ParseException;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades SolicitudVisitas.
 *
 * @author Jorge Esteban Patiño Fonseca Fecha de Creacion: 29/07/2020
 */
@SessionScoped
@ManagedBean(name = "beanSolicitudVisitas")
public class BeanSolicitudVisitas extends BaseBean implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4177607034334178947L;

	/** Constante cs_INFORMACION_VISITAS. */
	private static final String cs_INFORMACION_VISITAS = "informacionVisitas";

	/** Constante cs_USUARIOS_VISITADORES. */
	private static final String cs_USUARIOS_VISITADORES = "usuariosVisitadores";

	/** Constante cs_PROYECCION_ACTO_ADMINISTRATIVO. */
	private static final String cs_PROYECCION_ACTO_ADMINISTRATIVO = "proyeccionActoAdministrativo";

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanSolicitudVisitas.class);

	/** Propiedad icd dependencias. */
	private Collection<DependenciaSNR> icd_dependencias;

	/** Propiedad icu usuarios. */
	private Collection<Usuario> icu_usuarios;

	/** Propiedad lcsv usuarios visitadores. */
	private Collection<SolicitudVisitas> lcsv_usuariosVisitadores;

	/** Propiedad id fecha desde. */
	private Date id_fechaDesde;

	/** Propiedad id fecha hasta. */
	private Date id_fechaHasta;

	/** Propiedad idsc imagen documento. */
	private DefaultStreamedContent idsc_imagenDocumento;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is consideraciones. */
	private String is_consideraciones;

	/** Propiedad is id circulo. */
	private String is_idCirculo;

	/** Propiedad is id dependencia. */
	private String is_idDependencia;

	/** Propiedad is id solicitud. */
	private String is_idSolicitud;

	/** Propiedad is id solicitud visita update. */
	private String is_idSolicitudVisitaUpdate;

	/** Propiedad is id solicitud visitas. */
	private String is_idSolicitudVisitas;

	/** Propiedad is id tipo visita. */
	private String is_idTipoVisita;

	/** Propiedad is nir. */
	private String is_nir;

	/** Propiedad is nir comp. */
	private String is_nirComp;

	/** Propiedad is razon 1. */
	private String is_razon1;

	/** Propiedad is resuelve. */
	private String is_resuelve;

	/** Propiedad ivr visitas remote. */
	@EJB
	private VisitasRemote ivr_visitasRemote;

	/** Propiedad iba documento. */
	private byte[] iba_documento;

	/** Propiedad ib render consulta SGD. */
	private boolean ib_renderConsultaSGD;

	/** Propiedad ib render enviar delegado. */
	private boolean ib_renderEnviarDelegado;

	/** Propiedad ib render generar documento. */
	private boolean ib_renderGenerarDocumento;

	/** Propiedad ib render guardar documento. */
	private boolean ib_renderGuardarDocumento;

	/** Propiedad ib render regresar. */
	private boolean ib_renderRegresar;

	/** Propiedad ib render siguiente. */
	private boolean ib_renderSiguiente;

	/** Propiedad ib seleccionado principal. */
	private boolean ib_seleccionadoPrincipal;

	/**
	 * {@inheritdoc}.
	 *
	 * @return el valor de application
	 */
	@Override
	public String getApplication()
	{
		return null;
	}

	/**
	 * Valida la propiedad auto.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en auto
	 */
	public boolean isAuto()
	{
		boolean lb_auto;

		lb_auto = false;

		{
			String ls_idTipoVisita;

			ls_idTipoVisita     = getIdTipoVisita();

			lb_auto = StringUtils.isValidString(ls_idTipoVisita)
					&& (ls_idTipoVisita.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
					|| ls_idTipoVisita.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2));
		}

		return lb_auto;
	}

	/**
	 * Modifica el valor de Consideraciones.
	 *
	 * @param as_s de is consideraciones
	 */
	public void setConsideraciones(String as_s)
	{
		is_consideraciones = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor consideraciones.
	 *
	 * @return el valor de consideraciones
	 */
	public String getConsideraciones()
	{
		return is_consideraciones;
	}

	/**
	 * Modifica el valor de Dependencias.
	 *
	 * @param acd_cd de icd dependencias
	 */
	public void setDependencias(Collection<DependenciaSNR> acd_cd)
	{
		icd_dependencias = acd_cd;
	}

	/**
	 * Retorna Objeto o variable de valor dependencias.
	 *
	 * @return el valor de dependencias
	 */
	public Collection<DependenciaSNR> getDependencias()
	{
		return icd_dependencias;
	}

	/**
	 * Modifica el valor de Documento.
	 *
	 * @param aba_ba de iba documento
	 */
	public void setDocumento(byte[] aba_ba)
	{
		iba_documento = aba_ba;
	}

	/**
	 * Retorna Objeto o variable de valor documento.
	 *
	 * @return el valor de documento
	 */
	public byte[] getDocumento()
	{
		return iba_documento;
	}

	/**
	 * Modifica el valor de Fecha desde.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaDesde(Date ad_d)
	{
		id_fechaDesde = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha desde.
	 *
	 * @return el valor de fecha desde
	 */
	public Date getFechaDesde()
	{
		return id_fechaDesde;
	}

	/**
	 * Modifica el valor de Fecha hasta.
	 *
	 * @param ad_d asigna el valor a la propiedad
	 */
	public void setFechaHasta(Date ad_d)
	{
		id_fechaHasta = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha hasta.
	 *
	 * @return el valor de fecha hasta
	 */
	public Date getFechaHasta()
	{
		return id_fechaHasta;
	}

	/**
	 * Modifica el valor de IdCirculo.
	 *
	 * @param as_s de as s
	 */
	public void setIdCirculo(String as_s)
	{
		is_idCirculo = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id circulo.
	 *
	 * @return el valor de id circulo
	 */
	public String getIdCirculo()
	{
		return is_idCirculo;
	}

	/**
	 * Modifica el valor de IdDependencia.
	 *
	 * @param as_s de as s
	 */
	public void setIdDependencia(String as_s)
	{
		is_idDependencia = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id dependencia.
	 *
	 * @return el valor de id dependencia
	 */
	public String getIdDependencia()
	{
		return is_idDependencia;
	}

	/**
	 * Modifica el valor de IdSolicitud.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitud(String as_s)
	{
		is_idSolicitud = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud.
	 *
	 * @return el valor de id solicitud
	 */
	public String getIdSolicitud()
	{
		return is_idSolicitud;
	}

	/**
	 * @param Modifica el valor de la propiedad is_idSolicitudVisitaUpdate por is_idSolicitudVisitaUpdate
	 */
	public void setIdSolicitudVisitaUpdate(String as_isvu)
	{
		is_idSolicitudVisitaUpdate = as_isvu;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idSolicitudVisitaUpdate
	 */
	public String getIdSolicitudVisitaUpdate()
	{
		return is_idSolicitudVisitaUpdate;
	}

	/**
	 * Modifica el valor de IdSolicitudVisitas.
	 *
	 * @param as_s de as s
	 */
	public void setIdSolicitudVisitas(String as_s)
	{
		is_idSolicitudVisitas = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id solicitud visitas.
	 *
	 * @return el valor de id solicitud visitas
	 */
	public String getIdSolicitudVisitas()
	{
		return is_idSolicitudVisitas;
	}

	/**
	 * Modifica el valor de IdTipoVisita.
	 *
	 * @param as_s de as s
	 */
	public void setIdTipoVisita(String as_s)
	{
		is_idTipoVisita = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id tipo visita.
	 *
	 * @return el valor de id tipo visita
	 */
	public String getIdTipoVisita()
	{
		return is_idTipoVisita;
	}

	/**
	 * Modifica el valor de ImagenDocumento.
	 *
	 * @param adsc_dsc de adsc dsc
	 */
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc)
	{
		idsc_imagenDocumento = adsc_dsc;
	}

	/**
	 * Retorna Objeto o variable de valor imagen documento.
	 *
	 * @return el valor de imagen documento
	 */
	public DefaultStreamedContent getImagenDocumento()
	{
		return idsc_imagenDocumento;
	}

	/**
	 * Modifica el valor de Nir.
	 *
	 * @param as_s de is nir
	 */
	public void setNir(String as_s)
	{
		is_nir = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor nir.
	 *
	 * @return el valor de nir
	 */
	public String getNir()
	{
		return is_nir;
	}

	/**
	 * @param Modifica el valor de la propiedad is_nirComp por is_nirComp
	 */
	public void setNirComp(String as_nc)
	{
		is_nirComp = as_nc;
	}

	/**
	 * @return Retorna el valor de la propiedad is_nirComp
	 */
	public String getNirComp()
	{
		return is_nirComp;
	}

	/**
	 * Modifica el valor de Razon1.
	 *
	 * @param as_s de as s
	 */
	public void setRazon1(String as_s)
	{
		is_razon1 = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor razon 1.
	 *
	 * @return el valor de razon 1
	 */
	public String getRazon1()
	{
		return is_razon1;
	}

	/**
	 * Modifica el valor de RenderConsultaSGD.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderConsultaSGD(boolean ab_b)
	{
		ib_renderConsultaSGD = ab_b;
	}

	/**
	 * Valida la propiedad render consulta SGD.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en render
	 * consulta SGD
	 */
	public boolean isRenderConsultaSGD()
	{
		return ib_renderConsultaSGD;
	}

	/**
	 * Modifica el valor de RenderEnviarDelegado.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderEnviarDelegado(boolean ab_b)
	{
		ib_renderEnviarDelegado = ab_b;
	}

	/**
	 * Valida la propiedad render enviar delegado.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en render
	 * enviar delegado
	 */
	public boolean isRenderEnviarDelegado()
	{
		return ib_renderEnviarDelegado;
	}

	/**
	 * Modifica el valor de RenderGenerarDocumento.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderGenerarDocumento(boolean ab_b)
	{
		ib_renderGenerarDocumento = ab_b;
	}

	/**
	 * Valida la propiedad render generar documento.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en render
	 * generar documento
	 */
	public boolean isRenderGenerarDocumento()
	{
		return ib_renderGenerarDocumento;
	}

	/**
	 * Modifica el valor de RenderGuardarDocumento.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderGuardarDocumento(boolean ab_b)
	{
		ib_renderGuardarDocumento = ab_b;
	}

	/**
	 * Valida la propiedad render guardar documento.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en render
	 * guardar documento
	 */
	public boolean isRenderGuardarDocumento()
	{
		return ib_renderGuardarDocumento;
	}

	/**
	 * Modifica el valor de RenderRegresar.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderRegresar(boolean ab_b)
	{
		ib_renderRegresar = ab_b;
	}

	/**
	 * Valida la propiedad render regresar.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en render
	 * regresar
	 */
	public boolean isRenderRegresar()
	{
		return ib_renderRegresar;
	}

	/**
	 * Modifica el valor de RenderSiguiente.
	 *
	 * @param ab_b de ab b
	 */
	public void setRenderSiguiente(boolean ab_b)
	{
		ib_renderSiguiente = ab_b;
	}

	/**
	 * Valida la propiedad render siguiente.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en render
	 * siguiente
	 */
	public boolean isRenderSiguiente()
	{
		return ib_renderSiguiente;
	}

	/**
	 * Modifica el valor de Resuelve.
	 *
	 * @param as_s de as s
	 */
	public void setResuelve(String as_s)
	{
		is_resuelve = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor resuelve.
	 *
	 * @return el valor de resuelve
	 */
	public String getResuelve()
	{
		return is_resuelve;
	}

	/**
	 * @param Modifica el valor de la propiedad seleccionadoPrincipal por seleccionadoPrincipal
	 */
	public void setSeleccionadoPrincipal(boolean ab_sp)
	{
		ib_seleccionadoPrincipal = ab_sp;
	}

	/**
	 * @return Retorna el valor de la propiedad seleccionadoPrincipal
	 */
	public boolean isSeleccionadoPrincipal()
	{
		return ib_seleccionadoPrincipal;
	}

	/**
	 * Modifica el valor de Usuarios.
	 *
	 * @param acu_cu de <code>Collection</code> de <code>Usuario</code>
	 */
	public void setUsuarios(Collection<Usuario> acu_cu)
	{
		icu_usuarios = acu_cu;
	}

	/**
	 * Retorna Objeto o variable de valor usuarios.
	 *
	 * @return el valor de usuarios
	 */
	public Collection<Usuario> getUsuarios()
	{
		return icu_usuarios;
	}

	/**
	 * Retorna Objeto o variable de valor usuarios visitadores.
	 *
	 * @return el valor de usuarios visitadores
	 */
	public Collection<SolicitudVisitas> getUsuariosVisitadores()
	{
		return lcsv_usuariosVisitadores;
	}

	/**
	 * Modifica el valor de UusuariosVisitadores.
	 *
	 * @param acsv_csv de acsv csv
	 */
	public void setUusuariosVisitadores(Collection<SolicitudVisitas> acsv_csv)
	{
		lcsv_usuariosVisitadores = acsv_csv;
	}

	/**
	 * Accion boton generar documentos.
	 */
	public void accionBotonGenerarDocumentos()
	{
		try
		{
			OficiosTexto lof_campos;

			lof_campos = new OficiosTexto();

			{
				String ls_consideraciones;

				ls_consideraciones = getConsideraciones();

				if(StringUtils.isValidString(ls_consideraciones))
					lof_campos.setConsideracion(convertirTextoParaRtf(ls_consideraciones));
				else
					throw new B2BException("Debe ingresar datos validos para consideraciones");
			}

			{
				String ls_resuelve;

				ls_resuelve = getResuelve();

				if(StringUtils.isValidString(ls_resuelve))
					lof_campos.setResuelve(convertirTextoParaRtf(ls_resuelve));
				else
					throw new B2BException("Debe ingresar datos validos para resuelve");
			}

			{
				String ls_razon1;

				ls_razon1 = getRazon1();

				if(StringUtils.isValidString(ls_razon1))
					lof_campos.setRazon1(convertirTextoParaRtf(ls_razon1));
				else
					throw new B2BException("Debe ingresar datos validos para motivo visita");
			}

			seleccionarDocumentoGenerar(lof_campos);
			actualizarWizard("proyeccionActoAdministrativo", "fSolicitudVisitas:myWizard", "fSolicitudVisitas");
			setRenderGuardarDocumento(true);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("accionBotonGenerarDocumentos", lb2be_e);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	/**
	 * Accion consulta SGD.
	 */
	public void accionConsultaSGD()
	{
		try
		{
			DocumentoOWCC ldo_documento;

			ldo_documento = new DocumentoOWCC(getNir(), true);

			accionSGD(ldo_documento, NavegacionCommon.SOLICITUD_VISITAS, true);
		}
		catch(Exception le_e)
		{
			B2BException lb2be_e;

			lb2be_e = new B2BException("IOException", le_e);

			addMessage(lb2be_e);
			clh_LOGGER.error("accionConsultaSGD", lb2be_e);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			PrimeFaces.current().ajax().update("fSolicitudVisitas:globalMsg");
		}
	}

	/**
	 * Accion enviar delegado registro.
	 *
	 * @return el valor de string
	 */
	public String accionEnviarDelegadoRegistro()
	{
		String ls_return;

		ls_return = null;

		try
		{
			ivr_visitasRemote.accionEnviarDelegadoRegistro(
			    getIdSolicitud(), getIdTipoVisita(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);
			ls_return = NavegacionCommon.PRINCIPAL;
			clean();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("accionEnviarDelegadoRegistro", lb2be_e);
		}

		return ls_return;
	}

	/**
	 * Accion guardar documento.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void accionGuardarDocumento()
	    throws B2BException
	{
		try
		{
			ivr_visitasRemote.guardarDocumentoOWCC(
			    getIdSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress(), isAuto()
			);
			setRenderGenerarDocumento(false);
			setRenderGuardarDocumento(false);
			setRenderConsultaSGD(true);
			setRenderEnviarDelegado(true);
			setImagenDocumento(
			    generarArchivosDescarga(
			        getDocumento(), TipoContenidoCommon.PDF, ConstanteCommon.NOMBRE_ARCHIVO_COMUNICADO_DESISTIMIENTO
			    )
			);
			actualizarWizard("proyeccionActoAdministrativo", "fSolicitudVisitas:myWizard", "fSolicitudVisitas");
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("accionGuardarDocumento", lb2be_e);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			throw lb2be_e;
		}
	}

	/**
	 * Cambiar usuario principal.
	 *
	 * @param au_usuario correspondiente al valor de au usuario
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cambiarUsuarioPrincipal(Usuario au_usuario)
	    throws B2BException
	{
		if(au_usuario != null)
		{
			try
			{
				boolean lb_seleccionado;
				boolean lb_seleccionadoPrincipal;

				lb_seleccionado              = au_usuario.isVisitadorPrincipal();
				lb_seleccionadoPrincipal     = isSeleccionadoPrincipal();

				if(lb_seleccionado)
				{
					if(!lb_seleccionadoPrincipal)
						setSeleccionadoPrincipal(true);
					else
						throw new B2BException(ErrorKeys.UN_VISITADOR_PRINCIPAL);
				}
				else
					setSeleccionadoPrincipal(false);
			}
			catch(B2BException lb2be_e)
			{
				au_usuario.setVisitadorPrincipal(false);
				clh_LOGGER.error("cambiarUsuarioPrincipal", lb2be_e);
				addMessage(lb2be_e);
			}
		}
	}

	/**
	 * Cargar dependencias.
	 */
	public void cargarDependencias()
	{
		setDependencias(findAllDependenciaByIndVisitas());
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setUsuarios(null);
		setUusuariosVisitadores(null);
		setFechaDesde(null);
		setFechaHasta(null);
		setImagenDocumento(null);
		setIdCirculo(null);
		setIdDependencia(null);
		setIdSolicitud(null);
		setIdSolicitudVisitas(null);
		setIdTipoVisita(null);
		setRazon1(null);
		setDependencias(null);
		setRenderGuardarDocumento(false);
		setRenderGenerarDocumento(true);
		setRenderConsultaSGD(false);
		setRenderSiguiente(true);
		setRenderRegresar(false);
		setRenderEnviarDelegado(false);
		setConsideraciones(null);
		setNirComp(null);
	}

	/**
	 * Consultar usuarios.
	 */
	public void consultarUsuarios()
	{
		Collection<Usuario> lcu_usuario;

		lcu_usuario = new LinkedList<Usuario>();

		try
		{
			lcu_usuario = irr_referenceRemote.findAllUsersActivos(
				    "", getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("consultarUsuarios", lb2be_e);
		}

		setUsuarios(lcu_usuario);
	}

	/**
	 * Flow listener.
	 *
	 * @param afe_event de <code>FlowEvent</code>
	 * @return el valor de <code>String</code>
	 * @throws ParseException
	 */
	public String flowListener(FlowEvent afe_event)
	{
		String ls_return;

		ls_return = afe_event.getNewStep();

		if(afe_event != null)
		{
			boolean lb_focus;

			lb_focus = true;

			try
			{
				FacesContext lfc_context;
				String       ls_oldStep;
				String       ls_newStep;

				lfc_context     = FacesContext.getCurrentInstance();
				ls_oldStep      = afe_event.getOldStep();
				ls_newStep      = afe_event.getNewStep();
				setRenderRegresar(true);

				if(
				    (lfc_context != null) && StringUtils.isValidString(ls_oldStep)
					    && StringUtils.isValidString(ls_newStep)
				)
				{
					if(
					    ls_oldStep.equalsIgnoreCase(cs_INFORMACION_VISITAS)
						    && ls_newStep.equalsIgnoreCase(cs_USUARIOS_VISITADORES)
					)
					{
						salvarInformacionVisita(getIdSolicitud(), validarInformacionVisita(lb_focus, lfc_context));
						consultarUsuarios();
						setSeleccionadoPrincipal(false);
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(cs_USUARIOS_VISITADORES)
						    && ls_newStep.equalsIgnoreCase(cs_PROYECCION_ACTO_ADMINISTRATIVO)
					)
					{
						salvarUsuariosVisitadores(getNirComp());
						seleccionarDocumentoGenerar(null);
						setRenderSiguiente(false);
						setNirComp(getNir());
					}
					else if(
					    ls_oldStep.equalsIgnoreCase(cs_USUARIOS_VISITADORES)
						    && ls_newStep.equalsIgnoreCase(cs_INFORMACION_VISITAS)
					)
						setRenderRegresar(false);
					else if(
					    ls_oldStep.equalsIgnoreCase(cs_PROYECCION_ACTO_ADMINISTRATIVO)
						    && ls_newStep.equalsIgnoreCase(cs_USUARIOS_VISITADORES)
					)
						setRenderSiguiente(true);
				}
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("flowListener", lb2be_e);
				addMessage(lb2be_e);
				ls_return = afe_event.getOldStep();
			}
			finally
			{
				PrimeFaces.current().ajax().update("fSolicitudVisitas:globalMsg");
				PrimeFaces.current().ajax().update("fSolicitudVisitas:botonesBandeja");
			}
		}

		return ls_return;
	}

	/**
	 * Cargar texto campos.
	 */
	private void cargarTextoCampos()
	{
		try
		{
			boolean lb_auto;

			lb_auto = isAuto();

			{
				String[] las_textos;

				las_textos = ivr_visitasRemote.cargarTextoCampos(
					    lb_auto, getIdSolicitudVisitas(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(CollectionUtils.isValidCollection(las_textos))
					setResuelve(las_textos[0]);
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("cargarTextoCampos", lb2be_e);
		}
	}

	/**
	 * Find all dependencia by ind visitas.
	 *
	 * @return el valor de <code>Collection</code> de <code>Dependencia</code>
	 */
	private Collection<DependenciaSNR> findAllDependenciaByIndVisitas()
	{
		Collection<DependenciaSNR> lcd_data;

		lcd_data = null;

		try
		{
			lcd_data = irr_referenceRemote.findAllDependenciaByIndVisitas();
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
		}

		return lcd_data;
	}

	/**
	 * Generar auto ordena visita general.
	 *
	 * @param aot_oficio de aot oficio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void generarAutoOrdenaVisitaGeneral(OficiosTexto aot_oficio)
	    throws B2BException
	{
		try
		{
			byte[] lba_documento;

			lba_documento = ivr_visitasRemote.generarAutoOrdenaVisitaGeneral(
				    getIdSolicitudVisitas(), aot_oficio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lba_documento != null)
			{
				setDocumento(lba_documento);
				setImagenDocumento(
				    generarArchivosDescarga(
				        lba_documento, TipoContenidoCommon.PDF, ConstanteCommon.NOMBRE_ARCHIVO_COMUNICADO_DESISTIMIENTO
				    )
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarAutoOrdenaVisitaGeneral", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Generar resolucion ordena intervencion.
	 *
	 * @param aot_oficio de aot oficio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void generarResolucionOrdenaIntervencion(OficiosTexto aot_oficio)
	    throws B2BException
	{
		try
		{
			byte[] lba_documento;

			lba_documento = ivr_visitasRemote.generarResolucionOrdenaIntervencion(
				    getIdSolicitudVisitas(), aot_oficio, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);

			if(lba_documento != null)
			{
				setDocumento(lba_documento);
				setImagenDocumento(
				    generarArchivosDescarga(
				        lba_documento, TipoContenidoCommon.PDF, ConstanteCommon.NOMBRE_ARCHIVO_COMUNICADO_DESISTIMIENTO
				    )
				);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarAutoOrdenaVisitaGeneral", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Salvar informacion visita.
	 *
	 * @param as_solicitud de as solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void salvarInformacionVisita(String as_idSolicitudComp, SolicitudVisitas as_solicitud)
	    throws B2BException
	{
		try
		{
			if(as_solicitud != null)
			{
				SolicitudVisitas lsv_solicitud;

				lsv_solicitud = ivr_visitasRemote.salvarInformacionVisitas(
					    as_idSolicitudComp, as_solicitud, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(lsv_solicitud != null)
				{
					setIdSolicitud(lsv_solicitud.getIdSolicitud());

					if(lsv_solicitud.getIdSolicitudVisitas() != null)
						setIdSolicitudVisitaUpdate(lsv_solicitud.getIdSolicitudVisitas());

					if(lsv_solicitud.getIdSolicitudVisitas() == null)
						lsv_solicitud.setIdSolicitudVisitas(getIdSolicitudVisitaUpdate());

					setIdSolicitudVisitas(lsv_solicitud.getIdSolicitudVisitas());
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarInformacionVisita", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Salvar usuarios visitadores.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void salvarUsuariosVisitadores(String as_nirComp)
	    throws B2BException
	{
		try
		{
			setNir(
			    ivr_visitasRemote.salvarUsuariosVisitadores(
			        as_nirComp, getUsuarios(), getIdSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			    )
			);
			cargarTextoCampos();
			PrimeFaces.current().executeScript(ScriptsCommon.DIALOG_NIR_VISITAS_SHOW);
			PrimeFaces.current().ajax().update("fDialog2:otMensaje");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarUsuariosVisitadores", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Seleccionar documento generar.
	 *
	 * @param aof_oficio de aof oficio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	private void seleccionarDocumentoGenerar(OficiosTexto aof_oficio)
	    throws B2BException
	{
		try
		{
			String ls_tipoVisita;

			ls_tipoVisita = getIdTipoVisita();

			if(StringUtils.isValidString(ls_tipoVisita))
			{
				switch(ls_tipoVisita)
				{
					case ProcesoCommon.ID_SUBPROCESO_1:
					case ProcesoCommon.ID_SUBPROCESO_2:
						generarAutoOrdenaVisitaGeneral(aof_oficio);

						break;

					case ProcesoCommon.ID_SUBPROCESO_3:
					case ProcesoCommon.ID_SUBPROCESO_4:
					case ProcesoCommon.ID_SUBPROCESO_5:
						generarResolucionOrdenaIntervencion(aof_oficio);

						break;

					default:
						break;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("salvarUsuariosVisitadores", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Validar informacion visita.
	 *
	 * @param ab_focus de ab focus
	 * @param afc_context de afc context
	 * @return el valor de solicitud visitas
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws ParseException
	 */
	private SolicitudVisitas validarInformacionVisita(boolean ab_focus, FacesContext afc_context)
	    throws B2BException
	{
		SolicitudVisitas lsv_solicitudVisitas;

		lsv_solicitudVisitas = null;

		if(afc_context != null)
		{
			try
			{
				Solicitud ls_solicitud;

				ls_solicitud             = new Solicitud();
				lsv_solicitudVisitas     = new SolicitudVisitas();

				ls_solicitud.setIdProceso(ProcesoCommon.ID_PROCESO_62);
				ls_solicitud.setIdTipoRecepcion(TipoRecepcionCommon.TIPO_RECEPCION_7);
				ls_solicitud.setIdCalidadSolicitante(CalidadSolicitanteCommon.FUNCIONARIO);
				ls_solicitud.setDerechoPeticion(EstadoCommon.NO);
				ls_solicitud.setParticipaInterviniente(EstadoCommon.NO);
				ls_solicitud.setDigitalizada(EstadoCommon.NO);
				ls_solicitud.setIdUsuarioCreacion(getUserId());

				{
					String ls_idCirculo;

					ls_idCirculo     = getIdCirculo();

					ab_focus = validateStyles(":fSolicitudVisitas:idSOMOrip", afc_context, ls_idCirculo, ab_focus);

					if(!StringUtils.isValidString(ls_idCirculo))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_CIRCULO_REGISTRAL);

					lsv_solicitudVisitas.setIdCirculo(ls_idCirculo);
				}

				{
					String ls_tipoVisita;

					ls_tipoVisita     = getIdTipoVisita();

					ab_focus = validateStyles(
						    ":fSolicitudVisitas:idSOMTipoVisita", afc_context, ls_tipoVisita, ab_focus
						);

					if(!StringUtils.isValidString(ls_tipoVisita))
						throw new B2BException("Debe seleccionar un tipo visita válido");

					ls_solicitud.setIdSubproceso(ls_tipoVisita);
				}

				{
					String ls_idDependencia;

					ls_idDependencia     = getIdDependencia();

					ab_focus = validateStyles(
						    ":fSolicitudVisitas:idSOMDependencia", afc_context, ls_idDependencia, ab_focus
						);

					if(!StringUtils.isValidString(ls_idDependencia))
						throw new B2BException("Seleccione una dependencia");

					lsv_solicitudVisitas.setIdDependencia(ls_idDependencia);
				}

				{
					Date ld_fechaDesde;
					Date ld_fechaHasta;

					ld_fechaDesde     = getFechaDesde();
					ld_fechaHasta     = getFechaHasta();

					validarFechaDesdeFechaHasta(
					    ld_fechaDesde, ld_fechaHasta, ":fSolicitudVisitas:idFechaDesde",
					    ":fSolicitudVisitas:idFechaHasta", afc_context, ab_focus, false
					);

					if(ld_fechaHasta == null)
					{
						ab_focus = validateStyles(
							    ":fSolicitudVisitas:idFechaHasta", afc_context, IdentificadoresCommon.DATO_INVALIDO,
							    ab_focus
							);
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_HASTA);
					}
					else
						ab_focus = validateStyles(
							    ":fSolicitudVisitas:idFechaHasta", afc_context, IdentificadoresCommon.DATO_VALIDO,
							    ab_focus
							);

					lsv_solicitudVisitas.setFechaDesde(ld_fechaDesde);
					lsv_solicitudVisitas.setFechaHasta(ld_fechaHasta);
				}

				lsv_solicitudVisitas.setSolicitud(ls_solicitud);
				lsv_solicitudVisitas.setIdUsuarioCreacion(getUserId());
			}
			catch(B2BException lb2be_e)
			{
				clh_LOGGER.error("validarInformacionVisita", lb2be_e);
				throw lb2be_e;
			}
		}

		return lsv_solicitudVisitas;
	}
}
