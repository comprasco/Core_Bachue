package com.bachue.snr.prosnr01.ejb.session.stateless.notificarCatastro;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades NotificarCatastroRemote.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 4/05/2020
 */
@Remote
public interface NotificarCatastroRemote
{
	/**
	 * JOB Notificar catastro mutacion primer orden.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void notificarCatastroPO(String as_remoteIp)
	    throws B2BException;

	/**
	 * JOB Notificar catastro mutacion segundo orden con cambio de propietario.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void notificarCatastroSOCP(String as_remoteIp)
	    throws B2BException;

	/**
	 * JOB Notificar catastro mutacion segundo orden sin cambio de propietario.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void notificarCatastroSOSP(String as_remoteIp)
	    throws B2BException;

	/**
	 * JOB Notificar catastro mutacion tercer orden.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void notificarCatastroTO(String as_remoteIp)
	    throws B2BException;
}
