package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.cuentaCupos;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.cuentaCupos.ConsultaCuentaCuposBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.cuentaCupos.ConsultaCuentaCupos;

import org.perf4j.StopWatch;


/**
 * Clase para la implementación de los metodos de la interfaz remota del proceso de consulta de cuenta cupos.
 *
 * @author Manuel Blanco
 *
 */
@javax.ejb.Stateless
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaCuentaCuposBean implements ConsultaCuentaCuposRemote
{
	/** The iccb cuenta cupo business. */
	private ConsultaCuentaCuposBusiness iccb_cuentaCupoBusiness;

	/** {@inheritdoc} */
	public ConsultaCuentaCupos obtenerMovimientosCuentaCupo(
	    ConsultaCuentaCupos accc_datosConsulta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		ConsultaCuentaCupos lccc_consulta;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_consulta = getBusiness().obtenerMovimientosCuentaCupo(accc_datosConsulta);

		Logger.log(
		    lsw_watch, "ConsultaCuentaCuposBean", "obtenerMovimientosCuentaCupo", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lccc_consulta;
	}

	/** {@inheritdoc} */
	public byte[] generarDocumentoMovimientos(
	    ConsultaCuentaCupos accc_datosConsulta, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		byte[]    lba_documento;

		lsw_watch     = Logger.getNewStopWatch();

		lba_documento = getBusiness().generarDocumentoMovimientos(accc_datosConsulta, as_userId);

		Logger.log(
		    lsw_watch, "ConsultaCuentaCuposBean", "generarDocumentoMovimientos", as_userId, as_localIp, as_remoteIp,
		    null
		);

		return lba_documento;
	}

	/** {@inheritdoc} */
	public ConsultaCuentaCupos obtenerDatosUltimaRecarga(
	    String as_idCuentaCupo, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch           lsw_watch;
		ConsultaCuentaCupos lccc_consulta;

		lsw_watch     = Logger.getNewStopWatch();

		lccc_consulta = getBusiness().obtenerDatosUltimaRecarga(as_idCuentaCupo);

		Logger.log(
		    lsw_watch, "ConsultaCuentaCuposBean", "obtenerDatosUltimaRecarga", as_userId, as_localIp, as_remoteIp, null
		);

		return lccc_consulta;
	}

	/**
	 * Obtiene el valor de business.
	 *
	 * @return el valor de business
	 */
	private ConsultaCuentaCuposBusiness getBusiness()
	{
		if(iccb_cuentaCupoBusiness == null)
			iccb_cuentaCupoBusiness = new ConsultaCuentaCuposBusiness();

		return iccb_cuentaCupoBusiness;
	}
}
