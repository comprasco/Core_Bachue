package com.bachue.snr.prosnr04.web.services;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.messages.Messages;

import com.b2bsg.common.web.axis.RequestInfo;
import com.b2bsg.common.web.axis.RequestUtil;

import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;

import com.bachue.snr.prosnr03.ejb.session.stateless.gestionUsuarios.GestionUsuariosRemote;

import com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras.NotificacionDePagosRemote;
import com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras.OperacionesFinancierasRemote;

import com.bachue.snr.prosnr05.ejb.session.stateless.consulta.catalogos.ConsultaCatalogosRemote;

import com.bachue.snr.prosnr05.web.services.delegate.ConsultaCatalogosDelegado;

import com.bachue.snr.prosnr06.ejb.session.stateless.consulta.trazabilidad.ConsultaTrazabilidadRemote;

import com.bachue.snr.prosnr07.ejb.session.stateless.consulta.historial.solicitudes.pagadas.ConsultaHistorialSolicitudesPagadasRemote;

import com.bachue.snr.prosnr07.web.services.delegate.consulta.historial.solicitudes.pagadas.ConsultaHistorialSolicitudesPagadasDelegado;

import com.bachue.snr.prosnr08.ejb.session.stateless.entrega.producto.EntregaProductoRemote;

import com.bachue.snr.prosnr09.ejb.session.stateless.gestion.alertas.titulares.GestionAlertasTitularesRemote;

import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.CambioLinderosPredioRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.CambioPropietarioRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.MutacionesCuartoOrdenRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.MutacionesQuintoOrdenRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.MutacionesTercerOrdenRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.NuevasMatriculasRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.PartesInteresadasRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.RRRMatriculasRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.RegistrarReintentoServicioRemote;
import com.bachue.snr.prosnr10.ejb.session.stateless.catastro.SalvedadesRemote;

import com.bachue.snr.prosnr10.web.services.delegate.CambioLinderosPredioDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.CambioPropietarioDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.MutacionesCuartoOrdenDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.MutacionesQuintoOrdenDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.MutacionesTercerOrdenDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.NuevasMatriculasDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.PartesInteresadasDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.RRRMatriculasDelegado;
import com.bachue.snr.prosnr10.web.services.delegate.SalvedadesDelegado;

import com.bachue.snr.prosnr11.ejb.session.stateless.controlDigitalizacion.ControlDigitalizacionRemote;

import com.bachue.snr.prosnr12.ejb.session.stateless.gestionCuentaCupos.GestionCuentaCuposRemote;

import com.bachue.snr.prosnr13.ejb.session.stateless.generacionSolicitud.GeneracionSolicitudRemote;

import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.AnotacionesRemote;
import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.CertificadoTradicionPDFRemote;
import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.ConsultaMigracionRemote;
import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.HistoricoPropietariosRemote;
import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.IndicePropietariosRemote;
import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.InmueblesRemote;
import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.MatriculasRelacionadasRemote;
import com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.TramitesEnCursoRemote;

import com.bachue.snr.prosnr14.web.services.delegate.AnotacionesDelegado;
import com.bachue.snr.prosnr14.web.services.delegate.CertificadoTradicionPDFDelegado;
import com.bachue.snr.prosnr14.web.services.delegate.ConsultaMigracionDelegado;
import com.bachue.snr.prosnr14.web.services.delegate.IndicePropietariosDelegado;
import com.bachue.snr.prosnr14.web.services.delegate.InmueblesDelegado;
import com.bachue.snr.prosnr14.web.services.delegate.MatriculasRelacionadasDelegado;
import com.bachue.snr.prosnr14.web.services.delegate.TramitesEnCursoDelegado;

import com.bachue.snr.prosnr15.ejb.session.stateless.consulta.datos.registrales.ConsultaDatosRegistralesRemote;

import com.bachue.snr.prosnr15.web.services.delegate.consulta.datos.registrales.ConsultaDatosRegistralesDelegado;

import com.bachue.snr.prosnr16.ejb.session.stateless.cyn.MotorRecepcionRemote;
import com.bachue.snr.prosnr16.ejb.session.stateless.cyn.NotificadorCorrespondenciaRemote;

import com.bachue.snr.prosnr16.web.services.delegate.MotorRecepcionDelegado;
import com.bachue.snr.prosnr16.web.services.delegate.NotificadorCorrespondenciaDelegado;

import com.bachue.snr.prosnr17.ejb.session.stateless.solicitud.correccion.SolicitudCorreccionRemote;

import com.bachue.snr.prosnr17.web.services.delegate.solicitud.correccion.SolicitudCorreccionDelegado;

import com.bachue.snr.prosnr18.ejb.session.stateless.solicitud.copias.SolicitudCopiasRemote;

import com.bachue.snr.prosnr18.web.services.delegate.solicitud.copias.SolicitudCopiasDelegado;

import com.bachue.snr.prosnr19.ejb.session.stateless.alertaTierras.AlertaTierrasRemote;

import com.bachue.snr.prosnr19.web.services.delegate.AlertaTierrasDelegado;

import com.bachue.snr.prosnr20.ejb.session.stateless.cyn.NotificadorRemote;

import com.bachue.snr.prosnr20.web.services.delegate.NotificadorDelegado;

import com.bachue.snr.prosnr22.ejb.session.stateless.notificarDigitalizacionContent.NotificarDigitalizacionContentRemote;

import org.apache.axis.MessageContext;

import java.io.Serializable;


/**
 * Clase que contiene todos las propiedades BaseServices.
 *
 * @author  Julian Vaca
 * Fecha de Creacion: 12/09/2019
 */
public class BaseServices implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 6980919287601282824L;

	/** Constante CONFIG_MESSAGES. */
	public static final String CONFIG_MESSAGES = "com.bachue.snr.prosnr01.web.bean.resources.Messages";

	/** Constante CONFIG_ERRORS. */
	public static final String CONFIG_ERRORS = "com.bachue.snr.prosnr01.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_EP. */
	public static final String CONFIG_ERRORS_EP = "com.bachue.snr.prosnr08.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_CD. */
	public static final String CONFIG_ERRORS_CD = "com.bachue.snr.prosnr11.web.bean.resources.Errors";

	/** Constante CONFIG_ERRORS_CA. */
	public static final String CONFIG_ERRORS_CA = "com.bachue.snr.prosnr3.web.bean.resources.Errors";

	/** Constante USER_ID. */
	public static final String USER_ID = ConstanteCommon.USUARIO_PROCESOS_AUTOMATICOS;

	/** Propiedad im messages. */
	private static Messages im_messages;

	/** Propiedad iri request info. */
	private static RequestInfo iri_requestInfo;

	/** Propiedad iscxr remote. */
	private com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.SalvedadesRemote iscxr_remote;

	/** Propiedad iatr remote. */
	private AlertaTierrasRemote iatr_remote;

	/** Propiedad iar remote. */
	private AnotacionesRemote iar_remote;

	/** Propiedad iclpr remote. */
	private CambioLinderosPredioRemote iclpr_remote;

	/** Propiedad icpr remote. */
	private CambioPropietarioRemote icpr_remote;

	/** Propiedad ictpdfr remote. */
	private CertificadoTradicionPDFRemote ictpdfr_remote;

	/** Propiedad iccr remote. */
	private ConsultaCatalogosRemote iccr_remote;

	/** Propiedad icdr remote. */
	private ConsultaDatosRegistralesRemote icdr_remote;

	/** Propiedad ichspr remote. */
	private ConsultaHistorialSolicitudesPagadasRemote ichspr_remote;

	/** Propiedad icmr remote. */
	private ConsultaMigracionRemote icmr_remote;

	/** Propiedad ictr remote. */
	private ConsultaTrazabilidadRemote ictr_remote;

	/** Propiedad icd remote. */
	private ControlDigitalizacionRemote icd_remote;

	/** Propiedad iep remote. */
	private EntregaProductoRemote iep_remote;

	/** Propiedad igsr remote. */
	private GeneracionSolicitudRemote igsr_remote;

	/** Propiedad igatr remote. */
	private GestionAlertasTitularesRemote igatr_remote;

	/** Propiedad igccr remote. */
	private GestionCuentaCuposRemote igccr_remote;

	/** Propiedad igu remote. */
	private GestionUsuariosRemote igu_remote;

	/** Propiedad ihpr remote. */
	private HistoricoPropietariosRemote ihpr_remote;

	/** Propiedad ipr remote. */
	private IndicePropietariosRemote ipr_remote;

	/** Propiedad iir remote. */
	private InmueblesRemote iir_remote;

	/** Propiedad imrr remote. */
	private MatriculasRelacionadasRemote imrr_remote;

	/** Propiedad imr remote. */
	private MotorRecepcionRemote imr_remote;

	/** Propiedad imco remote. */
	private MutacionesCuartoOrdenRemote imco_remote;

	/** Propiedad imqo remote. */
	private MutacionesQuintoOrdenRemote imqo_remote;

	/** Propiedad imto remote. */
	private MutacionesTercerOrdenRemote imto_remote;

	/** Propiedad inp remote. */
	private NotificacionDePagosRemote inp_remote;

	/** Propiedad incr remote. */
	private NotificadorCorrespondenciaRemote incr_remote;

	/** Propiedad inr remote. */
	private NotificadorRemote inr_remote;

	/** Propiedad icd remote. */
	private NotificarDigitalizacionContentRemote indcr_remote;

	/** Propiedad inm remote. */
	private NuevasMatriculasRemote inm_remote;

	/** Propiedad iof remote. */
	private OperacionesFinancierasRemote iof_remote;

	/** Propiedad ipi remote. */
	private PartesInteresadasRemote ipi_remote;

	/** Propiedad irrrmr remote. */
	private RRRMatriculasRemote irrrmr_remote;

	/** Propiedad irrs remote. */
	private RegistrarReintentoServicioRemote irrs_remote;

	/** Propiedad is remote. */
	private SalvedadesRemote isr_remote;

	/** Propiedad isdcr remote. */
	private SolicitudCopiasRemote isdcr_remote;

	/** Propiedad iscr remote. */
	private SolicitudCorreccionRemote iscr_remote;

	/** Propiedad itecr remote. */
	private TramitesEnCursoRemote itecr_remote;

	/** Instancia un nuevo objeto base services. */
	public BaseServices()
	{
	}

	/**
	 * Método de obtencion del Error Key
	 *
	 * @param ab2be_e de ab 2 be e
	 * @return el valor de string
	 */
	public String getErrorKey(B2BException ab2be_e)
	{
		return getErrorKey(ab2be_e, CONFIG_ERRORS);
	}

	/**
	 *  Método de obtencion del Error Key
	 *
	 * @param ab2be_e de ab 2 be e
	 * @param as_configError de as config error
	 * @return el valor de string
	 */
	public String getErrorKey(B2BException ab2be_e, String as_configError)
	{
		String ls_error;

		ls_error = null;

		if(ab2be_e != null)
		{
			B2BException.setBundle(as_configError);
			ls_error = ab2be_e.getMessage();
		}

		return ls_error;
	}

	/**
	 * Retorna Objeto o variable de valor error key CA.
	 *
	 * @param ab2be_e de ab 2 be e
	 * @return el valor de error key CA
	 */
	public String getErrorKeyCA(B2BException ab2be_e)
	{
		return getErrorKey(ab2be_e, CONFIG_ERRORS_CA);
	}

	/**
	 *  Método de obtencion del Error Key CD
	 *
	 * @param ab2be_e de ab 2 be e
	 * @return el valor de string
	 */
	public String getErrorKeyCD(B2BException ab2be_e)
	{
		return getErrorKey(ab2be_e, CONFIG_ERRORS_CD);
	}

	/**
	 *  Método de obtencion del Error Key EP.
	 *
	 * @param ab2be_e de ab 2 be e
	 * @return el valor de string
	 */
	public String getErrorKeyEP(B2BException ab2be_e)
	{
		return getErrorKey(ab2be_e, CONFIG_ERRORS_EP);
	}

	/**
	 *  Método de obtencion del ip address.
	 *
	 * @return el valor de string
	 */
	public String getLocalIpAddress()
	{
		RequestInfo lri_info;

		lri_info = getRequestInfo();

		return (lri_info != null) ? lri_info.getLocalIp() : null;
	}

	/**
	 *  Método de obtencion del Message Key.
	 *
	 * @param as_messageKey de as message key
	 * @return el valor de string
	 */
	public String getMessageKey(String as_messageKey)
	{
		return getMessages().getString(as_messageKey);
	}

	/**
	 *  Método de obtencion de Messages.
	 *
	 * @return el valor de messages
	 */
	public static Messages getMessages()
	{
		if(im_messages == null)
			im_messages = new Messages(CONFIG_MESSAGES);

		return im_messages;
	}

	/**
	 *  Método de obtencion de la ip address.
	 *
	 * @return el valor de string
	 */
	public String getRemoteIpAddress()
	{
		RequestInfo lri_info;

		lri_info = getRequestInfo();

		return (lri_info != null) ? lri_info.getRemoteIp() : null;
	}

	/**
	 *  Método de obtencion del request info.
	 *
	 * @return el valor de request info
	 */
	public static RequestInfo getRequestInfo()
	{
		if(iri_requestInfo == null)
			iri_requestInfo = RequestUtil.getRequestInfo(MessageContext.getCurrentContext());

		return iri_requestInfo;
	}

	/**
	 *  Método de obtencion del user id.
	 *
	 * @return el valor de string
	 */
	public String getUserId()
	{
		return USER_ID;
	}

	/**
	 * Retorna Objeto o variable de valor alerta tierras remote.
	 *
	 * @return el valor de alerta tierras remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected AlertaTierrasRemote getAlertaTierrasRemote()
	    throws B2BException
	{
		if(iatr_remote == null)
			iatr_remote = new AlertaTierrasDelegado().getRemote();

		return iatr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor anotaciones remote.
	 *
	 * @return el valor de anotaciones remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected AnotacionesRemote getAnotacionesRemote()
	    throws B2BException
	{
		if(iar_remote == null)
			iar_remote = new AnotacionesDelegado().getRemote();

		return iar_remote;
	}

	/**
	 * Retorna Objeto o variable de valor cambio linderos predio remote.
	 *
	 * @return el valor de cambio linderos predio remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected CambioLinderosPredioRemote getCambioLinderosPredioRemote()
	    throws B2BException
	{
		if(iclpr_remote == null)
			iclpr_remote = new CambioLinderosPredioDelegado().getRemote();

		return iclpr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor cambio propietario remote.
	 *
	 * @return el valor de cambio propietario remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected CambioPropietarioRemote getCambioPropietarioRemote()
	    throws B2BException
	{
		if(icpr_remote == null)
			icpr_remote = new CambioPropietarioDelegado().getRemote();

		return icpr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor certificado tradicion PDF remote.
	 *
	 * @return el valor de certificado tradicion PDF remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected CertificadoTradicionPDFRemote getCertificadoTradicionPDFRemote()
	    throws B2BException
	{
		if(ictpdfr_remote == null)
			ictpdfr_remote = new CertificadoTradicionPDFDelegado().getRemote();

		return ictpdfr_remote;
	}

	/**
	 *  Método de obtencion de consulta catalogos remote.
	 *
	 * @return el valor de consulta catalogos remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected ConsultaCatalogosRemote getConsultaCatalogosRemote()
	    throws B2BException
	{
		if(iccr_remote == null)
			iccr_remote = new ConsultaCatalogosDelegado().getRemote();

		return iccr_remote;
	}

	/**
	 *  Método de obtencion de consulta datos registrales remote.
	 *
	 * @return el valor de consulta datos registrales remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected ConsultaDatosRegistralesRemote getConsultaDatosRegistralesRemote()
	    throws B2BException
	{
		if(icdr_remote == null)
			icdr_remote = new ConsultaDatosRegistralesDelegado().getRemote();

		return icdr_remote;
	}

	/**
	 *  Método de obtencion de consulta historial solicitudes pagadas remote.
	 *
	 * @return el valor de consulta historial solicitudes pagadas remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected ConsultaHistorialSolicitudesPagadasRemote getConsultaHistorialSolicitudesPagadasRemote()
	    throws B2BException
	{
		if(ichspr_remote == null)
			ichspr_remote = new ConsultaHistorialSolicitudesPagadasDelegado().getRemote();

		return ichspr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor consulta migracion remote.
	 *
	 * @return el valor de consulta migracion remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected ConsultaMigracionRemote getConsultaMigracionRemote()
	    throws B2BException
	{
		if(icmr_remote == null)
			icmr_remote = new ConsultaMigracionDelegado().getRemote();

		return icmr_remote;
	}

	/**
	 * Método de obtencion de control digitalización remote.
	 *
	 * @return el valor de entrega producto remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected ControlDigitalizacionRemote getControlDigitalizacionRemote()
	    throws B2BException
	{
		if(icd_remote == null)
			icd_remote = new com.bachue.snr.prosnr11.web.services.delegate.ControlDigitalizacionDelegado().getRemote();

		return icd_remote;
	}

	/**
	 * Método de obtencion de entrega producto remote.
	 *
	 * @return el valor de entrega producto remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected EntregaProductoRemote getEntregaProductoRemote()
	    throws B2BException
	{
		if(iep_remote == null)
			iep_remote = new com.bachue.snr.prosnr08.web.services.delegate.entrega.producto.EntregarProductoDelegado()
					.getRemote();

		return iep_remote;
	}

	/**
	 * Obtiene la interfaz remota del servicio de Generación Solicitud.
	 *
	 * @return la interfaz remota del servicio de Generación Solicitud.
	 * @throws B2BException Si ocurre un error en la obtención de la interfaz remota
	 */
	protected GeneracionSolicitudRemote getGeneracionSolicitudRemote()
	    throws B2BException
	{
		if(igsr_remote == null)
			igsr_remote = new com.bachue.snr.prosnr13.web.services.delegate.generacionSolicitud.GeneracionSolicitudDelegado()
					.getRemote();

		return igsr_remote;
	}

	/**
	 * Método de obtencion de gestion alertas titulares remote.
	 *
	 * @return el valor de gestion alertas titulares remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected GestionAlertasTitularesRemote getGestionAlertasTitularesRemote()
	    throws B2BException
	{
		if(igatr_remote == null)
			igatr_remote = new com.bachue.snr.prosnr09.web.services.delegate.gestion.alertas.titulares.GestionAlertasTitularesDelegado()
					.getRemote();

		return igatr_remote;
	}

	/**
	 * Obtiene la interfaz remota del servicio de Gestion Cuenta Cupos.
	 *
	 * @return la interfaz remota del servicio de Gestion Cuenta Cupos.
	 * @throws B2BException Si ocurre un error en la obtención de la interfaz remota
	 */
	protected GestionCuentaCuposRemote getGestionCuentaCuposRemote()
	    throws B2BException
	{
		if(igccr_remote == null)
			igccr_remote = new com.bachue.snr.prosnr12.web.services.delegate.gestionCuentaCupos.GestionCuentaCuposDelegado()
					.getRemote();

		return igccr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor gestion usuarios remote.
	 *
	 * @return el valor de gestion usuarios remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected GestionUsuariosRemote getGestionUsuariosRemote()
	    throws B2BException
	{
		if(igu_remote == null)
			igu_remote = new com.bachue.snr.prosnr03.web.services.delegate.GestionUsuariosDelegado().getRemote();

		return igu_remote;
	}

	/**
	 * Retorna Objeto o variable de valor historico propietarios remote.
	 *
	 * @return el valor de historico propietarios remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected HistoricoPropietariosRemote getHistoricoPropietariosRemote()
	    throws B2BException
	{
		if(ihpr_remote == null)
			ihpr_remote = new com.bachue.snr.prosnr14.web.services.delegate.HistoricoPropietariosDelegado().getRemote();

		return ihpr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor indice propietarios remote.
	 *
	 * @return el valor de indice propietarios remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected IndicePropietariosRemote getIndicePropietariosRemote()
	    throws B2BException
	{
		if(ipr_remote == null)
			ipr_remote = new IndicePropietariosDelegado().getRemote();

		return ipr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor inmuebles remote.
	 *
	 * @return el valor de inmuebles remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected InmueblesRemote getInmueblesRemote()
	    throws B2BException
	{
		if(iir_remote == null)
			iir_remote = new InmueblesDelegado().getRemote();

		return iir_remote;
	}

	/**
	 * Retorna Objeto o variable de valor matriculas relacionadas remote.
	 *
	 * @return el valor de matriculas relacionadas remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected MatriculasRelacionadasRemote getMatriculasRelacionadasRemote()
	    throws B2BException
	{
		if(imrr_remote == null)
			imrr_remote = new MatriculasRelacionadasDelegado().getRemote();

		return imrr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor motor recepcion remote.
	 *
	 * @return el valor de motor recepcion remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected MotorRecepcionRemote getMotorRecepcionRemote()
	    throws B2BException
	{
		if(imr_remote == null)
			imr_remote = new MotorRecepcionDelegado().getRemote();

		return imr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor mutaciones cuarto orden remote.
	 *
	 * @return el valor de mutaciones cuarto orden remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected MutacionesCuartoOrdenRemote getMutacionesCuartoOrdenRemote()
	    throws B2BException
	{
		if(imco_remote == null)
			imco_remote = new MutacionesCuartoOrdenDelegado().getRemote();

		return imco_remote;
	}

	/**
	 * Retorna Objeto o variable de valor mutaciones quinto orden remote.
	 *
	 * @return el valor de mutaciones quinto orden remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected MutacionesQuintoOrdenRemote getMutacionesQuintoOrdenRemote()
	    throws B2BException
	{
		if(imqo_remote == null)
			imqo_remote = new MutacionesQuintoOrdenDelegado().getRemote();

		return imqo_remote;
	}

	/**
	 * Retorna Objeto o variable de valor mutaciones tercer orden remote.
	 *
	 * @return el valor de mutaciones tercer orden remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected MutacionesTercerOrdenRemote getMutacionesTercerOrdenRemote()
	    throws B2BException
	{
		if(imto_remote == null)
			imto_remote = new MutacionesTercerOrdenDelegado().getRemote();

		return imto_remote;
	}

	/**
	 * Método de obtencion de notificacion de pagos remote.
	 *
	 * @return el valor de notificacion de pagos remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected NotificacionDePagosRemote getNotificacionDePagosRemote()
	    throws B2BException
	{
		if(inp_remote == null)
			inp_remote = new com.bachue.snr.prosnr04.web.services.delegate.NotificacionDePagosDelegado().getRemote();

		return inp_remote;
	}

	/**
	 * Retorna Objeto o variable de valor notificador correspondencia remote.
	 *
	 * @return el valor de notificador correspondencia remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected NotificadorCorrespondenciaRemote getNotificadorCorrespondenciaRemote()
	    throws B2BException
	{
		if(incr_remote == null)
			incr_remote = new NotificadorCorrespondenciaDelegado().getRemote();

		return incr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor notificador remote.
	 *
	 * @return el valor de notificador remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected NotificadorRemote getNotificadorRemote()
	    throws B2BException
	{
		if(inr_remote == null)
			inr_remote = new NotificadorDelegado().getRemote();

		return inr_remote;
	}

	/**
	 * Método de obtencion de control digitalización remote.
	 *
	 * @return el valor de entrega producto remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected NotificarDigitalizacionContentRemote getNotificarDigitalizacionContentRemote()
	    throws B2BException
	{
		if(indcr_remote == null)
			indcr_remote = new com.bachue.snr.prosnr22.web.services.delegate.NotificarDigitalizacionContentDelegado()
					.getRemote();

		return indcr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor nuevas matriculas remote.
	 *
	 * @return el valor de nuevas matriculas remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected NuevasMatriculasRemote getNuevasMatriculasRemote()
	    throws B2BException
	{
		if(inm_remote == null)
			inm_remote = new NuevasMatriculasDelegado().getRemote();

		return inm_remote;
	}

	/**
	 * Método de obtencion de operaciones financieras remote.
	 *
	 * @return el valor de operaciones financieras remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected OperacionesFinancierasRemote getOperacionesFinancierasRemote()
	    throws B2BException
	{
		if(iof_remote == null)
			iof_remote = new com.bachue.snr.prosnr04.web.services.delegate.OperacionesFinancierasDelegado().getRemote();

		return iof_remote;
	}

	/**
	 * Retorna Objeto o variable de valor partes interesadas remote.
	 *
	 * @return el valor de partes interesadas remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected PartesInteresadasRemote getPartesInteresadasRemote()
	    throws B2BException
	{
		if(ipi_remote == null)
			ipi_remote = new PartesInteresadasDelegado().getRemote();

		return ipi_remote;
	}

	/**
	 * Retorna Objeto o variable de valor RRR matriculas remote.
	 *
	 * @return el valor de RRR matriculas remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected RRRMatriculasRemote getRRRMatriculasRemote()
	    throws B2BException
	{
		if(irrrmr_remote == null)
			irrrmr_remote = new RRRMatriculasDelegado().getRemote();

		return irrrmr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor registrar reintento servicio remote.
	 *
	 * @return el valor de registrar reintento servicio remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected RegistrarReintentoServicioRemote getRegistrarReintentoServicioRemote()
	    throws B2BException
	{
		if(irrs_remote == null)
			irrs_remote = new com.bachue.snr.prosnr10.web.services.delegate.RegistrarReintentoServicioDelegado()
					.getRemote();

		return irrs_remote;
	}

	/**
	 * Retorna Objeto o variable de valor salvedades CX remote.
	 *
	 * @return el valor de salvedades CX remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected com.bachue.snr.prosnr14.ejb.session.stateless.coexistencia.SalvedadesRemote getSalvedadesCXRemote()
	    throws B2BException
	{
		if(iscxr_remote == null)
			iscxr_remote = new com.bachue.snr.prosnr14.web.services.delegate.SalvedadesDelegado().getRemote();

		return iscxr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor salvedades remote.
	 *
	 * @return el valor de salvedades remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected SalvedadesRemote getSalvedadesRemote()
	    throws B2BException
	{
		if(isr_remote == null)
			isr_remote = new SalvedadesDelegado().getRemote();

		return isr_remote;
	}

	/**
	 * Método de obtencion de service consulta trazabilidad remote.
	 *
	 * @return el valor de consulta trazabilidad remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected ConsultaTrazabilidadRemote getServiceConsultaTrazabilidadRemote()
	    throws B2BException
	{
		if(ictr_remote == null)
			ictr_remote = new com.bachue.snr.prosnr06.web.services.delegate.ConsultaTrazabilidadDelegado().getRemote();

		return ictr_remote;
	}

	/**
	 * Método de obtencion de Solicitud Copias Remote.
	 *
	 * @return el valor de Solicitud Copias Remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected SolicitudCopiasRemote getSolicitudCopiasRemote()
	    throws B2BException
	{
		if(isdcr_remote == null)
			isdcr_remote = new SolicitudCopiasDelegado().getRemote();

		return isdcr_remote;
	}

	/**
	 * Método de obtencion de Solicitud Correccion Remote.
	 *
	 * @return el valor de Solicitud Correccion Remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected SolicitudCorreccionRemote getSolicitudCorreccionRemote()
	    throws B2BException
	{
		if(iscr_remote == null)
			iscr_remote = new SolicitudCorreccionDelegado().getRemote();

		return iscr_remote;
	}

	/**
	 * Retorna Objeto o variable de valor tramites en curso remote.
	 *
	 * @return el valor de tramites en curso remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	protected TramitesEnCursoRemote getTramitesEnCursoRemote()
	    throws B2BException
	{
		if(itecr_remote == null)
			itecr_remote = new TramitesEnCursoDelegado().getRemote();

		return itecr_remote;
	}
}
