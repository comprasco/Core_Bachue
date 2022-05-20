package com.bachue.snr.prosnr01.ejb.session.stateless.realizarNotificacion;

import com.b2bsg.common.exception.B2BException;
import com.bachue.snr.prosnr01.model.aprobacion.Aprobacion;
import com.bachue.snr.prosnr01.model.notificaciones.Notificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.DocumentosSalida;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;
import com.bachue.snr.prosnr01.model.sdb.bng.Imagenes;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades RealizarNotificacionRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 03/04/2020
 */
@Remote
public interface RealizarNotificacionRemote
{
	/**
	 * Cambiar medio publicacion.
	 *
	 * @param ath_turnoHisotria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno con la información modificar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cambiarMedioPublicacion(
	    TurnoHistoria ath_turnoHisotria, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Cambiar responsable publicacion.
	 *
	 * @param ath_turnoHisotria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno con la información modificar.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cambiarResponsablePublicacion(
			TurnoHistoria ath_turnoHisotria, String as_userId, String as_localIp, String as_remoteIp
			)
					throws B2BException;

	/**
	 * Consultar docuemnto salida.
	 *
	 * @param al_idDocumentoSalida Argumento de tipo <code>long</code> que contiene el id del documento salida.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de documentos salida
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public DocumentosSalida consultarDocuemntoSalida(
	    long al_idDocumentoSalida, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar informacion notificacion.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de notificacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Notificacion consultarInformacionNotificacion(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Desfijar avisos.
	 *
	 * @param acth_turnos de Collection<TurnoHistoria>
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void desfijarAvisos(
	    Collection<TurnoHistoria> acth_turnos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Enviar aviso.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAviso(String as_remoteIp)
	    throws B2BException;

	/**
	 * Enviar aviso web.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAvisoWeb(String as_remoteIp)
	    throws B2BException;

	/**
	 * Envio visor SGD.
	 *
	 * @param as_idDocumentoSalida de id documento salida
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String envioVisorSGD(String as_idDocumentoSalida, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Fijar avisos.
	 *
	 * @param acth_turnos de Collection<TurnoHistoria>
	 * @param al_idEtapa de al id etapa
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void fijarAvisos(
	    Collection<TurnoHistoria> acth_turnos, long al_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Insertar documento salida.
	 *
	 * @param ath_turnoHisotria de ath turno hisotria
	 * @param ath_turnoHistoria Argumento de tipo <code>TurnoHistoria</code> que contiene el turno historia asociado al documento salida.
	 * @param li_imagen Argumento de tipo <code>Imagenes</code> que contiene la imagen asociada al documento salida.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insertarDocumentoSalida(
	    TurnoHistoria ath_turnoHisotria, Imagenes li_imagen, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener casos avisos.
	 *
	 * @param al_idEtapa de id etapa
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TurnoHistoria> obtenerCasosAvisos(
	    long al_idEtapa, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener casos fijacion avisos.
	 *
	 * @param al_idEtapa Argumento de tipo <code>long</code> que contiene el id de la etapa.
	 * @param as_idProceso Argumento de tipo <code>String</code> que contiene el id del proceso.
	 * @param as_subproceso Argumento de tipo <code>String</code> que contiene el id del subproceso.
	 * @param aa_aprobacion Argumento de tipo <code>Aprobacion</code> que contiene la información de la aprobación.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<TurnoHistoria> obtenerCasosFijacionAvisos(
	    long al_idEtapa, String as_idProceso, String as_subproceso,Aprobacion aa_aprobacion, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Obtener documentos aviso.
	 *
	 * @param acth_turnos de Collection<TurnoHistoria>
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<DocumentosSalida> obtenerDocumentosAviso(
	    Collection<TurnoHistoria> acth_turnos, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * notificar.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void realizarNotificacion(String as_remoteIp)
	    throws B2BException;

	/**
	 * Validar personas notificadas.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void validarPersonasNotificadas(String as_remoteIp)
	    throws B2BException;
}
