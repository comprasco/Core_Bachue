package com.bachue.snr.prosnr11.ejb.session.stateless.controlDigitalizacion;

import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoEntradaNotificarDigitalizacion;
import co.gov.supernotariado.www.schemas.bachue.cb.controldigitalizacion.notificardigitalizacion.v1.TipoSalidaNotificarDigitalizacion;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ControlDigitalizacionRemote.
 *
 * @author  Heiner Castañeda
 * Fecha de Creacion: 07/11/2019
 */
@Remote
public interface ControlDigitalizacionRemote
{
	
	/**
	 * Actualizar datos solicitante.
	 *
	 * @param atend_request de TipoEntradaNotificarDigitalizacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida notificar digitalizacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaNotificarDigitalizacion notificarDigitalizacion(
	    TipoEntradaNotificarDigitalizacion atend_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
