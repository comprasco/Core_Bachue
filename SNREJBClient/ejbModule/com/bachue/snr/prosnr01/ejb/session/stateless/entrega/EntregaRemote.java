package com.bachue.snr.prosnr01.ejb.session.stateless.entrega;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

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

import java.util.Collection;
import java.util.Map;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades EntregaRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface EntregaRemote
{
	
	/**
	 * Actualizar estado impresion.
	 *
	 * @param ads_documento de documento
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarEstadoImpresion(
	    DocumentosSalida ads_documento, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Actualizar interviene.
	 *
	 * @param as_interviene <code>String</code> de interviene
	 * @param as_idTurno <code>String</code> de id turno
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarInterviene(
	    String as_interviene, String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Metodo encargado de consultar los tipos documentales de un apoderado o tercero.
	 *
	 * @param as_solicitud Argumento de tipo <code>Solicitud</code> que contiene la calidad solicitante para saber si es apoderado o tercero.
	 * @param actd_tiposDocumentales Argumento de tipo <code>Collection</code> que contiene los tipos documentales cargados por pantalla.
	 * @param as_constanteApoderado Argumento de tipo <code>String</code> que contiene la constante a usar para el apoderado.
	 * @param as_constanteTercero Argumento de tipo <code>String</code> que contiene la constante a usar para el tercero.
	 * @param as_userId de as user id
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return <code>Collection</code> que contiene los tipos documentales cargados por constante.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumental> actualizarTiposDocumentales(
	    Solicitud as_solicitud, Collection<TipoDocumental> actd_tiposDocumentales, String as_constanteApoderado,
	    String as_constanteTercero, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar causal reimpresion.
	 *
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CausalReimpresion> cargarCausalReimpresion(
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar datos persona reimpresion.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona cargarDatosPersonaReimpresion(
	    String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar documentos a reimprimir.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> cargarDocumentosAReimprimir(
	    String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar documentos impresion.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> cargarDocumentosImpresion(
	    String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar documentos reimpresion.
	 *
	 * @param as_idTurno de id turno
	 * @param as_nir de nir
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteTurno> cargarDocumentosReimpresion(
	    String as_idTurno, String as_nir, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cargar recibos caja liquidacion.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_nir de as nir
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ReimpresionRecibos> cargarRecibosCajaLiquidacion(
	    String as_idTurno, String as_nir, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método que carga el detalle del turno para entrega.
	 *
	 * @param att_param <code>TramiteTurno</code> que contiene información con la cual se realizarán búsquedas
	 *     en la base de datos.
	 * @param al_etapa <code>long</code> que contiene la etapa con la cual se realizarán búsquedas
	 *     en la base de datos.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return <code>Entrega</code> cargado con la información resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Entrega cargarTurno(
	    TramiteTurno att_param, long al_etapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Devolver A calificacion.
	 *
	 * @param ath_th de ath th
	 * @param as_decisionEntrega de as decision entrega
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void devolverACalificacion(
	    TurnoHistoria ath_th, String as_decisionEntrega, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Eliminar datos persona tercero.
	 *
	 * @param ape_entrega <code>PersonaEntrega</code> ape entrega
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarDatosPersonaTercero(
	    PersonaEntrega ape_entrega, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Eliminar tipo documental.
	 *
	 * @param atd_tipoDocumental <code>TipoDocumental</code> atd tipo documental
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void eliminarTipoDocumental(
	    TipoDocumental atd_tipoDocumental, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar correos por id persona.
	 *
	 * @param asi_interSelected de asi inter selected
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> findCorreosByIdPersona(SolicitudInterviniente asi_interSelected, String as_userId)
	    throws B2BException;

	/**
	 * Buscar datos persona tercero.
	 *
	 * @param as_idPersona de as id persona
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de persona
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Persona findDatosPersonaTercero(
	    String as_idPersona, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar detalle entrega.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param idState de id state
	 * @param as_nir de nir
	 * @param as_idTurno de id turno
	 * @param ab_notificaciones de notificaciones
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteTurno> findDetailDelivery(
	    String as_userId, String as_localIp, String as_remoteIp, long idState, String as_nir, String as_idTurno,
	    boolean ab_notificaciones
	)
	    throws B2BException;

	/**
	 * Buscar detalle entrega.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param idState de id state
	 * @param as_nir de nir
	 * @param as_idTurno de id turno
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteTurno> findDetailDelivery(
	    String as_userId, String as_localIp, String as_remoteIp, long idState, String as_nir, String as_idTurno
	)
	    throws B2BException;

	/**
	 * Buscar bandeja de entrada por id usuario.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_idTurno de id turno
	 * @param as_nir de nir
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByTurnoNir(
	    String as_userId, String as_localIp, String as_remoteIp, String as_idTurno, String as_nir
	)
	    throws B2BException;

	/**
	 * Buscar bandeja de entrada por turno nir correspondencia.
	 *
	 * @param atc_tc de atc tc
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByTurnoNirCorrespondencia(
	    TramiteCantidad atc_tc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar matriculas por solicitud circulo.
	 *
	 * @param at_param de SolicitudMatricula
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findMatriculasBySolicitudCirculo(
	    SolicitudMatricula at_param, String as_userId
	)
	    throws B2BException;

	/**
	 * Buscar matriculas por solicitud circulo turno.
	 *
	 * @param asm_parametros de SolicitudMatricula
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param ls_remoteIp de ls remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> findMatriculasBySolicitudCirculoTurno(
	    SolicitudMatricula asm_parametros, String as_userId, String as_localIp, String ls_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar persona entrega por solicitud.
	 *
	 * @param ape_data de PersonaEntrega
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PersonaEntrega> findPersonaEntregaBySolicitud(
	    PersonaEntrega ape_data, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar predio documento por turno entrega.
	 *
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param adpte_idTurno de DatosPredioTurnoEntrega
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de datos predio turno entrega
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosPredioTurnoEntrega findPredioDocumentoByTurnoEntrega(
	    String as_userId, DatosPredioTurnoEntrega adpte_idTurno, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar solicitud por id.
	 *
	 * @param as_solicitud de Solicitud
	 * 	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_userId de as user id
	 * @return el valor de solicitud
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Solicitud findSolicitudById(Solicitud as_solicitud, String as_userId)
	    throws B2BException;

	/**
	 * Buscar turno por id.
	 *
	 * @param at_turno de turno
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno findTurnoById(Turno at_turno, String as_userId)
	    throws B2BException;

	/**
	 * Generar archivo zip.
	 *
	 * @param at_param de DocumentosSalida
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param ab_activos de activos
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarArchivoZip(
	    DocumentosSalida at_param, String as_userId, boolean ab_activos, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar archivo zip entrega relacion.
	 *
	 * @param aba_ba de Map<String, byte[]>
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarArchivoZipEntregaRelacion(Map<String, byte[]> aba_ba)
	    throws B2BException;

	/**
	 * Generar documento entrega.
	 *
	 * @param aefg_param <code>ObtenerFirmaDTO</code> aefg param
	 * @param as_idTurno <code>String</code> de as id turno
	 * @param ap_personaEntrega <code>Persona</code> ap persona entrega
	 * @param ab_interviene de ab interviene
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return <code>byte[]</code>
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public byte[] generarDocumentoEntrega(
	    ObtenerFirmaDTO aefg_param, String as_idTurno, Persona ap_personaEntrega, boolean ab_interviene,
	    String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws Exception;

	/**
	 * Generar documento reimpresion.
	 *
	 * @param aefg_param de ObtenerFirmaDTO
	 * @param as_idTurno de id turno
	 * @param ap_persona de persona
	 * @param acds_docs de Collection<DocumentosSalida>
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public byte[] generarDocumentoReimpresion(
	    ObtenerFirmaDTO aefg_param, String as_idTurno, Persona ap_persona, Collection<DocumentosSalida> acds_docs,
	    String as_idUsuario, String as_localIp, String as_ipRemota
	)
	    throws Exception;

	/**
	 * genera documento relacion entrega.
	 *
	 * @param actt_tramitesTurno tramites del turno para generar relacion
	 * @param ab_firmar si se va a firmar
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 * @throws Exception cuando se produce algun error en el proceso
	 */
	public Map<String, byte[]> generarDocumentoRelacionEntrega(
	    Collection<TramiteTurno> actt_tramitesTurno, boolean ab_firmar, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException, Exception;

	/**
	 * Guardar documento OWCC.
	 *
	 * @param as_idTurno <code>String</code> de id turno
	 * @param aofdto_firma de aofdto firma
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarDocumentoOWCC(
	    String as_idTurno, ObtenerFirmaDTO aofdto_firma, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar datos intervininete.
	 *
	 * @param ar_registro de registro
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de persona entrega
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PersonaEntrega salvarDatosIntervininete(
	    Registro ar_registro, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar entrega.
	 *
	 * @param ae_entrega de Entrega
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarEntrega(Entrega ae_entrega, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Salvar impresion documentos correspondencia.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarImpresionDocumentosCorrespondencia(
	    TurnoHistoria ath_turnoHistoria, String as_remoteIp, String as_localIp, String as_userId
	)
	    throws B2BException;

	/**
	 * Salvar reimpresion recibos.
	 *
	 * @param acds_reimpresion de Collection<DocumentosSalida>
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarReimpresionDocumentos(
	    Collection<DocumentosSalida> acds_reimpresion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar reimpresion recibos.
	 *
	 * @param arr_reimpresion de ReimpresionRecibos
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarReimpresionRecibos(
	    ReimpresionRecibos arr_reimpresion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Devolver A calificacion.
	 *
	 * @param acacd_tiposDocumentales <code>Collection</code> de <code>TipoDocumental</code>
	 *     los cuales serán insertados en la tabla de tipos documentales.
	 * @param as_idTurno <code>String</code> de as id turno
	 * @param as_userId <code>String</code> de as user id
	 * @param as_localIp <code>String</code> de as local ip
	 * @param as_remoteIp <code>String</code> de as remote ip
	 * @return <code>Collection</code> <code>TipoDocumental</code>
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TipoDocumental> salvarTiposDocumentales(
	    Collection<TipoDocumental> acacd_tiposDocumentales, String as_idTurno, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;
}
