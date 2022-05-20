package com.bachue.snr.prosnr01.ejb.session.stateless.gobernaciones;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades GobernacionesRemote.
 *
 * @author  Luis Chacon
 * Fecha de Creacion: 17/02/2020
 */
@Remote
public interface GobernacionesRemote
{
	
	/**
	 * Gobernaciones.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operaci�n.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void gobernaciones(String as_remoteIp)
	    throws B2BException;
}
