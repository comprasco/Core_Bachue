/**
 * SDI_CB_GestionAlertasTitularesSOAP12BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package co.gov.supernotariado.www.services.bachue.cb.gestionalertastitulares.v1;

import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoEntradaActualizarContactoAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.actualizarcontactoalerta.v1.TipoSalidaActualizarContactoAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoEntradaConsultarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultaralerta.v1.TipoSalidaConsultarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoEntradaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.consultartarifaalertastitulares.v1.TipoSalidaConsultarTarifaAlertasTitulares;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoEntradaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.inactivaralerta.v1.TipoSalidaInactivarAlerta;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoEntradaValidarSolicitudAlertaIndividual;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertaindividual.v1.TipoSalidaValidarSolicitudAlertaIndividual;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoEntradaValidarSolicitudAlertaMasiva;
import co.gov.supernotariado.www.schemas.bachue.cb.gestionalertastitulares.validarsolicitudalertamasiva.v1.TipoSalidaValidarSolicitudAlertaMasiva;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.logging.LoggerHandler;

import com.bachue.snr.prosnr01.common.constants.ProyectosCommon;

import com.bachue.snr.prosnr04.web.services.BaseServices;

import java.rmi.RemoteException;


/**
 * Clase que contiene todos las propiedades SDI_CB_GestionAlertasTitularesSOAP12BindingImpl.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 3/06/2020
 */
public class SDI_CB_GestionAlertasTitularesSOAP12BindingImpl extends BaseServices
    implements SDI_CB_GestionAlertasTitulares_PortType
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8875969087767140142L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(
			    SDI_CB_GestionAlertasTitularesSOAP12BindingImpl.class, ProyectosCommon.GESTION_ALERTA_TITULARES_09
			);

	/**
	 * Permite actualizar los datos de contacto registrados en la alerta.
	 *
	 * @param ateaca_teaca de ateaca teaca
	 * @return el valor de tipo salida actualizar contacto alerta
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaActualizarContactoAlerta actualizarContactoAlerta(
	    TipoEntradaActualizarContactoAlerta ateaca_teaca
	)
	    throws RemoteException
	{
		TipoSalidaActualizarContactoAlerta ltsaca_response;

		ltsaca_response = null;

		try
		{
			ltsaca_response = getGestionAlertasTitularesRemote()
					                  .actualizarContactoAlerta(
					    ateaca_teaca, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("actualizarContactoAlerta", lb2be_e);
		}

		return ltsaca_response;
	}

	/**
	 * Permite obtener las alertas inscritas por titular de derechos por medio de una operación paginada.
	 *
	 * @param ateca_teca de ateca teca
	 * @return el valor de tipo salida consultar alerta
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarAlerta consultarAlerta(TipoEntradaConsultarAlerta ateca_teca)
	    throws RemoteException
	{
		TipoSalidaConsultarAlerta ltsca_salida;

		ltsca_salida = null;

		try
		{
			ltsca_salida = getGestionAlertasTitularesRemote()
					               .consultarAlerta(ateca_teca, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarAlerta", lb2be_e);
		}

		return ltsca_salida;
	}

	/**
	 * Permite obtener la tarifa de las alertas de titulares de derechos.
	 *
	 * @param atectat_tectat de atectat tectat
	 * @return el valor de tipo salida consultar tarifa alertas titulares
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaConsultarTarifaAlertasTitulares consultarTarifaAlertasTitulares(
	    TipoEntradaConsultarTarifaAlertasTitulares atectat_tectat
	)
	    throws RemoteException
	{
		TipoSalidaConsultarTarifaAlertasTitulares ltsctat_response;

		ltsctat_response = null;

		try
		{
			ltsctat_response = getGestionAlertasTitularesRemote()
					                   .consultarTarifaAlertasTitulares(
					    atectat_tectat, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("consultarTarifaAlertasTitulares", lb2be_e);
		}

		return ltsctat_response;
	}

	/**
	 * Permite inactivar una alerta inscrita.
	 *
	 * @param ateia_teia de ateia teia
	 * @return el valor de tipo salida inactivar alerta
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaInactivarAlerta inactivarAlerta(TipoEntradaInactivarAlerta ateia_teia)
	    throws RemoteException
	{
		TipoSalidaInactivarAlerta ltsia_return;

		ltsia_return = null;

		try
		{
			ltsia_return = getGestionAlertasTitularesRemote()
					               .inactivarAlerta(ateia_teia, getUserId(), getLocalIpAddress(), getRemoteIpAddress());
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("inactivarAlerta", lb2be_e);
		}

		return ltsia_return;
	}

	/**
	 * Permite verificar de manera previa las condiciones para la solicitud de la alerta de titular individual.
	 *
	 * @param atevsai_tevsai de atevsai tevsai
	 * @return el valor de tipo salida validar solicitud alerta individual
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaValidarSolicitudAlertaIndividual validarSolicitudAlertaIndividual(
	    TipoEntradaValidarSolicitudAlertaIndividual atevsai_tevsai
	)
	    throws RemoteException
	{
		TipoSalidaValidarSolicitudAlertaIndividual ltsvsai_response;

		ltsvsai_response = null;

		try
		{
			ltsvsai_response = getGestionAlertasTitularesRemote()
					                   .validarSolicitudAlertaIndividual(
					    atevsai_tevsai, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarSolicitudAlertaIndividual", lb2be_e);
		}

		return ltsvsai_response;
	}

	/**
	 * Permite verificar de manera previa las condiciones para la solicitud de las alertas de titualres masivas.
	 *
	 * @param atevsam_tevsam de atevsam tevsam
	 * @return el valor de tipo salida validar solicitud alerta masiva
	 * @throws RemoteException cuando se produce algun error en el proceso
	 */
	public TipoSalidaValidarSolicitudAlertaMasiva validarSolicitudAlertaMasiva(
	    TipoEntradaValidarSolicitudAlertaMasiva atevsam_tevsam
	)
	    throws RemoteException
	{
		TipoSalidaValidarSolicitudAlertaMasiva ltsvsam_response;

		ltsvsam_response = null;

		try
		{
			ltsvsam_response = getGestionAlertasTitularesRemote()
					                   .validarSolicitudAlertaMasiva(
					    atevsam_tevsam, getUserId(), getLocalIpAddress(), getRemoteIpAddress()
					);
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("validarSolicitudAlertaMasiva", lb2be_e);
		}

		return ltsvsam_response;
	}
}
