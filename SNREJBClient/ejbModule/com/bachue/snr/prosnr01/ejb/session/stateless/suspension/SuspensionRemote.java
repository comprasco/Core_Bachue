package com.bachue.snr.prosnr01.ejb.session.stateless.suspension;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.calificacion.DatosEtapaAnterior;
import com.bachue.snr.prosnr01.model.calificacion.Suspension;
import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades SuspensionRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface SuspensionRemote
{
	/**
	 * Actualizar observaciones turno historia.
	 *
	 * @param lth_param de TurnoHistoria
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarObservacionesTurnoHistoria(
	    TurnoHistoria lth_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar etapa 80 anterior.
	 *
	 * @param lth_param de id turno historia
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de datos etapa anterior
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DatosEtapaAnterior buscarEtapa80Anterior(
	    String lth_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Buscar oficios texto por turno.
	 *
	 * @param ls_param de id turno
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de oficios texto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficiosTexto buscarOficiosTextoPorTurno(
	    String ls_param, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find data suspension of the procedure.
	 *
	 * @param ath_parametros de TurnoHistoria
	 * @param ab_solicitudDocumentacion de true si requiere documentacion false de lo contrario
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de suspension
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Suspension findDataSuspensionOfTheProcedure(
	    TurnoHistoria ath_parametros, boolean ab_solicitudDocumentacion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar documentos suspensiones.
	 *
	 * @param acs_parametros de Collection<Suspension>
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipRemote de as ip remote
	 * @return el valor de suspension
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Suspension generarDocumentosSuspensiones(
	    Collection<Suspension> acs_parametros, String as_userId, String as_localIp, String as_ipRemote
	)
	    throws B2BException;

	/**
	 * Terminar proceso suspensiones.
	 *
	 * @param acs_parametros de Collection<Suspension>
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de suspension
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Suspension terminarProcesoSuspensiones(
	    Collection<Suspension> acs_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar tramite suspension.
	 *
	 * @param as_circulo de id circulo
	 * @param as_matricula de id matricula
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarTramiteSuspension(
	    String as_circulo, String as_matricula, String as_usuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Validar tramite suspension.
	 *
	 * @param as_circulo de id circulo
	 * @param as_matricula de id matricula
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public boolean validarTramiteSuspension(
	    String as_circulo, String as_matricula, boolean ab_actoProhibicion, String as_usuario, String as_localIp,
	    String as_remoteIp, Collection<String> acc_alertas
	)
	    throws B2BException;

	/**
	 * Validar tramite suspension.
	 *
	 * @param as_circulo de id circulo
	 * @param as_matricula de id matricula
	 * @param as_usuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<String> validarTramiteSuspension2(
	    String as_circulo, String as_matricula, boolean ab_actoProhibicion, String as_usuario, String as_localIp,
	    String as_remoteIp, Collection<String> acc_alertas
	)
	    throws B2BException;
}
