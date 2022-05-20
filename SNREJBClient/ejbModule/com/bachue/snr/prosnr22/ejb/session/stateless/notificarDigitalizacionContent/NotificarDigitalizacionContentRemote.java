package com.bachue.snr.prosnr22.ejb.session.stateless.notificarDigitalizacionContent;

import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoEntradaNotificarDigitalizacionContent;
import co.gov.supernotariado.www.schemas.bachue.co.notificarDigitalizacionContent.notificarDigitalizacionContent.v1.TipoSalidaNotificarDigitalizacionContent;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades NotificarDigitalizacionContentRemote.
 *
 * @author hcastaneda
 */
@Remote
public interface NotificarDigitalizacionContentRemote
{
	/**
	 * Actualizar datos solicitante.
	 *
	 * @param atend_request Argumento de tipo <code>TipoEntradaNotificarDigitalizacionContent</code> que contiene los criterios
	 * para notificar la digitalización en completitud documental.
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida notificar digitalizacion content
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaNotificarDigitalizacionContent notificarDigitalizacionContent(
	    TipoEntradaNotificarDigitalizacionContent atend_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
