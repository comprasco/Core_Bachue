package com.bachue.snr.prosnr01.web.bean.traslados;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.BeanNameCommon;
import com.bachue.snr.prosnr01.common.constants.ConstanteCommon;
import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.EtapaCommon;
import com.bachue.snr.prosnr01.common.constants.ExtensionCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;
import com.bachue.snr.prosnr01.common.constants.MessagesKeys;
import com.bachue.snr.prosnr01.common.constants.MotivoTramiteCommon;
import com.bachue.snr.prosnr01.common.constants.NavegacionCommon;
import com.bachue.snr.prosnr01.common.constants.TipoContenidoCommon;
import com.bachue.snr.prosnr01.common.utils.ExpresionRegular;

import com.bachue.snr.prosnr01.ejb.session.stateless.actuaciones.administrativas.ActuacionesAdministrativasRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema.AntiguoSistemaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.calificacion.CalificacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.entrega.EntregaRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.grabacion.GrabacionRemote;
import com.bachue.snr.prosnr01.ejb.session.stateless.registro.RegistroRemote;

import com.bachue.snr.prosnr01.model.calificacion.DatosDelInteresado;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaCorreoElectronico;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaTelefono;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.TagPlantillaResolucion;

import com.bachue.snr.prosnr01.web.bean.calificacion.BeanCalificacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanGrabacion;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanRecepcionDocumentos;
import com.bachue.snr.prosnr01.web.bean.calificacion.BeanTurnos;
import com.bachue.snr.prosnr01.web.bean.parameter.BeanDireccion;
import com.bachue.snr.prosnr01.web.util.FacesUtils;

import org.primefaces.PrimeFaces;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Clase que contiene todos las propiedades y acciones de BeanTrasladosDetalle.
 *
 * @author dbeltran
 */
@SessionScoped
@ManagedBean(name = "beanTrasladosDetalle")
public class BeanTrasladosDetalle extends BeanRecepcionDocumentos implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8161993949944640990L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(BeanTrasladosDetalle.class);

	/** Constante is_formularioGrabacion. */
	private static final String is_formularioGrabacion = ":fGrabacion";

	/** Constante is_formularioProcesosGrabacion. */
	private static final String CS_ID_FORM = ":fProcesosTraslados";

	/** Constante is_globalMsg. */
	private static final String is_globalMsg = ":globalMsg";

	/** Constante is_messageProcesosGrabacion. */
	private static final String is_messages = CS_ID_FORM + is_globalMsg;

	/** Propiedad is miga pan. */
	String is_migaPan;

	/**  Propiedad laar_actuacionesAdministrativasRemote. */
	@EJB
	private ActuacionesAdministrativasRemote iaar_actuacionesAdministrativasRemote;

	/** Propiedad iasr antiguo sistema remote. */
	@EJB
	private AntiguoSistemaRemote iasr_antiguoSistemaRemote;

	/** Propiedad irr calificacion remote. */
	@EJB
	private CalificacionRemote irr_calificacionRemote;

	/** Propiedad idas datos ant sistema. */
	private DatosAntSistema idas_datosAntSistema;

	/** Propiedad idsc imagen documento. */
	private DefaultStreamedContent idsc_imagenDocumento;

	/** Propiedad idsc imagen documento2. */
	private DefaultStreamedContent idsc_imagenDocumento2;

	/** Atributo ier_entregaRemote. */
	@EJB
	private EntregaRemote ier_entregaRemote;

	/** Propiedad igr grabacion remote. */
	@EJB
	private GrabacionRemote igr_grabacionRemote;

	/** Propiedad irr registro remote. */
	@EJB
	private RegistroRemote irr_registroRemote;

	/** Propiedad isc_zip. */
	private StreamedContent isc_zip;

	/** Propiedad is decisionTramiteRegistral. */
	private String is_decisionTramiteRegistral;

	/** Propiedad is id turno consulta. */
	private String is_idTurnoConsulta;

	/** Propiedad is nir consulta. */
	private String is_nirConsulta;

	/**  Propiedad is_tipoArchivo. */
	private String is_tipoArchivo;

	/**  Propiedad is_tipoArchivo2. */
	private String is_tipoArchivo2;

	/** Propiedad iba archivo. */
	private byte[] iba_archivo;

	/** Propiedad iba archivo2. */
	private byte[] iba_archivo2;

	/** Propiedad iba file. */
	private byte[] iba_file;

	/** Propiedad ib bdatos guardados. */
	private boolean ib_bdatosGuardados;

	/** Atributo ib_documentosEnviados */
	private boolean ib_documentosEnviados;

	/**  Atributo ib_mostrarDescargarZip. */
	private boolean ib_mostrarDescargarZip;

	/** Propiedad ib mostrar resolucion. */
	private boolean ib_mostrarResolucion;

	/** Propiedad ib negacion solicitud. */
	private boolean ib_negacionSolicitud;

	/** Propiedad ib planeacion. */
	private boolean ib_planeacion;

	/** Propiedad ib proyectar. */
	private boolean ib_proyectar;

	/** Propiedad ib proyectar resolucion. */
	private boolean ib_proyectarResolucion;

	/** Propiedad ib resolucion masivos. */
	private boolean ib_resolucionMasivos;

	/** Propiedad ib segunda instancia. */
	private boolean ib_segundaInstancia;

	/** Propiedad ib solicitud documentacion. */
	private boolean ib_solicitudDocumentacion;

	/**
	 * Modifica el valor de Archivo.
	 *
	 * @param aba_ba de aba ba
	 */
	public void setArchivo(byte[] aba_ba)
	{
		iba_archivo = aba_ba;
	}

	/**
	 * Retorna Objeto o variable de valor archivo.
	 *
	 * @return el valor de archivo
	 */
	public byte[] getArchivo()
	{
		return iba_archivo;
	}

	/**
	 * Modifica el valor de aba ba.
	 *
	 * @param aba_ba asigna el valor a la propiedad aba ba
	 */
	public void setArchivo2(byte[] aba_ba)
	{
		iba_archivo2 = aba_ba;
	}

	/**
	 * Retorna el valor de iba archivo2.
	 *
	 * @return el valor de iba archivo
	 */
	public byte[] getArchivo2()
	{
		return iba_archivo2;
	}

	/**
	 * Modifica el valor de datos ant sistema.
	 *
	 * @param adas_das asigna el valor a la propiedad datos ant sistema
	 */
	public void setDatosAntSistema(DatosAntSistema adas_das)
	{
		idas_datosAntSistema = adas_das;
	}

	/**
	 * Retorna el valor de datos ant sistema.
	 *
	 * @return el valor de datos ant sistema
	 */
	public DatosAntSistema getDatosAntSistema()
	{
		return idas_datosAntSistema;
	}

	/**
	 * Modifica el valor de datos guardados.
	 *
	 * @param ab_b asigna el valor a la propiedad datos guardados
	 */
	public void setDatosGuardados(boolean ab_b)
	{
		ib_bdatosGuardados = ab_b;
	}

	/**
	 * Valida la propiedad datos guardados.
	 *
	 * @return verdadero si se cumple la condicion, de lo contario retorna falso en datos guardados
	 */
	public boolean isDatosGuardados()
	{
		return ib_bdatosGuardados;
	}

	/**
	 * @param Modifica el valor de la propiedad is_decisionTramiteRegistral por is_decisionTramiteRegistral
	 */
	public void setDecisionTramiteRegistral(String as_s)
	{
		is_decisionTramiteRegistral = as_s;
	}

	/**
	 * @return Retorna el valor de la propiedad is_decisionTramiteRegistral
	 */
	public String getDecisionTramiteRegistral()
	{
		return is_decisionTramiteRegistral;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setDocumentosEnviados(boolean ab_b)
	{
		ib_documentosEnviados = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isDocumentosEnviados()
	{
		return ib_documentosEnviados;
	}

	/**
	 * Modifica el valor de file.
	 *
	 * @param aba_ba de aba ba
	 */
	public void setFile(byte[] aba_ba)
	{
		iba_file = aba_ba;
	}

	/**
	 * Retorna el valor de file.
	 *
	 * @return el valor de file
	 */
	public byte[] getFile()
	{
		return iba_file;
	}

	/** {@inheritdoc} */
	public String getIdTurno()
	{
		String        ls_return;
		TurnoHistoria lth_turnoHistoria;

		ls_return             = super.getIdTurno();
		lth_turnoHistoria     = getTurnoHistoria();

		if((lth_turnoHistoria != null) && !StringUtils.isValidString(ls_return))
			ls_return = lth_turnoHistoria.getIdTurno();

		return ls_return;
	}

	/**
	 * Modifica el valor de id turno consulta.
	 *
	 * @param as_s asigna el valor a la propiedad id turno consulta
	 */
	public void setIdTurnoConsulta(String as_s)
	{
		is_idTurnoConsulta = as_s;
	}

	/**
	 * Retorna el valor de id turno consulta.
	 *
	 * @return el valor de id turno consulta
	 */
	public String getIdTurnoConsulta()
	{
		return is_idTurnoConsulta;
	}

	/** {@inheritdoc} */
	public String getIdTurnoHistoria()
	{
		String        ls_return;
		TurnoHistoria lth_turnoHistoria;

		ls_return             = super.getIdTurnoHistoria();
		lth_turnoHistoria     = getTurnoHistoria();

		if((lth_turnoHistoria != null) && !StringUtils.isValidString(ls_return))
		{
			Long ll_idTurnoHistoria;

			ll_idTurnoHistoria = lth_turnoHistoria.getIdTurnoHistoria();

			if(NumericUtils.isValidLong(ll_idTurnoHistoria))
				ls_return = ll_idTurnoHistoria.toString();
		}

		return ls_return;
	}

	/**
	 * Modifica el valor de imagen documento.
	 *
	 * @param adsc_dsc asigna el valor a la propiedad imagen documento
	 */
	public void setImagenDocumento(DefaultStreamedContent adsc_dsc)
	{
		idsc_imagenDocumento = adsc_dsc;
	}

	/**
	 * Retorna el valor de imagen documento.
	 *
	 * @return el valor de imagen documento
	 */
	public DefaultStreamedContent getImagenDocumento()
	{
		return idsc_imagenDocumento;
	}

	/**
	 * Modifica el valor de imagen documento 2.
	 *
	 * @param adsc_dsc asigna el valor a la propiedad imagen documento 2
	 */
	public void setImagenDocumento2(DefaultStreamedContent adsc_dsc)
	{
		idsc_imagenDocumento2 = adsc_dsc;
	}

	/**
	 * Retorna el valor de imagen documento 2.
	 *
	 * @return el valor de imagen documento 2
	 */
	public DefaultStreamedContent getImagenDocumento2()
	{
		return idsc_imagenDocumento2;
	}

	/**
	 * Modifica el valor de miga pan.
	 *
	 * @param as_s asigna el valor a la propiedad miga pan
	 */
	public void setMigaPan(String as_s)
	{
		is_migaPan = as_s;
	}

	/**
	 * Retorna el valor de miga pan.
	 *
	 * @return el valor de miga pan
	 */
	public String getMigaPan()
	{
		return is_migaPan;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param ab_b Argumento encargado de modificar la propiedad.
	 */
	public void setMostrarDescargarZip(boolean ab_b)
	{
		ib_mostrarDescargarZip = ab_b;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public boolean isMostrarDescargarZip()
	{
		return ib_mostrarDescargarZip;
	}

	/**
	 * Modifica el valor de MostrarResolucion.
	 *
	 * @param ab_b de ab b
	 */
	public void setMostrarResolucion(boolean ab_b)
	{
		ib_mostrarResolucion = ab_b;
	}

	/**
	 * Valida la propiedad mostrar resolucion.
	 *
	 * @return Retorna el valor de la propiedad mostrarResolucion
	 */
	public boolean isMostrarResolucion()
	{
		return ib_mostrarResolucion;
	}

	/**
	 * @param Modifica el valor de la propiedad negacionSolicitud por negacionSolicitud
	 */
	public void setNegacionSolicitud(boolean ab_b)
	{
		ib_negacionSolicitud = ab_b;
	}

	/**
	 * Valida la propiedad negacion solicitud.
	 *
	 * @return Retorna el valor de la propiedad negacionSolicitud
	 */
	public boolean isNegacionSolicitud()
	{
		return ib_negacionSolicitud;
	}

	/**
	 * Modifica el valor de nir consulta.
	 *
	 * @param as_s asigna el valor a la propiedad nir consulta
	 */
	public void setNirConsulta(String as_s)
	{
		is_nirConsulta = as_s;
	}

	/**
	 * Retorna el valor de nir consulta.
	 *
	 * @return el valor de nir consulta
	 */
	public String getNirConsulta()
	{
		return is_nirConsulta;
	}

	/**
	 * @param Modifica el valor de la propiedad planeacion por planeacion
	 */
	public void setPlaneacion(boolean ab_b)
	{
		ib_planeacion = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad planeacion
	 */
	public boolean isPlaneacion()
	{
		return ib_planeacion;
	}

	/**
	 * @param Modifica el valor de la propiedad proyectar por proyectar
	 */
	public void setProyectar(boolean ab_b)
	{
		ib_proyectar = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad proyectar
	 */
	public boolean isProyectar()
	{
		return ib_proyectar;
	}

	/**
	 * Modifica el valor de ProyectarResolucion.
	 *
	 * @param ab_b de ab b
	 */
	public void setProyectarResolucion(boolean ab_b)
	{
		ib_proyectarResolucion = ab_b;
	}

	/**
	 * Valida la propiedad proyectar resolucion.
	 *
	 * @return Retorna el valor de la propiedad proyectarResolución
	 */
	public boolean isProyectarResolucion()
	{
		return ib_proyectarResolucion;
	}

	/**
	 * @param Modifica el valor de la propiedad resolucionMasivos por resolucionMasivos
	 */
	public void setResolucionMasivos(boolean ab_b)
	{
		ib_resolucionMasivos = ab_b;
	}

	/**
	 * Valida la propiedad resolucion masivos.
	 *
	 * @return Retorna el valor de la propiedad resolucionMasivos
	 */
	public boolean isResolucionMasivos()
	{
		return ib_resolucionMasivos;
	}

	/**
	 * @param Modifica el valor de la propiedad segundaInstancia por segundaInstancia
	 */
	public void setSegundaInstancia(boolean ab_b)
	{
		ib_segundaInstancia = ab_b;
	}

	/**
	 * @return Retorna el valor de la propiedad segundaInstancia
	 */
	public boolean isSegundaInstancia()
	{
		return ib_segundaInstancia;
	}

	/**
	 * Modifica el valor de SolicitudDocumentacion.
	 *
	 * @param ab_b de ab b
	 */
	public void setSolicitudDocumentacion(boolean ab_b)
	{
		ib_solicitudDocumentacion = ab_b;
	}

	/**
	 * Valida la propiedad solicitud documentacion.
	 *
	 * @return Retorna el valor de la propiedad solicitudDocumentacion
	 */
	public boolean isSolicitudDocumentacion()
	{
		return ib_solicitudDocumentacion;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setTipoArchivo(String as_s)
	{
		is_tipoArchivo = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoArchivo()
	{
		return is_tipoArchivo;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param as_s Argumento encargado de modificar la propiedad.
	 */
	public void setTipoArchivo2(String as_s)
	{
		is_tipoArchivo2 = as_s;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public String getTipoArchivo2()
	{
		return is_tipoArchivo2;
	}

	/**
	 * Metodo encargado de modificar la propiedad.
	 *
	 * @param asc_sc Argumento encargado de modificar la propiedad.
	 */
	public void setZip(StreamedContent asc_sc)
	{
		isc_zip = asc_sc;
	}

	/**
	 * Metodo encargado de retornar el valor de la propiedad.
	 *
	 * @return retorna el valor de la propiedad.
	 */
	public StreamedContent getZip()
	{
		return isc_zip;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String accionVolver()
	{
		setMotivoTramite(null);

		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Metodo encargado de cargar la información de actuaciones administrativas.
	 *
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cargarDatosActuacionesAdministrativas()
	    throws B2BException
	{
		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				TagPlantillaResolucion ltpr_actuacionesAdministrativas;
				String                 ls_idTurno;
				long                   ll_idEtapa;

				ltpr_actuacionesAdministrativas     = new TagPlantillaResolucion();
				ls_idTurno                          = getIdTurno();

				ltpr_actuacionesAdministrativas.setIdTurno(ls_idTurno);
				ltpr_actuacionesAdministrativas.setIdEtapa(getEtapa());
				ltpr_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				ltpr_actuacionesAdministrativas.setIdMotivo(NumericUtils.getLong(getMotivoTramite()));
				ltpr_actuacionesAdministrativas.setTraslado(!isSegundaInstancia());
				ltpr_actuacionesAdministrativas.setSegundaInstancia(isSegundaInstancia());
				ltpr_actuacionesAdministrativas.setMasivo(isResolucionMasivos());
				ltpr_actuacionesAdministrativas.setProyectar(isProyectar());
				ltpr_actuacionesAdministrativas.setPlaneacion(isPlaneacion());

				ll_idEtapa = getEtapa();

				if(ll_idEtapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
					ltpr_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote
							.cargarDatosActuacionesAdministrativas(
							    ltpr_actuacionesAdministrativas, true, getUserId(), getLocalIpAddress(),
							    getRemoteIpAddress()
							);
				else
					ltpr_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote
							.cargarDatosActuacionesAdministrativas(
							    ltpr_actuacionesAdministrativas, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
							);

				if(ltpr_actuacionesAdministrativas != null)
				{
					byte[] lba_archivo;

					lba_archivo = ltpr_actuacionesAdministrativas.getArchivo();

					ls_parametros.setOficiosTexto(ltpr_actuacionesAdministrativas.getOficiosTexto());
					setTipoArchivo(ltpr_actuacionesAdministrativas.getTipoArchivo());
					setTipoArchivo2(ltpr_actuacionesAdministrativas.getTipoArchivo2());
					setIdTurno(ls_idTurno);
					setArchivo(lba_archivo);

					setImagenDocumento(
					    generarArchivosDescarga(
					        lba_archivo, TipoContenidoCommon.PDF,
					        ltpr_actuacionesAdministrativas.getTipoArchivo() + ExtensionCommon.PDF_PUNTO
					    )
					);

					if(isResolucionMasivos())
					{
						byte[] lba_archivo2;

						lba_archivo2 = ltpr_actuacionesAdministrativas.getArchivo2();

						setArchivo2(lba_archivo2);

						setImagenDocumento2(
						    generarArchivosDescarga(
						        lba_archivo2, TipoContenidoCommon.PDF,
						        ltpr_actuacionesAdministrativas.getTipoArchivo() + ExtensionCommon.PDF_PUNTO
						    )
						);
					}
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("cargarDatosActuacionesAdministrativas", lb2be_e);

			throw lb2be_e;
		}
	}

	/**
	 * Clean.
	 */
	public void clean()
	{
		setNegacionSolicitud(false);
		setProyectarResolucion(false);
		setSolicitudDocumentacion(false);
		setResolucionMasivos(false);
		setMostrarResolucion(false);
		setDocumentosEnviados(false);
	}

	/**
	 * Consultar persona interesado.
	 *
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void consultarPersonaInteresado()
	    throws B2BException
	{
		TurnoHistoria lth_turnoHistoria;

		lth_turnoHistoria = getTurnoHistoria();

		if(lth_turnoHistoria != null)
		{
			String ls_idSolicitud;

			ls_idSolicitud = lth_turnoHistoria.getIdSolicitud();

			if(StringUtils.isValidString(ls_idSolicitud))
			{
				Suspension ls_parametros;

				ls_parametros = new Suspension();

				{
					DatosDelInteresado lddi_data;

					lddi_data = igr_grabacionRemote.consultarPersonaInteresado(ls_idSolicitud, true);

					if(lddi_data != null)
					{
						BeanDireccion lbd_beanDireccion;

						lbd_beanDireccion = getBeanDireccion();

						lbd_beanDireccion.limpiarBeanDireccion();
						lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
						lbd_beanDireccion.setDeshabilitarResidencia(true);
						lbd_beanDireccion.setHabilitadoPorConsulta(true);
						lbd_beanDireccion.setForm(CS_ID_FORM);
						lbd_beanDireccion.setDireccionCorrespondencia(lddi_data.getDireccionCorrespondencia());
						lbd_beanDireccion.setDireccionResidencia(lddi_data.getDireccionResidencia());
					}
					else
						lddi_data = new DatosDelInteresado();

					ls_parametros.setDatosDelInteresado(lddi_data);
				}

				ls_parametros.setSolicitud2(
				    irr_registroRemote.findSolicitudById(new Solicitud(ls_idSolicitud), getUserId())
				);

				setParametros(ls_parametros);
			}
		}
	}

	/**
	 * Metodo encargado de descargar Zip de actuaciones administrativas.
	 */
	public void descargarZipActuacionesAdmin()
	{
		byte[] lba_file;

		lba_file = getFile();

		if(lba_file != null)
		{
			setZip(
			    generarArchivosDescarga(
			        lba_file, TipoContenidoCommon.ZIP, IdentificadoresCommon.TRASLADOS + ExtensionCommon.ZIP_PUNTO
			    )
			);

			actualizarComponente(CS_ID_FORM + ":opBotones");
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC
	 */
	public void documentosEnviadosOWCC()
	{
		try
		{
			documentosEnviadosOWCC(getIdTurnoHistoria());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			addMessage(lb2be_e);
		}
		finally
		{
			actualizarComponente(CS_ID_FORM);
		}
	}

	/**
	 * Metodo encargado de consultar si ya se enviaron los documentos al OWCC.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor de id turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void documentosEnviadosOWCC(String as_idTurnoHistoria)
	    throws B2BException
	{
		try
		{
			boolean lb_enviados;

			lb_enviados = validarEnvioDocumentosOWCC(getIdTurnoHistoria());

			setDocumentosEnviados(lb_enviados);

			if(lb_enviados)
				PrimeFaces.current().executeScript("PF('wvPoll').stop();");
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("documentosEnviadosOWCC", lb2be_e);
			throw lb2be_e;
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public String enviarAprobador()
	    throws B2BException
	{
		String ls_return;

		ls_return = null;

		try
		{
			Suspension ls_parametros;
			String     ls_motivo;

			ls_parametros     = getParametros();
			ls_motivo         = getMotivoTramite();

			if((ls_parametros != null) && StringUtils.isValidString(ls_motivo))
			{
				BeanCalificacion lb_beanCalificacion;
				boolean          lb_focus;
				boolean          lb_negacion;
				boolean          lb_resolucion;
				FacesContext     lfc_context;
				long             ll_idEtapa;
				String           ls_consideracion;

				lb_focus             = true;
				lb_negacion          = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
					);
				lb_resolucion        = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
					);
				lfc_context          = FacesUtils.getFacesContext();
				ll_idEtapa           = getEtapa();
				ls_consideracion     = ls_parametros.getConsideracion();

				lb_focus = validateStyles(
					    CS_ID_FORM + ":idItaConsideraciones", lfc_context, ls_consideracion, lb_focus
					);

				if(!StringUtils.isValidString(ls_consideracion))
				{
					if(lb_negacion)
						throw new B2BException(ErrorKeys.ERROR_RAZONES_NEGACION);
					else if(lb_resolucion)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
				}

				ls_parametros.setTurnoHistoria(getTurnoHistoria());
				ls_parametros.setObservacionesProcesoAnterior(getObservaciones());
				ls_parametros.setEtapa(ll_idEtapa);

				if(lb_negacion)
					igr_grabacionRemote.negarSolicitud(
					    ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
				else if(lb_resolucion)
					igr_grabacionRemote.generarResolucion(
					    ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);

				if(ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
				{
					lb_beanCalificacion = (BeanCalificacion)lfc_context.getApplication()
							                                               .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_CALIFICACION, BeanCalificacion.class
							);

					if(lb_beanCalificacion != null)
					{
						String ls_idEtapa;

						ls_idEtapa = StringUtils.getString(EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS);

						lb_beanCalificacion.clear();
						lb_beanCalificacion.setIdEtapa(ls_idEtapa);
						lb_beanCalificacion.generarDatosTramiteCantidad(is_formularioGrabacion, ls_idEtapa, true);

						ls_return = NavegacionCommon.GRABACION;
					}
				}
				else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
				{
					BeanGrabacion lb_beanGrabacion;

					lb_beanGrabacion = (BeanGrabacion)lfc_context.getApplication()
							                                         .evaluateExpressionGet(
							    lfc_context, BeanNameCommon.BEAN_GRABACION, BeanGrabacion.class
							);

					lb_beanGrabacion.setIdTurnoConsulta(null);
					lb_beanGrabacion.setNirConsulta(null);
					lb_beanGrabacion.generarDatosTramiteCantidad();

					ls_return = NavegacionCommon.GRABACION_MATRICULAS;
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messages);
		}

		return ls_return;
	}

	/**
	 * Metodo encargado de cargar la información de municipios para actuaciones administrativas.
	 *
	 * @return el valor de string
	 */
	public String enviarResponsableActuacionesAdmin()
	{
		String ls_return;

		ls_return = null;

		try
		{
			{
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				laa_actuacionesAdministrativas = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actuacionesAdministrativas.setObservaciones(getObservaciones());
				laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
				laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
				laa_actuacionesAdministrativas.setIdEtapa(EtapaCommon.ID_ETAPA_ANALISIS_DE_TRASLADOS);
				laa_actuacionesAdministrativas.setNoSalvarCompletitudDocumental(true);

				iaar_actuacionesAdministrativasRemote.enviarResponsableActuacionesAdmin(
				    laa_actuacionesAdministrativas, NumericUtils.getLong(getMotivoTramite()), getUserId(),
				    getLocalIpAddress(), getRemoteIpAddress()
				);
			}

			{
				BeanTurnos lbt_bean;

				lbt_bean = (BeanTurnos)obtenerInstanciaBean(BeanTurnos.class, BeanNameCommon.BEAN_TURNOS);

				lbt_bean.setNirConsulta(null);
				lbt_bean.setIdTurnoConsulta(null);
				lbt_bean.generarData();

				ls_return = NavegacionCommon.TURNOS;
			}
		}
		catch(B2BException lb2be_e)
		{
			addMessage(lb2be_e);
			actualizarComponente(CS_ID_FORM);
		}

		return ls_return;
	}

	/**
	 * Generar descarga.
	 */
	public void generarDescarga()
	{
		setImagen(
		    generarArchivosDescarga(
		        getArchivo(), TipoContenidoCommon.PDF,
		        ConstanteCommon.NOMBRE_ARCHIVO_GENERADO + ExtensionCommon.PDF_PUNTO
		    )
		);
	}

	/**
	 * Metodo encargado de generar los documentos de actuaciones administrativas.
	 *
	 * @param ab_suspension de ab suspension
	 */
	public void generarDocumentosActuacionesAdmin(boolean ab_suspension)
	{
		try
		{
			Suspension ls_parametros;

			ls_parametros = getParametros();

			if(ls_parametros != null)
			{
				TagPlantillaResolucion laa_actuacionesAdministrativas;
				long                   ll_idMotivo;
				String                 ls_tipoArchivo;
				long                   ll_etapa;

				laa_actuacionesAdministrativas     = new TagPlantillaResolucion();
				ll_idMotivo                        = NumericUtils.getLong(getMotivoTramite());
				ls_tipoArchivo                     = ab_suspension ? StringUtils.getStringNotNull(getTipoArchivo2())
					                                               : StringUtils.getStringNotNull(getTipoArchivo());
				ll_etapa                           = getEtapa();

				laa_actuacionesAdministrativas.setOficiosTexto(
				    convertirOficiosTexto(
				        ab_suspension ? ls_parametros.getOficiosTexto2() : ls_parametros.getOficiosTexto()
				    )
				);
				laa_actuacionesAdministrativas.setIdTurno(getIdTurno());
				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());
				laa_actuacionesAdministrativas.setTiposDocumentales(getTiposDocumentales());
				laa_actuacionesAdministrativas.setTipoArchivo(ls_tipoArchivo);
				laa_actuacionesAdministrativas.setTraslado(true);
				laa_actuacionesAdministrativas.setMasivo(ab_suspension);

				if(ll_etapa == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
					laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote
							.generarDocumentosActuacionesAdmin(
							    laa_actuacionesAdministrativas, ll_idMotivo, getUserId(), getLocalIpAddress(),
							    getRemoteIpAddress(), true
							);
				else
					laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote
							.generarDocumentosActuacionesAdmin(
							    laa_actuacionesAdministrativas, ll_idMotivo, getUserId(), getLocalIpAddress(),
							    getRemoteIpAddress()
							);

				if(laa_actuacionesAdministrativas != null)
				{
					PrimeFaces.current().executeScript("PF('wvPoll').start();");
					setFile(laa_actuacionesAdministrativas.getArchivo());

					if(ab_suspension)
						setMostrarResolucion(true);
					else
						setMostrarDescargarZip(true);

					setDocumentosEnviados(false);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarDocumentosActuacionesAdmin", lb2be_e);

			addMessage(lb2be_e);
		}
		finally
		{
			setImagenDocumento2(
			    generarArchivosDescarga(
			        getArchivo2(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);
			setImagenDocumento(
			    generarArchivosDescarga(
			        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);

			actualizarComponente(CS_ID_FORM);
		}
	}

	/**
	 * Generar miga pan.
	 *
	 * @param as_motivo correspondiente al valor del tipo de objeto String
	 */
	public void generarMigaPan(String as_motivo)
	{
		if(StringUtils.isValidString(as_motivo))
		{
			String ls_migaPan;

			ls_migaPan = null;

			if(as_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.PROYECTAR_RESOLUCION_TRASLADOS)))
				ls_migaPan = getMessages().getString(MessagesKeys.LABEL_PROYECTAR_RESOLUCION_TRASLADOS);
			else if(as_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.SOLICITUD_DOCUMENTACION)))
				ls_migaPan = getMessages().getString(MessagesKeys.LABEL_SOLICITUD_DOCUMENTAICON);
			else if(as_motivo.equalsIgnoreCase(StringUtils.getString(MotivoTramiteCommon.NEGACION_SOLICITUD_TRASLADOS)))
				ls_migaPan = getMessages().getString(MessagesKeys.LABEL_NEGAR_SOLICITUD_TRASLADOS);

			setMigaPan(ls_migaPan);
		}
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String regresar()
	{
		long   ll_idEtapa;
		String ls_return;

		ll_idEtapa     = getEtapa();
		ls_return      = null;

		if(ll_idEtapa == EtapaCommon.ID_ETAPA_GRABACION_MATRICULAS)
			ls_return = NavegacionCommon.TURNOS;
		else if(ll_idEtapa == EtapaCommon.ID_ETAPA_EJECUCION_GRABACION_MATRICULAS)
			ls_return = NavegacionCommon.DETALLE_GRABACION_MATRICULAS;

		return ls_return;
	}

	/**
	 * Salvar grabacion matriculas.
	 */
	public void salvarGrabacionMatriculas()
	{
		try
		{
			Suspension ls_parametros;
			String     ls_motivo;

			ls_parametros     = getParametros();
			ls_motivo         = getMotivoTramite();

			if((ls_parametros != null) && StringUtils.isValidString(ls_motivo))
			{
				BeanDireccion      lbd_beanDireccion;
				boolean            lb_focus;
				boolean            lb_negacion;
				boolean            lb_resolucion;
				DatosDelInteresado lddi_datosDelInteresado;
				FacesContext       lfc_context;
				String             ls_consideraciones;

				lbd_beanDireccion           = getBeanDireccion();
				lb_focus                    = true;
				lb_negacion                 = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.NEGAR_SOLICITUD_DE_GRABACION)
					);
				lb_resolucion               = ls_motivo.equalsIgnoreCase(
					    StringUtils.getString(MotivoTramiteCommon.GENERAR_RESOLUCION_DE_GRABACION)
					);
				lddi_datosDelInteresado     = ls_parametros.getDatosDelInteresado();
				lfc_context                 = FacesContext.getCurrentInstance();
				ls_consideraciones          = ls_parametros.getConsideracion();
				lb_focus                    = validateStyles(
					    CS_ID_FORM + ":idItaConsideraciones", lfc_context, ls_consideraciones, lb_focus
					);

				if(!StringUtils.isValidString(ls_consideraciones))
				{
					if(lb_negacion)
						throw new B2BException(ErrorKeys.ERROR_RAZONES_NEGACION);
					else if(lb_resolucion)
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_JUSTIFICACION);
				}

				validarDatosBasicos(lddi_datosDelInteresado, lfc_context);

				if(isEditarDireccionResidencia())
					lbd_beanDireccion.validarCamposDireccionResidencia();

				if(isEditarDireccionCorrespondencia())
					lbd_beanDireccion.validarCamposDireccionCorrespondencia(false);

				if(isEditarDatosContacto())
					validarDatosContacto(ls_parametros, lfc_context);

				lddi_datosDelInteresado.setDireccionCorrespondencia(lbd_beanDireccion.getDireccionCorrespondencia());
				lddi_datosDelInteresado.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
				lddi_datosDelInteresado.setEditarDatosBasicos(isEditarDatosBasicos());
				lddi_datosDelInteresado.setEditarDatosContacto(isEditarDatosContacto());
				lddi_datosDelInteresado.setEditarDireccionCorrespondencia(isEditarDireccionCorrespondencia());
				lddi_datosDelInteresado.setEditarDireccionResidencia(isEditarDireccionResidencia());

				ls_parametros.setDatosDelInteresado(lddi_datosDelInteresado);
				ls_parametros.setTurnoHistoria(getTurnoHistoria());

				if(
				    igr_grabacionRemote.salvarGrabacionMatriculas(
					        ls_parametros, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					    )
				)
				{
					addMessage(MessagesKeys.INFORMACION_GUARDADA_ENVIAR_APROBADOR);

					lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
					lbd_beanDireccion.setDeshabilitarResidencia(true);
					setDatosGuardados(true);
				}
				else
					throw new B2BException(ErrorKeys.GRABACION_SALVAR_NEGACION);
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error(lb2be_e);
			addMessage(lb2be_e);
			PrimeFaces.current().ajax().update(is_messages);
		}
	}

	/**
	 * Validar datos basicos.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosBasicos(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			Persona lp_persona;

			lp_persona = addi_data.getPersona();

			if(lp_persona != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lp_persona.getIdTipoPersona();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMTipoPersona", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_PERSONA);
				}

				{
					ls_datoValidar     = lp_persona.getIdDocumentoTipo();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMTipoDoc", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_DOC);
				}

				{
					ls_datoValidar     = lp_persona.getNumeroDocumento();

					lb_focus = validateStyles(CS_ID_FORM + ":idOlDocumento", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DOCUMENTO_INVALIDO);
				}

				{
					ls_datoValidar     = lp_persona.getIdPais();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMNacionalidad", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_NACIONALIDAD);
				}

				{
					ls_datoValidar     = lp_persona.getPrimerNombre();

					lb_focus = validateStyles(CS_ID_FORM + ":idOlPNombre", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_NOMBRE);
				}

				{
					ls_datoValidar     = lp_persona.getPrimerApellido();

					lb_focus = validateStyles(CS_ID_FORM + ":idOlPApellido", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_DIGITAR_PRIMER_APELLIDO);
				}

				{
					ls_datoValidar     = lp_persona.getIdNaturalGenero();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMGenero", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_GENERO);
				}
			}
		}
	}

	/**
	 * Validar datos contacto.
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto Suspension
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDatosContacto(Suspension as_parametros, FacesContext afc_context)
	    throws B2BException
	{
		if(as_parametros != null)
		{
			DatosDelInteresado lddi_data;

			lddi_data = as_parametros.getDatosDelInteresado();

			if(lddi_data != null)
			{
				boolean                  lb_focus;
				PersonaCorreoElectronico lpce_correo;
				PersonaTelefono          lpt_fijo;
				PersonaTelefono          lpt_movil;
				String                   ls_datoValidar;

				lb_focus           = true;
				lpce_correo        = lddi_data.getCorreoElectronico();
				lpt_fijo           = lddi_data.getTelefonoFijo();
				lpt_movil          = lddi_data.getTelefonoMovil();
				ls_datoValidar     = null;

				if(lpt_fijo != null)
				{
					String ls_telefono;

					ls_telefono = lpt_fijo.getTelefono();

					if(StringUtils.isValidString(ls_telefono) || lddi_data.isDataFijo())
					{
						{
							ls_datoValidar     = lpt_fijo.getIdPais();

							lb_focus = validateStyles(
								    CS_ID_FORM + ":idSOMPaisTel", afc_context, ls_datoValidar, lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_FIJO);
						}

						{
							ls_datoValidar     = lpt_fijo.getIdDepartamento();

							lb_focus = validateStyles(
								    CS_ID_FORM + ":idSOMDepTel", afc_context, ls_datoValidar, lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
						}

						{
							lb_focus = validateStyles(
								    CS_ID_FORM + ":idItTelefonoFijo", afc_context, ls_telefono, lb_focus
								);

							if(!StringUtils.isValidString(ls_telefono))
								throw new B2BException(ErrorKeys.TELEFONO_FIJO_INVALIDO);
						}
					}
				}

				if(lpt_movil != null)
				{
					String ls_telefono;

					ls_telefono = lpt_movil.getTelefono();

					if(StringUtils.isValidString(ls_telefono) || lddi_data.isDataMovil())
					{
						{
							ls_datoValidar     = lpt_movil.getIdPais();

							lb_focus = validateStyles(
								    CS_ID_FORM + ":idSOMPaisTelMov", afc_context, ls_datoValidar, lb_focus
								);

							if(!StringUtils.isValidString(ls_datoValidar))
								throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_TEL_MOVIL);
						}

						{
							lb_focus = validateStyles(
								    CS_ID_FORM + ":idItTelefonoMovil", afc_context, ls_telefono, lb_focus
								);

							if(!StringUtils.isValidString(ls_telefono))
								throw new B2BException(ErrorKeys.TELEFONO_FIJO_MOVIL);
						}
					}
				}

				if(lpce_correo != null)
				{
					ls_datoValidar = lpce_correo.getCorreoElectronico();

					if(StringUtils.isValidString(ls_datoValidar) || lddi_data.isDataCorreo())
					{
						lb_focus = validateStyles(CS_ID_FORM + ":idItEmail", afc_context, ls_datoValidar, lb_focus);

						if(!StringUtils.isValidString(ls_datoValidar))
							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);

						if(!ExpresionRegular.getExpresionRegular().esCorreoElectronico(ls_datoValidar))
						{
							lb_focus = validateStyles(
								    CS_ID_FORM + ":idItEmail", afc_context, IdentificadoresCommon.DATO_INVALIDO,
								    lb_focus
								);

							throw new B2BException(ErrorKeys.DEBE_DIGITAR_CORREO_ELECTRONICO_VALIDO);
						}
					}
				}
			}
		}
	}

	/**
	 * Validar dir correspondencia.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDirCorrespondencia(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			PersonaDireccion lpd_dirCorrespondencia;

			lpd_dirCorrespondencia = addi_data.getDireccionCorrespondencia();

			if(lpd_dirCorrespondencia != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdTipoEjePrincipal();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEjeC", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getDatoEjePrincipal();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjePrincipalC", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_PRINCIPAL);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdTipoEjeSecundario();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEje1C", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getDatoEjeSecundario();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjeSecundarioC", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_SECUNDARIO);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdPais();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMPaisDirCor", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdDepartamento();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMDepDirCor", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					ls_datoValidar     = lpd_dirCorrespondencia.getIdMunicipio();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMMunDirCor", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}
			}
		}
	}

	/**
	 * Validar dir residencia.
	 *
	 * @param addi_data correspondiente al valor del tipo de objeto DatosDelInteresado
	 * @param afc_context correspondiente al valor del tipo de objeto FacesContext
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void validarDirResidencia(DatosDelInteresado addi_data, FacesContext afc_context)
	    throws B2BException
	{
		if(addi_data != null)
		{
			PersonaDireccion lpd_dirResidencia;

			lpd_dirResidencia = addi_data.getDireccionResidencia();

			if(lpd_dirResidencia != null)
			{
				boolean lb_focus;
				String  ls_datoValidar;

				lb_focus           = true;
				ls_datoValidar     = null;

				{
					ls_datoValidar     = lpd_dirResidencia.getIdTipoEjePrincipal();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEje", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getDatoEjePrincipal();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjePrincipal", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_PRINCIPAL);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdTipoEjeSecundario();

					lb_focus = validateStyles(CS_ID_FORM + ":idsomTipoEje1", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_TIPO_EJE1);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getDatoEjeSecundario();

					lb_focus = validateStyles(
						    CS_ID_FORM + ":idItDatoEjeSecundario", afc_context, ls_datoValidar, lb_focus
						);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.ERROR_SIN_EJE_SECUNDARIO);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdPais();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMPaisDirRe", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_PAIS_RESIDENCIA);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdDepartamento();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMDepDirRe", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_DEPARTAMENTO);
				}

				{
					ls_datoValidar     = lpd_dirResidencia.getIdMunicipio();

					lb_focus = validateStyles(CS_ID_FORM + ":idSOMMunDirRe", afc_context, ls_datoValidar, lb_focus);

					if(!StringUtils.isValidString(ls_datoValidar))
						throw new B2BException(ErrorKeys.DEBE_SELECCIONAR_MUNICIPIO);
				}
			}
		}
	}

	/**
	 * Metodo encargado de visualizar los cambios realizados en la resolución.
	 *
	 * @param ab_suspension de ab suspension
	 * @return Retorna a la misma pantalla que lo invocó.
	 */
	public String visualizarCambiosResolucion(boolean ab_suspension)
	{
		try
		{
			Suspension ls_data;

			ls_data = getParametros();

			if(ls_data != null)
			{
				BeanDireccion          lbd_beanDireccion;
				TagPlantillaResolucion laa_actuacionesAdministrativas;

				lbd_beanDireccion                  = getBeanDireccion();
				laa_actuacionesAdministrativas     = new TagPlantillaResolucion();

				laa_actuacionesAdministrativas.setOficiosTexto(
				    convertirOficiosTexto(ab_suspension ? ls_data.getOficiosTexto2() : ls_data.getOficiosTexto())
				);
				laa_actuacionesAdministrativas.setTraslado(true);
				laa_actuacionesAdministrativas.setMasivo(ab_suspension);
				laa_actuacionesAdministrativas.setIdTurnoHistoria(getIdTurnoHistoria());

				{
					DatosDelInteresado lddi_datosDelInteresado;
					FacesContext       lfc_context;

					lddi_datosDelInteresado     = ls_data.getDatosDelInteresado();
					lfc_context                 = FacesContext.getCurrentInstance();

					validarDatosBasicos(lddi_datosDelInteresado, lfc_context);

					if(isEditarDireccionResidencia())
						lbd_beanDireccion.validarCamposDireccionResidencia();

					if(isEditarDireccionCorrespondencia())
						lbd_beanDireccion.validarCamposDireccionCorrespondencia(false);

					if(isEditarDatosContacto())
						validarDatosContacto(ls_data, lfc_context);

					lddi_datosDelInteresado.setDireccionCorrespondencia(
					    lbd_beanDireccion.getDireccionCorrespondencia()
					);
					lddi_datosDelInteresado.setDireccionResidencia(lbd_beanDireccion.getDireccionResidencia());
					lddi_datosDelInteresado.setEditarDatosBasicos(isEditarDatosBasicos());
					lddi_datosDelInteresado.setEditarDatosContacto(isEditarDatosContacto());
					lddi_datosDelInteresado.setEditarDireccionCorrespondencia(isEditarDireccionCorrespondencia());
					lddi_datosDelInteresado.setEditarDireccionResidencia(isEditarDireccionResidencia());

					ls_data.setDatosDelInteresado(lddi_datosDelInteresado);
				}

				ls_data.setTurnoHistoria(getTurnoHistoria());
				laa_actuacionesAdministrativas.setSuspension(ls_data);

				if(getEtapa() == EtapaCommon.ID_ETAPA_ANALISTA_DE_TRASLADOS_OFICINA_DESTINO)
					laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.visualizarCambiosResolucion(
						    laa_actuacionesAdministrativas, NumericUtils.getLong(getMotivoTramite()), true, getUserId(),
						    getLocalIpAddress(), getRemoteIpAddress()
						);
				else
					laa_actuacionesAdministrativas = iaar_actuacionesAdministrativasRemote.visualizarCambiosResolucion(
						    laa_actuacionesAdministrativas, NumericUtils.getLong(getMotivoTramite()), getUserId(),
						    getLocalIpAddress(), getRemoteIpAddress()
						);

				if(laa_actuacionesAdministrativas != null)
				{
					if(ab_suspension)
						setArchivo2(laa_actuacionesAdministrativas.getArchivo());
					else
						setArchivo(laa_actuacionesAdministrativas.getArchivo());

					lbd_beanDireccion.setDeshabilitarCorrespondencia(true);
					lbd_beanDireccion.setDeshabilitarResidencia(true);
					setDatosGuardados(true);
				}
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("visualizarCambiosResolucion", lb2be_e);

			addMessage(lb2be_e);
			actualizarComponente(is_messages);

			{
				ExternalContext lec_externalContext;

				lec_externalContext = FacesContext.getCurrentInstance().getExternalContext();

				if(lec_externalContext != null)
				{
					Flash lf_flash;

					lf_flash = lec_externalContext.getFlash();

					if(lf_flash != null)
						lf_flash.setKeepMessages(true);
				}
			}
		}

		{
			setImagenDocumento2(
			    generarArchivosDescarga(
			        getArchivo2(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);
			setImagenDocumento(
			    generarArchivosDescarga(
			        getArchivo(), TipoContenidoCommon.PDF, IdentificadoresCommon.AUTO + ExtensionCommon.PDF_PUNTO
			    )
			);
		}

		return NavegacionCommon.ANALISIS_TRASLADOS_DETALLE;
	}

	/**
	 * Retorna el valor del objeto de String.
	 *
	 * @return devuelve el valor de String
	 */
	public String volver()
	{
		return NavegacionCommon.DETALLE_ACTO;
	}

	/**
	 * Metodo encargado de convertir los textos capturados por pantalla.
	 * @param aot_oficiosTexto Argumento de tipo <code>OficiosTexto</code> que contiene los criterios a convertir.
	 * @return Objeto de tipo <code>OficiosTexto</code> que contiene los criterios convertidos.
	 * @throws B2BException Se genera cuando se presenta una excepcion.
	 */
	protected OficiosTexto convertirOficiosTexto(OficiosTexto aot_oficiosTexto)
	    throws B2BException
	{
		OficiosTexto lot_oficiosTexto;

		lot_oficiosTexto = null;

		try
		{
			if(aot_oficiosTexto != null)
			{
				lot_oficiosTexto = new OficiosTexto();

				lot_oficiosTexto.setEncabezado(convertirTextoParaRtf(aot_oficiosTexto.getEncabezado()));
				lot_oficiosTexto.setAntecedentes(convertirTextoParaRtf(aot_oficiosTexto.getAntecedentes()));
				lot_oficiosTexto.setConsideracion(convertirTextoParaRtf(aot_oficiosTexto.getConsideracion()));
				lot_oficiosTexto.setRazon1(convertirTextoParaRtf(aot_oficiosTexto.getRazon1()));
				lot_oficiosTexto.setRazon2(convertirTextoParaRtf(aot_oficiosTexto.getRazon2()));
				lot_oficiosTexto.setAnalisisProbatorio(convertirTextoParaRtf(aot_oficiosTexto.getAnalisisProbatorio()));
				lot_oficiosTexto.setPertinencia(convertirTextoParaRtf(aot_oficiosTexto.getPertinencia()));
				lot_oficiosTexto.setIntervencionInteresados(
				    convertirTextoParaRtf(aot_oficiosTexto.getIntervencionInteresados())
				);
				lot_oficiosTexto.setPruebasRecaudadas(convertirTextoParaRtf(aot_oficiosTexto.getPruebasRecaudadas()));
				lot_oficiosTexto.setResuelve(convertirTextoParaRtf(aot_oficiosTexto.getResuelve()));
				lot_oficiosTexto.setArgumentosRecurrente(
				    convertirTextoParaRtf(aot_oficiosTexto.getArgumentosRecurrente())
				);
				lot_oficiosTexto.setDiasSuspension(aot_oficiosTexto.getDiasSuspension());
				lot_oficiosTexto.setDiasLetras(aot_oficiosTexto.getDiasLetras());
				lot_oficiosTexto.setIdTurnoHistoria(aot_oficiosTexto.getIdTurnoHistoria());
				lot_oficiosTexto.setFechaInicioTraslado(aot_oficiosTexto.getFechaInicioTraslado());
				lot_oficiosTexto.setFechaFinTraslado(aot_oficiosTexto.getFechaFinTraslado());
				lot_oficiosTexto.setReferencia(convertirTextoParaRtf(aot_oficiosTexto.getReferencia()));
				lot_oficiosTexto.setDecisionTramiteRegistral(convertirTextoParaRtf(getDecisionTramiteRegistral()));
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("convertirOficiosTexto", lb2be_e);

			throw lb2be_e;
		}

		return lot_oficiosTexto;
	}
}
