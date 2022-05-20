/**
 * SBB_CB_MutacionesQuintoOrden_BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.mutacionesQuintoOrden.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoEntradaRegistrarCambioQuintoOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesQuintoOrden.registrarCambioQuintoOrden.v1.TipoSalidaRegistrarCambioQuintoOrden;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


public class SBB_CB_MutacionesQuintoOrden_BindingImpl extends BaseServices
    implements SBB_CB_MutacionesQuintoOrden_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7078907473303845047L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_MutacionesQuintoOrden_BindingImpl.class);

	/**
	 * Permite informar a Bachué los cambios que ocurren como consecuencia de la inscripción de predios o mejoras por
	 * edificaciones no declarados u omitidos durante la formación catastral o la actualización de la formación del catastro.
	 *
	 * @param atercqo_entrada Objeto contenedor de la información para utilizar en la consulta
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param as_localIp Dirección IP del servidor donde se ejecuta la acción
	 * @param as_remoteIp Dirección IP del cliente que ejecuta la acción
	 * @return TipoEntradaRegistrarCambioQuintoOrden con el resultado de la consulta
	 * @throws B2BException Si ocurre un error en la consulta
	 */
	public TipoSalidaRegistrarCambioQuintoOrden registraCambioQuintoOrden(
	    TipoEntradaRegistrarCambioQuintoOrden atercqo_entrada
	)
	    throws RemoteException
	{
		TipoSalidaRegistrarCambioQuintoOrden ltsrcqo_salida;

		ltsrcqo_salida = new TipoSalidaRegistrarCambioQuintoOrden();

		try
		{
			ltsrcqo_salida = getMutacionesQuintoOrdenRemote()
					                 .registraCambioQuintoOrden(
					    atercqo_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("registraCambioQuintoOrden", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("registraCambioQuintoOrden", le_e);
		}

		return ltsrcqo_salida;
	}
}
