package com.bachue.snr.prosnr01.ejb.session.stateless.envioCorrespondenciaFisica;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades EnvioCorrespondenciaFisicaRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 29/03/2020
 */
@Remote
public interface EnvioCorrespondenciaFisicaRemote
{
	/**
	 * Enviar correspondencia fisica.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarCorrespondenciaFisica(String as_remoteIp)
	    throws B2BException;

	/**
	 * Entrega documentos correspondencia.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void entregaDocumentosCorrespondencia(String as_remoteIp)
	    throws B2BException;

	/**
	 * Entrega documentos correo electronico.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void entregaDocumentosCorreoElectronico(String as_remoteIp)
	    throws B2BException;
}
