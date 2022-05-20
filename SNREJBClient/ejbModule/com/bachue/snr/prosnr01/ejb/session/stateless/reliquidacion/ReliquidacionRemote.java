package com.bachue.snr.prosnr01.ejb.session.stateless.reliquidacion;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ReliquidacionRemote.
 *
 * @author Gabriel Arias
 * Fecha de Creacion: 14/12/2021
 */
@Remote
public interface ReliquidacionRemote
{
	/**
	 * Job de Reliquidacion.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void reliquidacion(String as_remoteIp)
	    throws B2BException;
}
