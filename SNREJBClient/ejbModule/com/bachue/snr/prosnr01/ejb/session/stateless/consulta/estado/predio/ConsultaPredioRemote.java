package com.bachue.snr.prosnr01.ejb.session.stateless.consulta.estado.predio;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.consulta.predio.ConsultaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import javax.ejb.Remote;


/**
 * Interface que contiene todos las propiedades ConsultaPredioRemote.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 20/02/2019
 */
@Remote
public interface ConsultaPredioRemote
{
	
	/**
	 * Consulta criterios.
	 *
	 * @param aopr_pr de ConsultaPredio
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio consultaCriterios(ConsultaPredio aopr_pr, String as_userId)
	    throws B2BException;

	/**
	 * Consultar.
	 *
	 * @param lcp_consultaPredio de ConsultaPredio
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio consultar(ConsultaPredio lcp_consultaPredio)
	    throws B2BException;

	/**
	 * Consultar acc.
	 *
	 * @param lcp_consultaPredio de ConsultaPredio
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio consultarAcc(ConsultaPredio lcp_consultaPredio)
	    throws B2BException;

	/**
	 * Find info tabs.
	 *
	 * @param aopr_pr de PredioRegistro
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param ab_withTurno de with turno
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio findInfoTabs(PredioRegistro aopr_pr, String as_userId, boolean ab_withTurno)
	    throws B2BException;

	/**
	 * Find info tabs.
	 *
	 * @param aopr_pr de PredioRegistro
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param ab_withTurno de ab with turno
	 * @param ab_consultaTraza de ab consulta traza
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio findInfoTabs(
	    PredioRegistro aopr_pr, String as_userId, boolean ab_withTurno, boolean ab_consultaTraza
	)
	    throws B2BException;

	/**
	 * Find info tabs.
	 *
	 * @param aopr_pr de PredioRegistro
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio findInfoTabs(PredioRegistro aopr_pr, String as_userId)
	    throws B2BException;

	/**
	 * Find info tabs bng.
	 *
	 * @param aopr_pr de PredioRegistro
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param ab_anotacionesAcc de ab anotaciones acc
	 * @param ab_anotacionesDashBoard de ab anotaciones dash board
	 * @param ab_consultaPredio de ab consulta predio
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio findInfoTabsBng(
	    PredioRegistro aopr_pr, String as_userId, boolean ab_anotacionesAcc, boolean ab_anotacionesDashBoard,
	    boolean ab_consultaPredio
	)
	    throws B2BException;

	/**
	 * Find info tabs bng.
	 *
	 * @param aopr_pr de PredioRegistro
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param ab_anotacionesAcc de ab anotaciones acc
	 * @return el valor de consulta predio
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ConsultaPredio findInfoTabsBng(PredioRegistro aopr_pr, String as_userId, boolean ab_anotacionesAcc)
	    throws B2BException;

	/**
	 * Obtener url mapa geografico.
	 *
	 * @param as_numeroPredial de numero predial
	 * @param as_userId Argumento de tipo <code>String</code> que contiene el usuario que realiza la operación.
	 * @param as_localIp Argumento de tipo <code>String</code> que contiene la ip local desde donde se realiza la operación.
	 * @param as_remoteIp Argumento de tipo <code>String</code> que contiene la ip remota desde donde se realiza la operación.
	 * @return el valor de string
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String obtenerUrlMapaGeografico(
	    String as_numeroPredial, String as_userId, String as_localIp, String as_remoteIp
	)
	    throws B2BException;
}
