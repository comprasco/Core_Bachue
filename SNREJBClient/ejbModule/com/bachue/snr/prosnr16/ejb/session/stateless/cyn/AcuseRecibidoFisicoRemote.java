package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades AcuseRecibidoFisicoRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 30/03/2020
 */
@Remote
public interface AcuseRecibidoFisicoRemote
{
	/**
	 * JOB Acuse recibido.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void acuseRecibidoFisico(String as_remoteIp)
	    throws B2BException;
}
