package com.bachue.snr.prosnr01.ejb.session.stateless.admHistoricosMisional;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.admHistoricosMisional.AdmHomologacion;
import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudMatricula;
import com.bachue.snr.prosnr01.model.ui.PagosUI;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades AdmHistoricosMisionalRemote.
 *
 * @author  Heiner Castañeda
 * Fecha de Creacion: 28/06/2019
 */
@Remote
public interface AdmHistoricosMisionalRemote
{
	/**
	 * Consultar adm homologacion.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de collection AdmHomologacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AdmHomologacion> consultarAdmHomologacion(
	    String as_turnoActualizado, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar matriculas por solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<SolicitudMatricula> consultarMatriculasPorSolicitud(
	    String as_idSolicitud, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consultar pagos.
	 *
	 * @param as_turnoActualizado de as turno actualizado
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PagosUI> consultarPagos(
	    String as_turnoActualizado, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Generar datos tramite cantidad turnos homologacion.
	 *
	 * @param as_idTurno de as id turno
	 * @param as_idUsuario de as id usuario
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de tramite cantidad
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TramiteCantidad generarDatosTramiteCantidadTurnosHomologacion(
	    String as_idTurno, String as_idUsuario, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Guardar matriculas asociadas.
	 *
	 * @param acsm_solicitudMatricula Argumento de tipo <code>Collection</code> de tipo SolicitudMatricula que contiene las matrículas asociadas.
	 * @param as_idTurno Argumento de tipo <code>String</code> que contiene el id del turno.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la operación.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void guardarMatriculasAsociadas(
	    Collection<SolicitudMatricula> acsm_solicitudMatricula, String as_idTurno, String as_userId,
	    String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Insertar acto homologado.
	 *
	 * @param aah_homologacion de aah homologacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de adm homologacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AdmHomologacion insertarActoHomologado(
	    AdmHomologacion aah_homologacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Terminar proceso homologacion.
	 *
	 * @param aah_homologacion de aah homologacion
	 * @param ab_administracionHomologacionActosLiquidacion de ab administracion homologacion actos liquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException de b 2 B exception
	 */
	public void terminarProcesoHomologacion(
	    AdmHomologacion aah_homologacion, boolean ab_administracionHomologacionActosLiquidacion, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
