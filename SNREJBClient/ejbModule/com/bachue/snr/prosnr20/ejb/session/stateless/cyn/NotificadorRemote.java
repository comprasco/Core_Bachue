package com.bachue.snr.prosnr20.ejb.session.stateless.cyn;

import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoEntradaAcusarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cb.notificador.acusarmensaje.v1.TipoSalidaAcusarMensaje;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades NotificadorRemote.
 *
 * @author  Juli�n David Vaca Rodriguez
 * Fecha de Creacion: 04/04/2020
 */
@Remote
public interface NotificadorRemote
{
	/**
	 * Acusar mensaje.
	 *
	 * @param ateam_entrada de TipoEntradaAcusarMensaje
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operaci�n.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operaci�n.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operaci�n.
	 * @return el valor de tipo salida acusar mensaje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaAcusarMensaje acusarMensaje(
	    TipoEntradaAcusarMensaje ateam_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;
}
