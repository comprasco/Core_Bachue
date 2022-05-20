package com.bachue.snr.prosnr01.ejb.session.stateless.digitalizacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import java.util.Collection;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades DigitalizacionRemote.
 *
 * @author  Carlos Calderon
 * Fecha de Creacion: 17/09/2019
 */
@Remote
public interface DigitalizacionRemote
{
	
	/**
	 * Metodo encargado de actualizar el estado de digitalización.
	 *
	 * @param ath_parametros Argumento de tipo <code>TurnoHistoria</code> que contiene
	 * los criterios necesarios para realizar la actualización.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene
	 * el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene
	 * la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene
	 * la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void actualizarEstadoDigitalizacion(
	    TurnoHistoria ath_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Construir url capture.
	 *
	 * @param as_nir de as nir
	 * @param as_idTurno de as id turno
	 * @param as_userid de as userid
	 * @param as_localIp de as local ip
	 * @param as_remoteIp de as remote ip
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String construirUrlCapture(
	    String as_nir, String as_idTurno, String as_userid, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Método encargado de consultar los turnos para un nir y estado actividad de etapas menores a las ingresadas.
	 *
	 * @param at_parametros Argumento de tipo <code>Turno</code> que contiene el nir para realizar la consulta.
	 * @param as_userid Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la consulta.
	 * @return Collection<Turno> Colección de datos de tipo turnos con todos los registros encontrados.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Turno> consultarTurnosNirEtapaEstado(
	    Turno at_parametros, String as_userid, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Find turnos.
	 *
	 * @param at_t de Turno
	 * @param as_userid Argumento de tipo <code>String</code> que contiene el id del usuario que realiza la consulta.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la consulta.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la consulta.
	 * @return el valor de tramite cantidad
	 * @throws B2BException de b 2 B exception
	 */
	public TramiteCantidad findTurnos(Turno at_t, String as_userid, String as_localIp, String as_remoteIp)
	    throws B2BException;
}
