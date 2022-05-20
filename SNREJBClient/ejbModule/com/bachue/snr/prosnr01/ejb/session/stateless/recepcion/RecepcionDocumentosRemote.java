package com.bachue.snr.prosnr01.ejb.session.stateless.recepcion;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades RecepcionDocumentosRemote.
 *
 * @author  Gabriel Arias
 * Fecha de Creacion: 17/01/2020
 */
@Remote
public interface RecepcionDocumentosRemote
{
	
	/**
	 * Recepcion documentos.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void recepcionDocumentos(String as_remoteIp)
	    throws B2BException;
}
