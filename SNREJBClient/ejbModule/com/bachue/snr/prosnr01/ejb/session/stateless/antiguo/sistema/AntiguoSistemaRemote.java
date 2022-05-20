package com.bachue.snr.prosnr01.ejb.session.stateless.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.AmpliacionHistoriaRegistral;
import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.AntiguoSistemaData;
import com.bachue.snr.prosnr01.model.antiguoSistema.AreaPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.Complementacion;
import com.bachue.snr.prosnr01.model.antiguoSistema.ConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaAntSistema;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaDatosDocumento;
import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DatosBasicos;
import com.bachue.snr.prosnr01.model.antiguoSistema.DireccionDelPredio;
import com.bachue.snr.prosnr01.model.antiguoSistema.DocumentoData;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.AccComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccLinderoPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredioCiudadano;
import com.bachue.snr.prosnr01.model.sdb.acc.CompletitudComplementacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DatosAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.DetalleAntSistemaUI;
import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.PredioSegregado;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatriculaActo;
import com.bachue.snr.prosnr01.model.sdb.acc.TipoCausal;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.ComplementacionPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.CirculoRegistral;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.ZonaRegistral;
import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades AntiguoSistemaRemote.
 *
 * @author  Juli�n David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface AntiguoSistemaRemote
{
	/**
	 * Actualizar completitud complementacion.
	 *
	 * @param cc_parametros the cc parametros
	 * @param ab_definitivo102 the ab definitivo 102
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 */
	public void actualizarCompletitudComplementacion(
	    CompletitudComplementacion cc_parametros, boolean ab_definitivo102, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de actualizar direccion predio.
	 *
	 * @param adb_db Objeto que contiene la informaci�n de la direcci�n.
	 * @param as_userId Variable que contiene el id actualizarCompletitudComplementaciondel usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Objeto que contiene la informacio�n de la direcci�n actualizada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionDelPredio actualizarDireccionPredio(
	    DireccionDelPredio adb_db, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar luego de actualizar la etapa 101.
	 *
	 * @param adas_datosAntSistema Contiene la informaci�n de antiguo sistema.
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_observaciones Contiene las observaciones.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarActualizar101(
	    DatosAntSistema adas_datosAntSistema, String as_idTurnoHistoria, String as_observaciones, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar luego de asociar matriculas.
	 *
	 * @param lcsma_datosBandejaActosInsertar Contiene la informaci�n de la bandeja actos a insertar.
	 * @param lcsm_datosBandejaPrediosInsertar Contiene la informaci�n de la bandeja predios a insertar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_observaciones Contiene las observaciones.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarAsociarMatricula(
	    Collection<SolicitudMatriculaActo> lcsma_datosBandejaActosInsertar,
	    Collection<SolicitudMatricula> lcsm_datosBandejaPrediosInsertar, String as_userId, String as_localIp,
	    String as_ipRemote, String as_idTurnoHistoria, String as_observaciones
	)
	    throws B2BException;

	/**
	 * Ant sistema salvar asociar matricula individual.
	 *
	 * @param acdcpc_data Contiene la informaci�n de la consulta por cirterio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_ip Variable que contiene la ip del usuario.
	 * @param as_turnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_observaciones Contiene las observaciones.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarAsociarMatriculaIndividual(
	    Collection<DataConsultaPorCriterio> acdcpc_data, String as_userId, String as_ip, String as_turnoHistoria,
	    String as_observaciones
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar luego de asociar matriculas individuales en la etapa 101.
	 *
	 * @param amsb_archivos Contiene la informaci�n de los archivos cargados.
	 * @param ad_documento Contiene la informaci�n del documento.
	 * @param acdcpc_data Contiene la informaci�n de la consulta por criterio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @param as_observaciones Contiene las observaciones.
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarAsociarMatriculaIndividual101(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento,
	    Collection<DataConsultaPorCriterio> acdcpc_data, String as_userId, String as_localIp, String as_ipRemote,
	    String as_observaciones, String as_idTurnoHistoria
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar luego de crear matricula.
	 *
	 * @param lcapr_capr Contiene la informaci�n del predio a crear.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_observaciones Contiene las observaciones.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarCrear(
	    Collection<AccPredioRegistro> lcapr_capr, String as_userId, String as_localIp, String as_ipRemote,
	    String as_idTurnoHistoria, String as_observaciones
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar luego de crear matricula en etapa 101.
	 *
	 * @param amsb_archivos Contiene la informaci�n de los archivos cargados.
	 * @param ad_documento Contiene la informaci�n del documento.
	 * @param acapr_capr Contiene la informaci�n de los predios a crear.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @param as_observaciones Contiene las observaciones.
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarCrear101(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento, Collection<AccPredioRegistro> acapr_capr,
	    String as_userId, String as_localIp, String as_ipRemote, String as_observaciones, String as_idTurnoHistoria
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar luego de rechazar el proceso.
	 *
	 * @param atc_tiposCausales Contiene la informaci�n de los tipos causales.
	 * @param as_idTurnoHistori Contiene la informaci�n del turno historia.
	 * @param aahr_ampliacionHistoriaRegistral Contiene la informaci�n de la ampliaci�n de historia registral.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @param as_observaciones Contiene las observaciones.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antSistemaSalvarRechazar(
	    TipoCausal atc_tiposCausales, String as_idTurnoHistori,
	    AmpliacionHistoriaRegistral aahr_ampliacionHistoriaRegistral, String as_userId, String as_localIp,
	    String as_ipRemote, String as_observaciones
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar luego de realizar un informe de b�squeda.
	 *
	 * @param amsb_archivos Contiene la informaci�n de los archivos cargados.
	 * @param ad_documento Contiene la informaci�n del documento.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @param as_observaciones Contiene las observaciones.
	 * @param ls_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void antsistemaSalvarInformeBusqueda(
	    Map<String, byte[]> amsb_archivos, DocumentosSalida ad_documento, String as_userId, String as_localIp,
	    String as_ipRemote, String as_observaciones, String ls_idTurnoHistoria
	)
	    throws B2BException;

	/**
	 * Buscar completitud complementacion by turno.
	 *
	 * @param adb_datosBasicos the adb datos basicos
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @return the completitud complementacion
	 * @throws B2BException the b 2 B exception
	 */
	public CompletitudComplementacion buscarCompletitudComplementacionByTurno(
	    CompletitudComplementacion adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar todos detalles por solicitud.
	 *
	 * @param adas_parametros Contiene la informaci�n de antiguo sistema.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp correspondiente al valor de remote ip
	 * @return Contiene la informaci�n de los detalles consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAntSistemaUI> buscarTodosDetallesPorSolicitud(
	    DatosAntSistema adas_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de cargar la complementacion del predio.
	 *
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @param as_idTH Contiene la informaci�n del tunro historia.
	 * @return Contiene la informaci�n de la complementaci�n.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccComplementacionPredio cargarComplementacionPredio(String as_idTurno, Long as_idTH)
	    throws B2BException;

	/**
	 * M�todo encargado de cargar la matricula creada.
	 *
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_idDatosAnteSistema Contiene la informaci�n de antiguo sistema.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean cargarCrearMatricula(String as_idTurnoHistoria, String as_idDatosAnteSistema)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar detalle de antiguo sistema.
	 *
	 * @param acdasu_parametros Contiene la informaci�n de antiguo sistema.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de los detalles de antiguo sistema.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAntSistemaUI> consultaDetalleAntSistema(
	    DetalleAntSistemaUI acdasu_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar la max id anotacion.
	 *
	 * @param aap_anotacionPredio Contiene la informaci�n de anotaci�n predio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp correspondiente al valor de remote ip
	 * @return Contiene la informaci�n del max id anotacion.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int consultarMaxIdAnotacion(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar la max orden.
	 *
	 * @param aap_anotacionPredio Contiene la informaci�n de anotaci�n predio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n del max orden.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int consultarMaxOrden(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de crear detalle de ant sistema.
	 *
	 * @param acdasu_parametros Contiene la informaci�n del detalle de antiguo sistema.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp correspondiente al valor de remote ip
	 * @return Contiene la informaci�n del detalle antiguo sistema UI
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DetalleAntSistemaUI crearDetalleAntSistema(
	    DetalleAntSistemaUI acdasu_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de eliminar un registro por circulo, matricula y turno.
	 *
	 * @param aps_ps Contiene la informaci�n del predio a eliminar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteByCirculoMatriculaTurno(PredioSegregado aps_ps)
	    throws B2BException;

	/**
	 * M�todo encargado de eliminar un registro.
	 *
	 * @param aps_ps Contiene la informaci�n de la direcci�n a eliminar.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void deleteById(DireccionPredioAcc aps_ps)
	    throws B2BException;

	/**
	 * M�todo encargado de eliminar una anotacion predio.
	 *
	 * @param aap_anotacionPredio Contiene la informaci�n de la anotaci�n del predio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de las anotaciones.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AnotacionPredio> eliminarAnotacioPredio(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * M�todo encargado de eliminar el detalle antiguo sistema.
	 *
	 * @param adasu_parametros Contiene la informaci�n del detalle de antiguo sistema.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarDetalleAntSistema(
	    DetalleAntSistemaUI adasu_parametros, String as_userId, String as_localIp, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * M�todo encargado de eliminar matriculas asociadas.
	 *
	 * @param adas_datosAntSistema Contiene la informaci�n de datos antiguo sistema.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarMatriculasAsociadas(
	    DatosAntSistema adas_datosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de eliminar las matriculas creadas.
	 *
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @param as_idDatosAntSistema Contiene la informaci�n de datos ant sistema.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarMatriculasCreadas(
	    String as_idTurno, String as_idDatosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de enviar al responsable de aprobacion firma libro ant sistema.
	 *
	 * @param ath_parametros correspondiente al valor de TurnoHistoria
	 * @param al_idMotivo correspondiente al valor de id motivo
	 * @param as_userId Variable de tipo String que contiene el id del usuario que est� realizando el tr�mite.
	 * @param as_localIp correspondiente al valor de local ip
	 * @param as_remoteIp Variable de tipo String que contiene la ip del usuario que est� realizando el tr�mite.
	 * @throws B2BException Se genera cuando se presenta una excepci�n.
	 */
	public void enviarResponsableAntSistemaFirma(
	    TurnoHistoria ath_parametros, long al_idMotivo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar direccion de acc por un id.
	 *
	 * @param adp_param Contiene la informaci�n de la direcci�n a buscar.
	 * @return Contiene la informaci�n de la direcci�n encontrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DireccionPredioAcc findAccDireccionById(DireccionPredioAcc adp_param)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar un predio registro acc por su turno historia.
	 *
	 * @param aapr_accPredioRegistro Contiene la informaci�n del predio a buscar.
	 * @return Contiene la informaci�n del predio encontrado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccPredioRegistro findAccPredioRegistroByTurnoHistoria(AccPredioRegistro aapr_accPredioRegistro)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar data de antiguo sistema.
	 *
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param ldas_datosAntSistema de ldas datos ant sistema
	 * @return Contiene la informaci�n de antiguo sistema.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AntiguoSistemaData findAntiguoSistemaData(String as_userId, DatosAntSistema ldas_datosAntSistema)
	    throws B2BException;

	/**
	 * M�todo encargdo de buscar un predio por su circulo, matricula y turno.
	 *
	 * @param aps_param Contiene la informaci�n del predio a buscar.
	 * @return Contiene la informaci�n del predio encontrado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PredioSegregado findByCirculoMatriculaTurno(PredioSegregado aps_param)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar una anotaci�n por su circulo, matricula, turno y anotacion.
	 *
	 * @param aap_parametros Contiene la informaci�n la informaci�n de la anotaci�n a buscar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de anotaci�n encontrada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AnotacionPredio findByCirculoMatriculaTurnoAnotacion(
	    AnotacionPredio aap_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar datos de consulta de antiguo sistema.
	 *
	 * @param datosAntiguoSistema Contiene la informaci�n de antiguo sistema.
	 * @return Contiene la informaci�n de los datos de consulta de antiguo sistema.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DataConsultaAntSistema> findByDatosAntSistema(DatosAntSistema datosAntiguoSistema)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar datos de documento.
	 *
	 * @param acdd_data Contiene la informaci�n de datos documento.
	 * @return Contiene la informaci�n de datos de documento consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DataConsultaDatosDocumento> findByDatosDocumento(ConsultaDatosDocumento acdd_data)
	    throws B2BException;

	/**
	 * Find codigo nombre circulo destino.
	 *
	 * @param as_idCirculoDestino correspondiente al valor de as id circulo destino
	 * @return el valor de circulo registral
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CirculoRegistral findCodigoNombreCirculoDestino(String as_idCirculoDestino)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar complementacion.
	 *
	 * @param lc_complementacion Contiene la informaci�n de complementaci�n a buscar.
	 * @return Contiene la informaci�n de complementaci�n consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ComplementacionPredio findComplementacion(Complementacion lc_complementacion)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar datos de antiguo sistema por id turno historia.
	 *
	 * @param al_idTurnoHistoria Contiene la informaci�n del id turno historia.
	 * @return Contiene la informaci�n de los datos de antiguo sistema.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosAntSistema findDatosAntSistema(Long al_idTurnoHistoria)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar datos de documento.
	 *
	 * @param al_turnoHistoria Contiene la informaci�n de turno historia.
	 * @return Contiene la informaci�n de los datos documento consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaDatosDocumento findDatosDocumento(Long al_turnoHistoria)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar datos del predio por turno.
	 *
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @param as_section Contiene la informaci�n de la secci�n.
	 * @return Contiene la informaci�n de la lista.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findDatosPredioByTurno(String as_userId, String as_idTurno, String as_section)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar detail inbox.
	 *
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param al_idEtapa Contiene la informaci�n de la etapa.
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @param as_nir Contiene la informaci�n del nir.
	 * @return Contiene la informaci�n de la lista.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List<Map<String, Object>> findDetailInbox(
	    String as_userId, Long al_idEtapa, String as_idTurno, String as_nir
	)
	    throws B2BException;

	/**
	 * M�todo encargado de buscar documento data.
	 *
	 * @param userId Variable que contiene el id del usuario.
	 * @param acdd_consultaDatosDocumento Contiene la informaci�n del documento a consultar.
	 * @return Contiene la informaci�n del documento consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentoData findDocumentoData(String userId, ConsultaDatosDocumento acdd_consultaDatosDocumento)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar turno por su circulo origen.
	 *
	 * @param at_turno Contiene la informaci�n del turno.
	 * @return Contiene la informaci�n del turno consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno findIdCirculoOrigen(Turno at_turno)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar inbox by user id.
	 *
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @param as_nir Contiene la informaci�n del nir.
	 * @param al_etapa contiene la etapa a consultar
	 * @return Contiene la informaci�n de los tramites.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByUserId(
	    String as_userId, String as_idTurno, String as_nir, long al_etapa
	)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar informes de busqueda.
	 *
	 * @param ads_documentoSalida Contiene la informaci�n de los documentos a consultar.
	 * @return Contiene la informaci�n de los documentos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> findInformesBusqueda(DocumentosSalida ads_documentoSalida)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar matriculas solicitud antiguo sistema.
	 *
	 * @param as_idDatosAntSistemaActual Contiene la informaci�n de datos antiguo sistema actual.
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @return Contiene la informaci�n del mapa de datos de solicitud matricula acto.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Collection<SolicitudMatriculaActo>> findMatriculasSolicitudAntSistema(
	    String as_idDatosAntSistemaActual, String as_idTurno
	)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar naturaleza juridica por su id.
	 *
	 * @param anj_param Contiene la informaci�n de la naturaleza juridica a consultar.
	 * @return Contiene la informaci�n de la naturaleza juridica consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NaturalezaJuridica findNaturalezaJuridicaById(NaturalezaJuridica anj_param)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar als observaciones del calificador.
	 *
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @return Contiene la informaci�n de las observaciones.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findObservacionesCalificador(String as_idTurnoHistoria)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar predio registro por su id.
	 *
	 * @param ap_parametros Contiene la informaci�n de la anotacion a consultar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @return Contiene la informaci�n de la anotaci�n consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Anotacion findPredioRegistroById(Anotacion ap_parametros, String as_userId)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar el proceso de antiguo sistema.
	 *
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @param as_idDatosAntSistemaActual Contiene la informaci�n de datos antiguo sistema actual.
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @return Contiene la informaci�n del mapa de procesos.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Boolean> findProcesoAntiguoSistema(
	    String as_idTurno, String as_idDatosAntSistemaActual, String as_idTurnoHistoria
	)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar el tipo acto por su id.
	 *
	 * @param ata_parametros Contiene la informaci�n del tipo acto a consultar.
	 * @return Contiene la informaci�n del tipo acto consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoActo findTipoActoById(TipoActo ata_parametros)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar turno historia por su id.
	 *
	 * @param ath_param Contiene la informaci�n del turno historia.
	 * @return Contiene la informaci�n del turno historia consultado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException;

	/**
	 * M�todo encargado de consultar una zona registral circulo.
	 *
	 * @param azr_zonaRegistral Contiene la informaci�n de la zona registral circulo a consultar.
	 * @return Contiene la informaci�n de la zona registral circulo consultada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ZonaRegistral findZonaRegistralCirculo(ZonaRegistral azr_zonaRegistral)
	    throws B2BException;

	/**
	 * Firmar libro ant sistema.
	 *
	 * @param as_idDatosAntSistema correspondiente al valor de as id datos ant sistema
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void firmarLibroAntSistema(
	    String as_idDatosAntSistema, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de generar el archivo de rechazo.
	 *
	 * @param aotc_tc Contiene la informaci�n del tipo causal.
	 * @param as_turnoH Contiene la informaci�n del turno historia.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param aahr_ahr Contiene la informaci�n de la ampliaci�n historia registral.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n del documento generado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarArchivoRechazo(
	    TipoCausal aotc_tc, String as_turnoH, String as_userId, AmpliacionHistoriaRegistral aahr_ahr, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo de generaci�n del documento de matr�cula oficio para AS
	 * @param aot_oficioTextoData con los datos para generar el documento
	 * @param as_userId con el usuario de la transacci�n
	 * @param as_remoteIp con la ip remota del usuario de la transacci�n
	 * @param as_localIp con la ip local del usuario de la transacci�n
	 * @param al_motivo con el id motivo de la plantilla a generar
	 * @return de tipo byte con el documento generado
	 * @throws B2BException
	 */
	public byte[] generarDocumentoMatriculaOficioAS(
	    OficiosTexto aot_oficioTextoData, String as_userId, String as_remoteIp, String as_localIp, long al_motivo
	)
	    throws B2BException;

	/**
	 * Generar pdf autorizacion firma.
	 *
	 * @param alth_lth de alth lth
	 * @param ab_cargar de ab cargar
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfAutorizacionFirma(
	    TurnoHistoria alth_lth, boolean ab_cargar, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de generar el pdf de solicitud de complementacion.
	 *
	 * @param aahr_ampliacionHistoriaRegistral Contiene la informaci�n ampliaci�n historia registral.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n del documento generado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfSolicitudComplementacion(
	    AmpliacionHistoriaRegistral aahr_ampliacionHistoriaRegistral, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar pdf solicitud complementacion ofi destino.
	 *
	 * @param adas_das correspondiente al valor de adas das
	 * @param amso_mso correspondiente al valor de amso mso
	 * @param ab_vieneDeEtapaAntSis correspondiente al valor de ab viene de etapa ant sis
	 * @param as_idUsuario correspondiente al valor de as id usuario
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfSolicitudComplementacionOfiDestino(
	    String alth_lth, boolean ab_vieneDeEtapaAntSis, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de generar el pdf de solicitud de firma.
	 *
	 * @param adas_das de adas das
	 * @param amso_mso de amso mso
	 * @param ab_vieneDeEtapaAntSis de  ab viene de etapa ant sis
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarPdfSolicitudFirma(
	    DatosAntSistema adas_das, Map<String, Object> amso_mso, boolean ab_vieneDeEtapaAntSis, String as_idUsuario,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos de anotacion.
	 *
	 * @param aap_anotacionPredio Contiene la informaci�n de la anotaci�n predio a guardar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosAnotacion(
	    AnotacionPredio aap_anotacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos de antiguo sistema.
	 *
	 * @param aap_anotacionPredio Contiene la informaci�n de la anotaci�n del predio.
	 * @param adas_detalleAntSistema Contiene la informaci�n del detalle de antiguo sistema.
	 * @param aa_anotacion Contiene la informaci�n de la anotaci�n.
	 * @param aa_iterador Contiene la informaci�n de la anotaci�n iterador.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosAntSistema(
	    AnotacionPredio aap_anotacionPredio, DetalleAntSistema adas_detalleAntSistema, Anotacion aa_anotacion,
	    Anotacion aa_iterador, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos de apertura.
	 *
	 * @param adb_datosBasicos Contiene la informaci�n de los datos b�sicos.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosApertura(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos catastral.
	 *
	 * @param adb_datosBasicos Contiene la informaci�n de datos b�sicos.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosCatastral(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos de direccion.
	 *
	 * @param aapm_predio Contiene la informaci�n de predio registro acc.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosDireccion(
	    AccPredioRegistro aapm_predio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos de documento anotacion.
	 *
	 * @param ad_documento Contiene la informaci�n del documento.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosDocumentoAnotacion(
	    Documento ad_documento, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos de especificacion de la anotacion.
	 *
	 * @param aap_anotacionPredio Contiene la informaci�n de la anotaci�n del predio.
	 * @param al_idAnotacion Contiene la informaci�n del id anotaci�n.
	 * @param aa_anotacion Contiene la informaci�n de la anotaci�n.
	 * @param ath_turnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosEspecificacionAnotacion(
	    AnotacionPredio aap_anotacionPredio, Long al_idAnotacion, Anotacion aa_anotacion,
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los datos de matriculas.
	 *
	 * @param adb_datosBasicos Contiene la informaci�n de los datos b�sicos.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDatosMatriculas(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar el informe de busqueda.
	 *
	 * @param as_observaciones Contiene la informaci�n de las observaciones.
	 * @param ad_documento Contiene la informaci�n del documento.
	 * @param amsb_archivos Contiene la informaci�n de los archivos.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarInformeDeBusqueda(
	    String as_observaciones, DocumentosSalida ad_documento, Map<String, byte[]> amsb_archivos, String as_userId,
	    String as_localIp, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * M�todo encargado de guardar los intervinientes de una anotacion.
	 *
	 * @param aa_anotacion Contiene la informaci�n de la anotaci�n.
	 * @param aa_anotacionPredioCiudadano Contiene la informaci�n del ciudadano para la anotaci�n.
	 * @param as_idSolicitud Contiene la informaci�n de la solicitud.
	 * @param as_idAnotacionPredio Contiene la informaci�n de la anotaci�n predio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarIntervinientesAnotacion(
	    Anotacion aa_anotacion, AnotacionPredioCiudadano aa_anotacionPredioCiudadano, String as_idSolicitud,
	    String as_idAnotacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de inactivar el informe de resultados.
	 *
	 * @param as_idTurno Contiene la informaci�n del turno.
	 * @param as_idDatosAntSistema Contiene la informaci�n de datos de antiguo sistema.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void inactivarInformeResultados(
	    String as_idTurno, String as_idDatosAntSistema, String as_userId, String as_localIp, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * M�todo encargado de insertar o actualizar los datos de antiguo sistema para la solicitud.
	 *
	 * @param adas_temp Contiene la informaci�n de datos antiguo sistema.
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_observaciones Contiene la informaci�n de las observaciones.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertUpdateDatosAntSistemaSolicitud(
	    DatosAntSistema adas_temp, String as_idTurnoHistoria, String as_observaciones, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de obtener los datos de antiguo sistema.
	 *
	 * @param as_idSolicitud Contiene la informaci�n de la solicitud.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de los datos de antiguo sistema consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DatosAntSistema> obtenerDatosAntSistema(
	    String as_idSolicitud, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de obtener los detalles de antiguo sistema.
	 *
	 * @param adas_dato Contiene la informaci�n de los datos de antiguo sistema a consultar.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de los detalles de antiguo sistema consultados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DetalleAntSistema> obtenerDetallesAntSistema(
	    DatosAntSistema adas_dato, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de obtener el documento de antiguo sistema.
	 *
	 * @param adas_detalle Contiene la informaci�n del detalle de antiguo sistema para realizar la b�squeda.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n del documento de antiguo sistema encontrado.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento obtenerDocumentoAntSistema(
	    DetalleAntSistema adas_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de reordenar las anotaciones.
	 *
	 * @param aa_anotacion Contiene la informaci�n de la anotaci�n.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void reordenarAnotaciones(Anotacion aa_anotacion, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * M�todo encargado de validar si est� revisado antiguo sistema.
	 *
	 * @param adas_datosAntSistema Contiene la informaci�n de los datos de antiguo sistema.
	 * @param acdasui_detallesSeleccionados Contiene la informaci�n de los detalles de antiguo sistema.
	 * @param acsm_csm Contiene la informaci�n de la solicitud matricula.
	 * @param as_turnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_turno Contiene la informaci�n del turno.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param ls_localIp correspondiente al valor de local ip
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean revisadoAntSistema(
	    DatosAntSistema adas_datosAntSistema, Collection<DetalleAntSistemaUI> acdasui_detallesSeleccionados,
	    Collection<SolicitudMatricula> acsm_csm, String as_turnoHistoria, String as_turno, String as_userId,
	    String ls_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de validar si est� revisado antiguo sistema definitivo.
	 *
	 * @param adas_datosAntSistema Contiene la informaci�n de datos antiguo sistema.
	 * @param as_turnoHistoria Contiene la informaci�n de turno historia.
	 * @param as_turno Contiene la informaci�n del turno.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param ls_localIp correspondiente al valor de local ip
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void revisadoAntSistemaDefinitivo(
	    DatosAntSistema adas_datosAntSistema, String as_turnoHistoria, String as_turno, String as_userId,
	    String ls_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar las anotaciones.
	 *
	 * @param aa_parametros Contiene la informaci�n de la anotaci�n.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de la anotaci�n guardada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Anotacion salvarAnotaciones(
	    Anotacion aa_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar el area del predio.
	 *
	 * @param apr_accPredio Contiene la informaci�n del predio.
	 * @param aap_areaPredio Contiene la informaci�n del �rea del predio.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @return Contiene la informaci�n del area guardada.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccPredioRegistro salvarAreaPredio(
	    AccPredioRegistro apr_accPredio, AreaPredio aap_areaPredio, String as_userId, String as_remoteIp,
	    String as_localIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar cabida y linderos.
	 *
	 * @param llp_linderoPredio Contiene la informaci�n del lindero.
	 * @param lacp_complementacionPredio Contiene la informaci�n de la complementaci�n.
	 * @param ab_accionNueva Contiene la informaci�n para saber si la acci�n es nueva.
	 * @param lcp_complementacionPredio Contiene la informaci�n de complementaci�n.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarCabidaYLinderos(
	    AccLinderoPredio llp_linderoPredio, AccComplementacionPredio lacp_complementacionPredio, boolean ab_accionNueva,
	    ComplementacionPredio lcp_complementacionPredio, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar completitud complementacion.
	 *
	 * @param cc_parametros correspondiente al valor de cc parametros
	 * @param acr_matriculaPredio
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIp correspondiente al valor de as local ip
	 * @param as_remoteIp correspondiente al valor de as remote ip
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarCompletitudComplementacion(
	    CompletitudComplementacion cc_parametros, String as_circuloDetino, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar los datos basicos.
	 *
	 * @param adb_db Contiene la informaci�n de los datos b�sicos.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de los datos b�sicos salvados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosBasicos salvarDatosBasicos(DatosBasicos adb_db, String as_userId, String as_remoteIp)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar datos basicos ubicacion.
	 *
	 * @param adb_datosBasicos Contiene la informaci�n de datos basicos
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarDatosBasicosUbicacion(
	    DatosBasicos adb_datosBasicos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar direccion predio.
	 *
	 * @param acddp_db de Collection< DireccionDelPredio >
	 * @param as_s Contiene la informaci�n de s
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param aapm_predio Contiene la informaci�n de Acc Predio Registro
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_ipRemote Variable que contiene la ip del usuario.
	 * @return Contiene la informaci�n de la colecci�n de direcciones.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DireccionDelPredio> salvarDireccionPredio(
	    Collection<DireccionDelPredio> acddp_db, String as_s, String as_userId, AccPredioRegistro aapm_predio,
	    String as_localIp, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * Salvar proceso ant sistema.
	 *
	 * @param as_idTurnoHistoria correspondiente al valor de as id turno historia
	 * @param as_userId correspondiente al valor de as user id
	 * @param as_localIpAddress correspondiente al valor de as local ip address
	 * @param as_remoteIpAddress correspondiente al valor de as remote ip address
	 * @param as_observaciones correspondiente al valor de as observaciones
	 * @param ab_solicitaComplementacionOtroCirculo correspondiente al valor de ab solicita complementacion otro circulo
	 * @return retorna <code>true</code> si se cumple la condicion, de lo contario retorna <code>false</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarProcesoAntSistema(
	    String as_idTurnoHistoria, String as_userId, String as_localIpAddress, String as_remoteIpAddress,
	    String as_observaciones, boolean ab_solicitaComplementacionOtroCirculo
	)
	    throws B2BException;

	/**
	 * M�todo encargado de salvar proceso ant sistema.
	 *
	 * @param as_idTurnoHistoria Contiene la informaci�n del turno historia.
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @param as_observaciones Contiene la informaci�n de las observaciones.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean salvarProcesoAntSistema(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp, String as_observaciones
	)
	    throws B2BException;

	/**
	 * M�todo para terminar el turno historia de 105
	 * @param al_etapa
	 * @param as_idTurnoHistoria
	 * @param al_motivo
	 * @param as_userId
	 * @param as_remoteIp
	 * @param ls_localIp
	 * @throws B2BException
	 */
	public void terminarTurnoHistoria(
	    long al_etapa, String as_idTurnoHistoria, long al_motivo, String as_userId, String as_remoteIp,
	    String ls_localIp
	)
	    throws B2BException;

	/**
	 * M�todo encargado de actualizar fecha inicial.
	 *
	 * @param ath_param Contiene la informaci�n de Turno Historia
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void updateFechaInicial(TurnoHistoria ath_param, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * M�todo encargado de validar acto ejecutoria.
	 *
	 * @param as_idTipoDoc Contiene la informaci�n de tipo documento.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarActoEjecutoria(String as_idTipoDoc)
	    throws B2BException;

	/**
	 * M�todo encgargado de validar datos ant sistema iguales.
	 *
	 * @param ldas_temp Contiene la informaci�n de Datos Antiguo Sistema
	 * @param idTurnoHistoria Contiene la informaci�n de turno historia
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarDatosAntSistemaIguales(DatosAntSistema ldas_temp, String idTurnoHistoria)
	    throws B2BException;

	/**
	 * M�todo encargado de validar docuentos crear matricula.
	 *
	 * @param acapr_capr de Collection<AccPredioRegistro>
	 * @param as_idTurno Contiene la informaci�n del turno
	 * @return Contiene la informaci�n del mapa de informaci�n.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, Boolean> validarDocuentosCrearMatricula(
	    Collection<AccPredioRegistro> acapr_capr, String as_idTurno
	)
	    throws B2BException;

	/**
	 * M�todo encargado de validar si es devolucion.
	 *
	 * @param as_idTurnoHistoria Contiene la informaci�n de turno historia
	 * @param as_userId Variable que contiene el id del usuario.
	 * @param as_localIp Variable que contiene la ip del servidor.
	 * @param as_remoteIp Variable que contiene la ip del usuario.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarSiEsDevolucion(
	    String as_idTurnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
