package com.bachue.snr.prosnr01.ejb.session.stateless.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.cuentaCupos.CuentaCuposBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.calificacion.TramiteCantidad;
import com.bachue.snr.prosnr01.model.cuentaCupos.AprobacionCuentaCupos;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;

import org.perf4j.StopWatch;

import java.math.BigDecimal;

import java.util.Collection;


/**
 * Clase para la implementación de los metodos de la interfaz remota del proceso de aprobación de cuenta cupos.
 *
 * @author Manuel Blanco
 *
 */
@javax.ejb.Stateless(name = "CuentaCupos", mappedName = "cuentaCuposMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class CuentaCuposBean implements CuentaCuposRemote
{
	/** Propiedad iccb cuenta cupo business. */
	private CuentaCuposBusiness iccb_cuentaCupoBusiness;

	/** {@inheritdoc} */
	public void aprobarSolicitudCuentaCupo(
	    AprobacionCuentaCupos aacc_datosCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().aprobarSolicitudCuentaCupo(aacc_datosCuenta, as_userId, as_remoteIp);

		Logger.log(
		    lsw_watch, "CuentaCuposBean", "aprobarSolicitudCuentaCupo", as_userId, as_localIp, as_remoteIp, null
		);
	}

	/** {@inheritdoc} */
	public void inactivarCuentaCupo(
	    AprobacionCuentaCupos aacc_datosCuenta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().inactivarCuentaCupo(aacc_datosCuenta, as_userId, as_remoteIp);

		Logger.log(lsw_watch, "CuentaCuposBean", "inactivarCuentaCupo", as_userId, as_localIp, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public CuentaCupo buscarPorIdCuentaCupo(
	    String as_idCuentaCupo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		CuentaCupo lcc_return;

		lsw_watch     = Logger.getNewStopWatch();

		lcc_return = getBusiness().buscarPorIdCuentaCupo(as_idCuentaCupo);

		Logger.log(lsw_watch, "CuentaCuposBean", "buscarPorIdCuentaCupo", as_userId, as_localIp, as_remoteIp, null);

		return lcc_return;
	}

	/** {@inheritdoc} */
	public void desencolarPago(String as_remoteIp)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getBusiness().desencolarPago(as_remoteIp);

		Logger.log(lsw_watch, "CuentaCuposBean", "desencolarPago", null, null, as_remoteIp, null);
	}

	/** {@inheritdoc} */
	public byte[] generarConstanciaDecisionAprobacion(
	    String as_idSolicitud, String as_idCuentaCupo, String as_observaciones, boolean ab_aprobar,
	    BigDecimal lbd_valorMinimo, BigDecimal lbd_valorMaximo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_return;

		lsw_watch     = Logger.getNewStopWatch();

		lba_return = getBusiness()
				             .generarConstanciaDecisionAprobacion(
				    as_idSolicitud, as_idCuentaCupo, as_observaciones, ab_aprobar, lbd_valorMinimo, lbd_valorMaximo
				);

		Logger.log(
		    lsw_watch, "CuentaCuposBean", "generarConstanciaDecisionAprobacion", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lba_return;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> obtenerBandejaDetalle(
	    TramiteCantidad atc_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lctc_tramites;

		lsw_watch     = Logger.getNewStopWatch();

		lctc_tramites = getBusiness().obtenerBandejaDetalle(atc_parametros);

		Logger.log(
		    lsw_watch, "CuentaCuposBean", "obtenerBandejaDetalle", as_userId, as_localIp, as_remoteIp, lctc_tramites
		);

		return lctc_tramites;
	}

	/** {@inheritdoc} */
	public Collection<TramiteCantidad> obtenerBandejaEntrada(
	    TramiteCantidad atc_parametros, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch                   lsw_watch;
		Collection<TramiteCantidad> lctc_tramites;

		lsw_watch     = Logger.getNewStopWatch();

		lctc_tramites = getBusiness().obtenerBandejaEntrada(atc_parametros);

		Logger.log(
		    lsw_watch, "CuentaCuposBean", "obtenerBandejaEntrada", as_userId, as_localIp, as_remoteIp, lctc_tramites
		);

		return lctc_tramites;
	}

	/** {@inheritdoc} */
	public AprobacionCuentaCupos obtenerDatosTurno(
	    String as_idTurno, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch             lsw_watch;
		AprobacionCuentaCupos lctc_tramites;

		lsw_watch     = Logger.getNewStopWatch();

		lctc_tramites = getBusiness().obtenerDatosTurno(as_idTurno);

		Logger.log(lsw_watch, "CuentaCuposBean", "obtenerDatosTurno", as_userId, as_localIp, as_remoteIp, null);

		return lctc_tramites;
	}

	/**
	 * Obtiene el valor de business.
	 *
	 * @return el valor de business
	 */
	private CuentaCuposBusiness getBusiness()
	{
		if(iccb_cuentaCupoBusiness == null)
			iccb_cuentaCupoBusiness = new CuentaCuposBusiness();

		return iccb_cuentaCupoBusiness;
	}
}
