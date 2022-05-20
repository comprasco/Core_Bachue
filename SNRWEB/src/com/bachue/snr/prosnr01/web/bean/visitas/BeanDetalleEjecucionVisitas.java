package com.bachue.snr.prosnr01.web.bean.visitas;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.ProcesoCommon;
import com.bachue.snr.prosnr01.common.constants.RolCommon;
import com.bachue.snr.prosnr01.common.constants.TramiteCommon;

import com.bachue.snr.prosnr01.ejb.session.stateless.reference.ReferenceRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.visitas.VisitasRemote;

import com.bachue.snr.prosnr01.model.business.DetalleEjecucionVisitas;
import com.bachue.snr.prosnr01.model.business.EjecucionVisitas;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudVisitas;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.aut.Usuario;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisita;
import com.bachue.snr.prosnr01.model.sdb.pgn.TramiteVisitaTipo;

import com.bachue.snr.prosnr01.web.bean.BaseBean;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanAprobacion;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.BeanHeaderDatosDelTurno;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.BeanPanelSolicitudVisitas;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.BeanPanelTipoTramitesARealizarVisitas;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.BeanPanelTramitesARealizarVisitas;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.BeanProyeccionDocumento;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelSolicitudVisitasInterface;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTramitesARealizarVisitasInterface;
import com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.model.DefaultStreamedContent;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


/**
 * Clase que contiene todos las propiedades BeanDetalleEjecucionVisitas.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 23/10/2021
 */
@SessionScoped
@ManagedBean(name = "beanDetalleEjecucionVisitas")
public class BeanDetalleEjecucionVisitas extends BaseBean implements Serializable, HeaderDatosDelTurnoInterface,
	PanelSolicitudVisitasInterface, PanelTramitesARealizarVisitasInterface, PanelTipoTramitesARealizarVisitasInterface,
	ProyeccionDocumentoInterface
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 714893232804274324L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanDetalleEjecucionVisitas.class);
	/** Propiedad icu all usuarios. */
	Collection<Usuario> icu_allUsuarios;

	/** Propiedad ibhddt bean. */
	private BeanHeaderDatosDelTurno ibhddt_bean;

	/** Propiedad ibpsv bean. */
	private BeanPanelSolicitudVisitas ibpsv_bean;

	/** Propiedad ipttarv bean. */
	private BeanPanelTipoTramitesARealizarVisitas ipttarv_bean;

	/** Propiedad ibptarv bean. */
	private BeanPanelTramitesARealizarVisitas ibptarv_bean;

	/** Propiedad ibpd bean. */
	private BeanProyeccionDocumento ibpd_bean;

	/** Propiedad icu usuarios. */
	private Collection<Usuario> icu_usuarios;

	/** Propiedad id fecha final. */
	private Date id_fechaFinal;

	/** Propiedad id fecha inicial. */
	private Date id_fechaInicial;

	/** Propiedad idev informacion proyeccion documentos. */
	private DetalleEjecucionVisitas idev_informacionProyeccionDocumentos;

	/** Propiedad irr reference remote. */
	@EJB
	private ReferenceRemote irr_referenceRemote;

	/** Propiedad is id constante. */
	private String is_idConstante;

	/** Propiedad is id solicitud visitas. */
	private String is_idSolicitudVisitas;

	/** Propiedad is id subproceso. */
	private String is_idSubProceso;

	/** Propiedad is id usuario seleccionado. */
	private String is_idUsuarioSeleccionado;

	/** Propiedad iu usuario seleccionado. */
	private Usuario iu_usuarioSeleccionado;

	/** Propiedad ivr remote. */
	@EJB
	private VisitasRemote ivr_remote;

	/** Propiedad ib proceso reasignacion. */
	private boolean ib_procesoReasignacion = false;

	/** Propiedad ib rol tiene permiso. */
	private boolean ib_rolTienePermiso;

	/** Propiedad ib seleccionado principal. */
	private boolean ib_seleccionadoPrincipal;

	/**
	 * @param Modifica el valor de la propiedad icu_allUsuarios por icu_allUsuarios
	 */
	public void setAllUsuarios(Collection<Usuario> acu_allUsuarios)
	{
		icu_allUsuarios = acu_allUsuarios;
	}

	/**
	 * @return Retorna el valor de la propiedad icu_allUsuarios
	 */
	public Collection<Usuario> getAllUsuarios()
	{
		return icu_allUsuarios;
	}

	@Override
	public String getApplication()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	* @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#setCamposPantalla(java.util.Collection)
	*/
	@Override
	public void setCamposPantalla(Collection<TagPlantillaResolucion> actpr_ctpr)
	{
		getBeanProyeccionDocumento().setCamposPantalla(actpr_ctpr);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#getCamposPantalla()
	 */
	@Override
	public Collection<TagPlantillaResolucion> getCamposPantalla()
	{
		return getBeanProyeccionDocumento().getCamposPantalla();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelSolicitudVisitasInterface#setDatosPanelSolicitudVisitas(java.util.Collection)
	 */
	@Override
	public void setDatosPanelSolicitudVisitas(Collection<SolicitudVisitas> acsv_csv)
	{
		getBeanPanelSolicitudVisitas().setDatosPanelSolicitudVisitas(acsv_csv);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelSolicitudVisitasInterface#getDatosPanelSolicitudVisitas()
	 */
	@Override
	public Collection<SolicitudVisitas> getDatosPanelSolicitudVisitas()
	{
		return getBeanPanelSolicitudVisitas().getDatosPanelSolicitudVisitas();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#setDecisionCalificacion(java.lang.String)
	 */
	@Override
	public void setDecisionCalificacion(String as_s)
	{
		getBeanHeaderDatosDelTurno().setDecisionCalificacion(as_s);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#getDecisionCalificacion()
	 */
	@Override
	public String getDecisionCalificacion()
	{
		return getBeanHeaderDatosDelTurno().getDecisionCalificacion();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#setDelegadoRegistro(boolean)
	 */
	@Override
	public void setDelegadoRegistro(boolean ab_b)
	{
		getBeanPanelTipoTramitesARealizarVisitas().setDelegadoRegistro(ab_b);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#isDelegadoRegistro()
	 */
	@Override
	public boolean isDelegadoRegistro()
	{
		return getBeanPanelTipoTramitesARealizarVisitas().isDelegadoRegistro();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#setDocumentoArray(byte[])
	 */
	@Override
	public void setDocumentoArray(byte[] aba_ba)
	{
		getBeanProyeccionDocumento().setDocumentoArray(aba_ba);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#getDocumentoArray()
	 */
	@Override
	public byte[] getDocumentoArray()
	{
		return getBeanProyeccionDocumento().getDocumentoArray();
	}

	/**
	 * Modifica el valor de FechaFinal.
	 *
	 * @param ad_d de ad d
	 */
	public void setFechaFinal(Date ad_d)
	{
		id_fechaFinal = ad_d;
	}

	/**
	 * Retorna Objeto o variable de valor fecha final.
	 *
	 * @return el valor de fecha final
	 */
	public Date getFechaFinal()
	{
		return id_fechaFinal;
	}

	/**
	* Modifica el valor de FechaInicial.
	*
	* @param ad_d de ad d
	*/
	public void setFechaInicial(Date ad_d)
	{
		id_fechaInicial = ad_d;
	}

	/**
	* Retorna Objeto o variable de valor fecha inicial.
	*
	* @return el valor de fecha inicial
	*/
	public Date getFechaInicial()
	{
		return id_fechaInicial;
	}

	/**
	 * Modifica el valor de IdConstante.
	 *
	 * @param as_s de as s
	 */
	public void setIdConstante(String as_s)
	{
		is_idConstante = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id constante.
	 *
	 * @return el valor de id constante
	 */
	public String getIdConstante()
	{
		return is_idConstante;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelSolicitudVisitasInterface#setIdSolicitud(java.lang.String)
	 */
	@Override
	public void setIdSolicitud(String as_s)
	{
		getBeanPanelSolicitudVisitas().setIdSolicitud(as_s);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelSolicitudVisitasInterface#getIdSolicitud()
	 */
	@Override
	public String getIdSolicitud()
	{
		return getBeanPanelSolicitudVisitas().getIdSolicitud();
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
	 * Modifica el valor de IdSubproceso.
	 *
	 * @param as_s de as s
	 */
	public void setIdSubProceso(String as_s)
	{
		is_idSubProceso = as_s;
	}

	/**
	 * Retorna Objeto o variable de valor id subproceso.
	 *
	 * @return el valor de id subproceso
	 */
	public String getIdSubProceso()
	{
		return is_idSubProceso;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#setIdTurno(java.lang.String)
	 */
	@Override
	public void setIdTurno(String as_s)
	{
		getBeanHeaderDatosDelTurno().setIdTurno(as_s);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#getIdTurno()
	 */
	@Override
	public String getIdTurno()
	    throws B2BException
	{
		return getBeanHeaderDatosDelTurno().getIdTurno();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#setIdTurnoConsulta(java.lang.String)
	 */
	@Override
	public void setIdTurnoConsulta(String as_s)
	{
		getBeanHeaderDatosDelTurno().setIdTurnoConsulta(as_s);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#setIdTurnoHistoria(java.lang.String)
	 */
	@Override
	public void setIdTurnoHistoria(String as_s)
	{
		getBeanHeaderDatosDelTurno().setIdTurnoHistoria(as_s);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#getIdTurnoHistoria()
	 */
	@Override
	public String getIdTurnoHistoria()
	{
		return getBeanHeaderDatosDelTurno().getIdTurnoHistoria();
	}

	/**
	 * @param Modifica el valor de la propiedad is_idUsuarioSeleccionado por is_idUsuarioSeleccionado
	 */
	public void setIdUsuarioSeleccionado(String as_idUsuarioSeleccionado)
	{
		is_idUsuarioSeleccionado = as_idUsuarioSeleccionado;
	}

	/**
	 * @return Retorna el valor de la propiedad is_idUsuarioSeleccionado
	 */
	public String getIdUsuarioSeleccionado()
	{
		return is_idUsuarioSeleccionado;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#setImagenDocumento(org.primefaces.model.DefaultStreamedContent)
	 */
	@Override
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc)
	{
		getBeanProyeccionDocumento().setImagenDocumento(adsc_dsc);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#getImagenDocumento()
	 */
	@Override
	public DefaultStreamedContent getImagenDocumento()
	{
		return getBeanProyeccionDocumento().getImagenDocumento();
	}

	/**
	 * Modifica el valor de InformacionProyeccionDocumentos.
	 *
	 * @param adev_dev de idev informacion proyeccion documentos
	 */
	public void setInformacionProyeccionDocumentos(DetalleEjecucionVisitas adev_dev)
	{
		idev_informacionProyeccionDocumentos = adev_dev;
	}

	/**
	 * Retorna Objeto o variable de valor informacion proyeccion documentos.
	 *
	 * @return el valor de informacion proyeccion documentos
	 */
	public DetalleEjecucionVisitas getInformacionProyeccionDocumentos()
	{
		return idev_informacionProyeccionDocumentos;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#setLiderVigilanciaControl(boolean)
	 */
	@Override
	public void setLiderVigilanciaControl(boolean ab_b)
	{
		getBeanPanelTipoTramitesARealizarVisitas().setLiderVigilanciaControl(ab_b);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#isLiderVigilanciaControl()
	 */
	@Override
	public boolean isLiderVigilanciaControl()
	{
		return getBeanPanelTipoTramitesARealizarVisitas().isLiderVigilanciaControl();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#setMostrarBotonSalvarProyeccionDocumento(boolean)
	 */
	@Override
	public void setMostrarBotonSalvarProyeccionDocumento(boolean ab_b)
	{
		getBeanProyeccionDocumento().setMostrarBotonSalvarProyeccionDocumento(ab_b);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#isMostrarBoton()
	 */
	@Override
	public boolean isMostrarBotonSalvarProyeccionDocumento()
	{
		return getBeanProyeccionDocumento().isMostrarBotonSalvarProyeccionDocumento();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#setPredio(java.util.Map)
	 */
	@Override
	public void setPredio(Map<String, Object> ahmso_hmso)
	{
		getBeanHeaderDatosDelTurno().setPredio(ahmso_hmso);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#getPredio()
	 */
	@Override
	public Map<String, Object> getPredio()
	{
		return getBeanHeaderDatosDelTurno().getPredio();
	}

	/**
	 * @param Modifica el valor de la propiedad ib_procesoReasignacion por ib_procesoReasignacion
	 */
	public void setProcesoReasignacion(boolean ab_procesoReasignacion)
	{
		ib_procesoReasignacion = ab_procesoReasignacion;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_procesoReasignacion
	 */
	public boolean isProcesoReasignacion()
	{
		return ib_procesoReasignacion;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#setLiderVigilanciaControl(boolean)
	 */
	@Override
	public void setResponsableSeguimientoVisita(boolean ab_b)
	{
		getBeanPanelTipoTramitesARealizarVisitas().setResponsableSeguimientoVisita(ab_b);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#isLiderVigilanciaControl()
	 */
	@Override
	public boolean isResponsableSeguimientoVisita()
	{
		return getBeanPanelTipoTramitesARealizarVisitas().isResponsableSeguimientoVisita();
	}

	/**
	 * Modifica el valor de RolTienePermiso.
	 *
	 * @param ab_b de ab b
	 */
	public void setRolTienePermiso(boolean ab_b)
	{
		ib_rolTienePermiso = ab_b;
	}

	/**
	 * Valida la propiedad rol tiene permiso.
	 *
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code> en rol tiene permiso
	 */
	public boolean isRolTienePermiso()
	{
		return ib_rolTienePermiso;
	}

	/**
	 * @param Modifica el valor de la propiedad ib_seleccionadoPrincipal por ib_seleccionadoPrincipal
	 */
	public void setSeleccionadoPrincipal(boolean ab_sp)
	{
		ib_seleccionadoPrincipal = ab_sp;
	}

	/**
	 * @return Retorna el valor de la propiedad ib_seleccionadoPrincipal
	 */
	public boolean isSeleccionadoPrincipal()
	{
		return ib_seleccionadoPrincipal;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#setSolucionTags(com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto)
	 */
	@Override
	public void setSolucionTags(OficiosTexto aot_ot)
	{
		getBeanProyeccionDocumento().setSolucionTags(aot_ot);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#getSolucionTags()
	 */
	@Override
	public OficiosTexto getSolucionTags()
	{
		return getBeanProyeccionDocumento().getSolucionTags();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#setTipoTramiteSeleccionado(java.lang.String)
	 */
	@Override
	public void setTipoTramiteSeleccionado(String as_s)
	{
		getBeanPanelTipoTramitesARealizarVisitas().setTipoTramiteSeleccionado(as_s);
	}

	/* (non-Javadoc)
	* @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#getTipoTramiteSeleccionado()
	*/
	@Override
	public String getTipoTramiteSeleccionado()
	{
		return getBeanPanelTipoTramitesARealizarVisitas().getTipoTramiteSeleccionado();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#setTipoTramitesARealizar(java.util.Collection)
	 */
	@Override
	public void setTipoTramitesARealizar(Collection<TramiteVisitaTipo> actvt_ctvt)
	{
		getBeanPanelTipoTramitesARealizarVisitas().setTipoTramitesARealizar(actvt_ctvt);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#getTipoTramitesARealizar()
	 */
	@Override
	public Collection<TramiteVisitaTipo> getTipoTramitesARealizar()
	{
		return getBeanPanelTipoTramitesARealizarVisitas().getTipoTramitesARealizar();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTramitesARealizarVisitasInterface#setTramiteSeleccionado(java.lang.String)
	 */
	@Override
	public void setTramiteSeleccionado(String as_s)
	{
		getBeanPanelTramitesARealizarVisitas().setTramiteSeleccionado(as_s);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTramitesARealizarVisitasInterface#getTramiteSeleccionado()
	 */
	@Override
	public String getTramiteSeleccionado()
	{
		return getBeanPanelTramitesARealizarVisitas().getTramiteSeleccionado();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTramitesARealizarVisitasInterface#setTramitesARealizar(java.util.Collection)
	 */
	@Override
	public void setTramitesARealizar(Collection<TramiteVisita> acs_cs)
	{
		getBeanPanelTramitesARealizarVisitas().setTramitesARealizar(acs_cs);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTramitesARealizarVisitasInterface#getTramitesARealizar()
	 */
	@Override
	public Collection<TramiteVisita> getTramitesARealizar()
	{
		return getBeanPanelTramitesARealizarVisitas().getTramitesARealizar();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#setTurno(com.bachue.snr.prosnr01.model.sdb.acc.Turno)
	 */
	@Override
	public void setTurno(Turno at_t)
	{
		getBeanHeaderDatosDelTurno().setTurno(at_t);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#getTurno()
	 */
	@Override
	public Turno getTurno()
	    throws B2BException
	{
		return getBeanHeaderDatosDelTurno().getTurno();
	}

	/**
	 * @param Modifica el valor de la propiedad iu_usuarioSeleccionado por iu_usuarioSeleccionado
	 */
	public void setUsuarioSeleccionado(Usuario au_usuarioSeleccionado)
	{
		iu_usuarioSeleccionado = au_usuarioSeleccionado;
	}

	/**
	 * @return Retorna el valor de la propiedad iu_usuarioSeleccionado
	 */
	public Usuario getUsuarioSeleccionado()
	{
		return iu_usuarioSeleccionado;
	}

	/**
	 * @param Modifica el valor de la propiedad icu_usuarios por icu_usuarios
	 */
	public void setUsuarios(Collection<Usuario> acu_acu)
	{
		icu_usuarios = acu_acu;
	}

	/**
	 * @return Retorna el valor de la propiedad icu_usuarios
	 */
	public Collection<Usuario> getUsuarios()
	{
		return icu_usuarios;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#setVisitador(boolean)
	 */
	@Override
	public void setVisitador(boolean ab_b)
	{
		getBeanPanelTipoTramitesARealizarVisitas().setVisitador(ab_b);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.PanelTipoTramitesARealizarVisitasInterface#isVisitador()
	 */
	@Override
	public boolean isVisitador()
	{
		return getBeanPanelTipoTramitesARealizarVisitas().isVisitador();
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
	 * Cargar datos ejecucion visitas.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDatosEjecucionVisitas()
	    throws B2BException
	{
		EjecucionVisitas lev_infoVisita;

		lev_infoVisita = ivr_remote.cargarDatosEjecucionVisitas(getIdSolicitud(), getUserId());

		if(lev_infoVisita != null)
		{
			setDatosPanelSolicitudVisitas(lev_infoVisita.getInformacionSolicitudVisita());
			setTramitesARealizar(lev_infoVisita.getTramitesARealizar());
			setIdSubProceso(lev_infoVisita.getIdSubProceso());

			{
				Map<String, Boolean> lmsb_permisosRoles;

				lmsb_permisosRoles = lev_infoVisita.getPermisosRoles();

				if(CollectionUtils.isValidCollection(lmsb_permisosRoles))
				{
					setLiderVigilanciaControl(
					    BooleanUtils.getBooleanValue(
					        lmsb_permisosRoles.get(RolCommon.CB_ROL_LIDER_VIGILANCIA_CONTROL_TXT)
					    )
					);
					setDelegadoRegistro(
					    BooleanUtils.getBooleanValue(lmsb_permisosRoles.get(RolCommon.CB_ROL_DELEGADO_REGISTRO_TXT))
					);
					setVisitador(BooleanUtils.getBooleanValue(lmsb_permisosRoles.get(RolCommon.CB_ROL_VISITADOR_TXT)));
					setResponsableSeguimientoVisita(
					    BooleanUtils.getBooleanValue(
					        lmsb_permisosRoles.get(RolCommon.CB_ROL_RESPONSABLE_SEGUIMIENTO_VISITAS_TXT)
					    )
					);
				}
			}
		}
	}

	/**
	 * Cargar datos panel tipo tramites A realizar.
	 */
	public void cargarDatosPanelTipoTramitesARealizar()
	{
		try
		{
			setTipoTramitesARealizar(
			    ivr_remote.cargarDatosPanelTipoTramitesARealizar(getTramiteSeleccionado(), calcularIdRol())
			);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosPanelTipoTramitesARealizar", lb2be_e);
			addMessage(lb2be_e);
		}
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		ibhddt_bean      = null;
		ibpsv_bean       = null;
		ibptarv_bean     = null;
		setLiderVigilanciaControl(false);
		setDelegadoRegistro(false);
		setVisitador(false);
		setResponsableSeguimientoVisita(false);
		setRolTienePermiso(false);
		setTramiteSeleccionado(null);
		setTipoTramiteSeleccionado(null);
		setIdConstante(null);
		setIdSolicitudVisitas(null);
		setFechaInicial(null);
		setFechaFinal(null);
		cleanProyeccionDocumento();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#cleanProyeccionDocumento()
	 */
	@Override
	public void cleanProyeccionDocumento()
	{
		getBeanProyeccionDocumento().cleanProyeccionDocumento();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#consultaSGD()
	 */
	@Override
	public String consultaSGD()
	{
		return getBeanHeaderDatosDelTurno().consultaSGD();
	}

	public void consultarAllUsuarios()
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

		setAllUsuarios(lcu_usuario);
	}

	/**
	 * Consultar usuarios reasignacion.
	 */
	public void consultarUsuariosReasignacion()
	{
		Collection<Usuario> lcu_usuarios;

		lcu_usuarios = new LinkedList<Usuario>();

		try
		{
			lcu_usuarios = ivr_remote.consultarUsuariosReasignacion(
				    getIdSolicitud(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
				);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			clh_LOGGER.error("consultarUsuarios", lb2be_e);
		}

		setUsuarios(lcu_usuarios);
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.HeaderDatosDelTurnoInterface#dirigirTrazabilidad()
	 */
	@Override
	public String dirigirTrazabilidad()
	{
		return getBeanHeaderDatosDelTurno().dirigirTrazabilidad();
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#generarDocumentos(byte[])
	 */
	@Override
	public void generarDocumentos()
	{
		try
		{
			Collection<Usuario> lcu_usuariosReasignacion;
			int                 li_contadorVP;

			li_contadorVP = 0;

			if(ib_procesoReasignacion)
			{
				lcu_usuariosReasignacion = getUsuarios();

				for(Usuario liu_iterador : lcu_usuariosReasignacion)
				{
					if(!liu_iterador.isSeleccionado())
						throw new B2BException("Debe seleccionar al menos un usuario para reasignar.");

					if(liu_iterador.isVisitadorPrincipal())
					{
						li_contadorVP++;

						if(li_contadorVP > 1)
							throw new B2BException("Solo puede haber un visitador principal por proceso.");
					}
					else
						throw new B2BException("Debe haber al menos un visitador principal.");

					if(
					    !StringUtils.isValidString(liu_iterador.getIdUsuarioReasignacion())
						    || (liu_iterador.getIdUsuarioReasignacion() == null)
					)
						throw new B2BException("Debe seleccionar el nombre del usuario para reasignar..");
				}
			}

			getBeanProyeccionDocumento().generarDocumentos();
			llenarSolucionTags();

			{
				String       ls_tramiteVisita;
				String       ls_tramiteVisitaTipo;
				boolean      lb_documentoConFechas;
				OficiosTexto lof_solucionTags;

				ls_tramiteVisita          = getTramiteSeleccionado();
				ls_tramiteVisitaTipo      = getTipoTramiteSeleccionado();
				lb_documentoConFechas     = false;
				lof_solucionTags          = getSolucionTags();

				if(StringUtils.isValidString(ls_tramiteVisita) && StringUtils.isValidString(ls_tramiteVisitaTipo))
					lb_documentoConFechas = ((ls_tramiteVisita.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_1)
							&& (ls_tramiteVisitaTipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
							|| ls_tramiteVisitaTipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_3)))
							|| (ls_tramiteVisita.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_2)
							&& ls_tramiteVisitaTipo.equalsIgnoreCase(ProcesoCommon.ID_PROCESO_10)));

				if(lb_documentoConFechas && (lof_solucionTags != null))
				{
					Date ld_fechaInicial;
					Date ld_fechaFinal;

					ld_fechaInicial     = getFechaInicial();
					ld_fechaFinal       = getFechaFinal();

					validarFechaDesdeFechaHasta(
					    ld_fechaInicial, ld_fechaFinal, IdentificadoresCommon.DATO_VALIDO,
					    IdentificadoresCommon.DATO_VALIDO, FacesContext.getCurrentInstance(), false, false
					);

					if(ld_fechaFinal == null)
						throw new B2BException(ErrorKeys.ERROR_SIN_FECHA_HASTA);

					lof_solucionTags.setFechaInicialPantalla(getFechaInicial());
					lof_solucionTags.setFechaFinalPantalla(getFechaFinal());
				}

				{
					DetalleEjecucionVisitas ldev_detalleEjecucionVisitas;

					ldev_detalleEjecucionVisitas = ivr_remote.generarDocumentos(
						    getIdConstante(), getIdSolicitudVisitas(), lof_solucionTags, getUserId(),
						    getLocalIpAddress(), getRemoteIpAddress()
						);

					if(ldev_detalleEjecucionVisitas != null)
					{
						setDocumentoArray(ldev_detalleEjecucionVisitas.getDocumento());
						setMostrarBotonSalvarProyeccionDocumento(true);
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
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#ocultarBotonSalvarProyeccionDocumento()
	 */
	public void ocultarBotonSalvarProyeccionDocumento()
	{
		getBeanProyeccionDocumento().ocultarBotonSalvarProyeccionDocumento();
	}

	/**
	 * Regresar.
	 *
	 * @return el valor de string
	 */
	public String regresar()
	{
		return NavegacionCommon.APROBACION;
	}

	/* (non-Javadoc)
	 * @see com.bachue.snr.prosnr01.web.bean.reusableScreens.ProyeccionDocumentoInterface#regresarProyeccionDocumento()
	 */
	@Override
	public String regresarProyeccionDocumento()
	{
		cleanProyeccionDocumento();

		return NavegacionCommon.DETALLE_EJECUCION_VISITAS;
	}

	/**
	 * Salvar ejecucion visitas.
	 */
	public String salvarEjecucionVisitas()
	{
		String ls_navigationRule;

		ls_navigationRule = NavegacionCommon.EJECUCION_VISITAS;

		try
		{
			ivr_remote.salvarEjecucionVisitas(
			    isProcesoReasignacion(), getUsuarios(), getIdConstante(), getIdSolicitudVisitas(), getDocumentoArray(),
			    getSolucionTags(), getUserId(), getLocalIpAddress(), getRemoteIpAddress()
			);

			{
				FacesContext   lfc_context;
				BeanAprobacion lb_beanAprobacion;

				lfc_context           = FacesUtils.getFacesContext();
				lb_beanAprobacion     = (BeanAprobacion)lfc_context.getApplication()
						                                               .evaluateExpressionGet(
						    lfc_context, BeanNameCommon.BEAN_APROBACION, BeanAprobacion.class
						);

				if(lb_beanAprobacion != null)
				{
					lb_beanAprobacion.clean();
					lb_beanAprobacion.limpiarBanderaProcesos();
					lb_beanAprobacion.setVieneDeEjecucionVisitas(true);
					lb_beanAprobacion.findDetalleAprobacion();
					lb_beanAprobacion.setMotivoTramiteSeleccionado(null);
					lb_beanAprobacion.setIdProcesoSeleccionado(null);
					lb_beanAprobacion.generarGraficoDeTorta(EtapaCommon.ID_ETAPA_830, false);
					lb_beanAprobacion.setVieneDeEjecucionVisitas(true);
				}

				ls_navigationRule = NavegacionCommon.BANDEJA_ENTRADA;
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		clean();

		return ls_navigationRule;
	}

	/**
	 * Siguiente.
	 *
	 * @return el valor de string
	 */
	public String siguiente()
	{
		try
		{
			String ls_tipoTramiteSeleccionado;

			ls_tipoTramiteSeleccionado = getTipoTramiteSeleccionado();

			if(StringUtils.isValidString(ls_tipoTramiteSeleccionado))
			{
				DetalleEjecucionVisitas ldev_detalleEjecucionVisitas;

				ldev_detalleEjecucionVisitas = ivr_remote.cargarDetalleEjecucionVisitas(
					    ls_tipoTramiteSeleccionado, getIdSolicitud(), getUserId(), getLocalIpAddress(),
					    getRemoteIpAddress()
					);

				if(ldev_detalleEjecucionVisitas != null)
				{
					setFechaInicial(null);
					setFechaFinal(null);
					setCamposPantalla(ldev_detalleEjecucionVisitas.getCamposPantalla());
					setSolucionTags(ldev_detalleEjecucionVisitas.getSolucionTags());
					setDocumentoArray(ldev_detalleEjecucionVisitas.getDocumento());
					setIdConstante(ldev_detalleEjecucionVisitas.getIdConstante());
					setIdSolicitudVisitas(ldev_detalleEjecucionVisitas.getIdSolicitudVisitas());
					setProcesoReasignacion(false);
				}

				if(
				    ls_tipoTramiteSeleccionado.equals(TramiteCommon.AUTO)
					    && getTramiteSeleccionado().equals(TramiteCommon.AUTO)
				)
					setProcesoReasignacion(true);
			}

			if(ib_procesoReasignacion)
			{
				consultarUsuariosReasignacion();
				consultarAllUsuarios();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		return NavegacionCommon.PROYECCION_DOCUMENTO;
	}

	/**
	 * Validar datos reasignacion.
	 */
	public void validarDatosReasignacion()
	{
		Collection<Usuario> lcu_usuariosReasignacion;

		try
		{
			lcu_usuariosReasignacion = getUsuarios();

			for(Usuario liu_iterador : lcu_usuariosReasignacion)
			{
				if(!liu_iterador.isSeleccionado())
					throw new B2BException("Debe seleccionar al menos un usuario para reasignar.");
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	/**
	 * Validar permisos rol.
	 */
	public void validarPermisosRol()
	{
		try
		{
			String ls_tramiteSeleccionado;

			ls_tramiteSeleccionado = getTramiteSeleccionado();

			if(StringUtils.isValidString(ls_tramiteSeleccionado))
			{
				String ls_idSubProceso;

				ls_idSubProceso = getIdSubProceso();

				if(StringUtils.isValidString(ls_idSubProceso))
				{
					switch(ls_tramiteSeleccionado)
					{
						case TramiteCommon.AUTO:

							if(isDelegadoRegistro() || isLiderVigilanciaControl() || isVisitador())
							{
								if(
								    ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
									    || ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
								)
									setRolTienePermiso(true);
								else
								{
									setTramiteSeleccionado(null);
									throw new B2BException(
									    "El trámite seleccionado no está permitido para este proceso."
									);
								}
							}
							else
							{
								setTramiteSeleccionado(null);
								throw new B2BException("El usuario no posee roles válidos para este proceso.");
							}

							break;

						case TramiteCommon.RESOLUCIONES:

							if(
							    isDelegadoRegistro() || isLiderVigilanciaControl() || isVisitador()
								    || isResponsableSeguimientoVisita()
							)
							{
								if(
								    ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
									    || ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4)
									    || ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_5)
								)
									setRolTienePermiso(true);
								else
								{
									setTramiteSeleccionado(null);
									throw new B2BException(
									    "El trámite seleccionado no está permitido para este proceso."
									);
								}
							}
							else
							{
								setTramiteSeleccionado(null);
								throw new B2BException("El usuario no posee roles válidos para este proceso.");
							}

							break;

						case TramiteCommon.INFORMES:

							if(isDelegadoRegistro() || isLiderVigilanciaControl() || isVisitador())
							{
								if(
								    ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_1)
									    || ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_2)
									    || ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_3)
									    || ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_4)
									    || ls_idSubProceso.equalsIgnoreCase(ProcesoCommon.ID_SUBPROCESO_5)
								)
									setRolTienePermiso(true);
								else
								{
									setTramiteSeleccionado(null);
									throw new B2BException(
									    "El trámite seleccionado no está permitido para este proceso."
									);
								}
							}
							else
							{
								setTramiteSeleccionado(null);
								throw new B2BException("El usuario no posee roles válidos para este proceso.");
							}

							break;

						default:
							break;
					}

					cargarDatosPanelTipoTramitesARealizar();
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
	 * Retorna Objeto o variable de valor bean header datos del turno.
	 *
	 * @return el valor de bean header datos del turno
	 */
	private BeanHeaderDatosDelTurno getBeanHeaderDatosDelTurno()
	{
		try
		{
			if(ibhddt_bean == null)
				ibhddt_bean = (BeanHeaderDatosDelTurno)FacesUtils.obtenerInstanciaBean(
					    BeanHeaderDatosDelTurno.class, BeanNameCommon.BEAN_HEADER_DATOS_DEL_TURNO
					);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("getBeanHeaderDatosDelTurno", lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		return ibhddt_bean;
	}

	/**
	 * Retorna Objeto o variable de valor bean panel solicitud visitas.
	 *
	 * @return el valor de bean panel solicitud visitas
	 */
	private BeanPanelSolicitudVisitas getBeanPanelSolicitudVisitas()
	{
		try
		{
			if(ibpsv_bean == null)
				ibpsv_bean = (BeanPanelSolicitudVisitas)FacesUtils.obtenerInstanciaBean(
					    BeanPanelSolicitudVisitas.class, BeanNameCommon.BEAN_PANEL_SOLICITUID_VISITAS
					);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("getBeanHeaderDatosDelTurno", lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		return ibpsv_bean;
	}

	/**
	 * Retorna Objeto o variable de valor bean panel tipo tramites A realizar visitas.
	 *
	 * @return el valor de bean panel tipo tramites A realizar visitas
	 */
	private BeanPanelTipoTramitesARealizarVisitas getBeanPanelTipoTramitesARealizarVisitas()
	{
		try
		{
			if(ipttarv_bean == null)
				ipttarv_bean = (BeanPanelTipoTramitesARealizarVisitas)FacesUtils.obtenerInstanciaBean(
					    BeanPanelTipoTramitesARealizarVisitas.class, BeanNameCommon.BEAN_PANEL_TIPO_TRAMITES_A_REALIZAR
					);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("getBeanPanelTipoTramitesARealizarVisitas", lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		return ipttarv_bean;
	}

	/**
	 * Retorna Objeto o variable de valor bean panel tramites A realizar visitas.
	 *
	 * @return el valor de bean panel tramites A realizar visitas
	 */
	private BeanPanelTramitesARealizarVisitas getBeanPanelTramitesARealizarVisitas()
	{
		try
		{
			if(ibptarv_bean == null)
				ibptarv_bean = (BeanPanelTramitesARealizarVisitas)FacesUtils.obtenerInstanciaBean(
					    BeanPanelTramitesARealizarVisitas.class, BeanNameCommon.BEAN_PANEL_TRAMITES_A_REALIZAR
					);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("getBeanHeaderDatosDelTurno", lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		return ibptarv_bean;
	}

	/**
	 * Retorna Objeto o variable de valor bean proyeccion documento.
	 *
	 * @return el valor de bean proyeccion documento
	 */
	private BeanProyeccionDocumento getBeanProyeccionDocumento()
	{
		try
		{
			if(ibpd_bean == null)
				ibpd_bean = (BeanProyeccionDocumento)FacesUtils.obtenerInstanciaBean(
					    BeanProyeccionDocumento.class, BeanNameCommon.BEAN_PROYECCION_DOCUMENTO
					);
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);

			clh_LOGGER.error("getBeanProyeccionDocumento", lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		return ibpd_bean;
	}

	/**
	* Calcular id rol.
	*
	* @return el valor de string
	*/
	private String calcularIdRol()
	{
		String ls_idRol;

		ls_idRol = null;

		if(isLiderVigilanciaControl())
			ls_idRol = RolCommon.CB_ROL_LIDER_VIGILANCIA_CONTROL;
		else if(isDelegadoRegistro())
			ls_idRol = RolCommon.CB_ROL_DELEGADO_REGISTRO;
		else if(isVisitador())
			ls_idRol = RolCommon.CB_ROL_VISITADOR;
		else if(isResponsableSeguimientoVisita())
			ls_idRol = RolCommon.CB_ROL_RESPONSABLE_SEGUIMIENTO_VISITAS;

		return ls_idRol;
	}

	/**
	 * Llenar solucion tags.
	 */
	private void llenarSolucionTags()
	{
		try
		{
			Collection<TagPlantillaResolucion> lctpr_camposPantalla;

			lctpr_camposPantalla = getCamposPantalla();

			if(CollectionUtils.isValidCollection(lctpr_camposPantalla))
			{
				OficiosTexto lot_solucionTags;

				lot_solucionTags = getSolucionTags();

				if(lot_solucionTags == null)
					lot_solucionTags = new OficiosTexto();

				for(TagPlantillaResolucion ltpr_iterador : lctpr_camposPantalla)
				{
					if(ltpr_iterador != null)
					{
						String ls_ubicacionSalvado;
						String ls_texto;

						ls_ubicacionSalvado     = ltpr_iterador.getIdUbicacionSalvado();
						ls_texto                = ltpr_iterador.getTexto();

						if(StringUtils.isValidString(ls_ubicacionSalvado) && StringUtils.isValidString(ls_texto))
						{
							ls_texto = convertirTextoParaRtf(ls_texto);

							switch(ls_ubicacionSalvado)
							{
								case "1":
									lot_solucionTags.setAnalisisProbatorio(ls_texto);

									break;

								case "2":
									lot_solucionTags.setAntecedentes(ls_texto);

									break;

								case "3":
									lot_solucionTags.setArgumentosRecurrente(ls_texto);

									break;

								case "4":
									lot_solucionTags.setArticulo(ls_texto);

									break;

								case "5":
									lot_solucionTags.setConsideracion(ls_texto);

									break;

								case "6":
									lot_solucionTags.setEncabezado(ls_texto);

									break;

								case "7":
									lot_solucionTags.setIntervencionInteresados(ls_texto);

									break;

								case "8":
									lot_solucionTags.setPertinencia(ls_texto);

									break;

								case "9":
									lot_solucionTags.setPruebasRecaudadas(ls_texto);

									break;

								case "10":
									lot_solucionTags.setRazon1(ls_texto);

									break;

								case "11":
									lot_solucionTags.setRazon2(ls_texto);

									break;

								case "12":
									lot_solucionTags.setResuelve(ls_texto);

									break;

								default:
									break;
							}
						}
					}
				}

				setSolucionTags(lot_solucionTags);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
	}
}
