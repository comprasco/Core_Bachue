package com.bachue.snr.prosnr01.ejb.session.stateless.envioNotificacionPago;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades EnvioNotificacionPagoRemote.
 *
 * @author  Juli?n David Vaca Rodriguez
 * Fecha de Creacion: 30/08/2019
 */
@Remote
public interface EnvioNotificacionPagoRemote
{
	
	/**
	 * Enviar notificacion pago.
	 *
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operaci?n.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void enviarNotificacionPago(String as_remoteIp)
	    throws B2BException;
}
