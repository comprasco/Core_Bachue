package com.bachue.snr.prosnr01.ejb.session.stateless.notificaciones;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.entrega.Entrega;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades NotificacionesRemote.
 *
 * @author  Jorge Esteban Patiño Fonseca
 * Fecha de Creacion: 11/03/2020
 */
@Remote
public interface NotificacionesRemote
{
	/**
	 * Actualizar renuncia terminos.
	 *
	 * @param as_renuncia de as renuncia
	 * @param as_idTurno de as id turno
	 * @param al_idEtapa de al id etapa
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarRenunciaTerminos(
	    String as_renuncia, String as_idTurno, long al_idEtapa, String as_idUsuario, String as_localIp,
	    String as_ipRemota
	)
	    throws B2BException;

	/**
	 * Cargar datos notificaciones.
	 *
	 * @param ae_entrega de Entrega
	 * @param al_idEtapa de al id etapa
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Notificacion cargarDatosNotificaciones(
	    Entrega ae_entrega, long al_idEtapa, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * JOB Cierre expediente.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cierreExpediente(String as_remoteIp)
	    throws B2BException;

	/**
	 * JOB Envio documento electronico.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void envioDocumentoElectronico(String as_remoteIp)
	    throws B2BException;

	/**
	 * Find inbox by turno nir notificacion personal.
	 *
	 * @param atc_tc de TramiteCantidad
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TramiteCantidad> findInboxByTurnoNirNotificacionPersonal(
	    TramiteCantidad atc_tc, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Firmar documento electronico.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algún error controlado.
	 */
	public void firmarDocumentoElectronico(String as_remoteIp)
	    throws B2BException;

	/**
	 * Generar acta notificacion personal.
	 *
	 * @param as_turno de as turno
	 * @param as_solicitud de Solicitud
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void generarActaNotificacionPersonal(
	    String as_turno, Solicitud as_solicitud, String as_userId, String as_localIp, String as_remoteIp,
	    ObtenerFirmaDTO aofd_param
	)
	    throws B2BException;

	/**
	 * Guardar acta OWCC.
	 *
	 * @param as_idTurno de as id turno
	 * @param aofdto_firma de ObtenerFirmaDTO
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarActaOWCC(
	    String as_idTurno, ObtenerFirmaDTO aofdto_firma, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Salvar notificacion.
	 *
	 * @param al_turnoHistoria de id turno historia
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void salvarNotificacion(Long al_turnoHistoria, String as_remoteIp, String as_localIp, String as_userId)
	    throws B2BException;

	/**
	 * Solicitud documento electronico.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algún error controlado.
	 */
	public void solicitudDocumentoElectronico(String as_remoteIp)
	    throws B2BException;
}
