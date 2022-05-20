package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.digitador.antiguo.sistema;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistemaBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistemaRemote;

import com.bachue.snr.prosnr01.model.antiguoSistema.Anotacion;
import com.bachue.snr.prosnr01.model.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistema;
import com.bachue.snr.prosnr01.model.sdb.acc.AccPredioRegistro;
import com.bachue.snr.prosnr01.model.sdb.acc.AnotacionPredio;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades DigitadorAntiguoSistemaBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "DigitadorAntiguoSistema", mappedName = "digitadorAntiguoSistemaMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class DigitadorAntiguoSistemaBean implements DigitadorAntiguoSistemaRemote
{
	/** Propiedad idas business. */
	private DigitadorAntiguoSistemaBusiness idas_business;

	/** {@inheritdoc} */
	public Collection<AccPredioRegistro> findAccMatriculasByTurnoAntSistema(
	    AccPredioRegistro AccPredioRegistro, String as_userId
	)
	    throws B2BException
	{
		StopWatch                     lsw_watch;
		Collection<AccPredioRegistro> la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusiness().findAccMatriculasByTurnoAntSistema(AccPredioRegistro, as_userId);

		Logger.log(
		    lsw_watch, "DigitadorAntiguoSistemaBean", "findAccMatriculasByTurno", as_userId, null, null, la_datos
		);

		return la_datos;
	}

	/** {@inheritdoc} */
	public DigitadorAntiguoSistema findDigitadorAntiguoSistema(
	    AccPredioRegistro AccPredioRegistro, long al_etapa, String as_userId
	)
	    throws B2BException
	{
		StopWatch               lsw_watch;
		DigitadorAntiguoSistema la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusiness().findDigitadorAntiguoSistema(AccPredioRegistro, al_etapa, as_userId);

		Logger.log(
		    lsw_watch, "DigitadorAntiguoSistemaBean", "findDigitadorAntiguoSistema", as_userId, null, null, null
		);

		return la_datos;
	}

	/** {@inheritdoc} */
	public Collection<Anotacion> validarReordenamientoPorFechaAnotacion(
	    Collection<Anotacion> aca_anotacionesAgregadas, AnotacionPredio aap_anotacionPredio, String as_userId,
	    String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		Collection<Anotacion> lca_anotacionesAgregadas;
		StopWatch             lsw_watch;

		lsw_watch                    = Logger.getNewStopWatch();
		lca_anotacionesAgregadas     = getBusiness()
				                               .validarReordenamientoPorFechaAnotacion(
				    aca_anotacionesAgregadas, aap_anotacionPredio
				);

		Logger.log(
		    lsw_watch, "DigitadorAntiguoSistemaBean", "validarReordenamientoPorFechaAnotacion", as_userId, as_localIp,
		    as_remoteIp, null
		);

		return lca_anotacionesAgregadas;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private DigitadorAntiguoSistemaBusiness getBusiness()
	{
		if(idas_business == null)
			idas_business = new DigitadorAntiguoSistemaBusiness();

		return idas_business;
	}
}
