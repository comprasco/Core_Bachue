package com.bachue.snr.prosnr01.ejb.session.stateless.generacionCitatorio;

import javax.ejb.Remote;

import com.b2bsg.common.exception.B2BException;

/**
 * Interface que contiene todos las propiedades GeneracionCitatorioRemote.
 *
 * @author  Manuel Blanco
 * Fecha de Creacion: 30/03/2020
 */
@Remote
public interface GeneracionCitatorioRemote
{
	
	/**
	 * Procesar casos.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void procesarCasos(String as_remoteIp)
		    throws B2BException;
	
	/**
	 * Enviar documentos.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarDocumentos(String as_remoteIp)
			throws B2BException;
}
