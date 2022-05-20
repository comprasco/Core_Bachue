package com.bachue.snr.prosnr01.ejb.session.stateless.cancelacion;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;

/**
 * Interface que contiene todos las propiedades CancelacionRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface CancelacionRemote
{
	
	/**
	 * Job de Cancelaciones.
	 * 
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void cancelacion(String as_remoteIp)
	    throws B2BException;
}
