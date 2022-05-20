package com.bachue.snr.prosnr01.ejb.session.stateless.entrega;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.business.entrega.EntregaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.entrega.DatosPredioTurnoEntrega;
import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.entrega.TramiteTurno;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.reimpresion.ReimpresionRecibos;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaEntrega;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalReimpresion;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import org.perf4j.StopWatch;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades EntregaBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Entrega", mappedName = "entregaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class EntregaBean implements EntregaRemote
{
	/** Propiedad ieb business. */
	private EntregaBusiness ieb_business;

	/** {@inheritdoc} */
	public void actualizarEstadoImpresion(
	    DocumentosSalida ads_documento, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().actualizarEstadoImpresion(ads_documento, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "actualizarEstadoImpresion", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void actualizarInterviene(
	    String as_interviene, String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().actualizarInterviene(as_interviene, as_idTurno, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "EntregaBusiness", "actualizarInterviene", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumental> actualizarTiposDocumentales(
	    Solicitud as_solicitud, Collection<TipoDocumental> actd_tiposDocumentales, String as_constanteApoderado,
	    String as_constanteTercero, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoDocumental> lctd_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lctd_datos     = getBusiness()
				                 .actualizarTiposDocumentales(
				    as_solicitud, actd_tiposDocumentales, as_constanteApoderado, as_constanteTercero
				);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "actualizarTiposDocumentales", as_userId, as_localIp, as_remoteIp, lctd_datos
		);

		return lctd_datos;
	}

	/** {@inheritdoc} */
	public Collection<CausalReimpresion> cargarCausalReimpresion(
	    String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<CausalReimpresion> lccr_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lccr_datos = getBusiness().cargarCausalReimpresion();

		Logger.log(
		    lsw_watch, "EntregaBusiness", "cargarCausalReimpresion", as_userId, as_localIp, as_remoteIp, lccr_datos
		);

		return lccr_datos;
	}

	/** {@inheritdoc} */
	public Persona cargarDatosPersonaReimpresion(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Persona   lp_retorno;

		lsw_watch      = Logger.getNewStopWatch();
		lp_retorno     = getBusiness().cargarDatosPersonaReimpresion(as_idTurno);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "cargarDatosPersonaReimpresion", as_userId, as_localIp, as_remoteIp, null
		);

		return lp_retorno;
	}

	/** {@inheritdoc} */
	public Collection<DocumentosSalida> cargarDocumentosAReimprimir(
	    String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<DocumentosSalida> lcds_documentos;

		lsw_watch           = Logger.getNewStopWatch();
		lcds_documentos     = getBusiness().cargarDocumentosAReimprimir(as_idTurno);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "cargarDocumentosAReimprimir", as_idUsuario, as_localIp, as_remoteIp,
		    lcds_documentos
		);

		return lcds_documentos;
	}

	/** {@inheritdoc} */
	public Collection<DocumentosSalida> cargarDocumentosImpresion(
	    String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<DocumentosSalida> lcds_documentos;

		lsw_watch           = Logger.getNewStopWatch();
		lcds_documentos     = getBusiness().cargarDocumentosImpresion(as_idTurno);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "cargarDocumentosImpresion", as_idUsuario, as_localIp, as_remoteIp,
		    lcds_documentos
		);

		return lcds_documentos;
	}

	/** {@inheritdoc} */
	public Collection<TramiteTurno> cargarDocumentosReimpresion(
	    String as_idTurno, String as_nir, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<TramiteTurno> lcds_documentos;

		lsw_watch           = Logger.getNewStopWatch();
		lcds_documentos     = getBusiness().cargarDocumentosReimpresion(as_idTurno, as_nir);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "cargarDocumentosRempresion", as_idUsuario, as_localIp, as_remoteIp,
		    lcds_documentos
		);

		return lcds_documentos;
	}

	/** {@inheritdoc} */
	public Collection<ReimpresionRecibos> cargarRecibosCajaLiquidacion(
	    String as_idTurno, String as_nir, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<ReimpresionRecibos> lcr_documentos;

		lsw_watch          = Logger.getNewStopWatch();
		lcr_documentos     = getBusiness().cargarRecibosCajaLiquidacion(as_idTurno, as_nir);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "cargarRecibosCajaLiquidacion", as_idUsuario, as_localIp, as_remoteIp,
		    lcr_documentos
		);

		return lcr_documentos;
	}

	/** {@inheritdoc} */
	public Entrega cargarTurno(
	    TramiteTurno att_param, long al_etapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Entrega   le_retorno;

		lsw_watch      = Logger.getNewStopWatch();
		le_retorno     = getBusiness().cargarTurno(att_param, al_etapa);

		Logger.log(lsw_watch, "EntregaBusiness", "cargarTurno", as_userId, as_localIp, as_remoteIp, null);

		return le_retorno;
	}

	/** {@inheritdoc} */
	public void devolverACalificacion(
	    TurnoHistoria ath_th, String as_decisionEntrega, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().devolverACalificacion(ath_th, as_decisionEntrega, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "EntregaBusiness", "devolverACalificacion", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void eliminarDatosPersonaTercero(
	    PersonaEntrega ape_entrega, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().eliminarDatosPersonaTercero(ape_entrega);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "eliminarDatosPersonaTercero", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void eliminarTipoDocumental(
	    TipoDocumental atd_tipoDocumental, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().eliminarTipoDocumental(atd_tipoDocumental);

		Logger.log(lsw_watch, "EntregaBusiness", "eliminarTipoDocumental", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<String> findCorreosByIdPersona(SolicitudInterviniente asi_interSelected, String as_userId)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcsi_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcsi_datos     = getBusiness().findCorreosByIdPersona(asi_interSelected);

		Logger.log(lsw_watch, "EntregaBusiness", "findCorreosByIdPersona", as_userId, null, null, lcsi_datos);

		return lcsi_datos;
	}

	/** {@inheritdoc} */
	public Persona findDatosPersonaTercero(
	    String as_idPersona, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Persona   lp_persona;

		lsw_watch      = Logger.getNewStopWatch();
		lp_persona     = getBusiness().findDatosPersonaTercero(as_idPersona);

		Logger.log(lsw_watch, "EntregaBusiness", "findDatosPersonaTercero", as_userId, as_localIp, as_remoteIp, null);

		return lp_persona;
	}

	/** {@inheritdoc} */
	public Collection<TramiteTurno> findDetailDelivery(
	    String as_userId, String as_localIp, String as_remoteIp, long idState, String as_nir, String as_idTurno
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<TramiteTurno> lctt_turnos;

		lsw_watch       = Logger.getNewStopWatch();
		lctt_turnos     = getBusiness().findDetailDelivery(idState, as_nir, as_idTurno, as_userId);

		Logger.log(lsw_watch, "EntregaBusiness", "findDetailDelivery", as_userId, as_localIp, as_remoteIp, lctt_turnos);

		return lctt_turnos;
	}

	/** {@inheritdoc} */
	public Collection<TramiteTurno> findDetailDelivery(
	    String as_userId, String as_localIp, String as_remoteIp, long idState, String as_nir, String as_idTurno,
	    boolean ab_notificaciones
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		Collection<TramiteTurno> lctt_turnos;

		lsw_watch       = Logger.getNewStopWatch();
		lctt_turnos     = getBusiness().findDetailDelivery(idState, as_nir, as_idTurno, ab_notificaciones, as_userId);

		Logger.log(lsw_watch, "EntregaBusiness", "findDetailDelivery", as_userId, as_localIp, as_remoteIp, lctt_turnos);

		return lctt_turnos;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> findInboxByTurnoNir(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idTurno, String as_nir
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().findInboxByTurnoNir(as_idTurno, as_nir);

		Logger.log(lsw_watch, "EntregaBusiness", "findInboxByUserId", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> findInboxByTurnoNirCorrespondencia(
	    TramiteCantidad atc_tc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lctc_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lctc_datos     = getBusiness().findInboxByTurnoNirCorrespondencia(atc_tc);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "findInboxByTurnoNirCorrespondencia", as_userId, as_localIp, as_remoteIp,
		    lctc_datos
		);

		return lctc_datos;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findMatriculasBySolicitudCirculo(
	    SolicitudMatricula at_param, String as_userId
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lr_response;

		lsw_watch       = Logger.getNewStopWatch();
		lr_response     = getBusiness().findMatriculasBySolicitudCirculo(at_param);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "findMatriculasBySolicitudCirculo", as_userId, null, null, lr_response
		);

		return lr_response;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findMatriculasBySolicitudCirculoTurno(
	    SolicitudMatricula asm_parametros, String as_userId, String as_localIp, String ls_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lcsm_datos;

		lsw_watch      = Logger.getNewStopWatch();
		lcsm_datos     = getBusiness().findMatriculasBySolicitudCirculoTurno(asm_parametros);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "findMatriculasBySolicitudCirculoTurno", as_userId, as_localIp, ls_remoteIp,
		    lcsm_datos
		);

		return lcsm_datos;
	}

	/** {@inheritdoc} */
	public Collection<PersonaEntrega> findPersonaEntregaBySolicitud(
	    PersonaEntrega ape_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<PersonaEntrega> lcpe_retorno;

		lsw_watch        = Logger.getNewStopWatch();
		lcpe_retorno     = getBusiness().findPersonaEntregaBySolicitud(ape_data);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "findPersonaEntregaBySolicitud", as_userId, as_localIp, as_remoteIp,
		    lcpe_retorno
		);

		return lcpe_retorno;
	}

	/** {@inheritdoc} */
	public DatosPredioTurnoEntrega findPredioDocumentoByTurnoEntrega(
	    String as_userId, DatosPredioTurnoEntrega adpte_idTurno, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		DatosPredioTurnoEntrega lr_retorno;

		lsw_watch      = Logger.getNewStopWatch();
		lr_retorno     = getBusiness().findDatosPredioByTurnoEntrega(adpte_idTurno);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "findPredioDocumentoByTurnoEntrega", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_retorno;
	}

	/** {@inheritdoc} */
	public Solicitud findSolicitudById(Solicitud at_turno, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud lt_turno;

		lsw_watch     = Logger.getNewStopWatch();
		lt_turno      = getBusiness().findSolicitudById(at_turno);

		Logger.log(lsw_watch, "EntregaBusiness", "findSolicitudById", as_userId, null, null, null);

		return lt_turno;
	}

	/** {@inheritdoc} */
	public Turno findTurnoById(Turno at_turno, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Turno     lt_turno;

		lsw_watch     = Logger.getNewStopWatch();
		lt_turno      = getBusiness().findTurnoById(at_turno);

		Logger.log(lsw_watch, "EntregaBusiness", "findTurnoById", as_userId, null, null, null);

		return lt_turno;
	}

	/** {@inheritdoc} */
	public byte[] generarArchivoZip(
	    DocumentosSalida at_param, String as_userId, boolean ab_activos, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_response;

		lsw_watch        = Logger.getNewStopWatch();
		lba_response     = getBusiness().generarArchivoZip(at_param, ab_activos);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "findMatriculasBySolicitudCirculo", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_response;
	}

	/** {@inheritdoc} */
	public byte[] generarArchivoZipEntregaRelacion(Map<String, byte[]> aba_ba)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_zip;

		lsw_watch     = Logger.getNewStopWatch();
		lba_zip       = getBusiness().generarArchivoZipEntregaRelacion(aba_ba);

		Logger.log(lsw_watch, "AprobacionBean", "generarArchivoZipEntregaRelacion", null, null, null, null);

		return lba_zip;
	}

	/** {@inheritdoc} */
	public byte[] generarDocumentoEntrega(
	    ObtenerFirmaDTO aefg_param, String as_idTurno, Persona ap_personaEntrega, boolean ab_interviene,
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws Exception
	{
		StopWatch lsw_watch;
		byte[]    lba_documento;

		lsw_watch         = Logger.getNewStopWatch();
		lba_documento     = getBusiness()
				                    .generarDocumentoEntrega(
				    aefg_param, as_idTurno, ap_personaEntrega, ab_interviene, as_idUsuario, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "generarDocumentoEntrega", as_idUsuario, as_localIp, as_remoteIp, null
		);

		return lba_documento;
	}

	/** {@inheritdoc} */
	public byte[] generarDocumentoReimpresion(
	    ObtenerFirmaDTO aefg_param, String as_idTurno, Persona ap_persona, Collection<DocumentosSalida> acds_docs,
	    String as_idUsuario, String as_localIp, String as_ipRemota
	)
	    throws Exception
	{
		StopWatch lsw_watch;
		byte[]    lba_documento;

		lsw_watch         = Logger.getNewStopWatch();
		lba_documento     = getBusiness()
				                    .generarDocumentoReimpresion(
				    aefg_param, as_idTurno, ap_persona, acds_docs, as_idUsuario, as_ipRemota
				);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "generarDocumentoReimpresion", as_idUsuario, as_localIp, as_ipRemota, null
		);

		return lba_documento;
	}

	/** {@inheritdoc} */
	public Map<String, byte[]> generarDocumentoRelacionEntrega(
	    Collection<TramiteTurno> actt_tramitesTurno, boolean ab_firmar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, Exception
	{
		StopWatch           lsw_watch;
		Map<String, byte[]> llhm_documentos;

		lsw_watch           = Logger.getNewStopWatch();
		llhm_documentos     = getBusiness()
				                      .generarDocumentoRelacionEntrega(
				    actt_tramitesTurno, ab_firmar, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "EntregaBean", "generarDocumentoRelacionAprobacion", as_userId, as_localIp, as_remoteIp, null
		);

		return llhm_documentos;
	}

	/** {@inheritdoc} */
	public void guardarDocumentoOWCC(
	    String as_idTurno, ObtenerFirmaDTO aofdto_firma, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().guardarDocumentoOWCC(as_idTurno, aofdto_firma, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "EntregaBusiness", "guardarDocumentoOWCC", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public PersonaEntrega salvarDatosIntervininete(
	    Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		PersonaEntrega lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().salvarDatosIntervininete(ar_registro, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "EntregaBusiness", "salvarDatosIntervininete", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public void salvarEntrega(Entrega ae_entrega, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarEntrega(ae_entrega, as_idUsuario, as_remoteIp);

		Logger.log(lsw_watch, "EntregaBusiness", "salvarEntrega", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarImpresionDocumentosCorrespondencia(
	    TurnoHistoria ath_turnoHistoria, String as_remoteIp, String as_localIp, String as_userId
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarImpresionDocumentosCorrespondencia(ath_turnoHistoria, as_remoteIp, as_userId);

		Logger.log(lsw_watch, "EntregaBusiness", "guardarDocumentoOWCC", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarReimpresionDocumentos(
	    Collection<DocumentosSalida> acds_reimpresion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarReimpresionDocumentos(acds_reimpresion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "salvarReimpresionDocumentos", as_userId, as_localIp, as_remoteIp,
		    acds_reimpresion
		);
	}

	/** {@inheritdoc} */
	public void salvarReimpresionRecibos(
	    ReimpresionRecibos arr_reimpresion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarReimpresionRecibos(arr_reimpresion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "EntregaBusiness", "salvarReimpresionRecibos", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<TipoDocumental> salvarTiposDocumentales(
	    Collection<TipoDocumental> acacd_tiposDocumentales, String as_idTurno, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<TipoDocumental> lctd_return;

		lsw_watch       = Logger.getNewStopWatch();
		lctd_return     = getBusiness()
				                  .salvarTiposDocumentales(acacd_tiposDocumentales, as_idTurno, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "EntregaBusiness", "salvarTiposDocumentales", as_userId, as_localIp, as_remoteIp, lctd_return
		);

		return lctd_return;
	}

	/**
	 * Obtiene un <code>EntregaBusiness</code>.
	 *
	 * @return <code>EntregaBusiness</code>
	 */
	private EntregaBusiness getBusiness()
	{
		if(ieb_business == null)
			ieb_business = new EntregaBusiness();

		return ieb_business;
	}
}
