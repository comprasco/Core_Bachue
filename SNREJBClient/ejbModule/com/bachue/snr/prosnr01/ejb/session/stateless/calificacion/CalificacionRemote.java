package com.bachue.snr.prosnr01.ejb.session.stateless.calificacion;

import com.b2bsg.common.exception.B2BException;

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

import java.math.BigDecimal;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades CalificacionRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface CalificacionRemote
{
	/**
	 * Retorna el valor de datos usuario calificador.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de datos usuario calificador
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona getDatosUsuarioCalificador(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Retorna el valor de indicadores by id turno.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de indicadores by id turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Map<String, Object>> getIndicadoresByIdTurno(String as_idTurno)
	    throws B2BException;

	/**
	 * Retorna el valor de mensaje error.
	 *
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de mensaje error
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String getMensajeError(String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Retorna el valor de tipo doc persona by matricula.
	 *
	 * @param al_idMatricula de al id matricula
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return el valor de tipo doc persona by matricula
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> getTipoDocPersonaByMatricula(Long al_idMatricula, String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Accion salvar view.
	 *
	 * @param alp_linderoPredio de alp lindero predio
	 * @param aacp_complementacionPredio de aacp complementacion predio
	 * @param as_tipoComplementacion de as tipo complementacion
	 * @param acp_complementacionPredio de acp complementacion predio
	 * @param aapr_pr de aapr pr
	 * @param aaui_areaPredio de aaui area predio
	 * @param acddp_temp de acddp temp
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param as_localIp de as local ip
	 * @return el valor de int
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int accionSalvarView(
	    AccLinderoPredio alp_linderoPredio, AccComplementacionPredio aacp_complementacionPredio,
	    String as_tipoComplementacion, ComplementacionPredio acp_complementacionPredio, AccPredioRegistro aapr_pr,
	    AccAreaUI aaui_areaPredio, Collection<DireccionDelPredio> acddp_temp, String as_idTurnoHistoria,
	    String as_userId, String as_remoteIp, String as_localIp
	)
	    throws B2BException;

	/**
	 * Actualizacion masiva.
	 *
	 * @param apr_pr de apr pr
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de acc predio registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccPredioRegistro actualizacionMasiva(
	    AccPredioRegistro apr_pr, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Actualizar areas.
	 *
	 * @param acc_predio de acc predio
	 * @param adp_direccion de adp direccion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarAreas(
	    AccAreaPredio acc_predio, DireccionPredioAcc adp_direccion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Actualizar lista intervinientes.
	 *
	 * @param lp_personaModificada de lp persona modificada
	 * @param lca_cllAnotacion de lca cll anotacion
	 * @param asi_solicitudInterviniente de asi solicitud interviniente
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Anotacion> actualizarListaIntervinientes(
	    Persona lp_personaModificada, Collection<Anotacion> lca_cllAnotacion,
	    SolicitudInterviniente asi_solicitudInterviniente
	)
	    throws B2BException;

	/**
	 * Actualizar revision.
	 *
	 * @param aottf_data de aottf data
	 * @param arc_data de arc data
	 * @param ab_b de ab b
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarRevision(
	    AccPredioRegistro aottf_data, RegistroCalificacion arc_data, boolean ab_b, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Agregar matricula complementacion.
	 *
	 * @param acmb_matriculas de acmb matriculas
	 * @param acc_complementacionCalificacion de acc complementacion calificacion
	 * @param arc_registroCalificacion de arc registro calificacion
	 * @param as_matricula de as matricula
	 * @param ab_primerVez de ab primer vez
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MatriculaBase> agregarMatriculaComplementacion(
	    Collection<MatriculaBase> acmb_matriculas, ComplementacionCalificacion acc_complementacionCalificacion,
	    RegistroCalificacion arc_registroCalificacion, String as_matricula, boolean ab_primerVez, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Alertas intervinientes baldios.
	 *
	 * @param as_idPersona de as id persona
	 * @param ab_accion de ab accion
	 * @param as_tipoActo de as tipo acto
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object[]> alertasIntervinientesBaldios(
	    String as_idPersona, boolean ab_accion, String as_tipoActo
	)
	    throws B2BException;

	/**
	 * Aperturar matriculas segregacion.
	 *
	 * @param arc_data Objeto que contiene la información del proceso.
	 * @param as_userId Variable que contiene el usuario del proceso.
	 * @param as_localIpAddress Variable que contiene la ip local.
	 * @param as_remoteIpAddress Variable que contiene la ip remota.
	 * @return Objeto que contiene la información del proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion aperturarMatriculasSegregacion(
	    RegistroCalificacion arc_data, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Aprobar reasignacion apoyo nacional.
	 *
	 * @param asanui_solicitudApoyoNacional de asanui solicitud apoyo nacional
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudApoyoNacionalUI aprobarReasignacionApoyoNacional(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNacional, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Archivo generado.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param ab_registroParcial de ab registro parcial
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean archivoGenerado(String as_idTurnoHistoria, boolean ab_registroParcial)
	    throws B2BException;

	/**
	 * Calcular area.
	 *
	 * @param arc_tmp de arc tmp
	 * @param acap_ap de acap ap
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String calcularArea(RegistroCalificacion arc_tmp, Collection<AreaPredio> acap_ap)
	    throws B2BException;

	/**
	 * Cargar anotacion proceso.
	 *
	 * @param arc_data de arc data
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RegistroCalificacion> cargarAnotacionProceso(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Cargar anotaciones englobes.
	 *
	 * @param arc_data de arc data
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarAnotacionesEnglobes(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Cargar cheks correcciones.
	 *
	 * @param ascc_camposCorreccion de ascc campos correccion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de permisos correcciones UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PermisosCorreccionesUI cargarCheksCorrecciones(
	    SolicitudCamposCorreccion ascc_camposCorreccion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar cierre folio.
	 *
	 * @param arc_data de arc data
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarCierreFolio(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Cargar complementacion.
	 *
	 * @param acp_cp de acp cp
	 * @param as_userId de as user id
	 * @return el valor de acc complementacion predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccComplementacionPredio cargarComplementacion(AccComplementacionPredio acp_cp, String as_userId)
	    throws B2BException;

	/**
	 * Cargar complementacion englobes.
	 *
	 * @param arc_data de arc data
	 * @return el valor de complementacion calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ComplementacionCalificacion cargarComplementacionEnglobes(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Cargar datos anotaciones A cancelar.
	 *
	 * @param aac_anotacionCancelacion de aac anotacion cancelacion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<RegistroCalificacion> cargarDatosAnotacionesACancelar(
	    AnotacionCancelacion aac_anotacionCancelacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar datos area englobes.
	 *
	 * @param arc_data de arc data
	 * @return el valor de acc area UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccAreaUI cargarDatosAreaEnglobes(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Cargar datos area venta.
	 *
	 * @param arc_data de arc data
	 * @return el valor de acc area UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccAreaUI cargarDatosAreaVenta(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Método encargado de consultar los datos básicos para digitador masivo.
	 *
	 * @param arc_data Objeto que contiene la información del predio matriz.
	 * @return Objeto que contiene la información de los datos básicos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosBasicos cargarDatosBasicosDigitadorMasivo(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Cargar datos basicos englobes.
	 *
	 * @param arc_data de arc data
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarDatosBasicosEnglobes(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Método encargado de cargar la información de la dirección con base al medio a notificar de la solicitud.
	 *
	 * @param as_idTurnoHistoria Variable tipo String que contiene el id del turno historia.
	 * @return Objeto que contiene la información de la dirección.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Registro cargarDireccionNotificacion(String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Cargar direcciones englobes.
	 *
	 * @param arc_matriculas de arc matriculas
	 * @param ab_accion de ab accion
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarDireccionesEnglobes(RegistroCalificacion arc_matriculas, boolean ab_accion)
	    throws B2BException;

	/**
	 * Método encargado de consultar los las direcciones del predio temporal.
	 *
	 * @param arc_data Objeto que contiene la información del predio a consultar.
	 * @return Objeto que contiene la información de la dirección.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarDireccionesPredioTemporal(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Método encargado de cargar la información del englobes para la anotación.
	 *
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la información del englobe para la anotación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarInfoEnglobeAnotacion(
	    Anotacion aa_anotacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar linderos.
	 *
	 * @param lc_anotaciones de lc anotaciones
	 * @return el valor de lindero registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LinderoRegistroCalificacion cargarLinderos(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones
	)
	    throws B2BException;

	/**
	 * Cargar linderos digitador masivo.
	 *
	 * @param lc_anotaciones de lc anotaciones
	 * @return el valor de lindero registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LinderoRegistroCalificacion cargarLinderosDigitadorMasivo(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> lc_anotaciones
	)
	    throws B2BException;

	/**
	 * Cargar linderos englobes.
	 *
	 * @param arc_data de arc data
	 * @return el valor de lindero registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LinderoRegistroCalificacion cargarLinderosEnglobes(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Cargar oficina origen medida cautelar.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de oficina origen
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficinaOrigen cargarOficinaOrigenMedidaCautelar(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de cargar los oficio texto para traslados.
	 *
	 * @param as_idTurno Contiene el id del turno.
	 * @param as_motivoTramite Contiene el motivo tramite.
	 * @return Contiene los oficio texto del proceso.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficiosTexto cargarOficioTextoTraslados(String as_idTurno, String as_motivoTramite)
	    throws B2BException;

	/**
	 * Método encargado de validar si la anotación contiene un acto de englobes y cargar los predios de la correccion.
	 *
	 * @param aap_anotacion Objeto que contiene la información de la anotación a validar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Colección que contiene los predios de la corrección.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> cargarPrediosEnglobeCorreccion(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacion, String as_userId, String as_localIp,
	    String                                                as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar venta parcial.
	 *
	 * @param arc_data de arc data
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion cargarVentaParcial(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Método encargado de validar si se debe habilitar un cierre de folio.
	 *
	 * @param acps_predios Colección que contiene los predios a validar.
	 * @return Variable tipo boolean que indica si se debe habilitar cierre de folio.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean cierreFolio(Collection<PredioSegregado> acps_predios)
	    throws B2BException;

	/**
	 * Método encargado de confirmar la cantidad de predios a aperturar por una anotación.
	 *
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void confirmarCantidadAperturarAnotacion(
	    Anotacion aa_anotacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de confirmar las matrículas seleccionadas para el englobe.
	 *
	 * @param aa_anotacion Objeto que contiene la información de la anotación.
	 * @param as_idCirculo Variable que contiene el id del circulo.
	 * @param al_idMatricula Variable que contiene el id de la matrícula.
	 * @param as_idTurno Variable que contiene el id del turno.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return bjeto que contiene la información de la anotación.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Anotacion confirmarMatriculasEnglobe(
	    String as_idCirculo, Long al_idMatricula, String as_idTurno, Anotacion aa_anotacion, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Construir complementacion anotaciones.
	 *
	 * @param lc_anotaciones de lc anotaciones
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ComplementacionAnotacion> construirComplementacionAnotaciones(
	    Collection<AnotacionPredio> lc_anotaciones
	)
	    throws B2BException;

	/**
	 * Consultar turno historia anterior.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria consultarTurnoHistoriaAnterior(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método para consultar si el turno es migrado y no tiene liquidacion
	 * @param ath_turnoHistoria turno historia con la información a consultar
	 * @param as_userId de as user id
	 * @return TurnoHistoria
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria consultarTurnoMigrado(TurnoHistoria ath_turnoHistoria, String as_userId)
	    throws B2BException;

	/**
	 * Consultar turnos derivados.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<TurnoHistoria, Boolean> consultarTurnosDerivados(TurnoHistoria ath_turnoHistoria, String as_userId)
	    throws B2BException;

	/**
	 * Crear anotacion baldios.
	 *
	 * @param aapr_predio de aapr predio
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de anotacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Anotacion crearAnotacionBaldios(
	    AccPredioRegistro aapr_predio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Datalle anotaciones.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion datalleAnotaciones(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Detalle intervinientes.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion detalleIntervinientes(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Eliminar acto.
	 *
	 * @param aa_parametros de aa parametros
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarActo(Acto aa_parametros, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Eliminar anotacion.
	 *
	 * @param lorc_tmp de lorc tmp
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarAnotacion(RegistroCalificacion lorc_tmp)
	    throws B2BException;

	/**
	 * Eliminar data persona.
	 *
	 * @param asi_si de aorc rc
	 * @param as_userId de as user id
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion eliminarDataPersona(SolicitudInterviniente asi_si, String as_userId)
	    throws B2BException;

	/**
	 * Eliminar direccion predio acc.
	 *
	 * @param as_idDireccionPredio the as id direccion predio
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 */
	public void eliminarDireccionPredioAcc(
	    String as_idDireccionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Encontrar turnos actuales.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String encontrarTurnosActuales(String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Enviar A digitador.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion enviarADigitador(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Enviar A digitador desenglobe.
	 *
	 * @param arc_data de arc data
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarADigitadorDesenglobe(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar A digitador englobes.
	 *
	 * @param arc_data de arc data
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarADigitadorEnglobes(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Enviar A digitador englobes devolucion.
	 *
	 * @param arc_data de arc data
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarADigitadorEnglobesDevolucion(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar aprobador realizar correcciones.
	 *
	 * @param as_observaciones de as observaciones
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param ab_secuenciaAgregada de ab secuencia agregada
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAprobadorRealizarCorrecciones(
	    String as_observaciones, String as_idTurnoHistoria, boolean ab_secuenciaAgregada, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar aprobador rep constancia.
	 *
	 * @param ac_c de ac c
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAprobadorRepConstancia(
	    Calificacion ac_c, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar aprobador secuencia o actos homologación.
	 *
	 * @param ath_th de ath th
	 * @param ab_aprobadorSecuencia de boolean
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAprobadorSecuenciaOActosHomologacion(
	    TurnoHistoria ath_th, boolean ab_aprobadorSecuencia, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar aprobar prorroga documentos.
	 *
	 * @param ath_th de ath th
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAprobarProrrogaDocumentos(
	    TurnoHistoria ath_th, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de cambiar de etapa para el proceso de actuaciones administrativas.
	 *
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno historia a terminar.
	 * @param amt_motivoTramite Argumento de tipo <code>MotivoTramite</code> que contiene el motivo de trámite.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp de as local ip
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip de la maquina del usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarResponsableActuacionesAdministrativas(
	    TurnoHistoria ath_turnoHistoria, MotivoTramite amt_motivoTramite, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Es proceso baldios.
	 *
	 * @param as_idTurno de as id turno
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean esProcesoBaldios(String as_idTurno)
	    throws B2BException;

	/**
	 * Es segunda vez calificacion lindero.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean esSegundaVezCalificacionLindero(TurnoHistoria ath_turnoHistoria)
	    throws B2BException;

	/**
	 * Existen anotaciones inactivas.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean existenAnotacionesInactivas(String as_idSolicitud)
	    throws B2BException;

	/**
	 * Existen anotaciones inactivas by turno.
	 *
	 * @param as_idTurno de as id turno
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean existenAnotacionesInactivasByTurno(String as_idTurno)
	    throws B2BException;

	/**
	 * Find actos by id solicitud circulo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idCirculo de as id circulo
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Acto> findActosByIdSolicitudCirculo(
	    String as_idSolicitud, String as_idCirculo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find actos by id turno.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurno(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find actos by id turno ind mayor valor.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<com.bachue.snr.prosnr01.model.registro.Acto> findActosByIdTurnoIndMayorValor(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find actos turno matriculas.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Acto> findActosTurnoMatriculas(String as_idTurno)
	    throws B2BException;

	/**
	 * Find by datos ant sistema.
	 *
	 * @param lcccas_consultaCriteriosAntiguoSistema de lcccas consulta criterios antiguo sistema
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DataConsultaAntSistema> findByDatosAntSistema(
	    ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException;

	/**
	 * Find by datos ant sistema calificacion.
	 *
	 * @param lcccas_consultaCriteriosAntiguoSistema de lcccas consulta criterios antiguo sistema
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DataConsultaPorCriterio> findByDatosAntSistemaCalificacion(
	    ConsultaCriteriosCalificacionAntiguoSistema lcccas_consultaCriteriosAntiguoSistema
	)
	    throws B2BException;

	/**
	 * Find by datos documento.
	 *
	 * @param consultaCriteriosCalificacionDocumento de consulta criterios calificacion documento
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DataConsultaDatosDocumento> findByDatosDocumento(
	    ConsultaCriteriosCalificacionDocumento consultaCriteriosCalificacionDocumento
	)
	    throws B2BException;

	/**
	 * Find by id turno.
	 *
	 * @param idTurno de id turno
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findByIdTurno(String idTurno)
	    throws B2BException;

	/**
	 * Find causales correccion calificacion.
	 *
	 * @param lsm_solMat de lsm sol mat
	 * @param as_solicitudNueva de as solicitud nueva
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalCorreccion> findCausalesCorreccionCalificacion(
	    SolicitudMatricula lsm_solMat, String as_solicitudNueva, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find constante by id.
	 *
	 * @param as_constante de as constante
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de constantes
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Constantes findConstanteById(String as_constante, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find data bandeja.
	 *
	 * @param auaiu_usuarioActividad de auaiu usuario actividad
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<UsuarioActividadUI> findDataBandeja(UsuarioActividadUI auaiu_usuarioActividad)
	    throws B2BException;

	/**
	 * Find datos ant sistema by id.
	 *
	 * @param as_idDatosAntSistema de as id datos ant sistema
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de datos ant sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosAntSistema findDatosAntSistemaById(
	    String as_idDatosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find detail inbox.
	 *
	 * @param as_userId de as user id
	 * @param as_idTurno de as id turno
	 * @param as_idSolicitud de as id solicitud
	 * @param idState de id state
	 * @return el valor de list
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findDetailInbox(
	    String as_userId, String as_idTurno, String as_idSolicitud, Long idState
	)
	    throws B2BException;

	/**
	 * Find detalle ant sistema by id.
	 *
	 * @param adas_detalle de adas detalle
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de detalle ant sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleAntSistema findDetalleAntSistemaById(
	    DetalleAntSistema adas_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find detalle matriculas.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion findDetalleMatriculas(RegistroCalificacion aorc_rc)
	    throws B2BException;

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
	    throws B2BException;

	/**
	 * Find documento by id turno historia.
	 *
	 * @param arc_registroCalificacion de arc registro calificacion
	 * @param ab_just0400Group de ab just 0400 group
	 * @param ab_anotacionCancelada de ab anotacion cancelada
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion findDocumentoByIdTurnoHistoria(
	    RegistroCalificacion arc_registroCalificacion, boolean ab_just0400Group, boolean ab_anotacionCancelada
	)
	    throws B2BException;

	/**
	 * Find id solicitud by id turno historia.
	 *
	 * @param idTurnoHistoria de id turno historia
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findIdSolicitudByIdTurnoHistoria(String idTurnoHistoria)
	    throws B2BException;
	/**
	 * 
	 * Método de validación del acto 0463 en calificación
	 * @param acm_matriculas
	 * @throws B2BException
	 */
	public void validarActo0463(Collection<Map<String, Object>> acm_matriculas)
		    throws B2BException;

	/**
	 * Find inbox by user id.
	 *
	 * @param at_t de at t
	 * @param al_idMotivo de al id motivo
	 * @return el valor de tramite cantidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TramiteCantidad findInboxByUserId(Turno at_t, long al_idMotivo)
	    throws B2BException;

	/**
	 * Find intervinientes.
	 *
	 * @param ls_idAnotacion de ls id anotacion
	 * @param ls_circulo de ls circulo
	 * @param ls_matricula de ls matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Anotacion> findIntervinientes(Long ls_idAnotacion, String ls_circulo, Long ls_matricula)
	    throws B2BException;

	/**
	 * Find lindero.
	 *
	 * @param alrc_linderoRegistroCalificacion de alrc lindero registro calificacion
	 * @param ab_accion de ab accion
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findLindero(LinderoRegistroCalificacion alrc_linderoRegistroCalificacion, boolean ab_accion)
	    throws B2BException;

	/**
	 * Find matriculas.
	 *
	 * @param as_idMatricula de as id matricula
	 * @param as_idTurnoHist de as id turno hist
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_ipRemote de as ip remote
	 * @param ab_b de ab b
	 * @param al_etapaP de al etapa P
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion findMatriculas(
	    String as_idMatricula, String as_idTurnoHist, String as_idTurno, String as_userId, String as_ipRemote,
	    boolean ab_b, long al_etapaP
	)
	    throws B2BException;

	/**
	 * Find matriculas by id solicitud.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findMatriculasByIdSolicitud(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find matriculas inf by turno.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion findMatriculasInfByTurno(String as_idTurno, String as_userId, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find matriculas informacion.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion findMatriculasInformacion(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Find observaciones by id turno historia.
	 *
	 * @param idTurnoHistoria de id turno historia
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findObservacionesByIdTurnoHistoria(String idTurnoHistoria)
	    throws B2BException;

	/**
	 * Find persona.
	 *
	 * @param is_idTurnoHistoria de is id turno historia
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona findPersona(String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Find predio documento by turno.
	 *
	 * @param as_userId de as user id
	 * @param as_idTurno de as id turno
	 * @param as_section de as section
	 * @return el valor de list
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findPredioDocumentoByTurno(String as_userId, String as_idTurno, String as_section)
	    throws B2BException;

	/**
	 * Find predio documento by turno.
	 *
	 * @param as_userId de as user id
	 * @param as_idTurno de as id turno
	 * @param as_section de as section
	 * @param ad_fechaDocumento de ad fecha documento
	 * @return el valor de list
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findPredioDocumentoByTurno(
	    String as_userId, String as_idTurno, String as_section, Date ad_fechaDocumento
	)
	    throws B2BException;

	/**
	 * findPredioRegistro.
	 *
	 * @param idTurnoHistoria de id turno historia
	 * @return el valor de la coleccion de predios registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findPredioRegistro(String idTurnoHistoria)
	    throws B2BException;

	/**
	 * Encuentra registro pago exceso por un turno en especifico.
	 *
	 * @param as_idTurno de as id turno
	 * @return el valor de registro pago exceso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroPagoExceso findRegistroPagoExcesoByTurno(String as_idTurno)
	    throws B2BException;

	/**
	 * Find solicitud by id turno.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Solicitud findSolicitudByIdTurno(String as_idTurno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Find solicitud by id turno historia.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return el valor de solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Solicitud findSolicitudByIdTurnoHistoria(String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Find solicitud by TH.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Solicitud findSolicitudByTH(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find solicitud matricula.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findSolicitudMatricula(SolicitudMatricula at_param)
	    throws B2BException;

	/**
	 * Find solicitud matricula acto.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatriculaActo> findSolicitudMatriculaActo(SolicitudMatriculaActo at_param)
	    throws B2BException;

	/**
	 * Find solicitud matricula estado.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findSolicitudMatriculaEstado(SolicitudMatricula at_param)
	    throws B2BException;

	/**
	 * Find solicitud matricula order circulo.
	 *
	 * @param asm_sm de asm sm
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findSolicitudMatriculaOrderCirculo(SolicitudMatricula asm_sm)
	    throws B2BException;

	/**
	 * Find solicitud matriculas TH.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findSolicitudMatriculasTH(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find tipo acto by id.
	 *
	 * @param ls_idTipoActo de ls id tipo acto
	 * @return el valor de tipo acto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActo findTipoActoById(String as_idTipoActo)
	    throws B2BException;

	/**
	 * Find tipo actos.
	 *
	 * @param turno de turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoActo> findTipoActos(String as_turno)
	    throws B2BException;

	/**
	 * Find tmp solicitud matricula.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TmpSolicitudMatricula> findTmpSolicitudMatricula(TmpSolicitudMatricula at_param)
	    throws B2BException;

	/**
	 * Find tmp solicitud matricula acto.
	 *
	 * @param at_param de at param
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TmpSolicitudMatriculaActo> findTmpSolicitudMatriculaActo(TmpSolicitudMatriculaActo at_param)
	    throws B2BException;

	/**
	 * Find turno.
	 *
	 * @param idTurno de id turno
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno findTurno(String idTurno)
	    throws B2BException;

	/**
	 * Find turno historia.
	 *
	 * @param as_Idturno de as idturno
	 * @param al_idEtapa de al id etapa
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria findTurnoHistoria(Long as_Idturno, Long al_idEtapa)
	    throws B2BException;

	/**
	 * Find turno historia anterior by id.
	 *
	 * @param ath_param de ath param
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria findTurnoHistoriaAnteriorById(TurnoHistoria ath_param)
	    throws B2BException;

	/**
	 * Find turno historia by id.
	 *
	 * @param ath_param de ath param
	 * @return el valor de turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException;

	/**
	 * Find turnos cantidad.
	 *
	 * @param ls_asg de ls asg
	 * @param ls_idUsuario de ls id usuario
	 * @param ll_idEtapa de ll id etapa
	 * @param ab_allUsuarios de ab all usuarios
	 * @return el valor de integer
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Integer findTurnosCantidad(String ls_asg, String ls_idUsuario, long ll_idEtapa, boolean ab_allUsuarios)
	    throws B2BException;

	/**
	 * Método encargado de validar si el tipo acto gener segregación o es de apertura.
	 *
	 * @param aps_predio Objeto que contiene los datos del predio a validar.
	 * @param ab_accion Variable boolean que indica si el predio es segregado o con base en.
	 * @return Variable de tipo boolean que indica si el tipo acto genera segregación o es de apertura.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean generaSegregacionOApertura(PredioSegregado aps_predio, boolean ab_accion)
	    throws B2BException;

	/**
	 * Generar alertas.
	 *
	 * @param acrc_data de acrc data
	 * @param arc_data de arc data
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Object[]> generarAlertas(
	    Collection<RegistroCalificacion> acrc_data, RegistroCalificacion arc_data
	)
	    throws B2BException;

	/**
	 * Generar formulario correcciones.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarFormularioCorrecciones(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generate file cancelacion.
	 *
	 * @param aorc_rc de aorc rc
	 * @param ab_firma de ab firma
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generateFileCancelacion(
	    RegistroCalificacion aorc_rc, boolean ab_firma, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generate file nota informativa.
	 *
	 * @param arc_rc de arc rc
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generateFileNotaInformativa(
	    RegistroCalificacion arc_rc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generete file.
	 *
	 * @param aotc_tc de aotc tc
	 * @param arpc_rpc de arpc rpc
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] genereteFile(
	    TipoCausal aotc_tc, RegistroParcialCalificacion arpc_rpc, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar pdf comunicado direcciòn.
	 *
	 * @param aorc_rc de aorc rc
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_firmaMasiva de ab firma masiva
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] genereteFileComunicadoDireccion(
	    RegistroCalificacion aorc_rc, String as_userId, String as_localIp, String as_remoteIp, boolean ab_firmaMasiva
	)
	    throws B2BException;

	/**
	 * Generete file registro.
	 *
	 * @param aorc_rc de aorc rc
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_terminarEtapa de ab accion
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] genereteFileRegistro(
	    RegistroCalificacion aorc_rc, String as_userId, String as_localIp, String as_remoteIp, boolean ab_terminarEtapa
	)
	    throws B2BException;

	/**
	 * Gestion predio ciudadano.
	 *
	 * @param as_idInteviniente de as id inteviniente
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gestionPredioCiudadano(
	    RegistroCalificacion as_idInteviniente, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar datos area.
	 *
	 * @param aaaui_data de aaaui data
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosArea(AccAreaUI aaaui_data, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Guardar datos complementacion.
	 *
	 * @param arc_data de arc data
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosComplementacion(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar datos linderos.
	 *
	 * @param arc_data de arc data
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosLinderos(
	    RegistroCalificacion arc_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de guardar la información de segregaciones para el proceso de correcciones.
	 *
	 * @param acps_prediosSegregados de acps predios segregados
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param ab_segregado de ab segregado
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioSegregado> guardarDatosSegregacion(
	    Collection<PredioSegregado> acps_prediosSegregados, String as_idTurnoHistoria, String as_idTurno,
	    String as_userId, String as_localIpAddress, String as_remoteIpAddress, String as_idCirculo, Long al_idMatricula,
	    boolean ab_segregado
	)
	    throws B2BException;

	/**
	 * Guardar info digitador.
	 *
	 * @param adp_dp de adp dp
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion guardarInfoDigitador(
	    RegistroCalificacion adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar info registro.
	 *
	 * @param adp_dp de adp dp
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion guardarInfoRegistro(
	    RegistroCalificacion adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargadso de guardar la información de medidas cautelares.
	 *
	 * @param as_idTurno String con identificador de turno
	 * @param as_tipoProceso String con tipo de proceso
	 * @param as_numeroProceso String con número de proceso
	 * @param aoo_oficinaOrigen Objeto OficinaOrigen contenedor de la iformación a guardar
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarInformacionMedidaCautelar(
	    String as_idTurno, String as_tipoProceso, String as_numeroProceso, OficinaOrigen aoo_oficinaOrigen,
	    String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Guardar información panel información notificacion.
	 *
	 * @param apd_direccion de persona dirección
	 * @param ar_registro de ar registro
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarInformacionNotificacion(
	    PersonaDireccion apd_direccion, Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar salvedades.
	 *
	 * @param asp_salvedad de asp salvedad
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarSalvedades(
	    AccSalvedadPredio asp_salvedad, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar salvedades anotacion.
	 *
	 * @param asa_salvedad de asa salvedad
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarSalvedadesAnotacion(
	    AccSalvedadAnotacion asa_salvedad, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar solicitudes apoyo regional orip.
	 *
	 * @param asanui_solicitudApoyoNacional de asanui solicitud apoyo nacional
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarSolicitudesApoyoRegionalOrip(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNacional, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar tipo numero proceso.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_tipoProceso de as tipo proceso
	 * @param as_numeroProceso de as numero proceso
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarTipoNumeroProceso(
	    String as_idTurno, String as_tipoProceso, String as_numeroProceso, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Inactivar anotacion parcial.
	 *
	 * @param as_idAnotacionParcial de as id anotacion parcial
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void inactivarAnotacionParcial(String as_idAnotacionParcial, String as_userId, String as_localIp)
	    throws B2BException;

	/**
	 * Insertar matriculas segregacion.
	 *
	 * @param arc_data de arc data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion insertarMatriculasSegregacion(
	    RegistroCalificacion arc_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de marcar las anotaciones que han sido corregidas.
	 *
	 * @param aap_anotacion Objeto que contiene la información de la anotación.
	 * @param aca_anotacionesCargadas de aca anotaciones cargadas
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIpAddress de as local ip address
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void marcarAnotacionesCorreccion(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacion,
	    Collection<Anotacion> aca_anotacionesCargadas, String as_userId, String as_localIpAddress
	)
	    throws B2BException;

	/**
	 * Matriculas A heredar.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion matriculasAHeredar(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Modificar anotacion.
	 *
	 * @param aorc_rc de aorc rc
	 * @param ab_accion de ab accion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion modificarAnotacion(
	    RegistroCalificacion aorc_rc, boolean ab_accion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Modificar ciudadano.
	 *
	 * @param aorc_rc de aorc rc
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void modificarCiudadano(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Modificar IDS.
	 *
	 * @param aea_datosEliminar de aea datos eliminar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void modificarIDS(EliminarAnotacion aea_datosEliminar)
	    throws B2BException;

	/**
	 * Modifyid anotacion.
	 *
	 * @param as_idAnt de as id ant
	 * @param as_idnew de as idnew
	 * @param as_userId de as user id
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion modifyidAnotacion(String as_idAnt, String as_idnew, String as_userId)
	    throws B2BException;

	/**
	 * Nota devolutiva.
	 *
	 * @param aotc_tc de aotc tc
	 * @param ab_accion de ab accion
	 * @param as_observaciones de as observaciones
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tipo causal
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoCausal notaDevolutiva(
	    TipoCausal aotc_tc, boolean ab_accion, String as_observaciones, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener direccion predio.
	 *
	 * @param adp_dp de adp dp
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de direccion del predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionDelPredio obtenerDireccionPredio(
	    DireccionPredio adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener direccion predio digitadore masivo.
	 *
	 * @param adp_dp de adp dp
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de direccion del predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionDelPredio obtenerDireccionPredioDigitadoreMasivo(
	    DireccionPredio adp_dp, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener etapa actual.
	 *
	 * @param as_turnoHistoria de as turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de big decimal
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public BigDecimal obtenerEtapaActual(
	    String as_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener etapa anterior.
	 *
	 * @param as_turnoHistoria de as turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de big decimal
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public BigDecimal obtenerEtapaAnterior(
	    String as_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener informacion antiguo sistema.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de datos ant sistema
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosAntSistema obtenerInformacionAntiguoSistema(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Precalificar.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param ab_paramPreCalificar de ab param pre calificar
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void precalificar(
	    String as_idTurnoHistoria, boolean ab_paramPreCalificar, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Proc copia definitivo temporal.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void procCopiaDefinitivoTemporal(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Proc copiar anotaciones.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion procCopiarAnotaciones(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Salvar anotaciones.
	 *
	 * @param la_anotacion de la anotacion
	 * @param userId de user id
	 * @param localIpAddress de local ip address
	 * @param ipAddress de ip address
	 * @return el valor de anotacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Anotacion salvarAnotaciones(Anotacion la_anotacion, String userId, String localIpAddress, String ipAddress)
	    throws B2BException;

	/**
	 * Salvar anotaciones englobes.
	 *
	 * @param arc_arg de arc arg
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAnotacionesEnglobes(RegistroCalificacion arc_arg)
	    throws B2BException;

	/**
	 * Salvar ant sistema.
	 *
	 * @param consultaCriteriosCalificacion de consulta criterios calificacion
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarAntSistema(
	    ConsultaCriteriosCalificacion consultaCriteriosCalificacion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar ant sistema calificacion.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAntSistemaCalificacion(TurnoHistoria ath_turnoHistoria)
	    throws B2BException;

	/**
	 * Salvar area predio englobes.
	 *
	 * @param aaaui_data de aaaui data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAreaPredioEnglobes(
	    AccAreaUI aaaui_data, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar area predio venta.
	 *
	 * @param aaaui_data de aaaui data
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarAreaPredioVenta(AccAreaUI aaaui_data, String as_idUsuario, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Salvar cheks correcciones.
	 *
	 * @param apc_datos de apc datos
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCheksCorrecciones(
	    PermisosCorreccionesUI apc_datos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar confrontacion.
	 *
	 * @param accc_ccc de accc ccc
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarConfrontacion(
	    Collection<ConfrontacionCorrectiva> accc_ccc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar datos basicos englobes.
	 *
	 * @param arc_data de arc data
	 * @param as_idUsuario de as id usuario
	 * @param as_ip de as ip
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion salvarDatosBasicosEnglobes(
	    RegistroCalificacion arc_data, String as_idUsuario, String as_ip
	)
	    throws B2BException;

	/**
	 * Salvar desenglobe.
	 *
	 * @param arc_arg de arc arg
	 * @param as_userId de as user id
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemote de as ip remote
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarDesenglobe(
	    RegistroCalificacion arc_arg, String as_userId, String as_ipLocal, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * Salvar digitador.
	 *
	 * @param ap_ap de ap ap
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param ls_idTurno de ls id turno
	 * @param ai_totalRevision de ai total revision
	 * @param ai_totalMatriculas de ai total matriculas
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param as_observaciones de as observaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDigitador(
	    AreaPredio ap_ap, Long al_idTurnoHistoria, String ls_idTurno, int ai_totalRevision, int ai_totalMatriculas,
	    String as_userId, String as_localIp, String as_remoteIp, String as_observaciones
	)
	    throws B2BException;

	/**
	 * Salvar direccion venta.
	 *
	 * @param arc_rc de arc rc
	 * @param as_idUsuario de as id usuario
	 * @param as_ip de as ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionPredioAcc salvarDireccionVenta(RegistroCalificacion arc_rc, String as_idUsuario, String as_ip)
	    throws B2BException;

	/**
	 * Salvar englobes.
	 *
	 * @param arc_data de arc data
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEnglobes(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Salvar linderos complementacion englobes.
	 *
	 * @param acc_dataComplementacion de acc data complementacion
	 * @param alrc_dataLindero de alrc data lindero
	 * @param arc_data de arc data
	 * @param as_user de as user
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarLinderosComplementacionEnglobes(
	    ComplementacionCalificacion acc_dataComplementacion, LinderoRegistroCalificacion alrc_dataLindero,
	    RegistroCalificacion arc_data, String as_user, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar permisos.
	 *
	 * @param as_observaciones de as observaciones
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarPermisos(
	    String as_observaciones, String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar reproduccion constancia calificador.
	 *
	 * @param adrc_parametros de adrc parametros
	 * @param as_usuario de as usuario
	 * @param as_ipLocal de as ip local
	 * @param as_ipRemota de as ip remota
	 * @return el valor de data reproduccion constancia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DataReproduccionConstancia salvarReproduccionConstanciaCalificador(
	    DataReproduccionConstancia adrc_parametros, String as_usuario, String as_ipLocal, String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Salvar venta parcial.
	 *
	 * @param arc_arg de arc arg
	 * @param as_user de as user
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_accion de ab accion
	 * @param ab_salvar de ab salvar
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarVentaParcial(
	    RegistroCalificacion arc_arg, String as_user, String as_localIp, String as_remoteIp, boolean ab_accion,
	    boolean ab_salvar
	)
	    throws B2BException;

	/**
	 * Salvar venta parcial no cementerio.
	 *
	 * @param aalp_lindero de aalp lindero
	 * @param as_user de as user
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarVentaParcialNoCementerio(
	    AccLinderoPredio aalp_lindero, String as_user, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Save causales matricula correccion.
	 *
	 * @param accc_causales de accc causales
	 * @param asm_solMat de asm sol mat
	 * @param as_turno de as turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void saveCausalesMatriculaCorreccion(
	    Collection<CausalCorreccion> accc_causales, SolicitudMatricula asm_solMat, String as_turno, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Save cheks.
	 *
	 * @param accc_ccc de accc ccc
	 * @param as_userId de as user id
	 * @param as_ipRemote de as ip remote
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void saveCheks(Collection<ConfrontacionCorrectiva> accc_ccc, String as_userId, String as_ipRemote)
	    throws B2BException;

	/**
	 * Save detail anotacion.
	 *
	 * @param aorc_rc de aorc rc
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion saveDetailAnotacion(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Terminar proceso apoyo nacional aprobacion.
	 *
	 * @param asanui_solicitudApoyoNacional de asanui solicitud apoyo nacional
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de solicitud apoyo nacional UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudApoyoNacionalUI terminarProcesoApoyoNacionalAprobacion(
	    SolicitudApoyoNacionalUI asanui_solicitudApoyoNacional, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Turnos vinculados.
	 *
	 * @param arc_rc de arc rc
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de registro calificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public RegistroCalificacion turnosVinculados(
	    RegistroCalificacion arc_rc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Update nota devolutiva.
	 *
	 * @param and_notaDevolutiva de and nota devolutiva
	 * @param ab_aprobador de ab aprobador
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateNotaDevolutiva(
	    TurnoHistoria and_notaDevolutiva, boolean ab_aprobador, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validacion cancelacion.
	 *
	 * @param aorc_rc de aorc rc
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validacionCancelacion(RegistroCalificacion aorc_rc)
	    throws B2BException;

	/**
	 * Validacion medida cautelar.
	 *
	 * @param acs_matriculasExtraidas de acs matriculas extraidas
	 * @param as_idTurno de as id turno
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String validacionMedidaCautelar(
	    Collection<String> acs_matriculasExtraidas, String as_idTurno, String as_userId, String as_localIpAddress,
	    String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Verifica si para un turno existen alertas activas.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param as_userId id del usuario que ejecuta la acción
	 * @param as_localIp dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp dirección IP del cliente que ejecuta la acción
	 * @return ValidacionAlertaTurno contenedor de las validaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ValidacionAlertaTurno validarAlertaTurnoTramite(
	    Long al_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar si la anotación contiene un acto que genere apertura de matrículas.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Variable de tipo boolean que indica si la anotación contiene un acto que genere apertura de matrículas.
	 * @throws B2BException
	 */
	public boolean validarAnotacionApertura(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar la anotación de corecciones a crear.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return
	 * @throws B2BException
	 */
	public Map<String, String> validarAnotacionCorreccionCrear(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String                                                as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar si la anotación contiene un acto de englobes.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Variable de tipo boolean que indica si la anotación contiene un acto de englobes.
	 * @throws B2BException
	 */
	public boolean validarAnotacionEnglobe(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar si las anotaciones editadas para los predios son validas.
	 *
	 * @param aps_predioSegregado de aps predio segregado
	 * @return Variable de tipo String que valida si hay alguna advertencia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String validarAnotacionesPredioSegregado(PredioSegregado aps_predioSegregado)
	    throws B2BException;

	/**
	 * Validar datos area.
	 *
	 * @param as_idTurno de as id turno
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarDatosArea(String as_idTurno)
	    throws B2BException;

	/**
	 * Validar documento.
	 *
	 * @param almso_documento de almso documento
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String validarDocumento(List<Map<String, Object>> almso_documento)
	    throws B2BException;

	/**
	 * Validar eliminar anotacion parcial.
	 *
	 * @param aea_datosEliminar de aea datos eliminar
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarEliminarAnotacionParcial(EliminarAnotacion aea_datosEliminar)
	    throws B2BException;

	/**
	 * Validar embargos vigentes.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @param as_idSolicitud de as id solicitud
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarEmbargosVigentes(String as_idCirculo, long al_idMatricula, String as_idSolicitud)
	    throws B2BException;

	/**
	 * Validar etapa grabacion.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @return turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno validarEtapaGrabacion(String al_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Método encargado de validar la fecha de la anotación a crear.
	 *
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación a validar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Variable String que contiene el indicador del mensaje
	 * @throws B2BException
	 */
	public String validarFechaAnotacionCrearCorrecciones(
	    com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp,
	    String                                                as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar el interviniente seleccionado.
	 *
	 * @param ap_persona Objeto que contiene la información de la persona.
	 * @param aap_anotacionPredio Objeto que contiene la información de la anotación.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return
	 * @throws B2BException
	 */
	public String validarInterviniente(
	    Persona ap_persona, com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio aap_anotacionPredio, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de validar si es la primera vez que entra al englobe.
	 *
	 * @param arc_data Objeto que contiene la información del englobe.
	 * @return Vairable tipo boolean que indica si es la primera vez que pasa por el englobe.
	 * @throws B2BException
	 */
	public boolean validarPrimerEnglobeDireccion(RegistroCalificacion arc_data)
	    throws B2BException;

	/**
	 * Método para validar si es una Prorroga entrega de pruebas
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return
	 * @throws B2BException
	 */
	public boolean validarProrrogaEntregaDePruebas(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar salvedades.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param asp_salvedad de asp salvedad
	 * @param as_observaciones de as observaciones
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @param ab_validarSegregacion de ab validar segregacion nuevo
	 * @param acps_datos de acps datos
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarSalvedades(
	    Long as_idTurnoHistoria, AccSalvedadPredio asp_salvedad, String as_observaciones, String as_idUsuario,
	    String as_localIp, String as_remoteIp, boolean ab_validarSegregacion, Collection<PredioSegregado> acps_datos
	)
	    throws B2BException;

	/**
	 * Validar salvedades.
	 *
	 * @param al_idTurnoHistoria de al id turno historia
	 * @param ab_mensaje de ab mensaje
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarSalvedades(Long al_idTurnoHistoria, boolean ab_mensaje)
	    throws B2BException;

	/**
	 * Validar sumatoria.
	 *
	 * @param as_idTurnoHistoria de as id turno historia
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarSumatoria(String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * Validar turno migrado.
	 *
	 * @param ath_turnoHistoria de ath turno historia
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarTurnoMigrado(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtiene el valor a devolver de la liquidacion
	 * @param acap_anotacionesPredio valor de los tipos actos a consultar
	 * @param as_idTurnoHistoria de as id turno historia
	 * @param as_userId de as user id
	 * @param as_remoteIp de as remote ip
	 * @param as_localIp de as local ip
	 * @return Big Decimal
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public BigDecimal valorADevolverDeLiquidacion(
	    Collection<com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio> acap_anotacionesPredio,
	    String                                                            as_idTurnoHistoria, String as_userId,
	    String                                                            as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Invocar funcion para verificar propiedad.
	 *
	 * @param as_idTurnoHistoria de as turno
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return booleana
	 * @throws B2BException de b2B exception
	 */
	public boolean verificaPropiedad(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Visualizar bandeja.
	 *
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean visualizarBandeja()
	    throws B2BException;
}
