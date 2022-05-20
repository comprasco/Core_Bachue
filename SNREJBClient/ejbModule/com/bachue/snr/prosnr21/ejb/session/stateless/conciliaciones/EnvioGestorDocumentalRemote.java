package com.bachue.snr.prosnr21.ejb.session.stateless.conciliaciones;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades EnvioGestorDocumentalRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface EnvioGestorDocumentalRemote
{
	
	/**
	 * Enviar gestor documental.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarGestorDocumental(String as_remoteIp)
	    throws B2BException;
}
