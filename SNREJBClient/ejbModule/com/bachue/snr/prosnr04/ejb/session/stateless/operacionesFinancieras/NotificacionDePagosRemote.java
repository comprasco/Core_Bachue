package com.bachue.snr.prosnr04.ejb.session.stateless.operacionesFinancieras;

import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoEntradaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.consultarestadoliquidacion.v2.TipoSalidaConsultarEstadoLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoEntradaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.notificarpago.v2.TipoSalidaNotificarPago;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoEntradaRegistrarAnulacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registraranulacion.v2.TipoSalidaRegistrarAnulacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoEntradaRegistrarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarliquidacion.v2.TipoSalidaRegistrarLiquidacion;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoEntradaRegistrarReciboCaja;
import co.gov.supernotariado.www.schemas.bachue.np.operacionesfinancieras.registrarrecibocaja.v2.TipoSalidaRegistrarReciboCaja;

import com.b2bsg.common.exception.B2BException;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades NotificacionDePagosRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 08/11/2019
 */
@Remote
public interface NotificacionDePagosRemote
{
	
	/**
	 * Consultar estado liquidacion.
	 *
	 * @param atecel_peticion de TipoEntradaConsultarEstadoLiquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida consultar estado liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaConsultarEstadoLiquidacion consultarEstadoLiquidacion(
	    TipoEntradaConsultarEstadoLiquidacion atecel_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Notificar pago.
	 *
	 * @param atenp_peticion de TipoEntradaNotificarPago
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida notificar pago
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaNotificarPago notificarPago(
	    TipoEntradaNotificarPago atenp_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Registrar anulacion.
	 *
	 * @param atera_peticion de TipoEntradaRegistrarAnulacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida registrar anulacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRegistrarAnulacion registrarAnulacion(
	    TipoEntradaRegistrarAnulacion atera_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Registrar liquidacion.
	 *
	 * @param aterl_peticion de TipoEntradaRegistrarLiquidacion
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida registrar liquidacion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRegistrarLiquidacion registrarLiquidacion(
	    TipoEntradaRegistrarLiquidacion aterl_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;

	/**
	 * Registrar recibo caja.
	 *
	 * @param aterrc_peticion de TipoEntradaRegistrarReciboCaja
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de tipo salida registrar recibo caja
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public TipoSalidaRegistrarReciboCaja registrarReciboCaja(
	    TipoEntradaRegistrarReciboCaja aterrc_peticion, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
