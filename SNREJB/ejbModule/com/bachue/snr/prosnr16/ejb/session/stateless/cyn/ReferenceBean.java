package com.bachue.snr.prosnr16.ejb.session.stateless.cyn;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.ejb.session.stateless.Logger;

import com.bachue.snr.prosnr01.model.sdb.pgn.Constantes;

import com.bachue.snr.prosnr16.business.cyn.reference.ReferenceBusiness;

import org.perf4j.StopWatch;

import java.util.Collection;


/**
 * Clase que contiene todos las propiedades ReferenceBean.
 *
 * @author  Julián David Vaca Rodriguez
 * Fecha de Creacion: 30/03/2020
 */
@javax.ejb.Stateless(name = "Referencecyn", mappedName = "referencecynMappedName")
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.BEAN)
public class ReferenceBean implements ReferenceRemote
{
	/** Propiedad irb business. */
	private ReferenceBusiness irb_business;

	/** {@inheritDoc} */
	public Constantes findConstantesById(String as_parametro)
	    throws B2BException
	{
		StopWatch  lsw_watch;
		Constantes lc_datos;

		lsw_watch     = Logger.getNewStopWatch();
		lc_datos      = getReferenceBusiness().findConstantesById(as_parametro);

		Logger.log(lsw_watch, "ReferenceBean", "findConstantesById", null, null, null, null);

		return lc_datos;
	}

	/** {@inheritDoc} */
	public String obtenerCaracterConstante(String as_idConstante)
	    throws B2BException
	{
		StopWatch lsw_watch;
		String    ls_constante;

		lsw_watch        = Logger.getNewStopWatch();
		ls_constante     = getReferenceBusiness().obtenerCaracterConstante(as_idConstante);

		Logger.log(lsw_watch, "ReferenceBean", "obtenerCaracterConstante", null, null, null, null);

		return ls_constante;
	}

	/** {@inheritDoc} */
	public Collection<String> obtenerIdConstanesPorCaracterIdLikeCaracter(String as_IdLike, String as_caracter)
	    throws B2BException
	{
		Collection<String> lcs_idConstantes;
		StopWatch          lsw_watch;

		lsw_watch            = Logger.getNewStopWatch();
		lcs_idConstantes     = getReferenceBusiness().obtenerIdConstanesPorCaracterIdLikeCaracter(
			    as_IdLike, as_caracter
			);

		Logger.log(lsw_watch, "ReferenceBean", "obtenerIdConstanesPorCaracterIdLikeCaracter", null, null, null, null);

		return lcs_idConstantes;
	}

	/** {@inheritDoc} */
	public void updateCaracter(String as_id, String as_caracter, String as_userId)
	    throws B2BException
	{
		StopWatch lsw_watch;

		lsw_watch = Logger.getNewStopWatch();

		getReferenceBusiness().updateCaracter(as_id, as_caracter, as_userId);

		Logger.log(lsw_watch, "ReferenceBean", "updateCaracter", as_userId, null, null, null);
	}

	/**
	 * Retorna el valor de reference business.
	 *
	 * @return el valor de reference business
	 */
	private ReferenceBusiness getReferenceBusiness()
	{
		if(irb_business == null)
			irb_business = new ReferenceBusiness();

		return irb_business;
	}
}
