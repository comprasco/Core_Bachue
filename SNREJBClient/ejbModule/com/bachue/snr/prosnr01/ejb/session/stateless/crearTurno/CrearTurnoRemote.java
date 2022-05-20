package com.bachue.snr.prosnr01.ejb.session.stateless.crearTurno;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades CrearTurnoRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 04/05/2020
 */
@Remote
public interface CrearTurnoRemote
{
	/**
	 * JOB Crear turno.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void crearTurno(String as_remoteIp)
	    throws B2BException;
}
