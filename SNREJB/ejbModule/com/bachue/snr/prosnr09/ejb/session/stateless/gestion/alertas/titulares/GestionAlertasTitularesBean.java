package com.bachue.snr.prosnr09.ejb.session.stateless.gestion.alertas.titulares;

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

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr09.business.gestion.alertas.titulares.GestionAlertasTitularesBusiness;

import com.bachue.snr.prosnr09.ejb.session.stateless.gestion.alertas.titulares.GestionAlertasTitularesRemote;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades GestionAlertasTitularesBean.
 *
 * @author Manuel Blanco
 */
@javax.ejb.Stateless(name = "GestionAlertasTitulares", mappedName = "gestionAlertasTitularesMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GestionAlertasTitularesBean implements GestionAlertasTitularesRemote
{
	/** Propiedad igatb business. */
	private GestionAlertasTitularesBusiness igatb_business;

	/**
	 * Retorna el valor de gestion alertas titulares business.
	 *
	 * @return el valor de gestion alertas titulares business
	 */
	public GestionAlertasTitularesBusiness getGestionAlertasTitularesBusiness()
	{
		if(igatb_business == null)
			igatb_business = new GestionAlertasTitularesBusiness();

		return igatb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaActualizarContactoAlerta actualizarContactoAlerta(
	    TipoEntradaActualizarContactoAlerta ateaca_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                          lsw_watch;
		TipoSalidaActualizarContactoAlerta ltsaca_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsaca_response = getGestionAlertasTitularesBusiness()
				                  .actualizarContactoAlerta(ateaca_request, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "GestionAlertasTitularesBean", "actualizarContactoAlerta", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsaca_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarAlerta consultarAlerta(
	    TipoEntradaConsultarAlerta atevsai_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		TipoSalidaConsultarAlerta ltsca_return;

		lsw_watch     = Logger.getNewStopWatch();

		ltsca_return = getGestionAlertasTitularesBusiness().consultarAlerta(atevsai_request);

		Logger.log(
		    lsw_watch, "GestionAlertasTitularesBean", "consultarAlerta", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsca_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarTarifaAlertasTitulares consultarTarifaAlertasTitulares(
	    TipoEntradaConsultarTarifaAlertasTitulares atectat_request, String as_userId, String as_localIp,
	    String                                     as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                 lsw_watch;
		TipoSalidaConsultarTarifaAlertasTitulares ltsctat_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsctat_response = getGestionAlertasTitularesBusiness().consultarTarifaAlertasTitulares(atectat_request);

		Logger.log(
		    lsw_watch, "GestionAlertasTitularesBean", "consultarTarifaAlertasTitulares", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltsctat_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaInactivarAlerta inactivarAlerta(
	    TipoEntradaInactivarAlerta ateia_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		TipoSalidaInactivarAlerta ltsia_return;

		lsw_watch     = Logger.getNewStopWatch();

		ltsia_return = getGestionAlertasTitularesBusiness().inactivarAlerta(ateia_request, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "GestionAlertasTitularesBean", "inactivarAlerta", as_userId, as_localIp, as_remoteIp, null
		);

		return ltsia_return;
	}

	/** {@inheritdoc} */
	public TipoSalidaValidarSolicitudAlertaIndividual validarSolicitudAlertaIndividual(
	    TipoEntradaValidarSolicitudAlertaIndividual atevsai_request, String as_userId, String as_localIp,
	    String                                      as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                                  lsw_watch;
		TipoSalidaValidarSolicitudAlertaIndividual ltsvsai_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsvsai_response = getGestionAlertasTitularesBusiness().validarSolicitudAlertaIndividual(atevsai_request);

		Logger.log(
		    lsw_watch, "GestionAlertasTitularesBean", "validarSolicitudAlertaIndividual", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return ltsvsai_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaValidarSolicitudAlertaMasiva validarSolicitudAlertaMasiva(
	    TipoEntradaValidarSolicitudAlertaMasiva atevsam_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                              lsw_watch;
		TipoSalidaValidarSolicitudAlertaMasiva ltsvsai_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsvsai_response = getGestionAlertasTitularesBusiness().validarSolicitudAlertaMasiva(atevsam_request);

		Logger.log(
		    lsw_watch, "GestionAlertasTitularesBean", "validarSolicitudAlertaMasiva", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return ltsvsai_response;
	}
}
