package com.bachue.snr.prosnr12.ejb.session.stateless.gestionCuentaCupos;

import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoEntradaActualizarEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.actualizarentidad.v1.TipoSalidaActualizarEntidad;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoEntradaCancelarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.cancelarusuario.v1.TipoSalidaCancelarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoEntradaConsultarSaldosNotaCredito;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarSaldosNotaCredito.v1.TipoSalidaConsultarSaldosNotaCredito;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoEntradaConsultarIDCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultaridcuentacupo.v1.TipoSalidaConsultarIDCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoEntradaConsultarMovimientos;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarmovimientos.v1.TipoSalidaConsultarMovimientos;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoEntradaConsultarSaldo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarsaldo.v1.TipoSalidaConsultarSaldo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoEntradaConsultarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuario.v1.TipoSalidaConsultarUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoEntradaConsultarUsuarios;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.consultarusuarios.v1.TipoSalidaConsultarUsuarios;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoEntradaInscribirUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.inscribirusuario.v1.TipoSalidaInscribirUsuario;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoEntradaPagarCuentaCupo;
import co.gov.supernotariado.www.schemas.bachue.cb.gestioncuentacupos.pagarcuentacupo.v1.TipoSalidaPagarCuentaCupo;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr12.business.gestionCuentaCupos.GestionCuentaCuposBusiness;

import org.perf4j.StopWatch;


/**
 * Clase para la implementación de los metodos de la interfaz remota del servicio Gestion Cuenta Cupos.
 *
 * @author Manuel Blanco
 *
 */
@javax.ejb.Stateless(name = "GestionCuentaCupos", mappedName = "gestionCuentaCuposMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class GestionCuentaCuposBean implements GestionCuentaCuposRemote
{
	/** Propiedad igccb business. */
	private GestionCuentaCuposBusiness igccb_business;

	/**
	 * Retorna el valor de gestion cuenta cupos business.
	 *
	 * @return el valor de gestion cuenta cupos business
	 */
	private GestionCuentaCuposBusiness getBusiness()
	{
		if(igccb_business == null)
			igccb_business = new GestionCuentaCuposBusiness();

		return igccb_business;
	}

	/** {@inheritdoc} */
	public TipoSalidaInscribirUsuario inscribirUsuario(
	    TipoEntradaInscribirUsuario ateiu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		TipoSalidaInscribirUsuario ltsiu_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsiu_response = getBusiness().inscribirUsuario(ateiu_request, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "GestionCuentaCuposBean", "inscribirUsuario", as_userId, as_localIp, as_remoteIp, null);

		return ltsiu_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaCancelarUsuario cancelarUsuario(
	    TipoEntradaCancelarUsuario atecu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		TipoSalidaCancelarUsuario ltscu_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscu_response = getBusiness().cancelarUsuario(atecu_request, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "GestionCuentaCuposBean", "cancelarUsuario", as_userId, as_localIp, as_remoteIp, null);

		return ltscu_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarUsuario consultarUsuario(
	    TipoEntradaConsultarUsuario atecu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                  lsw_watch;
		TipoSalidaConsultarUsuario ltscu_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscu_response = getBusiness().consultarUsuario(atecu_request);

		Logger.log(lsw_watch, "GestionCuentaCuposBean", "consultarUsuario", as_userId, as_localIp, as_remoteIp, null);

		return ltscu_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarUsuarios consultarUsuarios(
	    TipoEntradaConsultarUsuarios atecu_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		TipoSalidaConsultarUsuarios ltscu_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscu_response = getBusiness().consultarUsuarios(atecu_request);

		Logger.log(lsw_watch, "GestionCuentaCuposBean", "consultarUsuarios", as_userId, as_localIp, as_remoteIp, null);

		return ltscu_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarSaldo consultarSaldo(
	    TipoEntradaConsultarSaldo atecs_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                lsw_watch;
		TipoSalidaConsultarSaldo ltscs_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscs_response = getBusiness().consultarSaldo(atecs_request);

		Logger.log(lsw_watch, "GestionCuentaCuposBean", "consultarSaldo", as_userId, as_localIp, as_remoteIp, null);

		return ltscs_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarMovimientos consultarMovimientos(
	    TipoEntradaConsultarMovimientos atecm_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                      lsw_watch;
		TipoSalidaConsultarMovimientos ltscm_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscm_response = getBusiness().consultarMovimientos(atecm_request);

		Logger.log(
		    lsw_watch, "GestionCuentaCuposBean", "consultarMovimientos", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscm_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaPagarCuentaCupo pagarCuentaCupo(
	    TipoEntradaPagarCuentaCupo atecm_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                 lsw_watch;
		TipoSalidaPagarCuentaCupo ltscm_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscm_response = getBusiness().pagarCuentaCupo(atecm_request, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "GestionCuentaCuposBean", "pagarCuentaCupo", as_userId, as_localIp, as_remoteIp, null);

		return ltscm_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarIDCuentaCupo consultarIDCuentaCupo(
	    TipoEntradaConsultarIDCuentaCupo atecicc_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                       lsw_watch;
		TipoSalidaConsultarIDCuentaCupo ltscicc_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscicc_response = getBusiness().consultarIDCuentaCupo(atecicc_request);

		Logger.log(
		    lsw_watch, "GestionCuentaCuposBean", "consultarIDCuentaCupo", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscicc_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaActualizarEntidad actualizarEntidad(
	    TipoEntradaActualizarEntidad ateae_request, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		TipoSalidaActualizarEntidad ltsae_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltsae_response = getBusiness().actualizarEntidad(ateae_request, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "GestionCuentaCuposBean", "actualizarEntidad", as_userId, as_localIp, as_remoteIp, null);

		return ltsae_response;
	}

	/** {@inheritdoc} */
	public TipoSalidaConsultarSaldosNotaCredito consultarSaldosNotaCredito(
	    TipoEntradaConsultarSaldosNotaCredito atecsnc_entrada, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                            lsw_watch;
		TipoSalidaConsultarSaldosNotaCredito ltscsnt_response;

		lsw_watch     = Logger.getNewStopWatch();

		ltscsnt_response = getBusiness().consultarSaldosNotaCredito(atecsnc_entrada, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "GestionCuentaCuposBean", "consultarSaldosNotaCredito", as_userId, as_localIp, as_remoteIp, null
		);

		return ltscsnt_response;
	}
}
