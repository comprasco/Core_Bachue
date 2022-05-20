package com.bachue.snr.prosnr01.ejb.session.stateless.firma;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades FirmaMasivaRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface FirmaMasivaRemote
{
	
	/**
	 * Firma masiva.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void firmaMasiva(String as_remoteIp)
	    throws B2BException;
}
