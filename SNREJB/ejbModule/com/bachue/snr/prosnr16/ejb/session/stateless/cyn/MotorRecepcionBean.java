package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoEntradaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.consultarenvio.v1.TipoSalidaConsultarEnvio;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoEntradaEnviarMensaje;
import co.gov.supernotariado.www.schemas.bachue.cn.mensajero.enviarmensaje.v1.TipoSalidaEnviarMensaje;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr16.business.cyn.motor.recepcion.MotorRecepcionBusiness;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades MotorRecepcionBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 26/03/2020
 */
@javax.ejb.Stateless(name = "MotorRecepcion", mappedName = "MotorRecepcionMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class MotorRecepcionBean implements MotorRecepcionRemote
{
	/** Propiedad imr business. */
	private MotorRecepcionBusiness imr_business;

	/**
	 * consultar Envio.
	 *
	 * @param atecm_entrada de atecm entrada
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de tipo salida consultar envio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarEnvio consultarEnvio(
	    TipoEntradaConsultarEnvio atecm_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		TipoSalidaConsultarEnvio ltsem_salida;

		lsw_watch        = Logger.getNewStopWatch();
		ltsem_salida     = getBusiness().consultarEnvio(
			    atecm_entrada, as_userId, as_localIpAddress, as_remoteIpAddress
			);

		Logger.log(
		    lsw_watch, "MotorRecepcionBean", "consultarEnvio", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);

		return ltsem_salida;
	}

	/**
	 * enviar Mensaje.
	 *
	 * @param ateem_entrada de ateem entrada
	 * @param as_userId de as user id
	 * @param as_localIpAddress de as local ip address
	 * @param as_remoteIpAddress de as remote ip address
	 * @return el valor de tipo salida enviar mensaje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaEnviarMensaje enviarMensaje(
	    TipoEntradaEnviarMensaje ateem_entrada, String as_userId, String as_localIpAddress, String as_remoteIpAddress
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		TipoSalidaEnviarMensaje ltsem_salida;

		lsw_watch        = Logger.getNewStopWatch();
		ltsem_salida     = getBusiness().enviarMensaje(ateem_entrada, as_userId, as_localIpAddress, as_remoteIpAddress);

		Logger.log(
		    lsw_watch, "MotorRecepcionBean", "enviarMensaje", as_userId, as_localIpAddress, as_remoteIpAddress, null
		);

		return ltsem_salida;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private MotorRecepcionBusiness getBusiness()
	{
		if(imr_business == null)
			imr_business = new MotorRecepcionBusiness();

		return imr_business;
	}
}
