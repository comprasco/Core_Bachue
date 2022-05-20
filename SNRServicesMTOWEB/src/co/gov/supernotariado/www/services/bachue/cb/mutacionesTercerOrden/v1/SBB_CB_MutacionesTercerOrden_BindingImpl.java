package co.gov.supernotariado.www.services.bachue.cb.mutacionesTercerOrden.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoEntradaConsultaCambioTercerOrden;
import co.gov.supernotariado.www.schemas.bachue.cb.mutacionesTercerOrden.consultaCambioTercerOrden.v1.TipoSalidaConsultaCambioTercerOrden;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades
 * SBB_CB_MutacionesTercerOrden_BindingImpl.
 *
 * @author Julián David Vaca Rodriguez Fecha de Creacion: 20/02/2020
 */
public class SBB_CB_MutacionesTercerOrden_BindingImpl extends BaseServices
    implements SBB_CB_MutacionesTercerOrden_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -873721052924169247L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(SBB_CB_MutacionesTercerOrden_BindingImpl.class);

	/**
	 * Permite consultar la mutación de tercer orden por los cambios que ocurren en
	 * los predios por nuevas edificaciones, construcciones o demoliciones.
	 */
	public TipoSalidaConsultaCambioTercerOrden consultaCambioTercerOrden(
	    TipoEntradaConsultaCambioTercerOrden ateccto_entrada
	)
	    throws RemoteException
	{
		TipoSalidaConsultaCambioTercerOrden ltsccto_salida;

		ltsccto_salida = new TipoSalidaConsultaCambioTercerOrden();

		try
		{
			ltsccto_salida = getMutacionesTercerOrdenRemote()
					                 .consultaCambioTercerOrden(
					    ateccto_entrada, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultaCambioTercerOrden", lb2be_e);
		}
		catch(Exception le_e)
		{
			clh_LOGGER.error("consultaCambioTercerOrden", le_e);
		}

		return ltsccto_salida;
	}
}
