package com.bachue.snr.prosnr01.ejb.session.stateless.reanotacion;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.sdb.acc.TurnoHistoria;

import javax.ejb.Remote;

/**
 * Interface que contiene todos las propiedades ReanotacionRemote.
 *
 * @author  Jorge Patiño
 * Fecha de Creacion: 22/03/2019
 */
@Remote
public interface ReanotacionRemote
{
	
	/**
	 * Retorna el valor de observaciones.
	 *
	 * @param ath_turnoHistoria de TurnoHistoria
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de observaciones
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String getObservaciones(
	    TurnoHistoria ath_turnoHistoria, String as_userId, String as_localIp,
	    String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Consulta by turno.
	 *
	 * @param as_turno de id turno
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Long consultaByTurno(String as_turno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;

	/**
	 * Enviar A entrega.
	 *
	 * @param ath_turno de TurnoHistoria
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarAEntrega(TurnoHistoria ath_turno, String as_userId, String as_localIp, String as_remoteIp)
	    throws B2BException;
}
