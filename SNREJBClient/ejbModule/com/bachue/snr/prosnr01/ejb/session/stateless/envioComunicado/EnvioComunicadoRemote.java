package com.bachue.snr.prosnr01.ejb.session.stateless.envioComunicado;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades EnvioComunicadoRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 16/06/2020
 */
@Remote
public interface EnvioComunicadoRemote
{
	
	/**
	 * JOB Enviar comunicado.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarComunicado(String as_remoteIp)
	    throws B2BException;
}
