package com.bachue.snr.prosnr01.ejb.session.stateless.testamentos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.registro.ReproduccionConstanciaTestamento;
import com.bachue.snr.prosnr01.model.registro.SolicitudTestamento;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.LibroTestamento;
import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades TestamentosRemote.
 *
 * @author  Duvan Beltran
 * Fecha de Creacion: 14/02/2020
 */
@Remote
public interface TestamentosRemote
{
	/**
	 * Método de busqueda de reproduccion por turno.
	 *
	 * @param ad_documento con parametros para la búsqueda
	 * @param as_userId con el usuario que solicita la busqueda
	 * @param as_localIp con la Ip local del usuario que solicita la busqueda
	 * @param as_remoteIp con la ip remota de quien solicita la busqueda
	 * @return un objeto de tipo ReproduccionConstanciaTestamento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ReproduccionConstanciaTestamento buscarRepConstanciaDocumento(
	    Documento ad_documento, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método de busqueda de reproduccion por turno.
	 *
	 * @param at_turno con parametros para la búsqueda
	 * @param as_userId con el usuario que solicita la busqueda
	 * @param as_localIp con la Ip local del usuario que solicita la busqueda
	 * @param as_remoteIp con la ip remota de quien solicita la busqueda
	 * @return un objeto de tipo ReproduccionConstanciaTestamento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ReproduccionConstanciaTestamento buscarRepConstanciaTurno(
	    Turno at_turno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta bandeja Testamentos.
	 *
	 * @param atc_tc de TramiteCantidad
	 * @param ab_detalle de true si requiere  detalle false de lo contrario
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> consultaBandeja(
	    TramiteCantidad atc_tc, boolean ab_detalle, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar turno.
	 *
	 * @param as_idTurno de id turno
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Turno consultarTurno(String as_idTurno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Enviar A etapa posterior.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAEtapaPosterior(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar Libro Testamento.
	 *
	 * @param ath_param de LibroTestamento
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public LibroTestamento findLibroTestamento(
	    LibroTestamento ath_param, String as_userId, String as_remoteIpAddress, String as_localIpAddress
	)
	    throws B2BException;

	/**
	 * Consultar turno historia.
	 *
	 * @param ath_param de TurnoHistoria
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TurnoHistoria findTurnoHistoriaById(TurnoHistoria ath_param)
	    throws B2BException;

	/**
	 * Generar constancia testamento.
	 *
	 * @param ast_solicitudTestamento de solicitud testamento
	 * @param as_nir de nir
	 * @param as_idTurnoHistoria de SolicitudTestamento
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, String as_nir, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar turno.
	 *
	 * @param as_idTurno de id turno
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de turno
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public SolicitudTestamento generarDataTestamentos(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar reproduccion de constancia testamento.
	 *
	 * @param ast_solicitudTestamento con los parametros del pdf
	 * @param as_idTurnoHistoria de id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarReproduccionConstanciaTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * guardar constancia  reproduccion testamento.
	 *
	 * @param ast_solicitudTestamento de ReproduccionConstanciaTestamento
	 * @param as_idTurnoHistoria de id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] guardarConstanciaReproduccionTestamento(
	    ReproduccionConstanciaTestamento ast_solicitudTestamento, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * guardar constancia testamento.
	 *
	 * @param ast_solicitudTestamento de SolicitudTestamento
	 * @param as_nir de nir
	 * @param as_idTurnoHistoria de id turno historia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] guardarConstanciaTestamento(
	    SolicitudTestamento ast_solicitudTestamento, String as_nir, String as_idTurnoHistoria, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método de guardado de solicitud de reproduccion  por turno.
	 *
	 * @param at_turno con parametros para la búsqueda
	 * @param as_cantidadCopias de cantidad copias
	 * @param ab_copias de copias
	 * @param as_userId con el usuario que solicita la busqueda
	 * @param as_localIp con la Ip local del usuario que solicita la busqueda
	 * @param as_remoteIp con la ip remota de quien solicita la busqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarSolicitudReproduccion(
	    ReproduccionConstanciaTestamento at_turno, String as_cantidadCopias, boolean ab_copias, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Actualizar etapa Y crear siguiente.
	 *
	 * @param ath_parametros de TurnoHistoria
	 * @param amt_motivoTramite de MotivoTramite
	 * @param as_usuario de as usuario
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void terminarTurnoHistoriaYCrearEtapa(
	    TurnoHistoria ath_parametros, MotivoTramite amt_motivoTramite, String as_usuario, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;
}
