package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoEntradaNotificarCorrespondencia;
import co.gov.supernotariado.www.schemas.bachue.cn.notificadorcorrespondencia.notificarcorrespondencia.v1.TipoSalidaNotificarCorrespondencia;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades NotificadorCorrespondenciaRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 01/04/2020
 */
@Remote
public interface NotificadorCorrespondenciaRemote
{
	/**
	 * Notificar correspondencia.
	 *
	 * @param atenc_entrada de TipoEntradaNotificarCorrespondencia
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida notificar correspondencia
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaNotificarCorrespondencia notificarCorrespondencia(
	    TipoEntradaNotificarCorrespondencia atenc_entrada, String as_userId, String as_localIpAddress,
	    String                              as_remoteIpAddress
	)
	    throws B2BException;
}
