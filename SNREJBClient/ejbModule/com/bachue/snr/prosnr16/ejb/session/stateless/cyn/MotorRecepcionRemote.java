package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades MotorEnvioRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
@Remote
public interface MotorRecepcionRemote
{
	/**
	 * Consultar envio.
	 *
	 * @param atecm_entrada de TipoEntradaConsultarEnvio
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar envio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarEnvio consultarEnvio(
	    TipoEntradaConsultarEnvio atecm_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;

	/**
	 * Enviar mensaje.
	 *
	 * @param ateem_entrada de TipoEntradaEnviarMensaje
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIpAddress Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIpAddress Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida enviar mensaje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaEnviarMensaje enviarMensaje(
	    TipoEntradaEnviarMensaje ateem_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException;
}
