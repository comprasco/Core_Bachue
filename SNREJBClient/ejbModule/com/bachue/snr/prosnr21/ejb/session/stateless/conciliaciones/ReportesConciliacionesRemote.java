package com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ReportesConciliacionesRemote.
 *
 * @author Gabriel Arias
 * Fecha de Creacion: 09/11/2021
 */
@Remote
public interface ReportesConciliacionesRemote
{
	/**
	 * Generar reportes.
	 *
	 * @param as_idProcesoConciliacion the as id proceso conciliacion
	 * @param as_userId the as user id
	 * @param as_localIp the as local ip
	 * @param as_remoteIp the as remote ip
	 * @throws B2BException the b 2 B exception
	 * @throws Exception the exception
	 */
	public void generarReportes(
	    String as_idProcesoConciliacion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException, Exception;
}
