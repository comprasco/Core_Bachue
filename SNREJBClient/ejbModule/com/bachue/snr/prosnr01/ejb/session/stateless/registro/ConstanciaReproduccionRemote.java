package com.bachue.snr.prosnr01.ejb.session.stateless.registro;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.registro.ConstanciaReproduccion;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConstanciaReproduccionRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConstanciaReproduccionRemote
{
	
	/**
	 * Constancia de reproduccion.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void constanciaReproduccion(String as_remoteIp)
	    throws B2BException;

	/**
	 * Generar constancia inscripcion reproduccion de constancia.
	 *
	 * @param acr_constanciaReproduccion de acr constancia reproduccion
	 * @param ab_firmaMasiva true si se estampa firma o false de lo contrario
	 * @param as_idUsuario Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_ipRemota Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de byte[]
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public byte[] generarConstanciaInscripcionRepConstancia(
	    ConstanciaReproduccion acr_constanciaReproduccion, boolean ab_firmaMasiva, String as_idUsuario,
	    String as_ipRemota
	)
	    throws B2BException;
}
