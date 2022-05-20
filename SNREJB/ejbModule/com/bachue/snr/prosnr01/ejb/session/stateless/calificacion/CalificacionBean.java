package com.bachue.snr.prosnr01.ejb.session.stateless.calificacion;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;

import com.bachue.snr.prosnr01.business.calificacion.CalificacionBusiness;
import com.bachue.snr.prosnr01.business.calificacion.RegistroCalificacionBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.MatriculaBase;
import com.bachue.snr.prosnr01.model.calificacion.Calificacion;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionAnotacion;
import com.bachue.snr.prosnr01.model.calificacion.ComplementacionCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConfrontacionCorrectiva;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionAntiguoSistema;
import com.bachue.snr.prosnr01.model.calificacion.ConsultaCriteriosCalificacionDocumento;
import com.bachue.snr.prosnr01.model.calificacion.EliminarAnotacion;
import com.bachue.snr.prosnr01.model.calificacion.LinderoRegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.RegistroParcialCalificacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.calificacion.ValidacionAlertaTurno;
import com.bachue.snr.prosnr01.model.registro.DataReproduccionConstancia;
import com.bachue.snr.prosnr01.model.registro.Registro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadAnotacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccSalvedadPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.Acto;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionCancelacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;
import com.bachue.snr.prosnr01.model.sdb.acc.RegistroPagoExceso;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudCamposCorreccion;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudInterviniente;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.TmpSolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.AreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.DireccionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.pgn.CausalCorreccion;
import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;
import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.ui.AccAreaUI;
import com.bachue.snr.prosnr01.model.ui.PermisosCorreccionesUI;
import com.bachue.snr.prosnr01.model.ui.SolicitudApoyoNacionalUI;
import com.bachue.snr.prosnr01.model.ui.UsuarioActividadUI;

import org.perf4j.StopWatch;

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades CalificacionBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "Calificacion", mappedName = "calificacionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CalificacionBean implements CalificacionRemote
{
	/** Propiedad irb business. */
	private CalificacionBusiness irb_business;

	/** Propiedad irb registro calificacion. */
	private RegistroCalificacionBusiness irb_registroCalificacion;

	/** {@inheritdoc} */
	public Persona getDatosUsuarioCalificador(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Persona   lp_persona;

		lsw_watch     = Logger.getNewStopWatch();

		lp_persona = getBusiness().getDatosUsuarioCalificador(as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "getDatosUsuarioCalificador", as_userId, as_localIp, as_remoteIp, null
		);

		return lp_persona;
	}

	/** {@inheritdoc} */
	@Override
	public Collection<Map<String, Object>> getIndicadoresByIdTurno(String as_idTurno)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		Collection<Map<String, Object>> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().getIndicadoresByIdTurno(as_idTurno);

		Logger.log(lsw_watch, "CalificacionBusiness", "getIndicadoresByIdTurno", null, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public String getMensajeError(String as_userId, String as_localIp, String as_remoteIp)
	{
		StopWatch lsw_watch;
		String    ls_mensajeError;

		lsw_watch     = Logger.getNewStopWatch();

		ls_mensajeError = getBusiness().getMensajeError();

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "findSolicitudByIdTurno", as_userId, as_localIp, as_remoteIp, null
		);

		return ls_mensajeError;
	}

	/** {@inheritdoc} */
	public Collection<String> getTipoDocPersonaByMatricula(Long al_idMatricula, String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		Collection<String> lcs_tipoDocumentos;

		lsw_watch     = Logger.getNewStopWatch();

		lcs_tipoDocumentos = getBusiness().getTipoDocPersonaByMatricula(al_idMatricula, as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "getTipoDocPersonaByMatricula", null, null, null, null);

		return lcs_tipoDocumentos;
	}

	/** {@inheritdoc} */
	public int accionSalvarView(
	    AccLinderoPredio alp_linderoPredio, AccComplementacionPredio aacp_complementacionPredio,
	    String as_tipoComplementacion, ComplementacionPredio acp_complementacionPredio, AccPredioRegistro aapr_pr,
	    AccAreaUI aaui_areaPredio, Collection<DireccionDelPredio> acddp_temp, String as_idTurnoHistoria,
	    String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		int       li_secuenciaBng;

		lsw_watch     = Logger.getNewStopWatch();

		li_secuenciaBng = getregistroCalificacionBusiness()
				                  .accionSalvarView(
				    alp_linderoPredio, aacp_complementacionPredio, as_tipoComplementacion, acp_complementacionPredio,
				    aapr_pr, aaui_areaPredio, acddp_temp, as_idTurnoHistoria, as_userId, as_localIp, as_remoteIp
				);

		Logger.log(lsw_watch, "CalificacionBusiness", "accionSalvarView", as_userId, as_localIp, as_remoteIp, null);

		return li_secuenciaBng;
	}

	/** {@inheritdoc} */
	public AccPredioRegistro actualizacionMasiva(
	    AccPredioRegistro apr_pr, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		AccPredioRegistro lpr_pr;

		lsw_watch     = Logger.getNewStopWatch();
		lpr_pr        = getBusiness().actualizacionMasiva(apr_pr, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "actualizacionMasiva", as_userId, as_localIp, as_remoteIp, null);

		return lpr_pr;
	}

	/** {@inheritdoc} */
	@Override
	public void actualizarAreas(
	    AccAreaPredio acc_predio, DireccionPredioAcc adp_direccion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().actualizarAreas(acc_predio, adp_direccion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "actualizarAreas", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<Anotacion> actualizarListaIntervinientes(
	    Persona ap_persona, Collection<Anotacion> aca_cllAnotacion, SolicitudInterviniente asi_solicitudInterviniente
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Anotacion> lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness()
				              .actualizarListaIntervinientes(ap_persona, aca_cllAnotacion, asi_solicitudInterviniente);

		Logger.log(lsw_watch, "CalificacionBean", "actualizarListaIntervinientes", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public void actualizarRevision(
	    AccPredioRegistro aottf_data, RegistroCalificacion arc_data, boolean ab_b, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().actualizarRevision(aottf_data, arc_data, ab_b);

		Logger.log(lsw_watch, "CalificacionBean", "actualizarRevision", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<MatriculaBase> agregarMatriculaComplementacion(
	    Collection<MatriculaBase> acmb_matriculas, ComplementacionCalificacion acc_complementacionCalificacion,
	    RegistroCalificacion arc_registroCalificacion, String as_matricula, boolean ab_primerVez, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		Collection<MatriculaBase> lcmb_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcmb_return     = getregistroCalificacionBusiness()
				                  .agregarMatriculaComplementacion(
				    acmb_matriculas, acc_complementacionCalificacion, arc_registroCalificacion, as_matricula,
				    ab_primerVez
				);

		Logger.log(
		    lsw_watch, "CalificacionBean", "agregarMatriculaComplementacion", as_userId, as_localIp, as_remoteIp,
		    acmb_matriculas
		);

		return lcmb_return;
	}

	/** {@inheritdoc} */
	public Map<String, Object[]> alertasIntervinientesBaldios(
	    String as_idPersona, boolean ab_accion, String as_tipoActo
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Map<String, Object[]> lmso_return;

		lsw_watch       = Logger.getNewStopWatch();
		lmso_return     = getregistroCalificacionBusiness()
				                  .alertasIntervinientesBaldios(as_idPersona, ab_accion, as_tipoActo);

		Logger.log(lsw_watch, "CalificacionBean", "alertasIntervinientesBaldios", null, null, null, null);

		return lmso_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion aperturarMatriculasSegregacion(
	    RegistroCalificacion arc_data, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getBusiness().aperturarMatriculasSegregacion(arc_data, as_userId, as_remoteIpAddress);

		Logger.log(lsw_watch, "CalificacionBean", "aperturarMatriculasSegregacion", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public SolicitudApoyoNacionalUI aprobarReasignacionApoyoNacional(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNacional, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		SolicitudApoyoNacionalUI lsanui_return;

		lsw_watch         = Logger.getNewStopWatch();
		lsanui_return     = getregistroCalificacionBusiness()
				                    .aprobarReasignacionApoyoNacional(
				    asanui_solicitudApoyoNacional, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "CalificacionBean", "aprobarReasignacionApoyoNacional", null, null, null, null);

		return lsanui_return;
	}

	/** {@inheritdoc} */
	public boolean archivoGenerado(String as_idTurnoHistoria, boolean ab_registroParcial)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_b;

		lsw_watch     = Logger.getNewStopWatch();
		lb_b          = getregistroCalificacionBusiness().archivoGenerado(as_idTurnoHistoria, ab_registroParcial);

		Logger.log(lsw_watch, "CalificacionBean", "archivoGenerado", null, null, null, null);

		return lb_b;
	}

	/** {@inheritdoc} */
	public String calcularArea(RegistroCalificacion arc_tmp, Collection<AreaPredio> acap_ap)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = null;

		if((arc_tmp != null) && CollectionUtils.isValidCollection(acap_ap))
			lb_return = getBusiness().calcularArea(arc_tmp, acap_ap);

		Logger.log(lsw_watch, "CalificacionBean", "calcularArea", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public Collection<RegistroCalificacion> cargarAnotacionProceso(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<RegistroCalificacion> lcrc_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcrc_return     = getregistroCalificacionBusiness().cargarAnotacionProceso(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarAnotacionProceso", null, null, null, null);

		return lcrc_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion cargarAnotacionesEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getregistroCalificacionBusiness().cargarAnotacionesEnglobes(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarAnotacionesEnglobes", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public PermisosCorreccionesUI cargarCheksCorrecciones(
	    SolicitudCamposCorreccion ascc_camposCorreccion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch              lsw_watch;
		PermisosCorreccionesUI lpcui_return;

		lsw_watch        = Logger.getNewStopWatch();
		lpcui_return     = getBusiness()
				                   .cargarCheksCorrecciones(ascc_camposCorreccion, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "cargarCheksCorrecciones", null, null, null, null);

		return lpcui_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion cargarCierreFolio(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getregistroCalificacionBusiness().cargarCierreFolio(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarCierreFolio", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public AccComplementacionPredio cargarComplementacion(AccComplementacionPredio acp_cp, String as_userId)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		AccComplementacionPredio lrc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_return = getregistroCalificacionBusiness().cargarComplementacion(acp_cp, as_userId);

		Logger.log(lsw_watch, "CalificacionBean", "cargarComplementacion", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public ComplementacionCalificacion cargarComplementacionEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		ComplementacionCalificacion lcc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lcc_return     = getBusiness().cargarComplementacionEnglobes(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarComplementacionEnglobes", null, null, null, null);

		return lcc_return;
	}

	/** {@inheritdoc} */
	public Collection<RegistroCalificacion> cargarDatosAnotacionesACancelar(
	    AnotacionCancelacion aac_anotacionCancelacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                        lsw_watch;
		Collection<RegistroCalificacion> lcrc_registroCalificacion;

		lsw_watch     = Logger.getNewStopWatch();

		lcrc_registroCalificacion = getregistroCalificacionBusiness()
				                            .cargarDatosAnotacionesACancelar(aac_anotacionCancelacion);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "cargarDatosAnotacionesACancelar", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lcrc_registroCalificacion;
	}

	/** {@inheritdoc} */
	public AccAreaUI cargarDatosAreaEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch lsw_watch;
		AccAreaUI laaui_return;

		lsw_watch        = Logger.getNewStopWatch();
		laaui_return     = getregistroCalificacionBusiness().cargarDatosAreaEnglobes(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarDatosAreaEnglobes", null, null, null, null);

		return laaui_return;
	}

	/** {@inheritdoc} */
	public AccAreaUI cargarDatosAreaVenta(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch lsw_watch;
		AccAreaUI laaui_return;

		lsw_watch        = Logger.getNewStopWatch();
		laaui_return     = getregistroCalificacionBusiness().cargarDatosAreaVenta(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarDatosAreaVenta", null, null, null, null);

		return laaui_return;
	}

	/** {@inheritdoc} */
	public DatosBasicos cargarDatosBasicosDigitadorMasivo(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		DatosBasicos ldb_return;

		lsw_watch      = Logger.getNewStopWatch();
		ldb_return     = getBusiness().cargarDatosBasicosDigitadorMasivo(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarDatosBasicosDigitadorMasivo", null, null, null, null);

		return ldb_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion cargarDatosBasicosEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getregistroCalificacionBusiness().cargarDatosBasicosEnglobes(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarDatosBasicosEnglobes", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public Registro cargarDireccionNotificacion(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Registro  lr_return;

		lsw_watch     = Logger.getNewStopWatch();
		lr_return     = getregistroCalificacionBusiness().cargarDireccionNotificacion(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBean", "cargarDireccionNotificacion", null, null, null, null);

		return lr_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion cargarDireccionesEnglobes(RegistroCalificacion arc_matriculas, boolean ab_accion)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getBusiness().cargarDireccionesEnglobes(arc_matriculas, ab_accion);

		Logger.log(lsw_watch, "CalificacionBean", "cargarDireccionesEnglobes", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion cargarDireccionesPredioTemporal(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getBusiness().cargarDireccionesPredioTemporal(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarDireccionesPredioTemporal", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion cargarInfoEnglobeAnotacion(
	    Anotacion aa_anotacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getBusiness().cargarInfoEnglobeAnotacion(aa_anotacion);

		Logger.log(
		    lsw_watch, "CalificacionBean", "cargarInfoEnglobeAnotacion", as_userId, as_localIp, as_remoteIp, null
		);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public LinderoRegistroCalificacion cargarLinderos(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		LinderoRegistroCalificacion lrc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_return = getregistroCalificacionBusiness().cargarLinderos(lc_anotaciones);

		Logger.log(lsw_watch, "CalificacionBean", "cargarLinderos", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public LinderoRegistroCalificacion cargarLinderosDigitadorMasivo(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		LinderoRegistroCalificacion lrc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_return = getregistroCalificacionBusiness().cargarLinderosDigitadorMasivo(lc_anotaciones);

		Logger.log(lsw_watch, "CalificacionBean", "cargarLinderosDigitadorMasivo", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public LinderoRegistroCalificacion cargarLinderosEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		LinderoRegistroCalificacion llrc_return;

		lsw_watch       = Logger.getNewStopWatch();
		llrc_return     = getBusiness().cargarLinderosEnglobes(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarLinderosEnglobes", null, null, null, null);

		return llrc_return;
	}

	/** {@inheritdoc} */
	public OficinaOrigen cargarOficinaOrigenMedidaCautelar(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		OficinaOrigen lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getregistroCalificacionBusiness().cargarOficinaOrigenMedidaCautelar(as_idTurno);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "cargarOficinaOrigenMedidaCautelar", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public OficiosTexto cargarOficioTextoTraslados(String as_idTurno, String as_motivoTramite)
	    throws B2BException
	{
		StopWatch    lsw_watch;
		OficiosTexto lot_return;

		lsw_watch      = Logger.getNewStopWatch();
		lot_return     = getBusiness().cargarOficioTextoTraslados(as_idTurno, as_motivoTramite);

		Logger.log(lsw_watch, "CalificacionBusiness", "cargarOficioTextoTraslados", null, null, null, null);

		return lot_return;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> cargarPrediosEnglobeCorreccion(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacion, String as_userId, String as_localIp,
	    String                                                as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lcsm_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcsm_return     = getBusiness().cargarPrediosEnglobeCorreccion(aap_anotacion);

		Logger.log(lsw_watch, "CalificacionBean", "cargarPrediosEnglobeCorreccion", null, null, null, null);

		return lcsm_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion cargarVentaParcial(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getregistroCalificacionBusiness().cargarVentaParcial(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "cargarVentaParcial", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public boolean cierreFolio(Collection<PredioSegregado> acps_predios)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().cierreFolio(acps_predios);

		Logger.log(lsw_watch, "CalificacionBean", "cierreFolio", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void confirmarCantidadAperturarAnotacion(
	    Anotacion aa_anotacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().confirmarCantidadAperturarAnotacion(aa_anotacion, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "confirmarCantidadAperturarAnotacion", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public Anotacion confirmarMatriculasEnglobe(
	    String as_idCirculo, Long al_idMatricula, String as_idTurno, Anotacion aa_anotacion, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Anotacion la_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		la_return     = getBusiness()
				                .confirmarMatriculasEnglobe(
				    as_idCirculo, al_idMatricula, as_idTurno, aa_anotacion, as_userId, as_remoteIp
				);

		Logger.log(
		    lsw_watch, "CalificacionBean", "confirmarMatriculasEnglobe", as_userId, as_localIp, as_remoteIp, null
		);

		return la_return;
	}

	/** {@inheritdoc} */
	public Collection<ComplementacionAnotacion> construirComplementacionAnotaciones(
	    Collection<AnotacionPredio> lc_anotaciones
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		Collection<ComplementacionAnotacion> lc_complementacionAnotacion;

		lsw_watch     = Logger.getNewStopWatch();

		lc_complementacionAnotacion = getregistroCalificacionBusiness()
				                              .construirComplementacionAnotaciones(lc_anotaciones);

		Logger.log(lsw_watch, "CalificacionBean", "construirComplementacionAnotaciones", null, null, null, null);

		return lc_complementacionAnotacion;
	}

	/** {@inheritdoc} */
	public TurnoHistoria consultarTurnoHistoriaAnterior(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lth_datos = getBusiness().consultarTurnoHistoriaAnterior(ath_turnoHistoria);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "consultarTurnoHistoriaAnterior", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lth_datos;
	}

	/** {@inheritdoc} */
	public TurnoHistoria consultarTurnoMigrado(TurnoHistoria ath_turnoHistoria, String as_userId)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_turnoHistoria;

		lsw_watch     = Logger.getNewStopWatch();

		lth_turnoHistoria = getBusiness().consultarTurnoMigrado(ath_turnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "consultarTurnoMigrado", as_userId, null, null, null);

		return lth_turnoHistoria;
	}

	/** {@inheritdoc} */
	public Map<TurnoHistoria, Boolean> consultarTurnosDerivados(TurnoHistoria ath_turnoHistoria, String as_userId)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Map<TurnoHistoria, Boolean> lmtb_turnos;

		lsw_watch     = Logger.getNewStopWatch();

		lmtb_turnos = getBusiness().consultarTurnosDerivados(ath_turnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "consultarTurnosDerivados", as_userId, null, null, null);

		return lmtb_turnos;
	}

	/** {@inheritdoc} */
	public Anotacion crearAnotacionBaldios(
	    AccPredioRegistro aapr_predio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Anotacion la_return;
		StopWatch lsw_watch;

		la_return     = getregistroCalificacionBusiness().crearAnotacionBaldios(aapr_predio, as_userId, as_remoteIp);
		lsw_watch     = Logger.getNewStopWatch();

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "crearAnotacionBaldios", as_userId, as_localIp, as_remoteIp, null
		);

		return la_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion datalleAnotaciones(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().datalleAnotaciones(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "datalleAnotaciones", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion detalleIntervinientes(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().detalleIntervinientes(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "detalleIntervinientes", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public void eliminarActo(Acto aa_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().eliminarActo(aa_parametros);

		Logger.log(lsw_watch, "CalificacionBusiness", "eliminarActo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void eliminarAnotacion(RegistroCalificacion lorc_tmp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().eliminarAnotacion(lorc_tmp);

		Logger.log(lsw_watch, "CalificacionBean", "eliminarAnotacion", null, null, null, null);
	}

	/** {@inheritdoc} */
	public RegistroCalificacion eliminarDataPersona(SolicitudInterviniente asi_dataPersona, String as_userId)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().eliminarDataPersona(asi_dataPersona, as_userId);

		Logger.log(lsw_watch, "CalificacionBean", "eliminarDataPersona", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public void eliminarDireccionPredioAcc(
	    String as_idDireccionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().eliminarDireccionPredioAcc(as_idDireccionPredio);

		Logger.log(lsw_watch, "CalificacionBean", "eliminarDataPersona", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public String encontrarTurnosActuales(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_mensaje;

		lsw_watch     = Logger.getNewStopWatch();

		ls_mensaje = getBusiness().encontrarTurnosActuales(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "encontrarTurnosActuales", null, null, null, null);

		return ls_mensaje;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion enviarADigitador(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().enviarADigitador(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "enviarADigitador", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public void enviarADigitadorDesenglobe(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().enviarADigitadorDesenglobe(arc_data, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "enviarADigitadorDesenglobe", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void enviarADigitadorEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().enviarADigitadorEnglobes(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "enviarADigitadorEnglobes", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void enviarADigitadorEnglobesDevolucion(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		if(arc_data != null)
		{
			StopWatch lsw_watch;

			lsw_watch = Logger.getNewStopWatch();

			getregistroCalificacionBusiness().enviarADigitadorEnglobesDevolucion(arc_data, as_userId, as_remoteIp);

			Logger.log(
			    lsw_watch, "CalificacionBean", "enviarADigitadorEnglobesDevolucion", as_userId, as_localIp, as_remoteIp,
			    null
			);
		}
	}

	/** {@inheritdoc} */
	public void enviarAprobadorRealizarCorrecciones(
	    String as_observaciones, String as_idTurnoHistoria, boolean ab_secuenciaAgregada, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .enviarAprobadorRealizarCorrecciones(
			    as_observaciones, as_idTurnoHistoria, ab_secuenciaAgregada, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "CalificacionBean", "enviarAprobadorRealizarCorrecciones", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public void enviarAprobadorRepConstancia(
	    Calificacion ac_c, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarAprobadorRepConstancia(ac_c, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "enviarAprobadorRepConstancia", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void enviarAprobadorSecuenciaOActosHomologacion(
	    TurnoHistoria ath_th, boolean ab_aprobadorSecuencia, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarAprobadorSecuenciaOActosHomologacion(ath_th, ab_aprobadorSecuencia, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "enviarAprobadorSecuenciaOActosHomologacion", as_userId, as_localIp,
		    as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void enviarAprobarProrrogaDocumentos(
	    TurnoHistoria ath_th, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().enviarAprobarProrrogaDocumentos(ath_th, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "enviarAprobarProrrogaDocumentos", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void enviarResponsableActuacionesAdministrativas(
	    TurnoHistoria ath_turnoHistoria, MotivoTramite amt_motivoTramite, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .enviarResponsableActuacionesAdministrativas(
			    ath_turnoHistoria, amt_motivoTramite, as_userId, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "CalificacionBean", "enviarResponsableActuacionesAdministrativas", as_userId, as_localIp,
		    as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public boolean esProcesoBaldios(String as_idTurno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getregistroCalificacionBusiness().esProcesoBaldios(as_idTurno);

		Logger.log(lsw_watch, "CalificacionBean", "esProcesoBaldiosTH", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean esSegundaVezCalificacionLindero(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		Logger.log(lsw_watch, "CalificacionBean", "esSegundaVezCalificacionLindero", null, null, null, null);

		return getregistroCalificacionBusiness().esSegundaVezCalificacionLindero(ath_turnoHistoria);
	}

	/** {@inheritdoc} */
	public boolean existenAnotacionesInactivas(String as_idSolicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_existenAnotacionesInactivas;

		lsw_watch     = Logger.getNewStopWatch();

		lb_existenAnotacionesInactivas = getBusiness().existenAnotacionesInactivas(as_idSolicitud);

		Logger.log(lsw_watch, "CalificacionBusiness", "existenAnotacionesInactivas", null, null, null, null);

		return lb_existenAnotacionesInactivas;
	}

	/** {@inheritdoc} */
	public boolean existenAnotacionesInactivasByTurno(String as_idTurno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_existenAnotacionesInactivas;

		lsw_watch     = Logger.getNewStopWatch();

		lb_existenAnotacionesInactivas = getBusiness().existenAnotacionesInactivasByTurno(as_idTurno);

		Logger.log(lsw_watch, "CalificacionBusiness", "existenAnotacionesInactivasByTurno", null, null, null, null);

		return lb_existenAnotacionesInactivas;
	}

	/** {@inheritdoc} */
	public Collection<Acto> findActosByIdSolicitudCirculo(
	    String as_idSolicitud, String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		Collection<Acto> lca_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lca_datos = getBusiness().findActosByIdSolicitudCirculo(as_idSolicitud, as_idCirculo);

		Logger.log(
		    lsw_watch, "CalificacionBean", "findActosByIdSolicitudCirculo", as_userId, as_localIp, as_remoteIp,
		    lca_datos
		);

		return lca_datos;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurno(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                               lsw_watch;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lcdcas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdcas_return = getBusiness().findActosByIdTurno(as_idTurno);

		Logger.log(
		    lsw_watch, "CalificacionBean", "findActosByIdTurno", as_userId, as_localIp, as_remoteIp, lcdcas_return
		);

		return lcdcas_return;
	}

	/** {@inheritdoc} */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurnoIndMayorValor(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                               lsw_watch;
		Collection<com.bachue.snr.prosnr01.model.registro.Acto> lcdcas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdcas_return = getBusiness().findActosByIdTurnoIndMayorValor(as_idTurno);

		Logger.log(
		    lsw_watch, "CalificacionBean", "findActosByIdTurnoIndMayorValor", as_userId, as_localIp, as_remoteIp,
		    lcdcas_return
		);

		return lcdcas_return;
	}

	/** {@inheritdoc} */
	public Collection<Acto> findActosTurnoMatriculas(String as_idTurno)
	    throws B2BException
	{
		StopWatch        lsw_watch;
		Collection<Acto> lca_return;

		lsw_watch      = Logger.getNewStopWatch();
		lca_return     = getBusiness().findActosTurnoMatriculas(as_idTurno);

		Logger.log(lsw_watch, "CalificacionBean", "findActosTurno", null, null, null, null);

		return lca_return;
	}

	/** {@inheritdoc} */
	public Collection<DataConsultaAntSistema> findByDatosAntSistema(
	    ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<DataConsultaAntSistema> lcdcas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdcas_return = getBusiness().findByDatosAntSistema(lcccas_consultaCriteriosAntiguoSistema);

		Logger.log(lsw_watch, "CalificacionBean", "findByDatosAntSistema", null, null, null, null);

		return lcdcas_return;
	}

	/** {@inheritdoc} */
	public Collection<DataConsultaPorCriterio> findByDatosAntSistemaCalificacion(
	    ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException
	{
		StopWatch                           lsw_watch;
		Collection<DataConsultaPorCriterio> lcdcas_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcdcas_return = getBusiness().findByDatosAntSistemaCalificacion(lcccas_consultaCriteriosAntiguoSistema);

		Logger.log(lsw_watch, "CalificacionBean", "findByDatosAntSistemaCalificacion", null, null, null, null);

		return lcdcas_return;
	}

	/** {@inheritdoc} */
	public Collection<DataConsultaDatosDocumento> findByDatosDocumento(
	    ConsultaCriteriosCalificacionDocumento consultaCriteriosCalificacionDocumento
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		Collection<DataConsultaDatosDocumento> ll_return;

		lsw_watch     = Logger.getNewStopWatch();

		ll_return = getBusiness().findByDatosDocumento(consultaCriteriosCalificacionDocumento);

		Logger.log(lsw_watch, "CalificacionBean", "findByDatosDocumento", null, null, null, null);

		return ll_return;
	}

	/** {@inheritdoc} */
	@Override
	public String findByIdTurno(String idTurno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findByIdTurno(idTurno);

		Logger.log(lsw_watch, "CalificacionBusiness", "findByIdTurno", null, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Collection<CausalCorreccion> findCausalesCorreccionCalificacion(
	    SolicitudMatricula lsm_solMat, String as_solicitudNueva, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                    lsw_watch;
		Collection<CausalCorreccion> lccc_causales;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_causales = getBusiness().findCausalesCorreccionCalificacion(lsm_solMat, as_solicitudNueva);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "findCausalesCorreccionCalificacion", as_userId, as_localIp, as_remoteIp,
		    lccc_causales
		);

		return lccc_causales;
	}

	/** {@inheritdoc} */
	public Constantes findConstanteById(String as_constante, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lp_persona;

		lsw_watch     = Logger.getNewStopWatch();

		lp_persona = getBusiness().findConstanteById(as_constante);

		Logger.log(lsw_watch, "CalificacionBusiness", "findConstanteById", as_userId, as_localIp, as_remoteIp, null);

		return lp_persona;
	}

	/** {@inheritdoc} */
	public Collection<UsuarioActividadUI> findDataBandeja(UsuarioActividadUI auaiu_usuarioActividad)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<UsuarioActividadUI> lcth_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcth_return = getBusiness().findDataBandeja(auaiu_usuarioActividad);

		Logger.log(lsw_watch, "CalificacionBean", "findDataBandeja", null, null, null, null);

		return lcth_return;
	}

	/** {@inheritdoc} */
	public DatosAntSistema findDatosAntSistemaById(
	    String as_idDatosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		DatosAntSistema ldas_datosAntSistema;

		lsw_watch     = Logger.getNewStopWatch();

		ldas_datosAntSistema = getBusiness().findDatosAntSistemaById(as_idDatosAntSistema);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "findDatosAntSistemaById", as_userId, as_localIp, as_remoteIp, null
		);

		return ldas_datosAntSistema;
	}

	/** {@inheritdoc} */
	@Override
	public List<Map<String, Object>> findDetailInbox(String as_userId, String as_idTurno, String as_nir, Long idState)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().findDetailInbox(as_userId, as_idTurno, as_nir, idState);

		Logger.log(lsw_watch, "CalificacionBusiness", "findDetailInbox", as_userId, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public DetalleAntSistema findDetalleAntSistemaById(
	    DetalleAntSistema adas_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch         lsw_watch;
		DetalleAntSistema ldas_datosAntSistema;

		lsw_watch     = Logger.getNewStopWatch();

		ldas_datosAntSistema = getBusiness().findDetalleAntSistemaById(adas_detalle);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "findDetalleAntSistemaById", as_userId, as_localIp, as_remoteIp, null
		);

		return ldas_datosAntSistema;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion findDetalleMatriculas(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().findDetalleMatriculas(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "findDetalleMatriculas", null, null, null, null);

		return lorc_return;
	}

	/**
	 * Find direccion predio by id circulo matricula turno.
	 *
	 * @param al_idMatricula de al id matricula
	 * @param as_idCirculo de as id circulo
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de direccion predio acc
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionPredioAcc findDireccionPredioByIdCirculoMatriculaTurno(
	    Long al_idMatricula, String as_idCirculo, String as_idTurno, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DireccionPredioAcc ldb_return;

		lsw_watch      = Logger.getNewStopWatch();
		ldb_return     = getBusiness()
				                 .findDireccionPredioByIdCirculoMatriculaTurno(
				    al_idMatricula, as_idCirculo, as_idTurno
				);

		Logger.log(
		    lsw_watch, "CalificacionBean", "findDireccionPredioByIdCirculoMatriculaTurno", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ldb_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion findDocumentoByIdTurnoHistoria(
	    RegistroCalificacion arc_registroCalificacion, boolean findDocumentoByIdTurnoHistoria,
	    boolean ab_anotacionCancelada
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_return = getregistroCalificacionBusiness()
				             .findDocumentoByIdTurnoHistoria(
				    arc_registroCalificacion, findDocumentoByIdTurnoHistoria, ab_anotacionCancelada
				);

		Logger.log(lsw_watch, "RegistroBean", "findDocumentoByIdTurnoHistoria", null, null, null, null);

		return lrc_return;
	}

	/**
	 * Find documento by id turno historia.
	 *
	 * @param arc_registroCalificacion de arc registro calificacion
	 * @param findDocumentoByIdTurnoHistoria de find documento by id turno historia
	 * @return el valor de registro calificacion
	 * @throws B2BException de b 2 B exception
	 */
	public RegistroCalificacion findDocumentoByIdTurnoHistoria(
	    RegistroCalificacion arc_registroCalificacion, boolean findDocumentoByIdTurnoHistoria
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_return = getregistroCalificacionBusiness()
				             .findDocumentoByIdTurnoHistoria(arc_registroCalificacion, findDocumentoByIdTurnoHistoria);

		Logger.log(lsw_watch, "RegistroBean", "findDocumentoByIdTurnoHistoria", null, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	@Override
	public void validarActo0463(Collection<Map<String, Object>> acm_matriculas)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		getBusiness().validarActo0463(acm_matriculas);

		Logger.log(lsw_watch, "CalificacionBusiness", "validarActo0463", null, null, null, null);

	}
	
	/** {@inheritdoc} */
	@Override
	public String findIdSolicitudByIdTurnoHistoria(String idTurnoHistoria)
			throws B2BException
	{
		StopWatch lsw_watch;
		String    lr_registro;
		
		lsw_watch     = Logger.getNewStopWatch();
		
		lr_registro = getBusiness().findIdSolicitudByIdTurnoHistoria(idTurnoHistoria);
		
		Logger.log(lsw_watch, "CalificacionBusiness", "findSolicitudByIdTurnoHistoria", null, null, null, null);
		
		return lr_registro;
	}

	/** {@inheritdoc} */
	public TramiteCantidad findInboxByUserId(Turno at_t, long al_idMotivo)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		TramiteCantidad lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().findInboxByUserId(at_t, al_idMotivo);

		Logger.log(lsw_watch, "CalificacionBusiness", "findInboxByUserId", null, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Collection<Anotacion> findIntervinientes(Long as_idAnotacion, String as_circulo, Long as_matricula)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Collection<Anotacion> lcs_tipoDocumentos;

		lsw_watch     = Logger.getNewStopWatch();

		lcs_tipoDocumentos = getBusiness().findIntervinientes(as_idAnotacion, as_circulo, as_matricula);

		Logger.log(lsw_watch, "CalificacionBusiness", "findIntervinientes", null, null, null, null);

		return lcs_tipoDocumentos;
	}

	/** {@inheritdoc} */
	public String findLindero(LinderoRegistroCalificacion alrc_linderoRegistroCalificacion, boolean ab_accion)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().findLindero(alrc_linderoRegistroCalificacion, ab_accion);

		Logger.log(lsw_watch, "CalificacionBean", "findLindero", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion findMatriculas(
	    String as_idMatricula, String as_idTurnoHist, String as_idTurno, String as_userId, String as_ipRemote,
	    boolean ab_b, long al_etapaP
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch       = Logger.getNewStopWatch();
		lorc_return     = getregistroCalificacionBusiness()
				                  .findMatriculas(
				    as_idMatricula, as_idTurnoHist, as_idTurno, as_userId, as_ipRemote, ab_b, al_etapaP
				);

		Logger.log(lsw_watch, "CalificacionBean", "findMatriculas", as_userId, null, as_ipRemote, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findMatriculasByIdSolicitud(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lcsm_datos;

		lsw_watch     = Logger.getNewStopWatch();

		lcsm_datos = getBusiness().findMatriculasByIdSolicitud(as_idTurno, as_userId, as_localIp, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "findMatriculasByIdSolicitud", as_userId, as_localIp, as_remoteIp, lcsm_datos
		);

		return lcsm_datos;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion findMatriculasInfByTurno(String as_idTurno, String as_userId, String as_remoteIp)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_rc;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_rc = getregistroCalificacionBusiness().findMatriculasInfByTurno(as_idTurno, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "guardarfindMatriculasInfByTurnoInfoDigitador", as_userId, null, as_remoteIp,
		    null
		);

		return lorc_rc;
	}

	/** {@inheritdoc} */
	@Override
	public RegistroCalificacion findMatriculasInformacion(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().findMatriculasInformacion(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "findMatriculasInformacion", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public String findObservacionesByIdTurnoHistoria(String idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_observaciones;

		lsw_watch     = Logger.getNewStopWatch();

		ls_observaciones = getBusiness().findObservacionesByIdTurnoHistoria(idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "findObservacionesByIdTurnoHistoria", null, null, null, null);

		return ls_observaciones;
	}

	/** {@inheritdoc} */
	public Persona findPersona(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Persona   lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().findPersona(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBean", "findPersona", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public List<Map<String, Object>> findPredioDocumentoByTurno(String as_userId, String as_idTurno, String as_section)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().findDatosPredioByTurno(as_userId, as_idTurno, as_section, null);

		Logger.log(lsw_watch, "CalificacionBusiness", "findPredioDocumentoByTurno", as_idTurno, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public List<Map<String, Object>> findPredioDocumentoByTurno(
	    String as_userId, String as_idTurno, String as_section, Date ad_fechaDocumento
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		List<Map<String, Object>> lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().findDatosPredioByTurno(as_userId, as_idTurno, as_section, ad_fechaDocumento);

		Logger.log(lsw_watch, "CalificacionBusiness", "findPredioDocumentoByTurno", as_idTurno, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public Collection<PredioRegistro> findPredioRegistro(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		Collection<PredioRegistro> lcpr_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcpr_return     = getBusiness().findPredioRegistro(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBean", "findPredioRegistro", null, null, null, null);

		return lcpr_return;
	}

	/** {@inheritdoc} */
	public RegistroPagoExceso findRegistroPagoExcesoByTurno(String as_idTurno)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		RegistroPagoExceso lrpe_rpe;

		lsw_watch     = Logger.getNewStopWatch();
		lrpe_rpe      = getBusiness().findRegistroPagoExcesoByTurno(as_idTurno);

		Logger.log(lsw_watch, "CalificacionBusiness", "findRegistroPagoExcesoByTurno", as_idTurno, null, null, null);

		return lrpe_rpe;
	}

	/** {@inheritdoc} */
	public Solicitud findSolicitudByIdTurno(String as_idTurno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud ls_solicitud;

		lsw_watch     = Logger.getNewStopWatch();

		ls_solicitud = getBusiness().findSolicitudByIdTurno(as_idTurno);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "findSolicitudByIdTurno", as_userId, as_localIp, as_remoteIp, null
		);

		return ls_solicitud;
	}

	/** {@inheritdoc} */
	public Solicitud findSolicitudByIdTurnoHistoria(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud ls_solicitudReturn;

		lsw_watch     = Logger.getNewStopWatch();

		ls_solicitudReturn = getregistroCalificacionBusiness().findSolicitudByIdTurnoHistoria(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBean", "findSolicitudByIdTurnoHistoria", null, null, null, null);

		return ls_solicitudReturn;
	}

	/** {@inheritdoc} */
	public Solicitud findSolicitudByTH(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Solicitud ls_solicitud;

		lsw_watch     = Logger.getNewStopWatch();

		ls_solicitud = getBusiness().findSolicitudByTH(ath_turnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "findSolicitudByTH", as_userId, as_localIp, as_remoteIp, null);

		return ls_solicitud;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findSolicitudMatricula(SolicitudMatricula at_param)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lr_response;

		lsw_watch     = Logger.getNewStopWatch();

		lr_response = getBusiness().findSolicitudMatricula(at_param);

		Logger.log(lsw_watch, "CalificacionBusiness", "findSolicitudMatricula", null, null, null, lr_response);

		return lr_response;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatriculaActo> findSolicitudMatriculaActo(SolicitudMatriculaActo at_param)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		Collection<SolicitudMatriculaActo> lr_response;

		lsw_watch     = Logger.getNewStopWatch();

		lr_response = getBusiness().findSolicitudMatriculaActo(at_param);

		Logger.log(lsw_watch, "CalificacionBusiness", "findSolicitudMatriculaActo", null, null, null, lr_response);

		return lr_response;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findSolicitudMatriculaEstado(SolicitudMatricula at_param)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lr_response;

		lsw_watch       = Logger.getNewStopWatch();
		lr_response     = getBusiness().findSolicitudMatriculaEstado(at_param);

		Logger.log(lsw_watch, "CalificacionBusiness", "findSolicitudMatriculaEstado", null, null, null, lr_response);

		return lr_response;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findSolicitudMatriculaOrderCirculo(SolicitudMatricula asm_sm)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lcsm_return;

		lsw_watch       = Logger.getNewStopWatch();
		lcsm_return     = getBusiness().findSolicitudMatriculaOrderCirculo(asm_sm);

		Logger.log(lsw_watch, "CalificacionBean", "findSolicitudMatriculaOrderCirculo", null, null, null, null);

		return lcsm_return;
	}

	/** {@inheritdoc} */
	public Collection<SolicitudMatricula> findSolicitudMatriculasTH(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		Collection<SolicitudMatricula> lcsm_matriculas;

		lsw_watch     = Logger.getNewStopWatch();

		lcsm_matriculas = getBusiness().findSolicitudMatriculasTH(ath_turnoHistoria);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "findSolicitudMatriculasTH", as_userId, as_localIp, as_remoteIp,
		    lcsm_matriculas
		);

		return lcsm_matriculas;
	}

	/** {@inheritdoc} */
	public TipoActo findTipoActoById(String as_idTipoActop)
	    throws B2BException
	{
		StopWatch lsw_watch;
		TipoActo  lta_tipoActoReturn;

		lsw_watch     = Logger.getNewStopWatch();

		lta_tipoActoReturn = getBusiness().findTipoActoById(as_idTipoActop);

		Logger.log(lsw_watch, "CalificacionBusiness", "getDatosUsuarioCalificador", null, null, null, null);

		return lta_tipoActoReturn;
	}

	/** {@inheritdoc} */
	public Collection<TipoActo> findTipoActos(String turno)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		Collection<TipoActo> lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().findTipoActos(turno);

		Logger.log(lsw_watch, "CalificacionBean", "findTipoActos", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public Collection<TmpSolicitudMatricula> findTmpSolicitudMatricula(TmpSolicitudMatricula at_param)
	    throws B2BException
	{
		StopWatch                         lsw_watch;
		Collection<TmpSolicitudMatricula> lr_response;

		lsw_watch     = Logger.getNewStopWatch();

		lr_response = getBusiness().findTmpSolicitudMatricula(at_param);

		Logger.log(lsw_watch, "CalificacionBusiness", "findTmpSolicitudMatricula", null, null, null, lr_response);

		return lr_response;
	}

	/** {@inheritdoc} */
	public Collection<TmpSolicitudMatriculaActo> findTmpSolicitudMatriculaActo(TmpSolicitudMatriculaActo at_param)
	    throws B2BException
	{
		StopWatch                             lsw_watch;
		Collection<TmpSolicitudMatriculaActo> lr_response;

		lsw_watch       = Logger.getNewStopWatch();
		lr_response     = getBusiness().findTmpSolicitudMatriculaActo(at_param);

		Logger.log(lsw_watch, "CalificacionBusiness", "findTmpSolicitudMatriculaActo", null, null, null, lr_response);

		return lr_response;
	}

	/** {@inheritdoc} */
	public Turno findTurno(String as_idTurno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Turno     lt_returno;

		lsw_watch      = Logger.getNewStopWatch();
		lt_returno     = getregistroCalificacionBusiness().findTurno(as_idTurno);

		Logger.log(lsw_watch, "CalificacionBean", "findTurno", null, null, null, null);

		return lt_returno;
	}

	/** {@inheritdoc} */
	public TurnoHistoria findTurnoHistoria(Long al_idTurnoHistoria, Long al_idEtapa)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().findTurnoHistoria(al_idTurnoHistoria, al_idEtapa);

		Logger.log(lsw_watch, "CalificacionBusiness", "findTurnoHistoria", null, null, null, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public TurnoHistoria findTurnoHistoriaAnteriorById(TurnoHistoria ath_param)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_response;

		lsw_watch     = Logger.getNewStopWatch();

		lth_response = getBusiness().findTurnoHistoriaAnteriorById(ath_param);

		Logger.log(lsw_watch, "CalificacionBusiness", "findTurnoHistoriaAnteriorById", null, null, null, null);

		return lth_response;
	}

	/** {@inheritdoc} */
	public TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException
	{
		StopWatch     lsw_watch;
		TurnoHistoria lth_response;

		lsw_watch     = Logger.getNewStopWatch();

		lth_response = getBusiness().findTurnoHistoriaById(ath_param);

		Logger.log(lsw_watch, "CalificacionBusiness", "findTurnoHistoriaById", null, null, null, null);

		return lth_response;
	}

	/** {@inheritdoc} */
	public Integer findTurnosCantidad(String ls_estado, String ls_idUsuario, long ll_idEtapa, boolean ab_allUsuarios)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Integer   lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getBusiness().findTurnosCantidad(ls_estado, ls_idUsuario, ll_idEtapa, ab_allUsuarios);

		Logger.log(lsw_watch, "CalificacionBean", "findTurnosCantidad", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public boolean generaSegregacionOApertura(PredioSegregado aps_predio, boolean ab_accion)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().generaSegregacionOApertura(aps_predio, ab_accion);

		Logger.log(lsw_watch, "CalificacionBean", "generaSegregacionOApertura", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public Map<String, Object[]> generarAlertas(
	    Collection<RegistroCalificacion> acrc_data, RegistroCalificacion arc_data
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		Map<String, Object[]> lmso_return;

		lsw_watch       = Logger.getNewStopWatch();
		lmso_return     = getregistroCalificacionBusiness().generarAlertas(acrc_data, arc_data);

		Logger.log(lsw_watch, "CalificacionBusiness", "generarAlertas", null, null, null, null);

		return lmso_return;
	}

	/** {@inheritdoc} */
	public byte[] generarFormularioCorrecciones(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_file;

		lsw_watch     = Logger.getNewStopWatch();
		lba_file      = getBusiness().generarFormularioCorrecciones(as_idTurnoHistoria, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "generarFormularioCorrecciones", as_userId, as_localIp, as_remoteIp, null
		);

		return lba_file;
	}

	/** {@inheritdoc} */
	public byte[] generateFileCancelacion(
	    RegistroCalificacion aorc_rc, boolean ab_firma, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getregistroCalificacionBusiness()
				              .genereteFileCancelacion(aorc_rc, ab_firma, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "generateFileCancelacion", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public byte[] generateFileNotaInformativa(
	    RegistroCalificacion arc_rc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getregistroCalificacionBusiness().generateFileNotaInformativa(arc_rc, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "generateFileNotaInformativa", as_userId, as_localIp, as_remoteIp, null
		);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public byte[] genereteFile(
	    TipoCausal aotc_tc, RegistroParcialCalificacion arpc_rpc, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getBusiness().generarNotaDevolutiva(aotc_tc, arpc_rpc, false, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "genereteFile", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public byte[] genereteFileComunicadoDireccion(
	    RegistroCalificacion aorc_rc, String as_userId, String as_localIp, String as_remoteIp, boolean ab_firmaMasiva
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getregistroCalificacionBusiness()
				                  .genereteFileComunicadoDireccion(aorc_rc, as_userId, as_remoteIp, ab_firmaMasiva);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "genereteFileComunicadoDireccion", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public byte[] genereteFileRegistro(
	    RegistroCalificacion aorc_rc, String as_userId, String as_localIp, String as_remoteIp, boolean ab_terminarEtapa
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lr_registro;

		lsw_watch       = Logger.getNewStopWatch();
		lr_registro     = getregistroCalificacionBusiness()
				                  .genereteFileRegistro(aorc_rc, false, as_userId, as_remoteIp, ab_terminarEtapa);

		Logger.log(lsw_watch, "CalificacionBusiness", "genereteFileRegistro", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public void gestionPredioCiudadano(
	    RegistroCalificacion aorc_idInteviniente, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().gestionPredioCiudadano(aorc_idInteviniente, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "gestionPredioCiudadano", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void guardarDatosArea(AccAreaUI aaaui_data, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().salvarAreaPredio(aaaui_data, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "guardarDatosArea", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void guardarDatosComplementacion(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().salvarComplementacion(arc_data, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "guardarDatosComplementacion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void guardarDatosLinderos(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().salvarLindero(arc_data, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "guardarDatosLinderos", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public Collection<PredioSegregado> guardarDatosSegregacion(
	    Collection<PredioSegregado> acps_prediosSegregados, String as_idTurnoHistoria, String as_idTurno,
	    String as_userId, String as_localIp, String as_remoteIp, String as_idCirculo, Long al_idMatricula,
	    boolean ab_segregado
	)
	    throws B2BException
	{
		Collection<PredioSegregado> lcps_return;
		StopWatch                   lsw_watch;

		lsw_watch       = Logger.getNewStopWatch();
		lcps_return     = getBusiness()
				                  .guardarDatosSegregacion(
				    acps_prediosSegregados, as_idTurnoHistoria, as_idTurno, as_userId, as_remoteIp, as_idCirculo,
				    al_idMatricula, ab_segregado
				);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "guardarDatosSegregacion", as_userId, as_localIp, as_remoteIp, null
		);

		return lcps_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion guardarInfoDigitador(
	    RegistroCalificacion adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_rc;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_rc = getregistroCalificacionBusiness().guardarInfoDigitador(adp_dp, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "guardarInfoDigitador", as_userId, as_localIp, as_remoteIp, null);

		return lorc_rc;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion guardarInfoRegistro(
	    RegistroCalificacion adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_rc;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_rc = getregistroCalificacionBusiness().guardarInfoRegistro(adp_dp, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "guardarInfoRegistro", as_userId, as_localIp, as_remoteIp, null);

		return lorc_rc;
	}

	/** {@inheritdoc} */
	public void guardarInformacionMedidaCautelar(
	    String as_idTurno, String as_tipoProceso, String as_numeroProceso, OficinaOrigen aoo_oficinaOrigen,
	    String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness()
			    .guardarInformacionMedidaCautelar(
			    as_idTurno, as_tipoProceso, as_numeroProceso, aoo_oficinaOrigen, as_userId, as_remoteIpAddress
			);

		Logger.log(
		    lsw_watch, "CalificacionBean", "guardarInformacionMedidaCautelar", as_userId, as_localIpAddress,
		    as_remoteIpAddress, null
		);
	}

	/** {@inheritdoc} */
	public void guardarInformacionNotificacion(
	    PersonaDireccion apd_direccion, Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness()
			    .guardarInformacionNotificacion(apd_direccion, ar_registro, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "guardarInformacionNotificacion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void guardarSalvedades(
	    AccSalvedadPredio asp_salvedad, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().guardarSalvedades(asp_salvedad, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "guardarSalvedades", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void guardarSalvedadesAnotacion(
	    AccSalvedadAnotacion asa_salvedad, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().guardarSalvedadesAnotacion(asa_salvedad, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "guardarSalvedadesAnotacion", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void guardarSolicitudesApoyoRegionalOrip(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNacional, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getregistroCalificacionBusiness()
			    .guardarSolicitudesApoyoRegionalOrip(asanui_solicitudApoyoNacional, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "guardarSolicitudesApoyoRegionalOrip", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void guardarTipoNumeroProceso(
	    String as_idTurno, String as_tipoProceso, String as_numeroProceso, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness()
			    .guardarTipoNumeroProceso(as_idTurno, as_tipoProceso, as_numeroProceso, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "guardarTipoNumeroProceso", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void inactivarAnotacionParcial(String as_idAnotacionParcial, String as_userId, String as_localIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().inactivarAnotacionParcial(as_idAnotacionParcial, as_userId, as_localIp);

		Logger.log(lsw_watch, "CalificacionBean", "inactivarAnotacionParcial", null, null, null, null);
	}

	/** {@inheritdoc} */
	public RegistroCalificacion insertarMatriculasSegregacion(
	    RegistroCalificacion arc_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_rc;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_rc = getBusiness().insertarMatriculasSegregacion(arc_data, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "insertarMatriculasSegregacion", as_idUsuario, as_localIp, as_remoteIp, null
		);

		return lrc_rc;
	}

	/** {@inheritdoc} */
	public void marcarAnotacionesCorreccion(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacion,
	    Collection<Anotacion> aca_anotacionesCargadas, String as_userId, String as_localIpAddress
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().marcarAnotacionesCorreccion(aap_anotacion, aca_anotacionesCargadas, as_userId, as_localIpAddress);

		Logger.log(lsw_watch, "CalificacionBean", "marcarAnotacionesCorreccion", null, null, null, null);
	}

	/** {@inheritdoc} */
	public RegistroCalificacion matriculasAHeredar(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().matriculasAHeredar(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "matriculasAHeredar", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion modificarAnotacion(
	    RegistroCalificacion aorc_rc, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().modificarAnotacion(aorc_rc, ab_accion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "modificarAnotacion", as_userId, as_localIp, as_remoteIp, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public void modificarCiudadano(RegistroCalificacion aorc_dataPersona)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().modificarCiudadano(aorc_dataPersona);

		Logger.log(lsw_watch, "CalificacionBean", "modificarCiudadano", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void modificarIDS(EliminarAnotacion aea_datosEliminar)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().modificarIDS(aea_datosEliminar);

		Logger.log(lsw_watch, "CalificacionBean", "modificarIDS", null, null, null, null);
	}

	/** {@inheritdoc} */
	public RegistroCalificacion modifyidAnotacion(String as_idAnt, String as_idnew, String as_userId)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().modifyidAnotacion(as_idAnt, as_idnew, as_userId);

		Logger.log(lsw_watch, "CalificacionBean", "modifyidAnotacion", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public TipoCausal notaDevolutiva(
	    TipoCausal aotc_tc, boolean ab_accion, String as_observaciones, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		TipoCausal lr_registro;

		lsw_watch     = Logger.getNewStopWatch();

		lr_registro = getBusiness().notaDevolutiva(aotc_tc, ab_accion, as_observaciones, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "notaDevolutiva", as_userId, as_localIp, as_remoteIp, null);

		return lr_registro;
	}

	/** {@inheritdoc} */
	public DireccionDelPredio obtenerDireccionPredio(
	    DireccionPredio adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DireccionDelPredio lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().obtenerDireccionPredio(adp_dp, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "obtenerDireccionPredio", as_userId, as_localIp, as_remoteIp, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public DireccionDelPredio obtenerDireccionPredioDigitadoreMasivo(
	    DireccionPredio adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch          lsw_watch;
		DireccionDelPredio lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness()
				              .obtenerDireccionPredioDigitadoreMasivo(adp_dp, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "obtenerDireccionPredioDigitadoreMasivo", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public BigDecimal obtenerEtapaActual(
	    String as_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		BigDecimal lbd_return;

		lsw_watch     = Logger.getNewStopWatch();

		lbd_return = getBusiness().obtenerEtapaActual(as_turnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "obtenerEtapaActual", as_userId, as_localIp, as_remoteIp, null);

		return lbd_return;
	}

	/** {@inheritdoc} */
	public BigDecimal obtenerEtapaAnterior(
	    String as_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		BigDecimal lbd_return;

		lsw_watch     = Logger.getNewStopWatch();

		lbd_return = getBusiness().obtenerEtapaAnterior(as_turnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "obtenerEtapaAnterior", as_userId, as_localIp, as_remoteIp, null);

		return lbd_return;
	}

	/** {@inheritdoc} */
	public DatosAntSistema obtenerInformacionAntiguoSistema(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch       lsw_watch;
		DatosAntSistema ldas_datosAntSistema;

		lsw_watch     = Logger.getNewStopWatch();

		ldas_datosAntSistema = getBusiness().obtenerInformacionAntiguoSistema(as_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "obtenerInformacionAntiguoSistema", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ldas_datosAntSistema;
	}

	/** {@inheritdoc} */
	public void precalificar(
	    String as_idTurnoHistoria, boolean ab_paramPreCalificar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness()
			    .precalificar(as_idTurnoHistoria, ab_paramPreCalificar, as_userId, as_localIp, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "precalificar", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void procCopiaDefinitivoTemporal(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().procCopiaDefinitivoTemporal(ath_turnoHistoria, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "procCopiaDefinitivoTemporal", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public RegistroCalificacion procCopiarAnotaciones(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().procCopiarAnotaciones(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "procCopiarAnotaciones", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public Anotacion salvarAnotaciones(
	    Anotacion aa_parametros, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Anotacion la_return;

		lsw_watch     = Logger.getNewStopWatch();

		la_return = getregistroCalificacionBusiness().salvarAnotaciones(aa_parametros, as_usuario, as_ipRemota);

		Logger.log(lsw_watch, "CalificacionBean", "salvarAnotaciones", as_usuario, as_ipLocal, as_ipRemota, null);

		return la_return;
	}

	/** {@inheritdoc} */
	public void salvarAnotacionesEnglobes(RegistroCalificacion arc_arg)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().salvarAnotacionesEnglobes(arc_arg);

		Logger.log(lsw_watch, "CalificacionBean", "salvarAnotacionesEnglobes", null, null, null, null);
	}

	/** {@inheritdoc} */
	public boolean salvarAntSistema(
	    ConsultaCriteriosCalificacion accc_consultaCriteriosCalificacion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().salvarAntSistema(accc_consultaCriteriosCalificacion, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "salvarAntSistema", as_userId, as_localIp, as_remoteIp, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void salvarAntSistemaCalificacion(TurnoHistoria ath_turnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarAntSistemaCalificacion(ath_turnoHistoria);

		Logger.log(lsw_watch, "CalificacionBean", "salvarAntSistemaCalificacion", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void salvarAreaPredioEnglobes(
	    AccAreaUI aaaui_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().salvarAreaPredioEnglobes(aaaui_data, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "salvarAreaPredioEnglobes", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarAreaPredioVenta(AccAreaUI aaaui_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getregistroCalificacionBusiness().salvarAreaPredioVenta(aaaui_data, as_idUsuario, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBean", "salvarAreaPredioEnglobes", as_idUsuario, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void salvarCheksCorrecciones(
	    PermisosCorreccionesUI apc_datos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarCheksCorrecciones(apc_datos, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "salvarCheksCorrecciones", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public void salvarConfrontacion(
	    Collection<ConfrontacionCorrectiva> accc_ccc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarConfrontacion(accc_ccc, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "salvarConfrontacion", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public RegistroCalificacion salvarDatosBasicosEnglobes(
	    RegistroCalificacion arc_data, String as_idUsuario, String as_ip
	)
	    throws B2BException
	{
		RegistroCalificacion lrc_return;
		StopWatch            lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();

		lrc_return = getregistroCalificacionBusiness().salvarDatosBasicosEnglobes(arc_data, as_idUsuario, as_ip);

		Logger.log(lsw_watch, "CalificacionBean", "salvarDatosBasicosEnglobes", as_idUsuario, null, null, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public boolean salvarDesenglobe(
	    RegistroCalificacion arc_arg, String as_userId, String as_ipLocal, String as_ipRemote
	)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getregistroCalificacionBusiness().salvarDesenglobe(arc_arg, as_userId, as_ipRemote);

		Logger.log(lsw_watch, "CalificacionBusiness", "salvarDesenglobe", as_userId, as_ipLocal, as_ipRemote, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void salvarDigitador(
	    AreaPredio ap_ap, Long al_idTurnoHistoria, String ls_idTurno, int ai_totalRevision, int ai_totalMatriculas,
	    String as_userId, String as_localIp, String as_remoteIp, String as_observaciones
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .salvarDigitador(
			    ap_ap, al_idTurnoHistoria, ls_idTurno, ai_totalRevision, ai_totalMatriculas, as_userId, as_localIp,
			    as_remoteIp, as_observaciones
			);

		Logger.log(lsw_watch, "CalificacionBusiness", "salvarDigitador", null, null, null, null);
	}

	/** {@inheritdoc} */
	public DireccionPredioAcc salvarDireccionVenta(RegistroCalificacion arc_rc, String as_idUsuario, String as_ip)
	    throws B2BException
	{
		DireccionPredioAcc ldpa_direccion;
		StopWatch          lsw_watch;

		ldpa_direccion     = getregistroCalificacionBusiness().salvarDireccionVenta(arc_rc, as_idUsuario, as_ip);
		lsw_watch          = Logger.getNewStopWatch();

		Logger.log(lsw_watch, "CalificacionBusiness", "salvarDireccionVenta", null, null, null, null);

		return ldpa_direccion;
	}

	/** {@inheritdoc} */
	public void salvarEnglobes(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarEnglobes(arc_data);

		Logger.log(lsw_watch, "CalificacionBusiness", "salvarEnglobes", null, null, null, null);
	}

	/** {@inheritdoc} */
	public void salvarLinderosComplementacionEnglobes(
	    ComplementacionCalificacion acc_dataComplementacion, LinderoRegistroCalificacion alrc_dataLindero,
	    RegistroCalificacion arc_data, String as_user, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .salvarLinderosComplementacionEnglobes(
			    acc_dataComplementacion, alrc_dataLindero, arc_data, as_user, as_remoteIp
			);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "salvarLinderosComplementacionEnglobes", as_user, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public void salvarPermisos(
	    String as_observaciones, String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().salvarPermisos(as_observaciones, as_idTurnoHistoria, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBusiness", "salvarPermisos", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public DataReproduccionConstancia salvarReproduccionConstanciaCalificador(
	    DataReproduccionConstancia adrc_parametros, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		DataReproduccionConstancia ldrc_datos;

		lsw_watch     = Logger.getNewStopWatch();

		ldrc_datos = getBusiness().salvarReproduccionConstanciaCalificador(adrc_parametros, as_usuario, as_ipRemota);

		Logger.log(
		    lsw_watch, "CalificacionBean", "salvarReproduccionConstanciaCalificador", as_usuario, as_ipLocal,
		    as_ipRemota, null
		);

		return ldrc_datos;
	}

	/** {@inheritdoc} */
	public boolean salvarVentaParcial(
	    RegistroCalificacion arc_arg, String as_user, String as_localIp, String as_remoteIp, boolean ab_accion,
	    boolean ab_salvar
	)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getregistroCalificacionBusiness()
				                .salvarVentaParcial(arc_arg, as_user, as_remoteIp, ab_accion, ab_salvar);

		Logger.log(lsw_watch, "CalificacionBusiness", "salvarVentaParcial", as_user, as_localIp, as_remoteIp, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean salvarVentaParcialNoCementerio(
	    AccLinderoPredio aalp_lindero, String as_user, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getregistroCalificacionBusiness()
				                .salvarVentaParcialNoCementerio(aalp_lindero, as_user, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "salvarVentaParcialNoCementerio", as_user, as_localIp, as_remoteIp, null
		);

		return lb_return;
	}

	/** {@inheritdoc} */
	public void saveCausalesMatriculaCorreccion(
	    Collection<CausalCorreccion> accc_causales, SolicitudMatricula asm_solMat, String as_turno, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().saveCausalesMatriculaCorreccion(accc_causales, asm_solMat, as_turno, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "saveCausalesMatriculaCorreccion", as_userId, as_localIp, as_remoteIp,
		    null
		);
	}

	/** {@inheritdoc} */
	public void saveCheks(Collection<ConfrontacionCorrectiva> accc_ccc, String as_userId, String as_ipRemote)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().saveCheks(accc_ccc, as_userId, as_ipRemote);

		Logger.log(lsw_watch, "CalificacionBusiness", "saveCheks", as_userId, null, as_ipRemote, null);
	}

	/** {@inheritdoc} */
	public RegistroCalificacion saveDetailAnotacion(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lorc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lorc_return = getregistroCalificacionBusiness().saveDetailAnotacion(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "saveDetailAnotacion", null, null, null, null);

		return lorc_return;
	}

	/** {@inheritdoc} */
	public SolicitudApoyoNacionalUI terminarProcesoApoyoNacionalAprobacion(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNacional, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		SolicitudApoyoNacionalUI lsanui_return;

		lsw_watch         = Logger.getNewStopWatch();
		lsanui_return     = getregistroCalificacionBusiness()
				                    .terminarProcesoApoyoNacionalAprobacion(
				    asanui_solicitudApoyoNacional, as_userId, as_remoteIp
				);

		Logger.log(lsw_watch, "CalificacionBean", "terminarProcesoApoyoNacionalAprobacion", null, null, null, null);

		return lsanui_return;
	}

	/** {@inheritdoc} */
	public RegistroCalificacion turnosVinculados(
	    RegistroCalificacion arc_rc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch            lsw_watch;
		RegistroCalificacion lrc_return;

		lsw_watch      = Logger.getNewStopWatch();
		lrc_return     = getregistroCalificacionBusiness().turnosVinculados(arc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "turnosVinculados", as_userId, as_localIp, as_remoteIp, null);

		return lrc_return;
	}

	/** {@inheritdoc} */
	public void updateNotaDevolutiva(
	    TurnoHistoria ath_turnoHistoria, boolean ab_aprobador, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();
		getBusiness().updateNotaDevolutiva(ath_turnoHistoria, ab_aprobador, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CalificacionBean", "updateNotaDevolutiva", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public boolean validacionCancelacion(RegistroCalificacion aorc_rc)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_b;

		lsw_watch     = Logger.getNewStopWatch();

		lb_b = getregistroCalificacionBusiness().validacionCancelacion(aorc_rc);

		Logger.log(lsw_watch, "CalificacionBean", "validacionCancelacion", null, null, null, null);

		return lb_b;
	}

	/**
	 * Método encargado de validar si una matricula presenta una medida cautelar vigente.
	 *
	 * @param acs_matriculasExtraidas <code>Collection<String></code> que contiene String con matriculas
	 * @param as_idTurno String que contiene turno a consultar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return String con matricula si encuentra que esta tiene una medida cautelar vigente
	 * @throws B2BException de b 2 B exception
	 */
	public String validacionMedidaCautelar(
	    Collection<String> acs_matriculasExtraidas, String as_idTurno, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_matricula;

		lsw_watch     = Logger.getNewStopWatch();

		ls_matricula = getBusiness().validacionMedidaCautelar(acs_matriculasExtraidas, as_idTurno);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "validacionMedidaCautelar", as_userId, as_localIp, as_remoteIp, null
		);

		return ls_matricula;
	}

	/** {@inheritdoc} */
	public ValidacionAlertaTurno validarAlertaTurnoTramite(
	    Long al_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		ValidacionAlertaTurno lvat_return;

		lsw_watch       = Logger.getNewStopWatch();
		lvat_return     = getBusiness().validarAlertaTurnoTramite(al_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "CalificacionBean", "validarAlertaTurnoTramite", as_userId, as_localIp, as_remoteIp, null
		);

		return lvat_return;
	}

	/** {@inheritdoc} */
	public boolean validarAnotacionApertura(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().validarAnotacionApertura(aap_anotacionPredio);

		Logger.log(lsw_watch, "CalificacionBean", "validarAnotacionApertura", as_userId, as_localIp, as_remoteIp, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public Map<String, String> validarAnotacionCorreccionCrear(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String                                                as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		Map<String, String> lmss_return;

		lsw_watch       = Logger.getNewStopWatch();
		lmss_return     = getBusiness().validarAnotacionCorreccionCrear(aap_anotacionPredio);

		Logger.log(
		    lsw_watch, "CalificacionBean", "validarAnotacionCorreccionCrear", as_userId, as_localIp, as_remoteIp, null
		);

		return lmss_return;
	}

	/** {@inheritdoc} */
	public boolean validarAnotacionEnglobe(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException
	{
		boolean   lb_return;
		StopWatch lsw_watch;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().validarAnotacionEnglobe(aap_anotacionPredio);

		Logger.log(lsw_watch, "CalificacionBean", "validarAnotacionEnglobe", as_userId, as_localIp, as_remoteIp, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public String validarAnotacionesPredioSegregado(PredioSegregado aps_predioSegregado)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getBusiness().validarAnotacionesPredioSegregado(aps_predioSegregado);

		Logger.log(lsw_watch, "CalificacionBean", "validarAnotacionesPredioSegregado", null, null, null, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public boolean validarDatosArea(String as_idTurno)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getregistroCalificacionBusiness().validarDatosArea(as_idTurno);

		Logger.log(lsw_watch, "CalificacionBean", "validarDatosArea", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public String validarDocumento(List<Map<String, Object>> almso_documento)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_mensaje;

		lsw_watch     = Logger.getNewStopWatch();

		ls_mensaje = getBusiness().validarDocumento(almso_documento);

		Logger.log(lsw_watch, "CalificacionBusiness", "validarDocumento", null, null, null, null);

		return ls_mensaje;
	}

	/** {@inheritdoc} */
	public boolean validarEliminarAnotacionParcial(EliminarAnotacion aea_datosEliminar)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getregistroCalificacionBusiness().validarEliminarAnotacionParcial(aea_datosEliminar);

		Logger.log(lsw_watch, "CalificacionBean", "validarEliminarAnotacionParcial", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarEmbargosVigentes(String as_circulo, long al_matricula, String as_idSolicitud)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getregistroCalificacionBusiness().validarEmbargosVigentes(as_circulo, al_matricula, as_idSolicitud);

		Logger.log(lsw_watch, "CalificacionBean", "validarEmbargosVigentes", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public Turno validarEtapaGrabacion(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		Turno     lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getBusiness().validarEtapaGrabacion(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBean", "validarEtapaGrabacion", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public String validarFechaAnotacionCrearCorrecciones(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String                                                as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getBusiness().validarFechaAnotacionCrearCorrecciones(aap_anotacionPredio);

		Logger.log(
		    lsw_watch, "CalificacionBean", "validarFechaAnotacionCrearCorrecciones", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ls_return;
	}

	/** {@inheritdoc} */
	public String validarInterviniente(
	    Persona ap_persona, com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getBusiness().validarInterviniente(ap_persona, aap_anotacionPredio);

		Logger.log(lsw_watch, "CalificacionBean", "validarInterviniente", as_userId, as_localIp, as_remoteIp, null);

		return ls_return;
	}

	/** {@inheritdoc} */
	public boolean validarPrimerEnglobeDireccion(RegistroCalificacion arc_data)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getBusiness().validarPrimerEnglobeDireccion(arc_data);

		Logger.log(lsw_watch, "CalificacionBean", "validarPrimerEnglobeDireccion", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarProrrogaEntregaDePruebas(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_bool;

		lsw_watch     = Logger.getNewStopWatch();

		lb_bool = getregistroCalificacionBusiness().validarProrrogaEntregaDePruebas(as_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "CalificacionBean", "validarProrrogaEntregaDePruebas", as_userId, as_localIp, as_remoteIp, null
		);

		return lb_bool;
	}

	/** {@inheritdoc} */
	public void validarSalvedades(
	    Long as_idTurnoHistoria, AccSalvedadPredio asp_salvedad, String as_observaciones, String as_idUsuario,
	    String as_localIp, String as_remoteIp, boolean ab_validarSegregacion, Collection<PredioSegregado> acps_datos
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness()
			    .validarSalvedades(
			    as_idTurnoHistoria, asp_salvedad, as_observaciones, as_idUsuario, as_remoteIp, ab_validarSegregacion,
			    acps_datos
			);

		Logger.log(lsw_watch, "CalificacionBean", "validarSalvedades", as_idUsuario, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public boolean validarSalvedades(Long al_idTurnoHistoria, boolean ab_mensaje)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getBusiness().validarSalvedades(al_idTurnoHistoria, ab_mensaje);

		Logger.log(lsw_watch, "CalificacionBean", "validarSalvedades", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarSumatoria(String as_idTurnoHistoria)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();
		lb_return     = getregistroCalificacionBusiness().validarSumatoria(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBean", "validarSumatoria", null, null, null, null);

		return lb_return;
	}

	/** {@inheritdoc} */
	public boolean validarTurnoMigrado(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_turnoMigrado;

		lsw_watch     = Logger.getNewStopWatch();

		lb_turnoMigrado = getBusiness().validarTurnoMigrado(ath_turnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "validarTurnoMigrado", as_userId, as_localIp, as_remoteIp, null);

		return lb_turnoMigrado;
	}

	/** {@inheritdoc} */
	public BigDecimal valorADevolverDeLiquidacion(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> acap_anotacionesPredio,
	    String                                                            as_idTurnoHistoria, String as_userId,
	    String                                                            as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		BigDecimal li_valoraDevolver;

		lsw_watch             = Logger.getNewStopWatch();
		li_valoraDevolver     = getBusiness().valorADevolverDeLiquidacion(acap_anotacionesPredio, as_idTurnoHistoria);

		Logger.log(
		    lsw_watch, "CalificacionBusiness", "valorADevolverDeLiquidacion", as_userId, as_localIp, as_remoteIp, null
		);

		return li_valoraDevolver;
	}

	/** {@inheritdoc} */
	public boolean verificaPropiedad(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   ib_boolean;

		lsw_watch     = Logger.getNewStopWatch();

		ib_boolean = getBusiness().verificaPropiedad(as_idTurnoHistoria);

		Logger.log(lsw_watch, "CalificacionBusiness", "verificaPropiedad", as_userId, as_localIp, as_remoteIp, null);

		return ib_boolean;
	}

	/** {@inheritdoc} */
	public boolean visualizarBandeja()
	    throws B2BException
	{
		StopWatch lsw_watch;
		boolean   lb_return;

		lsw_watch     = Logger.getNewStopWatch();

		lb_return = getBusiness().visualizarBandeja();

		Logger.log(lsw_watch, "CalificacionBean", "visualizarBandeja", null, null, null, null);

		return lb_return;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private CalificacionBusiness getBusiness()
	{
		if(irb_business == null)
			irb_business = new CalificacionBusiness();

		return irb_business;
	}

	/**
	 * Retorna el valor de registro calificacion business.
	 *
	 * @return el valor de registro calificacion business
	 */
	private RegistroCalificacionBusiness getregistroCalificacionBusiness()
	{
		if(irb_registroCalificacion == null)
			irb_registroCalificacion = new RegistroCalificacionBusiness();

		return irb_registroCalificacion;
	}
}
