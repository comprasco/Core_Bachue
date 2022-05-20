package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.business.consulta.digitador.antiguo.sistema.DigitadorAntiguoSistemaBusiness;
import com.bachue.snr.prosnr01.business.consulta.estado.predio.ConsultaPredioBusiness;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;
import com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio.ConsultaPredioRemote;

import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import org.perf4j.StopWatch;


/**
 * Clase que contiene todos las propiedades ConsultaPredioBean.
 *
 * @author Julian Vaca
 */
@javax.ejb.Stateless(name = "consultaPredio", mappedName = "consultaPredioMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ConsultaPredioBean implements ConsultaPredioRemote
{
	/** Propiedad icpb business. */
	private ConsultaPredioBusiness icpb_business;

	/** Propiedad icpb digitador business. */
	private DigitadorAntiguoSistemaBusiness icpb_digitadorBusiness;

	/** {@inheritdoc} */
	public ConsultaPredio consultaCriterios(ConsultaPredio aopr_pr, String as_userId)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusiness().consultaCriterios(aopr_pr, as_userId);

		Logger.log(lsw_watch, "ConsultaPredioBean", "consultaCriterios", as_userId, null, null, null);

		return la_datos;
	}

	/** {@inheritdoc} */
	public ConsultaPredio consultar(ConsultaPredio lcp_consultaPredio)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio locp_return;

		lsw_watch       = Logger.getNewStopWatch();
		locp_return     = getBusiness().consultar(lcp_consultaPredio);

		Logger.log(lsw_watch, "consultar", null, null, null, null, null);

		return locp_return;
	}

	/** {@inheritdoc} */
	public ConsultaPredio consultarAcc(ConsultaPredio lcp_consultaPredio)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio locp_return;

		lsw_watch       = Logger.getNewStopWatch();
		locp_return     = getBusiness().consultarAcc(lcp_consultaPredio);

		Logger.log(lsw_watch, "consultarAcc", null, null, null, null, null);

		return locp_return;
	}

	/** {@inheritdoc} */
	public ConsultaPredio findInfoTabs(PredioRegistro aopr_pr, String as_userId, boolean ab_withTurno)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusinessDigitador().findInfoTabs(aopr_pr, ab_withTurno);

		Logger.log(lsw_watch, "ConsultaPredioBean", "findInfoTabs", as_userId, null, null, null);

		return la_datos;
	}

	/**
	 * Metodo encargado de consultar toda la información de los tabs consulta del predio
	 *
	 * @param as_userId <code>String</code> que contiene el usuario que esta realizando la acción
	 * @param aopr_pr <code>PredioRegistro</code> del cual se extraerá la información necesaria para realizar la búsqueda
	 * @param ab_withTurno <code>boolean</code> indica si en el proceso se usará el turno.
	 * @param ab_consultaTraza <code>boolean</code> que indica si el metodo esta siendo usando por consulta trazabilidad
	 * @return <code>ConsultaPredio</code>
	 * @throws B2BException
	 */
	public ConsultaPredio findInfoTabs(
	    PredioRegistro aopr_pr, String as_userId, boolean ab_withTurno, boolean ab_consultaTraza
	)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusinessDigitador().findInfoTabs(aopr_pr, ab_withTurno, ab_consultaTraza);

		Logger.log(lsw_watch, "ConsultaPredioBean", "findInfoTabs", as_userId, null, null, null);

		return la_datos;
	}

	/** {@inheritdoc} */
	public ConsultaPredio findInfoTabs(PredioRegistro aopr_pr, String as_userId)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusinessDigitador().findInfoTabs(aopr_pr);

		Logger.log(lsw_watch, "ConsultaPredioBean", "findInfoTabs", as_userId, null, null, null);

		return la_datos;
	}

	/** {@inheritdoc} */
	public ConsultaPredio findInfoTabsBng(PredioRegistro aopr_pr, String as_userId, boolean ab_anotacionesAcc)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusinessDigitador().findInfoTabsBng(aopr_pr, as_userId, ab_anotacionesAcc);

		Logger.log(lsw_watch, "ConsultaPredioBean", "findInfoTabsBng", as_userId, null, null, null);

		return la_datos;
	}

	/** {@inheritdoc} */
	public ConsultaPredio findInfoTabsBng(
	    PredioRegistro aopr_pr, String as_userId, boolean ab_anotacionesAcc, boolean ab_anotacionesDashBoard,
	    boolean ab_consultaPredio
	)
	    throws B2BException
	{
		StopWatch      lsw_watch;
		ConsultaPredio la_datos;

		lsw_watch     = Logger.getNewStopWatch();
		la_datos      = getBusinessDigitador()
				                .findInfoTabsBng(
				    aopr_pr, as_userId, ab_anotacionesAcc, ab_anotacionesDashBoard, ab_consultaPredio
				);

		Logger.log(lsw_watch, "ConsultaPredioBean", "findInfoTabsBng", as_userId, null, null, null);

		return la_datos;
	}

	/** {@inheritdoc} */
	public String obtenerUrlMapaGeografico(
	    String as_numeroPredial, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_return;

		lsw_watch     = Logger.getNewStopWatch();
		ls_return     = getBusiness().obtenerUrlMapaGeografico(as_numeroPredial);

		Logger.log(
		    lsw_watch, "ConsultaPredioBean", "obtenerUrlMapaGeografico", as_userId, as_localIp, as_remoteIp, null
		);

		return ls_return;
	}

	/**
	 * Retorna el valor de business.
	 *
	 * @return el valor de business
	 */
	private ConsultaPredioBusiness getBusiness()
	{
		if(icpb_business == null)
			icpb_business = new ConsultaPredioBusiness();

		return icpb_business;
	}

	/**
	 * Retorna el valor de business digitador.
	 *
	 * @return el valor de business digitador
	 */
	private DigitadorAntiguoSistemaBusiness getBusinessDigitador()
	{
		if(icpb_digitadorBusiness == null)
			icpb_digitadorBusiness = new DigitadorAntiguoSistemaBusiness();

		return icpb_digitadorBusiness;
	}
}
