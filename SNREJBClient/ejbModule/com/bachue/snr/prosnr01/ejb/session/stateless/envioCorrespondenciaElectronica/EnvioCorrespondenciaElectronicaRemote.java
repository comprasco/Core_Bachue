package com.bachue.snr.prosnr01.ejb.session.stateless.envioCorrespondenciaElectronica;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades EnvioCorrespondenciaElectronicaRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
@Remote
public interface EnvioCorrespondenciaElectronicaRemote
{
	/**
	 * Enviar correspondencia electronica.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarCorrespondenciaElectronica(String as_remoteIp)
	    throws B2BException;

	/**
	 * Enviar correspondencia electronica 206.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarCorrespondenciaElectronica206(String as_remoteIp)
	    throws B2BException;
}
